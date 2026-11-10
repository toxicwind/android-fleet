package e.b;

import com.hisense.support.tv.HisenseCountryLanInfo;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Object f2162a = new a(null);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Map<String, Object> f2163b;

    private static final class a {
        public /* synthetic */ a(c cVar) {
        }

        public final Object clone() {
            return this;
        }

        public boolean equals(Object obj) {
            return obj == null || obj == this;
        }

        public String toString() {
            return "null";
        }
    }

    public d() {
        this.f2163b = new HashMap();
    }

    public static String n(String str) {
        String string;
        StringWriter stringWriter = new StringWriter();
        synchronized (stringWriter.getBuffer()) {
            try {
                try {
                    a(str, (Writer) stringWriter);
                    string = stringWriter.toString();
                } catch (IOException unused) {
                    return "";
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return string;
    }

    public static Object o(String str) {
        if (str.equals("")) {
            return str;
        }
        if (str.equalsIgnoreCase("true")) {
            return Boolean.TRUE;
        }
        if (str.equalsIgnoreCase("false")) {
            return Boolean.FALSE;
        }
        if (str.equalsIgnoreCase("null")) {
            return f2162a;
        }
        char cCharAt = str.charAt(0);
        if ((cCharAt >= '0' && cCharAt <= '9') || cCharAt == '-') {
            try {
                if (str.indexOf(46) > -1 || str.indexOf(101) > -1 || str.indexOf(69) > -1 || "-0".equals(str)) {
                    Double dValueOf = Double.valueOf(str);
                    if (!dValueOf.isInfinite() && !dValueOf.isNaN()) {
                        return dValueOf;
                    }
                } else {
                    Long l = new Long(str);
                    if (str.equals(l.toString())) {
                        return l.longValue() == ((long) l.intValue()) ? Integer.valueOf(l.intValue()) : l;
                    }
                }
            } catch (Exception unused) {
            }
        }
        return str;
    }

    public Object a(String str) {
        if (str == null) {
            throw new b("Null key.");
        }
        Object objJ = j(str);
        if (objJ != null) {
            return objJ;
        }
        StringBuilder sbA = b.a.a.a.a.a("JSONObject[");
        sbA.append(n(str));
        sbA.append("] not found.");
        throw new b(sbA.toString());
    }

    public boolean b(String str) {
        Object objA = a(str);
        if (objA.equals(Boolean.FALSE)) {
            return false;
        }
        boolean z = objA instanceof String;
        if (z && ((String) objA).equalsIgnoreCase("false")) {
            return false;
        }
        if (objA.equals(Boolean.TRUE)) {
            return true;
        }
        if (z && ((String) objA).equalsIgnoreCase("true")) {
            return true;
        }
        StringBuilder sbA = b.a.a.a.a.a("JSONObject[");
        sbA.append(n(str));
        sbA.append("] is not a Boolean.");
        throw new b(sbA.toString());
    }

    public double c(String str) {
        Object objA = a(str);
        try {
            return objA instanceof Number ? ((Number) objA).doubleValue() : Double.parseDouble((String) objA);
        } catch (Exception unused) {
            StringBuilder sbA = b.a.a.a.a.a("JSONObject[");
            sbA.append(n(str));
            sbA.append("] is not a number.");
            throw new b(sbA.toString());
        }
    }

    public int d(String str) {
        Object objA = a(str);
        try {
            return objA instanceof Number ? ((Number) objA).intValue() : Integer.parseInt((String) objA);
        } catch (Exception unused) {
            StringBuilder sbA = b.a.a.a.a.a("JSONObject[");
            sbA.append(n(str));
            sbA.append("] is not an int.");
            throw new b(sbA.toString());
        }
    }

    public e.b.a e(String str) {
        Object objA = a(str);
        if (objA instanceof e.b.a) {
            return (e.b.a) objA;
        }
        StringBuilder sbA = b.a.a.a.a.a("JSONObject[");
        sbA.append(n(str));
        sbA.append("] is not a JSONArray.");
        throw new b(sbA.toString());
    }

    public d f(String str) {
        Object objA = a(str);
        if (objA instanceof d) {
            return (d) objA;
        }
        StringBuilder sbA = b.a.a.a.a.a("JSONObject[");
        sbA.append(n(str));
        sbA.append("] is not a JSONObject.");
        throw new b(sbA.toString());
    }

    public long g(String str) {
        Object objA = a(str);
        try {
            return objA instanceof Number ? ((Number) objA).longValue() : Long.parseLong((String) objA);
        } catch (Exception unused) {
            StringBuilder sbA = b.a.a.a.a.a("JSONObject[");
            sbA.append(n(str));
            sbA.append("] is not a long.");
            throw new b(sbA.toString());
        }
    }

    public String h(String str) {
        Object objA = a(str);
        if (objA instanceof String) {
            return (String) objA;
        }
        StringBuilder sbA = b.a.a.a.a.a("JSONObject[");
        sbA.append(n(str));
        sbA.append("] not a string.");
        throw new b(sbA.toString());
    }

    public boolean i(String str) {
        return this.f2163b.containsKey(str);
    }

    public Object j(String str) {
        if (str == null) {
            return null;
        }
        return this.f2163b.get(str);
    }

    public e.b.a k(String str) {
        Object objJ = j(str);
        if (objJ instanceof e.b.a) {
            return (e.b.a) objJ;
        }
        return null;
    }

    public d l(String str) {
        Object objJ = j(str);
        if (objJ instanceof d) {
            return (d) objJ;
        }
        return null;
    }

    public String m(String str) {
        return a(str, "");
    }

    public String toString() {
        try {
            return a(0);
        } catch (Exception unused) {
            return null;
        }
    }

    public d(f fVar) {
        this();
        if (fVar.c() != '{') {
            throw fVar.a("A JSONObject text must begin with '{'");
        }
        while (true) {
            char c2 = fVar.c();
            if (c2 == 0) {
                throw fVar.a("A JSONObject text must end with '}'");
            }
            if (c2 == '}') {
                return;
            }
            fVar.a();
            String string = fVar.d().toString();
            if (fVar.c() == ':') {
                Object objD = fVar.d();
                if (string != null && objD != null) {
                    if (j(string) == null) {
                        a(string, objD);
                    } else {
                        throw new b(b.a.a.a.a.a("Duplicate key \"", string, "\""));
                    }
                }
                char c3 = fVar.c();
                if (c3 != ',' && c3 != ';') {
                    if (c3 != '}') {
                        throw fVar.a("Expected a ',' or '}'");
                    }
                    return;
                } else if (fVar.c() == '}') {
                    return;
                } else {
                    fVar.a();
                }
            } else {
                throw fVar.a("Expected a ':' after a key");
            }
        }
    }

    public Iterator<String> a() {
        return this.f2163b.keySet().iterator();
    }

    public boolean a(String str, boolean z) {
        try {
            return b(str);
        } catch (Exception unused) {
            return z;
        }
    }

    public double a(String str, double d2) {
        try {
            return c(str);
        } catch (Exception unused) {
            return d2;
        }
    }

    public int b() {
        return this.f2163b.size();
    }

    public int a(String str, int i) {
        try {
            return d(str);
        } catch (Exception unused) {
            return i;
        }
    }

    public d b(String str, boolean z) {
        a(str, z ? Boolean.TRUE : Boolean.FALSE);
        return this;
    }

    public long a(String str, long j) {
        try {
            return g(str);
        } catch (Exception unused) {
            return j;
        }
    }

    public d b(String str, int i) {
        a(str, new Integer(i));
        return this;
    }

    public String a(String str, String str2) {
        Object objJ = j(str);
        return f2162a.equals(objJ) ? str2 : objJ.toString();
    }

    public d b(String str, long j) {
        a(str, new Long(j));
        return this;
    }

    public static Object b(Object obj) {
        try {
            if (obj == null) {
                return f2162a;
            }
            if (!(obj instanceof d) && !(obj instanceof e.b.a) && !f2162a.equals(obj) && !(obj instanceof e) && !(obj instanceof Byte) && !(obj instanceof Character) && !(obj instanceof Short) && !(obj instanceof Integer) && !(obj instanceof Long) && !(obj instanceof Boolean) && !(obj instanceof Float) && !(obj instanceof Double) && !(obj instanceof String) && !(obj instanceof BigInteger) && !(obj instanceof BigDecimal)) {
                if (obj instanceof Collection) {
                    return new e.b.a((Collection<?>) obj);
                }
                if (obj.getClass().isArray()) {
                    return new e.b.a(obj);
                }
                if (obj instanceof Map) {
                    return new d((Map<?, ?>) obj);
                }
                Package r0 = obj.getClass().getPackage();
                String name = r0 != null ? r0.getName() : "";
                if (!name.startsWith("java.") && !name.startsWith("javax.") && obj.getClass().getClassLoader() != null) {
                    return new d(obj);
                }
                return obj.toString();
            }
            return obj;
        } catch (Exception unused) {
            return null;
        }
    }

    public d a(String str, Object obj) {
        if (str == null) {
            throw new NullPointerException("Null key.");
        }
        if (obj != null) {
            a(obj);
            this.f2163b.put(str, obj);
        } else {
            this.f2163b.remove(str);
        }
        return this;
    }

    /* JADX WARN: Code duplicated, block: B:38:0x007f  */
    public static Writer a(String str, Writer writer) throws IOException {
        if (str != null && str.length() != 0) {
            int length = str.length();
            writer.write(34);
            int i = 0;
            char c2 = 0;
            while (i < length) {
                char cCharAt = str.charAt(i);
                if (cCharAt == '\f') {
                    writer.write("\\f");
                } else if (cCharAt == '\r') {
                    writer.write("\\r");
                } else if (cCharAt == '\"') {
                    writer.write(92);
                    writer.write(cCharAt);
                } else if (cCharAt == '/') {
                    if (c2 == '<') {
                        writer.write(92);
                    }
                    writer.write(cCharAt);
                } else if (cCharAt != '\\') {
                    switch (cCharAt) {
                        case '\b':
                            writer.write("\\b");
                            break;
                        case '\t':
                            writer.write("\\t");
                            break;
                        case '\n':
                            writer.write("\\n");
                            break;
                        default:
                            if (cCharAt >= ' ' && ((cCharAt < 128 || cCharAt >= 160) && (cCharAt < 8192 || cCharAt >= 8448))) {
                                writer.write(cCharAt);
                            } else {
                                writer.write("\\u");
                                String hexString = Integer.toHexString(cCharAt);
                                writer.write("0000", 0, 4 - hexString.length());
                                writer.write(hexString);
                            }
                            break;
                    }
                } else {
                    writer.write(92);
                    writer.write(cCharAt);
                }
                i++;
                c2 = cCharAt;
            }
            writer.write(34);
            return writer;
        }
        writer.write("\"\"");
        return writer;
    }

    public d(Map<?, ?> map) {
        this.f2163b = new HashMap();
        if (map != null) {
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                Object value = entry.getValue();
                if (value != null) {
                    this.f2163b.put(String.valueOf(entry.getKey()), b(value));
                }
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:26:0x005e  */
    public d(Object obj) {
        Method[] declaredMethods;
        String strSubstring;
        this();
        Class<?> cls = obj.getClass();
        if (cls.getClassLoader() != null) {
            declaredMethods = cls.getMethods();
        } else {
            declaredMethods = cls.getDeclaredMethods();
        }
        for (Method method : declaredMethods) {
            try {
                if (Modifier.isPublic(method.getModifiers())) {
                    String name = method.getName();
                    if (name.startsWith("get")) {
                        if ("getClass".equals(name) || "getDeclaringClass".equals(name)) {
                            strSubstring = "";
                        } else {
                            strSubstring = name.substring(3);
                        }
                    } else if (name.startsWith(HisenseCountryLanInfo.LOCALE_ISL_CODE)) {
                        strSubstring = name.substring(2);
                    } else {
                        strSubstring = "";
                    }
                    if (strSubstring.length() > 0 && Character.isUpperCase(strSubstring.charAt(0)) && method.getParameterTypes().length == 0) {
                        if (strSubstring.length() == 1) {
                            strSubstring = strSubstring.toLowerCase();
                        } else if (!Character.isUpperCase(strSubstring.charAt(1))) {
                            strSubstring = strSubstring.substring(0, 1).toLowerCase() + strSubstring.substring(1);
                        }
                        Object objInvoke = method.invoke(obj, null);
                        if (objInvoke != null) {
                            this.f2163b.put(strSubstring, b(objInvoke));
                        }
                    }
                }
            } catch (Exception unused) {
            }
        }
    }

    public static void a(Object obj) {
        if (obj != null) {
            if (obj instanceof Double) {
                Double d2 = (Double) obj;
                if (d2.isInfinite() || d2.isNaN()) {
                    throw new b("JSON does not allow non-finite numbers.");
                }
                return;
            }
            if (obj instanceof Float) {
                Float f2 = (Float) obj;
                if (f2.isInfinite() || f2.isNaN()) {
                    throw new b("JSON does not allow non-finite numbers.");
                }
            }
        }
    }

    public String a(int i) {
        String string;
        StringWriter stringWriter = new StringWriter();
        synchronized (stringWriter.getBuffer()) {
            a(stringWriter, i, 0);
            string = stringWriter.toString();
        }
        return string;
    }

    public static final Writer a(Writer writer, Object obj, int i, int i2) throws IOException {
        if (obj != null && !obj.equals(null)) {
            if (obj instanceof d) {
                ((d) obj).a(writer, i, i2);
            } else if (obj instanceof e.b.a) {
                ((e.b.a) obj).a(writer, i, i2);
            } else if (obj instanceof Map) {
                new d((Map<?, ?>) obj).a(writer, i, i2);
            } else if (obj instanceof Collection) {
                new e.b.a((Collection<?>) obj).a(writer, i, i2);
            } else if (obj.getClass().isArray()) {
                new e.b.a(obj).a(writer, i, i2);
            } else if (obj instanceof Number) {
                Number number = (Number) obj;
                a(number);
                String string = number.toString();
                if (string.indexOf(46) > 0 && string.indexOf(101) < 0 && string.indexOf(69) < 0) {
                    while (string.endsWith("0")) {
                        string = string.substring(0, string.length() - 1);
                    }
                    if (string.endsWith(".")) {
                        string = string.substring(0, string.length() - 1);
                    }
                }
                writer.write(string);
            } else if (obj instanceof Boolean) {
                writer.write(obj.toString());
            } else if (obj instanceof e) {
                try {
                    String strA = ((e) obj).a();
                    if (strA == null) {
                        strA = n(obj.toString());
                    }
                    writer.write(strA);
                } catch (Exception e2) {
                    throw new b(e2);
                }
            } else {
                a(obj.toString(), writer);
            }
        } else {
            writer.write("null");
        }
        return writer;
    }

    public d(String str) {
        this(new f(str));
    }

    public static final void a(Writer writer, int i) throws IOException {
        for (int i2 = 0; i2 < i; i2++) {
            writer.write(32);
        }
    }

    public Writer a(Writer writer, int i, int i2) {
        boolean z = false;
        try {
            int iB = b();
            Iterator<String> itA = a();
            writer.write(123);
            if (iB == 1) {
                String next = itA.next();
                writer.write(n(next.toString()));
                writer.write(58);
                if (i > 0) {
                    writer.write(32);
                }
                a(writer, this.f2163b.get(next), i, i2);
            } else if (iB != 0) {
                int i3 = i2 + i;
                while (itA.hasNext()) {
                    String next2 = itA.next();
                    if (z) {
                        writer.write(44);
                    }
                    if (i > 0) {
                        writer.write(10);
                    }
                    a(writer, i3);
                    writer.write(n(next2.toString()));
                    writer.write(58);
                    if (i > 0) {
                        writer.write(32);
                    }
                    a(writer, this.f2163b.get(next2), i, i3);
                    z = true;
                }
                if (i > 0) {
                    writer.write(10);
                }
                a(writer, i2);
            }
            writer.write(125);
            return writer;
        } catch (IOException e2) {
            throw new b(e2);
        }
    }
}
