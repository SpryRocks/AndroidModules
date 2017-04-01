package com.spryrocks.android.modules.ui.mvp.v2;

public interface IPresenter<TPresenterView> extends ILogic {
    void setView(TPresenterView view);
}
