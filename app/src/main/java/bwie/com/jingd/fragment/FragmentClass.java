package bwie.com.jingd.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import bwie.com.jingd.R;
import bwie.com.jingd.adapter.LeftAdapter;
import bwie.com.jingd.adapter.ExpandableListViewAdapter;
import bwie.com.jingd.bean.ChildBean;
import bwie.com.jingd.bean.ClassBean;
import bwie.com.jingd.presenter.ClassPresenter;
import bwie.com.jingd.view.IClassView;

public class FragmentClass extends Fragment implements IClassView {
    @BindView(R.id.leftrecyclervierview)
    RecyclerView leftrecyclervierview;
    @BindView(R.id.rightlistview)
    ExpandableListView rightlistview;
    Unbinder unbinder;

    private List<String> list = new ArrayList<>();
    private int cid = 1;
    private List<ClassBean.DataBean> data;
    private ClassPresenter classPresenter;
    private ExpandableListViewAdapter expandableListAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_class, container, false);

        unbinder = ButterKnife.bind(this, view);
        initData();

        classPresenter = new ClassPresenter(this);
        classPresenter.classBean();
        classPresenter.child(cid);
        return view;
    }

    private void initData() {


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onLeftResponse(ClassBean classBean) {
        data = classBean.getData();

        LeftAdapter adapter = new LeftAdapter(getActivity(), data);
        leftrecyclervierview.setLayoutManager(new LinearLayoutManager(getActivity()));
        leftrecyclervierview.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        leftrecyclervierview.setAdapter(adapter);

        adapter.setOnitemClickListener(new LeftAdapter.OnitemClickListener() {
            @Override
            public void onItemClick(int cid) {
                classPresenter.child(cid);
                //Toast.makeText(getActivity(), "点击" + cid, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onLeftFailure(String e) {

    }

    @Override
    public void onRightResponse(ChildBean childBean) {
        List<ChildBean.DataBean> list = childBean.getData();
        expandableListAdapter = new ExpandableListViewAdapter(getActivity(),list);
        rightlistview.setAdapter(expandableListAdapter);
        for (int i = 0; i < list.size(); i++) {
            rightlistview.expandGroup(i);
        }
    }

    @Override
    public void onRightFailure(String e) {

    }
}
