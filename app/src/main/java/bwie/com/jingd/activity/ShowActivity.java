package bwie.com.jingd.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import com.hjm.bottomtabbar.BottomTabBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import bwie.com.jingd.R;
import bwie.com.jingd.fragment.FragmentCart;
import bwie.com.jingd.fragment.FragmentClass;
import bwie.com.jingd.fragment.FragmentFind;
import bwie.com.jingd.fragment.FragmentFistPage;
import bwie.com.jingd.fragment.FragmentMine;

public class ShowActivity extends AppCompatActivity {

    @BindView(R.id.bottomTabBar)
    BottomTabBar bottomTabBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        ButterKnife.bind(this);
        bottomTabBar.init(getSupportFragmentManager())//初始化方法布局管理
                .setFontSize(0)//设置文字大小
                //参数1：选中后的颜色，参数2：选中前的颜色
                //参数1：文字内容。参数2：导航图片。参数3：切换哪个fragment类
                .setImgSize(200,200)
                .setTabPadding(4,3,1)
                .addTabItem("首页", R.drawable.ac1, R.drawable.ac0, FragmentFistPage.class)
                .addTabItem("分类", R.drawable.abx, R.drawable.abw, FragmentClass.class)
                .addTabItem("发现", R.drawable.abz, R.drawable.aby, FragmentFind.class)
                .addTabItem("购物车", R.drawable.abv, R.drawable.abu, FragmentCart.class)
                .addTabItem("我的", R.drawable.ac3, R.drawable.ac2, FragmentMine.class)
                .isShowDivider(false);
    }
}
