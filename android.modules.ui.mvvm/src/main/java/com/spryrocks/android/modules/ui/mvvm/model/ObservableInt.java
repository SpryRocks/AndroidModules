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

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

@SuppressWarnings("unused")
public class ObservableInt extends androidx.databinding.ObservableInt {
    private final List<Callback> callbacks;

    @SuppressWarnings("WeakerAccess")
    public ObservableInt(int value, @Nullable Callback callback) {
        super(value);

        this.callbacks = new ArrayList<>();

        if (callback != null) {
            addCallback(callback);
        }
    }

    @SuppressWarnings("WeakerAccess")
    public ObservableInt(int value) {
        this(value, null);
    }

    @SuppressWarnings("WeakerAccess")
    public ObservableInt(Callback valueChangedCallback) {
        this(0, valueChangedCallback);
    }

    @SuppressWarnings("WeakerAccess")
    public ObservableInt() {
        this(0, null);
    }

    @Override
    public void set(int value) {
        int oldValue = get();

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
        void onValueChanged(int value);
    }
}
