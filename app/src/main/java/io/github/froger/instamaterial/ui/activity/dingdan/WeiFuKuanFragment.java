package io.github.froger.instamaterial.ui.activity.dingdan;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.github.froger.instamaterial.R;
import io.github.froger.instamaterial.ui.activity.fragment.BaseFragment;

public class WeiFuKuanFragment extends BaseFragment {

    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_wei_fu_kuan, container, false);
        return rootView;
    }

    @Override
    public String getTitle() {
        return "未付款";
    }
}
