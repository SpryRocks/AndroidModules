package com.spryrocks.android.modules.data.loaders;

public class LoaderResult<T> {
    public static <T> LoaderResult<T> create(T value) {
        return new LoaderResult<T>(value, null);
    }

    public static <T> LoaderResult<T> create(Exception error) {
        return new LoaderResult<T>(null, error);
    }

    public final T value;
    public final Exception error;

    private LoaderResult(T value, Exception error) {
        this.value = value;
        this.error = error;
    }
}
