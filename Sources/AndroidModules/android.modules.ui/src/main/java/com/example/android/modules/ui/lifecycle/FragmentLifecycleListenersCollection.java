package com.example.android.modules.ui.lifecycle;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

public class FragmentLifecycleListenersCollection extends LifecycleListenersCollection implements IFragmentLifecycleListener {
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        for (ILifecycleListener listener : listeners) {
            if (listener instanceof IFragmentLifecycleListener) {
                ((IFragmentLifecycleListener) listener).onActivityCreated(savedInstanceState);
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        for (ILifecycleListener listener : listeners) {
            if (listener instanceof IFragmentLifecycleListener) {
                ((IFragmentLifecycleListener) listener).onAttach(context);
            }
        }
    }

    @Override
    public void onDetach() {
        for (ILifecycleListener listener : listeners) {
            if (listener instanceof IFragmentLifecycleListener) {
                ((IFragmentLifecycleListener) listener).onDetach();
            }
        }
    }
}
