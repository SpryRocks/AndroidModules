package com.spryrocks.android.modules.data.loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

public abstract class AsyncTaskLoaderBase<T> extends AsyncTaskLoader<LoaderResult<T>> {
    public AsyncTaskLoaderBase(Context context) {
        super(context);
    }
}
