package bwie.com.jingd.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.youth.banner.Banner;
import com.youth.banner.Transformer;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import bwie.com.jingd.R;
import bwie.com.jingd.bean.DetailBean;
import bwie.com.jingd.bean.MessageBean;
import bwie.com.jingd.fragment.FragmentCart;
import bwie.com.jingd.presenter.DetailPresenter;
import bwie.com.jingd.utils.GlideUtils;
import bwie.com.jingd.view.IProductDetailView;

public class DetailActivity extends AppCompatActivity implements IProductDetailView {


    @BindView(R.id.detail_image_back)
    ImageView detailImageBack;
    @BindView(R.id.detail_banner)
    Banner detailBanner;
    @BindView(R.id.detail_title)
    TextView detailTitle;
    @BindView(R.id.detail_yuan_price)
    TextView detailYuanPrice;
    @BindView(R.id.tv_detail)
    TextView tvDetail;
    @BindView(R.id.detail_bargin_price)
    TextView detailBarginPrice;
    @BindView(R.id.watch_cart)
    ImageView watchCart;
    @BindView(R.id.detai_add_cart)
    TextView detaiAddCart;
    @BindView(R.id.detail_shopcar)
    TextView detailShopcar;
    private DetailPresenter detailPresenter;
    private int pid;
    private List<String> list = new ArrayList<>();
    private DetailBean.DataBean data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        detailPresenter = new DetailPresenter(this);
        initData();
        Intent intent = getIntent();
        pid = intent.getIntExtra("pid", pid);
//        if (pid != -1) {
        detailPresenter.detail(pid);
//        }
    }

    private void initData() {
        detaiAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DetailActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void success(DetailBean detailBean) {
        data = detailBean.getData();
        String images = data.getImages().replace("https", "http");
        String[] split = images.split("\\|");
        for (int i = 0; i < split.length; i++) {
            list.add(split[i]);
            Log.e("listimg", list.size() + "");
        }
        detailBanner.setImageLoader(new GlideUtils());
        detailBanner.setDelayTime(2000);
        detailBanner.setImages(list);
        detailBanner.isAutoPlay(true);
        detailBanner.setBannerAnimation(Transformer.DepthPage);
        detailBanner.start();

        Log.e("detailbanner", list.size() + "");

        detailTitle.setText(data.getTitle());
        detailYuanPrice.setText(data.getBargainPrice() + "");
        detailYuanPrice.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);//下划线
        detailYuanPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中间横线（删除线）
        detailYuanPrice.getPaint().setAntiAlias(true);// 抗锯齿
        detailBarginPrice.setText("¥" + data.getPrice() + "");
        tvDetail.setText(data.getSubhead());


    }

    @Override
    public void failure(String msg) {

    }


    @OnClick(R.id.detail_shopcar)
    public void onViewClicked() {
//        Toast.makeText(this,"dianjile",Toast.LENGTH_SHORT).show();
//        MessageBean messageBean = new MessageBean();
//        messageBean.setDetail("DetailActivity");
//        EventBus.getDefault().post(messageBean);
//        Intent intent = new Intent(this, FragmentCart.class);
//        startActivity(intent);
    }
}
