package ecnu.pb.wireless_order.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ecnu.pb.wireless_order.R;
import ecnu.pb.wireless_order.adapter.PlaceOrderAdapter;
import ecnu.pb.wireless_order.database.MenuData;
import ecnu.pb.wireless_order.model.MenuModel;
import ecnu.pb.wireless_order.model.OrderModel;
import ecnu.pb.wireless_order.presenter.PlaceOrderPresenter;

public class PlaceOrderActivity extends AppCompatActivity implements PlaceOrderPresenter.View {

    private PlaceOrderAdapter adapter;
    private List<MenuModel> list = new ArrayList<>();
    private PlaceOrderPresenter mPresenter;

    @Bind(R.id.lv_place_list)
    ListView listView;

    @OnClick(R.id.txt_btn_order)
    void OnClickOrder() {
        //String order =
//        mPresenter.placeOrder("");
        setResult(1);
        finish();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        mPresenter = new PlaceOrderPresenter();
        mPresenter.attachView(this);

//        list = ListCache.getList();
        MenuData menuData = new MenuData();
        list = menuData.getData();
        adapter = new PlaceOrderAdapter(this, list);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    @Override
    public void showView(OrderModel orderModel) {
        startActivity(new Intent(this, OrderActivity.class));
        finish();
    }

    @Override
    public Context getViewContext() {
        return this;
    }

    @Override
    public void destroyView() {
        finish();
    }
}
