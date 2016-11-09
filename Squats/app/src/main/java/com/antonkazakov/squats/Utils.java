package com.antonkazakov.squats;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by antonkazakov on 30.10.16.
 */

public class Utils {

    public static String toDate(long value) {
        Date date=new Date(value);
        SimpleDateFormat df2 = new SimpleDateFormat("dd MMMM HH:mm");
        return df2.format(date);
    }


}
