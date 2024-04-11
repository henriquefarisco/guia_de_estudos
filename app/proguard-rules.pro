# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:\Users\Usuario\AppData\Local\Android\Sdk\tools\proguard\proguard-android-optimize.txt
# You can edit the include path and order by changing the ProGuard
# include property in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Keep your application code safe
-keep class com.henriquefarisco.guiadeestudos.** { *; }

# If you are using Gson for JSON parsing, add this rule
-keepattributes Signature
-keepattributes *Annotation*
-keep class com.google.gson.stream.** { *; }

# If you are using Retrofit for network requests, add these rules
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes *Annotation*
