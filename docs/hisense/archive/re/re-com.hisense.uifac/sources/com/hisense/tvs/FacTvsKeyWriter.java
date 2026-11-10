package com.hisense.tvs;

import android.util.Log;
import com.fasterxml.jackson.databind.node.ObjectNode;

/* JADX INFO: loaded from: classes.dex */
public class FacTvsKeyWriter {
    private FacTvsInterface mFacTvsInterface = FacTvsInterface.getInstance();

    public int setTvsProjectId(String str) {
        ObjectNode json = this.mFacTvsInterface.getJson();
        json.put("name", str);
        if (this.mFacTvsInterface.sendMessage("FACTORY_SetProjectId", json) == null) {
            return -2;
        }
        int iAsInt = json.get("result").asInt();
        Log.i("FacTvsKeyWriter", "setTvsProjectId, pid is " + str + ", ret is " + iAsInt + "\n");
        return iAsInt;
    }

    public int writeTvsKey(int i, String str) {
        ObjectNode json = this.mFacTvsInterface.getJson();
        json.put("type", i);
        json.put("filepath", str);
        if (this.mFacTvsInterface.sendMessage("FACTORY_WriteKeyData", json) == null) {
            return -2;
        }
        int iAsInt = json.get("result").asInt();
        Log.i("FacTvsKeyWriter", "writeTvsKey, keyType is " + i + ", filepath is " + str + ", ret is " + iAsInt + "\n");
        return iAsInt;
    }
}
