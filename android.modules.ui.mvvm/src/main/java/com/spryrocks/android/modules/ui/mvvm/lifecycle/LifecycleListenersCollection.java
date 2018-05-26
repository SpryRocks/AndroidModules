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

package com.spryrocks.android.modules.ui.mvvm.lifecycle;

import java.util.ArrayList;
import java.util.List;

public class LifecycleListenersCollection implements ILifecycleListenersCollection, ILifecycleListener {
    private final List<ILifecycleListener> listeners;
    private boolean isInitialized;

    public LifecycleListenersCollection() {
        listeners = new ArrayList<>();
    }

    @Override
    public void onInitialized() {
        isInitialized = true;
        for (ILifecycleListener listener : listeners) {
            listener.onInitialized();
        }
    }

    @Override
    public void onViewAttached() {
        for (ILifecycleListener listener : listeners) {
            listener.onViewAttached();
        }
    }

    @Override
    public void onViewDetached() {
        for (ILifecycleListener listener : listeners) {
            listener.onViewDetached();
        }
    }

    @Override
    public void onActivated() {
        for (ILifecycleListener listener : listeners) {
            listener.onActivated();
        }
    }

    @Override
    public void onDeactivated() {
        for (ILifecycleListener listener : listeners) {
            listener.onDeactivated();
        }
    }

    @Override
    public void onCleared() {
        for (ILifecycleListener listener : listeners) {
            listener.onCleared();
        }
    }

    @Override
    public <T extends ILifecycleListener> T registerLifecycleListener(T lifecycleListener) {
        checkLockedState();

        listeners.add(lifecycleListener);

        return lifecycleListener;
    }

    private void checkLockedState() {
        if (isInitialized)
            throw new RuntimeException("Cannot register listener after onInitialized()");
    }
}
