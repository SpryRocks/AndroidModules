package com.spryrocks.android.modules.ui.routing.endpoints;

import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

import com.spryrocks.android.modules.ui.routing.context.IDialogTarget;
import com.spryrocks.android.modules.utils.Actions;

@SuppressWarnings("unused")
public class DialogFragmentEndpoint<TDialogFragment extends DialogFragment> extends DialogFragmentEndpointBase<TDialogFragment> implements IDialogEndpoint {
    public DialogFragmentEndpoint(IDialogTarget target, Class<TDialogFragment> tFragmentClass) {
        super(target, tFragmentClass);
    }

    @Override
    public void navigate(@Nullable Actions.Action1<DialogEndpointSettings> setupSettingsCallback) {
        Wrapper<TDialogFragment> wrapper = createWrapper(setupSettingsCallback);
        wrapper.start();
    }
}
