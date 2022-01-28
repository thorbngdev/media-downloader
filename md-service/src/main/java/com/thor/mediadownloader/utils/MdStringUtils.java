package com.thor.mediadownloader.utils;

public class MdStringUtils {

    public static String addLeftZeroToTimeField(long tf) {
        String strTimeField = String.valueOf(tf);

        return strTimeField.length() == 1 ? "0".concat(strTimeField) : strTimeField;
    }
}
