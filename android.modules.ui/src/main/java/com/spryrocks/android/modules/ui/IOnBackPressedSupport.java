package com.spryrocks.android.modules.ui;

public interface IOnBackPressedSupport {
    boolean onBackPressed();

    static <T> boolean onBackPressed(T obj) {
        return obj instanceof IOnBackPressedSupport && ((IOnBackPressedSupport) obj).onBackPressed();
    }
}
