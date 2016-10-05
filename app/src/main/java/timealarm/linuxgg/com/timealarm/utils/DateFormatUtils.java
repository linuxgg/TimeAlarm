/*
 * Copyright (C) ScanTrust SA - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * ScanTrust Authors, 2015
 */
package timealarm.linuxgg.com.timealarm.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtils {
    private static final DateFormat yyyyMMddFormat = new SimpleDateFormat("yyyy-MM-dd HH:SS");

    /**
     * @param date
     * @return yyyy-MM-dd Date String format
     */
    public static String getNormalDateFormatString(Date date) {
        if (date == null) {
            return "";
        } else {
            return yyyyMMddFormat.format(date);
        }
    }

    /**
     * @param timeMillis
     * @return yyyy-MM-dd Date String format
     */
    public static String getNormalDateFormatString(Long timeMillis) {
        if (timeMillis == null) {
            return "";
        } else {
            return getNormalDateFormatString(new Date(timeMillis));
        }
    }
}
