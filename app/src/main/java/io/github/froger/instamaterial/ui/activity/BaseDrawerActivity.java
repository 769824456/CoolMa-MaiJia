package io.github.froger.instamaterial.ui.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.BindDimen;
import butterknife.BindString;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.OnItemSelected;
import io.github.froger.instamaterial.ui.utils.CircleTransformation;
import io.github.froger.instamaterial.R;

/**
 * Created by Miroslaw Stanek on 15.07.15.
 */
public class BaseDrawerActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    @Bind(R.id.vNavigation)
    NavigationView vNavigation;

    @BindDimen(R.dimen.global_menu_avatar_size)
    int avatarSize;
    @BindString(R.string.user_profile_photo)
    String profilePhoto;

    //Cannot be bound via Butterknife, hosting view is initialized later (see setupHeader() method)
    private ImageView ivMenuUserProfilePhoto;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentViewWithoutInject(R.layout.activity_drawer);
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.flContentRoot);
        LayoutInflater.from(this).inflate(layoutResID, viewGroup, true);
        bindViews();
        setupHeader();
        vNavigation.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void setupToolbar() {
        super.setupToolbar();
        if (getToolbar() != null) {
            getToolbar().setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    drawerLayout.openDrawer(Gravity.LEFT);
                }
            });
        }
    }

    private void setupHeader() {
        View headerView = vNavigation.getHeaderView(0);
        ivMenuUserProfilePhoto = (ImageView) headerView.findViewById(R.id.ivMenuUserProfilePhoto);
        headerView.findViewById(R.id.vGlobalMenuHeader).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onGlobalMenuHeaderClick(v);
            }
        });

        Picasso.with(this)
                .load(profilePhoto)
                .placeholder(R.drawable.img_circle_placeholder)
                .resize(avatarSize, avatarSize)
                .centerCrop()
                .transform(new CircleTransformation())
                .into(ivMenuUserProfilePhoto);
    }

    public void onGlobalMenuHeaderClick(final View v) {
        drawerLayout.closeDrawer(Gravity.LEFT);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                int[] startingLocation = new int[2];
                v.getLocationOnScreen(startingLocation);
                startingLocation[0] += v.getWidth() / 2;
                UserProfileActivity.startUserProfileFromLocation(startingLocation, BaseDrawerActivity.this);
                overridePendingTransition(0, 0);
            }
        }, 200);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent = null;
        switch (id) {
            case R.id.menu_gouwuche:
                break;

            case R.id.menu_order:
                break;

            case R.id.menu_youhuima:
                break;

            case R.id.menu_shangchen:
                gotoShangChen();
                break;
            case R.id.menu_settings:
                intent = new Intent(BaseDrawerActivity.this, SettingsActivity.class);
                break;
            case R.id.menu_about:
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void gotoShangChen() {
        Intent intent = new Intent();
        intent.setClass(BaseDrawerActivity.this, CreditActivity.class);
        intent.putExtra("navColor", "#3F51B5");    //配置导航条的背景颜色，请用#ffffff长格式。
        intent.putExtra("titleColor", "#ffffff");    //配置导航条标题的颜色，请用#ffffff长格式。
        intent.putExtra("url", "http://www.duiba.com.cn/test/demoRedirectSAdfjosfdjdsa");    //配置自动登陆地址，每次需服务端动态生成。
        startActivity(intent);

        CreditActivity.creditsListener = new CreditActivity.CreditsListener() {
            /**
             * 当点击分享按钮被点击
             * @param shareUrl 分享的地址
             * @param shareThumbnail 分享的缩略图
             * @param shareTitle 分享的标题
             * @param shareSubtitle 分享的副标题
             */
            public void onShareClick(WebView webView, String shareUrl, String shareThumbnail, String shareTitle, String shareSubtitle) {
                //当分享按钮被点击时，会调用此处代码。在这里处理分享的业务逻辑。
                new AlertDialog.Builder(webView.getContext())
                        .setTitle("分享信息")
                        .setItems(new String[]{"标题：" + shareTitle, "副标题：" + shareSubtitle, "缩略图地址：" + shareThumbnail, "链接：" + shareUrl}, null)
                        .setNegativeButton("确定", null)
                        .show();
            }

            /**
             * 当点击登录
             * @param webView 用于登录成功后返回到当前的webview并刷新。
             * @param currentUrl 当前页面的url
             */
            public void onLoginClick(WebView webView, String currentUrl) {
                //当未登录的用户点击去登录时，会调用此处代码。
                //为了用户登录后能回到之前未登录前的页面。
                //当用户登录成功后，需要重新动态生成一次自动登录url，需包含redirect参数，将currentUrl放入redirect参数。
                new AlertDialog.Builder(webView.getContext())
                        .setTitle("跳转登录")
                        .setMessage("跳转到登录页面？")
                        .setPositiveButton("是", null)
                        .setNegativeButton("否", null)
                        .show();
            }

            /**
             * 当点击“复制”按钮时，触发该方法，回调获取到券码code
             * @param webView webview对象。
             * @param code 复制的券码
             */
            public void onCopyCode(WebView webView, String code) {
                //当未登录的用户点击去登录时，会调用此处代码。
                new AlertDialog.Builder(webView.getContext())
                        .setTitle("复制券码")
                        .setMessage("已复制，券码为：" + code)
                        .setPositiveButton("是", null)
                        .setNegativeButton("否", null)
                        .show();
            }

            /**
             * 积分商城返回首页刷新积分时，触发该方法。
             */
            public void onLocalRefresh(WebView mWebView, String credits) {
                //String credits为积分商城返回的最新积分，不保证准确。
                //触发更新本地积分，这里建议用ajax向自己服务器请求积分值，比较准确。
                Toast.makeText(getApplicationContext(), "触发本地刷新积分：" + credits, Toast.LENGTH_SHORT).show();
            }
        };
    }
}
