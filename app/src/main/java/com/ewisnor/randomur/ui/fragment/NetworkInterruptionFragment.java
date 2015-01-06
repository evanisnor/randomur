package com.ewisnor.randomur.ui.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ewisnor.randomur.R;

/**
 * Fragment for displaying a network interruption message.
 */
public class NetworkInterruptionFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_network_interruption, container, false);
    }


}
