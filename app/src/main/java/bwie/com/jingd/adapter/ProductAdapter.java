package bwie.com.jingd.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import bwie.com.jingd.R;
import bwie.com.jingd.bean.CartBean;
import bwie.com.jingd.inter.CartAllCheckLinstener;
import bwie.com.jingd.inter.CheckListener;
import bwie.com.jingd.myview.My_add_reduce;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.CartViewHolder>{
    private Context mcontext;
    private List<CartBean.DataBean.ListBean> listBeanList;
    private CheckListener checkListener;
    private CartAllCheckLinstener cartAllCheckLinstener;
    public ProductAdapter(Context context, List<CartBean.DataBean.ListBean> list) {
        mcontext = context;
        listBeanList = list;

    }

    public void setCheckListener(CheckListener checkListener) {
        this.checkListener = checkListener;
    }

    public void setCartAllCheckLinstener(CartAllCheckLinstener cartAllCheckLinstener) {
        this.cartAllCheckLinstener = cartAllCheckLinstener;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mcontext).inflate(R.layout.item_product_layout, parent, false);
        CartViewHolder viewHolder = new CartViewHolder(itemView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final CartViewHolder holder, final int position) {
        final CartBean.DataBean.ListBean bean = listBeanList.get(position);
        holder.priceTv.setText("优惠价:¥"+bean.getBargainPrice());
        Log.i("ssss",bean.getTitle());
        holder.titleTv.setText(bean.getTitle());
        String[] imgs = bean.getImages().split("\\|");
        if(imgs!=null&&imgs.length>0){
            Glide.with(mcontext).load(imgs[0]).into(holder.productIv);
        }else{
            holder.productIv.setImageResource(R.mipmap.ic_launcher);
        }
        holder.checkBox.setChecked(bean.isSelected());

        holder.myAddReduce.setNumEt(bean.getTotalNum());
        holder.delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listBeanList.remove(position);
                notifyDataSetChanged();
            }
        });
        holder.myAddReduce.setJiaJianLinstener(new My_add_reduce.JiaJianLinstener() {
            @Override
            public void getNum(int num) {
                bean.setTotalNum(num);
                if(checkListener!=null){
                    checkListener.notifyParpen();
                }
            }
        });

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.checkBox.isChecked()){
                    bean.setSelected(true);
                }else{
                    bean.setSelected(false);
                }
                if (checkListener!=null){
                    checkListener.notifyParpen();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listBeanList.size()==0 ? 0 : listBeanList.size();
    }


    public class CartViewHolder extends RecyclerView.ViewHolder {
        private CheckBox checkBox;
        private TextView titleTv,priceTv;
        private ImageView productIv;
        private My_add_reduce myAddReduce;
        private Button delBtn;

        public CartViewHolder(View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.productCheckBox);
            titleTv = itemView.findViewById(R.id.title);
            priceTv = itemView.findViewById(R.id.price);
            productIv = itemView.findViewById(R.id.product_icon);
            myAddReduce = itemView.findViewById(R.id.jiajianqi);
            delBtn = itemView.findViewById(R.id.btnDelete);
        }
    }
}
