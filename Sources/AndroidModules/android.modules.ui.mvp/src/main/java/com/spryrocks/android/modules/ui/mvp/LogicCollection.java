package com.spryrocks.android.modules.ui.mvp;

import com.spryrocks.android.modules.ui.lifecycle.ILifecycleListener;
import com.spryrocks.android.modules.ui.lifecycle.ILifecycleListenersCollection;

public class LogicCollection {
    private final ILifecycleListenersCollection lifecycleListenersCollection;

    public LogicCollection(ILifecycleListenersCollection lifecycleListenersCollection) {
        this.lifecycleListenersCollection = lifecycleListenersCollection;
    }

    public ILogic registerLogic(ILogic logic) {
        if (logic instanceof ILifecycleListener) {
            lifecycleListenersCollection.registerListener((ILifecycleListener) logic);
        }

        return logic;
    }
}
