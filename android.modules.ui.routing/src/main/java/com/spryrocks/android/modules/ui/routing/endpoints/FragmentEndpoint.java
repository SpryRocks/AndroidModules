package com.spryrocks.android.modules.ui.routing.endpoints;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.spryrocks.android.modules.ui.routing.context.IFrameTarget;
import com.spryrocks.android.modules.utils.Actions;

@SuppressWarnings("unused")
public class FragmentEndpoint<TFragment extends Fragment> extends FragmentEndpointBase<TFragment> implements IFrameEndpoint {
    public FragmentEndpoint(IFrameTarget target, Class<TFragment> tFragmentClass) {
        super(target, tFragmentClass);
    }

    @Override
    public void navigate(@Nullable Actions.Action1<FrameEndpointSettings> setupSettingsCallback) {
        Wrapper<TFragment> wrapper = createWrapper(setupSettingsCallback);
        wrapper.start();
    }
}
