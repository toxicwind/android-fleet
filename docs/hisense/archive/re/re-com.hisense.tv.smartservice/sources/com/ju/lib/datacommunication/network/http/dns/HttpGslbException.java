package com.ju.lib.datacommunication.network.http.dns;

import b.a.a.a.a;
import com.ju.lib.datacommunication.annotation.JuMethodAnnotation;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class HttpGslbException extends Exception {
    public List<GslbException> gslbExceptions;
    public Status mStatus = Status.Invalid;
    public String parsedHost;
    public Exception prepareException;

    public static class GslbException extends Exception {
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

        @JuMethodAnnotation(description = "gslb解析失败时，返回码", parameters = "", returnDes = "int")
        public int getErrorCode() {
            return this.errorCode;
        }

        @JuMethodAnnotation(description = "gslb解析失败时，返回码", parameters = "", returnDes = "String")
        public String getErrorMessage() {
            return this.errorMessage;
        }

        @JuMethodAnnotation(description = "GSLB服务器的ip或域名", parameters = "", returnDes = "String")
        public String getGslbHost() {
            return this.gslbHost;
        }

        @JuMethodAnnotation(description = "待解析的域名", parameters = "", returnDes = "String")
        public String getHost() {
            return this.host;
        }

        @JuMethodAnnotation(description = "gslb解析时的异常，如果非空，则errorCode，errorMessage无效", parameters = "", returnDes = "Throwable")
        public Throwable getThrowable() {
            return this.throwable;
        }

        @JuMethodAnnotation(description = "gslb解析结果", parameters = "", returnDes = "boolean")
        public boolean isResult() {
            return this.result;
        }

        @Override // java.lang.Throwable
        public String toString() {
            StringBuilder sbA = a.a("GslbException{gslbHost='");
            a.a(sbA, this.gslbHost, '\'', ", host='");
            a.a(sbA, this.host, '\'', ", result=");
            sbA.append(this.result);
            sbA.append(", errorCode=");
            sbA.append(this.errorCode);
            sbA.append(", errorMessage='");
            a.a(sbA, this.errorMessage, '\'', ", throwable=");
            sbA.append(this.throwable);
            sbA.append("} ");
            sbA.append(super.toString());
            return sbA.toString();
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

        public final String des;
        public final int index;

        Status(int i, String str) {
            this.index = i;
            this.des = str;
        }

        public String getDes() {
            return this.des;
        }

        public int getValue() {
            return this.index;
        }

        @Override // java.lang.Enum
        public String toString() {
            StringBuilder sbA = a.a("Status{index=");
            sbA.append(this.index);
            sbA.append(", des='");
            sbA.append(this.des);
            sbA.append('\'');
            sbA.append('}');
            return sbA.toString();
        }
    }

    public HttpGslbException(String str, List<GslbException> list) {
        this.parsedHost = str;
        this.gslbExceptions = list;
    }

    @JuMethodAnnotation(description = "GSLB解析异常", parameters = "", returnDes = "List<GslbException>")
    public List<GslbException> getGslbExceptions() {
        return this.gslbExceptions;
    }

    @JuMethodAnnotation(description = "待解析的域名", parameters = "", returnDes = "String")
    public String getParsedHost() {
        return this.parsedHost;
    }

    @JuMethodAnnotation(description = "GSLB解析预处理异常", parameters = "", returnDes = "Exception")
    public Exception getPrepareException() {
        return this.prepareException;
    }

    @JuMethodAnnotation(description = "获取GSLB解析状态", parameters = "", returnDes = "HttpGslbException.Status")
    public Status getStatus() {
        return this.mStatus;
    }

    @JuMethodAnnotation(description = "设置GSLB解析状态", parameters = "HttpGslbException.Status", returnDes = "HttpGslbException")
    public HttpGslbException setStatus(Status status) {
        this.mStatus = status;
        return this;
    }

    @Override // java.lang.Throwable
    public String toString() {
        StringBuilder sbA = a.a("HttpGslbException{parsedHost='");
        a.a(sbA, this.parsedHost, '\'', ", prepareException=");
        sbA.append(this.prepareException);
        sbA.append(", gslbExceptions=");
        sbA.append(this.gslbExceptions);
        sbA.append(", mStatus=");
        sbA.append(this.mStatus);
        sbA.append("} ");
        sbA.append(super.toString());
        return sbA.toString();
    }

    public HttpGslbException(String str, Exception exc) {
        this.parsedHost = str;
        this.prepareException = exc;
    }
}
