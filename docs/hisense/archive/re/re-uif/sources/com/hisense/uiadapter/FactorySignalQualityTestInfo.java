package com.hisense.uiadapter;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class FactorySignalQualityTestInfo {
    private List<InfosBean> infos;

    public List<InfosBean> getInfos() {
        return this.infos;
    }

    public static class InfosBean {
        private double bitErrorRate;
        private double signalLevel;
        private double signalQuality;
        private boolean tsPortResult;
        private boolean tunerResult;

        public double getSignalQuality() {
            return this.signalQuality;
        }

        public double getBitErrorRate() {
            return this.bitErrorRate;
        }

        public double getSignalLevel() {
            return this.signalLevel;
        }

        public boolean isTunerResult() {
            return this.tunerResult;
        }

        public boolean isTsPortResult() {
            return this.tsPortResult;
        }
    }
}
