package com.spryrocks.android.modules.ui.routing.endpoints;

import android.support.annotation.Nullable;

import com.spryrocks.android.modules.utils.Actions;

@SuppressWarnings("WeakerAccess")
public interface IEndpoint<TSettings> {
    void navigate(@Nullable Actions.Action1<TSettings> setupSettingsCallback);

    @SuppressWarnings("unused")
    default void navigate() {
        navigate(null);
    }
}
