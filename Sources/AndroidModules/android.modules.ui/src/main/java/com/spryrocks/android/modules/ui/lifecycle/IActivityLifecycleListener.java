package com.spryrocks.android.modules.ui.lifecycle;

import android.os.Bundle;

public interface IActivityLifecycleListener extends ILifecycleListener {
    void onRestoreInstanceState(Bundle savedInstanceState);
}
