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

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.ViewGroup;

class MvvmViewUtil {
    static <TBinding extends ViewDataBinding, TViewModel extends BaseViewModel>
    TBinding inflateAndInitBinding(
            IMvvmView<TBinding, TViewModel> mvvmView,
            LayoutInflater inflater, ViewGroup container,
            @LayoutRes int layoutId, TViewModel viewModel,
            int modelBindingVariableId) {
        TBinding binding = DataBindingUtil.inflate(inflater, layoutId, container, false);

        binding.setVariable(modelBindingVariableId, viewModel.getModel());

        mvvmView.initBinding(binding);

        return binding;
    }

    static <TBinding extends ViewDataBinding, TViewModel extends BaseViewModel>
    TBinding inflateAndInitBinding(
            Activity activity,
            IMvvmView<TBinding, TViewModel> mvvmView,
            @LayoutRes int layoutId, TViewModel viewModel,
            int modelBindingVariableId) {
        TBinding binding = DataBindingUtil.setContentView(activity, layoutId);

        binding.setVariable(modelBindingVariableId, viewModel.getModel());

        mvvmView.initBinding(binding);

        return binding;
    }
}
