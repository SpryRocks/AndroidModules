package com.spryrocks.android.modules.data.adapters.listview.adapters;

import java.util.ArrayList;
import java.util.List;

public abstract class GroupItemBase {
	public final List<Object> items;
	
	public GroupItemBase() {
		items = new ArrayList<Object>();
	}
}
