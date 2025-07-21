# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
-keepattributes Signature
-keepattributes *Annotation*
-keep class com.mbridge.** {*; }
-keep interface com.mbridge.** {*; }
-dontwarn com.mbridge.**
-keepclassmembers class **.R$* { public static final int mbridge*; }

-keep public class com.mbridge.* extends androidx.** { *; }
-keep public class androidx.viewpager.widget.PagerAdapter{*;}
-keep public class androidx.viewpager.widget.ViewPager.OnPageChangeListener{*;}
-keep interface androidx.annotation.IntDef{*;}
-keep interface androidx.annotation.Nullable{*;}
-keep interface androidx.annotation.CheckResult{*;}
-keep interface androidx.annotation.NonNull{*;}
-keep public class androidx.fragment.app.Fragment{*;}
-keep public class androidx.core.content.FileProvider{*;}
-keep public class androidx.core.app.NotificationCompat{*;}
-keep public class androidx.appcompat.widget.AppCompatImageView {*;}
-keep public class androidx.recyclerview.*{*;}
-keep class com.mbridge.msdk.foundation.tools.FastKV{*;}
-keep class com.mbridge.msdk.foundation.tools.FastKV$Builder{*;}

# keep ads config folder
-keep class com.BTSwallpaperHD.wallpaperbtspt.Utils.** { *; }
################################################################################################

# unity proguard
# Keep filenames and line numbers for stack traces
-keepattributes SourceFile,LineNumberTable
# Keep JavascriptInterface for WebView bridge
-keepattributes JavascriptInterface
# Sometimes keepattributes is not enough to keep annotations
-keep class android.webkit.JavascriptInterface {
   *;
}
# Keep all classes in Unity Ads package
-keep class com.unity3d.ads.** {
   *;
}
# Keep all classes in Unity Services package
-keep class com.unity3d.services.** {
   *;
}
-dontwarn com.google.ar.core.**
-dontwarn com.unity3d.services.**
-dontwarn com.ironsource.adapters.unityads.**

################################################################################################


# Mintegral proguard
-keepattributes Signature
-keepattributes *Annotation*
-keep class com.mbridge.** {*; }
-keep interface com.mbridge.** {*; }
-keep class android.support.v4.** { *; }
-dontwarn com.mbridge.**
-keep class **.R$* { public static final int mbridge*; }
-keep public class com.mbridge.* extends androidx.** { *; }
-keep public class androidx.viewpager.widget.PagerAdapter{ *; }
-keep public class androidx.viewpager.widget.ViewPager.OnPageChangeListener{ *; }
-keep interface androidx.annotation.IntDef{ *; }
-keep interface androidx.annotation.Nullable{ *; }
-keep interface androidx.annotation.CheckResult{ *; }
-keep interface androidx.annotation.NonNull{ *; }
-keep public class androidx.fragment.app.Fragment{ *; }
-keep public class androidx.core.content.FileProvider{ *; }
-keep public class androidx.core.app.NotificationCompat{ *; }
-keep public class androidx.appcompat.widget.AppCompatImageView { *; }
-keep public class androidx.recyclerview.*{ *; }

################################################################################################

#GSon proguard

# Prevent proguard from stripping interface information from TypeAdapter, TypeAdapterFactory,
# JsonSerializer, JsonDeserializer instances (so they can be used in @JsonAdapter)
-keep class * extends com.google.gson.TypeAdapter
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer
# Prevent R8 from leaving Data object members always null
-keepclasseswithmembers class * {
    <init>(...);
    @com.google.gson.annotations.SerializedName <fields>;
}
# Retain generic signatures of TypeToken and its subclasses with R8 version 3.0 and higher.
-keep,allowobfuscation,allowshrinking class com.google.gson.reflect.TypeToken
-keep,allowobfuscation,allowshrinking class * extends com.google.gson.reflect.TypeToken
# Gson
 -keepattributes Signature
 -keepattributes *Annotation*
 -dontwarn sun.misc.**
 -keep class com.google.gson.examples.android.model.** { *; }
 -keep class * implements com.google.gson.TypeAdapterFactory
 -keep class * implements com.google.gson.JsonSerializer
 -keep class * implements com.google.gson.JsonDeserializer
 # Google Android Advertising ID
 -keep class com.google.android.gms.internal.** { *; }
 -dontwarn com.google.android.gms.ads.identifier.**
 -keepclassmembers class com.ironsource.sdk.controller.IronSourceWebView$JSInterface {
     public *;
 }
# For using GSON @Expose annotation
-keepattributes *Annotation*

