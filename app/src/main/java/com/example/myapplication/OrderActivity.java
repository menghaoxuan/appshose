package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.base.BaseFragment;

import butterknife.ButterKnife;

public class OrderActivity extends BaseActivity {




    @Override
    protected void setContent() {
        super.setContent();
        setContentView(R.layout.activity_order);
        changeFragment(R.id.fragment, new TwoFragment());

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
}
