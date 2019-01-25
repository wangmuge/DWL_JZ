package dwl.com.jz.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dwl.com.jz.R;
import dwl.com.jz.adapter.homeAdapter;
import dwl.com.jz.bean.Home;

public class SearchActivity extends BaseActivity {
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.btn_back)
    ImageView btnBack;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.et_sreach)
    EditText etSreach;
    private homeAdapter homeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        tvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initData(etSreach.getText().toString().trim());
            }
        });
    }

    private void initData(String name) {
        List<Home> homeList = new ArrayList<>();
        String title[] = {"除螨", "地板打蜡", "门板打蜡", "沙发保养", "空调保养", "油烟机保养", "热水器保养", "椅子保养",
                "日式搬家", "电器搬迁", "家庭搬家", "企业搬迁", "店铺搬迁", "搬家车租用", "司机雇佣", "家具搬迁",
                "保姆", "优质保姆", "金牌保姆", "育儿嫂", "月嫂", "优质月嫂", "金牌月嫂", "催乳服务",
                "日常保洁", "深度清洁", "厕所保洁", "新居开荒", "长期保洁", "企业保洁", "厨房保养", "卫生间保养",
                "一般开锁", "大门开锁", "保险柜开锁", "一般换锁", "大门换锁", "保险柜换锁", "紧急开锁", "紧急换锁",
                "管道疏通", "马桶维修", "水龙头更换", "插座更换", "灯具安装", "换吊顶", "换地砖", "换马桶",
                "管道疏通", "马桶维修", "水龙头更换", "插座更换", "灯具安装", "换吊顶", "换地砖", "换马桶",
                "汽车除甲醛", "房间除甲醛", "家庭除甲醛", "企业除甲醛", "汽车甲醛检测", "家庭甲醛检测", "企业甲醛检测", "甲醛仪器租用",
                "空调维修", "油烟机维修", "插座维修", "电脑维修", "手机维修", "电视维修", "灯具维修", "洗衣机维修"

        };
        String price[] = {"300", "350", "350", "300", "100", "100", "100", "100",
                "300", "100", "400", "600", "300", "200", "150", "100",
                "4000", "5000", "6000", "5000", "5000", "6000", "8000", "500",
                "100", "200", "500", "600", "3000", "200", "100", "100",
                "100", "100", "50", "50", "100", "150", "100", "200",
                "100", "100", "50", "50", "100", "150", "100", "200",
                "100", "100", "50", "50", "100", "150", "100", "200",
                "200", "300", "500", "800", "100", "100", "100", "100",
                "100", "100", "50", "100", "100", "100", "80", "100"

        };
        int img[] = {R.mipmap.home_2, R.mipmap.home_4, R.mipmap.home_11,
                R.mipmap.home_1, R.mipmap.home_16, R.mipmap.home_17,
                R.mipmap.home_18, R.mipmap.home_15, R.mipmap.home_19, R.mipmap.home_10};
        int head[] = {R.drawable.bangongyi, R.drawable.kuaidiyunshu, R.drawable.pinzhi,
                R.drawable.qingjiebaojie, R.drawable.suoding, R.drawable.wuyebaoxiu,
                R.drawable.xiaotuiche, R.drawable.xinhaokuosan, R.drawable.diannao};

        for (int i = 0; i < title.length; i++) {
            Home home = new Home();
            if (title[i].contains(name)) {
                home.setTitle(title[i]);
                home.setPrice(price[i]);
//            home.setHead(head[i]);
                home.setImg(img[9]);
                homeList.add(home);
            }
        }
        GridLayoutManager layoutmanager = new GridLayoutManager(this, 2);
        rv.setLayoutManager(layoutmanager);
        rv.setHasFixedSize(true);
        rv.setNestedScrollingEnabled(false);
        homeAdapter = new homeAdapter(homeList, this);
        rv.setAdapter(homeAdapter);
    }

    @OnClick(R.id.btn_back)
    public void onViewClicked() {
        finish();
    }
}
