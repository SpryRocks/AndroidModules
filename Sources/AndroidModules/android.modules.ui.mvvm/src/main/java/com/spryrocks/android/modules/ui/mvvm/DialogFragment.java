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

import android.annotation.SuppressLint;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spryrocks.android.modules.ui.mvvm.connectedServices.ConnectedServicesRegistration;

@SuppressLint("ValidFragment")
@SuppressWarnings("unused")
public class DialogFragment<TBinding extends ViewDataBinding, TViewModel extends ViewModel>
        extends com.spryrocks.android.modules.ui.DialogFragment implements IMvvmView<TBinding, TViewModel> {
    private final ViewImplHelper.Fragment<TBinding, TViewModel> mvvmViewImplHelper;

    protected DialogFragment(@LayoutRes int layoutId, Class<TViewModel> viewModelClass, int modelBindingVariableId) {
        mvvmViewImplHelper = new ViewImplHelper.Fragment<>(layoutId, viewModelClass, modelBindingVariableId, this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        ConnectedServicesRegistration connectedServicesRegistration = new ConnectedServicesRegistration();
        registerLifecycleListener(connectedServicesRegistration);

        registerLifecycleListener(mvvmViewImplHelper);

        super.onCreate(savedInstanceState);

        mvvmViewImplHelper.onCreate(this, connectedServicesRegistration);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return mvvmViewImplHelper.onCreateView(inflater, container);
    }

    @SuppressWarnings("unused")
    @CallSuper
    @Override
    public void initViewModel(TViewModel viewModel) {
    }

    @Override
    public void initConnectedServices(ConnectedServicesRegistration services) {
    }

    @Override
    public void initBinding(@SuppressWarnings("unused") TBinding binding) {
    }

    @Override
    public TBinding getBinding() {
        return mvvmViewImplHelper.getBinding();
    }

    @Override
    public TViewModel getViewModel() {
        return mvvmViewImplHelper.getViewModel();
    }
}
