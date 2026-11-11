package com.lagradost.cloudstream3.ui.subtitles;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Layout;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.TextView;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.media3.common.text.Cue;
import androidx.media3.ui.CaptionStyleCompat;
import androidx.media3.ui.SubtitleView;
import androidx.preference.PreferenceManager;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.jaredrummler.android.colorpicker.ColorPickerDialog;
import com.lagradost.cloudstream3.CloudStreamApp;
import com.lagradost.cloudstream3.CommonActivity;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.databinding.SubtitleSettingsBinding;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.ui.BaseDialogFragment;
import com.lagradost.cloudstream3.ui.BaseFragment;
import com.lagradost.cloudstream3.ui.player.CustomDecoder;
import com.lagradost.cloudstream3.ui.player.OutlineSpan;
import com.lagradost.cloudstream3.ui.player.RoundedBackgroundColorSpan;
import com.lagradost.cloudstream3.ui.settings.Globals;
import com.lagradost.cloudstream3.utils.AppUtils;
import com.lagradost.cloudstream3.utils.DataStore;
import com.lagradost.cloudstream3.utils.Event;
import com.lagradost.cloudstream3.utils.SingleSelectionHelper;
import com.lagradost.cloudstream3.utils.SubtitleHelper;
import com.lagradost.cloudstream3.utils.UIHelper;
import io.ktor.http.ContentDisposition;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.StringSerializer;
import kotlinx.serialization.modules.SerializersModule;