# Gson specific classes
-keep class sun.misc.Unsafe { *; }
#-keep class com.google.gson.stream.** { *; }

# Application classes that will be serialized/deserialized over Gson
-keep class com.google.gson.examples.android.model.** { *; }

# Prevent proguard from stripping interface information from     TypeAdapterFactory,
# JsonSerializer, JsonDeserializer instances (so they can be used in @JsonAdapter)
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer


-keep class com.android.volley.** { *; }
-keep class org.apache.commons.logging.**

-keepattributes *Annotation*

-dontwarn org.apache.**
# Please add these rules to your existing keep rules in order to suppress warnings.
# This is generated automatically by the Android Gradle plugin.
-dontwarn com.facebook.infer.annotation.Nullsafe$Mode
-dontwarn com.facebook.infer.annotation.Nullsafe

-keep class com.google.gson.reflect.TypeToken
-keep class * extends com.google.gson.reflect.TypeToken
-keep public class * implements java.lang.reflect.Type

-keep class * extends androidx.room.RoomDatabase
-keep @androidx.room.Entity class *
-dontwarn androidx.room.paging.**

    -keep class theoremreach.com.** { *; }
# For communication with AdColony's WebView
-keepclassmembers class * {
    @android.webkit.JavascriptInterface <methods>;
}

################################################################################################

# jsoup proguard
-keep public class org.jsoup.** {
public *;
}
################################################################################################


##volley proguard rules
-keep class com.android.volley.** { *; }
-keep class org.apache.commons.logging.**

-keepattributes *Annotation*

-dontwarn org.apache.**


################################################################################################

##others proguard rules
# Please add these rules to your existing keep rules in order to suppress warnings.
# This is generated automatically by the Android Gradle plugin.
-dontwarn org.jspecify.annotations.NullMarked
# Please add these rules to your existing keep rules in order to suppress warnings.
# This is generated automatically by the Android Gradle plugin.
-dontwarn org.bouncycastle.jsse.BCSSLParameters
-dontwarn org.bouncycastle.jsse.BCSSLSocket
-dontwarn org.bouncycastle.jsse.provider.BouncyCastleJsseProvider
-dontwarn org.conscrypt.Conscrypt$Version
-dontwarn org.conscrypt.Conscrypt
-dontwarn org.conscrypt.ConscryptHostnameVerifier
-dontwarn org.openjsse.javax.net.ssl.SSLParameters
-dontwarn org.openjsse.javax.net.ssl.SSLSocket
-dontwarn org.openjsse.net.ssl.OpenJSSE

-keep class com.cinnamonrollwallpapers.amznapps.Pojo.** { *; }
-keep class com.cinnamonrollwallpapers.amznapps.room.** { *; }
-keep class com.cinnamonrollwallpapers.amznapps.Utils.** { *; }



## unity ads proguard rules
# Keep filenames and line numbers for stack traces
-keepattributes SourceFile,LineNumberTable
# Keep JavascriptInterface for WebView bridge
-keepattributes JavascriptInterface
# Sometimes keepattributes is not enough to keep annotations
-keep class android.webkit.JavascriptInterface {
   *;
}
# Keep all classes in Unity Ads package
-keep class com.unity3d.ads.** {
   *;
}
# Keep all classes in Unity Services package
-keep class com.unity3d.services.** {
   *;
}
-dontwarn com.google.ar.core.**
-dontwarn com.unity3d.services.**
-dontwarn com.ironsource.adapters.unityads.**

## mintegral proguard rules
-keepattributes Signature
-keepattributes *Annotation*
-keep class com.mbridge.** {*; }
-keep interface com.mbridge.** {*; }
-keep class android.support.v4.** { *; }
-dontwarn com.mbridge.**
-keep class **.R$* { public static final int mbridge*; }
-keep public class com.mbridge.* extends androidx.** { *; }
-keep public class androidx.viewpager.widget.PagerAdapter{ *; }
-keep public class androidx.viewpager.widget.ViewPager.OnPageChangeListener{ *; }
-keep interface androidx.annotation.IntDef{ *; }
-keep interface androidx.annotation.Nullable{ *; }
-keep interface androidx.annotation.CheckResult{ *; }
-keep interface androidx.annotation.NonNull{ *; }
-keep public class androidx.fragment.app.Fragment{ *; }
-keep public class androidx.core.content.FileProvider{ *; }
-keep public class androidx.core.app.NotificationCompat{ *; }
-keep public class androidx.appcompat.widget.AppCompatImageView { *; }
-keep public class androidx.recyclerview.*{ *; }


