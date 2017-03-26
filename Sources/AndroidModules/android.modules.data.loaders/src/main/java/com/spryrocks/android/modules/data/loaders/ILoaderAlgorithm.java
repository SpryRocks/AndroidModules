package com.spryrocks.android.modules.data.loaders;

import java.util.List;

public interface ILoaderAlgorithm<T> {
    List<T> loadItems(int offset, int count) throws Exception;

    boolean isPaginationSupported();
}
