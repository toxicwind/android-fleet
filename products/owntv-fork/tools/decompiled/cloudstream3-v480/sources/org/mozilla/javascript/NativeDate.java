package org.mozilla.javascript;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.common.base.Ascii;
import j$.time.Instant;
import j$.time.format.DateTimeFormatter;
import j$.time.format.FormatStyle;
import j$.util.DesugarTimeZone;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.mozilla.universalchardet.prober.HebrewProber;
import org.schabi.newpipe.extractor.stream.Stream;

/* JADX INFO: loaded from: classes8.dex */
final class NativeDate extends IdScriptableObject {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int ConstructorId_UTC = -1;
    private static final int ConstructorId_now = -3;
    private static final int ConstructorId_parse = -2;
    private static final double HalfTimeDomain = 8.64E15d;
    private static final double HoursPerDay = 24.0d;
    private static final int Id_constructor = 1;
    private static final int Id_getDate = 17;
    private static final int Id_getDay = 19;
    private static final int Id_getFullYear = 13;
    private static final int Id_getHours = 21;
    private static final int Id_getMilliseconds = 27;
    private static final int Id_getMinutes = 23;
    private static final int Id_getMonth = 15;
    private static final int Id_getSeconds = 25;
    private static final int Id_getTime = 11;
    private static final int Id_getTimezoneOffset = 29;
    private static final int Id_getUTCDate = 18;
    private static final int Id_getUTCDay = 20;
    private static final int Id_getUTCFullYear = 14;
    private static final int Id_getUTCHours = 22;
    private static final int Id_getUTCMilliseconds = 28;
    private static final int Id_getUTCMinutes = 24;
    private static final int Id_getUTCMonth = 16;
    private static final int Id_getUTCSeconds = 26;
    private static final int Id_getYear = 12;
    private static final int Id_setDate = 39;
    private static final int Id_setFullYear = 43;
    private static final int Id_setHours = 37;
    private static final int Id_setMilliseconds = 31;
    private static final int Id_setMinutes = 35;
    private static final int Id_setMonth = 41;
    private static final int Id_setSeconds = 33;
    private static final int Id_setTime = 30;
    private static final int Id_setUTCDate = 40;
    private static final int Id_setUTCFullYear = 44;
    private static final int Id_setUTCHours = 38;
    private static final int Id_setUTCMilliseconds = 32;
    private static final int Id_setUTCMinutes = 36;
    private static final int Id_setUTCMonth = 42;
    private static final int Id_setUTCSeconds = 34;
    private static final int Id_setYear = 45;
    private static final int Id_toDateString = 4;
    private static final int Id_toGMTString = 8;
    private static final int Id_toISOString = 46;
    private static final int Id_toJSON = 47;
    private static final int Id_toLocaleDateString = 7;
    private static final int Id_toLocaleString = 5;
    private static final int Id_toLocaleTimeString = 6;
    private static final int Id_toSource = 9;
    private static final int Id_toString = 2;
    private static final int Id_toTimeString = 3;
    private static final int Id_toUTCString = 8;
    private static final int Id_valueOf = 10;
    private static final int MAXARGS = 7;
    private static final int MAX_PROTOTYPE_ID = 48;
    private static final double MinutesPerDay = 1440.0d;
    private static final double MinutesPerHour = 60.0d;
    private static final double SecondsPerDay = 86400.0d;
    private static final double SecondsPerHour = 3600.0d;
    private static final double SecondsPerMinute = 60.0d;
    private static final int SymbolId_toPrimitive = 48;
    private static final String js_NaN_date_str = "Invalid Date";
    private static final double msPerDay = 8.64E7d;
    private static final double msPerHour = 3600000.0d;
    private static final double msPerMinute = 60000.0d;
    private static final double msPerSecond = 1000.0d;
    private static final long serialVersionUID = -8307438915861678966L;
    private double date;
    private static final Object DATE_TAG = "Date";
    private static final DateTimeFormatter timeZoneFormatter = DateTimeFormatter.ofPattern("zzz");
    private static final DateTimeFormatter localeDateTimeFormatter = DateTimeFormatter.ofPattern("MMMM d, yyyy h:mm:ss a z");
    private static final DateTimeFormatter localeDateFormatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");
    private static final DateTimeFormatter localeTimeFormatter = DateTimeFormatter.ofPattern("h:mm:ss a z");
    private static final DateTimeFormatter localeDateTimeFormatterES6 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
    private static final DateTimeFormatter localeDateFormatterES6 = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
    private static final DateTimeFormatter localeTimeFormatterES6 = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);

    private static double MakeDate(double d, double d2) {
        return (d * msPerDay) + d2;
    }

    private static double MakeTime(double d, double d2, double d3, double d4) {
        return (((((d * 60.0d) + d2) * 60.0d) + d3) * 1000.0d) + d4;
    }

    private static double TimeWithinDay(double d) {
        double d2 = d % msPerDay;
        return d2 < 0.0d ? d2 + msPerDay : d2;
    }

    private static int msFromTime(double d) {
        double d2 = d % 1000.0d;
        if (d2 < 0.0d) {
            d2 += 1000.0d;
        }
        return (int) d2;
    }

    static void init(Scriptable scriptable, boolean z) {
        NativeDate nativeDate = new NativeDate();
        nativeDate.date = Double.NaN;
        nativeDate.exportAsJSClass(48, scriptable, z);
    }

    private NativeDate() {
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public String getClassName() {
        return "Date";
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public Object getDefaultValue(Class<?> cls) {
        if (cls == null) {
            cls = ScriptRuntime.StringClass;
        }
        return super.getDefaultValue(cls);
    }

    double getJSTimeValue() {
        return this.date;
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected void fillConstructorProperties(IdFunctionObject idFunctionObject) {
        Object obj = DATE_TAG;
        addIdFunctionProperty(idFunctionObject, obj, -3, "now", 0);
        addIdFunctionProperty(idFunctionObject, obj, -2, "parse", 1);
        addIdFunctionProperty(idFunctionObject, obj, -1, "UTC", 7);
        super.fillConstructorProperties(idFunctionObject);
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected void initPrototypeId(int i) {
        String str;
        int i2 = 4;
        switch (i) {
            case 1:
                this = this;
                i = i;
                i2 = 7;
                str = "constructor";
                this.initPrototypeMethod(DATE_TAG, i, str, i2);
                return;
            case 2:
                str = "toString";
                i2 = 0;
                this.initPrototypeMethod(DATE_TAG, i, str, i2);
                return;
            case 3:
                str = "toTimeString";
                i2 = 0;
                this.initPrototypeMethod(DATE_TAG, i, str, i2);
                return;
            case 4:
                str = "toDateString";
                i2 = 0;
                this.initPrototypeMethod(DATE_TAG, i, str, i2);
                return;
            case 5:
                str = "toLocaleString";
                i2 = 0;
                this.initPrototypeMethod(DATE_TAG, i, str, i2);
                return;
            case 6:
                str = "toLocaleTimeString";
                i2 = 0;
                this.initPrototypeMethod(DATE_TAG, i, str, i2);
                return;
            case 7:
                str = "toLocaleDateString";
                i2 = 0;
                this.initPrototypeMethod(DATE_TAG, i, str, i2);
                return;
            case 8:
                str = "toUTCString";
                i2 = 0;
                this.initPrototypeMethod(DATE_TAG, i, str, i2);
                return;
            case 9:
                str = "toSource";
                i2 = 0;
                this.initPrototypeMethod(DATE_TAG, i, str, i2);
                return;
            case 10:
                str = "valueOf";
                i2 = 0;
                this.initPrototypeMethod(DATE_TAG, i, str, i2);
                return;
            case 11:
                str = "getTime";
                i2 = 0;
                this.initPrototypeMethod(DATE_TAG, i, str, i2);
                return;
            case 12:
                str = "getYear";
                i2 = 0;
                this.initPrototypeMethod(DATE_TAG, i, str, i2);
                return;
            case 13:
                str = "getFullYear";
                i2 = 0;
                this.initPrototypeMethod(DATE_TAG, i, str, i2);
                return;
            case 14:
                str = "getUTCFullYear";
                i2 = 0;
                this.initPrototypeMethod(DATE_TAG, i, str, i2);
                return;
            case 15:
                str = "getMonth";
                i2 = 0;
                this.initPrototypeMethod(DATE_TAG, i, str, i2);
                return;
            case 16:
                str = "getUTCMonth";
                i2 = 0;
                this.initPrototypeMethod(DATE_TAG, i, str, i2);
                return;
            case 17:
                str = "getDate";
                i2 = 0;
                this.initPrototypeMethod(DATE_TAG, i, str, i2);
                return;
            case 18:
                str = "getUTCDate";
                i2 = 0;
                this.initPrototypeMethod(DATE_TAG, i, str, i2);
                return;
            case 19:
                str = "getDay";
                i2 = 0;
                this.initPrototypeMethod(DATE_TAG, i, str, i2);
                return;
            case 20:
                str = "getUTCDay";
                i2 = 0;
                this.initPrototypeMethod(DATE_TAG, i, str, i2);
                return;
            case 21:
                str = "getHours";
                i2 = 0;
                this.initPrototypeMethod(DATE_TAG, i, str, i2);
                return;
            case 22:
                str = "getUTCHours";
                i2 = 0;
                this.initPrototypeMethod(DATE_TAG, i, str, i2);
                return;
            case 23:
                str = "getMinutes";
                i2 = 0;
                this.initPrototypeMethod(DATE_TAG, i, str, i2);
                return;
            case 24:
                str = "getUTCMinutes";
                i2 = 0;
                this.initPrototypeMethod(DATE_TAG, i, str, i2);
                return;
            case 25:
                str = "getSeconds";
                i2 = 0;
                this.initPrototypeMethod(DATE_TAG, i, str, i2);
                return;
            case 26:
                str = "getUTCSeconds";
                i2 = 0;
                this.initPrototypeMethod(DATE_TAG, i, str, i2);
                return;
            case 27:
                str = "getMilliseconds";
                i2 = 0;
                this.initPrototypeMethod(DATE_TAG, i, str, i2);
                return;
            case 28:
                str = "getUTCMilliseconds";
                i2 = 0;
                this.initPrototypeMethod(DATE_TAG, i, str, i2);
                return;
            case 29:
                str = "getTimezoneOffset";
                i2 = 0;
                this.initPrototypeMethod(DATE_TAG, i, str, i2);
                return;
            case 30:
                str = "setTime";
                i2 = 1;
                this.initPrototypeMethod(DATE_TAG, i, str, i2);
                return;
            case 31:
                str = "setMilliseconds";
                i2 = 1;
                this.initPrototypeMethod(DATE_TAG, i, str, i2);
                return;
            case 32:
                str = "setUTCMilliseconds";
                i2 = 1;
                this.initPrototypeMethod(DATE_TAG, i, str, i2);
                return;
            case 33:
                str = "setSeconds";
                i2 = 2;
                this.initPrototypeMethod(DATE_TAG, i, str, i2);
                return;
            case 34:
                str = "setUTCSeconds";
                i2 = 2;
                this.initPrototypeMethod(DATE_TAG, i, str, i2);
                return;
            case 35:
                str = "setMinutes";
                i2 = 3;
                this.initPrototypeMethod(DATE_TAG, i, str, i2);
                return;
            case 36:
                str = "setUTCMinutes";
                i2 = 3;
                this.initPrototypeMethod(DATE_TAG, i, str, i2);
                return;
            case 37:
                this = this;
                i = i;
                str = "setHours";
                this.initPrototypeMethod(DATE_TAG, i, str, i2);
                return;
            case 38:
                this = this;
                i = i;
                str = "setUTCHours";
                this.initPrototypeMethod(DATE_TAG, i, str, i2);
                return;
            case 39:
                str = "setDate";
                i2 = 1;
                this.initPrototypeMethod(DATE_TAG, i, str, i2);
                return;
            case 40:
                str = "setUTCDate";
                i2 = 1;
                this.initPrototypeMethod(DATE_TAG, i, str, i2);
                return;
            case 41:
                str = "setMonth";
                i2 = 2;
                this.initPrototypeMethod(DATE_TAG, i, str, i2);
                return;
            case 42:
                str = "setUTCMonth";
                i2 = 2;
                this.initPrototypeMethod(DATE_TAG, i, str, i2);
                return;
            case 43:
                str = "setFullYear";
                i2 = 3;
                this.initPrototypeMethod(DATE_TAG, i, str, i2);
                return;
            case 44:
                str = "setUTCFullYear";
                i2 = 3;
                this.initPrototypeMethod(DATE_TAG, i, str, i2);
                return;
            case 45:
                str = "setYear";
                i2 = 1;
                this.initPrototypeMethod(DATE_TAG, i, str, i2);
                return;
            case 46:
                str = "toISOString";
                i2 = 0;
                this.initPrototypeMethod(DATE_TAG, i, str, i2);
                return;
            case 47:
                str = "toJSON";
                i2 = 1;
                this.initPrototypeMethod(DATE_TAG, i, str, i2);
                return;
            case 48:
                initPrototypeMethod(DATE_TAG, i, SymbolKey.TO_PRIMITIVE, "[Symbol.toPrimitive]", 1);
                return;
            default:
                throw new IllegalArgumentException(String.valueOf(i));
        }
    }

    @Override // org.mozilla.javascript.IdScriptableObject, org.mozilla.javascript.IdFunctionCall
    public Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        double dTimeClip;
        if (!idFunctionObject.hasTag(DATE_TAG)) {
            return super.execIdCall(idFunctionObject, context, scriptable, scriptable2, objArr);
        }
        int iMethodId = idFunctionObject.methodId();
        if (iMethodId == -3) {
            return ScriptRuntime.wrapNumber(now());
        }
        if (iMethodId == -2) {
            return ScriptRuntime.wrapNumber(date_parseString(context, ScriptRuntime.toString(objArr, 0)));
        }
        if (iMethodId == -1) {
            return ScriptRuntime.wrapNumber(jsStaticFunction_UTC(objArr));
        }
        if (iMethodId == 1) {
            if (scriptable2 != null) {
                return date_format(context, now(), 2);
            }
            return jsConstructor(context, objArr);
        }
        Class<?> cls = null;
        if (iMethodId == 47) {
            Scriptable object = ScriptRuntime.toObject(context, scriptable, scriptable2);
            Object primitive = ScriptRuntime.toPrimitive(object, ScriptRuntime.NumberClass);
            if (primitive instanceof Number) {
                double dDoubleValue = ((Number) primitive).doubleValue();
                if (Double.isNaN(dDoubleValue) || Double.isInfinite(dDoubleValue)) {
                    return null;
                }
            }
            Object property = ScriptableObject.getProperty(object, "toISOString");
            if (property == NOT_FOUND) {
                throw ScriptRuntime.typeErrorById("msg.function.not.found.in", "toISOString", ScriptRuntime.toString(object));
            }
            if (!(property instanceof Callable)) {
                throw ScriptRuntime.typeErrorById("msg.isnt.function.in", "toISOString", ScriptRuntime.toString(object), ScriptRuntime.toString(property));
            }
            Object objCall = ((Callable) property).call(context, scriptable, object, ScriptRuntime.emptyArgs);
            if (ScriptRuntime.isPrimitive(objCall)) {
                return objCall;
            }
            throw ScriptRuntime.typeErrorById("msg.toisostring.must.return.primitive", ScriptRuntime.toString(objCall));
        }
        if (iMethodId == 48) {
            Scriptable object2 = ScriptRuntime.toObject(context, scriptable, scriptable2);
            Object obj = objArr.length > 0 ? objArr[0] : Undefined.instance;
            String string = obj instanceof CharSequence ? obj.toString() : null;
            if (TypedValues.Custom.S_STRING.equals(string) || "default".equals(string)) {
                cls = ScriptRuntime.StringClass;
            } else if ("number".equals(string)) {
                cls = ScriptRuntime.NumberClass;
            }
            if (cls == null) {
                throw ScriptRuntime.typeErrorById("msg.invalid.toprimitive.hint", ScriptRuntime.toString(obj));
            }
            return ScriptableObject.getDefaultValue(object2, cls);
        }
        NativeDate nativeDate = (NativeDate) ensureType(scriptable2, NativeDate.class, idFunctionObject);
        double dYearFromTime = nativeDate.date;
        switch (iMethodId) {
            case 2:
            case 3:
            case 4:
                if (Double.isNaN(dYearFromTime)) {
                    return js_NaN_date_str;
                }
                return date_format(context, dYearFromTime, iMethodId);
            case 5:
            case 6:
            case 7:
                if (Double.isNaN(dYearFromTime)) {
                    return js_NaN_date_str;
                }
                return toLocale_helper(context, dYearFromTime, iMethodId, objArr);
            case 8:
                if (Double.isNaN(dYearFromTime)) {
                    return js_NaN_date_str;
                }
                return js_toUTCString(dYearFromTime);
            case 9:
                return "(new Date(" + ScriptRuntime.toString(dYearFromTime) + "))";
            case 10:
            case 11:
                return ScriptRuntime.wrapNumber(dYearFromTime);
            case 12:
            case 13:
            case 14:
                if (!Double.isNaN(dYearFromTime)) {
                    if (iMethodId != 14) {
                        dYearFromTime = LocalTime(context, dYearFromTime);
                    }
                    dYearFromTime = YearFromTime(dYearFromTime);
                    if (iMethodId == 12 && (!context.hasFeature(1) || (1900.0d <= dYearFromTime && dYearFromTime < 2000.0d))) {
                        dYearFromTime -= 1900.0d;
                    }
                }
                return ScriptRuntime.wrapNumber(dYearFromTime);
            case 15:
            case 16:
                if (!Double.isNaN(dYearFromTime)) {
                    if (iMethodId == 15) {
                        dYearFromTime = LocalTime(context, dYearFromTime);
                    }
                    dYearFromTime = MonthFromTime(dYearFromTime);
                }
                return ScriptRuntime.wrapNumber(dYearFromTime);
            case 17:
            case 18:
                if (!Double.isNaN(dYearFromTime)) {
                    if (iMethodId == 17) {
                        dYearFromTime = LocalTime(context, dYearFromTime);
                    }
                    dYearFromTime = DateFromTime(dYearFromTime);
                }
                return ScriptRuntime.wrapNumber(dYearFromTime);
            case 19:
            case 20:
                if (!Double.isNaN(dYearFromTime)) {
                    if (iMethodId == 19) {
                        dYearFromTime = LocalTime(context, dYearFromTime);
                    }
                    dYearFromTime = WeekDay(dYearFromTime);
                }
                return ScriptRuntime.wrapNumber(dYearFromTime);
            case 21:
            case 22:
                if (!Double.isNaN(dYearFromTime)) {
                    if (iMethodId == 21) {
                        dYearFromTime = LocalTime(context, dYearFromTime);
                    }
                    dYearFromTime = HourFromTime(dYearFromTime);
                }
                return ScriptRuntime.wrapNumber(dYearFromTime);
            case 23:
            case 24:
                if (!Double.isNaN(dYearFromTime)) {
                    if (iMethodId == 23) {
                        dYearFromTime = LocalTime(context, dYearFromTime);
                    }
                    dYearFromTime = MinFromTime(dYearFromTime);
                }
                return ScriptRuntime.wrapNumber(dYearFromTime);
            case 25:
            case 26:
                if (!Double.isNaN(dYearFromTime)) {
                    if (iMethodId == 25) {
                        dYearFromTime = LocalTime(context, dYearFromTime);
                    }
                    dYearFromTime = SecFromTime(dYearFromTime);
                }
                return ScriptRuntime.wrapNumber(dYearFromTime);
            case 27:
            case 28:
                if (!Double.isNaN(dYearFromTime)) {
                    if (iMethodId == 27) {
                        dYearFromTime = LocalTime(context, dYearFromTime);
                    }
                    dYearFromTime = msFromTime(dYearFromTime);
                }
                return ScriptRuntime.wrapNumber(dYearFromTime);
            case 29:
                if (!Double.isNaN(dYearFromTime)) {
                    dYearFromTime = (dYearFromTime - LocalTime(context, dYearFromTime)) / msPerMinute;
                }
                return ScriptRuntime.wrapNumber(dYearFromTime);
            case 30:
                double dTimeClip2 = TimeClip(ScriptRuntime.toNumber(objArr, 0));
                nativeDate.date = dTimeClip2;
                return ScriptRuntime.wrapNumber(dTimeClip2);
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
                double dMakeTime = makeTime(context, dYearFromTime, objArr, iMethodId);
                nativeDate.date = dMakeTime;
                return ScriptRuntime.wrapNumber(dMakeTime);
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
                double dMakeDate = makeDate(context, dYearFromTime, objArr, iMethodId);
                nativeDate.date = dMakeDate;
                return ScriptRuntime.wrapNumber(dMakeDate);
            case 45:
                double number = ScriptRuntime.toNumber(objArr, 0);
                if (Double.isNaN(number) || Double.isInfinite(number)) {
                    dTimeClip = Double.NaN;
                } else {
                    double dLocalTime = Double.isNaN(dYearFromTime) ? 0.0d : LocalTime(context, dYearFromTime);
                    if (number >= 0.0d && number <= 99.0d) {
                        number += 1900.0d;
                    }
                    dTimeClip = TimeClip(internalUTC(context, MakeDate(MakeDay(number, MonthFromTime(dLocalTime), DateFromTime(dLocalTime)), TimeWithinDay(dLocalTime))));
                }
                nativeDate.date = dTimeClip;
                return ScriptRuntime.wrapNumber(dTimeClip);
            case 46:
                if (!Double.isNaN(dYearFromTime)) {
                    return js_toISOString(dYearFromTime);
                }
                throw ScriptRuntime.rangeError(ScriptRuntime.getMessageById("msg.invalid.date", new Object[0]));
            default:
                throw new IllegalArgumentException(String.valueOf(iMethodId));
        }
    }

    private static double Day(double d) {
        return Math.floor(d / msPerDay);
    }

    private static boolean IsLeapYear(int i) {
        if (i % 4 == 0) {
            return i % 100 != 0 || i % 400 == 0;
        }
        return false;
    }

    private static double DayFromYear(double d) {
        return ((((d - 1970.0d) * 365.0d) + Math.floor((d - 1969.0d) / 4.0d)) - Math.floor((d - 1901.0d) / 100.0d)) + Math.floor((d - 1601.0d) / 400.0d);
    }

    private static double TimeFromYear(double d) {
        return DayFromYear(d) * msPerDay;
    }

    private static int YearFromTime(double d) {
        if (Double.isInfinite(d) || Double.isNaN(d)) {
            return 0;
        }
        double dFloor = Math.floor(d / 3.1556952E10d) + 1970.0d;
        double dTimeFromYear = TimeFromYear(dFloor);
        if (dTimeFromYear > d) {
            dFloor -= 1.0d;
        } else if (dTimeFromYear + (DaysInYear(dFloor) * msPerDay) <= d) {
            dFloor += 1.0d;
        }
        return (int) dFloor;
    }

    private static double DayFromMonth(int i, int i2) {
        int i3;
        int i4;
        int i5 = i * 30;
        if (i >= 7) {
            i4 = i / 2;
        } else {
            if (i >= 2) {
                i4 = (i - 1) / 2;
            } else {
                i3 = i5 + i;
            }
            if (i >= 2 && IsLeapYear(i2)) {
                i3++;
            }
            return i3;
        }
        i3 = i5 + (i4 - 1);
        if (i >= 2) {
            i3++;
        }
        return i3;
    }

    private static double DaysInYear(double d) {
        if (Double.isInfinite(d) || Double.isNaN(d)) {
            return Double.NaN;
        }
        return IsLeapYear((int) d) ? 366.0d : 365.0d;
    }

    private static int DaysInMonth(int i, int i2) {
        if (i2 == 2) {
            return IsLeapYear(i) ? 29 : 28;
        }
        return i2 >= 8 ? 31 - (i2 & 1) : (i2 & 1) + 30;
    }

    private static int MonthFromTime(double d) {
        int i;
        int iYearFromTime = YearFromTime(d);
        int iDay = (int) (Day(d) - DayFromYear(iYearFromTime));
        int i2 = iDay - 59;
        if (i2 < 0) {
            return i2 < -28 ? 0 : 1;
        }
        if (IsLeapYear(iYearFromTime)) {
            if (i2 == 0) {
                return 1;
            }
            i2 = iDay - 60;
        }
        int i3 = i2 / 30;
        switch (i3) {
            case 0:
                return 2;
            case 1:
                i = 31;
                break;
            case 2:
                i = 61;
                break;
            case 3:
                i = 92;
                break;
            case 4:
                i = 122;
                break;
            case 5:
                i = 153;
                break;
            case 6:
                i = 184;
                break;
            case 7:
                i = 214;
                break;
            case 8:
                i = HebrewProber.FINAL_TSADI;
                break;
            case 9:
                i = 275;
                break;
            case 10:
                return 11;
            default:
                throw Kit.codeBug();
        }
        return i2 >= i ? i3 + 2 : i3 + 1;
    }

    /* JADX WARN: Code duplicated, block: B:34:0x0059  */
    private static int DateFromTime(double d) {
        int i;
        int i2;
        int iYearFromTime = YearFromTime(d);
        int iDay = (int) (Day(d) - DayFromYear(iYearFromTime));
        int i3 = iDay - 59;
        if (i3 < 0) {
            return i3 < -28 ? iDay + 1 : iDay - 30;
        }
        if (IsLeapYear(iYearFromTime)) {
            if (i3 == 0) {
                return 29;
            }
            i3 = iDay - 60;
        }
        int i4 = 30;
        int i5 = 31;
        switch (i3 / 30) {
            case 0:
                return i3 + 1;
            case 1:
                i4 = 31;
                i2 = i3 - i5;
                if (i2 < 0) {
                    i2 += i4;
                }
                return i2 + 1;
            case 2:
                i5 = 61;
                i2 = i3 - i5;
                if (i2 < 0) {
                    i2 += i4;
                }
                return i2 + 1;
            case 3:
                i = 92;
                i4 = 31;
                i5 = i;
                i2 = i3 - i5;
                if (i2 < 0) {
                    i2 += i4;
                }
                return i2 + 1;
            case 4:
                i5 = 122;
                i2 = i3 - i5;
                if (i2 < 0) {
                    i2 += i4;
                }
                return i2 + 1;
            case 5:
                i = 153;
                i4 = 31;
                i5 = i;
                i2 = i3 - i5;
                if (i2 < 0) {
                    i2 += i4;
                }
                return i2 + 1;
            case 6:
                i = 184;
                i4 = 31;
                i5 = i;
                i2 = i3 - i5;
                if (i2 < 0) {
                    i2 += i4;
                }
                return i2 + 1;
            case 7:
                i5 = 214;
                i2 = i3 - i5;
                if (i2 < 0) {
                    i2 += i4;
                }
                return i2 + 1;
            case 8:
                i = HebrewProber.FINAL_TSADI;
                i4 = 31;
                i5 = i;
                i2 = i3 - i5;
                if (i2 < 0) {
                    i2 += i4;
                }
                return i2 + 1;
            case 9:
                i5 = 275;
                i2 = i3 - i5;
                if (i2 < 0) {
                    i2 += i4;
                }
                return i2 + 1;
            case 10:
                return i3 - 274;
            default:
                throw Kit.codeBug();
        }
    }

    private static int WeekDay(double d) {
        double dDay = (Day(d) + 4.0d) % 7.0d;
        if (dDay < 0.0d) {
            dDay += 7.0d;
        }
        return (int) dDay;
    }

    private static double now() {
        return System.currentTimeMillis();
    }

    private static double DaylightSavingTA(Context context, double d) {
        if (d < 0.0d) {
            d = MakeDate(MakeDay(EquivalentYear(YearFromTime(d)), MonthFromTime(d), DateFromTime(d)), TimeWithinDay(d));
        }
        if (context.getTimeZone().inDaylightTime(new Date((long) d))) {
            return msPerHour;
        }
        return 0.0d;
    }

    private static int EquivalentYear(int i) {
        int iDayFromYear = (((int) DayFromYear(i)) + 4) % 7;
        if (iDayFromYear < 0) {
            iDayFromYear += 7;
        }
        if (IsLeapYear(i)) {
            switch (iDayFromYear) {
                case 0:
                    return 1984;
                case 1:
                    return 1996;
                case 2:
                    return 1980;
                case 3:
                    return 1992;
                case 4:
                    return 1976;
                case 5:
                    return 1988;
                case 6:
                    return 1972;
            }
        }
        switch (iDayFromYear) {
            case 0:
                return 1978;
            case 1:
                return 1973;
            case 2:
                return 1985;
            case 3:
                return 1986;
            case 4:
                return 1981;
            case 5:
                return 1971;
            case 6:
                return 1977;
        }
        throw Kit.codeBug();
    }

    private static double LocalTime(Context context, double d) {
        return ((double) context.getTimeZone().getRawOffset()) + d + DaylightSavingTA(context, d);
    }

    private static double internalUTC(Context context, double d) {
        double rawOffset = d - ((double) context.getTimeZone().getRawOffset());
        return rawOffset - DaylightSavingTA(context, rawOffset);
    }

    private static int HourFromTime(double d) {
        double dFloor = Math.floor(d / msPerHour) % HoursPerDay;
        if (dFloor < 0.0d) {
            dFloor += HoursPerDay;
        }
        return (int) dFloor;
    }

    private static int MinFromTime(double d) {
        double dFloor = Math.floor(d / msPerMinute) % 60.0d;
        if (dFloor < 0.0d) {
            dFloor += 60.0d;
        }
        return (int) dFloor;
    }

    private static int SecFromTime(double d) {
        double dFloor = Math.floor(d / 1000.0d) % 60.0d;
        if (dFloor < 0.0d) {
            dFloor += 60.0d;
        }
        return (int) dFloor;
    }

    private static double MakeDay(double d, double d2, double d3) {
        double dFloor = d + Math.floor(d2 / 12.0d);
        double d4 = d2 % 12.0d;
        if (d4 < 0.0d) {
            d4 += 12.0d;
        }
        return ((Math.floor(TimeFromYear(dFloor) / msPerDay) + DayFromMonth((int) d4, (int) dFloor)) + d3) - 1.0d;
    }

    private static double TimeClip(double d) {
        if (Double.isNaN(d) || d == Double.POSITIVE_INFINITY || d == Double.NEGATIVE_INFINITY || Math.abs(d) > HalfTimeDomain) {
            return Double.NaN;
        }
        if (d > 0.0d) {
            return Math.floor(d + 0.0d);
        }
        return Math.ceil(d + 0.0d);
    }

    private static double date_msecFromDate(double d, double d2, double d3, double d4, double d5, double d6, double d7) {
        return MakeDate(MakeDay(d, d2, d3), MakeTime(d4, d5, d6, d7));
    }

    private static double date_msecFromArgs(Object[] objArr) {
        double[] dArr = new double[7];
        for (int i = 0; i < 7; i++) {
            if (i < objArr.length) {
                double number = ScriptRuntime.toNumber(objArr[i]);
                if (Double.isNaN(number) || Double.isInfinite(number)) {
                    return Double.NaN;
                }
                dArr[i] = ScriptRuntime.toInteger(objArr[i]);
            } else if (i == 2) {
                dArr[i] = 1.0d;
            } else {
                dArr[i] = 0.0d;
            }
        }
        double d = dArr[0];
        if (d >= 0.0d && d <= 99.0d) {
            dArr[0] = d + 1900.0d;
        }
        return date_msecFromDate(dArr[0], dArr[1], dArr[2], dArr[3], dArr[4], dArr[5], dArr[6]);
    }

    private static double jsStaticFunction_UTC(Object[] objArr) {
        if (objArr.length == 0) {
            return Double.NaN;
        }
        return TimeClip(date_msecFromArgs(objArr));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:15:0x005b  */
    /* JADX WARN: Code duplicated, block: B:54:0x00d5  */
    /* JADX WARN: Code duplicated, block: B:69:0x00f5 A[PHI: r3
      0x00f5: PHI (r3v24 char) = (r3v20 char), (r3v25 char) binds: [B:67:0x00f2, B:61:0x00e5] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:70:0x00f7 A[PHI: r3
      0x00f7: PHI (r3v21 char) = (r3v20 char), (r3v20 char), (r3v25 char), (r3v25 char) binds: [B:66:0x00f0, B:67:0x00f2, B:60:0x00e3, B:61:0x00e5] A[DONT_GENERATE, DONT_INLINE]] */
    private static double parseISOString(Context context, String str) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        double rawOffset;
        char c;
        char c2;
        char c3;
        int i11;
        int[] iArr = new int[9];
        int i12 = 0;
        iArr[0] = 1970;
        int i13 = 1;
        iArr[1] = 1;
        iArr[2] = 1;
        iArr[3] = 0;
        iArr[4] = 0;
        iArr[5] = 0;
        iArr[6] = 0;
        int i14 = -1;
        iArr[7] = -1;
        iArr[8] = -1;
        int length = str.length();
        if (length == 0) {
            i = 1;
            i2 = 0;
            i3 = 0;
            i4 = 0;
            i5 = 4;
            i6 = 1;
        } else {
            char cCharAt = str.charAt(0);
            if (cCharAt == '+' || cCharAt == '-') {
                i = cCharAt == '-' ? -1 : 1;
                i3 = 1;
                i2 = 0;
                i4 = 0;
                i5 = 6;
            } else if (cCharAt == 'T') {
                i = 1;
                i3 = 1;
                i2 = 0;
                i4 = 0;
                i12 = 3;
                i5 = 4;
            } else {
                i = 1;
                i2 = 0;
                i3 = 0;
                i4 = 0;
                i5 = 4;
                i6 = 1;
            }
            i6 = i3;
        }
        while (true) {
            if (i12 != i14) {
                i7 = i13;
                int i15 = i3 + (i12 == 0 ? i5 : i12 == 6 ? 3 : 2);
                if (i15 > length) {
                    i8 = i14;
                    i12 = i8;
                } else {
                    i9 = i3;
                    int i16 = i2;
                    while (true) {
                        if (i9 < i15) {
                            char cCharAt2 = str.charAt(i9);
                            if (cCharAt2 >= '0' && cCharAt2 <= '9') {
                                i16 = (i16 * 10) + (cCharAt2 - '0');
                                i9++;
                            }
                        } else {
                            iArr[i12] = i16;
                            if (i9 != length) {
                                i3 = i9 + 1;
                                char cCharAt3 = str.charAt(i9);
                                if (cCharAt3 == 'Z') {
                                    iArr[7] = i2;
                                    iArr[r11] = i2;
                                    if (i12 == 4 || i12 == 5 || i12 == 6) {
                                        i9 = i3;
                                    } else {
                                        i9 = i3;
                                    }
                                } else {
                                    switch (i12) {
                                        case 0:
                                        case 1:
                                            c = 'T';
                                            c2 = '-';
                                            if (cCharAt3 != '-') {
                                                i12 = cCharAt3 != 'T' ? -1 : 3;
                                            } else {
                                                i12++;
                                            }
                                            break;
                                        case 2:
                                            c = 'T';
                                            i12 = cCharAt3 == 'T' ? 3 : -1;
                                            c2 = '-';
                                            break;
                                        case 3:
                                            i12 = cCharAt3 == ':' ? 4 : -1;
                                            i4 = i7;
                                            c = 'T';
                                            c2 = '-';
                                            break;
                                        case 4:
                                            if (cCharAt3 == ':') {
                                                i12 = 5;
                                            } else if (cCharAt3 == '+' || cCharAt3 == '-') {
                                                i12 = 7;
                                            } else {
                                                i12 = -1;
                                            }
                                            c = 'T';
                                            c2 = '-';
                                            break;
                                        case 5:
                                            c3 = '-';
                                            if (cCharAt3 == '.') {
                                                i11 = 6;
                                            } else if (cCharAt3 == '+' || cCharAt3 == '-') {
                                                i11 = 7;
                                            } else {
                                                i11 = -1;
                                            }
                                            c2 = c3;
                                            i12 = i11;
                                            c = 'T';
                                            break;
                                        case 6:
                                            c3 = '-';
                                            if (cCharAt3 == '+' || cCharAt3 == '-') {
                                                i11 = 7;
                                            } else {
                                                i11 = -1;
                                            }
                                            c2 = c3;
                                            i12 = i11;
                                            c = 'T';
                                            break;
                                        case 7:
                                            if (cCharAt3 == ':') {
                                                i9 = i3;
                                            }
                                            i3 = i9;
                                            i12 = 8;
                                            c = 'T';
                                            c2 = '-';
                                            break;
                                        case 8:
                                            i12 = -1;
                                            c = 'T';
                                            c2 = '-';
                                            break;
                                        default:
                                            c = 'T';
                                            c2 = '-';
                                            break;
                                    }
                                    if (i12 == 7) {
                                        i6 = cCharAt3 == c2 ? -1 : i7;
                                    }
                                    i13 = i7;
                                    i14 = -1;
                                }
                            } else if (i12 == 3 || i12 == 7) {
                                i12 = -1;
                            }
                            i8 = -1;
                        }
                        i8 = -1;
                        i12 = -1;
                    }
                }
                if (i12 == i8 && i9 == length) {
                    int i17 = iArr[i2];
                    int i18 = iArr[i7];
                    int i19 = iArr[2];
                    int i20 = iArr[3];
                    int i21 = iArr[4];
                    int i22 = iArr[5];
                    int i23 = iArr[6];
                    int i24 = iArr[7];
                    int i25 = iArr[r11];
                    if (i17 > 275943 || i18 < (i10 = i7) || i18 > 12 || i19 < i10 || i19 > DaysInMonth(i17, i18) || i20 > 24) {
                        return Double.NaN;
                    }
                    if ((i20 == 24 && (i21 > 0 || i22 > 0 || i23 > 0)) || i21 > 59 || i22 > 59 || i24 > 23 || i25 > 59) {
                        return Double.NaN;
                    }
                    double dDate_msecFromDate = date_msecFromDate(i17 * i, i18 - 1, i19, i20, i21, i22, i23);
                    if (i24 == -1) {
                        if (i4 != 0) {
                            rawOffset = ((double) context.getTimeZone().getRawOffset()) + DaylightSavingTA(context, dDate_msecFromDate);
                        }
                        if (dDate_msecFromDate < -8.64E15d && dDate_msecFromDate <= HalfTimeDomain) {
                            return dDate_msecFromDate;
                        }
                    }
                    rawOffset = ((double) ((i24 * 60) + i25)) * msPerMinute * ((double) i6);
                    dDate_msecFromDate -= rawOffset;
                    return dDate_msecFromDate < -8.64E15d ? Double.NaN : Double.NaN;
                }
            }
            i7 = i13;
            i8 = i14;
            i9 = i3;
            return i12 == i8 ? Double.NaN : Double.NaN;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:100:0x00fc  */
    /* JADX WARN: Code duplicated, block: B:102:0x0104  */
    /* JADX WARN: Code duplicated, block: B:105:0x0109  */
    /* JADX WARN: Code duplicated, block: B:132:0x0145  */
    /* JADX WARN: Code duplicated, block: B:136:0x0154  */
    /* JADX WARN: Code duplicated, block: B:140:0x015f  */
    /* JADX WARN: Code duplicated, block: B:149:0x0170  */
    /* JADX WARN: Code duplicated, block: B:152:0x0176  */
    /* JADX WARN: Code duplicated, block: B:154:0x017a A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:155:0x017c  */
    /* JADX WARN: Code duplicated, block: B:157:0x017f  */
    /* JADX WARN: Code duplicated, block: B:159:0x0193  */
    /* JADX WARN: Code duplicated, block: B:160:0x0197  */
    /* JADX WARN: Code duplicated, block: B:161:0x019a  */
    /* JADX WARN: Code duplicated, block: B:162:0x019d  */
    /* JADX WARN: Code duplicated, block: B:163:0x019f A[PHI: r0
      0x019f: PHI (r0v17 double) = (r0v16 double), (r0v18 double), (r0v19 double) binds: [B:158:0x0190, B:162:0x019d, B:160:0x0197] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:164:0x01a2  */
    /* JADX WARN: Code duplicated, block: B:165:0x01a5  */
    /* JADX WARN: Code duplicated, block: B:167:0x01a9 A[LOOP:2: B:133:0x0147->B:167:0x01a9, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:211:0x0144 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:212:0x0153 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:215:0x017e A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:238:0x015b A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:97:0x00f5  */
    /* JADX WARN: Code duplicated, block: B:99:0x00f9  */
    private static double date_parseString(Context context, String str) {
        char c;
        int i;
        int i2;
        int i3;
        int iIndexOf;
        int i4;
        double d;
        int i5;
        double iSOString = parseISOString(context, str);
        if (!Double.isNaN(iSOString)) {
            return iSOString;
        }
        int length = str.length();
        int i6 = -1;
        int i7 = -1;
        int i8 = -1;
        int i9 = -1;
        int i10 = -1;
        int i11 = -1;
        char c2 = 0;
        int i12 = 0;
        double d2 = -1.0d;
        boolean z = false;
        while (i12 < length) {
            char cCharAt = str.charAt(i12);
            int i13 = i12 + 1;
            if (cCharAt <= ' ' || cCharAt == ',' || cCharAt == '-') {
                c = c2;
                if (i13 < length) {
                    char cCharAt2 = str.charAt(i13);
                    if (cCharAt == '-' && '0' <= cCharAt2 && cCharAt2 <= '9') {
                        c2 = cCharAt;
                        i12 = i13;
                    }
                }
                i12 = i13;
                c2 = c;
            } else {
                int i14 = 1;
                if (cCharAt == '(') {
                    i12 = i13;
                    while (i12 < length) {
                        char cCharAt3 = str.charAt(i12);
                        i12++;
                        if (cCharAt3 != '(') {
                            if (cCharAt3 == ')' && (i14 = i14 - 1) <= 0) {
                                break;
                            }
                        } else {
                            i14++;
                        }
                    }
                } else if ('0' <= cCharAt && cCharAt <= '9') {
                    i12 = i13;
                    int i15 = cCharAt - '0';
                    while (i12 < length) {
                        cCharAt = str.charAt(i12);
                        if ('0' > cCharAt || cCharAt > '9') {
                            if (c2 != '+' || c2 == '-') {
                                if (i15 < 24) {
                                    i5 = i15 * 60;
                                } else {
                                    i5 = ((i15 / 100) * 60) + (i15 % 100);
                                }
                                if (c2 == '+') {
                                    i5 = -i5;
                                }
                                if (d2 == 0.0d && d2 != -1.0d) {
                                    return Double.NaN;
                                }
                                d2 = i5;
                                z = true;
                            } else if (i15 >= 70 || (c2 == '/' && i7 >= 0 && i8 >= 0 && i6 < 0)) {
                                if (i6 >= 0) {
                                    return Double.NaN;
                                }
                                if (cCharAt > ' ' && cCharAt != ',' && cCharAt != '/' && i12 < length) {
                                    return Double.NaN;
                                }
                                if (i15 < 100) {
                                    i15 += 1900;
                                }
                                i6 = i15;
                            } else if (cCharAt == ':') {
                                if (i11 < 0) {
                                    i11 = i15;
                                } else {
                                    if (i10 >= 0) {
                                        return Double.NaN;
                                    }
                                    i10 = i15;
                                }
                            } else if (cCharAt != '/') {
                                if (i12 < length && cCharAt != ',' && cCharAt > ' ' && cCharAt != '-') {
                                    return Double.NaN;
                                }
                                if (z && i15 < 60) {
                                    d2 = d2 < 0.0d ? d2 - ((double) i15) : d2 + ((double) i15);
                                } else if (i11 >= 0 && i10 < 0) {
                                    i10 = i15;
                                } else if (i10 < 0 || i9 >= 0) {
                                    if (i8 >= 0) {
                                        return Double.NaN;
                                    }
                                    i8 = i15;
                                } else {
                                    i9 = i15;
                                }
                            } else if (i7 < 0) {
                                i7 = i15 - 1;
                            } else {
                                if (i8 >= 0) {
                                    return Double.NaN;
                                }
                                i8 = i15;
                            }
                            c2 = 0;
                        } else {
                            i15 = ((i15 * 10) + cCharAt) - 48;
                            i12++;
                        }
                    }
                    if (c2 != '+') {
                        if (i15 < 24) {
                            i5 = i15 * 60;
                        } else {
                            i5 = ((i15 / 100) * 60) + (i15 % 100);
                        }
                        if (c2 == '+') {
                            i5 = -i5;
                        }
                        if (d2 == 0.0d) {
                        }
                        d2 = i5;
                        z = true;
                    } else {
                        if (i15 < 24) {
                            i5 = i15 * 60;
                        } else {
                            i5 = ((i15 / 100) * 60) + (i15 % 100);
                        }
                        if (c2 == '+') {
                            i5 = -i5;
                        }
                        if (d2 == 0.0d) {
                        }
                        d2 = i5;
                        z = true;
                    }
                    c2 = 0;
                } else if (cCharAt == '/' || cCharAt == ':' || cCharAt == '+' || cCharAt == '-') {
                    c2 = cCharAt;
                    i12 = i13;
                } else {
                    for (int i16 = i13; i16 < length; i16++) {
                        char cCharAt4 = str.charAt(i16);
                        if (('A' > cCharAt4 || cCharAt4 > 'Z') && ('a' > cCharAt4 || cCharAt4 > 'z')) {
                            i = i16 - i12;
                            if (i < 2) {
                                return Double.NaN;
                            }
                            i2 = 0;
                            i3 = 0;
                            while (true) {
                                c = c2;
                                iIndexOf = "am;pm;monday;tuesday;wednesday;thursday;friday;saturday;sunday;january;february;march;april;may;june;july;august;september;october;november;december;gmt;ut;utc;est;edt;cst;cdt;mst;mdt;pst;pdt;".indexOf(59, i2);
                                if (iIndexOf < 0) {
                                    return Double.NaN;
                                }
                                if ("am;pm;monday;tuesday;wednesday;thursday;friday;saturday;sunday;january;february;march;april;may;june;july;august;september;october;november;december;gmt;ut;utc;est;edt;cst;cdt;mst;mdt;pst;pdt;".regionMatches(true, i2, str, i12, i)) {
                                    if (i3 < 2) {
                                        if (i11 <= 12 || i11 < 0) {
                                            return Double.NaN;
                                        }
                                        if (i3 == 0) {
                                            if (i11 == 12) {
                                                i11 = 0;
                                            }
                                        } else if (i11 != 12) {
                                            i11 += 12;
                                        }
                                    } else if (i3 - 2 >= 7) {
                                        i4 = i3 - 9;
                                        if (i4 < 12) {
                                            d = 420.0d;
                                            switch (i3 - 21) {
                                                case 0:
                                                case 1:
                                                case 2:
                                                    d2 = 0.0d;
                                                    break;
                                                case 3:
                                                case 6:
                                                    d2 = 300.0d;
                                                    break;
                                                case 4:
                                                    d = 240.0d;
                                                    d2 = d;
                                                    break;
                                                case 5:
                                                case 8:
                                                    d2 = 360.0d;
                                                    break;
                                                case 7:
                                                case 10:
                                                    d2 = d;
                                                    break;
                                                case 9:
                                                    d = 480.0d;
                                                    d2 = d;
                                                    break;
                                                default:
                                                    Kit.codeBug();
                                                    break;
                                            }
                                        } else {
                                            if (i7 < 0) {
                                                return Double.NaN;
                                            }
                                            i7 = i4;
                                        }
                                    }
                                    i12 = i16;
                                } else {
                                    i2 = iIndexOf + 1;
                                    i3++;
                                    c2 = c;
                                }
                            }
                            c2 = c;
                        }
                    }
                    i = i16 - i12;
                    if (i < 2) {
                        return Double.NaN;
                    }
                    i2 = 0;
                    i3 = 0;
                    while (true) {
                        c = c2;
                        iIndexOf = "am;pm;monday;tuesday;wednesday;thursday;friday;saturday;sunday;january;february;march;april;may;june;july;august;september;october;november;december;gmt;ut;utc;est;edt;cst;cdt;mst;mdt;pst;pdt;".indexOf(59, i2);
                        if (iIndexOf < 0) {
                            return Double.NaN;
                        }
                        if ("am;pm;monday;tuesday;wednesday;thursday;friday;saturday;sunday;january;february;march;april;may;june;july;august;september;october;november;december;gmt;ut;utc;est;edt;cst;cdt;mst;mdt;pst;pdt;".regionMatches(true, i2, str, i12, i)) {
                            if (i3 < 2) {
                                if (i11 <= 12) {
                                }
                                return Double.NaN;
                            }
                            if (i3 - 2 >= 7) {
                                i4 = i3 - 9;
                                if (i4 < 12) {
                                    d = 420.0d;
                                    switch (i3 - 21) {
                                        case 0:
                                        case 1:
                                        case 2:
                                            d2 = 0.0d;
                                            break;
                                        case 3:
                                        case 6:
                                            d2 = 300.0d;
                                            break;
                                        case 4:
                                            d = 240.0d;
                                            d2 = d;
                                            break;
                                        case 5:
                                        case 8:
                                            d2 = 360.0d;
                                            break;
                                        case 7:
                                        case 10:
                                            d2 = d;
                                            break;
                                        case 9:
                                            d = 480.0d;
                                            d2 = d;
                                            break;
                                        default:
                                            Kit.codeBug();
                                            break;
                                    }
                                } else {
                                    if (i7 < 0) {
                                        return Double.NaN;
                                    }
                                    i7 = i4;
                                }
                            }
                            i12 = i16;
                        } else {
                            i2 = iIndexOf + 1;
                            i3++;
                            c2 = c;
                        }
                    }
                    c2 = c;
                }
            }
        }
        if (i6 < 0 || i7 < 0 || i8 < 0) {
            return Double.NaN;
        }
        if (i9 < 0) {
            i9 = 0;
        }
        if (i10 < 0) {
            i10 = 0;
        }
        double dDate_msecFromDate = date_msecFromDate(i6, i7, i8, i11 < 0 ? 0 : i11, i10, i9, 0.0d);
        return d2 == -1.0d ? internalUTC(context, dDate_msecFromDate) : dDate_msecFromDate + (d2 * msPerMinute);
    }

    private static String date_format(Context context, double d, int i) {
        StringBuilder sb = new StringBuilder(60);
        double dLocalTime = LocalTime(context, d);
        if (i != 3) {
            appendWeekDayName(sb, WeekDay(dLocalTime));
            sb.append(' ');
            appendMonthName(sb, MonthFromTime(dLocalTime));
            sb.append(' ');
            append0PaddedUint(sb, DateFromTime(dLocalTime), 2);
            sb.append(' ');
            int iYearFromTime = YearFromTime(dLocalTime);
            if (iYearFromTime < 0) {
                sb.append('-');
                iYearFromTime = -iYearFromTime;
            }
            append0PaddedUint(sb, iYearFromTime, 4);
            if (i != 4) {
                sb.append(' ');
            }
        }
        if (i != 4) {
            append0PaddedUint(sb, HourFromTime(dLocalTime), 2);
            sb.append(AbstractJsonLexerKt.COLON);
            append0PaddedUint(sb, MinFromTime(dLocalTime), 2);
            sb.append(AbstractJsonLexerKt.COLON);
            append0PaddedUint(sb, SecFromTime(dLocalTime), 2);
            int iFloor = (int) Math.floor((((double) context.getTimeZone().getRawOffset()) + DaylightSavingTA(context, d)) / msPerMinute);
            int i2 = ((iFloor / 60) * 100) + (iFloor % 60);
            if (i2 > 0) {
                sb.append(" GMT+");
            } else {
                sb.append(" GMT-");
                i2 = -i2;
            }
            append0PaddedUint(sb, i2, 4);
            if (d < 0.0d) {
                d = MakeDate(MakeDay(EquivalentYear(YearFromTime(dLocalTime)), MonthFromTime(d), DateFromTime(d)), TimeWithinDay(d));
            }
            sb.append(" (");
            sb.append(timeZoneFormatter.format(Instant.ofEpochMilli((long) d).atZone(DesugarTimeZone.toZoneId(context.getTimeZone()))));
            sb.append(')');
        }
        return sb.toString();
    }

    private static Object jsConstructor(Context context, Object[] objArr) {
        double number;
        NativeDate nativeDate = new NativeDate();
        if (objArr.length == 0) {
            nativeDate.date = now();
            return nativeDate;
        }
        if (objArr.length == 1) {
            Object obj = objArr[0];
            if (obj instanceof NativeDate) {
                nativeDate.date = ((NativeDate) obj).date;
                return nativeDate;
            }
            Object primitive = ScriptRuntime.toPrimitive(obj);
            if (primitive instanceof CharSequence) {
                number = date_parseString(context, primitive.toString());
            } else {
                number = ScriptRuntime.toNumber(primitive);
            }
            nativeDate.date = TimeClip(number);
            return nativeDate;
        }
        double dDate_msecFromArgs = date_msecFromArgs(objArr);
        if (!Double.isNaN(dDate_msecFromArgs) && !Double.isInfinite(dDate_msecFromArgs)) {
            dDate_msecFromArgs = TimeClip(internalUTC(context, dDate_msecFromArgs));
        }
        nativeDate.date = dDate_msecFromArgs;
        return nativeDate;
    }

    private static String toLocale_helper(Context context, double d, int i, Object[] objArr) {
        DateTimeFormatter dateTimeFormatterWithLocale;
        if (i != 5) {
            if (i != 6) {
                if (i == 7) {
                    if (context.getLanguageVersion() >= 200) {
                        dateTimeFormatterWithLocale = localeDateFormatterES6;
                    } else {
                        dateTimeFormatterWithLocale = localeDateFormatter;
                    }
                } else {
                    throw new AssertionError();
                }
            } else if (context.getLanguageVersion() >= 200) {
                dateTimeFormatterWithLocale = localeTimeFormatterES6;
            } else {
                dateTimeFormatterWithLocale = localeTimeFormatter;
            }
        } else if (context.getLanguageVersion() >= 200) {
            dateTimeFormatterWithLocale = localeDateTimeFormatterES6;
        } else {
            dateTimeFormatterWithLocale = localeDateTimeFormatter;
        }
        ArrayList arrayList = new ArrayList();
        if (objArr.length != 0) {
            Object obj = objArr[0];
            if (obj instanceof NativeArray) {
                Iterator it = ((NativeArray) obj).iterator();
                while (it.hasNext()) {
                    arrayList.add(Context.toString(it.next()));
                }
            } else {
                arrayList.add(Context.toString(obj));
            }
        }
        List listAsList = Arrays.asList(Locale.getAvailableLocales());
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            Locale localeForLanguageTag = Locale.forLanguageTag((String) it2.next());
            if (listAsList.contains(localeForLanguageTag)) {
                dateTimeFormatterWithLocale = dateTimeFormatterWithLocale.withLocale(localeForLanguageTag);
                break;
            }
        }
        return dateTimeFormatterWithLocale.format(Instant.ofEpochMilli((long) d).atZone(DesugarTimeZone.toZoneId(context.getTimeZone()))).replace(" ", Stream.ID_UNKNOWN);
    }

    private static String js_toUTCString(double d) {
        StringBuilder sb = new StringBuilder(60);
        appendWeekDayName(sb, WeekDay(d));
        sb.append(", ");
        append0PaddedUint(sb, DateFromTime(d), 2);
        sb.append(' ');
        appendMonthName(sb, MonthFromTime(d));
        sb.append(' ');
        int iYearFromTime = YearFromTime(d);
        if (iYearFromTime < 0) {
            sb.append('-');
            iYearFromTime = -iYearFromTime;
        }
        append0PaddedUint(sb, iYearFromTime, 4);
        sb.append(' ');
        append0PaddedUint(sb, HourFromTime(d), 2);
        sb.append(AbstractJsonLexerKt.COLON);
        append0PaddedUint(sb, MinFromTime(d), 2);
        sb.append(AbstractJsonLexerKt.COLON);
        append0PaddedUint(sb, SecFromTime(d), 2);
        sb.append(" GMT");
        return sb.toString();
    }

    private static String js_toISOString(double d) {
        StringBuilder sb = new StringBuilder(27);
        int iYearFromTime = YearFromTime(d);
        if (iYearFromTime < 0) {
            sb.append('-');
            append0PaddedUint(sb, -iYearFromTime, 6);
        } else if (iYearFromTime > 9999) {
            sb.append('+');
            append0PaddedUint(sb, iYearFromTime, 6);
        } else {
            append0PaddedUint(sb, iYearFromTime, 4);
        }
        sb.append('-');
        append0PaddedUint(sb, MonthFromTime(d) + 1, 2);
        sb.append('-');
        append0PaddedUint(sb, DateFromTime(d), 2);
        sb.append('T');
        append0PaddedUint(sb, HourFromTime(d), 2);
        sb.append(AbstractJsonLexerKt.COLON);
        append0PaddedUint(sb, MinFromTime(d), 2);
        sb.append(AbstractJsonLexerKt.COLON);
        append0PaddedUint(sb, SecFromTime(d), 2);
        sb.append('.');
        append0PaddedUint(sb, msFromTime(d), 3);
        sb.append('Z');
        return sb.toString();
    }

    private static void append0PaddedUint(StringBuilder sb, int i, int i2) {
        int i3;
        if (i < 0) {
            Kit.codeBug();
        }
        int i4 = i2 - 1;
        if (i >= 10) {
            i3 = 1000000000;
            if (i < 1000000000) {
                i3 = 1;
                while (true) {
                    int i5 = i3 * 10;
                    if (i < i5) {
                        break;
                    }
                    i4--;
                    i3 = i5;
                }
            } else {
                i4 = i2 - 10;
            }
        } else {
            i3 = 1;
        }
        while (i4 > 0) {
            sb.append('0');
            i4--;
        }
        while (i3 != 1) {
            sb.append((char) ((i / i3) + 48));
            i %= i3;
            i3 /= 10;
        }
        sb.append((char) (i + 48));
    }

    private static void appendMonthName(StringBuilder sb, int i) {
        int i2 = i * 3;
        for (int i3 = 0; i3 != 3; i3++) {
            sb.append("JanFebMarAprMayJunJulAugSepOctNovDec".charAt(i2 + i3));
        }
    }

    private static void appendWeekDayName(StringBuilder sb, int i) {
        int i2 = i * 3;
        for (int i3 = 0; i3 != 3; i3++) {
            sb.append("SunMonTueWedThuFriSat".charAt(i2 + i3));
        }
    }

    /* JADX WARN: Code duplicated, block: B:23:0x002b  */
    /* JADX WARN: Code duplicated, block: B:24:0x002d  */
    /* JADX WARN: Code duplicated, block: B:27:0x0034  */
    /* JADX WARN: Code duplicated, block: B:29:0x0040  */
    /* JADX WARN: Code duplicated, block: B:33:0x004e  */
    /* JADX WARN: Code duplicated, block: B:36:0x0054  */
    private static double makeTime(Context context, double d, Object[] objArr, int i) {
        boolean z;
        int i2;
        int length;
        int i3;
        boolean z2;
        double dHourFromTime;
        double dMinFromTime;
        double dSecFromTime;
        double dMsFromTime;
        double number;
        if (objArr.length == 0) {
            return Double.NaN;
        }
        int i4 = 0;
        switch (i) {
            case 31:
                z = true;
                i2 = 1;
                if (objArr.length < i2) {
                    length = objArr.length;
                } else {
                    length = i2;
                }
                double[] dArr = new double[4];
                z2 = false;
                for (i3 = 0; i3 < length; i3++) {
                    number = ScriptRuntime.toNumber(objArr[i3]);
                    if (!Double.isNaN(number) || Double.isInfinite(number)) {
                        z2 = true;
                    } else {
                        dArr[i3] = ScriptRuntime.toInteger(number);
                    }
                }
                if (!z2 || Double.isNaN(d)) {
                    return Double.NaN;
                }
                double dLocalTime = z ? LocalTime(context, d) : d;
                if (i2 >= 4 && length > 0) {
                    dHourFromTime = dArr[0];
                    i4 = 1;
                } else {
                    dHourFromTime = HourFromTime(dLocalTime);
                }
                if (i2 >= 3 && i4 < length) {
                    dMinFromTime = dArr[i4];
                    i4++;
                } else {
                    dMinFromTime = MinFromTime(dLocalTime);
                }
                if (i2 >= 2 && i4 < length) {
                    dSecFromTime = dArr[i4];
                    i4++;
                } else {
                    dSecFromTime = SecFromTime(dLocalTime);
                }
                double d2 = dSecFromTime;
                if (i4 < length) {
                    dMsFromTime = dArr[i4];
                } else {
                    dMsFromTime = msFromTime(dLocalTime);
                }
                double dMakeDate = MakeDate(Day(dLocalTime), MakeTime(dHourFromTime, dMinFromTime, d2, dMsFromTime));
                if (z) {
                    dMakeDate = internalUTC(context, dMakeDate);
                }
                return TimeClip(dMakeDate);
            case 32:
                z = false;
                i2 = 1;
                if (objArr.length < i2) {
                    length = objArr.length;
                } else {
                    length = i2;
                }
                double[] dArr2 = new double[4];
                z2 = false;
                while (i3 < length) {
                    number = ScriptRuntime.toNumber(objArr[i3]);
                    if (Double.isNaN(number)) {
                        z2 = true;
                    } else {
                        z2 = true;
                    }
                }
                if (z2) {
                    break;
                }
                return Double.NaN;
            case 33:
                z = true;
                i2 = 2;
                if (objArr.length < i2) {
                    length = objArr.length;
                } else {
                    length = i2;
                }
                double[] dArr3 = new double[4];
                z2 = false;
                while (i3 < length) {
                    number = ScriptRuntime.toNumber(objArr[i3]);
                    if (Double.isNaN(number)) {
                        z2 = true;
                    } else {
                        z2 = true;
                    }
                }
                if (z2) {
                    break;
                }
                return Double.NaN;
            case 34:
                z = false;
                i2 = 2;
                if (objArr.length < i2) {
                    length = objArr.length;
                } else {
                    length = i2;
                }
                double[] dArr4 = new double[4];
                z2 = false;
                while (i3 < length) {
                    number = ScriptRuntime.toNumber(objArr[i3]);
                    if (Double.isNaN(number)) {
                        z2 = true;
                    } else {
                        z2 = true;
                    }
                }
                if (z2) {
                    break;
                }
                return Double.NaN;
            case 35:
                z = true;
                i2 = 3;
                if (objArr.length < i2) {
                    length = objArr.length;
                } else {
                    length = i2;
                }
                double[] dArr5 = new double[4];
                z2 = false;
                while (i3 < length) {
                    number = ScriptRuntime.toNumber(objArr[i3]);
                    if (Double.isNaN(number)) {
                        z2 = true;
                    } else {
                        z2 = true;
                    }
                }
                if (z2) {
                    break;
                }
                return Double.NaN;
            case 36:
                z = false;
                i2 = 3;
                if (objArr.length < i2) {
                    length = objArr.length;
                } else {
                    length = i2;
                }
                double[] dArr6 = new double[4];
                z2 = false;
                while (i3 < length) {
                    number = ScriptRuntime.toNumber(objArr[i3]);
                    if (Double.isNaN(number)) {
                        z2 = true;
                    } else {
                        z2 = true;
                    }
                }
                if (z2) {
                    break;
                }
                return Double.NaN;
            case 37:
                z = true;
                i2 = 4;
                if (objArr.length < i2) {
                    length = objArr.length;
                } else {
                    length = i2;
                }
                double[] dArr7 = new double[4];
                z2 = false;
                while (i3 < length) {
                    number = ScriptRuntime.toNumber(objArr[i3]);
                    if (Double.isNaN(number)) {
                        z2 = true;
                    } else {
                        z2 = true;
                    }
                }
                if (z2) {
                    break;
                }
                return Double.NaN;
            case 38:
                z = false;
                i2 = 4;
                if (objArr.length < i2) {
                    length = objArr.length;
                } else {
                    length = i2;
                }
                double[] dArr8 = new double[4];
                z2 = false;
                while (i3 < length) {
                    number = ScriptRuntime.toNumber(objArr[i3]);
                    if (Double.isNaN(number)) {
                        z2 = true;
                    } else {
                        z2 = true;
                    }
                }
                if (z2) {
                    break;
                }
                return Double.NaN;
            default:
                throw Kit.codeBug();
        }
    }

    /* JADX WARN: Code duplicated, block: B:20:0x0025  */
    /* JADX WARN: Code duplicated, block: B:21:0x0027  */
    /* JADX WARN: Code duplicated, block: B:24:0x002e  */
    /* JADX WARN: Code duplicated, block: B:26:0x003a  */
    /* JADX WARN: Code duplicated, block: B:30:0x0048  */
    /* JADX WARN: Code duplicated, block: B:33:0x004e A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:34:0x004f  */
    /* JADX WARN: Code duplicated, block: B:36:0x0055 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:37:0x0057 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:38:0x0058  */
    /* JADX WARN: Code duplicated, block: B:39:0x005b A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:40:0x005d  */
    /* JADX WARN: Code duplicated, block: B:41:0x0062  */
    /* JADX WARN: Code duplicated, block: B:43:0x0066 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:45:0x006d  */
    /* JADX WARN: Code duplicated, block: B:47:0x0075 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:49:0x007e  */
    /* JADX WARN: Code duplicated, block: B:51:0x0086  */
    /* JADX WARN: Code duplicated, block: B:52:0x0089  */
    /* JADX WARN: Code duplicated, block: B:55:0x009e  */
    private static double makeDate(Context context, double d, Object[] objArr, int i) {
        boolean z;
        int i2;
        int length;
        double[] dArr;
        int i3;
        boolean z2;
        double dLocalTime;
        double dYearFromTime;
        double dMonthFromTime;
        double dDateFromTime;
        double dMakeDate;
        double number;
        if (objArr.length == 0) {
            return Double.NaN;
        }
        int i4 = 0;
        switch (i) {
            case 39:
                z = true;
                i2 = 1;
                if (objArr.length < i2) {
                    length = objArr.length;
                } else {
                    length = i2;
                }
                dArr = new double[3];
                z2 = false;
                for (i3 = 0; i3 < length; i3++) {
                    number = ScriptRuntime.toNumber(objArr[i3]);
                    if (!Double.isNaN(number) || Double.isInfinite(number)) {
                        z2 = true;
                    } else {
                        dArr[i3] = ScriptRuntime.toInteger(number);
                    }
                }
                if (z2) {
                    return Double.NaN;
                }
                if (Double.isNaN(d)) {
                    if (i2 < 3) {
                        return Double.NaN;
                    }
                    dLocalTime = 0.0d;
                } else if (z) {
                    dLocalTime = LocalTime(context, d);
                } else {
                    dLocalTime = d;
                }
                if (i2 < 3 && length > 0) {
                    dYearFromTime = dArr[0];
                    i4 = 1;
                } else {
                    dYearFromTime = YearFromTime(dLocalTime);
                }
                if (i2 < 2 && i4 < length) {
                    dMonthFromTime = dArr[i4];
                    i4++;
                } else {
                    dMonthFromTime = MonthFromTime(dLocalTime);
                }
                if (i4 < length) {
                    dDateFromTime = dArr[i4];
                } else {
                    dDateFromTime = DateFromTime(dLocalTime);
                }
                dMakeDate = MakeDate(MakeDay(dYearFromTime, dMonthFromTime, dDateFromTime), TimeWithinDay(dLocalTime));
                if (z) {
                    dMakeDate = internalUTC(context, dMakeDate);
                }
                return TimeClip(dMakeDate);
            case 40:
                z = false;
                i2 = 1;
                if (objArr.length < i2) {
                    length = objArr.length;
                } else {
                    length = i2;
                }
                dArr = new double[3];
                z2 = false;
                while (i3 < length) {
                    number = ScriptRuntime.toNumber(objArr[i3]);
                    if (Double.isNaN(number)) {
                        z2 = true;
                    } else {
                        z2 = true;
                    }
                }
                if (z2) {
                    return Double.NaN;
                }
                if (Double.isNaN(d)) {
                    if (i2 < 3) {
                        return Double.NaN;
                    }
                    dLocalTime = 0.0d;
                } else if (z) {
                    dLocalTime = LocalTime(context, d);
                } else {
                    dLocalTime = d;
                }
                if (i2 < 3) {
                    dYearFromTime = YearFromTime(dLocalTime);
                } else {
                    dYearFromTime = YearFromTime(dLocalTime);
                }
                if (i2 < 2) {
                    dMonthFromTime = MonthFromTime(dLocalTime);
                } else {
                    dMonthFromTime = MonthFromTime(dLocalTime);
                }
                if (i4 < length) {
                    dDateFromTime = dArr[i4];
                } else {
                    dDateFromTime = DateFromTime(dLocalTime);
                }
                dMakeDate = MakeDate(MakeDay(dYearFromTime, dMonthFromTime, dDateFromTime), TimeWithinDay(dLocalTime));
                if (z) {
                    dMakeDate = internalUTC(context, dMakeDate);
                }
                return TimeClip(dMakeDate);
            case 41:
                z = true;
                i2 = 2;
                if (objArr.length < i2) {
                    length = objArr.length;
                } else {
                    length = i2;
                }
                dArr = new double[3];
                z2 = false;
                while (i3 < length) {
                    number = ScriptRuntime.toNumber(objArr[i3]);
                    if (Double.isNaN(number)) {
                        z2 = true;
                    } else {
                        z2 = true;
                    }
                }
                if (z2) {
                    return Double.NaN;
                }
                if (Double.isNaN(d)) {
                    if (i2 < 3) {
                        return Double.NaN;
                    }
                    dLocalTime = 0.0d;
                } else if (z) {
                    dLocalTime = LocalTime(context, d);
                } else {
                    dLocalTime = d;
                }
                if (i2 < 3) {
                    dYearFromTime = YearFromTime(dLocalTime);
                } else {
                    dYearFromTime = YearFromTime(dLocalTime);
                }
                if (i2 < 2) {
                    dMonthFromTime = MonthFromTime(dLocalTime);
                } else {
                    dMonthFromTime = MonthFromTime(dLocalTime);
                }
                if (i4 < length) {
                    dDateFromTime = dArr[i4];
                } else {
                    dDateFromTime = DateFromTime(dLocalTime);
                }
                dMakeDate = MakeDate(MakeDay(dYearFromTime, dMonthFromTime, dDateFromTime), TimeWithinDay(dLocalTime));
                if (z) {
                    dMakeDate = internalUTC(context, dMakeDate);
                }
                return TimeClip(dMakeDate);
            case 42:
                z = false;
                i2 = 2;
                if (objArr.length < i2) {
                    length = objArr.length;
                } else {
                    length = i2;
                }
                dArr = new double[3];
                z2 = false;
                while (i3 < length) {
                    number = ScriptRuntime.toNumber(objArr[i3]);
                    if (Double.isNaN(number)) {
                        z2 = true;
                    } else {
                        z2 = true;
                    }
                }
                if (z2) {
                    return Double.NaN;
                }
                if (Double.isNaN(d)) {
                    if (i2 < 3) {
                        return Double.NaN;
                    }
                    dLocalTime = 0.0d;
                } else if (z) {
                    dLocalTime = LocalTime(context, d);
                } else {
                    dLocalTime = d;
                }
                if (i2 < 3) {
                    dYearFromTime = YearFromTime(dLocalTime);
                } else {
                    dYearFromTime = YearFromTime(dLocalTime);
                }
                if (i2 < 2) {
                    dMonthFromTime = MonthFromTime(dLocalTime);
                } else {
                    dMonthFromTime = MonthFromTime(dLocalTime);
                }
                if (i4 < length) {
                    dDateFromTime = dArr[i4];
                } else {
                    dDateFromTime = DateFromTime(dLocalTime);
                }
                dMakeDate = MakeDate(MakeDay(dYearFromTime, dMonthFromTime, dDateFromTime), TimeWithinDay(dLocalTime));
                if (z) {
                    dMakeDate = internalUTC(context, dMakeDate);
                }
                return TimeClip(dMakeDate);
            case 43:
                z = true;
                i2 = 3;
                if (objArr.length < i2) {
                    length = objArr.length;
                } else {
                    length = i2;
                }
                dArr = new double[3];
                z2 = false;
                while (i3 < length) {
                    number = ScriptRuntime.toNumber(objArr[i3]);
                    if (Double.isNaN(number)) {
                        z2 = true;
                    } else {
                        z2 = true;
                    }
                }
                if (z2) {
                    return Double.NaN;
                }
                if (Double.isNaN(d)) {
                    if (i2 < 3) {
                        return Double.NaN;
                    }
                    dLocalTime = 0.0d;
                } else if (z) {
                    dLocalTime = LocalTime(context, d);
                } else {
                    dLocalTime = d;
                }
                if (i2 < 3) {
                    dYearFromTime = YearFromTime(dLocalTime);
                } else {
                    dYearFromTime = YearFromTime(dLocalTime);
                }
                if (i2 < 2) {
                    dMonthFromTime = MonthFromTime(dLocalTime);
                } else {
                    dMonthFromTime = MonthFromTime(dLocalTime);
                }
                if (i4 < length) {
                    dDateFromTime = dArr[i4];
                } else {
                    dDateFromTime = DateFromTime(dLocalTime);
                }
                dMakeDate = MakeDate(MakeDay(dYearFromTime, dMonthFromTime, dDateFromTime), TimeWithinDay(dLocalTime));
                if (z) {
                    dMakeDate = internalUTC(context, dMakeDate);
                }
                return TimeClip(dMakeDate);
            case 44:
                z = false;
                i2 = 3;
                if (objArr.length < i2) {
                    length = objArr.length;
                } else {
                    length = i2;
                }
                dArr = new double[3];
                z2 = false;
                while (i3 < length) {
                    number = ScriptRuntime.toNumber(objArr[i3]);
                    if (Double.isNaN(number)) {
                        z2 = true;
                    } else {
                        z2 = true;
                    }
                }
                if (z2) {
                    return Double.NaN;
                }
                if (Double.isNaN(d)) {
                    if (i2 < 3) {
                        return Double.NaN;
                    }
                    dLocalTime = 0.0d;
                } else if (z) {
                    dLocalTime = LocalTime(context, d);
                } else {
                    dLocalTime = d;
                }
                if (i2 < 3) {
                    dYearFromTime = YearFromTime(dLocalTime);
                } else {
                    dYearFromTime = YearFromTime(dLocalTime);
                }
                if (i2 < 2) {
                    dMonthFromTime = MonthFromTime(dLocalTime);
                } else {
                    dMonthFromTime = MonthFromTime(dLocalTime);
                }
                if (i4 < length) {
                    dDateFromTime = dArr[i4];
                } else {
                    dDateFromTime = DateFromTime(dLocalTime);
                }
                dMakeDate = MakeDate(MakeDay(dYearFromTime, dMonthFromTime, dDateFromTime), TimeWithinDay(dLocalTime));
                if (z) {
                    dMakeDate = internalUTC(context, dMakeDate);
                }
                return TimeClip(dMakeDate);
            default:
                throw Kit.codeBug();
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // org.mozilla.javascript.IdScriptableObject
    protected int findPrototypeId(String str) {
        str.hashCode();
        byte b = -1;
        switch (str.hashCode()) {
            case -2020003546:
                if (str.equals("toLocaleString")) {
                    b = 0;
                }
                break;
            case -1919317088:
                if (str.equals("getUTCMilliseconds")) {
                    b = 1;
                }
                break;
            case -1781441930:
                if (str.equals("toSource")) {
                    b = 2;
                }
                break;
            case -1776922004:
                if (str.equals("toString")) {
                    b = 3;
                }
                break;
            case -1672509548:
                if (str.equals("setMilliseconds")) {
                    b = 4;
                }
                break;
            case -1588406278:
                if (str.equals("constructor")) {
                    b = 5;
                }
                break;
            case -1573876166:
                if (str.equals("toDateString")) {
                    b = 6;
                }
                break;
            case -1326181948:
                if (str.equals("toGMTString")) {
                    b = 7;
                }
                break;
            case -1312629223:
                if (str.equals("toTimeString")) {
                    b = 8;
                }
                break;
            case -1288010167:
                if (str.equals("getSeconds")) {
                    b = 9;
                }
                break;
            case -1249364890:
                if (str.equals("getDay")) {
                    b = 10;
                }
                break;
            case -1031333299:
                if (str.equals("setUTCHours")) {
                    b = 11;
                }
                break;
            case -1026722370:
                if (str.equals("setUTCMonth")) {
                    b = 12;
                }
                break;
            case -999283436:
                if (str.equals("setUTCMilliseconds")) {
                    b = 13;
                }
                break;
            case -974463506:
                if (str.equals("setFullYear")) {
                    b = 14;
                }
                break;
            case -973718674:
                if (str.equals("setUTCFullYear")) {
                    b = 15;
                }
                break;
            case -942753471:
                if (str.equals("getUTCHours")) {
                    b = 16;
                }
                break;
            case -938142542:
                if (str.equals("getUTCMonth")) {
                    b = 17;
                }
                break;
            case -885883678:
                if (str.equals("getFullYear")) {
                    b = 18;
                }
                break;
            case -869188125:
                if (str.equals("toJSON")) {
                    b = 19;
                }
                break;
            case -493762846:
                if (str.equals("getTimezoneOffset")) {
                    b = 20;
                }
                break;
            case -256399843:
                if (str.equals("setMinutes")) {
                    b = 21;
                }
                break;
            case -75605980:
                if (str.equals("getDate")) {
                    b = Ascii.SYN;
                }
                break;
            case -75121853:
                if (str.equals("getTime")) {
                    b = Ascii.ETB;
                }
                break;
            case -74977101:
                if (str.equals("getYear")) {
                    b = Ascii.CAN;
                }
                break;
            case -30544068:
                if (str.equals("getUTCDate")) {
                    b = Ascii.EM;
                }
                break;
            case 216418961:
                if (str.equals("getUTCMinutes")) {
                    b = Ascii.SUB;
                }
                break;
            case 231605032:
                if (str.equals("valueOf")) {
                    b = Ascii.ESC;
                }
                break;
            case 546336410:
                if (str.equals("toUTCString")) {
                    b = Ascii.FS;
                }
                break;
            case 648795069:
                if (str.equals("setSeconds")) {
                    b = Ascii.GS;
                }
                break;
            case 798017530:
                if (str.equals("getUTCFullYear")) {
                    b = Ascii.RS;
                }
                break;
            case 830298702:
                if (str.equals("getUTCDay")) {
                    b = Ascii.US;
                }
                break;
            case 990550173:
                if (str.equals("setUTCMinutes")) {
                    b = 32;
                }
                break;
            case 1078252731:
                if (str.equals("toISOString")) {
                    b = 33;
                }
                break;
            case 1121613873:
                if (str.equals("getUTCSeconds")) {
                    b = 34;
                }
                break;
            case 1162969076:
                if (str.equals("toLocaleDateString")) {
                    b = 35;
                }
                break;
            case 1394182093:
                if (str.equals("setHours")) {
                    b = 36;
                }
                break;
            case 1398793022:
                if (str.equals("setMonth")) {
                    b = 37;
                }
                break;
            case 1424216019:
                if (str.equals("toLocaleTimeString")) {
                    b = 38;
                }
                break;
            case 1711705224:
                if (str.equals("getMilliseconds")) {
                    b = 39;
                }
                break;
            case 1895745085:
                if (str.equals("setUTCSeconds")) {
                    b = 40;
                }
                break;
            case 1906261168:
                if (str.equals("setUTCDate")) {
                    b = 41;
                }
                break;
            case 1955294553:
                if (str.equals("getHours")) {
                    b = 42;
                }
                break;
            case 1959905482:
                if (str.equals("getMonth")) {
                    b = 43;
                }
                break;
            case 1984503600:
                if (str.equals("setDate")) {
                    b = 44;
                }
                break;
            case 1984987727:
                if (str.equals("setTime")) {
                    b = 45;
                }
                break;
            case 1985132479:
                if (str.equals("setYear")) {
                    b = 46;
                }
                break;
            case 2101762217:
                if (str.equals("getMinutes")) {
                    b = 47;
                }
                break;
        }
        switch (b) {
            case 0:
                return 5;
            case 1:
                return 28;
            case 2:
                return 9;
            case 3:
                return 2;
            case 4:
                return 31;
            case 5:
                return 1;
            case 6:
                return 4;
            case 7:
                return 8;
            case 8:
                return 3;
            case 9:
                return 25;
            case 10:
                return 19;
            case 11:
                return 38;
            case 12:
                return 42;
            case 13:
                return 32;
            case 14:
                return 43;
            case 15:
                return 44;
            case 16:
                return 22;
            case 17:
                return 16;
            case 18:
                return 13;
            case 19:
                return 47;
            case 20:
                return 29;
            case 21:
                return 35;
            case 22:
                return 17;
            case 23:
                return 11;
            case 24:
                return 12;
            case 25:
                return 18;
            case 26:
                return 24;
            case 27:
                return 10;
            case 28:
                return 8;
            case 29:
                return 33;
            case 30:
                return 14;
            case 31:
                return 20;
            case 32:
                return 36;
            case 33:
                return 46;
            case 34:
                return 26;
            case 35:
                return 7;
            case 36:
                return 37;
            case 37:
                return 41;
            case 38:
                return 6;
            case 39:
                return 27;
            case 40:
                return 34;
            case 41:
                return 40;
            case 42:
                return 21;
            case 43:
                return 15;
            case 44:
                return 39;
            case 45:
                return 30;
            case 46:
                return 45;
            case 47:
                return 23;
            default:
                return 0;
        }
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected int findPrototypeId(Symbol symbol) {
        return SymbolKey.TO_PRIMITIVE.equals(symbol) ? 48 : 0;
    }
}
