package com.example.android.modules.ui.lifecycle;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

public interface IFragmentLifecycleListener extends ILifecycleListener {
    void onActivityCreated(@Nullable Bundle savedInstanceState);

    void onAttach(Context context);
    void onDetach();
}
