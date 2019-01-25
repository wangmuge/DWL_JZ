package dwl.com.jz.adapter;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

import dwl.com.jz.R;
import dwl.com.jz.activity.BaseActivity;
import dwl.com.jz.activity.OrderActivity;
import dwl.com.jz.bean.Home;
import dwl.com.jz.bean.Order;

public class orderAdapter extends RecyclerView.Adapter<orderAdapter.VH> {
    public static class VH extends RecyclerView.ViewHolder{
        public final TextView title;
        public final TextView price;
        public final RoundedImageView img;
        public final TextView total;
        public final TextView address;
        public final TextView time;
        public final TextView time2;
        public final TextView name;
        public final TextView mobile;
        public VH(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.tv_title);
            price = (TextView) v.findViewById(R.id.tv_price);
            img = (RoundedImageView)v.findViewById(R.id.iv_img);
            total = (TextView) v.findViewById(R.id.tv_total);
            address = (TextView) v.findViewById(R.id.tv_address);
            time = (TextView) v.findViewById(R.id.tv_time);
            time2 = (TextView) v.findViewById(R.id.tv_time2);
            name = (TextView) v.findViewById(R.id.tv_name);
            mobile = (TextView) v.findViewById(R.id.tv_mobile);
        }
    }

    private List<Order> mDatas;
    private BaseActivity mContext;
    public orderAdapter(List<Order> data, BaseActivity mBaseActivity) {
        this.mDatas = data;
        mContext = mBaseActivity;
    }

    //③ 在Adapter中实现3个方法
    @Override
    public void onBindViewHolder(VH holder, final int position) {
        holder.title.setText("名称："+mDatas.get(position).getTitle());
        holder.price.setText("单价："+mDatas.get(position).getPrice()+"元");
        holder.total.setText("总价："+mDatas.get(position).getTotal()+"元");
        holder.time.setText("服务数量："+mDatas.get(position).getTime());
        holder.time2.setText("预约时间："+mDatas.get(position).getTime2());
        holder.name.setText("姓名："+mDatas.get(position).getName());
        holder.mobile.setText("手机号："+mDatas.get(position).getMobile());
        holder.address.setText("地址："+mDatas.get(position).getAddress());
        holder.img.setImageResource(mDatas.get(position).getImg());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                //item 点击事件
//                Bundle bundle = new Bundle();
//                bundle.putInt("img",mDatas.get(position).getImg());
//                bundle.putString("title",mDatas.get(position).getTitle());
//                bundle.putString("price",mDatas.get(position).getPrice());
//                mContext.toActivity(OrderActivity.class,bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        //LayoutInflater.from指定写法
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        return new VH(v);
    }
}

