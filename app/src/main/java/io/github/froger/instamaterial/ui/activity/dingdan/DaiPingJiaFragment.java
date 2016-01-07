package io.github.froger.instamaterial.ui.activity.dingdan;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.github.froger.instamaterial.R;
import io.github.froger.instamaterial.ui.activity.fragment.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class DaiPingJiaFragment extends BaseFragment {


    private View view;

    public DaiPingJiaFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_dai_ping_jia, container, false);
        return view;
    }

    @Override
    public String getTitle() {
        return "待评价";
    }
}
