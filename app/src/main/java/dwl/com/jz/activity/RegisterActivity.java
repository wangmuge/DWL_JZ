package dwl.com.jz.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dwl.com.jz.R;

public class RegisterActivity extends BaseActivity {

    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_password2)
    EditText etPassword2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_back, R.id.tv_reg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.tv_reg:
                String username = etAccount.getText().toString().trim();
                String psw = etPassword.getText().toString().trim();
                String psw2 = etPassword2.getText().toString().trim();
                if(username.length()>0 && psw.length()>0 &&psw2.length()>0) {
                    String regex = "[a-zA-Z]\\w{5,9}";
                    if (!username.matches(regex)) {
                        showToast("用户名要以字母开头，只能包括字母,下划线,数字，长度必须在6到10之间");
                        return;
                    }
                    if (!psw.equals(psw2)) {
                        showToast("密码不一致，请重新输入！");
                        return;
                    }
                    showToast("注册成功！");
                    setUser(username, psw);
                    finish();
                }else{
                    showToast("请输入用户名或者密码！");
                }
                break;
        }
    }
}
