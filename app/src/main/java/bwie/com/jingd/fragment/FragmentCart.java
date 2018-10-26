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
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import bwie.com.jingd.R;
import bwie.com.jingd.adapter.CartAdapter;
import bwie.com.jingd.bean.CartBean;
import bwie.com.jingd.bean.MessageBean;
import bwie.com.jingd.inter.CartAllCheckLinstener;
import bwie.com.jingd.presenter.CartPresenter;
import bwie.com.jingd.view.ICartView;

public class FragmentCart extends Fragment implements ICartView, CartAllCheckLinstener {
    @BindView(R.id.cartxrecyc)
    XRecyclerView cartxrecyc;
    @BindView(R.id.allCheckBox)
    CheckBox allCheckBox;
    @BindView(R.id.sumprice)
    TextView sumprice;
    @BindView(R.id.buy)
    TextView buy;
    Unbinder unbinder;
    private CartPresenter cartPresenter;
    private CartAdapter cartAdapter;
    private List<CartBean.DataBean> list;
    private String uid = "71";

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EventBus.getDefault().register(this);
//    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        unbinder = ButterKnife.bind(this, view);

        initData();
        initView();
        initLoad();
        return view;
    }

    private void initLoad() {
        cartxrecyc.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //刷新
                cartAdapter.notifyDataSetChanged();
                cartxrecyc.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                //加载更多
                cartxrecyc.loadMoreComplete();
            }
        });
    }

    private void initView() {
        list = new ArrayList<>();
        cartxrecyc.setLayoutManager(new LinearLayoutManager(getActivity()));
        allCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (allCheckBox.isChecked()) {
                    if (list != null && list.size() > 0) {
                        for (int i = 0; i < list.size(); i++) {
                            list.get(i).setSelected(true);
                            for (int i1 = 0; i1 < list.get(i).getList().size(); i1++) {
                                list.get(i).getList().get(i1).setSelected(true);
                            }
                        }
                    }
                } else {
                    if (list != null && list.size() > 0) {
                        for (int i = 0; i < list.size(); i++) {
                            list.get(i).setSelected(false);
                            for (int i1 = 0; i1 < list.get(i).getList().size(); i1++) {
                                list.get(i).getList().get(i1).setSelected(false);
                            }
                        }
                    }
                }
                cartAdapter.notifyDataSetChanged();
                totalPrice();
            }
        });
    }

    private void initData() {

        cartPresenter = new CartPresenter(this);
        cartPresenter.getCart(uid);


        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"生成订单",Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void Success(CartBean cartBean) {
        if (cartBean != null && cartBean.getData() != null) {
            list = cartBean.getData();

            cartAdapter = new CartAdapter(getActivity(), list);
            cartxrecyc.setAdapter(cartAdapter);
        }
        cartAdapter.setCartAllCheckLinstener(this);
    }

    @Override
    public void Failure(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void notifyAllCheckboxStatus() {
        StringBuilder stringBuilder = new StringBuilder();
        if (cartAdapter != null) {
            for (int i = 0; i < cartAdapter.getCartList().size(); i++) {
                stringBuilder.append(cartAdapter.getCartList().get(i).isSelected());
                for (int i1 = 0; i1 < cartAdapter.getCartList().get(i).getList().size(); i1++) {
                    stringBuilder.append(cartAdapter.getCartList().get(i).getList().get(i1).isSelected());
                }
            }
        }
        if (stringBuilder.toString().contains("false")) {
            allCheckBox.setChecked(false);
        } else {
            allCheckBox.setChecked(true);
        }
        totalPrice();
    }

    private void totalPrice() {
        double totalprice = 0;

        for (int i = 0; i < cartAdapter.getCartList().size(); i++) {
            for (int i1 = 0; i1 < cartAdapter.getCartList().get(i).getList().size(); i1++) {
                if (cartAdapter.getCartList().get(i).getList().get(i1).isSelected()) {
                    CartBean.DataBean.ListBean listBean = cartAdapter.getCartList().get(i).getList().get(i1);
                    totalprice += listBean.getBargainPrice() * listBean.getTotalNum();
                }
            }
        }
        sumprice.setText("" + totalprice);
    }
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void holdMessage(MessageBean messageBean){
//        String detail = messageBean.getDetail();
//        if ("DetailActivity".equals(detail)){
//
//        }
//    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
       // EventBus.getDefault().unregister(this);
    }

}
