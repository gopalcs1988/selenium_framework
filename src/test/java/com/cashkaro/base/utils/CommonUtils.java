package com.cashkaro.base.utils;

import java.util.HashMap;

public class CommonUtils {

    public static HashMap<String, String> sharedMap = new HashMap<String, String>();

    public static void setMapData(String key, String value) {
        sharedMap.put(key, value);
    }

    public static String getMapData(String key) {
        return sharedMap.get(key);
    }

}
