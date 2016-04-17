package ecnu.pb.wireless_order.presenter;

import ecnu.pb.wireless_order.model.UserModel;
import ecnu.pb.wireless_order.net.RestAsyncTask;
import ecnu.pb.wireless_order.net.RestClient;
import ecnu.pb.wireless_order.widget.ToastUtils;
import retrofit.RetrofitError;

public class SignInPresenter extends BasePresenter<SignInPresenter.View> {

    public SignInPresenter() {}

    public void signIn(String name, String password) {
        new SignInTask(name, password).execute();
    }

    private class SignInTask extends RestAsyncTask {

        private UserModel userModel;
        private String name;
        private String password;

        public SignInTask( String name, String password) {
            this.name = name;
            this.password = password;
        }

        @Override
        protected Integer doInBackground(Void... params) {
            try {
                userModel = RestClient.createUserService().signin(name, password);
                return userModel.getLoginSuccess();
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
                    ToastUtils.showToast(getContext(), "登录失败，用户名或密码错误");
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
