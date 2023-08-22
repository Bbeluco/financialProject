package com.study.financial.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StringUtils {

    public static String convertInputStreamIntoString(InputStream connInputStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(connInputStream));
        StringBuffer buff = new StringBuffer();
        String s = "";
        while((s = in.readLine()) != null) {
            buff.append(s);
        }
        in.close();
        return buff.toString();
    }
}
