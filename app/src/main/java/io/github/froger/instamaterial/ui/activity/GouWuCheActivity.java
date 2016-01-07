package io.github.froger.instamaterial.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import io.github.froger.instamaterial.R;

public class GouWuCheActivity extends BaseDrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gou_wu_che);
        setTitle("购物车");
    }
}
