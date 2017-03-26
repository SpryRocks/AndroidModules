package com.spryrocks.android.modules.data.loaders;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class EmptyDataSource<T> implements IDataSource<T> {
    @Override
    public ILoaderAlgorithm<T> createAlgorithm(Context context) {
        return new Algorithm<>();
    }

    private static class Algorithm<T> implements ILoaderAlgorithm<T> {
        @Override
        public List<T> loadItems(int offset, int count) throws Exception {
            return new ArrayList<>();
        }

        @Override
        public boolean isPaginationSupported() {
            return false;
        }
    }
}
