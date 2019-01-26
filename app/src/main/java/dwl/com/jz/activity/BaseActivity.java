package dwl.com.jz.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.widget.EditText;
import android.widget.Toast;



public abstract class BaseActivity extends AppCompatActivity  {
    protected Activity context;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = this;
    }


    public void showToast(String text) {
        showToast(text, false);
    }

    public void showToast(String text, boolean isLong) {
        if (text != null) {
            Toast.makeText(this, text,
                    isLong ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT).show();
        }
    }

    public void showToast(int stringResId) {
        showToast(stringResId, false);
    }

    public void showToast(int stringResId, boolean isLong) {
        if (stringResId != 0) {
            Toast.makeText(this, stringResId,
                    isLong ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT).show();
        }
    }

    public void jumpToActivity(Intent intent) {
        startActivity(intent);
    }

    public void jumpToActivity(Class activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }

//    public void jumpToWebViewActivity(String url) {
//        Intent intent = new Intent(this, WebViewActivity.class);
//        intent.putExtra("url", url);
//        jumpToActivity(intent);
//    }


    public void jumpToActivityAndClearTask(Class activity) {
        Intent intent = new Intent(this, activity);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    public void jumpToActivityAndClearTop(Class activity) {
        Intent intent = new Intent(this, activity);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    protected Bundle getBundle() {
        return getIntent().getExtras();
    }

    public void toActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (newConfig.fontScale != 1)//非默认值
            getResources();
        super.onConfigurationChanged(newConfig);
    }
    public void setSign(String name,String sign) {
        SharedPreferences sp = getSharedPreferences("sign",
                Context.MODE_PRIVATE);
        sp.edit().putString(name, sign).commit();

    }
    public String getSign(String name) {
        SharedPreferences sp = getSharedPreferences("sign",
                Context.MODE_PRIVATE);
        String num = sp.getString(name,null);
        return num;

    }

    public void setUser(String name,String psw) {
        SharedPreferences sp = getSharedPreferences("user",
                Context.MODE_PRIVATE);
        sp.edit().putString(name, psw).commit();

    }
    public String getUser(String name) {
        SharedPreferences sp = getSharedPreferences("user",
                Context.MODE_PRIVATE);
        String num = sp.getString(name,null);
        return num;

    }
    public void setUsername(String name) {
        SharedPreferences sp = getSharedPreferences("username",
                Context.MODE_PRIVATE);
        sp.edit().putString("username", name).commit();

    }
    public String getUsername() {
        SharedPreferences sp = getSharedPreferences("username",
                Context.MODE_PRIVATE);
        String num = sp.getString("username",null);
        return num;

    }
    public void setOrder(String name,String order) {
        SharedPreferences sp = getSharedPreferences("order_"+name,
                Context.MODE_PRIVATE);
        sp.edit().putString(name, order).commit();

    }
    public String getOrder(String name) {
        SharedPreferences sp = getSharedPreferences("order_"+name,
                Context.MODE_PRIVATE);
        String num = sp.getString(name,null);
        return num;

    }

}
