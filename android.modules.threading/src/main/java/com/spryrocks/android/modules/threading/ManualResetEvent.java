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