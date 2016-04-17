package ecnu.pb.wireless_order.presenter;

import ecnu.pb.wireless_order.model.OrderModel;
import ecnu.pb.wireless_order.net.RestAsyncTask;
import ecnu.pb.wireless_order.net.RestClient;
import ecnu.pb.wireless_order.widget.ToastUtils;
import retrofit.RetrofitError;

public class PlaceOrderPresenter extends BasePresenter<PlaceOrderPresenter.View> {

    public PlaceOrderPresenter() {}

    public void placeOrder(String order) {
        new OrderTask(order).execute();
    }

    private class OrderTask extends RestAsyncTask {

        private OrderModel orderModel;
        private String order;

        public OrderTask(String order) {
            this.order = order;
        }

        @Override
        protected Integer doInBackground(Void... params) {
            try {
                orderModel = RestClient.createOrderService().placeOrder(order);
                return 200;
            } catch (RetrofitError e) {
                return e.getResponse() != null ? e.getResponse().getStatus() : 500;
            }
        }

        @Override
        protected void onPostExecute(Integer status) {
            super.onPostExecute(status);
            switch (status) {
                case 200:
                    getView().showView(orderModel);
                    break;
                case 500:
                    ToastUtils.showToast(getContext(), "网络连接失败");
                    break;
            }
        }
    }

    public interface View extends BaseView {
        void showView(OrderModel orderModel);
    }
}
