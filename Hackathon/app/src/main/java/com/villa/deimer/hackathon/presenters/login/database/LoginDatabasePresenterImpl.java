package com.villa.deimer.hackathon.presenters.login.database;

import android.content.Context;

import com.villa.deimer.hackathon.models.entities.User;
import com.villa.deimer.hackathon.models.services.data.local.database.login.LoginDatabaseAdapter;
import com.villa.deimer.hackathon.views.login.database.LoginDatabaseInterface;

public class LoginDatabasePresenterImpl implements LoginDatabasePresenter {

    private Context context;
    private LoginDatabaseInterface loginDatabaseInterface;

    public LoginDatabasePresenterImpl(Context context, LoginDatabaseInterface loginDatabaseInterface) {
        this.context = context;
        this.loginDatabaseInterface = loginDatabaseInterface;
    }

    @Override
    public void create(User user) {
        LoginDatabaseAdapter loginDatabaseAdapter = new LoginDatabaseAdapter(context, this);
        loginDatabaseAdapter.create(user);
    }

    @Override
    public void resultCreateUser(boolean result, String message) {
        loginDatabaseInterface.resultCreateUser(result, message);
    }

}
