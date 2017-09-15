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

package com.spryrocks.android.modules.ui.mvvm.model;

import android.databinding.ObservableBoolean;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class OnClickCommand implements View.OnClickListener {
    private final List<Callback> callbacks;
    private final ObservableBoolean isEnabled;

    @SuppressWarnings("WeakerAccess")
    public OnClickCommand(@Nullable Callback callback) {
        callbacks = new ArrayList<>();
        isEnabled = new ObservableBoolean(true);

        if (callback != null) {
            addCallback(callback);
        }
    }

    public OnClickCommand() {
        this(null);
    }

    @Override
    public void onClick(View v) {
        onClick();
    }

    @SuppressWarnings("WeakerAccess")
    public void onClick() {
        for (Callback callback : callbacks) {
            callback.onClick();
        }
    }

    public ObservableBoolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled.set(enabled);
    }

    @SuppressWarnings("WeakerAccess")
    public void addCallback(@NonNull Callback callback) {
        callbacks.add(callback);
    }

    @SuppressWarnings("unused")
    public void removeCallback(@NonNull Callback callback) {
        callbacks.remove(callback);
    }

    public interface Callback {
        void onClick();
    }
}
