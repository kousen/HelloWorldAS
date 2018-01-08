package com.nfjs.helloworldas;

import android.content.res.Resources;

import java.util.List;

/**
 * Created by mm98800 on 1/6/18.
 */

public class ReturnMonth {
    public static String findMonth(int monthNum){
        String[] months = new String[]{
                "January",
                "February",
                "March",
                "April",
                "May",
                "June",
                "July",
                "August",
                "September",
                "October",
                "November",
                "December"};

        return months[monthNum];
    }

    public enum Months{
        January,
        February,
        March,
        April,
        May,
        June,
        July,
        August,
        September,
        October,
        November,
        December
    }
}
