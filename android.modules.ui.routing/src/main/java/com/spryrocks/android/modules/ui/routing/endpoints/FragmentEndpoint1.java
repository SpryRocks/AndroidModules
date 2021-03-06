package com.spryrocks.android.modules.ui.routing.endpoints;

import android.os.Bundle;

import com.spryrocks.android.modules.ui.routing.context.IFrameTarget;
import com.spryrocks.android.modules.utils.Actions;

import java.io.Serializable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

@SuppressWarnings("unused")
public class FragmentEndpoint1<TFragment extends Fragment, Arg extends Serializable>
        extends FragmentEndpointBase<TFragment> implements IFrameEndpoint1<Arg> {
    private final Key<Arg> arg1;

    public FragmentEndpoint1(IFrameTarget target, Class<TFragment> tFragmentClass, Key<Arg> arg1) {
        super(target, tFragmentClass);

        this.arg1 = arg1;
    }

    @Override
    public void navigate(@NonNull Arg arg, @Nullable Actions.Action1<FrameEndpointSettings> setupSettingsCallback) {
        Wrapper<TFragment> wrapper = createWrapper(setupSettingsCallback);

        Bundle bundle = new Bundle();

        arg1.setArgument(bundle, arg);

        wrapper.wrapped.setArguments(bundle);

        wrapper.start();
    }
}
