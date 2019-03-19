package com.example.majianghai.routerdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;
import java.util.Map;

public class TwoActivity extends BaseActivity {

    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);


        int price = (int) params.get("price");

        // 设置物理回退键指定跳转的类和参数
        Map params = new HashMap();
        params.put("price", 13);
        setPhysicalBack(OneActivity.class, params);


        btn = findViewById(R.id.route_forward_id);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map backParams = new HashMap();
                backParams.put("price", 12);
                Router.getInstance().routeBack(OneActivity.class, backParams);
            }
        });

    }
}
