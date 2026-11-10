package c.a.c.a;

import com.google.common.base.Ascii;
import com.google.common.net.InetAddresses;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import okhttp3.CertificatePinner;

/* JADX INFO: loaded from: classes2.dex */
public final class e implements HostnameVerifier {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final e f1639a = new e();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Pattern f1640b = Pattern.compile("([0-9a-fA-F]*:[0-9a-fA-F:.]*)|([\\d.]+)");

    public static List<String> a(X509Certificate x509Certificate, int i) {
        Integer num;
        String str;
        ArrayList arrayList = new ArrayList();
        try {
            Collection<List<?>> subjectAlternativeNames = x509Certificate.getSubjectAlternativeNames();
            if (subjectAlternativeNames == null) {
                return Collections.emptyList();
            }
            for (List<?> list : subjectAlternativeNames) {
                if (list != null && list.size() >= 2 && (num = (Integer) list.get(0)) != null && num.intValue() == i && (str = (String) list.get(1)) != null) {
                    arrayList.add(str);
                }
            }
            return arrayList;
        } catch (CertificateParsingException unused) {
            return Collections.emptyList();
        }
    }

    @Override // javax.net.ssl.HostnameVerifier
    public boolean verify(String str, SSLSession sSLSession) {
        try {
            return verify(str, (X509Certificate) sSLSession.getPeerCertificates()[0]);
        } catch (SSLException unused) {
            return false;
        }
    }

