package com.spryrocks.android.modules.ui.routing.context;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public interface IDialogTarget extends ITarget {
    void showDialog(@NonNull DialogFragment dialogFragment);
}
