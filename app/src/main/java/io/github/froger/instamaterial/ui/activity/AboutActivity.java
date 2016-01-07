package io.github.froger.instamaterial.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.froger.instamaterial.R;


public class AboutActivity extends BaseDrawerActivity {
    @Bind(R.id.logo)
    ImageView logo;
    @Bind(R.id.iv_setting_msg)
    ImageView ivSettingMsg;
    @Bind(R.id.s_setting_msg)
    Switch sSettingMsg;
    @Bind(R.id.iv_setting_update)
    ImageView ivSettingUpdate;
    @Bind(R.id.rl_setting_update)
    RelativeLayout rlSettingUpdate;
    @Bind(R.id.iv_setting_help)
    ImageView ivSettingHelp;
    @Bind(R.id.rl_setting_help)
    RelativeLayout rlSettingHelp;
    @Bind(R.id.iv_setting_feedback)
    ImageView ivSettingFeedback;
    @Bind(R.id.rl_setting_feedback)
    RelativeLayout rlSettingFeedback;
    @Bind(R.id.iv_setting_share)
    ImageView ivSettingShare;
    @Bind(R.id.rl_setting_share)
    RelativeLayout rlSettingShare;

    // 首先在您的Activity中添加如下成员变量
//    final UMSocialService mController = UMServiceFactory.getUMSocialService("com.umeng.share");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);

    }

    public void share(View view) {
//        // 设置分享内容
//        mController.setShareContent("友盟社会化组件（SDK）让移动应用快速整合社交分享功能，http://www.umeng.com/social");
//        mController.getConfig().removePlatform(SHARE_MEDIA.RENREN, SHARE_MEDIA.DOUBAN);
//        // 是否只有已登录用户才能打开分享选择页
//        mController.openShare(AboutActivity.this, false);
    }


}
