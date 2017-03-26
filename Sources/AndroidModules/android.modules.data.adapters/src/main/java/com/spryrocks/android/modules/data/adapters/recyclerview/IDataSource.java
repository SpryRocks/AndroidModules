package com.spryrocks.android.modules.data.adapters.recyclerview;

public interface IDataSource<TItem> {
    TItem getItem(int position);

    int getCount();
}
