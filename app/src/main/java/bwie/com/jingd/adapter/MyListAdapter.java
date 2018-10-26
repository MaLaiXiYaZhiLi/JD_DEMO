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
import bwie.com.jingd.bean.ListBean;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.MyViewHolder> implements View.OnClickListener{
    private Context context;
    private List<ListBean.DataBean> list;
    private OnItemClickListener onItemClickListener;
    public MyListAdapter(Context context, List<ListBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.search_item, null);
        MyViewHolder holder = new MyViewHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String[] imageUrls = list.get(position).getImages().split("\\|");
        if(imageUrls!=null&&imageUrls.length>0){
            holder.searchImage.setImageURI(imageUrls[0]);
        }

        holder.searchText.setText(list.get(position).getTitle());
        holder.itemView.setTag(position);
        holder.searchPrice.setText(list.get(position).getPrice());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View view) {
        if (onItemClickListener!=null){
            onItemClickListener.onItemClick((Integer) view.getTag());
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.search_image)
        SimpleDraweeView searchImage;
        @BindView(R.id.search_text)
        TextView searchText;
        @BindView(R.id.search_price)
        TextView searchPrice;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    public interface OnItemClickListener{
        void onItemClick(int position);
    }

}
