package com.spryrocks.android.modules.ui.lifecycle;

import android.os.Bundle;

public class ActivityLifecycleListenersCollection extends LifecycleListenersCollection implements IActivityLifecycleListener {
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        for (ILifecycleListener listener : listeners) {
            if (listener instanceof IActivityLifecycleListener) {
                ((IActivityLifecycleListener) listener).onRestoreInstanceState(savedInstanceState);
            }
        }
    }
}
