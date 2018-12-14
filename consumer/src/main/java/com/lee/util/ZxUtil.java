package com.lee.util;

import org.apache.commons.lang.StringUtils;

/**
 * @author lee
 **/
public class ZxUtil {

    public static boolean isBlank(String... strings) {
        for (String str : strings) {
            if (StringUtils.isBlank(str)) {
                return true;
            }
        }
        return false;
    }
}
