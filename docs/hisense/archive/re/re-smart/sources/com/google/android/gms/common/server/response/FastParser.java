package com.google.android.gms.common.server.response;

import android.util.Log;
import b.a.a.a.a;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.server.response.FastJsonResponse;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.common.util.JsonUtils;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.gson.stream.JsonReader;
import com.mediatek.twoworlds.tv.TVCallback;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/* JADX INFO: loaded from: classes.dex */
@ShowFirstParty
@KeepForSdk
public class FastParser<T extends FastJsonResponse> {
    public static final char[] zaqu = {'u', 'l', 'l'};
    public static final char[] zaqv = {'r', 'u', 'e'};
    public static final char[] zaqw = {'r', 'u', 'e', '\"'};
    public static final char[] zaqx = {'a', 'l', 's', 'e'};
    public static final char[] zaqy = {'a', 'l', 's', 'e', '\"'};
    public static final char[] zaqz = {'\n'};
    public static final zaa<Integer> zarb = new zab();
    public static final zaa<Long> zarc = new com.google.android.gms.common.server.response.zaa();
    public static final zaa<Float> zard = new zad();
    public static final zaa<Double> zare = new zac();
    public static final zaa<Boolean> zarf = new zaf();
    public static final zaa<String> zarg = new zae();
    public static final zaa<BigInteger> zarh = new zah();
    public static final zaa<BigDecimal> zari = new zag();
    public final char[] zaqp = new char[1];
    public final char[] zaqq = new char[32];
    public final char[] zaqr = new char[1024];
    public final StringBuilder zaqs = new StringBuilder(32);
    public final StringBuilder zaqt = new StringBuilder(1024);
    public final Stack<Integer> zara = new Stack<>();

    @ShowFirstParty
    @KeepForSdk
    public static class ParseException extends Exception {
        public ParseException(String str) {
            super(str);
        }

        public ParseException(String str, Throwable th) {
            super(str, th);
        }

