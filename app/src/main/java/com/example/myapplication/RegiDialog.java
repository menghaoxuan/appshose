package com.example.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.base.BaseDialog;
import com.example.myapplication.util.ScreenUtil;
import com.example.myapplication.util.StrUtil;
import com.example.myapplication.util.ToastUtil;
import com.example.myapplication.bean.UserInfoBean;


public class RegiDialog extends BaseDialog {

    TextView  tv_commit, tv_cannal;
    EditText et_phone, et_pwd;
    public RegiDialog(Activity activity) {
        super(activity);

    }

    @Override
    public void initDialogEvent(Window window) {
        window.setContentView(R.layout.dialog_regi);
        window.clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        //init view
        et_phone = window.findViewById(R.id.et_phone);
        et_pwd = window.findViewById(R.id.et_pwd);
        tv_commit = window.findViewById(R.id.tv_commit);
        tv_cannal = window.findViewById(R.id.tv_cannal);
        //set view
        //取消
        tv_cannal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        //确定
        tv_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tempPhone = et_phone.getText().toString().trim();
                String tempPwd = et_pwd.getText().toString().trim();
                if (StrUtil.isEmpty(tempPhone) || StrUtil.isEmpty(tempPwd)) {
                    ToastUtil.showToast(activity, "手机号或密码不能为空");
                    return;
                }
                if (tempPhone.length()!=11) {
                    ToastUtil.showToast(activity, "手机号错误");
                    return;
                }
                UserInfoBean tempBean = new UserInfoBean();
                tempBean.setName(tempPhone);
                tempBean.setPwd(tempPwd);
                tempBean.save();
                if (tempBean.isSaved()) {
                    ToastUtil.showToast(activity, "注册成功");
                    dialog.dismiss();
                }

            }
        });

    }


    @Override
    public void showDialog() {
        dialog = new AlertDialog.Builder(activity).create();
        //点击外部区域取消dialog
        dialog.setCanceledOnTouchOutside(false);
        dialog.setOnKeyListener(null);
        dialog.show();

        Window window = dialog.getWindow();
        window.setLayout((int) (ScreenUtil.getScreenWidth(activity) * 0.8), (int) (ScreenUtil.getScreenHeight(activity) * 0.5));
        window.setGravity(Gravity.CENTER);
        //解决棱角问题
        window.setBackgroundDrawable(new BitmapDrawable());
        initDialogEvent(window);
    }
}

