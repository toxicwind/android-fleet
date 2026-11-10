package e.b;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
public class a implements Iterable<Object> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList<Object> f2161a;

    public a() {
        this.f2161a = new ArrayList<>();
    }

    public d a(int i) {
        Object obj = get(i);
        if (obj instanceof d) {
            return (d) obj;
        }
        throw new b(b.a.a.a.a.g("JSONArray[", i, "] is not a JSONObject."));
    }

    public String b(int i) {
        Object obj = get(i);
        if (obj instanceof String) {
            return (String) obj;
        }
        throw new b(b.a.a.a.a.g("JSONArray[", i, "] not a string."));
    }

    public Object c(int i) {
        if (i < 0 || i >= a()) {
            return null;
        }
        return this.f2161a.get(i);
    }

    public d d(int i) {
        Object objC = c(i);
        if (objC instanceof d) {
            return (d) objC;
        }
        return null;
    }

    public String e(int i) {
        String string;
        StringWriter stringWriter = new StringWriter();
        synchronized (stringWriter.getBuffer()) {
            a(stringWriter, i, 0);
            string = stringWriter.toString();
        }
        return string;
    }

    public Object get(int i) {
        Object objC = c(i);
        if (objC != null) {
            return objC;
        }
        throw new b(b.a.a.a.a.g("JSONArray[", i, "] not found."));
    }

    @Override // java.lang.Iterable
    public Iterator<Object> iterator() {
        return this.f2161a.iterator();
    }

    public String toString() {
        try {
            return e(0);
        } catch (Exception unused) {
            return null;
        }
    }

    public a(f fVar) {
        this();
        if (fVar.c() == '[') {
            if (fVar.c() == ']') {
                return;
            }
            fVar.a();
            while (true) {
                if (fVar.c() == ',') {
                    fVar.a();
                    this.f2161a.add(d.f2162a);
                } else {
                    fVar.a();
                    this.f2161a.add(fVar.d());
                }
                char c2 = fVar.c();
                if (c2 != ',') {
                    if (c2 != ']') {
                        throw fVar.a("Expected a ',' or ']'");
                    }
                    return;
                } else if (fVar.c() == ']') {
                    return;
                } else {
                    fVar.a();
                }
            }
        } else {
            throw fVar.a("A JSONArray text must start with '['");
        }
    }

    public int a() {
        return this.f2161a.size();
    }

    public Writer a(Writer writer, int i, int i2) {
        try {
            int iA = a();
            writer.write(91);
            int i3 = 0;
            if (iA == 1) {
                d.a(writer, this.f2161a.get(0), i, i2);
            } else if (iA != 0) {
                int i4 = i2 + i;
                boolean z = false;
                while (i3 < iA) {
                    if (z) {
                        writer.write(44);
                    }
                    if (i > 0) {
                        writer.write(10);
                    }
                    d.a(writer, i4);
                    d.a(writer, this.f2161a.get(i3), i, i4);
                    i3++;
                    z = true;
                }
                if (i > 0) {
                    writer.write(10);
                }
                d.a(writer, i2);
            }
            writer.write(93);
            return writer;
        } catch (IOException e2) {
            throw new b(e2);
        }
    }

    public a(String str) {
        this(new f(str));
    }

    public a(Collection<?> collection) {
        this.f2161a = new ArrayList<>();
        if (collection != null) {
            Iterator<?> it = collection.iterator();
            while (it.hasNext()) {
                this.f2161a.add(d.b(it.next()));
            }
        }
    }

    public a(Object obj) {
        this();
        if (obj.getClass().isArray()) {
            int length = Array.getLength(obj);
            for (int i = 0; i < length; i++) {
                this.f2161a.add(d.b(Array.get(obj, i)));
            }
            return;
        }
        throw new b("JSONArray initial value should be a string or collection or array.");
    }
}
