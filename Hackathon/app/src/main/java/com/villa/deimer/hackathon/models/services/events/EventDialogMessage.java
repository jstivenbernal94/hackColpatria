package com.villa.deimer.hackathon.models.services.events;

public class EventDialogMessage {

    private int option;
    private boolean success;

    public EventDialogMessage(int option, boolean success) {
        this.option = option;
        this.success = success;
    }

    public int getOption() {
        return option;
    }
    public boolean isSuccess() {
        return success;
    }

}
