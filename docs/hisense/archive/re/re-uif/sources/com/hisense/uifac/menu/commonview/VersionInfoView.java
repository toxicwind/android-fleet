package com.hisense.uifac.menu.commonview;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hisense.tvcm.FacPropertyDemo;
import com.hisense.tvs.FacTvsFunctionUtil;
import com.hisense.uifac.R;
import com.hisense.uifac.menu.util.MenuConfigManager;

/* JADX INFO: loaded from: classes.dex */
public class VersionInfoView extends LinearLayout {
    private Context context;
    private LinearLayout frc_version_layout;
    private TextView frc_version_name;
    private TextView frc_version_value;
    private LinearLayout mAntishadowLayout;
    private TextView mAntishadowName;
    private TextView mAntishadowValue;
    private TextView mAttestationKey;
    private LinearLayout mBlu2StatusLayout;
    private TextView mBlu2StatusValue;
    private LinearLayout mBlu2VersionLayout;
    private TextView mBlu2VersionValue;
    private LinearLayout mBluStatusLayout;
    private TextView mBluStatusName;
    private TextView mBluStatusValue;
    private LinearLayout mBluVersionLayout;
    private TextView mBluVersionName;
    private TextView mBluVersionValue;
    private LinearLayout mChipIdLayout;
    private TextView mChipIdName;
    private TextView mChipIdValue;
    private TextView mDataDate;
    private TextView mDataVersion;
    private TextView mDlpMcuVersionView;
    private LinearLayout mDlpMcuVersionViewLayout;
    private TextView mFactoryNum;
    private Handler mHandler;
    private LayoutInflater mInflater;
    private boolean mIsGettingVersionInfo;
    private TextView mLedEffectVersionView;
    private LinearLayout mLedEffectVersionViewLayout;
    private TextView mMcuVersionView;
    private LinearLayout mMcuVersionViewLayout;
    private MenuConfigManager mMenuConfigManager;
    private TextView mPlayready25Key;
    private LinearLayout mPlayready25KeyLayout;
    private TextView mPlayready40Key;
    private LinearLayout mPlayready40KeyLayout;
    private TextView mPlayreadyKey;
    private LinearLayout mQDAUUIDLayout;
    private TextView mQDAUUIDValue;
    private LinearLayout mTconTempVrLayout;
    private LinearLayout mTconVersionLayout;
    private TextView mTconVersionName;
    private TextView mTconVersionValue;
    private LinearLayout mTypeCVersionLayout;
    private TextView mTypeCVersionValue;
    private TextView modelName;
    private TextView modelNameShow;
    private LinearLayout msd4030_version_layout;
    private TextView msd4030_version_value;
    private TextView serialNumShow;
    private TextView systemDataName;
    private TextView tconTempVrValue;
    private TextView versionShow;

    public VersionInfoView(Context context) {
        super(context);
        this.mIsGettingVersionInfo = false;
        this.context = context;
        this.mHandler = new Handler(context.getMainLooper());
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(context);
        this.mInflater = layoutInflaterFrom;
        layoutInflaterFrom.inflate(R.layout.menu_version_info, this);
        init(true);
    }

