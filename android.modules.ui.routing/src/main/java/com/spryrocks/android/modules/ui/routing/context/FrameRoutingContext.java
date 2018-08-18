package com.spryrocks.android.modules.ui.routing.context;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.spryrocks.android.modules.ui.IOnBackPressedSupport;
import com.spryrocks.android.modules.ui.util.RoutingUI;
import com.spryrocks.android.modules.utils.Actions;

public class FrameRoutingContext implements IFrameRoutingContext {
    @NonNull
    private final Context context;

    @NonNull
    private final IScreenTarget screenTarget;
    @NonNull
    private final IDialogTarget dialogTarget;

    @NonNull
    private final FragmentManager fragmentManager;
    @IdRes
    private final int containerViewId;

    @Nullable
    private Actions.Action1<Fragment> onCurrentFragmentChangedListener;

    FrameRoutingContext(FragmentActivity activity, IScreenTarget screenTarget, IDialogTarget dialogTarget, @IdRes int containerViewId) {
        this(activity, screenTarget, dialogTarget, activity.getSupportFragmentManager(), containerViewId);
    }

    FrameRoutingContext(@NonNull Fragment fragment, boolean useChildFragmentManager, @NonNull IScreenTarget screenTarget, @NonNull IDialogTarget dialogTarget, @IdRes int containerViewId) {
        this(fragment.getContext(), screenTarget, dialogTarget, !useChildFragmentManager ? fragment.getFragmentManager() : fragment.getChildFragmentManager(), containerViewId);
    }

    private FrameRoutingContext(@Nullable Context context, @NonNull IScreenTarget screenTarget, @NonNull IDialogTarget dialogTarget, @Nullable FragmentManager fragmentManager, int containerViewId) {
        if (context == null)
            throw new RuntimeException("context could not be null");
        if (fragmentManager == null)
            throw new RuntimeException("fragmentManager could not be null");

        this.context = context;
        this.screenTarget = screenTarget;
        this.dialogTarget = dialogTarget;
        this.fragmentManager = fragmentManager;
        this.containerViewId = containerViewId;
        this.fragmentManager.addOnBackStackChangedListener(onBackStackChangedListener);
    }

    @Override
    public void startActivity(@NonNull Intent intent) {
        screenTarget.startActivity(intent);
    }

    @Override
    public void replaceFragment(@NonNull Fragment fragment, boolean clearBackStack, boolean hideKeyboard) {
        FragmentTransaction transaction = fragmentManager.beginTransaction()
                .replace(containerViewId, fragment);

        boolean addToBackStack = fragmentManager.findFragmentById(containerViewId) != null;

        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        else {
            currentFragmentChanged(fragment);
        }

        // TODO: 28.10.2017  clear back stack


        if (hideKeyboard)
            RoutingUI.hideKeyboard(fragment.getActivity());

        transaction.commit();
    }

    @Override
    public void showDialog(@NonNull DialogFragment dialogFragment) {
        dialogTarget.showDialog(dialogFragment);
    }

    @Override
    public boolean onBackPressed() {
        if (IOnBackPressedSupport.onBackPressed(getCurrentFragment()))
            return true;

        if (fragmentManager.getBackStackEntryCount() < 1)
            return false;

        fragmentManager.popBackStack();
        return true;
    }

    @Override
    @Nullable
    public Fragment getCurrentFragment() {
        return fragmentManager.findFragmentById(containerViewId);
    }

    @NonNull
    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public void setOnCurrentFragmentChangedListener(@Nullable Actions.Action1<Fragment> onCurrentFragmentChangedListener) {
        this.onCurrentFragmentChangedListener = onCurrentFragmentChangedListener;
    }

    @SuppressWarnings({"Convert2Lambda", "FieldCanBeLocal"})
    private final FragmentManager.OnBackStackChangedListener onBackStackChangedListener = new FragmentManager.OnBackStackChangedListener() {
        @Override
        public void onBackStackChanged() {
            Fragment currentFragment = getCurrentFragment();
            if (currentFragment != null) {
                currentFragmentChanged(currentFragment);
            }
        }
    };

    private void currentFragmentChanged(@NonNull Fragment fragment) {
        if (onCurrentFragmentChangedListener != null) {
            onCurrentFragmentChangedListener.run(fragment);
        }
    }
}
