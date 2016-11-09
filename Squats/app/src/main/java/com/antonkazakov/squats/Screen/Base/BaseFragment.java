package com.antonkazakov.squats.Screen.Base;


import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.antonkazakov.squats.AppDelegate;
import com.antonkazakov.squats.di.components.AppComponent;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by antonkazakov on 17.10.16.
 */

public abstract class BaseFragment extends Fragment {

    private Unbinder unbinder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies(AppDelegate.getAppComponent(AppDelegate.getContext()));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getFragmentLayout(), container, false);
    }

    protected abstract @LayoutRes int getFragmentLayout();

    protected abstract void injectDependencies(AppComponent component);


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
