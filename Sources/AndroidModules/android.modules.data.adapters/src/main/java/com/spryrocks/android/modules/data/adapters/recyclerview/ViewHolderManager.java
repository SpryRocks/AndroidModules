package com.spryrocks.android.modules.data.adapters.recyclerview;

import android.view.LayoutInflater;
import android.view.ViewGroup;

public class ViewHolderManager<VH extends ViewHolderBase<TItem>, TItem> implements IViewHolderManager<VH, TItem> {
    private final IViewHolderCreator<VH, TItem> viewHolderCreator;
    private final IDataSource<TItem> dataSource;

    public ViewHolderManager(IViewHolderCreator<VH, TItem> viewHolderCreator, IDataSource<TItem> dataSource) {
        this.viewHolderCreator = viewHolderCreator;
        this.dataSource = dataSource;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup viewGroup, int viewType, LayoutInflater layoutInflater) {
        VH viewHolder = viewHolderCreator.createViewHolder(viewGroup, viewType, layoutInflater);
        viewHolder.initView(viewHolder.itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(VH viewHolder, int position) {
        TItem item = getItem(position);
        viewHolder.bind(item);
    }

    @Override
    public int getItemViewType(int position) {
        TItem item = getItem(position);
        return viewHolderCreator.getDataType(item);
    }

    @Override
    public int getItemCount() {
        return dataSource.getCount();
    }

    private TItem getItem(int position) {
        return dataSource.getItem(position);
    }
}
