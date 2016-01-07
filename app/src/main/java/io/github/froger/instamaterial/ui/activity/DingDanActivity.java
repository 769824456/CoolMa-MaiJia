package io.github.froger.instamaterial.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.froger.instamaterial.R;
import io.github.froger.instamaterial.ui.activity.dingdan.DaiFaHuoFragment;
import io.github.froger.instamaterial.ui.activity.dingdan.DaiPingJiaFragment;
import io.github.froger.instamaterial.ui.activity.dingdan.DaiShouHuoFragment;
import io.github.froger.instamaterial.ui.activity.dingdan.WeiFuKuanFragment;
import io.github.froger.instamaterial.ui.activity.fragment.BaseFragment;

public class DingDanActivity extends BaseDrawerActivity {

    @Bind(R.id.tlDingDanTabs)
    TabLayout tlUserProfileTabs;
    @Bind(R.id.vp_dingdan)
    ViewPager vpDingdan;
    private ArrayList<BaseFragment> mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ding_dan);
        ButterKnife.bind(this);
        setupViewPager();
    }

    private void setupViewPager() {
        tlUserProfileTabs.addTab(tlUserProfileTabs.newTab().setIcon(R.drawable.ic_grid_on_white));
        tlUserProfileTabs.addTab(tlUserProfileTabs.newTab().setIcon(R.drawable.ic_list_white));
        tlUserProfileTabs.addTab(tlUserProfileTabs.newTab().setIcon(R.drawable.ic_place_white));
        mFragments = new ArrayList<BaseFragment>();
        mFragments.add(new WeiFuKuanFragment());
        mFragments.add(new DaiFaHuoFragment());
        mFragments.add(new DaiShouHuoFragment());
        mFragments.add(new DaiPingJiaFragment());
        vpDingdan.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mFragments.get(position).getTitle();
            }
        });
        tlUserProfileTabs.setupWithViewPager(vpDingdan);
    }
}
