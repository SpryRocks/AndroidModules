package com.spryrocks.android.modules.data.adapters.listview.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class ViewHolderBase {
	
	private final int layoutViewId;
	private final Class<?> supportedType;
	
	private Object item;

	View view;
	
	protected ViewHolderBase(int layoutResId, Class<?> supportedType) {
		this.layoutViewId = layoutResId;
		this.supportedType = supportedType;
	}

	void inflateView_(ViewGroup root, LayoutInflater layoutInflater) {
		View view = inflateView(root, layoutInflater);
		if(view == null)
			throw new RuntimeException();
		init(view);
	}

	private void init(View view) {
		if(view.getTag() != null)
			throw new RuntimeException();
		
		view.setTag(this);
		
		this.view = view;
		
		initView(view);
	}
	
	public boolean isItemSupported(Object item) {
		return supportedType.isAssignableFrom(item.getClass());
	}
	
	protected View inflateView(ViewGroup root, LayoutInflater layoutInflater) {
		View view = layoutInflater.inflate(layoutViewId, root, false);
		return view;
	}
	
	protected abstract void initView(View view);
	protected abstract void itemChanged(Object item);
	protected abstract void updateView();
	
	void setItem(Object item) {
		itemChanged(item);
        this.item = item;
	}
	
	Object getItem() {
		return item;
	}
	
	public View getView() {
		return view;
	}
	
}
