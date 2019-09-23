package com.rio.proj.util;

import com.rio.proj.exception.CommonException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.time.DateUtils;

import java.util.Date;

public class CommonUtils {
    private static String[] parsePatterns = {
            "yyyy-MM-dd",
            "yyyy/MM/dd",
            "yyyy.MM.dd"
    };

    public static String md5(String value){
        return DigestUtils.md5Hex(value).toUpperCase();
    }

    public static Date parseStringDate(String dateString) throws CommonException {
        try {
            return DateUtils.parseDate(
                    dateString,
                    parsePatterns
            );
        }
        catch (Exception ex){
            throw new CommonException(ex.getMessage());
        }
    }
}
