package com.spryrocks.android.modules.data.adapters.listview.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import java.util.ArrayList;
import java.util.List;

public class SimpleExpandableListAdapterBase extends BaseExpandableListAdapter {

	private IViewHolderManager viewHolderManager;
	
	public final List<GroupItemBase> groups;
	
	public SimpleExpandableListAdapterBase(Context context, IViewHolderSelector viewHolderSelector) {
		this.viewHolderManager = ViewHolderManager.create(context, viewHolderSelector);
		
		this.groups = new ArrayList<GroupItemBase>();
	}
	
	protected void setViewHolderManager(IViewHolderManager viewHolderManager) {
		this.viewHolderManager = viewHolderManager;
	}
	
	@Override
	public int getGroupCount() {
		int size = groups.size();
		return size;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		GroupItemBase groupItem = getGroupItem(groupPosition);
		int size = groupItem.items.size();;
		return size;
	}

	@Override
	public Object getGroup(int groupPosition) {
		return getGroupItem(groupPosition);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		GroupItemBase groupItem = getGroupItem(groupPosition);
		Object childItem = groupItem.items.get(childPosition);
		return childItem;
	}

	@Override
	public long getGroupId(int groupPosition) {
		return 0;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return 0;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		GroupItemBase groupItem = getGroupItem(groupPosition);
		return viewHolderManager.getView(parent, convertView, groupItem);
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		Object item = getChild(groupPosition, childPosition);
		return viewHolderManager.getView(parent, convertView, item);
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

	
	/* private methods */
	
	public final GroupItemBase getGroupItem(int index) {
		return groups.get(index);
	}
	
}
