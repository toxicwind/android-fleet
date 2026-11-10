package com.hisense.tvmanager.atsc;

import android.util.Log;
import com.mediatek.twoworlds.tv.common.MtkTvConfigTypeBase;
import java.util.HashMap;
import vendor.hisense.tv.hs_tv_service.V1_0.IHisTvService;

/* JADX INFO: loaded from: classes.dex */
public class Commons {
    private static int COMMON_SETTINGS_INDEX_TUNER_MODE = 0;
    public static int LANGUAGE_ALBANIAN = 53;
    public static int LANGUAGE_ALEMANNIC = 38;
    public static int LANGUAGE_AMHARIC = 59;
    public static int LANGUAGE_ARABIC = 40;
    public static int LANGUAGE_ARMENIAN = 56;
    public static int LANGUAGE_AZERBAIJANI = 62;
    public static int LANGUAGE_BASQUE = 33;
    public static int LANGUAGE_BENGAL = 66;
    public static int LANGUAGE_BOSNIAN = 64;
    public static int LANGUAGE_BRETON = 36;
    public static int LANGUAGE_BULGARIAN = 39;
    public static int LANGUAGE_BURMESE = 45;
    public static int LANGUAGE_BYELORUSSIAN = 25;
    public static int LANGUAGE_CATALAN = 31;
    public static int LANGUAGE_CHINESE = 19;
    public static int LANGUAGE_CORSICAN = 37;
    public static int LANGUAGE_CROATIAN = 27;
    public static int LANGUAGE_CZECH = 6;
    public static int LANGUAGE_DANISH = 15;
    public static int LANGUAGE_ENGLISH = 1;
    public static int LANGUAGE_ESTONIAN = 22;
    public static int LANGUAGE_FARSI = 50;
    public static int LANGUAGE_FINNISH = 10;
    public static int LANGUAGE_FRENCH = 2;
    public static int LANGUAGE_GAELIC = 32;
    public static int LANGUAGE_GALICIAN = 34;
    public static int LANGUAGE_GEORGIAN = 57;
    public static int LANGUAGE_GERMAN = 0;
    public static int LANGUAGE_GREEK = 7;
    public static int LANGUAGE_HEBREW = 49;
    public static int LANGUAGE_HINDI = 47;
    public static int LANGUAGE_HUNGARIAN = 9;
    public static int LANGUAGE_ICELANDIC = 63;
    public static int LANGUAGE_INDONESIAN = 51;
    public static int LANGUAGE_IRISH = 29;
    public static int LANGUAGE_ITALIAN = 3;
    public static int LANGUAGE_JAPANESE = 28;
    public static int LANGUAGE_KAZAKH = 61;
    public static int LANGUAGE_KIRGHIZ = 60;
    public static int LANGUAGE_KURDISH = 54;
    public static int LANGUAGE_LATVIAN = 20;
    public static int LANGUAGE_LITHUANIAN = 21;
    public static int LANGUAGE_LUXEMBOURGISH = 42;
    public static int LANGUAGE_MACEDONIAN = 52;
    public static int LANGUAGE_MALAYSIAN = 48;
    public static int LANGUAGE_MOLDAVIAN = 65;
    public static int LANGUAGE_MONGOLIA = 67;
    public static int LANGUAGE_MONTENEGRO = 68;
    public static int LANGUAGE_NETHERLANDS = 5;
    public static int LANGUAGE_NORWEGIAN = 18;
    public static int LANGUAGE_POLISH = 8;
    public static int LANGUAGE_PORTUGUESE = 16;
    public static int LANGUAGE_QAA = 70;
    public static int LANGUAGE_QAB = 71;
    public static int LANGUAGE_QAC = 72;
    public static int LANGUAGE_ROMANIAN = 24;
    public static int LANGUAGE_RUSSIAN = 17;
    public static int LANGUAGE_SCOTS = 35;
    public static int LANGUAGE_SERBIAN = 26;
    public static int LANGUAGE_SLOVAK = 12;
    public static int LANGUAGE_SLOVENIAN = 11;
    public static int LANGUAGE_SPANISH = 4;
    public static int LANGUAGE_SWEDISH = 14;
    public static int LANGUAGE_TAMIL = 69;
    public static int LANGUAGE_THAI = 44;
    public static int LANGUAGE_TURKISH = 13;
    public static int LANGUAGE_T_CHINESE = 55;
    public static int LANGUAGE_UKRAINIAN = 23;
    public static int LANGUAGE_URDU = 58;
    public static int LANGUAGE_UZBEK = 46;
    public static int LANGUAGE_VIETNAMESE = 43;
    public static int LANGUAGE_WALLOON = 41;
    public static int LANGUAGE_WELSH = 30;
    private static Commons mCommons;
    private static IHisTvService mService;

