package com.company.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Created by zy on 17-7-21.
 */
public class JsonSplit {
    private String json;
    private static String UUID = "uuid";
    private static String DUID = "duid";
    private static String BGROUP = "bgroup";
    private static String BORG = "borg";
    private static String ADN = "adn";
    private static String BDEVICE = "bdevice";
    private static String BFLOOR = "bfloor";
    private static String ROOM = "room";
    private static String ROOMTEM = "roomtem";
    private static String TEM = "tem";
    private static String WIND = "wind";
    private static String MODESET = "modeset";
    private static String DEVICESTATUS = "devicestatus";
    private static String BUS = "bus";




//    public void setUuid(String uuid) {

    private JsonParser jsonParser = new JsonParser();;

    private JsonObject jsonObject;
    // return (String.valueOf(jsonObject.get("status")));
    public JsonSplit(String json){
        jsonObject = (JsonObject) jsonParser.parse(json);
        this.json = json;
    }
    //    }
//        this.uuid = uuid;
    public String getUuid() {
        return String.valueOf(jsonObject.get(UUID));
    }

    public String get(String name){
        return String.valueOf(jsonObject.get(name));
    }

    public String getBus(){
        return String.valueOf(jsonObject.get(BUS));
    }

    public String getDuid() {
        return String.valueOf(jsonObject.get(DUID));
    }

    public String getBgroup() {
        return String.valueOf(jsonObject.get(BGROUP));
    }

    public String getAdn() {
        return String.valueOf(jsonObject.get(ADN));
    }

    public String getBorg() {
        return String.valueOf(jsonObject.get(BORG));
    }

    public String getBdevicetype() {
        return String.valueOf(jsonObject.get(BDEVICE));
    }

    public String getBfloor() {
        return String.valueOf(jsonObject.get(BFLOOR));
    }

    public String getRoom() {
        return String.valueOf(jsonObject.get(ROOM));
    }

    public String getDevicestatus() {
        return String.valueOf(jsonObject.get(DEVICESTATUS));
    }

    public String getModeset() {
        return String.valueOf(jsonObject.get(MODESET));
    }

    public String getWind() {
        return String.valueOf(jsonObject.get(WIND));
    }

    public String getTem() {
        return String.valueOf(jsonObject.get(TEM));
    }

    public String getRoomtem() {
        return String.valueOf(jsonObject.get(ROOMTEM));
    }

    public JsonParser getJsonParser() {
        return jsonParser;
    }

    public JsonObject getJsonObject() {
        return jsonObject;
    }

}
