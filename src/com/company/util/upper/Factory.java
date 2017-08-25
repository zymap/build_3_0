package com.company.util.upper;

/**
 * Created by zy on 17-8-18.
 */
public class Factory {
    private static String ADN="ADN#";
    private static String COMMANDEND="&";

    public static String END="END";

    public static String upperRollData(String var){
        return ADN+var+COMMANDEND;
    }

    public static String cancelQuotation(String var){
        return var.substring(1,var.length()-1);
    }
}
