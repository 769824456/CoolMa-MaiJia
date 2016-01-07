package io.github.froger.instamaterial.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import io.github.froger.instamaterial.R;

public class YouHuiActivity extends BaseDrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_hui);
    }

    public void sms(View view) {
        Uri smsToUri = Uri.parse("smsto:400 10086");
        Intent mIntent = new Intent(android.content.Intent.ACTION_SENDTO, smsToUri);
        mIntent.putExtra("sms_body", "nuxptue");
        startActivity(mIntent);
    }

    public void email(View view) {
        Intent data = new Intent(Intent.ACTION_SENDTO);
        data.setData(Uri.parse("mailto:yulongsun0425@gmail.com"));
        data.putExtra(Intent.EXTRA_SUBJECT, "优惠码");
        data.putExtra(Intent.EXTRA_TEXT, "nuxptue");
        startActivity(data);

    }
}
