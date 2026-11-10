package e.a.a.a;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f2155a;

    static {
        char c2 = File.separatorChar;
        e.a.a.a.a.a aVar = new e.a.a.a.a.a(4);
        PrintWriter printWriter = new PrintWriter(aVar);
        printWriter.println();
        f2155a = aVar.f2146a.toString();
        printWriter.close();
    }

    public static void a(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException unused) {
            }
        }
    }

    public static void a(OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException unused) {
            }
        }
    }

    public static List<String> a(InputStream inputStream, Charset charset) throws IOException {
        Reader inputStreamReader = new InputStreamReader(inputStream, a.a(charset));
        BufferedReader bufferedReader = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader);
        ArrayList arrayList = new ArrayList();
        for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
            arrayList.add(line);
        }
        return arrayList;
    }

    public static void a(Collection<?> collection, String str, OutputStream outputStream, String str2) throws IOException {
        Charset charsetA = a.a(str2);
        if (collection == null) {
            return;
        }
        if (str == null) {
            str = f2155a;
        }
        Charset charsetA2 = a.a(charsetA);
        for (Object obj : collection) {
            if (obj != null) {
                outputStream.write(obj.toString().getBytes(charsetA2));
            }
            outputStream.write(str.getBytes(charsetA2));
        }
    }
}
