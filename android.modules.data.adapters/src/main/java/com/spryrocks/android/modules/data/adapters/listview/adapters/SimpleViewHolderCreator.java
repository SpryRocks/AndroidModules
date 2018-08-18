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
