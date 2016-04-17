package ecnu.pb.wireless_order.presenter;

import ecnu.pb.wireless_order.model.UserModel;
import ecnu.pb.wireless_order.net.RestAsyncTask;
import ecnu.pb.wireless_order.net.RestClient;
import ecnu.pb.wireless_order.widget.ToastUtils;
import retrofit.RetrofitError;

public class SignUpPresenter extends BasePresenter<SignUpPresenter.View> {

    public SignUpPresenter() {}

    public void signUp(String phone, String name, String password) {
        new SignUpTask(phone, name, password).execute();
    }

    private class SignUpTask extends RestAsyncTask {

        private UserModel userModel;
        private String phone;
        private String name;
        private String password;

        public SignUpTask(String phone , String name, String password) {
            this.phone = phone;
            this.name = name;
            this.password = password;
        }

        @Override
        protected Integer doInBackground(Void... params) {
            try {
                userModel = RestClient.createUserService().signup(name, password, phone);
                return userModel.getRegSuccess();
            } catch (RetrofitError e) {
                return e.getResponse() != null ? e.getResponse().getStatus() : 500;
            }
        }

        @Override
        protected void onPostExecute(Integer status) {
            super.onPostExecute(status);
            switch (status) {
                case 1:
                    getView().showView(userModel);
                    break;
                case 0:
                    ToastUtils.showToast(getContext(), "注册失败，用户名已存在");
                    break;
                case 500:
                    ToastUtils.showToast(getContext(), "网络连接失败");
                    break;
            }
        }
    }

    public interface View extends BaseView {
        void showView(UserModel userModel);
    }
}
