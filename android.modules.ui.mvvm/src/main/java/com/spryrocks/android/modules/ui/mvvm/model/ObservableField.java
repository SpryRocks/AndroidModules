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

import com.spryrocks.android.modules.utils.Actions;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

@SuppressWarnings("unused")
public class ObservableField<T> extends androidx.databinding.ObservableField<T> {
    private final List<Actions.Action1<T>> callbacks;

    @SuppressWarnings("WeakerAccess")
    public ObservableField(@Nullable T value, @Nullable Actions.Action1<T> callback) {
        super(value);

        callbacks = new ArrayList<>();

        if (callback != null) {
            addCallback(callback);
        }
    }

    @SuppressWarnings("WeakerAccess")
    public ObservableField(Actions.Action1<T> callback) {
        this(null, callback);
    }

    @SuppressWarnings("WeakerAccess")
    public ObservableField(T value) {
        this(value, null);
    }

    @SuppressWarnings("WeakerAccess")
    public ObservableField() {
        this(null, null);
    }

    @Override
    public void set(T value) {
        T oldValue = get();

        if (oldValue == value)
            return;

        super.set(value);

        if (oldValue != null && oldValue.equals(value))
            return;

        for (Actions.Action1<T> callback : callbacks) {
            callback.run(value);
        }
    }

    @SuppressWarnings("WeakerAccess")
    public void addCallback(@NonNull Actions.Action1<T> callback) {
        callbacks.add(callback);
    }

    @SuppressWarnings("unused")
    public void removeCallback(@NonNull Actions.Action1<T> callback) {
        callbacks.remove(callback);
    }
}
