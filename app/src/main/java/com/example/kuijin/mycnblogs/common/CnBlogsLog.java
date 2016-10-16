package com.example.kuijin.mycnblogs.common;

import android.util.Log;

/**
 * Created by kuijin on 2016/9/13.
 */
public class CnBlogsLog {

    public static final int LEVEL_ERROR = 0;
    public static final int LEVEL_WARNING = 1;
    public static final int LEVEL_INFO = 2;

    private static final String LogTag = "CnBlogsLogTag";

    public static void write(Exception ex) {
//        Log.i(LogTag, ex.getMessage());
        ex.printStackTrace();
    }

    public static void write(String className, String method, String message, int level) {
        Log.i(LogTag, ">>>>>>>>>>>>>>>" + message + " (" + className + "." + method + ")");
    }

}
