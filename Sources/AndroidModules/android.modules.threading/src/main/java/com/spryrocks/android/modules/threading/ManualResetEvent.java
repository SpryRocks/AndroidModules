package com.spryrocks.android.modules.threading;

public final class ManualResetEvent extends EventWaitHandle {

	private final Object _monitor;
	private volatile boolean _state;

	public ManualResetEvent(boolean initialState) {
		_monitor = new Object();
		_state = initialState;
	}

	@Override
	public boolean waitOne() throws InterruptedException {
		synchronized (_monitor) {
			while (!_state)
				_monitor.wait();
			return true;
		}
	}

	@Override
	public boolean waitOne(int millisecondsTimeout) throws InterruptedException {
		synchronized (_monitor) {
			if (_state)
				return true;
			_monitor.wait(millisecondsTimeout);
			return _state;
		}
	}

	@Override
	public boolean set() {
		synchronized (_monitor) {
			_state = true;
			_monitor.notifyAll();
			return true;
		}
	}

	@Override
	public boolean reset() {
		synchronized (_monitor) {
			_state = false;
			return true;
		}
	}

}