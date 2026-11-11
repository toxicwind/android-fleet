package com.lagradost.cloudstream3.utils;

import android.content.Context;
import android.util.Log;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: TextUtil.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \t2\u00020\u0001:\u0003\t\n\u000bB\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007J\u000e\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007\u0082\u0001\u0002\f\r¨\u0006\u000e"}, d2 = {"Lcom/lagradost/cloudstream3/utils/UiText;", "", "<init>", "()V", "asStringNull", "", "context", "Landroid/content/Context;", "asString", "Companion", "DynamicString", "StringResource", "Lcom/lagradost/cloudstream3/utils/UiText$DynamicString;", "Lcom/lagradost/cloudstream3/utils/UiText$StringResource;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class UiText {
    public static final String TAG = "UiText";

    public /* synthetic */ UiText(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private UiText() {
    }

    /* JADX INFO: compiled from: TextUtil.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\n\u0010\b\u001a\u00020\u0003H\u0096\u0080\u0004J\u0014\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0096\u0082\u0004J\n\u0010\r\u001a\u00020\u000eH\u0096\u0080\u0004J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/lagradost/cloudstream3/utils/UiText$DynamicString;", "Lcom/lagradost/cloudstream3/utils/UiText;", ES6Iterator.VALUE_PROPERTY, "", "<init>", "(Ljava/lang/String;)V", "getValue", "()Ljava/lang/String;", "toString", "equals", "", "other", "", "hashCode", "", "component1", "copy", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class DynamicString extends UiText {
        private final String value;

        public static /* synthetic */ DynamicString copy$default(DynamicString dynamicString, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = dynamicString.value;
            }
            return dynamicString.copy(str);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getValue() {
            return this.value;
        }

        public final DynamicString copy(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            return new DynamicString(value);
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public DynamicString(String value) {
            super(null);
            Intrinsics.checkNotNullParameter(value, "value");
            this.value = value;
        }

        public final String getValue() {
            return this.value;
        }

        public String toString() {
            return this.value;
        }

        public boolean equals(Object other) {
            if (other instanceof DynamicString) {
                return Intrinsics.areEqual(this.value, ((DynamicString) other).value);
            }
            return false;
        }

        public int hashCode() {
            return this.value.hashCode();
        }
    }

    /* JADX INFO: compiled from: TextUtil.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u001f\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\n\u0010\r\u001a\u00020\u000eH\u0096\u0080\u0004J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0006H\u0096\u0082\u0004J\n\u0010\u0012\u001a\u00020\u0003H\u0096\u0080\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0013"}, d2 = {"Lcom/lagradost/cloudstream3/utils/UiText$StringResource;", "Lcom/lagradost/cloudstream3/utils/UiText;", "resId", "", "args", "", "", "<init>", "(ILjava/util/List;)V", "getResId", "()I", "getArgs", "()Ljava/util/List;", "toString", "", "equals", "", "other", "hashCode", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class StringResource extends UiText {
        private final List<Object> args;
        private final int resId;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public StringResource(int i, List<? extends Object> args) {
            super(null);
            Intrinsics.checkNotNullParameter(args, "args");
            this.resId = i;
            this.args = args;
        }

        public final int getResId() {
            return this.resId;
        }

        public final List<Object> getArgs() {
            return this.args;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("resId = ");
            sb.append(this.resId);
            sb.append("\nargs = ");
            List list = CollectionsKt.toList(this.args);
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            for (Object obj : list) {
                arrayList.add("(" + Reflection.getOrCreateKotlinClass(obj.getClass()) + " = " + obj + ')');
            }
            sb.append(arrayList);
            return sb.toString();
        }

        public boolean equals(Object other) {
            if (!(other instanceof StringResource)) {
                return false;
            }
            StringResource stringResource = (StringResource) other;
            return this.resId == stringResource.resId && Intrinsics.areEqual(this.args, stringResource.args);
        }

        public int hashCode() {
            return (this.resId * 31) + this.args.hashCode();
        }
    }

    public final String asStringNull(Context context) {
        if (context == null) {
            return null;
        }
        try {
            return asString(context);
        } catch (Exception e) {
            Log.e(TAG, "Got invalid data from " + this);
            ArchComponentExtKt.logError(e);
            return null;
        }
    }

    public final String asString(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (this instanceof DynamicString) {
            return ((DynamicString) this).getValue();
        }
        if (!(this instanceof StringResource)) {
            throw new NoWhenBranchMatchedException();
        }
        StringResource stringResource = (StringResource) this;
        String string = context.getString(stringResource.getResId());
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        if (stringResource.getArgs().isEmpty()) {
            return string;
        }
        List<Object> args = stringResource.getArgs();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(args, 10));
        for (Object objAsString : args) {
            if (objAsString instanceof UiText) {
                objAsString = ((UiText) objAsString).asString(context);
            }
            arrayList.add(objAsString);
        }
        Object[] array = arrayList.toArray(new Object[0]);
        Object[] objArrCopyOf = Arrays.copyOf(array, array.length);
        String str = String.format(string, Arrays.copyOf(objArrCopyOf, objArrCopyOf.length));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }
}
