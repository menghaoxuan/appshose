package com.example.myapplication;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.base.GlobalData;
import com.example.myapplication.base.MyApplication;
import com.example.myapplication.util.SharedPreUtils;
import com.example.myapplication.util.StrUtil;
import com.example.myapplication.util.ToastUtil;
import com.example.myapplication.bean.UserInfoBean;
import org.litepal.crud.DataSupport;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity {
    @BindView(R.id.tv_regi)
    TextView tv_regi;
    @BindView(R.id.tv_login)
    TextView tv_login;
    @BindView(R.id.et_phone)
    EditText et_phone;
    @BindView(R.id.et_pwd)
    EditText et_pwd;
    @BindView(R.id.iv_right_err)
    ImageView iv_right_err;

    @Override
    protected void setContent() {
        super.setContent();
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        int temp = (int) SharedPreUtils.get(myContext, GlobalData.sp_key_1, 0);
        String temp2 = (String) SharedPreUtils.get(myContext, GlobalData.sp_key_2, "");
        String temp3 = (String) SharedPreUtils.get(myContext, GlobalData.sp_key_3, "");
        if (temp == 0) {
            iv_right_err.setBackgroundResource(R.mipmap.icon_0);
        } else {
            iv_right_err.setBackgroundResource(R.mipmap.icon_1);
            et_phone.setText(temp2);
            et_pwd.setText(temp3);
        }
    }

    @Override
    protected void initListener() {
        iv_right_err.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = (int) SharedPreUtils.get(myContext, GlobalData.sp_key_1, 0);
                if (temp == 0) {
                    SharedPreUtils.put(myContext, GlobalData.sp_key_1, 1);
                    iv_right_err.setBackgroundResource(R.mipmap.icon_1);
                } else {
                    SharedPreUtils.put(myContext, GlobalData.sp_key_1, 0);
                    iv_right_err.setBackgroundResource(R.mipmap.icon_0);
                }
            }
        });

        tv_regi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new RegiDialog(myActivity).showDialog();
            }
        });

        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tempPhone = et_phone.getText().toString().trim();
                String tempPwd = et_pwd.getText().toString().trim();
                if (StrUtil.isEmpty(tempPhone) || StrUtil.isEmpty(tempPwd)) {
                    ToastUtil.showToast(myActivity, "手机号或密码不能为空");
                    return;
                }

                if (tempPhone.length() != 11) {
                    ToastUtil.showToast(myActivity, "手机号错误");
                    return;
                }

                List<UserInfoBean> list = DataSupport.findAll(UserInfoBean.class);
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getName().equals(tempPhone) && list.get(i).getPwd().equals(tempPwd)) {
                        int temp = (int) SharedPreUtils.get(myContext, GlobalData.sp_key_1, 0);
                        if (temp == 0) {
                            SharedPreUtils.put(myContext, GlobalData.sp_key_2, "");
                            SharedPreUtils.put(myContext, GlobalData.sp_key_3, "");
                        } else {
                            SharedPreUtils.put(myContext, GlobalData.sp_key_2, tempPhone);
                            SharedPreUtils.put(myContext, GlobalData.sp_key_3, tempPwd);
                        }
                        MyApplication.curUser = tempPhone;
                        startActivity(new Intent(myContext, MainActivity.class));
                        finish();
                        return;
                    }
                }
                ToastUtil.showToast(myActivity, "手机号或密码不正确");

            }
        });
    }
}
