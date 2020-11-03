package com.example.myapplication.base;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.view.Window;

public abstract class BaseDialog {
    public static OnKeyListener keylistener = new OnKeyListener() {
        public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
            return keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0;
        }
    };
    public Activity activity;
    public AlertDialog dialog;

    public BaseDialog(Activity activity) {
        this.activity = activity;
    }

    public void showDialog() {
        dialog = new AlertDialog.Builder(activity).create();
        //点击外部区域取消dialog
        dialog.setCanceledOnTouchOutside(true);
        dialog.setOnKeyListener(keylistener);
        dialog.show();

        Window window = dialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        //解决棱角问题
        window.setBackgroundDrawable(new BitmapDrawable());
        initDialogEvent(window);

    }

    //初始化Dialog的事件
    public abstract void initDialogEvent(Window window);


}
