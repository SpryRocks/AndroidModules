package com.spryrocks.android.modules.data.loaders;

public interface IPaginationLoader {
    boolean isEnd();

    int getCount();
    void setCount(int count);

    void onContentChanged();
}