    public VersionInfoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mIsGettingVersionInfo = false;
        this.context = context;
        this.mHandler = new Handler(context.getMainLooper());
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(context);
        this.mInflater = layoutInflaterFrom;
        layoutInflaterFrom.inflate(R.layout.menu_version_info, this);
        init(false);
    }

    public void init(boolean z) {
        this.mMenuConfigManager = MenuConfigManager.getInstance(this.context);
        this.systemDataName = (TextView) findViewById(R.id.common_versioninfo_ver);
        this.modelName = (TextView) findViewById(R.id.common_versioninfo_name);
        this.modelNameShow = (TextView) findViewById(R.id.common_versioninfo_name_r);
        this.versionShow = (TextView) findViewById(R.id.common_versioninfo_ver_r);
        this.serialNumShow = (TextView) findViewById(R.id.common_versioninfo_num_r);
        this.mDataDate = (TextView) findViewById(R.id.common_versioninfo_dataver_r);
        this.mDataVersion = (TextView) findViewById(R.id.common_versioninfo_dataname_r);
        TextView textView = (TextView) findViewById(R.id.common_versioninfo_factorynum_r);
        this.mFactoryNum = textView;
        textView.setVisibility(8);
        this.mPlayreadyKey = (TextView) findViewById(R.id.factory_playready_key);
        this.mAttestationKey = (TextView) findViewById(R.id.factory_attestation_key);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.chip_id_layout);
        this.mChipIdLayout = linearLayout;
        linearLayout.setVisibility(0);
        this.mChipIdName = (TextView) findViewById(R.id.chip_id_name);
        this.mChipIdValue = (TextView) findViewById(R.id.chip_id_value);
        this.mPlayready25Key = (TextView) findViewById(R.id.factory_playready_25key);
        this.mPlayready40Key = (TextView) findViewById(R.id.factory_playready_40key);
        if (FacPropertyDemo.HIFAC_IS_OLED) {
            LinearLayout linearLayout2 = (LinearLayout) findViewById(R.id.tcon_temp_vr_layout);
            this.mTconTempVrLayout = linearLayout2;
            linearLayout2.setVisibility(0);
            this.tconTempVrValue = (TextView) findViewById(R.id.tcon_temp_vr_value);
        }
        if (FacPropertyDemo.HIFAC_MARKET_JP && FacPropertyDemo.HIFAC_IS_OLED_TVS) {
            LinearLayout linearLayout3 = (LinearLayout) findViewById(R.id.anti_shadow_layout);
            this.mAntishadowLayout = linearLayout3;
            linearLayout3.setVisibility(0);
            this.mAntishadowName = (TextView) findViewById(R.id.anti_shadow_name);
            this.mAntishadowValue = (TextView) findViewById(R.id.anti_shadow_value);
        }
        if (this.mMenuConfigManager.isFrcSupport()) {
            LinearLayout linearLayout4 = (LinearLayout) findViewById(R.id.frc_version_layout);
            this.frc_version_layout = linearLayout4;
            linearLayout4.setVisibility(0);
            this.frc_version_name = (TextView) findViewById(R.id.frc_version_name);
            this.frc_version_value = (TextView) findViewById(R.id.frc_version_value);
        }
        if (this.mMenuConfigManager.isTconSupport()) {
            LinearLayout linearLayout5 = (LinearLayout) findViewById(R.id.tcon_version_layout);
            this.mTconVersionLayout = linearLayout5;
            linearLayout5.setVisibility(0);
            this.mTconVersionName = (TextView) findViewById(R.id.tcon_version_name);
            this.mTconVersionValue = (TextView) findViewById(R.id.tcon_version_value);
        }
        if (FacPropertyDemo.HIFAC_PLAY_READY_25KEY_SUPPORT) {
            LinearLayout linearLayout6 = (LinearLayout) findViewById(R.id.uifac_playready_25_layout);
            this.mPlayready25KeyLayout = linearLayout6;
            linearLayout6.setVisibility(0);
            this.mPlayready25Key = (TextView) findViewById(R.id.factory_playready_25key);
        }
        if (FacPropertyDemo.HIFAC_PLAY_READY_40KEY_SUPPORT) {
            LinearLayout linearLayout7 = (LinearLayout) findViewById(R.id.uifac_playready_40_layout);
            this.mPlayready40KeyLayout = linearLayout7;
            linearLayout7.setVisibility(0);
            this.mPlayready40Key = (TextView) findViewById(R.id.factory_playready_40key);
        }
        if (this.mMenuConfigManager.isBluSupport()) {
            LinearLayout linearLayout8 = (LinearLayout) findViewById(R.id.blu_version_layout);
            this.mBluVersionLayout = linearLayout8;
            linearLayout8.setVisibility(0);
            LinearLayout linearLayout9 = (LinearLayout) findViewById(R.id.blu_status_layout);
            this.mBluStatusLayout = linearLayout9;
            linearLayout9.setVisibility(0);
            this.mBluVersionName = (TextView) findViewById(R.id.blu_version_name);
            this.mBluVersionValue = (TextView) findViewById(R.id.blu_version_value);
            this.mBluStatusName = (TextView) findViewById(R.id.blu_status_name);
            this.mBluStatusValue = (TextView) findViewById(R.id.blu_status_value);
            if (this.mMenuConfigManager.isDualBluSupport()) {
                LinearLayout linearLayout10 = (LinearLayout) findViewById(R.id.blu2_version_layout);
                this.mBlu2VersionLayout = linearLayout10;
                linearLayout10.setVisibility(0);
                LinearLayout linearLayout11 = (LinearLayout) findViewById(R.id.blu2_status_layout);
                this.mBlu2StatusLayout = linearLayout11;
                linearLayout11.setVisibility(0);
                this.mBlu2VersionValue = (TextView) findViewById(R.id.blu2_version_value);
                this.mBlu2StatusValue = (TextView) findViewById(R.id.blu2_status_value);
            }
        }
        if (this.mMenuConfigManager.isSupportQDAKey()) {
            LinearLayout linearLayout12 = (LinearLayout) findViewById(R.id.qda_uuid_layout);
            this.mQDAUUIDLayout = linearLayout12;
            linearLayout12.setVisibility(0);
            this.mQDAUUIDValue = (TextView) findViewById(R.id.qda_uuid_value);
        }
        if (FacPropertyDemo.HIFAC_TYPE_C_SUPPORT) {
            LinearLayout linearLayout13 = (LinearLayout) findViewById(R.id.msd4030_version_layout);
            this.msd4030_version_layout = linearLayout13;
            linearLayout13.setVisibility(0);
            this.msd4030_version_value = (TextView) findViewById(R.id.msd4030_version_value);
        }
        if (FacPropertyDemo.HIFAC_TYPE_C_SUPPORT) {
            LinearLayout linearLayout14 = (LinearLayout) findViewById(R.id.typec_version_layout);
            this.mTypeCVersionLayout = linearLayout14;
            linearLayout14.setVisibility(0);
            this.mTypeCVersionValue = (TextView) findViewById(R.id.typec_version_value);
        }
        if (FacPropertyDemo.HIFAC_IS_LASER4K) {
            LinearLayout linearLayout15 = (LinearLayout) findViewById(R.id.mcu_version_layout);
            this.mMcuVersionViewLayout = linearLayout15;
            linearLayout15.setVisibility(0);
            this.mMcuVersionView = (TextView) findViewById(R.id.mcu_version_value);
            LinearLayout linearLayout16 = (LinearLayout) findViewById(R.id.dlp_version_layout);
            this.mDlpMcuVersionViewLayout = linearLayout16;
            linearLayout16.setVisibility(0);
            this.mDlpMcuVersionView = (TextView) findViewById(R.id.dlp_mcu_version_value);
            LinearLayout linearLayout17 = (LinearLayout) findViewById(R.id.led_effect_version_layout);
            this.mLedEffectVersionViewLayout = linearLayout17;
            linearLayout17.setVisibility(0);
            this.mLedEffectVersionView = (TextView) findViewById(R.id.led_effect_version_value);
        }
        if (FacPropertyDemo.HIFAC_IS_OLED_TVS) {
            this.mAntishadowName.setTextColor(-7829368);
            this.mAntishadowValue.setTextColor(-7829368);
            this.modelName.setTextColor(-7829368);
            this.modelNameShow.setTextColor(-7829368);
            this.systemDataName.setTextColor(-7829368);
            this.versionShow.setTextColor(-7829368);
            this.mDataDate.setTextColor(-7829368);
            this.mDataDate.setTextColor(-7829368);
            TextView textView2 = (TextView) findViewById(R.id.factory_playready_40key_name);
            this.mPlayreadyKey.setTextColor(-7829368);
            this.mPlayready25Key.setTextColor(-7829368);
            this.mPlayready40Key.setTextColor(-7829368);
            textView2.setTextColor(-7829368);
            TextView textView3 = (TextView) findViewById(R.id.factory_attestation_key_name);
            this.mAttestationKey.setTextColor(-7829368);
            textView3.setTextColor(-7829368);
            this.mChipIdName.setTextColor(-7829368);
            this.mChipIdValue.setTextColor(-7829368);
            if (this.mMenuConfigManager.isBluSupport()) {
                this.mBluVersionName.setTextColor(-7829368);
                this.mBluVersionValue.setTextColor(-7829368);
                this.mBluStatusName.setTextColor(-7829368);
                this.mBluStatusValue.setTextColor(-7829368);
            }
            if (this.mMenuConfigManager.isTconSupport()) {
                this.mTconVersionName.setTextColor(-7829368);
                this.mTconVersionValue.setTextColor(-7829368);
            }
            if (this.mMenuConfigManager.isFrcSupport()) {
                this.frc_version_name.setTextColor(-7829368);
                this.frc_version_value.setTextColor(-7829368);
            }
            TextView textView4 = (TextView) findViewById(R.id.common_versioninfo_num);
            this.serialNumShow.setTextColor(-7829368);
            textView4.setTextColor(-7829368);
        }
        setValue();
    }

    public void setValue() {
        getVersionInfoBackground();
    }

    private String getFacApkVersion() {
        try {
            return "v" + this.context.getPackageManager().getPackageInfo(this.context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void getVersionInfoBackground() {
        if (this.mIsGettingVersionInfo) {
            return;
        }
        new Thread(new Runnable() { // from class: com.hisense.uifac.menu.commonview.VersionInfoView.1
            @Override // java.lang.Runnable
            public void run() {
                VersionInfoView.this.mIsGettingVersionInfo = true;
                VersionInfoBean versionInfoBean = VersionInfoView.this.getVersionInfoBean();
                VersionInfoView.this.mIsGettingVersionInfo = false;
                if (versionInfoBean != null) {
                    VersionInfoView.this.mHandler.post(VersionInfoView.this.new SetValueRunnable(versionInfoBean));
                }
            }
        }).start();
    }

    class SetValueRunnable implements Runnable {
        private VersionInfoBean mVersionInfoBean;

        public SetValueRunnable(VersionInfoBean versionInfoBean) {
            this.mVersionInfoBean = versionInfoBean;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.mVersionInfoBean != null) {
                VersionInfoView.this.modelNameShow.setText(this.mVersionInfoBean.getModelNameShowValue());
                VersionInfoView.this.versionShow.setText(this.mVersionInfoBean.getVersionShowValue());
                VersionInfoView.this.serialNumShow.setText(this.mVersionInfoBean.getSerialNumShow());
                VersionInfoView.this.mDataDate.setText(this.mVersionInfoBean.getDataDateValue());
                VersionInfoView.this.mDataVersion.setText(this.mVersionInfoBean.getDataVersionValue());
                VersionInfoView.this.mChipIdValue.setText(this.mVersionInfoBean.getChipIdValue());
                if (FacPropertyDemo.HIFAC_IS_OLED) {
                    VersionInfoView.this.tconTempVrValue.setText(this.mVersionInfoBean.getTconTempVrValue());
                }
                if (VersionInfoView.this.mMenuConfigManager.isFrcSupport()) {
                    VersionInfoView.this.frc_version_value.setText(this.mVersionInfoBean.getFrcVersionValue());
                }
                if (VersionInfoView.this.mMenuConfigManager.isTconSupport()) {
                    VersionInfoView.this.mTconVersionValue.setText(this.mVersionInfoBean.getTconVersionValue());
                }
                if (VersionInfoView.this.mMenuConfigManager.isBluSupport()) {
                    VersionInfoView.this.mBluVersionValue.setText(this.mVersionInfoBean.getBluVersionValue());
                    VersionInfoView.this.mBluStatusValue.setText(this.mVersionInfoBean.getBluStatusValue());
                    if (VersionInfoView.this.mMenuConfigManager.isDualBluSupport()) {
                        VersionInfoView.this.mBlu2VersionValue.setText(this.mVersionInfoBean.getBlu2VersionValue());
                        VersionInfoView.this.mBlu2StatusValue.setText(this.mVersionInfoBean.getBlu2StatusValue());
                    }
                }
                if (VersionInfoView.this.mMenuConfigManager.isSupportQDAKey()) {
                    VersionInfoView.this.mQDAUUIDValue.setText(this.mVersionInfoBean.getQDAUUIDValue());
                }
                if (FacPropertyDemo.HIFAC_TYPE_C_SUPPORT) {
                    VersionInfoView.this.msd4030_version_value.setText(this.mVersionInfoBean.getMsd4030VersionValue());
                }
                if (FacPropertyDemo.HIFAC_TYPE_C_SUPPORT) {
                    VersionInfoView.this.mTypeCVersionValue.setText(this.mVersionInfoBean.getTypeCVersionValue());
                }
                if (FacPropertyDemo.HIFAC_IS_LASER4K) {
                    VersionInfoView.this.mMcuVersionView.setText(VersionInfoView.this.mMenuConfigManager.getMcuVersionInfo());
                    VersionInfoView.this.mDlpMcuVersionView.setText(VersionInfoView.this.mMenuConfigManager.getDlpMcuVersion());
                    VersionInfoView.this.mLedEffectVersionView.setText(VersionInfoView.this.mMenuConfigManager.getLedEffectVersion());
                }
                if (FacPropertyDemo.HIFAC_PLAY_READY_30KEY_SUPPORT) {
                    VersionInfoView.this.mPlayreadyKey.setText(this.mVersionInfoBean.getPlayReadyKeyName());
                }
                if (FacPropertyDemo.HIFAC_PLAY_READY_25KEY_SUPPORT) {
                    VersionInfoView.this.mPlayready25Key.setText(this.mVersionInfoBean.getPlayReady25KeyName());
                }
                if (FacPropertyDemo.HIFAC_PLAY_READY_40KEY_SUPPORT) {
                    VersionInfoView.this.mPlayready40Key.setText(this.mVersionInfoBean.getPlayReady40KeyName());
                }
                if (FacPropertyDemo.HIFAC_MARKET_JP && FacPropertyDemo.HIFAC_IS_OLED_TVS) {
                    VersionInfoView.this.mAntishadowValue.setText(this.mVersionInfoBean.getAntishadowStatus());
                }
                VersionInfoView.this.mAttestationKey.setText(this.mVersionInfoBean.getAttestationKeyname());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:24:0x00ce  */
    /* JADX WARN: Code duplicated, block: B:27:0x00db  */
    /* JADX WARN: Code duplicated, block: B:28:0x00e2  */
    /* JADX WARN: Code duplicated, block: B:31:0x00ea  */
    /* JADX WARN: Code duplicated, block: B:34:0x00f7  */
    /* JADX WARN: Code duplicated, block: B:37:0x0104  */
    /* JADX WARN: Code duplicated, block: B:40:0x0111  */
    /* JADX WARN: Code duplicated, block: B:48:0x0140  */
    /* JADX WARN: Code duplicated, block: B:49:0x0143  */
    /* JADX WARN: Code duplicated, block: B:51:0x0147  */
    /* JADX WARN: Code duplicated, block: B:54:0x0150  */
    /* JADX WARN: Code duplicated, block: B:56:0x0158  */
    /* JADX WARN: Code duplicated, block: B:57:0x0170  */
    /* JADX WARN: Code duplicated, block: B:59:0x0178  */
    /* JADX WARN: Code duplicated, block: B:60:0x0190  */
    /* JADX WARN: Instruction removed from duplicated block: B:56:0x0158, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:59:0x0178, please report this as an issue */
    public VersionInfoBean getVersionInfoBean() {
        String string;
        String backlightDriverVersion;
        String lCMRunningStatus;
        String slaveBacklightDriverVersion;
        String slaveLCMRunningStatus;
        String tvChipVersion;
        int tvsTPC;
        String str;
        VersionInfoBean versionInfoBean = new VersionInfoBean(this);
        String dateInfo = this.mMenuConfigManager.getDateInfo(false);
        String dateInfo2 = this.mMenuConfigManager.getDateInfo(true);
        StringBuilder sb = new StringBuilder();
        String str2 = "";
        sb.append("");
        sb.append(dateInfo2);
        versionInfoBean.setModelNameShowValue(sb.toString());
        versionInfoBean.setVersionShowValue(dateInfo);
        if (this.mMenuConfigManager.getProductTypeName().startsWith("PX")) {
            string = this.mMenuConfigManager.getTvScreenVersion();
        } else {
            string = ((LinearLayout) this).mContext.getString(R.string.str_factorymenu_panelname);
        }
        versionInfoBean.setSerialNumShow(string);
        versionInfoBean.setDataDateValue(this.mMenuConfigManager.getDataDateInfo());
        versionInfoBean.setDataVersionValue(this.mMenuConfigManager.getDataVersion());
        versionInfoBean.setApkVersionValue(getFacApkVersion());
        versionInfoBean.setTconVersionValue(this.mMenuConfigManager.isTconSupport() ? this.mMenuConfigManager.getTconVersion() : null);
        versionInfoBean.setFrcVersionValue(this.mMenuConfigManager.isFrcSupport() ? this.mMenuConfigManager.getFRCVersion() : null);
        if (this.mMenuConfigManager.isBluSupport()) {
            backlightDriverVersion = this.mMenuConfigManager.getBacklightDriverVersion();
            lCMRunningStatus = this.mMenuConfigManager.getLCMRunningStatus();
            if (this.mMenuConfigManager.isDualBluSupport()) {
                slaveBacklightDriverVersion = this.mMenuConfigManager.getSlaveBacklightDriverVersion();
                slaveLCMRunningStatus = this.mMenuConfigManager.getSlaveLCMRunningStatus();
            } else {
                slaveBacklightDriverVersion = null;
            }
            versionInfoBean.setBluVersionValue(backlightDriverVersion);
            versionInfoBean.setBluStatusValue(lCMRunningStatus);
            versionInfoBean.setBlu2VersionValue(slaveBacklightDriverVersion);
            versionInfoBean.setBlu2StatusValue(slaveLCMRunningStatus);
            if (this.mMenuConfigManager.isSupportQDAKey()) {
                versionInfoBean.setQDAUUIDValue(this.mMenuConfigManager.getQDAUUID());
            }
            if (FacPropertyDemo.HIFAC_TYPE_C_SUPPORT) {
                tvChipVersion = this.mMenuConfigManager.getTvChipVersion();
            } else {
                tvChipVersion = null;
            }
            versionInfoBean.setMsd4030VersionValue(tvChipVersion);
            versionInfoBean.setTypeCVersionValue(FacPropertyDemo.HIFAC_TYPE_C_SUPPORT ? this.mMenuConfigManager.getTypeCVersion() : null);
            if (FacPropertyDemo.HIFAC_PLAY_READY_30KEY_SUPPORT) {
                versionInfoBean.setPlayReadyKeyName(this.mMenuConfigManager.getPlayReadyKey());
            }
            if (FacPropertyDemo.HIFAC_PLAY_READY_25KEY_SUPPORT) {
                versionInfoBean.setPlayReady25Keyname(this.mMenuConfigManager.getPlayReady25Key());
            }
            if (FacPropertyDemo.HIFAC_PLAY_READY_40KEY_SUPPORT) {
                versionInfoBean.setPlayReady40Keyname(this.mMenuConfigManager.getPlayReady40Key());
            }
            versionInfoBean.setAttestationKeyname(this.mMenuConfigManager.getAttestationKey());
            versionInfoBean.setChipIdValue(this.mMenuConfigManager.getChipID());
            tvsTPC = FacTvsFunctionUtil.TVS_VALUE_ALL_OFF;
            if (FacPropertyDemo.HIFAC_MARKET_JP && FacPropertyDemo.HIFAC_IS_OLED_TVS) {
                tvsTPC = this.mMenuConfigManager.getTvsTPC();
            }
            if (tvsTPC == FacTvsFunctionUtil.TVS_VALUE_ALL_OFF) {
                str2 = "ALL OFF";
            } else if (tvsTPC == FacTvsFunctionUtil.TVS_VALUE_AUTO) {
                str2 = "AUTO";
            }
            versionInfoBean.setAntishadowStatus(str2);
            if (FacPropertyDemo.HIFAC_IS_OLED) {
                if (this.mMenuConfigManager.checkTconTempVr() == 0) {
                    str = this.mMenuConfigManager.getTconTempVersion() + "  OK";
                } else if (this.mMenuConfigManager.checkTconTempVr() == 1) {
                    str = this.mMenuConfigManager.getTconTempVersion() + "  NG";
                } else {
                    str = "NG";
                }
                versionInfoBean.setTconTempVrValue(str);
            }
            return versionInfoBean;
        }
        backlightDriverVersion = null;
        lCMRunningStatus = null;
        slaveBacklightDriverVersion = null;
        slaveLCMRunningStatus = slaveBacklightDriverVersion;
        versionInfoBean.setBluVersionValue(backlightDriverVersion);
        versionInfoBean.setBluStatusValue(lCMRunningStatus);
        versionInfoBean.setBlu2VersionValue(slaveBacklightDriverVersion);
        versionInfoBean.setBlu2StatusValue(slaveLCMRunningStatus);
        if (this.mMenuConfigManager.isSupportQDAKey()) {
            versionInfoBean.setQDAUUIDValue(this.mMenuConfigManager.getQDAUUID());
        }
        if (FacPropertyDemo.HIFAC_TYPE_C_SUPPORT) {
            tvChipVersion = this.mMenuConfigManager.getTvChipVersion();
        } else {
            tvChipVersion = null;
        }
        versionInfoBean.setMsd4030VersionValue(tvChipVersion);
        versionInfoBean.setTypeCVersionValue(FacPropertyDemo.HIFAC_TYPE_C_SUPPORT ? this.mMenuConfigManager.getTypeCVersion() : null);
        if (FacPropertyDemo.HIFAC_PLAY_READY_30KEY_SUPPORT) {
            versionInfoBean.setPlayReadyKeyName(this.mMenuConfigManager.getPlayReadyKey());
        }
        if (FacPropertyDemo.HIFAC_PLAY_READY_25KEY_SUPPORT) {
            versionInfoBean.setPlayReady25Keyname(this.mMenuConfigManager.getPlayReady25Key());
        }
        if (FacPropertyDemo.HIFAC_PLAY_READY_40KEY_SUPPORT) {
            versionInfoBean.setPlayReady40Keyname(this.mMenuConfigManager.getPlayReady40Key());
        }
        versionInfoBean.setAttestationKeyname(this.mMenuConfigManager.getAttestationKey());
        versionInfoBean.setChipIdValue(this.mMenuConfigManager.getChipID());
        tvsTPC = FacTvsFunctionUtil.TVS_VALUE_ALL_OFF;
        if (FacPropertyDemo.HIFAC_MARKET_JP) {
            tvsTPC = this.mMenuConfigManager.getTvsTPC();
        }
        if (tvsTPC == FacTvsFunctionUtil.TVS_VALUE_ALL_OFF) {
            str2 = "ALL OFF";
        } else if (tvsTPC == FacTvsFunctionUtil.TVS_VALUE_AUTO) {
            str2 = "AUTO";
        }
        versionInfoBean.setAntishadowStatus(str2);
        if (FacPropertyDemo.HIFAC_IS_OLED) {
            if (this.mMenuConfigManager.checkTconTempVr() == 0) {
                str = this.mMenuConfigManager.getTconTempVersion() + "  OK";
            } else if (this.mMenuConfigManager.checkTconTempVr() == 1) {
                str = this.mMenuConfigManager.getTconTempVersion() + "  NG";
            } else {
                str = "NG";
            }
            versionInfoBean.setTconTempVrValue(str);
        }
        return versionInfoBean;
    }

    class VersionInfoBean {
        private String antishadowStatus;
        private String attestationKeyName;
        private String blu2StatusValue;
        private String blu2VersionValue;
        private String bluStatusValue;
        private String bluVersionValue;
        private String chipIdValue;
        private String dataDateValue;
        private String dataVersionValue;
        private String frcVersionValue;
        private String modelNameShowValue;
        private String msd4030VersionValue;
        private String playReady25Keyname;
        private String playReady40Keyname;
        private String playReadyKeyname;
        private String qdaUUIDValue;
        private String serialNumShow;
        private String tconTempVrValue;
        private String tconVersionValue;
        private String typeCVersionValue;
        private String versionShowValue;

        public void setApkVersionValue(String str) {
        }

        VersionInfoBean(VersionInfoView versionInfoView) {
        }

        public String getModelNameShowValue() {
            String str = this.modelNameShowValue;
            return str == null ? "" : str;
        }

        public void setModelNameShowValue(String str) {
            this.modelNameShowValue = str;
        }

        public String getVersionShowValue() {
            String str = this.versionShowValue;
            return str == null ? "" : str;
        }

        public void setVersionShowValue(String str) {
            this.versionShowValue = str;
        }

        public String getSerialNumShow() {
            String str = this.serialNumShow;
            return str == null ? "" : str;
        }

        public void setSerialNumShow(String str) {
            this.serialNumShow = str;
        }

        public String getDataDateValue() {
            String str = this.dataDateValue;
            return str == null ? "" : str;
        }

        public void setDataDateValue(String str) {
            this.dataDateValue = str;
        }

        public String getDataVersionValue() {
            String str = this.dataVersionValue;
            return str == null ? "" : str;
        }

        public void setDataVersionValue(String str) {
            this.dataVersionValue = str;
        }

        public String getFrcVersionValue() {
            String str = this.frcVersionValue;
            return str == null ? "" : str;
        }

        public String getTconVersionValue() {
            String str = this.tconVersionValue;
            return str == null ? "" : str;
        }

        public void setFrcVersionValue(String str) {
            this.frcVersionValue = str;
        }

        public void setTconVersionValue(String str) {
            this.tconVersionValue = str;
        }

        public String getBluVersionValue() {
            String str = this.bluVersionValue;
            return str == null ? "" : str;
        }

        public void setBluVersionValue(String str) {
            this.bluVersionValue = str;
        }

        public String getQDAUUIDValue() {
            String str = this.qdaUUIDValue;
            return str == null ? "" : str;
        }

        public void setQDAUUIDValue(String str) {
            this.qdaUUIDValue = str;
        }

        public String getBluStatusValue() {
            String str = this.bluStatusValue;
            return str == null ? "" : str;
        }

        public void setBluStatusValue(String str) {
            this.bluStatusValue = str;
        }

        public String getBlu2VersionValue() {
            String str = this.blu2VersionValue;
            return str == null ? "" : str;
        }

        public void setBlu2VersionValue(String str) {
            this.blu2VersionValue = str;
        }

        public String getBlu2StatusValue() {
            String str = this.blu2StatusValue;
            return str == null ? "" : str;
        }

        public void setBlu2StatusValue(String str) {
            this.blu2StatusValue = str;
        }

        public String getMsd4030VersionValue() {
            String str = this.msd4030VersionValue;
            return str == null ? "" : str;
        }

        public void setMsd4030VersionValue(String str) {
            this.msd4030VersionValue = str;
        }

        public String getTypeCVersionValue() {
            String str = this.typeCVersionValue;
            return str == null ? "" : str;
        }

        public void setTypeCVersionValue(String str) {
            this.typeCVersionValue = str;
        }

        public String getPlayReadyKeyName() {
            String str = this.playReadyKeyname;
            return str == null ? "" : str;
        }

        public void setPlayReadyKeyName(String str) {
            this.playReadyKeyname = str;
        }

        public String getPlayReady25KeyName() {
            String str = this.playReady25Keyname;
            return str == null ? "" : str;
        }

        public String getPlayReady40KeyName() {
            String str = this.playReady40Keyname;
            return str == null ? "" : str;
        }

        public void setPlayReady25Keyname(String str) {
            this.playReady25Keyname = str;
        }

        public void setPlayReady40Keyname(String str) {
            this.playReady40Keyname = str;
        }

        public String getAttestationKeyname() {
            String str = this.attestationKeyName;
            return str == null ? "" : str;
        }

        public void setAttestationKeyname(String str) {
            this.attestationKeyName = str;
        }

        public String getChipIdValue() {
            String str = this.chipIdValue;
            return str == null ? "" : str;
        }

        public void setChipIdValue(String str) {
            this.chipIdValue = str;
        }

        public String getAntishadowStatus() {
            String str = this.antishadowStatus;
            return str == null ? "" : str;
        }

        public void setAntishadowStatus(String str) {
            this.antishadowStatus = str;
        }

        public String getTconTempVrValue() {
            String str = this.tconTempVrValue;
            return str == null ? "" : str;
        }

        public void setTconTempVrValue(String str) {
            this.tconTempVrValue = str;
        }
    }
}
