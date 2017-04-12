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

public abstract class PresenterBase<TPresenterView> implements IPresenter<TPresenterView>, IPresenterCollection {

    private TPresenterView presenterView;

    private PresenterCollection presenterCollection;

    public PresenterBase() {
        presenterCollection = new PresenterCollection();
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

        presenterCollection.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        presenterCollection.onStart();
    }

    @Override
    public void onResume() {
        presenterCollection.onResume();
    }

    @Override
    public void onPause() {
        presenterCollection.onPause();
    }

    @Override
    public void onStop() {
        presenterCollection.onStop();
    }

    @Override
    public void onDestroy() {
        presenterCollection.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        presenterCollection.onSaveInstanceState(outState);
    }

    @Override
    public boolean onActivityResult(int requestCode, int resultCode, Intent data) {
        return presenterCollection.onActivityResult(requestCode, resultCode, data);
    }


    /* protected methods */

    public TPresenterView getView() {
        return presenterView;
    }


    /* public methods */

    @Override
    public <TPresenter extends IPresenter<TPresenterView2>, TPresenterView2>
    TPresenter registerPresenter(TPresenter presenter, TPresenterView2 presenterView) {
        return presenterCollection.registerPresenter(presenter, presenterView);
    }

}
