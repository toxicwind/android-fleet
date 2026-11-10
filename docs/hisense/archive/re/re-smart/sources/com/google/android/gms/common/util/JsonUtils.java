package com.google.android.gms.common.util;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import e.b.a;
import e.b.b;
import e.b.d;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
@VisibleForTesting
@KeepForSdk
public final class JsonUtils {
    public static final Pattern zza = Pattern.compile("\\\\.");
    public static final Pattern zzb = Pattern.compile("[\\\\\"/\b\f\n\r\t]");

    @KeepForSdk
    public static boolean areJsonValuesEquivalent(@Nullable Object obj, @Nullable Object obj2) {
        if (obj == null && obj2 == null) {
            return true;
        }
        if (obj == null || obj2 == null) {
            return false;
        }
        if ((obj instanceof d) && (obj2 instanceof d)) {
            d dVar = (d) obj;
            d dVar2 = (d) obj2;
            if (dVar.b() != dVar2.b()) {
                return false;
            }
            Iterator<String> itA = dVar.a();
            while (itA.hasNext()) {
                String next = itA.next();
                if (!dVar2.i(next)) {
                    return false;
                }
                try {
                    Preconditions.checkNotNull(next);
                    if (!areJsonValuesEquivalent(dVar.a(next), dVar2.a(next))) {
                        return false;
                    }
                } catch (b unused) {
                }
            }
            return true;
        }
        if (!(obj instanceof a) || !(obj2 instanceof a)) {
            return obj.equals(obj2);
        }
        a aVar = (a) obj;
        a aVar2 = (a) obj2;
        if (aVar.a() != aVar2.a()) {
            return false;
        }
        for (int i = 0; i < aVar.a(); i++) {
            try {
                if (!areJsonValuesEquivalent(aVar.get(i), aVar2.get(i))) {
                    return false;
                }
            } catch (b unused2) {
            }
        }
        return true;
    }

    @Nullable
    @KeepForSdk
    public static String escapeString(@Nullable String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        Matcher matcher = zzb.matcher(str);
        StringBuffer stringBuffer = null;
        while (matcher.find()) {
            if (stringBuffer == null) {
                stringBuffer = new StringBuffer();
            }
            char cCharAt = matcher.group().charAt(0);
            if (cCharAt == '\f') {
                matcher.appendReplacement(stringBuffer, "\\\\f");
            } else if (cCharAt == '\r') {
                matcher.appendReplacement(stringBuffer, "\\\\r");
            } else if (cCharAt == '\"') {
                matcher.appendReplacement(stringBuffer, "\\\\\\\"");
            } else if (cCharAt == '/') {
                matcher.appendReplacement(stringBuffer, "\\\\/");
            } else if (cCharAt != '\\') {
                switch (cCharAt) {
                    case '\b':
                        matcher.appendReplacement(stringBuffer, "\\\\b");
                        break;
                    case '\t':
                        matcher.appendReplacement(stringBuffer, "\\\\t");
                        break;
                    case '\n':
                        matcher.appendReplacement(stringBuffer, "\\\\n");
                        break;
                }
            } else {
                matcher.appendReplacement(stringBuffer, "\\\\\\\\");
            }
        }
        if (stringBuffer == null) {
            return str;
        }
        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
    }

    @NonNull
    @KeepForSdk
    public static String unescapeString(@NonNull String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        String strZza = zzc.zza(str);
        Matcher matcher = zza.matcher(strZza);
        StringBuffer stringBuffer = null;
        while (matcher.find()) {
            if (stringBuffer == null) {
                stringBuffer = new StringBuffer();
            }
            char cCharAt = matcher.group().charAt(1);
            if (cCharAt == '\"') {
                matcher.appendReplacement(stringBuffer, "\"");
            } else if (cCharAt == '/') {
                matcher.appendReplacement(stringBuffer, "/");
            } else if (cCharAt == '\\') {
                matcher.appendReplacement(stringBuffer, "\\\\");
            } else if (cCharAt == 'b') {
                matcher.appendReplacement(stringBuffer, "\b");
            } else if (cCharAt == 'f') {
                matcher.appendReplacement(stringBuffer, "\f");
            } else if (cCharAt == 'n') {
                matcher.appendReplacement(stringBuffer, "\n");
            } else if (cCharAt == 'r') {
                matcher.appendReplacement(stringBuffer, "\r");
            } else {
                if (cCharAt != 't') {
                    throw new IllegalStateException("Found an escaped character that should never be.");
                }
                matcher.appendReplacement(stringBuffer, "\t");
            }
        }
        if (stringBuffer == null) {
            return strZza;
        }
        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
    }
}
