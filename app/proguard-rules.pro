# RxKotlin
-dontwarn io.reactivex.internal.operators.**

# Kotlin
-dontwarn kotlin.**
-dontnote kotlin.**

# OkHttp 3
-dontwarn okhttp3.internal.**
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase

# Gson
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer
-keep class com.phelat.splash.data.model.** { *; }
-keep class com.phelat.splash.data.entity.** { *; }
-keep class com.phelat.splash.data.response.** { *; }

# Retrofit
-dontwarn retrofit2.Platform$Java8

# Okio
-dontwarn okio.**

# Picasso
-dontnote com.squareup.picasso.Utils
-dontwarn com.squareup.picasso.OkHttpDownloader

# Required rules for Retrofit and OkHttp
-dontwarn javax.annotation.**

# Required rules for Gson
-dontwarn sun.misc.**
-keepattributes *Annotation*
-keepattributes Signature

# Required rules for OkHttp
-dontwarn org.codehaus.mojo.animal_sniffer.*

# Required rules for Dagger
-dontwarn com.google.errorprone.annotations.**