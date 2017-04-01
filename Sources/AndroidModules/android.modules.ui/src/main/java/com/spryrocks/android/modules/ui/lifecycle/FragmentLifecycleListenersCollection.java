package com.spryrocks.android.modules.ui.lifecycle;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

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

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        for (ILifecycleListener listener : listeners) {
            if (listener instanceof IFragmentLifecycleListener) {
                ((IFragmentLifecycleListener) listener).onViewCreated(view, savedInstanceState);
            }
        }
    }

    @Override
    public void onDestroyView() {
        for (ILifecycleListener listener : listeners) {
            if (listener instanceof IFragmentLifecycleListener) {
                ((IFragmentLifecycleListener) listener).onDestroyView();
            }
        }
    }
}
