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

package com.spryrocks.android.modules.ui.mvvm;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;

import com.spryrocks.android.modules.ui.mvvm.connectedServices.ConnectedServices;
import com.spryrocks.android.modules.ui.mvvm.connectedServices.IConnectedServiceCallbacksManager;
import com.spryrocks.android.modules.ui.mvvm.connectedServices.IConnectedServices;
import com.spryrocks.android.modules.ui.mvvm.connectedServices.IConnectedServicesOwner;

public class ViewModel<TModel> extends AndroidViewModel implements IConnectedServicesOwner {
    @SuppressWarnings("WeakerAccess")
    protected final TModel model;
    private final ConnectedServices connectedServices;

    public ViewModel(Application application, TModel model) {
        super(application);

        this.model = model;

        this.connectedServices = new ConnectedServices();
    }

    @Override
    public IConnectedServices getConnectedServices() {
        return connectedServices;
    }

    @Override
    public IConnectedServiceCallbacksManager getConnectedServiceCallbacksManager() {
        return connectedServices;
    }

    @SuppressWarnings("WeakerAccess")
    public TModel getModel() {
        return model;
    }

    @SuppressWarnings({"unused", "WeakerAccess"})
    protected void onInitialized() {
    }

    @SuppressWarnings({"unused", "WeakerAccess"})
    protected void onViewAttached() {
    }

    @SuppressWarnings({"unused", "WeakerAccess"})
    protected void onViewDetached() {
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        // TODO: 02.08.2017 clear callbacks ?
    }
}
