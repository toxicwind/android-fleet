package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.util.Log;
import b.a.a.a.a;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.common.util.JsonUtils;
import com.google.android.gms.common.util.MapUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.firebase.installations.local.IidStore;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
@ShowFirstParty
@KeepForSdk
public abstract class FastJsonResponse {

    @VisibleForTesting
    @SafeParcelable.Class(creator = "FieldCreator")
    @ShowFirstParty
    @KeepForSdk
    public static class Field<I, O> extends AbstractSafeParcelable {
        public static final zai CREATOR = new zai();

        @SafeParcelable.VersionField(getter = "getVersionCode", id = 1)
        public final int zali;

        @SafeParcelable.Field(getter = "getTypeIn", id = 2)
        public final int zaqf;

        @SafeParcelable.Field(getter = "isTypeInArray", id = 3)
        public final boolean zaqg;

        @SafeParcelable.Field(getter = "getTypeOut", id = 4)
        public final int zaqh;

        @SafeParcelable.Field(getter = "isTypeOutArray", id = 5)
        public final boolean zaqi;

        @SafeParcelable.Field(getter = "getOutputFieldName", id = 6)
        public final String zaqj;

        @SafeParcelable.Field(getter = "getSafeParcelableFieldId", id = 7)
        public final int zaqk;
        public final Class<? extends FastJsonResponse> zaql;

        @SafeParcelable.Field(getter = "getConcreteTypeName", id = 8)
        public final String zaqm;
        public zaj zaqn;

        @SafeParcelable.Field(getter = "getWrappedConverter", id = 9, type = "com.google.android.gms.common.server.converter.ConverterWrapper")
        public FieldConverter<I, O> zaqo;

        @SafeParcelable.Constructor
        public Field(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) int i2, @SafeParcelable.Param(id = 3) boolean z, @SafeParcelable.Param(id = 4) int i3, @SafeParcelable.Param(id = 5) boolean z2, @SafeParcelable.Param(id = 6) String str, @SafeParcelable.Param(id = 7) int i4, @SafeParcelable.Param(id = 8) String str2, @SafeParcelable.Param(id = 9) com.google.android.gms.common.server.converter.zab zabVar) {
            this.zali = i;
            this.zaqf = i2;
            this.zaqg = z;
            this.zaqh = i3;
            this.zaqi = z2;
            this.zaqj = str;
            this.zaqk = i4;
            if (str2 == null) {
                this.zaql = null;
                this.zaqm = null;
            } else {
                this.zaql = SafeParcelResponse.class;
                this.zaqm = str2;
            }
            if (zabVar == null) {
                this.zaqo = null;
            } else {
                this.zaqo = (FieldConverter<I, O>) zabVar.zacg();
            }
        }

        @VisibleForTesting
        @KeepForSdk
        public static Field<byte[], byte[]> forBase64(String str, int i) {
            return new Field<>(8, false, 8, false, str, i, null, null);
        }

        @KeepForSdk
        public static Field<Boolean, Boolean> forBoolean(String str, int i) {
            return new Field<>(6, false, 6, false, str, i, null, null);
        }

        @KeepForSdk
        public static <T extends FastJsonResponse> Field<T, T> forConcreteType(String str, int i, Class<T> cls) {
            return new Field<>(11, false, 11, false, str, i, cls, null);
        }

        @KeepForSdk
        public static <T extends FastJsonResponse> Field<ArrayList<T>, ArrayList<T>> forConcreteTypeArray(String str, int i, Class<T> cls) {
            return new Field<>(11, true, 11, true, str, i, cls, null);
        }

        @KeepForSdk
        public static Field<Double, Double> forDouble(String str, int i) {
            return new Field<>(4, false, 4, false, str, i, null, null);
        }

        @KeepForSdk
        public static Field<Float, Float> forFloat(String str, int i) {
            return new Field<>(3, false, 3, false, str, i, null, null);
        }

        @VisibleForTesting
        @KeepForSdk
        public static Field<Integer, Integer> forInteger(String str, int i) {
            return new Field<>(0, false, 0, false, str, i, null, null);
        }

        @KeepForSdk
        public static Field<Long, Long> forLong(String str, int i) {
            return new Field<>(2, false, 2, false, str, i, null, null);
        }

        @KeepForSdk
        public static Field<String, String> forString(String str, int i) {
            return new Field<>(7, false, 7, false, str, i, null, null);
        }

        @KeepForSdk
        public static Field<HashMap<String, String>, HashMap<String, String>> forStringMap(String str, int i) {
            return new Field<>(10, false, 10, false, str, i, null, null);
        }

