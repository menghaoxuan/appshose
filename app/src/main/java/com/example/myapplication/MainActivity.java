package com.example.myapplication;

import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.myapplication.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tv_main)
    TextView tv_main;
    @BindView(R.id.tv_me)
    TextView tv_me;

    public Fragment getoFragment() {
//        if (null == oFragment) {
            oFragment = new HomeFragment();
//        }
        return oFragment;
    }

    public Fragment gettFragment() {
//        if (null == tFragment) {
           tFragment = new MeFragment();
//        }
        return tFragment;
    }

    private Fragment oFragment, tFragment;

    @Override
    protected void setContent() {
        super.setContent();
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        View v = findViewById(R.id.forever2);//找到你要设透明背景的layout 的id
        v.getBackground().setAlpha(150);

    }

    @Override
    protected void initData() {
        DisplayFragment(0);
    }

    @Override
    protected void initListener() {
        tv_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisplayFragment(0);
            }
        });

        tv_me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisplayFragment(1);
            }
        });
    }

    public void DisplayFragment(int position) {

        switch (position) {

            case 0:
                changeFragment(R.id.fragment, getoFragment());
                break;
            case 1:
                changeFragment(R.id.fragment, gettFragment());
                break;


        }
    }

}
