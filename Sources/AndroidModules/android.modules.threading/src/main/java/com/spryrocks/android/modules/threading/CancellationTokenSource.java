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