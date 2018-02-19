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

package com.spryrocks.android.modules.ui.mvp.v1;

import android.content.Intent;
import android.os.Bundle;

import com.spryrocks.android.modules.ui.lifecycle.ILifecycleListener;
import com.spryrocks.android.modules.ui.lifecycle.ILifecycleListenersCollection;
import com.spryrocks.android.modules.ui.lifecycle.LifecycleListenersCollection;

public abstract class PresenterBase<TPresenterView> implements IPresenter<TPresenterView>, ILifecycleListenersCollection {
    private TPresenterView presenterView;

    private LifecycleListenersCollection lifecycleListenersCollection;

    public PresenterBase() {
        lifecycleListenersCollection = new LifecycleListenersCollection();
    }

    @Override
    public void setView(TPresenterView presenterView) {

        if(presenterView == null)
            throw new NullPointerException("presenterView cannot to be null");
        if(this.presenterView != null)
            throw new RuntimeException("Cannot set presenterView twice");

        this.presenterView = presenterView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if(presenterView == null)
            throw new NullPointerException("presenterView cannot to be null");

        lifecycleListenersCollection.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        lifecycleListenersCollection.onStart();
    }

    @Override
    public void onResume() {
        lifecycleListenersCollection.onResume();
    }

    @Override
    public void onPause() {
        lifecycleListenersCollection.onPause();
    }

    @Override
    public void onStop() {
        lifecycleListenersCollection.onStop();
    }

    @Override
    public void onDestroy() {
        lifecycleListenersCollection.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        lifecycleListenersCollection.onSaveInstanceState(outState);
    }

    @Override
    public boolean onActivityResult(int requestCode, int resultCode, Intent data) {
        return lifecycleListenersCollection.onActivityResult(requestCode, resultCode, data);
    }


    /* protected methods */

    public TPresenterView getView() {
        return presenterView;
    }


    /* public methods */

    @Override
    public <T extends ILifecycleListener> T registerLifecycleListener(T lifecycleListener) {
        return lifecycleListenersCollection.registerLifecycleListener(lifecycleListener);
    }
}
