package com.spryrocks.android.modules.ui.routing.endpoints;

import android.content.Intent;

import java.io.Serializable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

interface IScreenEndpointBase {
    class Key<T extends Serializable> extends EndpointArgumentKey<T, FragmentActivity> {
        public Key(String key) {
            super(key);
        }

        @Override
        T getArgument(FragmentActivity activity) {
            Intent intent = activity.getIntent();
            if (intent == null)
                return null;

            return getArgument(intent);
        }

        @SuppressWarnings("WeakerAccess")
        @Nullable
        public T getArgument(@NonNull Intent intent) {
            //noinspection unchecked
            return (T) intent.getSerializableExtra(key);
        }

        void setArgument(Intent intent, T arg) {
            intent.putExtra(key, arg);
        }
    }
}
