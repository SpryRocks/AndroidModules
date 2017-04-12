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

import java.util.HashMap;
import java.util.Map;

public class SimpleViewHolderSelector implements IViewHolderSelector {

	private final Map<Class<?>, CreatorTypeItem> creators;
	
	public SimpleViewHolderSelector() {
		this.creators = new HashMap<Class<?>, CreatorTypeItem>();
	}
	
	@Override
	public IViewHolderCreator getCreator(Object item) {
		CreatorTypeItem creatorTypeItem = creators.get(item.getClass());
		if(creatorTypeItem == null)
			throw new RuntimeException("Unknown type");
		return creatorTypeItem.creator;
	}
	
	public final void add(Class<?> itemType, IViewHolderCreator creator) {
		creators.put(itemType, new CreatorTypeItem(creator));
	}
	
	public final void addType(Class<?> itemType, Class<?> viewHolderType) {
		add(itemType, new SimpleViewHolderCreator(viewHolderType));
	}
	
	private static class CreatorTypeItem {
		public final IViewHolderCreator creator;
		public CreatorTypeItem(IViewHolderCreator creator) {
			this.creator = creator;
		}
	}

}
