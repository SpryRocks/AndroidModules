package com.spryrocks.android.modules.ui.routing.endpoints;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.spryrocks.android.modules.utils.Actions;

import java.io.Serializable;

public interface IEndpoint1<Arg extends Serializable, TSettings> {
    void navigate(@NonNull Arg arg, @Nullable Actions.Action1<TSettings> setupSettingsCallback);

    @SuppressWarnings("unused")
    default void navigate(@NonNull Arg arg) {
        navigate(arg, null);
    }
}
