package com.spryrocks.android.modules.ui.routing.context;

import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

public interface IDialogTarget extends ITarget {
    void showDialog(@NonNull DialogFragment dialogFragment);
}
