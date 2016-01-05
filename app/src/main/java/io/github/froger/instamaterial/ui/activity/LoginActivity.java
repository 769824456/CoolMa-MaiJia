package io.github.froger.instamaterial.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.RequestSMSCodeListener;
import cn.bmob.v3.listener.SaveListener;
import io.github.froger.instamaterial.R;
import io.github.froger.instamaterial.ui.model.User;
import io.github.froger.instamaterial.ui.utils.CommonUtils;
import io.github.froger.instamaterial.ui.utils.PromptManager;

public class LoginActivity extends AppCompatActivity {

    int i = 60;
    @Bind(R.id.act_login_mobile)
    AutoCompleteTextView actLoginMobile;
    @Bind(R.id.et_login_code)
    EditText etLoginCode;
    @Bind(R.id.btn_get_code)
    Button btnGetCode;
    @Bind(R.id.btn_login)
    Button btnLogin;
    private String mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setTitleColor(getResources().getColor(R.color.colorPrimary));

    }

    public void getCode(View view) {
        mobile = actLoginMobile.getText().toString();
        if (!CommonUtils.isPhoneNum(LoginActivity.this, mobile)) {
            return;
        }
        BmobSMS.requestSMSCode(LoginActivity.this, mobile, "login", new RequestSMSCodeListener() {

            @Override
            public void done(Integer integer, BmobException e) {
                if (e == null) {//验证码发送成功
                    Toast.makeText(LoginActivity.this, "验证码发送成功", Toast.LENGTH_LONG).show();
                } else {
                    PromptManager.showToast(LoginActivity.this, e.toString());
                }
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (; i > 0; i--) {
                    handler.sendEmptyMessage(-9);
                    if (i <= 0) {
                        break;
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                handler.sendEmptyMessage(-8);
            }
        }).start();
    }

    /**
     * 接收消息
     */
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case -9:
                    btnGetCode.setText("重新发送(" + i + ")");
                    btnGetCode.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                    break;
                case -8:
                    btnGetCode.setText("获取验证码");
                    btnGetCode.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    btnGetCode.setClickable(true);
                    i = 60;
                    break;
                default:
                    break;
            }
        }
    };


    /**
     * 登陆
     *
     * @param view
     */
    public void login(View view) {
        String code = etLoginCode.getText().toString();
        PromptManager.showDialog(this, "登陆中...");
        if (TextUtils.isEmpty(code)) {
            PromptManager.showToast(LoginActivity.this, "验证码不能为空");
            return;
        }
        User user = new User();
        user.setMobilePhoneNumber(mobile);//设置手机号码（必填）
        user.setUserIcon(User.ICON);
        user.setUserType(User.USER_TYPE_GUKE);
        user.setUserJiFen(0);
        user.setUserXinYu(50);
        user.signOrLogin(this, code, new SaveListener() {

            @Override
            public void onSuccess() {
                Log.i("smile", "用户登陆成功");
                PromptManager.showToast(LoginActivity.this, "用户登陆成功");
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(int code, String msg) {
                PromptManager.showToast(LoginActivity.this, "错误码：" + code + ",错误原因：" + msg);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        PromptManager.hideDialog();
    }
}

