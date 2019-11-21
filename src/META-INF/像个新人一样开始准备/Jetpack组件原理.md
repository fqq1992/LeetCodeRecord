###LiveData

内部仅3个文件。

    LiveData 抽象类。
    Observer 接口。
    MutableLiveData 实现类。
    
其中LiveData 完成主要功能。依赖于Lifecycle 对外提供 订阅、移除订阅。postValues等。粘性消息。
当生命周期ON_DESTROY的时候 remove掉全部注册。
内部SafeIterableMap管理所有监听。持有强引用。



通过同包名可以继承MutableLiveData类。然后重新实现LifecycleBoundObserver的构造方法。
可以实现非主线程发送消息。以及，非粘性消息的功能。



###Lifecycle

核心中的核心。

整体构造。
    
    LifecycleOwner 接口。由fragment或者Activity自己实现getLifecycle();
    
    Lifecycle 抽象类。提供 addObserver/remove()、getCurrentState();
    
    LifecycleRegistry 具体实现类。
    
    ReportFragment 状态变更的实际操作类。依赖与ReportFragment跟随宿主Activity生命周期的变化。
    
    LifecycleEventObserver 状态变更监听类。 




###ViewModule

基本使用方式需要搭配。（api "android.arch.lifecycle:extensions:1.1.1"）使用。

其中 ViewModuleProviders.of(this).get(ViewModule.class);

    of(ViewModelStoreOwner owner)返回ViewModelProvider；
    get(class clazz)
    
Activity实现ViewModelStoreOwner 数据

NonConfigurationInstances则在Activity中静态实现共享。

ViewModelStore 中存在HashMap来保存 viewModule. key是XXX+ViewModule.getCanonicalName()。

ViewModel.onCleared() 资源回收；
    
    protected void onDestroy() {
        super.onDestroy();
        if (mViewModelStore != null && !isChangingConfigurations()) {
            mViewModelStore.clear();
        }
        mFragments.dispatchDestroy();
    }

也就是说，ViewModelStore 存在于Activity中 内存保存。会在横竖屏恢复。以求达到共享同一module的情况。