        @KeepForSdk
        public static Field<ArrayList<String>, ArrayList<String>> forStrings(String str, int i) {
            return new Field<>(7, true, 7, true, str, i, null, null);
        }

        @KeepForSdk
        public static Field withConverter(String str, int i, FieldConverter<?, ?> fieldConverter, boolean z) {
            return new Field(fieldConverter.zach(), z, fieldConverter.zaci(), false, str, i, null, fieldConverter);
        }

        private final String zack() {
            String str = this.zaqm;
            if (str == null) {
                return null;
            }
            return str;
        }

        private final com.google.android.gms.common.server.converter.zab zacm() {
            FieldConverter<I, O> fieldConverter = this.zaqo;
            if (fieldConverter == null) {
                return null;
            }
            return com.google.android.gms.common.server.converter.zab.zaa(fieldConverter);
        }

        public final O convert(I i) {
            return this.zaqo.convert(i);
        }

        public final I convertBack(O o) {
            return this.zaqo.convertBack(o);
        }

        @KeepForSdk
        public int getSafeParcelableFieldId() {
            return this.zaqk;
        }

        public String toString() {
            Objects.ToStringHelper toStringHelperAdd = Objects.toStringHelper(this).add("versionCode", Integer.valueOf(this.zali)).add("typeIn", Integer.valueOf(this.zaqf)).add("typeInArray", Boolean.valueOf(this.zaqg)).add("typeOut", Integer.valueOf(this.zaqh)).add("typeOutArray", Boolean.valueOf(this.zaqi)).add("outputFieldName", this.zaqj).add("safeParcelFieldId", Integer.valueOf(this.zaqk)).add("concreteTypeName", zack());
            Class<? extends FastJsonResponse> cls = this.zaql;
            if (cls != null) {
                toStringHelperAdd.add("concreteType.class", cls.getCanonicalName());
            }
            FieldConverter<I, O> fieldConverter = this.zaqo;
            if (fieldConverter != null) {
                toStringHelperAdd.add("converterName", fieldConverter.getClass().getCanonicalName());
            }
            return toStringHelperAdd.toString();
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            int i2 = this.zali;
            parcel.writeInt(262145);
            parcel.writeInt(i2);
            int i3 = this.zaqf;
            parcel.writeInt(262146);
            parcel.writeInt(i3);
            boolean z = this.zaqg;
            parcel.writeInt(262147);
            parcel.writeInt(z ? 1 : 0);
            int i4 = this.zaqh;
            parcel.writeInt(262148);
            parcel.writeInt(i4);
            boolean z2 = this.zaqi;
            parcel.writeInt(262149);
            parcel.writeInt(z2 ? 1 : 0);
            SafeParcelWriter.writeString(parcel, 6, this.zaqj, false);
            int safeParcelableFieldId = getSafeParcelableFieldId();
            parcel.writeInt(262151);
            parcel.writeInt(safeParcelableFieldId);
            SafeParcelWriter.writeString(parcel, 8, zack(), false);
            SafeParcelWriter.writeParcelable(parcel, 9, zacm(), i, false);
            SafeParcelWriter.zzb(parcel, iBeginObjectHeader);
        }

        public final void zaa(zaj zajVar) {
            this.zaqn = zajVar;
        }

        public final Field<I, O> zacj() {
            return new Field<>(this.zali, this.zaqf, this.zaqg, this.zaqh, this.zaqi, this.zaqj, this.zaqk, this.zaqm, zacm());
        }

        public final boolean zacl() {
            return this.zaqo != null;
        }

        public final FastJsonResponse zacn() {
            Class<? extends FastJsonResponse> cls = this.zaql;
            if (cls != SafeParcelResponse.class) {
                return cls.newInstance();
            }
            Preconditions.checkNotNull(this.zaqn, "The field mapping dictionary must be set if the concrete type is a SafeParcelResponse object.");
            return new SafeParcelResponse(this.zaqn, this.zaqm);
        }

        public final Map<String, Field<?, ?>> zaco() {
            Preconditions.checkNotNull(this.zaqm);
            Preconditions.checkNotNull(this.zaqn);
            return this.zaqn.zai(this.zaqm);
        }

