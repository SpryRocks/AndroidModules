package com.spryrocks.android.modules.ui.routing.endpoints;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

import com.spryrocks.android.modules.ui.routing.context.IDialogTarget;

class DialogFragmentEndpointBase<TDialogFragment extends DialogFragment> extends EndpointBase<IDialogTarget, TDialogFragment, TDialogFragment, DialogFragmentEndpointBase.Wrapper<TDialogFragment>, DialogEndpointSettings> {
    DialogFragmentEndpointBase(IDialogTarget target, Class<TDialogFragment> tFragmentClass) {
        super(target, tFragmentClass);
    }

    @NonNull
    @Override
    protected Wrapper<TDialogFragment> createWrapper(IDialogTarget target, Class<TDialogFragment> tFragmentClass) {
        return new Wrapper<>(target, tFragmentClass);
    }

    @NonNull
    @Override
    protected DialogEndpointSettings createSettings() {
        return new DialogEndpointSettings();
    }

    static class Wrapper<TDialogFragment extends DialogFragment> extends EndpointBase.Wrapper<IDialogTarget, TDialogFragment, DialogEndpointSettings> {
        Wrapper(IDialogTarget target, Class<TDialogFragment> tFragmentClass) {
            super(target, createFragment(tFragmentClass));
        }

        @Override
        void setup(@Nullable DialogEndpointSettings settings) {
            if (settings == null) {
                //noinspection UnnecessaryReturnStatement
                return;
            }

            // use settings
        }

        @Override
        void start() {
            target.showDialog(wrapped);
        }

        private static <T extends DialogFragment> T createFragment(Class<T> tDialogFragmentClass) {
            try {
                return tDialogFragmentClass.newInstance();
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }
}