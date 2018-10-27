package com.spryrocks.android.modules.ui.routing.endpoints;

import com.spryrocks.android.modules.ui.routing.context.IFrameTarget;
import com.spryrocks.android.modules.utils.Actions;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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
