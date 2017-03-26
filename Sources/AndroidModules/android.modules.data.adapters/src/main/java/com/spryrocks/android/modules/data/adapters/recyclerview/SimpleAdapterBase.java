package com.spryrocks.android.modules.data.adapters.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

public abstract class SimpleAdapterBase<VH extends ViewHolderBase<TItem>, TItem> extends RecyclerView.Adapter<VH> {
    private LayoutInflater layoutInflater;
    private IViewHolderManager<VH, TItem> manager;

    protected SimpleAdapterBase(Context context) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    protected void setManager(@NonNull IViewHolderManager<VH, TItem> manager) {
        if(this.manager != null)
            throw new RuntimeException("You shouldn't call this method twice");
        this.manager = manager;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        checkManagerIsPresent();
        return manager.onCreateViewHolder(viewGroup, viewType, layoutInflater);
    }

    @Override
    public void onBindViewHolder(VH viewHolder, int position) {
        manager.onBindViewHolder(viewHolder, position);
    }

    @Override
    public int getItemViewType(int position) {
        return manager.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return manager.getItemCount();
    }

    private void checkManagerIsPresent() {
        if(manager == null)
            throw new RuntimeException("Call setManager(...) from your constructor");
    }
}
