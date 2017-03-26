package com.example.android.modules.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.example.android.modules.ui.lifecycle.FragmentLifecycleListenersCollection;
import com.example.android.modules.ui.lifecycle.ILifecycleListener;
import com.example.android.modules.ui.lifecycle.ILifecycleListenersCollection;

public class BaseFragment extends Fragment implements ILifecycleListenersCollection {
    private final FragmentLifecycleListenersCollection lifecycleListenersCollection;

    public BaseFragment() {
        lifecycleListenersCollection = new FragmentLifecycleListenersCollection();
    }


    /* lifecycle */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lifecycleListenersCollection.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();

        lifecycleListenersCollection.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();

        lifecycleListenersCollection.onResume();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        lifecycleListenersCollection.onSaveInstanceState(outState);
    }

    @Override
    public void onPause() {
        super.onPause();

        lifecycleListenersCollection.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();

        lifecycleListenersCollection.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        lifecycleListenersCollection.onDestroy();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(lifecycleListenersCollection.onActivityResult(requestCode, resultCode, data))
            return;

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        lifecycleListenersCollection.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        lifecycleListenersCollection.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();

        lifecycleListenersCollection.onDetach();
    }


    /* public methods */

    @Override
    public void registerListener(ILifecycleListener listener) {
        lifecycleListenersCollection.registerListener(listener);
    }
}
