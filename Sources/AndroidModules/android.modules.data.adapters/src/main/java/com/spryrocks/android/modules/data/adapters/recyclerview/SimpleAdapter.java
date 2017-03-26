package com.spryrocks.android.modules.data.adapters.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.List;

public abstract class SimpleAdapter<VH extends ViewHolderBase<TItem>, TItem> extends SimpleAdapterBase<VH, TItem> {
    public final List<TItem> items;
    public final LayoutInflater layoutInflater;

    public SimpleAdapter(Context context, IViewHolderCreator<VH, TItem> viewHolderCreator) {
        super(context);
        items = new ArrayList<>();
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        IDataSource<TItem> dataSource = new ListDataSource<>(items);
        setManager(new ViewHolderManager<>(viewHolderCreator, dataSource));
    }
}
