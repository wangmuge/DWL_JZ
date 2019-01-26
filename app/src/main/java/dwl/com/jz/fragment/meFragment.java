package dwl.com.jz.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dwl.com.jz.R;
import dwl.com.jz.activity.LoginActivity;
import dwl.com.jz.activity.OrderListActivity;
import dwl.com.jz.activity.SignActivity;
import dwl.com.jz.activity.SignDetailsActivity;


public class meFragment extends BaseFragment {
    @BindView(R.id.tv_name)
    TextView tvName;
    Unbinder unbinder;

    //    private TextView textView;
//    private Button button;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tvName.setText("欢迎您， "+getBaseActivity().getUsername());

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.rlyt_order, R.id.rlyt_bm,R.id.rlyt_bmxq,R.id.tv_login_out})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rlyt_order:
                getBaseActivity().toActivity(OrderListActivity.class,null);
                break;
            case R.id.rlyt_bm:
                getBaseActivity().toActivity(SignActivity.class,null);
                break;
            case R.id.rlyt_bmxq:
                getBaseActivity().toActivity(SignDetailsActivity.class,null);
                break;
            case R.id.tv_login_out:
                SharedPreferences sp = getBaseActivity().getSharedPreferences(
                        "username", Context.MODE_PRIVATE);
                sp.edit().clear().commit();
                getBaseActivity().showToast("注销成功！");
                getBaseActivity().jumpToActivityAndClearTask(LoginActivity.class);
                break;
        }
    }
}
