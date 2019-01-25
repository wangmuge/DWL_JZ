package dwl.com.jz.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSONObject;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dwl.com.jz.R;
import dwl.com.jz.adapter.orderAdapter;
import dwl.com.jz.bean.Order;

public class OrderListActivity extends BaseActivity {

    @BindView(R.id.btn_back)
    ImageView btnBack;
    @BindView(R.id.rv)
    RecyclerView rv;
    private orderAdapter orderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        if (getOrder("1") != null && !getOrder("1").equals("")) {
            List<Order> list = JSONObject.parseArray(getOrder("1"), Order.class);
//            System.out.println("jiage" + list.get(1).getPrice().toString());
            Collections.reverse(list);
            LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
            rv.setLayoutManager(layoutmanager);
            rv.setHasFixedSize(true);
            rv.setNestedScrollingEnabled(false);
            orderAdapter = new orderAdapter(list,this);
            rv.setAdapter(orderAdapter);

        }else{
            showToast("请先下单！");
        }
    }

    @OnClick(R.id.btn_back)
    public void onViewClicked() {
        finish();
    }
}
