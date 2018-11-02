package com.spryrocks.android.modules.ui.routing.endpoints;

import com.spryrocks.android.modules.utils.Actions;

import java.io.Serializable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public interface IEndpoint1<Arg extends Serializable, TSettings> {
    void navigate(@NonNull Arg arg, @Nullable Actions.Action1<TSettings> setupSettingsCallback);

    @SuppressWarnings("unused")
    default void navigate(@NonNull Arg arg) {
        navigate(arg, null);
    }
}