    static {
        new HashMap<Integer, String>() { // from class: com.hisense.tvmanager.atsc.Commons.1
            {
                put(Integer.valueOf(Commons.LANGUAGE_GERMAN), MtkTvConfigTypeBase.S639_CFG_LANG_GER);
                put(Integer.valueOf(Commons.LANGUAGE_ENGLISH), MtkTvConfigTypeBase.S639_CFG_LANG_ENG);
                put(Integer.valueOf(Commons.LANGUAGE_FRENCH), MtkTvConfigTypeBase.S639_CFG_LANG_FRE);
                put(Integer.valueOf(Commons.LANGUAGE_ITALIAN), MtkTvConfigTypeBase.S639_CFG_LANG_ITA);
                put(Integer.valueOf(Commons.LANGUAGE_SPANISH), MtkTvConfigTypeBase.S639_CFG_LANG_SPA);
                put(Integer.valueOf(Commons.LANGUAGE_NETHERLANDS), MtkTvConfigTypeBase.S639_CFG_LANG_DUT);
                put(Integer.valueOf(Commons.LANGUAGE_CZECH), MtkTvConfigTypeBase.S639_CFG_LANG_CZE);
                put(Integer.valueOf(Commons.LANGUAGE_GREEK), MtkTvConfigTypeBase.S639_CFG_LANG_GRE);
                put(Integer.valueOf(Commons.LANGUAGE_POLISH), MtkTvConfigTypeBase.S639_CFG_LANG_POL);
                put(Integer.valueOf(Commons.LANGUAGE_HUNGARIAN), MtkTvConfigTypeBase.S639_CFG_LANG_HUN);
                put(Integer.valueOf(Commons.LANGUAGE_FINNISH), MtkTvConfigTypeBase.S639_CFG_LANG_FIN);
                put(Integer.valueOf(Commons.LANGUAGE_SLOVENIAN), MtkTvConfigTypeBase.S639_CFG_LANG_SLV);
                put(Integer.valueOf(Commons.LANGUAGE_SLOVAK), MtkTvConfigTypeBase.S639_CFG_LANG_SLK);
                put(Integer.valueOf(Commons.LANGUAGE_TURKISH), MtkTvConfigTypeBase.S639_CFG_LANG_TUR);
                put(Integer.valueOf(Commons.LANGUAGE_SWEDISH), MtkTvConfigTypeBase.S639_CFG_LANG_SWE);
                put(Integer.valueOf(Commons.LANGUAGE_DANISH), MtkTvConfigTypeBase.S638_CFG_LANG_DAN);
                put(Integer.valueOf(Commons.LANGUAGE_PORTUGUESE), MtkTvConfigTypeBase.S639_CFG_LANG_POR);
                put(Integer.valueOf(Commons.LANGUAGE_RUSSIAN), MtkTvConfigTypeBase.S639_CFG_LANG_RUS);
                put(Integer.valueOf(Commons.LANGUAGE_NORWEGIAN), MtkTvConfigTypeBase.S639_CFG_LANG_NOR);
                put(Integer.valueOf(Commons.LANGUAGE_CHINESE), MtkTvConfigTypeBase.S639_CFG_LANG_CHI);
                put(Integer.valueOf(Commons.LANGUAGE_LATVIAN), MtkTvConfigTypeBase.S639_CFG_LANG_LAV);
                put(Integer.valueOf(Commons.LANGUAGE_LITHUANIAN), MtkTvConfigTypeBase.S639_CFG_LANG_LIT);
                put(Integer.valueOf(Commons.LANGUAGE_ESTONIAN), MtkTvConfigTypeBase.S639_CFG_LANG_EST);
                put(Integer.valueOf(Commons.LANGUAGE_UKRAINIAN), MtkTvConfigTypeBase.S639_CFG_LANG_UKR);
                put(Integer.valueOf(Commons.LANGUAGE_ROMANIAN), MtkTvConfigTypeBase.S639_CFG_LANG_RUM);
                put(Integer.valueOf(Commons.LANGUAGE_BYELORUSSIAN), "bel");
                put(Integer.valueOf(Commons.LANGUAGE_SERBIAN), MtkTvConfigTypeBase.S639_CFG_LANG_SRP);
                put(Integer.valueOf(Commons.LANGUAGE_CROATIAN), MtkTvConfigTypeBase.S639_CFG_LANG_HRV);
                put(Integer.valueOf(Commons.LANGUAGE_JAPANESE), MtkTvConfigTypeBase.S639_CFG_LANG_JPN);
                put(Integer.valueOf(Commons.LANGUAGE_IRISH), "iri");
                put(Integer.valueOf(Commons.LANGUAGE_WELSH), "wel");
                put(Integer.valueOf(Commons.LANGUAGE_CATALAN), MtkTvConfigTypeBase.S639_CFG_LANG_CAT);
                put(Integer.valueOf(Commons.LANGUAGE_GAELIC), "gla");
                put(Integer.valueOf(Commons.LANGUAGE_BASQUE), MtkTvConfigTypeBase.S639_CFG_LANG_BAQ);
                put(Integer.valueOf(Commons.LANGUAGE_GALICIAN), "glg");
                put(Integer.valueOf(Commons.LANGUAGE_SCOTS), "sco");
                put(Integer.valueOf(Commons.LANGUAGE_BRETON), "bre");
                put(Integer.valueOf(Commons.LANGUAGE_CORSICAN), "cos");
                put(Integer.valueOf(Commons.LANGUAGE_ALEMANNIC), "gsw");
                put(Integer.valueOf(Commons.LANGUAGE_BULGARIAN), MtkTvConfigTypeBase.S639_CFG_LANG_BUL);
                put(Integer.valueOf(Commons.LANGUAGE_ARABIC), MtkTvConfigTypeBase.S639_CFG_LANG_ARA);
                put(Integer.valueOf(Commons.LANGUAGE_WALLOON), "wln");
                put(Integer.valueOf(Commons.LANGUAGE_LUXEMBOURGISH), "itz");
                put(Integer.valueOf(Commons.LANGUAGE_VIETNAMESE), MtkTvConfigTypeBase.S639_CFG_LANG_VIE);
                put(Integer.valueOf(Commons.LANGUAGE_THAI), MtkTvConfigTypeBase.S639_CFG_LANG_THA);
                put(Integer.valueOf(Commons.LANGUAGE_BURMESE), "bur");
                put(Integer.valueOf(Commons.LANGUAGE_UZBEK), MtkTvConfigTypeBase.S639_CFG_LANG_UZB);
                put(Integer.valueOf(Commons.LANGUAGE_HINDI), MtkTvConfigTypeBase.S639_CFG_LANG_HIN);
                put(Integer.valueOf(Commons.LANGUAGE_MALAYSIAN), MtkTvConfigTypeBase.S639_CFG_LANG_MAL);
                put(Integer.valueOf(Commons.LANGUAGE_HEBREW), MtkTvConfigTypeBase.S639_CFG_LANG_HBR);
                put(Integer.valueOf(Commons.LANGUAGE_FARSI), "far");
                put(Integer.valueOf(Commons.LANGUAGE_INDONESIAN), MtkTvConfigTypeBase.S639_CFG_LANG_IND);
                put(Integer.valueOf(Commons.LANGUAGE_MACEDONIAN), "mac");
                put(Integer.valueOf(Commons.LANGUAGE_ALBANIAN), "alb");
                put(Integer.valueOf(Commons.LANGUAGE_KURDISH), "kur");
                put(Integer.valueOf(Commons.LANGUAGE_T_CHINESE), "tzh");
                put(Integer.valueOf(Commons.LANGUAGE_ARMENIAN), "arm");
                put(Integer.valueOf(Commons.LANGUAGE_GEORGIAN), "geo");
                put(Integer.valueOf(Commons.LANGUAGE_URDU), MtkTvConfigTypeBase.S639_CFG_LANG_URD);
                put(Integer.valueOf(Commons.LANGUAGE_AMHARIC), MtkTvConfigTypeBase.S639_CFG_LANG_AMH);
                put(Integer.valueOf(Commons.LANGUAGE_KIRGHIZ), "kir");
                put(Integer.valueOf(Commons.LANGUAGE_KAZAKH), MtkTvConfigTypeBase.S639_CFG_LANG_KAZ);
                put(Integer.valueOf(Commons.LANGUAGE_AZERBAIJANI), MtkTvConfigTypeBase.S639_CFG_LANG_AZE);
                put(Integer.valueOf(Commons.LANGUAGE_ICELANDIC), "icl");
                put(Integer.valueOf(Commons.LANGUAGE_BOSNIAN), "bsn");
                put(Integer.valueOf(Commons.LANGUAGE_MOLDAVIAN), "mld");
                put(Integer.valueOf(Commons.LANGUAGE_BENGAL), "bng");
                put(Integer.valueOf(Commons.LANGUAGE_MONGOLIA), "mog");
                put(Integer.valueOf(Commons.LANGUAGE_MONTENEGRO), "cnr");
                put(Integer.valueOf(Commons.LANGUAGE_TAMIL), MtkTvConfigTypeBase.S639_CFG_LANG_TAM);
                put(Integer.valueOf(Commons.LANGUAGE_QAA), MtkTvConfigTypeBase.S639_CFG_LANG_QAA);
                put(Integer.valueOf(Commons.LANGUAGE_QAB), MtkTvConfigTypeBase.S639_CFG_LANG_QAB);
                put(Integer.valueOf(Commons.LANGUAGE_QAC), MtkTvConfigTypeBase.S639_CFG_LANG_QAC);
            }
        };
        new HashMap<String, Integer>() { // from class: com.hisense.tvmanager.atsc.Commons.2
            {
                put(MtkTvConfigTypeBase.S639_CFG_LANG_GER, Integer.valueOf(Commons.LANGUAGE_GERMAN));
                put(MtkTvConfigTypeBase.S639_CFG_LANG_ENG, Integer.valueOf(Commons.LANGUAGE_ENGLISH));
                put(MtkTvConfigTypeBase.S639_CFG_LANG_FRE, Integer.valueOf(Commons.LANGUAGE_FRENCH));
                put(MtkTvConfigTypeBase.S639_CFG_LANG_ITA, Integer.valueOf(Commons.LANGUAGE_ITALIAN));
                put(MtkTvConfigTypeBase.S639_CFG_LANG_SPA, Integer.valueOf(Commons.LANGUAGE_SPANISH));
                put(MtkTvConfigTypeBase.S639_CFG_LANG_DUT, Integer.valueOf(Commons.LANGUAGE_NETHERLANDS));
                put(MtkTvConfigTypeBase.S639_CFG_LANG_CZE, Integer.valueOf(Commons.LANGUAGE_CZECH));
                put(MtkTvConfigTypeBase.S639_CFG_LANG_GRE, Integer.valueOf(Commons.LANGUAGE_GREEK));
                put(MtkTvConfigTypeBase.S639_CFG_LANG_POL, Integer.valueOf(Commons.LANGUAGE_POLISH));
                put(MtkTvConfigTypeBase.S639_CFG_LANG_HUN, Integer.valueOf(Commons.LANGUAGE_HUNGARIAN));
                put(MtkTvConfigTypeBase.S639_CFG_LANG_FIN, Integer.valueOf(Commons.LANGUAGE_FINNISH));
                put(MtkTvConfigTypeBase.S639_CFG_LANG_SLV, Integer.valueOf(Commons.LANGUAGE_SLOVENIAN));
                put(MtkTvConfigTypeBase.S639_CFG_LANG_SLK, Integer.valueOf(Commons.LANGUAGE_SLOVAK));
                put(MtkTvConfigTypeBase.S639_CFG_LANG_TUR, Integer.valueOf(Commons.LANGUAGE_TURKISH));
                put(MtkTvConfigTypeBase.S639_CFG_LANG_SWE, Integer.valueOf(Commons.LANGUAGE_SWEDISH));
                put(MtkTvConfigTypeBase.S638_CFG_LANG_DAN, Integer.valueOf(Commons.LANGUAGE_DANISH));
                put(MtkTvConfigTypeBase.S639_CFG_LANG_POR, Integer.valueOf(Commons.LANGUAGE_PORTUGUESE));
                put(MtkTvConfigTypeBase.S639_CFG_LANG_RUS, Integer.valueOf(Commons.LANGUAGE_RUSSIAN));
                put(MtkTvConfigTypeBase.S639_CFG_LANG_NOR, Integer.valueOf(Commons.LANGUAGE_NORWEGIAN));
                put(MtkTvConfigTypeBase.S639_CFG_LANG_CHI, Integer.valueOf(Commons.LANGUAGE_CHINESE));
                put(MtkTvConfigTypeBase.S639_CFG_LANG_ZHO, Integer.valueOf(Commons.LANGUAGE_CHINESE));
                put(MtkTvConfigTypeBase.S639_CFG_LANG_LAV, Integer.valueOf(Commons.LANGUAGE_LATVIAN));
                put(MtkTvConfigTypeBase.S639_CFG_LANG_LIT, Integer.valueOf(Commons.LANGUAGE_LITHUANIAN));
                put(MtkTvConfigTypeBase.S639_CFG_LANG_EST, Integer.valueOf(Commons.LANGUAGE_ESTONIAN));
                put(MtkTvConfigTypeBase.S639_CFG_LANG_UKR, Integer.valueOf(Commons.LANGUAGE_UKRAINIAN));
                put(MtkTvConfigTypeBase.S639_CFG_LANG_RUM, Integer.valueOf(Commons.LANGUAGE_ROMANIAN));
                put("bel", Integer.valueOf(Commons.LANGUAGE_BYELORUSSIAN));
                put(MtkTvConfigTypeBase.S639_CFG_LANG_SRP, Integer.valueOf(Commons.LANGUAGE_SERBIAN));
                put(MtkTvConfigTypeBase.S639_CFG_LANG_HRV, Integer.valueOf(Commons.LANGUAGE_CROATIAN));
                put(MtkTvConfigTypeBase.S639_CFG_LANG_JPN, Integer.valueOf(Commons.LANGUAGE_JAPANESE));
                put("iri", Integer.valueOf(Commons.LANGUAGE_IRISH));
                put("wel", Integer.valueOf(Commons.LANGUAGE_WELSH));
                put(MtkTvConfigTypeBase.S639_CFG_LANG_CAT, Integer.valueOf(Commons.LANGUAGE_CATALAN));
                put("gla", Integer.valueOf(Commons.LANGUAGE_GAELIC));
                put(MtkTvConfigTypeBase.S639_CFG_LANG_BAQ, Integer.valueOf(Commons.LANGUAGE_BASQUE));
                put("glg", Integer.valueOf(Commons.LANGUAGE_GALICIAN));
                put("sco", Integer.valueOf(Commons.LANGUAGE_SCOTS));
                put("bre", Integer.valueOf(Commons.LANGUAGE_BRETON));
                put("cos", Integer.valueOf(Commons.LANGUAGE_CORSICAN));
                put("gsw", Integer.valueOf(Commons.LANGUAGE_ALEMANNIC));
                put(MtkTvConfigTypeBase.S639_CFG_LANG_BUL, Integer.valueOf(Commons.LANGUAGE_BULGARIAN));
                put(MtkTvConfigTypeBase.S639_CFG_LANG_ARA, Integer.valueOf(Commons.LANGUAGE_ARABIC));
                put("wln", Integer.valueOf(Commons.LANGUAGE_WALLOON));
                put("itz", Integer.valueOf(Commons.LANGUAGE_LUXEMBOURGISH));
                put(MtkTvConfigTypeBase.S639_CFG_LANG_VIE, Integer.valueOf(Commons.LANGUAGE_VIETNAMESE));
                put(MtkTvConfigTypeBase.S639_CFG_LANG_THA, Integer.valueOf(Commons.LANGUAGE_THAI));
                put("bur", Integer.valueOf(Commons.LANGUAGE_BURMESE));
                put(MtkTvConfigTypeBase.S639_CFG_LANG_MMR, Integer.valueOf(Commons.LANGUAGE_BURMESE));
                put(MtkTvConfigTypeBase.S639_CFG_LANG_UZB, Integer.valueOf(Commons.LANGUAGE_UZBEK));
                put(MtkTvConfigTypeBase.S639_CFG_LANG_HIN, Integer.valueOf(Commons.LANGUAGE_HINDI));
                put(MtkTvConfigTypeBase.S639_CFG_LANG_MAL, Integer.valueOf(Commons.LANGUAGE_MALAYSIAN));
                put("msa", Integer.valueOf(Commons.LANGUAGE_MALAYSIAN));
                put(MtkTvConfigTypeBase.S639_CFG_LANG_HBR, Integer.valueOf(Commons.LANGUAGE_HEBREW));
                put("far", Integer.valueOf(Commons.LANGUAGE_FARSI));
                put(MtkTvConfigTypeBase.S639_CFG_LANG_IND, Integer.valueOf(Commons.LANGUAGE_INDONESIAN));
                put("mac", Integer.valueOf(Commons.LANGUAGE_MACEDONIAN));
                put("alb", Integer.valueOf(Commons.LANGUAGE_ALBANIAN));
                put("kur", Integer.valueOf(Commons.LANGUAGE_KURDISH));
                put("tzh", Integer.valueOf(Commons.LANGUAGE_T_CHINESE));
                put("arm", Integer.valueOf(Commons.LANGUAGE_ARMENIAN));
                put("geo", Integer.valueOf(Commons.LANGUAGE_GEORGIAN));
                put(MtkTvConfigTypeBase.S639_CFG_LANG_URD, Integer.valueOf(Commons.LANGUAGE_URDU));
                put(MtkTvConfigTypeBase.S639_CFG_LANG_AMH, Integer.valueOf(Commons.LANGUAGE_AMHARIC));
                put("kir", Integer.valueOf(Commons.LANGUAGE_KIRGHIZ));
                put(MtkTvConfigTypeBase.S639_CFG_LANG_KAZ, Integer.valueOf(Commons.LANGUAGE_KAZAKH));
                put(MtkTvConfigTypeBase.S639_CFG_LANG_AZE, Integer.valueOf(Commons.LANGUAGE_AZERBAIJANI));
                put("icl", Integer.valueOf(Commons.LANGUAGE_ICELANDIC));
                put("bsn", Integer.valueOf(Commons.LANGUAGE_BOSNIAN));
                put("mld", Integer.valueOf(Commons.LANGUAGE_MOLDAVIAN));
                put("bng", Integer.valueOf(Commons.LANGUAGE_BENGAL));
                put("mog", Integer.valueOf(Commons.LANGUAGE_MONGOLIA));
                put("cnr", Integer.valueOf(Commons.LANGUAGE_MONTENEGRO));
                put(MtkTvConfigTypeBase.S639_CFG_LANG_TAM, Integer.valueOf(Commons.LANGUAGE_TAMIL));
                put(MtkTvConfigTypeBase.S639_CFG_LANG_QAA, Integer.valueOf(Commons.LANGUAGE_QAA));
                put(MtkTvConfigTypeBase.S639_CFG_LANG_QAB, Integer.valueOf(Commons.LANGUAGE_QAB));
                put(MtkTvConfigTypeBase.S639_CFG_LANG_QAC, Integer.valueOf(Commons.LANGUAGE_QAC));
            }
        };
        COMMON_SETTINGS_INDEX_TUNER_MODE = 0;
        mService = null;
    }

