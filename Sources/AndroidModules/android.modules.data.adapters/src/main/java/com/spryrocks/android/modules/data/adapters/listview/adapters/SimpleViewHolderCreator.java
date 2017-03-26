package com.spryrocks.android.modules.data.adapters.listview.adapters;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class SimpleViewHolderCreator implements IViewHolderCreator {
	
	private final Constructor<?> constructor;
	
	private final Class<?>[] parameterTypes;
	private final Object[] args;
	
	public SimpleViewHolderCreator(Class<?> viewHolderType) {
		this(viewHolderType, null, null);
	}
	
	public SimpleViewHolderCreator(Class<?> viewHolderType, ParameterTypes parameterTypes, Args args) {
		
		if(parameterTypes != null)
			this.parameterTypes = parameterTypes.parameterTypes;
		else
			this.parameterTypes = new Class<?>[0];
		
		if(args != null)
			this.args = args.args;
		else
			this.args = new Object[0];
		
		if(this.parameterTypes.length != this.args.length)
			throw new RuntimeException("Params and args length is not equals");

		try {
			constructor = viewHolderType.getDeclaredConstructor(this.parameterTypes);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	@Override
	public ViewHolderBase createViewHolder(Object item) {
		try {
			ViewHolderBase instance = (ViewHolderBase)constructor.newInstance(args);
			return instance;
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static class ParameterTypes {
		public final Class<?>[] parameterTypes;
		
		public ParameterTypes(Class<?> ... parameterTypes) {
			this.parameterTypes = parameterTypes;
		}
	}
	
	public static class Args {
		public final Object[] args;
		
		public Args(Object ... args) {
			this.args = args;
		}
	}

}
