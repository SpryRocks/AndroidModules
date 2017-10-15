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

package com.spryrocks.android.modules.ui.mvvm.connectedServices;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ConnectedServices implements IConnectedServices, IConnectedServicesManager,
        IConnectedServiceCallbacksReceiver, IConnectedServiceCallbacksManager {
    private final Map<Class<? extends IConnectedService>, IConnectedService> serviceMap;
    private final Map<Class<? extends IConnectedServiceCallbacks>, Set<IConnectedServiceCallbacks>> callbacksMap;

    public ConnectedServices() {
        serviceMap = new HashMap<>();
        callbacksMap = new HashMap<>();
    }

    //region Services
    @Override
    public <TService extends IConnectedService, TServiceImpl extends TService>
    TServiceImpl connectService(Class<TService> serviceClass, TServiceImpl serviceImpl) {
        serviceMap.put(serviceClass, serviceImpl);
        return serviceImpl;
    }

    @Override
    public <TService extends IConnectedService> void disconnectService(Class<TService> serviceClass) {
        serviceMap.remove(serviceClass);
    }

    @Override
    public void clearServices() {
        serviceMap.clear();
    }

    @Nullable
    @Override
    public <TService extends IConnectedService> TService getService(@NonNull Class<TService> serviceClass) {
        //noinspection unchecked
        return (TService) serviceMap.get(serviceClass);
    }
    //endregion

    //region Callbacks
    @Override
    @NonNull
    public <TCallbacks extends IConnectedServiceCallbacks>
    Set<TCallbacks> getCallbacks(@NonNull Class<TCallbacks> callbacksClass) {
        Set<TCallbacks> set = getCallbacksSet(callbacksClass);
        if (set == null) {
            return new HashSet<>();
        }

        return new HashSet<>(set);
    }

    @Override
    public <TCallbacks extends IConnectedServiceCallbacks, TCallbacksImpl extends TCallbacks>
    void addCallbacks(Class<TCallbacks> callbacksClass, TCallbacksImpl callbacks) {
        Set<IConnectedServiceCallbacks> set = callbacksMap.get(callbacksClass);
        if (set == null) {
            callbacksMap.put(callbacksClass, set = new HashSet<>());
        }

        set.add(callbacks);
    }

    @Override
    public <TCallbacks extends IConnectedServiceCallbacks, TCallbacksImpl extends TCallbacks>
    void removeCallbacks(Class<TCallbacks> callbacksClass, TCallbacksImpl callbacks) {
        Set<TCallbacks> set = getCallbacksSet(callbacksClass);
        if (set == null)
            return;

        set.remove(callbacks);
    }

    @Override
    public void clearCallbacks() {
        callbacksMap.clear();
    }

    @Nullable
    private <TCallbacks extends IConnectedServiceCallbacks>
    Set<TCallbacks> getCallbacksSet(Class<TCallbacks> callbacksClass) {
        //noinspection unchecked
        return (Set<TCallbacks>) callbacksMap.get(callbacksClass);
    }
    //endregion
}
