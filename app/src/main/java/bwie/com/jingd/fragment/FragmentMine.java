package bwie.com.jingd.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import bwie.com.jingd.R;
import bwie.com.jingd.activity.LoginActivity;
import bwie.com.jingd.activity.PersonActivity;
import bwie.com.jingd.activity.SettingActivity;
import bwie.com.jingd.adapter.HomeTuiJianAdapter;
import bwie.com.jingd.bean.DengluBean;
import bwie.com.jingd.bean.HomeBean;
import bwie.com.jingd.presenter.HomePresenter;
import bwie.com.jingd.view.IHomeView;

import static android.app.Activity.RESULT_OK;
public class FragmentMine extends Fragment implements IHomeView{

    @BindView(R.id.user_icon)
    ImageView userIcon;
    @BindView(R.id.user_name)
    TextView denglu;
    @BindView(R.id.mine_recyclerView)
    XRecyclerView mineRecyclerView;
    Unbinder unbinder;

    private Handler handler = new Handler();
    private SharedPreferences config;
    private SharedPreferences.Editor edit;
    private String uid;
    protected static final int CHOOSE_PICTURE = 0;
    protected static final int TAKE_PICTURE = 1;
    private static final int CROP_SMALL_PICTURE = 2;
    private List<HomeBean.DataBean.TuijianBean.ListBeanX> list;
    private HomeTuiJianAdapter jianAdapter;
    private HomePresenter presenter;
    protected static Uri tempUri;
    private String imagePath;
    private static String path = "/sdcard/myHead/";// sd路径
    private Bitmap head;// 头像Bitmap
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);


        unbinder = ButterKnife.bind(this, view);
        initView();

        Bitmap bt = BitmapFactory.decodeFile(path + "head.jpg");// 从SD卡中找头像，转换成Bitmap
        if (bt != null) {
            @SuppressWarnings("deprecation")
            Drawable drawable = new BitmapDrawable(bt);// 转换成drawable
            userIcon.setImageDrawable(drawable);
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //获取登录信息
        //获取保存的用户id
        config = getActivity().getSharedPreferences("config", 0);
        edit = config.edit();

        //进入页面判断是否上次是否登录
        uid = config.getString("uid", null);
        if(uid == null){
            userIcon.setImageResource(R.drawable.yhtx);

        }else{
            //头像下面的文字改变
            denglu.setText("马来西亚之力");
        }





        //XRecyclerview的上拉下拉方法
        mineRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //在子线程内完成下拉加载数据
                        presenter.cartBean();

                        jianAdapter.notifyDataSetChanged();
                        mineRecyclerView.refreshComplete();
                    }
                }, 888);
            }

            @Override
            public void onLoadMore() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //在子线程内完成下拉加载数据
                        presenter.cartBean();
                        jianAdapter.notifyDataSetChanged();
                        mineRecyclerView.loadMoreComplete();
                    }
                }, 888);
            }
        });

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onM(DengluBean dengluBean) {
        //走到这里 说明成功登录了,改变头像的设置,改变点击头像和点击设置跳转的页面
        if (dengluBean != null) {
            //userIcon.setImageResource(R.mipmap.ath);
            denglu.setText(dengluBean.getData().getUsername());      //登录文字被用户名替换

        }
    }

    private void initView() {
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2);
        mineRecyclerView.setLayoutManager(manager);

        presenter = new HomePresenter(this);
        presenter.cartBean();//获取数据




        if(denglu.getText().equals("马来西亚之力")){

        }
        denglu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (denglu.getText().equals("马来西亚之力")) {
                    startActivity(new Intent(getActivity(), SettingActivity.class));
                } else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
            }
        });
        EventBus.getDefault().register(this);
    }




    @Override
    public void success(HomeBean homeBean) {
        if (homeBean != null && homeBean.getData() != null) {
            list = homeBean.getData().getTuijian().getList();
            jianAdapter = new HomeTuiJianAdapter(getActivity(), list);
            mineRecyclerView.setAdapter(jianAdapter);
        }
    }

    @Override
    public void failure(String msg) {

    }

    @OnClick(R.id.mine_setting)
    public void onViewClicked() {
        //1.如果已经登录,就跳到设置页面,2.如果没登录,就跳到登录页面
        String uid = config.getString("uid", null);

        if (uid == null) {   //没登录

            //跳到登录页面
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        } else {             //已经登录

            denglu.setText("马来西亚之力");                         //头像下面的文字改变

            //跳到设置页面
            Intent intent = new Intent(getActivity(), SettingActivity.class);
            intent.putExtra("mobile", denglu.getText().toString());
            startActivity(intent);
        }
    }

    @OnClick({R.id.user_icon, R.id.user_name})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.user_icon:
                if(uid != null) {
                    //点击选择头像
                    showTypeDialog();
                }else {
                    userIcon.setImageResource(R.drawable.yhtx);
                }
                break;

            //登录按钮
            case R.id.user_name:
                //1.如果已经登录,就跳到 个人资料页面.2.如果没登录,就跳到登录页面
                String uid1 = config.getString("uid", null);

                //没登录
                if (uid1 == null) {
                    Intent intent1 = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent1);
                }else {
                    //头像下面的文字改变
                    denglu.setText("马来西亚之力");
                    //注册文字隐藏

                    //跳转到个人资料页面
                    Intent intent = new Intent(getActivity(), PersonActivity.class);
                    intent.putExtra("username", denglu.getText().toString());
                    startActivity(intent);
                    break;
                }
                break;

            default:
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        String uid = config.getString("uid", null);
        if(uid == null){
            //没登录
            userIcon.setImageResource(R.drawable.yhtx);
            denglu.setText("未登录");
        }else{
            //已登录
            denglu.setText("马来西亚之力");               //头像下面的文字改变
        }
    }

    private void showTypeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity ());
        final AlertDialog dialog = builder.create();
        View view = View.inflate(getActivity (), R.layout.add_image, null);
        TextView tv_select_gallery = view.findViewById(R.id.tv_select_gallery);
        TextView tv_select_camera = view.findViewById(R.id.tv_select_camera);
        TextView tv_select_remove = view.findViewById(R.id.tv_select_remove);
        tv_select_gallery.setOnClickListener(new View.OnClickListener () {// 在相册中选取
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Intent.ACTION_PICK, null);
                intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent1, 1);
                dialog.dismiss();
            }
        });
        tv_select_camera.setOnClickListener(new View.OnClickListener () {// 调用照相机
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent2.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(new File (Environment.getExternalStorageDirectory(), "head.jpg")));
                startActivityForResult(intent2, 2);// 采用ForResult打开
                dialog.dismiss();
            }
        });
        tv_select_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.setView(view);
        dialog.show();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    cropPhoto(data.getData());// 裁剪图片
                }

                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    File temp = new File(Environment.getExternalStorageDirectory() + "/head.jpg");
                    cropPhoto(Uri.fromFile(temp));// 裁剪图片
                }

                break;
            case 3:
                if (data != null) {
                    Bundle extras = data.getExtras();
                    head = extras.getParcelable("data");
                    if (head != null) {
                        /**
                         * 上传服务器代码
                         */
                        setPicToView(head);// 保存在SD卡中
                        userIcon.setImageBitmap(head);// 用ImageView显示出来
                    }
                }
                break;
            default:
                break;

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 调用系统的裁剪功能
     *
     * @param uri
     */
    public void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 3);
    }

    private void setPicToView(Bitmap mBitmap) {
        String sdStatus = Environment.getExternalStorageState ();
        if (!sdStatus.equals (Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
            return;
        }
        FileOutputStream b = null;
        File file = new File (path);
        file.mkdirs ();// 创建文件夹
        String fileName = path + "head.jpg";// 图片名字
        try {
            b = new FileOutputStream (fileName);
            mBitmap.compress (Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件
        } catch (FileNotFoundException e) {
            e.printStackTrace ();
        } finally {
            try {
                // 关闭流
                b.flush ();
                b.close ();
            } catch (IOException e) {
                e.printStackTrace ();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
