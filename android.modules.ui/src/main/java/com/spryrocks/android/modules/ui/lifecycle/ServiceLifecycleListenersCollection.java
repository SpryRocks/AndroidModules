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

import java.util.ArrayList;
import java.util.List;

public class ServiceLifecycleListenersCollection implements IServiceLifecycleListenersCollection, IServiceLifecycleListener {
    private final List<IServiceLifecycleListener> listeners;
    private boolean isCreated;

    public ServiceLifecycleListenersCollection() {
        listeners = new ArrayList<>();
    }


    @Override
    public void onCreate() {
        isCreated = true;

        for (int i = 0; i < listeners.size(); i++) {
            IServiceLifecycleListener listener = listeners.get(i);

            listener.onCreate();
        }
    }

    @Override
    public void onDestroy() {
        for(IServiceLifecycleListener listener : listeners){
            listener.onDestroy();
        }
    }

    @Override
    public <T extends IServiceLifecycleListener> T registerLifecycleListener(T lifecycleListener) {
        checkLockedState();

        listeners.add(lifecycleListener);

        return lifecycleListener;
    }

    private void checkLockedState() {
        if (isCreated)
            throw new RuntimeException("Cannot register listener after onCreate");
    }
}
