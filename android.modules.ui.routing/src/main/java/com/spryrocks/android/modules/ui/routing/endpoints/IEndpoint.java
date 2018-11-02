package com.spryrocks.android.modules.ui.routing.endpoints;

import com.spryrocks.android.modules.utils.Actions;

import androidx.annotation.Nullable;

@SuppressWarnings("WeakerAccess")
public interface IEndpoint<TSettings> {
    void navigate(@Nullable Actions.Action1<TSettings> setupSettingsCallback);

    @SuppressWarnings("unused")
    default void navigate() {
        navigate(null);
    }
}
