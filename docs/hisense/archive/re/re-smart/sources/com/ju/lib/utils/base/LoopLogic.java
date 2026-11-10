package com.ju.lib.utils.base;

import com.ju.lib.utils.log.LogUtil;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class LoopLogic<T> {
    public static final long DEFAULT_OVER_TIME_DURATION = 15000;
    public static final String TAG = "LoopLogic";
    public CountParam mCountParam;
    public List<T> mList;
    public long mOverTimeDuration;

    private static class CountParam {
        public int count;
        public long stamp;

        public CountParam(int i, long j) {
            this.count = i;
            this.stamp = j;
        }
    }

    public interface IMethod<T> {
        void run(T t);
    }

    public LoopLogic(List<T> list) {
        this.mOverTimeDuration = DEFAULT_OVER_TIME_DURATION;
        this.mList = list;
        this.mCountParam = new CountParam(this.mList.size(), System.currentTimeMillis());
    }

    public void process(IMethod iMethod) {
        CountParam countParam;
        long jCurrentTimeMillis = System.currentTimeMillis();
        Iterator<T> it = this.mList.iterator();
        while (it.hasNext()) {
            iMethod.run(it.next());
        }
        do {
            long jCurrentTimeMillis2 = System.currentTimeMillis();
            countParam = this.mCountParam;
            if (jCurrentTimeMillis2 - countParam.stamp > this.mOverTimeDuration) {
                LogUtil.w(TAG, "process(), overtime, counts : ", Integer.valueOf(countParam.count));
                break;
            }
        } while (countParam.count != 0);
        LogUtil.i(TAG, "process(), duration : ", Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis));
    }

    public void reduceCount() {
        synchronized (this.mCountParam) {
            if (this.mCountParam.count != 0) {
                this.mCountParam.count--;
            }
        }
    }

    public LoopLogic(List<T> list, long j) {
        this.mOverTimeDuration = DEFAULT_OVER_TIME_DURATION;
        this.mList = list;
        this.mOverTimeDuration = j < 0 ? this.mOverTimeDuration : j;
        this.mCountParam = new CountParam(this.mList.size(), System.currentTimeMillis());
    }
}