## Chartboost proguard rules
-keep class com.chartboost.** { *; }


## amazon progurd rules

-dontwarn com.amazon.**
-keep class com.amazon.** {*;}
-keepattributes *Annotation*


## jsoup and glide

-keepclassmembers class fqcn.of.javascript.interface.for.webview {
   public *;
}
-keeppackagenames org.jsoup.nodes

-keep public class org.jsoup.** {
public *;
}
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep class * extends com.bumptech.glide.module.AppGlideModule {
 <init>(...);
}
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
-keep class com.bumptech.glide.load.data.ParcelFileDescriptorRewinder$InternalRewinder {
  *** rewind();
}



# Keep annotations used by Gson (@Expose etc.)
-keepattributes *Annotation*

# Keep core Gson classes
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }

# Keep your application classes used with Gson (replace with your package names)
-keep class your.package.data.model.** { *; }

# Keep interfaces used with Gson adapters (@JsonAdapter)
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer
# Keep Volley classes
-keep class com.android.volley.** { *; }

# Don't warn about specific unused classes (optional)
-dontwarn org.apache.commons.logging.**

# Keep Sentry classes and annotations
-keep class io.sentry.** { *; }
-keepattributes *Annotation*

# Keep user-defined integrations (if applicable)
-keep class your.package.integrations.** { *; }


# Please add these rules to your existing keep rules in order to suppress warnings.
# This is generated automatically by the Android Gradle plugin.
-dontwarn org.jspecify.annotations.NullMarked
# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/rikshot/adt-bundle-mac-x86_64/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#  public *;
}

# Preserve the structure of Jsoup library
-keep class org.jsoup.** { *; }

# Keep the names of all classes and interfaces
-keepnames class org.jsoup.** { *; }

# Keep the names of all enum values
-keepclassmembers enum org.jsoup.** { *; }

# Keep the names of all fields and methods
-keepclassmembers class org.jsoup.** {
    *;
}

# Preserve annotations
-keepattributes *Annotation*


# Preserve Moshi classes
-keep class com.squareup.moshi.** { *; }

# Preserve generated adapters for Kotlin classes (if using Moshi with Kotlin)
-keep class **JsonAdapter { *; }

# Preserve @Json annotation used by Moshi
-keep @com.squareup.moshi.Json class * { *; }

# Preserve Kotlin classes that Moshi needs for reflection (if using Moshi with Kotlin)
-if class com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
-keepnames class kotlin.reflect.jvm.internal.impl.builtins.StandardNames$FqNames
-keepnames class kotlin.Metadata { *; }
-keepclassmembers class kotlin.Metadata {
    public <methods>;
}

# Preserve @JsonClass annotation used by Moshi codegen (if using Moshi codegen)
-keep @com.squareup.moshi.JsonClass class * { *; }

# Preserve any custom annotations that you use with Moshi
-keep @com.squareup.moshi.JsonQualifier interface * { *; }
-keep @com.squareup.moshi.JsonQualifier class * { *; }


-keep class  com.gameanalytics.sdk { *; }
-keep class  com.gameanalytics.sdk.** { *; }

-keep class  com.gameanalytics.sdk.GAPlatform { *; }
-keep class  com.gameanalytics.sdk.GAPlatform.** { *; }
-keep class android.net.ConnectivityManager.** { *; }
-keep class com.google.android.instantapps.InstantApps { *; }
-keepclassmembers class com.google.android.instantapps.InstantApps { *; }



# JSR 305 annotations are for embedding nullability information.
-dontwarn javax.annotation.**

-keepclasseswithmembers class * {
    @com.squareup.moshi.* <methods>;
}

-keep @com.squareup.moshi.JsonQualifier @interface *

# Enum field names are used by the integrated EnumJsonAdapter.
# values() is synthesized by the Kotlin compiler and is used by EnumJsonAdapter indirectly
# Annotate enums with @JsonClass(generateAdapter = false) to use them with Moshi.
-keepclassmembers @com.squareup.moshi.JsonClass class * extends java.lang.Enum {
    <fields>;
    **[] values();
}

# Keep helper method to avoid R8 optimisation that would keep all Kotlin Metadata when unwanted
-keepclassmembers class com.squareup.moshi.internal.Util {
    private static java.lang.String getKotlinMetadataClassName();
}

# Keep ToJson/FromJson-annotated methods
-keepclassmembers class * {
  @com.squareup.moshi.FromJson <methods>;
  @com.squareup.moshi.ToJson <methods>;
}