package com.spryrocks.android.modules.ui.lifecycle;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public interface IFragmentLifecycleListener extends ILifecycleListener {
    void onActivityCreated(@Nullable Bundle savedInstanceState);

    void onAttach(Context context);
    void onDetach();

    void onViewCreated(View view, @Nullable Bundle savedInstanceState);
    void onDestroyView();
}
