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

public final class CancellationTokenSource {
	private boolean _isCancellationRequested;
	private final CancellationToken _cancellationToken;
	final EventWaitHandle _waitHandle;

	public CancellationTokenSource() {
		_waitHandle = new ManualResetEvent(false);
		_cancellationToken = new CancellationToken(this);
	}

	public boolean isCancelleationRequested() {
		return _isCancellationRequested;
	}

	public CancellationToken token() {
		return _cancellationToken;
	}

	/**
	 * Set cancel signal
	 *
	 * @return true if cancel request does not exist
	 */
	public synchronized boolean cancel() {
		if (_isCancellationRequested)
			return false;

		// set cancel request
		_isCancellationRequested = true;

		// set wait handle signaled
		_waitHandle.set();

		return true;
	}
}