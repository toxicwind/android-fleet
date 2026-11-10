package com.hisense.tvcm;

import android.app.backup.BackupManager;
import android.bluetooth.BluetoothAdapter;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.hardware.hdmi.HdmiControlManager;
import android.hardware.hdmi.HdmiTvClient;
import android.media.AudioManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;
import com.hisense.avmiddleware.HisAudioApi;
import com.hisense.avmiddleware.HisVideoApi;
import com.hisense.dtvservice.ChannelInfoBase;
import com.hisense.dtvservice.HisChannelListApiImpl;
import com.hisense.dtvservice.HisCommonApiImpl;
import com.hisense.dtvservice.HisPlayApiImpl;
import com.hisense.panelprotect.PanelProtectManager;
import com.hisense.support.tv.HisensePlatformConfig;
import com.hisense.support.tv.HisenseTVNativeWrapper;
import com.hisense.support.tv.HisenseTvAPI;
import com.hisense.support.tv.HisenseTvFApiEmmc;
import com.hisense.support.tv.HisenseTvFactoryApi;
import com.hisense.support.tv.IFactoryCleanAppsListener;
import com.hisense.tvmanager.atsc.ChannelList;
import com.hisense.tvmanager.atsc.Commons;
import com.hisense.tvmanager.atsc.Factory;
import com.hisense.tvmanager.atsc.Play;
import com.hisense.tvs.CESIConstants;
import com.hisense.tvs.FacTvsAqPqManager;
import com.hisense.tvs.FacTvsFunctionManager;
import com.hisense.tvs.FacTvsFunctionUtil;
import com.hisense.tvs.FacTvsKeyWriter;
import com.hisense.tvs.FacTvsSourceManager;
import com.hisense.tvs.FacTvsTvManager;
import com.hisense.uiadapter.TVCalculator;
import com.hisense.uifac.R;
import com.hisense.uifac.network.adapter.WifiAdmin;
import com.hisense.uifac.util.FacCountryUtils;
import com.hisense.uifac.util.FacLanguageUtils;
import com.hisense.uifac.util.FacLogoUtils;
import com.hisense.uifac.util.IMtkFunCallback;
import com.hisense.uifac.util.ISourceStatusChangeCallBack;
import com.hisense.uifac.util.ISystemManagerStatusCallBack;
import com.hisense.uifac.util.SaveResultToFile;
import com.mediatek.RenderPattern.RenderPattern;
import com.mediatek.twoworlds.FactoryService.MtkTvFactoryServiceBase;
import com.mediatek.twoworlds.tv.MtkTvBanner;
import com.mediatek.twoworlds.tv.MtkTvBroadcast;
import com.mediatek.twoworlds.tv.MtkTvBroadcastBase;
import com.mediatek.twoworlds.tv.MtkTvCI;
import com.mediatek.twoworlds.tv.MtkTvConfig;
import com.mediatek.twoworlds.tv.MtkTvHighLevel;
import com.mediatek.twoworlds.tv.MtkTvInputSource;
import com.mediatek.twoworlds.tv.MtkTvInputSourceBase;
import com.mediatek.twoworlds.tv.MtkTvMultiView;
import com.mediatek.twoworlds.tv.MtkTvScanDvbsBase;
import com.mediatek.twoworlds.tv.MtkTvVolCtrl;
import com.mediatek.twoworlds.tv.common.MtkTvConfigTypeBase;
import com.mediatek.twoworlds.tv.common.MtkTvSvctxNotifyCode;
import com.mediatek.twoworlds.tv.common.MtkTvTISMsgBase;
import com.mediatek.twoworlds.tv.model.MtkTvRatingConvert2Goo;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class FacFunction_Adapter extends FacFunction {
    private static long mAgingTime;
    private static Context mContext;
    private static FacFunction_Adapter mFacFunction_Adapter;
    private static int mFacLogIndex;
    private static int mtkPictureModeValue;
    Object FactoryOkRetrySync;
    public int[] adc_bcut_value;
    public int[] adc_bdrv_value;
    public int[] adc_gcut_value;
    public int[] adc_gdrv_value;
    public int[] adc_rcut_value;
    public int[] adc_rdrv_value;
    private List<ATVPresetChannel> atvChInfo;
    public FacPropertyDemo.AudioMode audiomode_value;
    boolean bFactoryOkApi_ActionDone;
    MtkTvBroadcastBase broadbase;
    private int displayH;
    private int displayW;
    private List<DTVPresetChannel> dtvChInfo;
    public int freq_step_1;
    public int freq_step_2;
    public int freq_step_3;
    public int freq_step_4;
    public int freq_step_5;
    public int freq_step_6;
    public int freq_step_7;
    public int freq_step_8;
    public FacPropertyDemo.HomeOrTVMode fun_homeortv_value;
    public FacPropertyDemo.OSDLanguage fun_osdlanguage_value;
    FacFunctionAdapterHelper mAdapterHelper;
    private Commons mAtscCommons;
    private AudioManager mAudioManager;
    private String mBluVersion;
    ChannelList mChannelList;
    private long mEnterAgingModeTime;
    private long mExitAgingModeTime;
    private FacTvsAqPqManager mFacTvsAqPqmanager;
    private FacTvsSourceManager mFacTvsSourceManager;
    private FacTvsTvManager mFacTvsTvManager;
    Factory mFactory;
    private List<String> mFactoryLog;
    private String mFrcVersion;
    List<FacPropertyDemo.SourceEnum> mHdmiList;
    private HisAudioApi mHisAudioApi;
    HisChannelListApiImpl mHisChannelListApiImpl;
    HisCommonApiImpl mHisCommonApiImpl;
    HisPlayApiImpl mHisPlayApiImpl;
    private HisVideoApi mHisVideoApi;
    HisenseTvAPI mHisenseTvAPI;
    private boolean mIsBluSupport;
    private boolean mIsExitFacLogThread;
    private boolean mIsFactoryOkFailed;
    private boolean mIsFrcSupport;
    private boolean mIsTconSupport;
    private HisenseTvFactoryApi mKeyWriter;
    MtkTvCI mMtkTvCI;
    MtkTvConfig mMtkTvConfig;
    private MtkTvFactoryServiceBase mMtkTvFactoryService;
    private MtkTvHighLevel mMtkTvHighLevel;
    MtkTvInputSource mMtkTvInputSource;
    MtkTvVolCtrl mMtkTvVolCtrl;
    private PanelProtectManager mPanelProtectManager;
    Play mPlay;
    private RenderPattern mRenderPattern;
    SharedPreferences mSharedPreferences;
    List<MtkTvInputSourceBase.InputSourceRecord> mSourceList;
    private String mStrVideoInfo;
    private String mTconVersion;
    FacTvsFunctionManager mTvsFunc;
    FacTvsKeyWriter mTvsKey;
    private WifiAdmin mWifiAdmin;
    private boolean mainBoardTestIsStrat;
    public FacPropertyDemo.DimensionGroup mode_2dor3d_value;
    public FacPropertyDemo.ThreeDimensionMode mode_3d;
    public int mode_3dto2d;
    public int mode_convergence;
    public int mode_fielddepth;
    public int mode_leftrightswap;
    public int[] overscan_hstart;
    public int[] overscan_vstart;
    public int[][] picturecurve_point0_value;
    public int[][] picturecurve_point100_value;
    public int[][] picturecurve_point25_value;
    public int[][] picturecurve_point50_value;
    public int[][] picturecurve_point75_value;
    public FacPropertyDemo.VideoCurveModeGroup picturecurvemode_value;
    public FacPropertyDemo.SavingMode savingmode_value;
    int playResult = -1;
    ChannelInfoBase currentchannel = new ChannelInfoBase();
    List<ChannelInfoBase> list_air = new ArrayList();
    List<ChannelInfoBase> list_cable = new ArrayList();
    List<com.hisense.tvmanager.atsc.ChannelInfoBase> list_air_ATSC = new ArrayList();
    List<com.hisense.tvmanager.atsc.ChannelInfoBase> list_cable_ATSC = new ArrayList();
    com.hisense.tvmanager.atsc.ChannelInfoBase currentchannelInfo = new com.hisense.tvmanager.atsc.ChannelInfoBase();
    private int mSAChannelType = 0;
    private int mSAAirMajorChannelId = 0;
    private int mSAAirMinorChannelId = 0;
    private int mSACableMajorChannelId = 0;
    private int mSACableMinorChannelId = 0;
    public int[][][] rdrv_value = (int[][][]) Array.newInstance((Class<?>) int.class, FacPropertyDemo.WhiteBalanceSourceGroup.getSize(), FacPropertyDemo.ModelPanelGroup.getSize(), FacPropertyDemo.DimensionGroup.getSize());
    public int[][][] gdrv_value = (int[][][]) Array.newInstance((Class<?>) int.class, FacPropertyDemo.WhiteBalanceSourceGroup.getSize(), FacPropertyDemo.ModelPanelGroup.getSize(), FacPropertyDemo.DimensionGroup.getSize());

    private void showNotImplementToast(String str) {
    }

    @Override // com.hisense.tvcm.FacFunction
    public void addSourceChangedStatus(ISourceStatusChangeCallBack iSourceStatusChangeCallBack) {
    }

    @Override // com.hisense.tvcm.FacFunction
    public void cancelCurrMenu() {
    }

    public void checkSystemManagerStatus(ISystemManagerStatusCallBack iSystemManagerStatusCallBack) {
    }

    @Override // com.hisense.tvcm.FacFunction
    public void clearKeys() {
    }

    @Override // com.hisense.tvcm.FacFunction
    public void closeCurrentMMI() {
    }

    @Override // com.hisense.tvcm.FacFunction
    public void doUsbUpgrade(Context context) {
    }

    @Override // com.hisense.tvcm.FacFunction
    public void enterCAMMainMenu() {
    }

    @Override // com.hisense.tvcm.FacFunction
    public void executeClearProtectly() {
    }

    @Override // com.hisense.tvcm.FacFunction
    public void executeDataInit(Handler handler) {
    }

    @Override // com.hisense.tvcm.FacFunction
    public int get3D_Sync_Delay() {
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getAEFC_43() {
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getAEFC_44() {
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getAEFC_66Bit76() {
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getAEFC_6EBit3210() {
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getAEFC_6EBit7654() {
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getAEFC_A0() {
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getAEFC_A1() {
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getAEFC_CB() {
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getAEFC_D4() {
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getAEFC_D5Bit2() {
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getAEFC_D7HighBoun() {
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getAEFC_D7LowBoun() {
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getAEFC_D8Bit3210() {
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getAEFC_D9Bit0() {
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getAreaControl() {
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getAreaControlFrequence() {
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getAttackTime() {
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getAudioDRC() {
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getAudioDspVersion() {
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getAudioHiDevMode() {
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getAudioNrThr() {
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getAudioSifThreshold() {
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getAuthMode() {
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public String getCIPlusKey() {
        return "CIPLUS";
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getChinaDescramblerBox() {
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public String getChinaDrmKey() {
        return null;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getColorBypassEnable() {
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public String getDefaultMacAddress() {
        return "00:00:00:00:00:00";
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getDelayReduce() {
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getDmpMode() {
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public FacPropertyDemo.DtvDemodulationType getDtvType() {
        return null;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getEqualizer20KHz() {
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getEqualizer30KHz() {
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getEqualizer40KHz() {
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getEqualizer50KHz() {
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public String getFPGAVersion() {
        return null;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getGainDistributionThr() {
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getGammaBypassEnable() {
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public String getHdcpKey() {
        return null;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getImageCompensationEnable() {
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getInputSourceType(int i) {
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getLightSensorIndex() {
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getMaxx3DOption() {
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getMaxxBassFrequency() {
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getMaxxTrebleFrequency() {
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getMicDevice() {
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getNonstandardDescramble() {
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getNonstandardDescrambleplus() {
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public String getOrignalMachineID() {
        return null;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getPqBypassEnable() {
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getReleaseTime() {
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getSSCVByOneModu() {
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public String getSoundMcuVersion() {
        return null;
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean getSportMode() {
        return false;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getThreshold() {
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public String getTvChipVersion() {
        return null;
    }

    @Override // com.hisense.tvcm.FacFunction
    public String getTvScreenVersion() {
        return null;
    }

    @Override // com.hisense.tvcm.FacFunction
    public String getTypeCVersion() {
        return null;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getUserAgingSwitchStatus() {
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getVdDspVersion() {
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getVifAgcRef() {
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getVifAsiaSignalOption() {
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getVifClampGainOvNegative() {
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getVifCrKi() {
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getVifCrKp() {
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getVifCrKpKiAdjust() {
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getVifCrThr() {
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getVifOverModulation() {
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getVifTop() {
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getVifVersion() {
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getVifVgaMaximum() {
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getWooferAudioDRC() {
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getWooferAudioOutputPort() {
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getWooferAudioPaGain() {
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void initCamDisplayHandler(Handler handler, CamDisplayManager camDisplayManager) {
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean is3DSupported() {
        return false;
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean isKeyWriteProtecEnabled() {
        return false;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int isNoSignalStandbyEnabled() {
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean isShutdownBroadcastFromFactory() {
        return false;
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean isWooferModeOn() {
        return false;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void pauseTv(boolean z) {
    }

    @Override // com.hisense.tvcm.FacFunction
    public void registerCamListeners() {
    }

    @Override // com.hisense.tvcm.FacFunction
    public void selectMenuItem(int i) {
    }

    @Override // com.hisense.tvcm.FacFunction
    public void set3D_Sync_Delay(int i) {
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setAEFC_43(int i) {
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setAEFC_44(int i) {
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setAEFC_66Bit76(int i) {
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setAEFC_6EBit3210(int i) {
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setAEFC_6EBit7654(int i) {
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setAEFC_A0(int i) {
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setAEFC_A1(int i) {
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setAEFC_CB(int i) {
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setAEFC_D4(int i) {
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setAEFC_D5Bit2(int i) {
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setAEFC_D7HighBoun(int i) {
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setAEFC_D7LowBoun(int i) {
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setAEFC_D8Bit3210(int i) {
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setAEFC_D9Bit0(int i) {
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setAreaControl(int i) {
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setAreaControlFrequence(int i) {
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setAttackTimeAutoVolumeControl(int i) {
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setAudioDRC(int i) {
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setAudioDspVersion(int i) {
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setAudioHiDevMode(int i) {
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setAudioNrThr(int i) {
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setAudioSifThreshold(int i) {
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setAuthMode(int i) {
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean setBackLightOn(boolean z) {
        return false;
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean setCIPlusKey(String str) {
        return true;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setChinaDescramblerBox(int i) {
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean setChinaDrmKey(String str) {
        return false;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setColorBypassEnable(int i) {
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean setDMPMode(int i) {
        return false;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setDelayReduce(int i) {
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setDtvType(FacPropertyDemo.DtvDemodulationType dtvDemodulationType) {
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setEqualizer20KHz(int i) {
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setEqualizer30KHz(int i) {
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setEqualizer40KHz(int i) {
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setEqualizer50KHz(int i) {
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean setFilterChangeTime() {
        return false;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setGainDistributionThr(int i) {
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setGammaBypassEnable(int i) {
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setImageCompensationEnable(int i) {
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setLightSensorIndex(int i) {
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setLocaldimmingEnable(int i) {
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setMaxx3DOption(int i) {
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setMaxxBassFrequency(int i) {
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setMaxxTrebleFrequency(int i) {
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setMicDevice(int i) {
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setNoSignalStandby(int i) {
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setNonstandardDescramble(int i) {
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setNonstandardDescrambleplus(int i) {
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setNvodProgramDisplay(int i) {
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setPqBypassEnable(int i) {
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setReleaseTimeAutoVolumeControl(int i) {
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setSSCVByOneModu(int i) {
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean setSportMode(boolean z) {
        return false;
    }

    public void setSvcType(int i, int[] iArr) {
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setThresholdAutoVolumeControl(int i) {
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setUartDebugEnable() {
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setUserAgingSwitch(int i) {
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setVdDspVersion(int i) {
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setVifAgcRef(int i) {
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setVifAsiaSignalOption(int i) {
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setVifClampGainOvNegative(int i) {
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setVifCrKi(int i) {
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setVifCrKp(int i) {
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setVifCrKpKiAdjust(int i) {
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setVifCrThr(int i) {
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setVifOverModulation(int i) {
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setVifTop(int i) {
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setVifVersion(int i) {
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setVifVgaMaximum(int i) {
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean setVolumeMute(boolean z) {
        return false;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setWooferAudioDRC(int i) {
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setWooferAudioOutputPort(int i) {
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setWooferAudioPaGain(int i) {
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setnetworktype(int i) {
    }

    @Override // com.hisense.tvcm.FacFunction
    public void updateSscDefaultValueForTemp() {
    }

    @Override // com.hisense.tvcm.FacFunction
    public void upgradeBacklightDriver() {
    }

    @Override // com.hisense.tvcm.FacFunction
    public void upgradeFRC() {
    }

    static /* synthetic */ int access$608() {
        int i = mFacLogIndex;
        mFacLogIndex = i + 1;
        return i;
    }

    static {
        new HashMap();
        mtkPictureModeValue = 0;
        mFacFunction_Adapter = null;
        mAgingTime = 0L;
        mFacLogIndex = 0;
    }

    private FacFunction_Adapter() {
        this.mode_2dor3d_value = FacPropertyDemo.DimensionGroup.DIMENSION2;
        FacPropertyDemo.ModelPanelGroup modelPanelGroup = FacPropertyDemo.ModelPanelGroup.B1;
        FacPropertyDemo.ColorTemperature colorTemperature = FacPropertyDemo.ColorTemperature.STANDARD;
        int[] iArr = new int[FacPropertyDemo.ColorTemperature.getSize()];
        int[] iArr2 = new int[FacPropertyDemo.ColorTemperature.getSize()];
        int[] iArr3 = new int[FacPropertyDemo.ColorTemperature.getSize()];
        FacPropertyDemo.ToFacEnum toFacEnum = FacPropertyDemo.ToFacEnum.TOFAC_M;
        FacPropertyDemo.PowerModeEnum powerModeEnum = FacPropertyDemo.PowerModeEnum.LAST;
        this.fun_homeortv_value = FacPropertyDemo.HomeOrTVMode.TV;
        this.fun_osdlanguage_value = FacPropertyDemo.OSDLanguage.CHINESE;
        FacPropertyDemo.PictureMode pictureMode = FacPropertyDemo.PictureMode.STANDARD;
        this.picturecurvemode_value = FacPropertyDemo.VideoCurveModeGroup.BRIGHTNESS;
        this.picturecurve_point0_value = (int[][]) Array.newInstance((Class<?>) int.class, FacPropertyDemo.VideoCurveModeGroup.getSize(), FacPropertyDemo.VideoSourceGroup.getSize());
        this.picturecurve_point25_value = (int[][]) Array.newInstance((Class<?>) int.class, FacPropertyDemo.VideoCurveModeGroup.getSize(), FacPropertyDemo.VideoSourceGroup.getSize());
        this.picturecurve_point50_value = (int[][]) Array.newInstance((Class<?>) int.class, FacPropertyDemo.VideoCurveModeGroup.getSize(), FacPropertyDemo.VideoSourceGroup.getSize());
        this.picturecurve_point75_value = (int[][]) Array.newInstance((Class<?>) int.class, FacPropertyDemo.VideoCurveModeGroup.getSize(), FacPropertyDemo.VideoSourceGroup.getSize());
        this.picturecurve_point100_value = (int[][]) Array.newInstance((Class<?>) int.class, FacPropertyDemo.VideoCurveModeGroup.getSize(), FacPropertyDemo.VideoSourceGroup.getSize());
        this.audiomode_value = FacPropertyDemo.AudioMode.STANDARD;
        int[] iArr4 = new int[FacPropertyDemo.AudioMode.getSize()];
        int[] iArr5 = new int[FacPropertyDemo.AudioMode.getSize()];
        int[] iArr6 = new int[FacPropertyDemo.AudioMode.getSize()];
        int[] iArr7 = new int[FacPropertyDemo.AudioMode.getSize()];
        int[] iArr8 = new int[FacPropertyDemo.AudioMode.getSize()];
        int[] iArr9 = new int[FacPropertyDemo.AudioSourceGroup.getSize()];
        int[] iArr10 = new int[FacPropertyDemo.AudioSourceGroup.getSize()];
        int[] iArr11 = new int[FacPropertyDemo.AudioSourceGroup.getSize()];
        int[] iArr12 = new int[FacPropertyDemo.AudioSourceGroup.getSize()];
        int[] iArr13 = new int[FacPropertyDemo.AudioSourceGroup.getSize()];
        int[] iArr14 = new int[FacPropertyDemo.AudioSourceGroup.getSize()];
        int[] iArr15 = new int[FacPropertyDemo.OVERSCAN_TIMING.getSize()];
        int[] iArr16 = new int[FacPropertyDemo.OVERSCAN_TIMING.getSize()];
        int[] iArr17 = new int[FacPropertyDemo.OVERSCAN_TIMING.getSize()];
        int[] iArr18 = new int[FacPropertyDemo.OVERSCAN_TIMING.getSize()];
        this.overscan_hstart = new int[FacPropertyDemo.OVERSCAN_TIMING.getSize()];
        this.overscan_vstart = new int[FacPropertyDemo.OVERSCAN_TIMING.getSize()];
        this.mode_3d = FacPropertyDemo.ThreeDimensionMode.OFF;
        this.mode_leftrightswap = 0;
        this.mode_fielddepth = 0;
        this.mode_convergence = 0;
        this.mode_3dto2d = 0;
        this.savingmode_value = FacPropertyDemo.SavingMode.BRIGHT;
        FacPropertyDemo.SourceEnum sourceEnum = FacPropertyDemo.SourceEnum.ATV;
        this.adc_rdrv_value = new int[FacPropertyDemo.ADCColorSpaceGroup.getSize()];
        this.adc_gdrv_value = new int[FacPropertyDemo.ADCColorSpaceGroup.getSize()];
        this.adc_bdrv_value = new int[FacPropertyDemo.ADCColorSpaceGroup.getSize()];
        this.adc_rcut_value = new int[FacPropertyDemo.ADCColorSpaceGroup.getSize()];
        this.adc_gcut_value = new int[FacPropertyDemo.ADCColorSpaceGroup.getSize()];
        this.adc_bcut_value = new int[FacPropertyDemo.ADCColorSpaceGroup.getSize()];
        this.freq_step_1 = 1;
        this.freq_step_2 = 1;
        this.freq_step_3 = 1;
        this.freq_step_4 = 1;
        this.freq_step_5 = 1;
        this.freq_step_6 = 1;
        this.freq_step_7 = 1;
        this.freq_step_8 = 1;
        this.mIsFactoryOkFailed = false;
        this.FactoryOkRetrySync = new Object();
        this.mEnterAgingModeTime = 0L;
        this.mExitAgingModeTime = 0L;
        this.mSharedPreferences = null;
        this.mMtkTvFactoryService = null;
        this.displayW = 1920;
        this.displayH = 1080;
        this.mBluVersion = "NG";
        this.mTconVersion = "NG";
        this.mFrcVersion = "NG";
        this.mIsBluSupport = false;
        this.mIsTconSupport = false;
        this.mIsFrcSupport = false;
        this.mainBoardTestIsStrat = false;
        this.mHdmiList = null;
        this.atvChInfo = new ArrayList();
        this.dtvChInfo = new ArrayList();
        this.mKeyWriter = new HisenseTvFactoryApi();
        this.mAdapterHelper = null;
        this.mTvsKey = null;
        this.mTvsFunc = null;
        this.mFactoryLog = new ArrayList();
        this.mIsExitFacLogThread = false;
        this.mSourceList = null;
        new Handler(this) { // from class: com.hisense.tvcm.FacFunction_Adapter.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                Log.i("Fac_FacFunction_Adapter", "***msg=" + message.what);
                int i = message.what;
                if (i == 0) {
                    Toast.makeText(FacFunction_Adapter.mContext, FacFunction_Adapter.mContext.getResources().getString(R.string.str_widevine_key_not_exit), 1).show();
                } else if (i == 1) {
                    Toast.makeText(FacFunction_Adapter.mContext, FacFunction_Adapter.mContext.getResources().getString(R.string.str_playready_key_not_exit), 1).show();
                } else if (i == 2) {
                    Toast.makeText(FacFunction_Adapter.mContext, FacFunction_Adapter.mContext.getResources().getString(R.string.str_hdcp14_key_not_exit), 1).show();
                } else if (i == 3) {
                    Toast.makeText(FacFunction_Adapter.mContext, FacFunction_Adapter.mContext.getResources().getString(R.string.str_hdcp2x_key_not_exit), 1).show();
                }
                super.handleMessage(message);
            }
        };
        this.mStrVideoInfo = "";
        TVCalculator.getInstance();
        this.mMtkTvFactoryService = new MtkTvFactoryServiceBase();
        initSharedPreference();
        this.mWifiAdmin = WifiAdmin.getinstance(mContext);
        this.mAdapterHelper = new FacFunctionAdapterHelper(mContext, this);
        wirteLogThread();
        if (FacPropertyDemo.HIFAC_ATSC30_SUPPORT) {
            return;
        }
        this.mHisPlayApiImpl = HisPlayApiImpl.getInstance();
        this.mHisCommonApiImpl = HisCommonApiImpl.getInstance();
        this.mHisChannelListApiImpl = HisChannelListApiImpl.getInstance();
    }

    private void initSharedPreference() {
        try {
            this.mSharedPreferences = mContext.getSharedPreferences("FacShareP", 4);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static synchronized FacFunction_Adapter getInstance(Context context) {
        if (mFacFunction_Adapter == null) {
            mContext = context.getApplicationContext();
            mFacFunction_Adapter = new FacFunction_Adapter();
        }
        return mFacFunction_Adapter;
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean adcCalibration() {
        this.adc_rdrv_value[getCurrentADCColorSpaceGroup().ordinal()] = 123;
        this.adc_gdrv_value[getCurrentADCColorSpaceGroup().ordinal()] = 124;
        this.adc_bdrv_value[getCurrentADCColorSpaceGroup().ordinal()] = 125;
        this.adc_rcut_value[getCurrentADCColorSpaceGroup().ordinal()] = 90;
        this.adc_gcut_value[getCurrentADCColorSpaceGroup().ordinal()] = 91;
        this.adc_bcut_value[getCurrentADCColorSpaceGroup().ordinal()] = 92;
        return true;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void addATVPresetChannel(ATVPresetChannel aTVPresetChannel) {
        if (aTVPresetChannel != null) {
            this.atvChInfo.add(aTVPresetChannel);
            System.out.println("****add ATV ChannelNum" + aTVPresetChannel.getChannelNum());
            System.out.println("****add ATV ChannelName" + aTVPresetChannel.getChannelName());
            System.out.println("****add ATV Frequency" + aTVPresetChannel.getFrequency());
            System.out.println("****add ATV ColorSys" + aTVPresetChannel.getColorSys());
            System.out.println("****add ATV AudioSys" + aTVPresetChannel.getAudioSys());
        }
    }

    @Override // com.hisense.tvcm.FacFunction
    public void addDTVPresetChannel(DTVPresetChannel dTVPresetChannel) {
        if (dTVPresetChannel != null) {
            this.dtvChInfo.add(dTVPresetChannel);
            System.out.println("****DTV ChannelNum" + dTVPresetChannel.getChannelNum());
            System.out.println("****DTV ChannelName" + dTVPresetChannel.getChannelName());
            System.out.println("****DTV Frequency" + dTVPresetChannel.getFrequency());
            System.out.println("****DTV BW" + dTVPresetChannel.getBindWidth());
            System.out.println("****DTV NetworkId" + dTVPresetChannel.getNetworkId());
            System.out.println("****DTV OrgNetworkId" + dTVPresetChannel.getOrgNetworkId());
            System.out.println("****DTV ServiceId" + dTVPresetChannel.getServiceId());
            System.out.println("****DTV SymRate" + dTVPresetChannel.getSymRate());
            System.out.println("****DTV TsId" + dTVPresetChannel.getTsId());
            System.out.println("****DTV Modulation" + dTVPresetChannel.getMod());
        }
    }

    public void changeChannelForChannelUPAndDown(int i) {
        Log.i("Fac_FacFunction_Adapter", "channelIndex = " + i);
        if (getTunerMode() == 0) {
            int size = this.list_air.size();
            Log.i("Fac_FacFunction_Adapter", "air_size = " + size);
            if (size > 0) {
                int i2 = size - 1;
                if (i > i2) {
                    i = 0;
                }
                if (i >= 0) {
                    i2 = i;
                }
                ChannelInfoBase channelInfoBase = this.list_air.get(i2);
                this.currentchannel = channelInfoBase;
                this.mHisPlayApiImpl.playChannelByChannelKey(0, channelInfoBase.getChannelKey(), 0);
                setSourceStatusToAudioPicture(FacPropertyDemo.SourceEnum.TV);
                return;
            }
            Log.i("Fac_FacFunction_Adapter", "air has not channel");
            return;
        }
        int size2 = this.list_cable.size();
        Log.i("Fac_FacFunction_Adapter", "cable size = " + size2);
        if (size2 > 0) {
            int i3 = size2 - 1;
            if (i > i3) {
                i = 0;
            }
            if (i >= 0) {
                i3 = i;
            }
            ChannelInfoBase channelInfoBase2 = this.list_cable.get(i3);
            this.currentchannel = channelInfoBase2;
            this.mHisPlayApiImpl.playChannelByChannelKey(0, channelInfoBase2.getChannelKey(), 0);
            setSourceStatusToAudioPicture(FacPropertyDemo.SourceEnum.TV);
            return;
        }
        Log.i("Fac_FacFunction_Adapter", "cable has not channel");
    }

    public void changeChannelForChannelUPAndDownATSC(int i) {
        Log.i("Fac_FacFunction_Adapter", "channelIndex = " + i);
        if (getTunerMode() == 0) {
            int size = this.list_air_ATSC.size();
            Log.i("Fac_FacFunction_Adapter", "list_air_ATSC = " + size);
            if (size > 0) {
                int i2 = size - 1;
                if (i > i2) {
                    i = 0;
                }
                if (i >= 0) {
                    i2 = i;
                }
                this.currentchannelInfo = this.list_air_ATSC.get(i2);
                Log.i("Fac_FacFunction_Adapter", "startplay channel number: " + this.currentchannelInfo.getChannelNumber());
                this.playResult = this.mPlay.startPlay(this.currentchannelInfo.getServiceUUID());
                setSourceStatusToAudioPicture(FacPropertyDemo.SourceEnum.TV);
                Log.i("Fac_FacFunction_Adapter", "playResult = " + this.playResult);
                return;
            }
            Log.i("Fac_FacFunction_Adapter", "air has not channel");
            return;
        }
        int size2 = this.list_cable_ATSC.size();
        Log.i("Fac_FacFunction_Adapter", "list_cable_ATSC size = " + size2);
        if (size2 > 0) {
            int i3 = size2 - 1;
            if (i > i3) {
                i = 0;
            }
            if (i >= 0) {
                i3 = i;
            }
            this.currentchannelInfo = this.list_cable_ATSC.get(i3);
            Log.i("Fac_FacFunction_Adapter", "startplay channel number: " + this.currentchannelInfo.getChannelNumber());
            this.playResult = this.mPlay.startPlay(this.currentchannelInfo.getServiceUUID());
            setSourceStatusToAudioPicture(FacPropertyDemo.SourceEnum.TV);
            Log.i("Fac_FacFunction_Adapter", "playResult = " + this.playResult);
            return;
        }
        Log.i("Fac_FacFunction_Adapter", "cable has not channel");
    }

    @Override // com.hisense.tvcm.FacFunction
    public void changeChannel(int i, int i2) {
        Log.i("Fac_FacFunction_Adapter", "change channel = " + i + " tunerMode=" + i2);
        setTunerModeOnly(i2);
        ChannelInfoBase channelInfoBaseFindBestMatchChannel = findBestMatchChannel(i, i2);
        if (channelInfoBaseFindBestMatchChannel == null) {
            Log.i("Fac_FacFunction_Adapter", " targetChannel is not exist!!! ");
            return;
        }
        Log.i("Fac_FacFunction_Adapter", "change channel targetChannel = " + channelInfoBaseFindBestMatchChannel.getChannelNum());
        this.mHisPlayApiImpl.playChannelByChannelKey(0, channelInfoBaseFindBestMatchChannel.getChannelKey(), 0);
        this.currentchannel = channelInfoBaseFindBestMatchChannel;
        setSourceStatusToAudioPicture(FacPropertyDemo.SourceEnum.TV);
    }

    public ChannelInfoBase findBestMatchChannel(int i, int i2) {
        if (i2 == 0) {
            List<ChannelInfoBase> list = this.list_air;
            if (list == null) {
                return null;
            }
            for (ChannelInfoBase channelInfoBase : list) {
                String channelNum = channelInfoBase.getChannelNum();
                Log.i("Fac_FacFunction_Adapter", "air tvChannelInfoBase.channelNum = " + channelNum);
                String[] strArrSplit = channelNum.split("-|\\.");
                if (strArrSplit.length == 1) {
                    Log.i("Fac_FacFunction_Adapter", "air channelNum = " + strArrSplit[0]);
                    if (channelNum.equals(Integer.toString(i))) {
                        Log.i("Fac_FacFunction_Adapter", "find best matched air ChannelNumber = " + channelNum);
                        return channelInfoBase;
                    }
                } else {
                    String str = strArrSplit[0];
                    String str2 = strArrSplit[1];
                    Log.i("Fac_FacFunction_Adapter", "air channel infobase mainChnum = " + str + ", minorChnum =" + str2);
                    if (str.contains(i + "")) {
                        Log.i("Fac_FacFunction_Adapter", "air mainChnum = " + str);
                        return channelInfoBase;
                    }
                    if (str2.contains(i + "")) {
                        Log.i("Fac_FacFunction_Adapter", "air minorChnum = " + str2);
                        return channelInfoBase;
                    }
                }
            }
        } else {
            List<ChannelInfoBase> list2 = this.list_cable;
            if (list2 != null) {
                for (ChannelInfoBase channelInfoBase2 : list2) {
                    String channelNum2 = channelInfoBase2.getChannelNum();
                    Log.i("Fac_FacFunction_Adapter", "cable tvChannelInfoBase.channelNum = " + channelNum2);
                    String[] strArrSplit2 = channelNum2.split("-|\\.");
                    if (strArrSplit2.length == 1) {
                        Log.i("Fac_FacFunction_Adapter", "cable channelNum = " + strArrSplit2[0]);
                        if (channelNum2.equals(Integer.toString(i))) {
                            Log.i("Fac_FacFunction_Adapter", "find best matched cable ChannelNumber = " + channelNum2);
                            return channelInfoBase2;
                        }
                    } else {
                        String str3 = strArrSplit2[0];
                        String str4 = strArrSplit2[1];
                        Log.i("Fac_FacFunction_Adapter", "cable channel infobase mainChnum = " + str3 + ", minorChnum =" + str4);
                        if (str3.contains(i + "")) {
                            Log.i("Fac_FacFunction_Adapter", "cable mainChnum = " + str3);
                            return channelInfoBase2;
                        }
                        if (str4.contains(i + "")) {
                            Log.i("Fac_FacFunction_Adapter", "cable minorChnum = " + str4);
                            return channelInfoBase2;
                        }
                    }
                }
            }
        }
        return null;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void changeChannelATSC(int i, int i2) {
        List<com.hisense.tvmanager.atsc.ChannelInfoBase> list;
        Log.d("Fac_FacFunction_Adapter", "changeChannelATSC chnum = " + i + " ; tunerMode = " + i2);
        if (i2 == 0) {
            Log.i("Fac_FacFunction_Adapter", "list_air_ATSC size " + this.list_air_ATSC.size());
            list = this.list_air_ATSC;
            Log.i("Fac_FacFunction_Adapter", "channellist is list_air_ATSC");
        } else {
            list = this.list_cable_ATSC;
            Log.i("Fac_FacFunction_Adapter", "channellist is list_cable_ATSC");
        }
        com.hisense.tvmanager.atsc.ChannelInfoBase channelInfoBaseFindBestMatchChannel = findBestMatchChannel(i, i2, list);
        if (channelInfoBaseFindBestMatchChannel == null) {
            Log.i("Fac_FacFunction_Adapter", " targetChannel is not exist!!! ");
            return;
        }
        Log.d("Fac_FacFunction_Adapter", "find match ChannelNumber = " + channelInfoBaseFindBestMatchChannel.getChannelNumber());
        this.playResult = this.mPlay.startPlay(channelInfoBaseFindBestMatchChannel.getServiceUUID());
        Log.d("Fac_FacFunction_Adapter", "playResult = " + this.playResult);
        this.currentchannelInfo = channelInfoBaseFindBestMatchChannel;
        setSourceStatusToAudioPicture(FacPropertyDemo.SourceEnum.TV);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void changePreciseChannelForATSC(int i, int i2) {
        List<com.hisense.tvmanager.atsc.ChannelInfoBase> list;
        Log.i("Fac_FacFunction_Adapter", "changeChannelForId majorNo is " + i);
        Log.i("Fac_FacFunction_Adapter", "changeChannelForId minorNo is " + i2);
        if (getTunerMode() == 0) {
            list = this.list_air_ATSC;
        } else {
            list = this.list_cable_ATSC;
        }
        if (list != null && i2 != 0) {
            for (com.hisense.tvmanager.atsc.ChannelInfoBase channelInfoBase : list) {
                String channelNumber = channelInfoBase.getChannelNumber();
                Log.i("Fac_FacFunction_Adapter", "tvChannelInfo.channelNum [i] = " + channelNumber);
                String[] strArrSplit = channelNumber.split("-|\\.");
                if (strArrSplit.length != 1) {
                    String str = strArrSplit[0];
                    String str2 = strArrSplit[1];
                    Log.i("Fac_FacFunction_Adapter", "channel mainChnum = " + str + ", minorChnum =" + str2);
                    if (Integer.parseInt(str) == i && Integer.parseInt(str2) == i2) {
                        Log.i("Fac_FacFunction_Adapter", "find target channelinfo = " + channelInfoBase);
                        this.playResult = this.mPlay.startPlay(channelInfoBase.getServiceUUID());
                        Log.d("Fac_FacFunction_Adapter", "playResult = " + this.playResult);
                        this.currentchannelInfo = channelInfoBase;
                        setSourceStatusToAudioPicture(FacPropertyDemo.SourceEnum.TV);
                        return;
                    }
                }
            }
            return;
        }
        Log.i("Fac_FacFunction_Adapter", "Channel list is null !!");
    }

    public com.hisense.tvmanager.atsc.ChannelInfoBase findBestMatchChannel(int i, int i2, List<com.hisense.tvmanager.atsc.ChannelInfoBase> list) {
        if (list != null) {
            for (com.hisense.tvmanager.atsc.ChannelInfoBase channelInfoBase : list) {
                String channelNumber = channelInfoBase.getChannelNumber();
                Log.i("Fac_FacFunction_Adapter", "air tvChannelInfoBase.channelNum = " + channelNumber);
                String[] strArrSplit = channelNumber.split("-|\\.");
                if (strArrSplit.length == 1) {
                    Log.i("Fac_FacFunction_Adapter", "channelNum = " + strArrSplit[0]);
                    if (channelNumber.equals(Integer.toString(i))) {
                        Log.i("Fac_FacFunction_Adapter", "find best matched ChannelNumber = " + channelNumber);
                        return channelInfoBase;
                    }
                    if (channelNumber.contains(Integer.toString(i))) {
                        Log.i("Fac_FacFunction_Adapter", "find best matched ChannelNumber = " + channelNumber);
                        return channelInfoBase;
                    }
                } else {
                    String str = strArrSplit[0];
                    String str2 = strArrSplit[1];
                    Log.i("Fac_FacFunction_Adapter", "channel mainChnum = " + str + ", minorChnum =" + str2);
                    if (str.contains(i + "")) {
                        Log.i("Fac_FacFunction_Adapter", "mainChnum = " + str);
                        return channelInfoBase;
                    }
                    if (str2.contains(i + "")) {
                        Log.i("Fac_FacFunction_Adapter", "minorChnum = " + str2);
                        return channelInfoBase;
                    }
                }
            }
        }
        return null;
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean isStoreMode() {
        int i = Settings.Global.getInt(mContext.getContentResolver(), "use_mode", -1);
        Log.i("Fac_FacFunction_Adapter", " isStoreMode result=" + i);
        return i == 1 || i == 2;
    }

    public void facAdapterModifyGoogleServiceInterface(boolean z) {
        Log.d("Fac_FacFunction_Adapter", "facAdapterModifyGoogleServiceInterface value = " + z);
        if (z) {
            mContext.getPackageManager().setApplicationEnabledSetting("com.google.android.gms", 1, 0);
            mContext.getPackageManager().setApplicationEnabledSetting("com.android.vending", 1, 0);
        } else {
            mContext.getPackageManager().setApplicationEnabledSetting("com.google.android.gms", 3, 0);
            mContext.getPackageManager().setApplicationEnabledSetting("com.android.vending", 3, 0);
        }
    }

    @Override // com.hisense.tvcm.FacFunction
    public void executeClearUnprotectly() {
        Log.i("Fac_FacFunction_Adapter", "begain executeClearUnprotectly");
        new Thread() { // from class: com.hisense.tvcm.FacFunction_Adapter.2
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                FacFunction_Adapter.this.setLogToFile("execute clear all");
                Looper.prepare();
                if (FacPropertyDemo.HIFAC_MIC_SUPPORT) {
                    FacFunction_Adapter.this.setZDMicResult(0);
                    FacFunction_Adapter.this.setMicTestResult(0);
                }
                FacFunction_Adapter.this.cleanchannel();
                SystemProperties.set("sys.factory.do_clear", "1");
                if (FacPropertyDemo.HIFAC_MARKET_JP) {
                    FacFunction_Adapter.this.mTvsFunc.cleanTvsProtect();
                }
                FacFunction_Adapter.this.mHisenseTvAPI.doProtectedClear();
                FacFunction_Adapter.this.setFactoryProtectFlag(1);
                Log.i("Fac_FacFunction_Adapter", "factory protect flag = " + FacFunction_Adapter.this.mHisenseTvAPI.getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_PROTECT_FLAG));
                Log.i("Fac_FacFunction_Adapter", "wait for 1000ms");
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                FacFunction_Adapter.this.setFactoryStatus(1);
                FacFunction_Adapter.this.setToFac(FacPropertyDemo.ToFacEnum.TOFAC_M);
                FacFunction_Adapter.this.mHisenseTvAPI.setUartCtrlState(1);
                FacFunction_Adapter.this.mHisenseTvAPI.doMasterClear();
                Log.i("Fac_FacFunction_Adapter", "begain call domasterclear in executeClearUnprotectly");
                Looper.loop();
            }
        }.start();
        Log.i("Fac_FacFunction_Adapter", "finish executeClearUnprotectly");
    }

    @Override // com.hisense.tvcm.FacFunction
    public int setFactoryProtectFlag(int i) {
        return this.mHisenseTvAPI.setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_PROTECT_FLAG, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getFactoryProtectFlag() {
        return this.mHisenseTvAPI.getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_PROTECT_FLAG);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void executeFactoryOk(final IMtkFunCallback iMtkFunCallback) {
        facAdapterModifyGoogleServiceInterface(true);
        this.mIsFactoryOkFailed = false;
        new Thread() { // from class: com.hisense.tvcm.FacFunction_Adapter.3
            /* JADX WARN: Code duplicated, block: B:36:0x01ea  */
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                boolean z;
                synchronized (FacFunction_Adapter.this.FactoryOkRetrySync) {
                    FacFunction_Adapter.this.setLogToFile("execute ok start!");
                    SaveResultToFile saveResultToFile = new SaveResultToFile();
                    saveResultToFile.initFileWriter(FacFunction_Adapter.mContext, "factoryokresult.txt");
                    Log.i("Fac_FacFunction_Adapter", "begin executeFactoryOk");
                    FacFunction_Adapter.this.bFactoryOkApi_ActionDone = false;
                    iMtkFunCallback.notifyProgress(0);
                    saveResultToFile.saveResult("okProgerss", "0");
                    SystemProperties.set("bluetooth_remote_connected", "0");
                    SystemProperties.set("bluetooth_remote_connect_state", "");
                    SystemProperties.set("sys.vt.factory.do_ok", "1");
                    SystemProperties.set("persist.sys.pm.warmboot", "0");
                    SystemProperties.set("ctl.start", "cleandata");
                    PackageManager packageManager = FacFunction_Adapter.mContext.getPackageManager();
                    packageManager.setApplicationEnabledSetting("com.google.android.tungsten.setupwraith", 1, 1);
                    packageManager.setComponentEnabledSetting(new ComponentName("com.google.android.tungsten.setupwraith", "com.google.android.tungsten.setupwraith.MainActivity"), 1, 1);
                    packageManager.flushPackageRestrictionsAsUser(FacFunction_Adapter.mContext.getUserId());
                    Log.d("Fac_FacFunction_Adapter", "enable FTE");
                    boolean agingMode = FacFunction_Adapter.this.mHisenseTvAPI.setAgingMode(false);
                    boolean factoryMode = FacFunction_Adapter.this.mHisenseTvAPI.setFactoryMode(false);
                    boolean toFacMode = FacFunction_Adapter.this.mHisenseTvAPI.setToFacMode(FacPropertyDemo.ToFacEnum.TOFAC_U.ordinal());
                    FacFunction_Adapter.this.mHisenseTvAPI.setTvRunTime(MtkTvConfigTypeBase.CFG_FACTORY_RUN_TIME, 0);
                    saveResultToFile.saveResult("setAgingMode", agingMode);
                    saveResultToFile.saveResult("setFactoryMode", factoryMode);
                    saveResultToFile.saveResult("setToFacMode", toFacMode);
                    FacFunction_Adapter.this.setAuthMode(0);
                    Settings.Global.putInt(FacFunction_Adapter.mContext.getContentResolver(), "priority_speaker", 0);
                    if (FacPropertyDemo.HIFAC_IS_LASER4K) {
                        FacFunction_Adapter.this.setFirstBootState(0);
                        FacFunction_Adapter.this.setFirstPowerOnTime(0L);
                        FacFunction_Adapter.this.setStartOnCount(0L);
                    }
                    if (FacFunction_Adapter.this.isHaveTuner()) {
                        FacFunction_Adapter.this.switchSource(FacPropertyDemo.SourceEnum.TV);
                    } else {
                        FacFunction_Adapter.this.switchSource(FacPropertyDemo.SourceEnum.HDMI1);
                    }
                    iMtkFunCallback.notifyProgress(5);
                    saveResultToFile.saveResult("okProgerss", MtkTvRatingConvert2Goo.RATING_STR_5);
                    if (!FacPropertyDemo.HIFAC_MARKET_JP) {
                        if (FacPropertyDemo.HIFAC_ATSC30_SUPPORT) {
                            FacFunction_Adapter.this.mChannelList.cleanChannelListByTunerMode(0);
                            FacFunction_Adapter.this.mChannelList.cleanChannelListByTunerMode(1);
                            Log.d("Fac_FacFunction_Adapter", "finish clean ATSC ChannelList.");
                        } else {
                            int iCleanChannelsByTunerMode = FacFunction_Adapter.this.mHisChannelListApiImpl.cleanChannelsByTunerMode(0);
                            Log.d("Fac_FacFunction_Adapter", "cleanChannelList(1), return value = " + iCleanChannelsByTunerMode + "\n");
                            int iCleanChannelsByTunerMode2 = FacFunction_Adapter.this.mHisChannelListApiImpl.cleanChannelsByTunerMode(1);
                            Log.d("Fac_FacFunction_Adapter", "cleanChannelList(2), return value = " + iCleanChannelsByTunerMode2 + "\n");
                            FacFunction_Adapter.this.mHisChannelListApiImpl.flushChannelList();
                            if (iCleanChannelsByTunerMode == 1) {
                                saveResultToFile.saveResult("cleanAirChannelList", true);
                            }
                            if (iCleanChannelsByTunerMode2 == 1) {
                                saveResultToFile.saveResult("cleanCableChannelList", true);
                            }
                        }
                    }
                    iMtkFunCallback.notifyProgress(8);
                    saveResultToFile.saveResult("okProgerss", "8");
                    int defaultAudioVolume = FacFunction_Adapter.this.mHisenseTvAPI.getDefaultAudioVolume();
                    Log.i("Fac_FacFunction_Adapter", "defaultVolume = " + defaultAudioVolume);
                    if (FacPropertyDemo.HIFAC_MARKET.equals(FacPropertyDemo.MarketType.EM)) {
                        FacFunction_Adapter.this.setVolumeWithoutUI(defaultAudioVolume);
                    } else if (FacPropertyDemo.HIFAC_MARKET_SA) {
                        FacFunction_Adapter.this.setVolumeWithoutUI(10);
                    } else if (FacPropertyDemo.HIFAC_MARKET_JP) {
                        FacFunction_Adapter.this.setVolumeWithoutUI(30);
                    } else {
                        FacFunction_Adapter.this.setVolumeWithoutUI(20);
                    }
                    if (FacPropertyDemo.HIFAC_MARKET.equals(FacPropertyDemo.MarketType.EM)) {
                        if (FacFunction_Adapter.this.getVolume() == defaultAudioVolume) {
                            z = true;
                        } else {
                            z = false;
                        }
                    } else if (FacFunction_Adapter.this.getVolume() == 20) {
                        z = true;
                    } else {
                        z = false;
                    }
                    saveResultToFile.saveResult("setVolume", z);
                    iMtkFunCallback.notifyProgress(10);
                    saveResultToFile.saveResult("okProgerss", MtkTvRatingConvert2Goo.RATING_STR_10);
                    if (FacPropertyDemo.HIFAC_MARKET_TW || FacFunction_Adapter.this.getCountryName().equalsIgnoreCase(MtkTvConfigTypeBase.S3166_CFG_COUNT_MYS)) {
                        FacFunction_Adapter.this.writeBooleanSetting("hdmi_control_enabled", false);
                    }
                    if (FacPropertyDemo.HIFAC_MARKET_JP) {
                        FacFunction_Adapter.this.mTvsFunc.setTvsFactoryOk();
                    }
                    boolean zDoFactoryOKReset = FacFunction_Adapter.this.mHisenseTvAPI.doFactoryOKReset(false, new IFactoryCleanAppsListener(this, saveResultToFile) { // from class: com.hisense.tvcm.FacFunction_Adapter.3.1
                    });
                    saveResultToFile.saveResult("doFactoryOKReset", zDoFactoryOKReset);
                    if (!zDoFactoryOKReset) {
                        Log.i("Fac_FacFunction_Adapter", "doFactoryOKReset fail !!!");
                        FacFunction_Adapter.this.setFactoryOkFailed(true);
                    }
                    FacFunction_Adapter.this.resetPowerOnMode();
                    if (!FacFunction_Adapter.this.mIsFactoryOkFailed) {
                        if (SystemProperties.getInt("sys.vt.his.uifac.not_close_uart", 0) == 1969) {
                            FacFunction_Adapter.this.mHisenseTvAPI.setUartCtrlState(1);
                        } else {
                            FacFunction_Adapter.this.mHisenseTvAPI.setUartCtrlState(0);
                        }
                        if (FacPropertyDemo.HIFAC_MIC_SUPPORT) {
                            FacFunction_Adapter.this.setMicLedControl(2);
                        }
                        iMtkFunCallback.notifyProgress(95);
                        saveResultToFile.saveResult("okProgerss", "95");
                        Process processExec = null;
                        try {
                            processExec = Runtime.getRuntime().exec("sync");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            processExec.waitFor();
                        } catch (InterruptedException e2) {
                            e2.printStackTrace();
                        }
                        iMtkFunCallback.notifyProgress(96);
                        saveResultToFile.saveResult("okProgerss", "96");
                        FacFunction_Adapter.this.mMtkTvFactoryService.factoryWriteKeyFinish();
                        saveResultToFile.saveResult("setVolume", true);
                        saveResultToFile.saveResult("setToFac", true);
                        saveResultToFile.saveResult("checkpar", true);
                        if (FacPropertyDemo.HIFAC_TEE_ENVIRON_SUPPORT) {
                            saveResultToFile.saveResult("KeyWriteProtectEnabled", true);
                        }
                        FacFunction_Adapter.this.setLedControl(3);
                        iMtkFunCallback.notifyProgress(97);
                        saveResultToFile.saveResult("okProgerss", "97");
                        iMtkFunCallback.notifyProgress(100);
                        FacFunction_Adapter.this.setLogToFile("execute ok 100!");
                        saveResultToFile.saveResult("okProgerss", "100");
                        iMtkFunCallback.notifyProgress(101);
                        FacFunction_Adapter.this.setLogToFile("execute ok 101!");
                        saveResultToFile.saveResult("okProgerss", "101");
                        saveResultToFile.closeWriter();
                    }
                }
            }
        }.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setFactoryOkFailed(boolean z) {
        this.mIsFactoryOkFailed = z;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void execWhenHisFacEnding() {
        System.out.print("HisFac end!!!!!!!!!!");
    }

    @Override // com.hisense.tvcm.FacFunction
    public FacPropertyDemo.DimensionGroup get2Dor3D() {
        System.out.println("get 2Dor3D = " + this.mode_2dor3d_value.toString());
        return this.mode_2dor3d_value;
    }

    @Override // com.hisense.tvcm.FacFunction
    public FacPropertyDemo.ThreeDimensionMode get3DMode() {
        return this.mode_3d;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int get3Dto2D() {
        return this.mode_3dto2d;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getADCBlueGain() {
        return this.adc_bdrv_value[getCurrentADCColorSpaceGroup().ordinal()];
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getADCBlueOffset() {
        return this.adc_bcut_value[getCurrentADCColorSpaceGroup().ordinal()];
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getADCGreenGain() {
        return this.adc_gdrv_value[getCurrentADCColorSpaceGroup().ordinal()];
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getADCGreenOffset() {
        return this.adc_gcut_value[getCurrentADCColorSpaceGroup().ordinal()];
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getADCRedGain() {
        return this.adc_rdrv_value[getCurrentADCColorSpaceGroup().ordinal()];
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getADCRedOffset() {
        return this.adc_rcut_value[getCurrentADCColorSpaceGroup().ordinal()];
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getAgingFlag() {
        Log.i("Fac_FacFunction_Adapter", "get aging flag = " + this.mHisenseTvAPI.isAgingMode());
        return this.mHisenseTvAPI.isAgingMode() ? 1 : 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public FacPropertyDemo.AudioMode getAudioMode() {
        System.out.println("get audio mode = " + this.audiomode_value.toString());
        int[] allAudioModeValue = this.mHisenseTvAPI.getAllAudioModeValue(MtkTvConfigTypeBase.CFG_FACTORY_AUD_SOUND_MODE, 6);
        if (allAudioModeValue != null && FacPropertyDemo.AudioMode.values().length > allAudioModeValue[0]) {
            return FacPropertyDemo.AudioMode.values()[allAudioModeValue[0]];
        }
        return FacPropertyDemo.AudioMode.STANDARD;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getBalance() {
        return this.mHisenseTvAPI.getBalance();
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getBCUT() {
        int[] factoryWhiteBalanceOffsetBlue = this.mHisenseTvAPI.getFactoryWhiteBalanceOffsetBlue();
        if (factoryWhiteBalanceOffsetBlue == null || factoryWhiteBalanceOffsetBlue.length <= 2) {
            return 0;
        }
        Log.i("Fac_FacFunction_Adapter", "get rcut = " + factoryWhiteBalanceOffsetBlue[2]);
        return factoryWhiteBalanceOffsetBlue[2];
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getBDRV() {
        int[] factoryWhiteBalanceGainBlue = this.mHisenseTvAPI.getFactoryWhiteBalanceGainBlue();
        if (factoryWhiteBalanceGainBlue == null || factoryWhiteBalanceGainBlue.length <= 2) {
            return 0;
        }
        Log.i("Fac_FacFunction_Adapter", "get B gain = " + factoryWhiteBalanceGainBlue[2]);
        return factoryWhiteBalanceGainBlue[2];
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getBDRVOffset() {
        int[] whiteBalanceValue = this.mHisenseTvAPI.getWhiteBalanceValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_GAIN, 3);
        if (whiteBalanceValue == null || whiteBalanceValue.length <= 2) {
            return 0;
        }
        return whiteBalanceValue[2];
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getBrightness() {
        int[] brightness = this.mHisenseTvAPI.getBrightness();
        if (brightness == null || brightness.length <= 1) {
            return 0;
        }
        Log.i("Fac_FacFunction_Adapter", "getBrightness = " + brightness[1]);
        return brightness[1];
    }

    public int getChannelIndex() {
        int tunerMode = getTunerMode();
        String channelKey = this.currentchannel.getChannelKey();
        if (channelKey == null || channelKey.equals("")) {
            channelKey = this.mHisPlayApiImpl.getCurrentChannelKey();
            this.currentchannel = this.mHisChannelListApiImpl.getChannelInfoByChannelKey(channelKey);
        }
        Log.i("Fac_FacFunction_Adapter", "getChannelIndex: getCurrentChannelKey.channelKey= " + channelKey);
        int i = 0;
        if (tunerMode == 0) {
            Log.i("Fac_FacFunction_Adapter", "list air size = " + this.list_air.size());
            while (i < this.list_air.size()) {
                Log.i("Fac_FacFunction_Adapter", "list_air i get id = " + this.list_air.get(i).getChannelKey() + " channnel number = " + this.list_air.get(i).getChannelNum() + "  chlst get id = " + this.currentchannel.getChannelKey());
                if (this.list_air.get(i).getChannelKey().equals(channelKey)) {
                    Log.i("Fac_FacFunction_Adapter", "i = " + i);
                    return i;
                }
                i++;
            }
            return 1;
        }
        Log.i("Fac_FacFunction_Adapter", "list cable size = " + this.list_cable.size());
        while (i < this.list_cable.size()) {
            Log.i("Fac_FacFunction_Adapter", "list_cable i get id = " + this.list_cable.get(i).getChannelKey() + " channnel number = " + this.list_cable.get(i).getChannelNum() + "  chlst get id = " + this.currentchannel.getChannelKey());
            if (this.list_cable.get(i).getChannelKey().equals(channelKey)) {
                Log.i("Fac_FacFunction_Adapter", "i = " + i);
                return i;
            }
            i++;
        }
        return 1;
    }

    public int getChannelIndexForAtsc() {
        List<com.hisense.tvmanager.atsc.ChannelInfoBase> list;
        int tunerMode = getTunerMode();
        String serviceUUID = this.currentchannelInfo.getServiceUUID();
        if (serviceUUID == null || serviceUUID.equals("")) {
            serviceUUID = this.mPlay.getCurrentPlayChannel();
            this.currentchannelInfo = this.mChannelList.getChannelInfoByUUID(serviceUUID);
        }
        Log.i("Fac_FacFunction_Adapter", "getChannelIndex: getCurrentChannelKey.channelKey= " + serviceUUID);
        if (tunerMode == 0) {
            list = this.list_air_ATSC;
            Log.i("Fac_FacFunction_Adapter", "currentchannellist is list_air_ATSC");
        } else {
            list = this.list_cable_ATSC;
            Log.i("Fac_FacFunction_Adapter", "currentchannellist is list_cable_ATSC");
        }
        Log.i("Fac_FacFunction_Adapter", "channellist size = " + list.size() + ";currentchannelInfo uuid = " + serviceUUID);
        for (int i = 0; i < list.size(); i++) {
            Log.i("Fac_FacFunction_Adapter", "channellist i uuid = " + list.get(i).getServiceUUID() + ";channnel number = " + list.get(i).getChannelNumber());
            if (list.get(i).getServiceUUID().equals(serviceUUID)) {
                Log.i("Fac_FacFunction_Adapter", "i = " + i);
                return i;
            }
        }
        return 1;
    }

    @Override // com.hisense.tvcm.FacFunction
    public String getCurrentChannelNumberString() {
        String channelNumber;
        if (FacPropertyDemo.HIFAC_ATSC30_SUPPORT) {
            channelNumber = this.mChannelList.getChannelInfoByUUID(this.mPlay.getCurrentPlayChannel()).getChannelNumber();
        } else {
            ChannelInfoBase channelInfoByChannelKey = this.mHisChannelListApiImpl.getChannelInfoByChannelKey(this.mHisPlayApiImpl.getCurrentChannelKey());
            this.currentchannel = channelInfoByChannelKey;
            if (channelInfoByChannelKey != null) {
                String channelNum = channelInfoByChannelKey.getChannelNum();
                Log.i("Fac_FacFunction_Adapter", "getChannelDisPlayName = " + this.currentchannel.getChannelNum());
                channelNumber = channelNum;
            } else {
                channelNumber = "";
            }
        }
        Log.i("Fac_FacFunction_Adapter", "getChannelDisPlayName = " + channelNumber);
        return channelNumber == null ? "" : channelNumber;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getColor() {
        int[] color = this.mHisenseTvAPI.getColor();
        if (color == null || color.length <= 3) {
            return 0;
        }
        return color[3];
    }

    @Override // com.hisense.tvcm.FacFunction
    public FacPropertyDemo.ColorTemperature getColorTemperature() {
        int colorTemperature = this.mHisenseTvAPI.getColorTemperature();
        Log.i("Fac_FacFunction_Adapter", "colortmp get mtkvalue = " + colorTemperature);
        return FacPropertyDemo.ColorTemperature.values()[ApVsTILmap.TilToAp(3, colorTemperature)];
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getContrast() {
        int[] contrast = this.mHisenseTvAPI.getContrast();
        if (contrast == null || contrast.length <= 2) {
            return 0;
        }
        Log.i("Fac_FacFunction_Adapter", "getContrast = " + contrast[2]);
        return contrast[2];
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getConvergence() {
        return this.mode_convergence;
    }

    @Override // com.hisense.tvcm.FacFunction
    public FacPropertyDemo.OVERSCAN_TIMING getCurrentOverScanTiming() {
        return FacPropertyDemo.OVERSCAN_TIMING.EN_FACTORY_OverScan_AVNTSC;
    }

    @Override // com.hisense.tvcm.FacFunction
    public FacPropertyDemo.SourceEnum getCurrentSource() {
        if (this.mMtkTvInputSource == null) {
            this.mMtkTvInputSource = MtkTvInputSource.getInstance();
        }
        String currentInputSourceName = this.mMtkTvInputSource.getCurrentInputSourceName("main");
        Log.i("Fac_FacFunction_Adapter", "mtkSourcemName = " + currentInputSourceName);
        if (FacPropertyDemo.HIFAC_MARKET_JP) {
            currentInputSourceName = this.mMtkTvInputSource.getCurrentInputSourceName();
            Log.i("Fac_FacFunction_Adapter", "mtkSourcemName1 = " + currentInputSourceName);
        }
        if (currentInputSourceName == null || currentInputSourceName.isEmpty()) {
            if (FacPropertyDemo.HIFAC_MARKET_TW) {
                return FacPropertyDemo.SourceEnum.HDMI1;
            }
            return FacPropertyDemo.SourceEnum.TV;
        }
        if (currentInputSourceName.equalsIgnoreCase("TV")) {
            if (FacPropertyDemo.HIFAC_MARKET_TW) {
                return FacPropertyDemo.SourceEnum.HDMI1;
            }
            return FacPropertyDemo.SourceEnum.TV;
        }
        if (currentInputSourceName.equalsIgnoreCase("Composite") || currentInputSourceName.equalsIgnoreCase(MtkTvInputSourceBase.INPUT_TYPE_COMPONENT)) {
            return FacPropertyDemo.SourceEnum.AV;
        }
        if (currentInputSourceName.equalsIgnoreCase("HDMI 1")) {
            return FacPropertyDemo.SourceEnum.HDMI1;
        }
        if (currentInputSourceName.equalsIgnoreCase("HDMI 2")) {
            return FacPropertyDemo.SourceEnum.HDMI2;
        }
        if (currentInputSourceName.equalsIgnoreCase("HDMI 3")) {
            return FacPropertyDemo.SourceEnum.HDMI3;
        }
        if (currentInputSourceName.equalsIgnoreCase("HDMI 4")) {
            return FacPropertyDemo.SourceEnum.HDMI4;
        }
        return FacPropertyDemo.SourceEnum.TV;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getEqualizer10KHz() {
        int[] allAudioModeValue = this.mHisenseTvAPI.getAllAudioModeValue(MtkTvConfigTypeBase.CFG_FACTORY_AUD_EQ_BAND_5, 1);
        if (allAudioModeValue != null) {
            return allAudioModeValue[0];
        }
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getEqualizer120Hz() {
        int[] allAudioModeValue = this.mHisenseTvAPI.getAllAudioModeValue(MtkTvConfigTypeBase.CFG_FACTORY_AUD_EQ_BAND_1, 1);
        if (allAudioModeValue != null) {
            return allAudioModeValue[0];
        }
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getEqualizer1500Hz() {
        int[] allAudioModeValue = this.mHisenseTvAPI.getAllAudioModeValue(MtkTvConfigTypeBase.CFG_FACTORY_AUD_EQ_BAND_3, 1);
        if (allAudioModeValue != null) {
            return allAudioModeValue[0];
        }
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getEqualizer500Hz() {
        int[] allAudioModeValue = this.mHisenseTvAPI.getAllAudioModeValue(MtkTvConfigTypeBase.CFG_FACTORY_AUD_EQ_BAND_2, 1);
        if (allAudioModeValue != null) {
            return allAudioModeValue[0];
        }
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getEqualizer5KHz() {
        int[] allAudioModeValue = this.mHisenseTvAPI.getAllAudioModeValue(MtkTvConfigTypeBase.CFG_FACTORY_AUD_EQ_BAND_4, 1);
        if (allAudioModeValue != null) {
            return allAudioModeValue[0];
        }
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getFactoryStatus() {
        int i = 0;
        try {
            i = this.mHisenseTvAPI.isFactoryMode() ? 1 : 0;
            Log.i("Fac_FacFunction_Adapter", "get factory status = " + i);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getFieldDepth() {
        return this.mode_fielddepth;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getGCUT() {
        int[] factoryWhiteBalanceOffsetGreen = this.mHisenseTvAPI.getFactoryWhiteBalanceOffsetGreen();
        if (factoryWhiteBalanceOffsetGreen == null || factoryWhiteBalanceOffsetGreen.length <= 1) {
            return 0;
        }
        Log.i("Fac_FacFunction_Adapter", "get rcut = " + factoryWhiteBalanceOffsetGreen[1]);
        return factoryWhiteBalanceOffsetGreen[1];
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getGDRV() {
        int[] factoryWhiteBalanceGainGreen = this.mHisenseTvAPI.getFactoryWhiteBalanceGainGreen();
        if (factoryWhiteBalanceGainGreen == null || factoryWhiteBalanceGainGreen.length <= 1) {
            return 0;
        }
        Log.i("Fac_FacFunction_Adapter", "get G gain = " + factoryWhiteBalanceGainGreen[1]);
        return factoryWhiteBalanceGainGreen[1];
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getGDRVOffset() {
        int[] whiteBalanceValue = this.mHisenseTvAPI.getWhiteBalanceValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_GAIN, 3);
        if (whiteBalanceValue == null || whiteBalanceValue.length <= 1) {
            return 0;
        }
        return whiteBalanceValue[1];
    }

    @Override // com.hisense.tvcm.FacFunction
    public FacPropertyDemo.HomeOrTVMode getHomeorTV() {
        System.out.println("get HomeorTV = " + this.fun_homeortv_value.toString());
        return this.fun_homeortv_value;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getHStart() {
        return this.overscan_hstart[getCurrentOverScanTiming().ordinal()];
    }

    @Override // com.hisense.tvcm.FacFunction
    public String getIPAddress() {
        return getHostAddress(0);
    }

    public static final String getHostAddress(int i) {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            int i2 = 0;
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterfaceNextElement = networkInterfaces.nextElement();
                if (networkInterfaceNextElement.getName().toLowerCase().equals("eth0")) {
                    Enumeration<InetAddress> inetAddresses = networkInterfaceNextElement.getInetAddresses();
                    while (inetAddresses.hasMoreElements()) {
                        InetAddress inetAddressNextElement = inetAddresses.nextElement();
                        Log.i("Fac_FacFunction_Adapter", "isUsableAddress(addr)" + isUsableAddress(inetAddressNextElement));
                        if (isUsableAddress(inetAddressNextElement)) {
                            if (i2 >= i) {
                                Log.i("Fac_FacFunction_Adapter", "host is" + inetAddressNextElement.getHostAddress());
                                return inetAddressNextElement.getHostAddress();
                            }
                            i2++;
                        }
                    }
                }
            }
            return "0.0.0.0";
        } catch (Exception e) {
            e.printStackTrace();
            return "0.0.0.0";
        }
    }

    private static final boolean isUsableAddress(InetAddress inetAddress) {
        if (inetAddress.isLoopbackAddress()) {
            Log.i("Fac_FacFunction_Adapter", "isLoopbackAddress = " + inetAddress.isLoopbackAddress());
            return false;
        }
        if (!(inetAddress instanceof Inet6Address)) {
            return true;
        }
        Log.i("Fac_FacFunction_Adapter", "addr instanceof Inet6Address");
        return false;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getLeftRightSwap() {
        return this.mode_leftrightswap;
    }

    @Override // com.hisense.tvcm.FacFunction
    public String getMacFileNameInUsbDisk() {
        String str = FacPropertyDemo.HIFAC_MAC_FILENAME;
        Log.i("Fac_FacFunction_Adapter", "MacFileNameInUsbDisk = " + str);
        return str;
    }

    @Override // com.hisense.tvcm.FacFunction
    public FacPropertyDemo.ModelPanelGroup getModelPanel() {
        int whiteBalancePanel = this.mHisenseTvAPI.getWhiteBalancePanel(MtkTvConfigTypeBase.CFG_FACTORY_VID_PANEL_TYPE);
        Log.i("Fac_FacFunction_Adapter", "get mode panel = " + whiteBalancePanel);
        return FacPropertyDemo.ModelPanelGroup.values()[ApVsTILmap.TilToAp(2, whiteBalancePanel)];
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getnetworktype() {
        return WifiAdmin.getinstance().isWifiEnabled() ? 1 : 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public FacPropertyDemo.OSDLanguage getOSDLanguage() {
        System.out.println("get OSDLanguage = " + this.fun_osdlanguage_value.toString());
        return this.fun_osdlanguage_value;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getOverScanBottom() {
        int[] overScanBottom = this.mHisenseTvAPI.getOverScanBottom();
        if (overScanBottom != null && overScanBottom.length > 1) {
            Log.i("Fac_FacFunction_Adapter", " getOverScanBottom called, param:" + overScanBottom[1]);
            return overScanBottom[1];
        }
        Log.i("Fac_FacFunction_Adapter", " getOverScanBottom called,array is null");
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getOverScanLeft() {
        int[] overScanLeft = this.mHisenseTvAPI.getOverScanLeft();
        if (overScanLeft != null && overScanLeft.length > 2) {
            Log.i("Fac_FacFunction_Adapter", " getOverScanLeft called, param:" + overScanLeft[2]);
            return overScanLeft[2];
        }
        Log.i("Fac_FacFunction_Adapter", " getOverScanLeft called,array is null");
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getOverScanRight() {
        int[] overScanRight = this.mHisenseTvAPI.getOverScanRight();
        if (overScanRight != null && overScanRight.length > 3) {
            Log.i("Fac_FacFunction_Adapter", " getOverScanRigth called, param:" + overScanRight[3]);
            return overScanRight[3];
        }
        Log.i("Fac_FacFunction_Adapter", " getOverScanRigth called,array is null");
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getOverScanTop() {
        int[] overScanTop = this.mHisenseTvAPI.getOverScanTop();
        if (overScanTop != null) {
            Log.i("Fac_FacFunction_Adapter", " getOverScanTop called, param:" + overScanTop[0]);
            return overScanTop[0];
        }
        Log.i("Fac_FacFunction_Adapter", " getOverScanTop called,array is null");
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getPictureCurve0() {
        return this.picturecurve_point0_value[getVideoCurveModeGroup().ordinal()][getCurrentVideoSourceGroup().ordinal()];
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getPictureCurve100() {
        return this.picturecurve_point100_value[getVideoCurveModeGroup().ordinal()][getCurrentVideoSourceGroup().ordinal()];
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getPictureCurve25() {
        return this.picturecurve_point25_value[getVideoCurveModeGroup().ordinal()][getCurrentVideoSourceGroup().ordinal()];
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getPictureCurve50() {
        return this.picturecurve_point50_value[getVideoCurveModeGroup().ordinal()][getCurrentVideoSourceGroup().ordinal()];
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getPictureCurve75() {
        return this.picturecurve_point75_value[getVideoCurveModeGroup().ordinal()][getCurrentVideoSourceGroup().ordinal()];
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getPictureCurveMaximum() {
        int i = AnonymousClass7.$SwitchMap$com$hisense$tvcm$FacPropertyDemo$VideoCurveModeGroup[getVideoCurveModeGroup().ordinal()];
        return 100;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getPictureCurveMinimum() {
        int i = AnonymousClass7.$SwitchMap$com$hisense$tvcm$FacPropertyDemo$VideoCurveModeGroup[getVideoCurveModeGroup().ordinal()];
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public FacPropertyDemo.PictureMode getPictureMode() {
        int[] pictureMode = this.mHisenseTvAPI.getPictureMode();
        Log.i("Fac_FacFunction_Adapter", "mtkPicMode = " + pictureMode[0]);
        if (pictureMode != null && FacPropertyDemo.PictureMode.values().length > ApVsTILmap.TilToAp(1, pictureMode[0])) {
            Log.i("Fac_FacFunction_Adapter", "mtkPicMode = " + pictureMode[0]);
            return FacPropertyDemo.PictureMode.values()[ApVsTILmap.TilToAp(1, pictureMode[0])];
        }
        return FacPropertyDemo.PictureMode.STANDARD;
    }

    @Override // com.hisense.tvcm.FacFunction
    public FacPropertyDemo.PictureModeTW_Toshiba getPictureModeTW() {
        int[] pictureMode = this.mHisenseTvAPI.getPictureMode();
        Log.i("Fac_FacFunction_Adapter", "mtkPicModeTW = " + pictureMode[0]);
        if (pictureMode != null && FacPropertyDemo.PictureModeTW_Toshiba.values().length > ApVsTILmap.TilToAp(38, pictureMode[0])) {
            Log.i("Fac_FacFunction_Adapter", "mtkPicModeTW = " + pictureMode[0]);
            return FacPropertyDemo.PictureModeTW_Toshiba.values()[ApVsTILmap.TilToAp(38, pictureMode[0])];
        }
        return FacPropertyDemo.PictureModeTW_Toshiba.STANDARD;
    }

    @Override // com.hisense.tvcm.FacFunction
    public FacPropertyDemo.PowerModeEnum getPowerOnMode() {
        int powerControlMode = this.mHisenseTvAPI.getPowerControlMode(MtkTvConfigTypeBase.CFG_FACTORY_POWER_CTRL);
        Log.i("Fac_FacFunction_Adapter", "power mode is " + powerControlMode);
        if (powerControlMode >= 0 && powerControlMode < 3) {
            return FacPropertyDemo.PowerModeEnum.values()[powerControlMode];
        }
        return FacPropertyDemo.PowerModeEnum.values()[0];
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getPrescale() {
        int prescaleValue = this.mHisenseTvAPI.getPrescaleValue(MtkTvConfigTypeBase.CFG_FACTORY_AUD_SOC_PRESCALE);
        Log.i("Fac_FacFunction_Adapter", "!!!getPrescale called, value:" + prescaleValue);
        return prescaleValue;
    }

    @Override // com.hisense.tvcm.FacFunction
    public String getProductType() {
        String machineName = this.mHisenseTvAPI.getMachineName(MtkTvConfigTypeBase.CFG_FACTORY_FAC_MACHINE_NAME);
        Log.i("Fac_FacFunction_Adapter", "productmane = " + machineName);
        return machineName != null ? machineName : "";
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getRCUT() {
        int[] factoryWhiteBalanceOffsetRed = this.mHisenseTvAPI.getFactoryWhiteBalanceOffsetRed();
        if (factoryWhiteBalanceOffsetRed == null) {
            return 0;
        }
        Log.i("Fac_FacFunction_Adapter", "get rcut = " + factoryWhiteBalanceOffsetRed[0]);
        return factoryWhiteBalanceOffsetRed[0];
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getRDRV() {
        int[] factoryWhiteBalanceGainRed = this.mHisenseTvAPI.getFactoryWhiteBalanceGainRed();
        if (factoryWhiteBalanceGainRed == null) {
            return 0;
        }
        Log.i("Fac_FacFunction_Adapter", "get rdrv = " + factoryWhiteBalanceGainRed[0]);
        return factoryWhiteBalanceGainRed[0];
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getRDRVOffset() {
        int[] factoryWhiteBalanceOffsetRed = this.mHisenseTvAPI.getFactoryWhiteBalanceOffsetRed();
        if (factoryWhiteBalanceOffsetRed == null) {
            return 0;
        }
        Log.i("Fac_FacFunction_Adapter", "get rdrv = " + factoryWhiteBalanceOffsetRed[0]);
        return factoryWhiteBalanceOffsetRed[0];
    }

    @Override // com.hisense.tvcm.FacFunction
    public FacPropertyDemo.SavingMode getSavingMode() {
        return this.savingmode_value;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getSharpness() {
        int[] sharpness = this.mHisenseTvAPI.getSharpness();
        if (sharpness == null || sharpness.length <= 4) {
            return 0;
        }
        return sharpness[4];
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getSSCDDREnable() {
        Log.i("Fac_FacFunction_Adapter", "getSSCDDREnable called");
        return this.mHisenseTvAPI.isDdrSscEnable() ? 1 : 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getSSCDDRSpan() {
        Log.i("Fac_FacFunction_Adapter", "getSSCDDRSpan called");
        return this.mHisenseTvAPI.getDdrSpan();
    }

    @Override // com.hisense.tvcm.FacFunction
    public FacPropertyDemo.ToFacEnum getToFac() {
        Log.i("Fac_FacFunction_Adapter", "get tofac mode = " + this.mHisenseTvAPI.getToFacMode());
        if (this.mHisenseTvAPI.getToFacMode() > 1 || this.mHisenseTvAPI.getToFacMode() < 0) {
            return FacPropertyDemo.ToFacEnum.TOFAC_U;
        }
        return FacPropertyDemo.ToFacEnum.values()[this.mHisenseTvAPI.getToFacMode()];
    }

    @Override // com.hisense.tvcm.FacFunction
    public String getVersionInfo() {
        return this.mHisenseTvAPI.getSoftwareVersion();
    }

    @Override // com.hisense.tvcm.FacFunction
    public FacPropertyDemo.VideoCurveModeGroup getVideoCurveModeGroup() {
        return this.picturecurvemode_value;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getVolume() {
        int streamVolume = this.mAudioManager.getStreamVolume(3);
        Log.i("Fac_FacFunction_Adapter", "mtk getVolume = " + streamVolume);
        return streamVolume;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getVolumeCurve0() {
        int[] volumeCurve0 = this.mHisenseTvAPI.getVolumeCurve0();
        if (volumeCurve0 != null) {
            Log.i("Fac_FacFunction_Adapter", "getVolumeMin called, param:" + volumeCurve0[0]);
            return volumeCurve0[0];
        }
        Log.i("Fac_FacFunction_Adapter", "getVolume20 called,array is null");
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getVolumeCurve100() {
        int[] volumeCurve100 = this.mHisenseTvAPI.getVolumeCurve100();
        if (volumeCurve100 != null && volumeCurve100.length > 4) {
            Log.i("Fac_FacFunction_Adapter", "getVolumeMax called, param:" + volumeCurve100[4]);
            return volumeCurve100[4];
        }
        Log.i("Fac_FacFunction_Adapter", "getVolume20 called,array is null");
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getVolumeCurve25() {
        int[] volumeCurve25 = this.mHisenseTvAPI.getVolumeCurve25();
        if (volumeCurve25 != null && volumeCurve25.length > 1) {
            Log.i("Fac_FacFunction_Adapter", "getVolume20 called, param:" + volumeCurve25[1]);
            return volumeCurve25[1];
        }
        Log.i("Fac_FacFunction_Adapter", "getVolume20 called,array is null");
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getVolumeCurve50() {
        int[] volumeCurve50 = this.mHisenseTvAPI.getVolumeCurve50();
        if (volumeCurve50 != null && volumeCurve50.length > 2) {
            Log.i("Fac_FacFunction_Adapter", "getVolumeMid called, param:" + volumeCurve50[2]);
            return volumeCurve50[2];
        }
        Log.i("Fac_FacFunction_Adapter", "getVolume20 called,array is null");
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getVolumeCurve75() {
        int[] volumeCurve75 = this.mHisenseTvAPI.getVolumeCurve75();
        if (volumeCurve75 != null && volumeCurve75.length > 3) {
            Log.i("Fac_FacFunction_Adapter", "getVolume75 called, param:" + volumeCurve75[3]);
            return volumeCurve75[3];
        }
        Log.i("Fac_FacFunction_Adapter", "getVolume20 called,array is null");
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getVStart() {
        return this.overscan_vstart[getCurrentOverScanTiming().ordinal()];
    }

    @Override // com.hisense.tvcm.FacFunction
    public FacPropertyDemo.ZoomMode getZoomMode() {
        int aspectRatio = this.mHisenseTvAPI.getAspectRatio();
        Log.i("Fac_FacFunction_Adapter", "getZoomMode = " + aspectRatio);
        return FacPropertyDemo.ZoomMode.values()[ApVsTILmap.TilToAp(6, aspectRatio)];
    }

    @Override // com.hisense.tvcm.FacFunction
    public FacPropertyDemo.ZoomModeForEM getZoomModeForEM() {
        int aspectRatio = this.mHisenseTvAPI.getAspectRatio();
        Log.i("Fac_FacFunction_Adapter", "getZoomModeforEM= " + aspectRatio);
        return FacPropertyDemo.ZoomModeForEM.values()[ApVsTILmap.TilToAp(23, aspectRatio)];
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean isVolumeMute() {
        return this.mMtkTvVolCtrl.getMute();
    }

    @Override // com.hisense.tvcm.FacFunction
    public void set2Dor3D(FacPropertyDemo.DimensionGroup dimensionGroup) {
        System.out.println("set 2Dor3D = " + dimensionGroup.toString());
        this.mode_2dor3d_value = dimensionGroup;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void set3DMode(FacPropertyDemo.ThreeDimensionMode threeDimensionMode) {
        this.mode_3d = threeDimensionMode;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void set3Dto2D(int i) {
        this.mode_3dto2d = i;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setADCBlueGain(int i) {
        this.adc_bdrv_value[getCurrentADCColorSpaceGroup().ordinal()] = i;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setADCBlueOffset(int i) {
        this.adc_bcut_value[getCurrentADCColorSpaceGroup().ordinal()] = i;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setADCGreenGain(int i) {
        this.adc_gdrv_value[getCurrentADCColorSpaceGroup().ordinal()] = i;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setADCGreenOffset(int i) {
        this.adc_gcut_value[getCurrentADCColorSpaceGroup().ordinal()] = i;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setADCRedGain(int i) {
        this.adc_rdrv_value[getCurrentADCColorSpaceGroup().ordinal()] = i;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setADCRedOffset(int i) {
        this.adc_rcut_value[getCurrentADCColorSpaceGroup().ordinal()] = i;
    }

    private void resetEnterAgingModeTime() {
        this.mEnterAgingModeTime = SystemClock.uptimeMillis();
        Log.i("Fac_FacFunction_Adapter", "mEnterAgingModeTime is : " + this.mEnterAgingModeTime);
        if (this.mSharedPreferences == null) {
            initSharedPreference();
        }
        SharedPreferences sharedPreferences = this.mSharedPreferences;
        if (sharedPreferences == null) {
            return;
        }
        sharedPreferences.edit().putLong("startAgingTime", this.mEnterAgingModeTime).commit();
    }

    private void resetExitAgingModeTime() {
        this.mExitAgingModeTime = SystemClock.uptimeMillis();
        Log.i("Fac_FacFunction_Adapter", "mExitAgingModeTime is : " + this.mExitAgingModeTime);
    }

    private void setAgingTime() {
        mAgingTime += this.mExitAgingModeTime - this.mEnterAgingModeTime;
        Log.i("Fac_FacFunction_Adapter", "mAgingTime is : " + mAgingTime);
        if (this.mSharedPreferences == null) {
            initSharedPreference();
        }
        SharedPreferences sharedPreferences = this.mSharedPreferences;
        if (sharedPreferences == null) {
            return;
        }
        sharedPreferences.edit().putLong("agingTime", mAgingTime).commit();
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setAgingFlag(int i) {
        Log.d("Fac_FacFunction_Adapter", "setAgingFlag value = " + i);
        if (i == 1) {
            SystemProperties.set("sys.vt.hisense.uifac.inAgingMode", "1");
            if (SystemProperties.getInt("sys.fac_has_aged", 0) == 0) {
                SystemProperties.set("sys.fac_has_aged", "1");
            }
            resetEnterAgingModeTime();
            return;
        }
        SystemProperties.set("sys.vt.hisense.uifac.inAgingMode", "0");
        resetExitAgingModeTime();
        if (this.mSharedPreferences == null) {
            initSharedPreference();
        }
        setAgingTime();
    }

    @Override // com.hisense.tvcm.FacFunction
    public void cleanAgingTime() {
        Log.d("Fac_FacFunction_Adapter", "Clean aging time!!!");
        mAgingTime = 0L;
        if (this.mSharedPreferences == null) {
            initSharedPreference();
        }
        SharedPreferences sharedPreferences = this.mSharedPreferences;
        if (sharedPreferences != null && sharedPreferences.contains("agingTime")) {
            this.mSharedPreferences.edit().remove("agingTime").commit();
        }
    }

    @Override // com.hisense.tvcm.FacFunction
    public void restoreAgingTime() {
        Log.d("Fac_FacFunction_Adapter", "restore aging time when get into factory!!!");
        if (this.mSharedPreferences == null) {
            initSharedPreference();
        }
        SharedPreferences sharedPreferences = this.mSharedPreferences;
        if (sharedPreferences == null) {
            return;
        }
        mAgingTime = sharedPreferences.getLong("agingTime", 1000L);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setATVPresetChannel() {
        List<ATVPresetChannel> list = this.atvChInfo;
        if (list != null) {
            for (ATVPresetChannel aTVPresetChannel : list) {
                System.out.println("****set ATV ChannelNum" + aTVPresetChannel.getChannelNum());
                System.out.println("****set ATV ChannelName" + aTVPresetChannel.getChannelName());
                System.out.println("****set ATV Frequency" + aTVPresetChannel.getFrequency());
                System.out.println("****set ATV ColorSys" + aTVPresetChannel.getColorSys());
                System.out.println("****set ATV AudioSys" + aTVPresetChannel.getAudioSys());
            }
        }
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setAudioMode(FacPropertyDemo.AudioMode audioMode) {
        Log.i("Fac_FacFunction_Adapter", "!!!setAudioMode called!!! , value:" + audioMode.ordinal());
        this.mHisenseTvAPI.setAudioMode(MtkTvConfigTypeBase.CFG_FACTORY_AUD_SOUND_MODE, audioMode.ordinal());
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setBalance(int i) {
        this.mHisenseTvAPI.setBalance(i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getBalanceForTweeter() {
        int iAdjustTvsOutputToIndexLike550or650 = adjustTvsOutputToIndexLike550or650();
        Log.i("Fac_FacFunction_Adapter", "getBalanceForTweeter value = " + iAdjustTvsOutputToIndexLike550or650);
        return iAdjustTvsOutputToIndexLike550or650;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setBalanceForTweeter(int i) {
        Log.i("Fac_FacFunction_Adapter", "setBalanceForTweeter value = " + i);
        adjustTvsIndexToOutputLike550or650(i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getBalanceForTweeterWoofer() {
        int iAdjustTvsOutputToIndexLike550or650 = adjustTvsOutputToIndexLike550or650();
        Log.i("Fac_FacFunction_Adapter", "getBalanceForDoubleAttack value = " + iAdjustTvsOutputToIndexLike550or650);
        return iAdjustTvsOutputToIndexLike550or650;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setBalanceForTweeterWoofer(int i) {
        Log.i("Fac_FacFunction_Adapter", "setBalanceForTweeterWoofer value = " + i);
        adjustTvsIndexToOutputLike550or650(i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getBalanceForLike850() {
        int iAdjustTvsOutputToIndexLike850 = adjustTvsOutputToIndexLike850();
        Log.i("Fac_FacFunction_Adapter", "getBalanceForLike850 value = " + iAdjustTvsOutputToIndexLike850);
        return iAdjustTvsOutputToIndexLike850;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setBalanceForLike850(int i) {
        Log.i("Fac_FacFunction_Adapter", "setBalanceForLike850 value = " + i);
        adjustTvsIndexToOutputLike850(i);
    }

    private void adjustTvsIndexToOutputLike550or650(int i) {
        int i2;
        int i3 = 0;
        if (i == 0) {
            i3 = FactoryConstants.OUTPUT_TVS_SPEAKER_ALL;
            i2 = FactoryConstants.OUTPUT_TVS_BALANCE_CENTER;
        } else if (i == 1) {
            i3 = FactoryConstants.OUTPUT_TVS_SPEAKER_MAIN;
            i2 = FactoryConstants.OUTPUT_TVS_BALANCE_LEFT;
        } else if (i == 2) {
            i3 = FactoryConstants.OUTPUT_TVS_SPEAKER_MAIN;
            i2 = FactoryConstants.OUTPUT_TVS_BALANCE_RIGHT;
        } else if (i == 3) {
            i3 = FactoryConstants.OUTPUT_TVS_SPEAKER_WOOFER;
            i2 = FactoryConstants.OUTPUT_TVS_BALANCE_CENTER;
        } else if (i == 4) {
            i3 = FactoryConstants.OUTPUT_TVS_SPEAKER_TOP_TWEETER;
            i2 = FactoryConstants.OUTPUT_TVS_BALANCE_LEFT;
        } else if (i != 5) {
            i2 = 0;
        } else {
            i3 = FactoryConstants.OUTPUT_TVS_SPEAKER_TOP_TWEETER;
            i2 = FactoryConstants.OUTPUT_TVS_BALANCE_RIGHT;
        }
        this.mTvsFunc.setTvsOutputSpeaker(i3, i2);
    }

    private int adjustTvsOutputToIndexLike550or650() {
        int[] tvsOutputSpeaker = this.mTvsFunc.getTvsOutputSpeaker();
        if (tvsOutputSpeaker == null && tvsOutputSpeaker.length < 2) {
            Log.i("Fac_FacFunction_Adapter", "error!  the default is center!!!");
            return 0;
        }
        int i = tvsOutputSpeaker[0];
        int i2 = tvsOutputSpeaker[1];
        if (i == FactoryConstants.OUTPUT_TVS_SPEAKER_ALL) {
            int i3 = FactoryConstants.OUTPUT_TVS_BALANCE_CENTER;
        } else if (i == FactoryConstants.OUTPUT_TVS_SPEAKER_MAIN) {
            if (i2 == FactoryConstants.OUTPUT_TVS_BALANCE_LEFT) {
                return 1;
            }
            if (i2 == FactoryConstants.OUTPUT_TVS_BALANCE_RIGHT) {
                return 2;
            }
        } else if (i == FactoryConstants.OUTPUT_TVS_SPEAKER_TOP_TWEETER) {
            if (i2 == FactoryConstants.OUTPUT_TVS_BALANCE_LEFT) {
                return 4;
            }
            if (i2 == FactoryConstants.OUTPUT_TVS_BALANCE_RIGHT) {
                return 5;
            }
        } else if (i == FactoryConstants.OUTPUT_TVS_SPEAKER_WOOFER && i2 == FactoryConstants.OUTPUT_TVS_BALANCE_CENTER) {
            return 3;
        }
        return 0;
    }

    private void adjustTvsIndexToOutputLike850(int i) {
        int i2;
        int i3 = 0;
        if (i == 0) {
            i3 = FactoryConstants.OUTPUT_TVS_SPEAKER_ALL;
            i2 = FactoryConstants.OUTPUT_TVS_BALANCE_CENTER;
        } else if (i == 1) {
            i3 = FactoryConstants.OUTPUT_TVS_SPEAKER_MAIN;
            i2 = FactoryConstants.OUTPUT_TVS_BALANCE_LEFT;
        } else if (i == 2) {
            i3 = FactoryConstants.OUTPUT_TVS_SPEAKER_MAIN;
            i2 = FactoryConstants.OUTPUT_TVS_BALANCE_RIGHT;
        } else if (i == 3) {
            i3 = FactoryConstants.OUTPUT_TVS_SPEAKER_TWEETER;
            i2 = FactoryConstants.OUTPUT_TVS_BALANCE_LEFT;
        } else if (i != 4) {
            i2 = 0;
        } else {
            i3 = FactoryConstants.OUTPUT_TVS_SPEAKER_TWEETER;
            i2 = FactoryConstants.OUTPUT_TVS_BALANCE_RIGHT;
        }
        this.mTvsFunc.setTvsOutputSpeaker(i3, i2);
    }

    private int adjustTvsOutputToIndexLike850() {
        int[] tvsOutputSpeaker = this.mTvsFunc.getTvsOutputSpeaker();
        if (tvsOutputSpeaker == null && tvsOutputSpeaker.length < 2) {
            Log.i("Fac_FacFunction_Adapter", "error!  the default is center!!!");
            return 0;
        }
        int i = tvsOutputSpeaker[0];
        int i2 = tvsOutputSpeaker[1];
        if (i == FactoryConstants.OUTPUT_TVS_SPEAKER_ALL) {
            int i3 = FactoryConstants.OUTPUT_TVS_BALANCE_CENTER;
        } else if (i == FactoryConstants.OUTPUT_TVS_SPEAKER_MAIN) {
            if (i2 == FactoryConstants.OUTPUT_TVS_BALANCE_LEFT) {
                return 1;
            }
            if (i2 == FactoryConstants.OUTPUT_TVS_BALANCE_RIGHT) {
                return 2;
            }
        } else if (i == FactoryConstants.OUTPUT_TVS_SPEAKER_TWEETER) {
            if (i2 == FactoryConstants.OUTPUT_TVS_BALANCE_LEFT) {
                return 3;
            }
            if (i2 == FactoryConstants.OUTPUT_TVS_BALANCE_RIGHT) {
                return 4;
            }
        }
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setBCUT(int i) {
        this.mHisenseTvAPI.setWhiteBalanceValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_OFFSET_B, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setBDRV(int i) {
        this.mHisenseTvAPI.setWhiteBalanceValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_GAIN_B, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setBDRVOffset(int i) {
        this.mHisenseTvAPI.setWhiteBalanceValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_GAIN_B, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setBrightness(int i) {
        Log.i("Fac_FacFunction_Adapter", "setBrightness = " + i);
        this.mHisenseTvAPI.setPictureModeDetailValue(MtkTvConfigTypeBase.CFG_VIDEO_VID_BRIGHTNESS, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setColor(int i) {
        this.mHisenseTvAPI.setPictureModeDetailValue(MtkTvConfigTypeBase.CFG_VIDEO_VID_SAT, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setColorTemperature(FacPropertyDemo.ColorTemperature colorTemperature) {
        int iApToTil = ApVsTILmap.ApToTil(3, colorTemperature.ordinal());
        Log.i("Fac_FacFunction_Adapter", "set colortmp mtkvalue= " + iApToTil);
        this.mHisenseTvAPI.setColorTemperature(MtkTvConfigTypeBase.CFG_FACTORY_VID_COLOR_TEMP, iApToTil);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setContrast(int i) {
        Log.i("Fac_FacFunction_Adapter", "setContrast = " + i);
        this.mHisenseTvAPI.setPictureModeDetailValue(MtkTvConfigTypeBase.CFG_VIDEO_VID_CONTRAST, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setConvergence(int i) {
        this.mode_convergence = i;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setDTVPresetChannel() {
        List<DTVPresetChannel> list = this.dtvChInfo;
        if (list != null) {
            for (DTVPresetChannel dTVPresetChannel : list) {
                System.out.println("****set DTV ChannelNum" + dTVPresetChannel.getChannelNum());
                System.out.println("****set DTV ChannelName" + dTVPresetChannel.getChannelName());
                System.out.println("****set DTV Frequency" + dTVPresetChannel.getFrequency());
                System.out.println("****set DTV BW" + dTVPresetChannel.getBindWidth());
                System.out.println("****set DTV NetworkId" + dTVPresetChannel.getNetworkId());
                System.out.println("****set DTV OrgNetworkId" + dTVPresetChannel.getOrgNetworkId());
                System.out.println("****set DTV ServiceId" + dTVPresetChannel.getServiceId());
                System.out.println("****set DTV SymRate" + dTVPresetChannel.getSymRate());
                System.out.println("****set DTV TsId" + dTVPresetChannel.getTsId());
                System.out.println("****set DTV Modulation" + dTVPresetChannel.getMod());
            }
        }
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setEqualizer10KHz(int i) {
        Log.i("Fac_FacFunction_Adapter", "setEqualizer10000Hz called, value:" + i);
        this.mHisenseTvAPI.setAudioModeDetailValue(MtkTvConfigTypeBase.CFG_FACTORY_AUD_EQ_BAND_5, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setEqualizer120Hz(int i) {
        Log.i("Fac_FacFunction_Adapter", "setEqualizer120Hz called, value:" + i);
        this.mHisenseTvAPI.setAudioModeDetailValue(MtkTvConfigTypeBase.CFG_FACTORY_AUD_EQ_BAND_1, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setEqualizer1500Hz(int i) {
        Log.i("Fac_FacFunction_Adapter", "setEqualizer1500Hz called, value:" + i);
        this.mHisenseTvAPI.setAudioModeDetailValue(MtkTvConfigTypeBase.CFG_FACTORY_AUD_EQ_BAND_3, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setEqualizer500Hz(int i) {
        Log.i("Fac_FacFunction_Adapter", "setEqualizer500Hz called, value:" + i);
        this.mHisenseTvAPI.setAudioModeDetailValue(MtkTvConfigTypeBase.CFG_FACTORY_AUD_EQ_BAND_2, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setEqualizer5KHz(int i) {
        Log.i("Fac_FacFunction_Adapter", "setEqualizer5000Hz called, value:" + i);
        this.mHisenseTvAPI.setAudioModeDetailValue(MtkTvConfigTypeBase.CFG_FACTORY_AUD_EQ_BAND_4, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setFactoryStatus(int i) {
        if (i == 0) {
            this.mIsExitFacLogThread = true;
        } else {
            this.mIsExitFacLogThread = false;
        }
        if (FacPropertyDemo.HIFAC_MARKET_JP) {
            this.mTvsFunc.setTvsFactoryMode(i != 0);
        }
        this.mHisenseTvAPI.setFactoryMode(i != 0);
        this.mHisenseTvAPI.setFactoryModeToAudioPicture(i);
        StringBuilder sb = new StringBuilder();
        sb.append("setFactory status result = ");
        sb.append(this.mHisenseTvAPI.setFactoryMode(i != 0));
        Log.i("Fac_FacFunction_Adapter", sb.toString());
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setFieldDepth(int i) {
        this.mode_fielddepth = i;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setGCUT(int i) {
        this.mHisenseTvAPI.setWhiteBalanceValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_OFFSET_G, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setGDRV(int i) {
        System.out.println("set GDRV = " + i);
        this.gdrv_value[getCurrentWhiteBalanceSourceGroup().ordinal()][getModelPanel().ordinal()][get2Dor3D().ordinal()] = i;
        this.mHisenseTvAPI.setWhiteBalanceValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_GAIN_G, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setGDRVOffset(int i) {
        this.mHisenseTvAPI.setWhiteBalanceValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_GAIN_G, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setHomeorTV(FacPropertyDemo.HomeOrTVMode homeOrTVMode) {
        System.out.println("set HomeorTV = " + homeOrTVMode.toString());
        this.fun_homeortv_value = homeOrTVMode;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setHStart(int i) {
        this.overscan_hstart[getCurrentOverScanTiming().ordinal()] = i;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setLeftRightSwap(int i) {
        this.mode_leftrightswap = i;
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean setMacAddress(String str) {
        updateFacLogStatus(1);
        if (FacPropertyDemo.HIFAC_MARKET_JP) {
            this.mTvsFunc.setTvsWiredMacAddr(str);
            removeTvsKeyName(true);
        }
        if (this.mHisenseTvAPI.setMacAddressForFac(str) == 0) {
            Log.i("Fac_FacFunction_Adapter", "setMacAddress succ is " + str);
            return true;
        }
        Log.i("Fac_FacFunction_Adapter", "setMacAddress failed is " + str);
        return false;
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean setCountryIndex(int i) {
        Log.i("Fac_FacFunction_Adapter", "setCountry is " + i);
        boolean tvCountry = this.mHisenseTvAPI.setTvCountry(MtkTvConfigTypeBase.CFG_FACTORY_FAC_TV_CPUNTRY, i);
        setChangeFlage();
        return tvCountry;
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean setRegionTilValue(int i) {
        Log.i("Fac_FacFunction_Adapter", "setRegionTilValue is " + i);
        boolean regionValue = this.mHisenseTvAPI.setRegionValue(MtkTvConfigTypeBase.CFG_FACTORY_REGION, i);
        setChangeFlage();
        return regionValue;
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean setLogoIndex(int i) {
        Log.i("Fac_FacFunction_Adapter", "setLogoIndex is " + i);
        boolean bootUpLogo = this.mHisenseTvAPI.setBootUpLogo(i);
        setChangeFlage();
        if (FacPropertyDemo.HIFAC_MARKET_SA && this.mAdapterHelper.isNewsanLogo(i)) {
            setVolume(30);
            addTvPresentChannelForNewsan();
        }
        return bootUpLogo;
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean setLangIndex(int i) {
        boolean tvLanguage = this.mHisenseTvAPI.setTvLanguage(MtkTvConfigTypeBase.CFG_FACTORY_FAC_TV_LANGUAGE, i);
        Log.i("Fac_FacFunction_Adapter", "setLangIndex is " + i + "ret is " + tvLanguage);
        setChangeFlage();
        return tvLanguage;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setModelPanel(FacPropertyDemo.ModelPanelGroup modelPanelGroup) {
        int iApToTil = ApVsTILmap.ApToTil(2, modelPanelGroup.ordinal());
        Log.i("Fac_FacFunction_Adapter", "panel set to " + iApToTil);
        this.mHisenseTvAPI.setWhiteBalancePanel(MtkTvConfigTypeBase.CFG_FACTORY_VID_PANEL_TYPE, iApToTil);
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean getWifiStatus() {
        boolean zIsWifiEnabled = this.mWifiAdmin.isWifiEnabled();
        Log.i("Fac_FacFunction_Adapter", "getWifiStatus called, value:" + zIsWifiEnabled);
        return zIsWifiEnabled;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void disconnectWifi() {
        Log.i("Fac_FacFunction_Adapter", "enter disconnectWifi");
        this.mWifiAdmin.disconnectWifi();
    }

    @Override // com.hisense.tvcm.FacFunction
    public void openWifi() {
        Log.i("Fac_FacFunction_Adapter", "enter openWifi");
        this.mWifiAdmin.openWifi();
    }

    @Override // com.hisense.tvcm.FacFunction
    public void reconnectWifi() {
        Log.i("Fac_FacFunction_Adapter", "enter reconnectWifi");
        this.mWifiAdmin.reconnectWifi();
    }

    @Override // com.hisense.tvcm.FacFunction
    public void closeWifi() {
        Log.i("Fac_FacFunction_Adapter", "enter closeWifi");
        this.mWifiAdmin.closeWifi();
    }

    @Override // com.hisense.tvcm.FacFunction
    public void startWifiScan() {
        Log.i("Fac_FacFunction_Adapter", "startWifiScan");
        this.mWifiAdmin.startScan();
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setOSDLanguage(FacPropertyDemo.OSDLanguage oSDLanguage) {
        System.out.println("set OSDLanguage = " + oSDLanguage.toString());
        try {
            Class<?> cls = Class.forName("android.app.ActivityManagerNative");
            Object objInvoke = cls.getMethod("getDefault", new Class[0]).invoke(cls, new Object[0]);
            Object objInvoke2 = objInvoke.getClass().getMethod("getConfiguration", new Class[0]).invoke(objInvoke, new Object[0]);
            int iOrdinal = oSDLanguage.ordinal();
            if (iOrdinal == 0) {
                objInvoke2.getClass().getDeclaredField("locale").set(objInvoke2, Locale.ENGLISH);
            } else if (iOrdinal == 1) {
                objInvoke2.getClass().getDeclaredField("locale").set(objInvoke2, Locale.SIMPLIFIED_CHINESE);
            } else if (iOrdinal == 2) {
                objInvoke2.getClass().getDeclaredField("locale").set(objInvoke2, Locale.TRADITIONAL_CHINESE);
            }
            objInvoke2.getClass().getDeclaredField("userSetLocale").setBoolean(objInvoke2, true);
            objInvoke.getClass().getMethod("updateConfiguration", Configuration.class).invoke(objInvoke, objInvoke2);
            BackupManager.dataChanged("com.android.providers.settings");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        } catch (IllegalArgumentException e3) {
            e3.printStackTrace();
        } catch (NoSuchFieldException e4) {
            e4.printStackTrace();
        } catch (NoSuchMethodException e5) {
            e5.printStackTrace();
        } catch (SecurityException e6) {
            e6.printStackTrace();
        } catch (InvocationTargetException e7) {
            e7.printStackTrace();
        }
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setOverScanBottom(int i) {
        Log.i("Fac_FacFunction_Adapter", "!!!setOverScanBottom called, param:" + i);
        int[] overScanBottom = this.mHisenseTvAPI.getOverScanBottom();
        if (overScanBottom != null) {
            this.mHisenseTvAPI.setOverScanBottom(new int[]{overScanBottom[0], i, overScanBottom[2], overScanBottom[3]});
        } else {
            Log.i("Fac_FacFunction_Adapter", "!!!Over scan array is null");
        }
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setOverScanLeft(int i) {
        Log.i("Fac_FacFunction_Adapter", "!!!setOverScanLeft called, param:" + i);
        int[] overScanLeft = this.mHisenseTvAPI.getOverScanLeft();
        if (overScanLeft != null) {
            this.mHisenseTvAPI.setOverScanLeft(new int[]{overScanLeft[0], overScanLeft[1], i, overScanLeft[3]});
        } else {
            Log.i("Fac_FacFunction_Adapter", "!!!Over scan array is null");
        }
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setOverScanRight(int i) {
        Log.i("Fac_FacFunction_Adapter", "!!!setOverScanRight called, param:" + i);
        int[] overScanRight = this.mHisenseTvAPI.getOverScanRight();
        if (overScanRight != null) {
            this.mHisenseTvAPI.setOverScanRight(new int[]{overScanRight[0], overScanRight[1], overScanRight[2], i});
        } else {
            Log.i("Fac_FacFunction_Adapter", "!!!Over scan array is null");
        }
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setOverScanTop(int i) {
        Log.i("Fac_FacFunction_Adapter", "!!!setOverScanTop called, param:" + i);
        int[] overScanTop = this.mHisenseTvAPI.getOverScanTop();
        if (overScanTop != null) {
            this.mHisenseTvAPI.setOverScanTop(new int[]{i, overScanTop[1], overScanTop[2], overScanTop[3]});
        } else {
            Log.i("Fac_FacFunction_Adapter", "!!!Over scan array is null");
        }
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setPictureCurve0(int i) {
        this.picturecurve_point0_value[getVideoCurveModeGroup().ordinal()][getCurrentVideoSourceGroup().ordinal()] = i;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setPictureCurve100(int i) {
        this.picturecurve_point100_value[getVideoCurveModeGroup().ordinal()][getCurrentVideoSourceGroup().ordinal()] = i;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setPictureCurve25(int i) {
        this.picturecurve_point25_value[getVideoCurveModeGroup().ordinal()][getCurrentVideoSourceGroup().ordinal()] = i;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setPictureCurve50(int i) {
        this.picturecurve_point50_value[getVideoCurveModeGroup().ordinal()][getCurrentVideoSourceGroup().ordinal()] = i;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setPictureCurve75(int i) {
        this.picturecurve_point75_value[getVideoCurveModeGroup().ordinal()][getCurrentVideoSourceGroup().ordinal()] = i;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setPictureMode(FacPropertyDemo.PictureMode pictureMode) {
        Log.i("Fac_FacFunction_Adapter", "set picture mode = " + pictureMode.toString());
        mtkPictureModeValue = ApVsTILmap.ApToTil(1, pictureMode.ordinal());
        Log.i("Fac_FacFunction_Adapter", "set picture mode = " + mtkPictureModeValue);
        this.mHisenseTvAPI.setPictureMode(mtkPictureModeValue);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setPictureModeTW(FacPropertyDemo.PictureModeTW_Toshiba pictureModeTW_Toshiba) {
        Log.i("Fac_FacFunction_Adapter", "set picture mode TW = " + pictureModeTW_Toshiba.toString());
        mtkPictureModeValue = ApVsTILmap.ApToTil(38, pictureModeTW_Toshiba.ordinal());
        Log.i("Fac_FacFunction_Adapter", "set picture mode TW = " + mtkPictureModeValue);
        this.mHisenseTvAPI.setPictureMode(mtkPictureModeValue);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setPowerOnMode(FacPropertyDemo.PowerModeEnum powerModeEnum) {
        this.mHisenseTvAPI.setPowerControlMode(MtkTvConfigTypeBase.CFG_FACTORY_POWER_CTRL, powerModeEnum.ordinal());
    }

    public void resetPowerOnMode() {
        Log.i("Fac_FacFunction_Adapter", "start reset power on mode");
        if (FacPropertyDemo.HIFAC_IS_LASER4K && "lca".equalsIgnoreCase(getLogoName())) {
            setPowerOnMode(FacPropertyDemo.PowerModeEnum.STANDBY);
            Log.i("Fac_FacFunction_Adapter", "reset LASER_LCA power on mode to STANDBY, setVal = " + FacPropertyDemo.PowerModeEnum.STANDBY.ordinal());
            return;
        }
        if (!"tos".equalsIgnoreCase(getLogoName()) && FacPropertyDemo.HIFAC_MARKET_SA) {
            setPowerOnMode(FacPropertyDemo.PowerModeEnum.STANDBY);
            Log.i("Fac_FacFunction_Adapter", "reset SA_MARKET power on mode to STANDBY, setVal = " + FacPropertyDemo.PowerModeEnum.STANDBY.ordinal());
            return;
        }
        setPowerOnMode(FacPropertyDemo.PowerModeEnum.ON);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setPrescale(int i) {
        Log.i("Fac_FacFunction_Adapter", "setPreScale =" + i);
        this.mHisenseTvAPI.setPrescaleValue(MtkTvConfigTypeBase.CFG_FACTORY_AUD_SOC_PRESCALE, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setRCUT(int i) {
        this.mHisenseTvAPI.setWhiteBalanceValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_OFFSET_R, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setRDRV(int i) {
        System.out.println("set RDRV = " + i);
        this.rdrv_value[getCurrentWhiteBalanceSourceGroup().ordinal()][getModelPanel().ordinal()][get2Dor3D().ordinal()] = i;
        this.mHisenseTvAPI.setWhiteBalanceValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_GAIN_R, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setRDRVOffset(int i) {
        this.mHisenseTvAPI.setWhiteBalanceValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_GAIN_R, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setSavingMode(FacPropertyDemo.SavingMode savingMode) {
        System.out.println("set saving mode = " + savingMode.toString());
        this.savingmode_value = savingMode;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setSharpness(int i) {
        this.mHisenseTvAPI.setPictureModeDetailValue(MtkTvConfigTypeBase.CFG_VIDEO_VID_SHP, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setSSCDDREnable(int i) {
        Log.i("Fac_FacFunction_Adapter", "setSSCDDREnable called, value:" + i);
        Log.i("Fac_FacFunction_Adapter", "setSSCDDREnable called ddr2, return = " + this.mHisenseTvAPI.setDdrSscEnable(i == 0));
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setSSCDDRSpan(int i) {
        if (i > FacPropertyDemo.HIFAC_MAX_VBYONE_SPAN) {
            Log.i("Fac_FacFunction_Adapter", "setSSCDDRSpan called, value is too big:" + i);
            return;
        }
        Log.i("Fac_FacFunction_Adapter", "setSSCDDRSpan called, param:" + i);
        Log.i("Fac_FacFunction_Adapter", "setSSCDDRSpan called ddr2, return = " + this.mHisenseTvAPI.setDdrSpan(i));
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setToFac(FacPropertyDemo.ToFacEnum toFacEnum) {
        Log.i("Fac_FacFunction_Adapter", "set tofac mode = " + toFacEnum.toString());
        this.mHisenseTvAPI.setToFacMode(toFacEnum.ordinal());
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setVideoCurveModeGroup(FacPropertyDemo.VideoCurveModeGroup videoCurveModeGroup) {
        this.picturecurvemode_value = videoCurveModeGroup;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setVolume(int i) {
        Log.i("Fac_FacFunction_Adapter", "set volume  = " + i);
        this.mAudioManager.setStreamVolume(3, (this.mAudioManager.getStreamMaxVolume(3) * i) / 100, 1);
    }

    public void setVolumeWithoutUI(int i) {
        Log.i("Fac_FacFunction_Adapter", "set setVolumeWithoutUI  = " + i);
        this.mAudioManager.setStreamVolume(3, (this.mAudioManager.getStreamMaxVolume(3) * i) / 100, 8);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setVolumeCurve0(int i) {
        Log.i("Fac_FacFunction_Adapter", "setVolumeCurveMin called, param:" + i);
        this.mHisenseTvAPI.setAudioCurveDetailValue(MtkTvConfigTypeBase.CFG_FACTORY_AUD_VOL_MIN, i);
        showNotImplementToast("setVolumeCurve0");
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setVolumeCurve100(int i) {
        Log.i("Fac_FacFunction_Adapter", "setVolumeCurveMax called, param:" + i);
        this.mHisenseTvAPI.setAudioCurveDetailValue(MtkTvConfigTypeBase.CFG_FACTORY_AUD_VOL_MAX, i);
        showNotImplementToast("setVolumeCurve100");
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setVolumeCurve25(int i) {
        Log.i("Fac_FacFunction_Adapter", "setVolumeCurve20 called, param:" + i);
        this.mHisenseTvAPI.setAudioCurveDetailValue(MtkTvConfigTypeBase.CFG_FACTORY_AUD_VOL_20, i);
        showNotImplementToast("setVolumeCurve25");
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setVolumeCurve50(int i) {
        Log.i("Fac_FacFunction_Adapter", "setVolumeCurveMid called, param:" + i);
        this.mHisenseTvAPI.setAudioCurveDetailValue(MtkTvConfigTypeBase.CFG_FACTORY_AUD_VOL_MID, i);
        showNotImplementToast("showNotImplementToast");
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setVolumeCurve75(int i) {
        Log.i("Fac_FacFunction_Adapter", "setVolumeCurve75 called, param:" + i);
        this.mHisenseTvAPI.setAudioCurveDetailValue(MtkTvConfigTypeBase.CFG_FACTORY_AUD_VOL_75, i);
        showNotImplementToast("setVolumeCurve75");
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setVStart(int i) {
        this.overscan_vstart[getCurrentOverScanTiming().ordinal()] = i;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setZoomMode(FacPropertyDemo.ZoomMode zoomMode) {
        Log.i("Fac_FacFunction_Adapter", "setZoomMode = " + zoomMode);
        int iApToTil = ApVsTILmap.ApToTil(6, zoomMode.ordinal());
        Log.i("Fac_FacFunction_Adapter", "setZoomMode mtkValue= " + iApToTil);
        this.mHisenseTvAPI.setAspectRatio(iApToTil);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setZoomModeForEM(FacPropertyDemo.ZoomModeForEM zoomModeForEM) {
        Log.i("Fac_FacFunction_Adapter", "setZoomModeForEM = " + zoomModeForEM);
        int iApToTil = ApVsTILmap.ApToTil(23, zoomModeForEM.ordinal());
        Log.i("Fac_FacFunction_Adapter", "setZoomModeForEM mtkValue= " + iApToTil);
        this.mHisenseTvAPI.setAspectRatio(iApToTil);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void exitTvSource() {
        Log.i("Fac_FacFunction_Adapter", "exitTvSource ");
        this.mAtscCommons.exitTvSource();
    }

    @Override // com.hisense.tvcm.FacFunction
    public void switchSource(FacPropertyDemo.SourceEnum sourceEnum) {
        Log.i("Fac_FacFunction_Adapter", "switchSource SourceEnum ");
        if (FacPropertyDemo.HIFAC_MARKET_JP) {
            setSavingMode(FacTvsFunctionUtil.TVS_SAVING_MODE_STANDARD);
        }
        setSourceStatusToLog(sourceEnum);
        int tilValue = sourceEnum.getTilValue();
        Log.i("Fac_FacFunction_Adapter", "set source mtkvalue = " + tilValue);
        setLogToFile("source " + sourceEnum.getDisplayName());
        try {
            if (sourceEnum.getDisplayName().contains("TV")) {
                this.broadbase.syncStop("main", true);
                if (FacPropertyDemo.HIFAC_MARKET_JP) {
                    this.mHisenseTvAPI.setMiscInputInfo(1);
                    setTvsTvTuner(FacTvsFunctionUtil.FAC_TVS_TUNE_TYPE_TDJ62);
                } else if (FacPropertyDemo.HIFAC_ATSC30_SUPPORT) {
                    this.mHisenseTvAPI.setMiscInputInfo(1);
                    this.mAtscCommons.changeToTvSource();
                } else {
                    this.mMtkTvInputSource.changeInputSourcebySourceid(tilValue, "main");
                }
            } else {
                if (FacPropertyDemo.HIFAC_MARKET_JP) {
                    this.mHisenseTvAPI.setMiscInputInfo(0);
                    if (sourceEnum.toString().contains("HDMI1")) {
                        this.mFacTvsTvManager.setExternelInput(FacTvsFunctionUtil.TVS_SOURCE_HDMI, 1);
                    } else if (sourceEnum.toString().contains("HDMI2")) {
                        this.mFacTvsTvManager.setExternelInput(FacTvsFunctionUtil.TVS_SOURCE_HDMI, 2);
                    } else if (sourceEnum.toString().contains("HDMI3")) {
                        this.mFacTvsTvManager.setExternelInput(FacTvsFunctionUtil.TVS_SOURCE_HDMI, 3);
                    } else if (sourceEnum.toString().contains("HDMI4")) {
                        this.mFacTvsTvManager.setExternelInput(FacTvsFunctionUtil.TVS_SOURCE_HDMI, 4);
                    } else if (sourceEnum.toString().contains(MtkTvRatingConvert2Goo.RATING_STR_AV)) {
                        this.mFacTvsTvManager.setExternelInput(FacTvsFunctionUtil.TVS_SOURCE_AV, 1);
                    }
                } else if (FacPropertyDemo.HIFAC_ATSC30_SUPPORT) {
                    this.mHisenseTvAPI.setMiscInputInfo(0);
                    this.mAtscCommons.exitTvSource();
                }
                this.mMtkTvInputSource.changeInputSourcebySourceid(tilValue, "main");
            }
            if (sourceEnum.toString().contains("HDMI")) {
                setActiveContrast(0);
                if (FacPropertyDemo.HIFAC_MARKET.equals(FacPropertyDemo.MarketType.EM)) {
                    if (FacPropertyDemo.HIFAC_MARKET_TW) {
                        setPictureModeTW(FacPropertyDemo.PictureModeTW_Toshiba.STANDARD);
                    } else {
                        setPictureModeEM(FacPropertyDemo.PictureModeEM.STANDARD);
                    }
                } else {
                    setPictureMode(FacPropertyDemo.PictureMode.STANDARD);
                }
            }
            if (this.mSharedPreferences == null) {
                initSharedPreference();
            }
            this.mSharedPreferences.edit().putBoolean(sourceEnum.getDisplayName(), true).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (FacPropertyDemo.HIFAC_ATSC30_SUPPORT) {
            this.currentchannelInfo = this.mChannelList.getChannelInfoByUUID(this.mPlay.getCurrentPlayChannel());
        }
        setSourceStatusToAudioPicture(sourceEnum);
        if (FacPropertyDemo.HIFAC_MARKET_JP && FacPropertyDemo.HIFAC_IS_OLED_TVS) {
            if (sourceEnum.toString().contains("HDMI")) {
                setTvsTPC(FacTvsFunctionUtil.TVS_VALUE_ALL_OFF);
            } else {
                setTvsTPC(FacTvsFunctionUtil.TVS_VALUE_AUTO);
            }
        }
    }

    @Override // com.hisense.tvcm.FacFunction
    public String getFRCVersion() {
        return this.mFrcVersion;
    }

    @Override // com.hisense.tvcm.FacFunction
    public String getLCMRunningStatus() {
        String configString = this.mHisenseTvAPI.getConfigString(MtkTvConfigTypeBase.CFG_FACTORY_BLU_STATUS);
        Log.i("Fac_FacFunction_Adapter", "blu status  = " + configString);
        return configString;
    }

    @Override // com.hisense.tvcm.FacFunction
    public String getBacklightDriverVersion() {
        return this.mBluVersion;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setUartSwitch(int i) {
        this.mHisenseTvAPI.setUartCtrlState(i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getUartSwitchStatus() {
        return this.mHisenseTvAPI.getUartCtrlState();
    }

    @Override // com.hisense.tvcm.FacFunction
    public String getNewMachineID() {
        String serialNewNumber = this.mHisenseTvAPI.getSerialNewNumber(MtkTvConfigTypeBase.CFG_FACTORY_FAC_SERIAL_NEW_NUMBER);
        Log.i("Fac_FacFunction_Adapter", "get fangcuanhuo hao = " + serialNewNumber);
        return serialNewNumber != null ? serialNewNumber : "";
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setNewMachineID(String str) {
        Log.i("Fac_FacFunction_Adapter", "set fangcuanhuo hao = " + str);
        this.mHisenseTvAPI.setSerialNewNumber(MtkTvConfigTypeBase.CFG_FACTORY_FAC_SERIAL_NEW_NUMBER, str);
    }

    @Override // com.hisense.tvcm.FacFunction
    public String getChipID() {
        String chipId = this.mHisenseTvAPI.getChipId();
        Log.i("Fac_FacFunction_Adapter", "get CFG_SYSTEM_CHIP_ID chipId = " + chipId);
        return chipId != null ? chipId : "";
    }

    @Override // com.hisense.tvcm.FacFunction
    public void initMtkManagers(Context context) {
        Log.i("Fac_FacFunction_Adapter", "initMtkManagers");
        MtkTvBroadcast.getInstance();
        this.mMtkTvConfig = MtkTvConfig.getInstance();
        this.mMtkTvVolCtrl = MtkTvVolCtrl.getInstance();
        this.mMtkTvInputSource = MtkTvInputSource.getInstance();
        this.mHisenseTvAPI = HisenseTvAPI.getInstance(context);
        this.mHisVideoApi = new HisVideoApi();
        this.mHisAudioApi = new HisAudioApi();
        MtkTvBanner.getInstance();
        MtkTvMultiView.getInstance();
        this.mMtkTvCI = MtkTvCI.getInstance(0);
        this.broadbase = new MtkTvBroadcastBase();
        this.mAudioManager = (AudioManager) mContext.getSystemService("audio");
        if (FacPropertyDemo.HIFAC_ATSC30_SUPPORT) {
            this.mFactory = Factory.getInstance();
            this.mChannelList = ChannelList.getInstance();
            this.mPlay = Play.getInstance();
            this.mAtscCommons = Commons.getInstance();
        }
        if (FacPropertyDemo.HIFAC_ATSC30_SUPPORT) {
            getChannelListByTunerMode(0);
            getChannelListByTunerMode(1);
        } else {
            getAirChannelList();
            getCableChannelList();
        }
        this.mHisenseTvAPI.setConfigValue(MtkTvConfigTypeBase.CFG_MISC_AV_COND_MMP_MODE, 0);
        this.mSourceList = new ArrayList();
        updateCurrentSupportSourceFromMTK();
        FacPropertyDemo.setVcomProp(isVcomSupport());
        FacPropertyDemo.setDualVcomProp(isSupportSecondVcom());
        this.mRenderPattern = new RenderPattern(1);
        this.mHisenseTvAPI.setWifiCheckConfigValue(0);
        if (FacPropertyDemo.HIFAC_MIC_SUPPORT) {
            this.mHisenseTvAPI.initMicLed();
        }
        FacParamManager.FAC_ATTEST_COUNT = getCurrentAttestKeyCount();
        this.mMtkTvHighLevel = new MtkTvHighLevel();
        if (FacPropertyDemo.HIFAC_MARKET_JP) {
            CESIConstants.initData();
            FacTvsFunctionUtil.initData();
            setCountryIndex(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_LICENSE_SERVER_CONNECTION_ERROR);
            setLogoIndex(95);
            setLangIndex(68);
            this.mTvsKey = new FacTvsKeyWriter();
            FacTvsFunctionManager facTvsFunctionManager = new FacTvsFunctionManager();
            this.mTvsFunc = facTvsFunctionManager;
            facTvsFunctionManager.setStartApplication();
            this.mFacTvsTvManager = new FacTvsTvManager();
            this.mFacTvsAqPqmanager = new FacTvsAqPqManager();
            this.mFacTvsSourceManager = new FacTvsSourceManager();
        }
        if (FacPropertyDemo.HIFAC_IS_OLED) {
            this.mPanelProtectManager = PanelProtectManager.getInstance(mContext);
        }
        setODStatus();
        getQDAUUID();
    }

    @Override // com.hisense.tvcm.FacFunction
    public void writeToExternalControl(int i, byte[] bArr, int i2) {
        Log.i("Fac_FacFunction_Adapter", "writeToExternalControl action=" + i + "   data=" + bArr + "  len=" + i2);
        showNotImplementToast("writeToExternalControl");
        this.mHisenseTvAPI.writeToExternalControl(i, bArr, i2, 1);
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean setWBPattern(int i) {
        int[] iArr;
        int[] iArr2;
        Log.i("Fac_FacFunction_Adapter", "setWBPattern times = " + i);
        if (i == 20) {
            if (isVolumeMute()) {
                sentMuteHideBroadcast(true);
            }
            iArr2 = new int[]{1, 0};
        } else {
            if (i == 21) {
                if (isVolumeMute()) {
                    sentMuteHideBroadcast(false);
                }
                iArr = new int[]{0, i};
            } else {
                iArr = new int[]{1, i};
            }
            iArr2 = iArr;
        }
        return this.mHisenseTvAPI.setTestPatternValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_WB_TEST_PATTERN, iArr2, 2);
    }

    private void sentMuteHideBroadcast(boolean z) {
        Log.i("Fac_FacFunction_Adapter", "sendbroadcast hide mute icon");
        Intent intent = new Intent("com.hisense.intent.HIDE_MUTE_ICON");
        intent.putExtra("hide_mute_icon", z);
        intent.addFlags(268435456);
        mContext.sendBroadcast(intent);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getBCUTOffset() {
        showNotImplementToast("getBCUTOffset");
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public String getDeviceID() {
        String str = "NG";
        String keyNameFromFile = this.mKeyWriter.checkWideVineKey() ? this.mKeyWriter.readKeyNameFromFile(HisenseTvFactoryApi.FacKeyID.KEY_WIDEVINE_ID) : "NG";
        if (keyNameFromFile != null && !keyNameFromFile.isEmpty()) {
            str = keyNameFromFile;
        }
        Log.i("Fac_FacFunction_Adapter", " getDeviceID keyName " + str);
        return str;
    }

    @Override // com.hisense.tvcm.FacFunction
    public String getPlayReadyKey() {
        String keyNameFromFile;
        if (!this.mKeyWriter.checkPlaready30Key() || (keyNameFromFile = this.mKeyWriter.readKeyNameFromFile(HisenseTvFactoryApi.FacKeyID.KEY_PLAYREADY30_ID)) == null || keyNameFromFile.isEmpty()) {
            return "NG";
        }
        Log.i("Fac_FacFunction_Adapter", " getPlayReady30Key keyName " + keyNameFromFile);
        return keyNameFromFile;
    }

    @Override // com.hisense.tvcm.FacFunction
    public String getPlayReady40Key() {
        String keyNameFromFile;
        if (!this.mKeyWriter.checkPlaready40Key() || (keyNameFromFile = this.mKeyWriter.readKeyNameFromFile(HisenseTvFactoryApi.FacKeyID.KEY_PLAYREADY40_ID)) == null || keyNameFromFile.isEmpty()) {
            return "NG";
        }
        Log.i("Fac_FacFunction_Adapter", " getPlayReady40Key keyName " + keyNameFromFile);
        return keyNameFromFile;
    }

    @Override // com.hisense.tvcm.FacFunction
    public String getPlayReady25Key() {
        String keyNameFromFile;
        if (!this.mKeyWriter.checkPlaready25Key() || (keyNameFromFile = this.mKeyWriter.readKeyNameFromFile(HisenseTvFactoryApi.FacKeyID.KEY_PLAYREADY25_ID)) == null || keyNameFromFile.isEmpty()) {
            return "NG";
        }
        Log.i("Fac_FacFunction_Adapter", " getPlayReady25Key keyName " + keyNameFromFile);
        return keyNameFromFile;
    }

    @Override // com.hisense.tvcm.FacFunction
    public String getHdcp14Key() {
        String str = "NG";
        String keyNameFromFile = this.mKeyWriter.checkHDCP14Key() ? this.mKeyWriter.readKeyNameFromFile(HisenseTvFactoryApi.FacKeyID.KEY_HDCP14_ID) : "NG";
        if (keyNameFromFile != null && !keyNameFromFile.isEmpty()) {
            str = keyNameFromFile;
        }
        Log.i("Fac_FacFunction_Adapter", " getHdcpKey14 keyName " + str);
        return str;
    }

    @Override // com.hisense.tvcm.FacFunction
    public String getHdcp2xKey() {
        String str = "NG";
        String keyNameFromFile = this.mKeyWriter.checkHDCP22Key() ? this.mKeyWriter.readKeyNameFromFile(HisenseTvFactoryApi.FacKeyID.KEY_HDCP22_ID) : "NG";
        if (keyNameFromFile != null && !keyNameFromFile.isEmpty()) {
            str = keyNameFromFile;
        }
        Log.i("Fac_FacFunction_Adapter", " getHdcpKey22 keyName " + str);
        return str;
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean getProductionKey() {
        Log.i("Fac_FacFunction_Adapter", " getProductionKey exist 1");
        return false;
    }

    @Override // com.hisense.tvcm.FacFunction
    public String getMacAddress() {
        String facMacAddress = this.mHisenseTvAPI.getFacMacAddress();
        Log.i("Fac_FacFunction_Adapter", "get mac Address is " + facMacAddress);
        if (facMacAddress == null || facMacAddress.isEmpty()) {
            facMacAddress = "00:00:00:00:00:00";
        }
        Log.i("Fac_FacFunction_Adapter", "ret mac Address is " + facMacAddress);
        return facMacAddress;
    }

    @Override // com.hisense.tvcm.FacFunction
    public String getBTMacAddress() {
        if (FacPropertyDemo.HIFAC_BLUETOOTH_SUPPORT) {
            return BluetoothAdapter.getDefaultAdapter().isEnabled() ? BluetoothAdapter.getDefaultAdapter().getAddress() : "00:00:00:00:00:00";
        }
        return null;
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean clearHdcp14Key() {
        Log.i("Fac_FacFunction_Adapter", "enter clearHdcp14Key!!!");
        showNotImplementToast("clearHdcp14Key");
        return false;
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean clearHdcp22Key() {
        Log.i("Fac_FacFunction_Adapter", "enter clearHdcp22Key!!!");
        showNotImplementToast("clearHdcp22Key");
        return false;
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean clearWideVineKey() {
        Log.i("Fac_FacFunction_Adapter", "enter clearWideVineKey!!!");
        showNotImplementToast("clearWideVineKey");
        return false;
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean clearPlayReadyKey() {
        showNotImplementToast("clearPlayReadyKey");
        return false;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getGCUTOffset() {
        showNotImplementToast("getGCUTOffset");
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getRCUTOffset() {
        showNotImplementToast("getRCUTOffset");
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getSSCDDRModu() {
        Log.i("Fac_FacFunction_Adapter", "getSSCDDRModu called");
        return this.mHisenseTvAPI.getDdrSscFreq();
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getSSCVByOneEnable() {
        Log.i("Fac_FacFunction_Adapter", "getSSCVByOneEnable called, value:" + this.mHisenseTvAPI.getLvdsMode());
        return this.mHisenseTvAPI.getLVDSEnable();
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getLocaldimmingEnable() {
        return this.mHisenseTvAPI.getLocaldimmingEnable();
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getSSCVByOneSpan() {
        int sSCVByOneSpan = this.mHisenseTvAPI.getSSCVByOneSpan();
        Log.i("Fac_FacFunction_Adapter", "getSSCVByOneSpan called value = " + sSCVByOneSpan);
        return sSCVByOneSpan / 1000;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getSSCVByOneRange() {
        Log.i("Fac_FacFunction_Adapter", "getSSCVByOneRange called,value/10");
        return this.mHisenseTvAPI.getSSCVByOneRange();
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setBCUTOffset(int i) {
        this.mHisenseTvAPI.setWhiteBalanceValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_OFFSET_B, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setGCUTOffset(int i) {
        this.mHisenseTvAPI.setWhiteBalanceValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_OFFSET_G, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean setWideVineKey(String str) {
        int iFactoryWriteWidevine = this.mKeyWriter.factoryWriteWidevine(str, false);
        Log.i("Fac_FacFunction_Adapter", "setWideVineKey result = " + iFactoryWriteWidevine);
        setLogToFile("setWideVineKey " + iFactoryWriteWidevine);
        updateFacLogStatus(16);
        if (iFactoryWriteWidevine == 0) {
            restartWidevineDrmService();
        }
        return iFactoryWriteWidevine == 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean setProductionKey(String str) {
        Log.i("Fac_FacFunction_Adapter", "setProductionKey result = 1  keypath = " + str);
        return false;
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean setHdcp14Key(String str) {
        int iFactoryWriteHdcp14Key = this.mKeyWriter.factoryWriteHdcp14Key(str, false);
        Log.i("Fac_FacFunction_Adapter", "setHdcp14Key result = " + iFactoryWriteHdcp14Key);
        setLogToFile("sethdcp14 " + iFactoryWriteHdcp14Key);
        updateFacLogStatus(2);
        return iFactoryWriteHdcp14Key == 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean setHdcp2xKey(String str) {
        int iFactoryWriteHdcp22Key = this.mKeyWriter.factoryWriteHdcp22Key(str, false);
        Log.i("Fac_FacFunction_Adapter", "setHdcp2xKey result = " + iFactoryWriteHdcp22Key);
        setLogToFile("sethdcp22 " + iFactoryWriteHdcp22Key);
        updateFacLogStatus(4);
        return iFactoryWriteHdcp22Key == 0;
    }

    public boolean setPlayReadyBgcertKey(String str) {
        int iFactoryWriteKey = this.mMtkTvFactoryService.factoryWriteKey("PR30_BGCERT", str);
        Log.i("Fac_FacFunction_Adapter", "setPlayReadyBgcertKey result = " + iFactoryWriteKey);
        setLogToFile("setPlayReadyBgcertKey " + iFactoryWriteKey);
        return iFactoryWriteKey == 0;
    }

    public boolean setPlayReadyDevCertKey(String str) {
        int iFactoryWriteKey = this.mMtkTvFactoryService.factoryWriteKey("PR30_DEVCERT", str);
        Log.i("Fac_FacFunction_Adapter", "setPlayReadyDevCertKey result = " + iFactoryWriteKey);
        setLogToFile("setPlayReadyDevCertKey " + iFactoryWriteKey);
        return iFactoryWriteKey == 0;
    }

    public boolean setPlayReadyPrivKey(String str) {
        int iFactoryWriteKey = this.mMtkTvFactoryService.factoryWriteKey("PR30_PRIV", str);
        Log.i("Fac_FacFunction_Adapter", "setPlayReadyPrivKey result = " + iFactoryWriteKey);
        setLogToFile("setPlayReadyPrivKey " + iFactoryWriteKey);
        return iFactoryWriteKey == 0;
    }

    public boolean setPlayReadyZgPrivKey(String str) {
        int iFactoryWriteKey = this.mMtkTvFactoryService.factoryWriteKey("PR30_ZGPRIV", str);
        Log.i("Fac_FacFunction_Adapter", "setPlayReadyZgPrivKey result = " + iFactoryWriteKey);
        setLogToFile("setPlayReadyZgPrivKey " + iFactoryWriteKey);
        return iFactoryWriteKey == 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean setQDAKey(String str) {
        int iFactoryWriteQDAKey = this.mKeyWriter.factoryWriteQDAKey(str, false);
        Log.i("Fac_FacFunction_Adapter", "setQDAKey result = " + iFactoryWriteQDAKey);
        setLogToFile("setQDAKey " + iFactoryWriteQDAKey);
        updateFacLogStatus(2097152);
        return iFactoryWriteQDAKey == 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public String getQDAKey() {
        String str = "NG";
        String keyNameFromFile = this.mKeyWriter.checkQDAKey() ? this.mKeyWriter.readKeyNameFromFile(HisenseTvFactoryApi.FacKeyID.KEY_QDA_ID) : "NG";
        if (keyNameFromFile != null && !keyNameFromFile.isEmpty()) {
            Log.i("Fac_FacFunction_Adapter", " getQDAKey keyName " + keyNameFromFile);
            StringBuilder sb = new StringBuilder();
            sb.append(getQDAUUID());
            sb.append(".bin");
            str = keyNameFromFile.endsWith(sb.toString()) ? keyNameFromFile : "NG";
            Log.i("Fac_FacFunction_Adapter", " getQDAKey1 keyName " + str);
        }
        return str;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int[] getVcomsValue() {
        int[] iArr = {0, 0};
        byte[] vcomsValueByte = this.mHisenseTvAPI.getVcomsValueByte();
        if (vcomsValueByte != null) {
            for (int i = 0; i < 4; i++) {
                iArr[0] = iArr[0] + ((vcomsValueByte[i] & 255) << (i * 8));
            }
            Log.i("Fac_FacFunction_Adapter", "vcomVal1 = " + iArr[0]);
            for (int i2 = 0; i2 < 4; i2++) {
                iArr[1] = iArr[1] + ((vcomsValueByte[i2 + 4] & 255) << (i2 * 8));
            }
            Log.i("Fac_FacFunction_Adapter", "vcomVal2 = " + iArr[1]);
        }
        return iArr;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int checkTconTempVr() {
        int oledtempLutVersion = this.mHisenseTvAPI.getOledtempLutVersion();
        Log.i("Fac_FacFunction_Adapter", " checkTconTempVr : " + oledtempLutVersion);
        return oledtempLutVersion;
    }

    @Override // com.hisense.tvcm.FacFunction
    public String getTconTempVersion() {
        return this.mHisenseTvAPI.getOledtcontempLutVersion();
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setRCUTOffset(int i) {
        showNotImplementToast("setRCUTOffset");
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean setSensorEnable(boolean z) {
        return this.mHisenseTvAPI.setSensorEnable(z) == 1;
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean getSensorEnableStatus() {
        return this.mHisenseTvAPI.getSensorEnableStatus();
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setSSCDDRModu(int i) {
        Log.i("Fac_FacFunction_Adapter", "setSSCDDRModu called, param:" + i);
        Log.i("Fac_FacFunction_Adapter", "setSSCDDRModu called ddr2, return = " + this.mHisenseTvAPI.setSSCDDRModu(i));
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setSSCVByOneEnable(int i) {
        this.mHisenseTvAPI.setSSCVByOneEnable(i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setSSCVByOneSpan(int i) {
        Log.i("Fac_FacFunction_Adapter", "setSSCVByOneSpan called, param:" + i);
        this.mHisenseTvAPI.setLvdsDetailValue(i * 1000);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setSSCVByOneRange(int i) {
        Log.i("Fac_FacFunction_Adapter", "setSSCVByOneRange called, param: 10 * " + i);
        this.mHisenseTvAPI.setSSCVByOneRange(i);
    }

    public void switchSource(int i) {
        Log.i("Fac_FacFunction_Adapter", "switch mtk source is " + i);
        if (FacPropertyDemo.HIFAC_MARKET_JP) {
            setSavingMode(FacTvsFunctionUtil.TVS_SAVING_MODE_STANDARD);
        }
        setSourceStatusToLog(i);
        setLogToFile("source index " + i);
        if (i == FacPropertyDemo.SourceEnum.TV.getTilValue()) {
            this.broadbase.syncStop("main", true);
            if (FacPropertyDemo.HIFAC_MARKET_JP) {
                this.mHisenseTvAPI.setConfigValue(MtkTvConfigTypeBase.CFG_MISC_INPUT_INFO_UPDATE, 1);
                setTvsTvTuner(FacTvsFunctionUtil.FAC_TVS_TUNE_TYPE_TDJ62);
            } else if (FacPropertyDemo.HIFAC_ATSC30_SUPPORT) {
                this.mHisenseTvAPI.setMiscInputInfo(1);
                this.mAtscCommons.changeToTvSource();
            } else {
                this.mMtkTvInputSource.changeInputSourcebySourceid(i, "main");
            }
        } else {
            if (FacPropertyDemo.HIFAC_MARKET_JP) {
                if (i == FacPropertyDemo.SourceEnum.HDMI1.getTilValue()) {
                    this.mFacTvsTvManager.setExternelInput(FacTvsFunctionUtil.TVS_SOURCE_HDMI, 1);
                } else if (i == FacPropertyDemo.SourceEnum.HDMI2.getTilValue()) {
                    this.mFacTvsTvManager.setExternelInput(FacTvsFunctionUtil.TVS_SOURCE_HDMI, 2);
                } else if (i == FacPropertyDemo.SourceEnum.HDMI3.getTilValue()) {
                    this.mFacTvsTvManager.setExternelInput(FacTvsFunctionUtil.TVS_SOURCE_HDMI, 3);
                } else if (i == FacPropertyDemo.SourceEnum.HDMI4.getTilValue()) {
                    this.mFacTvsTvManager.setExternelInput(FacTvsFunctionUtil.TVS_SOURCE_HDMI, 4);
                } else if (i == FacPropertyDemo.SourceEnum.AV.getTilValue()) {
                    this.mFacTvsTvManager.setExternelInput(FacTvsFunctionUtil.TVS_SOURCE_AV, 1);
                }
                this.mHisenseTvAPI.setConfigValue(MtkTvConfigTypeBase.CFG_MISC_INPUT_INFO_UPDATE, 0);
            } else if (FacPropertyDemo.HIFAC_ATSC30_SUPPORT) {
                this.mHisenseTvAPI.setMiscInputInfo(0);
                this.mAtscCommons.exitTvSource();
            }
            this.mMtkTvInputSource.changeInputSourcebySourceid(i, "main");
        }
        if (FacPropertyDemo.HIFAC_ATSC30_SUPPORT) {
            this.currentchannelInfo = this.mChannelList.getChannelInfoByUUID(this.mPlay.getCurrentPlayChannel());
        }
        setSourceStatusToAudioPicture(i);
        if (i == FacPropertyDemo.SourceEnum.HDMI1.getTilValue() || i == FacPropertyDemo.SourceEnum.HDMI2.getTilValue() || i == FacPropertyDemo.SourceEnum.HDMI3.getTilValue() || i == FacPropertyDemo.SourceEnum.HDMI4.getTilValue()) {
            setActiveContrast(0);
            if (FacPropertyDemo.HIFAC_MARKET.equals(FacPropertyDemo.MarketType.EM)) {
                if (FacPropertyDemo.HIFAC_MARKET_TW) {
                    setPictureModeTW(FacPropertyDemo.PictureModeTW_Toshiba.STANDARD);
                } else {
                    setPictureModeEM(FacPropertyDemo.PictureModeEM.STANDARD);
                }
            } else {
                setPictureMode(FacPropertyDemo.PictureMode.STANDARD);
            }
        }
        if (FacPropertyDemo.HIFAC_MARKET_JP && FacPropertyDemo.HIFAC_IS_OLED_TVS) {
            if (i == FacPropertyDemo.SourceEnum.HDMI1.getTilValue() || i == FacPropertyDemo.SourceEnum.HDMI2.getTilValue() || i == FacPropertyDemo.SourceEnum.HDMI3.getTilValue() || i == FacPropertyDemo.SourceEnum.HDMI4.getTilValue()) {
                setTvsTPC(FacTvsFunctionUtil.TVS_VALUE_ALL_OFF);
            } else {
                setTvsTPC(FacTvsFunctionUtil.TVS_VALUE_AUTO);
            }
        }
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getVcomVoltage() {
        Log.i("Fac_FacFunction_Adapter", "getVcomVoltage = " + this.mHisenseTvAPI.getVcomValue(MtkTvConfigTypeBase.CFG_FACTORY_VCOM_VALUE));
        return this.mHisenseTvAPI.getVcomValue(MtkTvConfigTypeBase.CFG_FACTORY_VCOM_VALUE);
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean setVcomVoltage(int i) {
        Log.i("Fac_FacFunction_Adapter", "setVcomVoltage = " + i);
        return this.mHisenseTvAPI.setVcomValue(MtkTvConfigTypeBase.CFG_FACTORY_VCOM_VALUE, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean saveVcomVoltage(int i) {
        Log.i("Fac_FacFunction_Adapter", "saveVcomVoltage = " + i);
        if (this.mSharedPreferences == null) {
            initSharedPreference();
        }
        if (this.mHisenseTvAPI.setVcomValue(MtkTvConfigTypeBase.CFG_FACTORY_VCOM_SAVE, i)) {
            Log.i("Fac_FacFunction_Adapter", "save vcom successfully!");
            this.mSharedPreferences.edit().putBoolean("saveVcomResult", true).commit();
            return true;
        }
        this.mSharedPreferences.edit().putBoolean("saveVcomResult", false).commit();
        Log.i("Fac_FacFunction_Adapter", "fail to save vcom!");
        return false;
    }

    @Override // com.hisense.tvcm.FacFunction
    public String getVideoInfo() {
        String str;
        String str2;
        String str3;
        String str4;
        MtkTvBanner.getInstance();
        Log.i("Fac_FacFunction_Adapter", "getVideoInfo");
        this.mStrVideoInfo = "";
        FacPropertyDemo.SourceEnum currentSource = getCurrentSource();
        Log.i("Fac_FacFunction_Adapter", "inputSource = " + currentSource);
        switch (AnonymousClass7.$SwitchMap$com$hisense$tvcm$FacPropertyDemo$SourceEnum[currentSource.ordinal()]) {
            case 1:
            case 2:
            case 3:
                Log.i("Fac_FacFunction_Adapter", "case tv and dtv ");
                String videoFormatInfo = getVideoFormatInfo();
                this.mStrVideoInfo = videoFormatInfo;
                if (FacPropertyDemo.HIFAC_MARKET_SA) {
                    if (this.mainBoardTestIsStrat && "" != videoFormatInfo && videoFormatInfo != null) {
                        updateFacCheckLogStatus(8);
                    }
                    int tunerMode = getTunerMode();
                    if (tunerMode == 0) {
                        this.mStrVideoInfo += "\nAIR";
                    } else if (tunerMode == 1) {
                        this.mStrVideoInfo += "\nCABLE";
                    }
                }
                break;
            case 4:
                String videoFormatInfo2 = getVideoFormatInfo();
                this.mStrVideoInfo = videoFormatInfo2;
                if (FacPropertyDemo.HIFAC_MARKET_SA && this.mainBoardTestIsStrat && "" != videoFormatInfo2 && videoFormatInfo2 != null) {
                    updateFacCheckLogStatus(16);
                }
                break;
            case 7:
            case 8:
            case 9:
            case 10:
                this.mStrVideoInfo = getVideoFormatInfo();
                if (FacPropertyDemo.HIFAC_MARKET_SA && this.mainBoardTestIsStrat) {
                    if (currentSource.toString().contains("HDMI1") && "" != (str4 = this.mStrVideoInfo) && str4 != null) {
                        updateFacCheckLogStatus(512);
                    } else if (currentSource.toString().contains("HDMI2") && "" != (str3 = this.mStrVideoInfo) && str3 != null) {
                        updateFacCheckLogStatus(1024);
                    } else if (currentSource.toString().contains("HDMI3") && "" != (str2 = this.mStrVideoInfo) && str2 != null) {
                        updateFacCheckLogStatus(2048);
                    } else if (currentSource.toString().contains("HDMI4") && "" != (str = this.mStrVideoInfo) && str != null) {
                        updateFacCheckLogStatus(4096);
                    }
                }
                break;
            case 12:
                this.mStrVideoInfo = getVideoFormatInfo();
                break;
        }
        Log.i("Fac_FacFunction_Adapter", "mStrVideoInfo = " + this.mStrVideoInfo + "FrameRate=" + getFrameRate());
        return this.mStrVideoInfo;
    }

    /* JADX INFO: renamed from: com.hisense.tvcm.FacFunction_Adapter$7, reason: invalid class name */
    static /* synthetic */ class AnonymousClass7 {
        static final /* synthetic */ int[] $SwitchMap$com$hisense$tvcm$FacPropertyDemo$SourceEnum;
        static final /* synthetic */ int[] $SwitchMap$com$hisense$tvcm$FacPropertyDemo$VideoCurveModeGroup;

        static {
            int[] iArr = new int[FacPropertyDemo.SourceEnum.values().length];
            $SwitchMap$com$hisense$tvcm$FacPropertyDemo$SourceEnum = iArr;
            try {
                iArr[FacPropertyDemo.SourceEnum.TV.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$hisense$tvcm$FacPropertyDemo$SourceEnum[FacPropertyDemo.SourceEnum.DTV.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$hisense$tvcm$FacPropertyDemo$SourceEnum[FacPropertyDemo.SourceEnum.ATV.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$hisense$tvcm$FacPropertyDemo$SourceEnum[FacPropertyDemo.SourceEnum.AV.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$hisense$tvcm$FacPropertyDemo$SourceEnum[FacPropertyDemo.SourceEnum.AV1.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$hisense$tvcm$FacPropertyDemo$SourceEnum[FacPropertyDemo.SourceEnum.AV2.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$hisense$tvcm$FacPropertyDemo$SourceEnum[FacPropertyDemo.SourceEnum.HDMI1.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$hisense$tvcm$FacPropertyDemo$SourceEnum[FacPropertyDemo.SourceEnum.HDMI2.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$hisense$tvcm$FacPropertyDemo$SourceEnum[FacPropertyDemo.SourceEnum.HDMI3.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$hisense$tvcm$FacPropertyDemo$SourceEnum[FacPropertyDemo.SourceEnum.HDMI4.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$hisense$tvcm$FacPropertyDemo$SourceEnum[FacPropertyDemo.SourceEnum.VGA.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$hisense$tvcm$FacPropertyDemo$SourceEnum[FacPropertyDemo.SourceEnum.COMPONENT.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            int[] iArr2 = new int[FacPropertyDemo.VideoCurveModeGroup.values().length];
            $SwitchMap$com$hisense$tvcm$FacPropertyDemo$VideoCurveModeGroup = iArr2;
            try {
                iArr2[FacPropertyDemo.VideoCurveModeGroup.CONTRAST.ordinal()] = 1;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$hisense$tvcm$FacPropertyDemo$VideoCurveModeGroup[FacPropertyDemo.VideoCurveModeGroup.COLOR.ordinal()] = 2;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$hisense$tvcm$FacPropertyDemo$VideoCurveModeGroup[FacPropertyDemo.VideoCurveModeGroup.SHARPNESS.ordinal()] = 3;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$hisense$tvcm$FacPropertyDemo$VideoCurveModeGroup[FacPropertyDemo.VideoCurveModeGroup.BRIGHTNESS.ordinal()] = 4;
            } catch (NoSuchFieldError unused16) {
            }
        }
    }

    @Override // com.hisense.tvcm.FacFunction
    public String getTconVersion() {
        return this.mTconVersion;
    }

    @Override // com.hisense.tvcm.FacFunction
    public String getWideKey() {
        String str = "NG";
        String keyNameFromFile = this.mKeyWriter.checkWideVineKey() ? this.mKeyWriter.readKeyNameFromFile(HisenseTvFactoryApi.FacKeyID.KEY_WIDEVINE_ID) : "NG";
        if (keyNameFromFile != null && !keyNameFromFile.isEmpty()) {
            str = keyNameFromFile;
        }
        Log.i("Fac_FacFunction_Adapter", "getWideKey keyName = " + str);
        return str;
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean setFirstTimePowerOn(int i) {
        return this.mHisenseTvAPI.setFirstTimePowerOn(MtkTvConfigTypeBase.CFG_FACTORY_FIRST_POWER_ON, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void switchDTVrouteToDtmb() {
        changeChannel(12, 0);
    }

    public void cleanchannel() {
        if (FacPropertyDemo.HIFAC_ATSC30_SUPPORT) {
            this.mChannelList.cleanChannelListByTunerMode(0);
            this.mChannelList.cleanChannelListByTunerMode(1);
            Log.d("Fac_FacFunction_Adapter", "finish clean ATSC ChannelList.");
            return;
        }
        Log.d("Fac_FacFunction_Adapter", "cleanChannelList(1), return value = " + this.mHisChannelListApiImpl.cleanChannelsByTunerMode(0) + "\n");
        Log.d("Fac_FacFunction_Adapter", "cleanChannelList(2), return value = " + this.mHisChannelListApiImpl.cleanChannelsByTunerMode(1) + "\n");
        this.mHisenseTvAPI.setConfigValue(MtkTvConfigTypeBase.CFG_MISC_CHANNEL_STORE, 1);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void addTVPresetChannelForcityname(final String str) {
        Log.i("Fac_FacFunction_Adapter", "preset channle");
        setLogToFile("add channel " + str);
        new Thread(new Runnable() { // from class: com.hisense.tvcm.FacFunction_Adapter.4
            @Override // java.lang.Runnable
            public void run() {
                FacPropertyDemo.SourceEnum currentSource = FacFunction_Adapter.this.getCurrentSource();
                FacPropertyDemo.SourceEnum sourceEnum = FacPropertyDemo.SourceEnum.TV;
                if (currentSource != sourceEnum) {
                    FacFunction_Adapter.this.switchSource(sourceEnum);
                    try {
                        Thread.sleep(200L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                FacFunction_Adapter.this.cleanchannel();
                Log.i("Fac_FacFunction_Adapter", "city name =" + str);
                if (FacPropertyDemo.HIFAC_MARKET_JP) {
                    FacFunction_Adapter.this.mFacTvsTvManager.presetChannel(CESIConstants.getValue("FactoryPresetChannel_e", "HUANGDAO"));
                    return;
                }
                if (FacPropertyDemo.HIFAC_MARKET.equals(FacPropertyDemo.MarketType.EM)) {
                    if (FacPropertyDemo.HIFAC_MARKET_HK) {
                        if (str.equalsIgnoreCase("huangdao_hk")) {
                            FacFunction_Adapter.this.mHisChannelListApiImpl.addPresetChannel(31);
                        } else if (str.equalsIgnoreCase("qingdao_hk")) {
                            FacFunction_Adapter.this.mHisChannelListApiImpl.addPresetChannel(32);
                        } else if (str.equalsIgnoreCase("guiyang_hk")) {
                            FacFunction_Adapter.this.mHisChannelListApiImpl.addPresetChannel(33);
                        } else if (str.equalsIgnoreCase("jiangmen_hk")) {
                            FacFunction_Adapter.this.mHisChannelListApiImpl.addPresetChannel(34);
                        }
                    } else if (str.equalsIgnoreCase("huangdao_em")) {
                        FacFunction_Adapter.this.mHisChannelListApiImpl.addPresetChannel(5);
                    } else if (str.equalsIgnoreCase("qingdao")) {
                        FacFunction_Adapter.this.mHisChannelListApiImpl.addPresetChannel(6);
                    } else if (str.equalsIgnoreCase("guiyang")) {
                        FacFunction_Adapter.this.mHisChannelListApiImpl.addPresetChannel(7);
                    } else if (str.equalsIgnoreCase("jiangmen")) {
                        FacFunction_Adapter.this.mHisChannelListApiImpl.addPresetChannel(8);
                    } else if (str.equalsIgnoreCase("south africa")) {
                        FacFunction_Adapter.this.mHisChannelListApiImpl.addPresetChannel(13);
                    } else if (str.equalsIgnoreCase("algeria")) {
                        FacFunction_Adapter.this.mHisChannelListApiImpl.addPresetChannel(14);
                    } else if (str.equalsIgnoreCase("malaysia")) {
                        FacFunction_Adapter.this.mHisChannelListApiImpl.addPresetChannel(15);
                    } else if (str.equalsIgnoreCase("iran")) {
                        FacFunction_Adapter.this.mHisChannelListApiImpl.addPresetChannel(16);
                    } else if (str.equalsIgnoreCase("indonesia")) {
                        FacFunction_Adapter.this.mHisChannelListApiImpl.addPresetChannel(17);
                    } else if (str.equalsIgnoreCase("ruassia")) {
                        FacFunction_Adapter.this.mHisChannelListApiImpl.addPresetChannel(18);
                    } else if (str.equalsIgnoreCase("egypt")) {
                        FacFunction_Adapter.this.mHisChannelListApiImpl.addPresetChannel(19);
                    } else if (str.equalsIgnoreCase("shunde")) {
                        FacFunction_Adapter.this.mHisChannelListApiImpl.addPresetChannel(20);
                    } else if (str.equalsIgnoreCase("syria")) {
                        FacFunction_Adapter.this.mHisChannelListApiImpl.addPresetChannel(21);
                    } else if (str.equalsIgnoreCase("nigeria")) {
                        FacFunction_Adapter.this.mHisChannelListApiImpl.addPresetChannel(24);
                    } else if (str.equalsIgnoreCase("pakistan")) {
                        FacFunction_Adapter.this.mHisChannelListApiImpl.addPresetChannel(25);
                    } else if (str.equalsIgnoreCase("egypt tsb")) {
                        FacFunction_Adapter.this.mHisChannelListApiImpl.addPresetChannel(26);
                    } else if (str.equalsIgnoreCase("angola")) {
                        FacFunction_Adapter.this.mHisChannelListApiImpl.addPresetChannel(36);
                    } else if (str.equalsIgnoreCase("brandt")) {
                        FacFunction_Adapter.this.mHisChannelListApiImpl.addPresetChannel(37);
                    } else if (str.equalsIgnoreCase("polytron")) {
                        FacFunction_Adapter.this.mHisChannelListApiImpl.addPresetChannel(38);
                    }
                    FacFunction_Adapter.this.list_air.clear();
                    FacFunction_Adapter.this.list_cable.clear();
                    FacFunction_Adapter.this.getAirChannelList();
                    FacFunction_Adapter.this.getCableChannelList();
                    FacFunction_Adapter.this.changeChannel(904, 0);
                    return;
                }
                if (FacPropertyDemo.HIFAC_MARKET_SA) {
                    FacFunction_Adapter.this.mSAChannelType = 0;
                    if (str.equalsIgnoreCase("huangdao_sa")) {
                        FacFunction_Adapter.this.mHisChannelListApiImpl.addPresetChannel(9);
                    } else if (str.equalsIgnoreCase("qingdao_sa")) {
                        FacFunction_Adapter.this.mHisChannelListApiImpl.addPresetChannel(10);
                    } else if (str.equalsIgnoreCase("jiangmen_sa")) {
                        FacFunction_Adapter.this.mHisChannelListApiImpl.addPresetChannel(12);
                        FacFunction_Adapter.this.mSAChannelType = 12;
                    }
                    FacFunction_Adapter.this.list_air.clear();
                    FacFunction_Adapter.this.list_cable.clear();
                    FacFunction_Adapter.this.getAirChannelList();
                    FacFunction_Adapter.this.getCableChannelList();
                    FacFunction_Adapter.this.setTunerMode(1);
                    return;
                }
                if (FacPropertyDemo.HIFAC_ATSC30_SUPPORT) {
                    if (str.equalsIgnoreCase("huangdao")) {
                        Log.i("Fac_FacFunction_Adapter", "city name is huangdao");
                        Log.i("Fac_FacFunction_Adapter", "atscPresetResult = " + FacFunction_Adapter.this.mFactory.presetChannel(0));
                    }
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }
                    FacFunction_Adapter.this.list_air_ATSC.clear();
                    FacFunction_Adapter.this.list_cable_ATSC.clear();
                    Log.i("Fac_FacFunction_Adapter", "clean list_air_ATSC and list_cable_ATSC finish !");
                    FacFunction_Adapter.this.getChannelListByTunerMode(0);
                    Log.i("Fac_FacFunction_Adapter", "finish obtain list_air_ATSC  !");
                    FacFunction_Adapter.this.getChannelListByTunerMode(1);
                    Log.i("Fac_FacFunction_Adapter", "finish obtain list_cable_ATSC !");
                    int tunerMode = FacFunction_Adapter.this.getTunerMode();
                    if (tunerMode == 0) {
                        FacFunction_Adapter.this.changeChannelATSC(5, tunerMode);
                        return;
                    } else {
                        FacFunction_Adapter.this.changeChannelATSC(39, tunerMode);
                        return;
                    }
                }
                if (str.equalsIgnoreCase("huangdao")) {
                    Log.i("Fac_FacFunction_Adapter", "city name is huangdao");
                    FacFunction_Adapter.this.mHisChannelListApiImpl.addPresetChannel(1);
                    FacFunction_Adapter.this.list_air.clear();
                    FacFunction_Adapter.this.list_cable.clear();
                    FacFunction_Adapter.this.getAirChannelList();
                    FacFunction_Adapter.this.getCableChannelList();
                }
                int tunerMode2 = FacFunction_Adapter.this.getTunerMode();
                if (tunerMode2 == 0) {
                    FacFunction_Adapter.this.changeChannel(5, tunerMode2);
                } else {
                    FacFunction_Adapter.this.changeChannel(39, tunerMode2);
                }
            }
        }).start();
    }

    public void addTVPresetChannelForcityname(final String str, final Handler handler) {
        Log.i("Fac_FacFunction_Adapter", "addTVPresetChannelForcityname2");
        new Thread(new Runnable() { // from class: com.hisense.tvcm.FacFunction_Adapter.5
            @Override // java.lang.Runnable
            public void run() {
                FacPropertyDemo.SourceEnum currentSource = FacFunction_Adapter.this.getCurrentSource();
                FacPropertyDemo.SourceEnum sourceEnum = FacPropertyDemo.SourceEnum.TV;
                if (currentSource != sourceEnum) {
                    FacFunction_Adapter.this.switchSource(sourceEnum);
                    try {
                        Thread.sleep(200L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                FacFunction_Adapter.this.cleanchannel();
                Log.i("Fac_FacFunction_Adapter", "city name =" + str);
                if (FacPropertyDemo.HIFAC_MARKET_JP) {
                    FacFunction_Adapter.this.mFacTvsTvManager.presetChannel(CESIConstants.getValue("FactoryPresetChannel_e", "HUANGDAO"));
                } else if (FacPropertyDemo.HIFAC_MARKET.equals(FacPropertyDemo.MarketType.EM)) {
                    if (FacPropertyDemo.HIFAC_MARKET_HK) {
                        if (str.equalsIgnoreCase("huangdao_hk")) {
                            FacFunction_Adapter.this.mHisChannelListApiImpl.addPresetChannel(31);
                        } else if (str.equalsIgnoreCase("qingdao_hk")) {
                            FacFunction_Adapter.this.mHisChannelListApiImpl.addPresetChannel(32);
                        } else if (str.equalsIgnoreCase("guiyang_hk")) {
                            FacFunction_Adapter.this.mHisChannelListApiImpl.addPresetChannel(33);
                        } else if (str.equalsIgnoreCase("jiangmen_hk")) {
                            FacFunction_Adapter.this.mHisChannelListApiImpl.addPresetChannel(34);
                        }
                    } else if (str.equalsIgnoreCase("huangdao_em")) {
                        FacFunction_Adapter.this.mHisChannelListApiImpl.addPresetChannel(5);
                    } else if (str.equalsIgnoreCase("qingdao")) {
                        FacFunction_Adapter.this.mHisChannelListApiImpl.addPresetChannel(6);
                    } else if (str.equalsIgnoreCase("guiyang")) {
                        FacFunction_Adapter.this.mHisChannelListApiImpl.addPresetChannel(7);
                    } else if (str.equalsIgnoreCase("jiangmen")) {
                        FacFunction_Adapter.this.mHisChannelListApiImpl.addPresetChannel(8);
                    } else if (str.equalsIgnoreCase("south africa")) {
                        FacFunction_Adapter.this.mHisChannelListApiImpl.addPresetChannel(13);
                    } else if (str.equalsIgnoreCase("algeria")) {
                        FacFunction_Adapter.this.mHisChannelListApiImpl.addPresetChannel(14);
                    } else if (str.equalsIgnoreCase("malaysia")) {
                        FacFunction_Adapter.this.mHisChannelListApiImpl.addPresetChannel(15);
                    } else if (str.equalsIgnoreCase("iran")) {
                        FacFunction_Adapter.this.mHisChannelListApiImpl.addPresetChannel(16);
                    } else if (str.equalsIgnoreCase("indonesia")) {
                        FacFunction_Adapter.this.mHisChannelListApiImpl.addPresetChannel(17);
                    } else if (str.equalsIgnoreCase("ruassia")) {
                        FacFunction_Adapter.this.mHisChannelListApiImpl.addPresetChannel(18);
                    } else if (str.equalsIgnoreCase("egypt")) {
                        FacFunction_Adapter.this.mHisChannelListApiImpl.addPresetChannel(19);
                    } else if (str.equalsIgnoreCase("shunde")) {
                        FacFunction_Adapter.this.mHisChannelListApiImpl.addPresetChannel(20);
                    } else if (str.equalsIgnoreCase("syria")) {
                        FacFunction_Adapter.this.mHisChannelListApiImpl.addPresetChannel(21);
                    } else if (str.equalsIgnoreCase("nigeria")) {
                        FacFunction_Adapter.this.mHisChannelListApiImpl.addPresetChannel(24);
                    } else if (str.equalsIgnoreCase("pakistan")) {
                        FacFunction_Adapter.this.mHisChannelListApiImpl.addPresetChannel(25);
                    } else if (str.equalsIgnoreCase("egypt tsb")) {
                        FacFunction_Adapter.this.mHisChannelListApiImpl.addPresetChannel(26);
                    } else if (str.equalsIgnoreCase("angola")) {
                        FacFunction_Adapter.this.mHisChannelListApiImpl.addPresetChannel(36);
                    } else if (str.equalsIgnoreCase("brandt")) {
                        FacFunction_Adapter.this.mHisChannelListApiImpl.addPresetChannel(37);
                    } else if (str.equalsIgnoreCase("polytron")) {
                        FacFunction_Adapter.this.mHisChannelListApiImpl.addPresetChannel(38);
                    }
                    FacFunction_Adapter.this.list_air.clear();
                    FacFunction_Adapter.this.list_cable.clear();
                    FacFunction_Adapter.this.getAirChannelList();
                    FacFunction_Adapter.this.getCableChannelList();
                    FacFunction_Adapter.this.changeChannel(904, 0);
                } else if (FacPropertyDemo.HIFAC_MARKET_SA) {
                    FacFunction_Adapter.this.mSAChannelType = 0;
                    if (str.equalsIgnoreCase("huangdao_sa")) {
                        FacFunction_Adapter.this.mHisChannelListApiImpl.addPresetChannel(9);
                    } else if (str.equalsIgnoreCase("qingdao_sa")) {
                        FacFunction_Adapter.this.mHisChannelListApiImpl.addPresetChannel(10);
                    } else if (str.equalsIgnoreCase("jiangmen_sa")) {
                        FacFunction_Adapter.this.mHisChannelListApiImpl.addPresetChannel(12);
                        FacFunction_Adapter.this.mSAChannelType = 12;
                    }
                    FacFunction_Adapter.this.list_air.clear();
                    FacFunction_Adapter.this.list_cable.clear();
                    FacFunction_Adapter.this.getAirChannelList();
                    FacFunction_Adapter.this.getCableChannelList();
                    FacFunction_Adapter.this.setTunerMode(1);
                } else if (FacPropertyDemo.HIFAC_ATSC30_SUPPORT) {
                    if (str.equalsIgnoreCase("huangdao")) {
                        Log.i("Fac_FacFunction_Adapter", "city name is huangdao");
                        Log.i("Fac_FacFunction_Adapter", "atscPresetResult = " + FacFunction_Adapter.this.mFactory.presetChannel(0));
                    }
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }
                    FacFunction_Adapter.this.list_air_ATSC.clear();
                    FacFunction_Adapter.this.list_cable_ATSC.clear();
                    Log.i("Fac_FacFunction_Adapter", "clean list_air_ATSC and list_cable_ATSC finish !");
                    FacFunction_Adapter.this.getChannelListByTunerMode(0);
                    Log.i("Fac_FacFunction_Adapter", "finish obtain list_air_ATSC!");
                    FacFunction_Adapter.this.getChannelListByTunerMode(1);
                    Log.i("Fac_FacFunction_Adapter", "finish obtain list_cable_ATSC !");
                    int tunerMode = FacFunction_Adapter.this.getTunerMode();
                    if (tunerMode == 0) {
                        FacFunction_Adapter.this.changeChannelATSC(5, tunerMode);
                    } else {
                        FacFunction_Adapter.this.changeChannelATSC(39, tunerMode);
                    }
                } else {
                    if (str.equalsIgnoreCase("huangdao")) {
                        Log.i("Fac_FacFunction_Adapter", "city name is huangdao");
                        FacFunction_Adapter.this.mHisChannelListApiImpl.addPresetChannel(1);
                        FacFunction_Adapter.this.list_air.clear();
                        FacFunction_Adapter.this.list_cable.clear();
                        FacFunction_Adapter.this.getAirChannelList();
                        FacFunction_Adapter.this.getCableChannelList();
                    }
                    int tunerMode2 = FacFunction_Adapter.this.getTunerMode();
                    if (tunerMode2 == 0) {
                        FacFunction_Adapter.this.changeChannel(5, tunerMode2);
                    } else {
                        FacFunction_Adapter.this.changeChannel(39, tunerMode2);
                    }
                }
                handler.sendEmptyMessage(112);
            }
        }).start();
    }

    @Override // com.hisense.tvcm.FacFunction
    public void addTVPresetChannelForSA(int i) {
        FacPropertyDemo.SourceEnum currentSource = getCurrentSource();
        if (currentSource != FacPropertyDemo.SourceEnum.TV && currentSource != FacPropertyDemo.SourceEnum.ATV && currentSource != FacPropertyDemo.SourceEnum.DTV) {
            Log.i("Fac_FacFunction_Adapter", "currentSource is " + currentSource.getDisplayName());
            switchSource(FacPropertyDemo.SourceEnum.TV);
        }
        cleanchannel();
        this.mHisChannelListApiImpl.addPresetChannel(i);
        this.list_air.clear();
        this.list_cable.clear();
        try {
            Thread.sleep(300L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        getAirChannelList();
        getCableChannelList();
        this.mSAChannelType = i;
        Log.i("Fac_FacFunction_Adapter", "addTVPresetChannelForSA mSAChannelType = " + this.mSAChannelType);
        int i2 = 1;
        if (i != 35) {
            switch (i) {
                case 27:
                    this.mSAAirMajorChannelId = 26;
                    this.mSAAirMinorChannelId = 2;
                    this.mSACableMajorChannelId = 5;
                    this.mSACableMinorChannelId = 0;
                    break;
                case 28:
                    this.mSAAirMajorChannelId = 4;
                    this.mSAAirMinorChannelId = 1;
                    this.mSACableMajorChannelId = 7;
                    this.mSACableMinorChannelId = 0;
                    break;
                case 29:
                    this.mSAAirMajorChannelId = 2;
                    this.mSAAirMinorChannelId = 1;
                    this.mSACableMajorChannelId = 38;
                    this.mSACableMinorChannelId = 0;
                    i2 = 0;
                    break;
                case 30:
                    this.mSAAirMajorChannelId = 4;
                    this.mSAAirMinorChannelId = 0;
                    this.mSACableMajorChannelId = 4;
                    this.mSACableMinorChannelId = 0;
                    break;
            }
        } else {
            this.mSAAirMajorChannelId = 69;
            this.mSAAirMinorChannelId = 0;
            this.mSACableMajorChannelId = 12;
            this.mSACableMinorChannelId = 0;
        }
        setTunerMode(i2);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getFREQ1() {
        Log.i("Fac_FacFunction_Adapter", "getFREQ1");
        return this.mHisenseTvAPI.getAudioPeq(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_1_FREQ);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setFREQ1(int i) {
        Log.i("Fac_FacFunction_Adapter", "setFREQ1");
        this.mHisenseTvAPI.setAudioPeq(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_1_FREQ, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getFREQ2() {
        Log.i("Fac_FacFunction_Adapter", "getFREQ2");
        return this.mHisenseTvAPI.getAudioPeq(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_2_FREQ);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setFREQ2(int i) {
        Log.i("Fac_FacFunction_Adapter", "setFREQ2");
        this.mHisenseTvAPI.setAudioPeq(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_2_FREQ, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getFREQ3() {
        Log.i("Fac_FacFunction_Adapter", "getFREQ3");
        return this.mHisenseTvAPI.getAudioPeq(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_3_FREQ);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setFREQ3(int i) {
        Log.i("Fac_FacFunction_Adapter", "setFREQ3");
        this.mHisenseTvAPI.setAudioPeq(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_3_FREQ, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getFREQ4() {
        Log.i("Fac_FacFunction_Adapter", "getFREQ4");
        return this.mHisenseTvAPI.getAudioPeq(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_4_FREQ);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setFREQ4(int i) {
        Log.i("Fac_FacFunction_Adapter", "setFREQ4");
        this.mHisenseTvAPI.setAudioPeq(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_4_FREQ, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getFREQ5() {
        Log.i("Fac_FacFunction_Adapter", "getFREQ5");
        return this.mHisenseTvAPI.getAudioPeq(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_5_FREQ);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setFREQ5(int i) {
        Log.i("Fac_FacFunction_Adapter", "setFREQ5");
        this.mHisenseTvAPI.setAudioPeq(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_5_FREQ, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getFREQ6() {
        Log.i("Fac_FacFunction_Adapter", "getFREQ6");
        return this.mHisenseTvAPI.getAudioPeq(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_6_FREQ);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setFREQ6(int i) {
        Log.i("Fac_FacFunction_Adapter", "setFREQ6");
        this.mHisenseTvAPI.setAudioPeq(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_6_FREQ, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getFREQ7() {
        Log.i("Fac_FacFunction_Adapter", "getFREQ7");
        return this.mHisenseTvAPI.getAudioPeq(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_7_FREQ);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setFREQ7(int i) {
        Log.i("Fac_FacFunction_Adapter", "setFREQ7");
        this.mHisenseTvAPI.setAudioPeq(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_7_FREQ, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getFREQ8() {
        Log.i("Fac_FacFunction_Adapter", "getFREQ8");
        return this.mHisenseTvAPI.getAudioPeq(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_8_FREQ);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setFREQ8(int i) {
        Log.i("Fac_FacFunction_Adapter", "setFREQ8");
        this.mHisenseTvAPI.setAudioPeq(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_8_FREQ, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getGAIN1() {
        Log.i("Fac_FacFunction_Adapter", "getGAIN1");
        return this.mHisenseTvAPI.getAudioPeq(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_1_GAIN);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getGAIN2() {
        Log.i("Fac_FacFunction_Adapter", "getGAIN2");
        return this.mHisenseTvAPI.getAudioPeq(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_2_GAIN);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getGAIN3() {
        Log.i("Fac_FacFunction_Adapter", "getGAIN3");
        return this.mHisenseTvAPI.getAudioPeq(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_3_GAIN);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getGAIN4() {
        Log.i("Fac_FacFunction_Adapter", "getGAIN4");
        return this.mHisenseTvAPI.getAudioPeq(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_4_GAIN);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getGAIN5() {
        Log.i("Fac_FacFunction_Adapter", "getGAIN5");
        return this.mHisenseTvAPI.getAudioPeq(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_5_GAIN);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getGAIN6() {
        Log.i("Fac_FacFunction_Adapter", "getGAIN6");
        return this.mHisenseTvAPI.getAudioPeq(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_6_GAIN);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getGAIN7() {
        Log.i("Fac_FacFunction_Adapter", "getGAIN7");
        return this.mHisenseTvAPI.getAudioPeq(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_7_GAIN);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getGAIN8() {
        Log.i("Fac_FacFunction_Adapter", "getGAIN8");
        return this.mHisenseTvAPI.getAudioPeq(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_8_GAIN);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setGAIN1(int i) {
        Log.i("Fac_FacFunction_Adapter", "setGAIN1");
        this.mHisenseTvAPI.setAudioPeq(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_1_GAIN, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setGAIN2(int i) {
        Log.i("Fac_FacFunction_Adapter", "setGAIN2");
        this.mHisenseTvAPI.setAudioPeq(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_2_GAIN, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setGAIN3(int i) {
        Log.i("Fac_FacFunction_Adapter", "setGAIN3");
        this.mHisenseTvAPI.setAudioPeq(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_3_GAIN, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setGAIN4(int i) {
        Log.i("Fac_FacFunction_Adapter", "setGAIN4");
        this.mHisenseTvAPI.setAudioPeq(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_4_GAIN, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setGAIN5(int i) {
        Log.i("Fac_FacFunction_Adapter", "setGAIN5");
        this.mHisenseTvAPI.setAudioPeq(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_5_GAIN, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setGAIN6(int i) {
        Log.i("Fac_FacFunction_Adapter", "setGAIN6");
        this.mHisenseTvAPI.setAudioPeq(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_6_GAIN, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setGAIN7(int i) {
        Log.i("Fac_FacFunction_Adapter", "setGAIN7");
        this.mHisenseTvAPI.setAudioPeq(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_7_GAIN, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setGAIN8(int i) {
        Log.i("Fac_FacFunction_Adapter", "setGAIN8");
        this.mHisenseTvAPI.setAudioPeq(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_8_GAIN, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getQ1() {
        Log.i("Fac_FacFunction_Adapter", "getQ1");
        return this.mHisenseTvAPI.getAudioPeq(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_1_Q);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getQ2() {
        Log.i("Fac_FacFunction_Adapter", "getQ2");
        return this.mHisenseTvAPI.getAudioPeq(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_2_Q);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getQ3() {
        Log.i("Fac_FacFunction_Adapter", "getQ3");
        return this.mHisenseTvAPI.getAudioPeq(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_3_Q);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getQ4() {
        Log.i("Fac_FacFunction_Adapter", "getQ4");
        return this.mHisenseTvAPI.getAudioPeq(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_4_Q);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getQ5() {
        Log.i("Fac_FacFunction_Adapter", "getQ5");
        return this.mHisenseTvAPI.getAudioPeq(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_5_Q);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getQ6() {
        Log.i("Fac_FacFunction_Adapter", "getQ6");
        return this.mHisenseTvAPI.getAudioPeq(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_6_Q);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getQ7() {
        Log.i("Fac_FacFunction_Adapter", "getQ7");
        return this.mHisenseTvAPI.getAudioPeq(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_7_Q);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getQ8() {
        Log.i("Fac_FacFunction_Adapter", "getQ8");
        return this.mHisenseTvAPI.getAudioPeq(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_8_Q);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setQ1(int i) {
        Log.i("Fac_FacFunction_Adapter", "setQ1");
        this.mHisenseTvAPI.setAudioPeq(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_1_Q, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setQ2(int i) {
        Log.i("Fac_FacFunction_Adapter", "setQ2");
        this.mHisenseTvAPI.setAudioPeq(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_2_Q, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setQ3(int i) {
        Log.i("Fac_FacFunction_Adapter", "setQ3");
        this.mHisenseTvAPI.setAudioPeq(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_3_Q, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setQ4(int i) {
        Log.i("Fac_FacFunction_Adapter", "setQ4");
        this.mHisenseTvAPI.setAudioPeq(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_4_Q, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setQ5(int i) {
        Log.i("Fac_FacFunction_Adapter", "setQ5");
        this.mHisenseTvAPI.setAudioPeq(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_5_Q, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setQ6(int i) {
        Log.i("Fac_FacFunction_Adapter", "setQ6");
        this.mHisenseTvAPI.setAudioPeq(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_6_Q, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setQ7(int i) {
        Log.i("Fac_FacFunction_Adapter", "setQ7");
        this.mHisenseTvAPI.setAudioPeq(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_7_Q, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setQ8(int i) {
        Log.i("Fac_FacFunction_Adapter", "setQ8");
        this.mHisenseTvAPI.setAudioPeq(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_8_Q, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getSTEP1() {
        Log.i("Fac_FacFunction_Adapter", "getSTEP1");
        return this.freq_step_1;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getSTEP2() {
        Log.i("Fac_FacFunction_Adapter", "getSTEP2");
        return this.freq_step_2;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getSTEP3() {
        Log.i("Fac_FacFunction_Adapter", "getSTEP3");
        return this.freq_step_3;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getSTEP4() {
        Log.i("Fac_FacFunction_Adapter", "getSTEP4");
        return this.freq_step_4;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getSTEP5() {
        Log.i("Fac_FacFunction_Adapter", "getSTEP5");
        return this.freq_step_5;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getSTEP6() {
        Log.i("Fac_FacFunction_Adapter", "getSTEP6");
        return this.freq_step_6;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getSTEP7() {
        Log.i("Fac_FacFunction_Adapter", "getSTEP7");
        return this.freq_step_7;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getSTEP8() {
        Log.i("Fac_FacFunction_Adapter", "getSTEP8");
        return this.freq_step_8;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setSTEP1(int i) {
        Log.i("Fac_FacFunction_Adapter", "setSTEP1 value = " + i);
        this.freq_step_1 = i;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setSTEP2(int i) {
        Log.i("Fac_FacFunction_Adapter", "setSTEP2 value = " + i);
        this.freq_step_2 = i;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setSTEP3(int i) {
        Log.i("Fac_FacFunction_Adapter", "setSTEP3 value = " + i);
        this.freq_step_3 = i;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setSTEP4(int i) {
        Log.i("Fac_FacFunction_Adapter", "setSTEP4 value = " + i);
        this.freq_step_4 = i;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setSTEP5(int i) {
        Log.i("Fac_FacFunction_Adapter", "setSTEP5 value = " + i);
        this.freq_step_5 = i;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setSTEP6(int i) {
        Log.i("Fac_FacFunction_Adapter", "setSTEP6 value = " + i);
        this.freq_step_6 = i;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setSTEP7(int i) {
        Log.i("Fac_FacFunction_Adapter", "setSTEP7 value = " + i);
        this.freq_step_7 = i;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setSTEP8(int i) {
        Log.i("Fac_FacFunction_Adapter", "setSTEP8 value = " + i);
        this.freq_step_8 = i;
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean isSlotActive() {
        return this.mMtkTvCI.getSlotActive();
    }

    @Override // com.hisense.tvcm.FacFunction
    public long getDataTime() {
        long j = Build.TIME;
        long j2 = SystemProperties.getLong("ro.build.date.utc", 0L) * 1000;
        Log.i("Fac_FacFunction_Adapter", "Build.TIME = " + j + "   ro.build.date.utc = " + j2);
        return j >= j2 ? j : j2;
    }

    @Override // com.hisense.tvcm.FacFunction
    public String getDataVersion() {
        String str = SystemProperties.get("ro.hisense.data.version", "");
        Log.i("Fac_FacFunction_Adapter", "data version is " + str);
        return str;
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean setVcomCardParam(String str, int i) {
        Log.i("Fac_FacFunction_Adapter", "showVcomBmp path = " + str);
        updateFacLogStatus(16384);
        DrawDmp(str);
        return true;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int closeVcomCardParam() {
        Log.i("Fac_FacFunction_Adapter", "close vcom card");
        this.mRenderPattern.deinitRenderPattern();
        setLogToFile("close vcom");
        return 0;
    }

    public int setActiveContrast(int i) {
        int activeContrast = this.mHisenseTvAPI.setActiveContrast(i);
        Log.i("Fac_FacFunction_Adapter", "setActiveContrast resultValue = " + activeContrast);
        return activeContrast;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int setLocalDimmingSwitch(boolean z) {
        Log.i("Fac_FacFunction_Adapter", "setLocalDimmingSwitch = " + z);
        return this.mHisenseTvAPI.setLocalDimmingSwitch(z);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int setLocalDimmingState(int i) {
        Log.i("Fac_FacFunction_Adapter", "set local dimming value = " + i);
        return this.mHisenseTvAPI.setLocalDimmingState(i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getLocalDimmingState() {
        int localDimmingState = this.mHisenseTvAPI.getLocalDimmingState();
        Log.i("Fac_FacFunction_Adapter", "get local dimming value = " + localDimmingState);
        return localDimmingState;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getLightSensorValue() {
        int sensorValue = this.mHisenseTvAPI.getSensorValue();
        Log.i("Fac_FacFunction_Adapter", "sensorValue = " + sensorValue);
        return sensorValue;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getLightSensorOptionState() {
        int lightSensorOptionState = HisVideoApi.getLightSensorOptionState();
        Log.i("Fac_FacFunction_Adapter", "sensorOptionState = " + lightSensorOptionState);
        return lightSensorOptionState;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getAmbientLightValue() {
        return getLightSensorValue();
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getCWValue1() {
        int laserCW1 = this.mHisenseTvAPI.getLaserCW1();
        Log.i("Fac_FacFunction_Adapter", "getCWValue1:" + laserCW1);
        return laserCW1;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getCWValue2() {
        int laserCW2 = this.mHisenseTvAPI.getLaserCW2();
        Log.i("Fac_FacFunction_Adapter", "getCWValue2:" + laserCW2);
        return laserCW2;
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean setCWValue1(int i) {
        Log.i("Fac_FacFunction_Adapter", "setCWValue1:" + i);
        boolean laserCW1 = this.mHisenseTvAPI.setLaserCW1(i);
        Log.i("Fac_FacFunction_Adapter", "setCWValue1Result:" + laserCW1);
        return laserCW1;
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean setCWValue2(int i) {
        Log.i("Fac_FacFunction_Adapter", "setCWValue2:" + i);
        boolean laserCW2 = this.mHisenseTvAPI.setLaserCW2(i);
        Log.i("Fac_FacFunction_Adapter", "setCWValue2Result:" + laserCW2);
        return laserCW2;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getLaserAlarmEnable() {
        int laserAlertLedOnOff = this.mHisenseTvAPI.getLaserAlertLedOnOff();
        Log.i("Fac_FacFunction_Adapter", "getAlarmEnableResult = " + laserAlertLedOnOff);
        return laserAlertLedOnOff;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getTemperatureProtection() {
        int laserTemperatureProtectSwitch = this.mHisenseTvAPI.getLaserTemperatureProtectSwitch();
        Log.i("Fac_FacFunction_Adapter", "getTemperatureProtectionResult = " + laserTemperatureProtectSwitch);
        return laserTemperatureProtectSwitch;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getDisplayBoardUpgradeMode() {
        int laserSoftwareUpgradeMode = this.mHisenseTvAPI.getLaserSoftwareUpgradeMode();
        Log.i("Fac_FacFunction_Adapter", "getDisplayPanelUpgradeModeResult :" + laserSoftwareUpgradeMode);
        return laserSoftwareUpgradeMode;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setLaserToAlarm(int i) {
        int laserAlertLedOnOff;
        Log.i("Fac_FacFunction_Adapter", "setLaserToAlarmStatus :" + i);
        if (i == 0) {
            laserAlertLedOnOff = this.mHisenseTvAPI.setLaserAlertLedOnOff(i);
            setLedControl(1);
        } else {
            setLedControl(0);
            laserAlertLedOnOff = this.mHisenseTvAPI.setLaserAlertLedOnOff(i);
        }
        Log.i("Fac_FacFunction_Adapter", "setLaserToAlarmResult :" + laserAlertLedOnOff);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setTemperatureProtection(int i) {
        Log.i("Fac_FacFunction_Adapter", "setTemperatureProtection = " + i);
        Log.i("Fac_FacFunction_Adapter", "setTemperatureProtectionResult = " + this.mHisenseTvAPI.setLaserTemperatureProtectSwitch(i));
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setDisplayBoardUpgrade(int i) {
        Log.i("Fac_FacFunction_Adapter", "setDisplayBoardUpgradeMode = " + i);
        Log.i("Fac_FacFunction_Adapter", "setDisplayBoardUpgraderesult = " + this.mHisenseTvAPI.setLaserSoftwareUpgradeMode(i));
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getMirrorValue() {
        Log.i("Fac_FacFunction_Adapter", "getLaserMirrorGainValue");
        int laserMirrorGainValue = this.mHisenseTvAPI.getLaserMirrorGainValue();
        Log.i("Fac_FacFunction_Adapter", "getMirrorValueResult = " + laserMirrorGainValue);
        return laserMirrorGainValue;
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean setMirrorValue(int i) {
        Log.i("Fac_FacFunction_Adapter", "setMirrorValue = " + i);
        int laserMirrorAdjustValue = this.mHisenseTvAPI.setLaserMirrorAdjustValue(i);
        Log.i("Fac_FacFunction_Adapter", "setOpenMirrorResult = " + laserMirrorAdjustValue);
        return laserMirrorAdjustValue == 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean setOpenMirror(boolean z) {
        Log.i("Fac_FacFunction_Adapter", "setLaserMirrorAdjustSwitch = " + z);
        int laserMirrorAdjustSwitch = this.mHisenseTvAPI.setLaserMirrorAdjustSwitch(z ? 1 : 0);
        Log.i("Fac_FacFunction_Adapter", "setOpenMirrorResult = " + laserMirrorAdjustSwitch);
        return laserMirrorAdjustSwitch == 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void initScreenType() {
        String str = SystemProperties.get("ro.vt.product.laser_screen_type");
        Log.i("Fac_FacFunction_Adapter", "initScreenType = " + str);
        int i = (str == null || str.equalsIgnoreCase("")) ? 0 : Integer.parseInt(str);
        int laserScreenType = this.mHisenseTvAPI.getLaserScreenType();
        Log.i("Fac_FacFunction_Adapter", "getscreenType = " + laserScreenType);
        if (laserScreenType == 255) {
            this.mHisenseTvAPI.setLaserScreenType(i);
        }
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getScreen() {
        int laserScreenType = this.mHisenseTvAPI.getLaserScreenType();
        Log.i("Fac_FacFunction_Adapter", "getscreenType = " + laserScreenType);
        return laserScreenType;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setScreen(int i) {
        Log.i("Fac_FacFunction_Adapter", "setScreenType = " + i);
        Log.i("Fac_FacFunction_Adapter", "setLaserScreenTypeResult = " + this.mHisenseTvAPI.setLaserScreenType(i));
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getSensorLuxValue() {
        int luxValue = this.mHisenseTvAPI.getLuxValue();
        Log.i("Fac_FacFunction_Adapter", "luxValue = " + luxValue);
        return luxValue;
    }

    @Override // com.hisense.tvcm.FacFunction
    public String getMcuVersionInfo() {
        int laserMcuVersion = this.mHisenseTvAPI.getLaserMcuVersion();
        Log.i("Fac_FacFunction_Adapter", "getMcuVersionInfo = " + laserMcuVersion);
        String hexString = Integer.toHexString(laserMcuVersion);
        Log.i("Fac_FacFunction_Adapter", "getMcuVersionInfohexstring = " + hexString);
        return hexString;
    }

    @Override // com.hisense.tvcm.FacFunction
    public String getDlpMcuVersion() {
        int laserDlpVersion = this.mHisenseTvAPI.getLaserDlpVersion();
        Log.i("Fac_FacFunction_Adapter", "getDlpVersion = " + laserDlpVersion);
        String hexString = Integer.toHexString(laserDlpVersion);
        Log.i("Fac_FacFunction_Adapter", "getDlpVersionInfohexstring = " + hexString);
        return hexString;
    }

    @Override // com.hisense.tvcm.FacFunction
    public String getLedEffectVersion() {
        String laserLedVersion = this.mHisenseTvAPI.getLaserLedVersion();
        Log.i("Fac_FacFunction_Adapter", "ledVersion = " + laserLedVersion);
        return laserLedVersion;
    }

    public void setFunctionNumber(String str) {
        this.mHisenseTvAPI.setFactoryFunctionNumber(MtkTvConfigTypeBase.CFG_FACTORY_FAC_FUNCTION_NUMBER, str);
    }

    public void setChipNumber(String str) {
        this.mHisenseTvAPI.setMachineNumber(MtkTvConfigTypeBase.CFG_FACTORY_FAC_MACHINE_NUMBER, str);
    }

    public String getFunctionNumber() {
        return this.mHisenseTvAPI.getFactoryFunctionNumber(MtkTvConfigTypeBase.CFG_FACTORY_FAC_FUNCTION_NUMBER);
    }

    public String getChipNumber() {
        try {
            return this.mHisenseTvAPI.getMachineNumber(MtkTvConfigTypeBase.CFG_FACTORY_FAC_MACHINE_NUMBER);
        } catch (NoSuchMethodError e) {
            Log.i("Fac_FacFunction_Adapter", "can.t getChipNumber from lower layer");
            e.printStackTrace();
            return "";
        }
    }

    public void setZoomMode(int i) {
        Log.i("Fac_FacFunction_Adapter", "setSerial ZoomMode =" + i);
        this.mHisenseTvAPI.setAspectRatio(i);
    }

    public void set3DMode(int i) {
        Log.e("Fac_FacFunction_Adapter", "set3DMode = " + i);
    }

    public void setModelPanel(int i) {
        Log.i("Fac_FacFunction_Adapter", "serial to set panel =" + i);
        this.mHisenseTvAPI.setWhiteBalancePanel(MtkTvConfigTypeBase.CFG_FACTORY_VID_PANEL_TYPE, i);
    }

    public void setPictureMode(int i) {
        Log.i("Fac_FacFunction_Adapter", "serial to set picturemode mtkvalue= " + i);
        this.mHisenseTvAPI.setPictureMode(i);
    }

    public boolean setColorTemperature(int i) {
        Log.i("Fac_FacFunction_Adapter", "serial to set colortmp mtkvalue= " + i);
        return this.mHisenseTvAPI.setColorTemperature(MtkTvConfigTypeBase.CFG_FACTORY_VID_COLOR_TEMP, i);
    }

    public boolean setSavingMode(int i) {
        Log.i("Fac_FacFunction_Adapter", "setSavingMode is " + i);
        if (!FacPropertyDemo.HIFAC_MARKET_JP) {
            return false;
        }
        this.mTvsFunc.setSavingMode(i);
        return true;
    }

    public boolean setSerialWBPattern(int i) {
        Log.i("Fac_FacFunction_Adapter", "wb pattern times is " + i);
        if (i == 0) {
            return setWBPattern(21);
        }
        if (i == 1) {
            SystemProperties.set("sys.vt.factory.wb_pattern_test", "true");
            return setWBPattern(20);
        }
        SystemProperties.set("sys.vt.factory.wb_pattern_test", "true");
        return setWBPattern(i - 2);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getBacklight_Min(FacPropertyDemo.BACKLIGHTCURVE_POINT backlightcurve_point) {
        int[] backlightMin = this.mHisenseTvAPI.getBacklightMin();
        if (backlightMin != null) {
            Log.i("Fac_FacFunction_Adapter", "getBacklight_Min called, param:" + backlightMin[0]);
            return backlightMin[0];
        }
        Log.i("Fac_FacFunction_Adapter", "getBacklight_Min called,array is null");
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getBacklight_20(FacPropertyDemo.BACKLIGHTCURVE_POINT backlightcurve_point) {
        int[] backlight20 = this.mHisenseTvAPI.getBacklight20();
        if (backlight20 != null) {
            Log.i("Fac_FacFunction_Adapter", "getBacklight_20 called, param:" + backlight20[1]);
            return backlight20[1];
        }
        Log.i("Fac_FacFunction_Adapter", "getBacklight_20 called,array is null");
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getBacklight_Mid(FacPropertyDemo.BACKLIGHTCURVE_POINT backlightcurve_point) {
        int[] backlightMid = this.mHisenseTvAPI.getBacklightMid();
        if (backlightMid != null) {
            Log.i("Fac_FacFunction_Adapter", "getBacklight_Mid called, param:" + backlightMid[2]);
            return backlightMid[2];
        }
        Log.i("Fac_FacFunction_Adapter", "getBacklight_Mid called,array is null");
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getBacklight_80(FacPropertyDemo.BACKLIGHTCURVE_POINT backlightcurve_point) {
        int[] backlight80 = this.mHisenseTvAPI.getBacklight80();
        if (backlight80 != null) {
            Log.i("Fac_FacFunction_Adapter", "getBacklight_80 called, param:" + backlight80[3]);
            return backlight80[3];
        }
        Log.i("Fac_FacFunction_Adapter", "getBacklight_80 called,array is null");
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getBacklight_Max(FacPropertyDemo.BACKLIGHTCURVE_POINT backlightcurve_point) {
        int[] backlightMax = this.mHisenseTvAPI.getBacklightMax();
        if (backlightMax != null) {
            Log.i("Fac_FacFunction_Adapter", "getBacklight_Max called, param:" + backlightMax[4]);
            return backlightMax[4];
        }
        Log.i("Fac_FacFunction_Adapter", "getBacklight_Max called,array is null");
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getPWMFreq(FacPropertyDemo.BACKLIGHTCURVE_POINT backlightcurve_point) {
        Log.i("Fac_FacFunction_Adapter", "getPWMFreq called");
        return this.mHisenseTvAPI.getPwmFrequency(MtkTvConfigTypeBase.CFG_FACTORY_VID_PWM_FREQ);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setBacklight_Min(FacPropertyDemo.BACKLIGHTCURVE_POINT backlightcurve_point, int i) {
        Log.i("Fac_FacFunction_Adapter", "setBacklight_Min called, param: " + i);
        this.mHisenseTvAPI.setBacklightModeDetailValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_BACKLIGHT_MIN, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setBacklight_20(FacPropertyDemo.BACKLIGHTCURVE_POINT backlightcurve_point, int i) {
        Log.i("Fac_FacFunction_Adapter", "setBacklight_20 called, param: " + i);
        this.mHisenseTvAPI.setBacklightModeDetailValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_BACKLIGHT_20, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setBacklight_Mid(FacPropertyDemo.BACKLIGHTCURVE_POINT backlightcurve_point, int i) {
        Log.i("Fac_FacFunction_Adapter", "setBacklight_Mid called, param: " + i);
        this.mHisenseTvAPI.setBacklightModeDetailValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_BACKLIGHT_MID, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setBacklight_80(FacPropertyDemo.BACKLIGHTCURVE_POINT backlightcurve_point, int i) {
        Log.i("Fac_FacFunction_Adapter", "setBacklight_80 called, param: " + i);
        this.mHisenseTvAPI.setBacklightModeDetailValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_BACKLIGHT_80, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setBacklight_Max(FacPropertyDemo.BACKLIGHTCURVE_POINT backlightcurve_point, int i) {
        Log.i("Fac_FacFunction_Adapter", "setBacklight_Max called, param: " + i);
        this.mHisenseTvAPI.setBacklightModeDetailValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_BACKLIGHT_MAX, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setPWMFreq(FacPropertyDemo.BACKLIGHTCURVE_POINT backlightcurve_point, int i) {
        Log.i("Fac_FacFunction_Adapter", "setPWMFreq called, param: " + i);
        this.mHisenseTvAPI.setPwmFrequency(MtkTvConfigTypeBase.CFG_FACTORY_VID_PWM_FREQ, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getTunerMode() {
        int tunerMode;
        if (FacPropertyDemo.HIFAC_ATSC30_SUPPORT) {
            tunerMode = this.mAtscCommons.getTunerMode();
        } else {
            tunerMode = HisCommonApiImpl.getTunerMode();
        }
        Log.i("Fac_FacFunction_Adapter", "getTunerMode = " + tunerMode);
        return tunerMode;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setTunerModeForEM(int i) {
        Log.i("Fac_FacFunction_Adapter", "setTunerModeForEM = " + i);
        this.mMtkTvConfig.setConfigValue(MtkTvConfigTypeBase.CFG_BS_BS_SRC, i, 0);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setTunerModeforATSC(int i) {
        if (FacPropertyDemo.HIFAC_ATSC30_SUPPORT) {
            this.mAtscCommons.setTunerMode(i);
        }
        Log.i("Fac_FacFunction_Adapter", "setTunerModeforATSC end , value = " + i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setTunerMode(int i) {
        Log.i("Fac_FacFunction_Adapter", "setTunerMode = " + i);
        if (FacPropertyDemo.HIFAC_ATSC30_SUPPORT) {
            this.mAtscCommons.setTunerMode(i);
        } else {
            HisCommonApiImpl.setTunerMode(i, false);
        }
        Log.i("Fac_FacFunction_Adapter", "setTunerMode end");
        try {
            Thread.sleep(200L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (i == 1) {
            if (FacPropertyDemo.HIFAC_MARKET.equals(FacPropertyDemo.MarketType.EM)) {
                changeChannel(1, 1);
                return;
            }
            if (FacPropertyDemo.HIFAC_MARKET_SA) {
                updateCableChannelWithTunerChanged();
                return;
            } else if (FacPropertyDemo.HIFAC_ATSC30_SUPPORT) {
                changeChannelATSC(39, 1);
                return;
            } else {
                changeChannel(39, 1);
                return;
            }
        }
        if (FacPropertyDemo.HIFAC_MARKET.equals(FacPropertyDemo.MarketType.EM)) {
            changeChannel(9, 0);
            return;
        }
        if (FacPropertyDemo.HIFAC_MARKET_SA) {
            updateAirChannelWithTunerChanged();
        } else if (FacPropertyDemo.HIFAC_ATSC30_SUPPORT) {
            changeChannelATSC(5, 0);
        } else {
            changeChannel(5, 0);
        }
    }

    private void updateAirChannelWithTunerChanged() {
        Log.i("Fac_FacFunction_Adapter", "updateChannelWithTunerChanged mSAChannelType = " + this.mSAChannelType);
        int i = this.mSAChannelType;
        if (i == 27 || i == 28 || i == 29 || i == 30 || i == 35) {
            int i2 = this.mSAAirMinorChannelId;
            if (i2 == 0) {
                changeChannel(this.mSAAirMajorChannelId, 0);
                return;
            } else {
                changeChannelForId(this.mSAAirMajorChannelId, i2);
                return;
            }
        }
        changeChannel(5, 0);
    }

    private void updateCableChannelWithTunerChanged() {
        Log.i("Fac_FacFunction_Adapter", "updateChannelWithTunerChanged mSAChannelType = " + this.mSAChannelType);
        int i = this.mSAChannelType;
        if (i != 27 && i != 28 && i != 29 && i != 30 && i != 35) {
            if (i == 12) {
                changeChannel(6, 1);
                return;
            } else {
                changeChannel(39, 1);
                return;
            }
        }
        int i2 = this.mSACableMinorChannelId;
        if (i2 == 0) {
            changeChannel(this.mSACableMajorChannelId, 1);
        } else {
            changeChannelForId(this.mSACableMajorChannelId, i2);
        }
    }

    public void setTunerModeOnly(int i) {
        Log.i("Fac_FacFunction_Adapter", "setTunerMode = " + i);
        if (FacPropertyDemo.HIFAC_ATSC30_SUPPORT) {
            this.mAtscCommons.setTunerMode(i);
        } else {
            HisCommonApiImpl.setTunerMode(i, false);
        }
        Log.i("Fac_FacFunction_Adapter", "setTunerMode end");
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getBacklight() {
        int[] backlight = this.mHisenseTvAPI.getBacklight();
        if (backlight == null) {
            return 0;
        }
        Log.i("Fac_FacFunction_Adapter", "getBackLight = " + backlight[5]);
        return backlight[5];
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setBacklight(int i) {
        Log.i("Fac_FacFunction_Adapter", "setBackLight = " + i);
        this.mHisenseTvAPI.setPictureModeDetailValue(MtkTvConfigTypeBase.CFG_DISP_DISP_DISP_BACK_LIGHT, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setCountry(int i) {
        int index;
        Log.i("Fac_FacFunction_Adapter", "set country ori value is " + i);
        int region = getRegion();
        int iKeyByPosition = 0;
        if (!FacPropertyDemo.HIFAC_MARKET_MONITOR) {
            if (FacPropertyDemo.HIFAC_MARKET_TW || FacPropertyDemo.HIFAC_MARKET_JP) {
                return;
            }
            if (FacPropertyDemo.HIFAC_MARKET.equals(FacPropertyDemo.MarketType.EM)) {
                if (region == 0) {
                    iKeyByPosition = ApVsTILmap.ApToTil(14, i);
                } else if (region == 1) {
                    iKeyByPosition = ApVsTILmap.ApToTil(15, i);
                } else if (region == 2) {
                    iKeyByPosition = ApVsTILmap.ApToTil(16, i);
                } else if (region == 3) {
                    iKeyByPosition = ApVsTILmap.ApToTil(17, i);
                } else {
                    iKeyByPosition = ApVsTILmap.ApToTil(32, i);
                }
            } else if (FacPropertyDemo.HIFAC_MARKET_SA) {
                if (region == 5) {
                    FacPropertyDemo.CountryForRegion5[] countryForRegion5ArrValues = FacPropertyDemo.CountryForRegion5.values();
                    int length = countryForRegion5ArrValues.length;
                    index = 0;
                    while (iKeyByPosition < length) {
                        FacPropertyDemo.CountryForRegion5 countryForRegion5 = countryForRegion5ArrValues[iKeyByPosition];
                        if (countryForRegion5.enumindex == i) {
                            index = countryForRegion5.getIndex();
                        }
                        iKeyByPosition++;
                    }
                } else {
                    FacPropertyDemo.CountryForRegion6[] countryForRegion6ArrValues = FacPropertyDemo.CountryForRegion6.values();
                    int length2 = countryForRegion6ArrValues.length;
                    index = 0;
                    while (iKeyByPosition < length2) {
                        FacPropertyDemo.CountryForRegion6 countryForRegion6 = countryForRegion6ArrValues[iKeyByPosition];
                        if (countryForRegion6.enumindex == i) {
                            index = countryForRegion6.getIndex();
                        }
                        iKeyByPosition++;
                    }
                }
                iKeyByPosition = index;
            } else {
                iKeyByPosition = FacCountryUtils.keyByPosition(i);
            }
        }
        Log.i("Fac_FacFunction_Adapter", "set country value after transition is " + iKeyByPosition);
        setCountryIndex(iKeyByPosition);
        setChangeFlage();
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setRegion(int i) {
        Log.i("Fac_FacFunction_Adapter", "set region ori value is " + i);
        int iApToTil = ApVsTILmap.ApToTil(13, i);
        Log.i("Fac_FacFunction_Adapter", "set region value after transition is " + iApToTil);
        setRegionTilValue(iApToTil);
        setChangeFlage();
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setLanguage(int i) {
        int languagekeyByUIIndex;
        Log.i("Fac_FacFunction_Adapter", "set language ori value is " + i);
        int region = getRegion();
        if (FacPropertyDemo.HIFAC_MARKET_HK || FacPropertyDemo.HIFAC_MARKET_MONITOR) {
            languagekeyByUIIndex = FacLanguageUtils.getLanguagekeyByUIIndex(i);
        } else {
            int i2 = 0;
            if (FacPropertyDemo.HIFAC_MARKET_TW) {
                FacPropertyDemo.LanguageForTWN[] languageForTWNArrValues = FacPropertyDemo.LanguageForTWN.values();
                int length = languageForTWNArrValues.length;
                languagekeyByUIIndex = 0;
                while (i2 < length) {
                    FacPropertyDemo.LanguageForTWN languageForTWN = languageForTWNArrValues[i2];
                    if (languageForTWN.enumIndex == i) {
                        languagekeyByUIIndex = languageForTWN.getIndex();
                    }
                    i2++;
                }
            } else if (FacPropertyDemo.HIFAC_MARKET_JP) {
                FacPropertyDemo.LanguageForJPN[] languageForJPNArrValues = FacPropertyDemo.LanguageForJPN.values();
                int length2 = languageForJPNArrValues.length;
                languagekeyByUIIndex = 0;
                while (i2 < length2) {
                    FacPropertyDemo.LanguageForJPN languageForJPN = languageForJPNArrValues[i2];
                    if (languageForJPN.enumIndex == i) {
                        languagekeyByUIIndex = languageForJPN.getIndex();
                    }
                    i2++;
                }
            } else if (FacPropertyDemo.HIFAC_MARKET.equals(FacPropertyDemo.MarketType.EM)) {
                if (region == 0) {
                    languagekeyByUIIndex = ApVsTILmap.ApToTil(18, i);
                } else if (region == 1) {
                    languagekeyByUIIndex = ApVsTILmap.ApToTil(19, i);
                } else if (region == 2) {
                    languagekeyByUIIndex = ApVsTILmap.ApToTil(20, i);
                } else if (region == 3) {
                    languagekeyByUIIndex = ApVsTILmap.ApToTil(21, i);
                } else {
                    languagekeyByUIIndex = ApVsTILmap.ApToTil(33, i);
                }
            } else if (!FacPropertyDemo.HIFAC_MARKET_SA) {
                languagekeyByUIIndex = ApVsTILmap.ApToTil(12, i);
            } else if (region == 5) {
                FacPropertyDemo.LanguageForRegion5[] languageForRegion5ArrValues = FacPropertyDemo.LanguageForRegion5.values();
                int length3 = languageForRegion5ArrValues.length;
                languagekeyByUIIndex = 0;
                while (i2 < length3) {
                    FacPropertyDemo.LanguageForRegion5 languageForRegion5 = languageForRegion5ArrValues[i2];
                    if (languageForRegion5.enumIndex == i) {
                        languagekeyByUIIndex = languageForRegion5.getIndex();
                    }
                    i2++;
                }
            } else {
                FacPropertyDemo.LanguageForRegion6[] languageForRegion6ArrValues = FacPropertyDemo.LanguageForRegion6.values();
                int length4 = languageForRegion6ArrValues.length;
                languagekeyByUIIndex = 0;
                while (i2 < length4) {
                    FacPropertyDemo.LanguageForRegion6 languageForRegion6 = languageForRegion6ArrValues[i2];
                    if (languageForRegion6.enumIndex == i) {
                        languagekeyByUIIndex = languageForRegion6.getIndex();
                    }
                    i2++;
                }
            }
        }
        Log.i("Fac_FacFunction_Adapter", "set langugage value after transition is " + languagekeyByUIIndex);
        this.mHisenseTvAPI.setTvLanguage(MtkTvConfigTypeBase.CFG_FACTORY_FAC_TV_LANGUAGE, languagekeyByUIIndex);
        setChangeFlage();
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setLogo(int i) {
        Log.i("Fac_FacFunction_Adapter", "setLogo ui index is " + i);
        int logokeyByUIIndex = 2;
        if (FacPropertyDemo.HIFAC_MARKET_HK || FacPropertyDemo.HIFAC_MARKET_MONITOR || FacPropertyDemo.HIFAC_PRODUCT_OEM || FacPropertyDemo.HIFAC_MARKET_KO) {
            logokeyByUIIndex = FacLogoUtils.getLogokeyByUIIndex(i);
        } else {
            if (FacPropertyDemo.HIFAC_MARKET_TW) {
                return;
            }
            if (FacPropertyDemo.HIFAC_MARKET.equals(FacPropertyDemo.MarketType.EM)) {
                if (FacPropertyDemo.HIFAC_REGION5_SUPPORT) {
                    logokeyByUIIndex = ApVsTILmap.ApToTil(34, i);
                } else if (!this.mAdapterHelper.isBrandIsolate()) {
                    logokeyByUIIndex = this.mAdapterHelper.getNoBrandIsolateTilValue(i);
                } else if (i != 1) {
                    logokeyByUIIndex = i == 2 ? 100 : ApVsTILmap.getLogoIndexByStr(getBrand());
                }
            } else if (FacPropertyDemo.HIFAC_MARKET_SA) {
                int newSALogoValue = this.mAdapterHelper.getNewSALogoValue(getBrand());
                Log.i("Fac_FacFunction_Adapter", "old logo value is " + newSALogoValue);
                logokeyByUIIndex = this.mAdapterHelper.getNewSALogoValue(i, newSALogoValue);
            } else if (FacPropertyDemo.HIFAC_MARKET_JP) {
                logokeyByUIIndex = ApVsTILmap.ApToTil(35, i);
            } else {
                logokeyByUIIndex = ApVsTILmap.ApToTil(10, i);
            }
        }
        Log.i("Fac_FacFunction_Adapter", "setLogo tilValue is " + logokeyByUIIndex);
        setLogoIndex(logokeyByUIIndex);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getCountry() {
        int i;
        int countryFromEmmc = getCountryFromEmmc();
        Log.i("Fac_FacFunction_Adapter", "get country value is " + countryFromEmmc);
        int region = getRegion();
        int iIndexOfCountry = 0;
        if (!FacPropertyDemo.HIFAC_MARKET_MONITOR) {
            if (FacPropertyDemo.HIFAC_MARKET.equals(FacPropertyDemo.MarketType.EM)) {
                if (!FacPropertyDemo.HIFAC_MARKET_TW) {
                    if (region == 0) {
                        Log.i("Fac_FacFunction_Adapter", "region1");
                        iIndexOfCountry = ApVsTILmap.TilToAp(14, countryFromEmmc);
                    } else if (region == 1) {
                        Log.i("Fac_FacFunction_Adapter", "region2");
                        iIndexOfCountry = ApVsTILmap.TilToAp(15, countryFromEmmc);
                    } else if (region == 2) {
                        Log.i("Fac_FacFunction_Adapter", "region3");
                        iIndexOfCountry = ApVsTILmap.TilToAp(16, countryFromEmmc);
                    } else if (region == 3) {
                        Log.i("Fac_FacFunction_Adapter", "region4");
                        iIndexOfCountry = ApVsTILmap.TilToAp(17, countryFromEmmc);
                    } else {
                        Log.i("Fac_FacFunction_Adapter", "region0");
                        iIndexOfCountry = ApVsTILmap.TilToAp(32, countryFromEmmc);
                    }
                }
            } else if (FacPropertyDemo.HIFAC_MARKET_SA) {
                if (region == 5) {
                    FacPropertyDemo.CountryForRegion5[] countryForRegion5ArrValues = FacPropertyDemo.CountryForRegion5.values();
                    int length = countryForRegion5ArrValues.length;
                    i = 0;
                    while (iIndexOfCountry < length) {
                        FacPropertyDemo.CountryForRegion5 countryForRegion5 = countryForRegion5ArrValues[iIndexOfCountry];
                        if (countryForRegion5.getIndex() == countryFromEmmc) {
                            i = countryForRegion5.enumindex;
                        }
                        iIndexOfCountry++;
                    }
                } else {
                    FacPropertyDemo.CountryForRegion6[] countryForRegion6ArrValues = FacPropertyDemo.CountryForRegion6.values();
                    int length2 = countryForRegion6ArrValues.length;
                    i = 0;
                    while (iIndexOfCountry < length2) {
                        FacPropertyDemo.CountryForRegion6 countryForRegion6 = countryForRegion6ArrValues[iIndexOfCountry];
                        if (countryForRegion6.getIndex() == countryFromEmmc) {
                            i = countryForRegion6.enumindex;
                        }
                        iIndexOfCountry++;
                    }
                }
                iIndexOfCountry = i;
            } else if (FacPropertyDemo.HIFAC_MARKET_JP) {
                Log.i("Fac_FacFunction_Adapter", "TVS country is jpn");
                iIndexOfCountry = ApVsTILmap.TilToAp(36, countryFromEmmc);
            } else {
                iIndexOfCountry = FacCountryUtils.indexOfCountry(countryFromEmmc);
            }
        }
        Log.i("Fac_FacFunction_Adapter", "get country value after transition is " + iIndexOfCountry);
        return iIndexOfCountry;
    }

    private int getCountryFromEmmc() {
        return this.mHisenseTvAPI.getTvCountry(MtkTvConfigTypeBase.CFG_FACTORY_FAC_TV_CPUNTRY);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getRegion() {
        int regionValue = this.mHisenseTvAPI.getRegionValue(MtkTvConfigTypeBase.CFG_FACTORY_REGION);
        Log.i("Fac_FacFunction_Adapter", "get region tilValue is " + regionValue);
        int iTilToAp = ApVsTILmap.TilToAp(13, regionValue);
        Log.i("Fac_FacFunction_Adapter", "get region is " + iTilToAp);
        if (regionValue < 0) {
            FacPropertyDemo.MarketType marketType = FacPropertyDemo.HIFAC_MARKET;
            FacPropertyDemo.MarketType marketType2 = FacPropertyDemo.MarketType.EM;
        }
        return iTilToAp;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getLanguage() {
        int languageUIIndexByKey;
        int languageFromEmmc = getLanguageFromEmmc();
        int region = getRegion();
        Log.i("Fac_FacFunction_Adapter", "language value is " + languageFromEmmc);
        if (FacPropertyDemo.HIFAC_MARKET_HK || FacPropertyDemo.HIFAC_MARKET_MONITOR) {
            languageUIIndexByKey = FacLanguageUtils.getLanguageUIIndexByKey(languageFromEmmc);
        } else {
            int i = 0;
            if (FacPropertyDemo.HIFAC_MARKET_TW) {
                FacPropertyDemo.LanguageForTWN[] languageForTWNArrValues = FacPropertyDemo.LanguageForTWN.values();
                int length = languageForTWNArrValues.length;
                languageUIIndexByKey = 0;
                while (i < length) {
                    FacPropertyDemo.LanguageForTWN languageForTWN = languageForTWNArrValues[i];
                    if (languageForTWN.getIndex() == languageFromEmmc) {
                        languageUIIndexByKey = languageForTWN.enumIndex;
                    }
                    i++;
                }
            } else if (FacPropertyDemo.HIFAC_MARKET_JP) {
                FacPropertyDemo.LanguageForJPN[] languageForJPNArrValues = FacPropertyDemo.LanguageForJPN.values();
                int length2 = languageForJPNArrValues.length;
                languageUIIndexByKey = 0;
                while (i < length2) {
                    FacPropertyDemo.LanguageForJPN languageForJPN = languageForJPNArrValues[i];
                    if (languageForJPN.getIndex() == languageFromEmmc) {
                        languageUIIndexByKey = languageForJPN.enumIndex;
                    }
                    i++;
                }
            } else if (FacPropertyDemo.HIFAC_MARKET.equals(FacPropertyDemo.MarketType.EM)) {
                if (region == 0) {
                    languageUIIndexByKey = ApVsTILmap.TilToAp(18, languageFromEmmc);
                } else if (region == 1) {
                    languageUIIndexByKey = ApVsTILmap.TilToAp(19, languageFromEmmc);
                } else if (region == 2) {
                    languageUIIndexByKey = ApVsTILmap.TilToAp(20, languageFromEmmc);
                } else if (region == 3) {
                    languageUIIndexByKey = ApVsTILmap.TilToAp(21, languageFromEmmc);
                } else {
                    languageUIIndexByKey = ApVsTILmap.TilToAp(33, languageFromEmmc);
                }
            } else if (FacPropertyDemo.HIFAC_MARKET_SA) {
                if (region == 5) {
                    FacPropertyDemo.LanguageForRegion5[] languageForRegion5ArrValues = FacPropertyDemo.LanguageForRegion5.values();
                    int length3 = languageForRegion5ArrValues.length;
                    languageUIIndexByKey = 0;
                    while (i < length3) {
                        FacPropertyDemo.LanguageForRegion5 languageForRegion5 = languageForRegion5ArrValues[i];
                        if (languageForRegion5.getIndex() == languageFromEmmc) {
                            languageUIIndexByKey = languageForRegion5.enumIndex;
                        }
                        i++;
                    }
                } else {
                    FacPropertyDemo.LanguageForRegion6[] languageForRegion6ArrValues = FacPropertyDemo.LanguageForRegion6.values();
                    int length4 = languageForRegion6ArrValues.length;
                    languageUIIndexByKey = 0;
                    while (i < length4) {
                        FacPropertyDemo.LanguageForRegion6 languageForRegion6 = languageForRegion6ArrValues[i];
                        if (languageForRegion6.getIndex() == languageFromEmmc) {
                            languageUIIndexByKey = languageForRegion6.enumIndex;
                        }
                        i++;
                    }
                }
            } else {
                if (FacPropertyDemo.HIFAC_MARKET_KO) {
                    return 0;
                }
                languageUIIndexByKey = ApVsTILmap.TilToAp(12, languageFromEmmc);
            }
        }
        Log.i("Fac_FacFunction_Adapter", "language value after transition is " + languageUIIndexByKey);
        return languageUIIndexByKey;
    }

    private int getLanguageFromEmmc() {
        return this.mHisenseTvAPI.getTvLanguage(MtkTvConfigTypeBase.CFG_FACTORY_FAC_TV_LANGUAGE);
    }

    /* JADX WARN: Code duplicated, block: B:11:0x002d  */
    @Override // com.hisense.tvcm.FacFunction
    public int getLogo() {
        int logoFromEmmc = getLogoFromEmmc();
        Log.i("Fac_FacFunction_Adapter", "getLogo logo value is " + logoFromEmmc);
        int logoUIIndexByKey = 2;
        if (FacPropertyDemo.HIFAC_MARKET_HK || FacPropertyDemo.HIFAC_MARKET_MONITOR || FacPropertyDemo.HIFAC_PRODUCT_OEM) {
            logoUIIndexByKey = FacLogoUtils.getLogoUIIndexByKey(logoFromEmmc);
        } else if (FacPropertyDemo.HIFAC_MARKET_TW) {
            logoUIIndexByKey = 0;
        } else if (FacPropertyDemo.HIFAC_MARKET.equals(FacPropertyDemo.MarketType.EM)) {
            if (FacPropertyDemo.HIFAC_REGION5_SUPPORT) {
                logoUIIndexByKey = ApVsTILmap.TilToAp(34, logoFromEmmc);
            } else if (!this.mAdapterHelper.isBrandIsolate()) {
                logoUIIndexByKey = this.mAdapterHelper.getNoBrandIsolateUIIndex(logoFromEmmc);
            } else if (logoFromEmmc == 2) {
                logoUIIndexByKey = 1;
            } else if (logoFromEmmc != 100) {
                logoUIIndexByKey = 0;
            }
        } else if (FacPropertyDemo.HIFAC_MARKET_SA) {
            logoUIIndexByKey = this.mAdapterHelper.getCurrentSALogoIndexInUi(logoFromEmmc);
        } else if (FacPropertyDemo.HIFAC_MARKET_KO) {
            logoUIIndexByKey = FacLogoUtils.getLogoUIIndexByKey(logoFromEmmc);
        } else {
            logoUIIndexByKey = ApVsTILmap.TilToAp(10, logoFromEmmc);
        }
        Log.i("Fac_FacFunction_Adapter", "getLogo logo value after transition is " + logoUIIndexByKey);
        return logoUIIndexByKey;
    }

    @Override // com.hisense.tvcm.FacFunction
    public FacPropertyDemo.Logo getLogoEnum() {
        return FacPropertyDemo.Logo.values()[getLogo()];
    }

    @Override // com.hisense.tvcm.FacFunction
    public FacPropertyDemo.LogoForEM_SA getLogoForEMSAEnum() {
        return FacPropertyDemo.LogoForEM_SA.values()[getLogo()];
    }

    public String getLogoForSA() {
        int logoFromEmmc = getLogoFromEmmc();
        Log.i("Fac_FacFunction_Adapter", "getLogoForSA value is " + logoFromEmmc);
        return this.mAdapterHelper.getCurrentSALogoName(logoFromEmmc);
    }

    public String getLogoForTWN() {
        int logoFromEmmc = getLogoFromEmmc();
        Log.i("Fac_FacFunction_Adapter", "getLogoForTWN value is " + logoFromEmmc);
        if (logoFromEmmc == 95) {
            return "tos";
        }
        return logoFromEmmc == 60 ? "ben" : "NG";
    }

    public String getLogoForJPN() {
        int logoFromEmmc = getLogoFromEmmc();
        Log.i("Fac_FacFunction_Adapter", "getLogoForJPN value is " + logoFromEmmc);
        if (logoFromEmmc == 95) {
            return "tos";
        }
        return logoFromEmmc == 1 ? "his" : "NG";
    }

    public String getLogoFactorynameForEM() {
        int logoFromEmmc = getLogoFromEmmc();
        Log.i("Fac_FacFunction_Adapter", "getLogoFactorynameForEM value is " + logoFromEmmc);
        String logoNameByTilValue = ApVsTILmap.getLogoNameByTilValue(logoFromEmmc);
        Log.i("Fac_FacFunction_Adapter", "getLogoFactorynameForEM logoName is " + logoNameByTilValue);
        return logoNameByTilValue;
    }

    public FacPropertyDemo.CountryForRegion1 getCountryEnumForRegion1() {
        return FacPropertyDemo.CountryForRegion1.values()[getCountry()];
    }

    public FacPropertyDemo.CountryForRegion2 getCountryEnumForRegion2() {
        return FacPropertyDemo.CountryForRegion2.values()[getCountry()];
    }

    public FacPropertyDemo.CountryForRegion3 getCountryEnumForRegion3() {
        return FacPropertyDemo.CountryForRegion3.values()[getCountry()];
    }

    public FacPropertyDemo.CountryForRegion0 getCountryEnumForRegion0() {
        return FacPropertyDemo.CountryForRegion0.values()[getCountry()];
    }

    public FacPropertyDemo.CountryForRegion5 getCountryEnumForRegion5() {
        return FacPropertyDemo.CountryForRegion5.values()[getCountry()];
    }

    public FacPropertyDemo.CountryForRegion6 getCountryEnumForRegion6() {
        return FacPropertyDemo.CountryForRegion6.values()[getCountry()];
    }

    public FacPropertyDemo.CountryForRegion4 getCountryEnumForRegion4() {
        return FacPropertyDemo.CountryForRegion4.values()[getCountry()];
    }

    public String getLocalName() {
        int countryFromEmmc = getCountryFromEmmc();
        Log.i("Fac_FacFunction_Adapter", "getLocalName tilvalue is " + countryFromEmmc);
        if (FacPropertyDemo.HIFAC_MARKET_TW) {
            return countryFromEmmc == 52 ? MtkTvConfigTypeBase.S3166_CFG_COUNT_TWN : "NG";
        }
        if (FacPropertyDemo.HIFAC_MARKET_KO) {
            return countryFromEmmc == 206 ? MtkTvConfigTypeBase.S3166_CFG_COUNT_KOR : "NG";
        }
        return (FacPropertyDemo.HIFAC_MARKET_JP && countryFromEmmc == 202) ? MtkTvConfigTypeBase.S3166_CFG_COUNT_JPN : "NG";
    }

    public FacPropertyDemo.Language getlanguageEnum() {
        return FacPropertyDemo.Language.values()[getLanguage()];
    }

    public FacPropertyDemo.LanguageForRegion1 getlanguageEnumForRegion1() {
        return FacPropertyDemo.LanguageForRegion1.values()[getLanguage()];
    }

    public FacPropertyDemo.LanguageForRegion2 getlanguageEnumForRegion2() {
        return FacPropertyDemo.LanguageForRegion2.values()[getLanguage()];
    }

    public FacPropertyDemo.LanguageForRegion3 getlanguageEnumForRegion3() {
        return FacPropertyDemo.LanguageForRegion3.values()[getLanguage()];
    }

    public FacPropertyDemo.LanguageForRegion0 getlanguageEnumForRegion0() {
        return FacPropertyDemo.LanguageForRegion0.values()[getLanguage()];
    }

    public FacPropertyDemo.LanguageForRegion4 getlanguageEnumForRegion4() {
        return FacPropertyDemo.LanguageForRegion4.values()[getLanguage()];
    }

    public FacPropertyDemo.LanguageForRegion5 getlanguageEnumForRegion5() {
        return FacPropertyDemo.LanguageForRegion5.values()[getLanguage()];
    }

    public FacPropertyDemo.LanguageForRegion6 getlanguageEnumForRegion6() {
        return FacPropertyDemo.LanguageForRegion6.values()[getLanguage()];
    }

    public String getlanguageEnumForTWN() {
        int languageFromEmmc = getLanguageFromEmmc();
        Log.i("Fac_FacFunction_Adapter", "getlanguageEnumForTWN tilValue is " + languageFromEmmc);
        int i = -1;
        for (FacPropertyDemo.LanguageForTWN languageForTWN : FacPropertyDemo.LanguageForTWN.values()) {
            if (languageForTWN.getIndex() == languageFromEmmc) {
                i = languageForTWN.enumIndex;
            }
        }
        Log.i("Fac_FacFunction_Adapter", "getlanguageEnumForTWN languageIndex is " + i);
        return (i < 0 || i > FacPropertyDemo.LanguageForTWN.getSize()) ? "NG" : FacPropertyDemo.LanguageForTWN.values()[i].toString();
    }

    public String getlanguageEnumForJPN() {
        int languageFromEmmc = getLanguageFromEmmc();
        Log.i("Fac_FacFunction_Adapter", "getlanguageEnumForJPN tilValue is " + languageFromEmmc);
        int i = -1;
        for (FacPropertyDemo.LanguageForJPN languageForJPN : FacPropertyDemo.LanguageForJPN.values()) {
            if (languageForJPN.getIndex() == languageFromEmmc) {
                i = languageForJPN.enumIndex;
            }
        }
        Log.i("Fac_FacFunction_Adapter", "getlanguageEnumForJPN languageIndex is " + i);
        return (i < 0 || i > FacPropertyDemo.LanguageForJPN.getSize()) ? "NG" : FacPropertyDemo.LanguageForJPN.values()[i].toString();
    }

    @Override // com.hisense.tvcm.FacFunction
    public String getLogoName() {
        String logoFactorynameForEM;
        if (FacPropertyDemo.HIFAC_MARKET_HK || FacPropertyDemo.HIFAC_MARKET_MONITOR || FacPropertyDemo.HIFAC_PRODUCT_OEM) {
            logoFactorynameForEM = getLogoFactorynameForEM();
        } else if (FacPropertyDemo.HIFAC_MARKET_TW) {
            logoFactorynameForEM = getLogoForTWN();
        } else if (FacPropertyDemo.HIFAC_MARKET_JP) {
            logoFactorynameForEM = getLogoForJPN();
        } else if (FacPropertyDemo.HIFAC_MARKET.equals(FacPropertyDemo.MarketType.EM)) {
            if (FacPropertyDemo.HIFAC_REGION5_SUPPORT) {
                logoFactorynameForEM = getLogoForEMSAEnum().getName();
            } else {
                logoFactorynameForEM = getLogoFactorynameForEM();
            }
        } else if (FacPropertyDemo.HIFAC_MARKET_SA) {
            logoFactorynameForEM = getLogoForSA();
        } else if (FacPropertyDemo.HIFAC_MARKET_KO) {
            logoFactorynameForEM = getLogoFactorynameForEM();
        } else {
            logoFactorynameForEM = getLogoEnum().getName();
        }
        Log.i("Fac_FacFunction_Adapter", "getLogoName = " + logoFactorynameForEM);
        return logoFactorynameForEM;
    }

    @Override // com.hisense.tvcm.FacFunction
    public String getFullLogoName() {
        String logoFullNameByTilValue;
        if (FacPropertyDemo.HIFAC_MARKET_HK || FacPropertyDemo.HIFAC_MARKET_MONITOR) {
            int logoFromEmmc = getLogoFromEmmc();
            Log.i("Fac_FacFunction_Adapter", "getFullLogoName monitor value is " + logoFromEmmc);
            logoFullNameByTilValue = ApVsTILmap.getLogoFullNameByTilValue(logoFromEmmc);
            Log.i("Fac_FacFunction_Adapter", "getFullLogoName monitor logoName is " + logoFullNameByTilValue);
        } else if (FacPropertyDemo.HIFAC_MARKET_TW) {
            logoFullNameByTilValue = getLogoForTWN();
        } else if (FacPropertyDemo.HIFAC_MARKET.equals(FacPropertyDemo.MarketType.EM)) {
            if (FacPropertyDemo.HIFAC_REGION5_SUPPORT) {
                logoFullNameByTilValue = "";
            } else {
                int logoFromEmmc2 = getLogoFromEmmc();
                Log.i("Fac_FacFunction_Adapter", "getFullLogoName value is " + logoFromEmmc2);
                logoFullNameByTilValue = ApVsTILmap.getLogoFullNameByTilValue(logoFromEmmc2);
                Log.i("Fac_FacFunction_Adapter", "getFullLogoName logoName is " + logoFullNameByTilValue);
            }
        } else if (FacPropertyDemo.HIFAC_MARKET_SA) {
            logoFullNameByTilValue = getLogoForSA();
        } else {
            logoFullNameByTilValue = getLogoEnum().getName();
        }
        Log.i("Fac_FacFunction_Adapter", "getFullLogoName = " + logoFullNameByTilValue);
        return logoFullNameByTilValue;
    }

    @Override // com.hisense.tvcm.FacFunction
    public String getCountryName() {
        int region = getRegion();
        String countryStringByIndex = "NG";
        if (FacPropertyDemo.HIFAC_MARKET_HK) {
            int countryFromEmmc = getCountryFromEmmc();
            Log.i("Fac_FacFunction_Adapter", "getCountryName country value is " + countryFromEmmc);
            return countryFromEmmc == 154 ? MtkTvConfigTypeBase.S3166_CFG_COUNT_HKG : "NG";
        }
        if (FacPropertyDemo.HIFAC_MARKET_MONITOR) {
            countryStringByIndex = ApVsTILmap.getCountryNameByTilValue(getCountryFromEmmc());
        } else if (FacPropertyDemo.HIFAC_MARKET.equals(FacPropertyDemo.MarketType.EM)) {
            if (FacPropertyDemo.HIFAC_MARKET_TW) {
                countryStringByIndex = getLocalName();
            } else if (region == 0) {
                Log.i("Fac_FacFunction_Adapter", "region0");
                countryStringByIndex = getCountryEnumForRegion0().getName();
            } else if (region == 1) {
                Log.i("Fac_FacFunction_Adapter", "region1");
                countryStringByIndex = getCountryEnumForRegion1().getName();
            } else if (region == 2) {
                Log.i("Fac_FacFunction_Adapter", "region2");
                countryStringByIndex = getCountryEnumForRegion2().getName();
            } else if (region == 3) {
                Log.i("Fac_FacFunction_Adapter", "region3");
                countryStringByIndex = getCountryEnumForRegion3().getName();
            } else if (region == 4) {
                Log.i("Fac_FacFunction_Adapter", "region4");
                countryStringByIndex = getCountryEnumForRegion4().getName();
            }
        } else if (FacPropertyDemo.HIFAC_MARKET_SA) {
            if (region == 5) {
                countryStringByIndex = getCountryEnumForRegion5().getName();
            } else if (region == 6) {
                countryStringByIndex = getCountryEnumForRegion6().getName();
            }
        } else if (FacPropertyDemo.HIFAC_MARKET_JP) {
            Log.i("Fac_FacFunction_Adapter", "getCountry JP");
            countryStringByIndex = getLocalName();
        } else if (FacPropertyDemo.HIFAC_MARKET_KO) {
            countryStringByIndex = getLocalName();
        } else {
            countryStringByIndex = ApVsTILmap.getCountryStringByIndex(getCountryFromEmmc());
        }
        Log.i("Fac_FacFunction_Adapter", "get country = " + countryStringByIndex);
        return countryStringByIndex;
    }

    @Override // com.hisense.tvcm.FacFunction
    public String getlanguageName() {
        String languageNameByTilValue;
        int region = getRegion();
        if (FacPropertyDemo.HIFAC_MARKET_HK || FacPropertyDemo.HIFAC_MARKET_MONITOR) {
            languageNameByTilValue = ApVsTILmap.getLanguageNameByTilValue(getLanguageFromEmmc());
            Log.i("Fac_FacFunction_Adapter", "get global language = " + languageNameByTilValue);
        } else if (FacPropertyDemo.HIFAC_MARKET_TW) {
            languageNameByTilValue = getlanguageEnumForTWN();
            Log.i("Fac_FacFunction_Adapter", "get language = " + languageNameByTilValue);
        } else if (FacPropertyDemo.HIFAC_MARKET_JP) {
            languageNameByTilValue = getlanguageEnumForJPN();
            Log.i("Fac_FacFunction_Adapter", "get language = " + languageNameByTilValue);
        } else if (FacPropertyDemo.HIFAC_MARKET.equals(FacPropertyDemo.MarketType.EM)) {
            if (region == 0) {
                languageNameByTilValue = getlanguageEnumForRegion0().getName();
            } else if (region == 1) {
                languageNameByTilValue = getlanguageEnumForRegion1().getName();
            } else if (region == 2) {
                languageNameByTilValue = getlanguageEnumForRegion2().getName();
            } else if (region == 3) {
                languageNameByTilValue = getlanguageEnumForRegion3().getName();
            } else {
                languageNameByTilValue = getlanguageEnumForRegion4().getName();
            }
        } else if (!FacPropertyDemo.HIFAC_MARKET_SA) {
            languageNameByTilValue = FacPropertyDemo.HIFAC_MARKET_KO ? MtkTvConfigTypeBase.S639_CFG_LANG_KOR : getlanguageEnum().getName();
        } else if (region == 5) {
            languageNameByTilValue = getlanguageEnumForRegion5().getName();
        } else {
            languageNameByTilValue = region == 6 ? getlanguageEnumForRegion6().getName() : "NG";
        }
        Log.i("Fac_FacFunction_Adapter", "get language = " + languageNameByTilValue);
        return languageNameByTilValue;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setPiCurveBrigthMin(int i) {
        Log.i("Fac_FacFunction_Adapter", "setPiCurveBrigthMin called, param: " + i);
        this.mHisenseTvAPI.setVideoCurveDetailValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_BRI_MIN, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getPiCurveBrigthMin() {
        int[] brightnessMin = this.mHisenseTvAPI.getBrightnessMin();
        if (brightnessMin != null) {
            Log.i("Fac_FacFunction_Adapter", "getPiCurveBrigthMin called, param:" + brightnessMin[0]);
            return brightnessMin[0];
        }
        Log.i("Fac_FacFunction_Adapter", "getPiCurveBrigthMin called,array is null");
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setPiCurveBrigth20(int i) {
        Log.i("Fac_FacFunction_Adapter", "setPiCurveBrigth20 called, param: " + i);
        this.mHisenseTvAPI.setVideoCurveDetailValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_BRI_20, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getPiCurveBrigth20() {
        int[] brightness20 = this.mHisenseTvAPI.getBrightness20();
        if (brightness20 != null) {
            Log.i("Fac_FacFunction_Adapter", "getPiCurveBrigth20 called, param:" + brightness20[1]);
            return brightness20[1];
        }
        Log.i("Fac_FacFunction_Adapter", "getPiCurveBrigth20 called,array is null");
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setPiCurveBrigthMid(int i) {
        Log.i("Fac_FacFunction_Adapter", "setPiCurveBrigthMid called, param: " + i);
        this.mHisenseTvAPI.setVideoCurveDetailValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_BRI_MID, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getPiCurveBrigthMid() {
        int[] brightnessMid = this.mHisenseTvAPI.getBrightnessMid();
        if (brightnessMid != null) {
            Log.i("Fac_FacFunction_Adapter", "getPiCurveBrigthMid called, param:" + brightnessMid[2]);
            return brightnessMid[2];
        }
        Log.i("Fac_FacFunction_Adapter", "getPiCurveBrigthMid called,array is null");
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setPiCurveBrigth80(int i) {
        Log.i("Fac_FacFunction_Adapter", "setPiCurveBrigth80 called, param: " + i);
        this.mHisenseTvAPI.setVideoCurveDetailValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_BRI_80, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getPiCurveBrigth80() {
        int[] brightness80 = this.mHisenseTvAPI.getBrightness80();
        if (brightness80 != null) {
            Log.i("Fac_FacFunction_Adapter", "getPiCurveBrigth80 called, param:" + brightness80[3]);
            return brightness80[3];
        }
        Log.i("Fac_FacFunction_Adapter", "getPiCurveBrigth80 called,array is null");
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setPiCurveBrigthMax(int i) {
        Log.i("Fac_FacFunction_Adapter", "setPiCurveBrigthMax called, param: " + i);
        this.mHisenseTvAPI.setVideoCurveDetailValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_BRI_MAX, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getPiCurveBrigthMax() {
        int[] brightnessMax = this.mHisenseTvAPI.getBrightnessMax();
        if (brightnessMax != null) {
            Log.i("Fac_FacFunction_Adapter", "getPiCurveBrigthMax called, param:" + brightnessMax[4]);
            return brightnessMax[4];
        }
        Log.i("Fac_FacFunction_Adapter", "getPiCurveBrigthMax called,array is null");
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setPiCurveContractMin(int i) {
        Log.i("Fac_FacFunction_Adapter", "setPiCurveContractMin called, param: " + i);
        this.mHisenseTvAPI.setVideoCurveDetailValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_CNT_MIN, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getPiCurveContractMin() {
        int[] contrastMin = this.mHisenseTvAPI.getContrastMin();
        if (contrastMin != null) {
            Log.i("Fac_FacFunction_Adapter", "getPiCurveContractMin called, param:" + contrastMin[0]);
            return contrastMin[0];
        }
        Log.i("Fac_FacFunction_Adapter", "getPiCurveContractMin called,array is null");
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setPiCurveContract20(int i) {
        Log.i("Fac_FacFunction_Adapter", "setPiCurveContract20 called, param: " + i);
        this.mHisenseTvAPI.setVideoCurveDetailValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_CNT_20, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getPiCurveContract20() {
        int[] contrast20 = this.mHisenseTvAPI.getContrast20();
        if (contrast20 != null) {
            Log.i("Fac_FacFunction_Adapter", "getPiCurveContract20 called, param:" + contrast20[1]);
            return contrast20[1];
        }
        Log.i("Fac_FacFunction_Adapter", "getPiCurveContract20 called,array is null");
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setPiCurveContractMid(int i) {
        Log.i("Fac_FacFunction_Adapter", "setPiCurveContractMid called, param: " + i);
        this.mHisenseTvAPI.setVideoCurveDetailValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_CNT_MID, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getPiCurveContractMid() {
        int[] contrastMid = this.mHisenseTvAPI.getContrastMid();
        if (contrastMid != null) {
            Log.i("Fac_FacFunction_Adapter", "getPiCurveContractMid called, param:" + contrastMid[2]);
            return contrastMid[2];
        }
        Log.i("Fac_FacFunction_Adapter", "getPiCurveContractMid called,array is null");
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setPiCurveContract80(int i) {
        Log.i("Fac_FacFunction_Adapter", "setPiCurveContract80 called, param: " + i);
        this.mHisenseTvAPI.setVideoCurveDetailValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_CNT_80, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getPiCurveContract80() {
        int[] contrast80 = this.mHisenseTvAPI.getContrast80();
        if (contrast80 != null && contrast80.length > 3) {
            Log.i("Fac_FacFunction_Adapter", "getPiCurveContract80 called, param:" + contrast80[3]);
            return contrast80[3];
        }
        Log.i("Fac_FacFunction_Adapter", "getPiCurveContract80 called,array is null");
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setPiCurveContractMax(int i) {
        Log.i("Fac_FacFunction_Adapter", "setPiCurveContractMax called, param: " + i);
        this.mHisenseTvAPI.setVideoCurveDetailValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_CNT_MAX, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getPiCurveContractMax() {
        int[] contrastMax = this.mHisenseTvAPI.getContrastMax();
        if (contrastMax != null && contrastMax.length > 4) {
            Log.i("Fac_FacFunction_Adapter", "getPiCurveContractMax called, param:" + contrastMax[4]);
            return contrastMax[4];
        }
        Log.i("Fac_FacFunction_Adapter", "getPiCurveContractMax called,array is null");
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setPiCurveSaturationMin(int i) {
        Log.i("Fac_FacFunction_Adapter", "setPiCurveSaturationMin called, param: " + i);
        this.mHisenseTvAPI.setVideoCurveDetailValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_SAT_MIN, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getPiCurveSaturationMin() {
        int[] saturationMin = this.mHisenseTvAPI.getSaturationMin();
        if (saturationMin != null) {
            Log.i("Fac_FacFunction_Adapter", "getPiCurveSaturationMin called, param:" + saturationMin[0]);
            return saturationMin[0];
        }
        Log.i("Fac_FacFunction_Adapter", "getPiCurveSaturationMin called,array is null");
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setPiCurveSaturation20(int i) {
        Log.i("Fac_FacFunction_Adapter", "setPiCurveSaturation20 called, param: " + i);
        this.mHisenseTvAPI.setVideoCurveDetailValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_SAT_20, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getPiCurveSaturation20() {
        int[] saturation20 = this.mHisenseTvAPI.getSaturation20();
        if (saturation20 != null) {
            Log.i("Fac_FacFunction_Adapter", "getPiCurveSaturation20 called, param:" + saturation20[1]);
            return saturation20[1];
        }
        Log.i("Fac_FacFunction_Adapter", "getPiCurveSaturation20 called,array is null");
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setPiCurveSaturationMid(int i) {
        Log.i("Fac_FacFunction_Adapter", "setPiCurveSaturationMid called, param: " + i);
        this.mHisenseTvAPI.setVideoCurveDetailValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_SAT_MID, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getPiCurveSaturationMid() {
        int[] saturationMid = this.mHisenseTvAPI.getSaturationMid();
        if (saturationMid != null) {
            Log.i("Fac_FacFunction_Adapter", "getPiCurveSaturationMid called, param:" + saturationMid[2]);
            return saturationMid[2];
        }
        Log.i("Fac_FacFunction_Adapter", "getPiCurveSaturationMid called,array is null");
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setPiCurveSaturation80(int i) {
        Log.i("Fac_FacFunction_Adapter", "setPiCurveSaturation80 called, param: " + i);
        this.mHisenseTvAPI.setVideoCurveDetailValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_SAT_80, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getPiCurveSaturation80() {
        int[] saturation80 = this.mHisenseTvAPI.getSaturation80();
        if (saturation80 != null) {
            Log.i("Fac_FacFunction_Adapter", "getPiCurveSaturation80 called, param:" + saturation80[3]);
            return saturation80[3];
        }
        Log.i("Fac_FacFunction_Adapter", "getPiCurveSaturation80 called,array is null");
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setPiCurveSaturationMax(int i) {
        Log.i("Fac_FacFunction_Adapter", "setPiCurveSaturationMax called, param: " + i);
        this.mHisenseTvAPI.setVideoCurveDetailValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_SAT_MAX, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getPiCurveSaturationMax() {
        int[] saturationMax = this.mHisenseTvAPI.getSaturationMax();
        if (saturationMax != null) {
            Log.i("Fac_FacFunction_Adapter", "getPiCurveSaturationMax called, param:" + saturationMax[4]);
            return saturationMax[4];
        }
        Log.i("Fac_FacFunction_Adapter", "getPiCurveSaturationMax called,array is null");
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setPiCurveHueMin(int i) {
        Log.i("Fac_FacFunction_Adapter", "setPiCurveHueMin called, param: " + i);
        this.mHisenseTvAPI.setVideoCurveDetailValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_HUE_MIN, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getPiCurveHueMin() {
        int[] hueMin = this.mHisenseTvAPI.getHueMin();
        if (hueMin != null) {
            Log.i("Fac_FacFunction_Adapter", "getPiCurveHueMin called, param:" + hueMin[0]);
            return hueMin[0];
        }
        Log.i("Fac_FacFunction_Adapter", "getPiCurveHueMin called,array is null");
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setPiCurveHue20(int i) {
        Log.i("Fac_FacFunction_Adapter", "setPiCurveHue20 called, param: " + i);
        this.mHisenseTvAPI.setVideoCurveDetailValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_HUE_20, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getPiCurveHue20() {
        int[] hue20 = this.mHisenseTvAPI.getHue20();
        if (hue20 != null) {
            Log.i("Fac_FacFunction_Adapter", "getPiCurveHue20 called, param:" + hue20[1]);
            return hue20[1];
        }
        Log.i("Fac_FacFunction_Adapter", "getPiCurveHue20 called,array is null");
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setPiCurveHueMid(int i) {
        Log.i("Fac_FacFunction_Adapter", "setPiCurveHueMid called, param: " + i);
        this.mHisenseTvAPI.setVideoCurveDetailValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_HUE_MID, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getPiCurveHueMid() {
        int[] hueMid = this.mHisenseTvAPI.getHueMid();
        if (hueMid != null) {
            Log.i("Fac_FacFunction_Adapter", "getPiCurveHueMid called, param:" + hueMid[2]);
            return hueMid[2];
        }
        Log.i("Fac_FacFunction_Adapter", "getPiCurveHueMid called,array is null");
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setPiCurveHue80(int i) {
        Log.i("Fac_FacFunction_Adapter", "setPiCurveHue80 called, param: " + i);
        this.mHisenseTvAPI.setVideoCurveDetailValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_HUE_80, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getPiCurveHue80() {
        int[] hue80 = this.mHisenseTvAPI.getHue80();
        if (hue80 != null) {
            Log.i("Fac_FacFunction_Adapter", "getPiCurveHue80 called, param:" + hue80[3]);
            return hue80[3];
        }
        Log.i("Fac_FacFunction_Adapter", "getPiCurveHue80 called,array is null");
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setPiCurveHueMax(int i) {
        Log.i("Fac_FacFunction_Adapter", "setPiCurveHueMax called, param: " + i);
        this.mHisenseTvAPI.setVideoCurveDetailValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_HUE_MAX, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getPiCurveHueMax() {
        int[] hueMax = this.mHisenseTvAPI.getHueMax();
        if (hueMax != null) {
            Log.i("Fac_FacFunction_Adapter", "getPiCurveHueMax called, param:" + hueMax[4]);
            return hueMax[4];
        }
        Log.i("Fac_FacFunction_Adapter", "getPiCurveHueMax called,array is null");
        return 0;
    }

    public List<ChannelInfoBase> getAirChannelList() {
        this.list_air = this.mHisChannelListApiImpl.getChannelListByTunerMode(0, false);
        Log.i("Fac_FacFunction_Adapter", "list_air :" + this.list_air.size());
        for (ChannelInfoBase channelInfoBase : this.list_air) {
            Log.i("Fac_FacFunction_Adapter", "list_airIChannelInfo = " + channelInfoBase.getChannelNum() + " " + channelInfoBase.getChannelKey());
        }
        return this.list_air;
    }

    public List<ChannelInfoBase> getCableChannelList() {
        this.list_cable = this.mHisChannelListApiImpl.getChannelListByTunerMode(1, false);
        Log.i("Fac_FacFunction_Adapter", "list_cable :" + this.list_cable.size());
        for (ChannelInfoBase channelInfoBase : this.list_cable) {
            Log.i("Fac_FacFunction_Adapter", "list_cableIChannelInfo = " + channelInfoBase.getChannelNum() + " " + channelInfoBase.getChannelKey());
        }
        return this.list_cable;
    }

    public List<com.hisense.tvmanager.atsc.ChannelInfoBase> getChannelListByTunerMode(int i) {
        if (i == 0) {
            this.list_air_ATSC = this.mChannelList.getChannelListByTunerMode(0);
            Log.i("Fac_FacFunction_Adapter", "list_air_ATSC :" + this.list_air_ATSC.size());
            for (com.hisense.tvmanager.atsc.ChannelInfoBase channelInfoBase : this.list_air_ATSC) {
                Log.i("Fac_FacFunction_Adapter", "list_air_ATSC i ChannelNum : " + channelInfoBase.getChannelNumber() + "; getChannelKey : " + channelInfoBase.getServiceUUID());
            }
            return this.list_air_ATSC;
        }
        this.list_cable_ATSC = this.mChannelList.getChannelListByTunerMode(1);
        Log.i("Fac_FacFunction_Adapter", "list_cable_ATSC :" + this.list_cable_ATSC.size());
        for (com.hisense.tvmanager.atsc.ChannelInfoBase channelInfoBase2 : this.list_cable_ATSC) {
            Log.i("Fac_FacFunction_Adapter", "list_cable_ATSC i ChannelNum : " + channelInfoBase2.getChannelNumber() + "; getChannelKey : " + channelInfoBase2.getServiceUUID());
        }
        return this.list_cable_ATSC;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void channelDown() {
        int channelIndex;
        if (FacPropertyDemo.HIFAC_MARKET_JP) {
            this.mFacTvsTvManager.setTune(FacTvsFunctionUtil.FAC_TVS_TUNE_CHANNEL_DOWN);
            return;
        }
        if (FacPropertyDemo.HIFAC_ATSC30_SUPPORT) {
            channelIndex = getChannelIndexForAtsc();
        } else {
            channelIndex = getChannelIndex();
        }
        int i = channelIndex - 1;
        Log.i("Fac_FacFunction_Adapter", "channel down chnum = " + i);
        if (FacPropertyDemo.HIFAC_ATSC30_SUPPORT) {
            changeChannelForChannelUPAndDownATSC(i);
        } else {
            changeChannelForChannelUPAndDown(i);
        }
    }

    @Override // com.hisense.tvcm.FacFunction
    public void channelUp() {
        int channelIndex;
        if (FacPropertyDemo.HIFAC_MARKET_JP) {
            this.mFacTvsTvManager.setTune(FacTvsFunctionUtil.FAC_TVS_TUNE_CHANNEL_UP);
            return;
        }
        if (FacPropertyDemo.HIFAC_ATSC30_SUPPORT) {
            channelIndex = getChannelIndexForAtsc();
        } else {
            channelIndex = getChannelIndex();
        }
        int i = channelIndex + 1;
        Log.i("Fac_FacFunction_Adapter", "channel up chnum = " + i);
        if (FacPropertyDemo.HIFAC_ATSC30_SUPPORT) {
            changeChannelForChannelUPAndDownATSC(i);
        } else {
            changeChannelForChannelUPAndDown(i);
        }
    }

    public int getFrameRate() {
        Log.i("Fac_FacFunction_Adapter", "resolution is null");
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void changeChannel(int i) {
        int tunerMode = getTunerMode();
        Log.i("Fac_FacFunction_Adapter", "change channel to " + i);
        if (FacPropertyDemo.HIFAC_ATSC30_SUPPORT) {
            changeChannelATSC(i, tunerMode);
        } else {
            changeChannel(i, tunerMode);
        }
    }

    public String getVideoFormatInfo() {
        int[] signalVideoFormat = this.mHisenseTvAPI.getSignalVideoFormat();
        if (signalVideoFormat == null || signalVideoFormat.length < 4) {
            Log.d("Fac_FacFunction_Adapter", "getVideoFormat --> Video Format Data is wrong !");
            return "";
        }
        StringBuilder sb = new StringBuilder();
        if (signalVideoFormat[0] != 0 && signalVideoFormat[1] != 0) {
            sb.append(signalVideoFormat[0]);
            sb.append('*');
            sb.append(signalVideoFormat[1]);
            sb.append(signalVideoFormat[3] == 1 ? 'p' : 'l');
            sb.append('@');
            sb.append(signalVideoFormat[2]);
            sb.append("Hz");
        }
        Log.d("Fac_FacFunction_Adapter", "getVideoFormat --> values 0:" + signalVideoFormat[0] + "  1:" + signalVideoFormat[1] + "   2:" + signalVideoFormat[2] + "   3:" + signalVideoFormat[3] + "   result:" + sb.toString());
        return sb.toString();
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getBypassPQEnable() {
        int byPassValue = this.mHisenseTvAPI.getByPassValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_PQ_BYPASS);
        Log.i("Fac_FacFunction_Adapter", "!!!getBypassPQEnable called, value:" + byPassValue);
        return byPassValue;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setBypassPQEnable(int i) {
        Log.i("Fac_FacFunction_Adapter", "!!!setBypassPQEnable called, param:" + i);
        this.mHisenseTvAPI.setByPassValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_PQ_BYPASS, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getAutoVolumeCtrlEnable() {
        int aVCEnableValue = this.mHisenseTvAPI.getAVCEnableValue(MtkTvConfigTypeBase.CFG_AUD_AUTO_VOLUME_CTRL);
        Log.i("Fac_FacFunction_Adapter", "!!!getAutoVolumeCtrlEnable called, value:" + aVCEnableValue);
        return aVCEnableValue;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setAutoVolumeCtrlEnable(int i) {
        Log.i("Fac_FacFunction_Adapter", "!!!setAutoVolumeCtrlEnable called, param:" + i);
        this.mHisenseTvAPI.setAVCEnableValue(MtkTvConfigTypeBase.CFG_AUD_AUTO_VOLUME_CTRL, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getBypassGammaEnable() {
        int byPassValue = this.mHisenseTvAPI.getByPassValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_GAMMA_BYPASS);
        Log.i("Fac_FacFunction_Adapter", "!!!getBypassGammaEnable called, value:" + byPassValue);
        return byPassValue;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setBypassGammaEnable(int i) {
        Log.i("Fac_FacFunction_Adapter", "!!!setBypassGammaEnable called, param:" + i);
        this.mHisenseTvAPI.setByPassValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_GAMMA_BYPASS, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getBypassSecEnable() {
        int byPassValue = this.mHisenseTvAPI.getByPassValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_SCE_BYPASS);
        Log.i("Fac_FacFunction_Adapter", "!!!getBypassSecEnable called, value:" + byPassValue);
        return byPassValue;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setBypassSecEnable(int i) {
        Log.i("Fac_FacFunction_Adapter", "!!!setBypassSecEnable called, param:" + i);
        this.mHisenseTvAPI.setByPassValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_SCE_BYPASS, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getBypassXvyccEnable() {
        int byPassValue = this.mHisenseTvAPI.getByPassValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_XVYCC_BYPASS);
        Log.i("Fac_FacFunction_Adapter", "!!!getBypassXvyccEnable called, value:" + byPassValue);
        return byPassValue;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setBypassXvyccEnable(int i) {
        Log.i("Fac_FacFunction_Adapter", "!!!setBypassXvyccEnable called, param:" + i);
        this.mHisenseTvAPI.setByPassValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_XVYCC_BYPASS, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public String getTVRunningTime() {
        int tvRunTime = this.mHisenseTvAPI.getTvRunTime(MtkTvConfigTypeBase.CFG_FACTORY_RUN_TIME);
        Log.i("Fac_FacFunction_Adapter", "TV runtime = " + tvRunTime);
        int i = tvRunTime % 60;
        int i2 = tvRunTime / 60;
        String str = "day:" + (i2 / 24) + "  hour:" + (i2 % 24) + "  min:" + i;
        Log.i("Fac_FacFunction_Adapter", "getRuntimeString = " + str);
        return str;
    }

    public String getTVRunningTimeSerial() {
        int tvRunTime = this.mHisenseTvAPI.getTvRunTime(MtkTvConfigTypeBase.CFG_FACTORY_RUN_TIME);
        Log.i("Fac_FacFunction_Adapter", "TV runtime = " + tvRunTime);
        String string = Integer.toString(tvRunTime / 60);
        Log.i("Fac_FacFunction_Adapter", "getRuntimeString = " + string);
        return string;
    }

    public boolean setCountryString(String str) {
        Log.i("Fac_FacFunction_Adapter", "setCountryString value = " + str);
        String upperCase = str.toUpperCase(Locale.ENGLISH);
        Log.i("Fac_FacFunction_Adapter", "setCountryString countryName = " + upperCase);
        int countryIndexByString = ApVsTILmap.getCountryIndexByString(upperCase);
        if (FacPropertyDemo.HIFAC_MARKET.equals(FacPropertyDemo.MarketType.EM)) {
            if (isRegionX(countryIndexByString) == 0) {
                setRegionTilValue(0);
            } else if (isRegionX(countryIndexByString) == 1) {
                setRegionTilValue(1);
            } else if (isRegionX(countryIndexByString) == 2) {
                setRegionTilValue(2);
            } else if (isRegionX(countryIndexByString) == 3) {
                setRegionTilValue(3);
            } else {
                setRegionTilValue(4);
            }
        } else if (FacPropertyDemo.HIFAC_MARKET_SA) {
            if (isRegionX(countryIndexByString) == 5) {
                setRegionTilValue(5);
            } else {
                setRegionTilValue(6);
            }
        }
        if (countryIndexByString == 0) {
            return false;
        }
        setCountryIndex(countryIndexByString);
        Log.i("Fac_FacFunction_Adapter", "set country = " + str);
        return true;
    }

    public boolean setLanguageString(String str) {
        String lowerCase = str.toLowerCase(Locale.ENGLISH);
        Log.i("Fac_FacFunction_Adapter", "setLanguageString languageString = " + lowerCase);
        int languageIndexByString = ApVsTILmap.getLanguageIndexByString(lowerCase);
        Log.i("Fac_FacFunction_Adapter", "setLanguageString languageIndex = " + languageIndexByString);
        if (languageIndexByString == 0) {
            return false;
        }
        setLangIndex(languageIndexByString);
        return true;
    }

    public boolean setLogoString(String str) {
        int logoIndexByStr;
        if (str.equalsIgnoreCase("his")) {
            logoIndexByStr = 1;
        } else if (str.equalsIgnoreCase("shp")) {
            logoIndexByStr = 49;
        } else if (str.equalsIgnoreCase("tos")) {
            logoIndexByStr = 95;
        } else if (str.equalsIgnoreCase("lca")) {
            logoIndexByStr = 259;
        } else if (str.equalsIgnoreCase("con")) {
            logoIndexByStr = 18;
        } else if (str.equalsIgnoreCase("alh")) {
            logoIndexByStr = 86;
        } else if (str.equalsIgnoreCase("gua")) {
            logoIndexByStr = 55;
        } else if (str.equalsIgnoreCase("gen")) {
            logoIndexByStr = 19;
        } else if (str.equalsIgnoreCase("vuz")) {
            logoIndexByStr = 23;
        } else if (str.equalsIgnoreCase("llo")) {
            logoIndexByStr = 24;
        } else if (str.equalsIgnoreCase("bpl")) {
            logoIndexByStr = 72;
        } else if (str.equalsIgnoreCase("vis")) {
            logoIndexByStr = 90;
        } else if (str.equalsIgnoreCase("aba")) {
            logoIndexByStr = 70;
        } else if (str.equalsIgnoreCase("inn")) {
            logoIndexByStr = 25;
        } else if (str.equalsIgnoreCase("ben")) {
            logoIndexByStr = 60;
        } else if (str.equalsIgnoreCase("mag")) {
            logoIndexByStr = 68;
        } else if (str.equalsIgnoreCase("dof")) {
            logoIndexByStr = 92;
        } else if (str.equalsIgnoreCase("dex")) {
            logoIndexByStr = 30;
        } else if (str.equalsIgnoreCase("haa")) {
            logoIndexByStr = 20;
        } else if (str.equalsIgnoreCase("tit")) {
            logoIndexByStr = 21;
        } else if (str.equalsIgnoreCase("kmc")) {
            logoIndexByStr = 22;
        } else if (str.equalsIgnoreCase("tho")) {
            logoIndexByStr = 45;
        } else if (str.equalsIgnoreCase("str")) {
            logoIndexByStr = 51;
        } else if (str.equalsIgnoreCase("kal")) {
            logoIndexByStr = 13;
        } else {
            logoIndexByStr = str.equalsIgnoreCase("ggb") ? MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_BITRATE_TOO_HIGH : 0;
        }
        if (FacPropertyDemo.HIFAC_MARKET_SA) {
            logoIndexByStr = this.mAdapterHelper.getNewSALogoValue(str);
        } else if (FacPropertyDemo.HIFAC_MARKET.equals(FacPropertyDemo.MarketType.EM) || logoIndexByStr == 0) {
            logoIndexByStr = ApVsTILmap.getLogoIndexByStr(str.toLowerCase());
        }
        if (logoIndexByStr == 0) {
            return false;
        }
        setBrand(str);
        setLogoIndex(logoIndexByStr);
        Log.i("Fac_FacFunction_Adapter", "set logoString = " + str);
        return true;
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean closeGamma(int[] iArr) {
        Log.i("Fac_FacFunction_Adapter", "closeGamma : mode = -1 ; R = " + iArr[1] + "; G = " + iArr[2] + " ; B = " + iArr[3]);
        return this.mHisenseTvAPI.adjustGammaPattenr(-1, iArr[1], iArr[2], iArr[3]) == 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean resetGamma(int i) {
        Log.i("Fac_FacFunction_Adapter", "reset gamma");
        removeFile(FacParamManager.FAC_AUTO_GAMMA_FILE_PATH);
        removeFile(FacParamManager.FAC_AUTO_GAMMA_BIN_FILE_PATH);
        return this.mHisenseTvAPI.setAutoGammaEnable(0) == 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean setGamma(int[] iArr) {
        Log.i("Fac_FacFunction_Adapter", "setGamma : mode = 0 ; R = " + iArr[1] + "; G = " + iArr[2] + " ; B = " + iArr[3]);
        return this.mHisenseTvAPI.adjustGammaPattenr(0, iArr[1], iArr[2], iArr[3]) == 0;
    }

    public boolean set4096Gamma(int[] iArr) {
        Log.i("Fac_FacFunction_Adapter", "setGamma");
        return this.mHisenseTvAPI.adjust4096GammaPattenr(iArr, 4);
    }

    public boolean setForce120HzEnable(int i) {
        Log.i("Fac_FacFunction_Adapter", "setForce120HZEnable");
        return HisVideoApi.setForce120HzEnable(i) == 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public FacPropertyDemo.ColorTuner getColorTuner() {
        int colorTuner = this.mHisenseTvAPI.getColorTuner();
        Log.i("Fac_FacFunction_Adapter", "colortuner get mtkvalue = " + colorTuner);
        return FacPropertyDemo.ColorTuner.values()[colorTuner];
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setColorTuner(FacPropertyDemo.ColorTuner colorTuner) {
        Log.i("Fac_FacFunction_Adapter", "set colortuner mtkvalue= " + colorTuner.ordinal());
        this.mHisenseTvAPI.setColorTuner(colorTuner.ordinal());
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getCTHue() {
        int cTHue = this.mHisenseTvAPI.getCTHue();
        Log.i("Fac_FacFunction_Adapter", "get CT Hue =" + cTHue);
        return cTHue;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setCTHue(int i) {
        Log.i("Fac_FacFunction_Adapter", "set CT Hue =" + i);
        this.mHisenseTvAPI.setCTHue(i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getCTSaturation() {
        int cTSaturation = this.mHisenseTvAPI.getCTSaturation();
        Log.i("Fac_FacFunction_Adapter", "get CT Saturation =" + cTSaturation);
        return cTSaturation;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setCTSaturation(int i) {
        Log.i("Fac_FacFunction_Adapter", "set CT Saturation =" + i);
        this.mHisenseTvAPI.setCTSaturation(i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getCTBrightness() {
        int cTBrightness = this.mHisenseTvAPI.getCTBrightness();
        Log.i("Fac_FacFunction_Adapter", "get CT Brightness =" + cTBrightness);
        return cTBrightness;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setCTBrightness(int i) {
        Log.i("Fac_FacFunction_Adapter", "set CT Brightness =" + i);
        this.mHisenseTvAPI.setCTBrightness(i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setWB20PointLevel(int i) {
        Log.i("Fac_FacFunction_Adapter", "set 20 point level =" + i);
        this.mHisenseTvAPI.setWB20PointLevel(i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setWB20PointRed(int i) {
        Log.i("Fac_FacFunction_Adapter", "set 20 point red =" + i);
        this.mHisenseTvAPI.setWB20PointRed(i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setWB20PointGreen(int i) {
        Log.i("Fac_FacFunction_Adapter", "set 20 point green =" + i);
        this.mHisenseTvAPI.setWB20PointGreen(i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setWB20PointBlue(int i) {
        Log.i("Fac_FacFunction_Adapter", "set 20 point blue =" + i);
        this.mHisenseTvAPI.setWB20PointBlue(i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getWB20PointLevel() {
        int wB20PointLevel = this.mHisenseTvAPI.getWB20PointLevel();
        Log.i("Fac_FacFunction_Adapter", "get 20 point level =" + wB20PointLevel);
        return wB20PointLevel;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getWB20PointRed() {
        int wB20PointRed = this.mHisenseTvAPI.getWB20PointRed();
        Log.i("Fac_FacFunction_Adapter", "get 20 point red =" + wB20PointRed);
        return wB20PointRed;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getWB20PointGreen() {
        int wB20PointGreen = this.mHisenseTvAPI.getWB20PointGreen();
        Log.i("Fac_FacFunction_Adapter", "get 20 point green =" + wB20PointGreen);
        return wB20PointGreen;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getWB20PointBlue() {
        int wB20PointBlue = this.mHisenseTvAPI.getWB20PointBlue();
        Log.i("Fac_FacFunction_Adapter", "get 20 point blue =" + wB20PointBlue);
        return wB20PointBlue;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setEnterVcomVideoParameter() {
        Log.i("Fac_FacFunction_Adapter", "set Enter Vcom Video Parameter");
        this.mHisenseTvAPI.setEnterVcomVideoParameter(1);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setExitVcomVideoParameter() {
        Log.i("Fac_FacFunction_Adapter", "set Exit Vcom Video Parameter");
        this.mHisenseTvAPI.setExitVcomVideoParameter(0);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void closeDBX() {
        Log.i("Fac_FacFunction_Adapter", "close DBX");
        this.mHisenseTvAPI.setDBX(0);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void resetDBX() {
        Log.i("Fac_FacFunction_Adapter", "reset DBX");
        this.mHisenseTvAPI.setDBX(1);
    }

    public int renderPatternOnOSD(Rect rect, Rect rect2, Bitmap bitmap) {
        int iInitRenderPattern = this.mRenderPattern.initRenderPattern();
        if (iInitRenderPattern != 0) {
            System.out.println("fail ret=" + iInitRenderPattern);
            return 0;
        }
        this.mRenderPattern.renderPatternOnOsd(rect, rect2, bitmap);
        return 1;
    }

    public Bitmap decodeBmp(String str) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        Log.i("Fac_FacFunction_Adapter", "width=" + options.outWidth + ", height=" + options.outHeight);
        options.inJustDecodeBounds = false;
        options.inSampleSize = 1;
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        long jCurrentTimeMillis = System.currentTimeMillis();
        Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(str, options);
        Log.i("Fac_FacFunction_Adapter", "decode cost " + (System.currentTimeMillis() - jCurrentTimeMillis) + "ms.");
        return bitmapDecodeFile;
    }

    public void DrawDmp(String str) {
        int i;
        int i2;
        Bitmap bitmapDecodeBmp = decodeBmp(str);
        String str2 = SystemProperties.get("ro.product.board");
        if (FacPropertyDemo.HIFAC_MARKET.equals(FacPropertyDemo.MarketType.EM)) {
            if (str2.equalsIgnoreCase("MT9900")) {
                this.displayW = 3840;
                this.displayH = 2160;
            } else {
                this.displayW = 1920;
                this.displayH = 1080;
            }
        } else if (FacPropertyDemo.HIFAC_MARKET.equals(FacPropertyDemo.MarketType.US)) {
            this.displayW = 3840;
            this.displayH = 2160;
        } else {
            this.displayW = 3840;
            this.displayH = 2160;
        }
        int width = bitmapDecodeBmp.getWidth();
        int height = bitmapDecodeBmp.getHeight();
        Log.i("Fac_FacFunction_Adapter", "bmp width = " + width + "  bmp height = " + height);
        if (width > this.displayW || height > this.displayH) {
            i = this.displayW;
            int i3 = (width * 1000) / i;
            i2 = this.displayH;
            if (i3 > (height * 1000) / i2) {
                i2 = (((i * 1000) / width) * height) / 1000;
            } else {
                i = (((i2 * 1000) / height) * width) / 1000;
            }
        } else {
            i = width;
            i2 = height;
        }
        Rect rect = new Rect(0, 0, width, height);
        int i4 = this.displayW;
        int i5 = this.displayH;
        Rect rect2 = new Rect((i4 - i) / 2, (i5 - i2) / 2, i + ((i4 - i) / 2), i2 + ((i5 - i2) / 2));
        Paint paint = new Paint();
        Canvas canvas = new Canvas();
        Log.i("Fac_FacFunction_Adapter", "displayW = " + this.displayW + "displayH = " + this.displayH);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(this.displayW, this.displayH, Bitmap.Config.ARGB_8888);
        canvas.setBitmap(bitmapCreateBitmap);
        canvas.drawBitmap(bitmapDecodeBmp, rect, rect2, paint);
        System.currentTimeMillis();
        Log.i("Fac_FacFunction_Adapter", "srcRect_left = " + rect.left + "srcRect_top=" + rect.top + "srcRect_right=" + rect.right + "srcRect_bottom=" + rect.bottom);
        Log.i("Fac_FacFunction_Adapter", "dstRect_left = " + rect2.left + "dstRect_top=" + rect2.top + "dstRect_right=" + rect2.right + "dstRect_bottom=" + rect2.bottom);
        renderPatternOnOSD(rect, rect2, bitmapCreateBitmap);
        Log.i("Fac_FacFunction_Adapter", "draw dmp end");
        System.currentTimeMillis();
    }

    public String getChipVersion() {
        String str = SystemProperties.get("ro.product.board");
        Log.i("Fac_FacFunction_Adapter", "getChipVersion = " + str);
        return str;
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean setAgingMode(boolean z) {
        boolean agingMode = this.mHisenseTvAPI.setAgingMode(z);
        Log.i("Fac_FacFunction_Adapter", "Set aging mode to " + z + ", the return result is : " + agingMode);
        return agingMode;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int setLedControl(int i) {
        int micLedControl;
        Log.i("Fac_FacFunction_Adapter", "setLedControl value = " + i);
        int configValue = this.mHisenseTvAPI.setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_LED_CTRL, i);
        Log.i("Fac_FacFunction_Adapter", "setLedControl result = " + configValue);
        if (FacPropertyDemo.HIFAC_MIC_SUPPORT) {
            if (i == 1) {
                micLedControl = setMicLedControl(0);
            } else {
                if (i == 3) {
                    micLedControl = setMicLedControl(2);
                }
                Log.i("Fac_FacFunction_Adapter", "setLedControl MIC led result = " + configValue);
            }
            configValue = micLedControl;
            Log.i("Fac_FacFunction_Adapter", "setLedControl MIC led result = " + configValue);
        }
        return configValue;
    }

    public int setMicLedControl(int i) {
        Log.i("Fac_FacFunction_Adapter", "setMicLedControl value = " + i);
        int micLed = this.mHisenseTvAPI.setMicLed(i);
        Log.i("Fac_FacFunction_Adapter", "setMicLedControl result = " + micLed);
        return micLed;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getUpgradeFlag() {
        int upgradeFlag = this.mHisenseTvAPI.getUpgradeFlag();
        Log.i("Fac_FacFunction_Adapter", "get upgrade flag = " + upgradeFlag);
        return upgradeFlag;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setUpgradeFlag(int i) {
        this.mHisenseTvAPI.setUpgradeFlag(i);
    }

    public int[] getColorTemList() {
        int[] autoGammaColorTemperatureList = this.mHisenseTvAPI.getAutoGammaColorTemperatureList();
        if (autoGammaColorTemperatureList != null) {
            for (int i : autoGammaColorTemperatureList) {
                Log.i("Fac_FacFunction_Adapter", "colorTemlist i = " + i);
            }
        }
        return autoGammaColorTemperatureList;
    }

    public String getGammaParam() {
        return this.mHisenseTvAPI.getAutoGammaList();
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean factoryOk_checkAutoGamma() {
        Log.i("Fac_FacFunction_Adapter", "check auto gamma");
        int autoGammaResult = new File("/tvdatabase/Gamma_His.ini").exists() ? this.mHisenseTvAPI.getAutoGammaResult() : 1;
        Log.i("Fac_FacFunction_Adapter", "check auto gamma value is " + autoGammaResult);
        return autoGammaResult == 1;
    }

    @Override // com.hisense.tvcm.FacFunction
    public String getCICardName() {
        Log.i("Fac_FacFunction_Adapter", "CICardName = ");
        return this.mMtkTvCI.getCamName();
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean getCICardStatus() {
        return this.mMtkTvCI.getSlotActive();
    }

    @Override // com.hisense.tvcm.FacFunction
    public int isRegionX(int i) {
        return ApVsTILmap.getRegionAboutCountry(i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public String getBrand() {
        String tvBrand = this.mHisenseTvAPI.getTvBrand();
        Log.i("Fac_FacFunction_Adapter", "getBrand = " + tvBrand);
        return tvBrand;
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean setBrand(String str) {
        Log.i("Fac_FacFunction_Adapter", "setBrand = " + str);
        return this.mHisenseTvAPI.setTvBrand(str);
    }

    public void setPictureModeEM(FacPropertyDemo.PictureModeEM pictureModeEM) {
        Log.i("Fac_FacFunction_Adapter", "set picture modeEM = " + pictureModeEM.toString());
        mtkPictureModeValue = ApVsTILmap.ApToTil(31, pictureModeEM.ordinal());
        Log.i("Fac_FacFunction_Adapter", "set picture modeEM = " + mtkPictureModeValue);
        this.mHisenseTvAPI.setPictureMode(mtkPictureModeValue);
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean setPlayready30Key(String str) {
        int iFactoryWritePlayReady30Key = this.mKeyWriter.factoryWritePlayReady30Key(str, false);
        Log.i("Fac_FacFunction_Adapter", "setPlayready30Key result = " + iFactoryWritePlayReady30Key);
        setLogToFile("setPlayready30Key " + iFactoryWritePlayReady30Key);
        return iFactoryWritePlayReady30Key == 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean setPlayready40Key(String str) {
        int iFactoryWritePlayReady40Key = this.mKeyWriter.factoryWritePlayReady40Key(str, false);
        Log.i("Fac_FacFunction_Adapter", "setPlayready40Key result = " + iFactoryWritePlayReady40Key);
        setLogToFile("setPlayready40Key " + iFactoryWritePlayReady40Key);
        return iFactoryWritePlayReady40Key == 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean getAudHpfStatus() {
        Log.i("Fac_FacFunction_Adapter", "getAudHpfStatus");
        return this.mHisenseTvAPI.getAudHpfStatus();
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean setAudHpfStatus(boolean z) {
        Log.i("Fac_FacFunction_Adapter", "setAudHpfStatus");
        return this.mHisenseTvAPI.setAudHpfStatus(z);
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean switchSourceByName(String str) {
        if (mContext == null) {
            return false;
        }
        Intent intent = new Intent();
        intent.setAction("com.mediatek.select.source");
        intent.putExtra("com.mediatek.select.sourcename", str);
        mContext.sendBroadcast(intent);
        return true;
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean setPlayready25Key(String str) {
        int iFactoryWritePlayReady25Key = this.mKeyWriter.factoryWritePlayReady25Key(str, false);
        Log.i("Fac_FacFunction_Adapter", "setPlayready25Key result = " + iFactoryWritePlayReady25Key);
        setLogToFile("setPlayready25Key " + iFactoryWritePlayReady25Key);
        return iFactoryWritePlayReady25Key == 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean setMGKKey(String str) {
        int iFactoryWriteNetflix50Key = this.mKeyWriter.factoryWriteNetflix50Key(str, false);
        Log.i("Fac_FacFunction_Adapter", "setMGKKey result = " + iFactoryWriteNetflix50Key);
        setLogToFile("setMGKKey " + iFactoryWriteNetflix50Key);
        updateFacLogStatus(8);
        return iFactoryWriteNetflix50Key == 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean setAttestationKey(String str) {
        int iFactoryWriteKeyMaster = this.mKeyWriter.factoryWriteKeyMaster(str, false);
        Log.i("Fac_FacFunction_Adapter", "setAttestationKey result = " + iFactoryWriteKeyMaster);
        setLogToFile("setAttestationKey " + iFactoryWriteKeyMaster);
        if (iFactoryWriteKeyMaster == 0) {
            restartWidevineDrmService();
        }
        return iFactoryWriteKeyMaster == 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public String getMGKKey() {
        String str = "NG";
        String keyNameFromFile = this.mKeyWriter.checkNetflixKey() ? this.mKeyWriter.readKeyNameFromFile(HisenseTvFactoryApi.FacKeyID.KEY_NETFLIX50_ID) : "NG";
        if (keyNameFromFile != null && !keyNameFromFile.isEmpty()) {
            str = keyNameFromFile;
        }
        Log.i("Fac_FacFunction_Adapter", "getMGKKey keyName = " + str);
        return str;
    }

    @Override // com.hisense.tvcm.FacFunction
    public String getAttestationKey() {
        String str = "NG";
        String keyNameFromFile = this.mKeyWriter.checkAttestKey() ? this.mKeyWriter.readKeyNameFromFile(HisenseTvFactoryApi.FacKeyID.E_MTK_FAPI_SYSTEM_KEY_KEYMASTER) : "NG";
        if (keyNameFromFile != null && !keyNameFromFile.isEmpty()) {
            str = keyNameFromFile;
        }
        Log.i("Fac_FacFunction_Adapter", "getAttestationKey keyName = " + str);
        return str;
    }

    public int removefactorykeyfile(String str) {
        return this.mKeyWriter.removefactorykeyfile(str);
    }

    public int removeFile(String str) {
        return this.mHisenseTvAPI.removeFile(str);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int writeFile(String str, String str2, boolean z, boolean z2) {
        Log.i("Fac_FacFunction_Adapter", "writeFile filePath = " + str);
        return this.mHisenseTvAPI.writeFile(str, str2, z, z2);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int setProjectid(String str) {
        int projectId = 1;
        if (str == null || str.isEmpty()) {
            Log.i("Fac_FacFunction_Adapter", "Project id is null");
            return 1;
        }
        if (!str.matches("\\d+")) {
            Log.i("Fac_FacFunction_Adapter", "Project id foemat error,its not a number");
            return 1;
        }
        int i = Integer.parseInt(str);
        Log.i("Fac_FacFunction_Adapter", "setProjectid projectId = " + i);
        this.mHisenseTvAPI.getFactoryTconStatus();
        Log.i("Fac_FacFunction_Adapter", "setProjectid tconType = 0");
        if (i >= 0 && i < 128) {
            setLogToFile("setProjectid " + i);
            projectId = this.mHisenseTvAPI.setProjectId(i);
            if (FacPropertyDemo.HIFAC_MARKET_JP) {
                this.mTvsKey.setTvsProjectId(str);
            }
            Log.i("Fac_FacFunction_Adapter", "setProjectId, result = " + projectId);
            setLogToFile("setProjectid result = " + projectId);
        }
        return projectId;
    }

    @Override // com.hisense.tvcm.FacFunction
    public String getProjectId() {
        String strValueOf;
        int projectId = this.mHisenseTvAPI.getProjectId();
        Log.i("Fac_FacFunction_Adapter", "projectId = " + projectId);
        int iLengthNum = lengthNum(projectId);
        if (iLengthNum == 1) {
            strValueOf = "00" + String.valueOf(projectId);
        } else if (iLengthNum == 2) {
            strValueOf = "0" + String.valueOf(projectId);
        } else {
            strValueOf = iLengthNum != 3 ? "" : String.valueOf(projectId);
        }
        Log.i("Fac_FacFunction_Adapter", "projectIdStr = " + strValueOf);
        return strValueOf;
    }

    public byte getProjectIdForSerial() {
        int projectId = this.mHisenseTvAPI.getProjectId();
        Log.i("Fac_FacFunction_Adapter", "getProjectIdForSerial projectId = " + projectId);
        return (byte) (projectId & 255);
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean setDTSSwitch(int i) {
        Log.i("Fac_FacFunction_Adapter", "setDTSSwitch, value = " + i);
        boolean dTSSwitch = this.mHisenseTvAPI.setDTSSwitch(i);
        Log.i("Fac_FacFunction_Adapter", "setDTSSwitch, result = " + dTSSwitch);
        return dTSSwitch;
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean setAudPeqEnableStatus(boolean z) {
        Log.i("Fac_FacFunction_Adapter", "setAudPeqEnableStatus  status" + z);
        return this.mHisenseTvAPI.setAudPeqEnableStatus(z);
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean getAudPeqEnableStatus() {
        Log.i("Fac_FacFunction_Adapter", "getAudPeqEnableStatus");
        return this.mHisenseTvAPI.getAudPeqEnableStatus() == 1;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getHpfFrequency() {
        Log.i("Fac_FacFunction_Adapter", "getHpfFrequency");
        return this.mHisenseTvAPI.getAudioPeq(MtkTvConfigTypeBase.CFG_FAC_AUD_HPF_FREQUENCY);
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean setHpfFrequency(int i) {
        Log.i("Fac_FacFunction_Adapter", "setHpfFrequency  value = " + i);
        return this.mHisenseTvAPI.setAudioPeq(MtkTvConfigTypeBase.CFG_FAC_AUD_HPF_FREQUENCY, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean setSocDrc(int i) {
        Log.i("Fac_FacFunction_Adapter", "setSocDrc  value = " + i);
        return this.mHisenseTvAPI.setSocDrc(i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getSocDrc() {
        Log.i("Fac_FacFunction_Adapter", "getSocDrc");
        return this.mHisenseTvAPI.getSocDrc();
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getAutoVolumeCtrlThreshold() {
        Log.i("Fac_FacFunction_Adapter", "getAutoVolumeCtrlThreshold");
        return this.mHisenseTvAPI.getAVCThreshold();
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean setAutoVolumeCtrlThreshold(int i) {
        Log.i("Fac_FacFunction_Adapter", "setAutoVolumeCtrlThreshold  value = " + i);
        return this.mHisenseTvAPI.setAVCThreshold(i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean setAudSocDrcEnableStatus(boolean z) {
        Log.i("Fac_FacFunction_Adapter", "setAudSocDrcEnableStatus  status" + z);
        return this.mHisenseTvAPI.setSocDrcEnable(z);
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean getAudSocDrcEnableStatus() {
        Log.i("Fac_FacFunction_Adapter", "getAudSocDrcEnableStatus");
        return this.mHisenseTvAPI.getSocDrcEnable();
    }

    @Override // com.hisense.tvcm.FacFunction
    public int setLvdsSscDisableOnce(int i) {
        Log.i("Fac_FacFunction_Adapter", "setLvdsSscDisableOnce is called,value = " + i);
        int lVSDSscStatusByOnce = this.mHisenseTvAPI.setLVSDSscStatusByOnce(i);
        Log.i("Fac_FacFunction_Adapter", "setLvdsSscDisableOnce, result = " + lVSDSscStatusByOnce);
        return lVSDSscStatusByOnce;
    }

    private int setChangeFlage() {
        int iFactoryWriteProjectId;
        HisenseTvFApiEmmc hisenseTvFApiEmmc = new HisenseTvFApiEmmc();
        ArrayList<Byte> arrayListFactoryReadProjectId = hisenseTvFApiEmmc.factoryReadProjectId();
        if (arrayListFactoryReadProjectId != null) {
            Byte b = (byte) 5;
            arrayListFactoryReadProjectId.set(b.byteValue(), (byte) 1);
            iFactoryWriteProjectId = hisenseTvFApiEmmc.factoryWriteProjectId(arrayListFactoryReadProjectId);
        } else {
            iFactoryWriteProjectId = -1;
        }
        Log.i("Fac_FacFunction_Adapter", "setChangeFlage result = " + iFactoryWriteProjectId);
        return iFactoryWriteProjectId;
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean setOttSrc(int i) {
        Log.i("Fac_FacFunction_Adapter", "setOttSrc  type" + i);
        this.mHisenseTvAPI.setConfigValue(MtkTvConfigTypeBase.CFG_MISC_OTT_SRC, i);
        return this.mHisenseTvAPI.setCurrentVirtualSource(i) == 1;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setPiCurveSharpnessMin(int i) {
        Log.i("Fac_FacFunction_Adapter", "setPiCurveSharpnessMin called, param: " + i);
        this.mHisenseTvAPI.setVideoCurveDetailValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_SHP_MIN, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getPiCurveSharpnessMin() {
        int[] sharpnessMin = this.mHisenseTvAPI.getSharpnessMin();
        if (sharpnessMin != null) {
            Log.i("Fac_FacFunction_Adapter", "getPiCurveSharpnessMin called, param:" + sharpnessMin[0]);
            return sharpnessMin[0];
        }
        Log.i("Fac_FacFunction_Adapter", "getPiCurveSharpnessMin called,array is null");
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setPiCurveSharpness20(int i) {
        Log.i("Fac_FacFunction_Adapter", "setPiCurveSharpnessMin called, param: " + i);
        this.mHisenseTvAPI.setVideoCurveDetailValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_SHP_20, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getPiCurveSharpness20() {
        int[] sharpness20 = this.mHisenseTvAPI.getSharpness20();
        if (sharpness20 != null) {
            Log.i("Fac_FacFunction_Adapter", "getPiCurveSharpness20 called, param:" + sharpness20[1]);
            return sharpness20[1];
        }
        Log.i("Fac_FacFunction_Adapter", "getPiCurveSharpness20 called,array is null");
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setPiCurveSharpnessMid(int i) {
        Log.i("Fac_FacFunction_Adapter", "setPiCurveSharpnessMin called, param: " + i);
        this.mHisenseTvAPI.setVideoCurveDetailValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_SHP_MID, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getPiCurveSharpnessMid() {
        int[] sharpnessMid = this.mHisenseTvAPI.getSharpnessMid();
        if (sharpnessMid != null) {
            Log.i("Fac_FacFunction_Adapter", "getPiCurveSharpnessMid called, param:" + sharpnessMid[2]);
            return sharpnessMid[2];
        }
        Log.i("Fac_FacFunction_Adapter", "getPiCurveSharpnessMid called,array is null");
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setPiCurveSharpness80(int i) {
        Log.i("Fac_FacFunction_Adapter", "setPiCurveSharpnessMin called, param: " + i);
        this.mHisenseTvAPI.setVideoCurveDetailValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_SHP_80, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getPiCurveSharpness80() {
        int[] sharpness80 = this.mHisenseTvAPI.getSharpness80();
        if (sharpness80 != null) {
            Log.i("Fac_FacFunction_Adapter", "getPiCurveSharpness80 called, param:" + sharpness80[3]);
            return sharpness80[3];
        }
        Log.i("Fac_FacFunction_Adapter", "getPiCurveSharpness80 called,array is null");
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setPiCurveSharpnessMax(int i) {
        Log.i("Fac_FacFunction_Adapter", "setPiCurveSharpnessMin called, param: " + i);
        this.mHisenseTvAPI.setVideoCurveDetailValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_SHP_MAX, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getPiCurveSharpnessMax() {
        int[] sharpnessMax = this.mHisenseTvAPI.getSharpnessMax();
        if (sharpnessMax != null) {
            Log.i("Fac_FacFunction_Adapter", "getPiCurveSharpnessMax called, param:" + sharpnessMax[4]);
            return sharpnessMax[4];
        }
        Log.i("Fac_FacFunction_Adapter", "getPiCurveSharpnessMax called,array is null");
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void syncFs() {
        this.mKeyWriter.syncFs();
    }

    public void setIPTakesEffectImmediately(String str) {
        Log.i("Fac_FacFunction_Adapter", "setIPTakesEffectImmediately");
        this.mKeyWriter.sendCmd(str);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setLogToFile(String str) {
        Log.i("Fac_FacFunction_Adapter", "setLogToFile log = " + str);
        this.mFactoryLog.add(str);
    }

    @Override // com.hisense.tvcm.FacFunction
    public String readLog() {
        return this.mKeyWriter.readFactoryLog();
    }

    public boolean setGammaEnableForSerial(int i, int i2) {
        Log.i("Fac_FacFunction_Adapter", "setGammaEnableForSerial isEnable = " + i);
        Log.i("Fac_FacFunction_Adapter", "setGammaEnableForSerial index = " + i2);
        setLogToFile("Gamma file enable " + i + " index " + i2);
        updateFacLogStatus(32768);
        return this.mHisenseTvAPI.setAutoGammaEnable(i) == 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int setTestPattern(int i, int i2, int i3, int i4) {
        return this.mHisenseTvAPI.setGammaOperation(MtkTvConfigTypeBase.CFG_FACTORY_VID_GAMMA_ADJUST_PATTERN, new int[]{i, i2, i3, i4}, 4) ? 0 : 1;
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean isHaveFte() {
        return this.mHisenseTvAPI.isHaveFte();
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean isFirstPowerOn() {
        int firstTimePowerOn = this.mHisenseTvAPI.getFirstTimePowerOn(MtkTvConfigTypeBase.CFG_FACTORY_FIRST_POWER_ON);
        Log.i("Fac_FacFunction_Adapter", "isFirstPowerOn factoryMode = " + firstTimePowerOn);
        return 255 == firstTimePowerOn || 1 == firstTimePowerOn;
    }

    @Override // com.hisense.tvcm.FacFunction
    public String getWifiMacAddress() {
        WifiInfo connectionInfo = ((WifiManager) mContext.getSystemService("wifi")).getConnectionInfo();
        Log.d("Fac_FacFunction_Adapter", "wifiMAC" + connectionInfo);
        if (connectionInfo == null || connectionInfo.getMacAddress().equals("00:00:00:00:00:00")) {
            return "";
        }
        return "" + connectionInfo.getMacAddress();
    }

    @Override // com.hisense.tvcm.FacFunction
    public void sendCmd(String str) {
        Log.i("Fac_FacFunction_Adapter", "sendCmd");
        this.mKeyWriter.sendCmd(str);
    }

    private int lengthNum(int i) {
        int i2 = 0;
        while (i >= 1) {
            i /= 10;
            i2++;
        }
        return i2;
    }

    public int resetWhiteBalance() {
        return this.mHisenseTvAPI.resetWhteBalance();
    }

    public boolean setRemoteStatus(int i) {
        Log.i("Fac_FacFunction_Adapter", "setRemoteStatus = " + i);
        if (i == 0) {
            SystemProperties.set("sys.vt.factory.serialport.ignoreallkey", "1");
        } else {
            if (i != 1) {
                return false;
            }
            SystemProperties.set("sys.vt.factory.serialport.ignoreallkey", "0");
        }
        return true;
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean isFrcSupport() {
        return this.mIsFrcSupport;
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean isTconSupport() {
        return this.mIsTconSupport;
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean isBluSupport() {
        return this.mIsBluSupport;
    }

    private void updateTvSourceInfo(int i) {
        Log.i("Fac_FacFunction_Adapter", "updateTvSourceInfo id = " + i);
        if (!isHaveTuner()) {
            FacPropertyDemo.SourceEnum.TV.setEnabled(false);
            FacPropertyDemo.SourceEnum.TV.setShown(false);
        } else {
            FacPropertyDemo.SourceEnum.TV.setEnabled(true);
            FacPropertyDemo.SourceEnum.TV.setShown(true);
        }
        if (i >= 0 && i <= FacPropertyDemo.SourceEnum.TV.getTilValue()) {
            FacPropertyDemo.SourceEnum.TV.setTilValue(i);
        }
        FacPropertyDemo.SourceEnum.ATV.setEnabled(false);
        FacPropertyDemo.SourceEnum.ATV.setShown(false);
        FacPropertyDemo.SourceEnum.DTV.setEnabled(false);
        FacPropertyDemo.SourceEnum.DTV.setShown(false);
    }

    private void updateAvSourceInfo(int i) {
        Log.i("Fac_FacFunction_Adapter", "updateAvSourceInfo id = " + i);
        FacPropertyDemo.SourceEnum.AV.setEnabled(true);
        FacPropertyDemo.SourceEnum.AV.setShown(true);
        FacPropertyDemo.SourceEnum.AV.setTilValue(i);
        FacPropertyDemo.SourceEnum.COMPONENT.setEnabled(false);
        FacPropertyDemo.SourceEnum.COMPONENT.setShown(false);
    }

    private void updateHDMIShowStatus(int i, int i2) {
        Log.i("Fac_FacFunction_Adapter", "updateHDMIShowStatus hdmiPort = " + i);
        Log.i("Fac_FacFunction_Adapter", "updateHDMIShowStatus sourceId = " + i2);
        if (i == 1) {
            FacPropertyDemo.SourceEnum.HDMI1.setTilValue(i2);
            FacPropertyDemo.SourceEnum.HDMI1.setEnabled(true);
            FacPropertyDemo.SourceEnum.HDMI1.setShown(true);
        }
        if (i == 2) {
            FacPropertyDemo.SourceEnum.HDMI2.setTilValue(i2);
            FacPropertyDemo.SourceEnum.HDMI2.setEnabled(true);
            FacPropertyDemo.SourceEnum.HDMI2.setShown(true);
        }
        if (i == 3) {
            FacPropertyDemo.SourceEnum.HDMI3.setTilValue(i2);
            FacPropertyDemo.SourceEnum.HDMI3.setEnabled(true);
            FacPropertyDemo.SourceEnum.HDMI3.setShown(true);
        }
        if (i == 4) {
            FacPropertyDemo.SourceEnum.HDMI4.setTilValue(i2);
            FacPropertyDemo.SourceEnum.HDMI4.setEnabled(true);
            FacPropertyDemo.SourceEnum.HDMI4.setShown(true);
        }
    }

    @Override // com.hisense.tvcm.FacFunction
    public String getCurrentSourceName() {
        return MtkTvInputSource.getInstance().getCurrentInputSourceName();
    }

    public boolean setDemuraSwitchStauts(int i) {
        Log.i("Fac_FacFunction_Adapter", "setDemuraSwitchStauts  status = " + i);
        int demuraSwitchStatus = this.mHisenseTvAPI.setDemuraSwitchStatus(i);
        Log.i("Fac_FacFunction_Adapter", "setDemuraSwitchStauts result = " + demuraSwitchStatus);
        return demuraSwitchStatus == 0;
    }

    public int isVcomSupport() {
        int iIsSupportVcom = this.mHisenseTvAPI.isSupportVcom();
        Log.i("Fac_FacFunction_Adapter", "isVcomSupport  = " + iIsSupportVcom);
        return iIsSupportVcom;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getMicSwitchStatus() {
        int micSwitchStatus = this.mHisenseTvAPI.getMicSwitchStatus();
        Log.i("Fac_FacFunction_Adapter", "getMicSwitchStatus = " + micSwitchStatus);
        return micSwitchStatus;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setMicTestResult(int i) {
        Log.i("Fac_FacFunction_Adapter", "setMicTestResult = " + this.mHisenseTvAPI.setMICTestValue(i));
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getMicTestResult() {
        int mICTestValue = this.mHisenseTvAPI.getMICTestValue();
        Log.i("Fac_FacFunction_Adapter", "getMicTestResult  = " + mICTestValue);
        return mICTestValue;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getOledTpcValue() {
        int oLEDTPCEnable = HisVideoApi.getOLEDTPCEnable();
        Log.i("Fac_FacFunction_Adapter", "tpcValue  = " + oLEDTPCEnable);
        return oLEDTPCEnable;
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean setOledTpcValue(int i) {
        Log.i("Fac_FacFunction_Adapter", "setOledTpcValue  value = " + i);
        int oLEDTPCEnable = HisVideoApi.setOLEDTPCEnable(i);
        Log.i("Fac_FacFunction_Adapter", "setOledTpcValue result = " + oLEDTPCEnable);
        return oLEDTPCEnable == 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getOledPixelShiftShapeValue() {
        int oledPixelShiftShapeValue = this.mHisenseTvAPI.getOledPixelShiftShapeValue();
        Log.i("Fac_FacFunction_Adapter", "shiftShape  = " + oledPixelShiftShapeValue);
        return oledPixelShiftShapeValue;
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean setOledPixelShiftShapeValue(int i) {
        Log.i("Fac_FacFunction_Adapter", "setShiftShapeValue  value = " + i);
        boolean oledPixelShiftShapeValue = this.mHisenseTvAPI.setOledPixelShiftShapeValue(i);
        Log.i("Fac_FacFunction_Adapter", "setShiftShapeValue result = " + oledPixelShiftShapeValue);
        return oledPixelShiftShapeValue;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getOledPixelShiftTimerValue() {
        int oledPixelShiftTimerValue = this.mHisenseTvAPI.getOledPixelShiftTimerValue();
        Log.i("Fac_FacFunction_Adapter", "shiftTimer  = " + oledPixelShiftTimerValue);
        return oledPixelShiftTimerValue;
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean setOledPixelShiftTimerValue(int i) {
        Log.i("Fac_FacFunction_Adapter", "setdPixelShiftTimerValue    value = " + i);
        boolean oledPixelShiftTimerValue = this.mHisenseTvAPI.setOledPixelShiftTimerValue(i);
        Log.i("Fac_FacFunction_Adapter", "setPixelShiftTimerValue result = " + oledPixelShiftTimerValue);
        return oledPixelShiftTimerValue;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getOledPixelShiftTypeValue() {
        int oledPixelShiftTypeValue = this.mHisenseTvAPI.getOledPixelShiftTypeValue();
        Log.i("Fac_FacFunction_Adapter", "shiftType  = " + oledPixelShiftTypeValue);
        return oledPixelShiftTypeValue;
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean setOledPixelShiftTypeValue(int i) {
        Log.i("Fac_FacFunction_Adapter", "setShiftTypeValue  value = " + i);
        boolean oledPixelShiftTypeValue = this.mHisenseTvAPI.setOledPixelShiftTypeValue(i);
        Log.i("Fac_FacFunction_Adapter", "setShiftTypeValue result = " + oledPixelShiftTypeValue);
        return oledPixelShiftTypeValue;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getOledLeaValue() {
        int oLEDLEAEnable = HisVideoApi.getOLEDLEAEnable();
        Log.i("Fac_FacFunction_Adapter", "leaValue  = " + oLEDLEAEnable);
        return oLEDLEAEnable;
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean setOledLeaValue(int i) {
        Log.i("Fac_FacFunction_Adapter", "setOledLeaValue  value = " + i);
        int oLEDLEAEnable = HisVideoApi.setOLEDLEAEnable(i);
        Log.i("Fac_FacFunction_Adapter", "setOledLeaValue result = " + oLEDLEAEnable);
        return oLEDLEAEnable == 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getOledCpcValue() {
        int oledCpcValue = this.mHisenseTvAPI.getOledCpcValue();
        Log.i("Fac_FacFunction_Adapter", "cpcValue  = " + oledCpcValue);
        return oledCpcValue;
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean setOledCpcValue(int i) {
        Log.i("Fac_FacFunction_Adapter", "setOledCpcValue  value = " + i);
        boolean oledCpcValue = this.mHisenseTvAPI.setOledCpcValue(i);
        Log.i("Fac_FacFunction_Adapter", "setOledCpcValue result = " + oledCpcValue);
        return oledCpcValue;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getOledLbc2Value() {
        int oledLbc2Value = this.mHisenseTvAPI.getOledLbc2Value();
        Log.i("Fac_FacFunction_Adapter", "lbc2Value  = " + oledLbc2Value);
        return oledLbc2Value;
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean setOledLbc2Value(int i) {
        Log.i("Fac_FacFunction_Adapter", "setOledLbc2Value  value = " + i);
        boolean oledLbc2Value = this.mHisenseTvAPI.setOledLbc2Value(i);
        Log.i("Fac_FacFunction_Adapter", "setOledLbc2Value result = " + oledLbc2Value);
        return oledLbc2Value;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getOledOrbitValue() {
        int pixelShiftEnable = HisVideoApi.getPixelShiftEnable();
        Log.i("Fac_FacFunction_Adapter", "orbitValue  = " + pixelShiftEnable);
        return pixelShiftEnable;
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean setOledOrbitValue(int i) {
        Log.i("Fac_FacFunction_Adapter", "setOledOrbitValue  value = " + i);
        int pixelShiftEnable = HisVideoApi.setPixelShiftEnable(i);
        Log.i("Fac_FacFunction_Adapter", "setOledOrbitValue result = " + pixelShiftEnable);
        return pixelShiftEnable == 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getOledLbaValue() {
        int oLEDLocalFrozenChkEnable = HisVideoApi.getOLEDLocalFrozenChkEnable();
        Log.i("Fac_FacFunction_Adapter", "lbaValue  = " + oLEDLocalFrozenChkEnable);
        return oLEDLocalFrozenChkEnable;
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean setOledLbaValue(int i) {
        Log.i("Fac_FacFunction_Adapter", "setOledLbaValue  value = " + i);
        int oLEDLocalFrozenChkEnable = HisVideoApi.setOLEDLocalFrozenChkEnable(i);
        Log.i("Fac_FacFunction_Adapter", "setOledLbaValue result = " + oLEDLocalFrozenChkEnable);
        return oLEDLocalFrozenChkEnable == 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getOledHdrValue() {
        int oLEDHDREnable = HisVideoApi.getOLEDHDREnable();
        Log.i("Fac_FacFunction_Adapter", "hdrValue  = " + oLEDHDREnable);
        return oLEDHDREnable;
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean setOledHdrValue(int i) {
        Log.i("Fac_FacFunction_Adapter", "setOledHdrValue  value = " + i);
        int oLEDHDREnable = HisVideoApi.setOLEDHDREnable(i);
        Log.i("Fac_FacFunction_Adapter", "setOledHdrValue result = " + oLEDHDREnable);
        return oLEDHDREnable == 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getJBExecuteCount() {
        int jBExecuteCount = this.mHisenseTvAPI.getJBExecuteCount();
        Log.i("Fac_FacFunction_Adapter", "jbExecCntValue  = " + jBExecuteCount);
        return jBExecuteCount;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getOffrsExecuteCount() {
        int offsExecuteCount = this.mHisenseTvAPI.getOffsExecuteCount();
        Log.i("Fac_FacFunction_Adapter", "offrsExecCntValue = " + offsExecuteCount);
        return offsExecuteCount;
    }

    @Override // com.hisense.tvcm.FacFunction
    public String getJBExecuteTime() {
        long j;
        long j2;
        long jCurrentTimeMillis = System.currentTimeMillis() / 1000;
        byte[] jBExecuteTime = this.mHisenseTvAPI.getJBExecuteTime();
        if (jBExecuteTime == null) {
            return "Error";
        }
        long j3 = 0;
        long j4 = 0;
        for (int i = 0; i < 8; i++) {
            j4 += (long) ((jBExecuteTime[i] & 255) << (i * 8));
        }
        Log.i("Fac_FacFunction_Adapter", "Before correction, jbExecTime = " + j4);
        if (j4 == Math.pow(2.0d, 64.0d) - 1.0d) {
            j = 0;
            j2 = 0;
        } else {
            long j5 = jCurrentTimeMillis - j4;
            Log.i("Fac_FacFunction_Adapter", "After correction, i.e., jbExecTime = currentTime - jbExecTime, jbExecTime = " + j5);
            long j6 = j5 / 60;
            j = j6 % 60;
            long j7 = j6 / 60;
            j2 = j7 % 24;
            j3 = j7 / 24;
        }
        String str = "day:" + j3 + "  hour:" + j2 + "  min:" + j;
        Log.i("Fac_FacFunction_Adapter", "jbExecTimeString  = " + str);
        return str;
    }

    @Override // com.hisense.tvcm.FacFunction
    public String getOffrsExecuteTime() {
        long j;
        long j2;
        long jCurrentTimeMillis = System.currentTimeMillis() / 1000;
        byte[] offsExecuteTime = this.mHisenseTvAPI.getOffsExecuteTime();
        if (offsExecuteTime == null) {
            return "Error";
        }
        long j3 = 0;
        long j4 = 0;
        for (int i = 0; i < 8; i++) {
            j4 += (long) ((offsExecuteTime[i] & 255) << (i * 8));
        }
        Log.i("Fac_FacFunction_Adapter", "Before correction, offrsExecTime = " + j4);
        if (j4 == Math.pow(2.0d, 64.0d) - 1.0d) {
            j = 0;
            j2 = 0;
        } else {
            long j5 = jCurrentTimeMillis - j4;
            Log.i("Fac_FacFunction_Adapter", "After correction, i.e., offrsExecTime = currentTime - offrsExecTime, offrsExecTime = " + j5);
            long j6 = j5 / 60;
            j = j6 % 60;
            long j7 = j6 / 60;
            j2 = j7 % 24;
            j3 = j7 / 24;
        }
        String str = "day:" + j3 + "  hour:" + j2 + "  min:" + j;
        Log.i("Fac_FacFunction_Adapter", "offrsExecTimeString  = " + str);
        return str;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getErrDetCount() {
        int errDetCount = this.mHisenseTvAPI.getErrDetCount();
        Log.i("Fac_FacFunction_Adapter", "errDetValue  = " + errDetCount);
        if (errDetCount == 255) {
            return 0;
        }
        return errDetCount;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void readDriverInfo() {
        String str;
        Log.i("Fac_FacFunction_Adapter", "readDriverInfo");
        int fRCVersion = HisVideoApi.getFRCVersion();
        Log.d("Fac_FacFunction_Adapter", "mFrcVersionVal = " + fRCVersion);
        if (fRCVersion == 0) {
            str = null;
        } else {
            str = fRCVersion + "";
        }
        this.mFrcVersion = str;
        this.mIsFrcSupport = isNeedToShow(str);
        String tconVersion = this.mHisenseTvAPI.getTconVersion();
        this.mTconVersion = tconVersion;
        this.mIsTconSupport = isNeedToShow(tconVersion);
        String bluVersion = this.mHisenseTvAPI.getBluVersion();
        this.mBluVersion = bluVersion;
        this.mIsBluSupport = isNeedToShow(bluVersion);
    }

    private boolean isNeedToShow(String str) {
        Log.d("Fac_FacFunction_Adapter", "isNeedToShow, version = " + str + "\n");
        return (str == null || str.isEmpty() || str.equals("BLANK")) ? false : true;
    }

    @Override // com.hisense.tvcm.FacFunction
    public String getProjectIdFileNameInUsbDisk() {
        String str = FacPropertyDemo.HIFAC_PROJECT_ID_FILENAME;
        Log.i("Fac_FacFunction_Adapter", "ProjectIdFileNameInUsbDisk = " + str);
        return str;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setMicDb(int i) {
        Log.i("Fac_FacFunction_Adapter", "setMicDb value = " + i);
        Log.i("Fac_FacFunction_Adapter", "setMicDb result = " + this.mHisenseTvAPI.setMicDbDiffSelect(i));
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getMicDb() {
        int micDbDiffSelect = this.mHisenseTvAPI.getMicDbDiffSelect();
        Log.i("Fac_FacFunction_Adapter", "getMicDb result = " + micDbDiffSelect);
        if (255 == micDbDiffSelect) {
            return 0;
        }
        return micDbDiffSelect;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setMicRecordTime(int i) {
        Log.i("Fac_FacFunction_Adapter", "setMicRecordTime value = " + i);
        if (this.mSharedPreferences == null) {
            initSharedPreference();
        }
        this.mSharedPreferences.edit().putInt("micrecordtime", i).commit();
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getMicRecordTime() {
        if (this.mSharedPreferences == null) {
            initSharedPreference();
        }
        return this.mSharedPreferences.getInt("micrecordtime", 0);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setOSDCFlag(boolean z) {
        Log.i("Fac_FacFunction_Adapter", "setOSDCFlag flag = " + z);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setZDMicResult(int i) {
        Log.i("Fac_FacFunction_Adapter", "setZDMicResult value = " + i);
        this.mHisenseTvAPI.setZDMICValue(i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getZDMicResult() {
        int zDMICValue = this.mHisenseTvAPI.getZDMICValue();
        Log.i("Fac_FacFunction_Adapter", "getZDMicResult value = " + zDMICValue);
        return zDMICValue;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getLogoValueForSA(String str) {
        return this.mAdapterHelper.getSALogoValueForMac(str);
    }

    @Override // com.hisense.tvcm.FacFunction
    public String getLogoFullNameForSA() {
        int logoFromEmmc = getLogoFromEmmc();
        Log.i("Fac_FacFunction_Adapter", "getLogoFullNameForSA value is " + logoFromEmmc);
        return this.mAdapterHelper.getSALogoFullName(logoFromEmmc);
    }

    @Override // com.hisense.tvcm.FacFunction
    public String getLogoFullName() {
        int logoFromEmmc = getLogoFromEmmc();
        Log.i("Fac_FacFunction_Adapter", "getLogoFullName til value is " + logoFromEmmc);
        return ApVsTILmap.getLogoFullNameByTilValue(logoFromEmmc);
    }

    @Override // com.hisense.tvcm.FacFunction
    public String getLogoFullNameMon() {
        int logoFromEmmc = getLogoFromEmmc();
        Log.i("Fac_FacFunction_Adapter", "getLogoFullNameForMon value is " + logoFromEmmc);
        String logoFullNameByTilValue = ApVsTILmap.getLogoFullNameByTilValue(logoFromEmmc);
        Log.i("Fac_FacFunction_Adapter", "getLogoFullNameForMon logo name is " + logoFullNameByTilValue);
        return logoFullNameByTilValue;
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean setClientType(String str) {
        Log.i("Fac_FacFunction_Adapter", "setClientType type is " + str);
        boolean customerTypeValue = this.mHisenseTvAPI.setCustomerTypeValue(str);
        Log.i("Fac_FacFunction_Adapter", "setClientType ret is " + customerTypeValue);
        return customerTypeValue;
    }

    @Override // com.hisense.tvcm.FacFunction
    public String getClientType() {
        String customerTypeValue = this.mHisenseTvAPI.getCustomerTypeValue();
        Log.i("Fac_FacFunction_Adapter", "getClientType ret is " + customerTypeValue);
        return customerTypeValue;
    }

    private int getLogoFromEmmc() {
        return this.mHisenseTvAPI.getBootUpLogo();
    }

    @Override // com.hisense.tvcm.FacFunction
    public void changeChannelForId(int i, int i2) {
        List<ChannelInfoBase> list;
        Log.i("Fac_FacFunction_Adapter", "changeChannelForId majorNo is " + i);
        Log.i("Fac_FacFunction_Adapter", "changeChannelForId minorNo is " + i2);
        if (getTunerMode() == 0) {
            list = this.list_air;
        } else {
            list = this.list_cable;
        }
        if (list != null && i2 != 0) {
            for (ChannelInfoBase channelInfoBase : list) {
                String channelNum = channelInfoBase.getChannelNum();
                Log.i("Fac_FacFunction_Adapter", "tvChannelInfo.channelNum [i] = " + channelNum);
                String[] strArrSplit = channelNum.split("-|\\.");
                if (strArrSplit.length != 1) {
                    String str = strArrSplit[0];
                    String str2 = strArrSplit[1];
                    Log.i("Fac_FacFunction_Adapter", "channel mainChnum = " + str + ", minorChnum =" + str2);
                    if (Integer.parseInt(str) == i && Integer.parseInt(str2) == i2) {
                        Log.i("Fac_FacFunction_Adapter", "find target channelNumber = " + channelNum);
                        this.mHisPlayApiImpl.playChannelByChannelKey(0, channelInfoBase.getChannelKey(), 0);
                        this.currentchannel = channelInfoBase;
                        setSourceStatusToAudioPicture(FacPropertyDemo.SourceEnum.TV);
                        return;
                    }
                }
            }
            return;
        }
        Log.i("Fac_FacFunction_Adapter", "Channel list is null !!");
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getFacLogStatus() {
        Log.i("Fac_FacFunction_Adapter", "getFacLogStatus is " + this.mHisenseTvAPI.getFactoryOperatorStatus());
        return this.mHisenseTvAPI.getFactoryOperatorStatus();
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setFacLogStatus(int i) {
        Log.i("Fac_FacFunction_Adapter", "setFacLogStatus value is " + Integer.toBinaryString(i));
        this.mHisenseTvAPI.setFactoryOperatorStatus(i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void generateEepKeyFile() {
        Log.i("Fac_FacFunction_Adapter", "generateEepKeyFile");
        this.mHisenseTvAPI.generateEepKeyFile();
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean cleanEepKeyFile() {
        Log.i("Fac_FacFunction_Adapter", "cleanEepKeyFile");
        boolean zCleanEepKeyFile = this.mHisenseTvAPI.cleanEepKeyFile();
        Log.i("Fac_FacFunction_Adapter", "cleanEepKeyFile result = " + zCleanEepKeyFile);
        return zCleanEepKeyFile;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void updateFacLogStatus(int i) {
        Log.i("Fac_FacFunction_Adapter", "setFacLogStatus value is " + Integer.toBinaryString(i));
        if (i == 0) {
            setFacLogStatus(0);
            return;
        }
        int facLogStatus = getFacLogStatus() | i;
        Log.i("Fac_FacFunction_Adapter", "new setFacLogStatus value is " + Integer.toBinaryString(i));
        setFacLogStatus(facLogStatus);
    }

    private void setSourceStatusToLog(FacPropertyDemo.SourceEnum sourceEnum) {
        Log.i("Fac_FacFunction_Adapter", "setSourceStatusToLog source is " + sourceEnum.toString());
        if (sourceEnum.toString().contains("TV")) {
            updateFacLogStatus(64);
        }
        if (sourceEnum.toString().contains(MtkTvRatingConvert2Goo.RATING_STR_AV)) {
            updateFacLogStatus(128);
        }
        if (sourceEnum.toString().contains("HDMI1")) {
            updateFacLogStatus(256);
        }
        if (sourceEnum.toString().contains("HDMI2")) {
            updateFacLogStatus(512);
        }
        if (sourceEnum.toString().contains("HDMI3")) {
            updateFacLogStatus(1024);
        }
        if (sourceEnum.toString().contains("HDMI4")) {
            updateFacLogStatus(2048);
        }
    }

    private void setSourceStatusToLog(int i) {
        Log.i("Fac_FacFunction_Adapter", "setSourceStatusToLog tilValue is " + i);
        if (i == FacPropertyDemo.SourceEnum.TV.getTilValue()) {
            updateFacLogStatus(64);
        }
        if (i == FacPropertyDemo.SourceEnum.AV.getTilValue()) {
            updateFacLogStatus(128);
        }
        if (i == FacPropertyDemo.SourceEnum.HDMI1.getTilValue()) {
            updateFacLogStatus(256);
        }
        if (i == FacPropertyDemo.SourceEnum.HDMI2.getTilValue()) {
            updateFacLogStatus(512);
        }
        if (i == FacPropertyDemo.SourceEnum.HDMI3.getTilValue()) {
            updateFacLogStatus(1024);
        }
        if (i == FacPropertyDemo.SourceEnum.HDMI4.getTilValue()) {
            updateFacLogStatus(2048);
        }
    }

    private void addTvPresentChannelForNewsan() {
        this.list_air.clear();
        this.list_cable.clear();
        getAirChannelList();
        getCableChannelList();
        changeChannelForId(26, 2);
    }

    private void setSourceStatusToAudioPicture(FacPropertyDemo.SourceEnum sourceEnum) {
        Log.i("Fac_FacFunction_Adapter", "setSourceStatusToAudioPicture source is " + sourceEnum.toString());
        int i = 4;
        if (sourceEnum.toString().contains("TV")) {
            if (FacPropertyDemo.HIFAC_ATSC30_SUPPORT) {
                com.hisense.tvmanager.atsc.ChannelInfoBase channelInfoBase = this.currentchannelInfo;
                Log.i("Fac_FacFunction_Adapter", "currentChannelATSC channel info = " + channelInfoBase);
                if (channelInfoBase != null) {
                    if (channelInfoBase.isAnalogService()) {
                        Log.i("Fac_FacFunction_Adapter", "this channel is ATV ");
                    } else {
                        Log.i("Fac_FacFunction_Adapter", "this channel is DTV ");
                        i = 1;
                    }
                }
            } else {
                ChannelInfoBase channelInfoByChannelKey = this.mHisChannelListApiImpl.getChannelInfoByChannelKey(this.mHisPlayApiImpl.getCurrentChannelKey());
                if (channelInfoByChannelKey != null) {
                    int brdcstType = channelInfoByChannelKey.getBrdcstType();
                    Log.i("Fac_FacFunction_Adapter", "setSourceStatusToAudioPicture enum tvType is " + brdcstType);
                    if (brdcstType != 0 && 1 != brdcstType && 3 != brdcstType && 4 != brdcstType && 2 != brdcstType) {
                        i = 1;
                    }
                }
            }
        } else if (sourceEnum.toString().contains(MtkTvRatingConvert2Goo.RATING_STR_AV)) {
            i = 2;
        } else if (sourceEnum.toString().contains("COMPONENT")) {
            i = 3;
        } else if (!sourceEnum.toString().contains("HDMI1")) {
            if (sourceEnum.toString().contains("HDMI2")) {
                i = 5;
            } else if (sourceEnum.toString().contains("HDMI3")) {
                i = 6;
            } else {
                i = sourceEnum.toString().contains("HDMI4") ? 7 : 0;
            }
        }
        this.mHisenseTvAPI.setCurrentSource(i);
    }

    private void setSourceStatusToAudioPicture(int i) {
        if (FacPropertyDemo.HIFAC_MARKET_SA) {
            Log.i("Fac_FacFunction_Adapter", "setSourceStatusToAudioPicture tilValue is " + i);
            int i2 = 4;
            if (i == FacPropertyDemo.SourceEnum.TV.getTilValue()) {
                if (FacPropertyDemo.HIFAC_ATSC30_SUPPORT) {
                    com.hisense.tvmanager.atsc.ChannelInfoBase channelInfoBase = this.currentchannelInfo;
                    Log.i("Fac_FacFunction_Adapter", "currentChannelATSC channel info = " + channelInfoBase);
                    if (channelInfoBase != null) {
                        if (channelInfoBase.isAnalogService()) {
                            Log.i("Fac_FacFunction_Adapter", "this channel is ATV ");
                        } else {
                            Log.i("Fac_FacFunction_Adapter", "this channel is DTV ");
                            i2 = 1;
                        }
                    }
                } else {
                    ChannelInfoBase channelInfoByChannelKey = this.mHisChannelListApiImpl.getChannelInfoByChannelKey(this.mHisPlayApiImpl.getCurrentChannelKey());
                    if (channelInfoByChannelKey != null) {
                        int brdcstType = channelInfoByChannelKey.getBrdcstType();
                        Log.i("Fac_FacFunction_Adapter", "setSourceStatusToAudioPicture tvType is " + brdcstType);
                        if (brdcstType != 0 && 1 != brdcstType && 3 != brdcstType && 4 != brdcstType && 2 != brdcstType) {
                            i2 = 1;
                        }
                    }
                }
            } else if (i == FacPropertyDemo.SourceEnum.AV.getTilValue()) {
                i2 = 2;
            } else if (i == FacPropertyDemo.SourceEnum.COMPONENT.getTilValue()) {
                i2 = 3;
            } else if (i != FacPropertyDemo.SourceEnum.HDMI1.getTilValue()) {
                if (i == FacPropertyDemo.SourceEnum.HDMI2.getTilValue()) {
                    i2 = 5;
                } else if (i == FacPropertyDemo.SourceEnum.HDMI3.getTilValue()) {
                    i2 = 6;
                } else {
                    i2 = i == FacPropertyDemo.SourceEnum.HDMI4.getTilValue() ? 7 : 0;
                }
            }
            this.mHisenseTvAPI.setCurrentSource(i2);
        }
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean isBrandIsolate() {
        return this.mAdapterHelper.isBrandIsolate();
    }

    @Override // com.hisense.tvcm.FacFunction
    public String[] geBrandIsolateList() {
        return this.mAdapterHelper.geBrandIsolateList();
    }

    @Override // com.hisense.tvcm.FacFunction
    public int set2PWBOffsetRedDelta(int i) {
        Log.d("Fac_FacFunction_Adapter", "set2PWBOffsetRedDelta val:" + i);
        return this.mHisenseTvAPI.set2PWBOffsetRedDelta(i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int get2PWBOffsetRedDelta() {
        int i = this.mHisenseTvAPI.get2PWBOffsetRedDelta();
        Log.d("Fac_FacFunction_Adapter", "get2PWBOffsetRedDelta val = " + i);
        return i;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int set2PWBOffsetGreenDelta(int i) {
        Log.d("Fac_FacFunction_Adapter", "set2PWBOffsetGreenDelta val:" + i);
        return this.mHisenseTvAPI.set2PWBOffsetGreenDelta(i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int get2PWBOffsetGreenDelta() {
        int i = this.mHisenseTvAPI.get2PWBOffsetGreenDelta();
        Log.d("Fac_FacFunction_Adapter", "get2PWBOffsetGreenDelta val = " + i);
        return i;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int set2PWBOffsetBlueDelta(int i) {
        Log.d("Fac_FacFunction_Adapter", "set2PWBOffsetBlueDelta val:" + i);
        return this.mHisenseTvAPI.set2PWBOffsetBlueDelta(i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int get2PWBOffsetBlueDelta() {
        int i = this.mHisenseTvAPI.get2PWBOffsetBlueDelta();
        Log.d("Fac_FacFunction_Adapter", "get2PWBOffsetBlueDelta val = " + i);
        return i;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int set2PWBGainRedDelta(int i) {
        Log.d("Fac_FacFunction_Adapter", "set2PWBGainRedDelta val:" + i);
        return this.mHisenseTvAPI.set2PWBGainRedDelta(i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int get2PWBGainRedDelta() {
        int i = this.mHisenseTvAPI.get2PWBGainRedDelta();
        Log.d("Fac_FacFunction_Adapter", "get2PWBGainRedDelta val = " + i);
        return i;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int set2PWBGainGreenDelta(int i) {
        Log.d("Fac_FacFunction_Adapter", "set2PWBGainGreenDelta val:" + i);
        return this.mHisenseTvAPI.set2PWBGainGreenDelta(i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int get2PWBGainGreenDelta() {
        int i = this.mHisenseTvAPI.get2PWBGainGreenDelta();
        Log.d("Fac_FacFunction_Adapter", "get2PWBGainGreenDelta val = " + i);
        return i;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int set2PWBGainBlueDelta(int i) {
        Log.d("Fac_FacFunction_Adapter", "set2PWBGainBlueDelta val:" + i);
        return this.mHisenseTvAPI.set2PWBGainBlueDelta(i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int get2PWBGainBlueDelta() {
        int i = this.mHisenseTvAPI.get2PWBGainBlueDelta();
        Log.d("Fac_FacFunction_Adapter", "get2PWBGainBlueDelta val = " + i);
        return i;
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean setChannelInitFileFromUdisk(String str) {
        Log.d("Fac_FacFunction_Adapter", "setChannelInitFileFromUdisk");
        int iImportChannelFromUSB = this.mHisChannelListApiImpl.importChannelFromUSB(str, false);
        Log.d("Fac_FacFunction_Adapter", "result = " + iImportChannelFromUSB);
        this.mHisenseTvAPI.setConfigValue(MtkTvConfigTypeBase.CFG_MISC_CHANNEL_STORE, 0);
        if (FacPropertyDemo.HIFAC_ATSC30_SUPPORT) {
            getChannelListByTunerMode(0);
            getChannelListByTunerMode(1);
        } else {
            getAirChannelList();
            getCableChannelList();
        }
        switchSource(FacPropertyDemo.SourceEnum.TV);
        return iImportChannelFromUSB == 0;
    }

    public void startAutoGammaTunning() {
        Log.d("Fac_FacFunction_Adapter", "startAutoGammaTunning start");
        if (!FacPropertyDemo.SourceEnum.HDMI1.equals(getCurrentSource())) {
            Log.d("Fac_FacFunction_Adapter", "startAutoGammaTunning switch source to HDMI and set picture mode to stand");
            switchSource(FacPropertyDemo.SourceEnum.HDMI1);
        }
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (100 != getBacklight()) {
            Log.d("Fac_FacFunction_Adapter", "startAutoGammaTunning set backlight 100");
            setBacklight(100);
        }
        this.mHisenseTvAPI.setAutoGammaStartTunning(1);
        Log.d("Fac_FacFunction_Adapter", "startAutoGammaTunning end");
    }

    public void endAutoGammaTunning() {
        Log.d("Fac_FacFunction_Adapter", "endAutoGammaTunning start");
        this.mHisenseTvAPI.setAutoGammaStartTunning(0);
        Log.d("Fac_FacFunction_Adapter", "endAutoGammaTunning end");
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getSoundVolCurveMin() {
        int soundVolCurveMin = this.mHisenseTvAPI.getSoundVolCurveMin();
        Log.d("Fac_FacFunction_Adapter", "getSoundVolCurveMin val = " + soundVolCurveMin);
        return soundVolCurveMin;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getSoundVolCurveMax() {
        int soundVolCurveMax = this.mHisenseTvAPI.getSoundVolCurveMax();
        Log.d("Fac_FacFunction_Adapter", "getSoundVolCurveMax val = " + soundVolCurveMax);
        return soundVolCurveMax;
    }

    private void updateCurrentSupportSourceFromMTK() {
        List<MtkTvInputSourceBase.InputSourceRecord> currentSupportSource = this.mHisenseTvAPI.getCurrentSupportSource();
        this.mSourceList = currentSupportSource;
        if (currentSupportSource == null || currentSupportSource.size() < 0) {
            Log.i("Fac_FacFunction_Adapter", "updateCurrentSupportSourceFromMTK error");
            return;
        }
        int size = this.mSourceList.size();
        for (int i = 0; i < size; i++) {
            Log.i("Fac_FacFunction_Adapter", "updateCurrentSupportSourceFromMTK i = " + i);
            updateSourceTilValue(this.mSourceList.get(i));
        }
    }

    private void updateSourceTilValue(MtkTvInputSourceBase.InputSourceRecord inputSourceRecord) {
        if (inputSourceRecord == null) {
            return;
        }
        int internalIdx = inputSourceRecord.getInternalIdx();
        String strName = inputSourceRecord.getInputType().name();
        int id = inputSourceRecord.getId();
        Log.i("Fac_FacFunction_Adapter", "updateSourceTilValue record id = " + internalIdx);
        Log.i("Fac_FacFunction_Adapter", "updateSourceTilValue record currentSourceName = " + strName);
        Log.i("Fac_FacFunction_Adapter", "updateSourceTilValue record tilValue = " + id);
        if (strName.contains("HDMI")) {
            int i = 1;
            if (internalIdx >= 0 && internalIdx <= 3) {
                i = 1 + internalIdx;
            }
            updateHDMIShowStatus(i, id);
            return;
        }
        if (strName.contains("COMPOSITE")) {
            if (internalIdx != 0) {
                return;
            }
            updateAvSourceInfo(id);
        } else if (strName.contains("TV") && internalIdx == 0) {
            updateTvSourceInfo(id);
        }
    }

    @Override // com.hisense.tvcm.FacFunction
    public String get5GChannel() {
        int[] iArr = this.mHisenseTvAPI.get5GChannel();
        if (iArr == null) {
            Log.i("Fac_FacFunction_Adapter", "get5GChannel chanle is null");
            return "NG";
        }
        if (iArr[0] == 0) {
            return "NG";
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i : iArr) {
            stringBuffer.append(i + " ");
        }
        return stringBuffer.toString();
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean setEarcStatus(int i) {
        HdmiControlManager hdmiControlManager;
        HdmiTvClient tvClient;
        Log.i("Fac_FacFunction_Adapter", "setEarcStatus" + i);
        int eARCStatus = this.mHisenseTvAPI.setEARCStatus(i);
        Log.i("Fac_FacFunction_Adapter", "setEarcStatusResult" + eARCStatus);
        if (FacPropertyDemo.HIFAC_MARKET_JP && (hdmiControlManager = (HdmiControlManager) mContext.getSystemService(HdmiControlManager.class)) != null && (tvClient = hdmiControlManager.getTvClient()) != null && i == 0) {
            tvClient.setArcMode(true);
        }
        return eARCStatus == 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getEarcStatus() {
        int eARCStatus = this.mHisenseTvAPI.getEARCStatus();
        Log.i("Fac_FacFunction_Adapter", "getEARCStatus" + eARCStatus);
        return eARCStatus;
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean setLocalDimmingCheck(int i) {
        int localDimmingCheck = this.mHisenseTvAPI.setLocalDimmingCheck(i);
        Log.i("Fac_FacFunction_Adapter", "setLocalDimmingCheck" + localDimmingCheck);
        return localDimmingCheck == 0;
    }

    public int getCurrentAttestKeyCount() {
        File file = new File("perm/orig_attest/");
        int i = 0;
        if (file.exists() && file.isDirectory()) {
            File[] fileArrListFiles = file.listFiles();
            if (fileArrListFiles == null) {
                return 0;
            }
            int length = fileArrListFiles.length;
            int i2 = 0;
            while (i < length) {
                File file2 = fileArrListFiles[i];
                if (!file2.isDirectory()) {
                    String name = file2.getName();
                    Log.i("Fac_FacFunction_Adapter", "getCurrentAttestKeyCount filename = " + name);
                    if (name != null && !name.isEmpty() && file2.getName().contains("attest") && file2.getName().contains(".bin")) {
                        i2++;
                    }
                }
                i++;
            }
            i = i2;
        }
        Log.i("Fac_FacFunction_Adapter", "getCurrentAttestKeyCount count = " + i);
        return i;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getLightSensorCurve(int i, int i2) {
        Log.i("Fac_FacFunction_Adapter", "mode" + i + "index" + i2);
        int lightSensorCurve = this.mHisenseTvAPI.getLightSensorCurve(i, i2);
        StringBuilder sb = new StringBuilder();
        sb.append("getLightSensorCurve = ");
        sb.append(lightSensorCurve);
        Log.i("Fac_FacFunction_Adapter", sb.toString());
        return lightSensorCurve;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int setLightSensorCurve(int i, int i2, int i3) {
        Log.i("Fac_FacFunction_Adapter", "mode" + i + "index" + i2 + "value" + i3);
        int lightSensorCurve = this.mHisenseTvAPI.setLightSensorCurve(i, i2, i3);
        StringBuilder sb = new StringBuilder();
        sb.append("setresule = ");
        sb.append(lightSensorCurve);
        Log.i("Fac_FacFunction_Adapter", sb.toString());
        return lightSensorCurve;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int setDolbyIQUserBinEnable(int i) {
        Log.i("Fac_FacFunction_Adapter", "value = " + i);
        int dolbyIQUserBinEnable = this.mHisenseTvAPI.setDolbyIQUserBinEnable(i);
        Log.i("Fac_FacFunction_Adapter", "setDolbyIQUserBinEnable = " + dolbyIQUserBinEnable);
        return dolbyIQUserBinEnable;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getDolbyIQUserBinEnable() {
        int dolbyIQUserBinEnable = this.mHisenseTvAPI.getDolbyIQUserBinEnable();
        Log.i("Fac_FacFunction_Adapter", "getDolbyIQUserBinEnable = " + dolbyIQUserBinEnable);
        return dolbyIQUserBinEnable;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int setDolbyIQUserBinUpdate(String str) {
        Log.i("Fac_FacFunction_Adapter", "DolbyIQUpdatePath = " + str);
        int dolbyIQUserBinUpdate = this.mHisenseTvAPI.setDolbyIQUserBinUpdate(str);
        Log.i("Fac_FacFunction_Adapter", "DolbyIQUpdateResult = " + dolbyIQUserBinUpdate);
        return dolbyIQUserBinUpdate;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getVcomVoltage2(int i) {
        int vcomValue;
        Log.i("Fac_FacFunction_Adapter", "getVcomVoltage2 type = " + i);
        if (i == 1) {
            vcomValue = this.mHisenseTvAPI.getUHDVcomValue();
        } else if (i == 2) {
            vcomValue = this.mHisenseTvAPI.getFHDVcomValue();
        } else {
            vcomValue = this.mHisenseTvAPI.getVcomValue(MtkTvConfigTypeBase.CFG_FACTORY_VCOM_VALUE);
        }
        Log.i("Fac_FacFunction_Adapter", "getVcomVoltage2 value = " + vcomValue);
        return vcomValue;
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean setVcomVoltage2(int i, int i2) {
        Log.i("Fac_FacFunction_Adapter", "setVcomVoltage2 = " + i);
        Log.i("Fac_FacFunction_Adapter", "setVcomVoltage2 = " + i2);
        return this.mHisenseTvAPI.setVcomArrayValue(new int[]{i2, i}, 2);
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean saveVcomVoltage2(int i, int i2) {
        Log.i("Fac_FacFunction_Adapter", "saveVcomVoltage2 = " + i);
        Log.i("Fac_FacFunction_Adapter", "saveVcomVoltage2 = " + i2);
        return this.mHisenseTvAPI.updateVcomArrayValue(new int[]{i2, i}, 2);
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean setVcomPanelType(int i) {
        Log.i("Fac_FacFunction_Adapter", "setVcomPanelType = " + i);
        return this.mHisenseTvAPI.setVcomPanelType(i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean IsDolbyIQSupport() {
        int dolbyIQEnable = this.mHisenseTvAPI.getDolbyIQEnable();
        Log.i("Fac_FacFunction_Adapter", "IsDolbyIQSupportValue = " + dolbyIQEnable);
        return dolbyIQEnable == 1;
    }

    @Override // com.hisense.tvcm.FacFunction
    public String getDtcpIpKey() {
        String keyNameFromFile = this.mKeyWriter.readKeyNameFromFile(HisenseTvFactoryApi.FacKeyID.KEY_DTCPIP_ID);
        if (keyNameFromFile == null || keyNameFromFile.isEmpty()) {
            keyNameFromFile = "NG";
        }
        Log.i("Fac_FacFunction_Adapter", "getDtcpIipKey keyName = " + keyNameFromFile);
        return keyNameFromFile;
    }

    @Override // com.hisense.tvcm.FacFunction
    public String getMarlinKey() {
        String keyNameFromFile = this.mKeyWriter.readKeyNameFromFile(HisenseTvFactoryApi.FacKeyID.KEY_MARLIN_ID);
        if (keyNameFromFile == null || keyNameFromFile.isEmpty()) {
            keyNameFromFile = "NG";
        }
        Log.i("Fac_FacFunction_Adapter", "getMarlinKey keyName = " + keyNameFromFile);
        return keyNameFromFile;
    }

    @Override // com.hisense.tvcm.FacFunction
    public String getSeeQVaultKey() {
        String keyNameFromFile = this.mKeyWriter.readKeyNameFromFile(HisenseTvFactoryApi.FacKeyID.KEY_SQV000_ID);
        if (keyNameFromFile == null || keyNameFromFile.isEmpty()) {
            keyNameFromFile = "NG";
        }
        Log.i("Fac_FacFunction_Adapter", "getSeeQVaultKey keyName = " + keyNameFromFile);
        return keyNameFromFile;
    }

    @Override // com.hisense.tvcm.FacFunction
    public String getPVRKey() {
        String keyNameFromFile = this.mKeyWriter.readKeyNameFromFile(HisenseTvFactoryApi.FacKeyID.KEY_PVR000_ID);
        if (keyNameFromFile == null || keyNameFromFile.isEmpty()) {
            keyNameFromFile = "NG";
        }
        Log.i("Fac_FacFunction_Adapter", "getPVRKey keyName = " + keyNameFromFile);
        return keyNameFromFile;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void getDolbyUpdateResultTip(int i) {
        if (setDolbyIQUserBinEnable(i) == 0) {
            Toast.makeText(mContext, "Dolby Upgrade Success!", 1).show();
            return;
        }
        if (setDolbyIQUserBinEnable(i) == -1) {
            Toast.makeText(mContext, " cfg error!", 1).show();
        } else if (setDolbyIQUserBinEnable(i) == -2) {
            Toast.makeText(mContext, "bin error!", 1).show();
        } else if (setDolbyIQUserBinEnable(i) == -3) {
            Toast.makeText(mContext, "driver error! ", 1).show();
        }
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean isHaveTuner() {
        return this.mHisenseTvAPI.getTunnerGpioStatus() != 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public String getSlaveBacklightDriverVersion() {
        String slaveBluVersion = this.mHisenseTvAPI.getSlaveBluVersion();
        Log.i("Fac_FacFunction_Adapter", "slaveBluVersion  = " + slaveBluVersion);
        return slaveBluVersion;
    }

    @Override // com.hisense.tvcm.FacFunction
    public String getSlaveLCMRunningStatus() {
        String slaveBluStatus = this.mHisenseTvAPI.getSlaveBluStatus();
        Log.i("Fac_FacFunction_Adapter", "slaveblu status  = " + slaveBluStatus);
        return slaveBluStatus;
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean isDualBluSupport() {
        int bluNums = this.mHisenseTvAPI.getBluNums();
        Log.i("Fac_FacFunction_Adapter", "Blu Numbers  = " + bluNums);
        return bluNums == 2;
    }

    public boolean setDemuraToolsDriverParam(int i) {
        int demuraTconStatus = this.mHisenseTvAPI.setDemuraTconStatus(i);
        Log.i("Fac_FacFunction_Adapter", "setDemuraToolsDriverParam result = " + demuraTconStatus);
        return demuraTconStatus == 0;
    }

    public boolean setDemuraSPIStatus(int i) {
        Log.i("Fac_FacFunction_Adapter", "setDemuraSPIStatus  status = " + i);
        int demuraSpiStatus = this.mHisenseTvAPI.setDemuraSpiStatus(i);
        Log.i("Fac_FacFunction_Adapter", "setDemuraSPIStatus result = " + demuraSpiStatus);
        return demuraSpiStatus == 0;
    }

    public boolean setDemuraTconStatus(int i) {
        Log.i("Fac_FacFunction_Adapter", "setDemuraTconStatus  status = " + i);
        int demuraResetTcon = this.mHisenseTvAPI.setDemuraResetTcon(i);
        Log.i("Fac_FacFunction_Adapter", "setDemuraTconStatus result = " + demuraResetTcon);
        return demuraResetTcon == 0;
    }

    public boolean setDemuraDataErase() {
        Log.i("Fac_FacFunction_Adapter", "setDemuraDataErase ");
        boolean demuraFlashErase = this.mHisenseTvAPI.setDemuraFlashErase();
        Log.i("Fac_FacFunction_Adapter", "setDemuraDataErase result = " + demuraFlashErase);
        return demuraFlashErase;
    }

    public boolean setDemuraDataUpdate() {
        Log.i("Fac_FacFunction_Adapter", "setDemuraDataUpdate ");
        if (new File("/data/vendor/3rd_rw/factory/demura.bin").exists()) {
            boolean demuraFlashUpgrade = this.mHisenseTvAPI.setDemuraFlashUpgrade("/data/vendor/3rd_rw/factory/demura.bin");
            Log.i("Fac_FacFunction_Adapter", "setDemuraDataUpdate result = " + demuraFlashUpgrade);
            return demuraFlashUpgrade;
        }
        Log.i("Fac_FacFunction_Adapter", "setDemuraDataUpdate:demura file not exists ");
        return false;
    }

    @Override // com.hisense.tvcm.FacFunction
    public String getDolbyIQUserBinInfo() {
        String dolbyIQUserBinInfo = this.mHisenseTvAPI.getDolbyIQUserBinInfo();
        Log.i("Fac_FacFunction_Adapter", "userBinInfo:" + dolbyIQUserBinInfo);
        return dolbyIQUserBinInfo;
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean setDTCPIPKey(String str) {
        int iWriteTvsKey = this.mTvsKey.writeTvsKey(FacTvsFunctionUtil.DTCPIP_KEY_TYPE, str);
        if (iWriteTvsKey == 0) {
            this.mKeyWriter.writeTvsKeyName(HisenseTvFactoryApi.FacKeyID.KEY_DTCPIP_ID, str);
            updateFacLogStatus(131072);
        }
        Log.i("Fac_FacFunction_Adapter", "setDTCPIPKey result = " + iWriteTvsKey);
        setLogToFile("setDTCPIPKey " + iWriteTvsKey);
        return iWriteTvsKey == 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean setMarlinKey(String str) {
        int iWriteTvsKey = this.mTvsKey.writeTvsKey(FacTvsFunctionUtil.MARLIN_KEY_TYPE, str);
        if (iWriteTvsKey == 0) {
            this.mKeyWriter.writeTvsKeyName(HisenseTvFactoryApi.FacKeyID.KEY_MARLIN_ID, str);
            updateFacLogStatus(MtkTvScanDvbsBase.SB_DVBS_CONFIG_INSTALL_SCRAMBLE_SVC_ONLY);
        }
        Log.i("Fac_FacFunction_Adapter", "setMarlinKey result = " + iWriteTvsKey);
        setLogToFile("setMarlinKey " + iWriteTvsKey);
        return iWriteTvsKey == 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean setSQVKey(String str) {
        int iWriteTvsKey = this.mTvsKey.writeTvsKey(FacTvsFunctionUtil.SQV000_KEY_TYPE, str);
        if (iWriteTvsKey == 0) {
            this.mKeyWriter.writeTvsKeyName(HisenseTvFactoryApi.FacKeyID.KEY_SQV000_ID, str);
            updateFacLogStatus(524288);
        }
        Log.i("Fac_FacFunction_Adapter", "setSQVKey result = " + iWriteTvsKey);
        setLogToFile("setSQVKey " + iWriteTvsKey);
        return iWriteTvsKey == 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean setPVRKey(String str) {
        removeTvsKeyName(false);
        int iWriteTvsKey = this.mTvsKey.writeTvsKey(FacTvsFunctionUtil.PVR000_KEY_TYPE, str);
        if (iWriteTvsKey == 0) {
            this.mKeyWriter.writeTvsKeyName(HisenseTvFactoryApi.FacKeyID.KEY_PVR000_ID, str);
            updateFacLogStatus(1048576);
        }
        Log.i("Fac_FacFunction_Adapter", "setPVRKey result = " + iWriteTvsKey);
        setLogToFile("setPVRKey " + iWriteTvsKey);
        return iWriteTvsKey == 0;
    }

    public boolean isSupportSecondVcom() {
        boolean zIsSupportSecondVcom = this.mHisenseTvAPI.isSupportSecondVcom();
        Log.i("Fac_FacFunction_Adapter", "isSupportSecondVcom:" + zIsSupportSecondVcom);
        return zIsSupportSecondVcom;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getDemuraSwitchStatus() {
        int demuraSwitchStatus = this.mHisenseTvAPI.getDemuraSwitchStatus();
        Log.i("Fac_FacFunction_Adapter", "getDemuraSwitchStatus result = " + demuraSwitchStatus);
        return demuraSwitchStatus;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int setDemuraSwitchStatus(int i) {
        Log.i("Fac_FacFunction_Adapter", "setDemuraSwitchStatusValue:" + i);
        int demuraSwitchStatus = this.mHisenseTvAPI.setDemuraSwitchStatus(i);
        Log.i("Fac_FacFunction_Adapter", "setDemuraSwitchStatusresult:" + demuraSwitchStatus);
        return demuraSwitchStatus;
    }

    @Override // com.hisense.tvcm.FacFunction
    public String getAutoGammaStatus() {
        String autoGammaStatus = this.mHisenseTvAPI.getAutoGammaStatus();
        Log.i("Fac_FacFunction_Adapter", "autoGammaStatus:" + autoGammaStatus);
        return autoGammaStatus;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setTvsTvTuner(int i) {
        String currentInputSourceName = this.mMtkTvInputSource.getCurrentInputSourceName();
        Log.i("Fac_FacFunction_Adapter", "setTvsTvTuner source name  = " + currentInputSourceName);
        if (currentInputSourceName != null && !currentInputSourceName.isEmpty() && !currentInputSourceName.equalsIgnoreCase("TV")) {
            this.broadbase.syncStop("main", true);
            this.mHisenseTvAPI.setMiscInputInfo(1);
        }
        this.mFacTvsTvManager.setTune(i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public String getF1Result() {
        String aCASId = this.mFacTvsTvManager.getACASId();
        StringBuilder sb = new StringBuilder();
        sb.append("ACAS:");
        if (aCASId == null || aCASId.equals("")) {
            aCASId = "ERROR";
        }
        sb.append(aCASId);
        return ((sb.toString() + "\n") + "HDD:") + this.mFacTvsTvManager.getTvsStorageInfo();
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean isHaveHDD() {
        String tvsStorageInfo = this.mFacTvsTvManager.getTvsStorageInfo();
        Log.i("Fac_FacFunction_Adapter", "isHaveHDD result:" + tvsStorageInfo);
        return (tvsStorageInfo == null || tvsStorageInfo.isEmpty() || tvsStorageInfo.equals(" ")) ? false : true;
    }

    public void removeTvsKeyName(boolean z) {
        if (z) {
            HisenseTVNativeWrapper.factoryRemoveFile_native("/perm/keyname/pvr000.txt");
        }
        HisenseTVNativeWrapper.factoryRemoveFile_native("/perm/keyname/dtcpip.txt");
        HisenseTVNativeWrapper.factoryRemoveFile_native("/perm/keyname/marlin.txt");
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setUSBOverCurrent() {
        this.mTvsFunc.resetTvsUsbOverCurrent(CESIConstants.getValue("UsbPort_e", "GENERAL1"));
        this.mTvsFunc.resetTvsUsbOverCurrent(CESIConstants.getValue("UsbPort_e", "GENERAL2"));
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setLNB(int i) {
        Log.i("Fac_FacFunction_Adapter", "setLNB:" + i);
        if (i == 0) {
            i = FacTvsFunctionUtil.TVS_ANTENNA_POWER_MODE_AUTO;
        } else if (1 == i) {
            i = FacTvsFunctionUtil.TVS_ANTENNA_POWER_MODE_OFF;
        }
        this.mFacTvsTvManager.setAntennaPowerMode(i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getLNB() {
        return this.mFacTvsTvManager.getAntennaPowerMode();
    }

    @Override // com.hisense.tvcm.FacFunction
    public String getACASId() {
        String aCASId = this.mFacTvsTvManager.getACASId();
        return (aCASId == null || aCASId.equals("")) ? "null" : aCASId;
    }

    @Override // com.hisense.tvcm.FacFunction
    public String getHDDSize() {
        String tvsStorageInfo = this.mFacTvsTvManager.getTvsStorageInfo();
        return (tvsStorageInfo == null || tvsStorageInfo.equals("")) ? "null" : tvsStorageInfo;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getACASState() {
        return this.mFacTvsTvManager.getACASState();
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getSubWooferTotal() {
        int isSubwooferSupport = this.mHisenseTvAPI.getIsSubwooferSupport();
        Log.i("Fac_FacFunction_Adapter", "getSubWooferTotal:" + isSubwooferSupport);
        return isSubwooferSupport;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int setMultiDigitalAMPValueByDevId(int i, int i2, int i3) {
        Log.i("Fac_FacFunction_Adapter", "setMultiDigitalAMPValueByDevId :devId=" + i + ", index=" + i2 + ", value=" + i3);
        int multiDigitalAMPValueByDevId = this.mHisenseTvAPI.setMultiDigitalAMPValueByDevId(i, i2, i3);
        StringBuilder sb = new StringBuilder();
        sb.append("setMultiDigitalAMPValueByDevIdResult = ");
        sb.append(multiDigitalAMPValueByDevId);
        Log.i("Fac_FacFunction_Adapter", sb.toString());
        return multiDigitalAMPValueByDevId;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getMultiDigitalAMPValueByDevId(int i, int i2) {
        Log.i("Fac_FacFunction_Adapter", "getMultiDigitalAMPValueByDevId :devId=" + i + ", index=" + i2);
        int multiDigitalAMPValueByDevId = this.mHisenseTvAPI.getMultiDigitalAMPValueByDevId(i, i2);
        StringBuilder sb = new StringBuilder();
        sb.append("getMultiDigitalAMPValueByDevIdresult = ");
        sb.append(multiDigitalAMPValueByDevId);
        Log.i("Fac_FacFunction_Adapter", sb.toString());
        return multiDigitalAMPValueByDevId;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int setMultiBalance(int i) {
        Log.i("Fac_FacFunction_Adapter", "setMultiBalance value = " + i);
        int multiBalance = this.mHisenseTvAPI.setMultiBalance(i);
        Log.i("Fac_FacFunction_Adapter", "setMultiBalance result = " + multiBalance);
        return multiBalance;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getMultiBalance() {
        int multiBalance = this.mHisenseTvAPI.getMultiBalance();
        Log.i("Fac_FacFunction_Adapter", "getMultiBalance result = " + multiBalance);
        return multiBalance;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int setUnivMultiBalance(int i) {
        Log.i("Fac_FacFunction_Adapter", "setUnivMultiBalance value = " + i);
        int multiBalance = HisAudioApi.setMultiBalance(i);
        Log.i("Fac_FacFunction_Adapter", "setUnivMultiBalance result = " + multiBalance);
        return multiBalance;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getUnivMultiBalance(int i) {
        int soundCommonInf = HisAudioApi.getSoundCommonInf(0, i, 0);
        Log.i("Fac_FacFunction_Adapter", "getUnivMultiBalance result = " + soundCommonInf);
        return soundCommonInf;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setTVSLog(String str) {
        this.mTvsFunc.setTVSLog(str);
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean setLaserEyeProtectSwitch(int i) {
        Log.i("Fac_FacFunction_Adapter", "setLaserEyeProtectSwitch value = " + i);
        int laserEyeProtectSwitch = this.mHisenseTvAPI.setLaserEyeProtectSwitch(i);
        Log.i("Fac_FacFunction_Adapter", "setLaserEyeProtectSwitch result = " + laserEyeProtectSwitch);
        return laserEyeProtectSwitch == 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getLaserEyeProtectSwitch() {
        Log.i("Fac_FacFunction_Adapter", "getLaserEyeProtectSwitch");
        int laserEyeProtectSwitch = this.mHisenseTvAPI.getLaserEyeProtectSwitch();
        Log.i("Fac_FacFunction_Adapter", "getLaserEyeProtectSwitch result = " + laserEyeProtectSwitch);
        return laserEyeProtectSwitch;
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean clearOMRunningTime() {
        Log.i("Fac_FacFunction_Adapter", "clearOMRunningTime");
        int cleanStartUpTime = this.mHisenseTvAPI.setCleanStartUpTime(1);
        Log.i("Fac_FacFunction_Adapter", "clearOMRunningTime result = " + cleanStartUpTime);
        return cleanStartUpTime == 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean setLaserAutoGraphCorrection(int[] iArr) {
        boolean laserAutoGraphCorrection;
        Log.i("Fac_FacFunction_Adapter", "setLaserAutoGraphCorrection");
        if (FacPropertyDemo.HIFAC_LASER_IS_L9) {
            laserAutoGraphCorrection = this.mHisenseTvAPI.setLaserAutoGraphCorrection(iArr, 12);
        } else {
            ArrayList arrayList = new ArrayList();
            for (int i : iArr) {
                arrayList.add(Integer.valueOf(i));
            }
            Log.i("Fac_FacFunction_Adapter", "setLaserAutoGraphCorrectionUSB : value =" + arrayList);
            laserAutoGraphCorrection = HisensePlatformConfig.setLaserAutoGraphCorrectionUSB(arrayList) == 0;
        }
        Log.i("Fac_FacFunction_Adapter", "setLaserAutoGraphCorrection result = " + laserAutoGraphCorrection);
        return laserAutoGraphCorrection;
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean resetGraphCorrection() {
        boolean zResetGraphCorrection;
        Log.i("Fac_FacFunction_Adapter", "resetGraphCorrection");
        if (FacPropertyDemo.HIFAC_LASER_IS_L9) {
            zResetGraphCorrection = this.mHisenseTvAPI.resetGraphCorrection();
        } else {
            zResetGraphCorrection = HisensePlatformConfig.resetGraphCorrectionUSB() == 0;
        }
        Log.i("Fac_FacFunction_Adapter", "resetGraphCorrection result = " + zResetGraphCorrection);
        return zResetGraphCorrection;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getModelSelection() {
        Log.i("Fac_FacFunction_Adapter", "get getModel Selection");
        int laserCloOverlapSelect = this.mHisenseTvAPI.getLaserCloOverlapSelect();
        Log.i("Fac_FacFunction_Adapter", "get getModel Selection = " + laserCloOverlapSelect);
        return laserCloOverlapSelect;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setModelSelection(int i) {
        Log.i("Fac_FacFunction_Adapter", "set getModel Selection value = " + i);
        Log.i("Fac_FacFunction_Adapter", "set getModel Selection result = " + this.mHisenseTvAPI.setLaserCloOverlapSelect(i));
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getModeValue() {
        int laserOverlapMode;
        Log.i("Fac_FacFunction_Adapter", "get Mode Value");
        if (getModelSelection() == 0) {
            laserOverlapMode = this.mHisenseTvAPI.getLaserCloMode();
        } else {
            laserOverlapMode = this.mHisenseTvAPI.getLaserOverlapMode();
        }
        Log.i("Fac_FacFunction_Adapter", "get Mode Value = " + laserOverlapMode);
        return laserOverlapMode;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setModeValue(int i) {
        int laserOverlapMode;
        Log.i("Fac_FacFunction_Adapter", "set Mode Value");
        if (getModelSelection() == 0) {
            laserOverlapMode = this.mHisenseTvAPI.setLaserCloMode(i);
        } else {
            laserOverlapMode = this.mHisenseTvAPI.setLaserOverlapMode(i);
        }
        Log.i("Fac_FacFunction_Adapter", "set Mode Value result = " + laserOverlapMode);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getDistanceSensorStrengthValue() {
        Log.i("Fac_FacFunction_Adapter", "getDistanceSensorStrengthValue");
        int distenceSensorStrength = this.mHisenseTvAPI.getDistenceSensorStrength();
        Log.i("Fac_FacFunction_Adapter", "get Distance Sensor Strength Value = " + distenceSensorStrength);
        return distenceSensorStrength;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getBackgroundNoiseValue() {
        Log.i("Fac_FacFunction_Adapter", "getBackgroundNoiseValue");
        int backgroundNoiseValue = this.mHisenseTvAPI.getBackgroundNoiseValue();
        Log.i("Fac_FacFunction_Adapter", "get Background Noise Value = " + backgroundNoiseValue);
        return backgroundNoiseValue;
    }

    @Override // com.hisense.tvcm.FacFunction
    public String getLaserRuningState() {
        byte[] runingTime = this.mHisenseTvAPI.getRuningTime();
        if (runingTime == null) {
            return "[]";
        }
        String str = "[";
        for (int i = 0; i < runingTime.length; i += 2) {
            if (i > 0) {
                str = str + ", ";
            }
            StringBuilder sb = new StringBuilder();
            sb.append("bytes[");
            sb.append(i);
            sb.append("] =");
            sb.append((int) runingTime[i]);
            sb.append("bytes[");
            int i2 = i + 1;
            sb.append(i2);
            sb.append("] =");
            sb.append((int) runingTime[i2]);
            Log.i("Fac_FacFunction_Adapter", sb.toString());
            String str2 = "0x" + Integer.toHexString(runingTime[i] & 255) + Integer.toHexString(runingTime[i2] & 255);
            Log.i("Fac_FacFunction_Adapter", "byte2HexString =" + str2);
            int iIntValue = Integer.decode(str2).intValue();
            Log.i("Fac_FacFunction_Adapter", "hexString2Int =" + iIntValue);
            str = str + iIntValue;
        }
        String str3 = str + "]";
        Log.i("Fac_FacFunction_Adapter", "getLaserRuningState =" + str3);
        return str3;
    }

    @Override // com.hisense.tvcm.FacFunction
    public String getLaserRuningTime() {
        int laserRuningTime = this.mHisenseTvAPI.getLaserRuningTime();
        Log.i("Fac_FacFunction_Adapter", "getLaserRuningTime =" + laserRuningTime);
        return Integer.toString(laserRuningTime);
    }

    public void setFirstBootState(int i) {
        Log.i("Fac_FacFunction_Adapter", "setFirstBootState, value = " + i);
        Log.i("Fac_FacFunction_Adapter", "setFirstBootState, result = " + Boolean.valueOf(this.mHisenseTvAPI.setFirstBootState(i)));
    }

    public void setFirstPowerOnTime(long j) {
        Log.i("Fac_FacFunction_Adapter", "setFirstPowerOnTime, value = " + j);
        Log.i("Fac_FacFunction_Adapter", "setFirstPowerOnTime, result = " + Boolean.valueOf(this.mHisenseTvAPI.setFirstPowerOnTime(j)));
    }

    @Override // com.hisense.tvcm.FacFunction
    public String getFirstPowerOnTime() {
        long firstPowerOnTime = this.mHisenseTvAPI.getFirstPowerOnTime();
        Log.i("Fac_FacFunction_Adapter", "getFirstPowerOnTime =" + firstPowerOnTime);
        if (firstPowerOnTime <= 0) {
            return "";
        }
        String string = Long.toString(firstPowerOnTime);
        int length = string.length();
        if (length < 14) {
            Log.e("Fac_FacFunction_Adapter", "getFirstPowerOnTime, firstPowerOnTime length < 14 !!!");
            return "";
        }
        int i = length - 10;
        int i2 = length - 8;
        int i3 = length - 6;
        int i4 = length - 4;
        int i5 = length - 2;
        return string.substring(0, i) + MtkTvScanDvbsBase.TVAPI_DVBS_LINK_STRING + string.substring(i, i2) + MtkTvScanDvbsBase.TVAPI_DVBS_LINK_STRING + string.substring(i2, i3) + MtkTvScanDvbsBase.TVAPI_DVBS_LINK_STRING + string.substring(i3, i4) + MtkTvScanDvbsBase.TVAPI_DVBS_LINK_STRING + string.substring(i4, i5) + MtkTvScanDvbsBase.TVAPI_DVBS_LINK_STRING + string.substring(i5, length);
    }

    public void setStartOnCount(long j) {
        Log.i("Fac_FacFunction_Adapter", "setStartOnCount, value = " + j);
        Log.i("Fac_FacFunction_Adapter", "setStartOnCount, result = " + Boolean.valueOf(this.mHisenseTvAPI.setStartingOnCount(j)));
        Log.i("Fac_FacFunction_Adapter", "setStrStartOnCount, result = " + Boolean.valueOf(this.mHisenseTvAPI.setStrStartOnCount(j)));
    }

    @Override // com.hisense.tvcm.FacFunction
    public String getStartOnCount() {
        long startingOnCount = this.mHisenseTvAPI.getStartingOnCount();
        Log.i("Fac_FacFunction_Adapter", "getStartOnCount =" + startingOnCount);
        long strStartOnCount = this.mHisenseTvAPI.getStrStartOnCount();
        Log.i("Fac_FacFunction_Adapter", "getStrStartOnCount, result = " + strStartOnCount);
        long j = startingOnCount + strStartOnCount;
        return j > 0 ? Long.toString(j) : "";
    }

    @Override // com.hisense.tvcm.FacFunction
    public int[] getFansSpeed() {
        byte[] fanSpeed = this.mHisenseTvAPI.getFanSpeed();
        Log.d("Fac_FacFunction_Adapter", "getFansSpeed the value is : " + fanSpeed);
        int[] iArr = new int[fanSpeed.length];
        for (int i = 0; i < fanSpeed.length; i++) {
            if (fanSpeed[i] >= 0) {
                iArr[i] = fanSpeed[i];
            } else {
                iArr[i] = fanSpeed[i] + MtkTvTISMsgBase.MTK_TIS_VALUE_FALSE;
            }
        }
        return iArr;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getLaserForeignObjectDetectSwitch() {
        int laserForeignBodyDetectSwitch = this.mHisenseTvAPI.getLaserForeignBodyDetectSwitch();
        Log.i("Fac_FacFunction_Adapter", "getLaserForeignObjectDetectSwitchResult:" + laserForeignBodyDetectSwitch);
        return laserForeignBodyDetectSwitch;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setLaserForeignObjectDetectSwitch(int i) {
        Log.i("Fac_FacFunction_Adapter", "setLaserForeignObjectDetectSwitch:" + i);
        Log.i("Fac_FacFunction_Adapter", "setLaserForeignObjectDetectSwitchResult:" + this.mHisenseTvAPI.setLaserForeignBodyDetectSwitch(i));
    }

    private void wirteLogThread() {
        new Thread(new Runnable() { // from class: com.hisense.tvcm.FacFunction_Adapter.6
            @Override // java.lang.Runnable
            public void run() {
                while (true) {
                    if (FacFunction_Adapter.mFacLogIndex < FacFunction_Adapter.this.mFactoryLog.size()) {
                        String str = (String) FacFunction_Adapter.this.mFactoryLog.get(FacFunction_Adapter.mFacLogIndex);
                        int iMyPid = Process.myPid();
                        if (str != null && !str.isEmpty()) {
                            Log.i("Fac_FacFunction_Adapter", "wirteLogThread  pid" + iMyPid);
                            FacFunction_Adapter.this.mKeyWriter.setFactoryLog(str);
                            FacFunction_Adapter.access$608();
                            try {
                                Thread.sleep(50L);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Log.i("Fac_FacFunction_Adapter", "wirteLogThread do nothing pid" + iMyPid);
                            try {
                                Thread.sleep(200L);
                            } catch (InterruptedException e2) {
                                e2.printStackTrace();
                            }
                        }
                    } else {
                        if (FacFunction_Adapter.this.mIsExitFacLogThread) {
                            return;
                        }
                        try {
                            Thread.sleep(200L);
                        } catch (InterruptedException e3) {
                            e3.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getEqualizer120Hz_New() {
        int[] allAudioModeValue = this.mHisenseTvAPI.getAllAudioModeValue(MtkTvConfigTypeBase.CFG_FACTORY_AUD_EQ_BAND_1, 1);
        if (allAudioModeValue != null) {
            return allAudioModeValue[0];
        }
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getEqualizer250Hz() {
        int[] allAudioModeValue = this.mHisenseTvAPI.getAllAudioModeValue(MtkTvConfigTypeBase.CFG_FACTORY_AUD_EQ_BAND_2, 1);
        if (allAudioModeValue != null) {
            return allAudioModeValue[0];
        }
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getEqualizer600Hz() {
        int[] allAudioModeValue = this.mHisenseTvAPI.getAllAudioModeValue(MtkTvConfigTypeBase.CFG_FACTORY_AUD_EQ_BAND_3, 1);
        if (allAudioModeValue != null) {
            return allAudioModeValue[0];
        }
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getEqualizer1KHz() {
        int[] allAudioModeValue = this.mHisenseTvAPI.getAllAudioModeValue(MtkTvConfigTypeBase.CFG_FACTORY_AUD_EQ_BAND_4, 1);
        if (allAudioModeValue != null) {
            return allAudioModeValue[0];
        }
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getEqualizer2500Hz() {
        int[] allAudioModeValue = this.mHisenseTvAPI.getAllAudioModeValue(MtkTvConfigTypeBase.CFG_FACTORY_AUD_EQ_BAND_5, 1);
        if (allAudioModeValue != null) {
            return allAudioModeValue[0];
        }
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getEqualizer6KHz() {
        int[] allAudioModeValue = this.mHisenseTvAPI.getAllAudioModeValue(MtkTvConfigTypeBase.CFG_FACTORY_AUD_EQ_BAND_6, 1);
        if (allAudioModeValue != null) {
            return allAudioModeValue[0];
        }
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getEqualizer10KHz_New() {
        int[] allAudioModeValue = this.mHisenseTvAPI.getAllAudioModeValue(MtkTvConfigTypeBase.CFG_FACTORY_AUD_EQ_BAND_7, 1);
        if (allAudioModeValue != null) {
            return allAudioModeValue[0];
        }
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setEqualizer120Hz_New(int i) {
        Log.i("Fac_FacFunction_Adapter", "setEqualizer120Hz_New called, value:" + i);
        this.mHisenseTvAPI.setAudioModeDetailValue(MtkTvConfigTypeBase.CFG_FACTORY_AUD_EQ_BAND_1, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setEqualizer250Hz(int i) {
        Log.i("Fac_FacFunction_Adapter", "setEqualizer250Hz called, value:" + i);
        this.mHisenseTvAPI.setAudioModeDetailValue(MtkTvConfigTypeBase.CFG_FACTORY_AUD_EQ_BAND_2, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setEqualizer600Hz(int i) {
        Log.i("Fac_FacFunction_Adapter", "setEqualizer600Hz called, value:" + i);
        this.mHisenseTvAPI.setAudioModeDetailValue(MtkTvConfigTypeBase.CFG_FACTORY_AUD_EQ_BAND_3, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setEqualizer1KHz(int i) {
        Log.i("Fac_FacFunction_Adapter", "setEqualizer1KHz called, value:" + i);
        this.mHisenseTvAPI.setAudioModeDetailValue(MtkTvConfigTypeBase.CFG_FACTORY_AUD_EQ_BAND_4, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setEqualizer2500Hz(int i) {
        Log.i("Fac_FacFunction_Adapter", "setEqualizer2500Hz called, value:" + i);
        this.mHisenseTvAPI.setAudioModeDetailValue(MtkTvConfigTypeBase.CFG_FACTORY_AUD_EQ_BAND_5, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setEqualizer6KHz(int i) {
        Log.i("Fac_FacFunction_Adapter", "setEqualizer6KHz called, value:" + i);
        this.mHisenseTvAPI.setAudioModeDetailValue(MtkTvConfigTypeBase.CFG_FACTORY_AUD_EQ_BAND_6, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setEqualizer10KHz_New(int i) {
        Log.i("Fac_FacFunction_Adapter", "setEqualizer10KHz_New called, value:" + i);
        this.mHisenseTvAPI.setAudioModeDetailValue(MtkTvConfigTypeBase.CFG_FACTORY_AUD_EQ_BAND_7, i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int[] getBoardTemperature() {
        byte[] laserTemperature = this.mHisenseTvAPI.getLaserTemperature();
        Log.d("Fac_FacFunction_Adapter", "getBoardTemperature the value is : " + laserTemperature);
        int[] iArr = new int[4];
        for (int i = 0; i < 4; i++) {
            iArr[i] = laserTemperature[i];
        }
        return iArr;
    }

    public void setTVSTvSourceStatus(boolean z, int i) {
        this.mFacTvsTvManager.setTvDisabled(z);
        this.mHisenseTvAPI.setMiscInputInfo(i);
    }

    public void setTvDisabled(boolean z) {
        if (FacPropertyDemo.HIFAC_MARKET_JP) {
            this.mFacTvsTvManager.setTvDisabled(z);
        }
    }

    @Override // com.hisense.tvcm.FacFunction
    public String getTvsLightSensor() {
        return this.mFacTvsAqPqmanager.getLightSensor();
    }

    public int getTvsLightSensorForSerial(int i) {
        return this.mFacTvsAqPqmanager.getLightSensorForSerial(i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getLastSource() {
        return this.mFacTvsSourceManager.getLastSource();
    }

    @Override // com.hisense.tvcm.FacFunction
    public int setBackgroundNoiseValue(int i) {
        Log.d("Fac_FacFunction_Adapter", "setBackgroundNoiseValue the value is : " + i);
        int backgroundNoiseValue = this.mHisenseTvAPI.setBackgroundNoiseValue(i);
        Log.d("Fac_FacFunction_Adapter", "setBackgroundNoiseValue result is : " + backgroundNoiseValue);
        return backgroundNoiseValue;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getBackgroundNoisePredeterminedValue() {
        int i = SystemProperties.getInt("ro.vt.product.laser_noise_max", MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_MSGQ_EMPTY);
        Log.d("Fac_FacFunction_Adapter", "getBackgroundNoisePredeterminedValue result is : " + i);
        return i;
    }

    @Override // com.hisense.tvcm.FacFunction
    public float getBackgroundNoiseCorrectionFactorValue() {
        String str = SystemProperties.get("ro.vt.product.laser_noise_ratio", "1");
        Log.d("Fac_FacFunction_Adapter", "getBackgroundNoiseCorrectionFactorValue original result is : " + str);
        Float fValueOf = Float.valueOf(Float.valueOf(str).floatValue());
        Log.d("Fac_FacFunction_Adapter", "getBackgroundNoiseCorrectionFactorValue result is : " + str);
        return fValueOf.floatValue();
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setAudioCurveDetailValueNew(int i, int i2) {
        Log.i("Fac_FacFunction_Adapter", "setAudioCurveDetailValueNew, index:" + i + "value:" + i2);
        boolean audioCurveDetailValueNew = this.mHisenseTvAPI.setAudioCurveDetailValueNew(i, i2);
        StringBuilder sb = new StringBuilder();
        sb.append("setAudioCurveDetailValueNewResult = ");
        sb.append(audioCurveDetailValueNew);
        Log.i("Fac_FacFunction_Adapter", sb.toString());
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getVolumeCurve(int i) {
        Log.i("Fac_FacFunction_Adapter", "getVolumeCurve,index = " + i);
        int volumeCurve = this.mHisenseTvAPI.getVolumeCurve(i);
        Log.i("Fac_FacFunction_Adapter", "getVolumeCurveResult = " + volumeCurve);
        return volumeCurve;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int setTVSSavingMode() {
        return this.mTvsFunc.setSavingMode(255);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setMtkSourceStatus(boolean z) {
        MtkTvHighLevel mtkTvHighLevel;
        Log.i("Fac_FacFunction_Adapter", "setMtkSourceStatus,isStart = " + z);
        if (FacPropertyDemo.HIFAC_MARKET_JP) {
            String currentSourceName = getCurrentSourceName();
            Log.i("Fac_FacFunction_Adapter", "setMtkSourceStatus,name = " + currentSourceName);
            if (MtkTvInputSourceBase.INPUT_TYPE_TV.equalsIgnoreCase(currentSourceName) || (mtkTvHighLevel = this.mMtkTvHighLevel) == null) {
                return;
            }
            if (z) {
                mtkTvHighLevel.startTV();
                return;
            } else {
                mtkTvHighLevel.stopTV();
                return;
            }
        }
        if (FacPropertyDemo.HIFAC_ATSC30_SUPPORT) {
            String currentSourceName2 = getCurrentSourceName();
            Log.i("Fac_FacFunction_Adapter", "setMtkSourceStatus,name = " + currentSourceName2);
            if (MtkTvInputSourceBase.INPUT_TYPE_TV.equalsIgnoreCase(currentSourceName2)) {
                if (z) {
                    this.mAtscCommons.startTV();
                    return;
                } else {
                    this.mAtscCommons.stopTV();
                    return;
                }
            }
            MtkTvHighLevel mtkTvHighLevel2 = this.mMtkTvHighLevel;
            if (mtkTvHighLevel2 != null) {
                if (z) {
                    mtkTvHighLevel2.startTV();
                    return;
                } else {
                    mtkTvHighLevel2.stopTV();
                    return;
                }
            }
            return;
        }
        MtkTvHighLevel mtkTvHighLevel3 = this.mMtkTvHighLevel;
        if (mtkTvHighLevel3 != null) {
            if (z) {
                mtkTvHighLevel3.startTV();
            } else {
                mtkTvHighLevel3.stopTV();
            }
        }
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getTvsCurrentChannelNumber() {
        Log.i("Fac_FacFunction_Adapter", "getTvsCurrentChannelNumber");
        int currentChannelNumber = this.mFacTvsTvManager.getCurrentChannelNumber();
        Log.i("Fac_FacFunction_Adapter", "getTvsCurrentChannelNumber = " + currentChannelNumber);
        return currentChannelNumber;
    }

    @Override // com.hisense.tvcm.FacFunction
    public String getTvsCurrentChannelTuner() {
        Log.i("Fac_FacFunction_Adapter", "getTvsCurrentChannelTuner");
        String currentChannelTuner = this.mFacTvsTvManager.getCurrentChannelTuner();
        Log.i("Fac_FacFunction_Adapter", "getTvsCurrentChannelTuner = " + currentChannelTuner);
        return currentChannelTuner;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int setTVSSavingMode(int i) {
        return this.mTvsFunc.setSavingMode(i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean setMajorMaintenance() {
        Log.i("Fac_FacFunction_Adapter", "setMajorMaintenance");
        boolean zStartJb = this.mPanelProtectManager.startJb(0);
        Log.i("Fac_FacFunction_Adapter", "setMajorMaintenance result = " + zStartJb);
        return zStartJb;
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean setMinorMaintenance() {
        Log.i("Fac_FacFunction_Adapter", "setMinorMaintenance");
        boolean zStartOffrs = this.mPanelProtectManager.startOffrs(0);
        Log.i("Fac_FacFunction_Adapter", "setMinorMaintenance result = " + zStartOffrs);
        return zStartOffrs;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int setMuteColor(int i) {
        Log.d("Fac_FacFunction_Adapter", "setMuteColor" + i);
        int muteColor = this.mHisenseTvAPI.setMuteColor(i);
        Log.d("Fac_FacFunction_Adapter", "setMuteColor ret = " + muteColor);
        return muteColor;
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean isFileExit(String str) {
        return this.mHisenseTvAPI.isFileExit(str) == 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int setTwelveVoltsStatus(int i) {
        Log.i("Fac_FacFunction_Adapter", "set 12v value = " + i);
        Log.i("Fac_FacFunction_Adapter", "set 12v rel = 0");
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int initInnertconBoard() {
        Log.i("Fac_FacFunction_Adapter", "initInnertconBoard rel = 0");
        return 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getFactoryTconStatus() {
        Log.i("Fac_FacFunction_Adapter", "tconType(1:inner tcon;0 outside tcon) = 0");
        return 0;
    }

    private void setODStatus() {
        Log.i("Fac_FacFunction_Adapter", "setODStatus value = ");
    }

    @Override // com.hisense.tvcm.FacFunction
    public String getQDAUUID() {
        char cByteValue;
        ArrayList<Byte> qdauuid = this.mKeyWriter.getQDAUUID();
        String str = "";
        if (qdauuid != null && qdauuid.size() > 0) {
            Iterator<Byte> it = qdauuid.iterator();
            while (it.hasNext() && (cByteValue = (char) it.next().byteValue()) != 0) {
                str = str + cByteValue;
            }
        }
        Log.i("Fac_FacFunction_Adapter", "getQDAUUID = " + str);
        return str;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getTvsTPC() {
        return this.mFacTvsAqPqmanager.getTvsTPC();
    }

    public void setTvsTPC(int i) {
        this.mFacTvsAqPqmanager.setTvsTPC(i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getCurrentTvInch() {
        int length;
        String str = SystemProperties.get("ro.oem.key1", "");
        Log.i("Fac_FacFunction_Adapter", "startTestMic  oemkey = " + str);
        if (str != null && !str.isEmpty() && (length = str.length()) >= 4) {
            String strSubstring = str.substring(length - 4, length - 2);
            Log.i("Fac_FacFunction_Adapter", "startTestMic  tvInch = " + strSubstring);
            if (strSubstring != null && !strSubstring.isEmpty()) {
                int i = Integer.parseInt(strSubstring);
                Log.i("Fac_FacFunction_Adapter", "startTestMic  result = " + i);
                return i;
            }
        }
        return 43;
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean getEmmcType() {
        Log.i("Fac_FacFunction_Adapter", "getEmmcType");
        int emmcType = this.mHisenseTvAPI.getEmmcType();
        Log.i("Fac_FacFunction_Adapter", "getEmmcType result = " + emmcType);
        return emmcType == 1;
    }

    public boolean setAdjustFocus(int i) {
        Log.i("Fac_FacFunction_Adapter", "setAdjustFocus  : " + i);
        boolean laserFocus = this.mHisenseTvAPI.setLaserFocus(i);
        Log.i("Fac_FacFunction_Adapter", "setAdjustFocus result = " + laserFocus);
        return laserFocus;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void writeBooleanSetting(String str, boolean z) {
        Settings.Global.putInt(mContext.getContentResolver(), str, z ? 1 : 0);
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean setServiceTag(String str) {
        Log.i("Fac_FacFunction_Adapter", "setServiceTag  : " + str);
        return this.mHisenseTvAPI.setServiceTag(str);
    }

    @Override // com.hisense.tvcm.FacFunction
    public String getServiceTag() {
        String serviceTag = this.mHisenseTvAPI.getServiceTag();
        Log.i("Fac_FacFunction_Adapter", "getServiceTag1  : " + serviceTag);
        if (!serviceTag.startsWith("ST") || serviceTag.length() != 16) {
            serviceTag = "";
        }
        Log.i("Fac_FacFunction_Adapter", "getServiceTag2  : " + serviceTag);
        return serviceTag;
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setFacCheckLogStatus(int i) {
        Log.i("Fac_FacFunction_Adapter", "setFacCheckLogStatus value is " + Integer.toBinaryString(i));
        this.mHisenseTvAPI.setFactoryCheckStatus(i);
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getFacCheckLogStatus() {
        int factoryCheckStatus = this.mHisenseTvAPI.getFactoryCheckStatus();
        Log.i("Fac_FacFunction_Adapter", "getFacCheckLogStatus is " + factoryCheckStatus);
        return factoryCheckStatus;
    }

    public void updateFacCheckLogStatus(int i) {
        Log.i("Fac_FacFunction_Adapter", "updateFacCheckLogStatus value is " + Integer.toBinaryString(i));
        if (i == 0) {
            setFacCheckLogStatus(0);
            return;
        }
        int facCheckLogStatus = getFacCheckLogStatus() | i;
        Log.i("Fac_FacFunction_Adapter", "SA markert setFacCheckLogStatus value is " + Integer.toBinaryString(i));
        setFacCheckLogStatus(facCheckLogStatus);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setSAMarketFacCheckFlag() {
        this.mainBoardTestIsStrat = true;
        setFacCheckLogStatus(0);
        updateFacCheckLogStatus(1);
        Log.d("Fac_FacFunction_Adapter", "setSAMarketFacCheckFlag ;mainBoardTestIsStrat =" + this.mainBoardTestIsStrat);
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setLocalKeypadLog() {
        Log.d("Fac_FacFunction_Adapter", "setLocalKeypadLog ;mainBoardTestIsStrat =" + this.mainBoardTestIsStrat);
        if (this.mainBoardTestIsStrat && FacPropertyDemo.HIFAC_MARKET_SA) {
            updateFacCheckLogStatus(256);
        }
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setSoftwareVersionLog() {
        Log.d("Fac_FacFunction_Adapter", "setSoftwareVersionLog ;mainBoardTestIsStrat =" + this.mainBoardTestIsStrat);
        if (this.mainBoardTestIsStrat && FacPropertyDemo.HIFAC_MARKET_SA) {
            updateFacCheckLogStatus(2);
        }
    }

    public void setWifiBTStatusToLog() {
        Log.d("Fac_FacFunction_Adapter", "setWifiBTStatusToLog ;mainBoardTestIsStrat =" + this.mainBoardTestIsStrat);
        if (this.mainBoardTestIsStrat && FacPropertyDemo.HIFAC_MARKET_SA) {
            updateFacCheckLogStatus(64);
            updateFacCheckLogStatus(128);
        }
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setMacKeysStatusToLog() {
        Log.d("Fac_FacFunction_Adapter", "setMacKeysStatusToLog ;mainBoardTestIsStrat =" + this.mainBoardTestIsStrat);
        if (this.mainBoardTestIsStrat && FacPropertyDemo.HIFAC_MARKET_SA) {
            updateFacCheckLogStatus(4);
        }
    }

    @Override // com.hisense.tvcm.FacFunction
    public void setDMPStatusToLog() {
        Log.d("Fac_FacFunction_Adapter", "setDMPStatusToLog ;mainBoardTestIsStrat =" + this.mainBoardTestIsStrat);
        if (this.mainBoardTestIsStrat && FacPropertyDemo.HIFAC_MARKET_SA) {
            updateFacCheckLogStatus(32);
        }
    }

    public void setHDMISourceList() {
        this.mHdmiList = new ArrayList();
        for (FacPropertyDemo.SourceEnum sourceEnum : FacPropertyDemo.SourceEnum.values()) {
            if (sourceEnum.isEnabled() && sourceEnum.isShown() && sourceEnum.name().contains("HDMI")) {
                Log.i("Fac_FacPropertyDemo", "hdmi source:" + sourceEnum.name());
                this.mHdmiList.add(sourceEnum);
            }
        }
    }

    public int getHDMINumber() {
        setHDMISourceList();
        if (this.mHdmiList == null) {
            return 0;
        }
        Log.d("Fac_FacFunction_Adapter", "getHDMINumber" + this.mHdmiList.size());
        return this.mHdmiList.size();
    }

    public boolean setLDTestEnable(int i) {
        Log.i("Fac_FacFunction_Adapter", "setOledTpcValue  value = " + i);
        int lDTestEnable = HisVideoApi.setLDTestEnable(i);
        Log.i("Fac_FacFunction_Adapter", "setOledTpcValue result = " + lDTestEnable);
        return lDTestEnable == 0;
    }

    @Override // com.hisense.tvcm.FacFunction
    public boolean setLaserSlidingCoverMode(int i) {
        Log.i("Fac_FacFunction_Adapter", "setLaserSlidingCoverMode  value = " + i);
        boolean laserSlidingCoverMode = this.mHisenseTvAPI.setLaserSlidingCoverMode(i);
        Log.i("Fac_FacFunction_Adapter", "setLaserSlidingCoverMode result = " + laserSlidingCoverMode);
        return laserSlidingCoverMode;
    }

    public boolean setLaserMovableCoverStep(int i) {
        Log.i("Fac_FacFunction_Adapter", "setLaserMovableCoverStep  value = " + i);
        boolean laserMovableCoverStep = this.mHisenseTvAPI.setLaserMovableCoverStep(i);
        Log.i("Fac_FacFunction_Adapter", "setLaserMovableCoverStep result = " + laserMovableCoverStep);
        return laserMovableCoverStep;
    }

    @Override // com.hisense.tvcm.FacFunction
    public int getLaserMovableCoverStep() {
        int laserMovableCoverStep = this.mHisenseTvAPI.getLaserMovableCoverStep();
        Log.i("Fac_FacFunction_Adapter", "getLaserMovableCoverStep is " + laserMovableCoverStep);
        return laserMovableCoverStep;
    }

    public boolean saveLaserMovableCoverStep() {
        boolean zSaveLaserMovableCoverStep = this.mHisenseTvAPI.SaveLaserMovableCoverStep();
        Log.i("Fac_FacFunction_Adapter", "saveLaserMovableCoverStep is " + zSaveLaserMovableCoverStep);
        return zSaveLaserMovableCoverStep;
    }

    public boolean resetLaserMovableCoverStep() {
        boolean zResetLaserMovableCoverStep = this.mHisenseTvAPI.ResetLaserMovableCoverStep();
        Log.i("Fac_FacFunction_Adapter", "resetLaserMovableCoverStep is " + zResetLaserMovableCoverStep);
        return zResetLaserMovableCoverStep;
    }

    private final void restartWidevineDrmService() {
        Log.i("Fac_FacFunction_Adapter", "try to restart widevine drm service: " + FacPropertyDemo.WIDEVINE_DRM_SERVICE_NAME);
        SystemProperties.set("ctl.restart", FacPropertyDemo.WIDEVINE_DRM_SERVICE_NAME);
    }
}
