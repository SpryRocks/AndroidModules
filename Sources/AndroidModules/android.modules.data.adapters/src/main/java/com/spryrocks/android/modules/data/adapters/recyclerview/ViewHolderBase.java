package com.spryrocks.android.modules.data.adapters.recyclerview;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class ViewHolderBase<TItem> extends RecyclerView.ViewHolder {

    private ViewHolderBase(View itemView) {
        super(itemView);
    }

    public ViewHolderBase(LayoutInflater layoutInflater, ViewGroup root, @LayoutRes int layoutResId) {
        this(layoutInflater.inflate(layoutResId, root, false));
    }

    private TItem item;

    public void bind(TItem item) {
        boolean isNewItem = this.item != item;
        if(isNewItem)
            this.item = item;
        this.updateData(item, isNewItem);
    }

    protected abstract void initView(View view);

    protected abstract void updateData(TItem item, boolean isNewItem);

}
