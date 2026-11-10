package com.ju.lib.datacommunication.network.http.dns;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class HttpGslbException extends Exception {
    private List gslbExceptions;
    private Status mStatus = Status.Invalid;
    private String parsedHost;
    private Exception prepareException;

    public class GslbException extends Exception {
        public int errorCode;
        public String errorMessage;
        public String gslbHost;
        public String host;
        public boolean result;
        public Throwable throwable;

        public GslbException(String str, String str2, boolean z, int i, String str3, Throwable th) {
            this.errorCode = -1;
            this.gslbHost = str;
            this.host = str2;
            this.result = z;
            this.errorCode = i;
            this.errorMessage = str3;
            this.throwable = th;
        }

        @Override // java.lang.Throwable
        public String toString() {
            return "GslbException{gslbHost='" + this.gslbHost + "', host='" + this.host + "', result=" + this.result + ", errorCode=" + this.errorCode + ", errorMessage='" + this.errorMessage + "', throwable=" + this.throwable + "} " + super.toString();
        }
    }

    public enum Status {
        Invalid(-1, "Invalid"),
        Success(0, "Success"),
        NetworkNotConnected(1, "NetworkNotConnected"),
        Frozen(2, "Frozen"),
        ParsedListInvalid(3, "ParsedListInvalid"),
        ParsedListUnsupported(4, "ParsedListUnsupported"),
        RequestParamError(5, "RequestParamError"),
        Other(6, "Other");

        private final String des;
        private final int index;

        Status(int i2, String str) {
            this.index = i2;
            this.des = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return "Status{index=" + this.index + ", des='" + this.des + "'}";
        }
    }

    public HttpGslbException(String str, Exception exc) {
        this.parsedHost = str;
        this.prepareException = exc;
    }

    public HttpGslbException(String str, List list) {
        this.parsedHost = str;
        this.gslbExceptions = list;
    }

    public Status a() {
        return this.mStatus;
    }

    public HttpGslbException b(Status status) {
        this.mStatus = status;
        return this;
    }

    @Override // java.lang.Throwable
    public String toString() {
        return "HttpGslbException{parsedHost='" + this.parsedHost + "', prepareException=" + this.prepareException + ", gslbExceptions=" + this.gslbExceptions + ", mStatus=" + this.mStatus + "} " + super.toString();
    }
}
