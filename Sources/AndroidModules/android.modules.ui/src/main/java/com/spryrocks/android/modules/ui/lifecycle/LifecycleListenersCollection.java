package com.spryrocks.android.modules.ui.lifecycle;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class LifecycleListenersCollection implements ILifecycleListenersCollection, ILifecycleListener {
    protected final List<ILifecycleListener> listeners;
    private boolean isCreated;

    public LifecycleListenersCollection() {
        listeners = new ArrayList<>();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        isCreated = true;

        for (int i = 0; i < listeners.size(); i++) {
            ILifecycleListener listener = listeners.get(i);

            Bundle bundle = null;
            if (savedInstanceState != null) {
                String key = getInstanceStateBundleKey(i);
                bundle = savedInstanceState.getBundle(key);
            }

            listener.onCreate(bundle);
        }
    }

    @Override
    public void onStart() {
        for (ILifecycleListener listener : listeners) {
            listener.onStart();
        }
    }

    @Override
    public void onResume() {
        for (ILifecycleListener listener : listeners) {
            listener.onResume();
        }
    }

    @Override
    public void onPause() {
        for (ILifecycleListener listener : listeners) {
            listener.onPause();
        }
    }

    @Override
    public void onStop() {
        for (ILifecycleListener listener : listeners) {
            listener.onStop();
        }
    }

    @Override
    public void onDestroy() {
        for (ILifecycleListener listener : listeners) {
            listener.onDestroy();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        for (int i = 0; i < listeners.size(); i++) {
            ILifecycleListener listener = listeners.get(i);

            String key = getInstanceStateBundleKey(i);
            Bundle bundle  = new Bundle();

            listener.onSaveInstanceState(bundle);

            outState.putBundle(key, bundle);
        }
    }

    @Override
    public boolean onActivityResult(int requestCode, int resultCode, Intent data) {
        for (ILifecycleListener listener : listeners) {
            if (listener.onActivityResult(requestCode, resultCode, data))
                return true;
        }

        return false;
    }

    @Override
    public void registerListener(ILifecycleListener listener) {
        checkLockedState();

        listeners.add(listener);
    }

    public void checkLockedState() {
        if (isCreated)
            throw new RuntimeException("Cannot register listener after onCreate");
    }


    /* private methods */

    private String getInstanceStateBundleKey(int index) {
        return "lifecycleListener_" + index;
    }
}
