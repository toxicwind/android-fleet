package com.google.firebase.firestore.bundle;

import android.util.Base64;
import androidx.annotation.Nullable;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.core.Bound;
import com.google.firebase.firestore.core.FieldFilter;
import com.google.firebase.firestore.core.Filter;
import com.google.firebase.firestore.core.OrderBy;
import com.google.firebase.firestore.core.Query;
import com.google.firebase.firestore.core.Target;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.ObjectValue;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.model.Values;
import com.google.firebase.firestore.remote.RemoteSerializer;
import com.google.firestore.v1.ArrayValue;
import com.google.firestore.v1.MapValue;
import com.google.firestore.v1.Value;
import com.google.gson.internal.bind.util.ISO8601Utils;
import com.google.protobuf.ByteString;
import com.google.protobuf.NullValue;
import com.google.type.LatLng;
import com.mediatek.twoworlds.tv.common.MtkTvConfigTypeBase;
import e.b.a;
import e.b.d;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes.dex */
public class BundleSerializer {
    public static final long MILLIS_PER_SECOND = 1000;
    public final RemoteSerializer remoteSerializer;
    public final SimpleDateFormat timestampFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);

    public BundleSerializer(RemoteSerializer remoteSerializer) {
        this.remoteSerializer = remoteSerializer;
        GregorianCalendar gregorianCalendar = new GregorianCalendar(TimeZone.getTimeZone(ISO8601Utils.UTC_ID));
        gregorianCalendar.setGregorianChange(new Date(Long.MIN_VALUE));
        this.timestampFormat.setCalendar(gregorianCalendar);
    }

    private void decodeArrayValue(Value.Builder builder, @Nullable a aVar) {
        ArrayValue.Builder builderNewBuilder = ArrayValue.newBuilder();
        if (aVar != null) {
            for (int i = 0; i < aVar.a(); i++) {
                builderNewBuilder.addValues(decodeValue(aVar.a(i)));
            }
        }
        builder.setArrayValue(builderNewBuilder);
    }

    private BundledQuery decodeBundledQuery(d dVar) {
        String strH;
        d dVarF = dVar.f("structuredQuery");
        verifyNoSelect(dVarF);
        ResourcePath resourcePathDecodeName = decodeName(dVar.h("parent"));
        a aVarE = dVarF.e("from");
        verifyCollectionSelector(aVarE);
        d dVarA = aVarE.a(0);
        if (dVarA.a("allDescendants", false)) {
            strH = dVarA.h("collectionId");
        } else {
            resourcePathDecodeName = resourcePathDecodeName.append(dVarA.h("collectionId"));
            strH = null;
        }
        List<Filter> listDecodeWhere = decodeWhere(dVarF.l("where"));
        List<OrderBy> listDecodeOrderBy = decodeOrderBy(dVarF.k("orderBy"));
        Bound boundDecodeStartAtBound = decodeStartAtBound(dVarF.l("startAt"));
        Bound boundDecodeEndAtBound = decodeEndAtBound(dVarF.l("endAt"));
        verifyNoOffset(dVarF);
        return new BundledQuery(new Target(resourcePathDecodeName, strH, listDecodeWhere, listDecodeOrderBy, decodeLimit(dVarF), boundDecodeStartAtBound, boundDecodeEndAtBound), decodeLimitType(dVar));
    }

    private void decodeCompositeFilter(List<Filter> list, d dVar) {
        if (!dVar.h("op").equals(MtkTvConfigTypeBase.S3166_CFG_COUNT_AND)) {
            throw new IllegalArgumentException("The Android SDK only supports composite filters of type 'AND'");
        }
        a aVarK = dVar.k("filters");
        if (aVarK != null) {
            for (int i = 0; i < aVarK.a(); i++) {
                decodeFilter(list, aVarK.a(i));
            }
        }
    }

    private Bound decodeEndAtBound(@Nullable d dVar) {
        if (dVar == null) {
            return null;
        }
        return new Bound(decodePosition(dVar), !dVar.a("before", false));
    }

    private void decodeFieldFilter(List<Filter> list, d dVar) {
        list.add(FieldFilter.create(decodeFieldReference(dVar.f("field")), FieldFilter.Operator.valueOf(dVar.h("op")), decodeValue(dVar.f("value"))));
    }

    private FieldFilter.Operator decodeFieldFilterOperator(String str) {
        return FieldFilter.Operator.valueOf(str);
    }

    private FieldPath decodeFieldReference(d dVar) {
        return FieldPath.fromServerFormat(dVar.h("fieldPath"));
    }

    private void decodeFilter(List<Filter> list, d dVar) {
        if (dVar.f2163b.containsKey("compositeFilter")) {
            decodeCompositeFilter(list, dVar.f("compositeFilter"));
        } else if (dVar.f2163b.containsKey("fieldFilter")) {
            decodeFieldFilter(list, dVar.f("fieldFilter"));
        } else if (dVar.f2163b.containsKey("unaryFilter")) {
            decodeUnaryFilter(list, dVar.f("unaryFilter"));
        }
    }

    private void decodeGeoPoint(Value.Builder builder, d dVar) {
        builder.setGeoPointValue(LatLng.newBuilder().setLatitude(dVar.a("latitude", Double.NaN)).setLongitude(dVar.a("longitude", Double.NaN)));
    }

    private int decodeLimit(d dVar) {
        d dVarL = dVar.l("limit");
        return dVarL != null ? dVarL.a("value", -1) : dVar.a("limit", -1);
    }

    private Query.LimitType decodeLimitType(d dVar) {
        String strA = dVar.a("limitType", "FIRST");
        if (strA.equals("FIRST")) {
            return Query.LimitType.LIMIT_TO_FIRST;
        }
        if (strA.equals("LAST")) {
            return Query.LimitType.LIMIT_TO_LAST;
        }
        throw new IllegalArgumentException(b.a.a.a.a.a("Invalid limit type for bundle query: ", strA));
    }

    private void decodeMapValue(Value.Builder builder, @Nullable d dVar) {
        MapValue.Builder builderNewBuilder = MapValue.newBuilder();
        if (dVar != null) {
            Iterator<String> itA = dVar.a();
            while (itA.hasNext()) {
                String next = itA.next();
                builderNewBuilder.putFields(next, decodeValue(dVar.f(next)));
            }
        }
        builder.setMapValue(builderNewBuilder);
    }

    private ResourcePath decodeName(String str) {
        ResourcePath resourcePathFromString = ResourcePath.fromString(str);
        if (this.remoteSerializer.isLocalResourceName(resourcePathFromString)) {
            return resourcePathFromString.popFirst(5);
        }
        throw new IllegalArgumentException(b.a.a.a.a.a("Resource name is not valid for current instance: ", str));
    }

    private List<OrderBy> decodeOrderBy(@Nullable a aVar) {
        ArrayList arrayList = new ArrayList();
        if (aVar != null) {
            for (int i = 0; i < aVar.a(); i++) {
                d dVarA = aVar.a(i);
                arrayList.add(new OrderBy(dVarA.a("direction", "ASCENDING").equals("ASCENDING") ? OrderBy.Direction.ASCENDING : OrderBy.Direction.DESCENDING, decodeFieldReference(dVarA.f("field"))));
            }
        }
        return arrayList;
    }

    private List<Value> decodePosition(d dVar) {
        ArrayList arrayList = new ArrayList();
        a aVarK = dVar.k("values");
        if (aVarK != null) {
            for (int i = 0; i < aVarK.a(); i++) {
                arrayList.add(decodeValue(aVarK.a(i)));
            }
        }
        return arrayList;
    }

    private SnapshotVersion decodeSnapshotVersion(Object obj) {
        return new SnapshotVersion(decodeTimestamp(obj));
    }

    private Bound decodeStartAtBound(@Nullable d dVar) {
        if (dVar == null) {
            return null;
        }
        return new Bound(decodePosition(dVar), dVar.a("before", false));
    }

    private Timestamp decodeTimestamp(d dVar) {
        return new Timestamp(dVar.a("seconds", 0L), dVar.a("nanos", 0));
    }

    public static long decodeTimezoneOffset(String str) {
        int iIndexOf = str.indexOf(58);
        if (iIndexOf == -1) {
            throw new IllegalArgumentException(b.a.a.a.a.a("Invalid offset value: ", str));
        }
        String strSubstring = str.substring(0, iIndexOf);
        String strSubstring2 = str.substring(iIndexOf + 1);
        return (Long.parseLong(strSubstring2) + (Long.parseLong(strSubstring) * 60)) * 60;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:17:0x0043  */
    private void decodeUnaryFilter(List<Filter> list, d dVar) {
        byte b2;
        FieldPath fieldPathDecodeFieldReference = decodeFieldReference(dVar.f("field"));
        String strH = dVar.h("op");
        switch (strH) {
            case "IS_NAN":
                b2 = 0;
                break;
            case "IS_NULL":
                b2 = 1;
                break;
            case "IS_NOT_NAN":
                b2 = 2;
                break;
            case "IS_NOT_NULL":
                b2 = 3;
                break;
            default:
                b2 = -1;
                break;
        }
        if (b2 == 0) {
            list.add(FieldFilter.create(fieldPathDecodeFieldReference, FieldFilter.Operator.EQUAL, Values.NAN_VALUE));
            return;
        }
        if (b2 == 1) {
            list.add(FieldFilter.create(fieldPathDecodeFieldReference, FieldFilter.Operator.EQUAL, Values.NULL_VALUE));
        } else if (b2 == 2) {
            list.add(FieldFilter.create(fieldPathDecodeFieldReference, FieldFilter.Operator.NOT_EQUAL, Values.NAN_VALUE));
        } else {
            if (b2 != 3) {
                throw new IllegalArgumentException(b.a.a.a.a.a("Unexpected unary filter: ", strH));
            }
            list.add(FieldFilter.create(fieldPathDecodeFieldReference, FieldFilter.Operator.NOT_EQUAL, Values.NULL_VALUE));
        }
    }

    private Value decodeValue(d dVar) {
        Value.Builder builderNewBuilder = Value.newBuilder();
        if (dVar.f2163b.containsKey("nullValue")) {
            builderNewBuilder.setNullValue(NullValue.NULL_VALUE);
        } else if (dVar.f2163b.containsKey("booleanValue")) {
            builderNewBuilder.setBooleanValue(dVar.a("booleanValue", false));
        } else if (dVar.f2163b.containsKey("integerValue")) {
            builderNewBuilder.setIntegerValue(dVar.a("integerValue", 0L));
        } else if (dVar.f2163b.containsKey("doubleValue")) {
            builderNewBuilder.setDoubleValue(dVar.a("doubleValue", Double.NaN));
        } else if (dVar.f2163b.containsKey("timestampValue")) {
            decodeTimestamp(builderNewBuilder, dVar.a("timestampValue"));
        } else if (dVar.f2163b.containsKey("stringValue")) {
            builderNewBuilder.setStringValue(dVar.a("stringValue", ""));
        } else if (dVar.f2163b.containsKey("bytesValue")) {
            builderNewBuilder.setBytesValue(ByteString.copyFrom(Base64.decode(dVar.h("bytesValue"), 0)));
        } else if (dVar.f2163b.containsKey("referenceValue")) {
            builderNewBuilder.setReferenceValue(dVar.h("referenceValue"));
        } else if (dVar.f2163b.containsKey("geoPointValue")) {
            decodeGeoPoint(builderNewBuilder, dVar.f("geoPointValue"));
        } else if (dVar.f2163b.containsKey("arrayValue")) {
            decodeArrayValue(builderNewBuilder, dVar.f("arrayValue").k("values"));
        } else {
            if (!dVar.f2163b.containsKey("mapValue")) {
                throw new IllegalArgumentException(b.a.a.a.a.a("Unexpected value type: ", dVar));
            }
            decodeMapValue(builderNewBuilder, dVar.f("mapValue").l("fields"));
        }
        return builderNewBuilder.build();
    }

    private List<Filter> decodeWhere(@Nullable d dVar) {
        ArrayList arrayList = new ArrayList();
        if (dVar != null) {
            decodeFilter(arrayList, dVar);
        }
        return arrayList;
    }

    public static int parseNanos(String str) {
        int iCharAt = 0;
        for (int i = 0; i < 9; i++) {
            iCharAt *= 10;
            if (i < str.length()) {
                if (str.charAt(i) < '0' || str.charAt(i) > '9') {
                    throw new IllegalArgumentException(b.a.a.a.a.a("Invalid nanoseconds: ", str));
                }
                iCharAt = (str.charAt(i) - '0') + iCharAt;
            }
        }
        return iCharAt;
    }

    private void verifyCollectionSelector(a aVar) {
        if (aVar.a() != 1) {
            throw new IllegalArgumentException("Only queries with a single 'from' clause are supported by the Android SDK");
        }
    }

    private void verifyNoOffset(d dVar) {
        if (dVar.f2163b.containsKey("offset")) {
            throw new IllegalArgumentException("Queries with offsets are not supported by the Android SDK");
        }
    }

    private void verifyNoSelect(d dVar) {
        if (dVar.f2163b.containsKey("select")) {
            throw new IllegalArgumentException("Queries with 'select' statements are not supported by the Android SDK");
        }
    }

    public BundleMetadata decodeBundleMetadata(d dVar) {
        return new BundleMetadata(dVar.h("id"), dVar.d("version"), decodeSnapshotVersion(dVar.a("createTime")), dVar.d("totalDocuments"), dVar.g("totalBytes"));
    }

    public BundledDocumentMetadata decodeBundledDocumentMetadata(d dVar) {
        DocumentKey documentKey = new DocumentKey(decodeName(dVar.h("name")));
        SnapshotVersion snapshotVersionDecodeSnapshotVersion = decodeSnapshotVersion(dVar.a("readTime"));
        boolean zA = dVar.a("exists", false);
        a aVarK = dVar.k("queries");
        ArrayList arrayList = new ArrayList();
        if (aVarK != null) {
            for (int i = 0; i < aVarK.a(); i++) {
                arrayList.add(aVarK.b(i));
            }
        }
        return new BundledDocumentMetadata(documentKey, snapshotVersionDecodeSnapshotVersion, zA, arrayList);
    }

    public BundleDocument decodeDocument(d dVar) {
        DocumentKey documentKey = new DocumentKey(decodeName(dVar.h("name")));
        SnapshotVersion snapshotVersionDecodeSnapshotVersion = decodeSnapshotVersion(dVar.a("updateTime"));
        Value.Builder builderNewBuilder = Value.newBuilder();
        decodeMapValue(builderNewBuilder, dVar.f("fields"));
        return new BundleDocument(MutableDocument.newFoundDocument(documentKey, snapshotVersionDecodeSnapshotVersion, ObjectValue.fromMap(builderNewBuilder.getMapValue().getFieldsMap())));
    }

    public NamedQuery decodeNamedQuery(d dVar) {
        return new NamedQuery(dVar.h("name"), decodeBundledQuery(dVar.f("bundledQuery")), decodeSnapshotVersion(dVar.a("readTime")));
    }

    private Timestamp decodeTimestamp(String str) {
        String strSubstring;
        try {
            int iIndexOf = str.indexOf(84);
            if (iIndexOf != -1) {
                int iIndexOf2 = str.indexOf(90, iIndexOf);
                if (iIndexOf2 == -1) {
                    iIndexOf2 = str.indexOf(43, iIndexOf);
                }
                if (iIndexOf2 == -1) {
                    iIndexOf2 = str.indexOf(45, iIndexOf);
                }
                if (iIndexOf2 != -1) {
                    int nanos = 0;
                    String strSubstring2 = str.substring(0, iIndexOf2);
                    String strSubstring3 = "";
                    int iIndexOf3 = strSubstring2.indexOf(46);
                    if (iIndexOf3 != -1) {
                        strSubstring = strSubstring2.substring(0, iIndexOf3);
                        strSubstring3 = strSubstring2.substring(iIndexOf3 + 1);
                    } else {
                        strSubstring = strSubstring2;
                    }
                    long time = this.timestampFormat.parse(strSubstring).getTime() / 1000;
                    if (!strSubstring3.isEmpty()) {
                        nanos = parseNanos(strSubstring3);
                    }
                    if (str.charAt(iIndexOf2) == 'Z') {
                        if (str.length() != iIndexOf2 + 1) {
                            throw new IllegalArgumentException("Invalid timestamp: Invalid trailing data \"" + str.substring(iIndexOf2) + "\"");
                        }
                    } else {
                        long jDecodeTimezoneOffset = decodeTimezoneOffset(str.substring(iIndexOf2 + 1));
                        time = str.charAt(iIndexOf2) == '+' ? time - jDecodeTimezoneOffset : time + jDecodeTimezoneOffset;
                    }
                    return new Timestamp(time, nanos);
                }
                throw new IllegalArgumentException("Invalid timestamp: Missing valid timezone offset: " + str);
            }
            throw new IllegalArgumentException("Invalid timestamp: " + str);
        } catch (ParseException e2) {
            throw new IllegalArgumentException("Failed to parse timestamp", e2);
        }
    }

    private Timestamp decodeTimestamp(Object obj) {
        if (obj instanceof String) {
            return decodeTimestamp((String) obj);
        }
        if (obj instanceof d) {
            return decodeTimestamp((d) obj);
        }
        throw new IllegalArgumentException("Timestamps must be either ISO 8601-formatted strings or JSON objects");
    }

    private void decodeTimestamp(Value.Builder builder, Object obj) {
        Timestamp timestampDecodeTimestamp = decodeTimestamp(obj);
        builder.setTimestampValue(com.google.protobuf.Timestamp.newBuilder().setSeconds(timestampDecodeTimestamp.getSeconds()).setNanos(timestampDecodeTimestamp.getNanoseconds()));
    }
}
