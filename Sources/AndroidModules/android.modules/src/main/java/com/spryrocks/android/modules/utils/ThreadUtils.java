package com.spryrocks.android.modules.utils;

import android.os.Looper;

public class ThreadUtils {
    public static boolean isMainThread() {
        Thread mainThread = getMainThread();
        Thread currentThread = getCurrentThread();
        return mainThread.equals(currentThread);
    }

    public static boolean isNotMainThread() {
        return !isMainThread();
    }

    public static void throwIfMainThread() {
        if (isMainThread())
            throw new RuntimeException("You cannot run this operation in main thread");
    }

    public static void throwIfNotMainThread() {
        if (isNotMainThread())
            throw new RuntimeException("You must run this operation in main thread");
    }

    public static Thread getMainThread() {
        return Looper.getMainLooper().getThread();
    }

    public static Thread getCurrentThread() {
        return Thread.currentThread();
    }
}