package com.ju.lib.datareport;

import android.text.TextUtils;
import b.a.a.a.a;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public class Strategy {
    public TypeStrategy mBusiness;
    public boolean mCompress;
    public TypeStrategy mDebug;
    public boolean mEncrypt;
    public TypeStrategy mException;
    public Map<String, String> mExt;
    public int mNetType;
    public long mPolicySeq;
    public int mResultCode = Integer.MIN_VALUE;
    public Set<String> mTags;

    static class CloseTypeStrategy {
        public static final TypeStrategy INSTANCE = new TypeStrategy(false, "", 0, 0);
    }

    static final class TypeStrategy {
        public long mDuration;
        public int mFileSize;
        public boolean mStatus;
        public String mUrl;

        public TypeStrategy(boolean z, String str, long j, int i) {
            this.mStatus = z && !TextUtils.isEmpty(str);
            this.mUrl = str;
            this.mDuration = j;
            this.mFileSize = i;
        }

        public long getDuration() {
            return this.mDuration;
        }

        public int getFileSize() {
            return this.mFileSize;
        }

        public String getUrl() {
            return this.mUrl;
        }

        public boolean isStatus() {
            return this.mStatus;
        }

        public void setDuration(long j) {
            this.mDuration = j;
        }

        public void setFileSize(int i) {
            this.mFileSize = i;
        }

        public void setStatus(boolean z) {
            this.mStatus = z;
        }

        public void setUrl(String str) {
            this.mUrl = str;
        }

        public String toString() {
            StringBuilder sbA = a.a("TypeStrategy: Status = ");
            sbA.append(this.mStatus);
            sbA.append(", Url = ");
            sbA.append(this.mUrl);
            sbA.append(", Duration = ");
            sbA.append(this.mDuration);
            sbA.append(", FileSize = ");
            sbA.append(this.mFileSize);
            return sbA.toString();
        }
    }

    public TypeStrategy getBusinessStrategy() {
        return this.mBusiness;
    }

    public TypeStrategy getDebugStrategy() {
        return this.mDebug;
    }

    public TypeStrategy getExceptionStrategy() {
        return this.mException;
    }

    public Map<String, String> getExt() {
        return this.mExt;
    }

    public int getNetType() {
        return this.mNetType;
    }

    public long getPolicySeq() {
        return this.mPolicySeq;
    }

    public int getResultCode() {
        return this.mResultCode;
    }

    public Set<String> getTags() {
        return this.mTags;
    }

    public boolean isCompress() {
        return this.mCompress;
    }

    public boolean isEncrypt() {
        return this.mEncrypt;
    }

    public void setBusinessStrategy(TypeStrategy typeStrategy) {
        this.mBusiness = typeStrategy;
    }

    public void setCompress(boolean z) {
        this.mCompress = z;
    }

    public void setDebugStrategy(TypeStrategy typeStrategy) {
        this.mDebug = typeStrategy;
    }

    public void setEncrypt(boolean z) {
        this.mEncrypt = z;
    }

    public void setExceptionStrategy(TypeStrategy typeStrategy) {
        this.mException = typeStrategy;
    }

    public void setExt(Map<String, String> map) {
        this.mExt = map;
    }

    public void setNetType(int i) {
        this.mNetType = i;
    }

    public void setPolicySeq(long j) {
        this.mPolicySeq = j;
    }

    public void setResultCode(int i) {
        this.mResultCode = i;
    }

    public void setTags(Set<String> set) {
        this.mTags = set;
    }

    public String toString() {
        StringBuilder sbA = a.a("Strategy: ResultCode = ");
        sbA.append(this.mResultCode);
        sbA.append(", Encrypt = ");
        sbA.append(this.mEncrypt);
        sbA.append(", Compress = ");
        sbA.append(this.mCompress);
        sbA.append(", PolicySeq = ");
        sbA.append(this.mPolicySeq);
        sbA.append(", NetType = ");
        sbA.append(this.mNetType);
        sbA.append("\nBusiness = ");
        sbA.append(this.mBusiness);
        sbA.append("\nDebug = ");
        sbA.append(this.mDebug);
        sbA.append("\nException = ");
        sbA.append(this.mException);
        sbA.append("\nTags = ");
        sbA.append(this.mTags);
        sbA.append("\nExt = ");
        sbA.append(this.mExt);
        return sbA.toString();
    }
}
