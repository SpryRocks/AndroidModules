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
