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

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spryrocks.android.modules.ui.mvvm.connectedServices.ConnectedServicesRegistration;
import com.spryrocks.android.modules.ui.mvvm.connectedServices.IConnectedServiceCallbacksReceiver;

class ViewImplHelper<TBinding extends ViewDataBinding, TViewModel extends ViewModel>
        implements IMvvmView<TBinding, TViewModel> {
    private final IMvvmView<TBinding, TViewModel> ownerView;
    private @LayoutRes final int layoutId;
    private final Class<TViewModel> viewModelClass;
    private final int modelBindingVariableId;
    private TBinding binding;
    private IConnectedServiceCallbacksReceiver connectedServicesCallbacksReceiver;
    TViewModel viewModel;

    ViewImplHelper(@LayoutRes int layoutId, Class<TViewModel> viewModelClass, int modelBindingVariableId, IMvvmView<TBinding, TViewModel> ownerView) {
        this.layoutId = layoutId;
        this.viewModelClass = viewModelClass;
        this.modelBindingVariableId = modelBindingVariableId;
        this.ownerView = ownerView;
    }

    @Override
    public void initViewModel(TViewModel viewModel) {
        ownerView.initViewModel(viewModel);
    }

    @Override
    public void initConnectedServices(ConnectedServicesRegistration services) {
        ownerView.initConnectedServices(services);
    }

    @Override
    public void initBinding(TBinding binding) {
        ownerView.initBinding(binding);
    }

    @Override
    public TBinding getBinding() {
        return binding;
    }

    @Override
    public TViewModel getViewModel() {
        return viewModel;
    }

    @Override
    public void cleanViewModel(TViewModel viewModel) {
        ownerView.cleanViewModel(viewModel);
    }

    @Override
    public IConnectedServiceCallbacksReceiver getConnectedServicesCallbacksReceiver() {
        return connectedServicesCallbacksReceiver;
    }

    void onCreate(ViewModelProvider viewModelProvider, ConnectedServicesRegistration connectedServicesRegistration) {
        connectedServicesCallbacksReceiver = connectedServicesRegistration;

        this.viewModel = viewModelProvider.get(viewModelClass);
        initViewModel(viewModel);

        connectedServicesRegistration.setConnectedServicesOwner(viewModel);

        initConnectedServices(connectedServicesRegistration);

        viewModel.onViewAttached();
    }

    void onDestroy() {
        viewModel.onViewDetached();

        cleanViewModel(getViewModel());
    }

    void inflateAndInitBinding(TBinding binding) {
        this.binding = binding;

        binding.setVariable(modelBindingVariableId, viewModel.getModel());

        initBinding(binding);
    }

    int getLayoutId() {
        return layoutId;
    }

    static class FragmentActivity<TBinding extends ViewDataBinding, TViewModel extends ViewModel>
            extends ViewImplHelper<TBinding, TViewModel> {
        FragmentActivity(int layoutId, Class<TViewModel> tViewModelClass, int modelBindingVariableId, IMvvmView<TBinding, TViewModel> ownerView) {
            super(layoutId, tViewModelClass, modelBindingVariableId, ownerView);
        }

        void onCreate(Bundle savedInstanceState, android.support.v4.app.FragmentActivity fragmentActivity, ConnectedServicesRegistration connectedServicesRegistration) {
            ViewModelProvider viewModelProvider = ViewModelProviders.of(fragmentActivity);

            super.onCreate(viewModelProvider, connectedServicesRegistration);

            TBinding binding = DataBindingUtil.setContentView(fragmentActivity, getLayoutId());
            inflateAndInitBinding(binding);

            if (savedInstanceState == null) {
                viewModel.onInitialized();
            }
        }
    }

    static class Fragment<TBinding extends ViewDataBinding, TViewModel extends ViewModel>
            extends ViewImplHelper<TBinding, TViewModel> {
        Fragment(int layoutId, Class<TViewModel> tViewModelClass, int modelBindingVariableId, IMvvmView<TBinding, TViewModel> ownerView) {
            super(layoutId, tViewModelClass, modelBindingVariableId, ownerView);
        }

        void onCreate(Bundle savedInstanceState, android.support.v4.app.Fragment fragment, ConnectedServicesRegistration connectedServicesRegistration) {
            ViewModelProvider viewModelProvider = ViewModelProviders.of(fragment);

            super.onCreate(viewModelProvider, connectedServicesRegistration);

            if (savedInstanceState == null) {
                viewModel.onInitialized();
            }
        }

        View onCreateView(LayoutInflater inflater, ViewGroup container) {
            TBinding binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
            inflateAndInitBinding(binding);
            return binding.getRoot();
        }
    }
}
