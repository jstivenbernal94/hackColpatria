package com.villa.deimer.hackathon.presenters.home.database;

import com.villa.deimer.hackathon.models.entities.User;

public interface HomeDatabasePresenter {

    void showUser();
    void logout(User user);

    void resultShowUser(User user);
    void resultLogout(boolean result);

}
