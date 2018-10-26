package bwie.com.jingd.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import bwie.com.jingd.R;
import bwie.com.jingd.bean.ChildBean;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> implements View.OnClickListener{
    private Context context;
    private List<ChildBean.DataBean.ListBean> list;
    private OnItemClickListener monItemClickListener;
    public RecyclerViewAdapter(Context context, List<ChildBean.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    public void setMonItemClickListener(OnItemClickListener monItemClickListener) {
        this.monItemClickListener = monItemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.rightitem, null);
        MyViewHolder holder = new MyViewHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.rightsimple.setImageURI(list.get(position).getIcon());
        holder.righttext.setText(list.get(position).getName());
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View view) {
        if(monItemClickListener != null){
            monItemClickListener.onItemClick((Integer) view.getTag());
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.rightsimple)
        SimpleDraweeView rightsimple;
        @BindView(R.id.righttext)
        TextView righttext;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    public interface OnItemClickListener{
        void onItemClick(int position);
    }
}
