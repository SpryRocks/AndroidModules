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
