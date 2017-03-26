package com.spryrocks.android.modules.data.adapters.recyclerview;

import android.view.LayoutInflater;
import android.view.ViewGroup;

public interface IViewHolderCreator<VH extends ViewHolderBase<TItem>, TItem> {
    VH createViewHolder(ViewGroup root, int viewType, LayoutInflater layoutInflater);

    int getDataType(TItem item);
}
