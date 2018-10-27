package com.spryrocks.android.modules.ui.routing.context;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;

public interface IScreenTarget extends ITarget {
    @NonNull
    Context getContext();

    void startActivity(@NonNull Intent intent);
}
