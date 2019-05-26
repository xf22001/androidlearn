package com.xiaofei.android_learn;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.xiaofei.android_learn.activites.BottomNav_Viewpager_Fragment;
import com.xiaofei.android_learn.activites.GuideActivity;
import com.xiaofei.android_learn.activites.SlidingTabLayout_Viewpager_Fragment;
import com.xiaofei.android_learn.activites.TestRecyleView;
import com.xiaofei.android_learn.activites.ToolbarActivity;
import com.xiaofei.android_learn.activites.WaitCatDialog;


public class MainActivity extends AppCompatActivity {
    private LinearLayout content_layout;
    private Class info[] = {
            MainActivity.class,
            TestRecyleView.class,
            GuideActivity.class,
            BottomNav_Viewpager_Fragment.class,
            SlidingTabLayout_Viewpager_Fragment.class,
            ToolbarActivity.class,
            WaitCatDialog.class,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //常亮
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        //去掉常亮
        //this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_main);
        initview();
    }

    private Context getMainActivityContext() {
        return this;
    }

    private void initview() {
        int i;
        content_layout = (LinearLayout) findViewById(R.id.content);
        for (i = 0; i < info.length; i++) {
            final Class cls = info[i];
            final String buttoin_name = cls.getSimpleName();
            Button button = new Button(this);
            button.setText(buttoin_name);
            button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, buttoin_name, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getMainActivityContext(), cls);
                    startActivity(intent);
                    overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
                }
            });
            content_layout.addView(button);
        }

    }
}
