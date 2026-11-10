package b.d.a.a.e;

import java.util.ArrayList;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final char[] f206a = new char[0];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final a f207b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public char[] f208c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f209d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f210e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ArrayList<char[]> f211f;
    public boolean g = false;
    public int h;
    public char[] i;
    public int j;
    public String k;
    public char[] l;

    public f(a aVar) {
        this.f207b = aVar;
    }

    public void a(char[] cArr, int i, int i2) {
        this.k = null;
        this.l = null;
        this.f208c = cArr;
        this.f209d = i;
        this.f210e = i2;
        if (this.g) {
            a();
        }
    }

    public char[] b() {
        int i;
        char[] cArrCopyOf = this.l;
        if (cArrCopyOf == null) {
            String str = this.k;
            if (str != null) {
                cArrCopyOf = str.toCharArray();
            } else {
                int i2 = this.f209d;
                if (i2 >= 0) {
                    int i3 = this.f210e;
                    cArrCopyOf = i3 < 1 ? f206a : i2 == 0 ? Arrays.copyOf(this.f208c, i3) : Arrays.copyOfRange(this.f208c, i2, i3 + i2);
                } else {
                    int iK = k();
                    if (iK < 1) {
                        cArrCopyOf = f206a;
                    } else {
                        cArrCopyOf = new char[iK];
                        ArrayList<char[]> arrayList = this.f211f;
                        if (arrayList != null) {
                            int size = arrayList.size();
                            i = 0;
                            for (int i4 = 0; i4 < size; i4++) {
                                char[] cArr = this.f211f.get(i4);
                                int length = cArr.length;
                                System.arraycopy(cArr, 0, cArrCopyOf, i, length);
                                i += length;
                            }
                        } else {
                            i = 0;
                        }
                        System.arraycopy(this.i, 0, cArrCopyOf, i, this.j);
                    }
                }
            }
            this.l = cArrCopyOf;
        }
        return cArrCopyOf;
    }

    public String c() {
        if (this.k == null) {
            char[] cArr = this.l;
            if (cArr != null) {
                this.k = new String(cArr);
            } else {
                int i = this.f209d;
                if (i >= 0) {
                    int i2 = this.f210e;
                    if (i2 < 1) {
                        this.k = "";
                        return "";
                    }
                    this.k = new String(this.f208c, i, i2);
                } else {
                    int i3 = this.h;
                    int i4 = this.j;
                    if (i3 == 0) {
                        this.k = i4 != 0 ? new String(this.i, 0, i4) : "";
                    } else {
                        StringBuilder sb = new StringBuilder(i3 + i4);
                        ArrayList<char[]> arrayList = this.f211f;
                        if (arrayList != null) {
                            int size = arrayList.size();
                            for (int i5 = 0; i5 < size; i5++) {
                                char[] cArr2 = this.f211f.get(i5);
                                sb.append(cArr2, 0, cArr2.length);
                            }
                        }
                        sb.append(this.i, 0, this.j);
                        this.k = sb.toString();
                    }
                }
            }
        }
        return this.k;
    }

    public char[] d() {
        this.f209d = -1;
        this.j = 0;
        this.f210e = 0;
        this.f208c = null;
        this.k = null;
        this.l = null;
        if (this.g) {
            a();
        }
        char[] cArr = this.i;
        if (cArr != null) {
            return cArr;
        }
        char[] cArrA = a(0);
        this.i = cArrA;
        return cArrA;
    }

    public char[] e() {
        char[] cArr = this.i;
        int length = cArr.length;
        int i = (length >> 1) + length;
        if (i > 262144) {
            i = (length >> 2) + length;
        }
        char[] cArrCopyOf = Arrays.copyOf(cArr, i);
        this.i = cArrCopyOf;
        return cArrCopyOf;
    }

    public char[] f() {
        if (this.f211f == null) {
            this.f211f = new ArrayList<>();
        }
        this.g = true;
        this.f211f.add(this.i);
        int length = this.i.length;
        this.h += length;
        this.j = 0;
        int i = length + (length >> 1);
        if (i < 1000) {
            i = 1000;
        } else if (i > 262144) {
            i = 262144;
        }
        char[] cArr = new char[i];
        this.i = cArr;
        return cArr;
    }

    public char[] g() {
        if (this.f209d >= 0) {
            c(1);
        } else {
            char[] cArr = this.i;
            if (cArr == null) {
                this.i = a(0);
            } else if (this.j >= cArr.length) {
                b(1);
            }
        }
        return this.i;
    }

    public char[] h() {
        if (this.f209d >= 0) {
            return this.f208c;
        }
        char[] cArr = this.l;
        if (cArr != null) {
            return cArr;
        }
        String str = this.k;
        if (str == null) {
            return !this.g ? this.i : b();
        }
        char[] charArray = str.toCharArray();
        this.l = charArray;
        return charArray;
    }

    public int i() {
        int i = this.f209d;
        if (i >= 0) {
            return i;
        }
        return 0;
    }

    public void j() {
        this.f209d = -1;
        this.j = 0;
        this.f210e = 0;
        this.f208c = null;
        this.k = null;
        this.l = null;
        if (this.g) {
            a();
        }
    }

    public int k() {
        if (this.f209d >= 0) {
            return this.f210e;
        }
        char[] cArr = this.l;
        if (cArr != null) {
            return cArr.length;
        }
        String str = this.k;
        return str != null ? str.length() : this.h + this.j;
    }

    public String toString() {
        return c();
    }

    public final char[] a(int i) {
        a aVar = this.f207b;
        if (aVar != null) {
            return aVar.a(2, i);
        }
        return new char[Math.max(i, 1000)];
    }

    public final void a() {
        this.g = false;
        this.f211f.clear();
        this.h = 0;
        this.j = 0;
    }

    public final void b(int i) {
        if (this.f211f == null) {
            this.f211f = new ArrayList<>();
        }
        char[] cArr = this.i;
        this.g = true;
        this.f211f.add(cArr);
        this.h += cArr.length;
        this.j = 0;
        int length = cArr.length;
        int i2 = length + (length >> 1);
        if (i2 < 1000) {
            i2 = 1000;
        } else if (i2 > 262144) {
            i2 = 262144;
        }
        this.i = new char[i2];
    }

    public final void c(int i) {
        int i2 = this.f210e;
        this.f210e = 0;
        char[] cArr = this.f208c;
        this.f208c = null;
        int i3 = this.f209d;
        this.f209d = -1;
        int i4 = i + i2;
        char[] cArr2 = this.i;
        if (cArr2 == null || i4 > cArr2.length) {
            this.i = a(i4);
        }
        if (i2 > 0) {
            System.arraycopy(cArr, i3, this.i, 0, i2);
        }
        this.h = 0;
        this.j = i2;
    }
}
