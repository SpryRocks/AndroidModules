package com.spryrocks.android.modules.ui.routing.endpoints;

import android.app.Activity;
import android.content.Intent;

import com.spryrocks.android.modules.ui.routing.context.IScreenTarget;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ActivityEndpointBase<TActivity extends Activity>
        extends EndpointBase<IScreenTarget, TActivity, Intent, ActivityEndpointBase.Wrapper, ScreenEndpointSettings> {
    ActivityEndpointBase(IScreenTarget target, Class<TActivity> tActivityClass) {
        super(target, tActivityClass);
    }

    @NonNull
    @Override
    protected Wrapper createWrapper(IScreenTarget target, Class<TActivity> tActivityClass) {
        return new Wrapper(target, new Intent(target.getContext(), tActivityClass));
    }

    @NonNull
    @Override
    protected ScreenEndpointSettings createSettings() {
        return new ScreenEndpointSettings();
    }

    static class Wrapper extends EndpointBase.Wrapper<IScreenTarget, Intent, ScreenEndpointSettings> {
        Wrapper(IScreenTarget target, Intent intent) {
            super(target, intent);
        }

        @Override
        void setup(@Nullable ScreenEndpointSettings settings) {
            if (settings == null)
                return;

            if (settings.restartTask) {
                wrapped.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            }

            if (settings.newTask) {
                wrapped.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
        }

        @Override
        void start() {
            target.startActivity(wrapped);
        }
    }
}
