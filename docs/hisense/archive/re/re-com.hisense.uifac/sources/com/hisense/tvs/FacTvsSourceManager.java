package com.hisense.tvs;

import android.util.Log;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/* JADX INFO: loaded from: classes.dex */
public class FacTvsSourceManager {
    private FacTvsInterface mFacTvsInterface = FacTvsInterface.getInstance();

    public int getLastSource() throws JsonProcessingException {
        JsonNode jsonNode;
        Object objSendMessage = this.mFacTvsInterface.sendMessage("TUNER_LastSource", this.mFacTvsInterface.getJson());
        int iAsInt = 5;
        if (objSendMessage != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode tree = null;
            try {
                try {
                    tree = objectMapper.readTree(objectMapper.writeValueAsString(objSendMessage));
                } catch (Exception e) {
                    Log.i("FacTvsSourceManager", "readTree Exception e: " + e);
                }
            } catch (Exception e2) {
                Log.i("FacTvsSourceManager", "writeValueAsString Exception e: " + e2);
            }
            Log.i("FacTvsSourceManager", "getLastSource, jsonNode is " + tree);
            if (tree != null && (jsonNode = tree.get("source")) != null) {
                iAsInt = jsonNode.asInt();
            }
            Log.i("FacTvsSourceManager", "getLastSource, result is " + iAsInt);
        }
        return iAsInt;
    }
}
