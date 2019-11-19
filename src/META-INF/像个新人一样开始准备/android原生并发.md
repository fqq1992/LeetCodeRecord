此处并发仅涉及
    Handler机制、HandlerThread、AsyncTask、IntentService。
    

1、Handler为android实现机制。主要通过Looper来完成消息的分发。Looper内则使用来完成。
    
    Handler主要有5个数据成员。
    mLooper         
    mAsynchronous   true，那么Handler插入的消息就是异步的
    mCallback
    mQueue
    mMessenger      Handler内部实现的一个信鸽（Bindler），适用于跨进程通讯。
    
    
    核心方法。
    
    enqueueMessag(MessageQueue queue, Message msg, long uptimeMillis) 将消息插入到消息队列中去，设置target和是否异步。
    
    dispatchMessage(msg) 消息有Runnable，那么先执行Runnable的run().如果Handler的CallBack不为空，且mCallback.handleMessage(msg)消耗了消息.
                         后续执行handleMessage(msg).
    sendMessageAtFrontOfQueue(Message msg).
        立即发送Message到队列，而且是放在队列的最前面
        
        
    补丁版本。
    当Handler发送异步消息时（mAsynchronous = true 或者自己msg.setAsynchronous(true)）
    如果启动内存屏障 则优先执行。异步消息 同时同步消息阻塞。
    
    使用场景 ：View更新时，draw、requestLayout、invalidate等很多地方都调用了ViewRootImpl#scheduleTraversals()。
        
        //开启内存屏障
        mTraversalBarrier = mHandler.getLooper().getQueue().postSyncBarrier();//开启内存屏障。
        //移除内存屏障
        mHandler.getLooper().getQueue().removeSyncBarrier(mTraversalBarrier);
        
其中Message，有个消息池的概念，优先考虑Message.obtain()来获取消息对象。



2、HandlerThread 是一个Thread的子类。在run()方法中 调用了
```
public void run() {
    mTid = Process.myTid(); //获取当前线程的id
    Looper.prepare();   
    synchronized (this) {
        mLooper = Looper.myLooper();
        notifyAll();
    }
    Process.setThreadPriority(mPriority);   // 设置线程优先级
    onLooperPrepared();     // 可以重写。
    Looper.loop();      // 开始消息循环
    mTid = -1;
}
```

对外提供 getLooper()/getThreadHandler()方法。用来对外提供 线程间消息交互的方式。


终止线程 提供了quit()、quitSafely()

```
public boolean quit() { //quitSafely()
    Looper looper = getLooper();
        if (looper != null) {
        looper.quit(); //quitSafely()  最终有messageQueue来实现。
        return true;
    }
    return false;
}


两个方法的区别为：
    quit方法会将消息队列中的所有消息移除(延迟消息和非延迟消息)。
    quitSafely 会将消息队列所有延迟消息移除。非延迟消息则派发出去让Handler去处理。
    quitSafely相比于quit方法安全之处在于清空消息之前会派发所有的非延迟消息。
```


3、AsyncTask 是系统层面提供的一个简易版异步库。

内部通过线程池，以及MainHandler来完成异步工作线程与UI线程的交互。

构造参数可以传Handler or Looper 用来完成子线程间的通信。
另外一个需要注意的点， 如果直接使用 AsyncTask.execute().默认是排队顺序执行。
可以通过executeOnExecutor（Executor exec,Params... params）来指定其他线程池完成。

```
  private class MyTask extends AsyncTask<Params, Progress, Result> {
        ....
      // 方法1：onPreExecute（）
      // 作用：执行 线程任务前的操作
      // 注：根据需求复写
      @Override
      protected void onPreExecute() {
           ...
        }
      // 方法2：doInBackground（）
      // 作用：接收输入参数、执行任务中的耗时操作、返回 线程任务执行的结果
      // 注：必须复写，从而自定义线程任务
      @Override
      protected String doInBackground(String... params) {
            ...// 自定义的线程任务
            // 可调用publishProgress（）显示进度, 之后将执行onProgressUpdate（）
             publishProgress(count);
         }
      // 方法3：onProgressUpdate（）
      // 作用：在主线程 显示线程任务执行的进度
      // 注：根据需求复写
      @Override
      protected void onProgressUpdate(Integer... progresses) {
            ...
        }
      // 方法4：onPostExecute（）
      // 作用：接收线程任务执行结果、将执行结果显示到UI组件
      // 注：必须复写，从而自定义UI操作
      @Override
      protected void onPostExecute(String result) {
         ...// UI操作
        }
      // 方法5：onCancelled()
      // 作用：将异步任务设置为：取消状态
      @Override
        protected void onCancelled() {
        ...
        }
  }
```
    
4、IntentService

继承至Service,在onCreate中创建HandlerThread,并启动，保存loop,handler，来完成对线程的通信。
完成了使命之后会自动停止，适合需要在工作线程处理UI无关任务的场景。

如果启动 IntentService 多次，那么每一个耗时操作会以工作队列的方式在 IntentService 的 onHandleIntent 回调方法中执行，依次去执行，使用串行的方式，执行完自动结束。

中间停止服务后，后续的事件得不到执行。

```
@Override
public void onDestroy() {
    mServiceLooper.quit();
}

```
    
    
    
    