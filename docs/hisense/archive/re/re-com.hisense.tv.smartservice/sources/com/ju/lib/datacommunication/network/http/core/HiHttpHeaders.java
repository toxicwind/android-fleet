package com.ju.lib.datacommunication.network.http.core;

import b.a.a.a.a;
import com.google.firebase.installations.Utils;
import com.ju.lib.datacommunication.network.http.utils.HttpLog;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public class HiHttpHeaders {
    public static final String TAG = "HTTP2.Stack";
    public final List<NameValuePair> mHeaders;

    private static class NameValuePair {
        public final String mName;
        public final String mValue;

        public NameValuePair(String str, String str2) {
            this.mName = str;
            this.mValue = str2;
        }

        private boolean stringEquals(String str, String str2) {
            if (str == null) {
                return str2 == null;
            }
            return str.equals(str2);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof NameValuePair)) {
                return false;
            }
            NameValuePair nameValuePair = (NameValuePair) obj;
            return stringEquals(nameValuePair.mName, this.mName) && stringEquals(nameValuePair.mValue, this.mValue);
        }
    }

    public HiHttpHeaders(Builder builder) {
        this.mHeaders = new ArrayList(builder.mHeaders);
    }

    public boolean equals(Object obj) {
        if (obj instanceof HiHttpHeaders) {
            return this.mHeaders.equals(((HiHttpHeaders) obj).mHeaders);
        }
        return false;
    }

    public String get(String str) {
        for (NameValuePair nameValuePair : this.mHeaders) {
            if (nameValuePair.mName.equalsIgnoreCase(str)) {
                return nameValuePair.mValue;
            }
        }
        return null;
    }

    public String getName(int i) {
        return this.mHeaders.get(i).mName;
    }

    public Set<String> getNames() {
        HashSet hashSet = new HashSet(this.mHeaders.size());
        Iterator<NameValuePair> it = this.mHeaders.iterator();
        while (it.hasNext()) {
            hashSet.add(it.next().mName);
        }
        return hashSet;
    }

    public String getValue(int i) {
        return this.mHeaders.get(i).mValue;
    }

    public List<String> getValues(String str) {
        ArrayList arrayList = null;
        for (NameValuePair nameValuePair : this.mHeaders) {
            if (nameValuePair.mName.equalsIgnoreCase(str)) {
                if (arrayList == null) {
                    arrayList = new ArrayList(2);
                }
                arrayList.add(nameValuePair.mValue);
            }
        }
        return arrayList != null ? Collections.unmodifiableList(arrayList) : Collections.emptyList();
    }

    public int hashCode() {
        return this.mHeaders.hashCode();
    }

    public int size() {
        return this.mHeaders.size();
    }

    public static final class Builder {
        public final List<NameValuePair> mHeaders = new ArrayList();

        private boolean checkNameAndValue(String str, String str2) {
            if (str == null) {
                HttpLog.d("HTTP2.Stack", "checkNameAndValue name == null");
                return false;
            }
            if (str.isEmpty()) {
                HttpLog.d("HTTP2.Stack", "checkNameAndValue name is empty");
                return false;
            }
            int length = str.length();
            for (int i = 0; i < length; i++) {
                char cCharAt = str.charAt(i);
                if (cCharAt <= ' ' || cCharAt >= 127) {
                    StringBuilder sbA = a.a("checkNameAndValue ");
                    sbA.append(String.format("Unexpected char %#04x at %d in header name: %s", Integer.valueOf(cCharAt), Integer.valueOf(i), str));
                    HttpLog.d("HTTP2.Stack", sbA.toString());
                    return false;
                }
            }
            if (str2 == null) {
                HttpLog.d("HTTP2.Stack", "checkNameAndValue value == null");
                return false;
            }
            int length2 = str2.length();
            for (int i2 = 0; i2 < length2; i2++) {
                char cCharAt2 = str2.charAt(i2);
                if ((cCharAt2 <= 31 && cCharAt2 != '\t') || cCharAt2 >= 127) {
                    StringBuilder sbA2 = a.a("checkNameAndValue ");
                    sbA2.append(String.format("Unexpected char %#04x at %d in %s value: %s", Integer.valueOf(cCharAt2), Integer.valueOf(i2), str, str2));
                    HttpLog.d("HTTP2.Stack", sbA2.toString());
                    return false;
                }
            }
            return true;
        }

        public Builder add(String str) {
            int iIndexOf = str.indexOf(Utils.APP_ID_IDENTIFICATION_SUBSTRING);
            if (iIndexOf != -1) {
                return add(str.substring(0, iIndexOf).trim(), str.substring(iIndexOf + 1));
            }
            throw new IllegalArgumentException(a.a("Unexpected header: ", str));
        }

        public HiHttpHeaders build() {
            return new HiHttpHeaders(this, null);
        }

        public Builder removeAll(String str) {
            Iterator<NameValuePair> it = this.mHeaders.iterator();
            while (it.hasNext()) {
                if (it.next().mName.equalsIgnoreCase(str)) {
                    it.remove();
                }
            }
            return this;
        }

        public Builder set(String str, String str2) {
            if (checkNameAndValue(str, str2)) {
                removeAll(str);
                this.mHeaders.add(new NameValuePair(str, str2));
            }
            return this;
        }

        public Builder add(String str, String str2) {
            if (checkNameAndValue(str, str2)) {
                this.mHeaders.add(new NameValuePair(str, str2));
            }
            return this;
        }
    }

    public /* synthetic */ HiHttpHeaders(Builder builder, AnonymousClass1 anonymousClass1) {
        this.mHeaders = new ArrayList(builder.mHeaders);
    }
}
