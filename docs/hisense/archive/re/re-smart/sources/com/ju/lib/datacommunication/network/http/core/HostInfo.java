package com.ju.lib.datacommunication.network.http.core;

import b.a.a.a.a;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public class HostInfo {
    public long mClientTimeStamp;
    public String mHostname;
    public List<String> mIps;
    public List<String> mProxyIps;
    public long mTtl;

    public static final class Builder {
        public long mClientTimeStamp;
        public String mHostname;
        public Set<String> mIps;
        public Set<String> mProxyIps;
        public long mTtl;

        public Builder addIp(String str) {
            this.mIps.add(str);
            return this;
        }

        public Builder addIps(Collection<String> collection) {
            this.mIps.addAll(collection);
            return this;
        }

        public Builder addProxyIp(String str) {
            this.mProxyIps.add(str);
            return this;
        }

        public Builder addProxyIps(Collection<String> collection) {
            this.mProxyIps.addAll(collection);
            return this;
        }

        public HostInfo build() {
            return new HostInfo(this);
        }

        public Builder setClientTimeStamp(long j) {
            this.mClientTimeStamp = j;
            return this;
        }

        public Builder setHostName(String str) {
            this.mHostname = str;
            return this;
        }

        public Builder setTtl(long j) {
            this.mTtl = j;
            return this;
        }

        public Builder() {
            this.mIps = new LinkedHashSet();
            this.mProxyIps = new LinkedHashSet();
        }

        public Builder(HostInfo hostInfo) {
            this.mHostname = hostInfo.mHostname;
            this.mIps = new LinkedHashSet(hostInfo.getIps());
            this.mProxyIps = new LinkedHashSet(hostInfo.getProxyIps());
            this.mTtl = hostInfo.mTtl;
            this.mClientTimeStamp = hostInfo.mClientTimeStamp;
        }
    }

    public Builder builder() {
        return new Builder(this);
    }

    public String getHostName() {
        return this.mHostname;
    }

    public List<InetAddress> getInetAddress() {
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = this.mIps.iterator();
        while (it.hasNext()) {
            try {
                arrayList.add(InetAddress.getByAddress(getHostName(), InetAddress.getByName(it.next()).getAddress()));
            } catch (UnknownHostException e2) {
                e2.printStackTrace();
            }
        }
        return arrayList;
    }

    public List<String> getIps() {
        return Collections.unmodifiableList(this.mIps);
    }

    public List<InetAddress> getProxyInetAddress() {
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = this.mProxyIps.iterator();
        while (it.hasNext()) {
            try {
                arrayList.add(InetAddress.getByAddress(getHostName(), InetAddress.getByName(it.next()).getAddress()));
            } catch (UnknownHostException e2) {
                e2.printStackTrace();
            }
        }
        return arrayList;
    }

    public List<String> getProxyIps() {
        return Collections.unmodifiableList(this.mProxyIps);
    }

    public long getTimeStamp() {
        return this.mClientTimeStamp;
    }

    public long getTtl() {
        return this.mTtl;
    }

    public int ipSize() {
        return this.mIps.size();
    }

    public int proxyIpsSize() {
        return this.mProxyIps.size();
    }

    public String toString() {
        StringBuilder sbA = a.a("HostInfo: ");
        sbA.append(this.mHostname);
        sbA.append(", ");
        sbA.append(this.mTtl);
        sbA.append(", ");
        sbA.append(this.mIps);
        sbA.append(", ");
        sbA.append(this.mProxyIps);
        return sbA.toString();
    }

    public HostInfo(Builder builder) {
        this.mHostname = builder.mHostname;
        this.mIps = new ArrayList(builder.mIps);
        this.mProxyIps = new ArrayList(builder.mProxyIps);
        this.mTtl = builder.mTtl;
        this.mClientTimeStamp = builder.mClientTimeStamp;
    }
}
