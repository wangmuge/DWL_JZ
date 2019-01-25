package dwl.com.jz.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dwl.com.jz.R;
import dwl.com.jz.activity.SearchActivity;
import dwl.com.jz.activity.ServiceListActivity;
import dwl.com.jz.adapter.homeAdapter;
import dwl.com.jz.bean.Home;


public class homeFragment extends BaseFragment {

    @BindView(R.id.tv_sreach)
    TextView tvSreach;
    @BindView(R.id.gridview)
    GridView gridview;
    Unbinder unbinder;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.rv)
    RecyclerView rv;
    private MyImageLoader mMyImageLoader;
    private ArrayList<Integer> imagePath;
    private ArrayList<String> imageTitle;
    private List<Map<String, Object>> dataList;
    private SimpleAdapter adapter;
    private homeAdapter homeAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        imagePath = new ArrayList<>();
        imageTitle = new ArrayList<>();
        imagePath.add(R.mipmap.banner_1);
        imagePath.add(R.mipmap.banner_2);
        imagePath.add(R.mipmap.banner_3);
        imagePath.add(R.mipmap.banner_4);
        imagePath.add(R.mipmap.banner_5);
        imagePath.add(R.mipmap.banner_6);
        imageTitle.add("呵护母婴，体贴放心");
        imageTitle.add("厨房保养，感受敞亮空间");
        imageTitle.add("体验深度保洁，享受新家感觉");
        imageTitle.add("卫生间保养，焕发闪亮光洁");
        imageTitle.add("一站式搬家，不动手搬家");
        imageTitle.add("用我们的专业，解决您的烦恼");

        mMyImageLoader = new MyImageLoader();
        //设置样式，里面有很多种样式可以自己都看看效果
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器
        banner.setImageLoader(mMyImageLoader);
        //设置轮播的动画效果,里面有很多种特效,可以都看看效果。
        banner.setBannerAnimation(Transformer.ZoomOutSlide);
        //轮播图片的文字
        banner.setBannerTitles(imageTitle);
        //设置轮播间隔时间
        banner.setDelayTime(3000);
        //设置是否为自动轮播，默认是true
        banner.isAutoPlay(true);
        //设置指示器的位置，小点点，居中显示
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //设置图片加载地址
        banner.setImages(imagePath)
                //轮播图的监听
                .start();
        int icno[] = {R.drawable.bangongyi, R.drawable.kuaidiyunshu, R.drawable.pinzhi,
                R.drawable.qingjiebaojie, R.drawable.suoding, R.drawable.wuyebaoxiu, R.drawable.xiaotuiche,
                R.drawable.xinhaokuosan, R.drawable.diannao, R.drawable.quanbu};
        String name[] = {"家具保养", "搬家", "品质保姆", "保洁", "开锁换锁", "房屋维修", "房屋改造", "除甲醛", "家电维修", "全部"};
        dataList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < icno.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("img", icno[i]);
            map.put("text", name[i]);
            dataList.add(map);
            String[] from = {"img", "text"};
            int[] to = {R.id.img, R.id.text};
            adapter = new SimpleAdapter(getBaseActivity(), dataList, R.layout.gride_item, from, to);
            gridview.setAdapter(adapter);
            gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                        long arg3) {
                    Bundle mBundle = new Bundle();
                    mBundle.putString("type", arg2 + "");
                    getBaseActivity().toActivity(ServiceListActivity.class, mBundle);
                }
            });
            initData();
        }


    }

    private void initData() {
//        JSONArray array1 = new JSONArray();
//        JSONObject object2 = new JSONObject();
//        JSONObject object3 = new JSONObject();
//        JSONObject object4 = new JSONObject();
//        JSONObject object5 = new JSONObject();
//        JSONObject object6 = new JSONObject();
//        JSONObject object7 = new JSONObject();
//        try {
//            object2.put("img", R.drawable.bangongyi);
//            object2.put("title", "12");
//            object2.put("price", "12");
//            object2.put("head", "12");
//            object3.put("img", R.drawable.bangongyi);
//            object3.put("title", "12");
//            object3.put("price", "12");
//            object3.put("head",  R.drawable.bangongyi);
//            array1.put(object2);
//            array1.put(object3);
//            System.out.println(array1.toString());
//        } catch (Exception e) {
//
//
//        }
        String title[] = {"擦玻璃", "油烟机清洗", "日常保洁", "新居开荒", "空调清洗", "金牌保姆", "热水器维修", "空调滤芯更换"};
        String price[] = {"150", "170", "50", "500", "150", "4000", "30", "30"};
        int img[] = {R.mipmap.home_2, R.mipmap.home_12, R.mipmap.home_1,
                R.mipmap.home_4, R.mipmap.home_6, R.mipmap.home_10, R.mipmap.home_13,
                R.mipmap.home_14};
        int head[] = {R.drawable.bangongyi, R.drawable.kuaidiyunshu, R.drawable.pinzhi,
                R.drawable.qingjiebaojie, R.drawable.suoding, R.drawable.wuyebaoxiu, R.drawable.xiaotuiche,
                R.drawable.xinhaokuosan, R.drawable.diannao, R.drawable.quanbu};
        List<Home> homeList = new ArrayList<>();
        for (int i = 0; i < title.length; i++) {
            Home home = new Home();
            home.setTitle(title[i]);
            home.setPrice(price[i]);
            home.setHead(head[i]);
            home.setImg(img[i]);
            homeList.add(home);
        }
        GridLayoutManager layoutmanager = new GridLayoutManager(getBaseActivity(), 2);
        rv.setLayoutManager(layoutmanager);
        rv.setHasFixedSize(true);
        rv.setNestedScrollingEnabled(false);
        homeAdapter = new homeAdapter(homeList, getBaseActivity());
        rv.setAdapter(homeAdapter);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.tv_sreach)
    public void onViewClicked() {
        getBaseActivity().toActivity(SearchActivity.class,null);
    }


    private class MyImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(getBaseActivity())
                    .load(path)
                    .into(imageView);
        }
    }
}
