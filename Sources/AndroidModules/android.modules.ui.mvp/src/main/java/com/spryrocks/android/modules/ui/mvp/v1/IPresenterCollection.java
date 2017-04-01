package com.spryrocks.android.modules.ui.mvp.v1;

public interface IPresenterCollection {
    <TPresenter extends IPresenter<TPresenterView>, TPresenterView>
        TPresenter registerPresenter(TPresenter presenter, TPresenterView presenterView);
}
