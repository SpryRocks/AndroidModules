package com.spryrocks.android.modules.ui.routing.endpoints;

import android.content.Intent;

import com.spryrocks.android.modules.utils.Actions;

import java.io.Serializable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public interface IScreenEndpoint1<Arg extends Serializable> extends IEndpoint1<Arg, ScreenEndpointSettings>, IScreenEndpointBase {
    Intent intent(@NonNull Arg arg, @Nullable Actions.Action1<ScreenEndpointSettings> setupSettingsCallback);

    @SuppressWarnings("unused")
    default Intent intent(@NonNull Arg arg) {
        return intent(arg, null);
    }
}
