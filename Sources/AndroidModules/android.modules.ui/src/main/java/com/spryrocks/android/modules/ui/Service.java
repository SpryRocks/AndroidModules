package com.spryrocks.android.modules.ui;

import com.spryrocks.android.modules.ui.lifecycle.IServiceLifecycleListener;
import com.spryrocks.android.modules.ui.lifecycle.IServiceLifecycleListenersCollection;
import com.spryrocks.android.modules.ui.lifecycle.ServiceLifecycleListenersCollection;

public abstract class Service extends android.app.Service implements IServiceLifecycleListenersCollection {
    private final ServiceLifecycleListenersCollection lifecycleListenersCollection;

    public Service() {
        lifecycleListenersCollection = new ServiceLifecycleListenersCollection();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        lifecycleListenersCollection.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        lifecycleListenersCollection.onDestroy();
    }

    @Override
    public <T extends IServiceLifecycleListener> T registerLifecycleListener(T lifecycleListener) {
        return lifecycleListenersCollection.registerLifecycleListener(lifecycleListener);
    }
}
