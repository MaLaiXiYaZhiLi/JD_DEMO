package bwie.com.jingd.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import bwie.com.jingd.R;
import bwie.com.jingd.bean.HomeBean;

public class HomeRecycAdapter extends RecyclerView.Adapter<HomeRecycAdapter.MyViewHodler> {
    private Context context;
    private List<HomeBean.DataBean.FenleiBean> list;
    private static final String TAG = "RecyclegroAdapter---";

    public HomeRecycAdapter(Context context, List<HomeBean.DataBean.FenleiBean> list) {
        this.context = context;
        this.list = list;

    }

    @Override
    public MyViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = View.inflate(context, R.layout.item_homerecyc, null);

        MyViewHodler hodler = new MyViewHodler(view);

        return hodler;
    }

    @Override
    public void onBindViewHolder(MyViewHodler holder, int position) {

        holder.classItemDraw.setImageURI(list.get(position).getIcon());

        holder.classItemTv.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHodler extends RecyclerView.ViewHolder {
        @BindView(R.id.class_item_draw)
        SimpleDraweeView classItemDraw;
        @BindView(R.id.class_item_tv)
        TextView classItemTv;
        public MyViewHodler(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
