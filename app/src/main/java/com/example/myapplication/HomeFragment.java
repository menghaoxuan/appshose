package com.example.myapplication;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.base.BaseRecyclerAdapter;
import com.example.myapplication.base.MyApplication;
import com.example.myapplication.base.MyRVViewHolder;
import com.example.myapplication.bean.GoodsBean;
import com.example.myapplication.bean.GoodsBeanMy;
import com.example.myapplication.util.DateUtil;
import com.example.myapplication.util.ToastUtil;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HomeFragment extends BaseFragment {
    @BindView(R.id.lv)
    RecyclerView lv;


    private List<GoodsBean> itemBeanList = new ArrayList();
    private MyAdapter myAdapter;

    public HomeFragment() {

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        ButterKnife.bind(this, view);
        initData();
        return view;
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initData() {
        initAdapter();

    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initAdapter() {
        for (int i = 0; i < 5; i++) {
            GoodsBean bean = new GoodsBean();
            bean.setGoods_id(i);
            bean.setGoods_num(1);
            bean.setGoods_price(10 + (i * 5));

            String name = "";
            int pic = 0;
            if (i == 0) {
                name = "椰子350";
                pic = R.mipmap.pic_yezi;
                bean.setZf("￥1899");
                bean.setKll("2019.03.30");
                bean.setDbz("Adidas Yeezy Boost 350 v2");
                bean.setmUrl("https://uland.taobao.com/sem/tbsearch?refpid=mm_26632258_3504122_32538762&clk1=654713619292c38cb50bc7c534d3fed7&keyword=%E6%A4%B0%E5%AD%90350&page=0");
            } else if (i == 1) {
                name = "欧文5";
                pic = R.mipmap.pic_ouwen;
                bean.setZf("￥999");
                bean.setKll("2019.08.10");
                bean.setDbz("SpongeBob SquarePants * Nike Kyrie 5");
                bean.setmUrl("https://uland.taobao.com/sem/tbsearch?refpid=mm_26632258_3504122_32538762&clk1=654713619292c38cb50bc7c534d3fed7&keyword=%E6%AC%A7%E6%96%875&page=0");

            } else if (i == 2) {
                name = "匡威";
                pic = R.mipmap.pic_kuangwei;
                bean.setZf("￥569");
                bean.setKll("2018.03.01");
                bean.setDbz("Converrse All Star 70");
                bean.setmUrl("https://uland.taobao.com/sem/tbsearch?refpid=mm_26632258_3504122_32538762&clk1=654713619292c38cb50bc7c534d3fed7&keyword=%E5%8C%A1%E5%A8%81&page=0");

            } else if (i == 3) {
                name = "AJ13";
                pic = R.mipmap.pic_aj13;
                bean.setZf("￥1499");
                bean.setKll("2020.01.01");
                bean.setDbz("Air Jordan 13 Retro CNY");
                bean.setmUrl("https://uland.taobao.com/sem/tbsearch?refpid=mm_26632258_3504122_32538762&clk1=654713619292c38cb50bc7c534d3fed7&keyword=aj13&page=0");

            } else if (i == 4) {
                name = "AJ1";
                pic = R.mipmap.pic_aj1;
                bean.setZf("￥1299");
                bean.setKll("2019.05.11");
                bean.setDbz("Travis Scott * Air Jordan 1");
                bean.setmUrl("https://uland.taobao.com/sem/tbsearch?refpid=mm_26632258_3504122_32538762&clk1=654713619292c38cb50bc7c534d3fed7&keyword=aj1&page=0");

            }

            bean.setGoods_name(name);
            bean.setGoods_pic(pic);

            itemBeanList.add(bean);

            //init listview
            @SuppressLint("WrongConstant")
            LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            if (null == manager)
                return;
            lv.setLayoutManager(manager);
            myAdapter = new MyAdapter(getActivity(), itemBeanList, R.layout.item_meal);
            lv.setAdapter(myAdapter);
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    class MyAdapter extends BaseRecyclerAdapter<GoodsBean> {

        private TextView tv_list_item, tv_1, tv_2, tv_3, tv_price, tv_add;
        private ImageView imgv_list;


        public MyAdapter(Context context, List<GoodsBean> datas, int layoutId) {
            super(context, datas, layoutId);
        }

        @Override
        public void setView(MyRVViewHolder holder, GoodsBean bean, int position) {
            if (null == holder || null == bean)
                return;
            //init view
            imgv_list = holder.getView(R.id.imgv_list);
            tv_list_item = holder.getView(R.id.tv_list_item);
            tv_1 = holder.getView(R.id.tv_1);
            tv_2 = holder.getView(R.id.tv_2);
            tv_3 = holder.getView(R.id.tv_3);
            tv_add = holder.getView(R.id.tv_add);
            //set view

            tv_1.setText("名称：" + bean.getDbz());
            tv_2.setText("发售日期：" + bean.getKll());
            tv_3.setText("价格：" + bean.getZf());
            tv_list_item.setText(bean.getGoods_name());
            imgv_list.setImageResource(bean.getGoods_pic());
            tv_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    new BuyDialog(getActivity(), bean).showDialog();

                    GoodsBeanMy tempBean = new GoodsBeanMy();
                    tempBean.setUserName(MyApplication.curUser);
                    tempBean.setmTime(DateUtil.getTodayData_3());
                    tempBean.setGoods_id(bean.getGoods_id());
//                    tempBean.setGoods_num(1);
                    tempBean.setGoods_price(bean.getGoods_price());
                    tempBean.setZf(bean.getZf());
                    tempBean.setKll(bean.getKll());
                    tempBean.setDbz(bean.getDbz());
                    tempBean.setGoods_name(bean.getGoods_name());
                    tempBean.setGoods_pic(bean.getGoods_pic());
                    tempBean.setmUrl(bean.getmUrl());
                    tempBean.save();
                    if (tempBean.isSaved()) {
                        ToastUtil.showToast(getActivity(), "购物车成功");
                    }
                }
            });
        }
    }


}
