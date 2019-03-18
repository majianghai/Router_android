package com.example.majianghai.routerdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;
import java.util.Map;

public class OneActivity extends BaseActivity {

    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);

        int price = (int) params.get("price");

        btn = findViewById(R.id.route_forward_id);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map params = new HashMap();
                params.put("price", 11);
                Router.getInstance().routeForward(TwoActivity.class, params);

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (backParams != null) {
            int price = (int) backParams.get("price");
        }

        System.out.println();
    }
}
