package com.spryrocks.android.modules.ui.routing.context;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.spryrocks.android.modules.utils.Actions;

@SuppressWarnings("unused")
public interface IFrameTarget extends ITarget {
    void replaceFragment(@NonNull Fragment fragment, boolean clearBackStack);
    void setOnCurrentFragmentChangedListener(@Nullable Actions.Action1<Fragment> onCurrentFragmentChangedListener);
    @Nullable Fragment getCurrentFragment();
}
