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
