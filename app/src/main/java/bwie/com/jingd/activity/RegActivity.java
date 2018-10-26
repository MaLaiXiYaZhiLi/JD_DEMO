package bwie.com.jingd.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import bwie.com.jingd.R;
import bwie.com.jingd.bean.RegisterBean;
import bwie.com.jingd.presenter.RegPresenter;
import bwie.com.jingd.presenter.RegisterPresenter;
import bwie.com.jingd.view.RegisterViewListener;

public class RegActivity extends AppCompatActivity implements RegisterViewListener {

    @BindView(R.id.zhuce_cha)
    ImageView zhuceCha;
    @BindView(R.id.zhuce_zh)
    EditText zhuceZh;
    @BindView(R.id.zhuce_pwd)
    EditText zhucePwd;
    @BindView(R.id.eye_btn)
    ImageView eyeBtn;
    @BindView(R.id.register)
    Button register;
  //  private RegisterPresenter registerPresenter;
    private RegPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        ButterKnife.bind(this);
       // registerPresenter = new RegisterPresenter(this);
        presenter = new RegPresenter(this);
    }


    @Override
    public void success(RegisterBean registerBean) {
        Toast.makeText(this,registerBean.getMsg(),Toast.LENGTH_LONG).show();
//
    }

    @Override
    public void failure(Throwable e) {
        Toast.makeText(this,"注册失败",Toast.LENGTH_LONG).show();
    }


    @OnClick(R.id.register)
    public void onViewClicked() {
//        Toast.makeText(RegActivity.this, "点击注册", Toast.LENGTH_SHORT).show();
        if (!TextUtils.isEmpty(zhuceZh.getText().toString()) && !TextUtils.isEmpty(zhucePwd.getText().toString())) {

            register.setBackgroundColor(Color.RED);        //信息都不为空时登录按钮为红色

            //如果都不为空,请求接口
            String s = zhuceZh.getText().toString();
            Log.i("aaa",s);
            String s1 = zhucePwd.getText().toString();
            Log.i("aaa",s1);
            presenter.getReg(s,s1);
        } else {
            register.setBackgroundColor(Color.LTGRAY);        //信息都为空时登录按钮为浅灰色
            Toast.makeText(RegActivity.this, "账号或密码不能为空", Toast.LENGTH_SHORT).show();
        }

    }
}
