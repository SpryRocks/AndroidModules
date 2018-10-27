package com.spryrocks.android.modules.ui.routing.context;

import com.spryrocks.android.modules.utils.Actions;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

@SuppressWarnings("unused")
public interface IFrameTarget extends ITarget {
    void replaceFragment(@NonNull Fragment fragment, boolean clearBackStack, boolean hideKeyboard);
    void setOnCurrentFragmentChangedListener(@Nullable Actions.Action1<Fragment> onCurrentFragmentChangedListener);
    @Nullable Fragment getCurrentFragment();
}
