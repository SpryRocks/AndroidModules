package com.spryrocks.android.modules.ui.mvp.v1.ui;

import android.content.Context;

public interface IBaseView {
    Context getContext();

    void showError(Exception error);
}
