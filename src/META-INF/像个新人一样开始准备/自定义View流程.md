总览：
    
act.setContentView(),到view显示到屏幕上， 主要是通过ViewRoot来连接。WindowManager 和 DecorView。
具体实现类为ViewRootImpl.

    Activity创建完毕后，会讲DecorView添加到window，同时创建了ViewRootImlp，将它和DecorView绑定。
    

~~~
View的绘制从 ViewRootImpl.performTraversals开始。performTraversals()函数中相继执行Measure、Layout、Draw三个流程
拿到页面布局后，在 Surface中 通过IPC协议交给 WindowMangerService。最终由SurfaceFlinger来展示到屏幕上。
~~~
展开特别多，在此主要速过一遍自定义View应用层点。一、View到屏幕的主要流程。二、主要属性方法。三、事件分发。

一、三大方法。
    
1、Measure篇
    
    首先从过程上分析
        View的 final measure()->onMeasure()->setMeasureDimension()->getDefaultSize()->完成测量。
        其中 getDefaultSize（）：根据View的宽高 测量规则计算。
        
        ViewGroup的流程则要在onMeasure后插入 measureChildren（）->measureChild（）->getChildMeasureSpec() 等遍历子View 测量&合并的过程。
        
        
其中核心参数有两个 

ViewGroup.LayoutParams：设置视图的宽度和高度等布局参数。

MeasureSpec类：测量规格类，测量View大小的依据。
    
    MeasureSpec，由两部分组成32位int类型参数，测量模式（前两位，mode）+测量大小(size，低30位)
    
    主要由父容器的Measure以及自身的LayoutParams共同决定。三种Mode
        模式             描述                       场景
        UNSPECIFIED     父视图不约束子视图，          系统内部
        EXACTLY         父视图给子视图指定确切尺寸
                        子视图必须在尺寸内           match or 100dp  
                               
        AT_MOST         父视图给子视图指定最大尺寸   wrap_content
                        子视图保证自身 
                        &所有子视图在范围内
        
   
   
   总结：
        
        当普通View是精确模式的时候，不管父的MeasureSpec是啥，View就是那么大。
        当普通View是match模式时候，父容器是精确模式，那么view大小就是剩下的大小。
        如果父容器也是match模式，那么view也是最大模式，但是最大不能超过父容器剩余空间。
        当view是warp模式的时候，不管父容器是什么模式，view总是最大模式，但是不能超过父容器剩下的空间。
        UNSPECIFIED 这个模式一般用于在Measure过程中一般不需要理睬。
        总结一波就是精确模式，我多大就是多大。 match模式，最大不能超过父容器剩余空间，模式和父容器一样。warp模式，最大化，不能超过父容器。
        
2、layout篇

layout():计算视图的位置，即Left、Top、Right、Bottom四点的位置

    首先从流程上分析
        View：start->layout()(PS:计算自己的位置 通过setFrame()/setOpticalFrame（）)
                   ->onLayout()（空实现）->完成。
                   
        ViewGroup 则在onLayout 后 遍历子View的layout方法完成测量合并的过程。
        
其中有几个重要方法。getWidth()、getHeight()与getMeasureWidth()、getMeasureHeight()。

    getWidth()/getHeight():获取View最终的宽高。 在layout的过程中赋值。主要在layout后的过程中使用。
    getMeasureWidth()/getMeasureHeight():获取View测量的宽高。赋值在measure过程中，主要在layout中调用。
    

3、Draw部分。

主要是完成 View视图的绘制

    同前面几部分。流程
        View：draw()->drawBackground()->onDraw()->dispatchDraw()->onDrawScrollBars().
        
其中onDraw()主要重写方法。通过其中的canvas一些方法来完成整个流程。
    
    绘制主要通过 paint/canvas，以及一些辅助类来实现。
    Paint 主要是设置颜色、阴影、粗细等。
    
    Canvas 则是对绘制的辅助——范围裁切和几何变换。
    
    常见的些辅助类有
        composeShader   混合两个Shader
        PorterDuffColorFilter   增加单色的ColorFilter
        Xfermode        设置绘制内容与View中已知内容的混合。 
        
    BitmapShader可以来做填充 实现圆形头像的问题。Xfermode也可以。
    
    

二、主要属性方法。

    getLeft()、getTop()、getRight()、getBottom()
    getWidth()、getHeight()最终宽高。
    分别对应View到父容器的距离。在onLayout()之后 才能获取到正常值。
    
    对应的解决方式1、View.Post(Runnable).2、Activity.onWindowFocusChanged().3、OnGlobalLayoutListener().
    
    requestLayout(). 是重新measure、layout。
    invalidate（）.则不会走上诉过程，直接draw->onDraw()等。
    
    
    onFinishInflate() 
        View中所有的子控件均被映射成xml后触发，从这里getChildAt才能得到子View
    onSizeChanged()
        在控件大小发生改变时调用。所以这里初始化会被调用一次。
    requestDisallowInterceptTouchEvent()
        阻止上层View的拦截事件
    getScrollX/getScrollY
        返回当前滑动View左边界的位置，其实获取的值就是这块幕布在窗口左边界时的x/y坐标，即内容滑动的大小
    scrollTo（int x,int y）
        让View相对于初始的位置滚到某点。
    scrollBy（int x,int y）
        让View相对应现在的位置。滑动某距离。
    offsetLeftAndRight()与offsetTopAndBottom()
        使view左右/上下移动。
        

三、事件分发。

采用责任链设计模式。自上往下依次传递
    
    主要流程为activity.dispatchTouchEvent -->View.dispatchTouchEvent()-->ViewGroup.onTouchListener --> onTouch()--> (无消费，递归向下传递)--> onTouchEvent() --> onClick().
    
 
注意问题。
    
    OnTouchListner的优先级高于onTouchEvent,如果OnTouchListner返回true，onTouchEvent就不执行,反之,则会调用。
    如果事件一直没有被消费，最后会传给Activity，如果Activity也不需要就被抛弃。
    判断事件是否被消费是根据返回值，而不是根据你是否使用了事件。
    onTouchListener—–>onTouchEvent—>onclick。优先级
    Android事件分发机制中，主要两个过程，一个是向下分发的过程，该过程主要调用dispatchTouchEvent()，还有一个是向上返回的过程，主要依靠onTouchEvent()方法
    requestDisallowInterceptTouchEvent 可以在子元素中干扰父元素的事件分发.down事件干扰不了.
    
多指问题。
    
    ACTION_DOWN：触控时，总是第一个被触发，之后就不会再触发
    ACTION_POINTER_DOWN：只要还有触控点在屏幕上，之后手指下去都是之触发这个事件
    ACTION_UP：触控点离开时，仅当最后一个触控点消失时才会触发
    ACTION_POINTER_UP：只要还有触控点在屏幕上，每当手指离开都会触发这个事件。
    
    注意点：
        亲测一个手指触屏，也会触发ACTION_POINTER_DOWN事件，按这个来判断是否有双指触屏不靠谱，应该用event.getPointerCount()来判断是否有多手指触屏。
    