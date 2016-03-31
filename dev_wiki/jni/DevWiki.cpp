/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
#include"debug/CrashHandler.h"
/* Header for class develop_wiki_android_global_GlobalActionManager */

#ifndef _Included_develop_wiki_android_global_GlobalActionManager
#define _Included_develop_wiki_android_global_GlobalActionManager
#ifdef __cplusplus
extern "C" {
#endif


JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM *jvm, void *reserved) {
	g_jvm = jvm;
	nativeCrashHandler_onLoad();
	return JNI_VERSION_1_6;
}

/*
 * Class:     develop_wiki_android_global_GlobalActionManager
 * Method:    initNativeCrashHandler
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_develop_wiki_android_global_GlobalActionManager_initNativeCrashHandler
(JNIEnv *env, jobject obj)
{
	nativeCrashHandler_onLoad();
}

/*
 * Class:     com_example_debugdemo_CrashTestActivity
 * Method:    monitorNativeCrash
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_example_debugdemo_CrashTestActivity_monitorNativeCrash
(JNIEnv *env, jobject obj)
{
	env = NULL;
	env -> FindClass("develop.wiki.android.global.debug.CrashHandler");
}

#ifdef __cplusplus
}
#endif
#endif
