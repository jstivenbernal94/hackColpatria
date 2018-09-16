package com.villa.deimer.hackathon.models.services.data.local.database.welcome;

import android.util.Log;
import android.content.Context;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.villa.deimer.hackathon.models.entities.User;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.villa.deimer.hackathon.presenters.welcome.database.WelcomeDatabasePresenter;
import com.villa.deimer.hackathon.models.services.data.local.database.helper.DatabaseHelper;

public class WelcomeDatabaseAdapter {

    private Context context;
    private WelcomeDatabasePresenter welcomeDatabasePresenter;

    public WelcomeDatabaseAdapter(Context context, WelcomeDatabasePresenter welcomeDatabasePresenter) {
        this.context = context;
        this.welcomeDatabasePresenter = welcomeDatabasePresenter;
    }

    public void isLogged() {
        try {
            DatabaseHelper helper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
            RuntimeExceptionDao<User, Integer> userDao = helper.getUserRuntimeDao();
            User user = userDao.queryForAll().get(0);
            if(user != null) {
                welcomeDatabasePresenter.resultIsLogged(true);
            } else {
                welcomeDatabasePresenter.resultIsLogged(false);
            }
        } catch (Exception ex) {
            welcomeDatabasePresenter.resultIsLogged(false);
            Log.e(this.getClass().getSimpleName(), "Error: " + ex.getMessage());
        }
    }

}
