package com.ceylonapz.nytimes.utility;

/**
 * Created by AMAL on 2018-08-08.
 */

public class Constant {

    static {
        System.loadLibrary("native-lib");
    }

    public static native String baseUrl();
    public static native String getApiKey();

    public final static String BASE_URL = baseUrl();
    public final static String API_KEY = getApiKey();

}
