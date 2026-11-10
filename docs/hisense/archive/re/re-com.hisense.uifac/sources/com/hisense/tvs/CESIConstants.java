package com.hisense.tvs;

import android.util.Log;
import com.fasterxml.jackson.databind.JsonNode;

/* JADX INFO: loaded from: classes.dex */
public class CESIConstants {
    public static void initData() {
        FacTvsInterface.getInstance();
    }

    public static int getValue(String str, String str2) {
        JsonNode constants = FacTvsInterface.getInstance().getConstants();
        if (constants == null) {
            Log.e("CESIConstants", "constantsJson is null, CesiApi is not connect");
            return -1;
        }
        JsonNode jsonNode = constants.get(str);
        if (jsonNode == null) {
            Log.e("CESIConstants", str + " no exit");
            return -1;
        }
        JsonNode jsonNode2 = jsonNode.get(str2);
        if (jsonNode2 == null) {
            Log.e("CESIConstants", str2 + " no exit");
            return -1;
        }
        int iAsInt = jsonNode2.asInt(-1);
        Log.d("CESIConstants", "getValue = " + str2 + " : " + iAsInt);
        return iAsInt;
    }
}
