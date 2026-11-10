package com.hisense.tvs;

import android.util.Log;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/* JADX INFO: loaded from: classes.dex */
public class FacTvsAqPqManager {
    private FacTvsInterface mFacTvsInterface = FacTvsInterface.getInstance();

    public String getLightSensor() throws JsonProcessingException {
        Object objSendMessage = this.mFacTvsInterface.sendMessage("FACTORY_GetLightSensor", this.mFacTvsInterface.getJson());
        String str = "";
        if (objSendMessage == null) {
            return "";
        }
        Log.i("FacTvsAqPqManager", "getLightSensor ret is " + objSendMessage + "\n");
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode tree = null;
        try {
            try {
                tree = objectMapper.readTree(objectMapper.writeValueAsString(objSendMessage));
            } catch (Exception e) {
                Log.i("FacTvsAqPqManager", "readTree Exception e: " + e + "\n");
            }
        } catch (Exception e2) {
            Log.i("FacTvsAqPqManager", "writeValueAsString Exception e: " + e2 + "\n");
        }
        Log.i("FacTvsAqPqManager", "getLightSensor jsonNode is " + tree + "\n");
        if (tree.get("type").asInt() == CESIConstants.getValue("FactorySensorHwType_e", "RGB")) {
            str = ((("Red:" + tree.get("red").asInt() + ";") + "Green:" + tree.get("green").asInt() + ";") + "Blue:" + tree.get("blue").asInt() + ";") + "White:" + tree.get("white").asInt() + ";";
        }
        Log.i("FacTvsAqPqManager", "getLightSensor data is " + str + "\n");
        return str;
    }

    public int getLightSensorForSerial(int i) throws JsonProcessingException {
        Object objSendMessage = this.mFacTvsInterface.sendMessage("FACTORY_GetLightSensor", this.mFacTvsInterface.getJson());
        int i2 = 0;
        if (objSendMessage == null) {
            return 0;
        }
        Log.i("FacTvsAqPqManager", "getLightSensorForSerial ret is " + objSendMessage + "\n");
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode tree = null;
        try {
            try {
                tree = objectMapper.readTree(objectMapper.writeValueAsString(objSendMessage));
            } catch (Exception e) {
                Log.i("FacTvsAqPqManager", "readTree Exception e: " + e + "\n");
            }
        } catch (Exception e2) {
            Log.i("FacTvsAqPqManager", "writeValueAsString Exception e: " + e2 + "\n");
        }
        Log.i("FacTvsAqPqManager", "getLightSensorForSerial jsonNode is " + tree + "\n");
        if (tree.get("type").asInt() == CESIConstants.getValue("FactorySensorHwType_e", "RGB")) {
            int iAsInt = tree.get("red").asInt();
            int iAsInt2 = tree.get("green").asInt();
            int iAsInt3 = tree.get("blue").asInt();
            tree.get("white").asInt();
            if (i == 1) {
                i2 = iAsInt;
            } else if (i == 2) {
                i2 = iAsInt2;
            } else if (i == 3) {
                i2 = iAsInt3;
            }
        }
        Log.i("FacTvsAqPqManager", "getLightSensorSerial data is " + i2 + "\n");
        return i2;
    }

    public int setTvsTPC(int i) {
        JsonNode jsonNode;
        ObjectNode json = this.mFacTvsInterface.getJson();
        json.withArray("props").add(CESIConstants.getValue("Property_e", "LTPC"));
        Object objSendMessage = this.mFacTvsInterface.sendMessage("SET_GetProperties", json);
        Log.i("FacTvsAqPqManager", "setTvsTPC, result is " + objSendMessage);
        ObjectNode json2 = this.mFacTvsInterface.getJson();
        int i2 = 0;
        if (objSendMessage != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode tree = null;
            try {
                try {
                    tree = objectMapper.readTree(objectMapper.writeValueAsString(objSendMessage));
                    Log.i("FacTvsAqPqManager", "setTvsTPC, node1 is " + json2);
                } catch (Exception e) {
                    Log.i("FacTvsAqPqManager", "setTvsTPC Exception e: " + e);
                }
            } catch (Exception e2) {
                Log.i("FacTvsAqPqManager", "setTvsTPC Exception e: " + e2);
            }
            if (tree != null && tree.get("values") != null) {
                JsonNode jsonNode2 = tree.get("values");
                if (jsonNode2.isArray()) {
                    for (JsonNode jsonNode3 : jsonNode2) {
                        JsonNode jsonNode4 = jsonNode3.get("prop");
                        Log.i("FacTvsAqPqManager", "setTvsTPC, propNode is " + jsonNode4);
                        if (jsonNode4 != null) {
                            int iAsInt = jsonNode4.asInt();
                            Log.i("FacTvsAqPqManager", "setTvsTPC, prop is " + iAsInt);
                            if (iAsInt == CESIConstants.getValue("Property_e", "LTPC") && (jsonNode = jsonNode3.get("val")) != null) {
                                Log.i("FacTvsAqPqManager", "setTvsTPC, resultData is " + jsonNode.asInt());
                                try {
                                    ObjectNode objectNode = (ObjectNode) jsonNode3.deepCopy();
                                    objectNode.put("val", i);
                                    Log.i("FacTvsAqPqManager", "setTvsTPC, node1 is " + objectNode);
                                    json2.withArray("props").add(objectNode);
                                } catch (Exception e3) {
                                    Log.i("FacTvsAqPqManager", "setTvsTPC Exception e: " + e3);
                                }
                                i2 = i;
                            }
                        }
                    }
                }
                Log.i("FacTvsAqPqManager", "setTvsTPC, node is " + json2);
                this.mFacTvsInterface.sendMessage("SET_SetProperties", json2);
            }
        }
        return i2;
    }

    public int getTvsTPC() throws JsonProcessingException {
        JsonNode jsonNode;
        ObjectNode json = this.mFacTvsInterface.getJson();
        json.withArray("props").add(CESIConstants.getValue("Property_e", "LTPC"));
        Object objSendMessage = this.mFacTvsInterface.sendMessage("SET_GetProperties", json);
        Log.i("FacTvsAqPqManager", "getTvsTPC, result is " + objSendMessage);
        int iAsInt = 0;
        if (objSendMessage != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode tree = null;
            try {
                try {
                    tree = objectMapper.readTree(objectMapper.writeValueAsString(objSendMessage));
                } catch (Exception e) {
                    Log.i("FacTvsAqPqManager", "getTvsTPC Exception e: " + e);
                }
            } catch (Exception e2) {
                Log.i("FacTvsAqPqManager", "getTvsTPC Exception e: " + e2);
            }
            if (tree != null && tree.get("values") != null) {
                JsonNode jsonNode2 = tree.get("values");
                if (jsonNode2.isArray()) {
                    for (JsonNode jsonNode3 : jsonNode2) {
                        JsonNode jsonNode4 = jsonNode3.get("prop");
                        Log.i("FacTvsAqPqManager", "getTvsTPC, propNode is " + jsonNode4);
                        if (jsonNode4 != null) {
                            int iAsInt2 = jsonNode4.asInt();
                            Log.i("FacTvsAqPqManager", "getTvsTPC, prop is " + iAsInt2);
                            if (iAsInt2 == CESIConstants.getValue("Property_e", "LTPC") && (jsonNode = jsonNode3.get("val")) != null) {
                                iAsInt = jsonNode.asInt();
                                Log.i("FacTvsAqPqManager", "getTvsTPC, resultData is " + iAsInt);
                            }
                        }
                    }
                }
            }
        }
        return iAsInt;
    }
}
