package com.spryrocks.android.modules.ui.mvp;

public interface IPresenter<TPresenterView> extends ILogic {
    void setView(TPresenterView view);
}
