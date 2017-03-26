package com.spryrocks.android.modules.threading;

public interface WaitHandle {
	boolean waitOne() throws InterruptedException;

	boolean waitOne(int millisecondsTimeout) throws InterruptedException;
}