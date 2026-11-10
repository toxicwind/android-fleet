package com.mediatek.twoworlds.tv.model;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvVideoInfoBase {
    public static final int SCC_HDMI_COLORIMETRY_ADOBE_RGB = 4;
    public static final int SCC_HDMI_COLORIMETRY_ADOBE_YCC601 = 3;
    public static final int SCC_HDMI_COLORIMETRY_BT2020_RGB_OR_YCBCR = 6;
    public static final int SCC_HDMI_COLORIMETRY_BT2020_YCBCR = 5;
    public static final int SCC_HDMI_COLORIMETRY_RESERVED = 7;
    public static final int SCC_HDMI_COLORIMETRY_SYCC601 = 2;
    public static final int SCC_HDMI_COLORIMETRY_XVYCC601 = 0;
    public static final int SCC_HDMI_COLORIMETRY_XVYCC709 = 1;
    public static final int SCC_HDMI_COLOR_FORMAT_RGB = 0;
    public static final int SCC_HDMI_COLOR_FORMAT_YCBCR_420 = 3;
    public static final int SCC_HDMI_COLOR_FORMAT_YCBCR_422 = 1;
    public static final int SCC_HDMI_COLOR_FORMAT_YCBCR_444 = 2;
    public static final int SCC_HDMI_COLOR_FORMAT_YCBCR_RESERVED = 4;
    private static final String TAG = "MtkTvVideoInfoBase";
    public static final int TV_DEC_DVI = 4;
    public static final int TV_DEC_MAX = 7;
    public static final int TV_DEC_MPEGHD = 5;
    public static final int TV_DEC_MPEGSD = 6;
    public static final int TV_DEC_NA = 0;
    public static final int TV_DEC_NOSIGNAL = 0;
    public static final int TV_DEC_NOSUPPORT = 1;
    public static final int TV_DEC_STABLE = 3;
    public static final int TV_DEC_TVD3D = 1;
    public static final int TV_DEC_UNKNOWN = 2;
    public static final int TV_DEC_VGA = 3;
    public static final int TV_DEC_YPBPR = 2;
    public static final int TV_TTYPE_NOPCMODE = 0;
    public static final int TV_TTYPE_PCMODE = 1;
    public static final int VIDEOINFO_HDR_COND_CHG = 0;
    public static final int VIDEOINFO_HDR_COND_ERROR = -1;
    public static final int VIDEOINFO_HDR_TYPE_DOVI = 3;
    public static final int VIDEOINFO_HDR_TYPE_HDR10 = 1;
    public static final int VIDEOINFO_HDR_TYPE_HDR10PLUS = 5;
    public static final int VIDEOINFO_HDR_TYPE_HLG = 2;
    public static final int VIDEOINFO_HDR_TYPE_SDR = 0;
    public static final int VIDEOINFO_HDR_TYPE_TECHNI = 4;
    public static final int VIDEOINFO_NFY_TYPE_DISP_REGION = 2;
    public static final int VIDEOINFO_NFY_TYPE_HDMI_COLOR_INFO_CHG = 4;
    public static final int VIDEOINFO_NFY_TYPE_HDR = 1;
    public static final int VIDEOINFO_NFY_TYPE_TVD_SRC_CHG = 3;
    private int eDataRange;
    private int eHistogramType;
    private int u16HistBinNum;
    private int u16HistBinNum_1;
    private int u16HueHistBinNum;
    private int u16LumaHistBinNum;
    private int u16SatByHueHistBinNum;
    private int u16SatByHueWinNum;
    private int u16SatByHueWinNum_1;
    private int u16SatByHueWinSliceNum;
    private int u16SatHistBinNum;
    private int u32AvgPixelLuma;
    private int u32MaxPixelLuma;
    private int u32MinPixelLuma;
    private int[] pu16HueWinStart = new int[6];
    private int[] pu16HueWinEnd = new int[6];
    private int[] pu32Histogram = new int[32];
    private ArrayList<int[]> ppu32SatByHueHistogram = new ArrayList<>();

    public MtkTvVideoInfoBase() {
        int i = 0;
        while (true) {
            int[] iArr = this.pu16HueWinStart;
            if (i >= iArr.length) {
                break;
            }
            iArr[i] = 0;
            i++;
        }
        int i2 = 0;
        while (true) {
            int[] iArr2 = this.pu16HueWinEnd;
            if (i2 >= iArr2.length) {
                break;
            }
            iArr2[i2] = 0;
            i2++;
        }
        int i3 = 0;
        while (true) {
            int[] iArr3 = this.pu32Histogram;
            if (i3 < iArr3.length) {
                iArr3[i3] = 0;
                i3++;
            } else {
                this.ppu32SatByHueHistogram.clear();
                return;
            }
        }
    }

    public int[] getPu16HueWinEnd() {
        return this.pu16HueWinEnd;
    }

    public void setPu16HueWinEnd(int[] iArr) {
        this.pu16HueWinEnd = iArr;
    }

    public int[] getPu16HueWinStart() {
        return this.pu16HueWinStart;
    }

    public void setPu16HueWinStart(int[] iArr) {
        this.pu16HueWinStart = iArr;
    }

    public int getU16SatByHueWinNum() {
        return this.u16SatByHueWinNum;
    }

    public void setU16SatByHueWinNum(int i) {
        this.u16SatByHueWinNum = i;
    }

    public int getU16SatByHueWinNum_1() {
        return this.u16SatByHueWinNum_1;
    }

    public void setU16SatByHueWinNum_1(int i) {
        this.u16SatByHueWinNum_1 = i;
    }

    public int getEHistogramType() {
        return this.eHistogramType;
    }

    public void setEHistogramType(int i) {
        this.eHistogramType = i;
    }

    public int getU16HistBinNum() {
        return this.u16HistBinNum;
    }

    public void setU16HistBinNum(int i) {
        this.u16HistBinNum = i;
    }

    public int getU16HistBinNum_1() {
        return this.u16HistBinNum_1;
    }

    public void setU16HistBinNum_1(int i) {
        this.u16HistBinNum_1 = i;
    }

    public int[] getPu32Histogram() {
        return this.pu32Histogram;
    }

    public void setPu32Histogram(int[] iArr) {
        this.pu32Histogram = iArr;
    }

    public int getU16LumaHistBinNum() {
        return this.u16LumaHistBinNum;
    }

    public void setU16LumaHistBinNum(int i) {
        this.u16LumaHistBinNum = i;
    }

    public int getU16SatHistBinNum() {
        return this.u16SatHistBinNum;
    }

    public void setU16SatHistBinNum(int i) {
        this.u16SatHistBinNum = i;
    }

    public int getU16HueHistBinNum() {
        return this.u16HueHistBinNum;
    }

    public void setU16HueHistBinNum(int i) {
        this.u16HueHistBinNum = i;
    }

    public int getU16SatByHueHistBinNum() {
        return this.u16SatByHueHistBinNum;
    }

    public void setU16SatByHueHistBinNum(int i) {
        this.u16SatByHueHistBinNum = i;
    }

    public int getU16SatByHueWinSliceNum() {
        return this.u16SatByHueWinSliceNum;
    }

    public void setU16SatByHueWinSliceNum(int i) {
        this.u16SatByHueWinSliceNum = i;
    }

    public int getEDataRange() {
        return this.eDataRange;
    }

    public void setEDataRange(int i) {
        this.eDataRange = i;
    }

    public int getU32MinPixelLuma() {
        return this.u32MinPixelLuma;
    }

    public void setU32MinPixelLuma(int i) {
        this.u32MinPixelLuma = i;
    }

    public int getU32MaxPixelLuma() {
        return this.u32MaxPixelLuma;
    }

    public void setU32MaxPixelLuma(int i) {
        this.u32MaxPixelLuma = i;
    }

    public int getU32AvgPixelLuma() {
        return this.u32AvgPixelLuma;
    }

    public void setU32AvgPixelLuma(int i) {
        this.u32AvgPixelLuma = i;
    }

    public ArrayList<int[]> getPpu32SatByHueHistogram() {
        return this.ppu32SatByHueHistogram;
    }

    public void setPpu32SatByHueHistogram(ArrayList<int[]> arrayList) {
        this.ppu32SatByHueHistogram = (ArrayList) arrayList.clone();
    }
}
