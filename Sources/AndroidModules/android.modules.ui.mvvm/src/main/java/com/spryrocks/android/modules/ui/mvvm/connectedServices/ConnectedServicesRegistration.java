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

import com.spryrocks.android.modules.ui.lifecycle.ILifecycleListener;
import com.spryrocks.android.modules.ui.lifecycle.ILifecycleListenersCollection;
import com.spryrocks.android.modules.ui.lifecycle.LifecycleListener;

public class ConnectedServicesRegistration extends LifecycleListener implements IConnectedServicesRegistration {
    private final ILifecycleListenersCollection lifecycleListenersCollection;

    private IConnectedServicesManager connectedServicesManager;
    private IConnectedServiceReceiver connectedServiceReceiver;
    private IConnectedServicesCallbacksReceiver connectedServicesCallbacksReceiver;

    public ConnectedServicesRegistration(ILifecycleListenersCollection lifecycleListenersCollection) {
        this.lifecycleListenersCollection = lifecycleListenersCollection;

        lifecycleListenersCollection.registerLifecycleListener(this);
    }

    public void setConnectedServicesOwner(IConnectedServicesOwner connectedServicesOwner) {
        IConnectedServices connectedServices = connectedServicesOwner.getConnectedServices();
        connectedServicesManager = (IConnectedServicesManager) connectedServices;
        connectedServiceReceiver = connectedServices;
        connectedServicesCallbacksReceiver = connectedServices;
    }

    @Override
    public <TService extends IConnectedService, TServiceImpl extends TService> TServiceImpl connectService(Class<TService> serviceClass, TServiceImpl service) {
        connectedServicesManager.connectService(serviceClass, service);

        if (service instanceof ILifecycleListener)
            lifecycleListenersCollection.registerLifecycleListener((ILifecycleListener) service);

        return service;
    }

    @Override
    public <TService extends IConnectedService> void disconnectService(Class<TService> serviceClass) {
        connectedServicesManager.disconnectService(serviceClass);
    }

    @Override
    public void clearServices() {
        connectedServicesManager.clearServices();
    }

    @Override
    public <TCallbacks extends IConnectedServiceCallbacks> TCallbacks getCallbacks(Class<TCallbacks> callbacksClass) {
        return connectedServicesCallbacksReceiver.getCallbacks(callbacksClass);
    }

    @Nullable
    @Override
    public <TService extends IConnectedService> TService getService(Class<TService> serviceClass) {
        return connectedServiceReceiver.getService(serviceClass);
    }

    @Override
    public void onDestroy() {
        clearServices();
    }
}
