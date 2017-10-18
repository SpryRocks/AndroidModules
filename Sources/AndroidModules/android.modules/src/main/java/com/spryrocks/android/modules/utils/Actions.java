package com.spryrocks.android.modules.utils;

@SuppressWarnings("unused")
public class Actions {
    public interface Action {
        void run();
    }

    public interface Action1<T> {
        void run(T arg);
    }

    public interface Action2<T1, T2> {
        void run(T1 arg1, T2 atr2);
    }

    public interface Action3<T1, T2, T3> {
        void run(T1 arg1, T2 arg2, T3 t3);
    }

    public interface Func<R> {
        R run();
    }

    public interface Func1<T, R> {
        R run(T arg);
    }

    public interface Func2<T1, T2, R> {
        R run(T1 arg1, T2 arg2);
    }

    public interface Func3<T1, T2, T3, R> {
        R run(T1 arg1, T2 arg2, T3 arg3);
    }
}
