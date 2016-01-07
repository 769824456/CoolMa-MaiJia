package io.github.froger.instamaterial.ui.activity.dingdan;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.github.froger.instamaterial.R;
import io.github.froger.instamaterial.ui.activity.fragment.BaseFragment;
import io.github.froger.instamaterial.ui.utils.PromptManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class DaiShouHuoFragment extends BaseFragment {


    private View view;

    public DaiShouHuoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dai_shou_huo, container, false);
        return view;
    }

    @Override
    public String getTitle() {
        return "代收货";
    }

    public void yanchangfahuo(View view) {
        PromptManager.showToast(getActivity(), "延长收货");
    }

    public void chakanwuliu(View view) {
        PromptManager.showToast(getActivity(), "查看物流");
    }

    public void querenshouhuo(View view) {
        PromptManager.showToast(getActivity(), "确认收货");
    }
}
