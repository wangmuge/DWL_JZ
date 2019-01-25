package dwl.com.jz.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import butterknife.Unbinder;
import dwl.com.jz.activity.BaseActivity;

/**
 * Created by wanglei on 2016/11/27.
 */

public abstract class BaseFragment extends Fragment {
    protected View rootView;
    protected LayoutInflater layoutInflater;
    protected Activity context;
    private BaseActivity mBaseActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        layoutInflater = inflater;
        ViewGroup viewGroup = (ViewGroup) rootView.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(rootView);
            }

        return rootView;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            this.context = (Activity) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        context = null;
    }



    protected BaseActivity getBaseActivity() {
        return mBaseActivity;
    }
    @Override
    public void onAttach(Activity activity) {
        // TODO Auto-generated method stub
        mBaseActivity = (BaseActivity) activity;
        super.onAttach(activity);
    }

}
