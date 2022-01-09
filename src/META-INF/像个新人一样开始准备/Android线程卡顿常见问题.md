
###一.主线程里面想做IO

    1、SP读写问题。尤其是需要序列化的对象。
    2、对象尽量实现生成parcelable。远快于Serializable。需要自上而下的添加相关序列化。
    
### 二、Activity启动，加载一切。

    1、有些布局可以按需加载。非条件下的布局可以不inflate。
    2、区分a/b 测试的View 不要全部加载。
    3、只有1%人用到的需求、100%人加载。（比如多语言选择、皮肤换色等）


### 三。类中的static变量。

    1、注意区分值是否编译时可知，不可知则尽量需要时再初始化
    
### 四。XML中view不显示 但是属性都有

    1、某些gone的View可以不加载实现背景等属性。
        比较耗时的属性 drawable、scroll等

### 五、系统API

    1、比如某华为HuaweiHomeBadger.executeBadge(context,sConponentName,badgeCount)//角标方法
        不会崩溃，但是很耗时。
    2、Fresco用的 soloader(so文件加载库)，第一次启动的时候会解压我们APK。解压应用中的所有so.到一个路径 后续他们统一管理。
