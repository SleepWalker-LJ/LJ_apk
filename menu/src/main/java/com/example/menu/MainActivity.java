package com.example.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.example.menu.Activity.RecycleView_test;
import com.example.menu.Activity.Topbar_test;
import com.example.menu.slidingmenu.view.SildingMenu;

public class MainActivity extends Activity {

    private SildingMenu menu;
    private TextView txv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        menu = findViewById(R.id.menu);
        initView();
    }

    private void initView() {
        txv1 = findViewById(R.id.txv1);
        TextView tv_menu1=findViewById(R.id.tv_menu1);

        txv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Topbar_test.class));
            }
        });
        tv_menu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RecycleView_test.class));
            }
        });
    }

    public void toggleMenu (View view){
        menu.toggle();
    }
}
