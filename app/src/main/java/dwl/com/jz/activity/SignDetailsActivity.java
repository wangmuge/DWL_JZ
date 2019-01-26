package dwl.com.jz.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dwl.com.jz.R;
import dwl.com.jz.bean.Order;
import dwl.com.jz.bean.Sign;

public class SignDetailsActivity extends BaseActivity {

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.tv_mobile)
    TextView tvMobile;
    @BindView(R.id.tv_age)
    TextView tvAge;
    @BindView(R.id.tv_hometown)
    TextView tvHometown;
    @BindView(R.id.tv_city)
    TextView tvCity;
    @BindView(R.id.tv_type)
    TextView tvType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_details);
        ButterKnife.bind(this);
        if (getSign(getUsername()) != null && !getSign(getUsername()).equals("")) {
            Sign sign = JSONObject.parseObject(getSign(getUsername()), Sign.class);
            tvAge.setText(sign.getAge());
            tvCity.setText(sign.getCity());
            tvHometown.setText(sign.getHometown());
            tvMobile.setText(sign.getMobile());
            tvName.setText(sign.getName());
            tvSex.setText(sign.getSex());
            tvType.setText(sign.getType());

        } else {
            showToast("您还未报名！");
        }
    }

    @OnClick(R.id.btn_back)
    public void onViewClicked() {
        finish();
    }
}
