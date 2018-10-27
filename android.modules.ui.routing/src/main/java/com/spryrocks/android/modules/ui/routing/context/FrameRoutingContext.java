package com.spryrocks.android.modules.ui.routing.context;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.spryrocks.android.modules.ui.IOnBackPressedSupport;
import com.spryrocks.android.modules.ui.utils.KeyboardUtils;
import com.spryrocks.android.modules.utils.Actions;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class FrameRoutingContext implements IFrameRoutingContext {
    @Nullable
    private final Activity activity;
    @Nullable
    private final Fragment fragment;

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
        this(activity, null, screenTarget, dialogTarget, activity.getSupportFragmentManager(), containerViewId);
    }

    FrameRoutingContext(@NonNull Fragment fragment, boolean useChildFragmentManager, @NonNull IScreenTarget screenTarget, @NonNull IDialogTarget dialogTarget, @IdRes int containerViewId) {
        this(null, fragment, screenTarget, dialogTarget, !useChildFragmentManager ? fragment.getFragmentManager() : fragment.getChildFragmentManager(), containerViewId);
    }

    private FrameRoutingContext(@Nullable Activity activity, @Nullable Fragment fragment, @NonNull IScreenTarget screenTarget, @NonNull IDialogTarget dialogTarget, @Nullable FragmentManager fragmentManager, int containerViewId) {
        if (activity == null && fragment == null)
            throw new RuntimeException("activity and fragment cannot be null in same time");

        if (fragmentManager == null)
            throw new RuntimeException("fragmentManager could not be null");

        this.activity = activity;
        this.fragment = fragment;
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
        } else {
            currentFragmentChanged(fragment);
        }

        // TODO: 28.10.2017  clear back stack

        transaction.commit();

        if (hideKeyboard) {
            KeyboardUtils.hideKeyboard(getActivity());
        }
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
    public Activity getActivity() {
        if (activity != null) {
            return activity;
        } else if (fragment != null) {
            return fragment.requireActivity();
        } else {
            throw new RuntimeException("activity and fragment cannot be null in same time");
        }
    }

    @NonNull
    @Override
    public Context getContext() {
        return getActivity();
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
