package com.spryrocks.android.modules.ui.mvp;

import com.example.android.modules.ui.lifecycle.ILifecycleListenersCollection;

public class PresenterCollection {
    private final LogicCollection logicCollection;

    public PresenterCollection(LogicCollection logicCollection) {
        this.logicCollection = logicCollection;
    }

    public PresenterCollection(ILifecycleListenersCollection lifecycleListenersCollection) {
        this(new LogicCollection(lifecycleListenersCollection));
    }

    public <TPresenter extends IPresenter<TPresenterView>, TPresenterView>
    TPresenter registerPresenter(TPresenter presenter, TPresenterView presenterView) {
        logicCollection.registerLogic(presenter);

        presenter.setView(presenterView);

        return presenter;
    }
}
