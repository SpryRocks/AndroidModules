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

package com.spryrocks.android.modules.ui.lifecycle;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class LifecycleListenersCollection implements ILifecycleListenersCollection, ILifecycleListener {
    final List<ILifecycleListener> listeners;
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
    public <T extends ILifecycleListener> T registerLifecycleListener(T lifecycleListener) {
        checkLockedState();

        listeners.add(lifecycleListener);

        return lifecycleListener;
    }

    private void checkLockedState() {
        if (isCreated)
            throw new RuntimeException("Cannot register listener after onCreate");
    }


    /* private methods */

    private String getInstanceStateBundleKey(int index) {
        return "lifecycleListener_" + index;
    }
}
