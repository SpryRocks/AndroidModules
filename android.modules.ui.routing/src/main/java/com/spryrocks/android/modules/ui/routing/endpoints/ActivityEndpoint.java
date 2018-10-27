package com.spryrocks.android.modules.ui.routing.endpoints;

import android.app.Activity;
import android.content.Intent;

import com.spryrocks.android.modules.ui.routing.context.IScreenTarget;
import com.spryrocks.android.modules.utils.Actions;

import androidx.annotation.Nullable;

@SuppressWarnings("unused")
public class ActivityEndpoint<TActivity extends Activity>
        extends ActivityEndpointBase<TActivity>
        implements IScreenEndpoint {

    public ActivityEndpoint(IScreenTarget target, Class<TActivity> activityClass) {
        super(target, activityClass);
    }

    @Override
    public void navigate(@Nullable Actions.Action1<ScreenEndpointSettings> setupSettingsCallback) {
        createWrapper(setupSettingsCallback).start();
    }

    @Override
    public Intent intent(@Nullable Actions.Action1<ScreenEndpointSettings> setupSettingsCallback) {
        return createWrapper(setupSettingsCallback).getWrapped();
    }
}
