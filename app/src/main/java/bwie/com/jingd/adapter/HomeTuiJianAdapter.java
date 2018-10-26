package bwie.com.jingd.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import bwie.com.jingd.R;
import bwie.com.jingd.bean.HomeBean;

public class HomeTuiJianAdapter extends RecyclerView.Adapter<HomeTuiJianAdapter.MyViewHodler> implements View.OnClickListener {
    private OnItemClickListener mItemClickListener;
    private Context context;
    private List<HomeBean.DataBean.TuijianBean.ListBeanX> list;

    public HomeTuiJianAdapter(Context context, List<HomeBean.DataBean.TuijianBean.ListBeanX> list) {
        this.context = context;
        this.list = list;
    }

    public void setmItemClickListener(OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    @NonNull
    @Override
    public MyViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_tuijian, parent, false);

        MyViewHodler hodler = new MyViewHodler(view);
        view.setOnClickListener(this);
        return hodler;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHodler holder, int position) {
        holder.recomTv.setText(list.get(position).getTitle());
        holder.recomPrice.setText(list.get(position).getPrice());
        String[] imageUrls = list.get(position).getImages().split("\\|");
        if(imageUrls!=null&&imageUrls.length>0){
            holder.recomIv.setImageURI(Uri.parse(imageUrls[0]));
        }
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View view) {
        if (mItemClickListener!=null){
            mItemClickListener.onItemClick((Integer) view.getTag());
        }
    }

    public class MyViewHodler extends RecyclerView.ViewHolder {
        @BindView(R.id.recom_iv)
        ImageView recomIv;
        @BindView(R.id.recom_tv)
        TextView recomTv;
        @BindView(R.id.recom_price)
        TextView recomPrice;
        public MyViewHodler(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    public interface OnItemClickListener{
        void onItemClick(int position);
    }
}