    public boolean verify(String str, X509Certificate x509Certificate) {
        String str2;
        if (f1640b.matcher(str).matches()) {
            List<String> listA = a(x509Certificate, 7);
            int size = listA.size();
            for (int i = 0; i < size; i++) {
                if (!str.equalsIgnoreCase(listA.get(i))) {
                }
            }
            return false;
        }
        String lowerCase = str.toLowerCase(Locale.US);
        List<String> listA2 = a(x509Certificate, 2);
        int size2 = listA2.size();
        int i2 = 0;
        boolean z = false;
        while (i2 < size2) {
            if (!a(lowerCase, listA2.get(i2))) {
                i2++;
                z = true;
            }
        }
        if (!z) {
            d dVar = new d(x509Certificate.getSubjectX500Principal());
            dVar.f1635c = 0;
            dVar.f1636d = 0;
            dVar.f1637e = 0;
            dVar.f1638f = 0;
            dVar.g = dVar.f1633a.toCharArray();
            String strB = dVar.b();
            if (strB == null) {
                str2 = null;
            } else {
                while (true) {
                    int i3 = dVar.f1635c;
                    int i4 = dVar.f1634b;
                    if (i3 == i4) {
                        break;
                    }
                    char c2 = dVar.g[i3];
                    if (c2 == '\"') {
                        dVar.f1635c = i3 + 1;
                        dVar.f1636d = dVar.f1635c;
                        dVar.f1637e = dVar.f1636d;
                        while (true) {
                            int i5 = dVar.f1635c;
                            if (i5 != dVar.f1634b) {
                                char[] cArr = dVar.g;
                                if (cArr[i5] == '\"') {
                                    dVar.f1635c = i5 + 1;
                                    while (true) {
                                        int i6 = dVar.f1635c;
                                        if (i6 >= dVar.f1634b || dVar.g[i6] != ' ') {
                                            break;
                                        }
                                        dVar.f1635c = i6 + 1;
                                    }
                                    char[] cArr2 = dVar.g;
                                    int i7 = dVar.f1636d;
                                    str2 = new String(cArr2, i7, dVar.f1637e - i7);
                                    break;
                                }
                                if (cArr[i5] == '\\') {
                                    cArr[dVar.f1637e] = dVar.a();
                                } else {
                                    cArr[dVar.f1637e] = cArr[i5];
                                }
                                dVar.f1635c++;
                                dVar.f1637e++;
                            } else {
                                StringBuilder sbA = b.a.a.a.a.a("Unexpected end of DN: ");
                                sbA.append(dVar.f1633a);
                                throw new IllegalStateException(sbA.toString());
                            }
                        }
                    } else if (c2 != '#') {
                        if (c2 != '+' && c2 != ',' && c2 != ';') {
                            dVar.f1636d = i3;
                            dVar.f1637e = i3;
                            while (true) {
                                int i8 = dVar.f1635c;
                                if (i8 >= dVar.f1634b) {
                                    char[] cArr3 = dVar.g;
                                    int i9 = dVar.f1636d;
                                    str2 = new String(cArr3, i9, dVar.f1637e - i9);
                                    break;
                                }
                                char[] cArr4 = dVar.g;
                                char c3 = cArr4[i8];
                                if (c3 != ' ') {
                                    if (c3 != ';') {
                                        if (c3 == '\\') {
                                            int i10 = dVar.f1637e;
                                            dVar.f1637e = i10 + 1;
                                            cArr4[i10] = dVar.a();
                                            dVar.f1635c++;
                                        } else if (c3 != '+' && c3 != ',') {
                                            int i11 = dVar.f1637e;
                                            dVar.f1637e = i11 + 1;
                                            cArr4[i11] = cArr4[i8];
                                            dVar.f1635c = i8 + 1;
                                        }
                                    }
                                    char[] cArr5 = dVar.g;
                                    int i12 = dVar.f1636d;
                                    str2 = new String(cArr5, i12, dVar.f1637e - i12);
                                    break;
                                }
                                int i13 = dVar.f1637e;
                                dVar.f1638f = i13;
                                dVar.f1635c = i8 + 1;
                                dVar.f1637e = i13 + 1;
                                cArr4[i13] = Ascii.CASE_MASK;
                                while (true) {
                                    int i14 = dVar.f1635c;
                                    if (i14 >= dVar.f1634b) {
                                        break;
                                    }
                                    char[] cArr6 = dVar.g;
                                    if (cArr6[i14] != ' ') {
                                        break;
                                    }
                                    int i15 = dVar.f1637e;
                                    dVar.f1637e = i15 + 1;
                                    cArr6[i15] = Ascii.CASE_MASK;
                                    dVar.f1635c = i14 + 1;
                                }
                                int i16 = dVar.f1635c;
                                if (i16 != dVar.f1634b) {
                                    char[] cArr7 = dVar.g;
                                    if (cArr7[i16] == ',' || cArr7[i16] == '+' || cArr7[i16] == ';') {
                                    }
                                }
                                char[] cArr8 = dVar.g;
                                int i17 = dVar.f1636d;
                                str2 = new String(cArr8, i17, dVar.f1638f - i17);
                                break;
                            }
                        }
                        str2 = "";
                    } else if (i3 + 4 < i4) {
                        dVar.f1636d = i3;
                        dVar.f1635c = i3 + 1;
                        while (true) {
                            int i18 = dVar.f1635c;
                            if (i18 != dVar.f1634b) {
                                char[] cArr9 = dVar.g;
                                if (cArr9[i18] != '+' && cArr9[i18] != ',' && cArr9[i18] != ';') {
                                    if (cArr9[i18] == ' ') {
                                        dVar.f1637e = i18;
                                        dVar.f1635c = i18 + 1;
                                        while (true) {
                                            int i19 = dVar.f1635c;
                                            if (i19 >= dVar.f1634b || dVar.g[i19] != ' ') {
                                                break;
                                            }
                                            dVar.f1635c = i19 + 1;
                                        }
                                    } else {
                                        if (cArr9[i18] >= 'A' && cArr9[i18] <= 'F') {
                                            cArr9[i18] = (char) (cArr9[i18] + Ascii.CASE_MASK);
                                        }
                                        dVar.f1635c++;
                                    }
                                }
                            }
                            dVar.f1637e = dVar.f1635c;
                            break;
                        }
                        int i20 = dVar.f1637e;
                        int i21 = dVar.f1636d;
                        int i22 = i20 - i21;
                        if (i22 >= 5 && (i22 & 1) != 0) {
                            byte[] bArr = new byte[i22 / 2];
                            int i23 = i21 + 1;
                            for (int i24 = 0; i24 < bArr.length; i24++) {
                                bArr[i24] = (byte) dVar.a(i23);
                                i23 += 2;
                            }
                            str2 = new String(dVar.g, dVar.f1636d, i22);
                        } else {
                            StringBuilder sbA2 = b.a.a.a.a.a("Unexpected end of DN: ");
                            sbA2.append(dVar.f1633a);
                            throw new IllegalStateException(sbA2.toString());
                        }
                    } else {
                        StringBuilder sbA3 = b.a.a.a.a.a("Unexpected end of DN: ");
                        sbA3.append(dVar.f1633a);
                        throw new IllegalStateException(sbA3.toString());
                    }
                    if (!"cn".equalsIgnoreCase(strB)) {
                        int i25 = dVar.f1635c;
                        if (i25 >= dVar.f1634b) {
                            break;
                        }
                        char[] cArr10 = dVar.g;
                        if (cArr10[i25] != ',' && cArr10[i25] != ';' && cArr10[i25] != '+') {
                            StringBuilder sbA4 = b.a.a.a.a.a("Malformed DN: ");
                            sbA4.append(dVar.f1633a);
                            throw new IllegalStateException(sbA4.toString());
                        }
                        dVar.f1635c++;
                        strB = dVar.b();
                        if (strB == null) {
                            StringBuilder sbA5 = b.a.a.a.a.a("Malformed DN: ");
                            sbA5.append(dVar.f1633a);
                            throw new IllegalStateException(sbA5.toString());
                        }
                    }
                }
                str2 = null;
            }
            if (str2 != null) {
                return a(lowerCase, str2);
            }
        }
        return false;
        return true;
    }

    public final boolean a(String str, String str2) {
        if (str != null && str.length() != 0 && !str.startsWith(".") && !str.endsWith("..") && str2 != null && str2.length() != 0 && !str2.startsWith(".") && !str2.endsWith("..")) {
            if (!str.endsWith(".")) {
                str = str + InetAddresses.IPV4_DELIMITER;
            }
            if (!str2.endsWith(".")) {
                str2 = str2 + InetAddresses.IPV4_DELIMITER;
            }
            String lowerCase = str2.toLowerCase(Locale.US);
            if (!lowerCase.contains("*")) {
                return str.equals(lowerCase);
            }
            if (!lowerCase.startsWith(CertificatePinner.Pin.WILDCARD) || lowerCase.indexOf(42, 1) != -1 || str.length() < lowerCase.length() || CertificatePinner.Pin.WILDCARD.equals(lowerCase)) {
                return false;
            }
            String strSubstring = lowerCase.substring(1);
            if (!str.endsWith(strSubstring)) {
                return false;
            }
            int length = str.length() - strSubstring.length();
            return length <= 0 || str.lastIndexOf(46, length - 1) == -1;
        }
        return false;
    }
}
