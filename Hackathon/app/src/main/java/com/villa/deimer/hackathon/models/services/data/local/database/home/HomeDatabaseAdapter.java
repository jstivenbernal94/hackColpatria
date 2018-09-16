package com.villa.deimer.hackathon.models.services.data.local.database.home;

import android.util.Log;
import android.content.Context;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.villa.deimer.hackathon.models.entities.User;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.villa.deimer.hackathon.models.services.data.local.database.helper.DatabaseHelper;
import com.villa.deimer.hackathon.presenters.home.database.HomeDatabasePresenter;

public class HomeDatabaseAdapter {

    private DatabaseHelper helper;
    private Context context;
    private HomeDatabasePresenter homeDatabasePresenter;

    public HomeDatabaseAdapter(Context context, HomeDatabasePresenter homeDatabasePresenter) {
        this.context = context;
        this.homeDatabasePresenter = homeDatabasePresenter;
    }

    public void show() {
        User user;
        try {
            helper = OpenHelperManager.getHelper(context,DatabaseHelper.class);
            RuntimeExceptionDao<User, Integer> userDao = helper.getUserRuntimeDao();
            user = userDao.queryForAll().get(0);
        } catch (Exception ex) {
            user = null;
            Log.e(this.getClass().getSimpleName(), "Error: " + ex.getMessage());
        }
        homeDatabasePresenter.resultShowUser(user);
    }

    public void logout(User user) {
        try {
            helper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
            RuntimeExceptionDao<User, Integer> userDao = helper.getUserRuntimeDao();
            userDao.delete(user);
            homeDatabasePresenter.resultLogout(true);
        }catch (Exception ex){
            homeDatabasePresenter.resultLogout(false);
            Log.e(this.getClass().getSimpleName(), "Error: " + ex.toString());
        }
    }

}
