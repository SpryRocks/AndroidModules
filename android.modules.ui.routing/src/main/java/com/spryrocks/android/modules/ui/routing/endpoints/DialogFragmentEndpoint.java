package com.spryrocks.android.modules.ui.routing.endpoints;

import com.spryrocks.android.modules.ui.routing.context.IDialogTarget;
import com.spryrocks.android.modules.utils.Actions;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

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
