package com.spryrocks.android.modules.utils;

import java.util.List;

public class StringHelpers {
	public static boolean isNullOrEmptySpace(String str) {
		return str == null || str.trim().length() < 1;
	}

	public static boolean isNullOrEmpty(String str) {
		return str == null || str.length() < 1;
	}

	public static boolean isEquals(String s1, String s2) {
		return s1 == s2 || s1 != null && s1.equals(s2);
	}

	public static String longArrayToString(long[] arr, String separator) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			sb.append(arr[i]);
			if (i < arr.length - 1)
				sb.append(separator);
		}
		return sb.toString();
	}

	public static String joinStrings(Object[] arr, String separator) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			sb.append(arr[i]);
			if (i < arr.length - 1)
				sb.append(separator);
		}
		return sb.toString();
	}

	public static String joinStrings(List<?> collection, String separator) {
		Object[] arr = new Object[collection.size()];
		collection.toArray(arr);
		return joinStrings(arr, separator);
	}
}