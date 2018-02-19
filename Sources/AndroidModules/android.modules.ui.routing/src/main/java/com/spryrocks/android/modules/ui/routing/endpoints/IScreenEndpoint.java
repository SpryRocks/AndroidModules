package com.spryrocks.android.modules.ui.routing.endpoints;

import android.content.Intent;
import android.support.annotation.Nullable;

import com.spryrocks.android.modules.utils.Actions;

public interface IScreenEndpoint extends IEndpoint<ScreenEndpointSettings> {
    Intent intent(@Nullable Actions.Action1<ScreenEndpointSettings> setupSettingsCallback);

    @SuppressWarnings("unused")
    default Intent intent() {
        return intent(null);
    }
}
