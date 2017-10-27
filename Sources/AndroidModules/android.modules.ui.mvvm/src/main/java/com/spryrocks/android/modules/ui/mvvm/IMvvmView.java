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

import android.databinding.ViewDataBinding;

import com.spryrocks.android.modules.ui.mvvm.connectedServices.ConnectedServicesRegistration;
import com.spryrocks.android.modules.ui.mvvm.connectedServices.IConnectedServiceCallbacksOwner;
import com.spryrocks.android.modules.ui.mvvm.connectedServices.IConnectedServices;

public interface IMvvmView<TBinding extends ViewDataBinding, TViewModel extends ViewModel> extends IConnectedServiceCallbacksOwner {
    void initViewModel(TViewModel viewModel);

    void initConnectedServices(ConnectedServicesRegistration services);

    void initBinding(TBinding binding);

    @SuppressWarnings("unused")
    TBinding getBinding();

    @SuppressWarnings("unused")
    TViewModel getViewModel();

    @Override
    default IConnectedServices getConnectedServices() {
        return getViewModel().getConnectedServices();
    }
}
