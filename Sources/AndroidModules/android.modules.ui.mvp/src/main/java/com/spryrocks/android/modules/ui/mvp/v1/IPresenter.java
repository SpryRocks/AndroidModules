package com.spryrocks.android.modules.ui.mvp.v1;

public interface IPresenter<TPresenterView> extends IPresenterCallbacks {
    void setView(TPresenterView view);
}
