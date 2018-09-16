package com.villa.deimer.hackathon.presenters.login.network;

import com.villa.deimer.hackathon.models.entities.User;

public interface LoginNetworkPresenter {

    void login(String baseUrl, String username, String password);

    void resultLogin(boolean result, String message, User user);

}
