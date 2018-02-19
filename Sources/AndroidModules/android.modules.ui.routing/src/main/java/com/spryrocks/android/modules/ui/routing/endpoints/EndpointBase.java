package com.spryrocks.android.modules.ui.routing.endpoints;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.spryrocks.android.modules.ui.routing.context.ITarget;
import com.spryrocks.android.modules.utils.Actions;

@SuppressWarnings("WeakerAccess")
abstract class EndpointBase<TTarget extends ITarget, T, TWrapped, TWrapper extends EndpointBase.Wrapper<TTarget, TWrapped, TSettings>, TSettings> {
    protected final TTarget target;
    protected final Class<T> tClass;

    EndpointBase(TTarget target, Class<T> tClass) {
        if (target == null)
            throw new IllegalArgumentException("Argument 'target' should be not null");

        this.target = target;
        this.tClass = tClass;
    }

    @NonNull
    protected TWrapper createWrapper(@Nullable Actions.Action1<TSettings> setupSettingsCallback) {
        TWrapper wrapper = createWrapper(target, tClass);

        TSettings settings = getSettings(setupSettingsCallback);

        wrapper.setup(settings);

        return wrapper;
    }

    @NonNull
    protected abstract TWrapper createWrapper(TTarget target, Class<T> tClass);

    @Nullable
    protected TSettings getSettings(@Nullable Actions.Action1<TSettings> setupSettingsCallback) {
        TSettings settings = null;

        if (setupSettingsCallback != null) {
            settings = createSettings();
            setupSettingsCallback.run(settings);
        }

        return settings;
    }

    @NonNull
    protected abstract TSettings createSettings();

    protected static abstract class Wrapper<TTarget extends ITarget, TWrapped, TSettings> {
        protected final TTarget target;
        protected final TWrapped wrapped;

        protected Wrapper(TTarget target, TWrapped wrapped) {
            this.target = target;
            this.wrapped = wrapped;
        }

        TWrapped getWrapped() {
            return wrapped;
        }

        abstract void setup(@Nullable TSettings settings);

        abstract void start();
    }
}
