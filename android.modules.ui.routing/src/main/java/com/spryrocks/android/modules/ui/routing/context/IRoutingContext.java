package com.spryrocks.android.modules.ui.routing.context;

import android.content.Context;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

@SuppressWarnings("unused")
public interface IRoutingContext {
    static IApplicationRoutingContext application(@NonNull Context context) {
        return new ApplicationRoutingContext(context);
    }

    static IScreenRoutingContext screen(@NonNull FragmentActivity activity) {
        return new ScreenRoutingContext(activity, application(activity));
    }

    static IScreenRoutingContext screen(@NonNull Fragment fragment) {
        FragmentActivity activity = fragment.getActivity();
        if (activity == null) {
            throw new RuntimeException("Activity should be not null");
        }
        
        return new ScreenRoutingContext(fragment, application(activity));
    }

    static IFrameRoutingContext frame(@NonNull FragmentActivity activity, @IdRes int containerResId) {
        IScreenRoutingContext screenRoutingContext = screen(activity);

        return new FrameRoutingContext(activity, screenRoutingContext, screenRoutingContext, containerResId);
    }

    static IFrameRoutingContext frame(@NonNull Fragment fragment, boolean useChildFragmentManager, @IdRes int containerResId) {
        FragmentActivity activity = fragment.getActivity();
        if (activity == null)
            throw new RuntimeException("Activity cannot be null");

        IScreenRoutingContext screenRoutingContext = screen(activity);

        return new FrameRoutingContext(fragment, useChildFragmentManager, screenRoutingContext, screenRoutingContext, containerResId);
    }
}
