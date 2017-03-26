package com.spryrocks.android.modules.data.adapters.listview.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public abstract class SimpleAdapterBase extends BaseAdapter {

	private IViewHolderManager viewHolderManager;
	
	public final List<Object> items;
	
	public SimpleAdapterBase() {
		this.items = new ArrayList<Object>();
	}

	public void setViewHolderManager(IViewHolderManager viewHolderManager) {
		this.viewHolderManager = viewHolderManager;
	}
	
	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Object item = getItem(position);

        if(viewHolderManager == null)
            throw new RuntimeException("You should call method setViewHolderManager to user class SimpleAdapterBase");

		return viewHolderManager.getView(parent, convertView, item);
	}
}
