# This directive instructs ProGuard to keep the names of all fields (variables) within all classes in your application during the
# obfuscation process, while allowing ProGuard to still obfuscate class names and method names.
-keepnames class * {
    <fields>;
}