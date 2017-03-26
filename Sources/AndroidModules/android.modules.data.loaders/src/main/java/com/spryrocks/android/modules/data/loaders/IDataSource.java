package com.spryrocks.android.modules.data.loaders;

import android.content.Context;

import java.io.Serializable;

public interface IDataSource<T> extends Serializable {
    ILoaderAlgorithm<T> createAlgorithm(Context context);
}
