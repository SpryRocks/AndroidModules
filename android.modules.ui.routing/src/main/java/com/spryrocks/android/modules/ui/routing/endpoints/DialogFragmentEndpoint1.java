package com.spryrocks.android.modules.ui.routing.endpoints;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

import com.spryrocks.android.modules.ui.routing.context.IDialogTarget;
import com.spryrocks.android.modules.utils.Actions;

import java.io.Serializable;

@SuppressWarnings("unused")
public class DialogFragmentEndpoint1<TDialogFragment extends DialogFragment, Arg extends Serializable> extends DialogFragmentEndpointBase<TDialogFragment>
        implements IDialogEndpoint1<Arg> {
    private final Key<Arg> arg1;

    public DialogFragmentEndpoint1(IDialogTarget target, Class<TDialogFragment> tFragmentClass, Key<Arg> arg1) {
        super(target, tFragmentClass);
        this.arg1 = arg1;
    }

    @Override
    public void navigate(@NonNull Arg arg, @Nullable Actions.Action1<DialogEndpointSettings> setupSettingsCallback) {
        Wrapper<TDialogFragment> wrapper = createWrapper(setupSettingsCallback);

        Bundle bundle = new Bundle();

        arg1.setArgument(bundle, arg);

        wrapper.wrapped.setArguments(bundle);

        wrapper.start();
    }
}
