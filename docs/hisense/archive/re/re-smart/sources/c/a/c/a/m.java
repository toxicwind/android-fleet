package c.a.c.a;

import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String[] f1673a = new String[0];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Charset f1674b = Charset.forName("UTF-8");

    static {
        byte[] bArr = new byte[0];
    }

    public static <T> List<T> a(T[] tArr) {
        return Collections.unmodifiableList(Arrays.asList((Object[]) tArr.clone()));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> T[] a(Class<T> cls, T[] tArr, T[] tArr2) {
        ArrayList arrayList = new ArrayList();
        for (Object[] objArr : tArr) {
            for (T t : tArr2) {
                if (objArr.equals(t)) {
                    arrayList.add(t);
                    break;
                }
            }
        }
        return (T[]) arrayList.toArray((Object[]) Array.newInstance((Class<?>) cls, arrayList.size()));
    }
}
