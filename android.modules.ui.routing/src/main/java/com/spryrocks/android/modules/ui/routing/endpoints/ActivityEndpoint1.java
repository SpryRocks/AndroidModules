package com.spryrocks.android.modules.ui.routing.endpoints;

import android.app.Activity;
import android.content.Intent;

import com.spryrocks.android.modules.ui.routing.context.IScreenTarget;
import com.spryrocks.android.modules.utils.Actions;

import java.io.Serializable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

@SuppressWarnings("unused")
public class ActivityEndpoint1<TActivity extends Activity, Arg extends Serializable>
        extends ActivityEndpointBase<TActivity>
        implements IScreenEndpoint1<Arg> {
    private final Key<Arg> arg1;

    public ActivityEndpoint1(IScreenTarget target, Class<TActivity> tActivityClass, Key<Arg> arg1) {
        super(target, tActivityClass);

        this.arg1 = arg1;
    }

    @Override
    public void navigate(@NonNull Arg arg, @Nullable Actions.Action1<ScreenEndpointSettings> setupSettingsCallback) {
        Wrapper wrapper = createWrapper(setupSettingsCallback);

        prepareIntent(wrapper.getWrapped(), arg);

        wrapper.start();
    }

    @Override
    public Intent intent(@NonNull Arg arg, @Nullable Actions.Action1<ScreenEndpointSettings> setupSettingsCallback) {
        Wrapper wrapper = createWrapper(setupSettingsCallback);

        return prepareIntent(wrapper.getWrapped(), arg);
    }

    private Intent prepareIntent(@NonNull Intent intent, @NonNull Arg arg) {
        arg1.setArgument(intent, arg);
        return intent;
    }
}
