package com.spryrocks.android.modules.data.adapters.listview.adapters;

import android.view.View;
import android.view.ViewGroup;

public interface IViewHolderManager {
	View getView(ViewGroup root, View convertView, Object item);
}
