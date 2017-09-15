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

import android.support.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

public class ConnectedServices implements IConnectedServices, IConnectedServicesManager,
        IConnectedServicesCallbacksReceiver, IConnectedServiceCallbacksManager {
    private final Map<Class<? extends IConnectedService>, IConnectedService> serviceMap;
    private final Map<Class<? extends IConnectedServiceCallbacks>, IConnectedServiceCallbacks> callbacksMap;

    public ConnectedServices() {
        serviceMap = new HashMap<>();
        callbacksMap = new HashMap<>();
    }

    @Override
    public <TService extends IConnectedService, TServiceImpl extends TService> TServiceImpl connectService(Class<TService> serviceClass, TServiceImpl serviceImpl) {
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
    public <TService extends IConnectedService> TService getService(Class<TService> serviceClass) {
        //noinspection unchecked
        return (TService) serviceMap.get(serviceClass);
    }

    @Override
    public <TCallbacks extends IConnectedServiceCallbacks> TCallbacks getCallbacks(Class<TCallbacks> callbacksClass) {
        //noinspection unchecked
        return (TCallbacks) callbacksMap.get(callbacksClass);
    }

    @Override
    public <TCallbacks extends IConnectedServiceCallbacks, TCallbacksImpl extends TCallbacks> void connectCallbacks(Class<TCallbacks> callbacksClass, TCallbacksImpl callbacks) {
        callbacksMap.put(callbacksClass, callbacks);
    }

    @Override
    public <TCallbacks extends IConnectedServiceCallbacks> void disconnectCallbacks(Class<TCallbacks> callbacksClass) {
        callbacksMap.remove(callbacksClass);
    }

    @Override
    public void clearCallbacks() {
        callbacksMap.clear();
    }
}
