package com.example.android.modules.ui.lifecycle;

import android.os.Bundle;

public interface IActivityLifecycleListener extends ILifecycleListener {
    void onRestoreInstanceState(Bundle savedInstanceState);
}
