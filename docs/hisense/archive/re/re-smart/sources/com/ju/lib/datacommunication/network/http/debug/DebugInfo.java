package com.ju.lib.datacommunication.network.http.debug;

import b.a.a.a.a;
import com.ju.lib.datacommunication.network.http.utils.HttpLog;

/* JADX INFO: loaded from: classes2.dex */
public class DebugInfo {
    public String content;
    public DebugCheckType debugCheckType;

    /* JADX INFO: renamed from: com.ju.lib.datacommunication.network.http.debug.DebugInfo$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$ju$lib$datacommunication$network$http$debug$DebugCheckType = new int[DebugCheckType.values().length];

        static {
            try {
                $SwitchMap$com$ju$lib$datacommunication$network$http$debug$DebugCheckType[DebugCheckType.Param.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$ju$lib$datacommunication$network$http$debug$DebugCheckType[DebugCheckType.RespBody.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$ju$lib$datacommunication$network$http$debug$DebugCheckType[DebugCheckType.RespCode.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$ju$lib$datacommunication$network$http$debug$DebugCheckType[DebugCheckType.Url.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public DebugInfo(DebugCheckType debugCheckType, String str) {
        this.content = "";
        this.debugCheckType = debugCheckType;
        this.content = str == null ? "" : str;
    }

    public String getUnDuplicateData(DebugTrace debugTrace) {
        String string = debugTrace.toString();
        if (this.content.contains(string)) {
            HttpLog.i("DebugHelper", " has the same stackTrace, do not save ");
            return null;
        }
        int iOrdinal = this.debugCheckType.ordinal();
        if (iOrdinal == 0) {
            StringBuilder sbB = a.b(string, "params: ");
            sbB.append(debugTrace.requestParams.toString());
            sbB.append("\n");
            string = sbB.toString();
        } else if (iOrdinal == 2 || iOrdinal == 3) {
            StringBuilder sbB2 = a.b(string, "params: ");
            sbB2.append(debugTrace.requestParams.toString());
            sbB2.append("\n");
            string = a.a(a.b(a.a(a.b(sbB2.toString(), "respCode:"), debugTrace.respCode, "\n"), "respBody:"), debugTrace.respBody, "\n");
        }
        this.content = a.a(new StringBuilder(), this.content, string);
        return string;
    }
}
