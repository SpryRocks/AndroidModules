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

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class ObservableBoolean extends android.databinding.ObservableBoolean {
    private final List<Callback> callbacks;

    @SuppressWarnings("WeakerAccess")
    public ObservableBoolean(boolean value, @Nullable Callback callback) {
        super(value);

        this.callbacks = new ArrayList<>();

        if (callback != null) {
            addCallback(callback);
        }
    }

    @SuppressWarnings("WeakerAccess")
    public ObservableBoolean(boolean value) {
        this(value, null);
    }

    @SuppressWarnings("WeakerAccess")
    public ObservableBoolean(Callback valueChangedCallback) {
        this(false, valueChangedCallback);
    }

    @SuppressWarnings("WeakerAccess")
    public ObservableBoolean() {
        this(false, null);
    }

    @Override
    public void set(boolean value) {
        boolean oldValue = get();

        if (oldValue == value)
            return;

        super.set(value);

        for (Callback callback : callbacks) {
            callback.onValueChanged(value);
        }
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
        void onValueChanged(boolean value);
    }
}
