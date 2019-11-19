1、生命周期。

2、编译APK的执行流程。

3、手撸multiDexKeepFile[方案](https://developer.android.com/studio/build/multidex)。

4、算法[方案](https://www.jianshu.com/p/53f6bf6f8d50)


一、Gradle 基础以及常见方法。

gradle一次完整的构建过程通常是三个部分。初始化、配置、执行任务。

    init初始化阶段：解析整个工程中所有的Project，构建所有的Project对应的Project对象（执行setting.gradle文件）
    Configuration配置阶段：解析所有projects对象中的task，构建好所有task的依赖关系图，该图为有向无环图。
    Execution执行阶段：执行具体的task及其依赖的task。
    
其中Task就是gradle一个操作，一个原子性的操作

```
task testTask {
    //读取配置时候 执行
    println 'testTask << print'
    // 表示在task最前面来执行的过程
    doFirst {
        println 'testTask do first'
    }
    // << 和 doLast表示对当前task追加执行过程，效果是一样的
    doLast{
        println 'testTask do last!'
    }
    afterEvaluate {  //在配置读取阶段执行，总在doFirst之前
        println  '---->afterEvaluate'
    }
}

Task 声明创建有两种 
    1、通过全局的task方法。
    task A << {
        println 'Hello from A'
     }
     2、通过TaskContainer的create方法创建。.
     tasks.create(name: 'hello') << {
         println 'hello'
     }
     
任务配置也有两种
    1、在定义Task的时候对Property进行配置
        task hello1 << {
            description = "this is hello1"
            println description
        }
    2、通过闭包的方式来配置一个已有的Task  
        task hello2 << {
            println description
        }
        hello2 {
            description = "this is hello2"
        } //Gradle会为每一个task创建一个同名的方法，该方法接受一个闭包
        或
        hello2.description = "this is hello2"//Gradle会为每一个task创建一个同名的Property，所以可以将该Task当作Property来访问
```

常见使用方法有

    productFlavors { //渠道定义。
        
    }
    
    buildTypes{
        all{
                buildConfigField "String", "AppType", "\"Test\""
            }
            debug{
                buildConfigField "boolean", "IS_DEBUG", "true"
                minifyEnabled false
                proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
            }
            release {
                buildConfigField "boolean", "IS_DEBUG", "false"
                minifyEnabled true
                proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
            }
    }
    
    其中  buildType配置的渠道权重高于productFlavors，一切在productFlavors中配置的项都能被buildType中的配置项覆盖，比如buildConfigField，只要在all\debug\release中出现，那么productFlavors重的buildConfigField会被覆盖。
    
    defaultConfig{
        resConfigs 'en', 'zh-rCN' ,'es'     //过滤语言。
    }
    
一些常见配置有：
    
    org.gradle.daemon=true //独立进程，停止后台进程命令：gradle --stop
    org.gradle.parallel=true //并行构建，需要将项目拆分成多个子项目，通过aar引用才能起效
    
    
二、编译APK的流程以及gradle的执行流程。

分两个问题来考量。

首先APK的编译流程官方有给出明确的过程。 
    
    编译-->DEX-->打包-->签名和对齐
    
概括性的讲，主要是两个过程，

先是编译过程。编译内容包含本工程文件以及依赖的库文件，编译输出包含dex以及相关资源文件。
其次打包过程。配合keystore对第一步输出进行签名对齐，生成最终APK文件。

相对细致的划分则有
    
    1、Java编译器对工程本身的java代码进行编译，这些java代码有三个来源：app的源代码，由资源文件生成的R文件(aapt工具)，以及有aidl文件生成的java接口文件(aidl工具)。产出为.class文件。
    2、.class文件和依赖的三方库文件通过dex工具生成Delvik虚拟机可执行的.dex文件，可能有一个或多个，包含了所有的class信息，包括项目自身的class和依赖的class。产出为.dex文件。
    3、apkbuilder工具将.dex文件和编译后的资源文件生成未经签名对齐的apk文件。
    4、分别由Jarsigner和zipalign对apk文件进行签名和对齐，生成最终的apk文件。
    
    编译-->DEX-->打包-->签名和对齐
    
    ![avatar](https://upload-images.jianshu.io/upload_images/2839011-28f3fb0ca3af7d9a.png)
    
其次。gradle assembleRelease命令后执行任务会经历以下[流程](https://blog.csdn.net/kylewo/article/details/82632154)


三、fat-aar-android 原理。
    创建多个task。
        按如下流程
        embedAssets             //Merge Assets
        embedLibraryResources   //res   合并。
        embedJniLibs            // jni 拷贝。
        embedManifests          //
        generateRJava
        embedJavaJars           //
        
    copy rename.
        
    
