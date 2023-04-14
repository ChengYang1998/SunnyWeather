# SunnyWeather

<img src="https://img.shields.io/badge/version-1.0.0-blue">

Kotlin实战项目，根据《第一行代码》第3版开发。

### 开发语言：Kotlin

### 项目技术中台：AndroidProject-Kotlin

getActivity 轮子哥 

https://github.com/getActivity/AndroidProject-Kotlin

### 项目架构：MVVM

架构开发，架构类似如下图：

<img src="https://cdn.jsdelivr.net/gh/ChengYang1998/blogImage@main/PicGo/image-20230414112931412.png" alt="image-20230414112931412" style="zoom: 50%;" />

编写的代码是严格按照MVVM架构来实现的，且拥有合理的架构分层。



​	相信学习完郭神的《第一行代码》，第一个编写的应用就是SunnyWeather这个天气应用。本项目是基于Kotlin的《第一行代码》第三版SunnyWeather实战项目，并且修复了原版本的一些问题：

1.   书中的应用使用的是Retrofit网络请求库，非常适合处理大型数据请求，但是Retrofit并不是那么好用，因为很多常用的功能实现起来比较麻烦，动态 Host 要写拦截器，日志打印要写拦截器，就连最常用的添加全局参数也要写拦截器，一个拦截器意味着要写很多代码，如果写得不够严谨还有可能出现Bug，从而影响整个 OkHttp请求流程，于是我改用轮子哥的EasyHttp，EasyHttp 采用了 OOP 思想，一个请求代表一个对象，通过类继承和实现的特性来对接口进行动态化配置，几乎涵盖接口开发中所有的功能，使用起来非常简单灵活。

2.   书中的应用UI布局使用固定dp，熟悉Android开发都知道，如果要针对不同尺寸屏幕设备进行开发，固定间距是大忌，我想书中这么用应该是考虑到做一个简单的App不需要做屏幕适配。但是在实际开发中，遇到许多同事是写死dp的，这让人很沮丧。因此本项目采用了smallestWidth限定符适配框架，他使用简单、稳定性好、兼容性好，具体请参考：

     【一种非常好用的 Android 屏幕适配——smallestWidth 限定符适配】https://github.com/wildma/ScreenAdaptation。

3.   书中的应用基于原生Android Studio创建的项目，而本项目基于轮子哥的技术中台AndroidProject，这也是本项目和原书差异巨大的地方。这个技术中台对项目中常见的代码进行了封装，或是封装到基类中、或是封装到工具类中、或者封装到框架中，不追求过度封装，根据实际场景和代码维护性考虑，保证了同一个功能的代码在项目中不重复。基于轮子哥的技术中台，极大方便了日后对该项目功能的增加。











