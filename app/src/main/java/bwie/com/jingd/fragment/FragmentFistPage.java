package bwie.com.jingd.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.uuzuche.lib_zxing.activity.CaptureActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import bwie.com.jingd.R;
import bwie.com.jingd.activity.SelectActivity;
import bwie.com.jingd.adapter.HomeAdapter;
import bwie.com.jingd.bean.HomeBean;
import bwie.com.jingd.presenter.HomePresenter;
import bwie.com.jingd.view.IHomeView;

public class FragmentFistPage extends Fragment implements IHomeView{
    @BindView(R.id.sm_btn)
    Button smBtn;
    @BindView(R.id.sel_et)
    EditText selEt;
    @BindView(R.id.xRecyclerview)
    XRecyclerView xRecyclerView;
    Unbinder unbinder;
    private HomeBean.DataBean data;
    private HomeAdapter adapter;
    private HomePresenter homePresenter;
    private List<HomeBean.DataBean.BannerBean> banner;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_firstpage, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        initData();
        initLoad();
        return view;
    }

    private void initLoad() {
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //刷新
                adapter.notifyDataSetChanged();
                xRecyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                //加载更多
                xRecyclerView.loadMoreComplete();
            }
        });
    }

    private void initView() {
        homePresenter = new HomePresenter(this);
        homePresenter.cartBean();
        xRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    private void initData() {
        smBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), CaptureActivity.class));
            }
        });

        selEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SelectActivity.class));
           }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void success(HomeBean homeBean) {
        if(homeBean!=null&&homeBean.getData()!=null){
            data = homeBean.getData();
            banner = data.getBanner();
            List<HomeBean.DataBean.FenleiBean> fenlei = data.getFenlei();
            List<HomeBean.DataBean.MiaoshaBean.ListBean> miaosha = data.getMiaosha().getList();
            List<HomeBean.DataBean.TuijianBean.ListBeanX> tuijian = data.getTuijian().getList();

            adapter = new HomeAdapter(getActivity(), data, banner,fenlei,miaosha,tuijian);
            xRecyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void failure(String msg) {

    }
}
