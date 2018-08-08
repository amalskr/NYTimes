#include <jni.h>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_ceylonapz_nytimes_utility_Constant_baseUrl(JNIEnv *env, jclass type) {
    return env->NewStringUTF("http://api.nytimes.com/svc/");
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_ceylonapz_nytimes_utility_Constant_getApiKey(JNIEnv *env, jclass type) {
    return env->NewStringUTF("54e5496eb75443aea29abca3eda6dbf6");
}