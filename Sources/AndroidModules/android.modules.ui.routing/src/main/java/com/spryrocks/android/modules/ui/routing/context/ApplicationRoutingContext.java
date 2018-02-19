package com.spryrocks.android.modules.ui.routing.context;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

public class ApplicationRoutingContext implements IApplicationRoutingContext {
    @NonNull
    private final Context context;

    ApplicationRoutingContext(@NonNull Context context) {
        this.context = context;
    }

    @Override
    public void startActivity(@NonNull Intent intent) {
        context.startActivity(intent);
    }

    @NonNull
    @Override
    public Context getContext() {
        return context;
    }
}
