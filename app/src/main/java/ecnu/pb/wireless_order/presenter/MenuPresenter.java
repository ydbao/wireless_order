package ecnu.pb.wireless_order.presenter;

import ecnu.pb.wireless_order.model.MenuModel;
import ecnu.pb.wireless_order.net.RestAsyncTask;
import ecnu.pb.wireless_order.net.RestClient;
import ecnu.pb.wireless_order.widget.ToastUtils;
import retrofit.RetrofitError;

public class MenuPresenter extends BasePresenter<MenuPresenter.View> {

    public MenuPresenter() {}

    public void getMenu(int type) {
        new MenuTask(type).execute();
    }

    private class MenuTask extends RestAsyncTask {

        private MenuModel menuModel;
        private int type;

        public MenuTask(int type) {
            this.type = type;
        }

        @Override
        protected Integer doInBackground(Void... params) {
            try {
                menuModel = RestClient.createMenuService().getMenu(type);
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
                    getView().showView(menuModel);
                    break;
                case 500:
                    ToastUtils.showToast(getContext(), "网络连接失败");
                    break;
            }
        }
    }

    public interface View extends BaseView {
        void showView(MenuModel menuModel);
    }
}
