package com.spryrocks.android.modules.data.adapters.listview.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ViewHolderManager implements IViewHolderManager {
	
	private static final boolean logging = true;
	private static final String log_tag = "ViewHolderManager";

	private static ViewHolderManager get(LayoutInflater layoutInflater, IViewHolderSelector viewHolderSelector) {
		return new ViewHolderManager(layoutInflater, viewHolderSelector);
	}

	public static ViewHolderManager create(Context context, IViewHolderSelector viewHolderSelector) {
		LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		return get(layoutInflater, viewHolderSelector);
	}
	
	private final LayoutInflater layoutInflater;
	private final IViewHolderSelector viewHolderSelector;

	private ViewHolderManager(LayoutInflater layoutInflater, IViewHolderSelector viewHolderSelector) {
		this.layoutInflater = layoutInflater;
		this.viewHolderSelector = viewHolderSelector;
	}

	public View getView(ViewGroup root, View convertView, Object item) {
		ViewHolderBase viewHolderBase = getOrCreate(root, convertView, item);
		return viewHolderBase.view;
	}
	
	private ViewHolderBase getOrCreate(ViewGroup root, View convertView, Object item) {
		ViewHolderBase viewHolderBase = null;
		
		if(convertView != null)
			viewHolderBase = (ViewHolderBase) convertView.getTag();
		
		if(viewHolderBase != null && !viewHolderBase.isItemSupported(item)) {
			log("delete viewHolder");
			
			viewHolderBase = null;
		}
			
		
		if(viewHolderBase == null) {
			log("create viewHolder");
			
			IViewHolderCreator creator = requestCreator(item);
			
			viewHolderBase = creator.createViewHolder(item);
			
			if(viewHolderBase == null)
				throw new RuntimeException("ViewHolderCreater can not be null");
			if(!viewHolderBase.isItemSupported(item))
				throw new RuntimeException("method isItemSupported returned false. Check it...");
			
			viewHolderBase.inflateView_(root, layoutInflater);
		}
		
		Object currentItem = viewHolderBase.getItem();
		if(currentItem != item) 
			viewHolderBase.setItem(item);
		viewHolderBase.updateView();
		return viewHolderBase;
	}

	private IViewHolderCreator requestCreator(Object item) {
		// implement cache of creators
		IViewHolderCreator creator = viewHolderSelector.getCreator(item);
		if(creator == null)
			throw new RuntimeException("Implementation of IViewHolderCreator returned null. Maybe, used item class type does not supported.");
		return creator;
	}
	
	private void log(String message) {
		if(!logging)
			return;
		
		Log.d(log_tag, message);
	}
	
}
