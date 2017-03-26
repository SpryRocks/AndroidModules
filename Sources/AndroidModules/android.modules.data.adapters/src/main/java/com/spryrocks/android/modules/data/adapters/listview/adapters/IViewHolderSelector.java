package com.spryrocks.android.modules.data.adapters.listview.adapters;

public interface IViewHolderSelector {
	
	IViewHolderCreator getCreator(Object item);
	
}
