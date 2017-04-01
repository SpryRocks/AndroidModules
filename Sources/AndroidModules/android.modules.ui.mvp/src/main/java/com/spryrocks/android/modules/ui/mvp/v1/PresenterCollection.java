package com.spryrocks.android.modules.ui.mvp.v1;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class PresenterCollection implements IPresenterCallbacks, IPresenterCollection {

    private boolean isCreated;
    private List<IPresenter> presenters;

    public PresenterCollection() {
        presenters = new ArrayList<>();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        isCreated = true;

        for (int i = 0; i < presenters.size(); i++) {
            IPresenter presenter = presenters.get(i);

            Bundle bundle = null;
            if (savedInstanceState != null) {
                String key = getInstanceStateBundleKey(i);
                bundle = savedInstanceState.getBundle(key);
            }

            presenter.onCreate(bundle);
        }
    }

    @Override
    public void onStart() {
        for (IPresenter presenter : presenters) {
            presenter.onStart();
        }
    }

    @Override
    public void onResume() {
        for (IPresenter presenter : presenters) {
            presenter.onResume();
        }
    }

    @Override
    public void onPause() {
        for (IPresenter presenter : presenters) {
            presenter.onPause();
        }
    }

    @Override
    public void onStop() {
        for (IPresenter presenter : presenters) {
            presenter.onStop();
        }
    }

    @Override
    public void onDestroy() {
        for (IPresenter presenter : presenters) {
            presenter.onDestroy();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        for (int i = 0; i < presenters.size(); i++) {
            IPresenter presenter = presenters.get(i);

            String key = getInstanceStateBundleKey(i);
            Bundle bundle  = new Bundle();

            presenter.onSaveInstanceState(bundle);

            outState.putBundle(key, bundle);
        }
    }

    @Override
    public boolean onActivityResult(int requestCode, int resultCode, Intent data) {
        for (IPresenter presenter : presenters) {
            if (presenter.onActivityResult(requestCode, resultCode, data))
                return true;
        }

        return false;
    }

    public <TPresenter extends IPresenter<TPresenterView>, TPresenterView>
    TPresenter registerPresenter(TPresenter presenter, TPresenterView presenterView) {

        if (isCreated)
            throw new RuntimeException("Cannot register presenter after onCreate");
        if (presenters.contains(presenter))
            throw new RuntimeException("This presenter already registered");

        presenters.add(presenter);
        presenter.setView(presenterView);

        return presenter;
    }


    /* private methods */

    private String getInstanceStateBundleKey(int index) {
        return "presenter_" + index;
    }

}
