package com.example.workout;

import android.os.Handler;

public class Time extends ActivitiesFragment {
    public long millis, startTime;
    public int seconds, minutes, hours;
   public Handler timerHandler;
   public Runnable timerRunnable;

    public void setTimerHandler(Handler timerHandler) {
        this.timerHandler = new Handler();
    }

    public void setTimerRunnable(Runnable timerRunnable) {
        this.timerRunnable = new Runnable() {
            @Override
            public void run() {
                millis=System.currentTimeMillis()-startTime;
                seconds=(int)(millis/1000);
                minutes=seconds/60;
                hours=minutes/60;
            }
        };
    }
}
