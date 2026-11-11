package androidx.navigation;

import androidx.exifinterface.media.ExifInterface;
import androidx.navigation.serialization.RouteSerializerKt;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlinx.serialization.SerializersKt;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: NavOptions.android.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001b\u0018\u00002\u00020\u0001:\u0001-B[\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\b\b\u0001\u0010\t\u001a\u00020\u0006\u0012\b\b\u0001\u0010\n\u001a\u00020\u0006\u0012\b\b\u0001\u0010\u000b\u001a\u00020\u0006\u0012\b\b\u0001\u0010\f\u001a\u00020\u0006¢\u0006\u0004\b\r\u0010\u000eBS\b\u0010\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0006\u0012\u0006\u0010\n\u001a\u00020\u0006\u0012\u0006\u0010\u000b\u001a\u00020\u0006\u0012\u0006\u0010\f\u001a\u00020\u0006¢\u0006\u0004\b\r\u0010\u0011BW\b\u0010\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\f\u0010\u0012\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0013\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0006\u0012\u0006\u0010\n\u001a\u00020\u0006\u0012\u0006\u0010\u000b\u001a\u00020\u0006\u0012\u0006\u0010\f\u001a\u00020\u0006¢\u0006\u0004\b\r\u0010\u0014BQ\b\u0010\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0015\u001a\u00020\u0001\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0006\u0012\u0006\u0010\n\u001a\u00020\u0006\u0012\u0006\u0010\u000b\u001a\u00020\u0006\u0012\u0006\u0010\f\u001a\u00020\u0006¢\u0006\u0004\b\r\u0010\u0016J\b\u0010\u001d\u001a\u00020\u0006H\u0007J\u0006\u0010%\u001a\u00020\u0003J\u0006\u0010&\u001a\u00020\u0003J\u0006\u0010'\u001a\u00020\u0003J\u0006\u0010(\u001a\u00020\u0003J\u0013\u0010)\u001a\u00020\u00032\b\u0010*\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010+\u001a\u00020\u0006H\u0016J\b\u0010,\u001a\u00020\u0010H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\u00020\u00068GX\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0007\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\t\u001a\u00020\u00068G¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0018R\u0013\u0010\n\u001a\u00020\u00068G¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0018R\u0013\u0010\u000b\u001a\u00020\u00068G¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0018R\u0013\u0010\f\u001a\u00020\u00068G¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0018R\"\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u001e\u001a\u0004\u0018\u00010\u0010@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R*\u0010\u0012\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00132\f\u0010\u001e\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0013@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\"\u0010\u0015\u001a\u0004\u0018\u00010\u00012\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$¨\u0006."}, d2 = {"Landroidx/navigation/NavOptions;", "", "singleTop", "", "restoreState", "popUpToId", "", "popUpToInclusive", "popUpToSaveState", "enterAnim", "exitAnim", "popEnterAnim", "popExitAnim", "<init>", "(ZZIZZIIII)V", "popUpToRoute", "", "(ZZLjava/lang/String;ZZIIII)V", "popUpToRouteClass", "Lkotlin/reflect/KClass;", "(ZZLkotlin/reflect/KClass;ZZIIII)V", "popUpToRouteObject", "(ZZLjava/lang/Object;ZZIIII)V", "getPopUpToId", "()I", "getEnterAnim", "getExitAnim", "getPopEnterAnim", "getPopExitAnim", "getPopUpTo", ES6Iterator.VALUE_PROPERTY, "getPopUpToRoute", "()Ljava/lang/String;", "getPopUpToRouteClass", "()Lkotlin/reflect/KClass;", "getPopUpToRouteObject", "()Ljava/lang/Object;", "shouldLaunchSingleTop", "shouldRestoreState", "isPopUpToInclusive", "shouldPopUpToSaveState", "equals", "other", "hashCode", "toString", "Builder", "navigation-common_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class NavOptions {
    private final int enterAnim;
    private final int exitAnim;
    private final int popEnterAnim;
    private final int popExitAnim;
    private final int popUpToId;
    private final boolean popUpToInclusive;
    private String popUpToRoute;
    private KClass<?> popUpToRouteClass;
    private Object popUpToRouteObject;
    private final boolean popUpToSaveState;
    private final boolean restoreState;
    private final boolean singleTop;

    public NavOptions(boolean z, boolean z2, int i, boolean z3, boolean z4, int i2, int i3, int i4, int i5) {
        this.singleTop = z;
        this.restoreState = z2;
        this.popUpToId = i;
        this.popUpToInclusive = z3;
        this.popUpToSaveState = z4;
        this.enterAnim = i2;
        this.exitAnim = i3;
        this.popEnterAnim = i4;
        this.popExitAnim = i5;
    }

    public final int getPopUpToId() {
        return this.popUpToId;
    }

    public final int getEnterAnim() {
        return this.enterAnim;
    }

    public final int getExitAnim() {
        return this.exitAnim;
    }

    public final int getPopEnterAnim() {
        return this.popEnterAnim;
    }

    public final int getPopExitAnim() {
        return this.popExitAnim;
    }

    @Deprecated(message = "Use popUpToId instead.", replaceWith = @ReplaceWith(expression = "popUpToId", imports = {}))
    /* JADX INFO: renamed from: getPopUpTo, reason: from getter */
    public final int getPopUpToId() {
        return this.popUpToId;
    }

    public final String getPopUpToRoute() {
        return this.popUpToRoute;
    }

    public final KClass<?> getPopUpToRouteClass() {
        return this.popUpToRouteClass;
    }

    public final Object getPopUpToRouteObject() {
        return this.popUpToRouteObject;
    }

    public NavOptions(boolean z, boolean z2, String str, boolean z3, boolean z4, int i, int i2, int i3, int i4) {
        this(z, z2, NavDestination.INSTANCE.createRoute(str).hashCode(), z3, z4, i, i2, i3, i4);
        this.popUpToRoute = str;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public NavOptions(boolean z, boolean z2, KClass<?> kClass, boolean z3, boolean z4, int i, int i2, int i3, int i4) {
        this(z, z2, RouteSerializerKt.generateHashCode(SerializersKt.serializer(kClass)), z3, z4, i, i2, i3, i4);
        Intrinsics.checkNotNull(kClass);
        this.popUpToRouteClass = kClass;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public NavOptions(boolean z, boolean z2, Object popUpToRouteObject, boolean z3, boolean z4, int i, int i2, int i3, int i4) {
        this(z, z2, RouteSerializerKt.generateHashCode(SerializersKt.serializer(Reflection.getOrCreateKotlinClass(popUpToRouteObject.getClass()))), z3, z4, i, i2, i3, i4);
        Intrinsics.checkNotNullParameter(popUpToRouteObject, "popUpToRouteObject");
        this.popUpToRouteObject = popUpToRouteObject;
    }

    /* JADX INFO: renamed from: shouldLaunchSingleTop, reason: from getter */
    public final boolean getSingleTop() {
        return this.singleTop;
    }

    /* JADX INFO: renamed from: shouldRestoreState, reason: from getter */
    public final boolean getRestoreState() {
        return this.restoreState;
    }

    /* JADX INFO: renamed from: isPopUpToInclusive, reason: from getter */
    public final boolean getPopUpToInclusive() {
        return this.popUpToInclusive;
    }

    /* JADX INFO: renamed from: shouldPopUpToSaveState, reason: from getter */
    public final boolean getPopUpToSaveState() {
        return this.popUpToSaveState;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other != null && (other instanceof NavOptions)) {
            NavOptions navOptions = (NavOptions) other;
            if (this.singleTop == navOptions.singleTop && this.restoreState == navOptions.restoreState && this.popUpToId == navOptions.popUpToId && Intrinsics.areEqual(this.popUpToRoute, navOptions.popUpToRoute) && Intrinsics.areEqual(this.popUpToRouteClass, navOptions.popUpToRouteClass) && Intrinsics.areEqual(this.popUpToRouteObject, navOptions.popUpToRouteObject) && this.popUpToInclusive == navOptions.popUpToInclusive && this.popUpToSaveState == navOptions.popUpToSaveState && this.enterAnim == navOptions.enterAnim && this.exitAnim == navOptions.exitAnim && this.popEnterAnim == navOptions.popEnterAnim && this.popExitAnim == navOptions.popExitAnim) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = (((((getSingleTop() ? 1 : 0) * 31) + (getRestoreState() ? 1 : 0)) * 31) + this.popUpToId) * 31;
        String str = this.popUpToRoute;
        int iHashCode = (i + (str != null ? str.hashCode() : 0)) * 31;
        KClass<?> kClass = this.popUpToRouteClass;
        int iHashCode2 = (iHashCode + (kClass != null ? kClass.hashCode() : 0)) * 31;
        Object obj = this.popUpToRouteObject;
        return ((((((((((((iHashCode2 + (obj != null ? obj.hashCode() : 0)) * 31) + (getPopUpToInclusive() ? 1 : 0)) * 31) + (getPopUpToSaveState() ? 1 : 0)) * 31) + this.enterAnim) * 31) + this.exitAnim) * 31) + this.popEnterAnim) * 31) + this.popExitAnim;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append("(");
        if (this.singleTop) {
            sb.append("launchSingleTop ");
        }
        if (this.restoreState) {
            sb.append("restoreState ");
        }
        String str = this.popUpToRoute;
        if ((str != null || this.popUpToId != -1) && str != null) {
            sb.append("popUpTo(");
            String str2 = this.popUpToRoute;
            if (str2 != null) {
                sb.append(str2);
            } else {
                KClass<?> kClass = this.popUpToRouteClass;
                if (kClass != null) {
                    sb.append(kClass);
                } else {
                    Object obj = this.popUpToRouteObject;
                    if (obj != null) {
                        sb.append(obj);
                    } else {
                        sb.append("0x");
                        sb.append(Integer.toHexString(this.popUpToId));
                    }
                }
            }
            if (this.popUpToInclusive) {
                sb.append(" inclusive");
            }
            if (this.popUpToSaveState) {
                sb.append(" saveState");
            }
            sb.append(")");
        }
        if (this.enterAnim != -1 || this.exitAnim != -1 || this.popEnterAnim != -1 || this.popExitAnim != -1) {
            sb.append("anim(enterAnim=0x");
            sb.append(Integer.toHexString(this.enterAnim));
            sb.append(" exitAnim=0x");
            sb.append(Integer.toHexString(this.exitAnim));
            sb.append(" popEnterAnim=0x");
            sb.append(Integer.toHexString(this.popEnterAnim));
            sb.append(" popExitAnim=0x");
            sb.append(Integer.toHexString(this.popExitAnim));
            sb.append(")");
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    /* JADX INFO: compiled from: NavOptions.android.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0014\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u0005J\u000e\u0010\u0015\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0005J$\u0010\u0016\u001a\u00020\u00002\b\b\u0001\u0010\u0017\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\u00052\b\b\u0002\u0010\u0019\u001a\u00020\u0005H\u0007J$\u0010\u0016\u001a\u00020\u00002\b\u0010\u001a\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0018\u001a\u00020\u00052\b\b\u0002\u0010\u0019\u001a\u00020\u0005H\u0007J'\u0010\u0016\u001a\u00020\u0000\"\n\b\u0000\u0010\u001b\u0018\u0001*\u00020\u00012\u0006\u0010\u0018\u001a\u00020\u00052\b\b\u0002\u0010\u0019\u001a\u00020\u0005H\u0087\bJ2\u0010\u0016\u001a\u00020\u0000\"\b\b\u0000\u0010\u001b*\u00020\u00012\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u001b0\f2\u0006\u0010\u0018\u001a\u00020\u00052\b\b\u0002\u0010\u0019\u001a\u00020\u0005H\u0007J1\u0010\u0016\u001a\u00020\u0000\"\b\b\u0000\u0010\u001b*\u00020\u00012\u0006\u0010\u001a\u001a\u0002H\u001b2\u0006\u0010\u0018\u001a\u00020\u00052\b\b\u0002\u0010\u0019\u001a\u00020\u0005H\u0007¢\u0006\u0002\u0010\u001cJ\u0010\u0010\u001d\u001a\u00020\u00002\b\b\u0001\u0010\u0010\u001a\u00020\bJ\u0010\u0010\u001e\u001a\u00020\u00002\b\b\u0001\u0010\u0011\u001a\u00020\bJ\u0010\u0010\u001f\u001a\u00020\u00002\b\b\u0001\u0010\u0012\u001a\u00020\bJ\u0010\u0010 \u001a\u00020\u00002\b\b\u0001\u0010\u0013\u001a\u00020\bJ\u0006\u0010!\u001a\u00020\"R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0007\u001a\u00020\b8\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0002\b\u0003\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0010\u001a\u00020\b8\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0011\u001a\u00020\b8\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0012\u001a\u00020\b8\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0013\u001a\u00020\b8\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Landroidx/navigation/NavOptions$Builder;", "", "<init>", "()V", "singleTop", "", "restoreState", "popUpToId", "", "popUpToRoute", "", "popUpToRouteClass", "Lkotlin/reflect/KClass;", "popUpToRouteObject", "popUpToInclusive", "popUpToSaveState", "enterAnim", "exitAnim", "popEnterAnim", "popExitAnim", "setLaunchSingleTop", "setRestoreState", "setPopUpTo", "destinationId", "inclusive", "saveState", "route", ExifInterface.GPS_DIRECTION_TRUE, "(Ljava/lang/Object;ZZ)Landroidx/navigation/NavOptions$Builder;", "setEnterAnim", "setExitAnim", "setPopEnterAnim", "setPopExitAnim", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Landroidx/navigation/NavOptions;", "navigation-common_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Builder {
        private boolean popUpToInclusive;
        private String popUpToRoute;
        private KClass<?> popUpToRouteClass;
        private Object popUpToRouteObject;
        private boolean popUpToSaveState;
        private boolean restoreState;
        private boolean singleTop;
        private int popUpToId = -1;
        private int enterAnim = -1;
        private int exitAnim = -1;
        private int popEnterAnim = -1;
        private int popExitAnim = -1;

        public final Builder setPopUpTo(int i, boolean z) {
            return setPopUpTo$default(this, i, z, false, 4, (Object) null);
        }

        public final <T> Builder setPopUpTo(T route, boolean z) {
            Intrinsics.checkNotNullParameter(route, "route");
            return setPopUpTo$default(this, (Object) route, z, false, 4, (Object) null);
        }

        public final Builder setPopUpTo(String str, boolean z) {
            return setPopUpTo$default(this, str, z, false, 4, (Object) null);
        }

        public final <T> Builder setPopUpTo(KClass<T> route, boolean z) {
            Intrinsics.checkNotNullParameter(route, "route");
            return setPopUpTo$default(this, (KClass) route, z, false, 4, (Object) null);
        }

        public final Builder setLaunchSingleTop(boolean singleTop) {
            this.singleTop = singleTop;
            return this;
        }

        public final Builder setRestoreState(boolean restoreState) {
            this.restoreState = restoreState;
            return this;
        }

        public static /* synthetic */ Builder setPopUpTo$default(Builder builder, int i, boolean z, boolean z2, int i2, Object obj) {
            if ((i2 & 4) != 0) {
                z2 = false;
            }
            return builder.setPopUpTo(i, z, z2);
        }

        public final Builder setPopUpTo(int destinationId, boolean inclusive, boolean saveState) {
            this.popUpToId = destinationId;
            this.popUpToRoute = null;
            this.popUpToInclusive = inclusive;
            this.popUpToSaveState = saveState;
            return this;
        }

        public static /* synthetic */ Builder setPopUpTo$default(Builder builder, String str, boolean z, boolean z2, int i, Object obj) {
            if ((i & 4) != 0) {
                z2 = false;
            }
            return builder.setPopUpTo(str, z, z2);
        }

        public final Builder setPopUpTo(String route, boolean inclusive, boolean saveState) {
            this.popUpToRoute = route;
            this.popUpToId = -1;
            this.popUpToInclusive = inclusive;
            this.popUpToSaveState = saveState;
            return this;
        }

        public static /* synthetic */ Builder setPopUpTo$default(Builder builder, boolean z, boolean z2, int i, Object obj) {
            if ((i & 2) != 0) {
                z2 = false;
            }
            Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
            builder.setPopUpTo(Reflection.getOrCreateKotlinClass(Object.class), z, z2);
            return builder;
        }

        public final /* synthetic */ <T> Builder setPopUpTo(boolean inclusive, boolean saveState) {
            Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
            setPopUpTo((KClass) Reflection.getOrCreateKotlinClass(Object.class), inclusive, saveState);
            return this;
        }

        public static /* synthetic */ Builder setPopUpTo$default(Builder builder, KClass kClass, boolean z, boolean z2, int i, Object obj) {
            if ((i & 4) != 0) {
                z2 = false;
            }
            return builder.setPopUpTo(kClass, z, z2);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final <T> Builder setPopUpTo(KClass<T> route, boolean inclusive, boolean saveState) {
            Intrinsics.checkNotNullParameter(route, "route");
            this.popUpToRouteClass = route;
            this.popUpToId = -1;
            this.popUpToInclusive = inclusive;
            this.popUpToSaveState = saveState;
            return this;
        }

        public static /* synthetic */ Builder setPopUpTo$default(Builder builder, Object obj, boolean z, boolean z2, int i, Object obj2) {
            if ((i & 4) != 0) {
                z2 = false;
            }
            return builder.setPopUpTo(obj, z, z2);
        }

        public final <T> Builder setPopUpTo(T route, boolean inclusive, boolean saveState) {
            Intrinsics.checkNotNullParameter(route, "route");
            this.popUpToRouteObject = route;
            setPopUpTo(RouteSerializerKt.generateHashCode(SerializersKt.serializer(Reflection.getOrCreateKotlinClass(route.getClass()))), inclusive, saveState);
            return this;
        }

        public final Builder setEnterAnim(int enterAnim) {
            this.enterAnim = enterAnim;
            return this;
        }

        public final Builder setExitAnim(int exitAnim) {
            this.exitAnim = exitAnim;
            return this;
        }

        public final Builder setPopEnterAnim(int popEnterAnim) {
            this.popEnterAnim = popEnterAnim;
            return this;
        }

        public final Builder setPopExitAnim(int popExitAnim) {
            this.popExitAnim = popExitAnim;
            return this;
        }

        public final NavOptions build() {
            if (this.popUpToRoute != null) {
                return new NavOptions(this.singleTop, this.restoreState, this.popUpToRoute, this.popUpToInclusive, this.popUpToSaveState, this.enterAnim, this.exitAnim, this.popEnterAnim, this.popExitAnim);
            }
            if (this.popUpToRouteClass != null) {
                return new NavOptions(this.singleTop, this.restoreState, this.popUpToRouteClass, this.popUpToInclusive, this.popUpToSaveState, this.enterAnim, this.exitAnim, this.popEnterAnim, this.popExitAnim);
            }
            if (this.popUpToRouteObject != null) {
                boolean z = this.singleTop;
                boolean z2 = this.restoreState;
                Object obj = this.popUpToRouteObject;
                Intrinsics.checkNotNull(obj);
                return new NavOptions(z, z2, obj, this.popUpToInclusive, this.popUpToSaveState, this.enterAnim, this.exitAnim, this.popEnterAnim, this.popExitAnim);
            }
            return new NavOptions(this.singleTop, this.restoreState, this.popUpToId, this.popUpToInclusive, this.popUpToSaveState, this.enterAnim, this.exitAnim, this.popEnterAnim, this.popExitAnim);
        }

        public final /* synthetic */ <T> Builder setPopUpTo(boolean z) {
            Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
            setPopUpTo((KClass) Reflection.getOrCreateKotlinClass(Object.class), z, false);
            return this;
        }
    }
}
