package com.hisense.tvs;

import android.util.Log;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mediatek.twoworlds.tv.model.MtkTvRatingConvert2Goo;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class FacTvsTvManager {
    private FacTvsInterface mFacTvsInterface = FacTvsInterface.getInstance();

    public void presetChannel(int i) {
        ObjectNode json = this.mFacTvsInterface.getJson();
        json.put("channel", i);
        this.mFacTvsInterface.sendMessage("FACTORY_SetPresetChannelData", json);
    }

    public void startSignalQualityTest() {
        this.mFacTvsInterface.sendMessage("FACTORY_StartSignalQualityTestOfCurrentTuning", this.mFacTvsInterface.getJson());
    }

    public void stopSignalQualityTest() {
        this.mFacTvsInterface.sendMessage("FACTORY_StopSignalQualityTestOfCurrentTuning", this.mFacTvsInterface.getJson());
    }

    public String getSignalQualityTestInfo() {
        Object objSendMessage = this.mFacTvsInterface.sendMessage("FACTORY_GetSignalQualityTestInfo", this.mFacTvsInterface.getJson());
        if (objSendMessage == null) {
            return "";
        }
        String string = objSendMessage.toString();
        Log.i("FacTvsTvManager", "getSignalQualityTestInfo: resultStr =" + string);
        return string;
    }

    public void setTune(int i) {
        ObjectNode json = this.mFacTvsInterface.getJson();
        json.put("type", i);
        this.mFacTvsInterface.sendMessage("FACTORY_Tune", json);
    }

    public String getTvsStorageInfo() {
        ObjectNode json = this.mFacTvsInterface.getJson();
        json.put("type", CESIConstants.getValue("StorageType_e", MtkTvRatingConvert2Goo.RATING_STR_ALL));
        json.put("use", CESIConstants.getValue("StorageUse_e", "RECORDING"));
        json.put("withoutSize", false);
        json.put("filter", CESIConstants.getValue("StorageFilterType_e", MtkTvRatingConvert2Goo.RATING_STR_ALL));
        Object objSendMessage = this.mFacTvsInterface.sendMessage("STORAGE_List", json);
        String str = " ";
        if (objSendMessage != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode tree = null;
            try {
                try {
                    tree = objectMapper.readTree(objectMapper.writeValueAsString(objSendMessage));
                } catch (Exception e) {
                    Log.i("FacTvsTvManager", "readTree Exception e: " + e);
                }
            } catch (Exception e2) {
                Log.i("FacTvsTvManager", "writeValueAsString Exception e: " + e2);
            }
            if (tree != null && tree.get("stors") != null) {
                JsonNode jsonNode = tree.get("stors");
                if (jsonNode.isArray()) {
                    Iterator<JsonNode> it = jsonNode.iterator();
                    while (it.hasNext()) {
                        JsonNode jsonNode2 = it.next().get("size");
                        if (jsonNode2 != null) {
                            str = str + ConventHDDData(jsonNode2.asDouble()) + "\n";
                        }
                    }
                }
            }
            Log.i("FacTvsTvManager", "getTvsStorageInfo, resultStr is " + str);
        }
        return str;
    }

    private String ConventHDDData(double d) {
        Log.i("FacTvsTvManager", "storsResult Double =" + d);
        long jLongValue = new Double(d).longValue();
        Log.i("FacTvsTvManager", "storsResult Long =" + jLongValue);
        long j = jLongValue >> 20;
        Log.i("FacTvsTvManager", "storsResult MB =" + j);
        String upperCase = Long.toHexString(j).toUpperCase();
        Log.i("FacTvsTvManager", "storsResult length " + upperCase.length());
        if (upperCase.length() <= 8) {
            Log.i("FacTvsTvManager", "storsResult length < 8,add 0");
            return addZeroForNum(upperCase, 8);
        }
        return upperCase.substring(0, 8);
    }

    private String addZeroForNum(String str, int i) {
        int length = str.length();
        if (length < i) {
            while (length < i) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("0");
                stringBuffer.append(str);
                str = stringBuffer.toString();
                length = str.length();
            }
        }
        return str;
    }

    public int getAntennaPowerMode() throws JsonProcessingException {
        ObjectNode json = this.mFacTvsInterface.getJson();
        Object objSendMessage = this.mFacTvsInterface.sendMessage("TUNER_GetAntennaPowerMode", json);
        if (objSendMessage == null) {
            return -2;
        }
        int iAsInt = json.get("result").asInt();
        Log.i("FacTvsTvManager", "getAntennaPowerMode ret is " + iAsInt + "\n");
        if (iAsInt != 0) {
            return -1;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode tree = null;
        try {
            try {
                tree = objectMapper.readTree(objectMapper.writeValueAsString(objSendMessage));
            } catch (Exception e) {
                Log.i("FacTvsTvManager", "readTree Exception e: " + e + "\n");
            }
        } catch (Exception e2) {
            Log.i("FacTvsTvManager", "writeValueAsString Exception e: " + e2 + "\n");
        }
        int iAsInt2 = tree.get("mode").asInt();
        Log.i("FacTvsTvManager", "getAntennaPowerMode, mode is " + iAsInt2 + "\n");
        return iAsInt2;
    }

    public int setAntennaPowerMode(int i) {
        ObjectNode json = this.mFacTvsInterface.getJson();
        json.put("mode", i);
        if (this.mFacTvsInterface.sendMessage("TUNER_SetAntennaPowerMode", json) == null) {
            return -2;
        }
        int iAsInt = json.get("result").asInt();
        Log.i("FacTvsTvManager", "setAntennaPowerMode, mode is " + i + ", ret is " + iAsInt + "\n");
        return iAsInt;
    }

    public String getCurrentChannelTuner() {
        JsonNode tree;
        Object objSendMessage = this.mFacTvsInterface.sendMessage("TUNER_CurrentChannel", this.mFacTvsInterface.getJson());
        String strAsText = "";
        if (objSendMessage != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                try {
                    tree = objectMapper.readTree(objectMapper.writeValueAsString(objSendMessage));
                } catch (Exception e) {
                    Log.i("FacTvsTvManager", "readTree Exception e: " + e);
                    tree = null;
                }
            } catch (Exception e2) {
                Log.i("FacTvsTvManager", "writeValueAsString Exception e: " + e2);
            }
            if (tree != null) {
                JsonNode jsonNode = tree.get("channel") != null ? tree.get("channel").get("bcname") : null;
                if (jsonNode != null) {
                    strAsText = jsonNode.asText("");
                }
            }
            Log.i("FacTvsTvManager", "getCurrentChannelTuner, resultStr is " + strAsText);
        }
        return strAsText;
    }

    public String getACASId() {
        JsonNode tree;
        ObjectNode json = this.mFacTvsInterface.getJson();
        json.put("index", 0);
        Object objSendMessage = this.mFacTvsInterface.sendMessage("TUNER_AcasInfo", json);
        String strAsText = "null";
        if (objSendMessage != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                try {
                    tree = objectMapper.readTree(objectMapper.writeValueAsString(objSendMessage));
                } catch (Exception e) {
                    Log.i("FacTvsTvManager", "readTree Exception e: " + e);
                    tree = null;
                }
            } catch (Exception e2) {
                Log.i("FacTvsTvManager", "writeValueAsString Exception e: " + e2);
            }
            if (tree != null) {
                JsonNode jsonNode = tree.get("info") != null ? tree.get("info").get("id") : null;
                if (jsonNode != null) {
                    strAsText = jsonNode.asText("");
                }
            }
            Log.i("FacTvsTvManager", "getACASInfo, resultStr is " + strAsText);
        }
        return strAsText;
    }

    public int setTvDisabled(boolean z) {
        ObjectNode json = this.mFacTvsInterface.getJson();
        json.put("disable", z);
        if (this.mFacTvsInterface.sendMessage("STATECONTROL_SetTVDisabled", json) == null) {
            return -2;
        }
        int iAsInt = json.get("result").asInt();
        Log.i("FacTvsTvManager", "setTvDisabled");
        return iAsInt;
    }

    public int setExternelInput(int i, int i2) {
        ObjectNode json = this.mFacTvsInterface.getJson();
        ObjectNode json2 = this.mFacTvsInterface.getJson();
        json.put("type", i);
        json.put("input", i2);
        json2.put("input", json);
        json2.put("playerId", 0);
        if (this.mFacTvsInterface.sendMessage("TUNER_SelectExternalInput", json2) == null) {
            return -2;
        }
        int iAsInt = json2.get("result").asInt();
        Log.i("FacTvsTvManager", "setExternelInput");
        return iAsInt;
    }

    public int getACASState() throws JsonProcessingException {
        Object objSendMessage = this.mFacTvsInterface.sendMessage("TUNER_AcasStates", this.mFacTvsInterface.getJson());
        int iAsInt = 5;
        if (objSendMessage != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode tree = null;
            try {
                try {
                    tree = objectMapper.readTree(objectMapper.writeValueAsString(objSendMessage));
                } catch (Exception e) {
                    Log.i("FacTvsTvManager", "readTree Exception e: " + e);
                }
            } catch (Exception e2) {
                Log.i("FacTvsTvManager", "writeValueAsString Exception e: " + e2);
            }
            Log.i("FacTvsTvManager", "getCurrentChannelTuner, jsonNode is " + tree);
            if (tree != null) {
                JsonNode jsonNode = tree.get("states");
                Log.i("FacTvsTvManager", "getCurrentChannelTuner, stateArray is " + jsonNode);
                if (jsonNode != null && jsonNode.isArray()) {
                    Iterator<JsonNode> it = jsonNode.iterator();
                    while (it.hasNext()) {
                        iAsInt = it.next().asInt();
                    }
                }
            }
            Log.i("FacTvsTvManager", "getCurrentChannelTuner, result is " + iAsInt);
        }
        return iAsInt;
    }

    public int getCurrentChannelNumber() {
        JsonNode tree;
        Object objSendMessage = this.mFacTvsInterface.sendMessage("TUNER_CurrentChannel", this.mFacTvsInterface.getJson());
        int iAsInt = 1;
        if (objSendMessage != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                try {
                    tree = objectMapper.readTree(objectMapper.writeValueAsString(objSendMessage));
                } catch (Exception e) {
                    Log.i("FacTvsTvManager", "readTree Exception e: " + e);
                    tree = null;
                }
            } catch (Exception e2) {
                Log.i("FacTvsTvManager", "writeValueAsString Exception e: " + e2);
            }
            if (tree != null) {
                JsonNode jsonNode = tree.get("channel") != null ? tree.get("channel").get("logicalnum") : null;
                if (jsonNode != null) {
                    iAsInt = jsonNode.asInt();
                }
            }
            Log.i("FacTvsTvManager", "getCurrentChannelNumber, result is " + iAsInt);
        }
        return iAsInt;
    }
}
