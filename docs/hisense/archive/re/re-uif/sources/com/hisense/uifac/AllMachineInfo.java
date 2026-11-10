package com.hisense.uifac;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hisense.tvcm.FacPropertyDemo;
import com.hisense.uifac.menu.util.BarUtil;
import com.hisense.uifac.menu.util.MenuConfigManager;
import com.mediatek.twoworlds.tv.common.MtkTvSvctxNotifyCode;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class AllMachineInfo extends Activity {
    private MenuConfigManager mMenuConfigManager;
    private SharedPreferences mSharedPreferences = null;
    private List<TextView> mTextViewNameList = new ArrayList();
    private List<TextView> mTextViewValueList = new ArrayList();

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.all_machine_info_layout);
        getWindow().addFlags(128);
        this.mSharedPreferences = getSharedPreferences("FacShareP", 7);
        Bundle extras = getIntent().getExtras();
        this.mMenuConfigManager = MenuConfigManager.getInstance(this);
        init(extras);
    }

    private void init(Bundle bundle) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        String str;
        String string;
        int i6;
        TextView textView = (TextView) findViewById(R.id.all_machine_info_machineName);
        TextView textView2 = (TextView) findViewById(R.id.all_machine_info_strVersionDate);
        TextView textView3 = (TextView) findViewById(R.id.all_machine_info_machineNo);
        TextView textView4 = (TextView) findViewById(R.id.all_machine_info_orimachineNo);
        TextView textView5 = (TextView) findViewById(R.id.all_machine_info_frcversion);
        TextView textView6 = (TextView) findViewById(R.id.all_machine_info_strIpAddrString);
        TextView textView7 = (TextView) findViewById(R.id.all_machine_info_strMacAddress);
        TextView textView8 = (TextView) findViewById(R.id.all_machine_info_blueMacAddress);
        TextView textView9 = (TextView) findViewById(R.id.all_machine_info_ciCardStatus);
        TextView textView10 = (TextView) findViewById(R.id.all_machine_info_caCardStatus);
        TextView textView11 = (TextView) findViewById(R.id.all_machine_info_usbPortStatus);
        TextView textView12 = (TextView) findViewById(R.id.all_machine_info_sdCardStatus);
        TextView textView13 = (TextView) findViewById(R.id.all_machine_info_widevineKey);
        TextView textView14 = (TextView) findViewById(R.id.all_machine_info_chinadrmKey);
        TextView textView15 = (TextView) findViewById(R.id.all_machine_info_strCountry);
        TextView textView16 = (TextView) findViewById(R.id.all_machine_info_strLanguage);
        TextView textView17 = (TextView) findViewById(R.id.all_machine_info_strLogo);
        TextView textView18 = (TextView) findViewById(R.id.all_machine_info_hdcp14Key);
        TextView textView19 = (TextView) findViewById(R.id.all_machine_info_hdcp22Key);
        TextView textView20 = (TextView) findViewById(R.id.all_machine_info_playreadyKey);
        TextView textView21 = (TextView) findViewById(R.id.all_machine_info_mgk);
        TextView textView22 = (TextView) findViewById(R.id.all_machine_info_playready25Key);
        TextView textView23 = (TextView) findViewById(R.id.all_machine_info_attest);
        TextView textView24 = (TextView) findViewById(R.id.all_machine_info_tconversion);
        TextView textView25 = (TextView) findViewById(R.id.all_machine_info_bluversion);
        TextView textView26 = (TextView) findViewById(R.id.all_machine_info_hddStatus);
        TextView textView27 = (TextView) findViewById(R.id.all_machine_info_marlin);
        TextView textView28 = (TextView) findViewById(R.id.all_machine_info_dtcpip);
        TextView textView29 = (TextView) findViewById(R.id.all_machine_info_pvr);
        TextView textView30 = (TextView) findViewById(R.id.all_machine_info_sn);
        TextView textView31 = (TextView) findViewById(R.id.all_machine_info_qda);
        this.mTextViewNameList.add(textView);
        this.mTextViewNameList.add(textView2);
        this.mTextViewNameList.add(textView3);
        this.mTextViewNameList.add(textView4);
        this.mTextViewNameList.add(textView5);
        this.mTextViewNameList.add(textView6);
        this.mTextViewNameList.add(textView7);
        this.mTextViewNameList.add(textView8);
        this.mTextViewNameList.add(textView9);
        this.mTextViewNameList.add(textView10);
        this.mTextViewNameList.add(textView11);
        this.mTextViewNameList.add(textView12);
        this.mTextViewNameList.add(textView13);
        this.mTextViewNameList.add(textView14);
        this.mTextViewNameList.add(textView15);
        this.mTextViewNameList.add(textView16);
        this.mTextViewNameList.add(textView17);
        this.mTextViewNameList.add(textView18);
        this.mTextViewNameList.add(textView19);
        this.mTextViewNameList.add(textView20);
        this.mTextViewNameList.add(textView21);
        this.mTextViewNameList.add(textView22);
        this.mTextViewNameList.add(textView23);
        this.mTextViewNameList.add(textView24);
        this.mTextViewNameList.add(textView25);
        this.mTextViewNameList.add(textView26);
        this.mTextViewNameList.add(textView27);
        this.mTextViewNameList.add(textView28);
        this.mTextViewNameList.add(textView29);
        this.mTextViewNameList.add(textView30);
        this.mTextViewNameList.add(textView31);
        TextView textView32 = (TextView) findViewById(R.id.all_machine_info_machineName_cnt);
        TextView textView33 = (TextView) findViewById(R.id.all_machine_info_strVersionDate_cnt);
        TextView textView34 = (TextView) findViewById(R.id.all_machine_info_machineNo_cnt);
        TextView textView35 = (TextView) findViewById(R.id.all_machine_info_orimachineNo_cnt);
        TextView textView36 = (TextView) findViewById(R.id.all_machine_info_frcversion_cnt);
        TextView textView37 = (TextView) findViewById(R.id.all_machine_info_strIpAddrString_cnt);
        TextView textView38 = (TextView) findViewById(R.id.all_machine_info_strMacAddress_cnt);
        TextView textView39 = (TextView) findViewById(R.id.all_machine_info_blueMacAddress_cnt);
        TextView textView40 = (TextView) findViewById(R.id.all_machine_info_ciCardStatus_cnt);
        TextView textView41 = (TextView) findViewById(R.id.all_machine_info_caCardStatus_cnt);
        TextView textView42 = (TextView) findViewById(R.id.all_machine_info_usbPortStatus_cnt);
        TextView textView43 = (TextView) findViewById(R.id.all_machine_info_sdCardStatus_cnt);
        TextView textView44 = (TextView) findViewById(R.id.all_machine_info_widevineKey_cnt);
        TextView textView45 = (TextView) findViewById(R.id.all_machine_info_chinadrmKey_cnt);
        TextView textView46 = (TextView) findViewById(R.id.all_machine_info_strCountry_cnt);
        TextView textView47 = (TextView) findViewById(R.id.all_machine_info_strLanguage_cnt);
        TextView textView48 = (TextView) findViewById(R.id.all_machine_info_strLogo_cnt);
        TextView textView49 = (TextView) findViewById(R.id.all_machine_info_hdcp14Key_cnt);
        TextView textView50 = (TextView) findViewById(R.id.all_machine_info_hdcp22Key_cnt);
        TextView textView51 = (TextView) findViewById(R.id.all_machine_info_playreadyKey_cnt);
        TextView textView52 = (TextView) findViewById(R.id.all_machine_info_ciplusKey_cnt);
        TextView textView53 = (TextView) findViewById(R.id.all_machine_info_mgk_cnt);
        TextView textView54 = (TextView) findViewById(R.id.all_machine_info_playready25Key_cnt);
        TextView textView55 = (TextView) findViewById(R.id.all_machine_info_attest_cnt);
        TextView textView56 = (TextView) findViewById(R.id.all_machine_info_tconversion_cnt);
        TextView textView57 = (TextView) findViewById(R.id.all_machine_info_bluversion_cnt);
        TextView textView58 = (TextView) findViewById(R.id.all_machine_info_hddStatus_cnt);
        TextView textView59 = (TextView) findViewById(R.id.all_machine_info_dtcpip_cnt);
        TextView textView60 = (TextView) findViewById(R.id.all_machine_info_marlin_cnt);
        TextView textView61 = (TextView) findViewById(R.id.all_machine_info_pvr_cnt);
        TextView textView62 = (TextView) findViewById(R.id.all_machine_info_sn_cnt);
        TextView textView63 = (TextView) findViewById(R.id.all_machine_info_qda_cnt);
        this.mTextViewValueList.add(textView32);
        this.mTextViewValueList.add(textView33);
        this.mTextViewValueList.add(textView34);
        this.mTextViewValueList.add(textView35);
        this.mTextViewValueList.add(textView36);
        this.mTextViewValueList.add(textView37);
        this.mTextViewValueList.add(textView38);
        this.mTextViewValueList.add(textView39);
        this.mTextViewValueList.add(textView40);
        this.mTextViewValueList.add(textView41);
        this.mTextViewValueList.add(textView42);
        this.mTextViewValueList.add(textView43);
        this.mTextViewValueList.add(textView44);
        this.mTextViewValueList.add(textView45);
        this.mTextViewValueList.add(textView46);
        this.mTextViewValueList.add(textView47);
        this.mTextViewValueList.add(textView48);
        this.mTextViewValueList.add(textView49);
        this.mTextViewValueList.add(textView50);
        this.mTextViewValueList.add(textView51);
        this.mTextViewValueList.add(textView53);
        this.mTextViewValueList.add(textView54);
        this.mTextViewValueList.add(textView55);
        this.mTextViewValueList.add(textView56);
        this.mTextViewValueList.add(textView57);
        this.mTextViewValueList.add(textView58);
        this.mTextViewValueList.add(textView59);
        this.mTextViewValueList.add(textView60);
        this.mTextViewValueList.add(textView61);
        this.mTextViewValueList.add(textView62);
        this.mTextViewValueList.add(textView63);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.machine_info_machineName);
        LinearLayout linearLayout2 = (LinearLayout) findViewById(R.id.machine_info_strVersionDate);
        LinearLayout linearLayout3 = (LinearLayout) findViewById(R.id.machine_info_machineNo);
        LinearLayout linearLayout4 = (LinearLayout) findViewById(R.id.machine_info_orimachineNo);
        LinearLayout linearLayout5 = (LinearLayout) findViewById(R.id.machine_info_frcversion);
        LinearLayout linearLayout6 = (LinearLayout) findViewById(R.id.machine_info_ipAddr);
        LinearLayout linearLayout7 = (LinearLayout) findViewById(R.id.machine_info_macAddr);
        LinearLayout linearLayout8 = (LinearLayout) findViewById(R.id.machine_info_bluemacAddr);
        LinearLayout linearLayout9 = (LinearLayout) findViewById(R.id.machine_info_ciCardStatus);
        LinearLayout linearLayout10 = (LinearLayout) findViewById(R.id.machine_info_caCardStatus);
        LinearLayout linearLayout11 = (LinearLayout) findViewById(R.id.machine_info_usbPortStatus);
        LinearLayout linearLayout12 = (LinearLayout) findViewById(R.id.machine_info_sdCardStatus);
        LinearLayout linearLayout13 = (LinearLayout) findViewById(R.id.machine_info_widevineKey);
        LinearLayout linearLayout14 = (LinearLayout) findViewById(R.id.machine_info_chinadrmKey);
        LinearLayout linearLayout15 = (LinearLayout) findViewById(R.id.machine_info_country);
        LinearLayout linearLayout16 = (LinearLayout) findViewById(R.id.machine_info_language);
        LinearLayout linearLayout17 = (LinearLayout) findViewById(R.id.machine_info_logo);
        LinearLayout linearLayout18 = (LinearLayout) findViewById(R.id.machine_info_hdcp14Key);
        LinearLayout linearLayout19 = (LinearLayout) findViewById(R.id.machine_info_hdcp22Key);
        LinearLayout linearLayout20 = (LinearLayout) findViewById(R.id.machine_info_playreadyKey);
        LinearLayout linearLayout21 = (LinearLayout) findViewById(R.id.machine_info_ciplusKey);
        LinearLayout linearLayout22 = (LinearLayout) findViewById(R.id.machine_info_mgk);
        LinearLayout linearLayout23 = (LinearLayout) findViewById(R.id.machine_info_attest);
        LinearLayout linearLayout24 = (LinearLayout) findViewById(R.id.machine_info_playready25Key);
        LinearLayout linearLayout25 = (LinearLayout) findViewById(R.id.machine_info_tconversion);
        LinearLayout linearLayout26 = (LinearLayout) findViewById(R.id.machine_info_bluversion);
        LinearLayout linearLayout27 = (LinearLayout) findViewById(R.id.machine_info_hddStatus);
        LinearLayout linearLayout28 = (LinearLayout) findViewById(R.id.machine_info_marlin);
        LinearLayout linearLayout29 = (LinearLayout) findViewById(R.id.machine_info_dtcpip);
        LinearLayout linearLayout30 = (LinearLayout) findViewById(R.id.machine_info_pvr);
        LinearLayout linearLayout31 = (LinearLayout) findViewById(R.id.machine_info_sn);
        LinearLayout linearLayout32 = (LinearLayout) findViewById(R.id.machine_info_qda);
        ImageView imageView = (ImageView) findViewById(R.id.image_mac_barcode);
        if ("info".equalsIgnoreCase(bundle.getString("type"))) {
            String string2 = bundle.getString("machineName");
            String string3 = bundle.getString("strVersionDate");
            String string4 = bundle.getString("machineNo");
            String string5 = bundle.getString("oriMachineNo");
            String string6 = this.mMenuConfigManager.isFrcSupport() ? bundle.getString("frcVersion") : " ";
            if (this.mMenuConfigManager.isTconSupport()) {
                textView56.setText(bundle.getString("tconVersion"));
                i4 = 0;
                linearLayout25.setVisibility(0);
                i5 = 8;
            } else {
                i4 = 0;
                i5 = 8;
                linearLayout25.setVisibility(8);
            }
            if (this.mMenuConfigManager.isBluSupport()) {
                textView57.setText(bundle.getString("bluVersion"));
                linearLayout26.setVisibility(i4);
            } else {
                linearLayout26.setVisibility(i5);
            }
            textView62.setText(bundle.getString("sn"));
            String string7 = bundle.getString("strIpAddrString");
            String string8 = bundle.getString("strMacAddress");
            String string9 = bundle.getString("strCountry");
            String string10 = bundle.getString("strLanguage");
            String string11 = bundle.getString("strLogo");
            String string12 = FacPropertyDemo.HIFAC_BLUETOOTH_SUPPORT ? bundle.getString("strBlueMacAddr") : "";
            String string13 = bundle.getString("ciCardStatus");
            String string14 = bundle.getString("caCardStatus");
            String string15 = bundle.getString("usbPortStatus");
            String string16 = bundle.getString("sdCardStatus");
            String string17 = FacPropertyDemo.HIFAC_WIDE_VINE_KEY_SUPPORT ? bundle.getString("wideVineKey") : " ";
            String string18 = FacPropertyDemo.HIFAC_HDCP_1_4_KEY_SUPPORT ? bundle.getString("hdcp14key") : " ";
            String string19 = FacPropertyDemo.HIFAC_HDCP_2_X_KEY_SUPPORT ? bundle.getString("hdcp22key") : " ";
            if (FacPropertyDemo.HIFAC_PLAY_READY_KEY_SUPPORT) {
                String string20 = bundle.getString("playreadykey");
                string = bundle.getString("playready25key");
                str = string20;
            } else {
                str = " ";
                string = str;
            }
            String string21 = FacPropertyDemo.HIFAC_CI_PLUS_KEY_SUPPORT ? bundle.getString("cipluskey") : " ";
            String string22 = FacPropertyDemo.HIFAC_CHINA_DRM_KEY_SUPPORT ? bundle.getString("chinaDrmKey") : " ";
            if (FacPropertyDemo.HIFAC_MARKET_JP) {
                textView58.setText(bundle.getString("hddStatus"));
                linearLayout27.setVisibility(0);
            } else {
                linearLayout27.setVisibility(8);
            }
            String string23 = FacPropertyDemo.HIFAC_ATTEST_KEY_SUPPORT ? bundle.getString("attest") : " ";
            textView.setText(R.string.str_all_machine_info_machineName);
            textView2.setText(R.string.str_all_machine_info_strVersionDate);
            textView3.setText(R.string.str_all_machine_info_machineNo);
            textView4.setText(R.string.str_factorymenu_factorynum);
            if (this.mMenuConfigManager.isFrcSupport()) {
                textView5.setText(R.string.str_all_machine_info_frcVersion);
            }
            textView6.setText(R.string.str_all_machine_info_strIpAddrString);
            textView7.setText(R.string.str_all_machine_info_strMacAddress);
            if (FacPropertyDemo.HIFAC_BLUETOOTH_SUPPORT) {
                textView8.setText(R.string.str_all_machine_info_strBluetoothMacAddress);
            }
            textView9.setText(R.string.str_all_machine_info_ciCardStatus);
            textView10.setText(R.string.str_all_machine_info_caCardStatus);
            textView11.setText(R.string.str_all_machine_info_usbPortStatus);
            textView12.setText(R.string.str_all_machine_info_sdCardStatus);
            if (FacPropertyDemo.HIFAC_WIDE_VINE_KEY_SUPPORT) {
                textView13.setText(R.string.str_all_machine_info_wideVineKey);
            }
            if (FacPropertyDemo.HIFAC_CHINA_DRM_KEY_SUPPORT) {
                textView14.setText(R.string.str_all_machine_info_chinaDrmKey);
            }
            textView15.setText(R.string.str_all_machine_info_country);
            textView16.setText(R.string.str_all_machine_info_language);
            textView17.setText(R.string.str_all_machine_info_logo);
            textView18.setText(R.string.str_all_machine_info_hdcp14Key);
            textView19.setText(R.string.str_all_machine_info_HDCP22Key);
            textView20.setText(R.string.str_all_machine_info_playreadyKey);
            textView21.setText(R.string.str_all_machine_info_mgk);
            textView22.setText(R.string.str_all_machine_info_playready25Key);
            textView23.setText(R.string.str_all_machine_info_attest);
            textView31.setText(R.string.str_all_machine_info_qdaKey);
            textView30.setText(R.string.str_all_machine_info_sn);
            textView25.setText("Blu version");
            textView24.setText("Tcon version");
            textView26.setText("HDD");
            textView27.setText("Marlin");
            textView28.setText("DtcpIp");
            textView29.setText("PVR");
            linearLayout.setVisibility(0);
            linearLayout2.setVisibility(0);
            linearLayout3.setVisibility(0);
            linearLayout4.setVisibility(8);
            if (this.mMenuConfigManager.isFrcSupport()) {
                linearLayout5.setVisibility(0);
            } else {
                linearLayout5.setVisibility(8);
            }
            linearLayout6.setVisibility(0);
            linearLayout7.setVisibility(0);
            if (FacPropertyDemo.HIFAC_BLUETOOTH_SUPPORT) {
                linearLayout8.setVisibility(0);
            } else {
                linearLayout8.setVisibility(8);
            }
            if (FacPropertyDemo.HIFAC_CI_CARD_SUPPORT) {
                linearLayout9.setVisibility(0);
            } else {
                linearLayout9.setVisibility(8);
            }
            if (FacPropertyDemo.HIFAC_CA_CARD_SUPPORT) {
                linearLayout10.setVisibility(0);
            } else {
                linearLayout10.setVisibility(8);
            }
            linearLayout11.setVisibility(0);
            if (FacPropertyDemo.HIFAC_TF_CARD_SUPPORT) {
                linearLayout12.setVisibility(0);
            } else {
                linearLayout12.setVisibility(8);
            }
            if (FacPropertyDemo.HIFAC_WIDE_VINE_KEY_SUPPORT) {
                linearLayout13.setVisibility(0);
            } else {
                linearLayout13.setVisibility(8);
            }
            if (FacPropertyDemo.HIFAC_MGK_KEY_SUPPORT) {
                linearLayout22.setVisibility(0);
            } else {
                linearLayout22.setVisibility(8);
            }
            if (FacPropertyDemo.HIFAC_ATTEST_KEY_SUPPORT) {
                linearLayout23.setVisibility(0);
            } else {
                linearLayout23.setVisibility(8);
            }
            if (this.mMenuConfigManager.isSupportQDAKey()) {
                linearLayout32.setVisibility(0);
            } else {
                linearLayout32.setVisibility(8);
            }
            linearLayout15.setVisibility(0);
            linearLayout16.setVisibility(0);
            linearLayout17.setVisibility(0);
            if (FacPropertyDemo.HIFAC_HDCP_1_4_KEY_SUPPORT) {
                linearLayout18.setVisibility(0);
            }
            if (FacPropertyDemo.HIFAC_HDCP_2_X_KEY_SUPPORT) {
                linearLayout19.setVisibility(0);
            }
            if (FacPropertyDemo.HIFAC_PLAY_READY_KEY_SUPPORT) {
                linearLayout20.setVisibility(0);
                if (FacPropertyDemo.HIFAC_PLAY_READY_25KEY_SUPPORT) {
                    linearLayout24.setVisibility(0);
                    i6 = 8;
                } else {
                    i6 = 8;
                    linearLayout24.setVisibility(8);
                }
            } else {
                i6 = 8;
            }
            if (FacPropertyDemo.HIFAC_CI_PLUS_KEY_SUPPORT) {
                linearLayout21.setVisibility(0);
            } else {
                linearLayout21.setVisibility(i6);
            }
            linearLayout31.setVisibility(i6);
            textView32.setText(string2);
            textView33.setText(string3);
            textView34.setText(string4);
            textView35.setText(string5);
            if (this.mMenuConfigManager.isFrcSupport()) {
                textView36.setText(string6);
            }
            textView37.setText(string7);
            textView38.setText(string8);
            if (FacPropertyDemo.HIFAC_BLUETOOTH_SUPPORT) {
                textView39.setText(string12);
            }
            textView40.setText(string13);
            textView41.setText(string14);
            textView42.setText(string15);
            textView43.setText(string16);
            if (FacPropertyDemo.HIFAC_WIDE_VINE_KEY_SUPPORT) {
                textView44.setText(string17);
            }
            if (FacPropertyDemo.HIFAC_CHINA_DRM_KEY_SUPPORT) {
                textView45.setText(string22);
            }
            textView46.setText(string9);
            textView47.setText(string10);
            textView48.setText(string11);
            if (FacPropertyDemo.HIFAC_HDCP_1_4_KEY_SUPPORT) {
                textView49.setText(string18);
            }
            if (FacPropertyDemo.HIFAC_HDCP_2_X_KEY_SUPPORT) {
                textView50.setText(string19);
            }
            if (FacPropertyDemo.HIFAC_PLAY_READY_KEY_SUPPORT) {
                textView51.setText(str);
                textView54.setText(string);
            }
            if (FacPropertyDemo.HIFAC_CI_PLUS_KEY_SUPPORT) {
                textView52.setText(string21);
            }
            if (FacPropertyDemo.HIFAC_MGK_KEY_SUPPORT) {
                textView53.setText(bundle.getString("mgk"));
            }
            if (FacPropertyDemo.HIFAC_ATTEST_KEY_SUPPORT) {
                textView55.setText(string23);
            }
            if (this.mMenuConfigManager.isSupportQDAKey()) {
                textView63.setText(bundle.getString("QDAKey"));
            }
            if (FacPropertyDemo.HIFAC_MARKET_JP) {
                textView60.setText(bundle.getString("marlin"));
                textView59.setText(bundle.getString("dtcpip"));
                textView61.setText(bundle.getString("pvr"));
                linearLayout30.setVisibility(0);
                linearLayout28.setVisibility(0);
                linearLayout29.setVisibility(0);
                return;
            }
            linearLayout30.setVisibility(8);
            linearLayout28.setVisibility(8);
            linearLayout29.setVisibility(8);
            return;
        }
        if ("test".equalsIgnoreCase(bundle.getString("type"))) {
            linearLayout.setVisibility(0);
            if (FacPropertyDemo.HIFAC_BLUETOOTH_SUPPORT) {
                linearLayout2.setVisibility(0);
                i3 = 8;
            } else {
                i3 = 8;
                linearLayout2.setVisibility(8);
            }
            linearLayout3.setVisibility(0);
            linearLayout4.setVisibility(0);
            if (FacPropertyDemo.HIFAC_MIC_DETECT_SUPPORT) {
                linearLayout5.setVisibility(0);
            } else {
                linearLayout5.setVisibility(i3);
            }
            linearLayout6.setVisibility(0);
            linearLayout7.setVisibility(0);
            linearLayout8.setVisibility(0);
            linearLayout9.setVisibility(0);
            linearLayout10.setVisibility(0);
            linearLayout11.setVisibility(0);
            linearLayout12.setVisibility(0);
            linearLayout13.setVisibility(0);
            linearLayout14.setVisibility(0);
            linearLayout31.setVisibility(i3);
            textView.setText(R.string.str_test_info_wifi);
            textView32.setText(this.mSharedPreferences.getBoolean(getResources().getString(R.string.str_test_info_wifi), false) ? "OK" : "NG");
            textView2.setText(R.string.str_test_info_bt);
            textView33.setText(this.mSharedPreferences.getBoolean(getResources().getString(R.string.str_test_info_bt), false) ? "OK" : "NG");
            textView3.setText(R.string.str_test_info_dmp_file);
            textView34.setText(this.mSharedPreferences.getBoolean(getResources().getString(R.string.str_test_info_dmp_file), false) ? "OK" : "NG");
            textView4.setText(R.string.str_test_info_dmp_play);
            textView35.setText(this.mSharedPreferences.getBoolean(getResources().getString(R.string.str_test_info_dmp_play), false) ? "OK" : "NG");
            textView5.setText(R.string.str_test_info_mic_test);
            textView36.setText(this.mSharedPreferences.getBoolean(getResources().getString(R.string.str_test_info_mic_test), false) ? "OK" : "NG");
            textView6.setText(R.string.str_test_info_wbadjustresult);
            Log.i("Fac_AllMachineInfo", "WB ADJUST RESULT IS " + this.mSharedPreferences.getInt(getResources().getString(R.string.str_test_info_wbadjustresult), 0));
            textView37.setText(this.mSharedPreferences.getInt(getResources().getString(R.string.str_test_info_wbadjustresult), 0) == 1 ? "OK" : "NG");
            textView7.setText(R.string.str_test_info_gammaadjustresult);
            Log.i("Fac_AllMachineInfo", "GAMMA ADJUST RESULT IS " + this.mSharedPreferences.getInt(getResources().getString(R.string.str_test_info_gammaadjustresult), 0));
            textView38.setText(this.mSharedPreferences.getInt(getResources().getString(R.string.str_test_info_gammaadjustresult), 0) == 1 ? "OK" : "NG");
            textView8.setText(R.string.str_test_info_savevcomresult);
            Log.i("Fac_AllMachineInfo", "vcom save RESULT IS " + this.mSharedPreferences.getBoolean(getResources().getString(R.string.str_test_info_savevcomresult), false));
            textView39.setText(this.mSharedPreferences.getBoolean(getResources().getString(R.string.str_test_info_savevcomresult), false) ? "OK" : "NG");
            for (int i7 = 0; i7 < FacPropertyDemo.SourceEnum.getShownSourceList().size(); i7++) {
                int i8 = i7 + 8;
                this.mTextViewNameList.get(i8).setText(FacPropertyDemo.SourceEnum.getShownSourceList().get(i7).getDisplayName());
                this.mTextViewValueList.get(i8).setText(this.mSharedPreferences.getBoolean(FacPropertyDemo.SourceEnum.getShownSourceList().get(i7).getDisplayName(), false) ? "OK" : "NG");
            }
            return;
        }
        String string24 = bundle.getString("ipAddr");
        String string25 = bundle.getString("macAddr");
        if (FacPropertyDemo.HIFAC_WIDE_VINE_KEY_SUPPORT && FacPropertyDemo.HIFAC_MARKET_SA) {
            String string26 = bundle.getString("wideVineKey");
            textView13.setText(R.string.str_all_machine_info_wideVineKey);
            textView44.setText(string26);
            i = 0;
            linearLayout13.setVisibility(0);
        } else {
            i = 0;
        }
        if (FacPropertyDemo.HIFAC_HDCP_1_4_KEY_SUPPORT && FacPropertyDemo.HIFAC_MARKET_SA) {
            String string27 = bundle.getString("hdcp14key");
            textView18.setText(R.string.str_all_machine_info_hdcp14Key);
            textView49.setText(string27);
            linearLayout18.setVisibility(i);
        }
        if (FacPropertyDemo.HIFAC_HDCP_2_X_KEY_SUPPORT && FacPropertyDemo.HIFAC_MARKET_SA) {
            String string28 = bundle.getString("hdcp22key");
            textView19.setText(R.string.str_all_machine_info_HDCP22Key);
            textView50.setText(string28);
            linearLayout19.setVisibility(i);
        }
        if (FacPropertyDemo.HIFAC_MGK_KEY_SUPPORT && FacPropertyDemo.HIFAC_MARKET_SA) {
            String string29 = bundle.getString("mgk");
            textView21.setText(R.string.str_all_machine_info_mgk);
            textView53.setText(string29);
            linearLayout22.setVisibility(i);
        }
        textView.setText(R.string.str_all_machine_info_strMacAddress);
        textView2.setText(R.string.str_all_machine_info_strIpAddrString);
        textView32.setText(string25);
        textView33.setText(string24);
        int length = string25.length();
        Log.i("Fac_AllMachineInfo", "macleng : " + length);
        if (FacPropertyDemo.HIFAC_MARKET_SA && length == 18) {
            imageView.setImageBitmap(BarUtil.rotateBitmap(BarUtil.generateBarcode(formatMacAddr(string25), 600, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_LICENSE_EXPIRED_ERROR), 90));
            imageView.setVisibility(0);
        }
        if (FacPropertyDemo.HIFAC_BLUETOOTH_SUPPORT) {
            String string30 = bundle.getString("bluetoothMac");
            bundle.putString("bluetoothMac", string30);
            textView3.setText(R.string.str_all_machine_info_strBluetoothMacAddress);
            textView34.setText(string30);
            i2 = 8;
        } else {
            i2 = 8;
            linearLayout3.setVisibility(8);
        }
        linearLayout4.setVisibility(i2);
        linearLayout5.setVisibility(i2);
        linearLayout6.setVisibility(i2);
        linearLayout7.setVisibility(i2);
        linearLayout8.setVisibility(i2);
        linearLayout9.setVisibility(i2);
        linearLayout10.setVisibility(i2);
        linearLayout11.setVisibility(i2);
        linearLayout12.setVisibility(i2);
        linearLayout14.setVisibility(i2);
        linearLayout31.setVisibility(i2);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4 && i != 82) {
            return false;
        }
        finish();
        return true;
    }

    private String formatMacAddr(String str) {
        String str2 = str.substring(1, 3) + str.substring(4, 6) + str.substring(7, 9) + str.substring(10, 12) + str.substring(13, 15) + str.substring(16, 18);
        Log.i("Fac_AllMachineInfo", "macAddrData : " + str2);
        return str2;
    }
}
