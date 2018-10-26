package bwie.com.jingd.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import bwie.com.jingd.R;

//个人信息页面
public class PersonActivity extends AppCompatActivity {


    @BindView(R.id.settings_cha)
    TextView settingsCha;
    @BindView(R.id.usercenter_headPhoto)
    ImageView usercenterHeadPhoto;
    @BindView(R.id.usercenter_name)
    TextView usercenterName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        ButterKnife.bind(this);

        //接收传值,设置头像和用户名
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        usercenterHeadPhoto.setImageResource(R.mipmap.ath);
        usercenterName.setText(username);
    }

    //退出个人信息页面
    @OnClick(R.id.settings_cha)
    public void onViewClicked() {
        finish();
    }
}
