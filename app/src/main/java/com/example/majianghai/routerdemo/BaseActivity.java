package com.example.majianghai.routerdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Map;

public class BaseActivity extends AppCompatActivity {

    public Map params;

    public Map backParams;

    private Class backPressedCls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        Router.getInstance().addActivity(this);

        BaseActivity currentActivity = (BaseActivity) Router.getInstance().currentActivity();

        currentActivity.params = Router.getInstance().params;
    }

    /*物理返回键设置参数方法*/
    public void setPhysicalBack(Class<?> cls, Map par) {
        backPressedCls = cls;
        backParams = par;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        System.out.println();

        if (backPressedCls != null) {
            Router.getInstance().routeBack(backPressedCls, backParams);
        }
    }

}
