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

package com.spryrocks.android.modules.ui.lifecycle;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

public class FragmentLifecycleListenersCollection extends LifecycleListenersCollection implements IFragmentLifecycleListener {
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        for (ILifecycleListener listener : listeners) {
            if (listener instanceof IFragmentLifecycleListener) {
                ((IFragmentLifecycleListener) listener).onActivityCreated(savedInstanceState);
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        for (ILifecycleListener listener : listeners) {
            if (listener instanceof IFragmentLifecycleListener) {
                ((IFragmentLifecycleListener) listener).onAttach(context);
            }
        }
    }

    @Override
    public void onDetach() {
        for (ILifecycleListener listener : listeners) {
            if (listener instanceof IFragmentLifecycleListener) {
                ((IFragmentLifecycleListener) listener).onDetach();
            }
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        for (ILifecycleListener listener : listeners) {
            if (listener instanceof IFragmentLifecycleListener) {
                ((IFragmentLifecycleListener) listener).onViewCreated(view, savedInstanceState);
            }
        }
    }

    @Override
    public void onDestroyView() {
        for (ILifecycleListener listener : listeners) {
            if (listener instanceof IFragmentLifecycleListener) {
                ((IFragmentLifecycleListener) listener).onDestroyView();
            }
        }
    }
}
