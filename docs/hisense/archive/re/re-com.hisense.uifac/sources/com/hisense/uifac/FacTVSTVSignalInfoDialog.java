package com.hisense.uifac;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;
import com.google.gson.Gson;
import com.hisense.tvs.FacTvsTvManager;
import com.hisense.uiadapter.FactorySignalQualityTestInfo;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class FacTVSTVSignalInfoDialog extends Dialog {
    private FacTvsTvManager mFacTvsTvManager;
    private final Handler mHandler;
    private TextView tvBCName;
    private TextView tvBERTh1;
    private TextView tvBERTh2;
    private TextView tvBERTh3;
    private TextView tvPortCheckResultTh1;
    private TextView tvPortCheckResultTh2;
    private TextView tvPortCheckResultTh3;
    private TextView tvSignalLevelTh1;
    private TextView tvSignalLevelTh2;
    private TextView tvSignalLevelTh3;
    private TextView tvSignalQualityTh1;
    private TextView tvSignalQualityTh2;
    private TextView tvSignalQualityTh3;
    private TextView tvTestResultTh1;
    private TextView tvTestResultTh2;
    private TextView tvTestResultTh3;

    public FacTVSTVSignalInfoDialog(Context context, int i, Handler handler) {
        super(context, i);
        this.mHandler = new Handler() { // from class: com.hisense.uifac.FacTVSTVSignalInfoDialog.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what == 0) {
                    FacTVSTVSignalInfoDialog.this.addDataToTable();
                }
                super.handleMessage(message);
            }
        };
        Log.i("FacTVSTVSignalInfoDialog", "on Constructor");
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initUI();
        this.mFacTvsTvManager = new FacTvsTvManager();
        initTunnerName();
        this.mFacTvsTvManager.startSignalQualityTest();
        addDataToTable();
    }

    private void initTunnerName() {
        this.tvBCName.setText(this.mFacTvsTvManager.getCurrentChannelTuner());
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        this.mHandler.removeMessages(0);
        this.mFacTvsTvManager.stopSignalQualityTest();
        super.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addDataToTable() {
        FactorySignalQualityTestInfo factorySignalQualityTestInfo;
        try {
            factorySignalQualityTestInfo = (FactorySignalQualityTestInfo) new Gson().fromJson(this.mFacTvsTvManager.getSignalQualityTestInfo(), FactorySignalQualityTestInfo.class);
        } catch (Exception e) {
            e.printStackTrace();
            factorySignalQualityTestInfo = null;
        }
        List<FactorySignalQualityTestInfo.InfosBean> infos = factorySignalQualityTestInfo != null ? factorySignalQualityTestInfo.getInfos() : null;
        int size = infos != null ? infos.size() : 0;
        Log.i("FacTVSTVSignalInfoDialog", "FacTVSTVSignalInfoDialog infoNum =" + size);
        if (size <= 0) {
            this.tvSignalQualityTh1.setText("GETTING");
            this.tvBERTh1.setText("GETTING");
            this.tvSignalLevelTh1.setText("GETTING");
            this.tvTestResultTh1.setText("GETTING");
            this.tvPortCheckResultTh1.setText("GETTING");
            this.tvSignalQualityTh2.setText("GETTING");
            this.tvBERTh2.setText("GETTING");
            this.tvSignalLevelTh2.setText("GETTING");
            this.tvTestResultTh2.setText("GETTING");
            this.tvPortCheckResultTh2.setText("GETTING");
            this.tvSignalQualityTh3.setText("GETTING");
            this.tvBERTh3.setText("GETTING");
            this.tvSignalLevelTh3.setText("GETTING");
            this.tvTestResultTh3.setText("GETTING");
            this.tvPortCheckResultTh3.setText("GETTING");
        }
        if (size == 1) {
            this.tvSignalQualityTh1.setText("" + infos.get(0).getSignalQuality());
            this.tvBERTh1.setText("" + infos.get(0).getBitErrorRate());
            this.tvSignalLevelTh1.setText("" + infos.get(0).getSignalLevel());
            this.tvTestResultTh1.setText(infos.get(0).isTunerResult() ? "OK" : "NG");
            this.tvPortCheckResultTh1.setText(infos.get(0).isTsPortResult() ? "OK" : "NG");
            this.tvSignalQualityTh2.setText("GETTING");
            this.tvBERTh2.setText("GETTING");
            this.tvSignalLevelTh2.setText("GETTING");
            this.tvTestResultTh2.setText("GETTING");
            this.tvPortCheckResultTh2.setText("GETTING");
            this.tvSignalQualityTh3.setText("GETTING");
            this.tvBERTh3.setText("GETTING");
            this.tvSignalLevelTh3.setText("GETTING");
            this.tvTestResultTh3.setText("GETTING");
            this.tvPortCheckResultTh3.setText("GETTING");
        }
        if (size == 2) {
            this.tvSignalQualityTh1.setText("" + infos.get(0).getSignalQuality());
            this.tvBERTh1.setText("" + infos.get(0).getBitErrorRate());
            this.tvSignalLevelTh1.setText("" + infos.get(0).getSignalLevel());
            this.tvTestResultTh1.setText(infos.get(0).isTunerResult() ? "OK" : "NG");
            this.tvPortCheckResultTh1.setText(infos.get(0).isTsPortResult() ? "OK" : "NG");
            this.tvSignalQualityTh2.setText("" + infos.get(1).getSignalQuality());
            this.tvBERTh2.setText("" + infos.get(1).getBitErrorRate());
            this.tvSignalLevelTh2.setText("" + infos.get(1).getSignalLevel());
            this.tvTestResultTh2.setText(infos.get(1).isTunerResult() ? "OK" : "NG");
            this.tvPortCheckResultTh2.setText(infos.get(1).isTsPortResult() ? "OK" : "NG");
            this.tvSignalQualityTh3.setText("GETTING");
            this.tvBERTh3.setText("GETTING");
            this.tvSignalLevelTh3.setText("GETTING");
            this.tvTestResultTh3.setText("GETTING");
            this.tvPortCheckResultTh3.setText("GETTING");
        }
        if (size >= 3) {
            this.tvSignalQualityTh1.setText("" + infos.get(0).getSignalQuality());
            this.tvBERTh1.setText("" + infos.get(0).getBitErrorRate());
            this.tvSignalLevelTh1.setText("" + infos.get(0).getSignalLevel());
            this.tvTestResultTh1.setText(infos.get(0).isTunerResult() ? "OK" : "NG");
            this.tvPortCheckResultTh1.setText(infos.get(0).isTsPortResult() ? "OK" : "NG");
            this.tvSignalQualityTh2.setText("" + infos.get(1).getSignalQuality());
            this.tvBERTh2.setText("" + infos.get(1).getBitErrorRate());
            this.tvSignalLevelTh2.setText("" + infos.get(1).getSignalLevel());
            this.tvTestResultTh2.setText(infos.get(1).isTunerResult() ? "OK" : "NG");
            this.tvPortCheckResultTh2.setText(infos.get(1).isTsPortResult() ? "OK" : "NG");
            this.tvSignalQualityTh3.setText("" + infos.get(2).getSignalQuality());
            this.tvBERTh3.setText("" + infos.get(2).getBitErrorRate());
            this.tvSignalLevelTh3.setText("" + infos.get(2).getSignalLevel());
            this.tvTestResultTh3.setText(infos.get(2).isTunerResult() ? "OK" : "NG");
            this.tvPortCheckResultTh3.setText(infos.get(2).isTsPortResult() ? "OK" : "NG");
        }
        checkResult();
    }

    private void checkResult() {
        if (this.tvTestResultTh1.getText() != null && "NG".equals(this.tvTestResultTh1.getText().toString())) {
            this.tvTestResultTh1.setTextColor(-65536);
        } else {
            this.tvTestResultTh1.setTextColor(-1);
        }
        if (this.tvSignalLevelTh2.getText() != null && "NG".equals(this.tvTestResultTh2.getText().toString())) {
            this.tvTestResultTh2.setTextColor(-65536);
        } else {
            this.tvTestResultTh2.setTextColor(-1);
        }
        if (this.tvSignalLevelTh3.getText() != null && "NG".equals(this.tvTestResultTh3.getText().toString())) {
            this.tvTestResultTh3.setTextColor(-65536);
        } else {
            this.tvTestResultTh3.setTextColor(-1);
        }
        if (this.tvPortCheckResultTh1.getText() != null && "NG".equals(this.tvPortCheckResultTh1.getText().toString())) {
            this.tvPortCheckResultTh1.setTextColor(-65536);
        } else {
            this.tvPortCheckResultTh1.setTextColor(-1);
        }
        if (this.tvPortCheckResultTh2.getText() != null && "NG".equals(this.tvPortCheckResultTh2.getText().toString())) {
            this.tvPortCheckResultTh2.setTextColor(-65536);
        } else {
            this.tvPortCheckResultTh2.setTextColor(-1);
        }
        if (this.tvPortCheckResultTh3.getText() != null && "NG".equals(this.tvPortCheckResultTh3.getText().toString())) {
            this.tvPortCheckResultTh3.setTextColor(-65536);
        } else {
            this.tvPortCheckResultTh3.setTextColor(-1);
        }
        this.mHandler.sendEmptyMessageDelayed(0, 2000L);
    }

    private void initUI() {
        setContentView(R.layout.tvs_signal_info_dialog);
        this.tvBCName = (TextView) findViewById(R.id.facmenu_tvs_bcname);
        this.tvSignalQualityTh1 = (TextView) findViewById(R.id.facmenu_tvs_th1_signal_quality);
        this.tvSignalQualityTh2 = (TextView) findViewById(R.id.facmenu_tvs_th2_signal_quality);
        this.tvSignalQualityTh3 = (TextView) findViewById(R.id.facmenu_tvs_th3_signal_quality);
        this.tvBERTh1 = (TextView) findViewById(R.id.facmenu_tvs_th1_ber);
        this.tvBERTh2 = (TextView) findViewById(R.id.facmenu_tvs_th2_ber);
        this.tvBERTh3 = (TextView) findViewById(R.id.facmenu_tvs_th3_ber);
        this.tvSignalLevelTh1 = (TextView) findViewById(R.id.facmenu_tvs_th1_signal_level);
        this.tvSignalLevelTh2 = (TextView) findViewById(R.id.facmenu_tvs_th2_signal_level);
        this.tvSignalLevelTh3 = (TextView) findViewById(R.id.facmenu_tvs_th3_signal_level);
        this.tvTestResultTh1 = (TextView) findViewById(R.id.facmenu_tvs_th1_test_result);
        this.tvTestResultTh2 = (TextView) findViewById(R.id.facmenu_tvs_th2_test_result);
        this.tvTestResultTh3 = (TextView) findViewById(R.id.facmenu_tvs_th3_test_result);
        this.tvPortCheckResultTh1 = (TextView) findViewById(R.id.facmenu_tvs_th1_port_check_result);
        this.tvPortCheckResultTh2 = (TextView) findViewById(R.id.facmenu_tvs_th2_port_check_result);
        this.tvPortCheckResultTh3 = (TextView) findViewById(R.id.facmenu_tvs_th3_port_check_result);
    }
}
