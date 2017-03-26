package com.spryrocks.android.modules.data.adapters.recyclerview;

import android.view.LayoutInflater;
import android.view.ViewGroup;

public interface IViewHolderManager<VH extends ViewHolderBase<TItem>, TItem> {
    VH onCreateViewHolder(ViewGroup viewGroup, int viewType, LayoutInflater layoutInflater);
    void onBindViewHolder(VH viewHolder, int position);
    int getItemViewType(int position);
    int getItemCount();
}
