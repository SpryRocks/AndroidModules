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
