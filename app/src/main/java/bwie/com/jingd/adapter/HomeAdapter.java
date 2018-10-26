package bwie.com.jingd.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.sunfusheng.marqueeview.MarqueeView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import bwie.com.jingd.R;
import bwie.com.jingd.activity.DetailActivity;
import bwie.com.jingd.bean.HomeBean;
import bwie.com.jingd.utils.GlideUtils;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int BANNER_VIEW_TYPE = 0;//轮播图
    private final int CLASSES_VIEW_TYPE = 1;//频道
    private final int NEWS_VIEW_TYPE = 2;
    private final int TIME_VIEW_TYPE = 3;
    private final int JIU_VIEW_TYPE = 4;
    private final int TUIJIAN_VIEW_TYPE = 5;


    private Context context;
    private List<String> imageList;
    private HomeBean.DataBean data;
    private List<HomeBean.DataBean.BannerBean> banner;
    private List<HomeBean.DataBean.FenleiBean> fenlei;
    private List<HomeBean.DataBean.MiaoshaBean.ListBean> miaoshalist;
    private List<HomeBean.DataBean.TuijianBean.ListBeanX> tuijianlist;
    private MyViewHolder6 hodler6;

    public HomeAdapter(Context context, HomeBean.DataBean data, List<HomeBean.DataBean.BannerBean> banner, List<HomeBean.DataBean.FenleiBean> fenlei, List<HomeBean.DataBean.MiaoshaBean.ListBean> miaosha, List<HomeBean.DataBean.TuijianBean.ListBeanX> tuijian) {
        this.context = context;
        this.banner = banner;
        this.fenlei = fenlei;
        this.miaoshalist = miaosha;
        this.tuijianlist = tuijian;
        imageList = new ArrayList<>();

        for (HomeBean.DataBean.BannerBean bannerBean : banner) {
            imageList.add(bannerBean.getIcon().replace("https","http"));
            System.out.println("aaaa" + imageList);
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {//第0个位置是轮播图
            return BANNER_VIEW_TYPE;
        } else if (position == 1) {
            return CLASSES_VIEW_TYPE;
        } else if (position == 2) {
            return NEWS_VIEW_TYPE;
        } else if (position == 3) {
            return TIME_VIEW_TYPE;
        } else if (position == 4) {
            return JIU_VIEW_TYPE;
        } else if (position == 5) {
            return TUIJIAN_VIEW_TYPE;
        } else {
            return super.getItemViewType(position);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == BANNER_VIEW_TYPE) {//如果viewType是轮播图就去创建轮播图的viewHolder
            view = LayoutInflater.from(context).inflate(R.layout.home_banner, parent, false);
            MyViewHodler1 myViewHodler1 = new MyViewHodler1(view);
            return myViewHodler1;
        } else if (viewType == CLASSES_VIEW_TYPE) {//频道
            view = LayoutInflater.from(context).inflate(R.layout.home_class, parent, false);
            MyViewHodler2 myViewHodler2 = new MyViewHodler2(view);
            return myViewHodler2;
        } else if (viewType == NEWS_VIEW_TYPE) {
            view = LayoutInflater.from(context).inflate(R.layout.home_msg, parent, false);

            MyViewHodler3 hodler3 = new MyViewHodler3(view);

            return hodler3;
        } else if (viewType == TIME_VIEW_TYPE) {
            view = LayoutInflater.from(context).inflate(R.layout.home_time, parent, false);

            hodler6 = new MyViewHolder6(view);

            return hodler6;
        } else if (viewType == JIU_VIEW_TYPE) {//秒杀
            view = LayoutInflater.from(context).inflate(R.layout.home_jiu, parent, false);
            return new MyViewHodler4(view);
        } else if (viewType == TUIJIAN_VIEW_TYPE) {//推荐
            view = LayoutInflater.from(context).inflate(R.layout.home_tuijian, parent, false);
            return new MyViewHodler5(view);
        }
        return null;

    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof MyViewHodler1) {
            MyViewHodler1 myViewHodler1 = (MyViewHodler1) holder;
            myViewHodler1.pageBanner.setImageLoader(new GlideUtils());
            myViewHodler1.pageBanner.setImages(imageList);
            myViewHodler1.pageBanner.setBannerAnimation(Transformer.Default);
            myViewHodler1.pageBanner.isAutoPlay(true);
            myViewHodler1.pageBanner.setDelayTime(3500);
            myViewHodler1.pageBanner.setIndicatorGravity(BannerConfig.CENTER);
            myViewHodler1.pageBanner.start();
        } else if (holder instanceof MyViewHodler2) {

            MyViewHodler2 myViewHodler2 = (MyViewHodler2) holder;
            myViewHodler2.classrecyc.setLayoutManager(new GridLayoutManager(context, 2, OrientationHelper.HORIZONTAL, false));
            HomeRecycAdapter recyclegroAdapter = new HomeRecycAdapter(context, fenlei);
            myViewHodler2.classrecyc.setAdapter(recyclegroAdapter);

        } else if (holder instanceof MyViewHodler3) {
            MyViewHodler3 myViewHodler3 = (MyViewHodler3) holder;
            List<String> notices = new ArrayList<>();
            notices.add("歌手高空拍MV坠亡");
            notices.add("王思聪怼全明星投票");
            notices.add("RNG止步8强");
            notices.add("手机被收男孩跳江");
            notices.add("程序员节");
            myViewHodler3.marquee.startWithList(notices);
            myViewHodler3.marquee.startWithList(notices, R.anim.anim_bottom_in, R.anim.anim_top_out);

        } else if (holder instanceof MyViewHodler4) {
            MyViewHodler4 myViewHodler4 = (MyViewHodler4) holder;
            GridViewAdapter adapter = new GridViewAdapter(context, miaoshalist);
            myViewHodler4.gridview.setAdapter(adapter);
            myViewHodler4.gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Toast.makeText(context, miaoshalist.get(i).getBargainPrice() + "元", Toast.LENGTH_SHORT).show();
                }
            });
        } else if (holder instanceof MyViewHodler5) {
            final MyViewHodler5 myViewHodler5 = (MyViewHodler5) holder;
            myViewHodler5.tuijianRecyc.setLayoutManager(new GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false));
            myViewHodler5.tuijianRecyc.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
            myViewHodler5.tuijianRecyc.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL));
            HomeTuiJianAdapter adapter = new HomeTuiJianAdapter(context, tuijianlist);
            myViewHodler5.tuijianRecyc.setAdapter(adapter);
            adapter.setmItemClickListener(new HomeTuiJianAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("pid", tuijianlist.get(position).getPid());
                    context.startActivity(intent);
                }
            });

        }else if(holder instanceof MyViewHolder6){
             hodler6 = (MyViewHolder6) holder;

            handler.sendEmptyMessage(0);
        }
    }
    private Handler handler = new Handler () {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            countDown();
            handler.sendEmptyMessageDelayed(0, 1000);
        }

        private void countDown() {
            SimpleDateFormat df = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
            Date curDate = new Date (System.currentTimeMillis());
            String format = df.format(curDate);
            StringBuffer buffer = new StringBuffer();
            String substring = format.substring(0, 11);
            buffer.append(substring);
            Log.d("ccc", substring);
            Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            if (hour % 2 == 0) {
                hodler6.tvMiaoshaTime.setText(hour + "点场");
                buffer.append((hour + 2));
                buffer.append(":00:00");
            } else {
                hodler6.tvMiaoshaTime.setText((hour - 1) + "点场");
                buffer.append((hour + 1));
                buffer.append(":00:00");
            }
            String totime = buffer.toString();
            try {
                Date date = df.parse(totime);
                Date date1 = df.parse(format);
                long defferenttime = date.getTime() - date1.getTime();
                long days = defferenttime / (1000 * 60 * 60 * 24);
                long hours = (defferenttime - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
                long minute = (defferenttime - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
                long seconds = defferenttime % 60000;
                long second = Math.round((float) seconds / 1000);
                hodler6.tvMiaoshaShi.setText("0" + hours + "");
                if (minute >= 10) {
                    hodler6.tvMiaoshaMinter.setText(minute + "");
                } else {
                    hodler6.tvMiaoshaMinter.setText("0" + minute + "");
                }
                if (second >= 10) {
                    hodler6.tvMiaoshaSecond.setText(second + "");
                } else {
                    hodler6.tvMiaoshaSecond.setText("0" + second + "");
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    public int getItemCount() {
        return 6;
    }

    //轮播
    class MyViewHodler1 extends RecyclerView.ViewHolder {
        @BindView(R.id.page_banner)
        Banner pageBanner;

        public MyViewHodler1(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //频道
    class MyViewHodler2 extends RecyclerView.ViewHolder {
        @BindView(R.id.class_recyc)
        RecyclerView classrecyc;

        public MyViewHodler2(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    //跑马灯
    class MyViewHodler3 extends RecyclerView.ViewHolder {
        @BindView(R.id.marquee)
        MarqueeView marquee;

        public MyViewHodler3(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //秒杀
    class MyViewHodler4 extends RecyclerView.ViewHolder {
        @BindView(R.id.gridview)
        GridView gridview;

        public MyViewHodler4(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //推荐
    class MyViewHodler5 extends RecyclerView.ViewHolder {
        @BindView(R.id.tuijian_recyc)
        RecyclerView tuijianRecyc;

        public MyViewHodler5(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class MyViewHolder6 extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_miaosha_time)
        TextView tvMiaoshaTime;
        @BindView(R.id.tv_miaosha_shi)
        TextView tvMiaoshaShi;
        @BindView(R.id.tv_miaosha_minter)
        TextView tvMiaoshaMinter;
        @BindView(R.id.tv_miaosha_second)
        TextView tvMiaoshaSecond;

        public MyViewHolder6(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
