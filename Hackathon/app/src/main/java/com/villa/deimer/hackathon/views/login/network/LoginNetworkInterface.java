package com.villa.deimer.hackathon.views.login.network;

import com.villa.deimer.hackathon.models.entities.User;

public interface LoginNetworkInterface {

    void resultLogin(boolean result, String message, User user);

}
