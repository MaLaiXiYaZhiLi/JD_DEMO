package bwie.com.jingd.model;

import java.util.HashMap;
import java.util.Map;

import bwie.com.jingd.api.GetDataInterface;
import bwie.com.jingd.bean.DengluBean;
import bwie.com.jingd.bean.RegisterBean;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterModel {
    public void getData(String zhuce_zh, String zhuce_pwd, final RegisterModelCallBack registerModelCallBack) {
        //https://www.zhaoapi.cn/user/reg?mobile=15810680959&password=123456
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.zhaoapi.cn")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetDataInterface service = retrofit.create(GetDataInterface.class);

        Map<String, String> map = new HashMap<>();
        map.put("mobile", zhuce_zh);
        map.put("password", zhuce_pwd);
        service.reg(map).enqueue(new Callback<RegisterBean>() {
            @Override
            public void onResponse(Call<RegisterBean> call, Response<RegisterBean> response) {
                RegisterBean registerBean = response.body();
                registerModelCallBack.success(registerBean);
                System.out.println("注册：" + registerBean.toString());
            }

            @Override
            public void onFailure(Call<RegisterBean> call, Throwable t) {
                registerModelCallBack.failure(new Exception());
            }
        });
    }
}