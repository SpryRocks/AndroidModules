package com.spryrocks.android.modules.ui.routing.endpoints;

import java.io.Serializable;

public abstract class EndpointArgumentKey<T extends Serializable, TView> {
    @SuppressWarnings("WeakerAccess")
    public final String key;

    EndpointArgumentKey(String key) {
        this.key = key;
    }

    abstract T getArgument(TView view);

    @SuppressWarnings({"WeakerAccess", "unused"})
    public T getArgument(Object view, @SuppressWarnings("SameParameterValue") boolean isRequired) {
        //noinspection unchecked
        Serializable result = getArgument((TView) view);

        //noinspection ConstantConditions
        if (isRequired && result == null) {
            throw new RuntimeException(key + " argument cannot be null");
        }

        //noinspection unchecked
        return (T) result;
    }
}