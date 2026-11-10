package b.c.a;

import b.d.a.a.g;
import b.d.a.a.j;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public abstract class a<T> {
    public abstract T parse(g gVar);

    public T parse(InputStream inputStream) {
        b.d.a.a.b bVar = b.f45f;
        g gVarA = bVar.a(inputStream, bVar.a((Object) inputStream, false));
        gVarA.u();
        return parse(gVarA);
    }

    public abstract void parseField(T t, String str, g gVar);

    public List<T> parseList(InputStream inputStream) {
        b.d.a.a.b bVar = b.f45f;
        g gVarA = bVar.a(inputStream, bVar.a((Object) inputStream, false));
        gVarA.u();
        return parseList(gVarA);
    }

    public Map<String, T> parseMap(InputStream inputStream) {
        b.d.a.a.b bVar = b.f45f;
        g gVarA = bVar.a(inputStream, bVar.a((Object) inputStream, false));
        gVarA.u();
        return parseMap(gVarA);
    }

    public String serialize(T t) {
        StringWriter stringWriter = new StringWriter();
        b.d.a.a.b bVar = b.f45f;
        b.d.a.a.b.c cVarA = bVar.a((Object) stringWriter, false);
        bVar.b(stringWriter, cVarA);
        b.d.a.a.d dVarA = bVar.a(stringWriter, cVarA);
        serialize(t, dVarA, true);
        dVarA.close();
        return stringWriter.toString();
    }

    public abstract void serialize(T t, b.d.a.a.d dVar, boolean z);

    public T parse(byte[] bArr) {
        g gVarA = b.f45f.a(bArr);
        gVarA.u();
        return parse(gVarA);
    }

    public List<T> parseList(byte[] bArr) {
        g gVarA = b.f45f.a(bArr);
        gVarA.u();
        return parseList(gVarA);
    }

    public Map<String, T> parseMap(byte[] bArr) {
        g gVarA = b.f45f.a(bArr);
        gVarA.u();
        return parseMap(gVarA);
    }

    public void serialize(T t, OutputStream outputStream) {
        b.d.a.a.d dVarA = b.f45f.a(outputStream);
        serialize(t, dVarA, true);
        dVarA.close();
    }

    public T parse(char[] cArr) {
        g gVarA = b.f45f.a(cArr);
        gVarA.u();
        return parse(gVarA);
    }

    public List<T> parseList(char[] cArr) {
        g gVarA = b.f45f.a(cArr);
        gVarA.u();
        return parseList(gVarA);
    }

    public Map<String, T> parseMap(char[] cArr) {
        g gVarA = b.f45f.a(cArr);
        gVarA.u();
        return parseMap(gVarA);
    }

    public String serialize(List<T> list) {
        StringWriter stringWriter = new StringWriter();
        b.d.a.a.b bVar = b.f45f;
        b.d.a.a.b.c cVarA = bVar.a((Object) stringWriter, false);
        bVar.b(stringWriter, cVarA);
        b.d.a.a.d dVarA = bVar.a(stringWriter, cVarA);
        serialize(list, dVarA);
        dVarA.close();
        return stringWriter.toString();
    }

    public T parse(String str) {
        g gVarA = b.f45f.a(str);
        gVarA.u();
        return parse(gVarA);
    }

    public List<T> parseList(String str) {
        g gVarA = b.f45f.a(str);
        gVarA.u();
        return parseList(gVarA);
    }

    public Map<String, T> parseMap(String str) {
        g gVarA = b.f45f.a(str);
        gVarA.u();
        return parseMap(gVarA);
    }

    public List<T> parseList(g gVar) {
        ArrayList arrayList = new ArrayList();
        if (((b.d.a.a.a.c) gVar).f70b == j.START_ARRAY) {
            while (gVar.u() != j.END_ARRAY) {
                arrayList.add(parse(gVar));
            }
        }
        return arrayList;
    }

    public Map<String, T> parseMap(g gVar) {
        HashMap map = new HashMap();
        while (gVar.u() != j.END_OBJECT) {
            String strS = gVar.s();
            gVar.u();
            if (((b.d.a.a.a.c) gVar).f70b == j.VALUE_NULL) {
                map.put(strS, null);
            } else {
                map.put(strS, parse(gVar));
            }
        }
        return map;
    }

    public void serialize(List<T> list, OutputStream outputStream) {
        b.d.a.a.d dVarA = b.f45f.a(outputStream);
        serialize(list, dVarA);
        dVarA.close();
    }

    public void serialize(List<T> list, b.d.a.a.d dVar) {
        dVar.t();
        for (T t : list) {
            if (t != null) {
                serialize(t, dVar, true);
            } else {
                dVar.s();
            }
        }
        dVar.q();
    }

    public String serialize(Map<String, T> map) {
        StringWriter stringWriter = new StringWriter();
        b.d.a.a.b bVar = b.f45f;
        b.d.a.a.b.c cVarA = bVar.a((Object) stringWriter, false);
        bVar.b(stringWriter, cVarA);
        b.d.a.a.d dVarA = bVar.a(stringWriter, cVarA);
        serialize(map, dVarA);
        dVarA.close();
        return stringWriter.toString();
    }

    public void serialize(Map<String, T> map, OutputStream outputStream) {
        b.d.a.a.d dVarA = b.f45f.a(outputStream);
        serialize(map, dVarA);
        dVarA.close();
    }

    public void serialize(Map<String, T> map, b.d.a.a.d dVar) {
        dVar.u();
        for (Map.Entry<String, T> entry : map.entrySet()) {
            dVar.b(entry.getKey());
            if (entry.getValue() == null) {
                dVar.s();
            } else {
                serialize(entry.getValue(), dVar, true);
            }
        }
        dVar.r();
    }
}
