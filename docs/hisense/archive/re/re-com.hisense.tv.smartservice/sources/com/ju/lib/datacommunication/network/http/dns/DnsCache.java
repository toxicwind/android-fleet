package com.ju.lib.datacommunication.network.http.dns;

import android.os.SystemClock;
import com.ju.lib.datacommunication.network.http.core.HostInfo;
import com.ju.lib.datacommunication.network.http.utils.HttpLog;
import java.net.InetAddress;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/* JADX INFO: loaded from: classes2.dex */
public class DnsCache {
    public volatile Map<String, HostInfo> mCache;
    public Set<String> mUnsupported;

    /* JADX INFO: Access modifiers changed from: private */
    static final class Holder {
        public static final DnsCache INSTANCE = new DnsCache();
    }

    public static final DnsCache getInstance() {
        return Holder.INSTANCE;
    }

    private boolean isTimeout(HostInfo hostInfo) {
        return SystemClock.elapsedRealtime() - hostInfo.getTimeStamp() > hostInfo.getTtl() * 1000;
    }

    public void addUnsupportedHostname(String str) {
        this.mUnsupported.add(str);
    }

    public synchronized void failed(String str, InetAddress inetAddress, long j) {
        HostInfo hostInfo = this.mCache.get(str);
        if (hostInfo != null && hostInfo.ipSize() == 1 && hostInfo.getTtl() > j) {
            if (((hostInfo.getTtl() * 1000) + hostInfo.getTimeStamp()) - SystemClock.elapsedRealtime() > 1000 * j) {
                this.mCache.put(hostInfo.getHostName(), hostInfo.builder().setTtl(j).setClientTimeStamp(SystemClock.elapsedRealtime()).build());
            }
        }
    }

    public synchronized HostInfo get(String str) {
        try {
            HostInfo hostInfo = this.mCache.get(str);
            if (hostInfo == null) {
                return null;
            }
            if (!isTimeout(hostInfo)) {
                return hostInfo;
            }
            this.mCache.remove(str);
            return null;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
        throw th;
    }

    public boolean isUnsupportedHostname(String str) {
        return this.mUnsupported.contains(str);
    }

    public synchronized void put(HostInfo hostInfo) {
        this.mCache.put(hostInfo.getHostName(), hostInfo);
    }

    public void resetCacheDns() {
        if (this.mCache != null) {
            this.mCache.clear();
            HttpLog.i("HTTP2.HttpDns", "mCache clear!");
        }
        Set<String> set = this.mUnsupported;
        if (set != null) {
            set.clear();
            HttpLog.i("HTTP2.HttpDns", "mUnsupported clear!");
        }
    }

    public DnsCache() {
        this.mCache = new ConcurrentHashMap();
        this.mUnsupported = new CopyOnWriteArraySet();
    }
}
