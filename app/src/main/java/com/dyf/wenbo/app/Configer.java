package com.dyf.wenbo.app;

import android.os.Environment;

import java.io.File;

public final class Configer{

    public static final String BUG_NAME = "wenbo";

    public static final String HTTP_URL = "http://59.46.12.222/";
    //public static final String HTTP_URL = "http://192.168.1.100:8080/";

    public static final boolean BUG_STATIC = true;

    public static final String PERF_USER = "wenbo_user";

    public static final String PERF_APP = "wenbo_app";

    public static final int app_type = 1;//1为文播

    public static final File SDCARD_DIR = Environment.getExternalStorageDirectory();
    public static final String _CACHE_ = "wenbo/cache";

}