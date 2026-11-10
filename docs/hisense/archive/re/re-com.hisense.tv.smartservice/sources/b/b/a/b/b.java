package b.b.a.b;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f7a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f8b;

    public static class a extends b {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f9c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f10d;

        public a(int i, int i2, int i3, int i4) {
            super(i, i2, null);
            this.f9c = i3;
            this.f10d = i4;
        }

        @Override // b.b.a.b.b
        public String b(StackTraceElement stackTraceElement) {
            String str;
            if (stackTraceElement == null) {
                throw new IllegalArgumentException("Caller not found");
            }
            if (stackTraceElement.getLineNumber() < 0) {
                str = String.format("%s#%s", stackTraceElement.getClassName(), stackTraceElement.getMethodName());
            } else {
                String string = stackTraceElement.toString();
                str = String.format("%s.%s%s", stackTraceElement.getClassName(), stackTraceElement.getMethodName(), string.substring(string.lastIndexOf(40), string.length()));
            }
            try {
                return b.b.a.c.a.a(str, this.f9c, this.f10d);
            } catch (Exception e2) {
                return e2.getMessage();
            }
        }
    }

    public static class c extends b {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final List<b> f17c;

        public c(int i, int i2, List<b> list) {
            super(i, i2, null);
            this.f17c = new ArrayList(list);
        }

        @Override // b.b.a.b.b
        public String b(StackTraceElement stackTraceElement) {
            StringBuilder sb = new StringBuilder();
            Iterator<b> it = this.f17c.iterator();
            while (it.hasNext()) {
                sb.append(it.next().a(stackTraceElement));
            }
            return sb.toString();
        }
    }

    public static class d extends b {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final SimpleDateFormat f18c;

        public d(int i, int i2, String str) {
            super(i, i2, null);
            if (str != null) {
                this.f18c = new SimpleDateFormat(str);
            } else {
                this.f18c = new SimpleDateFormat("HH:mm:ss.SSS");
            }
        }

        @Override // b.b.a.b.b
        public String b(StackTraceElement stackTraceElement) {
            return this.f18c.format(new Date());
        }
    }

    public static class e extends b {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final String f19c;

        public e(int i, int i2, String str) {
            super(i, i2, null);
            this.f19c = str;
        }

        @Override // b.b.a.b.b
        public String b(StackTraceElement stackTraceElement) {
            return this.f19c;
        }
    }

    public static class f extends b {
        public f(int i, int i2) {
            super(i, i2, null);
        }

        @Override // b.b.a.b.b
        public String b(StackTraceElement stackTraceElement) {
            return Thread.currentThread().getName();
        }
    }

    public /* synthetic */ b(int i, int i2, b.b.a.b.a aVar) {
        this.f7a = i;
        this.f8b = i2;
    }

    public final String a(StackTraceElement stackTraceElement) {
        String strB = b(stackTraceElement);
        int i = this.f7a;
        int i2 = this.f8b;
        if (strB == null) {
            return null;
        }
        if (Math.abs(i2) < strB.length()) {
            strB = i2 < 0 ? strB.substring(strB.length() + i2, strB.length()) : i2 > 0 ? strB.substring(0, i2) : strB;
        }
        return Math.abs(i) > strB.length() ? String.format(b.a.a.a.a.g("%", i, "s"), strB) : strB;
    }

    public abstract String b(StackTraceElement stackTraceElement);

    /* JADX INFO: renamed from: b.b.a.b.b$b, reason: collision with other inner class name */
    public static class C0003b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final Pattern f11a = Pattern.compile("%%");

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final Pattern f12b = Pattern.compile("%n");

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final Pattern f13c = Pattern.compile("%([+-]?\\d+)?(\\.([+-]?\\d+))?caller(\\{([+-]?\\d+)?(\\.([+-]?\\d+))?\\})?");

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static final Pattern f14d = Pattern.compile("%date(\\{(.*?)\\})?");

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public static final Pattern f15e = Pattern.compile("%([+-]?\\d+)?(\\.([+-]?\\d+))?\\(");

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public static final Pattern f16f = Pattern.compile("%d(\\{(.*?)\\})?");
        public static final Pattern g = Pattern.compile("%([+-]?\\d+)?(\\.([+-]?\\d+))?c(\\{([+-]?\\d+)?(\\.([+-]?\\d+))?\\})?");
        public static final Pattern h = Pattern.compile("%([+-]?\\d+)?(\\.([+-]?\\d+))?thread");
        public static final Pattern i = Pattern.compile("%([+-]?\\d+)?(\\.([+-]?\\d+))?t");
        public String j;
        public int k;
        public List<c> l;

        public b a(String str) {
            if (str == null) {
                return null;
            }
            this.k = 0;
            this.j = str;
            this.l = new ArrayList();
            this.l.add(new c(0, 0, new ArrayList()));
            while (true) {
                int length = str.length();
                int i2 = this.k;
                if (length <= i2) {
                    break;
                }
                int iIndexOf = str.indexOf("%", i2);
                int iIndexOf2 = str.indexOf(")", this.k);
                if (this.l.size() > 1 && iIndexOf2 < iIndexOf) {
                    List<c> list = this.l;
                    list.get(list.size() - 1).f17c.add(new e(0, 0, str.substring(this.k, iIndexOf2)));
                    List<c> list2 = this.l;
                    c cVar = list2.get(list2.size() - 2);
                    List<c> list3 = this.l;
                    cVar.f17c.add(list3.remove(list3.size() - 1));
                    this.k = iIndexOf2 + 1;
                }
                if (iIndexOf == -1) {
                    List<c> list4 = this.l;
                    list4.get(list4.size() - 1).f17c.add(new e(0, 0, str.substring(this.k)));
                    break;
                }
                List<c> list5 = this.l;
                list5.get(list5.size() - 1).f17c.add(new e(0, 0, str.substring(this.k, iIndexOf)));
                this.k = iIndexOf;
                Matcher matcherA = a(f11a);
                if (matcherA != null) {
                    List<c> list6 = this.l;
                    list6.get(list6.size() - 1).f17c.add(new e(0, 0, "%"));
                    this.k = matcherA.end();
                } else {
                    Matcher matcherA2 = a(f12b);
                    if (matcherA2 != null) {
                        List<c> list7 = this.l;
                        list7.get(list7.size() - 1).f17c.add(new e(0, 0, "\n"));
                        this.k = matcherA2.end();
                    } else {
                        Matcher matcherA3 = a(f13c);
                        if (matcherA3 == null && (matcherA3 = a(g)) == null) {
                            Matcher matcherA4 = a(f14d);
                            if (matcherA4 == null && (matcherA4 = a(f16f)) == null) {
                                Matcher matcherA5 = a(h);
                                if (matcherA5 == null && (matcherA5 = a(i)) == null) {
                                    Matcher matcherA6 = a(f15e);
                                    if (matcherA6 == null) {
                                        throw new IllegalArgumentException();
                                    }
                                    this.l.add(new c(Integer.parseInt(matcherA6.group(1) == null ? "0" : matcherA6.group(1)), Integer.parseInt(matcherA6.group(3) != null ? matcherA6.group(3) : "0"), new ArrayList()));
                                    this.k = matcherA6.end();
                                } else {
                                    int i3 = Integer.parseInt(matcherA5.group(1) == null ? "0" : matcherA5.group(1));
                                    int i4 = Integer.parseInt(matcherA5.group(3) != null ? matcherA5.group(3) : "0");
                                    List<c> list8 = this.l;
                                    list8.get(list8.size() - 1).f17c.add(new f(i3, i4));
                                    this.k = matcherA5.end();
                                }
                            } else {
                                String strGroup = matcherA4.group(2);
                                List<c> list9 = this.l;
                                list9.get(list9.size() - 1).f17c.add(new d(0, 0, strGroup));
                                this.k = matcherA4.end();
                            }
                        } else {
                            int i5 = Integer.parseInt(matcherA3.group(1) == null ? "0" : matcherA3.group(1));
                            int i6 = Integer.parseInt(matcherA3.group(3) == null ? "0" : matcherA3.group(3));
                            int i7 = Integer.parseInt(matcherA3.group(5) == null ? "0" : matcherA3.group(5));
                            int i8 = Integer.parseInt(matcherA3.group(7) != null ? matcherA3.group(7) : "0");
                            List<c> list10 = this.l;
                            list10.get(list10.size() - 1).f17c.add(new a(i5, i6, i7, i8));
                            this.k = matcherA3.end();
                        }
                    }
                }
            }
            return this.l.get(0);
        }

        public final Matcher a(Pattern pattern) {
            Matcher matcher = pattern.matcher(this.j);
            if (matcher.find(this.k) && matcher.start() == this.k) {
                return matcher;
            }
            return null;
        }
    }
}
