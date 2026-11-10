package com.google.firebase.remoteconfig.internal;

import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.util.BiConsumer;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigValue;
import e.b.b;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.Executor;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public class ConfigGetParameterHandler {
    public final ConfigCacheClient activatedConfigsCache;
    public final ConfigCacheClient defaultConfigsCache;
    public final Executor executor;
    public final Set<BiConsumer<String, ConfigContainer>> listeners = new HashSet();

    @VisibleForTesting(otherwise = 3)
    public static final Charset FRC_BYTE_ARRAY_ENCODING = Charset.forName("UTF-8");
    public static final Pattern TRUE_REGEX = Pattern.compile("^(1|true|t|yes|y|on)$", 2);
    public static final Pattern FALSE_REGEX = Pattern.compile("^(0|false|f|no|n|off|)$", 2);

    public ConfigGetParameterHandler(Executor executor, ConfigCacheClient configCacheClient, ConfigCacheClient configCacheClient2) {
        this.executor = executor;
        this.activatedConfigsCache = configCacheClient;
        this.defaultConfigsCache = configCacheClient2;
    }

    private void callListeners(final String str, final ConfigContainer configContainer) {
        if (configContainer == null) {
            return;
        }
        synchronized (this.listeners) {
            for (final BiConsumer<String, ConfigContainer> biConsumer : this.listeners) {
                this.executor.execute(new Runnable() { // from class: b.e.b.h.a.g
                    @Override // java.lang.Runnable
                    public final void run() {
                        biConsumer.accept(str, configContainer);
                    }
                });
            }
        }
    }

    @Nullable
    public static ConfigContainer getConfigsFromCache(ConfigCacheClient configCacheClient) {
        return configCacheClient.getBlocking();
    }

    @Nullable
    public static Double getDoubleFromCache(ConfigCacheClient configCacheClient, String str) {
        ConfigContainer blocking = configCacheClient.getBlocking();
        if (blocking == null) {
            return null;
        }
        try {
            return Double.valueOf(blocking.getConfigs().c(str));
        } catch (b unused) {
            return null;
        }
    }

    public static Set<String> getKeySetFromCache(ConfigCacheClient configCacheClient) {
        HashSet hashSet = new HashSet();
        ConfigContainer blocking = configCacheClient.getBlocking();
        if (blocking == null) {
            return hashSet;
        }
        Iterator<String> itA = blocking.getConfigs().a();
        while (itA.hasNext()) {
            hashSet.add(itA.next());
        }
        return hashSet;
    }

    @Nullable
    public static Long getLongFromCache(ConfigCacheClient configCacheClient, String str) {
        ConfigContainer blocking = configCacheClient.getBlocking();
        if (blocking == null) {
            return null;
        }
        try {
            return Long.valueOf(blocking.getConfigs().g(str));
        } catch (b unused) {
            return null;
        }
    }

    @Nullable
    public static String getStringFromCache(ConfigCacheClient configCacheClient, String str) {
        ConfigContainer blocking = configCacheClient.getBlocking();
        if (blocking == null) {
            return null;
        }
        try {
            return blocking.getConfigs().h(str);
        } catch (b unused) {
            return null;
        }
    }

    public static void logParameterValueDoesNotExist(String str, String str2) {
        Log.w(FirebaseRemoteConfig.TAG, String.format("No value of type '%s' exists for parameter key '%s'.", str2, str));
    }

    public void addListener(BiConsumer<String, ConfigContainer> biConsumer) {
        synchronized (this.listeners) {
            this.listeners.add(biConsumer);
        }
    }

    public Map<String, FirebaseRemoteConfigValue> getAll() {
        HashSet<String> hashSet = new HashSet();
        hashSet.addAll(getKeySetFromCache(this.activatedConfigsCache));
        hashSet.addAll(getKeySetFromCache(this.defaultConfigsCache));
        HashMap map = new HashMap();
        for (String str : hashSet) {
            map.put(str, getValue(str));
        }
        return map;
    }

    public boolean getBoolean(String str) {
        String stringFromCache = getStringFromCache(this.activatedConfigsCache, str);
        if (stringFromCache != null) {
            if (TRUE_REGEX.matcher(stringFromCache).matches()) {
                callListeners(str, this.activatedConfigsCache.getBlocking());
                return true;
            }
            if (FALSE_REGEX.matcher(stringFromCache).matches()) {
                callListeners(str, this.activatedConfigsCache.getBlocking());
                return false;
            }
        }
        String stringFromCache2 = getStringFromCache(this.defaultConfigsCache, str);
        if (stringFromCache2 != null) {
            if (TRUE_REGEX.matcher(stringFromCache2).matches()) {
                return true;
            }
            if (FALSE_REGEX.matcher(stringFromCache2).matches()) {
                return false;
            }
        }
        logParameterValueDoesNotExist(str, "Boolean");
        return false;
    }

    public byte[] getByteArray(String str) {
        String stringFromCache = getStringFromCache(this.activatedConfigsCache, str);
        if (stringFromCache != null) {
            callListeners(str, this.activatedConfigsCache.getBlocking());
            return stringFromCache.getBytes(FRC_BYTE_ARRAY_ENCODING);
        }
        String stringFromCache2 = getStringFromCache(this.defaultConfigsCache, str);
        if (stringFromCache2 != null) {
            return stringFromCache2.getBytes(FRC_BYTE_ARRAY_ENCODING);
        }
        logParameterValueDoesNotExist(str, "ByteArray");
        return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_BYTE_ARRAY;
    }

    public double getDouble(String str) {
        Double doubleFromCache = getDoubleFromCache(this.activatedConfigsCache, str);
        if (doubleFromCache != null) {
            callListeners(str, this.activatedConfigsCache.getBlocking());
            return doubleFromCache.doubleValue();
        }
        Double doubleFromCache2 = getDoubleFromCache(this.defaultConfigsCache, str);
        if (doubleFromCache2 != null) {
            return doubleFromCache2.doubleValue();
        }
        logParameterValueDoesNotExist(str, "Double");
        return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    }

    public Set<String> getKeysByPrefix(String str) {
        if (str == null) {
            str = "";
        }
        TreeSet treeSet = new TreeSet();
        ConfigContainer blocking = this.activatedConfigsCache.getBlocking();
        if (blocking != null) {
            treeSet.addAll(getKeysByPrefix(str, blocking));
        }
        ConfigContainer blocking2 = this.defaultConfigsCache.getBlocking();
        if (blocking2 != null) {
            treeSet.addAll(getKeysByPrefix(str, blocking2));
        }
        return treeSet;
    }

    public long getLong(String str) {
        Long longFromCache = getLongFromCache(this.activatedConfigsCache, str);
        if (longFromCache != null) {
            callListeners(str, this.activatedConfigsCache.getBlocking());
            return longFromCache.longValue();
        }
        Long longFromCache2 = getLongFromCache(this.defaultConfigsCache, str);
        if (longFromCache2 != null) {
            return longFromCache2.longValue();
        }
        logParameterValueDoesNotExist(str, "Long");
        return 0L;
    }

    public String getString(String str) {
        String stringFromCache = getStringFromCache(this.activatedConfigsCache, str);
        if (stringFromCache != null) {
            callListeners(str, this.activatedConfigsCache.getBlocking());
            return stringFromCache;
        }
        String stringFromCache2 = getStringFromCache(this.defaultConfigsCache, str);
        if (stringFromCache2 != null) {
            return stringFromCache2;
        }
        logParameterValueDoesNotExist(str, "String");
        return "";
    }

    public FirebaseRemoteConfigValue getValue(String str) {
        String stringFromCache = getStringFromCache(this.activatedConfigsCache, str);
        if (stringFromCache != null) {
            callListeners(str, this.activatedConfigsCache.getBlocking());
            return new FirebaseRemoteConfigValueImpl(stringFromCache, 2);
        }
        String stringFromCache2 = getStringFromCache(this.defaultConfigsCache, str);
        if (stringFromCache2 != null) {
            return new FirebaseRemoteConfigValueImpl(stringFromCache2, 1);
        }
        logParameterValueDoesNotExist(str, "FirebaseRemoteConfigValue");
        return new FirebaseRemoteConfigValueImpl("", 0);
    }

    public static TreeSet<String> getKeysByPrefix(String str, ConfigContainer configContainer) {
        TreeSet<String> treeSet = new TreeSet<>();
        Iterator<String> itA = configContainer.getConfigs().a();
        while (itA.hasNext()) {
            String next = itA.next();
            if (next.startsWith(str)) {
                treeSet.add(next);
            }
        }
        return treeSet;
    }
}
