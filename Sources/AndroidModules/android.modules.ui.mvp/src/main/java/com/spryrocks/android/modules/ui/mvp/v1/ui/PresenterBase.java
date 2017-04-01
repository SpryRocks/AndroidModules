package com.spryrocks.android.modules.ui.mvp.v1.ui;

import android.os.Bundle;

public class PresenterBase<IPresenterView extends IBaseView> extends com.spryrocks.android.modules.ui.mvp.v1.PresenterBase<IPresenterView> {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    protected void handleError(Exception error) {
        getView().showError(error);
    }
}
