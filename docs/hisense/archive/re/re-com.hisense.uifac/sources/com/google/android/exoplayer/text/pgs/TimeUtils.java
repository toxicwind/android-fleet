package com.google.android.exoplayer.text.pgs;

import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public class TimeUtils {
    static {
        Pattern.compile("(\\d+):(\\d+):(\\d+)[:\\.](\\d+)");
    }

    public static String ptsToTimeStr(long j) {
        int[] iArrPtsToTime = ptsToTime(j);
        return ToolBox.leftZeroPad(iArrPtsToTime[0], 2) + ":" + ToolBox.leftZeroPad(iArrPtsToTime[1], 2) + ":" + ToolBox.leftZeroPad(iArrPtsToTime[2], 2) + "." + ToolBox.leftZeroPad(iArrPtsToTime[3], 3);
    }

    private static int[] ptsToTime(long j) {
        return msToTime((j + 45) / 90);
    }

    private static int[] msToTime(long j) {
        int[] iArr = new int[4];
        iArr[0] = (int) (j / 3600000);
        long j2 = j - (((((long) iArr[0]) * 60) * 60) * 1000);
        iArr[1] = (int) (j2 / 60000);
        long j3 = j2 - ((((long) iArr[1]) * 60) * 1000);
        iArr[2] = (int) (j3 / 1000);
        iArr[3] = (int) (j3 - (((long) iArr[2]) * 1000));
        return iArr;
    }
}
