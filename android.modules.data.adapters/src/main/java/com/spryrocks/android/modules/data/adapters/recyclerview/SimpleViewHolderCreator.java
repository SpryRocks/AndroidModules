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

import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SimpleViewHolderCreator<VH extends ViewHolderBase<TItem>, TItem> implements IViewHolderCreator<VH, TItem> {
    public static <VH extends ViewHolderBase<TItem>, TItem> SimpleViewHolderCreator<VH, TItem> single(Class<TItem> itemClass, IViewHolderCreator<VH, TItem> viewHolderCreator) {
        return new SimpleViewHolderCreator<>(Collections.singletonList(
                new DataItemType<>(itemClass, viewHolderCreator)
        ));
    }

    public static <VH extends ViewHolderBase<TItem>, TItem> Builder<VH, TItem> multiple() {
        return new Builder<>();
    }

    private final List<DataItemType<VH, TItem>> dataItemTypes;

    public SimpleViewHolderCreator(List<DataItemType<VH, TItem>> dataItemTypes) {
        this.dataItemTypes = dataItemTypes;
    }

    @Override
    public VH createViewHolder(ViewGroup root, int viewType, LayoutInflater layoutInflater) {
        return getDataItemType(viewType).viewHolderCreator.createViewHolder(root, layoutInflater);
    }

    @Override
    public int getDataType(TItem item) {
        for (int i = 0; i < dataItemTypes.size(); i++) {
            if (getDataItemType(i).itemClass.equals(item.getClass()))
                return i;
        }

        throw new RuntimeException();
    }

    private DataItemType<VH, TItem> getDataItemType(int index) {
        return dataItemTypes.get(index);
    }

    private static class DataItemType<VH extends ViewHolderBase<TItem>, TItem> {
        private final Class<TItem> itemClass;
        private final IViewHolderCreator<VH, TItem> viewHolderCreator;

        private DataItemType(Class<TItem> itemClass, IViewHolderCreator<VH, TItem> viewHolderCreator) {
            this.itemClass = itemClass;
            this.viewHolderCreator = viewHolderCreator;
        }
    }

    public interface IViewHolderCreator<VH extends ViewHolderBase<TItem>, TItem> {
        VH createViewHolder(ViewGroup root, LayoutInflater layoutInflater);
    }

    public static class Builder<VH extends ViewHolderBase<TItem>, TItem> {
        private final List<DataItemType<VH, TItem>> itemTypeList;

        private Builder() {
            itemTypeList = new ArrayList<>();
        }

        public Builder add(Class<TItem> itemClass, IViewHolderCreator<VH, TItem> viewHolderCreator) {
            itemTypeList.add(new DataItemType<>(itemClass, viewHolderCreator));
            return this;
        }

        public SimpleViewHolderCreator<VH, TItem> build() {
            return new SimpleViewHolderCreator<>(itemTypeList);
        }
    }
}
