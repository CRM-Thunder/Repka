package com.example.workout;

public class ChronometerHelper {

    private static Long mStartTime;

    public static Long getmStartTime() {
        return mStartTime;
    }

    public static void setmStartTime(Long mStartTime) {
        ChronometerHelper.mStartTime = mStartTime;
    }
}
