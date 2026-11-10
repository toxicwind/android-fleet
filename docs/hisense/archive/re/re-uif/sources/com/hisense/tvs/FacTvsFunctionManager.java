package com.hisense.tvs;

import android.util.Log;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/* JADX INFO: loaded from: classes.dex */
public class FacTvsFunctionManager {
    private FacTvsInterface mFacTvsInterface = FacTvsInterface.getInstance();

    public int cleanTvsProtect() {
        ObjectNode json = this.mFacTvsInterface.getJson();
        if (this.mFacTvsInterface.sendMessage("FACTORY_InitAll", json) == null) {
            return -2;
        }
        int iAsInt = json.get("result").asInt();
        Log.i("FacTvsFunctionManager", "cleanTvsProtect, ret is " + iAsInt + "\n");
        return iAsInt;
    }

    public int setTvsFactoryOk() {
        ObjectNode json = this.mFacTvsInterface.getJson();
        if (this.mFacTvsInterface.sendMessage("FACTORY_InitShipout", json) == null) {
            return -2;
        }
        int iAsInt = json.get("result").asInt();
        Log.i("FacTvsFunctionManager", "setTvsFactoryOk, ret is " + iAsInt + "\n");
        return iAsInt;
    }

    public int setTvsFactoryMode(boolean z) {
        ObjectNode json = this.mFacTvsInterface.getJson();
        json.put("enabled", z);
        if (this.mFacTvsInterface.sendMessage("FACTORY_SetFactoryModeEnabled", json) == null) {
            return -2;
        }
        int iAsInt = json.get("result").asInt();
        Log.i("FacTvsFunctionManager", "setTvsFactoryMode, status is " + z + ", ret is " + iAsInt + "\n");
        return iAsInt;
    }

    public int setTvsWiredMacAddr(String str) {
        ObjectNode json = this.mFacTvsInterface.getJson();
        json.put("mac", str);
        if (this.mFacTvsInterface.sendMessage("NIC_SetWiredMacAddress", json) == null) {
            return -2;
        }
        int iAsInt = json.get("result").asInt();
        Log.i("FacTvsFunctionManager", "setTvsWiredMacAddr, ret is " + iAsInt + "\n");
        return iAsInt;
    }

    public int resetTvsUsbOverCurrent(int i) {
        ObjectNode json = this.mFacTvsInterface.getJson();
        json.put("port", i);
        if (this.mFacTvsInterface.sendMessage("FACTORY_ResetUsbOverCurrent", json) == null) {
            return -2;
        }
        int iAsInt = json.get("result").asInt();
        Log.i("FacTvsFunctionManager", "resetTvsUsbOverCurrent, ret is " + iAsInt + "\n");
        return iAsInt;
    }

    public void setTvsOutputSpeaker(int i, int i2) {
        ObjectNode json = this.mFacTvsInterface.getJson();
        json.put("speaker", i);
        json.put("balance", i2);
        this.mFacTvsInterface.sendMessage("FACTORY_SetOutputSpeaker", json);
    }

    public int[] getTvsOutputSpeaker() {
        Object objSendMessage = this.mFacTvsInterface.sendMessage("FACTORY_LastOutputSpeaker", this.mFacTvsInterface.getJson());
        int[] iArr = new int[2];
        if (objSendMessage != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode tree = null;
            try {
                try {
                    tree = objectMapper.readTree(objectMapper.writeValueAsString(objSendMessage));
                } catch (Exception e) {
                    Log.i("FacTvsFunctionManager", "readTree Exception e: " + e);
                }
            } catch (Exception e2) {
                Log.i("FacTvsFunctionManager", "writeValueAsString Exception e: " + e2);
            }
            if (tree != null) {
                JsonNode jsonNode = tree.get("speaker");
                JsonNode jsonNode2 = tree.get("balance");
                Log.i("FacTvsFunctionManager", "getTvsOutputSpeaker, speaker is " + jsonNode + " ; balance is " + jsonNode2);
                if (jsonNode != null && jsonNode2 != null) {
                    iArr[0] = jsonNode.asInt();
                    iArr[1] = jsonNode2.asInt();
                }
            }
        }
        return iArr;
    }

