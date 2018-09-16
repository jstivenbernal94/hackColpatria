package com.villa.deimer.hackathon.presenters.login.network;

import com.villa.deimer.hackathon.models.entities.User;
import com.villa.deimer.hackathon.views.login.network.LoginNetworkInterface;
import com.villa.deimer.hackathon.models.services.data.remote.login.LoginRetrofitAdapter;

public class LoginNetworkPresenterImpl implements LoginNetworkPresenter {

    private LoginNetworkInterface loginNetworkInterface;

    public LoginNetworkPresenterImpl(LoginNetworkInterface loginNetworkInterface) {
        this.loginNetworkInterface = loginNetworkInterface;
    }

    @Override
    public void login(String baseUrl, String username, String password) {
        LoginRetrofitAdapter loginRetrofitAdapter = new LoginRetrofitAdapter(this);
        loginRetrofitAdapter.login(baseUrl, username, password);
    }

    @Override
    public void resultLogin(boolean result, String message, User user) {
        loginNetworkInterface.resultLogin(result, message, user);
    }
}
