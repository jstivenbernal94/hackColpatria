package com.villa.deimer.hackathon.presenters.home.database;

import android.content.Context;
import com.villa.deimer.hackathon.models.entities.User;
import com.villa.deimer.hackathon.views.home.database.HomeDatabaseInterface;
import com.villa.deimer.hackathon.models.services.data.local.database.home.HomeDatabaseAdapter;

public class HomeDatabasePresenterImpl implements HomeDatabasePresenter {

    private Context context;
    private HomeDatabaseInterface homeDatabaseInterface;

    public HomeDatabasePresenterImpl(Context context, HomeDatabaseInterface homeDatabaseInterface) {
        this.context = context;
        this.homeDatabaseInterface = homeDatabaseInterface;
    }

    @Override
    public void showUser() {
        HomeDatabaseAdapter homeDatabaseAdapter = new HomeDatabaseAdapter(context, this);
        homeDatabaseAdapter.show();
    }

    @Override
    public void logout(User user) {
        HomeDatabaseAdapter homeDatabaseAdapter = new HomeDatabaseAdapter(context, this);
        homeDatabaseAdapter.logout(user);
    }

    @Override
    public void resultShowUser(User user) {
        homeDatabaseInterface.resultShowUser(user);
    }

    @Override
    public void resultLogout(boolean result) {
        homeDatabaseInterface.resultLogout(result);
    }
}