    public static boolean getHidlService() {
        try {
            IHisTvService service = IHisTvService.getService();
            mService = service;
            return service != null;
        } catch (Exception e) {
            Log.e("Commons", "get HisTvService HIDL Service exception" + e);
            return false;
        }
    }

    public static Commons getInstance() {
        if (mCommons == null) {
            synchronized (Commons.class) {
                if (mCommons == null) {
                    mCommons = new Commons();
                }
            }
        }
        return mCommons;
    }

    public void setTunerMode(int i) {
        if (getHidlService()) {
            try {
                mService.saveSetting(COMMON_SETTINGS_INDEX_TUNER_MODE, String.valueOf(i));
            } catch (Exception e) {
                Log.e("Commons", " error: " + e);
            }
        }
    }

    public int getTunerMode() {
        if (!getHidlService()) {
            return -1;
        }
        try {
            return Integer.parseInt(mService.loadSetting(COMMON_SETTINGS_INDEX_TUNER_MODE));
        } catch (Exception e) {
            Log.e("Commons", " error: " + e);
            return -1;
        }
    }

    public void changeToTvSource() {
        if (getHidlService()) {
            try {
                mService.changeToTvSource();
                return;
            } catch (Exception e) {
                Log.e("Commons", " error: " + e);
                return;
            }
        }
        Log.d("Commons", "getHidlService is false, call changeToTvSource failed");
    }

    public void exitTvSource() {
        if (getHidlService()) {
            try {
                mService.changeToOtherSource();
                return;
            } catch (Exception e) {
                Log.e("Commons", " error: " + e);
                return;
            }
        }
        Log.d("Commons", "getHidlService is false, call exitTvSource failed");
    }

    public void startTV() {
        if (getHidlService()) {
            try {
                mService.startService();
                return;
            } catch (Exception e) {
                Log.e("Commons", " error: " + e);
                return;
            }
        }
        Log.d("Commons", "getHidlService is false, call startTv failed");
    }

    public void stopTV() {
        if (getHidlService()) {
            try {
                mService.stopService();
                return;
            } catch (Exception e) {
                Log.e("Commons", " error: " + e);
                return;
            }
        }
        Log.d("Commons", "getHidlService is false, call stopTv failed");
    }
}
