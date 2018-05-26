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

package com.spryrocks.android.modules.ui.mvp.v2;

import com.spryrocks.android.modules.ui.lifecycle.ILifecycleListener;
import com.spryrocks.android.modules.ui.lifecycle.ILifecycleListenersCollection;

@SuppressWarnings("unused")
public class PresenterCollection {
    private final ILifecycleListenersCollection lifecycleListenersCollection;

    public PresenterCollection(ILifecycleListenersCollection lifecycleListenersCollection) {
        this.lifecycleListenersCollection = lifecycleListenersCollection;
    }

    public <TPresenter extends IPresenter<TPresenterView>, TPresenterView>
    TPresenter registerPresenter(TPresenter presenter, TPresenterView presenterView) {
        if (presenter instanceof ILifecycleListener)
            lifecycleListenersCollection.registerLifecycleListener((ILifecycleListener) presenter);

        presenter.setView(presenterView);

        return presenter;
    }
}
