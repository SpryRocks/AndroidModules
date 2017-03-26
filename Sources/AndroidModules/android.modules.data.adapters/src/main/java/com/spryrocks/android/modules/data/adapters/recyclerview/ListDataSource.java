package com.spryrocks.android.modules.data.adapters.recyclerview;

import java.util.List;

public class ListDataSource<TItem> implements IDataSource<TItem> {
    private final List<TItem> items;

    public ListDataSource(List<TItem> items) {
        this.items = items;
    }

    @Override
    public TItem getItem(int position) {
        return items.get(position);
    }

    @Override
    public int getCount() {
        return items.size();
    }
}
