package com.spryrocks.android.modules.threading;

public abstract class EventWaitHandle implements WaitHandle {
	public abstract boolean set();

	public abstract boolean reset();
}