package com.villa.deimer.hackathon.models.services.data.local.database.login;

import android.util.Log;
import android.content.Context;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.villa.deimer.hackathon.models.entities.User;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.villa.deimer.hackathon.presenters.login.database.LoginDatabasePresenter;
import com.villa.deimer.hackathon.models.services.data.local.database.helper.DatabaseHelper;

public class LoginDatabaseAdapter {

    private Context context;
    private LoginDatabasePresenter loginDatabasePresenter;

    public LoginDatabaseAdapter(Context context, LoginDatabasePresenter loginDatabasePresenter) {
        this.context = context;
        this.loginDatabasePresenter = loginDatabasePresenter;
    }

    public void create(User user) {
        try {
            DatabaseHelper helper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
            RuntimeExceptionDao<User, Integer> userDao = helper.getUserRuntimeDao();
            userDao.create(user);
            loginDatabasePresenter.resultCreateUser(true, "");
        } catch (Exception ex) {
            loginDatabasePresenter.resultCreateUser(false, ex.toString());
            Log.e(this.getClass().getSimpleName(), "Error: " + ex.getMessage());
        }
    }

}
