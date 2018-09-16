package com.villa.deimer.hackathon.presenters.login.database;

import com.villa.deimer.hackathon.models.entities.User;

public interface LoginDatabasePresenter {

    void create(User user);

    void resultCreateUser(boolean result, String message);

}
