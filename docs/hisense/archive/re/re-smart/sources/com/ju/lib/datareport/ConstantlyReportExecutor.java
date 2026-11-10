package com.ju.lib.datareport;

import b.a.a.a.a;
import com.ju.lib.utils.log.LogUtil;
import com.ju.lib.utils.thread.JuThread;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/* JADX INFO: loaded from: classes2.dex */
public class ConstantlyReportExecutor implements Reporter.StrategyListener {
    public static final int MAX_RETRY_TIMES = 2;
    public ReportPoster mPoster;
    public Thread reportThread;
    public Strategy strategy;
    public final String TAG = "ConstantlyReportExecutor";
    public ConcurrentLinkedQueue<ReportInfo> reportQueue = new ConcurrentLinkedQueue<>();
    public int index = 0;
    public boolean isReady = false;

    private class ReportInfo {
        public int index;
        public String mUrl;
        public List<ReportBean> mReportInfo = new ArrayList();
        public int retryTimes = 0;

        public ReportInfo(String str, ReportBean reportBean, int i) {
            this.mUrl = str;
            this.mReportInfo.add(reportBean);
            this.index = i;
        }

        public String toString() {
            try {
                return "ReportInfo :index：" + this.index + ",retryTimes:" + this.retryTimes + ",mReportEventCode:" + this.mReportInfo.get(0).getEventCode();
            } catch (Exception e2) {
                return e2.toString();
            }
        }
    }

    public ConstantlyReportExecutor() {
        createReportThread();
    }

    private void createReportThread() {
        this.reportThread = new JuThread(new Runnable() { // from class: com.ju.lib.datareport.ConstantlyReportExecutor.1
            @Override // java.lang.Runnable
            public void run() {
                while (!ConstantlyReportExecutor.this.reportQueue.isEmpty()) {
                    ReportInfo reportInfoPoll = ConstantlyReportExecutor.this.reportQueue.poll();
                    if (reportInfoPoll != null && ConstantlyReportExecutor.this.mPoster != null) {
                        try {
                            LogUtil.i("ConstantlyReportExecutor", reportInfoPoll);
                            ConstantlyReportExecutor.this.mPoster.post(reportInfoPoll.mUrl, reportInfoPoll.mReportInfo);
                            LogUtil.i("ConstantlyReportExecutor", "report success :" + reportInfoPoll);
                        } catch (Exception e2) {
                            if ((e2.getMessage() == null && (e2 instanceof SocketTimeoutException)) || reportInfoPoll == null) {
                                LogUtil.i("ConstantlyReportExecutor", a.a("report fail read timeout:", reportInfoPoll));
                            } else {
                                reportInfoPoll.retryTimes++;
                                LogUtil.i("ConstantlyReportExecutor", a.a("report fail :", reportInfoPoll));
                                if (reportInfoPoll.retryTimes <= 2) {
                                    ConstantlyReportExecutor.this.reportQueue.add(reportInfoPoll);
                                }
                            }
                        }
                    }
                }
            }
        }, "ConstantlyReportExecutor");
    }

    private Strategy.TypeStrategy getStrategy(int i, Strategy strategy) {
        if (i == 0) {
            return strategy.getExceptionStrategy();
        }
        if (i == 1) {
            return strategy.getDebugStrategy();
        }
        if (i == 2) {
            return strategy.getBusinessStrategy();
        }
        if (i != 3) {
            return null;
        }
        return strategy.getExceptionStrategy();
    }

    private void setStrategy(Strategy strategy) {
        if (strategy != null) {
            this.strategy = strategy;
            this.isReady = true;
            this.mPoster = new ReportPoster(strategy);
        }
    }

    private void startReport() {
        try {
            if ((this.reportThread == null || this.reportThread.isAlive()) && this.reportThread != null) {
                return;
            }
            createReportThread();
            this.reportThread.start();
            LogUtil.i("ConstantlyReportExecutor", "reportThread create finish !");
        } catch (Exception e2) {
            LogUtil.i("ConstantlyReportExecutor", e2, new Object[0]);
            if (e2 instanceof IllegalThreadStateException) {
                StringBuilder sbA = a.a("thread alive state:");
                sbA.append(this.reportThread.isAlive());
                sbA.append(",state:");
                sbA.append(this.reportThread.getState());
                LogUtil.i("ConstantlyReportExecutor", sbA.toString());
            }
        }
    }

    public boolean isConstantlyReport(ReportBean reportBean) {
        Strategy strategy = this.strategy;
        if (strategy == null) {
            return false;
        }
        if (!strategy.getTags().contains(reportBean.getEventCode())) {
            StringBuilder sbA = a.a("strategy not contains the eventcode:");
            sbA.append(reportBean.getEventCode());
            LogUtil.i("ConstantlyReportExecutor", sbA.toString());
            return false;
        }
        Strategy.TypeStrategy strategy2 = getStrategy(reportBean.getType(), this.strategy);
        boolean z = strategy2 != null && (strategy2.getDuration() < 1 || strategy2.getFileSize() <= 1);
        StringBuilder sbA2 = a.a("strategy is not realtime :");
        sbA2.append(reportBean.getEventCode());
        LogUtil.i("ConstantlyReportExecutor", sbA2.toString());
        return z;
    }

    public boolean isReady() {
        return this.isReady;
    }

    @Override // com.ju.lib.datareport.Reporter.StrategyListener
    public void onFinished(Strategy strategy) {
        setStrategy(strategy);
    }

    public void report(ReportBean reportBean) {
        Strategy.TypeStrategy strategy = getStrategy(reportBean.getType(), this.strategy);
        if (strategy == null) {
            return;
        }
        String url = strategy.getUrl();
        if (url != null && !url.isEmpty()) {
            StringBuilder sbA = a.a("ReportInfo = index:");
            sbA.append(this.index);
            sbA.append(", ");
            sbA.append(reportBean.getEventCode());
            LogUtil.i("ConstantlyReportExecutor", sbA.toString());
            this.reportQueue.add(new ReportInfo(url, reportBean, this.index));
            this.index++;
        }
        Thread thread = this.reportThread;
        if (thread != null && thread.isAlive()) {
            LogUtil.i("ConstantlyReportExecutor", "reportThread is alive so return!");
        } else {
            LogUtil.i("ConstantlyReportExecutor", "reportThread is not alive create new thread !");
            startReport();
        }
    }
}
