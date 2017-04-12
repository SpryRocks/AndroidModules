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
