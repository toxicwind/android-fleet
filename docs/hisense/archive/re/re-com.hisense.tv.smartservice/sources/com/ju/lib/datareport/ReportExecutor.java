package com.ju.lib.datareport;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import b.a.a.a.a;
import com.ju.lib.datareport.dynamic.DynamicConfigure;
import com.ju.lib.utils.log.LogUtil;
import e.b.d;
import java.net.SocketTimeoutException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes2.dex */
public class ReportExecutor {
    public static final int DEFAULT_MAX_COUNT = 10;
    public static final String D_A_K = "CT4M92FPB76HLK3R";
    public static final String D_A_V = "0000000000000005";
    public static final int INIT = 0;
    public static final Object LOCK = new Object();
    public static final long MAX_DELAY = 1800000;
    public static final int REQUEST_STRATEGY = 2;
    public static final int SAVE = 1;
    public static final int STRATEGY_DONE = 3;
    public static final String TAG = "ReportExecutor";
    public static final int UPLOAD = 4;
    public static final int UPLOAD_DONE = 5;
    public Context mContext;
    public String mDeviceId;
    public Executor mExecutor;
    public Handler mHandler;
    public String mKey;
    public ReportPoster mPoster;
    public Strategy mStrategy;
    public Reporter.StrategyListener mStrategyListener;
    public final String mStrategyUrl;
    public String mVersion;
    public int packageCount = 0;
    public Handler.Callback mCallback = new Handler.Callback() { // from class: com.ju.lib.datareport.ReportExecutor.1
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i = message.what;
            if (i == 0) {
                ReportExecutor.access$000(ReportExecutor.this);
                return true;
            }
            if (i == 1) {
                ReportExecutor.access$100(ReportExecutor.this, message);
                return true;
            }
            if (i == 2) {
                if (ReportExecutor.this.mStrategyStatus == Status.UNDONE) {
                    ReportExecutor.this.requestStrategy();
                }
                return true;
            }
            if (i == 3) {
                ReportExecutor.access$400(ReportExecutor.this, message);
                return true;
            }
            if (i != 4) {
                if (i != 5) {
                    return false;
                }
                ReportExecutor.access$700(ReportExecutor.this, message);
                return true;
            }
            Object obj = message.obj;
            if (!(obj instanceof TypeInfo)) {
                LogUtil.e(ReportExecutor.TAG, "UPLOAD obj not TypeInfo！");
                return true;
            }
            LogUtil.i(ReportExecutor.TAG, "UPLOAD handler step0");
            ReportExecutor.this.strategy((TypeInfo) obj);
            return false;
        }
    };
    public TypeInfo[] mTypes = {new TypeInfo(0), new TypeInfo(1), new TypeInfo(2)};
    public Status mStrategyStatus = Status.UNDONE;

    /* JADX INFO: renamed from: com.ju.lib.datareport.ReportExecutor$3, reason: invalid class name */
    public class AnonymousClass3 implements Runnable {
        public final /* synthetic */ TypeInfo val$type;

        public AnonymousClass3(TypeInfo typeInfo) {
            this.val$type = typeInfo;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.val$type.setReportTime();
                if (ReportExecutor.this.isDynamic()) {
                    ReportExecutor.this.mPoster.post(this.val$type, this.val$type.mBuffer);
                } else {
                    ReportExecutor.this.mPoster.post(this.val$type.mStrategy.getUrl(), this.val$type.mBuffer);
                }
                ReportExecutor.this.mHandler.obtainMessage(5, 1, 0, this.val$type).sendToTarget();
            } catch (Exception e2) {
                e2.printStackTrace();
                if (e2.getMessage() != null || !(e2 instanceof SocketTimeoutException)) {
                    ReportExecutor.this.mHandler.obtainMessage(5, 0, 0, this.val$type).sendToTarget();
                } else {
                    LogUtil.i(ReportExecutor.TAG, "UPLOAD: finish is read time out");
                    ReportExecutor.this.mHandler.obtainMessage(5, 1, 0, this.val$type).sendToTarget();
                }
            }
        }
    }

    /* JADX INFO: renamed from: com.ju.lib.datareport.ReportExecutor$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        public static final /* synthetic */ int[] $SwitchMap$com$ju$lib$datareport$ReportExecutor$Status = new int[Status.values().length];

        static {
            try {
                $SwitchMap$com$ju$lib$datareport$ReportExecutor$Status[Status.UNDONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$ju$lib$datareport$ReportExecutor$Status[Status.ERROR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$ju$lib$datareport$ReportExecutor$Status[Status.DONE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private enum Status {
        UNDONE,
        DOING,
        DONE,
        ERROR
    }

    public static class TypeInfo {
        public List<ReportBean> mBuffer;
        public long mCount = 0;
        public Status mStatus = Status.UNDONE;
        public Strategy.TypeStrategy mStrategy;
        public final int mType;

        public TypeInfo(int i) {
            this.mType = i;
        }

        public String getEventCodes() {
            StringBuffer stringBuffer = new StringBuffer();
            List<ReportBean> list = this.mBuffer;
            if (list == null) {
                stringBuffer.append("buffer isnull");
            } else {
                for (ReportBean reportBean : list) {
                    if (reportBean != null) {
                        try {
                            stringBuffer.append("{ id=");
                            stringBuffer.append(reportBean.getId());
                            stringBuffer.append(",EventCode=");
                            stringBuffer.append(reportBean.getEventCode());
                            stringBuffer.append("},");
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                }
            }
            return stringBuffer.toString();
        }

        public String getReportAppKey() {
            List<ReportBean> list = this.mBuffer;
            return (list == null || list.isEmpty() || this.mBuffer.get(0) == null) ? "" : this.mBuffer.get(0).getAppKey();
        }

        public void setReportTime() {
            List<ReportBean> list = this.mBuffer;
            if (list == null || this.mType != 0) {
                return;
            }
            for (ReportBean reportBean : list) {
                try {
                    d dVar = new d(reportBean.getData());
                    if (dVar.f2163b.containsKey("extraMessage")) {
                        String strA = dVar.a("extraMessage", "");
                        StringBuilder sb = new StringBuilder();
                        if (!strA.contains("reportTime:")) {
                            sb.append(strA);
                            sb.append(",{");
                            sb.append("reportTime:");
                            sb.append("" + System.currentTimeMillis());
                            sb.append("}");
                            dVar.a("extraMessage", (Object) sb.toString());
                            reportBean.setData(dVar.toString());
                        }
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }

        public String toString() {
            StringBuilder sbA = a.a("TypeInfo: mType = ");
            sbA.append(this.mType);
            sbA.append(", mStatus = ");
            sbA.append(this.mStatus);
            sbA.append(", mCount = ");
            sbA.append(this.mCount);
            return sbA.toString();
        }
    }

    public ReportExecutor(Context context, Looper looper, Executor executor, String str, String str2, String str3, Reporter.StrategyListener strategyListener, String str4) {
        this.mContext = context.getApplicationContext();
        this.mDeviceId = str;
        this.mKey = str2;
        this.mVersion = str3;
        this.mExecutor = executor;
        this.mStrategyListener = strategyListener;
        this.mHandler = new Handler(looper, this.mCallback);
        this.mHandler.sendEmptyMessage(0);
        this.mStrategyUrl = str4;
    }

    public static /* synthetic */ boolean access$000(ReportExecutor reportExecutor) {
        reportExecutor.onHandleMessageInit();
        return true;
    }

    public static /* synthetic */ boolean access$100(ReportExecutor reportExecutor, Message message) {
        reportExecutor.onHandleMessageSave(message);
        return true;
    }

    public static /* synthetic */ boolean access$400(ReportExecutor reportExecutor, Message message) {
        reportExecutor.onHandleMessageStrategyDone(message);
        return true;
    }

    public static /* synthetic */ boolean access$700(ReportExecutor reportExecutor, Message message) {
        reportExecutor.onHandleMessageUploadDone(message);
        return true;
    }

    private boolean checkTypeInfoIsOk(TypeInfo typeInfo) {
        if (typeInfo == null) {
            LogUtil.i(TAG, "upload type == null");
            return true;
        }
        LogUtil.d(TAG, "upload step1 type = ", Integer.valueOf(typeInfo.mType), ", status = ", typeInfo.mStatus, ", count = ", Long.valueOf(typeInfo.mCount), ",buffer beans:", typeInfo.getEventCodes());
        if (typeInfo.mStatus == Status.DOING) {
            LogUtil.i(TAG, "Status uninit! ");
            return true;
        }
        if (typeInfo.mCount <= 0) {
            LogUtil.i(TAG, "Count uninit! ");
            return true;
        }
        if (this.mPoster == null) {
            LogUtil.i(TAG, "Poster uninit! ");
            return true;
        }
        Strategy.TypeStrategy typeStrategy = typeInfo.mStrategy;
        if (typeStrategy != null && typeStrategy.isStatus()) {
            return false;
        }
        LogUtil.i(TAG, "Strategy uninit! type.mStrategy = ", typeInfo.mStrategy);
        return true;
    }

    private DynamicConfigure getDynamicConfigure() {
        if (ReportManager.INSTANCE != null) {
            return ReportManager.INSTANCE.getmDynamicConfigure();
        }
        printException("ReportManager.getInstance() is null");
        return null;
    }

    private int getPackageCount(TypeInfo typeInfo) {
        int fileSize = typeInfo.mStrategy.getFileSize();
        if (fileSize > 0) {
            return fileSize;
        }
        return 10;
    }

    private TypeInfo getTypeInfo(int i) {
        int i2 = 0;
        while (true) {
            TypeInfo[] typeInfoArr = this.mTypes;
            if (i2 >= typeInfoArr.length) {
                return null;
            }
            if (typeInfoArr[i2].mType == i) {
                return typeInfoArr[i2];
            }
            i2++;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isDynamic() {
        return getDynamicConfigure() != null;
    }

    private boolean onDoUploadOne(TypeInfo typeInfo) {
        int fileSize = typeInfo.mStrategy.getFileSize();
        LogUtil.i(TAG, "fileSize = ", Integer.valueOf(fileSize), ", type.mCount = ", Long.valueOf(typeInfo.mCount));
        this.packageCount = getPackageCount(typeInfo);
        if (fileSize > typeInfo.mCount) {
            List<ReportBean> list = typeInfo.mBuffer;
            if (list == null || list.isEmpty()) {
                try {
                    typeInfo.mBuffer = ReportDatabase.getInstance(this.mContext).query(this.mKey, typeInfo.mType, this.packageCount);
                } catch (IllegalArgumentException e2) {
                    typeInfo.mBuffer = ReportDatabase.getInstance(this.mContext).query(this.mKey, typeInfo.mType);
                    e2.printStackTrace();
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }
            List<ReportBean> list2 = typeInfo.mBuffer;
            if (list2 == null || list2.isEmpty()) {
                LogUtil.i(TAG, "Buffer isEmpty! ");
                return true;
            }
            long duration = ((typeInfo.mStrategy.getDuration() + typeInfo.mBuffer.get(0).getTime()) * 1000) - System.currentTimeMillis();
            LogUtil.d(TAG, "delay: ", Long.valueOf(duration), ",Duration:", Long.valueOf(typeInfo.mStrategy.getDuration()), ",buffer beans:", typeInfo.getEventCodes());
            if (duration > 0) {
                long j = MAX_DELAY;
                if (duration <= MAX_DELAY) {
                    j = duration;
                }
                this.mHandler.removeMessages(4, typeInfo);
                this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(4, typeInfo), j);
                return true;
            }
        }
        return false;
    }

    private boolean onDoUploadTwo(TypeInfo typeInfo) {
        this.mHandler.removeMessages(4, typeInfo);
        String str = TAG;
        Object[] objArr = new Object[2];
        objArr[0] = "type.mBuffer: ";
        List<ReportBean> list = typeInfo.mBuffer;
        objArr[1] = Integer.valueOf(list == null ? 0 : list.size());
        LogUtil.d(str, objArr);
        List<ReportBean> list2 = typeInfo.mBuffer;
        if (list2 == null || (list2.size() < this.packageCount && typeInfo.mBuffer.size() != typeInfo.mCount)) {
            try {
                typeInfo.mBuffer = ReportDatabase.getInstance(this.mContext).query(this.mKey, typeInfo.mType, this.packageCount);
            } catch (IllegalArgumentException e2) {
                typeInfo.mBuffer = ReportDatabase.getInstance(this.mContext).query(this.mKey, typeInfo.mType);
                e2.printStackTrace();
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
        List<ReportBean> list3 = typeInfo.mBuffer;
        if (list3 == null || list3.isEmpty()) {
            LogUtil.i(TAG, "Buffer isEmpty again! ");
            return true;
        }
        LogUtil.i(TAG, "UPLOAD: ", Integer.valueOf(typeInfo.mType), ", ", Integer.valueOf(typeInfo.mBuffer.size()));
        typeInfo.mStatus = Status.DOING;
        return false;
    }

    private void onExecutorDoExecute(TypeInfo typeInfo) {
        this.mExecutor.execute(new AnonymousClass3(typeInfo));
    }

    private boolean onHandleMessageInit() {
        for (TypeInfo typeInfo : this.mTypes) {
            long count = ReportDatabase.getInstance(this.mContext).getCount(this.mKey, typeInfo.mType);
            if (count > 0) {
                LogUtil.i(TAG, "type = ", typeInfo, ", count = ", Long.valueOf(count));
                requestStrategy();
                return true;
            }
        }
        return true;
    }

    private boolean onHandleMessageSave(Message message) {
        Strategy.TypeStrategy typeStrategy;
        Object obj = message.obj;
        if (!(obj instanceof ReportBean)) {
            LogUtil.e(TAG, "obj not ReportBean！");
            return true;
        }
        ReportBean reportBean = (ReportBean) obj;
        Strategy strategy = this.mStrategy;
        if (strategy != null && !strategy.getTags().contains(reportBean.getEventCode())) {
            LogUtil.i(TAG, "filter: ", reportBean.getEventCode());
            return true;
        }
        TypeInfo typeInfo = getTypeInfo(reportBean.getType());
        if (typeInfo != null && (typeStrategy = typeInfo.mStrategy) != null && !typeStrategy.isStatus()) {
            LogUtil.i(TAG, "save bean.getEventCode(): ", reportBean.getEventCode());
            return true;
        }
        long jInsert = ReportDatabase.getInstance(this.mContext).insert(reportBean);
        LogUtil.i(TAG, "save: ", Long.valueOf(jInsert), ", ", typeInfo, ",eventCode:", reportBean.getEventCode());
        if (typeInfo != null && jInsert > 0) {
            typeInfo.mCount++;
            strategy(typeInfo);
        }
        return true;
    }

    private boolean onHandleMessageStrategyDone(Message message) {
        if (message.obj == null) {
            LogUtil.i(TAG, "STRATEGY_DONE: obj is null");
            synchronized (LOCK) {
                this.mStrategyStatus = Status.ERROR;
                LOCK.notifyAll();
            }
        } else {
            synchronized (LOCK) {
                this.mStrategy = (Strategy) message.obj;
                this.mStrategyStatus = Status.DONE;
                LOCK.notifyAll();
            }
            this.mStrategyListener.onFinished(this.mStrategy);
            ReportDatabase.getInstance(this.mContext).filter(this.mKey, this.mStrategy.getTags());
            this.mPoster = new ReportPoster(this.mStrategy);
            for (TypeInfo typeInfo : this.mTypes) {
                typeInfo.mStrategy = getStrategy(typeInfo.mType, this.mStrategy);
                Strategy.TypeStrategy typeStrategy = typeInfo.mStrategy;
                if (typeStrategy == null || !typeStrategy.isStatus()) {
                    ReportDatabase.getInstance(this.mContext).filter(this.mKey, typeInfo.mType);
                } else {
                    typeInfo.mCount = ReportDatabase.getInstance(this.mContext).getCount(this.mKey, typeInfo.mType);
                    LogUtil.i(TAG, this.mKey, ", ", Integer.valueOf(typeInfo.mType), " init count: ", Long.valueOf(typeInfo.mCount));
                    upload(typeInfo);
                }
            }
        }
        return true;
    }

    private boolean onHandleMessageUploadDone(Message message) {
        Object obj = message.obj;
        if (!(obj instanceof TypeInfo)) {
            LogUtil.e(TAG, "UPLOAD_DONE obj not TypeInfo！");
            return true;
        }
        TypeInfo typeInfo = (TypeInfo) obj;
        LogUtil.i(TAG, "UPLOAD_DONE: ", Integer.valueOf(message.arg1));
        if (message.arg1 != 0) {
            LogUtil.d(TAG, "upload success count = ", Long.valueOf(typeInfo.mCount), ", buffer = ", Integer.valueOf(typeInfo.mBuffer.size()), ",key=", typeInfo.getReportAppKey(), ",eventCodes=", typeInfo.getEventCodes());
            typeInfo.mStatus = Status.DONE;
            int iDelete = ReportDatabase.getInstance(this.mContext).delete(typeInfo.mBuffer);
            LogUtil.i(TAG, "delete: ", Integer.valueOf(iDelete));
            if (iDelete > 0) {
                typeInfo.mCount -= (long) iDelete;
                if (typeInfo.mCount < 0) {
                    typeInfo.mCount = 0L;
                }
                typeInfo.mBuffer = null;
                upload(typeInfo);
            } else {
                typeInfo.mStatus = Status.UNDONE;
                LogUtil.i(TAG, "delete fail mBuffer: ", typeInfo.mBuffer);
                typeInfo.mBuffer = null;
            }
        } else if (ReportDatabase.getInstance(this.mContext).getCount(typeInfo.mType) > 100) {
            typeInfo.mStatus = Status.DONE;
            int iDelete2 = ReportDatabase.getInstance(this.mContext).delete(typeInfo.mBuffer);
            if (iDelete2 > 0) {
                typeInfo.mCount -= (long) iDelete2;
                if (typeInfo.mCount < 0) {
                    typeInfo.mCount = 0L;
                }
                typeInfo.mBuffer = null;
                upload(typeInfo);
            } else {
                typeInfo.mStatus = Status.UNDONE;
                typeInfo.mBuffer = null;
            }
        } else {
            typeInfo.mStatus = Status.UNDONE;
            typeInfo.mBuffer = null;
        }
        return true;
    }

    private void printException(String str) {
        new Exception(str).printStackTrace();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestStrategy() {
        LogUtil.i(TAG, "requestStrategy");
        this.mStrategyStatus = Status.DOING;
        this.mExecutor.execute(new Runnable() { // from class: com.ju.lib.datareport.ReportExecutor.2
            @Override // java.lang.Runnable
            public void run() {
                ReportExecutor.this.mHandler.obtainMessage(3, new StrategyHelper(ReportExecutor.this.mContext, ReportExecutor.this.mKey, ReportExecutor.this.mDeviceId, ReportExecutor.this.mVersion, ReportExecutor.this.mStrategyUrl).getStrategy()).sendToTarget();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void strategy(TypeInfo typeInfo) {
        LogUtil.i(TAG, "mStrategyStatus = ", this.mStrategyStatus);
        int iOrdinal = this.mStrategyStatus.ordinal();
        if (iOrdinal != 0) {
            if (iOrdinal == 2) {
                upload(typeInfo);
                return;
            } else if (iOrdinal != 3) {
                return;
            }
        }
        requestStrategy();
    }

    private void upload(TypeInfo typeInfo) {
        if (checkTypeInfoIsOk(typeInfo) || onDoUploadOne(typeInfo) || onDoUploadTwo(typeInfo)) {
            return;
        }
        this.mExecutor.execute(new AnonymousClass3(typeInfo));
    }

    public Strategy getStrategy() {
        Strategy strategy;
        synchronized (LOCK) {
            if (this.mStrategy == null) {
                this.mHandler.sendEmptyMessage(2);
                while (true) {
                    if (this.mStrategyStatus != Status.UNDONE && this.mStrategyStatus != Status.DOING) {
                        break;
                    }
                    try {
                        LOCK.wait();
                    } catch (InterruptedException unused) {
                    }
                }
            }
            strategy = this.mStrategy;
        }
        return strategy;
    }

    public void report(ReportBean reportBean) {
        this.mHandler.obtainMessage(1, 0, 0, reportBean).sendToTarget();
    }

    public void updateStrategy() {
        LogUtil.i(TAG, "updateStrategy start ");
        synchronized (LOCK) {
            this.mStrategyStatus = Status.UNDONE;
            LOCK.notifyAll();
        }
    }

    public void report(Collection<ReportBean> collection) {
        Iterator<ReportBean> it = collection.iterator();
        while (it.hasNext()) {
            this.mHandler.obtainMessage(1, 0, 0, it.next()).sendToTarget();
        }
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
}
