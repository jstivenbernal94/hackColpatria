package com.villa.deimer.hackathon.views.home.database;

import com.villa.deimer.hackathon.models.entities.User;

public interface HomeDatabaseInterface {

    void resultShowUser(User user);
    void resultLogout(boolean result);

}
