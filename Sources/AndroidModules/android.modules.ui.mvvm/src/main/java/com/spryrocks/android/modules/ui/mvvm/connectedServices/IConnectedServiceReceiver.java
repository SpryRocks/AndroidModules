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

package com.spryrocks.android.modules.ui.mvvm.connectedServices;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.spryrocks.android.modules.utils.Monads;

import static com.spryrocks.android.modules.utils.Monads.maybeNull;

public interface IConnectedServiceReceiver {
    @Nullable
    <TService extends IConnectedService> TService getService(@NonNull Class<TService> serviceClass);

    @SuppressWarnings("unused")
    default <TService extends IConnectedService> void useService(@NonNull Class<TService> serviceClass, @NonNull Monads.Action1<TService> action) {
        TService service = this.getService(serviceClass);
        if (DebugMode.isEnabled && service == null) {
            throw new RuntimeException(serviceClass.getName() + " not found. Make sure you call connectService(...) method in the view class (fragment or activity)");
        }

        maybeNull(getService(serviceClass), action);
    }
}
