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

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;

import com.spryrocks.android.modules.ui.BaseActivity;
import com.spryrocks.android.modules.ui.mvvm.connectedServices.ConnectedServicesRegistration;

public class MvvmActivity<TBinding extends ViewDataBinding, TViewModel extends BaseViewModel>
        extends BaseActivity implements IMvvmView<TBinding, TViewModel> {
    @LayoutRes
    private final int layoutId;
    private final Class<TViewModel> viewModelClass;
    private final int modelBindingVariableId;
    private TBinding binding;
    private TViewModel viewModel;

    protected MvvmActivity(@LayoutRes int layoutId, Class<TViewModel> viewModelClass, int modelBindingVariableId) {
        this.layoutId = layoutId;
        this.viewModelClass = viewModelClass;
        this.modelBindingVariableId = modelBindingVariableId;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        ConnectedServicesRegistration connectedServicesRegistration = new ConnectedServicesRegistration(this);

        super.onCreate(savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(viewModelClass);
        initViewModel(viewModel);

        connectedServicesRegistration.setConnectedServicesOwner(viewModel);

        initConnectedServices(connectedServicesRegistration);

        binding = MvvmViewUtil.inflateAndInitBinding(this, this, layoutId, viewModel, modelBindingVariableId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        cleanViewModel(viewModel);
    }

    @SuppressWarnings("unused")
    @Override
    public void initViewModel(TViewModel viewModel) {
    }

    @SuppressWarnings("unused")
    public void cleanViewModel(TViewModel viewModel) {
    }

    @Override
    public void initConnectedServices(ConnectedServicesRegistration services) {
    }

    @Override
    public void initBinding(@SuppressWarnings("unused") TBinding binding) {
    }

    @Override
    public TBinding getBinding() {
        return binding;
    }

    @Override
    public TViewModel getViewModel() {
        return viewModel;
    }
}