        public ParseException(Throwable th) {
            super(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    interface zaa<O> {
        O zah(FastParser fastParser, BufferedReader bufferedReader);
    }

    /* JADX WARN: Code duplicated, block: B:117:0x026a  */
    /* JADX WARN: Code duplicated, block: B:136:0x0286 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:137:0x026d A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:138:0x0268 A[SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    private final boolean zaa(BufferedReader bufferedReader, FastJsonResponse fastJsonResponse) throws ParseException, IOException {
        HashMap map;
        char cZaj;
        Map<String, FastJsonResponse.Field<?, ?>> fieldMappings = fastJsonResponse.getFieldMappings();
        String strZaa = zaa(bufferedReader);
        if (strZaa == null) {
            zak(1);
            return false;
        }
        while (strZaa != null) {
            FastJsonResponse.Field<?, ?> field = fieldMappings.get(strZaa);
            if (field == null) {
                strZaa = zab(bufferedReader);
            } else {
                int i = 4;
                this.zara.push(4);
                int i2 = field.zaqf;
                switch (i2) {
                    case 0:
                        if (field.zaqg) {
                            fastJsonResponse.zaa((FastJsonResponse.Field) field, (ArrayList<Integer>) zaa(bufferedReader, zarb));
                        } else {
                            fastJsonResponse.zaa((FastJsonResponse.Field) field, zad(bufferedReader));
                        }
                        i = 4;
                        zak(i);
                        zak(2);
                        cZaj = zaj(bufferedReader);
                        if (cZaj == ',') {
                            strZaa = zaa(bufferedReader);
                        } else {
                            if (cZaj != '}') {
                                StringBuilder sb = new StringBuilder(55);
                                sb.append("Expected end of object or field separator, but found: ");
                                sb.append(cZaj);
                                throw new ParseException(sb.toString());
                            }
                            strZaa = null;
                        }
                        break;
                    case 1:
                        if (field.zaqg) {
                            fastJsonResponse.zab((FastJsonResponse.Field) field, (ArrayList<BigInteger>) zaa(bufferedReader, zarh));
                        } else {
                            fastJsonResponse.zaa((FastJsonResponse.Field) field, zaf(bufferedReader));
                        }
                        i = 4;
                        zak(i);
                        zak(2);
                        cZaj = zaj(bufferedReader);
                        if (cZaj == ',') {
                            strZaa = zaa(bufferedReader);
                        } else {
                            if (cZaj != '}') {
                                StringBuilder sb2 = new StringBuilder(55);
                                sb2.append("Expected end of object or field separator, but found: ");
                                sb2.append(cZaj);
                                throw new ParseException(sb2.toString());
                            }
                            strZaa = null;
                        }
                        break;
                    case 2:
                        if (field.zaqg) {
                            fastJsonResponse.zac(field, zaa(bufferedReader, zarc));
                        } else {
                            fastJsonResponse.zaa((FastJsonResponse.Field) field, zae(bufferedReader));
                        }
                        i = 4;
                        zak(i);
                        zak(2);
                        cZaj = zaj(bufferedReader);
                        if (cZaj == ',') {
                            strZaa = zaa(bufferedReader);
                        } else {
                            if (cZaj != '}') {
                                StringBuilder sb3 = new StringBuilder(55);
                                sb3.append("Expected end of object or field separator, but found: ");
                                sb3.append(cZaj);
                                throw new ParseException(sb3.toString());
                            }
                            strZaa = null;
                        }
                        break;
                    case 3:
                        if (field.zaqg) {
                            fastJsonResponse.zad(field, zaa(bufferedReader, zard));
                        } else {
                            fastJsonResponse.zaa((FastJsonResponse.Field) field, zag(bufferedReader));
                        }
                        i = 4;
                        zak(i);
                        zak(2);
                        cZaj = zaj(bufferedReader);
                        if (cZaj == ',') {
                            strZaa = zaa(bufferedReader);
                        } else {
                            if (cZaj != '}') {
                                StringBuilder sb4 = new StringBuilder(55);
                                sb4.append("Expected end of object or field separator, but found: ");
                                sb4.append(cZaj);
                                throw new ParseException(sb4.toString());
                            }
                            strZaa = null;
                        }
                        break;
                    case 4:
                        if (field.zaqg) {
                            fastJsonResponse.zae(field, zaa(bufferedReader, zare));
                        } else {
                            fastJsonResponse.zaa(field, zah(bufferedReader));
                        }
                        i = 4;
                        zak(i);
                        zak(2);
                        cZaj = zaj(bufferedReader);
                        if (cZaj == ',') {
                            strZaa = zaa(bufferedReader);
                        } else {
                            if (cZaj != '}') {
                                StringBuilder sb5 = new StringBuilder(55);
                                sb5.append("Expected end of object or field separator, but found: ");
                                sb5.append(cZaj);
                                throw new ParseException(sb5.toString());
                            }
                            strZaa = null;
                        }
                        break;
                    case 5:
                        if (field.zaqg) {
                            fastJsonResponse.zaf(field, zaa(bufferedReader, zari));
                        } else {
                            fastJsonResponse.zaa((FastJsonResponse.Field) field, zai(bufferedReader));
                        }
                        i = 4;
                        zak(i);
                        zak(2);
                        cZaj = zaj(bufferedReader);
                        if (cZaj == ',') {
                            strZaa = zaa(bufferedReader);
                        } else {
                            if (cZaj != '}') {
                                StringBuilder sb6 = new StringBuilder(55);
                                sb6.append("Expected end of object or field separator, but found: ");
                                sb6.append(cZaj);
                                throw new ParseException(sb6.toString());
                            }
                            strZaa = null;
                        }
                        break;
                    case 6:
                        if (field.zaqg) {
                            fastJsonResponse.zag(field, zaa(bufferedReader, zarf));
                        } else {
                            fastJsonResponse.zaa(field, zaa(bufferedReader, false));
                        }
                        i = 4;
                        zak(i);
                        zak(2);
                        cZaj = zaj(bufferedReader);
                        if (cZaj == ',') {
                            strZaa = zaa(bufferedReader);
                        } else {
                            if (cZaj != '}') {
                                StringBuilder sb7 = new StringBuilder(55);
                                sb7.append("Expected end of object or field separator, but found: ");
                                sb7.append(cZaj);
                                throw new ParseException(sb7.toString());
                            }
                            strZaa = null;
                        }
                        break;
                    case 7:
                        if (field.zaqg) {
                            fastJsonResponse.zah(field, zaa(bufferedReader, zarg));
                        } else {
                            fastJsonResponse.zaa((FastJsonResponse.Field) field, zaa(bufferedReader, this.zaqq, this.zaqs, null));
                        }
                        i = 4;
                        zak(i);
                        zak(2);
                        cZaj = zaj(bufferedReader);
                        if (cZaj == ',') {
                            strZaa = zaa(bufferedReader);
                        } else {
                            if (cZaj != '}') {
                                StringBuilder sb8 = new StringBuilder(55);
                                sb8.append("Expected end of object or field separator, but found: ");
                                sb8.append(cZaj);
                                throw new ParseException(sb8.toString());
                            }
                            strZaa = null;
                        }
                        break;
                    case 8:
                        fastJsonResponse.zaa((FastJsonResponse.Field) field, Base64Utils.decode(zaa(bufferedReader, this.zaqr, this.zaqt, zaqz)));
                        i = 4;
                        zak(i);
                        zak(2);
                        cZaj = zaj(bufferedReader);
                        if (cZaj == ',') {
                            strZaa = zaa(bufferedReader);
                        } else {
                            if (cZaj != '}') {
                                StringBuilder sb9 = new StringBuilder(55);
                                sb9.append("Expected end of object or field separator, but found: ");
                                sb9.append(cZaj);
                                throw new ParseException(sb9.toString());
                            }
                            strZaa = null;
                        }
                        break;
                    case 9:
                        fastJsonResponse.zaa((FastJsonResponse.Field) field, Base64Utils.decodeUrlSafe(zaa(bufferedReader, this.zaqr, this.zaqt, zaqz)));
                        i = 4;
                        zak(i);
                        zak(2);
                        cZaj = zaj(bufferedReader);
                        if (cZaj == ',') {
                            strZaa = zaa(bufferedReader);
                        } else {
                            if (cZaj != '}') {
                                StringBuilder sb10 = new StringBuilder(55);
                                sb10.append("Expected end of object or field separator, but found: ");
                                sb10.append(cZaj);
                                throw new ParseException(sb10.toString());
                            }
                            strZaa = null;
                        }
                        break;
                    case 10:
                        char cZaj2 = zaj(bufferedReader);
                        if (cZaj2 == 'n') {
                            zab(bufferedReader, zaqu);
                            map = null;
                        } else {
                            if (cZaj2 != '{') {
                                throw new ParseException("Expected start of a map object");
                            }
                            this.zara.push(1);
                            map = new HashMap();
                            while (true) {
                                char cZaj3 = zaj(bufferedReader);
                                if (cZaj3 == 0) {
                                    throw new ParseException("Unexpected EOF");
                                }
                                if (cZaj3 == '\"') {
                                    String strZab = zab(bufferedReader, this.zaqq, this.zaqs, null);
                                    if (zaj(bufferedReader) != ':') {
                                        String strValueOf = String.valueOf(strZab);
                                        throw new ParseException(strValueOf.length() != 0 ? "No map value found for key ".concat(strValueOf) : new String("No map value found for key "));
                                    }
                                    if (zaj(bufferedReader) != '\"') {
                                        String strValueOf2 = String.valueOf(strZab);
                                        throw new ParseException(strValueOf2.length() != 0 ? "Expected String value for key ".concat(strValueOf2) : new String("Expected String value for key "));
                                    }
                                    map.put(strZab, zab(bufferedReader, this.zaqq, this.zaqs, null));
                                    char cZaj4 = zaj(bufferedReader);
                                    if (cZaj4 != ',') {
                                        if (cZaj4 != '}') {
                                            StringBuilder sb11 = new StringBuilder(48);
                                            sb11.append("Unexpected character while parsing string map: ");
                                            sb11.append(cZaj4);
                                            throw new ParseException(sb11.toString());
                                        }
                                        zak(1);
                                    }
                                } else if (cZaj3 == '}') {
                                    zak(1);
                                }
                                i = 4;
                                zak(i);
                                zak(2);
                                cZaj = zaj(bufferedReader);
                                if (cZaj == ',') {
                                    strZaa = zaa(bufferedReader);
                                } else {
                                    if (cZaj != '}') {
                                        StringBuilder sb12 = new StringBuilder(55);
                                        sb12.append("Expected end of object or field separator, but found: ");
                                        sb12.append(cZaj);
                                        throw new ParseException(sb12.toString());
                                    }
                                    strZaa = null;
                                }
                            }
                        }
                        fastJsonResponse.zaa((FastJsonResponse.Field) field, (Map<String, String>) map);
                        i = 4;
                        zak(i);
                        zak(2);
                        cZaj = zaj(bufferedReader);
                        if (cZaj == ',') {
                            strZaa = zaa(bufferedReader);
                        } else {
                            if (cZaj != '}') {
                                StringBuilder sb13 = new StringBuilder(55);
                                sb13.append("Expected end of object or field separator, but found: ");
                                sb13.append(cZaj);
                                throw new ParseException(sb13.toString());
                            }
                            strZaa = null;
                        }
                        break;
                    case 11:
                        if (!field.zaqg) {
                            char cZaj5 = zaj(bufferedReader);
                            if (cZaj5 == 'n') {
                                zab(bufferedReader, zaqu);
                                fastJsonResponse.addConcreteTypeInternal(field, field.zaqj, null);
                            } else {
                                this.zara.push(1);
                                if (cZaj5 != '{') {
                                    throw new ParseException("Expected start of object");
                                }
                                try {
                                    FastJsonResponse fastJsonResponseZacn = field.zacn();
                                    zaa(bufferedReader, fastJsonResponseZacn);
                                    fastJsonResponse.addConcreteTypeInternal(field, field.zaqj, fastJsonResponseZacn);
                                } catch (IllegalAccessException e2) {
                                    throw new ParseException("Error instantiating inner object", e2);
                                } catch (InstantiationException e3) {
                                    throw new ParseException("Error instantiating inner object", e3);
                                }
                            }
                            break;
                        } else {
                            char cZaj6 = zaj(bufferedReader);
                            if (cZaj6 == 'n') {
                                zab(bufferedReader, zaqu);
                                fastJsonResponse.addConcreteTypeArrayInternal(field, field.zaqj, null);
                            } else {
                                this.zara.push(5);
                                if (cZaj6 != '[') {
                                    throw new ParseException("Expected array start");
                                }
                                fastJsonResponse.addConcreteTypeArrayInternal(field, field.zaqj, zaa(bufferedReader, field));
                            }
                        }
                        zak(i);
                        zak(2);
                        cZaj = zaj(bufferedReader);
                        if (cZaj == ',') {
                            strZaa = zaa(bufferedReader);
                        } else {
                            if (cZaj != '}') {
                                StringBuilder sb14 = new StringBuilder(55);
                                sb14.append("Expected end of object or field separator, but found: ");
                                sb14.append(cZaj);
                                throw new ParseException(sb14.toString());
                            }
                            strZaa = null;
                        }
                        break;
                    default:
                        throw new ParseException(a.a(30, "Invalid field type ", i2));
                }
            }
        }
        zak(1);
        return true;
    }

    private final String zab(BufferedReader bufferedReader) throws ParseException, IOException {
        bufferedReader.mark(1024);
        char cZaj = zaj(bufferedReader);
        if (cZaj != '\"') {
            if (cZaj == ',') {
                throw new ParseException("Missing value");
            }
            int i = 1;
            if (cZaj == '[') {
                this.zara.push(5);
                bufferedReader.mark(32);
                if (zaj(bufferedReader) == ']') {
                    zak(5);
                } else {
                    bufferedReader.reset();
                    boolean z = false;
                    boolean z2 = false;
                    while (i > 0) {
                        char cZaj2 = zaj(bufferedReader);
                        if (cZaj2 == 0) {
                            throw new ParseException("Unexpected EOF while parsing array");
                        }
                        if (Character.isISOControl(cZaj2)) {
                            throw new ParseException("Unexpected control character while reading array");
                        }
                        if (cZaj2 == '\"' && !z) {
                            z2 = !z2;
                        }
                        if (cZaj2 == '[' && !z2) {
                            i++;
                        }
                        if (cZaj2 == ']' && !z2) {
                            i--;
                        }
                        z = (cZaj2 == '\\' && z2) ? !z : false;
                    }
                    zak(5);
                }
            } else if (cZaj != '{') {
                bufferedReader.reset();
                zaa(bufferedReader, this.zaqr);
            } else {
                this.zara.push(1);
                bufferedReader.mark(32);
                char cZaj3 = zaj(bufferedReader);
                if (cZaj3 == '}') {
                    zak(1);
                } else {
                    if (cZaj3 != '\"') {
                        StringBuilder sb = new StringBuilder(18);
                        sb.append("Unexpected token ");
                        sb.append(cZaj3);
                        throw new ParseException(sb.toString());
                    }
                    bufferedReader.reset();
                    zaa(bufferedReader);
                    while (zab(bufferedReader) != null) {
                    }
                    zak(1);
                }
            }
        } else {
            if (bufferedReader.read(this.zaqp) == -1) {
                throw new ParseException("Unexpected EOF while parsing string");
            }
            char c2 = this.zaqp[0];
            boolean z3 = false;
            while (true) {
                if (c2 == '\"' && !z3) {
                    break;
                }
                z3 = c2 == '\\' ? !z3 : false;
                if (bufferedReader.read(this.zaqp) == -1) {
                    throw new ParseException("Unexpected EOF while parsing string");
                }
                c2 = this.zaqp[0];
                if (Character.isISOControl(c2)) {
                    throw new ParseException("Unexpected control character while reading string");
                }
            }
        }
        char cZaj4 = zaj(bufferedReader);
        if (cZaj4 == ',') {
            zak(2);
            return zaa(bufferedReader);
        }
        if (cZaj4 == '}') {
            zak(2);
            return null;
        }
        StringBuilder sb2 = new StringBuilder(18);
        sb2.append("Unexpected token ");
        sb2.append(cZaj4);
        throw new ParseException(sb2.toString());
    }

    private final String zac(BufferedReader bufferedReader) {
        return zaa(bufferedReader, this.zaqq, this.zaqs, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int zad(BufferedReader bufferedReader) throws ParseException, IOException {
        boolean z;
        int i;
        int i2;
        int i3;
        int i4;
        int iZaa = zaa(bufferedReader, this.zaqr);
        if (iZaa == 0) {
            return 0;
        }
        char[] cArr = this.zaqr;
        if (iZaa <= 0) {
            throw new ParseException("No number to parse");
        }
        if (cArr[0] == '-') {
            i = Integer.MIN_VALUE;
            i2 = 1;
            z = true;
        } else {
            z = false;
            i = -2147483647;
            i2 = 0;
        }
        if (i2 < iZaa) {
            i4 = i2 + 1;
            int iDigit = Character.digit(cArr[i2], 10);
            if (iDigit < 0) {
                throw new ParseException("Unexpected non-digit character");
            }
            i3 = -iDigit;
        } else {
            int i5 = i2;
            i3 = 0;
            i4 = i5;
        }
        while (i4 < iZaa) {
            int i6 = i4 + 1;
            int iDigit2 = Character.digit(cArr[i4], 10);
            if (iDigit2 < 0) {
                throw new ParseException("Unexpected non-digit character");
            }
            if (i3 < -214748364) {
                throw new ParseException("Number too large");
            }
            int i7 = i3 * 10;
            if (i7 < i + iDigit2) {
                throw new ParseException("Number too large");
            }
            i3 = i7 - iDigit2;
            i4 = i6;
        }
        if (!z) {
            return -i3;
        }
        if (i4 > 1) {
            return i3;
        }
        throw new ParseException("No digits to parse");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final BigInteger zaf(BufferedReader bufferedReader) throws ParseException, IOException {
        int iZaa = zaa(bufferedReader, this.zaqr);
        if (iZaa == 0) {
            return null;
        }
        return new BigInteger(new String(this.zaqr, 0, iZaa));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final float zag(BufferedReader bufferedReader) throws ParseException, IOException {
        int iZaa = zaa(bufferedReader, this.zaqr);
        if (iZaa == 0) {
            return 0.0f;
        }
        return Float.parseFloat(new String(this.zaqr, 0, iZaa));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final double zah(BufferedReader bufferedReader) throws ParseException, IOException {
        int iZaa = zaa(bufferedReader, this.zaqr);
        return iZaa == 0 ? FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE : Double.parseDouble(new String(this.zaqr, 0, iZaa));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final BigDecimal zai(BufferedReader bufferedReader) throws ParseException, IOException {
        int iZaa = zaa(bufferedReader, this.zaqr);
        if (iZaa == 0) {
            return null;
        }
        return new BigDecimal(new String(this.zaqr, 0, iZaa));
    }

    private final char zaj(BufferedReader bufferedReader) {
        if (bufferedReader.read(this.zaqp) == -1) {
            return (char) 0;
        }
        while (Character.isWhitespace(this.zaqp[0])) {
            if (bufferedReader.read(this.zaqp) == -1) {
                return (char) 0;
            }
        }
        return this.zaqp[0];
    }

    private final void zak(int i) throws ParseException {
        if (this.zara.isEmpty()) {
            StringBuilder sb = new StringBuilder(46);
            sb.append("Expected state ");
            sb.append(i);
            sb.append(" but had empty stack");
            throw new ParseException(sb.toString());
        }
        int iIntValue = this.zara.pop().intValue();
        if (iIntValue == i) {
            return;
        }
        StringBuilder sb2 = new StringBuilder(46);
        sb2.append("Expected state ");
        sb2.append(i);
        sb2.append(" but had ");
        sb2.append(iIntValue);
        throw new ParseException(sb2.toString());
    }

    @KeepForSdk
    public void parse(InputStream inputStream, T t) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream), 1024);
        try {
            try {
                this.zara.push(0);
                char cZaj = zaj(bufferedReader);
                if (cZaj == 0) {
                    throw new ParseException("No data to parse");
                }
                if (cZaj == '[') {
                    this.zara.push(5);
                    Map<String, FastJsonResponse.Field<?, ?>> fieldMappings = t.getFieldMappings();
                    if (fieldMappings.size() != 1) {
                        throw new ParseException("Object array response class must have a single Field");
                    }
                    FastJsonResponse.Field<?, ?> value = fieldMappings.entrySet().iterator().next().getValue();
                    t.addConcreteTypeArrayInternal(value, value.zaqj, zaa(bufferedReader, value));
                } else {
                    if (cZaj != '{') {
                        StringBuilder sb = new StringBuilder(19);
                        sb.append("Unexpected token: ");
                        sb.append(cZaj);
                        throw new ParseException(sb.toString());
                    }
                    this.zara.push(1);
                    zaa(bufferedReader, t);
                }
                zak(0);
                try {
                    bufferedReader.close();
                } catch (IOException unused) {
                    Log.w("FastParser", "Failed to close reader while parsing.");
                }
            } catch (IOException e2) {
                throw new ParseException(e2);
            }
        } catch (Throwable th) {
            try {
                bufferedReader.close();
            } catch (IOException unused2) {
                Log.w("FastParser", "Failed to close reader while parsing.");
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final long zae(BufferedReader bufferedReader) throws ParseException, IOException {
        long j;
        boolean z;
        long j2;
        int i;
        int iZaa = zaa(bufferedReader, this.zaqr);
        if (iZaa == 0) {
            return 0L;
        }
        char[] cArr = this.zaqr;
        if (iZaa <= 0) {
            throw new ParseException("No number to parse");
        }
        int i2 = 0;
        if (cArr[0] == '-') {
            j = Long.MIN_VALUE;
            i2 = 1;
            z = true;
        } else {
            j = -9223372036854775807L;
            z = false;
        }
        if (i2 < iZaa) {
            i = i2 + 1;
            int iDigit = Character.digit(cArr[i2], 10);
            if (iDigit < 0) {
                throw new ParseException("Unexpected non-digit character");
            }
            j2 = -iDigit;
        } else {
            j2 = 0;
            i = i2;
        }
        while (i < iZaa) {
            int i3 = i + 1;
            int iDigit2 = Character.digit(cArr[i], 10);
            if (iDigit2 < 0) {
                throw new ParseException("Unexpected non-digit character");
            }
            if (j2 < JsonReader.MIN_INCOMPLETE_INTEGER) {
                throw new ParseException("Number too large");
            }
            long j3 = j2 * 10;
            boolean z2 = z;
            long j4 = iDigit2;
            if (j3 < j + j4) {
                throw new ParseException("Number too large");
            }
            j2 = j3 - j4;
            i = i3;
            z = z2;
        }
        if (!z) {
            return -j2;
        }
        if (i > 1) {
            return j2;
        }
        throw new ParseException("No digits to parse");
    }

    public static String zab(BufferedReader bufferedReader, char[] cArr, StringBuilder sb, char[] cArr2) throws ParseException, IOException {
        boolean z;
        sb.setLength(0);
        bufferedReader.mark(cArr.length);
        boolean z2 = false;
        boolean z3 = false;
        while (true) {
            int i = bufferedReader.read(cArr);
            if (i != -1) {
                boolean z4 = z3;
                boolean z5 = z2;
                for (int i2 = 0; i2 < i; i2++) {
                    char c2 = cArr[i2];
                    if (Character.isISOControl(c2)) {
                        if (cArr2 == null) {
                            z = false;
                            break;
                        }
                        int i3 = 0;
                        while (true) {
                            if (i3 >= cArr2.length) {
                                z = false;
                                break;
                            }
                            if (cArr2[i3] == c2) {
                                z = true;
                                break;
                            }
                            i3++;
                        }
                        if (!z) {
                            throw new ParseException("Unexpected control character while reading string");
                        }
                    }
                    if (c2 == '\"' && !z5) {
                        sb.append(cArr, 0, i2);
                        bufferedReader.reset();
                        bufferedReader.skip(i2 + 1);
                        if (z4) {
                            return JsonUtils.unescapeString(sb.toString());
                        }
                        return sb.toString();
                    }
                    if (c2 == '\\') {
                        z5 = !z5;
                        z4 = true;
                    } else {
                        z5 = false;
                    }
                }
                sb.append(cArr, 0, i);
                bufferedReader.mark(cArr.length);
                z2 = z5;
                z3 = z4;
            } else {
                throw new ParseException("Unexpected EOF while parsing string");
            }
        }
    }

    private final void zab(BufferedReader bufferedReader, char[] cArr) throws ParseException, IOException {
        int i = 0;
        while (i < cArr.length) {
            int i2 = bufferedReader.read(this.zaqq, 0, cArr.length - i);
            if (i2 == -1) {
                throw new ParseException("Unexpected EOF");
            }
            for (int i3 = 0; i3 < i2; i3++) {
                if (cArr[i3 + i] != this.zaqq[i3]) {
                    throw new ParseException("Unexpected character");
                }
            }
            i += i2;
        }
    }

    private final String zaa(BufferedReader bufferedReader) throws ParseException, IOException {
        this.zara.push(2);
        char cZaj = zaj(bufferedReader);
        if (cZaj == '\"') {
            this.zara.push(3);
            String strZab = zab(bufferedReader, this.zaqq, this.zaqs, null);
            zak(3);
            if (zaj(bufferedReader) == ':') {
                return strZab;
            }
            throw new ParseException("Expected key/value separator");
        }
        if (cZaj == ']') {
            zak(2);
            zak(1);
            zak(5);
            return null;
        }
        if (cZaj == '}') {
            zak(2);
            return null;
        }
        StringBuilder sb = new StringBuilder(19);
        sb.append("Unexpected token: ");
        sb.append(cZaj);
        throw new ParseException(sb.toString());
    }

    private final <O> ArrayList<O> zaa(BufferedReader bufferedReader, zaa<O> zaaVar) throws ParseException, IOException {
        char cZaj = zaj(bufferedReader);
        if (cZaj == 'n') {
            zab(bufferedReader, zaqu);
            return null;
        }
        if (cZaj == '[') {
            this.zara.push(5);
            ArrayList<O> arrayList = new ArrayList<>();
            while (true) {
                bufferedReader.mark(1024);
                char cZaj2 = zaj(bufferedReader);
                if (cZaj2 == 0) {
                    throw new ParseException("Unexpected EOF");
                }
                if (cZaj2 != ',') {
                    if (cZaj2 != ']') {
                        bufferedReader.reset();
                        arrayList.add(zaaVar.zah(this, bufferedReader));
                    } else {
                        zak(5);
                        return arrayList;
                    }
                }
            }
        } else {
            throw new ParseException("Expected start of array");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String zaa(BufferedReader bufferedReader, char[] cArr, StringBuilder sb, char[] cArr2) throws ParseException, IOException {
        char cZaj = zaj(bufferedReader);
        if (cZaj == '\"') {
            return zab(bufferedReader, cArr, sb, cArr2);
        }
        if (cZaj == 'n') {
            zab(bufferedReader, zaqu);
            return null;
        }
        throw new ParseException("Expected string");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean zaa(BufferedReader bufferedReader, boolean z) throws ParseException, IOException {
        while (true) {
            char cZaj = zaj(bufferedReader);
            if (cZaj != '\"') {
                if (cZaj == 'f') {
                    zab(bufferedReader, z ? zaqy : zaqx);
                    return false;
                }
                if (cZaj == 'n') {
                    zab(bufferedReader, zaqu);
                    return false;
                }
                if (cZaj == 't') {
                    zab(bufferedReader, z ? zaqw : zaqv);
                    return true;
                }
                StringBuilder sb = new StringBuilder(19);
                sb.append("Unexpected token: ");
                sb.append(cZaj);
                throw new ParseException(sb.toString());
            }
            if (z) {
                throw new ParseException("No boolean value found in string");
            }
            z = true;
        }
    }

    private final <T extends FastJsonResponse> ArrayList<T> zaa(BufferedReader bufferedReader, FastJsonResponse.Field<?, ?> field) throws ParseException, IOException {
        TVCallback.CallbackList callbackList = (ArrayList<T>) new ArrayList();
        char cZaj = zaj(bufferedReader);
        if (cZaj == ']') {
            zak(5);
            return callbackList;
        }
        if (cZaj == 'n') {
            zab(bufferedReader, zaqu);
            zak(5);
            return null;
        }
        if (cZaj == '{') {
            this.zara.push(1);
            while (true) {
                try {
                    FastJsonResponse fastJsonResponseZacn = field.zacn();
                    if (!zaa(bufferedReader, fastJsonResponseZacn)) {
                        return callbackList;
                    }
                    callbackList.add(fastJsonResponseZacn);
                    char cZaj2 = zaj(bufferedReader);
                    if (cZaj2 != ',') {
                        if (cZaj2 == ']') {
                            zak(5);
                            return callbackList;
                        }
                        StringBuilder sb = new StringBuilder(19);
                        sb.append("Unexpected token: ");
                        sb.append(cZaj2);
                        throw new ParseException(sb.toString());
                    }
                    if (zaj(bufferedReader) == '{') {
                        this.zara.push(1);
                    } else {
                        throw new ParseException("Expected start of next object in array");
                    }
                } catch (IllegalAccessException e2) {
                    throw new ParseException("Error instantiating inner object", e2);
                } catch (InstantiationException e3) {
                    throw new ParseException("Error instantiating inner object", e3);
                }
            }
        } else {
            StringBuilder sb2 = new StringBuilder(19);
            sb2.append("Unexpected token: ");
            sb2.append(cZaj);
            throw new ParseException(sb2.toString());
        }
    }

    private final int zaa(BufferedReader bufferedReader, char[] cArr) throws ParseException, IOException {
        int i;
        char cZaj = zaj(bufferedReader);
        if (cZaj == 0) {
            throw new ParseException("Unexpected EOF");
        }
        if (cZaj == ',') {
            throw new ParseException("Missing value");
        }
        if (cZaj == 'n') {
            zab(bufferedReader, zaqu);
            return 0;
        }
        bufferedReader.mark(1024);
        if (cZaj == '\"') {
            i = 0;
            boolean z = false;
            while (i < cArr.length && bufferedReader.read(cArr, i, 1) != -1) {
                char c2 = cArr[i];
                if (Character.isISOControl(c2)) {
                    throw new ParseException("Unexpected control character while reading string");
                }
                if (c2 == '\"' && !z) {
                    bufferedReader.reset();
                    bufferedReader.skip(i + 1);
                    return i;
                }
                z = c2 == '\\' ? !z : false;
                i++;
            }
        } else {
            cArr[0] = cZaj;
            i = 1;
            while (i < cArr.length && bufferedReader.read(cArr, i, 1) != -1) {
                if (cArr[i] == '}' || cArr[i] == ',' || Character.isWhitespace(cArr[i]) || cArr[i] == ']') {
                    bufferedReader.reset();
                    bufferedReader.skip(i - 1);
                    cArr[i] = 0;
                    return i;
                }
                i++;
            }
        }
        if (i == cArr.length) {
            throw new ParseException("Absurdly long value");
        }
        throw new ParseException("Unexpected EOF");
    }
}
