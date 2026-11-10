package com.hisense.uiadapter;

import android.content.Context;
import android.util.Log;
import com.hisense.tvcm.FacFunction;
import com.hisense.tvcm.FacFunction_Adapter;
import com.hisense.tvcm.FacPropertyDemo;
import com.mediatek.twoworlds.tv.common.MtkTvSvctxNotifyCode;
import java.util.Hashtable;

/* JADX INFO: loaded from: classes.dex */
public class TVComponent {
    protected static Context mContext;
    private static FacFunction mFacFunction;
    private static TVComponent mTVComponent;
    private final TVCalculator mTVCalculator;
    private final Hashtable<String, TVOption<?>> optionTable = new Hashtable<>();

    class IntegerRangeProxyOption implements TVOption<Integer> {
        private final int max;
        private final int min;
        private final String name;

        public IntegerRangeProxyOption(String str, int i, int i2, int i3) {
            this.name = str;
            this.min = i2;
            this.max = i3;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v1695 */
        /* JADX WARN: Type inference failed for: r0v1696 */
        /* JADX WARN: Type inference failed for: r0v1697 */
        /* JADX WARN: Type inference failed for: r0v1698 */
        /* JADX WARN: Type inference failed for: r0v1699 */
        /* JADX WARN: Type inference failed for: r0v1700 */
        /* JADX WARN: Type inference failed for: r0v1701 */
        /* JADX WARN: Type inference failed for: r0v1702 */
        /* JADX WARN: Type inference failed for: r0v1703 */
        /* JADX WARN: Type inference failed for: r0v1704 */
        /* JADX WARN: Type inference failed for: r0v1705 */
        /* JADX WARN: Type inference failed for: r0v1706 */
        /* JADX WARN: Type inference failed for: r0v1707 */
        /* JADX WARN: Type inference failed for: r0v1708 */
        /* JADX WARN: Type inference failed for: r0v1709 */
        /* JADX WARN: Type inference failed for: r0v1710 */
        /* JADX WARN: Type inference failed for: r0v1711 */
        /* JADX WARN: Type inference failed for: r0v1712 */
        /* JADX WARN: Type inference failed for: r0v1713 */
        /* JADX WARN: Type inference failed for: r0v1714 */
        /* JADX WARN: Type inference failed for: r0v1715 */
        /* JADX WARN: Type inference failed for: r0v1716 */
        /* JADX WARN: Type inference failed for: r0v1717 */
        /* JADX WARN: Type inference failed for: r0v1718 */
        /* JADX WARN: Type inference failed for: r0v1719 */
        /* JADX WARN: Type inference failed for: r0v1720 */
        /* JADX WARN: Type inference failed for: r0v1721 */
        /* JADX WARN: Type inference failed for: r0v1722 */
        /* JADX WARN: Type inference failed for: r0v1723 */
        /* JADX WARN: Type inference failed for: r0v1724 */
        /* JADX WARN: Type inference failed for: r0v1725 */
        /* JADX WARN: Type inference failed for: r0v1726 */
        /* JADX WARN: Type inference failed for: r0v1727 */
        /* JADX WARN: Type inference failed for: r0v1728 */
        /* JADX WARN: Type inference failed for: r0v1729 */
        /* JADX WARN: Type inference failed for: r0v1730 */
        /* JADX WARN: Type inference failed for: r0v1731 */
        /* JADX WARN: Type inference failed for: r0v1732 */
        /* JADX WARN: Type inference failed for: r0v1733 */
        /* JADX WARN: Type inference failed for: r0v1734 */
        /* JADX WARN: Type inference failed for: r0v1735 */
        /* JADX WARN: Type inference failed for: r0v1736 */
        /* JADX WARN: Type inference failed for: r0v1737 */
        /* JADX WARN: Type inference failed for: r0v1738 */
        /* JADX WARN: Type inference failed for: r0v1739 */
        /* JADX WARN: Type inference failed for: r0v1740 */
        /* JADX WARN: Type inference failed for: r0v1741 */
        /* JADX WARN: Type inference failed for: r0v1742 */
        /* JADX WARN: Type inference failed for: r0v1743 */
        /* JADX WARN: Type inference failed for: r0v1744 */
        /* JADX WARN: Type inference failed for: r0v1745 */
        /* JADX WARN: Type inference failed for: r0v1746 */
        /* JADX WARN: Type inference failed for: r0v1747 */
        /* JADX WARN: Type inference failed for: r0v1748 */
        /* JADX WARN: Type inference failed for: r0v1749 */
        /* JADX WARN: Type inference failed for: r0v1750 */
        /* JADX WARN: Type inference failed for: r0v1751 */
        /* JADX WARN: Type inference failed for: r0v1752 */
        /* JADX WARN: Type inference failed for: r0v1753 */
        /* JADX WARN: Type inference failed for: r0v1754 */
        /* JADX WARN: Type inference failed for: r0v1755 */
        /* JADX WARN: Type inference failed for: r0v1756 */
        /* JADX WARN: Type inference failed for: r0v1757 */
        /* JADX WARN: Type inference failed for: r0v1758 */
        /* JADX WARN: Type inference failed for: r0v1759 */
        /* JADX WARN: Type inference failed for: r0v1760 */
        /* JADX WARN: Type inference failed for: r0v1761 */
        /* JADX WARN: Type inference failed for: r0v1762 */
        /* JADX WARN: Type inference failed for: r0v1763 */
        /* JADX WARN: Type inference failed for: r0v1764 */
        /* JADX WARN: Type inference failed for: r0v1765 */
        /* JADX WARN: Type inference failed for: r0v1766 */
        /* JADX WARN: Type inference failed for: r0v1767 */
        /* JADX WARN: Type inference failed for: r0v1768 */
        /* JADX WARN: Type inference failed for: r0v1769 */
        /* JADX WARN: Type inference failed for: r0v1770 */
        /* JADX WARN: Type inference failed for: r0v1771 */
        /* JADX WARN: Type inference failed for: r0v1772 */
        /* JADX WARN: Type inference failed for: r0v1773 */
        /* JADX WARN: Type inference failed for: r0v1774 */
        /* JADX WARN: Type inference failed for: r0v1775 */
        /* JADX WARN: Type inference failed for: r0v1776 */
        /* JADX WARN: Type inference failed for: r0v1777 */
        /* JADX WARN: Type inference failed for: r0v1778 */
        /* JADX WARN: Type inference failed for: r0v1779 */
        /* JADX WARN: Type inference failed for: r0v1780 */
        /* JADX WARN: Type inference failed for: r0v1781 */
        /* JADX WARN: Type inference failed for: r0v1782 */
        /* JADX WARN: Type inference failed for: r0v1783 */
        /* JADX WARN: Type inference failed for: r0v1784 */
        /* JADX WARN: Type inference failed for: r0v1785 */
        /* JADX WARN: Type inference failed for: r0v1786 */
        /* JADX WARN: Type inference failed for: r0v1787 */
        /* JADX WARN: Type inference failed for: r0v1788 */
        /* JADX WARN: Type inference failed for: r0v1789 */
        /* JADX WARN: Type inference failed for: r0v1790 */
        /* JADX WARN: Type inference failed for: r0v1791 */
        /* JADX WARN: Type inference failed for: r0v1792 */
        /* JADX WARN: Type inference failed for: r0v308 */
        /* JADX WARN: Type inference failed for: r0v309 */
        /* JADX WARN: Type inference failed for: r0v310 */
        /* JADX WARN: Type inference failed for: r0v311 */
        /* JADX WARN: Type inference failed for: r0v312 */
        /* JADX WARN: Type inference failed for: r0v313 */
        /* JADX WARN: Type inference failed for: r0v314 */
        /* JADX WARN: Type inference failed for: r0v315 */
        /* JADX WARN: Type inference failed for: r0v316 */
        /* JADX WARN: Type inference failed for: r0v317 */
        /* JADX WARN: Type inference failed for: r0v318 */
        /* JADX WARN: Type inference failed for: r0v319 */
        /* JADX WARN: Type inference failed for: r0v320 */
        /* JADX WARN: Type inference failed for: r0v321 */
        /* JADX WARN: Type inference failed for: r0v322 */
        /* JADX WARN: Type inference failed for: r0v323 */
        /* JADX WARN: Type inference failed for: r0v324 */
        /* JADX WARN: Type inference failed for: r0v325 */
        /* JADX WARN: Type inference failed for: r0v326 */
        /* JADX WARN: Type inference failed for: r0v327 */
        /* JADX WARN: Type inference failed for: r0v328 */
        /* JADX WARN: Type inference failed for: r0v329 */
        /* JADX WARN: Type inference failed for: r0v330 */
        /* JADX WARN: Type inference failed for: r0v331 */
        /* JADX WARN: Type inference failed for: r0v332 */
        /* JADX WARN: Type inference failed for: r0v333 */
        /* JADX WARN: Type inference failed for: r0v334 */
        /* JADX WARN: Type inference failed for: r0v335 */
        /* JADX WARN: Type inference failed for: r0v336 */
        /* JADX WARN: Type inference failed for: r0v337 */
        /* JADX WARN: Type inference failed for: r0v338 */
        /* JADX WARN: Type inference failed for: r0v339 */
        /* JADX WARN: Type inference failed for: r0v340 */
        /* JADX WARN: Type inference failed for: r0v341 */
        /* JADX WARN: Type inference failed for: r0v342 */
        /* JADX WARN: Type inference failed for: r0v343 */
        /* JADX WARN: Type inference failed for: r0v344 */
        /* JADX WARN: Type inference failed for: r0v345 */
        /* JADX WARN: Type inference failed for: r0v346 */
        /* JADX WARN: Type inference failed for: r0v347 */
        /* JADX WARN: Type inference failed for: r0v348 */
        /* JADX WARN: Type inference failed for: r0v349 */
        /* JADX WARN: Type inference failed for: r0v350 */
        /* JADX WARN: Type inference failed for: r0v351 */
        /* JADX WARN: Type inference failed for: r0v352 */
        /* JADX WARN: Type inference failed for: r0v353 */
        /* JADX WARN: Type inference failed for: r0v354 */
        /* JADX WARN: Type inference failed for: r0v355 */
        /* JADX WARN: Type inference failed for: r0v356 */
        @Override // com.hisense.uiadapter.TVOption
        public Integer get() {
            int country;
            int region;
            int language;
            int logo;
            int multiBalance;
            int iConvertDataRange = this.name.equals("HIFAC_F7_NETWORK") ? TVComponent.mFacFunction.getnetworktype() : 0;
            if (this.name.equals("HIFAC_RDRV")) {
                int rdrv = TVComponent.mFacFunction.getRDRV();
                Log.i("Fac_TVComponent", "**get RDRV=" + rdrv);
                iConvertDataRange = TVComponent.this.mTVCalculator.convertDataRange(this.min, this.max, 0, 255, rdrv);
            }
            int iConvertDataRange2 = iConvertDataRange;
            if (this.name.equals("HIFAC_GDRV")) {
                iConvertDataRange2 = TVComponent.this.mTVCalculator.convertDataRange(this.min, this.max, 0, 255, TVComponent.mFacFunction.getGDRV());
            }
            int iConvertDataRange3 = iConvertDataRange2;
            if (this.name.equals("HIFAC_BDRV")) {
                iConvertDataRange3 = TVComponent.this.mTVCalculator.convertDataRange(this.min, this.max, 0, 255, TVComponent.mFacFunction.getBDRV());
            }
            int iConvertDataRange4 = iConvertDataRange3;
            if (this.name.equals("HIFAC_RCUT")) {
                iConvertDataRange4 = TVComponent.this.mTVCalculator.convertDataRange(this.min, this.max, 0, 511, TVComponent.mFacFunction.getRCUT());
            }
            int iConvertDataRange5 = iConvertDataRange4;
            if (this.name.equals("HIFAC_GCUT")) {
                iConvertDataRange5 = TVComponent.this.mTVCalculator.convertDataRange(this.min, this.max, 0, 511, TVComponent.mFacFunction.getGCUT());
            }
            int iConvertDataRange6 = iConvertDataRange5;
            if (this.name.equals("HIFAC_BCUT")) {
                iConvertDataRange6 = TVComponent.this.mTVCalculator.convertDataRange(this.min, this.max, 0, 511, TVComponent.mFacFunction.getBCUT());
            }
            int iConvertDataRange7 = iConvertDataRange6;
            if (this.name.equals("HIFAC_RGAIN_DELTA")) {
                iConvertDataRange7 = TVComponent.this.mTVCalculator.convertDataRange(this.min, this.max, -30, 30, TVComponent.mFacFunction.get2PWBGainRedDelta());
            }
            int iConvertDataRange8 = iConvertDataRange7;
            if (this.name.equals("HIFAC_GGAIN_DELTA")) {
                iConvertDataRange8 = TVComponent.this.mTVCalculator.convertDataRange(this.min, this.max, -30, 30, TVComponent.mFacFunction.get2PWBGainGreenDelta());
            }
            int iConvertDataRange9 = iConvertDataRange8;
            if (this.name.equals("HIFAC_BGAIN_DELTA")) {
                iConvertDataRange9 = TVComponent.this.mTVCalculator.convertDataRange(this.min, this.max, -30, 30, TVComponent.mFacFunction.get2PWBGainBlueDelta());
            }
            int iConvertDataRange10 = iConvertDataRange9;
            if (this.name.equals("HIFAC_ROFFSET_DELTA")) {
                iConvertDataRange10 = TVComponent.this.mTVCalculator.convertDataRange(this.min, this.max, -30, 30, TVComponent.mFacFunction.get2PWBOffsetRedDelta());
            }
            int iConvertDataRange11 = iConvertDataRange10;
            if (this.name.equals("HIFAC_GOFFSET_DELTA")) {
                iConvertDataRange11 = TVComponent.this.mTVCalculator.convertDataRange(this.min, this.max, -30, 30, TVComponent.mFacFunction.get2PWBOffsetGreenDelta());
            }
            int iConvertDataRange12 = iConvertDataRange11;
            if (this.name.equals("HIFAC_BOFFSET_DELTA")) {
                iConvertDataRange12 = TVComponent.this.mTVCalculator.convertDataRange(this.min, this.max, -30, 30, TVComponent.mFacFunction.get2PWBOffsetBlueDelta());
            }
            int iOrdinal = iConvertDataRange12;
            if (this.name.equals("HIFAC_PNL_SEL")) {
                iOrdinal = TVComponent.mFacFunction.getModelPanel().ordinal();
            }
            int tunerMode = iOrdinal;
            if (this.name.equals("HIFAC_TUNERMODE")) {
                tunerMode = TVComponent.mFacFunction.getTunerMode();
            }
            int iOrdinal2 = tunerMode;
            if (this.name.equals("HIFAC_2D3D")) {
                iOrdinal2 = TVComponent.mFacFunction.get2Dor3D().ordinal();
            }
            int iOrdinal3 = iOrdinal2;
            if (this.name.equals("FACTORY_COLORTUNER")) {
                iOrdinal3 = TVComponent.mFacFunction.getColorTuner().ordinal();
            }
            int cTHue = iOrdinal3;
            if (this.name.equals("FACTORY_CT_HUE")) {
                cTHue = TVComponent.mFacFunction.getCTHue();
            }
            int cTSaturation = cTHue;
            if (this.name.equals("FACTORY_CT_SATURATION")) {
                cTSaturation = TVComponent.mFacFunction.getCTSaturation();
            }
            int cTBrightness = cTSaturation;
            if (this.name.equals("FACTORY_CT_BRIGHTNESS")) {
                cTBrightness = TVComponent.mFacFunction.getCTBrightness();
            }
            int iOrdinal4 = cTBrightness;
            if (this.name.equals("HIFAC_COLORTEMPERATURE")) {
                iOrdinal4 = TVComponent.mFacFunction.getColorTemperature().ordinal();
            }
            int iOrdinal5 = iOrdinal4;
            if (this.name.equals("HIFAC_COLOR_TEMP")) {
                iOrdinal5 = TVComponent.mFacFunction.getColorTemperature().ordinal();
            }
            int iConvertDataRange13 = iOrdinal5;
            if (this.name.equals("HIFAC_RDRV_OFFSET")) {
                iConvertDataRange13 = TVComponent.this.mTVCalculator.convertDataRange(this.min, this.max, 0, 511, TVComponent.mFacFunction.getRDRVOffset());
            }
            int iConvertDataRange14 = iConvertDataRange13;
            if (this.name.equals("HIFAC_GDRV_OFFSET")) {
                iConvertDataRange14 = TVComponent.this.mTVCalculator.convertDataRange(this.min, this.max, 0, 511, TVComponent.mFacFunction.getGDRVOffset());
            }
            int iConvertDataRange15 = iConvertDataRange14;
            if (this.name.equals("HIFAC_BDRV_OFFSET")) {
                iConvertDataRange15 = TVComponent.this.mTVCalculator.convertDataRange(this.min, this.max, 0, 511, TVComponent.mFacFunction.getBDRVOffset());
            }
            int iConvertDataRange16 = iConvertDataRange15;
            if (this.name.equals("HIFAC_RCUT_OFFSET")) {
                iConvertDataRange16 = TVComponent.this.mTVCalculator.convertDataRange(this.min, this.max, 0, 511, TVComponent.mFacFunction.getRCUTOffset());
            }
            int iConvertDataRange17 = iConvertDataRange16;
            if (this.name.equals("HIFAC_GCUT_OFFSET")) {
                iConvertDataRange17 = TVComponent.this.mTVCalculator.convertDataRange(this.min, this.max, 0, 511, TVComponent.mFacFunction.getGCUTOffset());
            }
            int iConvertDataRange18 = iConvertDataRange17;
            if (this.name.equals("HIFAC_BCUT_OFFSET")) {
                iConvertDataRange18 = TVComponent.this.mTVCalculator.convertDataRange(this.min, this.max, 0, 511, TVComponent.mFacFunction.getBCUTOffset());
            }
            int iOrdinal6 = iConvertDataRange18;
            if (this.name.equals("HIFAC_TOFAC")) {
                iOrdinal6 = TVComponent.mFacFunction.getToFac().ordinal();
            }
            int i = iOrdinal6;
            if (this.name.equals("3D_Sync_Delay")) {
                i = TVComponent.mFacFunction.get3D_Sync_Delay();
            }
            int factoryStatus = i;
            if (this.name.equals("HIFAC_STATUS")) {
                factoryStatus = TVComponent.mFacFunction.getFactoryStatus();
            }
            int iOrdinal7 = factoryStatus;
            if (this.name.equals("HIFAC_POWERMODE")) {
                iOrdinal7 = TVComponent.mFacFunction.getPowerOnMode().ordinal();
            }
            if (this.name.equals("HIFAC_SETCOUNTRY") || this.name.equals("HIFAC_SETCOUNTRY_FOR_REGION1") || this.name.equals("HIFAC_SETCOUNTRY_FOR_REGION2") || this.name.equals("HIFAC_SETCOUNTRY_FOR_REGION3") || this.name.equals("HIFAC_SETCOUNTRY_FOR_REGION4") || this.name.equals("HIFAC_SETCOUNTRY_FOR_REGION5") || this.name.equals("HIFAC_SETCOUNTRY_FOR_SA_REGION1") || this.name.equals("HIFAC_SETCOUNTRY_FOR_SA_REGION2") || this.name.equals("HIFAC_SETCOUNTRY_MONITOR")) {
                country = iOrdinal7;
                country = TVComponent.mFacFunction.getCountry();
            }
            country = iOrdinal7;
            if (this.name.equals("HIFAC_SETREGION") || this.name.equals("HIFAC_SETREGION_SA")) {
                region = country;
                region = TVComponent.mFacFunction.getRegion();
            }
            region = country;
            if (this.name.equals("HIFAC_SETLANGUAGE") || this.name.equals("HIFAC_SETLANGUAGE_FOR_REGION1") || this.name.equals("HIFAC_SETLANGUAGE_FOR_REGION2") || this.name.equals("HIFAC_SETLANGUAGE_FOR_REGION3") || this.name.equals("HIFAC_SETLANGUAGE_FOR_REGION4") || this.name.equals("HIFAC_SETLANGUAGE_FOR_REGION5") || this.name.equals("HIFAC_SETLANGUAGE_FOR_SA_REGION1") || this.name.equals("HIFAC_SETLANGUAGE_FOR_SA_REGION2") || this.name.equals("HIFAC_SETLANGUAGE_FOR_TWN") || this.name.equals("HIFAC_SETLANGUAGE_FOR_JPN") || this.name.equals("HIFAC_SETLANGUAGE_FOR_HK") || this.name.equals("HIFAC_SETLANGUAGE_FOR_MONITOR")) {
                language = region;
                language = TVComponent.mFacFunction.getLanguage();
            }
            language = region;
            if (this.name.equals("HIFAC_SETLOGO") || this.name.equals("HIFAC_SETLOGO_FOR_EM_NON_BRAND_ISOLATION") || this.name.equals("HIFAC_SETLOGO_FOR_EM_BRAND_ISOLATION") || this.name.equals("HIFAC_SETLOGO_FOR_EM_SA") || this.name.equals("HIFAC_SETLOGO_FOR_SA") || this.name.equals("HIFAC_SETLOGO_FOR_JPN") || this.name.equals("HIFAC_SETLANGUAGE_FOR_HK") || this.name.equals("FACTORY_FUN_SETLOGO_FOR_MONITOR") || this.name.equals("HIFAC_SETLOGO_FOR_KOR")) {
                logo = language;
                logo = TVComponent.mFacFunction.getLogo();
            }
            logo = language;
            int iOrdinal8 = logo;
            if (this.name.equals("HIFAC_HOMEORTV")) {
                iOrdinal8 = TVComponent.mFacFunction.getHomeorTV().ordinal();
            }
            int iOrdinal9 = iOrdinal8;
            if (this.name.equals("HIFAC_OSDLANGUAGE")) {
                iOrdinal9 = TVComponent.mFacFunction.getOSDLanguage().ordinal();
            }
            int iOrdinal10 = iOrdinal9;
            if (this.name.equals("HIFAC_PICTUREMODE")) {
                iOrdinal10 = TVComponent.mFacFunction.getPictureMode().ordinal();
            }
            int iOrdinal11 = iOrdinal10;
            if (this.name.equals("HIFAC_PICTUREMODE_TW")) {
                iOrdinal11 = TVComponent.mFacFunction.getPictureModeTW().ordinal();
            }
            int brightness = iOrdinal11;
            if (this.name.equals("HIFAC_PICTUREMODE_BRIGHTNESS")) {
                brightness = TVComponent.mFacFunction.getBrightness();
            }
            int contrast = brightness;
            if (this.name.equals("HIFAC_PICTUREMODE_CONTRAST")) {
                contrast = TVComponent.mFacFunction.getContrast();
            }
            int color = contrast;
            if (this.name.equals("HIFAC_PICTUREMODE_COLOR")) {
                color = TVComponent.mFacFunction.getColor();
            }
            int sharpness = color;
            if (this.name.equals("HIFAC_PICTUREMODE_SHARPNESS")) {
                sharpness = TVComponent.mFacFunction.getSharpness();
            }
            int backlight = sharpness;
            if (this.name.equals("HIFAC_PICTUREMODE_BACKLIGHT")) {
                backlight = TVComponent.mFacFunction.getBacklight();
            }
            int iOrdinal12 = backlight;
            if (this.name.equals("HIFAC_SOUNDMODE")) {
                iOrdinal12 = TVComponent.mFacFunction.getAudioMode().ordinal();
            }
            int iConvertDataRange19 = iOrdinal12;
            if (this.name.equals("HIFAC_EQUALIZE_120HZ")) {
                int equalizer120Hz = TVComponent.mFacFunction.getEqualizer120Hz();
                Log.i("Fac_TVComponent", "getEqualizer120Hz = " + equalizer120Hz);
                iConvertDataRange19 = TVComponent.this.mTVCalculator.convertDataRange(this.min, this.max, -10, 10, equalizer120Hz);
            }
            int iConvertDataRange20 = iConvertDataRange19;
            if (this.name.equals("HIFAC_EQUALIZE_500HZ")) {
                iConvertDataRange20 = TVComponent.this.mTVCalculator.convertDataRange(this.min, this.max, -10, 10, TVComponent.mFacFunction.getEqualizer500Hz());
            }
            int iConvertDataRange21 = iConvertDataRange20;
            if (this.name.equals("HIFAC_EQUALIZE_1500HZ")) {
                iConvertDataRange21 = TVComponent.this.mTVCalculator.convertDataRange(this.min, this.max, -10, 10, TVComponent.mFacFunction.getEqualizer1500Hz());
            }
            int iConvertDataRange22 = iConvertDataRange21;
            if (this.name.equals("HIFAC_EQUALIZE_5KHZ")) {
                iConvertDataRange22 = TVComponent.this.mTVCalculator.convertDataRange(this.min, this.max, -10, 10, TVComponent.mFacFunction.getEqualizer5KHz());
            }
            int iConvertDataRange23 = iConvertDataRange22;
            if (this.name.equals("HIFAC_EQUALIZE_10KHZ")) {
                iConvertDataRange23 = TVComponent.this.mTVCalculator.convertDataRange(this.min, this.max, -10, 10, TVComponent.mFacFunction.getEqualizer10KHz());
            }
            int iConvertDataRange24 = iConvertDataRange23;
            if (this.name.equals("HIFAC_EQUALIZE_20KHZ")) {
                iConvertDataRange24 = TVComponent.this.mTVCalculator.convertDataRange(this.min, this.max, -10, 10, TVComponent.mFacFunction.getEqualizer20KHz());
            }
            int iConvertDataRange25 = iConvertDataRange24;
            if (this.name.equals("HIFAC_EQUALIZE_30KHZ")) {
                iConvertDataRange25 = TVComponent.this.mTVCalculator.convertDataRange(this.min, this.max, -10, 10, TVComponent.mFacFunction.getEqualizer30KHz());
            }
            int iConvertDataRange26 = iConvertDataRange25;
            if (this.name.equals("HIFAC_EQUALIZE_40KHZ")) {
                iConvertDataRange26 = TVComponent.this.mTVCalculator.convertDataRange(this.min, this.max, -10, 10, TVComponent.mFacFunction.getEqualizer40KHz());
            }
            int iConvertDataRange27 = iConvertDataRange26;
            if (this.name.equals("HIFAC_EQUALIZE_50KHZ")) {
                iConvertDataRange27 = TVComponent.this.mTVCalculator.convertDataRange(this.min, this.max, -10, 10, TVComponent.mFacFunction.getEqualizer50KHz());
            }
            int iConvertDataRange28 = iConvertDataRange27;
            if (this.name.equals("HIFAC_EQUALIZE_120HZ_NEW")) {
                iConvertDataRange28 = TVComponent.this.mTVCalculator.convertDataRange(this.min, this.max, -10, 10, TVComponent.mFacFunction.getEqualizer120Hz_New());
            }
            int iConvertDataRange29 = iConvertDataRange28;
            if (this.name.equals("HIFAC_EQUALIZE_250HZ")) {
                iConvertDataRange29 = TVComponent.this.mTVCalculator.convertDataRange(this.min, this.max, -10, 10, TVComponent.mFacFunction.getEqualizer250Hz());
            }
            int iConvertDataRange30 = iConvertDataRange29;
            if (this.name.equals("HIFAC_EQUALIZE_600HZ")) {
                iConvertDataRange30 = TVComponent.this.mTVCalculator.convertDataRange(this.min, this.max, -10, 10, TVComponent.mFacFunction.getEqualizer600Hz());
            }
            int iConvertDataRange31 = iConvertDataRange30;
            if (this.name.equals("HIFAC_EQUALIZE_1KHZ")) {
                iConvertDataRange31 = TVComponent.this.mTVCalculator.convertDataRange(this.min, this.max, -10, 10, TVComponent.mFacFunction.getEqualizer1KHz());
            }
            int iConvertDataRange32 = iConvertDataRange31;
            if (this.name.equals("HIFAC_EQUALIZE_2.5KHZ")) {
                iConvertDataRange32 = TVComponent.this.mTVCalculator.convertDataRange(this.min, this.max, -10, 10, TVComponent.mFacFunction.getEqualizer2500Hz());
            }
            int iConvertDataRange33 = iConvertDataRange32;
            if (this.name.equals("HIFAC_EQUALIZE_6KHZ")) {
                iConvertDataRange33 = TVComponent.this.mTVCalculator.convertDataRange(this.min, this.max, -10, 10, TVComponent.mFacFunction.getEqualizer6KHz());
            }
            int iConvertDataRange34 = iConvertDataRange33;
            if (this.name.equals("HIFAC_EQUALIZE_10KHZ_NEW")) {
                iConvertDataRange34 = TVComponent.this.mTVCalculator.convertDataRange(this.min, this.max, -10, 10, TVComponent.mFacFunction.getEqualizer10KHz_New());
            }
            int iOrdinal13 = iConvertDataRange34;
            if (this.name.equals("HIFAC_PICTURECURVE_MODE")) {
                iOrdinal13 = TVComponent.mFacFunction.getVideoCurveModeGroup().ordinal();
            }
            int prescale = iOrdinal13;
            if (this.name.equals("HIFAC_PRESCALE")) {
                prescale = TVComponent.mFacFunction.getPrescale();
            }
            int pictureCurve0 = prescale;
            if (this.name.equals("HIFAC_PICTURECURVE_CURVE0")) {
                pictureCurve0 = TVComponent.mFacFunction.getPictureCurve0();
            }
            int pictureCurve25 = pictureCurve0;
            if (this.name.equals("HIFAC_PICTURECURVE_CURVE25")) {
                pictureCurve25 = TVComponent.mFacFunction.getPictureCurve25();
            }
            int pictureCurve50 = pictureCurve25;
            if (this.name.equals("HIFAC_PICTURECURVE_CURVE50")) {
                pictureCurve50 = TVComponent.mFacFunction.getPictureCurve50();
            }
            int pictureCurve75 = pictureCurve50;
            if (this.name.equals("HIFAC_PICTURECURVE_CURVE75")) {
                pictureCurve75 = TVComponent.mFacFunction.getPictureCurve75();
            }
            int pictureCurve100 = pictureCurve75;
            if (this.name.equals("HIFAC_PICTURECURVE_CURVE100")) {
                pictureCurve100 = TVComponent.mFacFunction.getPictureCurve100();
            }
            int piCurveBrigth20 = pictureCurve100;
            if (this.name.equals("HIFAC_BRIGHT_20")) {
                piCurveBrigth20 = TVComponent.mFacFunction.getPiCurveBrigth20();
            }
            int piCurveBrigth80 = piCurveBrigth20;
            if (this.name.equals("HIFAC_BRIGHT_80")) {
                piCurveBrigth80 = TVComponent.mFacFunction.getPiCurveBrigth80();
            }
            int piCurveContract20 = piCurveBrigth80;
            if (this.name.equals("HIFAC_CONTRACT_20")) {
                piCurveContract20 = TVComponent.mFacFunction.getPiCurveContract20();
            }
            int piCurveContract80 = piCurveContract20;
            if (this.name.equals("HIFAC_CONTRACT_80")) {
                piCurveContract80 = TVComponent.mFacFunction.getPiCurveContract80();
            }
            int piCurveSaturation20 = piCurveContract80;
            if (this.name.equals("HIFAC_SATURATION_20")) {
                piCurveSaturation20 = TVComponent.mFacFunction.getPiCurveSaturation20();
            }
            int piCurveSaturation80 = piCurveSaturation20;
            if (this.name.equals("HIFAC_SATURATION_80")) {
                piCurveSaturation80 = TVComponent.mFacFunction.getPiCurveSaturation80();
            }
            int piCurveHue20 = piCurveSaturation80;
            if (this.name.equals("HIFAC_HUE_20")) {
                piCurveHue20 = TVComponent.mFacFunction.getPiCurveHue20();
            }
            int piCurveHue80 = piCurveHue20;
            if (this.name.equals("HIFAC_HUE_80")) {
                piCurveHue80 = TVComponent.mFacFunction.getPiCurveHue80();
            }
            int piCurveBrigthMin = piCurveHue80;
            if (this.name.equals("HIFAC_PICTURECURVE_BRIGHT_MIN")) {
                piCurveBrigthMin = TVComponent.mFacFunction.getPiCurveBrigthMin();
            }
            int piCurveBrigthMid = piCurveBrigthMin;
            if (this.name.equals("HIFAC_PICTURECURVE_BRIGHT_MID")) {
                piCurveBrigthMid = TVComponent.mFacFunction.getPiCurveBrigthMid();
            }
            int piCurveBrigthMax = piCurveBrigthMid;
            if (this.name.equals("HIFAC_PICTURECURVE_BRIGHT_MAX")) {
                piCurveBrigthMax = TVComponent.mFacFunction.getPiCurveBrigthMax();
            }
            int piCurveContractMin = piCurveBrigthMax;
            if (this.name.equals("HIFAC_PICTURECURVE_CONTRACT_MIN")) {
                piCurveContractMin = TVComponent.mFacFunction.getPiCurveContractMin();
            }
            int piCurveContractMid = piCurveContractMin;
            if (this.name.equals("HIFAC_PICTURECURVE_CONTRACT_MID")) {
                piCurveContractMid = TVComponent.mFacFunction.getPiCurveContractMid();
            }
            int piCurveContractMax = piCurveContractMid;
            if (this.name.equals("HIFAC_PICTURECURVE_CONTRACT_MAX")) {
                piCurveContractMax = TVComponent.mFacFunction.getPiCurveContractMax();
            }
            int piCurveSaturationMin = piCurveContractMax;
            if (this.name.equals("HIFAC_PICTURECURVE_SATURATION_MIN")) {
                piCurveSaturationMin = TVComponent.mFacFunction.getPiCurveSaturationMin();
            }
            int piCurveSaturationMid = piCurveSaturationMin;
            if (this.name.equals("HIFAC_PICTURECURVE_SATURATION_MID")) {
                piCurveSaturationMid = TVComponent.mFacFunction.getPiCurveSaturationMid();
            }
            int piCurveSaturationMax = piCurveSaturationMid;
            if (this.name.equals("HIFAC_PICTURECURVE_SATURATION_MAX")) {
                piCurveSaturationMax = TVComponent.mFacFunction.getPiCurveSaturationMax();
            }
            int piCurveHueMin = piCurveSaturationMax;
            if (this.name.equals("HIFAC_PICTURECURVE_HUE_MIN")) {
                piCurveHueMin = TVComponent.mFacFunction.getPiCurveHueMin();
            }
            int piCurveHueMid = piCurveHueMin;
            if (this.name.equals("HIFAC_PICTURECURVE_HUE_MID")) {
                piCurveHueMid = TVComponent.mFacFunction.getPiCurveHueMid();
            }
            int piCurveHueMax = piCurveHueMid;
            if (this.name.equals("HIFAC_PICTURECURVE_HUE_MAX")) {
                piCurveHueMax = TVComponent.mFacFunction.getPiCurveHueMax();
            }
            int piCurveSharpnessMin = piCurveHueMax;
            if (this.name.equals("HIFAC_PICTURECURVE_SHARPNESS_MIN")) {
                piCurveSharpnessMin = TVComponent.mFacFunction.getPiCurveSharpnessMin();
            }
            int piCurveSharpness20 = piCurveSharpnessMin;
            if (this.name.equals("HIFAC_PICTURECURVE_SHARPNESS_20")) {
                piCurveSharpness20 = TVComponent.mFacFunction.getPiCurveSharpness20();
            }
            int piCurveSharpnessMid = piCurveSharpness20;
            if (this.name.equals("HIFAC_PICTURECURVE_SHARPNESS_MID")) {
                piCurveSharpnessMid = TVComponent.mFacFunction.getPiCurveSharpnessMid();
            }
            int piCurveSharpness80 = piCurveSharpnessMid;
            if (this.name.equals("HIFAC_PICTURECURVE_SHARPNESS_80")) {
                piCurveSharpness80 = TVComponent.mFacFunction.getPiCurveSharpness80();
            }
            int piCurveSharpnessMax = piCurveSharpness80;
            if (this.name.equals("HIFAC_PICTURECURVE_SHARPNESS_MAX")) {
                piCurveSharpnessMax = TVComponent.mFacFunction.getPiCurveSharpnessMax();
            }
            int wB20PointLevel = piCurveSharpnessMax;
            if (this.name.equals("FACTORY_20POINT_LEVEL")) {
                wB20PointLevel = TVComponent.mFacFunction.getWB20PointLevel();
            }
            int wB20PointRed = wB20PointLevel;
            if (this.name.equals("FACTORY_20POINT_RED")) {
                wB20PointRed = TVComponent.mFacFunction.getWB20PointRed();
            }
            int wB20PointGreen = wB20PointRed;
            if (this.name.equals("FACTORY_20POINT_GREEN")) {
                wB20PointGreen = TVComponent.mFacFunction.getWB20PointGreen();
            }
            int wB20PointBlue = wB20PointGreen;
            if (this.name.equals("FACTORY_20POINT_BLUE")) {
                wB20PointBlue = TVComponent.mFacFunction.getWB20PointBlue();
            }
            int volumeCurve0 = wB20PointBlue;
            if (this.name.equals("HIFAC_AUDIOCURVE_CURVE1")) {
                volumeCurve0 = TVComponent.mFacFunction.getVolumeCurve0();
            }
            int volumeCurve25 = volumeCurve0;
            if (this.name.equals("HIFAC_AUDIOCURVE_CURVE25")) {
                volumeCurve25 = TVComponent.mFacFunction.getVolumeCurve25();
            }
            int volumeCurve50 = volumeCurve25;
            if (this.name.equals("HIFAC_AUDIOCURVE_CURVE50")) {
                volumeCurve50 = TVComponent.mFacFunction.getVolumeCurve50();
            }
            int volumeCurve75 = volumeCurve50;
            if (this.name.equals("HIFAC_AUDIOCURVE_CURVE75")) {
                volumeCurve75 = TVComponent.mFacFunction.getVolumeCurve75();
            }
            int volumeCurve100 = volumeCurve75;
            if (this.name.equals("HIFAC_AUDIOCURVE_CURVE100")) {
                volumeCurve100 = TVComponent.mFacFunction.getVolumeCurve100();
            }
            int volumeCurve = volumeCurve100;
            if (this.name.equals("HIFAC_AUDIOCURVE_CURVE1_NEW")) {
                volumeCurve = TVComponent.mFacFunction.getVolumeCurve(0);
            }
            int volumeCurve2 = volumeCurve;
            if (this.name.equals("HIFAC_AUDIOCURVE_CURVE10_NEW")) {
                volumeCurve2 = TVComponent.mFacFunction.getVolumeCurve(1);
            }
            int volumeCurve3 = volumeCurve2;
            if (this.name.equals("HIFAC_AUDIOCURVE_CURVE25_NEW")) {
                volumeCurve3 = TVComponent.mFacFunction.getVolumeCurve(2);
            }
            int volumeCurve4 = volumeCurve3;
            if (this.name.equals("HIFAC_AUDIOCURVE_CURVE50_NEW")) {
                volumeCurve4 = TVComponent.mFacFunction.getVolumeCurve(3);
            }
            int volumeCurve5 = volumeCurve4;
            if (this.name.equals("HIFAC_AUDIOCURVE_CURVE75_NEW")) {
                volumeCurve5 = TVComponent.mFacFunction.getVolumeCurve(4);
            }
            int volumeCurve6 = volumeCurve5;
            if (this.name.equals("HIFAC_AUDIOCURVE_CURVE100_NEW")) {
                volumeCurve6 = TVComponent.mFacFunction.getVolumeCurve(5);
            }
            int freq1 = volumeCurve6;
            if (this.name.equals("HIFAC_PEQ_1_FREQ")) {
                freq1 = TVComponent.mFacFunction.getFREQ1();
            }
            int freq2 = freq1;
            if (this.name.equals("HIFAC_PEQ_2_FREQ")) {
                freq2 = TVComponent.mFacFunction.getFREQ2();
            }
            int freq3 = freq2;
            if (this.name.equals("HIFAC_PEQ_3_FREQ")) {
                freq3 = TVComponent.mFacFunction.getFREQ3();
            }
            int freq4 = freq3;
            if (this.name.equals("HIFAC_PEQ_4_FREQ")) {
                freq4 = TVComponent.mFacFunction.getFREQ4();
            }
            int freq5 = freq4;
            if (this.name.equals("HIFAC_PEQ_5_FREQ")) {
                freq5 = TVComponent.mFacFunction.getFREQ5();
            }
            int freq6 = freq5;
            if (this.name.equals("HIFAC_PEQ_6_FREQ")) {
                freq6 = TVComponent.mFacFunction.getFREQ6();
            }
            int freq7 = freq6;
            if (this.name.equals("HIFAC_PEQ_7_FREQ")) {
                freq7 = TVComponent.mFacFunction.getFREQ7();
            }
            int freq8 = freq7;
            if (this.name.equals("HIFAC_PEQ_8_FREQ")) {
                freq8 = TVComponent.mFacFunction.getFREQ8();
            }
            int gain1 = freq8;
            if (this.name.equals("HIFAC_PEQ_1_GAIN")) {
                gain1 = TVComponent.mFacFunction.getGAIN1();
            }
            int gain2 = gain1;
            if (this.name.equals("HIFAC_PEQ_2_GAIN")) {
                gain2 = TVComponent.mFacFunction.getGAIN2();
            }
            int gain3 = gain2;
            if (this.name.equals("HIFAC_PEQ_3_GAIN")) {
                gain3 = TVComponent.mFacFunction.getGAIN3();
            }
            int gain4 = gain3;
            if (this.name.equals("HIFAC_PEQ_4_GAIN")) {
                gain4 = TVComponent.mFacFunction.getGAIN4();
            }
            int gain5 = gain4;
            if (this.name.equals("HIFAC_PEQ_5_GAIN")) {
                gain5 = TVComponent.mFacFunction.getGAIN5();
            }
            int gain6 = gain5;
            if (this.name.equals("HIFAC_PEQ_6_GAIN")) {
                gain6 = TVComponent.mFacFunction.getGAIN6();
            }
            int gain7 = gain6;
            if (this.name.equals("HIFAC_PEQ_7_GAIN")) {
                gain7 = TVComponent.mFacFunction.getGAIN7();
            }
            int gain8 = gain7;
            if (this.name.equals("HIFAC_PEQ_8_GAIN")) {
                gain8 = TVComponent.mFacFunction.getGAIN8();
            }
            int q1 = gain8;
            if (this.name.equals("HIFAC_PEQ_1_Q")) {
                q1 = TVComponent.mFacFunction.getQ1();
            }
            int q2 = q1;
            if (this.name.equals("HIFAC_PEQ_2_Q")) {
                q2 = TVComponent.mFacFunction.getQ2();
            }
            int q3 = q2;
            if (this.name.equals("HIFAC_PEQ_3_Q")) {
                q3 = TVComponent.mFacFunction.getQ3();
            }
            int q4 = q3;
            if (this.name.equals("HIFAC_PEQ_4_Q")) {
                q4 = TVComponent.mFacFunction.getQ4();
            }
            int q5 = q4;
            if (this.name.equals("HIFAC_PEQ_5_Q")) {
                q5 = TVComponent.mFacFunction.getQ5();
            }
            int q6 = q5;
            if (this.name.equals("HIFAC_PEQ_6_Q")) {
                q6 = TVComponent.mFacFunction.getQ6();
            }
            int q7 = q6;
            if (this.name.equals("HIFAC_PEQ_7_Q")) {
                q7 = TVComponent.mFacFunction.getQ7();
            }
            int q8 = q7;
            if (this.name.equals("HIFAC_PEQ_8_Q")) {
                q8 = TVComponent.mFacFunction.getQ8();
            }
            int hpfFrequency = q8;
            if (this.name.equals("HIFAC_HPF_FREQUENCY")) {
                hpfFrequency = TVComponent.mFacFunction.getHpfFrequency();
            }
            int socDrc = hpfFrequency;
            if (this.name.equals("HIFAC_SOC_DRC")) {
                socDrc = TVComponent.mFacFunction.getSocDrc();
            }
            int autoVolumeCtrlThreshold = socDrc;
            if (this.name.equals("HIFAC_AUD_AUTO_VOLUEM_CTRL_THRESHOLD")) {
                autoVolumeCtrlThreshold = TVComponent.mFacFunction.getAutoVolumeCtrlThreshold();
            }
            int step1 = autoVolumeCtrlThreshold;
            if (this.name.equals("HIFAC_PEQ_1_STEP")) {
                step1 = TVComponent.mFacFunction.getSTEP1();
            }
            int step2 = step1;
            if (this.name.equals("HIFAC_PEQ_2_STEP")) {
                step2 = TVComponent.mFacFunction.getSTEP2();
            }
            int step3 = step2;
            if (this.name.equals("HIFAC_PEQ_3_STEP")) {
                step3 = TVComponent.mFacFunction.getSTEP3();
            }
            int step4 = step3;
            if (this.name.equals("HIFAC_PEQ_4_STEP")) {
                step4 = TVComponent.mFacFunction.getSTEP4();
            }
            int step5 = step4;
            if (this.name.equals("HIFAC_PEQ_5_STEP")) {
                step5 = TVComponent.mFacFunction.getSTEP5();
            }
            int step6 = step5;
            if (this.name.equals("HIFAC_PEQ_6_STEP")) {
                step6 = TVComponent.mFacFunction.getSTEP6();
            }
            int step7 = step6;
            if (this.name.equals("HIFAC_PEQ_7_STEP")) {
                step7 = TVComponent.mFacFunction.getSTEP7();
            }
            int step8 = step7;
            if (this.name.equals("HIFAC_PEQ_8_STEP")) {
                step8 = TVComponent.mFacFunction.getSTEP8();
            }
            int iConvertDataRange35 = step8;
            if (this.name.equals("HIFAC_VOLUME")) {
                iConvertDataRange35 = TVComponent.this.mTVCalculator.convertDataRange(this.min, this.max, 0, 100, TVComponent.mFacFunction.getVolume());
            }
            int backlight_Min = iConvertDataRange35;
            if (this.name.equals("FACTORY_BL_MIN_BACKLIGHT")) {
                backlight_Min = TVComponent.mFacFunction.getBacklight_Min(FacPropertyDemo.BACKLIGHTCURVE_POINT.BACKLIGHT_MIN);
            }
            int backlight_20 = backlight_Min;
            if (this.name.equals("FACTORY_BL_20_BACKLIGHT")) {
                backlight_20 = TVComponent.mFacFunction.getBacklight_20(FacPropertyDemo.BACKLIGHTCURVE_POINT.BACKLIGHT_20);
            }
            int backlight_Mid = backlight_20;
            if (this.name.equals("FACTORY_BL_MID_BACKLIGHT")) {
                backlight_Mid = TVComponent.mFacFunction.getBacklight_Mid(FacPropertyDemo.BACKLIGHTCURVE_POINT.BACKLIGHT_MID);
            }
            int backlight_80 = backlight_Mid;
            if (this.name.equals("FACTORY_BL_80_BACKLIGHT")) {
                backlight_80 = TVComponent.mFacFunction.getBacklight_80(FacPropertyDemo.BACKLIGHTCURVE_POINT.BACKLIGHT_80);
            }
            int backlight_Max = backlight_80;
            if (this.name.equals("FACTORY_BL_MAX_BACKLIGHT")) {
                backlight_Max = TVComponent.mFacFunction.getBacklight_Max(FacPropertyDemo.BACKLIGHTCURVE_POINT.BACKLIGHT_MAX);
            }
            int pWMFreq = backlight_Max;
            if (this.name.equals("FACTORY_BL_PWM_FREQ")) {
                pWMFreq = TVComponent.mFacFunction.getPWMFreq(FacPropertyDemo.BACKLIGHTCURVE_POINT.BACKLIGHT_PWM_FREQ);
            }
            int iConvertDataRange36 = pWMFreq;
            if (this.name.equals("HIFAC_OVERSCAN_HPOS")) {
                iConvertDataRange36 = TVComponent.this.mTVCalculator.convertDataRange(this.min, this.max, 0, 1000, TVComponent.mFacFunction.getOverScanLeft());
            }
            int iConvertDataRange37 = iConvertDataRange36;
            if (this.name.equals("HIFAC_OVERSCAN_VPOS")) {
                iConvertDataRange37 = TVComponent.this.mTVCalculator.convertDataRange(this.min, this.max, 0, 1000, TVComponent.mFacFunction.getOverScanTop());
            }
            int iConvertDataRange38 = iConvertDataRange37;
            if (this.name.equals("HIFAC_OVERSCAN_HSIZE")) {
                iConvertDataRange38 = TVComponent.this.mTVCalculator.convertDataRange(this.min, this.max, 0, 1000, TVComponent.mFacFunction.getOverScanRight());
            }
            int iConvertDataRange39 = iConvertDataRange38;
            if (this.name.equals("HIFAC_OVERSCAN_VSIZE")) {
                iConvertDataRange39 = TVComponent.this.mTVCalculator.convertDataRange(this.min, this.max, 0, 1000, TVComponent.mFacFunction.getOverScanBottom());
            }
            int iConvertDataRange40 = iConvertDataRange39;
            if (this.name.equals("HIFAC_OVERSCAN_HSTART")) {
                iConvertDataRange40 = TVComponent.this.mTVCalculator.convertDataRange(this.min, this.max, 0, 1000, TVComponent.mFacFunction.getHStart());
            }
            int iConvertDataRange41 = iConvertDataRange40;
            if (this.name.equals("HIFAC_OVERSCAN_VSTART")) {
                iConvertDataRange41 = TVComponent.this.mTVCalculator.convertDataRange(this.min, this.max, 0, 1000, TVComponent.mFacFunction.getVStart());
            }
            int agingFlag = iConvertDataRange41;
            if (this.name.equals("HIFAC_AGING")) {
                agingFlag = TVComponent.mFacFunction.getAgingFlag();
            }
            int iOrdinal14 = agingFlag;
            if (this.name.equals("HIFAC_SAVINGMODE")) {
                iOrdinal14 = TVComponent.mFacFunction.getSavingMode().ordinal();
            }
            int iOrdinal15 = iOrdinal14;
            if (this.name.equals("HIFAC_ZOOMMODE")) {
                iOrdinal15 = TVComponent.mFacFunction.getZoomMode().ordinal();
            }
            int iOrdinal16 = iOrdinal15;
            if (this.name.equals("HIFAC_ZOOMMODE_FOR_EM")) {
                iOrdinal16 = TVComponent.mFacFunction.getZoomModeForEM().ordinal();
            }
            int balance = iOrdinal16;
            if (this.name.equals("HIFAC_BALANCE")) {
                balance = TVComponent.mFacFunction.getBalance();
            }
            if (this.name.equals("HIFAC_BALANCE_DOUBLE_AMP") || this.name.equals("HIFAC_BALANCE_MULTI_AMP")) {
                multiBalance = balance;
                multiBalance = TVComponent.mFacFunction.getMultiBalance();
            }
            multiBalance = balance;
            int balanceForTweeter = multiBalance;
            if (this.name.equals("HIFAC_TVS_BALANCE_LIKE_550")) {
                balanceForTweeter = TVComponent.mFacFunction.getBalanceForTweeter();
            }
            int balanceForTweeterWoofer = balanceForTweeter;
            if (this.name.equals("HIFAC_TVS_BALANCE_LIKE_650")) {
                balanceForTweeterWoofer = TVComponent.mFacFunction.getBalanceForTweeterWoofer();
            }
            int balanceForLike850 = balanceForTweeterWoofer;
            if (this.name.equals("HIFAC_TVS_BALANCE_LIKE_850")) {
                balanceForLike850 = TVComponent.mFacFunction.getBalanceForLike850();
            }
            int iOrdinal17 = balanceForLike850;
            if (this.name.equals("HIFAC_3DMODE")) {
                iOrdinal17 = TVComponent.mFacFunction.get3DMode().ordinal();
            }
            int leftRightSwap = iOrdinal17;
            if (this.name.equals("HIFAC_LR_SWAP")) {
                leftRightSwap = TVComponent.mFacFunction.getLeftRightSwap();
            }
            int fieldDepth = leftRightSwap;
            if (this.name.equals("HIFAC_DEPTH_OF_FIELD")) {
                fieldDepth = TVComponent.mFacFunction.getFieldDepth();
            }
            int convergence = fieldDepth;
            if (this.name.equals("HIFAC_CONVERGENCE")) {
                convergence = TVComponent.mFacFunction.getConvergence();
            }
            int i2 = convergence;
            if (this.name.equals("HIFAC_3DTO2D")) {
                i2 = TVComponent.mFacFunction.get3Dto2D();
            }
            int laserAlarmEnable = i2;
            if (this.name.equals("HIFAC_ALARMENABLE")) {
                laserAlarmEnable = TVComponent.mFacFunction.getLaserAlarmEnable();
            }
            int iIsNoSignalStandbyEnabled = laserAlarmEnable;
            if (this.name.equals("HIFAC_NO_SIGNAL_STANDBY")) {
                iIsNoSignalStandbyEnabled = TVComponent.mFacFunction.isNoSignalStandbyEnabled();
            }
            int temperatureProtection = iIsNoSignalStandbyEnabled;
            if (this.name.equals("FACTORY_TEMP_PROTECT")) {
                temperatureProtection = TVComponent.mFacFunction.getTemperatureProtection();
            }
            int displayBoardUpgradeMode = temperatureProtection;
            if (this.name.equals("DISPLAYBOARD_SOFTWARE_UPGRADE_PREPARE")) {
                displayBoardUpgradeMode = TVComponent.mFacFunction.getDisplayBoardUpgradeMode();
            }
            int screen = displayBoardUpgradeMode;
            if (this.name.equals("FACTORY_SCREEN_SELECT")) {
                screen = TVComponent.mFacFunction.getScreen();
            }
            int aDCRedGain = screen;
            if (this.name.equals("HIFAC_ADC_RGAIN")) {
                aDCRedGain = TVComponent.mFacFunction.getADCRedGain();
            }
            int aDCGreenGain = aDCRedGain;
            if (this.name.equals("HIFAC_ADC_GGAIN")) {
                aDCGreenGain = TVComponent.mFacFunction.getADCGreenGain();
            }
            int aDCBlueGain = aDCGreenGain;
            if (this.name.equals("HIFAC_ADC_BGAIN")) {
                aDCBlueGain = TVComponent.mFacFunction.getADCBlueGain();
            }
            int aDCRedOffset = aDCBlueGain;
            if (this.name.equals("HIFAC_ADC_ROFFSET")) {
                aDCRedOffset = TVComponent.mFacFunction.getADCRedOffset();
            }
            int aDCGreenOffset = aDCRedOffset;
            if (this.name.equals("HIFAC_ADC_GOFFSET")) {
                aDCGreenOffset = TVComponent.mFacFunction.getADCGreenOffset();
            }
            int aDCBlueOffset = aDCGreenOffset;
            if (this.name.equals("HIFAC_ADC_BOFFSET")) {
                aDCBlueOffset = TVComponent.mFacFunction.getADCBlueOffset();
            }
            int pqBypassEnable = aDCBlueOffset;
            if (this.name.equals("FACTORY_PQ_BYPASS_ENABLE")) {
                pqBypassEnable = TVComponent.mFacFunction.getPqBypassEnable();
            }
            int gammaBypassEnable = pqBypassEnable;
            if (this.name.equals("FACTORY_GAMMA_BYPASS_ENABLE")) {
                gammaBypassEnable = TVComponent.mFacFunction.getGammaBypassEnable();
            }
            int colorBypassEnable = gammaBypassEnable;
            if (this.name.equals("FACTORY_COLOR_BYPASS_ENABLE")) {
                colorBypassEnable = TVComponent.mFacFunction.getColorBypassEnable();
            }
            int localdimmingEnable = colorBypassEnable;
            if (this.name.equals("FACTORY_LOCALDIMMING_ENABLE")) {
                localdimmingEnable = TVComponent.mFacFunction.getLocaldimmingEnable();
            }
            int imageCompensationEnable = localdimmingEnable;
            if (this.name.equals("FACTORY_IMAGE_COMPENSATION_ENABLE")) {
                imageCompensationEnable = TVComponent.mFacFunction.getImageCompensationEnable();
            }
            int bypassPQEnable = imageCompensationEnable;
            if (this.name.equals("HIFAC_BYPASS_PQ")) {
                bypassPQEnable = TVComponent.mFacFunction.getBypassPQEnable();
            }
            int bypassGammaEnable = bypassPQEnable;
            if (this.name.equals("HIFAC_BYPASS_GAMMA")) {
                bypassGammaEnable = TVComponent.mFacFunction.getBypassGammaEnable();
            }
            int bypassSecEnable = bypassGammaEnable;
            if (this.name.equals("HIFAC_BYPASS_SEC")) {
                bypassSecEnable = TVComponent.mFacFunction.getBypassSecEnable();
            }
            int bypassXvyccEnable = bypassSecEnable;
            if (this.name.equals("HIFAC_BYPASS_XVYCC")) {
                bypassXvyccEnable = TVComponent.mFacFunction.getBypassXvyccEnable();
            }
            int sSCVByOneEnable = bypassXvyccEnable;
            if (this.name.equals("HIFAC_SSC_VBYONE_ENABLE")) {
                sSCVByOneEnable = TVComponent.mFacFunction.getSSCVByOneEnable();
            }
            int sSCVByOneRange = sSCVByOneEnable;
            if (this.name.equals("HIFAC_SSC_VBYONE_RANGE")) {
                sSCVByOneRange = TVComponent.mFacFunction.getSSCVByOneRange();
            }
            int sSCVByOneSpan = sSCVByOneRange;
            if (this.name.equals("HIFAC_SSC_VBYONE_SPAN")) {
                sSCVByOneSpan = TVComponent.mFacFunction.getSSCVByOneSpan();
            }
            int sSCVByOneModu = sSCVByOneSpan;
            if (this.name.equals("HIFAC_SSC_VBYONE_MODULATION")) {
                sSCVByOneModu = TVComponent.mFacFunction.getSSCVByOneModu();
            }
            int sSCDDREnable = sSCVByOneModu;
            if (this.name.equals("HIFAC_SSC_DDR_ENABLE")) {
                sSCDDREnable = TVComponent.mFacFunction.getSSCDDREnable();
            }
            int sSCDDRSpan = sSCDDREnable;
            if (this.name.equals("HIFAC_SSC_DDR_SPAN")) {
                sSCDDRSpan = TVComponent.mFacFunction.getSSCDDRSpan();
            }
            int sSCDDRModu = sSCDDRSpan;
            if (this.name.equals("HIFAC_SSC_DDR_MODULATION")) {
                sSCDDRModu = TVComponent.mFacFunction.getSSCDDRModu();
            }
            int audioDRC = sSCDDRModu;
            if (this.name.equals("HIFAC_AUDIO_DRC")) {
                audioDRC = TVComponent.mFacFunction.getAudioDRC();
            }
            int multiDigitalAMPValueByDevId = audioDRC;
            if (this.name.equals("HIFAC_AUDIO_DRC_L")) {
                multiDigitalAMPValueByDevId = TVComponent.mFacFunction.getMultiDigitalAMPValueByDevId(0, 0);
            }
            int multiDigitalAMPValueByDevId2 = multiDigitalAMPValueByDevId;
            if (this.name.equals("HIFAC_AUDIO_DRC_H")) {
                multiDigitalAMPValueByDevId2 = TVComponent.mFacFunction.getMultiDigitalAMPValueByDevId(0, 1);
            }
            int multiDigitalAMPValueByDevId3 = multiDigitalAMPValueByDevId2;
            if (this.name.equals("HIFAC_AUDIO_DRC_S")) {
                multiDigitalAMPValueByDevId3 = TVComponent.mFacFunction.getMultiDigitalAMPValueByDevId(0, 2);
            }
            int multiDigitalAMPValueByDevId4 = multiDigitalAMPValueByDevId3;
            if (this.name.equals("HIFAC_AUDIO_DRC_P")) {
                multiDigitalAMPValueByDevId4 = TVComponent.mFacFunction.getMultiDigitalAMPValueByDevId(0, 3);
            }
            int multiDigitalAMPValueByDevId5 = multiDigitalAMPValueByDevId4;
            if (this.name.equals("HIFAC_AUDIO_PA_GAIN")) {
                multiDigitalAMPValueByDevId5 = TVComponent.mFacFunction.getMultiDigitalAMPValueByDevId(0, 4);
            }
            int multiDigitalAMPValueByDevId6 = multiDigitalAMPValueByDevId5;
            if (this.name.equals("HIFAC_AUDIO_OUTPUT_PORT")) {
                multiDigitalAMPValueByDevId6 = TVComponent.mFacFunction.getMultiDigitalAMPValueByDevId(0, 5);
            }
            int wooferAudioDRC = multiDigitalAMPValueByDevId6;
            if (this.name.equals("HIFAC_WOOFER_AUDIO_DRC")) {
                wooferAudioDRC = TVComponent.mFacFunction.getWooferAudioDRC();
            }
            int wooferAudioPaGain = wooferAudioDRC;
            if (this.name.equals("HIFAC_WOOFER_AUDIO_PA_GAIN")) {
                wooferAudioPaGain = TVComponent.mFacFunction.getWooferAudioPaGain();
            }
            int multiDigitalAMPValueByDevId7 = wooferAudioPaGain;
            if (this.name.equals("HIFAC_AUDIO_SUB1_DRC_L")) {
                multiDigitalAMPValueByDevId7 = TVComponent.mFacFunction.getMultiDigitalAMPValueByDevId(1, 0);
            }
            int multiDigitalAMPValueByDevId8 = multiDigitalAMPValueByDevId7;
            if (this.name.equals("HIFAC_AUDIO_SUB1_DRC_H")) {
                multiDigitalAMPValueByDevId8 = TVComponent.mFacFunction.getMultiDigitalAMPValueByDevId(1, 1);
            }
            int multiDigitalAMPValueByDevId9 = multiDigitalAMPValueByDevId8;
            if (this.name.equals("HIFAC_AUDIO_SUB1_DRC_S")) {
                multiDigitalAMPValueByDevId9 = TVComponent.mFacFunction.getMultiDigitalAMPValueByDevId(1, 2);
            }
            int multiDigitalAMPValueByDevId10 = multiDigitalAMPValueByDevId9;
            if (this.name.equals("HIFAC_AUDIO_SUB1_DRC_P")) {
                multiDigitalAMPValueByDevId10 = TVComponent.mFacFunction.getMultiDigitalAMPValueByDevId(1, 3);
            }
            int multiDigitalAMPValueByDevId11 = multiDigitalAMPValueByDevId10;
            if (this.name.equals("HIFAC_SUB1_AUDIO_PA_GAIN")) {
                multiDigitalAMPValueByDevId11 = TVComponent.mFacFunction.getMultiDigitalAMPValueByDevId(1, 4);
            }
            int multiDigitalAMPValueByDevId12 = multiDigitalAMPValueByDevId11;
            if (this.name.equals("HIFAC_SUB1_AUDIO_OUTPUT_PORT")) {
                multiDigitalAMPValueByDevId12 = TVComponent.mFacFunction.getMultiDigitalAMPValueByDevId(1, 5);
            }
            int multiDigitalAMPValueByDevId13 = multiDigitalAMPValueByDevId12;
            if (this.name.equals("HIFAC_AUDIO_SUB2_DRC_L")) {
                multiDigitalAMPValueByDevId13 = TVComponent.mFacFunction.getMultiDigitalAMPValueByDevId(2, 0);
            }
            int multiDigitalAMPValueByDevId14 = multiDigitalAMPValueByDevId13;
            if (this.name.equals("HIFAC_AUDIO_SUB2_DRC_H")) {
                multiDigitalAMPValueByDevId14 = TVComponent.mFacFunction.getMultiDigitalAMPValueByDevId(2, 1);
            }
            int multiDigitalAMPValueByDevId15 = multiDigitalAMPValueByDevId14;
            if (this.name.equals("HIFAC_AUDIO_SUB2_DRC_S")) {
                multiDigitalAMPValueByDevId15 = TVComponent.mFacFunction.getMultiDigitalAMPValueByDevId(2, 2);
            }
            int multiDigitalAMPValueByDevId16 = multiDigitalAMPValueByDevId15;
            if (this.name.equals("HIFAC_AUDIO_SUB2_DRC_P")) {
                multiDigitalAMPValueByDevId16 = TVComponent.mFacFunction.getMultiDigitalAMPValueByDevId(2, 3);
            }
            int multiDigitalAMPValueByDevId17 = multiDigitalAMPValueByDevId16;
            if (this.name.equals("HIFAC_SUB2_AUDIO_PA_GAIN")) {
                multiDigitalAMPValueByDevId17 = TVComponent.mFacFunction.getMultiDigitalAMPValueByDevId(2, 4);
            }
            int multiDigitalAMPValueByDevId18 = multiDigitalAMPValueByDevId17;
            if (this.name.equals("HIFAC_SUB2_AUDIO_OUTPUT_PORT")) {
                multiDigitalAMPValueByDevId18 = TVComponent.mFacFunction.getMultiDigitalAMPValueByDevId(2, 5);
            }
            int multiDigitalAMPValueByDevId19 = multiDigitalAMPValueByDevId18;
            if (this.name.equals("HIFAC_AUDIO_SUB3_DRC_L")) {
                multiDigitalAMPValueByDevId19 = TVComponent.mFacFunction.getMultiDigitalAMPValueByDevId(3, 0);
            }
            int multiDigitalAMPValueByDevId20 = multiDigitalAMPValueByDevId19;
            if (this.name.equals("HIFAC_AUDIO_SUB3_DRC_H")) {
                multiDigitalAMPValueByDevId20 = TVComponent.mFacFunction.getMultiDigitalAMPValueByDevId(3, 1);
            }
            int multiDigitalAMPValueByDevId21 = multiDigitalAMPValueByDevId20;
            if (this.name.equals("HIFAC_AUDIO_SUB3_DRC_S")) {
                multiDigitalAMPValueByDevId21 = TVComponent.mFacFunction.getMultiDigitalAMPValueByDevId(3, 2);
            }
            int multiDigitalAMPValueByDevId22 = multiDigitalAMPValueByDevId21;
            if (this.name.equals("HIFAC_AUDIO_SUB3_DRC_P")) {
                multiDigitalAMPValueByDevId22 = TVComponent.mFacFunction.getMultiDigitalAMPValueByDevId(3, 3);
            }
            int multiDigitalAMPValueByDevId23 = multiDigitalAMPValueByDevId22;
            if (this.name.equals("HIFAC_SUB3_AUDIO_PA_GAIN")) {
                multiDigitalAMPValueByDevId23 = TVComponent.mFacFunction.getMultiDigitalAMPValueByDevId(3, 4);
            }
            int multiDigitalAMPValueByDevId24 = multiDigitalAMPValueByDevId23;
            if (this.name.equals("HIFAC_SUB3_AUDIO_OUTPUT_PORT")) {
                multiDigitalAMPValueByDevId24 = TVComponent.mFacFunction.getMultiDigitalAMPValueByDevId(3, 5);
            }
            int multiDigitalAMPValueByDevId25 = multiDigitalAMPValueByDevId24;
            if (this.name.equals("HIFAC_AUDIO_SUB4_DRC_L")) {
                multiDigitalAMPValueByDevId25 = TVComponent.mFacFunction.getMultiDigitalAMPValueByDevId(4, 0);
            }
            int multiDigitalAMPValueByDevId26 = multiDigitalAMPValueByDevId25;
            if (this.name.equals("HIFAC_AUDIO_SUB4_DRC_H")) {
                multiDigitalAMPValueByDevId26 = TVComponent.mFacFunction.getMultiDigitalAMPValueByDevId(4, 1);
            }
            int multiDigitalAMPValueByDevId27 = multiDigitalAMPValueByDevId26;
            if (this.name.equals("HIFAC_AUDIO_SUB4_DRC_S")) {
                multiDigitalAMPValueByDevId27 = TVComponent.mFacFunction.getMultiDigitalAMPValueByDevId(4, 2);
            }
            int multiDigitalAMPValueByDevId28 = multiDigitalAMPValueByDevId27;
            if (this.name.equals("HIFAC_AUDIO_SUB4_DRC_P")) {
                multiDigitalAMPValueByDevId28 = TVComponent.mFacFunction.getMultiDigitalAMPValueByDevId(4, 3);
            }
            int multiDigitalAMPValueByDevId29 = multiDigitalAMPValueByDevId28;
            if (this.name.equals("HIFAC_SUB4_AUDIO_PA_GAIN")) {
                multiDigitalAMPValueByDevId29 = TVComponent.mFacFunction.getMultiDigitalAMPValueByDevId(4, 4);
            }
            int multiDigitalAMPValueByDevId30 = multiDigitalAMPValueByDevId29;
            if (this.name.equals("HIFAC_SUB4_AUDIO_OUTPUT_PORT")) {
                multiDigitalAMPValueByDevId30 = TVComponent.mFacFunction.getMultiDigitalAMPValueByDevId(4, 5);
            }
            int multiDigitalAMPValueByDevId31 = multiDigitalAMPValueByDevId30;
            if (this.name.equals("HIFAC_AUDIO_SUB5_DRC_L")) {
                multiDigitalAMPValueByDevId31 = TVComponent.mFacFunction.getMultiDigitalAMPValueByDevId(5, 0);
            }
            int multiDigitalAMPValueByDevId32 = multiDigitalAMPValueByDevId31;
            if (this.name.equals("HIFAC_AUDIO_SUB5_DRC_H")) {
                multiDigitalAMPValueByDevId32 = TVComponent.mFacFunction.getMultiDigitalAMPValueByDevId(5, 1);
            }
            int multiDigitalAMPValueByDevId33 = multiDigitalAMPValueByDevId32;
            if (this.name.equals("HIFAC_AUDIO_SUB5_DRC_S")) {
                multiDigitalAMPValueByDevId33 = TVComponent.mFacFunction.getMultiDigitalAMPValueByDevId(5, 2);
            }
            int multiDigitalAMPValueByDevId34 = multiDigitalAMPValueByDevId33;
            if (this.name.equals("HIFAC_AUDIO_SUB5_DRC_P")) {
                multiDigitalAMPValueByDevId34 = TVComponent.mFacFunction.getMultiDigitalAMPValueByDevId(5, 3);
            }
            int multiDigitalAMPValueByDevId35 = multiDigitalAMPValueByDevId34;
            if (this.name.equals("HIFAC_SUB5_AUDIO_PA_GAIN")) {
                multiDigitalAMPValueByDevId35 = TVComponent.mFacFunction.getMultiDigitalAMPValueByDevId(5, 4);
            }
            int multiDigitalAMPValueByDevId36 = multiDigitalAMPValueByDevId35;
            if (this.name.equals("HIFAC_SUB5_AUDIO_OUTPUT_PORT")) {
                multiDigitalAMPValueByDevId36 = TVComponent.mFacFunction.getMultiDigitalAMPValueByDevId(5, 5);
            }
            int attackTime = multiDigitalAMPValueByDevId36;
            if (this.name.equals("FACTORY_AVC_ATTACK_TIME")) {
                attackTime = TVComponent.mFacFunction.getAttackTime();
            }
            int releaseTime = attackTime;
            if (this.name.equals("FACTORY_AVC_RELEASE_TIME")) {
                releaseTime = TVComponent.mFacFunction.getReleaseTime();
            }
            int threshold = releaseTime;
            if (this.name.equals("HIFAC_AVC_THRESHOLD")) {
                threshold = TVComponent.mFacFunction.getThreshold();
            }
            int wooferAudioOutputPort = threshold;
            if (this.name.equals("HIFAC_WOOFER_AUDIO_OUTPUT_PORT")) {
                wooferAudioOutputPort = TVComponent.mFacFunction.getWooferAudioOutputPort();
            }
            int vifTop = wooferAudioOutputPort;
            if (this.name.equals("HIFAC_NONSTANDARD_VIF_TOP")) {
                vifTop = TVComponent.mFacFunction.getVifTop();
            }
            int vifVgaMaximum = vifTop;
            if (this.name.equals("HIFAC_NONSTANDARD_VIF_VGA_MAXIMUM")) {
                vifVgaMaximum = TVComponent.mFacFunction.getVifVgaMaximum();
            }
            int vifCrKp = vifVgaMaximum;
            if (this.name.equals("HIFAC_NONSTANDARD_VIF_CR_KP")) {
                vifCrKp = TVComponent.mFacFunction.getVifCrKp();
            }
            int vifCrKi = vifCrKp;
            if (this.name.equals("HIFAC_NONSTANDARD_VIF_CR_KI")) {
                vifCrKi = TVComponent.mFacFunction.getVifCrKi();
            }
            int vifAsiaSignalOption = vifCrKi;
            if (this.name.equals("HIFAC_NONSTANDARD_VIF_ASIA_SIGNAL_OPTION")) {
                vifAsiaSignalOption = TVComponent.mFacFunction.getVifAsiaSignalOption();
            }
            int vifCrKpKiAdjust = vifAsiaSignalOption;
            if (this.name.equals("HIFAC_NONSTANDARD_VIF_CR_KP_KI_ADJUST")) {
                vifCrKpKiAdjust = TVComponent.mFacFunction.getVifCrKpKiAdjust();
            }
            int vifOverModulation = vifCrKpKiAdjust;
            if (this.name.equals("HIFAC_NONSTANDARD_HI_DEV")) {
                vifOverModulation = TVComponent.mFacFunction.getVifOverModulation();
            }
            int vifClampGainOvNegative = vifOverModulation;
            if (this.name.equals("HIFAC_NONSTANDARD_VIF_CLAMPGAIN_GAIN_OV_NEGATIVE")) {
                vifClampGainOvNegative = TVComponent.mFacFunction.getVifClampGainOvNegative();
            }
            int chinaDescramblerBox = vifClampGainOvNegative;
            if (this.name.equals("HIFAC_NONSTANDARD_CHINA_DESCRAMBLER_BOX")) {
                chinaDescramblerBox = TVComponent.mFacFunction.getChinaDescramblerBox();
            }
            int delayReduce = chinaDescramblerBox;
            if (this.name.equals("HIFAC_NONSTANDARD_Delay")) {
                delayReduce = TVComponent.mFacFunction.getDelayReduce();
            }
            int vifCrThr = delayReduce;
            if (this.name.equals("HIFAC_NONSTANDARD_VIF_CR_THR")) {
                vifCrThr = TVComponent.mFacFunction.getVifCrThr();
            }
            int vifVersion = vifCrThr;
            if (this.name.equals("HIFAC_NONSTANDARD_VIF_VERSION")) {
                vifVersion = TVComponent.mFacFunction.getVifVersion();
            }
            int vifAgcRef = vifVersion;
            if (this.name.equals("HIFAC_NONSTANDARD_VIF_AGC_REF")) {
                vifAgcRef = TVComponent.mFacFunction.getVifAgcRef();
            }
            int gainDistributionThr = vifAgcRef;
            if (this.name.equals("HIFAC_NONSTANDARD_GAIN_DISTRIBUTION_THR")) {
                gainDistributionThr = TVComponent.mFacFunction.getGainDistributionThr();
            }
            int aefc_d4 = gainDistributionThr;
            if (this.name.equals("HIFAC_NONSTANDARD_AEFC_D4")) {
                aefc_d4 = TVComponent.mFacFunction.getAEFC_D4();
            }
            int aEFC_D5Bit2 = aefc_d4;
            if (this.name.equals("HIFAC_NONSTANDARD_AEFC_D5")) {
                aEFC_D5Bit2 = TVComponent.mFacFunction.getAEFC_D5Bit2();
            }
            int aEFC_D8Bit3210 = aEFC_D5Bit2;
            if (this.name.equals("HIFAC_NONSTANDARD_AEFC_D8")) {
                aEFC_D8Bit3210 = TVComponent.mFacFunction.getAEFC_D8Bit3210();
            }
            int aEFC_D9Bit0 = aEFC_D8Bit3210;
            if (this.name.equals("HIFAC_NONSTANDARD_AEFC_D9[0]")) {
                aEFC_D9Bit0 = TVComponent.mFacFunction.getAEFC_D9Bit0();
            }
            int aEFC_D7HighBoun = aEFC_D9Bit0;
            if (this.name.equals("HIFAC_NONSTANDARD_AEFC_D7_HIGH_BOUN")) {
                aEFC_D7HighBoun = TVComponent.mFacFunction.getAEFC_D7HighBoun();
            }
            int aEFC_D7LowBoun = aEFC_D7HighBoun;
            if (this.name.equals("HIFAC_NONSTANDARD_AEFC_D7_LOW_BOUN")) {
                aEFC_D7LowBoun = TVComponent.mFacFunction.getAEFC_D7LowBoun();
            }
            int aefc_a0 = aEFC_D7LowBoun;
            if (this.name.equals("HIFAC_NONSTANDARD_AEFC_A0")) {
                aefc_a0 = TVComponent.mFacFunction.getAEFC_A0();
            }
            int aefc_a1 = aefc_a0;
            if (this.name.equals("HIFAC_NONSTANDARD_AEFC_A1")) {
                aefc_a1 = TVComponent.mFacFunction.getAEFC_A1();
            }
            int aEFC_66Bit76 = aefc_a1;
            if (this.name.equals("HIFAC_NONSTANDARD_AEFC_66")) {
                aEFC_66Bit76 = TVComponent.mFacFunction.getAEFC_66Bit76();
            }
            int aEFC_6EBit7654 = aEFC_66Bit76;
            if (this.name.equals("HIFAC_NONSTANDARD_AEFC_6E")) {
                aEFC_6EBit7654 = TVComponent.mFacFunction.getAEFC_6EBit7654();
            }
            int aEFC_6EBit3210 = aEFC_6EBit7654;
            if (this.name.equals("HIFAC_NONSTANDARD_AEFC_6E_LOW")) {
                aEFC_6EBit3210 = TVComponent.mFacFunction.getAEFC_6EBit3210();
            }
            int aefc_43 = aEFC_6EBit3210;
            if (this.name.equals("HIFAC_NONSTANDARD_AEFC_43")) {
                aefc_43 = TVComponent.mFacFunction.getAEFC_43();
            }
            int aefc_44 = aefc_43;
            if (this.name.equals("HIFAC_NONSTANDARD_AEFC_44")) {
                aefc_44 = TVComponent.mFacFunction.getAEFC_44();
            }
            int aefc_cb = aefc_44;
            if (this.name.equals("HIFAC_NONSTANDARD_AEFC_CB")) {
                aefc_cb = TVComponent.mFacFunction.getAEFC_CB();
            }
            int vdDspVersion = aefc_cb;
            if (this.name.equals("HIFAC_NONSTANDARD_VD_DSP_VERSION")) {
                vdDspVersion = TVComponent.mFacFunction.getVdDspVersion();
            }
            int audioHiDevMode = vdDspVersion;
            if (this.name.equals("HIFAC_NONSTANDARD_VUDIO_HIDEV_MODE")) {
                audioHiDevMode = TVComponent.mFacFunction.getAudioHiDevMode();
            }
            int audioNrThr = audioHiDevMode;
            if (this.name.equals("HIFAC_NONSTANDARD_AUDIO_NR_THR")) {
                audioNrThr = TVComponent.mFacFunction.getAudioNrThr();
            }
            int audioSifThreshold = audioNrThr;
            if (this.name.equals("HIFAC_NONSTANDARD_VAudio_Sif_Threshold")) {
                audioSifThreshold = TVComponent.mFacFunction.getAudioSifThreshold();
            }
            int audioDspVersion = audioSifThreshold;
            if (this.name.equals("HIFAC_NONSTANDARD_AUDIO_DSP_VERSION")) {
                audioDspVersion = TVComponent.mFacFunction.getAudioDspVersion();
            }
            int nvodProgramDisplay = audioDspVersion;
            if (this.name.equals("HIFAC_DTV_NONSTANDA_NVOD_PROG_DIS")) {
                nvodProgramDisplay = TVComponent.mFacFunction.getNvodProgramDisplay();
            }
            int programNumDisplay = nvodProgramDisplay;
            if (this.name.equals("HIFAC_DTV_NONSTANDA_PROG_NUM_DIS")) {
                programNumDisplay = TVComponent.mFacFunction.getProgramNumDisplay();
            }
            int userOrderSet = programNumDisplay;
            if (this.name.equals("HIFAC_DTV_NONSTANDA_USER_ORDER_SET")) {
                userOrderSet = TVComponent.mFacFunction.getUserOrderSet();
            }
            int areaControl = userOrderSet;
            if (this.name.equals("HIFAC_DTV_NONSTANDA_AREA_CONTROL_SET")) {
                areaControl = TVComponent.mFacFunction.getAreaControl();
            }
            int areaControlFrequence = areaControl;
            if (this.name.equals("HIFAC_DTV_NONSTANDA_AREA_CONTROL_USER")) {
                areaControlFrequence = TVComponent.mFacFunction.getAreaControlFrequence();
            }
            int overScanTop = areaControlFrequence;
            if (this.name.equals("HIFAC_OVERSCAN_TOP")) {
                overScanTop = TVComponent.mFacFunction.getOverScanTop();
            }
            int overScanBottom = overScanTop;
            if (this.name.equals("HIFAC_OVERSCAN_BUTTOM")) {
                overScanBottom = TVComponent.mFacFunction.getOverScanBottom();
            }
            int overScanLeft = overScanBottom;
            if (this.name.equals("HIFAC_OVERSCAN_LEFT")) {
                overScanLeft = TVComponent.mFacFunction.getOverScanLeft();
            }
            int overScanRight = overScanLeft;
            if (this.name.equals("HIFAC_OVERSCAN_RIGHT")) {
                overScanRight = TVComponent.mFacFunction.getOverScanRight();
            }
            int nonstandardDescramble = overScanRight;
            if (this.name.equals("HIFAC_DESCRAMBLEBOX")) {
                nonstandardDescramble = TVComponent.mFacFunction.getNonstandardDescramble();
            }
            int nonstandardDescrambleplus = nonstandardDescramble;
            if (this.name.equals("HIFAC_DESCRAMBLEBOX_TVD")) {
                nonstandardDescrambleplus = TVComponent.mFacFunction.getNonstandardDescrambleplus();
            }
            int dmpMode = nonstandardDescrambleplus;
            if (this.name.equals("HIFAC_DMP_SETTINGS")) {
                dmpMode = TVComponent.mFacFunction.getDmpMode();
            }
            int vcomVoltage2 = dmpMode;
            if (this.name.equals("FACTORY_OPTION_VCOM_VOLTAGE")) {
                vcomVoltage2 = FacPropertyDemo.HIFAC_DOUBLE_CELL_SUPPORT ? TVComponent.mFacFunction.getVcomVoltage2(1) : TVComponent.mFacFunction.getVcomVoltage();
            }
            int vcomVoltage3 = vcomVoltage2;
            if (this.name.equals("FACTORY_OPTION_VCOM2_VOLTAGE")) {
                vcomVoltage3 = TVComponent.mFacFunction.getVcomVoltage2(2);
            }
            int iOrdinal18 = vcomVoltage3;
            if (this.name.equals("FACTORY_DTVTYPE")) {
                Log.d("Fac_TVComponent", "get dtv type=" + TVComponent.mFacFunction.getDtvType().ordinal());
                iOrdinal18 = TVComponent.mFacFunction.getDtvType().ordinal();
            }
            int uartSwitchStatus = iOrdinal18;
            if (this.name.equals("HIFAC_UART_SWITCH")) {
                uartSwitchStatus = TVComponent.mFacFunction.getUartSwitchStatus();
            }
            int authMode = uartSwitchStatus;
            if (this.name.equals("FACTORY_ACCREDITATION")) {
                authMode = TVComponent.mFacFunction.getAuthMode();
            }
            int userAgingSwitchStatus = authMode;
            if (this.name.equals("HIFAC_USER_AGING_SWITCH")) {
                userAgingSwitchStatus = TVComponent.mFacFunction.getUserAgingSwitchStatus();
            }
            int maxxBassFrequency = userAgingSwitchStatus;
            if (this.name.equals("HIFAC_MAXXBASS_FREQUENCY")) {
                maxxBassFrequency = TVComponent.mFacFunction.getMaxxBassFrequency();
            }
            int maxxTrebleFrequency = maxxBassFrequency;
            if (this.name.equals("HIFAC_MAXXTREBLE_FREQUENCY")) {
                maxxTrebleFrequency = TVComponent.mFacFunction.getMaxxTrebleFrequency();
            }
            int maxx3DOption = maxxTrebleFrequency;
            if (this.name.equals("HIFAC_MAXX3D_OPTION")) {
                maxx3DOption = TVComponent.mFacFunction.getMaxx3DOption();
            }
            ?? sensorEnableStatus = maxx3DOption;
            if (this.name.equals("HIFAC_LIGHT_SENSOR_ENABLE")) {
                sensorEnableStatus = TVComponent.mFacFunction.getSensorEnableStatus();
            }
            ?? audHpfStatus = sensorEnableStatus;
            if (this.name.equals("HIFAC_AUD_HPF")) {
                audHpfStatus = TVComponent.mFacFunction.getAudHpfStatus();
            }
            ?? audPeqEnableStatus = audHpfStatus;
            if (this.name.equals("HIFAC_AUD_PEQ_ENABLE")) {
                audPeqEnableStatus = TVComponent.mFacFunction.getAudPeqEnableStatus();
            }
            ?? audSocDrcEnableStatus = audPeqEnableStatus;
            if (this.name.equals("HIFAC_AUD_SOC_DRC_ENABLE")) {
                audSocDrcEnableStatus = TVComponent.mFacFunction.getAudSocDrcEnableStatus();
            }
            ?? lightSensorIndex = audSocDrcEnableStatus;
            if (this.name.equals("HIFAC_LIGHT_SENSOR_INDEX0")) {
                lightSensorIndex = TVComponent.mFacFunction.getLightSensorIndex();
            }
            ?? lightSensorCurve = lightSensorIndex;
            if (this.name.equals("HIFAC_LIGHT_SENSOR_VALUE0")) {
                lightSensorCurve = TVComponent.mFacFunction.getLightSensorCurve(0, 0);
            }
            ?? lightSensorCurve2 = lightSensorCurve;
            if (this.name.equals("FACTORY_LUX_VALUE0")) {
                lightSensorCurve2 = TVComponent.mFacFunction.getLightSensorCurve(1, 0);
            }
            ?? lightSensorCurve3 = lightSensorCurve2;
            if (this.name.equals("HIFAC_PWM0")) {
                lightSensorCurve3 = TVComponent.mFacFunction.getLightSensorCurve(2, 0);
            }
            ?? lightSensorCurve4 = lightSensorCurve3;
            if (this.name.equals("HIFAC_LIGHT_SENSOR_VALUE1")) {
                lightSensorCurve4 = TVComponent.mFacFunction.getLightSensorCurve(0, 1);
            }
            ?? lightSensorCurve5 = lightSensorCurve4;
            if (this.name.equals("FACTORY_LUX_VALUE1")) {
                lightSensorCurve5 = TVComponent.mFacFunction.getLightSensorCurve(1, 1);
            }
            ?? lightSensorCurve6 = lightSensorCurve5;
            if (this.name.equals("HIFAC_PWM1")) {
                lightSensorCurve6 = TVComponent.mFacFunction.getLightSensorCurve(2, 1);
            }
            ?? lightSensorCurve7 = lightSensorCurve6;
            if (this.name.equals("HIFAC_LIGHT_SENSOR_VALUE2")) {
                lightSensorCurve7 = TVComponent.mFacFunction.getLightSensorCurve(0, 2);
            }
            ?? lightSensorCurve8 = lightSensorCurve7;
            if (this.name.equals("FACTORY_LUX_VALUE2")) {
                lightSensorCurve8 = TVComponent.mFacFunction.getLightSensorCurve(1, 2);
            }
            ?? lightSensorCurve9 = lightSensorCurve8;
            if (this.name.equals("HIFAC_PWM2")) {
                lightSensorCurve9 = TVComponent.mFacFunction.getLightSensorCurve(2, 2);
            }
            ?? lightSensorCurve10 = lightSensorCurve9;
            if (this.name.equals("HIFAC_LIGHT_SENSOR_VALUE3")) {
                lightSensorCurve10 = TVComponent.mFacFunction.getLightSensorCurve(0, 3);
            }
            ?? lightSensorCurve11 = lightSensorCurve10;
            if (this.name.equals("FACTORY_LUX_VALUE3")) {
                lightSensorCurve11 = TVComponent.mFacFunction.getLightSensorCurve(1, 3);
            }
            ?? lightSensorCurve12 = lightSensorCurve11;
            if (this.name.equals("HIFAC_PWM3")) {
                lightSensorCurve12 = TVComponent.mFacFunction.getLightSensorCurve(2, 3);
            }
            ?? lightSensorCurve13 = lightSensorCurve12;
            if (this.name.equals("HIFAC_LIGHT_SENSOR_VALUE4")) {
                lightSensorCurve13 = TVComponent.mFacFunction.getLightSensorCurve(0, 4);
            }
            ?? lightSensorCurve14 = lightSensorCurve13;
            if (this.name.equals("FACTORY_LUX_VALUE4")) {
                lightSensorCurve14 = TVComponent.mFacFunction.getLightSensorCurve(1, 4);
            }
            ?? lightSensorCurve15 = lightSensorCurve14;
            if (this.name.equals("HIFAC_PWM4")) {
                lightSensorCurve15 = TVComponent.mFacFunction.getLightSensorCurve(2, 4);
            }
            ?? lightSensorCurve16 = lightSensorCurve15;
            if (this.name.equals("HIFAC_LIGHT_SENSOR_VALUE5")) {
                lightSensorCurve16 = TVComponent.mFacFunction.getLightSensorCurve(0, 5);
            }
            ?? lightSensorCurve17 = lightSensorCurve16;
            if (this.name.equals("FACTORY_LUX_VALUE5")) {
                lightSensorCurve17 = TVComponent.mFacFunction.getLightSensorCurve(1, 5);
            }
            ?? lightSensorCurve18 = lightSensorCurve17;
            if (this.name.equals("HIFAC_PWM5")) {
                lightSensorCurve18 = TVComponent.mFacFunction.getLightSensorCurve(2, 5);
            }
            ?? lightSensorCurve19 = lightSensorCurve18;
            if (this.name.equals("HIFAC_LIGHT_SENSOR_VALUE6")) {
                lightSensorCurve19 = TVComponent.mFacFunction.getLightSensorCurve(0, 6);
            }
            ?? lightSensorCurve20 = lightSensorCurve19;
            if (this.name.equals("FACTORY_LUX_VALUE6")) {
                lightSensorCurve20 = TVComponent.mFacFunction.getLightSensorCurve(1, 6);
            }
            ?? lightSensorCurve21 = lightSensorCurve20;
            if (this.name.equals("HIFAC_PWM6")) {
                lightSensorCurve21 = TVComponent.mFacFunction.getLightSensorCurve(2, 6);
            }
            ?? lightSensorCurve22 = lightSensorCurve21;
            if (this.name.equals("HIFAC_LIGHT_SENSOR_VALUE7")) {
                lightSensorCurve22 = TVComponent.mFacFunction.getLightSensorCurve(0, 7);
            }
            ?? lightSensorCurve23 = lightSensorCurve22;
            if (this.name.equals("FACTORY_LUX_VALUE7")) {
                lightSensorCurve23 = TVComponent.mFacFunction.getLightSensorCurve(1, 7);
            }
            ?? lightSensorCurve24 = lightSensorCurve23;
            if (this.name.equals("HIFAC_PWM7")) {
                lightSensorCurve24 = TVComponent.mFacFunction.getLightSensorCurve(2, 7);
            }
            ?? autoVolumeCtrlEnable = lightSensorCurve24;
            if (this.name.equals("HIFAC_AUD_AUTO_VOLUEM_CTRL_ENABLE")) {
                autoVolumeCtrlEnable = TVComponent.mFacFunction.getAutoVolumeCtrlEnable();
            }
            ?? oledTpcValue = autoVolumeCtrlEnable;
            if (this.name.equals("HIFAC_OLED_TPC_ENABLE")) {
                oledTpcValue = TVComponent.mFacFunction.getOledTpcValue();
            }
            ?? oledPixelShiftShapeValue = oledTpcValue;
            if (this.name.equals("HIFAC_OLED_PIXEL_MOVE_SHAPE")) {
                oledPixelShiftShapeValue = TVComponent.mFacFunction.getOledPixelShiftShapeValue();
            }
            ?? oledPixelShiftTimerValue = oledPixelShiftShapeValue;
            if (this.name.equals("HIFAC_OLED_PIXEL_MOVE_TIMER")) {
                oledPixelShiftTimerValue = TVComponent.mFacFunction.getOledPixelShiftTimerValue();
            }
            ?? oledPixelShiftTypeValue = oledPixelShiftTimerValue;
            if (this.name.equals("HIFAC_OLED_PIXEL_MOVE_TYPE")) {
                oledPixelShiftTypeValue = TVComponent.mFacFunction.getOledPixelShiftTypeValue();
            }
            ?? oledLeaValue = oledPixelShiftTypeValue;
            if (this.name.equals("HIFAC_OLED_LEA_ENABLE")) {
                oledLeaValue = TVComponent.mFacFunction.getOledLeaValue();
            }
            ?? oledCpcValue = oledLeaValue;
            if (this.name.equals("HIFAC_OLED_CPC_ENABLE")) {
                oledCpcValue = TVComponent.mFacFunction.getOledCpcValue();
            }
            ?? oledLbc2Value = oledCpcValue;
            if (this.name.equals("HIFAC_OLED_LBC2_ENABLE")) {
                oledLbc2Value = TVComponent.mFacFunction.getOledLbc2Value();
            }
            ?? oledOrbitValue = oledLbc2Value;
            if (this.name.equals("HIFAC_OLED_ORBIT_ENABLE")) {
                oledOrbitValue = TVComponent.mFacFunction.getOledOrbitValue();
            }
            ?? oledLbaValue = oledOrbitValue;
            if (this.name.equals("HIFAC_OLED_LBA_ENABLE")) {
                oledLbaValue = TVComponent.mFacFunction.getOledLbaValue();
            }
            ?? oledHdrValue = oledLbaValue;
            if (this.name.equals("HIFAC_OLED_HDR_ENABLE")) {
                oledHdrValue = TVComponent.mFacFunction.getOledHdrValue();
            }
            ?? micDb = oledHdrValue;
            if (this.name.equals("HIFAC_MIC_YIZHIXING_DB")) {
                micDb = TVComponent.mFacFunction.getMicDb();
            }
            ?? micRecordTime = micDb;
            if (this.name.equals("HIFAC_MIC_RECORD_TIME")) {
                micRecordTime = TVComponent.mFacFunction.getMicRecordTime();
            }
            ?? dolbyIQUserBinEnable = micRecordTime;
            if (this.name.equals("FACTORY_Dolby_IQ")) {
                dolbyIQUserBinEnable = TVComponent.mFacFunction.getDolbyIQUserBinEnable();
            }
            ?? demuraSwitchStatus = dolbyIQUserBinEnable;
            if (this.name.equals("HIFAC_DEMURA_SWITCH")) {
                demuraSwitchStatus = TVComponent.mFacFunction.getDemuraSwitchStatus();
            }
            ?? lnb = demuraSwitchStatus;
            if (this.name.equals("HIFAC_TVS_LNB")) {
                lnb = TVComponent.mFacFunction.getLNB();
            }
            ?? laserEyeProtectSwitch = lnb;
            if (this.name.equals("FACTORY_EYE_PROTECT")) {
                laserEyeProtectSwitch = TVComponent.mFacFunction.getLaserEyeProtectSwitch();
            }
            ?? modelSelection = laserEyeProtectSwitch;
            if (this.name.equals("HIFAC_MODE_SELECTION")) {
                modelSelection = TVComponent.mFacFunction.getModelSelection();
            }
            ?? modeValue = modelSelection;
            if (this.name.equals("HIFAC_MODE_SELECTION_VALUE")) {
                modeValue = TVComponent.mFacFunction.getModeValue();
            }
            ?? laserForeignObjectDetectSwitch = modeValue;
            if (this.name.equals("FACTORY_FOREIGN_OBJECT_DETECT_SWITCH")) {
                laserForeignObjectDetectSwitch = TVComponent.mFacFunction.getLaserForeignObjectDetectSwitch();
            }
            int laserMovableCoverStep = laserForeignObjectDetectSwitch;
            if (this.name.equals("FACTORY_SLIDING_COVER_GAP_ADJUST")) {
                laserMovableCoverStep = TVComponent.mFacFunction.getLaserMovableCoverStep();
            }
            return Integer.valueOf(laserMovableCoverStep);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.hisense.uiadapter.TVOption
        public Integer getMax() {
            if (this.name.equals("HIFAC_RDRV") || this.name.equals("HIFAC_GDRV") || this.name.equals("HIFAC_BDRV")) {
                if (getStep().intValue() != 1) {
                    return Integer.valueOf((getStep().intValue() * (this.max - this.min)) + 0);
                }
                return 255;
            }
            if (this.name.equals("HIFAC_RCUT") || this.name.equals("HIFAC_GCUT") || this.name.equals("HIFAC_BCUT")) {
                if (getStep().intValue() != 1) {
                    return Integer.valueOf((getStep().intValue() * (this.max - this.min)) + 0);
                }
                return 511;
            }
            if (this.name.equals("HIFAC_RDRV_OFFSET") || this.name.equals("HIFAC_GDRV_OFFSET") || this.name.equals("HIFAC_BDRV_OFFSET")) {
                if (getStep().intValue() != 1) {
                    return Integer.valueOf((getStep().intValue() * (this.max - this.min)) + 0);
                }
                return 511;
            }
            if (this.name.equals("HIFAC_RGAIN_DELTA") || this.name.equals("HIFAC_GGAIN_DELTA") || this.name.equals("HIFAC_BGAIN_DELTA")) {
                if (getStep().intValue() != 1) {
                    return Integer.valueOf((getStep().intValue() * (this.max - this.min)) - 30);
                }
                return 30;
            }
            if (this.name.equals("HIFAC_ROFFSET_DELTA") || this.name.equals("HIFAC_GOFFSET_DELTA") || this.name.equals("HIFAC_BOFFSET_DELTA")) {
                if (getStep().intValue() != 1) {
                    return Integer.valueOf((getStep().intValue() * (this.max - this.min)) - 30);
                }
                return 30;
            }
            if (this.name.equals("FACTORY_CT_HUE") || this.name.equals("FACTORY_CT_SATURATION") || this.name.equals("FACTORY_CT_BRIGHTNESS")) {
                if (getStep().intValue() != 1) {
                    return Integer.valueOf((getStep().intValue() * (this.max - this.min)) - 10);
                }
                return 10;
            }
            if (this.name.equals("HIFAC_RCUT_OFFSET") || this.name.equals("HIFAC_GCUT_OFFSET") || this.name.equals("HIFAC_BCUT_OFFSET")) {
                if (getStep().intValue() != 1) {
                    return Integer.valueOf((getStep().intValue() * (this.max - this.min)) + 0);
                }
                return 511;
            }
            if (this.name.equals("HIFAC_PICTUREMODE_BRIGHTNESS")) {
                if (getStep().intValue() != 1) {
                    return Integer.valueOf((getStep().intValue() * (this.max - this.min)) + 0);
                }
                return 100;
            }
            if (this.name.equals("HIFAC_PICTUREMODE_CONTRAST")) {
                if (getStep().intValue() != 1) {
                    return Integer.valueOf((getStep().intValue() * (this.max - this.min)) + 0);
                }
                return 100;
            }
            if (this.name.equals("HIFAC_PICTUREMODE_COLOR")) {
                if (getStep().intValue() != 1) {
                    return Integer.valueOf((getStep().intValue() * (this.max - this.min)) + 0);
                }
                return 100;
            }
            if (this.name.equals("HIFAC_PICTUREMODE_SHARPNESS")) {
                if (getStep().intValue() != 1) {
                    return Integer.valueOf((getStep().intValue() * (this.max - this.min)) + 0);
                }
                return Integer.valueOf(FacPropertyDemo.HIFAC_MAX_SHARPNESS_UI);
            }
            if (this.name.equals("HIFAC_PICTUREMODE_BACKLIGHT")) {
                if (FacPropertyDemo.HIFAC_IS_LASER4K) {
                    if (getStep().intValue() != 1) {
                        return Integer.valueOf((getStep().intValue() * (this.max - this.min)) + 0);
                    }
                    return 10;
                }
                if (getStep().intValue() != 1) {
                    return Integer.valueOf((getStep().intValue() * (this.max - this.min)) + 0);
                }
                return 100;
            }
            if (this.name.equals("HIFAC_EQUALIZE_120HZ") || this.name.equals("HIFAC_EQUALIZE_500HZ") || this.name.equals("HIFAC_EQUALIZE_1500HZ") || this.name.equals("HIFAC_EQUALIZE_5KHZ") || this.name.equals("HIFAC_EQUALIZE_10KHZ") || this.name.equals("HIFAC_EQUALIZE_20KHZ") || this.name.equals("HIFAC_EQUALIZE_30KHZ") || this.name.equals("HIFAC_EQUALIZE_40KHZ") || this.name.equals("HIFAC_EQUALIZE_50KHZ") || this.name.equals("HIFAC_EQUALIZE_120HZ_NEW") || this.name.equals("HIFAC_EQUALIZE_250HZ") || this.name.equals("HIFAC_EQUALIZE_600HZ") || this.name.equals("HIFAC_EQUALIZE_1KHZ") || this.name.equals("HIFAC_EQUALIZE_2.5KHZ") || this.name.equals("HIFAC_EQUALIZE_6KHZ") || this.name.equals("HIFAC_EQUALIZE_10KHZ_NEW")) {
                if (getStep().intValue() != 1) {
                    return Integer.valueOf((getStep().intValue() * (this.max - this.min)) - 10);
                }
                return 10;
            }
            if (this.name.equals("HIFAC_PRESCALE")) {
                return Integer.valueOf(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_MSGQ_EMPTY);
            }
            if (this.name.equals("HIFAC_PICTURECURVE_CURVE0") || this.name.equals("HIFAC_PICTURECURVE_CURVE50") || this.name.equals("HIFAC_PICTURECURVE_CURVE100")) {
                return Integer.valueOf(TVComponent.mFacFunction.getPictureCurveMaximum());
            }
            if (TVComponent.getPictureCurvePointCount() == 5 && (this.name.equals("HIFAC_PICTURECURVE_CURVE25") || this.name.equals("HIFAC_PICTURECURVE_CURVE75"))) {
                return Integer.valueOf(TVComponent.mFacFunction.getPictureCurveMaximum());
            }
            if (this.name.equals("HIFAC_AUDIOCURVE_CURVE1") || this.name.equals("HIFAC_AUDIOCURVE_CURVE25") || this.name.equals("HIFAC_AUDIOCURVE_CURVE50") || this.name.equals("HIFAC_AUDIOCURVE_CURVE75") || this.name.equals("HIFAC_AUDIOCURVE_CURVE100") || this.name.equals("HIFAC_AUDIOCURVE_CURVE1_NEW") || this.name.equals("HIFAC_AUDIOCURVE_CURVE10_NEW") || this.name.equals("HIFAC_AUDIOCURVE_CURVE25_NEW") || this.name.equals("HIFAC_AUDIOCURVE_CURVE50_NEW") || this.name.equals("HIFAC_AUDIOCURVE_CURVE75_NEW") || this.name.equals("HIFAC_AUDIOCURVE_CURVE100_NEW")) {
                return Integer.valueOf(TVComponent.mFacFunction.getSoundVolCurveMax());
            }
            if (this.name.equals("HIFAC_PEQ_1_FREQ") || this.name.equals("HIFAC_PEQ_2_FREQ") || this.name.equals("HIFAC_PEQ_3_FREQ") || this.name.equals("HIFAC_PEQ_4_FREQ") || this.name.equals("HIFAC_PEQ_5_FREQ") || this.name.equals("HIFAC_PEQ_6_FREQ") || this.name.equals("HIFAC_PEQ_7_FREQ") || this.name.equals("HIFAC_PEQ_8_FREQ")) {
                return Integer.valueOf(FacPropertyDemo.HIFAC_MAX_FREQ);
            }
            if (this.name.equals("HIFAC_PEQ_1_GAIN") || this.name.equals("HIFAC_PEQ_2_GAIN") || this.name.equals("HIFAC_PEQ_3_GAIN") || this.name.equals("HIFAC_PEQ_4_GAIN") || this.name.equals("HIFAC_PEQ_5_GAIN") || this.name.equals("HIFAC_PEQ_6_GAIN") || this.name.equals("HIFAC_PEQ_7_GAIN") || this.name.equals("HIFAC_PEQ_8_GAIN")) {
                return Integer.valueOf(FacPropertyDemo.HIFAC_MAX_GAIN);
            }
            if (this.name.equals("HIFAC_PEQ_1_Q") || this.name.equals("HIFAC_PEQ_2_Q") || this.name.equals("HIFAC_PEQ_3_Q") || this.name.equals("HIFAC_PEQ_4_Q") || this.name.equals("HIFAC_PEQ_5_Q") || this.name.equals("HIFAC_PEQ_6_Q") || this.name.equals("HIFAC_PEQ_7_Q") || this.name.equals("HIFAC_PEQ_8_Q")) {
                return Integer.valueOf(FacPropertyDemo.HIFAC_MAX_Q);
            }
            if (this.name.equals("HIFAC_PEQ_1_STEP") || this.name.equals("HIFAC_PEQ_2_STEP") || this.name.equals("HIFAC_PEQ_3_STEP") || this.name.equals("HIFAC_PEQ_4_STEP") || this.name.equals("HIFAC_PEQ_5_STEP") || this.name.equals("HIFAC_PEQ_6_STEP") || this.name.equals("HIFAC_PEQ_7_STEP") || this.name.equals("HIFAC_PEQ_8_STEP")) {
                return Integer.valueOf(FacPropertyDemo.HIFAC_MAX_STEP);
            }
            if (this.name.equals("HIFAC_HPF_FREQUENCY")) {
                return Integer.valueOf(FacPropertyDemo.HIFAC_MAX_HPF_FREQUENCY);
            }
            if (this.name.equals("HIFAC_SOC_DRC")) {
                return Integer.valueOf(FacPropertyDemo.HIFAC_MAX_SOC_DRC);
            }
            if (this.name.equals("HIFAC_AUD_AUTO_VOLUEM_CTRL_THRESHOLD")) {
                return Integer.valueOf(FacPropertyDemo.HIFAC_MAX_VOLUME_CTRL_THRESHOLD);
            }
            if (this.name.equals("HIFAC_VOLUME")) {
                if (getStep().intValue() != 1) {
                    return Integer.valueOf((getStep().intValue() * (this.max - this.min)) + 0);
                }
                return 100;
            }
            if (this.name.equals("HIFAC_OVERSCAN_HPOS") || this.name.equals("HIFAC_OVERSCAN_VPOS")) {
                if (getStep().intValue() != 1) {
                    return Integer.valueOf((getStep().intValue() * (this.max - this.min)) + 0);
                }
                return 1000;
            }
            if (this.name.equals("HIFAC_OVERSCAN_HSIZE") || this.name.equals("HIFAC_OVERSCAN_VSIZE")) {
                if (getStep().intValue() != 1) {
                    return Integer.valueOf((getStep().intValue() * (this.max - this.min)) + 0);
                }
                return 1000;
            }
            if (this.name.equals("HIFAC_BALANCE")) {
                return Integer.valueOf(FacPropertyDemo.HIFAC_MAX_BALANCE);
            }
            if (this.name.equals("HIFAC_BALANCE_DOUBLE_AMP")) {
                return Integer.valueOf(FacPropertyDemo.HIFAC_MAX_DOUBLE_AMP_BALANCE);
            }
            if (this.name.equals("HIFAC_BALANCE_MULTI_AMP")) {
                return Integer.valueOf(FacPropertyDemo.HIFAC_MAX_MULTI_AMP_BALANCE);
            }
            if (this.name.equals("HIFAC_BALANCE_UNIV_MULTI_AMP")) {
                return Integer.valueOf(FacPropertyDemo.HIFAC_MAX_UNIV_MULTI_AMP_BALANCE);
            }
            if (this.name.equals("HIFAC_TVS_BALANCE_LIKE_550")) {
                return Integer.valueOf(FacPropertyDemo.HIFAC_MAX_TWEETER_BALANCE);
            }
            if (this.name.equals("HIFAC_TVS_BALANCE_LIKE_650")) {
                return Integer.valueOf(FacPropertyDemo.HIFAC_MAX_TWEETER_WOOFER_BALANCE);
            }
            if (this.name.equals("HIFAC_TVS_BALANCE_LIKE_850")) {
                return Integer.valueOf(FacPropertyDemo.HIFAC_MAX_BALANCE_LIKE_850);
            }
            if (this.name.equals("HIFAC_ADC_RGAIN") || this.name.equals("HIFAC_ADC_GGAIN") || this.name.equals("HIFAC_ADC_BGAIN") || this.name.equals("HIFAC_ADC_ROFFSET") || this.name.equals("HIFAC_ADC_GOFFSET") || this.name.equals("HIFAC_ADC_BOFFSET")) {
                return 255;
            }
            if (this.name.equals("HIFAC_SSC_VBYONE_SPAN")) {
                return Integer.valueOf(FacPropertyDemo.HIFAC_MAX_VBYONE_SPAN);
            }
            if (this.name.equals("HIFAC_SSC_VBYONE_RANGE")) {
                return 50;
            }
            if (this.name.equals("HIFAC_SSC_VBYONE_MODULATION")) {
                return Integer.valueOf(FacPropertyDemo.HIFAC_MAX_VBYONE_MODU);
            }
            if (this.name.equals("HIFAC_SSC_DDR_SPAN")) {
                return Integer.valueOf(FacPropertyDemo.HIFAC_MAX_DDRSSC_SPAN);
            }
            if (this.name.equals("HIFAC_SSC_DDR_MODULATION")) {
                return Integer.valueOf(FacPropertyDemo.HIFAC_MAX_DDRSSC_MODU);
            }
            return Integer.valueOf(this.max);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.hisense.uiadapter.TVOption
        public Integer getMin() {
            if (this.name.equals("HIFAC_RDRV") || this.name.equals("HIFAC_GDRV") || this.name.equals("HIFAC_BDRV") || this.name.equals("HIFAC_RCUT") || this.name.equals("HIFAC_GCUT") || this.name.equals("HIFAC_BCUT")) {
                return 0;
            }
            if (this.name.equals("HIFAC_RGAIN_DELTA") || this.name.equals("HIFAC_GGAIN_DELTA") || this.name.equals("HIFAC_BGAIN_DELTA")) {
                return -30;
            }
            if (this.name.equals("HIFAC_ROFFSET_DELTA") || this.name.equals("HIFAC_GOFFSET_DELTA") || this.name.equals("HIFAC_BOFFSET_DELTA")) {
                return -30;
            }
            if (this.name.equals("HIFAC_PICTUREMODE_BRIGHTNESS") || this.name.equals("HIFAC_PICTUREMODE_CONTRAST") || this.name.equals("HIFAC_PICTUREMODE_COLOR") || this.name.equals("HIFAC_PICTUREMODE_SHARPNESS")) {
                return 0;
            }
            if (this.name.equals("HIFAC_PICTUREMODE_BACKLIGHT")) {
                if (FacPropertyDemo.HIFAC_IS_LASER4K) {
                }
                return 0;
            }
            if (this.name.equals("HIFAC_EQUALIZE_120HZ") || this.name.equals("HIFAC_EQUALIZE_500HZ") || this.name.equals("HIFAC_EQUALIZE_1500HZ") || this.name.equals("HIFAC_EQUALIZE_5KHZ") || this.name.equals("HIFAC_EQUALIZE_10KHZ") || this.name.equals("HIFAC_EQUALIZE_20KHZ") || this.name.equals("HIFAC_EQUALIZE_30KHZ") || this.name.equals("HIFAC_EQUALIZE_40KHZ") || this.name.equals("HIFAC_EQUALIZE_50KHZ") || this.name.equals("HIFAC_EQUALIZE_120HZ_NEW") || this.name.equals("HIFAC_EQUALIZE_250HZ") || this.name.equals("HIFAC_EQUALIZE_600HZ") || this.name.equals("HIFAC_EQUALIZE_1KHZ") || this.name.equals("HIFAC_EQUALIZE_2.5KHZ") || this.name.equals("HIFAC_EQUALIZE_6KHZ") || this.name.equals("HIFAC_EQUALIZE_10KHZ_NEW")) {
                return -10;
            }
            if (this.name.equals("HIFAC_PRESCALE")) {
                return 0;
            }
            if (this.name.equals("HIFAC_PICTURECURVE_CURVE0") || this.name.equals("HIFAC_PICTURECURVE_CURVE50") || this.name.equals("HIFAC_PICTURECURVE_CURVE100")) {
                return Integer.valueOf(TVComponent.mFacFunction.getPictureCurveMinimum());
            }
            if (TVComponent.getPictureCurvePointCount() == 5 && (this.name.equals("HIFAC_PICTURECURVE_CURVE25") || this.name.equals("HIFAC_PICTURECURVE_CURVE75"))) {
                return Integer.valueOf(TVComponent.mFacFunction.getPictureCurveMinimum());
            }
            if (this.name.equals("HIFAC_AUDIOCURVE_CURVE1") || this.name.equals("HIFAC_AUDIOCURVE_CURVE25") || this.name.equals("HIFAC_AUDIOCURVE_CURVE50") || this.name.equals("HIFAC_AUDIOCURVE_CURVE75") || this.name.equals("HIFAC_AUDIOCURVE_CURVE100") || this.name.equals("HIFAC_AUDIOCURVE_CURVE1_NEW") || this.name.equals("HIFAC_AUDIOCURVE_CURVE10_NEW") || this.name.equals("HIFAC_AUDIOCURVE_CURVE25_NEW") || this.name.equals("HIFAC_AUDIOCURVE_CURVE50_NEW") || this.name.equals("HIFAC_AUDIOCURVE_CURVE75_NEW") || this.name.equals("HIFAC_AUDIOCURVE_CURVE100_NEW")) {
                return Integer.valueOf(TVComponent.mFacFunction.getSoundVolCurveMin());
            }
            if (this.name.equals("HIFAC_PEQ_1_FREQ") || this.name.equals("HIFAC_PEQ_2_FREQ") || this.name.equals("HIFAC_PEQ_3_FREQ") || this.name.equals("HIFAC_PEQ_4_FREQ") || this.name.equals("HIFAC_PEQ_5_FREQ") || this.name.equals("HIFAC_PEQ_6_FREQ") || this.name.equals("HIFAC_PEQ_7_FREQ") || this.name.equals("HIFAC_PEQ_8_FREQ")) {
                return Integer.valueOf(FacPropertyDemo.HIFAC_MIN_FREQ);
            }
            if (this.name.equals("HIFAC_PEQ_1_GAIN") || this.name.equals("HIFAC_PEQ_2_GAIN") || this.name.equals("HIFAC_PEQ_3_GAIN") || this.name.equals("HIFAC_PEQ_4_GAIN") || this.name.equals("HIFAC_PEQ_5_GAIN") || this.name.equals("HIFAC_PEQ_6_GAIN") || this.name.equals("HIFAC_PEQ_7_GAIN") || this.name.equals("HIFAC_PEQ_8_GAIN")) {
                return Integer.valueOf(FacPropertyDemo.HIFAC_MIN_GAIN);
            }
            if (this.name.equals("HIFAC_PEQ_1_Q") || this.name.equals("HIFAC_PEQ_2_Q") || this.name.equals("HIFAC_PEQ_3_Q") || this.name.equals("HIFAC_PEQ_4_Q") || this.name.equals("HIFAC_PEQ_5_Q") || this.name.equals("HIFAC_PEQ_6_Q") || this.name.equals("HIFAC_PEQ_7_Q") || this.name.equals("HIFAC_PEQ_8_Q")) {
                return Integer.valueOf(FacPropertyDemo.HIFAC_MIN_Q);
            }
            if (this.name.equals("HIFAC_PEQ_1_STEP") || this.name.equals("HIFAC_PEQ_2_STEP") || this.name.equals("HIFAC_PEQ_3_STEP") || this.name.equals("HIFAC_PEQ_4_STEP") || this.name.equals("HIFAC_PEQ_5_STEP") || this.name.equals("HIFAC_PEQ_6_STEP") || this.name.equals("HIFAC_PEQ_7_STEP") || this.name.equals("HIFAC_PEQ_8_STEP")) {
                return Integer.valueOf(FacPropertyDemo.HIFAC_MIN_STEP);
            }
            if (this.name.equals("HIFAC_HPF_FREQUENCY")) {
                return Integer.valueOf(FacPropertyDemo.HIFAC_MIN_HPF_FREQUENCY);
            }
            if (this.name.equals("HIFAC_SOC_DRC")) {
                return Integer.valueOf(FacPropertyDemo.HIFAC_MIN_SOC_DRC);
            }
            if (this.name.equals("HIFAC_AUD_AUTO_VOLUEM_CTRL_THRESHOLD")) {
                return Integer.valueOf(FacPropertyDemo.HIFAC_MIN_VOLUME_CTRL_THRESHOLD);
            }
            if (!this.name.equals("HIFAC_VOLUME") && !this.name.equals("HIFAC_OVERSCAN_HPOS") && !this.name.equals("HIFAC_OVERSCAN_VPOS") && !this.name.equals("HIFAC_OVERSCAN_HSIZE") && !this.name.equals("HIFAC_OVERSCAN_VSIZE")) {
                if (this.name.equals("HIFAC_BALANCE")) {
                    return Integer.valueOf(FacPropertyDemo.HIFAC_MIN_BALANCE);
                }
                if (this.name.equals("HIFAC_BALANCE_DOUBLE_AMP")) {
                    return Integer.valueOf(FacPropertyDemo.HIFAC_MIN_DOUBLE_AMP_BALANCE);
                }
                if (this.name.equals("HIFAC_BALANCE_MULTI_AMP")) {
                    return Integer.valueOf(FacPropertyDemo.HIFAC_MIN_MULTI_AMP_BALANCE);
                }
                if (this.name.equals("HIFAC_BALANCE_UNIV_MULTI_AMP")) {
                    return Integer.valueOf(FacPropertyDemo.HIFAC_MIN_UNIV_MULTI_AMP_BALANCE);
                }
                if (this.name.equals("HIFAC_TVS_BALANCE_LIKE_550")) {
                    return Integer.valueOf(FacPropertyDemo.HIFAC_MIN_TWEETER_BALANCE);
                }
                if (this.name.equals("HIFAC_TVS_BALANCE_LIKE_650")) {
                    return Integer.valueOf(FacPropertyDemo.HIFAC_MIN_TWEETER_WOOFER_BALANCE);
                }
                if (this.name.equals("HIFAC_TVS_BALANCE_LIKE_850")) {
                    return Integer.valueOf(FacPropertyDemo.HIFAC_MIN_BALANCE_LIKE_850);
                }
                if (!this.name.equals("HIFAC_ADC_RGAIN") && !this.name.equals("HIFAC_ADC_GGAIN") && !this.name.equals("HIFAC_ADC_BGAIN") && !this.name.equals("HIFAC_ADC_ROFFSET") && !this.name.equals("HIFAC_ADC_GOFFSET") && !this.name.equals("HIFAC_ADC_BOFFSET")) {
                    if (this.name.equals("HIFAC_SSC_VBYONE_SPAN")) {
                        return 1;
                    }
                    if (this.name.equals("HIFAC_SSC_VBYONE_RANGE")) {
                        return 0;
                    }
                    if (this.name.equals("HIFAC_SSC_VBYONE_MODULATION")) {
                        return Integer.valueOf(FacPropertyDemo.HIFAC_MIN_VBYONE_MODU);
                    }
                    if (this.name.equals("HIFAC_SSC_DDR_SPAN")) {
                        return Integer.valueOf(FacPropertyDemo.HIFAC_MIN_DDRSSC_SPAN);
                    }
                    if (this.name.equals("HIFAC_SSC_DDR_MODULATION")) {
                        return Integer.valueOf(FacPropertyDemo.HIFAC_MIN_DDRSSC_MODU);
                    }
                    return Integer.valueOf(this.min);
                }
            }
            return 0;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.hisense.uiadapter.TVOption
        public Integer getStep() {
            int i;
            int i2;
            int i3;
            int i4;
            int i5;
            int i6;
            int i7;
            int i8;
            int i9;
            int i10;
            int i11;
            int i12;
            int i13;
            int i14;
            int i15;
            int i16;
            int i17;
            int i18;
            int i19;
            int i20;
            int i21;
            int i22;
            int i23;
            int i24;
            int i25;
            int i26;
            Log.i("Fac_TVComponent", "**min=" + this.min + " max=" + this.max);
            int step8 = ((this.name.equals("HIFAC_RDRV") || this.name.equals("HIFAC_GDRV") || this.name.equals("HIFAC_BDRV")) && (i = this.min) != (i2 = this.max)) ? 255 / (i2 - i) : 1;
            if ((this.name.equals("HIFAC_RDRV_OFFSET") || this.name.equals("HIFAC_GDRV_OFFSET") || this.name.equals("HIFAC_BDRV_OFFSET")) && (i3 = this.min) != (i4 = this.max)) {
                step8 = 511 / (i4 - i3);
            }
            if ((this.name.equals("HIFAC_RCUT_OFFSET") || this.name.equals("HIFAC_GCUT_OFFSET") || this.name.equals("HIFAC_BCUT_OFFSET")) && (i5 = this.min) != (i6 = this.max)) {
                step8 = 511 / (i6 - i5);
            }
            if (this.name.equals("HIFAC_PICTUREMODE_BRIGHTNESS") && (i25 = this.min) != (i26 = this.max)) {
                step8 = 100 / (i26 - i25);
                if (100 % (i26 - i25) != 0) {
                    step8++;
                }
            }
            if ((this.name.equals("FACTORY_CT_HUE") || this.name.equals("FACTORY_CT_SATURATION") || this.name.equals("FACTORY_CT_BRIGHTNESS")) && (i7 = this.min) != (i8 = this.max)) {
                step8 = 20 / (i8 - i7);
            }
            if (this.name.equals("HIFAC_PICTUREMODE_CONTRAST") && (i23 = this.min) != (i24 = this.max)) {
                step8 = 100 / (i24 - i23);
                if (100 % (i24 - i23) != 0) {
                    step8++;
                }
            }
            if (this.name.equals("HIFAC_PICTUREMODE_COLOR") && (i21 = this.min) != (i22 = this.max)) {
                step8 = 100 / (i22 - i21);
                if (100 % (i22 - i21) != 0) {
                    step8++;
                }
            }
            if (this.name.equals("HIFAC_PICTUREMODE_SHARPNESS") && (i19 = this.min) != (i20 = this.max)) {
                step8 = (FacPropertyDemo.HIFAC_MAX_SHARPNESS_UI + 0) / (i20 - i19);
            }
            if (this.name.equals("HIFAC_PICTUREMODE_BACKLIGHT")) {
                if (FacPropertyDemo.HIFAC_IS_LASER4K) {
                    int i27 = this.min;
                    int i28 = this.max;
                    if (i27 != i28) {
                        step8 = 10 / (i28 - i27);
                    }
                } else {
                    int i29 = this.min;
                    int i30 = this.max;
                    if (i29 != i30) {
                        step8 = 100 / (i30 - i29);
                    }
                }
            }
            if ((this.name.equals("HIFAC_EQUALIZE_10KHZ") || this.name.equals("HIFAC_EQUALIZE_5KHZ") || this.name.equals("HIFAC_EQUALIZE_1500HZ") || this.name.equals("HIFAC_EQUALIZE_500HZ") || this.name.equals("HIFAC_EQUALIZE_120HZ") || this.name.equals("HIFAC_EQUALIZE_20KHZ") || this.name.equals("HIFAC_EQUALIZE_30KHZ") || this.name.equals("HIFAC_EQUALIZE_40KHZ") || this.name.equals("HIFAC_EQUALIZE_50KHZ") || this.name.equals("HIFAC_EQUALIZE_120HZ_NEW") || this.name.equals("HIFAC_EQUALIZE_250HZ") || this.name.equals("HIFAC_EQUALIZE_600HZ") || this.name.equals("HIFAC_EQUALIZE_1KHZ") || this.name.equals("HIFAC_EQUALIZE_2.5KHZ") || this.name.equals("HIFAC_EQUALIZE_6KHZ") || this.name.equals("HIFAC_EQUALIZE_10KHZ_NEW")) && (i9 = this.min) != (i10 = this.max)) {
                step8 = 20 / (i10 - i9);
                Log.i("Fac_TVComponent", "get step=" + step8);
            }
            if (this.name.equals("HIFAC_VOLUME") && (i17 = this.min) != (i18 = this.max)) {
                step8 = 100 / (i18 - i17);
            }
            if ((this.name.equals("HIFAC_OVERSCAN_HPOS") || this.name.equals("HIFAC_OVERSCAN_VPOS")) && (i11 = this.min) != (i12 = this.max)) {
                step8 = 1000 / (i12 - i11);
            }
            if ((this.name.equals("HIFAC_OVERSCAN_HSIZE") || this.name.equals("HIFAC_OVERSCAN_VSIZE")) && (i13 = this.min) != (i14 = this.max)) {
                step8 = 1000 / (i14 - i13);
            }
            if ((this.name.equals("HIFAC_OVERSCAN_HSTART") || this.name.equals("HIFAC_OVERSCAN_VSTART")) && (i15 = this.min) != (i16 = this.max)) {
                step8 = 1000 / (i16 - i15);
            }
            if (this.name.equals("HIFAC_OVERSCAN_TOP") || this.name.equals("HIFAC_OVERSCAN_BUTTOM") || this.name.equals("HIFAC_OVERSCAN_LEFT") || this.name.equals("HIFAC_OVERSCAN_RIGHT")) {
                step8 = 1;
            }
            if (this.name.equals("FACTORY_OPTION_VCOM_VOLTAGE")) {
                step8 = FacPropertyDemo.HIFAC_STEP_VOM_VOLTAGE;
            }
            if (this.name.equals("FACTORY_OPTION_VCOM2_VOLTAGE")) {
                step8 = FacPropertyDemo.HIFAC_STEP_VOM_VOLTAGE;
            }
            if (this.name.equals("FACTORY_20POINT_LEVEL")) {
                step8 = 1;
            }
            if (this.name.equals("HIFAC_PEQ_1_STEP")) {
                step8 = TVComponent.mFacFunction.getSTEP1();
            }
            if (this.name.equals("HIFAC_PEQ_2_STEP")) {
                step8 = TVComponent.mFacFunction.getSTEP2();
            }
            if (this.name.equals("HIFAC_PEQ_3_STEP")) {
                step8 = TVComponent.mFacFunction.getSTEP3();
            }
            if (this.name.equals("HIFAC_PEQ_4_STEP")) {
                step8 = TVComponent.mFacFunction.getSTEP4();
            }
            if (this.name.equals("HIFAC_PEQ_5_STEP")) {
                step8 = TVComponent.mFacFunction.getSTEP5();
            }
            if (this.name.equals("HIFAC_PEQ_6_STEP")) {
                step8 = TVComponent.mFacFunction.getSTEP6();
            }
            if (this.name.equals("HIFAC_PEQ_7_STEP")) {
                step8 = TVComponent.mFacFunction.getSTEP7();
            }
            if (this.name.equals("HIFAC_PEQ_8_STEP")) {
                step8 = TVComponent.mFacFunction.getSTEP8();
            }
            if (this.name.equals("HIFAC_PEQ_1_FREQ")) {
                step8 = TVComponent.mFacFunction.getSTEP1();
            }
            if (this.name.equals("HIFAC_PEQ_2_FREQ")) {
                step8 = TVComponent.mFacFunction.getSTEP2();
            }
            if (this.name.equals("HIFAC_PEQ_3_FREQ")) {
                step8 = TVComponent.mFacFunction.getSTEP3();
            }
            if (this.name.equals("HIFAC_PEQ_4_FREQ")) {
                step8 = TVComponent.mFacFunction.getSTEP4();
            }
            if (this.name.equals("HIFAC_PEQ_5_FREQ")) {
                step8 = TVComponent.mFacFunction.getSTEP5();
            }
            if (this.name.equals("HIFAC_PEQ_6_FREQ")) {
                step8 = TVComponent.mFacFunction.getSTEP6();
            }
            if (this.name.equals("HIFAC_PEQ_7_FREQ")) {
                step8 = TVComponent.mFacFunction.getSTEP7();
            }
            if (this.name.equals("HIFAC_PEQ_8_FREQ")) {
                step8 = TVComponent.mFacFunction.getSTEP8();
            }
            return Integer.valueOf(step8 != 0 ? step8 : 1);
        }

        @Override // com.hisense.uiadapter.TVOption
        public void set(Integer num) {
            Log.i("Fac_TVComponent", "tvcomponent set name is " + this.name + "value is " + num.intValue());
            if (this.name.equals("HIFAC_DMP_SETTINGS")) {
                TVComponent.mFacFunction.setDMPMode(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_F7_NETWORK")) {
                TVComponent.mFacFunction.setnetworktype(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_RDRV")) {
                TVComponent.mFacFunction.setRDRV(TVComponent.this.mTVCalculator.convertDataRange(0, 255, this.min, this.max, num.intValue()));
                return;
            }
            if (this.name.equals("HIFAC_GDRV")) {
                TVComponent.mFacFunction.setGDRV(TVComponent.this.mTVCalculator.convertDataRange(0, 255, this.min, this.max, num.intValue()));
                return;
            }
            if (this.name.equals("HIFAC_BDRV")) {
                TVComponent.mFacFunction.setBDRV(TVComponent.this.mTVCalculator.convertDataRange(0, 255, this.min, this.max, num.intValue()));
                return;
            }
            if (this.name.equals("HIFAC_RCUT")) {
                TVComponent.mFacFunction.setRCUT(TVComponent.this.mTVCalculator.convertDataRange(0, 511, this.min, this.max, num.intValue()));
                return;
            }
            if (this.name.equals("HIFAC_GCUT")) {
                TVComponent.mFacFunction.setGCUT(TVComponent.this.mTVCalculator.convertDataRange(0, 511, this.min, this.max, num.intValue()));
                return;
            }
            if (this.name.equals("HIFAC_BCUT")) {
                TVComponent.mFacFunction.setBCUT(TVComponent.this.mTVCalculator.convertDataRange(0, 511, this.min, this.max, num.intValue()));
                return;
            }
            if (this.name.equals("HIFAC_RGAIN_DELTA")) {
                TVComponent.mFacFunction.set2PWBGainRedDelta(TVComponent.this.mTVCalculator.convertDataRange(-30, 30, this.min, this.max, num.intValue()));
                return;
            }
            if (this.name.equals("HIFAC_GGAIN_DELTA")) {
                TVComponent.mFacFunction.set2PWBGainGreenDelta(TVComponent.this.mTVCalculator.convertDataRange(-30, 30, this.min, this.max, num.intValue()));
                return;
            }
            if (this.name.equals("HIFAC_BGAIN_DELTA")) {
                TVComponent.mFacFunction.set2PWBGainBlueDelta(TVComponent.this.mTVCalculator.convertDataRange(-30, 30, this.min, this.max, num.intValue()));
                return;
            }
            if (this.name.equals("HIFAC_ROFFSET_DELTA")) {
                TVComponent.mFacFunction.set2PWBOffsetRedDelta(TVComponent.this.mTVCalculator.convertDataRange(-30, 30, this.min, this.max, num.intValue()));
                return;
            }
            if (this.name.equals("HIFAC_GOFFSET_DELTA")) {
                TVComponent.this.mTVCalculator.convertDataRange(-30, 30, this.min, this.max, num.intValue());
                TVComponent.mFacFunction.set2PWBOffsetGreenDelta(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_BOFFSET_DELTA")) {
                TVComponent.this.mTVCalculator.convertDataRange(-30, 30, this.min, this.max, num.intValue());
                TVComponent.mFacFunction.set2PWBOffsetBlueDelta(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_PNL_SEL")) {
                TVComponent.mFacFunction.setModelPanel(FacPropertyDemo.ModelPanelGroup.values()[num.intValue()]);
                return;
            }
            if (this.name.equals("HIFAC_TUNERMODE")) {
                TVComponent.mFacFunction.setTunerMode(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_2D3D")) {
                TVComponent.mFacFunction.set2Dor3D(FacPropertyDemo.DimensionGroup.values()[num.intValue()]);
                return;
            }
            if (this.name.equals("FACTORY_COLORTUNER")) {
                TVComponent.mFacFunction.setColorTuner(FacPropertyDemo.ColorTuner.values()[num.intValue()]);
                return;
            }
            if (this.name.equals("FACTORY_CT_HUE")) {
                TVComponent.mFacFunction.setCTHue(num.intValue());
                return;
            }
            if (this.name.equals("FACTORY_CT_SATURATION")) {
                TVComponent.mFacFunction.setCTSaturation(num.intValue());
                return;
            }
            if (this.name.equals("FACTORY_CT_BRIGHTNESS")) {
                TVComponent.mFacFunction.setCTBrightness(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_COLORTEMPERATURE")) {
                TVComponent.mFacFunction.setColorTemperature(FacPropertyDemo.ColorTemperature.values()[num.intValue()]);
                return;
            }
            if (this.name.equals("HIFAC_COLOR_TEMP")) {
                TVComponent.mFacFunction.setColorTemperature(FacPropertyDemo.ColorTemperature.values()[num.intValue()]);
                return;
            }
            if (this.name.equals("HIFAC_RDRV_OFFSET")) {
                TVComponent.mFacFunction.setRDRVOffset(TVComponent.this.mTVCalculator.convertDataRange(0, 511, this.min, this.max, num.intValue()));
                return;
            }
            if (this.name.equals("HIFAC_GDRV_OFFSET")) {
                TVComponent.mFacFunction.setGDRVOffset(TVComponent.this.mTVCalculator.convertDataRange(0, 511, this.min, this.max, num.intValue()));
                return;
            }
            if (this.name.equals("HIFAC_BDRV_OFFSET")) {
                TVComponent.mFacFunction.setBDRVOffset(TVComponent.this.mTVCalculator.convertDataRange(0, 511, this.min, this.max, num.intValue()));
                return;
            }
            if (this.name.equals("HIFAC_RCUT_OFFSET")) {
                TVComponent.mFacFunction.setRCUTOffset(TVComponent.this.mTVCalculator.convertDataRange(0, 511, this.min, this.max, num.intValue()));
                return;
            }
            if (this.name.equals("HIFAC_GCUT_OFFSET")) {
                TVComponent.mFacFunction.setGCUTOffset(TVComponent.this.mTVCalculator.convertDataRange(0, 511, this.min, this.max, num.intValue()));
                return;
            }
            if (this.name.equals("HIFAC_BCUT_OFFSET")) {
                TVComponent.mFacFunction.setBCUTOffset(TVComponent.this.mTVCalculator.convertDataRange(0, 511, this.min, this.max, num.intValue()));
                return;
            }
            if (this.name.equals("HIFAC_TOFAC")) {
                TVComponent.mFacFunction.setToFac(FacPropertyDemo.ToFacEnum.values()[num.intValue()]);
                return;
            }
            if (this.name.equals("3D_Sync_Delay")) {
                TVComponent.mFacFunction.set3D_Sync_Delay(num.intValue());
                return;
            }
            if (this.name.equals("FACTORY_OPTION_VCOM_VOLTAGE")) {
                TVComponent.mFacFunction.setVcomVoltage(num.intValue());
                return;
            }
            if (this.name.equals("FACTORY_OPTION_VCOM2_VOLTAGE")) {
                TVComponent.mFacFunction.setVcomVoltage(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_STATUS")) {
                Log.i("Fac_TVComponent", "tvcomponent set name is111 " + this.name + "    value is " + num.intValue());
                TVComponent.mFacFunction.setFactoryStatus(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_POWERMODE")) {
                TVComponent.mFacFunction.setPowerOnMode(FacPropertyDemo.PowerModeEnum.values()[num.intValue()]);
                return;
            }
            if (this.name.equals("HIFAC_SETCOUNTRY") || this.name.equals("HIFAC_SETCOUNTRY_FOR_REGION1") || this.name.equals("HIFAC_SETCOUNTRY_FOR_REGION2") || this.name.equals("HIFAC_SETCOUNTRY_FOR_REGION3") || this.name.equals("HIFAC_SETCOUNTRY_FOR_REGION4") || this.name.equals("HIFAC_SETCOUNTRY_FOR_REGION5") || this.name.equals("HIFAC_SETCOUNTRY_FOR_SA_REGION1") || this.name.equals("HIFAC_SETCOUNTRY_FOR_SA_REGION2") || this.name.equals("HIFAC_SETCOUNTRY_MONITOR")) {
                TVComponent.mFacFunction.setCountry(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_SETREGION")) {
                TVComponent.mFacFunction.setRegion(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_SETREGION_SA")) {
                TVComponent.mFacFunction.setRegion(num.intValue() + 5);
                return;
            }
            if (this.name.equals("HIFAC_SETLANGUAGE") || this.name.equals("HIFAC_SETLANGUAGE_FOR_REGION1") || this.name.equals("HIFAC_SETLANGUAGE_FOR_REGION2") || this.name.equals("HIFAC_SETLANGUAGE_FOR_REGION3") || this.name.equals("HIFAC_SETLANGUAGE_FOR_REGION4") || this.name.equals("HIFAC_SETLANGUAGE_FOR_REGION5") || this.name.equals("HIFAC_SETLANGUAGE_FOR_SA_REGION1") || this.name.equals("HIFAC_SETLANGUAGE_FOR_SA_REGION2") || this.name.equals("HIFAC_SETLANGUAGE_FOR_TWN") || this.name.equals("HIFAC_SETLANGUAGE_FOR_JPN") || this.name.equals("HIFAC_SETLANGUAGE_FOR_MONITOR")) {
                TVComponent.mFacFunction.setLanguage(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_SETLOGO") || this.name.equals("HIFAC_SETLOGO_FOR_EM_NON_BRAND_ISOLATION") || this.name.equals("HIFAC_SETLOGO_FOR_EM_BRAND_ISOLATION") || this.name.equals("HIFAC_SETLOGO_FOR_EM_SA") || this.name.equals("HIFAC_SETLOGO_FOR_SA") || this.name.equals("HIFAC_SETLOGO_FOR_JPN") || this.name.equals("HIFAC_SETLANGUAGE_FOR_HK") || this.name.equals("FACTORY_FUN_SETLOGO_FOR_MONITOR") || this.name.equals("HIFAC_SETLOGO_FOR_KOR")) {
                TVComponent.mFacFunction.setLogo(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_HOMEORTV")) {
                TVComponent.mFacFunction.setHomeorTV(FacPropertyDemo.HomeOrTVMode.values()[num.intValue()]);
                return;
            }
            if (this.name.equals("HIFAC_OSDLANGUAGE")) {
                TVComponent.mFacFunction.setOSDLanguage(FacPropertyDemo.OSDLanguage.values()[num.intValue()]);
                return;
            }
            if (this.name.equals("HIFAC_PICTUREMODE")) {
                if (FacPropertyDemo.HIFAC_MARKET_JP) {
                    TVComponent.mFacFunction.setPictureMode(FacPropertyDemo.PictureMode.STANDARD);
                    return;
                } else {
                    TVComponent.mFacFunction.setPictureMode(FacPropertyDemo.PictureMode.values()[num.intValue()]);
                    return;
                }
            }
            if (this.name.equals("HIFAC_PICTUREMODE_TW")) {
                TVComponent.mFacFunction.setPictureModeTW(FacPropertyDemo.PictureModeTW_Toshiba.values()[num.intValue()]);
                return;
            }
            if (this.name.equals("HIFAC_PICTUREMODE_BRIGHTNESS")) {
                TVComponent.mFacFunction.setBrightness(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_PICTUREMODE_CONTRAST")) {
                TVComponent.mFacFunction.setContrast(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_PICTUREMODE_COLOR")) {
                TVComponent.mFacFunction.setColor(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_PICTUREMODE_SHARPNESS")) {
                TVComponent.mFacFunction.setSharpness(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_PICTUREMODE_BACKLIGHT")) {
                TVComponent.mFacFunction.setBacklight(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_SOUNDMODE")) {
                TVComponent.mFacFunction.setAudioMode(FacPropertyDemo.AudioMode.values()[num.intValue()]);
                return;
            }
            if (this.name.equals("HIFAC_EQUALIZE_120HZ")) {
                int iConvertDataRange = TVComponent.this.mTVCalculator.convertDataRange(-10, 10, this.min, this.max, num.intValue());
                Log.i("Fac_TVComponent", "setEqualizer120Hz = " + iConvertDataRange);
                TVComponent.mFacFunction.setEqualizer120Hz(iConvertDataRange);
                return;
            }
            if (this.name.equals("HIFAC_EQUALIZE_500HZ")) {
                TVComponent.mFacFunction.setEqualizer500Hz(TVComponent.this.mTVCalculator.convertDataRange(-10, 10, this.min, this.max, num.intValue()));
                return;
            }
            if (this.name.equals("HIFAC_EQUALIZE_1500HZ")) {
                TVComponent.mFacFunction.setEqualizer1500Hz(TVComponent.this.mTVCalculator.convertDataRange(-10, 10, this.min, this.max, num.intValue()));
                return;
            }
            if (this.name.equals("HIFAC_EQUALIZE_5KHZ")) {
                TVComponent.mFacFunction.setEqualizer5KHz(TVComponent.this.mTVCalculator.convertDataRange(-10, 10, this.min, this.max, num.intValue()));
                return;
            }
            if (this.name.equals("HIFAC_EQUALIZE_10KHZ")) {
                TVComponent.mFacFunction.setEqualizer10KHz(TVComponent.this.mTVCalculator.convertDataRange(-10, 10, this.min, this.max, num.intValue()));
                return;
            }
            if (this.name.equals("HIFAC_EQUALIZE_20KHZ")) {
                TVComponent.mFacFunction.setEqualizer20KHz(TVComponent.this.mTVCalculator.convertDataRange(-10, 10, this.min, this.max, num.intValue()));
                return;
            }
            if (this.name.equals("HIFAC_EQUALIZE_30KHZ")) {
                TVComponent.mFacFunction.setEqualizer30KHz(TVComponent.this.mTVCalculator.convertDataRange(-10, 10, this.min, this.max, num.intValue()));
                return;
            }
            if (this.name.equals("HIFAC_EQUALIZE_40KHZ")) {
                TVComponent.mFacFunction.setEqualizer40KHz(TVComponent.this.mTVCalculator.convertDataRange(-10, 10, this.min, this.max, num.intValue()));
                return;
            }
            if (this.name.equals("HIFAC_EQUALIZE_50KHZ")) {
                TVComponent.mFacFunction.setEqualizer50KHz(TVComponent.this.mTVCalculator.convertDataRange(-10, 10, this.min, this.max, num.intValue()));
                return;
            }
            if (this.name.equals("HIFAC_EQUALIZE_120HZ_NEW")) {
                TVComponent.mFacFunction.setEqualizer120Hz_New(TVComponent.this.mTVCalculator.convertDataRange(-10, 10, this.min, this.max, num.intValue()));
                return;
            }
            if (this.name.equals("HIFAC_EQUALIZE_250HZ")) {
                TVComponent.mFacFunction.setEqualizer250Hz(TVComponent.this.mTVCalculator.convertDataRange(-10, 10, this.min, this.max, num.intValue()));
                return;
            }
            if (this.name.equals("HIFAC_EQUALIZE_600HZ")) {
                TVComponent.mFacFunction.setEqualizer600Hz(TVComponent.this.mTVCalculator.convertDataRange(-10, 10, this.min, this.max, num.intValue()));
                return;
            }
            if (this.name.equals("HIFAC_EQUALIZE_1KHZ")) {
                TVComponent.mFacFunction.setEqualizer1KHz(TVComponent.this.mTVCalculator.convertDataRange(-10, 10, this.min, this.max, num.intValue()));
                return;
            }
            if (this.name.equals("HIFAC_EQUALIZE_2.5KHZ")) {
                TVComponent.mFacFunction.setEqualizer2500Hz(TVComponent.this.mTVCalculator.convertDataRange(-10, 10, this.min, this.max, num.intValue()));
                return;
            }
            if (this.name.equals("HIFAC_EQUALIZE_6KHZ")) {
                TVComponent.mFacFunction.setEqualizer6KHz(TVComponent.this.mTVCalculator.convertDataRange(-10, 10, this.min, this.max, num.intValue()));
                return;
            }
            if (this.name.equals("HIFAC_EQUALIZE_10KHZ_NEW")) {
                TVComponent.mFacFunction.setEqualizer10KHz_New(TVComponent.this.mTVCalculator.convertDataRange(-10, 10, this.min, this.max, num.intValue()));
                return;
            }
            if (this.name.equals("HIFAC_PICTURECURVE_MODE")) {
                TVComponent.mFacFunction.setVideoCurveModeGroup(FacPropertyDemo.VideoCurveModeGroup.values()[num.intValue()]);
                return;
            }
            if (this.name.equals("HIFAC_PRESCALE")) {
                TVComponent.mFacFunction.setPrescale(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_PICTURECURVE_CURVE0")) {
                TVComponent.mFacFunction.setPictureCurve0(num.intValue());
                return;
            }
            if (TVComponent.getPictureCurvePointCount() == 5 && this.name.equals("HIFAC_PICTURECURVE_CURVE25")) {
                TVComponent.mFacFunction.setPictureCurve25(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_PICTURECURVE_CURVE50")) {
                TVComponent.mFacFunction.setPictureCurve50(num.intValue());
                return;
            }
            if (TVComponent.getPictureCurvePointCount() == 5 && this.name.equals("HIFAC_PICTURECURVE_CURVE75")) {
                TVComponent.mFacFunction.setPictureCurve75(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_PICTURECURVE_CURVE100")) {
                TVComponent.mFacFunction.setPictureCurve100(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_BRIGHT_20")) {
                TVComponent.mFacFunction.setPiCurveBrigth20(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_BRIGHT_80")) {
                TVComponent.mFacFunction.setPiCurveBrigth80(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_CONTRACT_20")) {
                TVComponent.mFacFunction.setPiCurveContract20(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_CONTRACT_80")) {
                TVComponent.mFacFunction.setPiCurveContract80(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_SATURATION_20")) {
                TVComponent.mFacFunction.setPiCurveSaturation20(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_SATURATION_80")) {
                TVComponent.mFacFunction.setPiCurveSaturation80(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_HUE_20")) {
                TVComponent.mFacFunction.setPiCurveHue20(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_HUE_80")) {
                TVComponent.mFacFunction.setPiCurveHue80(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_PICTURECURVE_BRIGHT_MIN")) {
                TVComponent.mFacFunction.setPiCurveBrigthMin(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_PICTURECURVE_BRIGHT_MID")) {
                TVComponent.mFacFunction.setPiCurveBrigthMid(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_PICTURECURVE_BRIGHT_MAX")) {
                TVComponent.mFacFunction.setPiCurveBrigthMax(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_PICTURECURVE_CONTRACT_MIN")) {
                TVComponent.mFacFunction.setPiCurveContractMin(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_PICTURECURVE_CONTRACT_MID")) {
                TVComponent.mFacFunction.setPiCurveContractMid(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_PICTURECURVE_CONTRACT_MAX")) {
                TVComponent.mFacFunction.setPiCurveContractMax(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_PICTURECURVE_SATURATION_MIN")) {
                TVComponent.mFacFunction.setPiCurveSaturationMin(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_PICTURECURVE_SATURATION_MID")) {
                TVComponent.mFacFunction.setPiCurveSaturationMid(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_PICTURECURVE_SATURATION_MAX")) {
                TVComponent.mFacFunction.setPiCurveSaturationMax(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_PICTURECURVE_HUE_MIN")) {
                TVComponent.mFacFunction.setPiCurveHueMin(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_PICTURECURVE_HUE_MID")) {
                TVComponent.mFacFunction.setPiCurveHueMid(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_PICTURECURVE_HUE_MAX")) {
                TVComponent.mFacFunction.setPiCurveHueMax(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_PICTURECURVE_SHARPNESS_MIN")) {
                TVComponent.mFacFunction.setPiCurveSharpnessMin(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_PICTURECURVE_SHARPNESS_20")) {
                TVComponent.mFacFunction.setPiCurveSharpness20(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_PICTURECURVE_SHARPNESS_MID")) {
                TVComponent.mFacFunction.setPiCurveSharpnessMid(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_PICTURECURVE_SHARPNESS_80")) {
                TVComponent.mFacFunction.setPiCurveSharpness80(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_PICTURECURVE_SHARPNESS_MAX")) {
                TVComponent.mFacFunction.setPiCurveSharpnessMax(num.intValue());
                return;
            }
            if (this.name.equals("FACTORY_20POINT_LEVEL")) {
                TVComponent.mFacFunction.setWB20PointLevel(num.intValue());
                return;
            }
            if (this.name.equals("FACTORY_20POINT_RED")) {
                TVComponent.mFacFunction.setWB20PointRed(num.intValue());
                return;
            }
            if (this.name.equals("FACTORY_20POINT_GREEN")) {
                TVComponent.mFacFunction.setWB20PointGreen(num.intValue());
                return;
            }
            if (this.name.equals("FACTORY_20POINT_BLUE")) {
                TVComponent.mFacFunction.setWB20PointBlue(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_AUDIOCURVE_CURVE1")) {
                TVComponent.mFacFunction.setVolumeCurve0(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_AUDIOCURVE_CURVE25")) {
                TVComponent.mFacFunction.setVolumeCurve25(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_AUDIOCURVE_CURVE50")) {
                TVComponent.mFacFunction.setVolumeCurve50(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_AUDIOCURVE_CURVE75")) {
                TVComponent.mFacFunction.setVolumeCurve75(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_AUDIOCURVE_CURVE100")) {
                TVComponent.mFacFunction.setVolumeCurve100(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_AUDIOCURVE_CURVE1_NEW")) {
                TVComponent.mFacFunction.setAudioCurveDetailValueNew(0, num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_AUDIOCURVE_CURVE10_NEW")) {
                TVComponent.mFacFunction.setAudioCurveDetailValueNew(1, num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_AUDIOCURVE_CURVE25_NEW")) {
                TVComponent.mFacFunction.setAudioCurveDetailValueNew(2, num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_AUDIOCURVE_CURVE50_NEW")) {
                TVComponent.mFacFunction.setAudioCurveDetailValueNew(3, num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_AUDIOCURVE_CURVE75_NEW")) {
                TVComponent.mFacFunction.setAudioCurveDetailValueNew(4, num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_AUDIOCURVE_CURVE100_NEW")) {
                TVComponent.mFacFunction.setAudioCurveDetailValueNew(5, num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_PEQ_1_FREQ")) {
                TVComponent.mFacFunction.setFREQ1(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_PEQ_2_FREQ")) {
                TVComponent.mFacFunction.setFREQ2(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_PEQ_3_FREQ")) {
                TVComponent.mFacFunction.setFREQ3(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_PEQ_4_FREQ")) {
                TVComponent.mFacFunction.setFREQ4(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_PEQ_5_FREQ")) {
                TVComponent.mFacFunction.setFREQ5(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_PEQ_6_FREQ")) {
                TVComponent.mFacFunction.setFREQ6(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_PEQ_7_FREQ")) {
                TVComponent.mFacFunction.setFREQ7(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_PEQ_8_FREQ")) {
                TVComponent.mFacFunction.setFREQ8(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_PEQ_1_GAIN")) {
                TVComponent.mFacFunction.setGAIN1(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_PEQ_2_GAIN")) {
                TVComponent.mFacFunction.setGAIN2(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_PEQ_3_GAIN")) {
                TVComponent.mFacFunction.setGAIN3(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_PEQ_4_GAIN")) {
                TVComponent.mFacFunction.setGAIN4(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_PEQ_5_GAIN")) {
                TVComponent.mFacFunction.setGAIN5(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_PEQ_6_GAIN")) {
                TVComponent.mFacFunction.setGAIN6(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_PEQ_7_GAIN")) {
                TVComponent.mFacFunction.setGAIN7(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_PEQ_8_GAIN")) {
                TVComponent.mFacFunction.setGAIN8(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_PEQ_1_Q")) {
                TVComponent.mFacFunction.setQ1(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_PEQ_2_Q")) {
                TVComponent.mFacFunction.setQ2(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_PEQ_3_Q")) {
                TVComponent.mFacFunction.setQ3(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_PEQ_4_Q")) {
                TVComponent.mFacFunction.setQ4(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_PEQ_5_Q")) {
                TVComponent.mFacFunction.setQ5(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_PEQ_6_Q")) {
                TVComponent.mFacFunction.setQ6(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_PEQ_7_Q")) {
                TVComponent.mFacFunction.setQ7(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_PEQ_8_Q")) {
                TVComponent.mFacFunction.setQ8(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_PEQ_1_STEP")) {
                TVComponent.mFacFunction.setSTEP1(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_PEQ_2_STEP")) {
                TVComponent.mFacFunction.setSTEP2(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_PEQ_3_STEP")) {
                TVComponent.mFacFunction.setSTEP3(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_PEQ_4_STEP")) {
                TVComponent.mFacFunction.setSTEP4(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_PEQ_5_STEP")) {
                TVComponent.mFacFunction.setSTEP5(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_PEQ_6_STEP")) {
                TVComponent.mFacFunction.setSTEP6(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_PEQ_7_STEP")) {
                TVComponent.mFacFunction.setSTEP7(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_PEQ_8_STEP")) {
                TVComponent.mFacFunction.setSTEP8(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_HPF_FREQUENCY")) {
                TVComponent.mFacFunction.setHpfFrequency(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_SOC_DRC")) {
                TVComponent.mFacFunction.setSocDrc(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_AUD_AUTO_VOLUEM_CTRL_THRESHOLD")) {
                TVComponent.mFacFunction.setAutoVolumeCtrlThreshold(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_VOLUME")) {
                TVComponent.mFacFunction.setVolume(TVComponent.this.mTVCalculator.convertDataRange(0, 100, this.min, this.max, num.intValue()));
                return;
            }
            if (this.name.equals("FACTORY_BL_MIN_BACKLIGHT")) {
                TVComponent.mFacFunction.setBacklight_Min(FacPropertyDemo.BACKLIGHTCURVE_POINT.BACKLIGHT_MIN, num.intValue());
                return;
            }
            if (this.name.equals("FACTORY_BL_20_BACKLIGHT")) {
                TVComponent.mFacFunction.setBacklight_20(FacPropertyDemo.BACKLIGHTCURVE_POINT.BACKLIGHT_20, num.intValue());
                return;
            }
            if (this.name.equals("FACTORY_BL_MID_BACKLIGHT")) {
                TVComponent.mFacFunction.setBacklight_Mid(FacPropertyDemo.BACKLIGHTCURVE_POINT.BACKLIGHT_MID, num.intValue());
                return;
            }
            if (this.name.equals("FACTORY_BL_80_BACKLIGHT")) {
                TVComponent.mFacFunction.setBacklight_80(FacPropertyDemo.BACKLIGHTCURVE_POINT.BACKLIGHT_80, num.intValue());
                return;
            }
            if (this.name.equals("FACTORY_BL_MAX_BACKLIGHT")) {
                TVComponent.mFacFunction.setBacklight_Max(FacPropertyDemo.BACKLIGHTCURVE_POINT.BACKLIGHT_MAX, num.intValue());
                return;
            }
            if (this.name.equals("FACTORY_BL_PWM_FREQ")) {
                TVComponent.mFacFunction.setPWMFreq(FacPropertyDemo.BACKLIGHTCURVE_POINT.BACKLIGHT_PWM_FREQ, num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_OVERSCAN_HPOS")) {
                TVComponent.mFacFunction.setOverScanLeft(Integer.valueOf(TVComponent.this.mTVCalculator.convertDataRange(0, 1000, this.min, this.max, num.intValue())).intValue());
                return;
            }
            if (this.name.equals("HIFAC_OVERSCAN_VPOS")) {
                TVComponent.mFacFunction.setOverScanTop(Integer.valueOf(TVComponent.this.mTVCalculator.convertDataRange(0, 1000, this.min, this.max, num.intValue())).intValue());
                return;
            }
            if (this.name.equals("HIFAC_OVERSCAN_HSIZE")) {
                TVComponent.mFacFunction.setOverScanRight(Integer.valueOf(TVComponent.this.mTVCalculator.convertDataRange(0, 1000, this.min, this.max, num.intValue())).intValue());
                return;
            }
            if (this.name.equals("HIFAC_OVERSCAN_VSIZE")) {
                TVComponent.mFacFunction.setOverScanBottom(Integer.valueOf(TVComponent.this.mTVCalculator.convertDataRange(0, 1000, this.min, this.max, num.intValue())).intValue());
                return;
            }
            if (this.name.equals("HIFAC_OVERSCAN_HSTART")) {
                TVComponent.mFacFunction.setHStart(Integer.valueOf(TVComponent.this.mTVCalculator.convertDataRange(0, 1000, this.min, this.max, num.intValue())).intValue());
                return;
            }
            if (this.name.equals("HIFAC_OVERSCAN_VSTART")) {
                TVComponent.mFacFunction.setVStart(Integer.valueOf(TVComponent.this.mTVCalculator.convertDataRange(0, 1000, this.min, this.max, num.intValue())).intValue());
                return;
            }
            if (this.name.equals("HIFAC_AGING")) {
                Log.i("Fac_TVComponent", "tvcomponent setAgingFlag " + num.intValue());
                TVComponent.mFacFunction.setAgingFlag(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_SAVINGMODE")) {
                TVComponent.mFacFunction.setSavingMode(FacPropertyDemo.SavingMode.values()[num.intValue()]);
                return;
            }
            if (this.name.equals("HIFAC_ZOOMMODE")) {
                TVComponent.mFacFunction.setZoomMode(FacPropertyDemo.ZoomMode.values()[num.intValue()]);
                return;
            }
            if (this.name.equals("HIFAC_ZOOMMODE_FOR_EM")) {
                TVComponent.mFacFunction.setZoomModeForEM(FacPropertyDemo.ZoomModeForEM.values()[num.intValue()]);
                return;
            }
            if (this.name.equals("HIFAC_BALANCE")) {
                TVComponent.mFacFunction.setBalance(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_BALANCE_DOUBLE_AMP") || this.name.equals("HIFAC_BALANCE_MULTI_AMP")) {
                TVComponent.mFacFunction.setMultiBalance(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_BALANCE_UNIV_MULTI_AMP")) {
                TVComponent.mFacFunction.setUnivMultiBalance(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_TVS_BALANCE_LIKE_550")) {
                TVComponent.mFacFunction.setBalanceForTweeter(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_TVS_BALANCE_LIKE_650")) {
                TVComponent.mFacFunction.setBalanceForTweeterWoofer(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_TVS_BALANCE_LIKE_850")) {
                TVComponent.mFacFunction.setBalanceForLike850(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_3DMODE")) {
                TVComponent.mFacFunction.set3DMode(FacPropertyDemo.ThreeDimensionMode.values()[num.intValue()]);
                return;
            }
            if (this.name.equals("HIFAC_LR_SWAP")) {
                TVComponent.mFacFunction.setLeftRightSwap(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_DEPTH_OF_FIELD")) {
                TVComponent.mFacFunction.setFieldDepth(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_CONVERGENCE")) {
                TVComponent.mFacFunction.setConvergence(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_3DTO2D")) {
                TVComponent.mFacFunction.set3Dto2D(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_ALARMENABLE")) {
                TVComponent.mFacFunction.setLaserToAlarm(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_NO_SIGNAL_STANDBY")) {
                TVComponent.mFacFunction.setNoSignalStandby(num.intValue());
                return;
            }
            if (this.name.equals("FACTORY_TEMP_PROTECT")) {
                TVComponent.mFacFunction.setTemperatureProtection(num.intValue());
            }
            if (this.name.equals("DISPLAYBOARD_SOFTWARE_UPGRADE_PREPARE")) {
                TVComponent.mFacFunction.setDisplayBoardUpgrade(num.intValue());
            }
            if (this.name.equals("FACTORY_SCREEN_SELECT")) {
                TVComponent.mFacFunction.setScreen(num.intValue());
            }
            if (this.name.equals("HIFAC_ADC_RGAIN")) {
                TVComponent.mFacFunction.setADCRedGain(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_ADC_GGAIN")) {
                TVComponent.mFacFunction.setADCGreenGain(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_ADC_BGAIN")) {
                TVComponent.mFacFunction.setADCBlueGain(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_ADC_ROFFSET")) {
                TVComponent.mFacFunction.setADCRedOffset(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_ADC_GOFFSET")) {
                TVComponent.mFacFunction.setADCGreenOffset(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_ADC_BOFFSET")) {
                TVComponent.mFacFunction.setADCBlueOffset(num.intValue());
                return;
            }
            if (this.name.equals("FACTORY_PQ_BYPASS_ENABLE")) {
                TVComponent.mFacFunction.setPqBypassEnable(num.intValue());
                return;
            }
            if (this.name.equals("FACTORY_GAMMA_BYPASS_ENABLE")) {
                TVComponent.mFacFunction.setGammaBypassEnable(num.intValue());
                return;
            }
            if (this.name.equals("FACTORY_COLOR_BYPASS_ENABLE")) {
                TVComponent.mFacFunction.setColorBypassEnable(num.intValue());
                return;
            }
            if (this.name.equals("FACTORY_LOCALDIMMING_ENABLE")) {
                TVComponent.mFacFunction.setLocaldimmingEnable(num.intValue());
                return;
            }
            if (this.name.equals("FACTORY_IMAGE_COMPENSATION_ENABLE")) {
                TVComponent.mFacFunction.setImageCompensationEnable(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_BYPASS_PQ")) {
                TVComponent.mFacFunction.setBypassPQEnable(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_AUD_AUTO_VOLUEM_CTRL_ENABLE")) {
                TVComponent.mFacFunction.setAutoVolumeCtrlEnable(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_BYPASS_GAMMA")) {
                TVComponent.mFacFunction.setBypassGammaEnable(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_BYPASS_SEC")) {
                TVComponent.mFacFunction.setBypassSecEnable(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_BYPASS_XVYCC")) {
                TVComponent.mFacFunction.setBypassXvyccEnable(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_SSC_VBYONE_ENABLE")) {
                TVComponent.mFacFunction.setSSCVByOneEnable(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_SSC_VBYONE_RANGE")) {
                TVComponent.mFacFunction.setSSCVByOneRange(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_SSC_VBYONE_SPAN")) {
                TVComponent.mFacFunction.setSSCVByOneSpan(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_SSC_VBYONE_MODULATION")) {
                TVComponent.mFacFunction.setSSCVByOneModu(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_SSC_DDR_ENABLE")) {
                TVComponent.mFacFunction.setSSCDDREnable(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_SSC_DDR_SPAN")) {
                TVComponent.mFacFunction.setSSCDDRSpan(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_SSC_DDR_MODULATION")) {
                TVComponent.mFacFunction.setSSCDDRModu(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_AUDIO_DRC")) {
                TVComponent.mFacFunction.setAudioDRC(num.intValue());
            }
            if (this.name.equals("HIFAC_AUDIO_DRC_L")) {
                TVComponent.mFacFunction.setMultiDigitalAMPValueByDevId(0, 0, num.intValue());
            }
            if (this.name.equals("HIFAC_AUDIO_DRC_H")) {
                TVComponent.mFacFunction.setMultiDigitalAMPValueByDevId(0, 1, num.intValue());
            }
            if (this.name.equals("HIFAC_AUDIO_DRC_S")) {
                TVComponent.mFacFunction.setMultiDigitalAMPValueByDevId(0, 2, num.intValue());
            }
            if (this.name.equals("HIFAC_AUDIO_DRC_P")) {
                TVComponent.mFacFunction.setMultiDigitalAMPValueByDevId(0, 3, num.intValue());
            }
            if (this.name.equals("HIFAC_AUDIO_PA_GAIN")) {
                TVComponent.mFacFunction.setMultiDigitalAMPValueByDevId(0, 4, num.intValue());
            }
            if (this.name.equals("HIFAC_AUDIO_OUTPUT_PORT")) {
                TVComponent.mFacFunction.setMultiDigitalAMPValueByDevId(0, 5, num.intValue());
            }
            if (this.name.equals("HIFAC_WOOFER_AUDIO_DRC")) {
                TVComponent.mFacFunction.setWooferAudioDRC(num.intValue());
            }
            if (this.name.equals("HIFAC_WOOFER_AUDIO_PA_GAIN")) {
                TVComponent.mFacFunction.setWooferAudioPaGain(num.intValue());
            }
            if (this.name.equals("HIFAC_AUDIO_SUB1_DRC_L")) {
                TVComponent.mFacFunction.setMultiDigitalAMPValueByDevId(1, 0, num.intValue());
            }
            if (this.name.equals("HIFAC_AUDIO_SUB1_DRC_H")) {
                TVComponent.mFacFunction.setMultiDigitalAMPValueByDevId(1, 1, num.intValue());
            }
            if (this.name.equals("HIFAC_AUDIO_SUB1_DRC_S")) {
                TVComponent.mFacFunction.setMultiDigitalAMPValueByDevId(1, 2, num.intValue());
            }
            if (this.name.equals("HIFAC_AUDIO_SUB1_DRC_P")) {
                TVComponent.mFacFunction.setMultiDigitalAMPValueByDevId(1, 3, num.intValue());
            }
            if (this.name.equals("HIFAC_SUB1_AUDIO_PA_GAIN")) {
                TVComponent.mFacFunction.setMultiDigitalAMPValueByDevId(1, 4, num.intValue());
            }
            if (this.name.equals("HIFAC_SUB1_AUDIO_OUTPUT_PORT")) {
                TVComponent.mFacFunction.setMultiDigitalAMPValueByDevId(1, 5, num.intValue());
            }
            if (this.name.equals("HIFAC_AUDIO_SUB2_DRC_L")) {
                TVComponent.mFacFunction.setMultiDigitalAMPValueByDevId(2, 0, num.intValue());
            }
            if (this.name.equals("HIFAC_AUDIO_SUB2_DRC_H")) {
                TVComponent.mFacFunction.setMultiDigitalAMPValueByDevId(2, 1, num.intValue());
            }
            if (this.name.equals("HIFAC_AUDIO_SUB2_DRC_S")) {
                TVComponent.mFacFunction.setMultiDigitalAMPValueByDevId(2, 2, num.intValue());
            }
            if (this.name.equals("HIFAC_AUDIO_SUB2_DRC_P")) {
                TVComponent.mFacFunction.setMultiDigitalAMPValueByDevId(2, 3, num.intValue());
            }
            if (this.name.equals("HIFAC_SUB2_AUDIO_PA_GAIN")) {
                TVComponent.mFacFunction.setMultiDigitalAMPValueByDevId(2, 4, num.intValue());
            }
            if (this.name.equals("HIFAC_SUB2_AUDIO_OUTPUT_PORT")) {
                TVComponent.mFacFunction.setMultiDigitalAMPValueByDevId(2, 5, num.intValue());
            }
            if (this.name.equals("HIFAC_AUDIO_SUB3_DRC_L")) {
                TVComponent.mFacFunction.setMultiDigitalAMPValueByDevId(3, 0, num.intValue());
            }
            if (this.name.equals("HIFAC_AUDIO_SUB3_DRC_H")) {
                TVComponent.mFacFunction.setMultiDigitalAMPValueByDevId(3, 1, num.intValue());
            }
            if (this.name.equals("HIFAC_AUDIO_SUB3_DRC_S")) {
                TVComponent.mFacFunction.setMultiDigitalAMPValueByDevId(3, 2, num.intValue());
            }
            if (this.name.equals("HIFAC_AUDIO_SUB3_DRC_P")) {
                TVComponent.mFacFunction.setMultiDigitalAMPValueByDevId(3, 3, num.intValue());
            }
            if (this.name.equals("HIFAC_SUB3_AUDIO_PA_GAIN")) {
                TVComponent.mFacFunction.setMultiDigitalAMPValueByDevId(3, 4, num.intValue());
            }
            if (this.name.equals("HIFAC_SUB3_AUDIO_OUTPUT_PORT")) {
                TVComponent.mFacFunction.setMultiDigitalAMPValueByDevId(3, 5, num.intValue());
            }
            if (this.name.equals("HIFAC_AUDIO_SUB4_DRC_L")) {
                TVComponent.mFacFunction.setMultiDigitalAMPValueByDevId(4, 0, num.intValue());
            }
            if (this.name.equals("HIFAC_AUDIO_SUB4_DRC_H")) {
                TVComponent.mFacFunction.setMultiDigitalAMPValueByDevId(4, 1, num.intValue());
            }
            if (this.name.equals("HIFAC_AUDIO_SUB4_DRC_S")) {
                TVComponent.mFacFunction.setMultiDigitalAMPValueByDevId(4, 2, num.intValue());
            }
            if (this.name.equals("HIFAC_AUDIO_SUB4_DRC_P")) {
                TVComponent.mFacFunction.setMultiDigitalAMPValueByDevId(4, 3, num.intValue());
            }
            if (this.name.equals("HIFAC_SUB4_AUDIO_PA_GAIN")) {
                TVComponent.mFacFunction.setMultiDigitalAMPValueByDevId(4, 4, num.intValue());
            }
            if (this.name.equals("HIFAC_SUB4_AUDIO_OUTPUT_PORT")) {
                TVComponent.mFacFunction.setMultiDigitalAMPValueByDevId(4, 5, num.intValue());
            }
            if (this.name.equals("HIFAC_AUDIO_SUB5_DRC_L")) {
                TVComponent.mFacFunction.setMultiDigitalAMPValueByDevId(5, 0, num.intValue());
            }
            if (this.name.equals("HIFAC_AUDIO_SUB5_DRC_H")) {
                TVComponent.mFacFunction.setMultiDigitalAMPValueByDevId(5, 1, num.intValue());
            }
            if (this.name.equals("HIFAC_AUDIO_SUB5_DRC_S")) {
                TVComponent.mFacFunction.setMultiDigitalAMPValueByDevId(5, 2, num.intValue());
            }
            if (this.name.equals("HIFAC_AUDIO_SUB5_DRC_P")) {
                TVComponent.mFacFunction.setMultiDigitalAMPValueByDevId(5, 3, num.intValue());
            }
            if (this.name.equals("HIFAC_SUB5_AUDIO_PA_GAIN")) {
                TVComponent.mFacFunction.setMultiDigitalAMPValueByDevId(5, 4, num.intValue());
            }
            if (this.name.equals("HIFAC_SUB5_AUDIO_OUTPUT_PORT")) {
                TVComponent.mFacFunction.setMultiDigitalAMPValueByDevId(5, 5, num.intValue());
            }
            if (this.name.equals("HIFAC_AVC_THRESHOLD")) {
                TVComponent.mFacFunction.setThresholdAutoVolumeControl(num.intValue());
            }
            if (this.name.equals("FACTORY_AVC_ATTACK_TIME")) {
                TVComponent.mFacFunction.setAttackTimeAutoVolumeControl(num.intValue());
            }
            if (this.name.equals("FACTORY_AVC_RELEASE_TIME")) {
                TVComponent.mFacFunction.setReleaseTimeAutoVolumeControl(num.intValue());
            }
            if (this.name.equals("HIFAC_WOOFER_AUDIO_OUTPUT_PORT")) {
                TVComponent.mFacFunction.setWooferAudioOutputPort(num.intValue());
            }
            if (this.name.equals("HIFAC_MAXXBASS_FREQUENCY")) {
                TVComponent.mFacFunction.setMaxxBassFrequency(num.intValue());
            }
            if (this.name.equals("HIFAC_MAXXTREBLE_FREQUENCY")) {
                TVComponent.mFacFunction.setMaxxTrebleFrequency(num.intValue());
            }
            if (this.name.equals("HIFAC_MAXX3D_OPTION")) {
                TVComponent.mFacFunction.setMaxx3DOption(num.intValue());
            }
            if (this.name.equals("HIFAC_LIGHT_SENSOR_ENABLE")) {
                TVComponent.mFacFunction.setSensorEnable(num.intValue() == 1);
            }
            if (this.name.equals("HIFAC_AUD_HPF")) {
                TVComponent.mFacFunction.setAudHpfStatus(num.intValue() == 1);
            }
            if (this.name.equals("HIFAC_AUD_PEQ_ENABLE")) {
                TVComponent.mFacFunction.setAudPeqEnableStatus(num.intValue() == 1);
            }
            if (this.name.equals("HIFAC_AUD_SOC_DRC_ENABLE")) {
                TVComponent.mFacFunction.setAudSocDrcEnableStatus(num.intValue() == 1);
            }
            if (this.name.equals("HIFAC_LIGHT_SENSOR_INDEX0")) {
                TVComponent.mFacFunction.setLightSensorIndex(num.intValue());
            }
            if (this.name.equals("HIFAC_LIGHT_SENSOR_VALUE0")) {
                TVComponent.mFacFunction.setLightSensorCurve(0, 0, num.intValue());
            }
            if (this.name.equals("FACTORY_LUX_VALUE0")) {
                TVComponent.mFacFunction.setLightSensorCurve(1, 0, num.intValue());
            }
            if (this.name.equals("HIFAC_PWM0")) {
                TVComponent.mFacFunction.setLightSensorCurve(2, 0, num.intValue());
            }
            if (this.name.equals("HIFAC_LIGHT_SENSOR_VALUE1")) {
                TVComponent.mFacFunction.setLightSensorCurve(0, 1, num.intValue());
            }
            if (this.name.equals("FACTORY_LUX_VALUE1")) {
                TVComponent.mFacFunction.setLightSensorCurve(1, 1, num.intValue());
            }
            if (this.name.equals("HIFAC_PWM1")) {
                TVComponent.mFacFunction.setLightSensorCurve(2, 1, num.intValue());
            }
            if (this.name.equals("HIFAC_LIGHT_SENSOR_VALUE2")) {
                TVComponent.mFacFunction.setLightSensorCurve(0, 2, num.intValue());
            }
            if (this.name.equals("FACTORY_LUX_VALUE2")) {
                TVComponent.mFacFunction.setLightSensorCurve(1, 2, num.intValue());
            }
            if (this.name.equals("HIFAC_PWM2")) {
                TVComponent.mFacFunction.setLightSensorCurve(2, 2, num.intValue());
            }
            if (this.name.equals("HIFAC_LIGHT_SENSOR_VALUE3")) {
                TVComponent.mFacFunction.setLightSensorCurve(0, 3, num.intValue());
            }
            if (this.name.equals("FACTORY_LUX_VALUE3")) {
                TVComponent.mFacFunction.setLightSensorCurve(1, 3, num.intValue());
            }
            if (this.name.equals("HIFAC_PWM3")) {
                TVComponent.mFacFunction.setLightSensorCurve(2, 3, num.intValue());
            }
            if (this.name.equals("HIFAC_LIGHT_SENSOR_VALUE4")) {
                TVComponent.mFacFunction.setLightSensorCurve(0, 4, num.intValue());
            }
            if (this.name.equals("FACTORY_LUX_VALUE4")) {
                TVComponent.mFacFunction.setLightSensorCurve(1, 4, num.intValue());
            }
            if (this.name.equals("HIFAC_PWM4")) {
                TVComponent.mFacFunction.setLightSensorCurve(2, 4, num.intValue());
            }
            if (this.name.equals("HIFAC_LIGHT_SENSOR_VALUE5")) {
                TVComponent.mFacFunction.setLightSensorCurve(0, 5, num.intValue());
            }
            if (this.name.equals("FACTORY_LUX_VALUE5")) {
                TVComponent.mFacFunction.setLightSensorCurve(1, 5, num.intValue());
            }
            if (this.name.equals("HIFAC_PWM5")) {
                TVComponent.mFacFunction.setLightSensorCurve(2, 5, num.intValue());
            }
            if (this.name.equals("HIFAC_LIGHT_SENSOR_VALUE6")) {
                TVComponent.mFacFunction.setLightSensorCurve(0, 6, num.intValue());
            }
            if (this.name.equals("FACTORY_LUX_VALUE6")) {
                TVComponent.mFacFunction.setLightSensorCurve(1, 6, num.intValue());
            }
            if (this.name.equals("HIFAC_PWM6")) {
                TVComponent.mFacFunction.setLightSensorCurve(2, 6, num.intValue());
            }
            if (this.name.equals("HIFAC_LIGHT_SENSOR_VALUE7")) {
                TVComponent.mFacFunction.setLightSensorCurve(0, 7, num.intValue());
            }
            if (this.name.equals("FACTORY_LUX_VALUE7")) {
                TVComponent.mFacFunction.setLightSensorCurve(1, 7, num.intValue());
            }
            if (this.name.equals("HIFAC_PWM7")) {
                TVComponent.mFacFunction.setLightSensorCurve(2, 7, num.intValue());
            }
            if (this.name.equals("HIFAC_DESCRAMBLEBOX")) {
                TVComponent.mFacFunction.setNonstandardDescramble(num.intValue());
            }
            if (this.name.equals("HIFAC_DESCRAMBLEBOX_TVD")) {
                TVComponent.mFacFunction.setNonstandardDescrambleplus(num.intValue());
            }
            if (this.name.equals("HIFAC_NONSTANDARD_VIF_TOP")) {
                Log.i("Fac_TVComponent", "atv nonstandard set vif_top " + num.intValue());
                TVComponent.mFacFunction.setVifTop(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_NONSTANDARD_VIF_VGA_MAXIMUM")) {
                TVComponent.mFacFunction.setVifVgaMaximum(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_NONSTANDARD_VIF_CR_KP")) {
                TVComponent.mFacFunction.setVifCrKp(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_NONSTANDARD_VIF_CR_KI")) {
                TVComponent.mFacFunction.setVifCrKi(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_NONSTANDARD_VIF_ASIA_SIGNAL_OPTION")) {
                TVComponent.mFacFunction.setVifAsiaSignalOption(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_NONSTANDARD_VIF_CR_KP_KI_ADJUST")) {
                TVComponent.mFacFunction.setVifCrKpKiAdjust(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_NONSTANDARD_HI_DEV")) {
                TVComponent.mFacFunction.setVifOverModulation(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_NONSTANDARD_VIF_CLAMPGAIN_GAIN_OV_NEGATIVE")) {
                TVComponent.mFacFunction.setVifClampGainOvNegative(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_NONSTANDARD_CHINA_DESCRAMBLER_BOX")) {
                TVComponent.mFacFunction.setChinaDescramblerBox(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_NONSTANDARD_Delay")) {
                TVComponent.mFacFunction.setDelayReduce(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_NONSTANDARD_VIF_CR_THR")) {
                TVComponent.mFacFunction.setVifCrThr(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_NONSTANDARD_VIF_VERSION")) {
                TVComponent.mFacFunction.setVifVersion(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_NONSTANDARD_VIF_AGC_REF")) {
                TVComponent.mFacFunction.setVifAgcRef(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_NONSTANDARD_GAIN_DISTRIBUTION_THR")) {
                TVComponent.mFacFunction.setGainDistributionThr(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_NONSTANDARD_AEFC_D4")) {
                TVComponent.mFacFunction.setAEFC_D4(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_NONSTANDARD_AEFC_D5")) {
                TVComponent.mFacFunction.setAEFC_D5Bit2(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_NONSTANDARD_AEFC_D8")) {
                TVComponent.mFacFunction.setAEFC_D8Bit3210(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_NONSTANDARD_AEFC_D9[0]")) {
                TVComponent.mFacFunction.setAEFC_D9Bit0(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_NONSTANDARD_AEFC_D7_HIGH_BOUN")) {
                TVComponent.mFacFunction.setAEFC_D7HighBoun(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_NONSTANDARD_AEFC_D7_LOW_BOUN")) {
                TVComponent.mFacFunction.setAEFC_D7LowBoun(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_NONSTANDARD_AEFC_A0")) {
                TVComponent.mFacFunction.setAEFC_A0(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_NONSTANDARD_AEFC_A1")) {
                TVComponent.mFacFunction.setAEFC_A1(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_NONSTANDARD_AEFC_66")) {
                TVComponent.mFacFunction.setAEFC_66Bit76(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_NONSTANDARD_AEFC_6E")) {
                TVComponent.mFacFunction.setAEFC_6EBit7654(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_NONSTANDARD_AEFC_6E_LOW")) {
                TVComponent.mFacFunction.setAEFC_6EBit3210(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_NONSTANDARD_AEFC_43")) {
                TVComponent.mFacFunction.setAEFC_43(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_NONSTANDARD_AEFC_44")) {
                TVComponent.mFacFunction.setAEFC_44(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_NONSTANDARD_AEFC_CB")) {
                TVComponent.mFacFunction.setAEFC_CB(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_NONSTANDARD_VD_DSP_VERSION")) {
                TVComponent.mFacFunction.setVdDspVersion(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_NONSTANDARD_VUDIO_HIDEV_MODE")) {
                TVComponent.mFacFunction.setAudioHiDevMode(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_NONSTANDARD_AUDIO_NR_THR")) {
                TVComponent.mFacFunction.setAudioNrThr(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_NONSTANDARD_VAudio_Sif_Threshold")) {
                TVComponent.mFacFunction.setAudioSifThreshold(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_NONSTANDARD_AUDIO_DSP_VERSION")) {
                TVComponent.mFacFunction.setAudioDspVersion(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_DTV_NONSTANDA_NVOD_PROG_DIS")) {
                TVComponent.mFacFunction.setNvodProgramDisplay(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_DTV_NONSTANDA_PROG_NUM_DIS")) {
                TVComponent.mFacFunction.setProgramNumDisplay(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_DTV_NONSTANDA_USER_ORDER_SET")) {
                TVComponent.mFacFunction.setUserOrderSet(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_DTV_NONSTANDA_AREA_CONTROL_SET")) {
                TVComponent.mFacFunction.setAreaControl(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_DTV_NONSTANDA_AREA_CONTROL_USER")) {
                TVComponent.mFacFunction.setAreaControlFrequence(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_OVERSCAN_TOP")) {
                TVComponent.mFacFunction.setOverScanTop(num.intValue());
            }
            if (this.name.equals("HIFAC_OVERSCAN_BUTTOM")) {
                TVComponent.mFacFunction.setOverScanBottom(num.intValue());
            }
            if (this.name.equals("HIFAC_OVERSCAN_LEFT")) {
                TVComponent.mFacFunction.setOverScanLeft(num.intValue());
            }
            if (this.name.equals("HIFAC_OVERSCAN_RIGHT")) {
                TVComponent.mFacFunction.setOverScanRight(num.intValue());
            }
            if (this.name.equals("FACTORY_DTVTYPE")) {
                TVComponent.mFacFunction.setDtvType(FacPropertyDemo.DtvDemodulationType.values()[num.intValue()]);
            }
            if (this.name.equals("HIFAC_UART_SWITCH")) {
                TVComponent.mFacFunction.setUartSwitch(num.intValue());
            }
            if (this.name.equals("FACTORY_ACCREDITATION")) {
                TVComponent.mFacFunction.setAuthMode(num.intValue());
            }
            if (this.name.equals("HIFAC_USER_AGING_SWITCH")) {
                TVComponent.mFacFunction.setUserAgingSwitch(num.intValue());
            }
            if (this.name.equals("HIFAC_OLED_TPC_ENABLE")) {
                TVComponent.mFacFunction.setOledTpcValue(num.intValue());
            }
            if (this.name.equals("HIFAC_OLED_PIXEL_MOVE_SHAPE")) {
                TVComponent.mFacFunction.setOledPixelShiftShapeValue(num.intValue());
            }
            if (this.name.equals("HIFAC_OLED_PIXEL_MOVE_TIMER")) {
                TVComponent.mFacFunction.setOledPixelShiftTimerValue(num.intValue());
            }
            if (this.name.equals("HIFAC_OLED_PIXEL_MOVE_TYPE")) {
                TVComponent.mFacFunction.setOledPixelShiftTypeValue(num.intValue());
            }
            if (this.name.equals("HIFAC_OLED_LEA_ENABLE")) {
                TVComponent.mFacFunction.setOledLeaValue(num.intValue());
            }
            if (this.name.equals("HIFAC_OLED_CPC_ENABLE")) {
                TVComponent.mFacFunction.setOledCpcValue(num.intValue());
            }
            if (this.name.equals("HIFAC_OLED_LBC2_ENABLE")) {
                TVComponent.mFacFunction.setOledLbc2Value(num.intValue());
            }
            if (this.name.equals("HIFAC_OLED_ORBIT_ENABLE")) {
                TVComponent.mFacFunction.setOledOrbitValue(num.intValue());
            }
            if (this.name.equals("HIFAC_OLED_LBA_ENABLE")) {
                TVComponent.mFacFunction.setOledLbaValue(num.intValue());
            }
            if (this.name.equals("HIFAC_OLED_HDR_ENABLE")) {
                TVComponent.mFacFunction.setOledHdrValue(num.intValue());
            }
            if (this.name.equals("HIFAC_MIC_YIZHIXING_DB")) {
                TVComponent.mFacFunction.setMicDb(num.intValue());
            }
            if (this.name.equals("HIFAC_MIC_RECORD_TIME")) {
                TVComponent.mFacFunction.setMicRecordTime(num.intValue());
            }
            if (this.name.equals("FACTORY_Dolby_IQ")) {
                TVComponent.mFacFunction.setDolbyIQUserBinEnable(num.intValue());
                if (num.intValue() == 1) {
                    TVComponent.mFacFunction.getDolbyUpdateResultTip(num.intValue());
                }
            }
            if (this.name.equals("HIFAC_DEMURA_SWITCH")) {
                TVComponent.mFacFunction.setDemuraSwitchStatus(num.intValue());
            }
            if (this.name.equals("FACTORY_EYE_PROTECT")) {
                TVComponent.mFacFunction.setLaserEyeProtectSwitch(num.intValue());
            }
            if (this.name.equals("HIFAC_TVS_LNB")) {
                TVComponent.mFacFunction.setLNB(num.intValue());
                return;
            }
            if (this.name.equals("HIFAC_MODE_SELECTION")) {
                TVComponent.mFacFunction.setModelSelection(num.intValue());
            } else if (this.name.equals("HIFAC_MODE_SELECTION_VALUE")) {
                TVComponent.mFacFunction.setModeValue(num.intValue());
            } else if (this.name.equals("FACTORY_FOREIGN_OBJECT_DETECT_SWITCH")) {
                TVComponent.mFacFunction.setLaserForeignObjectDetectSwitch(num.intValue());
            }
        }
    }

    public static FacFunction createFacFunction(String str) {
        FacFunction facFunction = mFacFunction;
        if (facFunction != null) {
            return facFunction;
        }
        FacFunction_Adapter facFunction_Adapter = FacFunction_Adapter.getInstance(mContext);
        mFacFunction = facFunction_Adapter;
        return facFunction_Adapter;
    }

    public static TVComponent getInstance(Context context) {
        if (mTVComponent == null) {
            mTVComponent = new TVComponent(FacPropertyDemo.HIFAC_VENDOR, context);
        }
        return mTVComponent;
    }

    public static int getPictureCurvePointCount() {
        return FacPropertyDemo.HIFAC_PICTURECURVE_POINT_COUNT;
    }

    public static boolean isSSCSupport() {
        return FacPropertyDemo.HIFAC_SSC_SUPPORT;
    }

    public static boolean isSSCVByOneEnable() {
        return FacPropertyDemo.HIFAC_SSC_VBYONE_ENABLE;
    }

    public static boolean isSSCDdrEnable() {
        return FacPropertyDemo.HIFAC_SSC_DDR_ENABLE;
    }

    public TVComponent(String str, Context context) {
        mContext = context.getApplicationContext();
        mFacFunction = createFacFunction(str);
        this.mTVCalculator = TVCalculator.getInstance();
        initFacHashTable();
    }

    public final synchronized void addOption(String str, TVOption<?> tVOption) {
        if (this.optionTable.containsKey(str)) {
            return;
        }
        this.optionTable.put(str, tVOption);
    }

    public final synchronized TVOption<?> getOption(String str) {
        return this.optionTable.get(str);
    }

    public void initFacHashTable() {
        addIntegerRangeProxyOption("HIFAC_ALARMENABLE", 1, 0, 1);
        addIntegerRangeProxyOption("HIFAC_NO_SIGNAL_STANDBY", 1, 0, 1);
        addIntegerRangeProxyOption("FACTORY_TEMP_PROTECT", 0, 0, 1);
        addIntegerRangeProxyOption("DISPLAYBOARD_SOFTWARE_UPGRADE_PREPARE", 0, 0, 1);
        addIntegerRangeProxyOption("FACTORY_SCREEN_SELECT", 0, 0, 4);
        addIntegerRangeProxyOption("FACTORY_EYE_PROTECT", 0, 0, 1);
        addIntegerRangeProxyOption("FACTORY_FOREIGN_OBJECT_DETECT_SWITCH", 0, 0, 1);
        addIntegerRangeProxyOption("FACTORY_SLIDING_COVER_GAP_ADJUST", 0, 0, 30);
        addIntegerRangeProxyOption("HIFAC_RDRV", 128, 0, 255);
        addIntegerRangeProxyOption("HIFAC_GDRV", 128, 0, 255);
        addIntegerRangeProxyOption("HIFAC_BDRV", 128, 0, 255);
        addIntegerRangeProxyOption("HIFAC_RCUT", 128, 0, 511);
        addIntegerRangeProxyOption("HIFAC_GCUT", 128, 0, 511);
        addIntegerRangeProxyOption("HIFAC_BCUT", 128, 0, 511);
        addIntegerRangeProxyOption("HIFAC_PNL_SEL", 0, 0, 2);
        addIntegerRangeProxyOption("HIFAC_2D3D", 0, 0, 1);
        addIntegerRangeProxyOption("HIFAC_RGAIN_DELTA", 0, -30, 30);
        addIntegerRangeProxyOption("HIFAC_GGAIN_DELTA", 0, -30, 30);
        addIntegerRangeProxyOption("HIFAC_BGAIN_DELTA", 0, -30, 30);
        addIntegerRangeProxyOption("HIFAC_ROFFSET_DELTA", 0, -30, 30);
        addIntegerRangeProxyOption("HIFAC_GOFFSET_DELTA", 0, -30, 30);
        addIntegerRangeProxyOption("HIFAC_BOFFSET_DELTA", 0, -30, 30);
        addIntegerRangeProxyOption("HIFAC_TOFAC", 1, 0, 1);
        addIntegerRangeProxyOption("3D_Sync_Delay", 0, 0, 20000);
        addIntegerRangeProxyOption("HIFAC_POWERMODE", 2, 0, 2);
        addIntegerRangeProxyOption("HIFAC_HOMEORTV", 1, 0, 1);
        addIntegerRangeProxyOption("HIFAC_OSDLANGUAGE", 0, 0, 1);
        addIntegerRangeProxyOption("HIFAC_DMP_SETTINGS", 1, 0, 1);
        addIntegerRangeProxyOption("FACTORY_OPTION_VCOM_VOLTAGE", 1, FacPropertyDemo.HIFAC_MIN_VCOM_SPAN, FacPropertyDemo.HIFAC_MAX_VCOM_SPAN);
        addIntegerRangeProxyOption("FACTORY_OPTION_VCOM2_VOLTAGE", 1, FacPropertyDemo.HIFAC_MIN_VCOM_DOUBLE_CELL_FHD_SPAN, FacPropertyDemo.HIFAC_MAX_VCOM_DOUBLE_CELL_FHD_SPAN);
        addIntegerRangeProxyOption("HIFAC_UART_SWITCH", 0, 0, 1);
        addIntegerRangeProxyOption("HIFAC_USER_AGING_SWITCH", 0, 0, 1);
        addIntegerRangeProxyOption("FACTORY_ACCREDITATION", 0, 0, 1);
        addIntegerRangeProxyOption("HIFAC_SETCOUNTRY", 0, 0, 2);
        addIntegerRangeProxyOption("HIFAC_SETLOCAL_TWN", 0, 0, 1);
        addIntegerRangeProxyOption("HIFAC_SETLOCAL_JPN", 0, 0, 1);
        addIntegerRangeProxyOption("HIFAC_SETCOUNTRY_FOR_REGION1", 0, 0, 15);
        addIntegerRangeProxyOption("HIFAC_SETCOUNTRY_FOR_REGION2", 0, 0, 35);
        addIntegerRangeProxyOption("HIFAC_SETCOUNTRY_FOR_REGION3", 0, 0, 22);
        addIntegerRangeProxyOption("HIFAC_SETCOUNTRY_FOR_REGION4", 0, 0, 6);
        addIntegerRangeProxyOption("HIFAC_SETCOUNTRY_FOR_REGION5", 0, 0, 1);
        addIntegerRangeProxyOption("HIFAC_SETCOUNTRY_MONITOR", 0, 0, 14);
        addIntegerRangeProxyOption("HIFAC_SETREGION", 0, 0, 4);
        addIntegerRangeProxyOption("HIFAC_SETLANGUAGE", 0, 0, 2);
        addIntegerRangeProxyOption("HIFAC_SETLANGUAGE_FOR_REGION1", 0, 0, 20);
        addIntegerRangeProxyOption("HIFAC_SETLANGUAGE_FOR_REGION2", 0, 0, 18);
        addIntegerRangeProxyOption("HIFAC_SETLANGUAGE_FOR_REGION3", 0, 0, 10);
        addIntegerRangeProxyOption("HIFAC_SETLANGUAGE_FOR_REGION4", 0, 0, 17);
        addIntegerRangeProxyOption("HIFAC_SETLANGUAGE_FOR_REGION5", 0, 0, 2);
        addIntegerRangeProxyOption("HIFAC_SETLOGO", 0, 0, 1);
        addIntegerRangeProxyOption("HIFAC_SETLOGO_FOR_EM_NON_BRAND_ISOLATION", 0, 0, 11);
        addIntegerRangeProxyOption("HIFAC_SETLOGO_FOR_EM_BRAND_ISOLATION", 0, 0, 12);
        addIntegerRangeProxyOption("HIFAC_SETLOGO_FOR_SA", 0, 0, 17);
        addIntegerRangeProxyOption("HIFAC_SETLOGO_FOR_EM_SA", 0, 0, 1);
        addIntegerRangeProxyOption("HIFAC_SETLOGO_FOR_JPN", 0, 0, 2);
        addIntegerRangeProxyOption("HIFAC_SETLOGO_FOR_HK", 0, 0, 1);
        addIntegerRangeProxyOption("HIFAC_SETLOGO_FOR_KOR", 0, 0, 2);
        addIntegerRangeProxyOption("HIFAC_SETREGION_SA", 0, 0, 1);
        addIntegerRangeProxyOption("FACTORY_FUN_SETLOGO_FOR_MONITOR", 0, 0, 1);
        addIntegerRangeProxyOption("HIFAC_SETCOUNTRY_FOR_SA_REGION1", 0, 0, 7);
        addIntegerRangeProxyOption("HIFAC_SETCOUNTRY_FOR_SA_REGION2", 0, 0, 3);
        addIntegerRangeProxyOption("HIFAC_SETLANGUAGE_FOR_SA_REGION1", 0, 0, 4);
        addIntegerRangeProxyOption("HIFAC_SETLANGUAGE_FOR_SA_REGION2", 0, 0, 3);
        addIntegerRangeProxyOption("HIFAC_SETLANGUAGE_FOR_TWN", 0, 0, 2);
        addIntegerRangeProxyOption("HIFAC_SETLANGUAGE_FOR_JPN", 0, 0, 2);
        addIntegerRangeProxyOption("HIFAC_SETLANGUAGE_FOR_MONITOR", 0, 0, 11);
        addIntegerRangeProxyOption("HIFAC_DEMURA_SWITCH", 1, 0, 1);
        addIntegerRangeProxyOption("HIFAC_SETLANGUAGE_FOR_HK", 0, 0, 2);
        addIntegerRangeProxyOption("HIFAC_COLORTEMPERATURE", 0, 0, 4);
        addIntegerRangeProxyOption("HIFAC_COLOR_TEMP", 0, 0, 2);
        addIntegerRangeProxyOption("HIFAC_RDRV_OFFSET", 0, 0, 255);
        addIntegerRangeProxyOption("HIFAC_GDRV_OFFSET", 0, 0, 255);
        addIntegerRangeProxyOption("HIFAC_BDRV_OFFSET", 0, 0, 255);
        addIntegerRangeProxyOption("HIFAC_RCUT_OFFSET", 0, 0, 255);
        addIntegerRangeProxyOption("HIFAC_GCUT_OFFSET", 0, 0, 255);
        addIntegerRangeProxyOption("HIFAC_BCUT_OFFSET", 0, 0, 255);
        addIntegerRangeProxyOption("FACTORY_COLORTUNER", 0, 0, 6);
        addIntegerRangeProxyOption("FACTORY_CT_HUE", 0, -10, 10);
        addIntegerRangeProxyOption("FACTORY_CT_SATURATION", 0, -10, 10);
        addIntegerRangeProxyOption("FACTORY_CT_BRIGHTNESS", 0, -10, 10);
        addIntegerRangeProxyOption("HIFAC_ADC_RGAIN", 128, 0, 255);
        addIntegerRangeProxyOption("HIFAC_ADC_GGAIN", 128, 0, 255);
        addIntegerRangeProxyOption("HIFAC_ADC_BGAIN", 128, 0, 255);
        addIntegerRangeProxyOption("HIFAC_ADC_ROFFSET", 128, 0, 255);
        addIntegerRangeProxyOption("HIFAC_ADC_GOFFSET", 128, 0, 255);
        addIntegerRangeProxyOption("HIFAC_ADC_BOFFSET", 128, 0, 255);
        addIntegerRangeProxyOption("HIFAC_PICTUREMODE", 0, 0, 6);
        addIntegerRangeProxyOption("HIFAC_PICTUREMODE_TW", 0, 0, 5);
        addIntegerRangeProxyOption("HIFAC_PICTUREMODE_BRIGHTNESS", 50, 0, 100);
        addIntegerRangeProxyOption("HIFAC_PICTUREMODE_CONTRAST", 50, 0, 100);
        addIntegerRangeProxyOption("HIFAC_PICTUREMODE_COLOR", 50, 0, 100);
        addIntegerRangeProxyOption("HIFAC_PICTUREMODE_SHARPNESS", 50, 0, FacPropertyDemo.HIFAC_MAX_SHARPNESS_UI);
        if (FacPropertyDemo.HIFAC_IS_LASER4K) {
            addIntegerRangeProxyOption("HIFAC_PICTUREMODE_BACKLIGHT", 5, 0, 10);
        } else {
            addIntegerRangeProxyOption("HIFAC_PICTUREMODE_BACKLIGHT", 50, 0, 100);
        }
        addIntegerRangeProxyOption("HIFAC_SOUNDMODE", 0, 0, 7);
        addIntegerRangeProxyOption("HIFAC_EQUALIZE_120HZ", 0, -10, 10);
        addIntegerRangeProxyOption("HIFAC_EQUALIZE_500HZ", 0, -10, 10);
        addIntegerRangeProxyOption("HIFAC_EQUALIZE_1500HZ", 0, -10, 10);
        addIntegerRangeProxyOption("HIFAC_EQUALIZE_5KHZ", 0, -10, 10);
        addIntegerRangeProxyOption("HIFAC_EQUALIZE_10KHZ", 0, -10, 10);
        addIntegerRangeProxyOption("HIFAC_EQUALIZE_120HZ_NEW", 0, -10, 10);
        addIntegerRangeProxyOption("HIFAC_EQUALIZE_250HZ", 0, -10, 10);
        addIntegerRangeProxyOption("HIFAC_EQUALIZE_600HZ", 0, -10, 10);
        addIntegerRangeProxyOption("HIFAC_EQUALIZE_1KHZ", 0, -10, 10);
        addIntegerRangeProxyOption("HIFAC_EQUALIZE_2.5KHZ", 0, -10, 10);
        addIntegerRangeProxyOption("HIFAC_EQUALIZE_6KHZ", 0, -10, 10);
        addIntegerRangeProxyOption("HIFAC_EQUALIZE_10KHZ_NEW", 0, -10, 10);
        addIntegerRangeProxyOption("HIFAC_PICTURECURVE_MODE", 0, 0, 3);
        addIntegerRangeProxyOption("HIFAC_PICTURECURVE_CURVE0", 0, mFacFunction.getPictureCurveMinimum(), mFacFunction.getPictureCurveMaximum());
        addIntegerRangeProxyOption("HIFAC_PICTURECURVE_CURVE50", 50, mFacFunction.getPictureCurveMinimum(), mFacFunction.getPictureCurveMaximum());
        addIntegerRangeProxyOption("HIFAC_PICTURECURVE_CURVE100", 100, mFacFunction.getPictureCurveMinimum(), mFacFunction.getPictureCurveMaximum());
        if (getPictureCurvePointCount() == 5) {
            addIntegerRangeProxyOption("HIFAC_PICTURECURVE_CURVE25", 25, mFacFunction.getPictureCurveMinimum(), mFacFunction.getPictureCurveMaximum());
            addIntegerRangeProxyOption("HIFAC_PICTURECURVE_CURVE75", 75, mFacFunction.getPictureCurveMinimum(), mFacFunction.getPictureCurveMaximum());
        }
        addIntegerRangeProxyOption("HIFAC_PICTURECURVE_BRIGHT_MIN", 0, 0, 255);
        addIntegerRangeProxyOption("HIFAC_BRIGHT_20", 0, 0, 255);
        addIntegerRangeProxyOption("HIFAC_PICTURECURVE_BRIGHT_MID", 0, 0, 255);
        addIntegerRangeProxyOption("HIFAC_BRIGHT_80", 0, 0, 255);
        addIntegerRangeProxyOption("HIFAC_PICTURECURVE_BRIGHT_MAX", 0, 0, 255);
        addIntegerRangeProxyOption("HIFAC_PICTURECURVE_CONTRACT_MIN", 0, 0, 255);
        addIntegerRangeProxyOption("HIFAC_CONTRACT_20", 0, 0, 255);
        addIntegerRangeProxyOption("HIFAC_PICTURECURVE_CONTRACT_MID", 0, 0, 255);
        addIntegerRangeProxyOption("HIFAC_CONTRACT_80", 0, 0, 255);
        addIntegerRangeProxyOption("HIFAC_PICTURECURVE_CONTRACT_MAX", 0, 0, 255);
        addIntegerRangeProxyOption("HIFAC_PICTURECURVE_SATURATION_MIN", 0, 0, 255);
        addIntegerRangeProxyOption("HIFAC_SATURATION_20", 0, 0, 255);
        addIntegerRangeProxyOption("HIFAC_PICTURECURVE_SATURATION_MID", 0, 0, 255);
        addIntegerRangeProxyOption("HIFAC_SATURATION_80", 0, 0, 255);
        addIntegerRangeProxyOption("HIFAC_PICTURECURVE_SATURATION_MAX", 0, 0, 255);
        addIntegerRangeProxyOption("HIFAC_PICTURECURVE_HUE_MIN", 0, 0, 255);
        addIntegerRangeProxyOption("HIFAC_HUE_20", 0, 0, 255);
        addIntegerRangeProxyOption("HIFAC_PICTURECURVE_HUE_MID", 0, 0, 255);
        addIntegerRangeProxyOption("HIFAC_HUE_80", 0, 0, 255);
        addIntegerRangeProxyOption("HIFAC_PICTURECURVE_HUE_MAX", 0, 0, 255);
        addIntegerRangeProxyOption("HIFAC_PICTURECURVE_SHARPNESS_MIN", 0, 0, 255);
        addIntegerRangeProxyOption("HIFAC_PICTURECURVE_SHARPNESS_20", 0, 0, 255);
        addIntegerRangeProxyOption("HIFAC_PICTURECURVE_SHARPNESS_MID", 0, 0, 255);
        addIntegerRangeProxyOption("HIFAC_PICTURECURVE_SHARPNESS_80", 0, 0, 255);
        addIntegerRangeProxyOption("HIFAC_PICTURECURVE_SHARPNESS_MAX", 0, 0, 255);
        addIntegerRangeProxyOption("FACTORY_20POINT_LEVEL", 0, 0, 19);
        addIntegerRangeProxyOption("FACTORY_20POINT_RED", 0, -25, 25);
        addIntegerRangeProxyOption("FACTORY_20POINT_GREEN", 0, -25, 25);
        addIntegerRangeProxyOption("FACTORY_20POINT_BLUE", 0, -25, 25);
        addIntegerRangeProxyOption("HIFAC_AUDIOCURVE_CURVE1", 0, 0, 100);
        addIntegerRangeProxyOption("HIFAC_AUDIOCURVE_CURVE25", 25, 0, 100);
        addIntegerRangeProxyOption("HIFAC_AUDIOCURVE_CURVE50", 50, 0, 100);
        addIntegerRangeProxyOption("HIFAC_AUDIOCURVE_CURVE75", 75, 0, 100);
        addIntegerRangeProxyOption("HIFAC_AUDIOCURVE_CURVE100", 100, 0, 100);
        addIntegerRangeProxyOption("HIFAC_PRESCALE", 0, 0, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_MSGQ_EMPTY);
        addIntegerRangeProxyOption("HIFAC_AUDIOCURVE_CURVE1_NEW", 0, 0, 100);
        addIntegerRangeProxyOption("HIFAC_AUDIOCURVE_CURVE10_NEW", 10, 0, 100);
        addIntegerRangeProxyOption("HIFAC_AUDIOCURVE_CURVE25_NEW", 25, 0, 100);
        addIntegerRangeProxyOption("HIFAC_AUDIOCURVE_CURVE50_NEW", 50, 0, 100);
        addIntegerRangeProxyOption("HIFAC_AUDIOCURVE_CURVE75_NEW", 75, 0, 100);
        addIntegerRangeProxyOption("HIFAC_AUDIOCURVE_CURVE100_NEW", 100, 0, 100);
        addIntegerRangeProxyOption("HIFAC_PEQ_1_FREQ", 20, FacPropertyDemo.HIFAC_MIN_FREQ, FacPropertyDemo.HIFAC_MAX_FREQ);
        addIntegerRangeProxyOption("HIFAC_PEQ_2_FREQ", 20, FacPropertyDemo.HIFAC_MIN_FREQ, FacPropertyDemo.HIFAC_MAX_FREQ);
        addIntegerRangeProxyOption("HIFAC_PEQ_3_FREQ", 20, FacPropertyDemo.HIFAC_MIN_FREQ, FacPropertyDemo.HIFAC_MAX_FREQ);
        addIntegerRangeProxyOption("HIFAC_PEQ_4_FREQ", 20, FacPropertyDemo.HIFAC_MIN_FREQ, FacPropertyDemo.HIFAC_MAX_FREQ);
        addIntegerRangeProxyOption("HIFAC_PEQ_5_FREQ", 20, FacPropertyDemo.HIFAC_MIN_FREQ, FacPropertyDemo.HIFAC_MAX_FREQ);
        addIntegerRangeProxyOption("HIFAC_PEQ_6_FREQ", 20, FacPropertyDemo.HIFAC_MIN_FREQ, FacPropertyDemo.HIFAC_MAX_FREQ);
        addIntegerRangeProxyOption("HIFAC_PEQ_7_FREQ", 20, FacPropertyDemo.HIFAC_MIN_FREQ, FacPropertyDemo.HIFAC_MAX_FREQ);
        addIntegerRangeProxyOption("HIFAC_PEQ_8_FREQ", 20, FacPropertyDemo.HIFAC_MIN_FREQ, FacPropertyDemo.HIFAC_MAX_FREQ);
        addIntegerRangeProxyOption("HIFAC_PEQ_1_GAIN", 0, FacPropertyDemo.HIFAC_MIN_GAIN, FacPropertyDemo.HIFAC_MAX_GAIN);
        addIntegerRangeProxyOption("HIFAC_PEQ_2_GAIN", 0, FacPropertyDemo.HIFAC_MIN_GAIN, FacPropertyDemo.HIFAC_MAX_GAIN);
        addIntegerRangeProxyOption("HIFAC_PEQ_3_GAIN", 0, FacPropertyDemo.HIFAC_MIN_GAIN, FacPropertyDemo.HIFAC_MAX_GAIN);
        addIntegerRangeProxyOption("HIFAC_PEQ_4_GAIN", 0, FacPropertyDemo.HIFAC_MIN_GAIN, FacPropertyDemo.HIFAC_MAX_GAIN);
        addIntegerRangeProxyOption("HIFAC_PEQ_5_GAIN", 0, FacPropertyDemo.HIFAC_MIN_GAIN, FacPropertyDemo.HIFAC_MAX_GAIN);
        addIntegerRangeProxyOption("HIFAC_PEQ_6_GAIN", 0, FacPropertyDemo.HIFAC_MIN_GAIN, FacPropertyDemo.HIFAC_MAX_GAIN);
        addIntegerRangeProxyOption("HIFAC_PEQ_7_GAIN", 0, FacPropertyDemo.HIFAC_MIN_GAIN, FacPropertyDemo.HIFAC_MAX_GAIN);
        addIntegerRangeProxyOption("HIFAC_PEQ_8_GAIN", 0, FacPropertyDemo.HIFAC_MIN_GAIN, FacPropertyDemo.HIFAC_MAX_GAIN);
        addIntegerRangeProxyOption("HIFAC_PEQ_1_Q", 5, FacPropertyDemo.HIFAC_MIN_Q, FacPropertyDemo.HIFAC_MAX_Q);
        addIntegerRangeProxyOption("HIFAC_PEQ_2_Q", 5, FacPropertyDemo.HIFAC_MIN_Q, FacPropertyDemo.HIFAC_MAX_Q);
        addIntegerRangeProxyOption("HIFAC_PEQ_3_Q", 5, FacPropertyDemo.HIFAC_MIN_Q, FacPropertyDemo.HIFAC_MAX_Q);
        addIntegerRangeProxyOption("HIFAC_PEQ_4_Q", 5, FacPropertyDemo.HIFAC_MIN_Q, FacPropertyDemo.HIFAC_MAX_Q);
        addIntegerRangeProxyOption("HIFAC_PEQ_5_Q", 5, FacPropertyDemo.HIFAC_MIN_Q, FacPropertyDemo.HIFAC_MAX_Q);
        addIntegerRangeProxyOption("HIFAC_PEQ_6_Q", 5, FacPropertyDemo.HIFAC_MIN_Q, FacPropertyDemo.HIFAC_MAX_Q);
        addIntegerRangeProxyOption("HIFAC_PEQ_7_Q", 5, FacPropertyDemo.HIFAC_MIN_Q, FacPropertyDemo.HIFAC_MAX_Q);
        addIntegerRangeProxyOption("HIFAC_PEQ_8_Q", 5, FacPropertyDemo.HIFAC_MIN_Q, FacPropertyDemo.HIFAC_MAX_Q);
        addIntegerRangeProxyOption("HIFAC_PEQ_1_STEP", 10, FacPropertyDemo.HIFAC_MIN_STEP, FacPropertyDemo.HIFAC_MAX_STEP);
        addIntegerRangeProxyOption("HIFAC_PEQ_2_STEP", 10, FacPropertyDemo.HIFAC_MIN_STEP, FacPropertyDemo.HIFAC_MAX_STEP);
        addIntegerRangeProxyOption("HIFAC_PEQ_3_STEP", 10, FacPropertyDemo.HIFAC_MIN_STEP, FacPropertyDemo.HIFAC_MAX_STEP);
        addIntegerRangeProxyOption("HIFAC_PEQ_4_STEP", 10, FacPropertyDemo.HIFAC_MIN_STEP, FacPropertyDemo.HIFAC_MAX_STEP);
        addIntegerRangeProxyOption("HIFAC_PEQ_5_STEP", 10, FacPropertyDemo.HIFAC_MIN_STEP, FacPropertyDemo.HIFAC_MAX_STEP);
        addIntegerRangeProxyOption("HIFAC_PEQ_6_STEP", 10, FacPropertyDemo.HIFAC_MIN_STEP, FacPropertyDemo.HIFAC_MAX_STEP);
        addIntegerRangeProxyOption("HIFAC_PEQ_7_STEP", 10, FacPropertyDemo.HIFAC_MIN_STEP, FacPropertyDemo.HIFAC_MAX_STEP);
        addIntegerRangeProxyOption("HIFAC_PEQ_8_STEP", 10, FacPropertyDemo.HIFAC_MIN_STEP, FacPropertyDemo.HIFAC_MAX_STEP);
        addIntegerRangeProxyOption("FACTORY_BL_MIN_BACKLIGHT", 0, 0, 100);
        addIntegerRangeProxyOption("FACTORY_BL_20_BACKLIGHT", MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_REQ_STOP, 0, 100);
        addIntegerRangeProxyOption("FACTORY_BL_MID_BACKLIGHT", 0, 0, 100);
        addIntegerRangeProxyOption("FACTORY_BL_80_BACKLIGHT", MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_REQ_STOP, 0, 100);
        addIntegerRangeProxyOption("FACTORY_BL_MAX_BACKLIGHT", MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_RANGE_AB_DONE, 0, 100);
        addIntegerRangeProxyOption("FACTORY_BL_PWM_FREQ", 90, 0, 1000);
        addIntegerRangeProxyOption("HIFAC_OVERSCAN_HSTART", 0, 0, 1000);
        addIntegerRangeProxyOption("HIFAC_OVERSCAN_VSTART", 0, 0, 1000);
        addIntegerRangeProxyOption("HIFAC_OVERSCAN_HPOS", 0, 0, 1000);
        addIntegerRangeProxyOption("HIFAC_OVERSCAN_VPOS", 0, 0, 1000);
        addIntegerRangeProxyOption("HIFAC_OVERSCAN_HSIZE", 50, 0, 1000);
        addIntegerRangeProxyOption("HIFAC_OVERSCAN_VSIZE", 50, 0, 1000);
        addIntegerRangeProxyOption("HIFAC_OVERSCAN_TOP", 0, 0, 1000);
        addIntegerRangeProxyOption("HIFAC_OVERSCAN_BUTTOM", 0, 0, 1000);
        addIntegerRangeProxyOption("HIFAC_OVERSCAN_LEFT", 0, 0, 1000);
        addIntegerRangeProxyOption("HIFAC_OVERSCAN_RIGHT", 0, 0, 1000);
        addIntegerRangeProxyOption("FACTORY_PQ_BYPASS_ENABLE", 1, 0, 1);
        addIntegerRangeProxyOption("FACTORY_GAMMA_BYPASS_ENABLE", 1, 0, 1);
        addIntegerRangeProxyOption("FACTORY_COLOR_BYPASS_ENABLE", 1, 0, 1);
        addIntegerRangeProxyOption("FACTORY_LOCALDIMMING_ENABLE", 1, 0, 1);
        addIntegerRangeProxyOption("FACTORY_IMAGE_COMPENSATION_ENABLE", 1, 0, 1);
        addIntegerRangeProxyOption("HIFAC_BYPASS_PQ", 1, 0, 1);
        addIntegerRangeProxyOption("HIFAC_BYPASS_GAMMA", 1, 0, 1);
        addIntegerRangeProxyOption("HIFAC_BYPASS_SEC", 1, 0, 1);
        addIntegerRangeProxyOption("HIFAC_BYPASS_XVYCC", 1, 0, 1);
        addIntegerRangeProxyOption("HIFAC_SSC_VBYONE_ENABLE", 1, 0, 1);
        addIntegerRangeProxyOption("HIFAC_SSC_VBYONE_RANGE", 5, 0, 50);
        addIntegerRangeProxyOption("HIFAC_SSC_VBYONE_SPAN", 100, 1, FacPropertyDemo.HIFAC_MAX_VBYONE_SPAN);
        addIntegerRangeProxyOption("HIFAC_SSC_VBYONE_MODULATION", 20, FacPropertyDemo.HIFAC_MIN_VBYONE_MODU, FacPropertyDemo.HIFAC_MAX_VBYONE_MODU);
        addIntegerRangeProxyOption("HIFAC_SSC_DDR_ENABLE", 1, 0, 1);
        addIntegerRangeProxyOption("HIFAC_SSC_DDR_SPAN", 100, FacPropertyDemo.HIFAC_MIN_DDRSSC_SPAN, FacPropertyDemo.HIFAC_MAX_DDRSSC_SPAN);
        addIntegerRangeProxyOption("HIFAC_SSC_DDR_MODULATION", 1, FacPropertyDemo.HIFAC_MIN_DDRSSC_MODU, FacPropertyDemo.HIFAC_MAX_DDRSSC_MODU);
        addIntegerRangeProxyOption("HIFAC_VOLUME", 0, 0, 100);
        addIntegerRangeProxyOption("HIFAC_AGING", 0, 0, 1);
        addIntegerRangeProxyOption("HIFAC_STATUS", 1, 0, 1);
        addIntegerRangeProxyOption("HIFAC_SAVINGMODE", 0, 0, 1);
        addIntegerRangeProxyOption("HIFAC_ZOOMMODE", 0, 0, 7);
        addIntegerRangeProxyOption("HIFAC_ZOOMMODE_FOR_EM", 0, 0, 5);
        addIntegerRangeProxyOption("HIFAC_BALANCE", 0, FacPropertyDemo.HIFAC_MIN_BALANCE, FacPropertyDemo.HIFAC_MAX_BALANCE);
        addIntegerRangeProxyOption("HIFAC_BALANCE_DOUBLE_AMP", 0, FacPropertyDemo.HIFAC_MIN_DOUBLE_AMP_BALANCE, FacPropertyDemo.HIFAC_MAX_DOUBLE_AMP_BALANCE);
        addIntegerRangeProxyOption("HIFAC_BALANCE_MULTI_AMP", 0, FacPropertyDemo.HIFAC_MIN_DOUBLE_AMP_BALANCE, FacPropertyDemo.HIFAC_MAX_MULTI_AMP_BALANCE);
        addIntegerRangeProxyOption("HIFAC_BALANCE_UNIV_MULTI_AMP", 0, FacPropertyDemo.HIFAC_MIN_UNIV_MULTI_AMP_BALANCE, FacPropertyDemo.HIFAC_MAX_UNIV_MULTI_AMP_BALANCE);
        addIntegerRangeProxyOption("HIFAC_TVS_BALANCE_LIKE_550", 0, FacPropertyDemo.HIFAC_MIN_TWEETER_BALANCE, FacPropertyDemo.HIFAC_MAX_TWEETER_BALANCE);
        addIntegerRangeProxyOption("HIFAC_TVS_BALANCE_LIKE_650", 0, FacPropertyDemo.HIFAC_MIN_TWEETER_WOOFER_BALANCE, FacPropertyDemo.HIFAC_MAX_TWEETER_WOOFER_BALANCE);
        addIntegerRangeProxyOption("HIFAC_TVS_BALANCE_LIKE_850", 0, FacPropertyDemo.HIFAC_MIN_BALANCE_LIKE_850, FacPropertyDemo.HIFAC_MAX_BALANCE_LIKE_850);
        addIntegerRangeProxyOption("HIFAC_TUNERMODE", 0, 0, 1);
        addIntegerRangeProxyOption("HIFAC_3DMODE", 0, 0, 5);
        addIntegerRangeProxyOption("HIFAC_LR_SWAP", 0, 0, 1);
        addIntegerRangeProxyOption("HIFAC_DEPTH_OF_FIELD", 0, 0, 10);
        addIntegerRangeProxyOption("HIFAC_CONVERGENCE", 0, 0, 10);
        addIntegerRangeProxyOption("HIFAC_3DTO2D", 0, 0, 1);
        addIntegerRangeProxyOption("HIFAC_F7_NETWORK", 0, 0, 1);
        addIntegerRangeProxyOption("HIFAC_AVC_THRESHOLD", 0, 0, 40);
        addIntegerRangeProxyOption("FACTORY_AVC_ATTACK_TIME", 0, 0, 7);
        addIntegerRangeProxyOption("FACTORY_AVC_RELEASE_TIME", 0, 0, 7);
        addIntegerRangeProxyOption("HIFAC_OLED_TPC_ENABLE", 1, 0, 1);
        addIntegerRangeProxyOption("HIFAC_OLED_PIXEL_MOVE_SHAPE", 1, 0, 1);
        addIntegerRangeProxyOption("HIFAC_OLED_PIXEL_MOVE_TIMER", 60, 10, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_RANGE_AB_DONE);
        addIntegerRangeProxyOption("HIFAC_OLED_PIXEL_MOVE_TYPE", 0, 0, 2);
        addIntegerRangeProxyOption("HIFAC_OLED_LEA_ENABLE", 1, 0, 1);
        addIntegerRangeProxyOption("HIFAC_OLED_CPC_ENABLE", 1, 0, 1);
        addIntegerRangeProxyOption("HIFAC_OLED_LBC2_ENABLE", 1, 0, 1);
        addIntegerRangeProxyOption("HIFAC_OLED_ORBIT_ENABLE", 1, 0, 1);
        addIntegerRangeProxyOption("HIFAC_OLED_LBA_ENABLE", 1, 0, 1);
        addIntegerRangeProxyOption("HIFAC_OLED_HDR_ENABLE", 0, 0, 1);
        if (FacPropertyDemo.HIFAC_AMP_MODEL == FacPropertyDemo.AmplifierModel.NTP8212) {
            addIntegerRangeProxyOption("HIFAC_AUDIO_DRC_L", 0, 0, 20);
            addIntegerRangeProxyOption("HIFAC_AUDIO_DRC_H", 0, 0, 20);
            addIntegerRangeProxyOption("HIFAC_AUDIO_DRC_P", 0, 0, 20);
            addIntegerRangeProxyOption("HIFAC_AUDIO_PA_GAIN", 0, 0, 40);
            addIntegerRangeProxyOption("HIFAC_AUDIO_OUTPUT_PORT", 0, 0, 42);
            addIntegerRangeProxyOption("HIFAC_WOOFER_AUDIO_DRC", 0, 0, 20);
            addIntegerRangeProxyOption("HIFAC_WOOFER_AUDIO_PA_GAIN", 0, 0, 40);
            addIntegerRangeProxyOption("HIFAC_WOOFER_AUDIO_OUTPUT_PORT", 0, 0, 42);
        } else if (FacPropertyDemo.HIFAC_AMP_MODEL == FacPropertyDemo.AmplifierModel.TAS5711) {
            addIntegerRangeProxyOption("HIFAC_AUDIO_DRC", 0, 0, 60);
            addIntegerRangeProxyOption("HIFAC_AUDIO_PA_GAIN", 0, 0, 255);
            addIntegerRangeProxyOption("HIFAC_WOOFER_AUDIO_DRC", 0, 0, 60);
            addIntegerRangeProxyOption("HIFAC_WOOFER_AUDIO_PA_GAIN", 0, 0, 41);
            addIntegerRangeProxyOption("HIFAC_AUDIO_OUTPUT_PORT", 0, 0, 42);
        } else if (FacPropertyDemo.HIFAC_AMP_MODEL == FacPropertyDemo.AmplifierModel.TAS5727) {
            addIntegerRangeProxyOption("HIFAC_AUDIO_DRC", 0, 0, 60);
            addIntegerRangeProxyOption("HIFAC_AUDIO_PA_GAIN", 0, 0, 41);
            addIntegerRangeProxyOption("HIFAC_WOOFER_AUDIO_DRC", 0, 0, 60);
            addIntegerRangeProxyOption("HIFAC_WOOFER_AUDIO_PA_GAIN", 0, 0, 41);
            addIntegerRangeProxyOption("HIFAC_AUDIO_OUTPUT_PORT", 0, 0, 42);
        } else if (FacPropertyDemo.HIFAC_AMP_MODEL == FacPropertyDemo.AmplifierModel.NTP8214) {
            addIntegerRangeProxyOption("HIFAC_AUDIO_DRC_L", 0, 0, 20);
            addIntegerRangeProxyOption("HIFAC_AUDIO_DRC_H", 0, 0, 20);
            addIntegerRangeProxyOption("HIFAC_AUDIO_DRC_S", 0, 0, 20);
            addIntegerRangeProxyOption("HIFAC_AUDIO_DRC_P", 0, 0, 20);
            addIntegerRangeProxyOption("HIFAC_AUDIO_PA_GAIN", 0, 0, 40);
            addIntegerRangeProxyOption("HIFAC_AUDIO_OUTPUT_PORT", 0, 0, 42);
        } else if (FacPropertyDemo.HIFAC_AMP_MODEL == FacPropertyDemo.AmplifierModel.TAS5733) {
            addIntegerRangeProxyOption("HIFAC_AUDIO_DRC_L", 0, 0, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_OUT_OF_SAFE_RANGE_START);
            addIntegerRangeProxyOption("HIFAC_AUDIO_DRC_H", 0, 0, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_OUT_OF_SAFE_RANGE_START);
            addIntegerRangeProxyOption("HIFAC_AUDIO_DRC_S", 0, 0, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_OUT_OF_SAFE_RANGE_START);
            addIntegerRangeProxyOption("HIFAC_AUDIO_DRC_P", 0, 0, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_OUT_OF_SAFE_RANGE_START);
            addIntegerRangeProxyOption("HIFAC_AUDIO_PA_GAIN", 0, 0, 49);
            addIntegerRangeProxyOption("HIFAC_AUDIO_OUTPUT_PORT", 0, 0, 42);
        } else if (FacPropertyDemo.HIFAC_AMP_MODEL == FacPropertyDemo.AmplifierModel.TAS5751) {
            addIntegerRangeProxyOption("HIFAC_AUDIO_DRC_L", 0, 0, 61);
            addIntegerRangeProxyOption("HIFAC_AUDIO_DRC_H", 0, 0, 61);
            addIntegerRangeProxyOption("HIFAC_AUDIO_DRC_S", 0, 0, 61);
            addIntegerRangeProxyOption("HIFAC_AUDIO_DRC_P", 0, 0, 61);
            addIntegerRangeProxyOption("HIFAC_AUDIO_PA_GAIN", 0, 0, 49);
            addIntegerRangeProxyOption("HIFAC_WOOFER_AUDIO_DRC", 0, 0, 61);
            addIntegerRangeProxyOption("HIFAC_WOOFER_AUDIO_PA_GAIN", 0, 0, 49);
            addIntegerRangeProxyOption("HIFAC_AUDIO_OUTPUT_PORT", 0, 0, 42);
        } else if (FacPropertyDemo.HIFAC_AMP_MODEL == FacPropertyDemo.AmplifierModel.NTP8822) {
            addIntegerRangeProxyOption("HIFAC_AUDIO_DRC_L", 0, 0, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_RATING_UNBLOCKED);
            addIntegerRangeProxyOption("HIFAC_AUDIO_DRC_H", 0, 0, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_RATING_UNBLOCKED);
            addIntegerRangeProxyOption("HIFAC_AUDIO_OUTPUT_PORT", 0, 0, 255);
            addIntegerRangeProxyOption("HIFAC_AUDIO_PA_GAIN", 0, 0, 251);
            addIntegerRangeProxyOption("HIFAC_AUDIO_OUTPUT_PORT", 0, 0, 42);
        } else if (FacPropertyDemo.HIFAC_AMP_MODEL == FacPropertyDemo.AmplifierModel.NTP8824) {
            addIntegerRangeProxyOption("HIFAC_AUDIO_DRC_L", 0, 0, 98);
            addIntegerRangeProxyOption("HIFAC_AUDIO_DRC_S", 0, 0, 98);
            addIntegerRangeProxyOption("HIFAC_AUDIO_DRC_P", 0, 0, 98);
            addIntegerRangeProxyOption("HIFAC_AUDIO_DRC_H", 0, 0, 98);
            addIntegerRangeProxyOption("HIFAC_AUDIO_OUTPUT_PORT", 0, 0, 54);
            addIntegerRangeProxyOption("HIFAC_AUDIO_PA_GAIN", 0, 0, 48);
            addIntegerRangeProxyOption("HIFAC_AUDIO_SUB1_DRC_L", 0, 0, 98);
            addIntegerRangeProxyOption("HIFAC_AUDIO_SUB1_DRC_S", 0, 0, 98);
            addIntegerRangeProxyOption("HIFAC_AUDIO_SUB1_DRC_P", 0, 0, 98);
            addIntegerRangeProxyOption("HIFAC_AUDIO_SUB1_DRC_H", 0, 0, 98);
            addIntegerRangeProxyOption("HIFAC_SUB1_AUDIO_OUTPUT_PORT", 0, 0, 54);
            addIntegerRangeProxyOption("HIFAC_SUB1_AUDIO_PA_GAIN", 0, 0, 48);
            addIntegerRangeProxyOption("HIFAC_AUDIO_SUB2_DRC_L", 0, 0, 98);
            addIntegerRangeProxyOption("HIFAC_AUDIO_SUB2_DRC_S", 0, 0, 98);
            addIntegerRangeProxyOption("HIFAC_AUDIO_SUB2_DRC_P", 0, 0, 98);
            addIntegerRangeProxyOption("HIFAC_AUDIO_SUB2_DRC_H", 0, 0, 98);
            addIntegerRangeProxyOption("HIFAC_SUB2_AUDIO_OUTPUT_PORT", 0, 0, 54);
            addIntegerRangeProxyOption("HIFAC_SUB2_AUDIO_PA_GAIN", 0, 0, 48);
            addIntegerRangeProxyOption("HIFAC_AUDIO_SUB3_DRC_L", 0, 0, 98);
            addIntegerRangeProxyOption("HIFAC_AUDIO_SUB3_DRC_S", 0, 0, 98);
            addIntegerRangeProxyOption("HIFAC_AUDIO_SUB3_DRC_P", 0, 0, 98);
            addIntegerRangeProxyOption("HIFAC_AUDIO_SUB3_DRC_H", 0, 0, 98);
            addIntegerRangeProxyOption("HIFAC_SUB3_AUDIO_OUTPUT_PORT", 0, 0, 54);
            addIntegerRangeProxyOption("HIFAC_SUB3_AUDIO_PA_GAIN", 0, 0, 48);
            addIntegerRangeProxyOption("HIFAC_AUDIO_SUB4_DRC_L", 0, 0, 98);
            addIntegerRangeProxyOption("HIFAC_AUDIO_SUB4_DRC_S", 0, 0, 98);
            addIntegerRangeProxyOption("HIFAC_AUDIO_SUB4_DRC_P", 0, 0, 98);
            addIntegerRangeProxyOption("HIFAC_AUDIO_SUB4_DRC_H", 0, 0, 98);
            addIntegerRangeProxyOption("HIFAC_SUB4_AUDIO_OUTPUT_PORT", 0, 0, 54);
            addIntegerRangeProxyOption("HIFAC_SUB4_AUDIO_PA_GAIN", 0, 0, 48);
            addIntegerRangeProxyOption("HIFAC_AUDIO_SUB5_DRC_L", 0, 0, 98);
            addIntegerRangeProxyOption("HIFAC_AUDIO_SUB5_DRC_S", 0, 0, 98);
            addIntegerRangeProxyOption("HIFAC_AUDIO_SUB5_DRC_P", 0, 0, 98);
            addIntegerRangeProxyOption("HIFAC_AUDIO_SUB5_DRC_H", 0, 0, 98);
            addIntegerRangeProxyOption("HIFAC_SUB5_AUDIO_OUTPUT_PORT", 0, 0, 54);
            addIntegerRangeProxyOption("HIFAC_SUB5_AUDIO_PA_GAIN", 0, 0, 48);
        } else {
            addIntegerRangeProxyOption("HIFAC_AUDIO_DRC", 0, 0, 60);
            addIntegerRangeProxyOption("HIFAC_AUDIO_PA_GAIN", 0, 0, 255);
            addIntegerRangeProxyOption("HIFAC_WOOFER_AUDIO_DRC", 0, 0, 60);
            addIntegerRangeProxyOption("HIFAC_WOOFER_AUDIO_PA_GAIN", 0, 0, 41);
            addIntegerRangeProxyOption("HIFAC_AUDIO_OUTPUT_PORT", 0, 0, 42);
        }
        addIntegerRangeProxyOption("HIFAC_MAXXBASS_FREQUENCY", 0, 0, 24);
        addIntegerRangeProxyOption("HIFAC_MAXXTREBLE_FREQUENCY", 0, 0, 32);
        addIntegerRangeProxyOption("HIFAC_MAXX3D_OPTION", 0, 0, 8);
        addIntegerRangeProxyOption("HIFAC_LIGHT_SENSOR_ENABLE", 1, 0, 1);
        addIntegerRangeProxyOption("HIFAC_LIGHT_SENSOR_VALUE0", 0, 0, 65535);
        addIntegerRangeProxyOption("HIFAC_PWM0", 0, 0, 100);
        addIntegerRangeProxyOption("FACTORY_LUX_VALUE0", 0, 0, 65535);
        addIntegerRangeProxyOption("HIFAC_LIGHT_SENSOR_VALUE1", 0, 0, 65535);
        addIntegerRangeProxyOption("HIFAC_PWM1", 0, 0, 100);
        addIntegerRangeProxyOption("FACTORY_LUX_VALUE1", 0, 0, 65535);
        addIntegerRangeProxyOption("HIFAC_LIGHT_SENSOR_VALUE2", 0, 0, 65535);
        addIntegerRangeProxyOption("HIFAC_PWM2", 0, 0, 100);
        addIntegerRangeProxyOption("FACTORY_LUX_VALUE2", 0, 0, 65535);
        addIntegerRangeProxyOption("HIFAC_LIGHT_SENSOR_VALUE3", 0, 0, 65535);
        addIntegerRangeProxyOption("HIFAC_PWM3", 0, 0, 100);
        addIntegerRangeProxyOption("FACTORY_LUX_VALUE3", 0, 0, 65535);
        addIntegerRangeProxyOption("HIFAC_LIGHT_SENSOR_VALUE4", 0, 0, 65535);
        addIntegerRangeProxyOption("HIFAC_PWM4", 0, 0, 100);
        addIntegerRangeProxyOption("FACTORY_LUX_VALUE4", 0, 0, 65535);
        addIntegerRangeProxyOption("HIFAC_LIGHT_SENSOR_VALUE5", 0, 0, 65535);
        addIntegerRangeProxyOption("HIFAC_PWM5", 0, 0, 100);
        addIntegerRangeProxyOption("FACTORY_LUX_VALUE5", 0, 0, 65535);
        addIntegerRangeProxyOption("HIFAC_LIGHT_SENSOR_VALUE6", 0, 0, 65535);
        addIntegerRangeProxyOption("HIFAC_PWM6", 0, 0, 100);
        addIntegerRangeProxyOption("FACTORY_LUX_VALUE6", 0, 0, 65535);
        addIntegerRangeProxyOption("HIFAC_LIGHT_SENSOR_VALUE7", 0, 0, 65535);
        addIntegerRangeProxyOption("HIFAC_PWM7", 0, 0, 100);
        addIntegerRangeProxyOption("FACTORY_LUX_VALUE7", 0, 0, 65535);
        addIntegerRangeProxyOption("HIFAC_AUD_HPF", 1, 0, 1);
        addIntegerRangeProxyOption("HIFAC_AUD_PEQ_ENABLE", 1, 0, 1);
        addIntegerRangeProxyOption("HIFAC_AUD_SOC_DRC_ENABLE", 1, 0, 1);
        addIntegerRangeProxyOption("HIFAC_AUD_AUTO_VOLUEM_CTRL_ENABLE", 1, 0, 1);
        addIntegerRangeProxyOption("HIFAC_DESCRAMBLEBOX", 0, 0, 1);
        addIntegerRangeProxyOption("HIFAC_DESCRAMBLEBOX_TVD", 0, 0, 1);
        addIntegerRangeProxyOption("HIFAC_NONSTANDARD_VIF_TOP", 3, 0, 15);
        addIntegerRangeProxyOption("HIFAC_NONSTANDARD_VIF_VGA_MAXIMUM", 0, 0, 65535);
        addIntegerRangeProxyOption("HIFAC_NONSTANDARD_VIF_CR_KP", 0, 0, 15);
        addIntegerRangeProxyOption("HIFAC_NONSTANDARD_VIF_CR_KI", 0, 0, 15);
        addIntegerRangeProxyOption("HIFAC_NONSTANDARD_VIF_ASIA_SIGNAL_OPTION", 1, 0, 1);
        addIntegerRangeProxyOption("HIFAC_NONSTANDARD_VIF_CR_KP_KI_ADJUST", 1, 0, 1);
        addIntegerRangeProxyOption("HIFAC_NONSTANDARD_HI_DEV", 1, 0, 1);
        addIntegerRangeProxyOption("HIFAC_NONSTANDARD_VIF_CLAMPGAIN_GAIN_OV_NEGATIVE", 0, 0, 65535);
        addIntegerRangeProxyOption("HIFAC_NONSTANDARD_CHINA_DESCRAMBLER_BOX", 5, 0, 15);
        addIntegerRangeProxyOption("HIFAC_NONSTANDARD_Delay", 0, 0, 10);
        addIntegerRangeProxyOption("HIFAC_NONSTANDARD_VIF_CR_THR", 0, 0, 4095);
        addIntegerRangeProxyOption("HIFAC_NONSTANDARD_VIF_VERSION", 0, 0, 255);
        addIntegerRangeProxyOption("HIFAC_NONSTANDARD_VIF_AGC_REF", 0, 0, 255);
        addIntegerRangeProxyOption("HIFAC_NONSTANDARD_GAIN_DISTRIBUTION_THR", 0, 0, 65535);
        addIntegerRangeProxyOption("HIFAC_NONSTANDARD_AEFC_D4", 0, 0, 255);
        addIntegerRangeProxyOption("HIFAC_NONSTANDARD_AEFC_D5", 0, 0, 1);
        addIntegerRangeProxyOption("HIFAC_NONSTANDARD_AEFC_D8", 0, 0, 15);
        addIntegerRangeProxyOption("HIFAC_NONSTANDARD_AEFC_D9[0]", 0, 0, 1);
        addIntegerRangeProxyOption("HIFAC_NONSTANDARD_AEFC_D7_HIGH_BOUN", 0, 0, 255);
        addIntegerRangeProxyOption("HIFAC_NONSTANDARD_AEFC_D7_LOW_BOUN", 0, 0, 255);
        addIntegerRangeProxyOption("HIFAC_NONSTANDARD_AEFC_A0", 0, 0, 255);
        addIntegerRangeProxyOption("HIFAC_NONSTANDARD_AEFC_A1", 0, 0, 255);
        addIntegerRangeProxyOption("HIFAC_NONSTANDARD_AEFC_66", 0, 0, 3);
        addIntegerRangeProxyOption("HIFAC_NONSTANDARD_AEFC_6E", 0, 0, 15);
        addIntegerRangeProxyOption("HIFAC_NONSTANDARD_AEFC_6E_LOW", 0, 0, 15);
        addIntegerRangeProxyOption("HIFAC_NONSTANDARD_AEFC_43", 0, 0, 15);
        addIntegerRangeProxyOption("HIFAC_NONSTANDARD_AEFC_44", 0, 0, 255);
        addIntegerRangeProxyOption("HIFAC_NONSTANDARD_AEFC_CB", 0, 0, 255);
        addIntegerRangeProxyOption("HIFAC_NONSTANDARD_VD_DSP_VERSION", 0, 0, 255);
        addIntegerRangeProxyOption("HIFAC_NONSTANDARD_VUDIO_HIDEV_MODE", 1, 0, 4);
        addIntegerRangeProxyOption("HIFAC_NONSTANDARD_AUDIO_NR_THR", 0, 0, 255);
        addIntegerRangeProxyOption("HIFAC_NONSTANDARD_VAudio_Sif_Threshold", 0, 0, 255);
        addIntegerRangeProxyOption("HIFAC_NONSTANDARD_AUDIO_DSP_VERSION", 0, 0, 255);
        addIntegerRangeProxyOption("HIFAC_DTV_NONSTANDA_NVOD_PROG_DIS", 0, 0, 1);
        addIntegerRangeProxyOption("HIFAC_DTV_NONSTANDA_PROG_NUM_DIS", 0, 0, 2);
        addIntegerRangeProxyOption("HIFAC_DTV_NONSTANDA_USER_ORDER_SET", 0, 0, 2);
        addIntegerRangeProxyOption("HIFAC_DTV_NONSTANDA_AREA_CONTROL_SET", 0, 0, 1);
        addIntegerRangeProxyOption("HIFAC_DTV_NONSTANDA_AREA_CONTROL_USER", 474, 80, 874);
        addIntegerRangeProxyOption("FACTORY_DTVTYPE", 0, 0, 1);
        addIntegerRangeProxyOption("HIFAC_HPF_FREQUENCY", 50, FacPropertyDemo.HIFAC_MIN_HPF_FREQUENCY, FacPropertyDemo.HIFAC_MAX_HPF_FREQUENCY);
        addIntegerRangeProxyOption("HIFAC_SOC_DRC", 0, FacPropertyDemo.HIFAC_MIN_SOC_DRC, FacPropertyDemo.HIFAC_MAX_SOC_DRC);
        addIntegerRangeProxyOption("HIFAC_AUD_AUTO_VOLUEM_CTRL_THRESHOLD", 0, FacPropertyDemo.HIFAC_MIN_VOLUME_CTRL_THRESHOLD, FacPropertyDemo.HIFAC_MAX_VOLUME_CTRL_THRESHOLD);
        addIntegerRangeProxyOption("HIFAC_MIC_YIZHIXING_DB", 0, 0, 1);
        addIntegerRangeProxyOption("HIFAC_MIC_RECORD_TIME", 0, 0, 2);
        addIntegerRangeProxyOption("FACTORY_Dolby_IQ", 1, 0, 1);
        addIntegerRangeProxyOption("HIFAC_TVS_LNB", 1, 0, 1);
        addIntegerRangeProxyOption("HIFAC_MODE_SELECTION", 0, 0, 1);
        addIntegerRangeProxyOption("HIFAC_MODE_SELECTION_VALUE", 1, 1, 4);
    }

    protected void addIntegerRangeProxyOption(String str, int i, int i2, int i3) {
        addOption(str, new IntegerRangeProxyOption(str, i, i2, i3));
    }
}
