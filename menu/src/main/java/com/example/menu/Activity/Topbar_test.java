package com.example.menu.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.menu.R;
import com.example.menu.Utils.Topbar;

public class Topbar_test extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topbar_test);
        initView();
    }

    private void initView() {
        Topbar topbar=findViewById(R.id.topbar);
        topbar.setOnTopbarClickListener(new Topbar.topbarClickListener() {
            @Override
            public void leftClick() {
                Toast.makeText(Topbar_test.this, "你好",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void rightClick() {
                Toast.makeText(Topbar_test.this, "你好",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
