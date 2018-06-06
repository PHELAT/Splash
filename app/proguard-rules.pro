# Dagger 2
-dontwarn com.google.errorprone.annotations.**

# OkHttp 3
-dontwarn okhttp3.**
-dontwarn okio.**
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase

# Gson
-keep class com.phelat.splash.data.model.** { *; }
-keep class com.phelat.splash.data.entity.** { *; }
-keep class com.phelat.splash.data.response.** { *; }

# Retrofit
-dontwarn retrofit2.Platform$Java8

# Required rule for Retrofit and OkHttp
-dontwarn javax.annotation.**
# Required rule for OkHttp
-dontwarn org.conscrypt.**