package com.google.android.gms.internal.measurement;

import com.google.common.base.Ascii;
import com.google.protobuf.MessageLiteToString;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/* JADX INFO: loaded from: classes.dex */
public final class zzle {
    public static String zza(zzlc zzlcVar, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("# ");
        sb.append(str);
        zzd(zzlcVar, sb, 0);
        return sb.toString();
    }

    public static final void zzb(StringBuilder sb, int i, String str, Object obj) {
        if (obj instanceof List) {
            Iterator it = ((List) obj).iterator();
            while (it.hasNext()) {
                zzb(sb, i, str, it.next());
            }
            return;
        }
        if (obj instanceof Map) {
            Iterator it2 = ((Map) obj).entrySet().iterator();
            while (it2.hasNext()) {
                zzb(sb, i, str, (Map.Entry) it2.next());
            }
            return;
        }
        sb.append('\n');
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            sb.append(Ascii.CASE_MASK);
        }
        sb.append(str);
        if (obj instanceof String) {
            sb.append(": \"");
            sb.append(zzlz.zza(zzix.zzm((String) obj)));
            sb.append('\"');
            return;
        }
        if (obj instanceof zzix) {
            sb.append(": \"");
            sb.append(zzlz.zza((zzix) obj));
            sb.append('\"');
            return;
        }
        if (obj instanceof zzjx) {
            sb.append(" {");
            zzd((zzjx) obj, sb, i + 2);
            sb.append("\n");
            while (i2 < i) {
                sb.append(Ascii.CASE_MASK);
                i2++;
            }
            sb.append("}");
            return;
        }
        if (!(obj instanceof Map.Entry)) {
            sb.append(": ");
            sb.append(obj.toString());
            return;
        }
        sb.append(" {");
        Map.Entry entry = (Map.Entry) obj;
        int i4 = i + 2;
        zzb(sb, i4, "key", entry.getKey());
        zzb(sb, i4, "value", entry.getValue());
        sb.append("\n");
        while (i2 < i) {
            sb.append(Ascii.CASE_MASK);
            i2++;
        }
        sb.append("}");
    }

    public static final String zzc(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            if (Character.isUpperCase(cCharAt)) {
                sb.append("_");
            }
            sb.append(Character.toLowerCase(cCharAt));
        }
        return sb.toString();
    }

    public static void zzd(zzlc zzlcVar, StringBuilder sb, int i) {
        boolean zEquals;
        HashMap map = new HashMap();
        HashMap map2 = new HashMap();
        TreeSet<String> treeSet = new TreeSet();
        for (Method method : zzlcVar.getClass().getDeclaredMethods()) {
            map2.put(method.getName(), method);
            if (method.getParameterTypes().length == 0) {
                map.put(method.getName(), method);
                if (method.getName().startsWith("get")) {
                    treeSet.add(method.getName());
                }
            }
        }
        for (String str : treeSet) {
            String strSubstring = str.startsWith("get") ? str.substring(3) : str;
            if (strSubstring.endsWith(MessageLiteToString.LIST_SUFFIX) && !strSubstring.endsWith(MessageLiteToString.BUILDER_LIST_SUFFIX) && !strSubstring.equals(MessageLiteToString.LIST_SUFFIX)) {
                String strValueOf = String.valueOf(strSubstring.substring(0, 1).toLowerCase());
                String strValueOf2 = String.valueOf(strSubstring.substring(1, strSubstring.length() - 4));
                String strConcat = strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf);
                Method method2 = (Method) map.get(str);
                if (method2 != null && method2.getReturnType().equals(List.class)) {
                    zzb(sb, i, zzc(strConcat), zzjx.zzbE(method2, zzlcVar, new Object[0]));
                }
            }
            if (strSubstring.endsWith(MessageLiteToString.MAP_SUFFIX) && !strSubstring.equals(MessageLiteToString.MAP_SUFFIX)) {
                String strValueOf3 = String.valueOf(strSubstring.substring(0, 1).toLowerCase());
                String strValueOf4 = String.valueOf(strSubstring.substring(1, strSubstring.length() - 3));
                String strConcat2 = strValueOf4.length() != 0 ? strValueOf3.concat(strValueOf4) : new String(strValueOf3);
                Method method3 = (Method) map.get(str);
                if (method3 != null && method3.getReturnType().equals(Map.class) && !method3.isAnnotationPresent(Deprecated.class) && Modifier.isPublic(method3.getModifiers())) {
                    zzb(sb, i, zzc(strConcat2), zzjx.zzbE(method3, zzlcVar, new Object[0]));
                }
            }
            if (((Method) map2.get(strSubstring.length() != 0 ? "set".concat(strSubstring) : new String("set"))) != null) {
                if (strSubstring.endsWith(MessageLiteToString.BYTES_SUFFIX)) {
                    String strValueOf5 = String.valueOf(strSubstring.substring(0, strSubstring.length() - 5));
                    if (!map.containsKey(strValueOf5.length() != 0 ? "get".concat(strValueOf5) : new String("get"))) {
                    }
                }
                String strValueOf6 = String.valueOf(strSubstring.substring(0, 1).toLowerCase());
                String strValueOf7 = String.valueOf(strSubstring.substring(1));
                String strConcat3 = strValueOf7.length() != 0 ? strValueOf6.concat(strValueOf7) : new String(strValueOf6);
                Method method4 = (Method) map.get(strSubstring.length() != 0 ? "get".concat(strSubstring) : new String("get"));
                Method method5 = (Method) map.get(strSubstring.length() != 0 ? "has".concat(strSubstring) : new String("has"));
                if (method4 != null) {
                    Object objZzbE = zzjx.zzbE(method4, zzlcVar, new Object[0]);
                    if (method5 == null) {
                        if (objZzbE instanceof Boolean) {
                            if (((Boolean) objZzbE).booleanValue()) {
                                zzb(sb, i, zzc(strConcat3), objZzbE);
                            }
                        } else if (objZzbE instanceof Integer) {
                            if (((Integer) objZzbE).intValue() != 0) {
                                zzb(sb, i, zzc(strConcat3), objZzbE);
                            }
                        } else if (objZzbE instanceof Float) {
                            if (Float.floatToRawIntBits(((Float) objZzbE).floatValue()) != 0) {
                                zzb(sb, i, zzc(strConcat3), objZzbE);
                            }
                        } else if (!(objZzbE instanceof Double)) {
                            if (objZzbE instanceof String) {
                                zEquals = objZzbE.equals("");
                            } else if (objZzbE instanceof zzix) {
                                zEquals = objZzbE.equals(zzix.zzb);
                            } else if (objZzbE instanceof zzlc) {
                                if (objZzbE != ((zzlc) objZzbE).zzbL()) {
                                    zzb(sb, i, zzc(strConcat3), objZzbE);
                                }
                            } else if (!(objZzbE instanceof Enum) || ((Enum) objZzbE).ordinal() != 0) {
                                zzb(sb, i, zzc(strConcat3), objZzbE);
                            }
                            if (!zEquals) {
                                zzb(sb, i, zzc(strConcat3), objZzbE);
                            }
                        } else if (Double.doubleToRawLongBits(((Double) objZzbE).doubleValue()) != 0) {
                            zzb(sb, i, zzc(strConcat3), objZzbE);
                        }
                    } else if (((Boolean) zzjx.zzbE(method5, zzlcVar, new Object[0])).booleanValue()) {
                        zzb(sb, i, zzc(strConcat3), objZzbE);
                    }
                }
            }
        }
        if (zzlcVar instanceof zzju) {
            zzjo zzjoVar = ((zzju) zzlcVar).zza;
            throw null;
        }
        zzmc zzmcVar = ((zzjx) zzlcVar).zzc;
        if (zzmcVar != null) {
            zzmcVar.zzg(sb, i);
        }
    }
}
