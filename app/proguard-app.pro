# 忽略警告
#-ignorewarning

# 混淆保护自己项目的部分代码以及引用的第三方jar包
#-libraryjars libs/xxxxxxxxx.jar

# 不混淆这个包下的类
-keep class com.sunnyweather.android.http.api.** {
    <fields>;
}
-keep class com.sunnyweather.android.http.response.** {
    <fields>;
}
-keep class com.sunnyweather.android.http.model.** {
    <fields>;
}

# 不混淆被 Log 注解的方法信息
-keepclassmembernames class ** {
    @com.sunnyweather.android.aop.Log <methods>;
}