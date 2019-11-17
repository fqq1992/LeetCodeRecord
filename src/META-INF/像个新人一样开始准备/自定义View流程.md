总览：
    
act.setContentView(),到view显示到屏幕上， 主要是通过ViewRoot来连接。WindowManager 和 DecorView。
具体实现类为ViewRootImpl.

    Activity创建完毕后，会讲DecorView添加到window，同时创建了ViewRootImlp，将它和DecorView绑定。
    

~~~
View的绘制从 ViewRootImpl.performTraversals开始。performTraversals()函数中相继执行Measure、Layout、Draw三个流程
拿到页面布局后，在 Surface中 通过IPC协议交给 WindowMangerService。最终由SurfaceFlinger来展示到屏幕上。
~~~
展开特别多，在此主要速过一遍自定义View应用层点。一、View到屏幕的主要流程。二、主要属性方法。

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
    


