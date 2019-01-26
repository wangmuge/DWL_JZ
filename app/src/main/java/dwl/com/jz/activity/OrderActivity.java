package dwl.com.jz.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;

import org.angmarch.views.NiceSpinner;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import dwl.com.jz.R;

public class OrderActivity extends BaseActivity {

    @BindView(R.id.btn_back)
    ImageView btnBack;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.nice_spinner)
    NiceSpinner niceSpinner;
    @BindView(R.id.edt_order_note_text)
    EditText et_address;
    @BindView(R.id.tv_total)
    TextView tvTotal;
    @BindView(R.id.ll_bottom)
    RelativeLayout llBottom;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_time2)
    TextView tvTime2;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_mobile)
    EditText etMobile;
    private String title;
    private String price;
    private int img;
    private int total;
    private String time = "随时";
    List<String> dataset = new LinkedList<>(Arrays.asList("一小时", "两小时", "三小时", "四小时", "五小时", "六小时"));
    List<String> dataset2 = new LinkedList<>(Arrays.asList("一个月", "两个月", "三个月", "四个月", "五个月", "六个月"));
    List<String> dataset3 = new LinkedList<>(Arrays.asList("一台", "两台", "三台", "四台", "五台", "六台"));
    List<String> dataset4 = new LinkedList<>(Arrays.asList("一辆", "两辆", "三辆", "四辆", "五辆", "六辆"));
    List<String> dataset5 = new LinkedList<>(Arrays.asList("一个", "两个", "三个", "四个", "五个", "六个"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ButterKnife.bind(this);
        title = getBundle().getString("title");
        price = getBundle().getString("price");
        img = getBundle().getInt("img");
        total = Integer.parseInt(price);
        init();
    }

    private void init() {

        tvTotal.setText("总价：" + price + "元");
        tvTitle.setText(title);
        tvPrice.setText("￥" + price);
        if (Integer.parseInt(price) >= 1000) {
            niceSpinner.attachDataSource(dataset2);
        } else {
            if(title.contains("维修")||title.contains("清洗")){
                niceSpinner.attachDataSource(dataset3);
            }else if(title.contains("搬")){
                niceSpinner.attachDataSource(dataset4);
            }else if(title.contains("锁")){
                niceSpinner.attachDataSource(dataset5);
            }else{
                niceSpinner.attachDataSource(dataset);
            }

        }
        niceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                total = Integer.parseInt(price) * (position + 1);
                tvTotal.setText("总价：" + total + "元");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @OnClick(R.id.btn_back)
    public void onViewClicked() {
        finish();
    }

    @OnClick(R.id.btn_go)
    public void onViewClickedOrder() {
        JSONArray list = null;
        if (getOrder(getUsername()) != null && !getOrder(getUsername()).equals("")) {
            try {
                list = new JSONArray(getOrder(getUsername()));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            list = new JSONArray();
        }
        JSONObject object = new JSONObject();
        try {
            object.put("img", img);
            object.put("title", title);
            object.put("price", price);
            object.put("total", total);
            if (Integer.parseInt(price) >= 1000) {
                object.put("time", dataset2.get(niceSpinner.getSelectedIndex()));
            } else {
                if(title.contains("维修")){
                    object.put("time", dataset3.get(niceSpinner.getSelectedIndex()));
                }else if(title.contains("搬")){
                    object.put("time", dataset4.get(niceSpinner.getSelectedIndex()));
                }else if(title.contains("锁")){
                    object.put("time", dataset5.get(niceSpinner.getSelectedIndex()));
                }else{
                    object.put("time", dataset.get(niceSpinner.getSelectedIndex()));
                }

            }
            object.put("time2",time);
            object.put("name",etName.getText().toString().trim());
            object.put("mobile",etMobile.getText().toString().trim());
            object.put("address", et_address.getText().toString().trim());
            list.put(object);
            setOrder(getUsername(), list.toString());
            System.out.println(list.toString());
            new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("下单成功！")
                    .setConfirmText("查看订单")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            toActivity(OrderListActivity.class, null);
                        }
                    })
                    .show();
        } catch (Exception e) {
        }
    }

    @OnClick(R.id.tv_time2)
    public void onViewClickTime() {
        //时间选择器
        TimePickerView pvTime = new TimePickerBuilder(OrderActivity.this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
                tvTime2.setText(sdf.format(date));
                time = sdf.format(date);
            }
        }).setType(new boolean[]{true, true, true, true, true, false})//默认全部显示
                .setLabel("年", "月", "日", "时", "分", "秒").build();
        pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
        pvTime.show();
    }
}
