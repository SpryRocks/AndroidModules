package com.spryrocks.android.modules.ui.routing.endpoints;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import java.io.Serializable;

public interface IFrameEndpointBase {
    class Key<T extends Serializable> extends EndpointArgumentKey<T, Fragment> {
        public Key(String key) {
            super(key);
        }

        @Override
        T getArgument(Fragment view) {
            Bundle arguments = view.getArguments();
            if (arguments == null)
                return null;

            //noinspection unchecked
            return (T) arguments.getSerializable(key);
        }

        public void setArgument(Bundle bundle, T arg) {
            bundle.putSerializable(key, arg);
        }
    }
}
