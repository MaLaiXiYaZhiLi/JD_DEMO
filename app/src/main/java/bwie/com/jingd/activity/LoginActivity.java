package bwie.com.jingd.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import bwie.com.jingd.R;
import bwie.com.jingd.bean.DengluBean;
import bwie.com.jingd.bean.PersonInfoBean;
import bwie.com.jingd.presenter.LoginPresenter;
import bwie.com.jingd.presenter.PersonPresenter;
import bwie.com.jingd.view.LoginViewListener;
import bwie.com.jingd.view.PersonView;

//登录页面
public class LoginActivity extends AppCompatActivity implements LoginViewListener, PersonView {


    @BindView(R.id.denglu_zh)
    EditText dengluZh;
    @BindView(R.id.denglu_pwd)
    EditText dengluPwd;
    @BindView(R.id.eye_btn)
    ImageView eyeBtn;
    @BindView(R.id.denglu_btn)
    TextView dengluBtn;
    @BindView(R.id.zhuce)
    TextView zhuce;
    private LoginPresenter presenter;
    private PersonPresenter dataPresenter;
    private SharedPreferences.Editor edit;
    private SharedPreferences config;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        //shaerdpreferences 存取数据
        config = getSharedPreferences("config", 0);
        //拿到编辑对象
        edit = config.edit();

        //调用p层
        presenter = new LoginPresenter(this);

        //从presenter调用个人中心的接口
        dataPresenter = new PersonPresenter(this);
        dataPresenter.getData(92);
    }

    @OnClick({R.id.denglu_cha, R.id.zhuce, R.id.denglu_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.denglu_cha:
                //返回到之前的fragment
                finish();
                break;
            case R.id.zhuce:
                // 手机快速注册按钮，跳转至注册页面
                Intent intent = new Intent(LoginActivity.this, RegActivity.class);
                startActivity(intent);
                break;

            case R.id.denglu_btn:
                if (!TextUtils.isEmpty(dengluZh.getText().toString()) && !TextUtils.isEmpty(dengluPwd.getText().toString())) {

                    dengluBtn.setBackgroundColor(Color.RED);        //信息都不为空时登录按钮为红色

                    //如果都不为空,请求接口
                    presenter.getLogin(dengluZh.getText().toString(), dengluPwd.getText().toString());
                } else {

                    dengluBtn.setBackgroundColor(Color.LTGRAY);        //信息都为空时登录按钮为浅灰色

                    Toast.makeText(this, "账号或密码不能为空", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


    @Override
    public void success(DengluBean dengluBean) {

        if (dengluBean.getMsg().equals("登录成功")) {

            //获取用户id
            int ui = dengluBean.getData().getUid();
            String uid = String.valueOf(ui);
            //sharedpreferences存值,记录已经登录
            edit.putString("uid", uid);//将当前的uid存进去
            edit.commit();
            //打印输出一下 看看是否存进去
            System.out.println("LoginActivity uid:" + config.getString("uid", null));

            //eventbus传值 登录信息
            EventBus.getDefault().post(dengluBean);
            finish();

            //登录成功以后 跳转到首页页面,也就是 HomeActivity
            Intent intent = new Intent(LoginActivity.this, ShowActivity.class);
            startActivity(intent);
            finish();

        } else {
            Toast.makeText(LoginActivity.this, dengluBean.getMsg(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void failure(Throwable e) {
        Toast.makeText(this, "登录失败！请检查登录信息" + e, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void failed(int code) {
        Toast.makeText(LoginActivity.this, "个人数据错误：" + code, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void sucess(PersonInfoBean personInfoBean) {
        //Toast.makeText(getActivity(),"个人数据："+personInfoBean.getMsg()+"            "+personInfoBean.toString(),Toast.LENGTH_SHORT).show();

//        //eventbus传值 个人数据
//        EventBus.getDefault().post(personInfoBean);
//        finish();

    }
}
