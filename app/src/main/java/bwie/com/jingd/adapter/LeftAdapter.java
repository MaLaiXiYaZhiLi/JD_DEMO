package bwie.com.jingd.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import bwie.com.jingd.R;
import bwie.com.jingd.bean.ClassBean;

public class LeftAdapter extends RecyclerView.Adapter<LeftAdapter.MyViewHodler>{
    private Context context;
    private List<ClassBean.DataBean> data;
    public LeftAdapter(Context context, List<ClassBean.DataBean> data) {
        this.context = context;
        this.data = data;
    }


    @NonNull
    @Override
    public MyViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_left, null);
        MyViewHodler hodler = new MyViewHodler(view);
        return hodler;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHodler holder, final int position) {

        holder.lefttv.setText(data.get(position).getName());
        final ClassBean.DataBean dataBean = data.get(position);

        if(onitemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int cid = data.get(position).getCid();
                    onitemClickListener.onItemClick(cid);
                    for (ClassBean.DataBean data : data) {
                        if(data.getCid() == dataBean.getCid()){

                        }
                    }
                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHodler extends RecyclerView.ViewHolder {
        @BindView(R.id.lefttv)
        TextView lefttv;

        public MyViewHodler(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private OnitemClickListener onitemClickListener;

    public void setOnitemClickListener(OnitemClickListener onitemClickListener) {
        this.onitemClickListener = onitemClickListener;
    }

    public interface OnitemClickListener{

        void onItemClick(int cid);

    }

}