/* JADX INFO: compiled from: SubtitlesFragment.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 #2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001#B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u001c\u0010\u0005\u001a\u00020\u00062\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\bH\u0002J\u0010\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\tH\u0002J#\u0010\f\u001a\u00020\u0006*\u00020\r2\u0006\u0010\u000b\u001a\u00020\t2\b\u0010\u000e\u001a\u0004\u0018\u00010\tH\u0002¢\u0006\u0002\u0010\u000fJ\f\u0010\u0010\u001a\u00020\u0006*\u00020\rH\u0002J\u0010\u0010\u0011\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\tH\u0002J\b\u0010\u0016\u001a\u00020\u0006H\u0016J\b\u0010\u0017\u001a\u00020\u0006H\u0016J\b\u0010\u0018\u001a\u00020\tH\u0016J\u0010\u0010\u001e\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020 H\u0016J\u0010\u0010!\u001a\u00020\u00062\u0006\u0010\"\u001a\u00020\u0002H\u0016R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0019\u001a\u00020\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001d¨\u0006$"}, d2 = {"Lcom/lagradost/cloudstream3/ui/subtitles/SubtitlesFragment;", "Lcom/lagradost/cloudstream3/ui/BaseDialogFragment;", "Lcom/lagradost/cloudstream3/databinding/SubtitleSettingsBinding;", "<init>", "()V", "onColorSelected", "", "stuff", "Lkotlin/Pair;", "", "onDialogDismissed", "id", "setColor", "Landroid/content/Context;", "color", "(Landroid/content/Context;ILjava/lang/Integer;)V", "updateState", "getColor", "state", "Lcom/lagradost/cloudstream3/ui/subtitles/SaveCaptionStyle;", "hide", "", "onDestroy", "onStart", "getTheme", "systemBarsAddPadding", "getSystemBarsAddPadding", "()Z", "setSystemBarsAddPadding", "(Z)V", "fixLayout", "view", "Landroid/view/View;", "onBindingCreated", "binding", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class SubtitlesFragment extends BaseDialogFragment<SubtitleSettingsBinding> {
    private static SaveCaptionStyle cachedSubtitleStyle;
    private boolean hide;
    private SaveCaptionStyle state;
    private boolean systemBarsAddPadding;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Event<SaveCaptionStyle> applyStyleEvent = new Event<>();
    private static final Regex captionRegex = new Regex("(-\\s?|)[\\[({][\\S\\s]*?[])}]\\s*");

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.subtitles.SubtitlesFragment$1, reason: invalid class name */
    /* JADX INFO: compiled from: SubtitlesFragment.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function3<LayoutInflater, ViewGroup, Boolean, SubtitleSettingsBinding> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        AnonymousClass1() {
            super(3, SubtitleSettingsBinding.class, "inflate", "inflate(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Z)Lcom/lagradost/cloudstream3/databinding/SubtitleSettingsBinding;", 0);
        }

        public final SubtitleSettingsBinding invoke(LayoutInflater p0, ViewGroup viewGroup, boolean z) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return SubtitleSettingsBinding.inflate(p0, viewGroup, z);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ SubtitleSettingsBinding invoke(LayoutInflater layoutInflater, ViewGroup viewGroup, Boolean bool) {
            return invoke(layoutInflater, viewGroup, bool.booleanValue());
        }
    }

    public SubtitlesFragment() {
        super(new BaseFragment.BindingCreator.Inflate(AnonymousClass1.INSTANCE));
        this.hide = true;
        this.systemBarsAddPadding = Globals.INSTANCE.isLayout(6);
    }

    /* JADX INFO: compiled from: SubtitlesFragment.kt */
    @Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u0011J\u0012\u0010\u0012\u001a\u00020\u0013*\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0006J\u0014\u0010\u0015\u001a\u00020\u0016*\u00020\u00172\u0006\u0010\u000f\u001a\u00020\u0006H\u0002J\u001a\u0010\u0018\u001a\u00020\f2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u0011J\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001dH\u0002J\u0012\u0010 \u001a\u00020\f*\u00020\u00172\u0006\u0010\u0014\u001a\u00020\u0006J\u0006\u0010!\u001a\u00020\u0006J\u0012\u0010\"\u001a\b\u0012\u0004\u0012\u00020$0#*\u00020\u0017H\u0002J\u0018\u0010%\u001a\u00020\u001d2\u0006\u0010&\u001a\u00020\u001d2\u0006\u0010'\u001a\u00020(H\u0002J\f\u0010)\u001a\b\u0012\u0004\u0012\u00020*0#J\u0006\u0010+\u001a\u00020*R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006,"}, d2 = {"Lcom/lagradost/cloudstream3/ui/subtitles/SubtitlesFragment$Companion;", "", "<init>", "()V", "applyStyleEvent", "Lcom/lagradost/cloudstream3/utils/Event;", "Lcom/lagradost/cloudstream3/ui/subtitles/SaveCaptionStyle;", "getApplyStyleEvent", "()Lcom/lagradost/cloudstream3/utils/Event;", "captionRegex", "Lkotlin/text/Regex;", "setSubtitleViewStyle", "", "view", "Landroidx/media3/ui/SubtitleView;", "data", "applyElevation", "", "applyStyle", "Landroidx/media3/common/text/Cue$Builder;", "style", "fromSaveToStyle", "Landroidx/media3/ui/CaptionStyleCompat;", "Landroid/content/Context;", "push", "activity", "Landroid/app/Activity;", "hide", "getDefColor", "", "id", "cachedSubtitleStyle", "saveStyle", "getCurrentSavedStyle", "getSavedFonts", "", "Ljava/io/File;", "getPixels", "unit", ContentDisposition.Parameters.Size, "", "getDownloadSubsLanguageTagIETF", "", "getAutoSelectLanguageTagIETF", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final int getDefColor(int id) {
            if (id != 0) {
                return id != 1 ? 0 : -16777216;
            }
            return -1;
        }

        private Companion() {
        }

        public final Event<SaveCaptionStyle> getApplyStyleEvent() {
            return SubtitlesFragment.applyStyleEvent;
        }

        public final void setSubtitleViewStyle(SubtitleView view, SaveCaptionStyle data, boolean applyElevation) {
            Context context;
            Intrinsics.checkNotNullParameter(data, "data");
            if (view == null || (context = view.getContext()) == null) {
                return;
            }
            view.setStyle(fromSaveToStyle(context, data));
            if (applyElevation) {
                view.setPadding(view.getPaddingLeft(), UIHelper.INSTANCE.getToPx(data.getElevation()), view.getPaddingRight(), view.getPaddingBottom());
            }
            Float fixedTextSize = data.getFixedTextSize();
            view.setFixedTextSize(2, fixedTextSize != null ? fixedTextSize.floatValue() : 25.0f);
            view.setBottomPaddingFraction(0.0f);
        }

        public final Cue.Builder applyStyle(Cue.Builder builder, SaveCaptionStyle style) {
            Intrinsics.checkNotNullParameter(builder, "<this>");
            Intrinsics.checkNotNullParameter(style, "style");
            Float edgeSize = style.getEdgeSize();
            builder.setTextSize(-3.4028235E38f, Integer.MIN_VALUE);
            CharSequence text = builder.getText();
            if (text != null) {
                SpannableString spannableStringValueOf = SpannableString.valueOf(text);
                if (edgeSize != null) {
                    spannableStringValueOf.setSpan(new OutlineSpan(edgeSize.floatValue()), 0, spannableStringValueOf.length(), 33);
                }
                builder.setText(spannableStringValueOf);
            }
            CharSequence text2 = builder.getText();
            if (text2 != null) {
                SpannableString spannableStringValueOf2 = SpannableString.valueOf(text2);
                Pair pair = TuplesKt.to(Boolean.valueOf(style.getBold()), Boolean.valueOf(style.getItalic()));
                int i = 1;
                if (Intrinsics.areEqual(pair, TuplesKt.to(true, true))) {
                    i = 3;
                } else if (!Intrinsics.areEqual(pair, TuplesKt.to(true, false))) {
                    if (Intrinsics.areEqual(pair, TuplesKt.to(false, true))) {
                        i = 2;
                    } else {
                        Intrinsics.areEqual(pair, TuplesKt.to(false, false));
                        i = 0;
                    }
                }
                if (i != 0) {
                    spannableStringValueOf2.setSpan(new StyleSpan(i), 0, spannableStringValueOf2.length(), 33);
                }
                builder.setText(spannableStringValueOf2);
            }
            CharSequence text3 = builder.getText();
            if (text3 != null) {
                SpannableString spannableStringValueOf3 = SpannableString.valueOf(text3);
                Float backgroundRadius = style.getBackgroundRadius();
                if (backgroundRadius != null && style.getBackgroundColor() != 0) {
                    int backgroundColor = style.getBackgroundColor();
                    Layout.Alignment textAlignment = builder.getTextAlignment();
                    if (textAlignment == null) {
                        textAlignment = Layout.Alignment.ALIGN_CENTER;
                    }
                    spannableStringValueOf3.setSpan(new RoundedBackgroundColorSpan(backgroundColor, textAlignment, (backgroundRadius.floatValue() * 0.5f) + 2.0f, backgroundRadius.floatValue()), 0, spannableStringValueOf3.length(), 33);
                }
                builder.setText(spannableStringValueOf3);
            }
            CharSequence text4 = builder.getText();
            if (text4 != null && style.getRemoveCaptions()) {
                builder.setText(SubtitlesFragment.captionRegex.replace(text4, ""));
            }
            return CustomDecoder.INSTANCE.setSubtitleAlignment(builder, style.getAlignment());
        }

        /* JADX WARN: Code duplicated, block: B:12:0x0031  */
        /* JADX WARN: Code duplicated, block: B:14:0x0037  */
        /* JADX WARN: Code duplicated, block: B:16:0x0043  */
        /* JADX WARN: Code duplicated, block: B:18:0x0047  */
        private final CaptionStyleCompat fromSaveToStyle(Context context, SaveCaptionStyle saveCaptionStyle) {
            Typeface typefaceCreateFromFile;
            Typeface typeface;
            Typeface font;
            int foregroundColor = saveCaptionStyle.getForegroundColor();
            int backgroundColor = saveCaptionStyle.getBackgroundRadius() == null ? saveCaptionStyle.getBackgroundColor() : 0;
            int windowColor = saveCaptionStyle.getWindowColor();
            int edgeType = saveCaptionStyle.getEdgeType();
            int edgeColor = saveCaptionStyle.getEdgeColor();
            String typefaceFilePath = saveCaptionStyle.getTypefaceFilePath();
            if (typefaceFilePath != null) {
                try {
                    typefaceCreateFromFile = Typeface.createFromFile(new File(typefaceFilePath));
                } catch (Exception unused) {
                    typefaceCreateFromFile = null;
                }
                if (typefaceCreateFromFile == null) {
                    Integer typeface2 = saveCaptionStyle.getTypeface();
                    font = typeface2 != null ? ResourcesCompat.getFont(context, typeface2.intValue()) : null;
                    if (font == null) {
                        typefaceCreateFromFile = Typeface.SANS_SERIF;
                        typeface = typefaceCreateFromFile;
                    } else {
                        typeface = font;
                    }
                } else {
                    typeface = typefaceCreateFromFile;
                }
            } else {
                Integer typeface3 = saveCaptionStyle.getTypeface();
                if (typeface3 != null) {
                }
                if (font == null) {
                    typefaceCreateFromFile = Typeface.SANS_SERIF;
                    typeface = typefaceCreateFromFile;
                } else {
                    typeface = font;
                }
            }
            return new CaptionStyleCompat(foregroundColor, backgroundColor, windowColor, edgeType, edgeColor, typeface);
        }

        public static /* synthetic */ void push$default(Companion companion, Activity activity, boolean z, int i, Object obj) {
            if ((i & 2) != 0) {
                z = true;
            }
            companion.push(activity, z);
        }

        public final void push(Activity activity, boolean hide) {
            UIHelper uIHelper = UIHelper.INSTANCE;
            int i = R.id.global_to_navigation_subtitles;
            Bundle bundle = new Bundle();
            bundle.putBoolean("hide", hide);
            bundle.putBoolean("popFragment", true);
            Unit unit = Unit.INSTANCE;
            UIHelper.navigate$default(uIHelper, activity, i, bundle, null, 4, null);
        }

        public final void saveStyle(Context context, SaveCaptionStyle style) {
            Intrinsics.checkNotNullParameter(context, "<this>");
            Intrinsics.checkNotNullParameter(style, "style");
            SubtitlesFragment.cachedSubtitleStyle = style;
            DataStore.INSTANCE.setKey(context, SubtitlesFragmentKt.SUBTITLE_KEY, style);
        }

        public final SaveCaptionStyle getCurrentSavedStyle() {
            Object objM8936constructorimpl;
            Object objDecodeFromString;
            SaveCaptionStyle saveCaptionStyle = SubtitlesFragment.cachedSubtitleStyle;
            if (saveCaptionStyle != null) {
                return saveCaptionStyle;
            }
            Context context = CloudStreamApp.INSTANCE.getContext();
            Object obj = null;
            if (context != null) {
                try {
                    String string = DataStore.INSTANCE.getSharedPrefs(context).getString(SubtitlesFragmentKt.SUBTITLE_KEY, null);
                    if (string != null) {
                        AppUtils appUtils = AppUtils.INSTANCE;
                        try {
                            Result.Companion companion = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(SaveCaptionStyle.INSTANCE.serializer());
                        } catch (Throwable th) {
                            Result.Companion companion2 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                        }
                        if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) != null) {
                            try {
                                Result.Companion companion3 = Result.INSTANCE;
                                objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(SaveCaptionStyle.class), null, 2, null));
                            } catch (Throwable th2) {
                                Result.Companion companion4 = Result.INSTANCE;
                                objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th2));
                            }
                        }
                        if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                            objM8936constructorimpl = null;
                        }
                        KSerializer kSerializer = (KSerializer) objM8936constructorimpl;
                        if (kSerializer != null) {
                            try {
                                objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, string);
                            } catch (SerializationException e) {
                                ArchComponentExtKt.logError(e);
                                objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<SaveCaptionStyle>() { // from class: com.lagradost.cloudstream3.ui.subtitles.SubtitlesFragment$Companion$getCurrentSavedStyle$$inlined$getKey$1
                                });
                            } catch (Throwable unused) {
                                objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<SaveCaptionStyle>() { // from class: com.lagradost.cloudstream3.ui.subtitles.SubtitlesFragment$Companion$getCurrentSavedStyle$$inlined$getKey$1
                                });
                            }
                        } else {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<SaveCaptionStyle>() { // from class: com.lagradost.cloudstream3.ui.subtitles.SubtitlesFragment$Companion$getCurrentSavedStyle$$inlined$getKey$1
                            });
                        }
                        obj = objDecodeFromString;
                    }
                } catch (Exception unused2) {
                }
            }
            SaveCaptionStyle saveCaptionStyle2 = (SaveCaptionStyle) obj;
            if (saveCaptionStyle2 == null) {
                saveCaptionStyle2 = new SaveCaptionStyle(getDefColor(0), getDefColor(2), getDefColor(3), 1, getDefColor(1), (Integer) null, (String) null, 20, (Float) null, (Float) null, false, false, false, false, false, (Float) null, (Integer) null, 130560, (DefaultConstructorMarker) null);
            }
            Companion companion5 = SubtitlesFragment.INSTANCE;
            SubtitlesFragment.cachedSubtitleStyle = saveCaptionStyle2;
            return saveCaptionStyle2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final List<File> getSavedFonts(Context context) {
            File externalFilesDir = context.getExternalFilesDir(null);
            if (externalFilesDir == null) {
                return CollectionsKt.emptyList();
            }
            File file = new File(externalFilesDir.getAbsolutePath() + "/Fonts");
            file.mkdir();
            String[] list = file.list();
            if (list == null) {
                return CollectionsKt.emptyList();
            }
            ArrayList arrayList = new ArrayList();
            for (String str : list) {
                Intrinsics.checkNotNull(str);
                File file2 = (StringsKt.endsWith$default(str, ".ttf", false, 2, (Object) null) || StringsKt.endsWith$default(str, ".otf", false, 2, (Object) null)) ? new File(file.getAbsolutePath() + '/' + str) : null;
                if (file2 != null) {
                    arrayList.add(file2);
                }
            }
            return arrayList;
        }

        private final int getPixels(int unit, float size) {
            DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
            Intrinsics.checkNotNullExpressionValue(displayMetrics, "getDisplayMetrics(...)");
            return (int) TypedValue.applyDimension(unit, size, displayMetrics);
        }

        public final List<String> getDownloadSubsLanguageTagIETF() {
            Object objM8936constructorimpl;
            Object objDecodeFromString;
            Context context = CloudStreamApp.INSTANCE.getContext();
            Object obj = null;
            if (context != null) {
                try {
                    String string = DataStore.INSTANCE.getSharedPrefs(context).getString(SubtitlesFragmentKt.SUBTITLE_DOWNLOAD_KEY, null);
                    if (string != null) {
                        AppUtils appUtils = AppUtils.INSTANCE;
                        try {
                            Result.Companion companion = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(new ArrayListSerializer(StringSerializer.INSTANCE));
                        } catch (Throwable th) {
                            Result.Companion companion2 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                        }
                        if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) != null) {
                            try {
                                Result.Companion companion3 = Result.INSTANCE;
                                objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(List.class), null, 2, null));
                            } catch (Throwable th2) {
                                Result.Companion companion4 = Result.INSTANCE;
                                objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th2));
                            }
                        }
                        if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                            objM8936constructorimpl = null;
                        }
                        KSerializer kSerializer = (KSerializer) objM8936constructorimpl;
                        if (kSerializer != null) {
                            try {
                                objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, string);
                            } catch (SerializationException e) {
                                ArchComponentExtKt.logError(e);
                                objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<List<? extends String>>() { // from class: com.lagradost.cloudstream3.ui.subtitles.SubtitlesFragment$Companion$getDownloadSubsLanguageTagIETF$$inlined$getKey$1
                                });
                            } catch (Throwable unused) {
                                objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<List<? extends String>>() { // from class: com.lagradost.cloudstream3.ui.subtitles.SubtitlesFragment$Companion$getDownloadSubsLanguageTagIETF$$inlined$getKey$1
                                });
                            }
                        } else {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<List<? extends String>>() { // from class: com.lagradost.cloudstream3.ui.subtitles.SubtitlesFragment$Companion$getDownloadSubsLanguageTagIETF$$inlined$getKey$1
                            });
                        }
                        obj = objDecodeFromString;
                    }
                } catch (Exception unused2) {
                }
            }
            List<String> list = (List) obj;
            return list == null ? CollectionsKt.listOf("en") : list;
        }

        public final String getAutoSelectLanguageTagIETF() {
            Object objM8936constructorimpl;
            Object objDecodeFromString;
            Context context = CloudStreamApp.INSTANCE.getContext();
            Object obj = null;
            if (context != null) {
                try {
                    String string = DataStore.INSTANCE.getSharedPrefs(context).getString(SubtitlesFragmentKt.SUBTITLE_AUTO_SELECT_KEY, null);
                    if (string != null) {
                        AppUtils appUtils = AppUtils.INSTANCE;
                        try {
                            Result.Companion companion = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(StringSerializer.INSTANCE);
                        } catch (Throwable th) {
                            Result.Companion companion2 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                        }
                        if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) != null) {
                            try {
                                Result.Companion companion3 = Result.INSTANCE;
                                objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(String.class), null, 2, null));
                            } catch (Throwable th2) {
                                Result.Companion companion4 = Result.INSTANCE;
                                objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th2));
                            }
                        }
                        if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                            objM8936constructorimpl = null;
                        }
                        KSerializer kSerializer = (KSerializer) objM8936constructorimpl;
                        if (kSerializer != null) {
                            try {
                                objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, string);
                            } catch (SerializationException e) {
                                ArchComponentExtKt.logError(e);
                                objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<String>() { // from class: com.lagradost.cloudstream3.ui.subtitles.SubtitlesFragment$Companion$getAutoSelectLanguageTagIETF$$inlined$getKey$1
                                });
                            } catch (Throwable unused) {
                                objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<String>() { // from class: com.lagradost.cloudstream3.ui.subtitles.SubtitlesFragment$Companion$getAutoSelectLanguageTagIETF$$inlined$getKey$1
                                });
                            }
                        } else {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<String>() { // from class: com.lagradost.cloudstream3.ui.subtitles.SubtitlesFragment$Companion$getAutoSelectLanguageTagIETF$$inlined$getKey$1
                            });
                        }
                        obj = objDecodeFromString;
                    }
                } catch (Exception unused2) {
                }
            }
            String str = (String) obj;
            return str == null ? "en" : str;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onColorSelected(Pair<Integer, Integer> stuff) {
        FragmentActivity activity;
        Context context = getContext();
        if (context != null) {
            setColor(context, stuff.getFirst().intValue(), stuff.getSecond());
        }
        if (!this.hide || (activity = getActivity()) == null) {
            return;
        }
        UIHelper.INSTANCE.hideSystemUI(activity);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onDialogDismissed(int id) {
        FragmentActivity activity;
        if (!this.hide || (activity = getActivity()) == null) {
            return;
        }
        UIHelper.INSTANCE.hideSystemUI(activity);
    }

    private final void setColor(Context context, int i, Integer num) {
        int defColor;
        if (num == null) {
            defColor = INSTANCE.getDefColor(i);
        } else {
            defColor = num.intValue();
        }
        SaveCaptionStyle saveCaptionStyle = null;
        if (i == 0) {
            SaveCaptionStyle saveCaptionStyle2 = this.state;
            if (saveCaptionStyle2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("state");
            } else {
                saveCaptionStyle = saveCaptionStyle2;
            }
            saveCaptionStyle.setForegroundColor(defColor);
        } else if (i == 1) {
            SaveCaptionStyle saveCaptionStyle3 = this.state;
            if (saveCaptionStyle3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("state");
            } else {
                saveCaptionStyle = saveCaptionStyle3;
            }
            saveCaptionStyle.setEdgeColor(defColor);
        } else if (i == 2) {
            SaveCaptionStyle saveCaptionStyle4 = this.state;
            if (saveCaptionStyle4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("state");
            } else {
                saveCaptionStyle = saveCaptionStyle4;
            }
            saveCaptionStyle.setBackgroundColor(defColor);
        } else if (i == 3) {
            SaveCaptionStyle saveCaptionStyle5 = this.state;
            if (saveCaptionStyle5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("state");
            } else {
                saveCaptionStyle = saveCaptionStyle5;
            }
            saveCaptionStyle.setWindowColor(defColor);
        }
        updateState(context);
    }

    private final void updateState(Context context) {
        SubtitleView subtitleView;
        String string = context.getString(R.string.subtitles_example_text);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        SaveCaptionStyle saveCaptionStyle = this.state;
        SaveCaptionStyle saveCaptionStyle2 = null;
        if (saveCaptionStyle == null) {
            Intrinsics.throwUninitializedPropertyAccessException("state");
            saveCaptionStyle = null;
        }
        if (saveCaptionStyle.getUpperCase()) {
            string = string.toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(string, "toUpperCase(...)");
        }
        SpannableString spannableStringValueOf = SpannableString.valueOf(string);
        Companion companion = INSTANCE;
        SubtitleSettingsBinding binding = getBinding();
        SubtitleView subtitleView2 = binding != null ? binding.subtitleText : null;
        SaveCaptionStyle saveCaptionStyle3 = this.state;
        if (saveCaptionStyle3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("state");
            saveCaptionStyle3 = null;
        }
        companion.setSubtitleViewStyle(subtitleView2, saveCaptionStyle3, false);
        SubtitleSettingsBinding binding2 = getBinding();
        if (binding2 == null || (subtitleView = binding2.subtitleText) == null) {
            return;
        }
        Cue.Builder text = new Cue.Builder().setText(spannableStringValueOf);
        Intrinsics.checkNotNullExpressionValue(text, "setText(...)");
        SaveCaptionStyle saveCaptionStyle4 = this.state;
        if (saveCaptionStyle4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("state");
        } else {
            saveCaptionStyle2 = saveCaptionStyle4;
        }
        subtitleView.setCues(CollectionsKt.listOf(companion.applyStyle(text, saveCaptionStyle2).build()));
    }

    private final int getColor(int id) {
        int foregroundColor;
        SaveCaptionStyle saveCaptionStyle = null;
        if (id == 0) {
            SaveCaptionStyle saveCaptionStyle2 = this.state;
            if (saveCaptionStyle2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("state");
            } else {
                saveCaptionStyle = saveCaptionStyle2;
            }
            foregroundColor = saveCaptionStyle.getForegroundColor();
        } else if (id == 1) {
            SaveCaptionStyle saveCaptionStyle3 = this.state;
            if (saveCaptionStyle3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("state");
            } else {
                saveCaptionStyle = saveCaptionStyle3;
            }
            foregroundColor = saveCaptionStyle.getEdgeColor();
        } else if (id == 2) {
            SaveCaptionStyle saveCaptionStyle4 = this.state;
            if (saveCaptionStyle4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("state");
            } else {
                saveCaptionStyle = saveCaptionStyle4;
            }
            foregroundColor = saveCaptionStyle.getBackgroundColor();
        } else if (id != 3) {
            foregroundColor = 0;
        } else {
            SaveCaptionStyle saveCaptionStyle5 = this.state;
            if (saveCaptionStyle5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("state");
            } else {
                saveCaptionStyle = saveCaptionStyle5;
            }
            foregroundColor = saveCaptionStyle.getWindowColor();
        }
        if (foregroundColor == 0) {
            return -16777216;
        }
        return foregroundColor;
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.subtitles.SubtitlesFragment$onDestroy$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SubtitlesFragment.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class C09181 extends FunctionReferenceImpl implements Function1<Pair<? extends Integer, ? extends Integer>, Unit> {
        C09181(Object obj) {
            super(1, obj, SubtitlesFragment.class, "onColorSelected", "onColorSelected(Lkotlin/Pair;)V", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Pair<? extends Integer, ? extends Integer> pair) {
            invoke2((Pair<Integer, Integer>) pair);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(Pair<Integer, Integer> p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            ((SubtitlesFragment) this.receiver).onColorSelected(p0);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        CommonActivity.INSTANCE.getOnColorSelectedEvent().minusAssign(new C09181(this));
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        Window window;
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog == null || (window = dialog.getWindow()) == null) {
            return;
        }
        window.setWindowAnimations(R.style.DialogFullscreenPlayer);
    }

    @Override // androidx.fragment.app.DialogFragment
    public int getTheme() {
        return R.style.DialogFullscreenPlayer;
    }

    public final boolean getSystemBarsAddPadding() {
        return this.systemBarsAddPadding;
    }

    public final void setSystemBarsAddPadding(boolean z) {
        this.systemBarsAddPadding = z;
    }

    @Override // com.lagradost.cloudstream3.ui.BaseFragmentHelper
    public void fixLayout(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        UIHelper.fixSystemBarsPadding$default(UIHelper.INSTANCE, view, null, null, false, this.systemBarsAddPadding || Globals.INSTANCE.isLandscape(), this.systemBarsAddPadding, false, false, false, 462, null);
    }

    @Override // com.lagradost.cloudstream3.ui.BaseDialogFragment, com.lagradost.cloudstream3.ui.BaseFragmentHelper
    public void onBindingCreated(SubtitleSettingsBinding binding) {
        File externalFilesDir;
        Intrinsics.checkNotNullParameter(binding, "binding");
        Bundle arguments = getArguments();
        this.hide = arguments != null ? arguments.getBoolean("hide") : true;
        Bundle arguments2 = getArguments();
        final boolean z = arguments2 != null ? arguments2.getBoolean("popFragment") : false;
        CommonActivity.INSTANCE.getOnColorSelectedEvent().plusAssign(new C09171(this));
        CommonActivity.INSTANCE.getOnDialogDismissedEvent().plusAssign(new AnonymousClass2(this));
        TextView textView = binding.subsImportText;
        String string = getString(R.string.subs_import_text);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        StringBuilder sb = new StringBuilder();
        Context context = getContext();
        SaveCaptionStyle saveCaptionStyle = null;
        sb.append((context == null || (externalFilesDir = context.getExternalFilesDir(null)) == null) ? null : externalFilesDir.getAbsolutePath());
        sb.append("/Fonts");
        String str = String.format(string, Arrays.copyOf(new Object[]{sb.toString()}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        textView.setText(str);
        this.state = INSTANCE.getCurrentSavedStyle();
        Context context2 = getContext();
        if (context2 != null) {
            updateState(context2);
        }
        boolean zIsLayout = Globals.INSTANCE.isLayout(2);
        TextView subsTextColor = binding.subsTextColor;
        Intrinsics.checkNotNullExpressionValue(subsTextColor, "subsTextColor");
        onBindingCreated$setup(subsTextColor, zIsLayout, this, 0);
        TextView subsOutlineColor = binding.subsOutlineColor;
        Intrinsics.checkNotNullExpressionValue(subsOutlineColor, "subsOutlineColor");
        onBindingCreated$setup(subsOutlineColor, zIsLayout, this, 1);
        TextView subsBackgroundColor = binding.subsBackgroundColor;
        Intrinsics.checkNotNullExpressionValue(subsBackgroundColor, "subsBackgroundColor");
        onBindingCreated$setup(subsBackgroundColor, zIsLayout, this, 2);
        TextView subsWindowColor = binding.subsWindowColor;
        Intrinsics.checkNotNullExpressionValue(subsWindowColor, "subsWindowColor");
        onBindingCreated$setup(subsWindowColor, zIsLayout, this, 3);
        final Function0 function0 = new Function0() { // from class: com.lagradost.cloudstream3.ui.subtitles.SubtitlesFragment$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SubtitlesFragment.onBindingCreated$lambda$2$0(this.f$0);
            }
        };
        TextView subsSubtitleElevation = binding.subsSubtitleElevation;
        Intrinsics.checkNotNullExpressionValue(subsSubtitleElevation, "subsSubtitleElevation");
        subsSubtitleElevation.setFocusableInTouchMode(zIsLayout);
        binding.subsSubtitleElevation.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.subtitles.SubtitlesFragment$$ExternalSyntheticLambda13
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SubtitlesFragment.onBindingCreated$lambda$2$1(this.f$0, function0, view);
            }
        });
        binding.subsSubtitleElevation.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.lagradost.cloudstream3.ui.subtitles.SubtitlesFragment$$ExternalSyntheticLambda20
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                return SubtitlesFragment.onBindingCreated$lambda$2$2(this.f$0, view);
            }
        });
        TextView subsBackgroundRadius = binding.subsBackgroundRadius;
        Intrinsics.checkNotNullExpressionValue(subsBackgroundRadius, "subsBackgroundRadius");
        subsBackgroundRadius.setFocusableInTouchMode(zIsLayout);
        binding.subsBackgroundRadius.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.subtitles.SubtitlesFragment$$ExternalSyntheticLambda21
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SubtitlesFragment.onBindingCreated$lambda$2$3(this.f$0, function0, view);
            }
        });
        binding.subsBackgroundRadius.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.lagradost.cloudstream3.ui.subtitles.SubtitlesFragment$$ExternalSyntheticLambda23
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                return SubtitlesFragment.onBindingCreated$lambda$2$4(this.f$0, view);
            }
        });
        TextView subsSubtitleAlignment = binding.subsSubtitleAlignment;
        Intrinsics.checkNotNullExpressionValue(subsSubtitleAlignment, "subsSubtitleAlignment");
        subsSubtitleAlignment.setFocusableInTouchMode(zIsLayout);
        binding.subsSubtitleAlignment.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.subtitles.SubtitlesFragment$$ExternalSyntheticLambda24
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SubtitlesFragment.onBindingCreated$lambda$2$5(this.f$0, function0, view);
            }
        });
        TextView subsEdgeType = binding.subsEdgeType;
        Intrinsics.checkNotNullExpressionValue(subsEdgeType, "subsEdgeType");
        subsEdgeType.setFocusableInTouchMode(zIsLayout);
        binding.subsEdgeType.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.subtitles.SubtitlesFragment$$ExternalSyntheticLambda25
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SubtitlesFragment.onBindingCreated$lambda$2$6(this.f$0, function0, view);
            }
        });
        binding.subsEdgeType.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.lagradost.cloudstream3.ui.subtitles.SubtitlesFragment$$ExternalSyntheticLambda26
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                return SubtitlesFragment.onBindingCreated$lambda$2$7(this.f$0, view);
            }
        });
        TextView subsFontSize = binding.subsFontSize;
        Intrinsics.checkNotNullExpressionValue(subsFontSize, "subsFontSize");
        subsFontSize.setFocusableInTouchMode(zIsLayout);
        binding.subsFontSize.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.subtitles.SubtitlesFragment$$ExternalSyntheticLambda27
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SubtitlesFragment.onBindingCreated$lambda$2$8(this.f$0, function0, view);
            }
        });
        TextView subsEdgeSize = binding.subsEdgeSize;
        Intrinsics.checkNotNullExpressionValue(subsEdgeSize, "subsEdgeSize");
        subsEdgeSize.setFocusableInTouchMode(zIsLayout);
        binding.subsEdgeSize.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.subtitles.SubtitlesFragment$$ExternalSyntheticLambda28
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SubtitlesFragment.onBindingCreated$lambda$2$9(this.f$0, function0, view);
            }
        });
        SwitchMaterial switchMaterial = binding.subtitlesRemoveBloat;
        SaveCaptionStyle saveCaptionStyle2 = this.state;
        if (saveCaptionStyle2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("state");
            saveCaptionStyle2 = null;
        }
        switchMaterial.setChecked(saveCaptionStyle2.getRemoveBloat());
        binding.subtitlesRemoveBloat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.lagradost.cloudstream3.ui.subtitles.SubtitlesFragment$$ExternalSyntheticLambda2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z2) {
                SubtitlesFragment.onBindingCreated$lambda$2$10(this.f$0, compoundButton, z2);
            }
        });
        SwitchMaterial switchMaterial2 = binding.subtitlesUppercase;
        SaveCaptionStyle saveCaptionStyle3 = this.state;
        if (saveCaptionStyle3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("state");
            saveCaptionStyle3 = null;
        }
        switchMaterial2.setChecked(saveCaptionStyle3.getUpperCase());
        binding.subtitlesUppercase.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.lagradost.cloudstream3.ui.subtitles.SubtitlesFragment$$ExternalSyntheticLambda3
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z2) {
                SubtitlesFragment.onBindingCreated$lambda$2$11(this.f$0, compoundButton, z2);
            }
        });
        SwitchMaterial switchMaterial3 = binding.subtitlesRemoveCaptions;
        SaveCaptionStyle saveCaptionStyle4 = this.state;
        if (saveCaptionStyle4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("state");
            saveCaptionStyle4 = null;
        }
        switchMaterial3.setChecked(saveCaptionStyle4.getRemoveCaptions());
        binding.subtitlesRemoveCaptions.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.lagradost.cloudstream3.ui.subtitles.SubtitlesFragment$$ExternalSyntheticLambda4
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z2) {
                SubtitlesFragment.onBindingCreated$lambda$2$12(this.f$0, compoundButton, z2);
            }
        });
        SwitchMaterial switchMaterial4 = binding.subtitlesBold;
        SaveCaptionStyle saveCaptionStyle5 = this.state;
        if (saveCaptionStyle5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("state");
            saveCaptionStyle5 = null;
        }
        switchMaterial4.setChecked(saveCaptionStyle5.getBold());
        binding.subtitlesBold.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.lagradost.cloudstream3.ui.subtitles.SubtitlesFragment$$ExternalSyntheticLambda5
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z2) {
                SubtitlesFragment.onBindingCreated$lambda$2$13(this.f$0, compoundButton, z2);
            }
        });
        SwitchMaterial switchMaterial5 = binding.subtitlesItalic;
        SaveCaptionStyle saveCaptionStyle6 = this.state;
        if (saveCaptionStyle6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("state");
        } else {
            saveCaptionStyle = saveCaptionStyle6;
        }
        switchMaterial5.setChecked(saveCaptionStyle.getItalic());
        binding.subtitlesItalic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.lagradost.cloudstream3.ui.subtitles.SubtitlesFragment$$ExternalSyntheticLambda6
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z2) {
                SubtitlesFragment.onBindingCreated$lambda$2$14(this.f$0, compoundButton, z2);
            }
        });
        binding.subsFontSize.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.lagradost.cloudstream3.ui.subtitles.SubtitlesFragment$$ExternalSyntheticLambda7
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                return SubtitlesFragment.onBindingCreated$lambda$2$15(this.f$0, view);
            }
        });
        binding.subsEdgeSize.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.lagradost.cloudstream3.ui.subtitles.SubtitlesFragment$$ExternalSyntheticLambda8
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                return SubtitlesFragment.onBindingCreated$lambda$2$16(this.f$0, view);
            }
        });
        Context context3 = getContext();
        if (context3 != null) {
            binding.subtitlesFilterSubLang.setChecked(PreferenceManager.getDefaultSharedPreferences(context3).getBoolean(getString(R.string.filter_sub_lang_key), false));
        }
        binding.subtitlesFilterSubLang.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.lagradost.cloudstream3.ui.subtitles.SubtitlesFragment$$ExternalSyntheticLambda9
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z2) {
                SubtitlesFragment.onBindingCreated$lambda$2$18(this.f$0, compoundButton, z2);
            }
        });
        TextView subsFont = binding.subsFont;
        Intrinsics.checkNotNullExpressionValue(subsFont, "subsFont");
        subsFont.setFocusableInTouchMode(zIsLayout);
        binding.subsFont.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.subtitles.SubtitlesFragment$$ExternalSyntheticLambda10
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SubtitlesFragment.onBindingCreated$lambda$2$19(this.f$0, function0, view);
            }
        });
        binding.subsFont.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.lagradost.cloudstream3.ui.subtitles.SubtitlesFragment$$ExternalSyntheticLambda12
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                return SubtitlesFragment.onBindingCreated$lambda$2$20(this.f$0, view);
            }
        });
        TextView subsAutoSelectLanguage = binding.subsAutoSelectLanguage;
        Intrinsics.checkNotNullExpressionValue(subsAutoSelectLanguage, "subsAutoSelectLanguage");
        subsAutoSelectLanguage.setFocusableInTouchMode(zIsLayout);
        binding.subsAutoSelectLanguage.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.subtitles.SubtitlesFragment$$ExternalSyntheticLambda14
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SubtitlesFragment.onBindingCreated$lambda$2$21(this.f$0, function0, view);
            }
        });
        binding.subsAutoSelectLanguage.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.lagradost.cloudstream3.ui.subtitles.SubtitlesFragment$$ExternalSyntheticLambda15
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                return SubtitlesFragment.onBindingCreated$lambda$2$22(this.f$0, view);
            }
        });
        TextView subsDownloadLanguages = binding.subsDownloadLanguages;
        Intrinsics.checkNotNullExpressionValue(subsDownloadLanguages, "subsDownloadLanguages");
        subsDownloadLanguages.setFocusableInTouchMode(zIsLayout);
        binding.subsDownloadLanguages.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.subtitles.SubtitlesFragment$$ExternalSyntheticLambda16
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SubtitlesFragment.onBindingCreated$lambda$2$23(this.f$0, function0, view);
            }
        });
        binding.subsDownloadLanguages.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.lagradost.cloudstream3.ui.subtitles.SubtitlesFragment$$ExternalSyntheticLambda17
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                return SubtitlesFragment.onBindingCreated$lambda$2$24(this.f$0, view);
            }
        });
        binding.cancelBtt.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.subtitles.SubtitlesFragment$$ExternalSyntheticLambda18
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SubtitlesFragment.onBindingCreated$lambda$2$25(z, this, view);
            }
        });
        binding.applyBtt.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.subtitles.SubtitlesFragment$$ExternalSyntheticLambda19
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SubtitlesFragment.onBindingCreated$lambda$2$26(this.f$0, z, view);
            }
        });
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.subtitles.SubtitlesFragment$onBindingCreated$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SubtitlesFragment.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class C09171 extends FunctionReferenceImpl implements Function1<Pair<? extends Integer, ? extends Integer>, Unit> {
        C09171(Object obj) {
            super(1, obj, SubtitlesFragment.class, "onColorSelected", "onColorSelected(Lkotlin/Pair;)V", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Pair<? extends Integer, ? extends Integer> pair) {
            invoke2((Pair<Integer, Integer>) pair);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(Pair<Integer, Integer> p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            ((SubtitlesFragment) this.receiver).onColorSelected(p0);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.subtitles.SubtitlesFragment$onBindingCreated$2, reason: invalid class name */
    /* JADX INFO: compiled from: SubtitlesFragment.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass2 extends FunctionReferenceImpl implements Function1<Integer, Unit> {
        AnonymousClass2(Object obj) {
            super(1, obj, SubtitlesFragment.class, "onDialogDismissed", "onDialogDismissed(I)V", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
            invoke(num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(int i) {
            ((SubtitlesFragment) this.receiver).onDialogDismissed(i);
        }
    }

    private static final void onBindingCreated$setup(View view, boolean z, final SubtitlesFragment subtitlesFragment, final int i) {
        view.setFocusableInTouchMode(z);
        view.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.subtitles.SubtitlesFragment$$ExternalSyntheticLambda11
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                SubtitlesFragment.onBindingCreated$setup$lambda$0(this.f$0, i, view2);
            }
        });
        view.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.lagradost.cloudstream3.ui.subtitles.SubtitlesFragment$$ExternalSyntheticLambda22
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view2) {
                return SubtitlesFragment.onBindingCreated$setup$lambda$1(this.f$0, i, view2);
            }
        });
    }

    static final void onBindingCreated$setup$lambda$0(SubtitlesFragment subtitlesFragment, int i, View view) {
        FragmentActivity activity = subtitlesFragment.getActivity();
        if (activity != null) {
            ColorPickerDialog.newBuilder().setDialogId(i).setShowAlphaSlider(true).setColor(subtitlesFragment.getColor(i)).show(activity);
        }
    }

    static final boolean onBindingCreated$setup$lambda$1(SubtitlesFragment subtitlesFragment, int i, View view) {
        Context context = view.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        subtitlesFragment.setColor(context, i, null);
        CommonActivity.INSTANCE.showToast(R.string.subs_default_reset_toast, (Integer) 0);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindingCreated$lambda$2$0(SubtitlesFragment subtitlesFragment) {
        FragmentActivity activity;
        if (subtitlesFragment.hide && (activity = subtitlesFragment.getActivity()) != null) {
            UIHelper.INSTANCE.hideSystemUI(activity);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$2$1(final SubtitlesFragment subtitlesFragment, Function0 function0, final View view) {
        List listListOf = CollectionsKt.listOf(TuplesKt.to(0, view.getContext().getString(R.string.none)));
        IntRange intRange = new IntRange(1, 40);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(intRange, 10));
        Iterator<Integer> it = intRange.iterator();
        while (it.hasNext()) {
            int iNextInt = ((IntIterator) it).nextInt() * 10;
            arrayList.add(TuplesKt.to(Integer.valueOf(iNextInt), iNextInt + "dp"));
        }
        final List listPlus = CollectionsKt.plus((Collection) listListOf, (Iterable) arrayList);
        FragmentActivity activity = subtitlesFragment.getActivity();
        if (activity != null) {
            SingleSelectionHelper singleSelectionHelper = SingleSelectionHelper.INSTANCE;
            FragmentActivity fragmentActivity = activity;
            List list = listPlus;
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            Iterator it2 = list.iterator();
            while (it2.hasNext()) {
                arrayList2.add((String) ((Pair) it2.next()).getSecond());
            }
            ArrayList arrayList3 = arrayList2;
            ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            Iterator it3 = list.iterator();
            while (it3.hasNext()) {
                arrayList4.add(Integer.valueOf(((Number) ((Pair) it3.next()).getFirst()).intValue()));
            }
            ArrayList arrayList5 = arrayList4;
            SaveCaptionStyle saveCaptionStyle = subtitlesFragment.state;
            if (saveCaptionStyle == null) {
                Intrinsics.throwUninitializedPropertyAccessException("state");
                saveCaptionStyle = null;
            }
            int iIndexOf = arrayList5.indexOf(Integer.valueOf(saveCaptionStyle.getElevation()));
            Intrinsics.checkNotNull(view, "null cannot be cast to non-null type android.widget.TextView");
            singleSelectionHelper.showDialog(fragmentActivity, arrayList3, iIndexOf, ((TextView) view).getText().toString(), false, function0, new Function1() { // from class: com.lagradost.cloudstream3.ui.subtitles.SubtitlesFragment$$ExternalSyntheticLambda32
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return SubtitlesFragment.onBindingCreated$lambda$2$1$3(this.f$0, listPlus, view, ((Integer) obj).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindingCreated$lambda$2$1$3(SubtitlesFragment subtitlesFragment, List list, View view, int i) {
        FragmentActivity activity;
        SaveCaptionStyle saveCaptionStyle = subtitlesFragment.state;
        if (saveCaptionStyle == null) {
            Intrinsics.throwUninitializedPropertyAccessException("state");
            saveCaptionStyle = null;
        }
        List list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        Iterator it = list2.iterator();
        while (it.hasNext()) {
            arrayList.add(Integer.valueOf(((Number) ((Pair) it.next()).getFirst()).intValue()));
        }
        saveCaptionStyle.setElevation(((Number) arrayList.get(i)).intValue());
        Context context = ((TextView) view).getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        subtitlesFragment.updateState(context);
        if (subtitlesFragment.hide && (activity = subtitlesFragment.getActivity()) != null) {
            UIHelper.INSTANCE.hideSystemUI(activity);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onBindingCreated$lambda$2$2(SubtitlesFragment subtitlesFragment, View view) {
        SaveCaptionStyle saveCaptionStyle = subtitlesFragment.state;
        if (saveCaptionStyle == null) {
            Intrinsics.throwUninitializedPropertyAccessException("state");
            saveCaptionStyle = null;
        }
        saveCaptionStyle.setElevation(20);
        Context context = view.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        subtitlesFragment.updateState(context);
        CommonActivity.INSTANCE.showToast(R.string.subs_default_reset_toast, (Integer) 0);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$2$3(final SubtitlesFragment subtitlesFragment, Function0 function0, final View view) {
        List listListOf = CollectionsKt.listOf(TuplesKt.to(null, view.getContext().getString(R.string.none)));
        IntRange intRange = new IntRange(1, 10);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(intRange, 10));
        Iterator<Integer> it = intRange.iterator();
        while (it.hasNext()) {
            int iNextInt = ((IntIterator) it).nextInt() * 5;
            arrayList.add(TuplesKt.to(Integer.valueOf(iNextInt), iNextInt + "px"));
        }
        final List listPlus = CollectionsKt.plus((Collection) listListOf, (Iterable) arrayList);
        FragmentActivity activity = subtitlesFragment.getActivity();
        if (activity != null) {
            SingleSelectionHelper singleSelectionHelper = SingleSelectionHelper.INSTANCE;
            FragmentActivity fragmentActivity = activity;
            List list = listPlus;
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            Iterator it2 = list.iterator();
            while (it2.hasNext()) {
                arrayList2.add((String) ((Pair) it2.next()).getSecond());
            }
            ArrayList arrayList3 = arrayList2;
            ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            Iterator it3 = list.iterator();
            while (it3.hasNext()) {
                arrayList4.add((Integer) ((Pair) it3.next()).getFirst());
            }
            ArrayList arrayList5 = arrayList4;
            SaveCaptionStyle saveCaptionStyle = subtitlesFragment.state;
            if (saveCaptionStyle == null) {
                Intrinsics.throwUninitializedPropertyAccessException("state");
                saveCaptionStyle = null;
            }
            Float backgroundRadius = saveCaptionStyle.getBackgroundRadius();
            int iIndexOf = arrayList5.indexOf(backgroundRadius != null ? Integer.valueOf((int) backgroundRadius.floatValue()) : null);
            Intrinsics.checkNotNull(view, "null cannot be cast to non-null type android.widget.TextView");
            singleSelectionHelper.showDialog(fragmentActivity, arrayList3, iIndexOf, ((TextView) view).getText().toString(), false, function0, new Function1() { // from class: com.lagradost.cloudstream3.ui.subtitles.SubtitlesFragment$$ExternalSyntheticLambda33
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return SubtitlesFragment.onBindingCreated$lambda$2$3$3(this.f$0, listPlus, view, ((Integer) obj).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindingCreated$lambda$2$3$3(SubtitlesFragment subtitlesFragment, List list, View view, int i) {
        SaveCaptionStyle saveCaptionStyle = subtitlesFragment.state;
        if (saveCaptionStyle == null) {
            Intrinsics.throwUninitializedPropertyAccessException("state");
            saveCaptionStyle = null;
        }
        List list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        Iterator it = list2.iterator();
        while (it.hasNext()) {
            arrayList.add((Integer) ((Pair) it.next()).getFirst());
        }
        Integer num = (Integer) arrayList.get(i);
        saveCaptionStyle.setBackgroundRadius(num != null ? Float.valueOf(num.intValue()) : null);
        Context context = ((TextView) view).getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        subtitlesFragment.updateState(context);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onBindingCreated$lambda$2$4(SubtitlesFragment subtitlesFragment, View view) {
        SaveCaptionStyle saveCaptionStyle = subtitlesFragment.state;
        if (saveCaptionStyle == null) {
            Intrinsics.throwUninitializedPropertyAccessException("state");
            saveCaptionStyle = null;
        }
        saveCaptionStyle.setBackgroundRadius(null);
        Context context = view.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        subtitlesFragment.updateState(context);
        CommonActivity.INSTANCE.showToast(R.string.subs_default_reset_toast, (Integer) 0);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$2$5(final SubtitlesFragment subtitlesFragment, Function0 function0, final View view) {
        SaveCaptionStyle saveCaptionStyle = null;
        final List listListOf = CollectionsKt.listOf((Object[]) new Pair[]{TuplesKt.to(null, Integer.valueOf(R.string.automatic)), TuplesKt.to(1, Integer.valueOf(R.string.bottom_left)), TuplesKt.to(2, Integer.valueOf(R.string.bottom_center)), TuplesKt.to(3, Integer.valueOf(R.string.bottom_right)), TuplesKt.to(4, Integer.valueOf(R.string.middle_left)), TuplesKt.to(5, Integer.valueOf(R.string.middle_center)), TuplesKt.to(6, Integer.valueOf(R.string.middle_right)), TuplesKt.to(7, Integer.valueOf(R.string.top_left)), TuplesKt.to(8, Integer.valueOf(R.string.top_center)), TuplesKt.to(9, Integer.valueOf(R.string.top_right))});
        FragmentActivity activity = subtitlesFragment.getActivity();
        if (activity != null) {
            SingleSelectionHelper singleSelectionHelper = SingleSelectionHelper.INSTANCE;
            FragmentActivity fragmentActivity = activity;
            List list = listListOf;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            Iterator it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(view.getContext().getString(((Number) ((Pair) it.next()).getSecond()).intValue()));
            }
            ArrayList arrayList2 = arrayList;
            ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            Iterator it2 = list.iterator();
            while (it2.hasNext()) {
                arrayList3.add((Integer) ((Pair) it2.next()).getFirst());
            }
            ArrayList arrayList4 = arrayList3;
            SaveCaptionStyle saveCaptionStyle2 = subtitlesFragment.state;
            if (saveCaptionStyle2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("state");
            } else {
                saveCaptionStyle = saveCaptionStyle2;
            }
            int iIndexOf = arrayList4.indexOf(saveCaptionStyle.getAlignment());
            Intrinsics.checkNotNull(view, "null cannot be cast to non-null type android.widget.TextView");
            singleSelectionHelper.showDialog(fragmentActivity, arrayList2, iIndexOf, ((TextView) view).getText().toString(), false, function0, new Function1() { // from class: com.lagradost.cloudstream3.ui.subtitles.SubtitlesFragment$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return SubtitlesFragment.onBindingCreated$lambda$2$5$2(this.f$0, listListOf, view, ((Integer) obj).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindingCreated$lambda$2$5$2(SubtitlesFragment subtitlesFragment, List list, View view, int i) {
        SaveCaptionStyle saveCaptionStyle = subtitlesFragment.state;
        if (saveCaptionStyle == null) {
            Intrinsics.throwUninitializedPropertyAccessException("state");
            saveCaptionStyle = null;
        }
        List list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        Iterator it = list2.iterator();
        while (it.hasNext()) {
            arrayList.add((Integer) ((Pair) it.next()).getFirst());
        }
        saveCaptionStyle.setAlignment((Integer) arrayList.get(i));
        Context context = ((TextView) view).getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        subtitlesFragment.updateState(context);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$2$6(final SubtitlesFragment subtitlesFragment, Function0 function0, final View view) {
        final List listListOf = CollectionsKt.listOf((Object[]) new Pair[]{TuplesKt.to(0, view.getContext().getString(R.string.subtitles_none)), TuplesKt.to(1, view.getContext().getString(R.string.subtitles_outline)), TuplesKt.to(4, view.getContext().getString(R.string.subtitles_depressed)), TuplesKt.to(2, view.getContext().getString(R.string.subtitles_shadow)), TuplesKt.to(3, view.getContext().getString(R.string.subtitles_raised))});
        FragmentActivity activity = subtitlesFragment.getActivity();
        if (activity != null) {
            SingleSelectionHelper singleSelectionHelper = SingleSelectionHelper.INSTANCE;
            FragmentActivity fragmentActivity = activity;
            List list = listListOf;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            Iterator it = list.iterator();
            while (it.hasNext()) {
                arrayList.add((String) ((Pair) it.next()).getSecond());
            }
            ArrayList arrayList2 = arrayList;
            ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            Iterator it2 = list.iterator();
            while (it2.hasNext()) {
                arrayList3.add(Integer.valueOf(((Number) ((Pair) it2.next()).getFirst()).intValue()));
            }
            ArrayList arrayList4 = arrayList3;
            SaveCaptionStyle saveCaptionStyle = subtitlesFragment.state;
            if (saveCaptionStyle == null) {
                Intrinsics.throwUninitializedPropertyAccessException("state");
                saveCaptionStyle = null;
            }
            int iIndexOf = arrayList4.indexOf(Integer.valueOf(saveCaptionStyle.getEdgeType()));
            Intrinsics.checkNotNull(view, "null cannot be cast to non-null type android.widget.TextView");
            singleSelectionHelper.showDialog(fragmentActivity, arrayList2, iIndexOf, ((TextView) view).getText().toString(), false, function0, new Function1() { // from class: com.lagradost.cloudstream3.ui.subtitles.SubtitlesFragment$$ExternalSyntheticLambda30
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return SubtitlesFragment.onBindingCreated$lambda$2$6$2(this.f$0, listListOf, view, ((Integer) obj).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindingCreated$lambda$2$6$2(SubtitlesFragment subtitlesFragment, List list, View view, int i) {
        SaveCaptionStyle saveCaptionStyle = subtitlesFragment.state;
        if (saveCaptionStyle == null) {
            Intrinsics.throwUninitializedPropertyAccessException("state");
            saveCaptionStyle = null;
        }
        List list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        Iterator it = list2.iterator();
        while (it.hasNext()) {
            arrayList.add(Integer.valueOf(((Number) ((Pair) it.next()).getFirst()).intValue()));
        }
        saveCaptionStyle.setEdgeType(((Number) arrayList.get(i)).intValue());
        Context context = ((TextView) view).getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        subtitlesFragment.updateState(context);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onBindingCreated$lambda$2$7(SubtitlesFragment subtitlesFragment, View view) {
        SaveCaptionStyle saveCaptionStyle = subtitlesFragment.state;
        if (saveCaptionStyle == null) {
            Intrinsics.throwUninitializedPropertyAccessException("state");
            saveCaptionStyle = null;
        }
        saveCaptionStyle.setEdgeType(1);
        Context context = view.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        subtitlesFragment.updateState(context);
        CommonActivity.INSTANCE.showToast(R.string.subs_default_reset_toast, (Integer) 0);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$2$8(final SubtitlesFragment subtitlesFragment, Function0 function0, final View view) {
        SaveCaptionStyle saveCaptionStyle = null;
        List listListOf = CollectionsKt.listOf(TuplesKt.to(null, view.getContext().getString(R.string.normal)));
        IntRange intRange = new IntRange(6, 60);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(intRange, 10));
        Iterator<Integer> it = intRange.iterator();
        while (it.hasNext()) {
            int iNextInt = ((IntIterator) it).nextInt();
            arrayList.add(TuplesKt.to(Float.valueOf(iNextInt), iNextInt + "sp"));
        }
        final List listPlus = CollectionsKt.plus((Collection) listListOf, (Iterable) arrayList);
        FragmentActivity activity = subtitlesFragment.getActivity();
        if (activity != null) {
            SingleSelectionHelper singleSelectionHelper = SingleSelectionHelper.INSTANCE;
            FragmentActivity fragmentActivity = activity;
            List list = listPlus;
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            Iterator it2 = list.iterator();
            while (it2.hasNext()) {
                arrayList2.add((String) ((Pair) it2.next()).getSecond());
            }
            ArrayList arrayList3 = arrayList2;
            ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            Iterator it3 = list.iterator();
            while (it3.hasNext()) {
                arrayList4.add((Float) ((Pair) it3.next()).getFirst());
            }
            ArrayList arrayList5 = arrayList4;
            SaveCaptionStyle saveCaptionStyle2 = subtitlesFragment.state;
            if (saveCaptionStyle2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("state");
            } else {
                saveCaptionStyle = saveCaptionStyle2;
            }
            int iIndexOf = arrayList5.indexOf(saveCaptionStyle.getFixedTextSize());
            Intrinsics.checkNotNull(view, "null cannot be cast to non-null type android.widget.TextView");
            singleSelectionHelper.showDialog(fragmentActivity, arrayList3, iIndexOf, ((TextView) view).getText().toString(), false, function0, new Function1() { // from class: com.lagradost.cloudstream3.ui.subtitles.SubtitlesFragment$$ExternalSyntheticLambda36
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return SubtitlesFragment.onBindingCreated$lambda$2$8$3(this.f$0, listPlus, view, ((Integer) obj).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindingCreated$lambda$2$8$3(SubtitlesFragment subtitlesFragment, List list, View view, int i) {
        SaveCaptionStyle saveCaptionStyle = subtitlesFragment.state;
        if (saveCaptionStyle == null) {
            Intrinsics.throwUninitializedPropertyAccessException("state");
            saveCaptionStyle = null;
        }
        List list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        Iterator it = list2.iterator();
        while (it.hasNext()) {
            arrayList.add((Float) ((Pair) it.next()).getFirst());
        }
        saveCaptionStyle.setFixedTextSize((Float) arrayList.get(i));
        Context context = ((TextView) view).getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        subtitlesFragment.updateState(context);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$2$9(final SubtitlesFragment subtitlesFragment, Function0 function0, final View view) {
        SaveCaptionStyle saveCaptionStyle = null;
        List listListOf = CollectionsKt.listOf(TuplesKt.to(null, view.getContext().getString(R.string.normal)));
        IntRange intRange = new IntRange(1, 60);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(intRange, 10));
        Iterator<Integer> it = intRange.iterator();
        while (it.hasNext()) {
            int iNextInt = ((IntIterator) it).nextInt();
            arrayList.add(TuplesKt.to(Float.valueOf(iNextInt), iNextInt + "px"));
        }
        final List listPlus = CollectionsKt.plus((Collection) listListOf, (Iterable) arrayList);
        FragmentActivity activity = subtitlesFragment.getActivity();
        if (activity != null) {
            SingleSelectionHelper singleSelectionHelper = SingleSelectionHelper.INSTANCE;
            FragmentActivity fragmentActivity = activity;
            List list = listPlus;
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            Iterator it2 = list.iterator();
            while (it2.hasNext()) {
                arrayList2.add((String) ((Pair) it2.next()).getSecond());
            }
            ArrayList arrayList3 = arrayList2;
            ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            Iterator it3 = list.iterator();
            while (it3.hasNext()) {
                arrayList4.add((Float) ((Pair) it3.next()).getFirst());
            }
            ArrayList arrayList5 = arrayList4;
            SaveCaptionStyle saveCaptionStyle2 = subtitlesFragment.state;
            if (saveCaptionStyle2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("state");
            } else {
                saveCaptionStyle = saveCaptionStyle2;
            }
            int iIndexOf = arrayList5.indexOf(saveCaptionStyle.getEdgeSize());
            Intrinsics.checkNotNull(view, "null cannot be cast to non-null type android.widget.TextView");
            singleSelectionHelper.showDialog(fragmentActivity, arrayList3, iIndexOf, ((TextView) view).getText().toString(), false, function0, new Function1() { // from class: com.lagradost.cloudstream3.ui.subtitles.SubtitlesFragment$$ExternalSyntheticLambda34
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return SubtitlesFragment.onBindingCreated$lambda$2$9$3(this.f$0, listPlus, view, ((Integer) obj).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindingCreated$lambda$2$9$3(SubtitlesFragment subtitlesFragment, List list, View view, int i) {
        SaveCaptionStyle saveCaptionStyle = subtitlesFragment.state;
        if (saveCaptionStyle == null) {
            Intrinsics.throwUninitializedPropertyAccessException("state");
            saveCaptionStyle = null;
        }
        List list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        Iterator it = list2.iterator();
        while (it.hasNext()) {
            arrayList.add((Float) ((Pair) it.next()).getFirst());
        }
        saveCaptionStyle.setEdgeSize((Float) arrayList.get(i));
        Context context = ((TextView) view).getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        subtitlesFragment.updateState(context);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$2$10(SubtitlesFragment subtitlesFragment, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(compoundButton, "<unused var>");
        SaveCaptionStyle saveCaptionStyle = subtitlesFragment.state;
        if (saveCaptionStyle == null) {
            Intrinsics.throwUninitializedPropertyAccessException("state");
            saveCaptionStyle = null;
        }
        saveCaptionStyle.setRemoveBloat(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$2$11(SubtitlesFragment subtitlesFragment, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(compoundButton, "<unused var>");
        SaveCaptionStyle saveCaptionStyle = subtitlesFragment.state;
        if (saveCaptionStyle == null) {
            Intrinsics.throwUninitializedPropertyAccessException("state");
            saveCaptionStyle = null;
        }
        saveCaptionStyle.setUpperCase(z);
        Context context = subtitlesFragment.getContext();
        if (context != null) {
            subtitlesFragment.updateState(context);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$2$12(SubtitlesFragment subtitlesFragment, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(compoundButton, "<unused var>");
        SaveCaptionStyle saveCaptionStyle = subtitlesFragment.state;
        if (saveCaptionStyle == null) {
            Intrinsics.throwUninitializedPropertyAccessException("state");
            saveCaptionStyle = null;
        }
        saveCaptionStyle.setRemoveCaptions(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$2$13(SubtitlesFragment subtitlesFragment, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(compoundButton, "<unused var>");
        SaveCaptionStyle saveCaptionStyle = subtitlesFragment.state;
        if (saveCaptionStyle == null) {
            Intrinsics.throwUninitializedPropertyAccessException("state");
            saveCaptionStyle = null;
        }
        saveCaptionStyle.setBold(z);
        Context context = subtitlesFragment.getContext();
        if (context != null) {
            subtitlesFragment.updateState(context);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$2$14(SubtitlesFragment subtitlesFragment, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(compoundButton, "<unused var>");
        SaveCaptionStyle saveCaptionStyle = subtitlesFragment.state;
        if (saveCaptionStyle == null) {
            Intrinsics.throwUninitializedPropertyAccessException("state");
            saveCaptionStyle = null;
        }
        saveCaptionStyle.setItalic(z);
        Context context = subtitlesFragment.getContext();
        if (context != null) {
            subtitlesFragment.updateState(context);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onBindingCreated$lambda$2$15(SubtitlesFragment subtitlesFragment, View view) {
        SaveCaptionStyle saveCaptionStyle = subtitlesFragment.state;
        if (saveCaptionStyle == null) {
            Intrinsics.throwUninitializedPropertyAccessException("state");
            saveCaptionStyle = null;
        }
        saveCaptionStyle.setFixedTextSize(null);
        Context context = subtitlesFragment.getContext();
        if (context != null) {
            subtitlesFragment.updateState(context);
        }
        CommonActivity.INSTANCE.showToast((Activity) subtitlesFragment.getActivity(), R.string.subs_default_reset_toast, (Integer) 0);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onBindingCreated$lambda$2$16(SubtitlesFragment subtitlesFragment, View view) {
        SaveCaptionStyle saveCaptionStyle = subtitlesFragment.state;
        if (saveCaptionStyle == null) {
            Intrinsics.throwUninitializedPropertyAccessException("state");
            saveCaptionStyle = null;
        }
        saveCaptionStyle.setEdgeSize(null);
        Context context = subtitlesFragment.getContext();
        if (context != null) {
            subtitlesFragment.updateState(context);
        }
        CommonActivity.INSTANCE.showToast((Activity) subtitlesFragment.getActivity(), R.string.subs_default_reset_toast, (Integer) 0);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$2$18(SubtitlesFragment subtitlesFragment, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(compoundButton, "<unused var>");
        Context context = subtitlesFragment.getContext();
        if (context != null) {
            SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            Intrinsics.checkNotNullExpressionValue(defaultSharedPreferences, "getDefaultSharedPreferences(...)");
            SharedPreferences.Editor editorEdit = defaultSharedPreferences.edit();
            editorEdit.putBoolean(subtitlesFragment.getString(R.string.filter_sub_lang_key), z);
            editorEdit.apply();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:28:0x016f  */
    /* JADX WARN: Code duplicated, block: B:31:0x018c A[LOOP:2: B:29:0x0186->B:31:0x018c, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:35:0x01b8 A[LOOP:3: B:33:0x01b2->B:35:0x01b8, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:44:? A[RETURN, SYNTHETIC] */
    public static final void onBindingCreated$lambda$2$19(final SubtitlesFragment subtitlesFragment, Function0 function0, final View view) {
        int size;
        int i;
        FragmentActivity activity;
        ArrayList arrayList;
        Iterator it;
        ArrayList arrayList2;
        Iterator it2;
        int i2 = 0;
        final List listListOf = CollectionsKt.listOf((Object[]) new Pair[]{TuplesKt.to(null, view.getContext().getString(R.string.normal)), TuplesKt.to(Integer.valueOf(R.font.trebuchet_ms), "Trebuchet MS"), TuplesKt.to(Integer.valueOf(R.font.netflix_sans), "Netflix Sans"), TuplesKt.to(Integer.valueOf(R.font.google_sans), "Google Sans"), TuplesKt.to(Integer.valueOf(R.font.open_sans), "Open Sans"), TuplesKt.to(Integer.valueOf(R.font.futura), "Futura"), TuplesKt.to(Integer.valueOf(R.font.consola), "Consola"), TuplesKt.to(Integer.valueOf(R.font.gotham), "Gotham"), TuplesKt.to(Integer.valueOf(R.font.lucida_grande), "Lucida Grande"), TuplesKt.to(Integer.valueOf(R.font.stix_general), "STIX General"), TuplesKt.to(Integer.valueOf(R.font.times_new_roman), "Times New Roman"), TuplesKt.to(Integer.valueOf(R.font.verdana), "Verdana"), TuplesKt.to(Integer.valueOf(R.font.ubuntu_regular), "Ubuntu"), TuplesKt.to(Integer.valueOf(R.font.comic_sans), "Comic Sans"), TuplesKt.to(Integer.valueOf(R.font.poppins_regular), "Poppins")});
        Companion companion = INSTANCE;
        Context context = view.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        final List savedFonts = companion.getSavedFonts(context);
        Iterator it3 = savedFonts.iterator();
        int i3 = 0;
        while (true) {
            size = -1;
            if (!it3.hasNext()) {
                i3 = -1;
                break;
            }
            String absolutePath = ((File) it3.next()).getAbsolutePath();
            SaveCaptionStyle saveCaptionStyle = subtitlesFragment.state;
            if (saveCaptionStyle == null) {
                Intrinsics.throwUninitializedPropertyAccessException("state");
                saveCaptionStyle = null;
            }
            if (Intrinsics.areEqual(absolutePath, saveCaptionStyle.getTypefaceFilePath())) {
                break;
            } else {
                i3++;
            }
        }
        if (i3 == -1) {
            Iterator it4 = listListOf.iterator();
            while (true) {
                if (it4.hasNext()) {
                    Object first = ((Pair) it4.next()).getFirst();
                    SaveCaptionStyle saveCaptionStyle2 = subtitlesFragment.state;
                    if (saveCaptionStyle2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("state");
                        saveCaptionStyle2 = null;
                    }
                    if (Intrinsics.areEqual(first, saveCaptionStyle2.getTypeface())) {
                        i = i2;
                        break;
                    }
                    i2++;
                }
            }
            activity = subtitlesFragment.getActivity();
            if (activity != null) {
                SingleSelectionHelper singleSelectionHelper = SingleSelectionHelper.INSTANCE;
                FragmentActivity fragmentActivity = activity;
                List list = listListOf;
                arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                it = list.iterator();
                while (it.hasNext()) {
                    arrayList.add((String) ((Pair) it.next()).getSecond());
                }
                ArrayList arrayList3 = arrayList;
                List list2 = savedFonts;
                arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
                it2 = list2.iterator();
                while (it2.hasNext()) {
                    arrayList2.add(((File) it2.next()).getName());
                }
                List<String> listPlus = CollectionsKt.plus((Collection) arrayList3, (Iterable) arrayList2);
                Intrinsics.checkNotNull(view, "null cannot be cast to non-null type android.widget.TextView");
                singleSelectionHelper.showDialog(fragmentActivity, listPlus, i, ((TextView) view).getText().toString(), false, function0, new Function1() { // from class: com.lagradost.cloudstream3.ui.subtitles.SubtitlesFragment$$ExternalSyntheticLambda35
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SubtitlesFragment.onBindingCreated$lambda$2$19$4(listListOf, subtitlesFragment, savedFonts, view, ((Integer) obj).intValue());
                    }
                });
            }
        }
        size = i3 + listListOf.size();
        i = size;
        activity = subtitlesFragment.getActivity();
        if (activity != null) {
            SingleSelectionHelper singleSelectionHelper2 = SingleSelectionHelper.INSTANCE;
            FragmentActivity fragmentActivity2 = activity;
            List list3 = listListOf;
            arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list3, 10));
            it = list3.iterator();
            while (it.hasNext()) {
                arrayList.add((String) ((Pair) it.next()).getSecond());
            }
            ArrayList arrayList4 = arrayList;
            List list4 = savedFonts;
            arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list4, 10));
            it2 = list4.iterator();
            while (it2.hasNext()) {
                arrayList2.add(((File) it2.next()).getName());
            }
            List<String> listPlus2 = CollectionsKt.plus((Collection) arrayList4, (Iterable) arrayList2);
            Intrinsics.checkNotNull(view, "null cannot be cast to non-null type android.widget.TextView");
            singleSelectionHelper2.showDialog(fragmentActivity2, listPlus2, i, ((TextView) view).getText().toString(), false, function0, new Function1() { // from class: com.lagradost.cloudstream3.ui.subtitles.SubtitlesFragment$$ExternalSyntheticLambda35
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return SubtitlesFragment.onBindingCreated$lambda$2$19$4(listListOf, subtitlesFragment, savedFonts, view, ((Integer) obj).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindingCreated$lambda$2$19$4(List list, SubtitlesFragment subtitlesFragment, List list2, View view, int i) {
        if (i < list.size()) {
            SaveCaptionStyle saveCaptionStyle = subtitlesFragment.state;
            if (saveCaptionStyle == null) {
                Intrinsics.throwUninitializedPropertyAccessException("state");
                saveCaptionStyle = null;
            }
            saveCaptionStyle.setTypeface((Integer) ((Pair) list.get(i)).getFirst());
            SaveCaptionStyle saveCaptionStyle2 = subtitlesFragment.state;
            if (saveCaptionStyle2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("state");
                saveCaptionStyle2 = null;
            }
            saveCaptionStyle2.setTypefaceFilePath(null);
        } else {
            SaveCaptionStyle saveCaptionStyle3 = subtitlesFragment.state;
            if (saveCaptionStyle3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("state");
                saveCaptionStyle3 = null;
            }
            saveCaptionStyle3.setTypefaceFilePath(((File) list2.get(i - list.size())).getAbsolutePath());
            SaveCaptionStyle saveCaptionStyle4 = subtitlesFragment.state;
            if (saveCaptionStyle4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("state");
                saveCaptionStyle4 = null;
            }
            saveCaptionStyle4.setTypeface(null);
        }
        Context context = ((TextView) view).getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        subtitlesFragment.updateState(context);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onBindingCreated$lambda$2$20(SubtitlesFragment subtitlesFragment, View view) {
        SaveCaptionStyle saveCaptionStyle = subtitlesFragment.state;
        if (saveCaptionStyle == null) {
            Intrinsics.throwUninitializedPropertyAccessException("state");
            saveCaptionStyle = null;
        }
        saveCaptionStyle.setTypeface(null);
        SaveCaptionStyle saveCaptionStyle2 = subtitlesFragment.state;
        if (saveCaptionStyle2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("state");
            saveCaptionStyle2 = null;
        }
        saveCaptionStyle2.setTypefaceFilePath(null);
        Context context = view.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        subtitlesFragment.updateState(context);
        CommonActivity.INSTANCE.showToast((Activity) subtitlesFragment.getActivity(), R.string.subs_default_reset_toast, (Integer) 0);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$2$21(SubtitlesFragment subtitlesFragment, Function0 function0, View view) {
        List listListOf = CollectionsKt.listOf(new Pair(view.getContext().getString(R.string.none), view.getContext().getString(R.string.none)));
        List<SubtitleHelper.LanguageMetadata> languages = SubtitleHelper.INSTANCE.getLanguages();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(languages, 10));
        for (SubtitleHelper.LanguageMetadata languageMetadata : languages) {
            arrayList.add(new Pair(languageMetadata.getIETF_tag(), SubtitleHelper.LanguageMetadata.nameNextToFlagEmoji$default(languageMetadata, null, 1, null)));
        }
        Pair pairUnzip = CollectionsKt.unzip(CollectionsKt.plus((Collection) listListOf, (Iterable) CollectionsKt.sortedWith(arrayList, new Comparator() { // from class: com.lagradost.cloudstream3.ui.subtitles.SubtitlesFragment$onBindingCreated$lambda$2$21$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                String lowerCase = StringsKt.substringAfter$default((String) ((Pair) t).getSecond(), " ", (String) null, 2, (Object) null).toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                String lowerCase2 = StringsKt.substringAfter$default((String) ((Pair) t2).getSecond(), " ", (String) null, 2, (Object) null).toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
                return ComparisonsKt.compareValues(lowerCase, lowerCase2);
            }
        })));
        final List list = (List) pairUnzip.component1();
        List<String> list2 = (List) pairUnzip.component2();
        FragmentActivity activity = subtitlesFragment.getActivity();
        if (activity != null) {
            int iIndexOf = list.indexOf(INSTANCE.getAutoSelectLanguageTagIETF());
            Intrinsics.checkNotNull(view, "null cannot be cast to non-null type android.widget.TextView");
            SingleSelectionHelper.INSTANCE.showDialog(activity, list2, iIndexOf, ((TextView) view).getText().toString(), true, function0, new Function1() { // from class: com.lagradost.cloudstream3.ui.subtitles.SubtitlesFragment$$ExternalSyntheticLambda29
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return SubtitlesFragment.onBindingCreated$lambda$2$21$2(list, ((Integer) obj).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindingCreated$lambda$2$21$2(List list, int i) {
        CloudStreamApp.INSTANCE.setKey(SubtitlesFragmentKt.SUBTITLE_AUTO_SELECT_KEY, list.get(i));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onBindingCreated$lambda$2$22(SubtitlesFragment subtitlesFragment, View view) {
        CloudStreamApp.INSTANCE.setKey(SubtitlesFragmentKt.SUBTITLE_AUTO_SELECT_KEY, "en");
        CommonActivity.INSTANCE.showToast((Activity) subtitlesFragment.getActivity(), R.string.subs_default_reset_toast, (Integer) 0);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$2$23(SubtitlesFragment subtitlesFragment, Function0 function0, View view) {
        List<SubtitleHelper.LanguageMetadata> languages = SubtitleHelper.INSTANCE.getLanguages();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(languages, 10));
        for (SubtitleHelper.LanguageMetadata languageMetadata : languages) {
            arrayList.add(new Pair(languageMetadata.getIETF_tag(), SubtitleHelper.LanguageMetadata.nameNextToFlagEmoji$default(languageMetadata, null, 1, null)));
        }
        Pair pairUnzip = CollectionsKt.unzip(CollectionsKt.sortedWith(arrayList, new Comparator() { // from class: com.lagradost.cloudstream3.ui.subtitles.SubtitlesFragment$onBindingCreated$lambda$2$23$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                String lowerCase = StringsKt.substringAfter$default((String) ((Pair) t).getSecond(), " ", (String) null, 2, (Object) null).toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                String lowerCase2 = StringsKt.substringAfter$default((String) ((Pair) t2).getSecond(), " ", (String) null, 2, (Object) null).toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
                return ComparisonsKt.compareValues(lowerCase, lowerCase2);
            }
        }));
        final List list = (List) pairUnzip.component1();
        List<String> list2 = (List) pairUnzip.component2();
        List<String> downloadSubsLanguageTagIETF = INSTANCE.getDownloadSubsLanguageTagIETF();
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(downloadSubsLanguageTagIETF, 10));
        Iterator<T> it = downloadSubsLanguageTagIETF.iterator();
        while (it.hasNext()) {
            arrayList2.add(Integer.valueOf(list.indexOf((String) it.next())));
        }
        ArrayList arrayList3 = new ArrayList();
        for (Object obj : arrayList2) {
            if (((Number) obj).intValue() >= 0) {
                arrayList3.add(obj);
            }
        }
        ArrayList arrayList4 = arrayList3;
        FragmentActivity activity = subtitlesFragment.getActivity();
        if (activity != null) {
            Intrinsics.checkNotNull(view, "null cannot be cast to non-null type android.widget.TextView");
            SingleSelectionHelper.INSTANCE.showMultiDialog(activity, list2, arrayList4, ((TextView) view).getText().toString(), function0, new Function1() { // from class: com.lagradost.cloudstream3.ui.subtitles.SubtitlesFragment$$ExternalSyntheticLambda31
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return SubtitlesFragment.onBindingCreated$lambda$2$23$4(list, (List) obj2);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindingCreated$lambda$2$23$4(List list, List indexList) {
        Intrinsics.checkNotNullParameter(indexList, "indexList");
        CloudStreamApp.Companion companion = CloudStreamApp.INSTANCE;
        List list2 = indexList;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        Iterator it = list2.iterator();
        while (it.hasNext()) {
            arrayList.add((String) list.get(((Number) it.next()).intValue()));
        }
        companion.setKey(SubtitlesFragmentKt.SUBTITLE_DOWNLOAD_KEY, CollectionsKt.toList(arrayList));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onBindingCreated$lambda$2$24(SubtitlesFragment subtitlesFragment, View view) {
        CloudStreamApp.INSTANCE.setKey(SubtitlesFragmentKt.SUBTITLE_DOWNLOAD_KEY, CollectionsKt.listOf("en"));
        CommonActivity.INSTANCE.showToast((Activity) subtitlesFragment.getActivity(), R.string.subs_default_reset_toast, (Integer) 0);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$2$25(boolean z, SubtitlesFragment subtitlesFragment, View view) {
        if (z) {
            FragmentActivity activity = subtitlesFragment.getActivity();
            if (activity != null) {
                UIHelper.popCurrentPage$default(UIHelper.INSTANCE, activity, null, 1, null);
                return;
            }
            return;
        }
        subtitlesFragment.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindingCreated$lambda$2$26(SubtitlesFragment subtitlesFragment, boolean z, View view) {
        Companion companion = INSTANCE;
        Context context = view.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        SaveCaptionStyle saveCaptionStyle = subtitlesFragment.state;
        if (saveCaptionStyle == null) {
            Intrinsics.throwUninitializedPropertyAccessException("state");
            saveCaptionStyle = null;
        }
        companion.saveStyle(context, saveCaptionStyle);
        Event<SaveCaptionStyle> event = applyStyleEvent;
        SaveCaptionStyle saveCaptionStyle2 = subtitlesFragment.state;
        if (saveCaptionStyle2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("state");
            saveCaptionStyle2 = null;
        }
        event.invoke(saveCaptionStyle2);
        if (z) {
            FragmentActivity activity = subtitlesFragment.getActivity();
            if (activity != null) {
                UIHelper.popCurrentPage$default(UIHelper.INSTANCE, activity, null, 1, null);
                return;
            }
            return;
        }
        subtitlesFragment.dismiss();
    }
}
