package bwie.com.jingd.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;

import bwie.com.jingd.R;
import bwie.com.jingd.activity.ListActivity;
import bwie.com.jingd.bean.ChildBean;

public class ExpandableListViewAdapter extends BaseExpandableListAdapter{
    private Context context;
    private List<ChildBean.DataBean> list;
    private GridLayoutManager gridLayoutManager;
    private RecyclerViewAdapter recyclerViewAdapter;

    public ExpandableListViewAdapter(Context context, List<ChildBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public int getGroupCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return 1;
    }

    @Override
    public Object getGroup(int i) {
        return null;
    }

    @Override
    public Object getChild(int i, int i1) {
        return null;
    }

    @Override
    public long getGroupId(int i) {
        return 0;
    }

    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        ParentViewHolder parentholder = null;
        if (view == null) {
            view = View.inflate(context, R.layout.elv_parent_item, null);
            parentholder = new ParentViewHolder();
            parentholder.elv_parent_tv = view.findViewById(R.id.elv_parent_tv);
            view.setTag(parentholder);
        }else{

            parentholder = (ParentViewHolder) view.getTag();
        }
        parentholder.elv_parent_tv.setText(list.get(i).getName());
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        ChildViewHolder childholder;
        if (view == null) {
            view = View.inflate(context, R.layout.elv_child_item, null);
            childholder = new ChildViewHolder();
            childholder.rvadapter1 = (RecyclerView) view.findViewById(R.id.elv_child_item_rv11);
            view.setTag(childholder);
        } else {
            childholder = (ChildViewHolder) view.getTag();
        }
        final List<ChildBean.DataBean.ListBean> list = this.list.get(i).getList();
        recyclerViewAdapter = new RecyclerViewAdapter(viewGroup.getContext(), list);

        gridLayoutManager = new GridLayoutManager(viewGroup.getContext(), 2);

        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        childholder.rvadapter1.setLayoutManager(gridLayoutManager);

        childholder.rvadapter1.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.setMonItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(context, ListActivity.class);
                String keywords = list.get(position).getName();

                intent.putExtra("keywords",keywords);
                context.startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }

        class ParentViewHolder {

            public TextView elv_parent_tv;

        }

        class ChildViewHolder {

            public RecyclerView rvadapter1;

        }
}
