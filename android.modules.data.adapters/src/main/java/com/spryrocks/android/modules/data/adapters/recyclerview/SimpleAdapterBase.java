/*
 * Copyright 2017 Spry Rocks, Inc
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */

package com.spryrocks.android.modules.data.adapters.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
