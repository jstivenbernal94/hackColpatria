package com.villa.deimer.hackathon.presenters.welcome.database;

import android.content.Context;

import com.villa.deimer.hackathon.models.services.data.local.database.welcome.WelcomeDatabaseAdapter;
import com.villa.deimer.hackathon.views.welcome.database.WelcomeDatabaseInterface;

public class WelcomeDatabasePresenterImpl implements WelcomeDatabasePresenter {

    private Context context;
    private WelcomeDatabaseInterface welcomeDatabaseInterface;

    public WelcomeDatabasePresenterImpl(Context context, WelcomeDatabaseInterface welcomeDatabaseInterface) {
        this.context = context;
        this.welcomeDatabaseInterface = welcomeDatabaseInterface;
    }

    @Override
    public void isLogged() {
        WelcomeDatabaseAdapter welcomeDatabaseAdapter = new WelcomeDatabaseAdapter(context, this);
        welcomeDatabaseAdapter.isLogged();
    }

    @Override
    public void resultIsLogged(boolean result) {
        welcomeDatabaseInterface.resultIsLogged(result);
    }

}
