package com.xiaofei.android_learn.activites;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.xiaofei.android_learn.R;
import com.xiaofei.utils.AnimationUtils;
import com.xiaofei.utils.NavigatorUtils;

import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;


/**
 * Created by mayubao on 2016/11/28.
 * Contact me 345269374@qq.com
 */
public class TestRecyleView extends AppCompatActivity{

    private String TAG;
    RecyclerView rv;

    String[] strArray;
    Badge badge;
    int click_count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = this.getClass().getCanonicalName();
        setContentView(R.layout.activity_test_recyle_view);

        StringBuilder sb = new StringBuilder();
        String str = " Hello world ！#";
        for(int i=0; i < 30; i++){
            sb.append(str);
        }

        strArray = sb.toString().split("#");

        rv = (RecyclerView) this.findViewById(R.id.rv);
        // 设置布局显示方式，这里我使用都是垂直方式——LinearLayoutManager.VERTICAL
        rv.setLayoutManager(new GridLayoutManager(this, 1));
        // 设置添加删除item的时候的动画效果
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(new MyRecycleViewAdapter());

        TextView tv_added = (TextView) this.findViewById(R.id.added);
        badge = new QBadgeView(this).bindTarget(tv_added).setBadgeNumber(click_count);
        badge.setOnDragStateChangedListener(new Badge.OnDragStateChangedListener() {
            @Override
            public void onDragStateChanged(int dragState, Badge badge, View targetView) {
                Log.d(TAG, "dragState" + dragState);
                Log.d(TAG, "badge" + badge);
                Log.d(TAG, "targetView" + targetView);
                click_count = 0;
            }
        });

        Button btn_clear = (Button) this.findViewById(R.id.clear);
        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                badge.hide(true);
                click_count = 0;
            }
        });

    }

    public Activity getContext(){
        return this;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.test_recyle_view_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(this, "settings", Toast.LENGTH_SHORT).show();
            return true;
        }else if (id == R.id.action_client) {
//            Intent intent = new Intent(getContext(), ClientActivity.class);
//            startActivity(intent);
            NavigatorUtils.toChooseFileUI(getContext());
            return true;
        }else if (id == R.id.action_server) {
//            Intent intent = new Intent(getContext(), ServerActivity.class);
//            startActivity(intent);
            NavigatorUtils.toReceiverWaitingUI(getContext());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    class MyRecycleViewAdapter extends Adapter<MyRecycleViewAdapter.MyViewHolder>{
        private String TAG;

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            TAG = this.getClass().getCanonicalName();
            View contentView = View.inflate(getContext(), R.layout.item_transfer, null);
            return new MyViewHolder(contentView);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, int position) {
            Log.d(TAG, "onBindViewHolder called:" + "holder:" + holder + "," + "position:" + position);
            final TextView tv = (TextView) holder.itemView.findViewById(R.id.tv_item_transfer_name);
            tv.setText("transferitem:" + strArray[position] + position);
            final Button btn = (Button)holder.itemView.findViewById(R.id.btn_item_transfer_cancel);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, (String) tv.getText());
                    click_count += 1;
                    AnimationUtils.setAddTaskAnimation(getContext(), holder.itemView, badge.getTargetView(), null);
                    badge.setBadgeNumber(click_count);
                }
            });
        }

        @Override
        public int getItemCount() {
            return strArray.length;
        }

        class MyViewHolder extends ViewHolder{

            public MyViewHolder(View itemView) {
                super(itemView);
            }
        }
    }
}
