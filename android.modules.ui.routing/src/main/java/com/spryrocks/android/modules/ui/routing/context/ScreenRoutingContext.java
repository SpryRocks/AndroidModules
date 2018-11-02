package com.spryrocks.android.modules.ui.routing.context;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

public class ScreenRoutingContext implements IScreenRoutingContext {
    @NonNull
    private final Context context;
    @SuppressWarnings("unused")
    @Nullable
    private final Fragment fragment;
    @NonNull
    private final IScreenTarget screenTarget;
    @NonNull
    private final FragmentManager fragmentManager;

    ScreenRoutingContext(@NonNull FragmentActivity activity, @NonNull IScreenTarget screenTarget) {
        this.context = activity;
        this.fragment = null;
        this.fragmentManager = activity.getSupportFragmentManager();
        this.screenTarget = screenTarget;
    }

    ScreenRoutingContext(@NonNull Fragment fragment, @NonNull IScreenTarget screenTarget) {
        Context context = fragment.getContext();
        if (context == null) {
            throw new RuntimeException("Context should be not null");
        }
        this.context = context;

        this.fragment = fragment;

        this.fragmentManager = fragment.getChildFragmentManager();

        this.screenTarget = screenTarget;
    }

    @Override
    public void startActivity(@NonNull Intent intent) {
        screenTarget.startActivity(intent);
    }

    @Override
    public void showDialog(@NonNull DialogFragment dialogFragment) {
        dialogFragment.show(fragmentManager, null);
    }

    @NonNull
    @Override
    public Context getContext() {
        return context;
    }
}
