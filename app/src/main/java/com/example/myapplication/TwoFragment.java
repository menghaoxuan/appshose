package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.base.BaseRecyclerAdapter;
import com.example.myapplication.base.MyApplication;
import com.example.myapplication.base.MyRVViewHolder;
import com.example.myapplication.bean.GoodsBeanMy;
import com.example.myapplication.bean.EventBus_Tag;
import com.example.myapplication.util.ToastUtil;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.litepal.crud.DataSupport;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;


public class TwoFragment extends BaseFragment {
    @BindView(R.id.lv)
    RecyclerView lv;
    @BindView(R.id.tv_exit)
    TextView tv_exit;


    private List<GoodsBeanMy> buyItemBeans = new ArrayList();
    private MyAdapter myAdapter;

    public TwoFragment() {

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two, container, false);
        ButterKnife.bind(this, view);
        registerEventBus();
        initData();
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initData() {
        initAdapter();
        // initListener();
        tv_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initAdapter() {
        //init listview
        buyItemBeans.clear();
        List<GoodsBeanMy> temp = DataSupport.where("userName = " + MyApplication.curUser  ).find(GoodsBeanMy.class);//查询表Comment
        buyItemBeans.addAll(temp);
        @SuppressLint("WrongConstant")
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        if (null == manager)
            return;
        lv.setLayoutManager(manager);
        myAdapter = new MyAdapter(getActivity(), buyItemBeans, R.layout.item_meal2);
        lv.setAdapter(myAdapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    class MyAdapter extends BaseRecyclerAdapter<GoodsBeanMy> {

        private TextView tv_list_item, tv_1, tv_2, tv_3, tv_price, tv_add,tv_con;
        private ImageView imgv_list;
        private int selPosi;

        public void setSelPosi(int selPosi) {
            this.selPosi = selPosi;
        }

        public MyAdapter(Context context, List<GoodsBeanMy> datas, int layoutId) {
            super(context, datas, layoutId);
        }

        @Override
        public void setView(MyRVViewHolder holder, GoodsBeanMy bean, int position) {
            if (null == holder || null == bean)
                return;
            //init view
            imgv_list = holder.getView(R.id.imgv_list);
            tv_list_item = holder.getView(R.id.tv_list_item);
            tv_1 = holder.getView(R.id.tv_1);
            tv_2 = holder.getView(R.id.tv_2);
            tv_3 = holder.getView(R.id.tv_3);
            tv_add = holder.getView(R.id.tv_add);
            tv_con= holder.getView(R.id.tv_con);
            //set view
            tv_add.setText("取消添加");
            tv_1.setText("名称：" + bean.getDbz());
            tv_2.setText("发售日期：" + bean.getKll());
            tv_3.setText("价格：" + bean.getZf());
            tv_list_item.setText(bean.getGoods_name());
            imgv_list.setImageResource(bean.getGoods_pic());
            tv_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DataSupport.deleteAll(GoodsBeanMy.class, "mTime = ?", bean.getmTime());
                    EventBus.getDefault().post(new EventBus_Tag(12));
                }
            });
            tv_con.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    WebActivity.start(getActivity(), bean.getmUrl(), bean.getGoods_name(), false);

                }
            });
        }
    }

    String trainno12306;//标识删除名称

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(EventBus_Tag event) {
        switch (event.getTag()) {
            case 12:

                buyItemBeans.clear();
                List<GoodsBeanMy> temp = DataSupport.where("userName = " + MyApplication.curUser  ).find(GoodsBeanMy.class);//查询表Comment

                buyItemBeans.addAll(temp);
                myAdapter.notifyDataSetChanged();
                ToastUtil.showToast(getActivity(), "取消成功");
                break;
        }
    }

}
