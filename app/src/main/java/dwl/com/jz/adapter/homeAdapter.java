package dwl.com.jz.adapter;

import android.annotation.SuppressLint;
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

public class homeAdapter extends RecyclerView.Adapter<homeAdapter.VH> {
    public static class VH extends RecyclerView.ViewHolder{
        public final TextView title;
        public final TextView price;
        public final RoundedImageView img;
        public VH(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.tv_title);
            price = (TextView) v.findViewById(R.id.tv_price);
            img = (RoundedImageView)v.findViewById(R.id.iv_img);
        }
    }

    private List<Home> mDatas;
    private BaseActivity mContext;
    public homeAdapter(List<Home> data, BaseActivity mBaseActivity) {
        this.mDatas = data;
        mContext = mBaseActivity;
    }

    //③ 在Adapter中实现3个方法
    @Override
    public void onBindViewHolder(VH holder, final int position) {
        holder.title.setText(mDatas.get(position).getTitle());
        holder.price.setText(mDatas.get(position).getPrice()+"元起");
        holder.img.setImageResource(mDatas.get(position).getImg());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //item 点击事件
                Bundle bundle = new Bundle();
                bundle.putInt("img",mDatas.get(position).getImg());
                bundle.putString("title",mDatas.get(position).getTitle());
                bundle.putString("price",mDatas.get(position).getPrice());
                mContext.toActivity(OrderActivity.class,bundle);
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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home, parent, false);
        return new VH(v);
    }
}

