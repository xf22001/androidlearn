package com.xiaofei.android_learn.activites;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.xiaofei.android_learn.R;
import com.xiaofei.android_learn.fragments.AroundFragment;
import com.xiaofei.android_learn.fragments.HomeFragment;
import com.xiaofei.android_learn.fragments.MeFragment;
import com.xiaofei.android_learn.fragments.MoreFragment;


public class BottomNav_Viewpager_Fragment extends AppCompatActivity implements OnNavigationItemSelectedListener {

    private enum TabFragment {
        home(R.id.navigation_home, HomeFragment.class),
        around(R.id.navigation_around, AroundFragment.class),
        me(R.id.navigation_me, MeFragment.class),
        more(R.id.navigation_more, MoreFragment.class),
        ;

        private Fragment fragment;
        private final int menuId;
        private final Class<? extends Fragment> clazz;

        TabFragment(@IdRes int menuId, Class<? extends Fragment> clazz) {
            this.menuId = menuId;
            this.clazz = clazz;
        }

        @NonNull
        public Fragment fragment() {
            if (fragment == null) {
                try {
                    fragment = clazz.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                    fragment = new Fragment();
                }
            }
            return fragment;
        }

        public static TabFragment from(int itemId) {
            for (TabFragment fragment : values()) {
                if (fragment.menuId == itemId) {
                    return fragment;
                }
            }
            return me;
        }

        public static void onDestroy() {
            for (TabFragment fragment : values()) {
                fragment.fragment = null;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottonnav_viewpager_fragment);

        final BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
        MenuItem item = navigation.getMenu().add("test");
        item.setIcon(R.drawable.guide_btn_start_selector);

        ViewPager viewPager = findViewById(R.id.content);
        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return TabFragment.values().length;
            }
            @Override
            public Fragment getItem(int position) {
                return TabFragment.values()[position].fragment();
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                Log.d("xiaofei", "position" + position);
                navigation.setSelectedItemId(TabFragment.values()[position].menuId);
            }
        });

//        navigation.setSelectedItemId(R.id.navigation_me);
        viewPager.setCurrentItem(TabFragment.me.ordinal());
        //状态栏透明和间距处理
//        StatusBarUtil.immersive(this, 0xff000000, 0.1f);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        TabFragment.onDestroy();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Log.d("xiaofei", "TabFragment.from(item.getItemId()).ordinal()" + TabFragment.from(item.getItemId()).ordinal());
        ((ViewPager)findViewById(R.id.content)).setCurrentItem(TabFragment.from(item.getItemId()).ordinal());
        return true;
    }

}