    public void setTVSLog(String str) {
        ObjectNode json = this.mFacTvsInterface.getJson();
        json.put("path", str);
        this.mFacTvsInterface.sendMessage("FACTORY_StartDbgLogFile", json);
    }

    public int setSavingMode(int i) {
        JsonNode jsonNode;
        int i2 = FacTvsFunctionUtil.TVS_SAVING_MODE_STANDARD;
        ObjectNode json = this.mFacTvsInterface.getJson();
        ArrayNode arrayNodeWithArray = json.withArray("props");
        int value = CESIConstants.getValue("Property_e", "POWERSAVE_MODE");
        Log.i("FacTvsFunctionManager", "getSavingMode, tvsSaveModeType is " + value);
        arrayNodeWithArray.add(value);
        Object objSendMessage = this.mFacTvsInterface.sendMessage("SET_GetProperties", json);
        Log.i("FacTvsFunctionManager", "setSavingMode, result is " + objSendMessage);
        ObjectNode json2 = this.mFacTvsInterface.getJson();
        if (objSendMessage != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode tree = null;
            try {
                try {
                    tree = objectMapper.readTree(objectMapper.writeValueAsString(objSendMessage));
                    Log.i("FacTvsFunctionManager", "setSavingMode, node1 is " + json2);
                } catch (Exception e) {
                    Log.i("FacTvsFunctionManager", "setSavingMode Exception e: " + e);
                }
            } catch (Exception e2) {
                Log.i("FacTvsFunctionManager", "setSavingMode Exception e: " + e2);
            }
            if (tree != null && tree.get("values") != null) {
                JsonNode jsonNode2 = tree.get("values");
                if (jsonNode2.isArray()) {
                    for (JsonNode jsonNode3 : jsonNode2) {
                        JsonNode jsonNode4 = jsonNode3.get("prop");
                        Log.i("FacTvsFunctionManager", "setSavingMode, propNode is " + jsonNode4);
                        if (jsonNode4 != null) {
                            int iAsInt = jsonNode4.asInt();
                            Log.i("FacTvsFunctionManager", "setSavingMode, prop is " + iAsInt);
                            if (iAsInt == value && (jsonNode = jsonNode3.get("val")) != null) {
                                int iAsInt2 = jsonNode.asInt();
                                Log.i("FacTvsFunctionManager", "setSavingMode, resultData is " + iAsInt2);
                                if (i != 255) {
                                    i2 = i;
                                } else if (iAsInt2 == FacTvsFunctionUtil.TVS_SAVING_MODE_1) {
                                    i2 = FacTvsFunctionUtil.TVS_SAVING_MODE_2;
                                } else if (iAsInt2 == FacTvsFunctionUtil.TVS_SAVING_MODE_2) {
                                    i2 = FacTvsFunctionUtil.TVS_SAVING_MODE_STANDARD;
                                } else if (iAsInt2 == FacTvsFunctionUtil.TVS_SAVING_MODE_STANDARD) {
                                    i2 = FacTvsFunctionUtil.TVS_SAVING_MODE_1;
                                }
                                try {
                                    ObjectNode objectNode = (ObjectNode) jsonNode3.deepCopy();
                                    objectNode.put("val", i2);
                                    Log.i("FacTvsFunctionManager", "setSavingMode, node1 is " + objectNode);
                                    json2.withArray("props").add(objectNode);
                                } catch (Exception e3) {
                                    Log.i("FacTvsFunctionManager", "setSavingMode Exception e: " + e3);
                                }
                            }
                        }
                    }
                }
                Log.i("FacTvsFunctionManager", "setSavingMode, node is " + json2);
                this.mFacTvsInterface.sendMessage("SET_SetProperties", json2);
            }
        }
        return i2;
    }

    public int setStartApplication() {
        ObjectNode json = this.mFacTvsInterface.getJson();
        json.put("app", CESIConstants.getValue("Application_e", "FACTORY"));
        json.put("param", "");
        if (this.mFacTvsInterface.sendMessage("APP_StartApplication", json) == null) {
            return -2;
        }
        int iAsInt = json.get("result").asInt();
        Log.i("FacTvsFunctionManager", "setStartApplication,ret is " + iAsInt + "\n");
        return iAsInt;
    }
}
