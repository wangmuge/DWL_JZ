package dwl.com.jz.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import dwl.com.jz.R;

public class SignActivity extends BaseActivity {

    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_sex)
    EditText etSex;
    @BindView(R.id.et_mobile)
    EditText etMobile;
    @BindView(R.id.et_age)
    EditText etAge;
    @BindView(R.id.et_hometown)
    EditText etHometown;
    @BindView(R.id.et_city)
    EditText etCity;
    @BindView(R.id.et_type)
    EditText etType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.btn_back, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.tv_submit:
                JSONObject object = new JSONObject();
                try {
                    object.put("name", etName.getText().toString());
                    object.put("sex", etSex.getText().toString());
                    object.put("mobile", etMobile.getText().toString());
                    object.put("age", etAge.getText().toString());
                    object.put("hometown", etHometown.getText().toString());
                    object.put("city", etCity.getText().toString());
                    object.put("type", etType.getText().toString());

                    setSign(getUsername(),object.toString());

                    System.out.println(object.toString());
                    new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
                            .setTitleText("报名成功！")
                            .setConfirmText("查看报名详情")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    toActivity(SignDetailsActivity.class, null);
                                }
                            })
                            .show();
                } catch (Exception e) {
                }
                break;
        }
    }
}