        public Field(int i, boolean z, int i2, boolean z2, String str, int i3, Class<? extends FastJsonResponse> cls, FieldConverter<I, O> fieldConverter) {
            this.zali = 1;
            this.zaqf = i;
            this.zaqg = z;
            this.zaqh = i2;
            this.zaqi = z2;
            this.zaqj = str;
            this.zaqk = i3;
            this.zaql = cls;
            if (cls == null) {
                this.zaqm = null;
            } else {
                this.zaqm = cls.getCanonicalName();
            }
            this.zaqo = fieldConverter;
        }
    }

    @ShowFirstParty
    public interface FieldConverter<I, O> {
        O convert(I i);

        I convertBack(O o);

        int zach();

        int zaci();
    }

    private final <I, O> void zaa(Field<I, O> field, I i) {
        String str = field.zaqj;
        O oConvert = field.convert(i);
        int i2 = field.zaqh;
        switch (i2) {
            case 0:
                if (zaa(str, oConvert)) {
                    setIntegerInternal(field, str, ((Integer) oConvert).intValue());
                    return;
                }
                return;
            case 1:
                zaa((Field<?, ?>) field, str, (BigInteger) oConvert);
                return;
            case 2:
                if (zaa(str, oConvert)) {
                    setLongInternal(field, str, ((Long) oConvert).longValue());
                    return;
                }
                return;
            case 3:
            default:
                throw new IllegalStateException(a.a(44, "Unsupported type for conversion: ", i2));
            case 4:
                if (zaa(str, oConvert)) {
                    zaa((Field<?, ?>) field, str, ((Double) oConvert).doubleValue());
                    return;
                }
                return;
            case 5:
                zaa((Field<?, ?>) field, str, (BigDecimal) oConvert);
                return;
            case 6:
                if (zaa(str, oConvert)) {
                    setBooleanInternal(field, str, ((Boolean) oConvert).booleanValue());
                    return;
                }
                return;
            case 7:
                setStringInternal(field, str, (String) oConvert);
                return;
            case 8:
            case 9:
                if (zaa(str, oConvert)) {
                    setDecodedBytesInternal(field, str, (byte[]) oConvert);
                    return;
                }
                return;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <O, I> I zab(Field<I, O> field, Object obj) {
        return field.zaqo != null ? field.convertBack(obj) : obj;
    }

    @KeepForSdk
    public <T extends FastJsonResponse> void addConcreteTypeArrayInternal(Field<?, ?> field, String str, ArrayList<T> arrayList) {
        throw new UnsupportedOperationException("Concrete type array not supported");
    }

    @KeepForSdk
    public <T extends FastJsonResponse> void addConcreteTypeInternal(Field<?, ?> field, String str, T t) {
        throw new UnsupportedOperationException("Concrete type not supported");
    }

    @KeepForSdk
    public abstract Map<String, Field<?, ?>> getFieldMappings();

    @KeepForSdk
    public Object getFieldValue(Field field) {
        String str = field.zaqj;
        if (field.zaql == null) {
            return getValueObject(str);
        }
        Preconditions.checkState(getValueObject(str) == null, "Concrete field shouldn't be value object: %s", field.zaqj);
        boolean z = field.zaqi;
        try {
            char upperCase = Character.toUpperCase(str.charAt(0));
            String strSubstring = str.substring(1);
            StringBuilder sb = new StringBuilder(String.valueOf(strSubstring).length() + 4);
            sb.append("get");
            sb.append(upperCase);
            sb.append(strSubstring);
            return getClass().getMethod(sb.toString(), new Class[0]).invoke(this, new Object[0]);
        } catch (Exception e2) {
            throw new RuntimeException(e2);
        }
    }

    @KeepForSdk
    public abstract Object getValueObject(String str);

    @KeepForSdk
    public boolean isFieldSet(Field field) {
        if (field.zaqh != 11) {
            return isPrimitiveFieldSet(field.zaqj);
        }
        if (field.zaqi) {
            String str = field.zaqj;
            throw new UnsupportedOperationException("Concrete type arrays not supported");
        }
        String str2 = field.zaqj;
        throw new UnsupportedOperationException("Concrete types not supported");
    }

    @KeepForSdk
    public abstract boolean isPrimitiveFieldSet(String str);

    @KeepForSdk
    public void setBooleanInternal(Field<?, ?> field, String str, boolean z) {
        throw new UnsupportedOperationException("Boolean not supported");
    }

    @KeepForSdk
    public void setDecodedBytesInternal(Field<?, ?> field, String str, byte[] bArr) {
        throw new UnsupportedOperationException("byte[] not supported");
    }

    @KeepForSdk
    public void setIntegerInternal(Field<?, ?> field, String str, int i) {
        throw new UnsupportedOperationException("Integer not supported");
    }

    @KeepForSdk
    public void setLongInternal(Field<?, ?> field, String str, long j) {
        throw new UnsupportedOperationException("Long not supported");
    }

    @KeepForSdk
    public void setStringInternal(Field<?, ?> field, String str, String str2) {
        throw new UnsupportedOperationException("String not supported");
    }

    @KeepForSdk
    public void setStringMapInternal(Field<?, ?> field, String str, Map<String, String> map) {
        throw new UnsupportedOperationException("String map not supported");
    }

    @KeepForSdk
    public void setStringsInternal(Field<?, ?> field, String str, ArrayList<String> arrayList) {
        throw new UnsupportedOperationException("String list not supported");
    }

    @KeepForSdk
    public String toString() {
        Map<String, Field<?, ?>> fieldMappings = getFieldMappings();
        StringBuilder sb = new StringBuilder(100);
        for (String str : fieldMappings.keySet()) {
            Field<?, ?> field = fieldMappings.get(str);
            if (isFieldSet(field)) {
                Object objZab = zab(field, getFieldValue(field));
                if (sb.length() == 0) {
                    sb.append(IidStore.JSON_ENCODED_PREFIX);
                } else {
                    sb.append(",");
                }
                sb.append("\"");
                sb.append(str);
                sb.append("\":");
                if (objZab != null) {
                    switch (field.zaqh) {
                        case 8:
                            sb.append("\"");
                            sb.append(Base64Utils.encode((byte[]) objZab));
                            sb.append("\"");
                            break;
                        case 9:
                            sb.append("\"");
                            sb.append(Base64Utils.encodeUrlSafe((byte[]) objZab));
                            sb.append("\"");
                            break;
                        case 10:
                            MapUtils.writeStringMapToJson(sb, (HashMap) objZab);
                            break;
                        default:
                            if (field.zaqg) {
                                ArrayList arrayList = (ArrayList) objZab;
                                sb.append("[");
                                int size = arrayList.size();
                                for (int i = 0; i < size; i++) {
                                    if (i > 0) {
                                        sb.append(",");
                                    }
                                    Object obj = arrayList.get(i);
                                    if (obj != null) {
                                        zaa(sb, field, obj);
                                    }
                                }
                                sb.append("]");
                            } else {
                                zaa(sb, field, objZab);
                            }
                            break;
                    }
                } else {
                    sb.append("null");
                }
            }
        }
        if (sb.length() > 0) {
            sb.append("}");
        } else {
            sb.append("{}");
        }
        return sb.toString();
    }

    public final <O> void zac(Field<ArrayList<Long>, O> field, ArrayList<Long> arrayList) {
        if (field.zaqo != null) {
            zaa(field, arrayList);
        } else {
            zac(field, field.zaqj, arrayList);
        }
    }

    public final <O> void zad(Field<ArrayList<Float>, O> field, ArrayList<Float> arrayList) {
        if (field.zaqo != null) {
            zaa(field, arrayList);
        } else {
            zad(field, field.zaqj, arrayList);
        }
    }

    public final <O> void zae(Field<ArrayList<Double>, O> field, ArrayList<Double> arrayList) {
        if (field.zaqo != null) {
            zaa(field, arrayList);
        } else {
            zae(field, field.zaqj, arrayList);
        }
    }

    public final <O> void zaf(Field<ArrayList<BigDecimal>, O> field, ArrayList<BigDecimal> arrayList) {
        if (field.zaqo != null) {
            zaa(field, arrayList);
        } else {
            zaf(field, field.zaqj, arrayList);
        }
    }

    public final <O> void zag(Field<ArrayList<Boolean>, O> field, ArrayList<Boolean> arrayList) {
        if (field.zaqo != null) {
            zaa(field, arrayList);
        } else {
            zag(field, field.zaqj, arrayList);
        }
    }

    public final <O> void zah(Field<ArrayList<String>, O> field, ArrayList<String> arrayList) {
        if (field.zaqo != null) {
            zaa(field, arrayList);
        } else {
            setStringsInternal(field, field.zaqj, arrayList);
        }
    }

    public final <O> void zab(Field<ArrayList<BigInteger>, O> field, ArrayList<BigInteger> arrayList) {
        if (field.zaqo != null) {
            zaa(field, arrayList);
        } else {
            zab(field, field.zaqj, arrayList);
        }
    }

    public void zac(Field<?, ?> field, String str, ArrayList<Long> arrayList) {
        throw new UnsupportedOperationException("Long list not supported");
    }

    public void zad(Field<?, ?> field, String str, ArrayList<Float> arrayList) {
        throw new UnsupportedOperationException("Float list not supported");
    }

    public void zae(Field<?, ?> field, String str, ArrayList<Double> arrayList) {
        throw new UnsupportedOperationException("Double list not supported");
    }

    public void zaf(Field<?, ?> field, String str, ArrayList<BigDecimal> arrayList) {
        throw new UnsupportedOperationException("BigDecimal list not supported");
    }

    public void zag(Field<?, ?> field, String str, ArrayList<Boolean> arrayList) {
        throw new UnsupportedOperationException("Boolean list not supported");
    }

    public void zab(Field<?, ?> field, String str, ArrayList<BigInteger> arrayList) {
        throw new UnsupportedOperationException("BigInteger list not supported");
    }

    public final <O> void zaa(Field<Integer, O> field, int i) {
        if (field.zaqo != null) {
            zaa(field, Integer.valueOf(i));
        } else {
            setIntegerInternal(field, field.zaqj, i);
        }
    }

    public final <O> void zaa(Field<ArrayList<Integer>, O> field, ArrayList<Integer> arrayList) {
        if (field.zaqo != null) {
            zaa(field, arrayList);
        } else {
            zaa(field, field.zaqj, arrayList);
        }
    }

    public final <O> void zaa(Field<BigInteger, O> field, BigInteger bigInteger) {
        if (field.zaqo != null) {
            zaa(field, bigInteger);
        } else {
            zaa(field, field.zaqj, bigInteger);
        }
    }

    public final <O> void zaa(Field<Long, O> field, long j) {
        if (field.zaqo != null) {
            zaa(field, Long.valueOf(j));
        } else {
            setLongInternal(field, field.zaqj, j);
        }
    }

    public final <O> void zaa(Field<Float, O> field, float f2) {
        if (field.zaqo != null) {
            zaa(field, Float.valueOf(f2));
        } else {
            zaa((Field<?, ?>) field, field.zaqj, f2);
        }
    }

    public final <O> void zaa(Field<Double, O> field, double d2) {
        if (field.zaqo != null) {
            zaa(field, Double.valueOf(d2));
        } else {
            zaa(field, field.zaqj, d2);
        }
    }

    public final <O> void zaa(Field<BigDecimal, O> field, BigDecimal bigDecimal) {
        if (field.zaqo != null) {
            zaa(field, bigDecimal);
        } else {
            zaa(field, field.zaqj, bigDecimal);
        }
    }

    public final <O> void zaa(Field<Boolean, O> field, boolean z) {
        if (field.zaqo != null) {
            zaa(field, Boolean.valueOf(z));
        } else {
            setBooleanInternal(field, field.zaqj, z);
        }
    }

    public final <O> void zaa(Field<String, O> field, String str) {
        if (field.zaqo != null) {
            zaa(field, str);
        } else {
            setStringInternal(field, field.zaqj, str);
        }
    }

    public final <O> void zaa(Field<byte[], O> field, byte[] bArr) {
        if (field.zaqo != null) {
            zaa(field, bArr);
        } else {
            setDecodedBytesInternal(field, field.zaqj, bArr);
        }
    }

    public final <O> void zaa(Field<Map<String, String>, O> field, Map<String, String> map) {
        if (field.zaqo != null) {
            zaa(field, map);
        } else {
            setStringMapInternal(field, field.zaqj, map);
        }
    }

    public void zaa(Field<?, ?> field, String str, ArrayList<Integer> arrayList) {
        throw new UnsupportedOperationException("Integer list not supported");
    }

    public void zaa(Field<?, ?> field, String str, BigInteger bigInteger) {
        throw new UnsupportedOperationException("BigInteger not supported");
    }

    public void zaa(Field<?, ?> field, String str, float f2) {
        throw new UnsupportedOperationException("Float not supported");
    }

    public void zaa(Field<?, ?> field, String str, double d2) {
        throw new UnsupportedOperationException("Double not supported");
    }

    public void zaa(Field<?, ?> field, String str, BigDecimal bigDecimal) {
        throw new UnsupportedOperationException("BigDecimal not supported");
    }

    public static <O> boolean zaa(String str, O o) {
        if (o != null) {
            return true;
        }
        if (!Log.isLoggable("FastJsonResponse", 6)) {
            return false;
        }
        StringBuilder sb = new StringBuilder(a.a((Object) str, 58));
        sb.append("Output field (");
        sb.append(str);
        sb.append(") has a null value, but expected a primitive");
        Log.e("FastJsonResponse", sb.toString());
        return false;
    }

    public static void zaa(StringBuilder sb, Field field, Object obj) {
        int i = field.zaqf;
        if (i == 11) {
            sb.append(field.zaql.cast(obj).toString());
        } else {
            if (i == 7) {
                sb.append("\"");
                sb.append(JsonUtils.escapeString((String) obj));
                sb.append("\"");
                return;
            }
            sb.append(obj);
        }
    }
}
