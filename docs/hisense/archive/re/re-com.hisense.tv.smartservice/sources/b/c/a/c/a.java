package b.c.a.c;

import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class a<K, V> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[] f49a = new int[0];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Object[] f50b = new Object[0];

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static Object[] f51c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static int f52d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static Object[] f53e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static int f54f;
    public int[] g = f49a;
    public Object[] h = f50b;
    public int i = 0;

    public int a(Object obj, int i) {
        int i2 = this.i;
        if (i2 == 0) {
            return -1;
        }
        int iA = a(this.g, i2, i);
        if (iA < 0 || obj.equals(this.h[iA << 1])) {
            return iA;
        }
        int i3 = iA + 1;
        while (i3 < i2 && this.g[i3] == i) {
            if (obj.equals(this.h[i3 << 1])) {
                return i3;
            }
            i3++;
        }
        for (int i4 = iA - 1; i4 >= 0 && this.g[i4] == i; i4--) {
            if (obj.equals(this.h[i4 << 1])) {
                return i4;
            }
        }
        return ~i3;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Map) {
            Map map = (Map) obj;
            if (this.i != map.size()) {
                return false;
            }
            for (int i = 0; i < this.i; i++) {
                try {
                    int i2 = i << 1;
                    Object obj2 = this.h[i2];
                    Object obj3 = this.h[i2 + 1];
                    Object obj4 = map.get(obj2);
                    if (obj3 == null) {
                        if (obj4 != null || !map.containsKey(obj2)) {
                            return false;
                        }
                    } else if (!obj3.equals(obj4)) {
                        return false;
                    }
                } catch (ClassCastException | NullPointerException unused) {
                }
            }
            return true;
        }
        return false;
    }

    public int hashCode() {
        int[] iArr = this.g;
        Object[] objArr = this.h;
        int i = this.i;
        int i2 = 1;
        int i3 = 0;
        int iHashCode = 0;
        while (i3 < i) {
            Object obj = objArr[i2];
            iHashCode += (obj == null ? 0 : obj.hashCode()) ^ iArr[i3];
            i3++;
            i2 += 2;
        }
        return iHashCode;
    }

    public String toString() {
        if (this.i <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.i * 28);
        sb.append('{');
        for (int i = 0; i < this.i; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            int i2 = i << 1;
            Object obj = this.h[i2];
            if (obj != this) {
                sb.append(obj);
            } else {
                sb.append("(this Map)");
            }
            sb.append('=');
            Object obj2 = this.h[i2 + 1];
            if (obj2 != this) {
                sb.append(obj2);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public int a() {
        int i = this.i;
        if (i == 0) {
            return -1;
        }
        int iA = a(this.g, i, 0);
        if (iA < 0 || this.h[iA << 1] == null) {
            return iA;
        }
        int i2 = iA + 1;
        while (i2 < i && this.g[i2] == 0) {
            if (this.h[i2 << 1] == null) {
                return i2;
            }
            i2++;
        }
        for (int i3 = iA - 1; i3 >= 0 && this.g[i3] == 0; i3--) {
            if (this.h[i3 << 1] == null) {
                return i3;
            }
        }
        return ~i2;
    }

    public final void a(int i) {
        if (i == 8) {
            synchronized (a.class) {
                if (f53e != null) {
                    Object[] objArr = f53e;
                    this.h = objArr;
                    f53e = (Object[]) objArr[0];
                    this.g = (int[]) objArr[1];
                    objArr[1] = null;
                    objArr[0] = null;
                    f54f--;
                    return;
                }
            }
        } else if (i == 4) {
            synchronized (a.class) {
                if (f51c != null) {
                    Object[] objArr2 = f51c;
                    this.h = objArr2;
                    f51c = (Object[]) objArr2[0];
                    this.g = (int[]) objArr2[1];
                    objArr2[1] = null;
                    objArr2[0] = null;
                    f52d--;
                    return;
                }
            }
        }
        this.g = new int[i];
        this.h = new Object[i << 1];
    }

    public static void a(int[] iArr, Object[] objArr, int i) {
        if (iArr.length == 8) {
            synchronized (a.class) {
                if (f54f < 10) {
                    objArr[0] = f53e;
                    objArr[1] = iArr;
                    for (int i2 = (i << 1) - 1; i2 >= 2; i2--) {
                        objArr[i2] = null;
                    }
                    f53e = objArr;
                    f54f++;
                }
            }
            return;
        }
        if (iArr.length == 4) {
            synchronized (a.class) {
                if (f52d < 10) {
                    objArr[0] = f51c;
                    objArr[1] = iArr;
                    for (int i3 = (i << 1) - 1; i3 >= 2; i3--) {
                        objArr[i3] = null;
                    }
                    f51c = objArr;
                    f52d++;
                }
            }
        }
    }

    public int a(Object obj) {
        return obj == null ? a() : a(obj, obj.hashCode());
    }

    public V a(K k, V v) {
        int i;
        int iA;
        if (k == null) {
            iA = a();
            i = 0;
        } else {
            int iHashCode = k.hashCode();
            i = iHashCode;
            iA = a(k, iHashCode);
        }
        if (iA >= 0) {
            int i2 = (iA << 1) + 1;
            Object[] objArr = this.h;
            V v2 = (V) objArr[i2];
            objArr[i2] = v;
            return v2;
        }
        int i3 = ~iA;
        int i4 = this.i;
        if (i4 >= this.g.length) {
            int i5 = 4;
            if (i4 >= 8) {
                i5 = (i4 >> 1) + i4;
            } else if (i4 >= 4) {
                i5 = 8;
            }
            int[] iArr = this.g;
            Object[] objArr2 = this.h;
            a(i5);
            int[] iArr2 = this.g;
            if (iArr2.length > 0) {
                System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
                System.arraycopy(objArr2, 0, this.h, 0, objArr2.length);
            }
            a(iArr, objArr2, this.i);
        }
        int i6 = this.i;
        if (i3 < i6) {
            int[] iArr3 = this.g;
            int i7 = i3 + 1;
            System.arraycopy(iArr3, i3, iArr3, i7, i6 - i3);
            Object[] objArr3 = this.h;
            System.arraycopy(objArr3, i3 << 1, objArr3, i7 << 1, (this.i - i3) << 1);
        }
        this.g[i3] = i;
        Object[] objArr4 = this.h;
        int i8 = i3 << 1;
        objArr4[i8] = k;
        objArr4[i8 + 1] = v;
        this.i++;
        return null;
    }

    public static int a(int[] iArr, int i, int i2) {
        int i3 = i - 1;
        int i4 = 0;
        while (i4 <= i3) {
            int i5 = (i4 + i3) >>> 1;
            int i6 = iArr[i5];
            if (i6 < i2) {
                i4 = i5 + 1;
            } else {
                if (i6 <= i2) {
                    return i5;
                }
                i3 = i5 - 1;
            }
        }
        return ~i4;
    }
}
