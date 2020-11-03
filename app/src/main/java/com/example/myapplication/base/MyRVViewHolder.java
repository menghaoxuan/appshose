package com.example.myapplication.base;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;



public class MyRVViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;

    public MyRVViewHolder(View itemView) {
        super(itemView);
        mViews = new SparseArray<>();
    }

    //通过viewId获取控件
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }


}