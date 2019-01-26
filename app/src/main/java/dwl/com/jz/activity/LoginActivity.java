package dwl.com.jz.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dwl.com.jz.R;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.tv_reg)
    TextView tvReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_login, R.id.tv_reg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_login:
                String username = etAccount.getText().toString().trim();
                String psw = etPassword.getText().toString().trim();
                if(getUser(username)!=null) {
                    if (getUser(username).equals(psw)) {
                        toActivity(MainActivity.class, null);
                        showToast("登录成功！");
                        setUsername(username);
                    } else {
                        showToast("账号或密码错误！");
                    }
                }else{
                    showToast("请先注册！");
                }
                break;
            case R.id.tv_reg:
                toActivity(RegisterActivity.class,null);
                break;
        }
    }
}
