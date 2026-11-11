package com.lagradost.cloudstream3.ui.subtitles;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.media3.common.text.Cue;
import androidx.media3.ui.SubtitleView;
import com.fasterxml.jackson.core.type.TypeReference;
import com.jaredrummler.android.colorpicker.ColorPickerDialog;
import com.lagradost.cloudstream3.CloudStreamApp;
import com.lagradost.cloudstream3.CommonActivity;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.databinding.ChromecastSubtitleSettingsBinding;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.ui.BaseFragment;
import com.lagradost.cloudstream3.ui.settings.Globals;
import com.lagradost.cloudstream3.utils.AppUtils;
import com.lagradost.cloudstream3.utils.DataStore;
import com.lagradost.cloudstream3.utils.Event;
import com.lagradost.cloudstream3.utils.SingleSelectionHelper;
import com.lagradost.cloudstream3.utils.UIHelper;
import io.ktor.http.ContentDisposition;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.modules.SerializersModule;

/* JADX INFO: compiled from: ChromecastSubtitlesFragment.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u001c2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001cB\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u001c\u0010\u0005\u001a\u00020\u00062\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\bH\u0002J\u0010\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\tH\u0002J\u0010\u0010\f\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\tH\u0002J\u001f\u0010\r\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\t2\b\u0010\u000e\u001a\u0004\u0018\u00010\tH\u0002¢\u0006\u0002\u0010\u000fJ\b\u0010\u0010\u001a\u00020\u0006H\u0002J\b\u0010\u0015\u001a\u00020\u0006H\u0016J\u0010\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0010\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u0002H\u0016J\u0010\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u0002H\u0003R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/lagradost/cloudstream3/ui/subtitles/ChromecastSubtitlesFragment;", "Lcom/lagradost/cloudstream3/ui/BaseFragment;", "Lcom/lagradost/cloudstream3/databinding/ChromecastSubtitleSettingsBinding;", "<init>", "()V", "onColorSelected", "", "stuff", "Lkotlin/Pair;", "", "onDialogDismissed", "id", "getColor", "setColor", "color", "(ILjava/lang/Integer;)V", "updateState", "state", "Lcom/lagradost/cloudstream3/ui/subtitles/SaveChromeCaptionStyle;", "hide", "", "onDestroy", "fixLayout", "view", "Landroid/view/View;", "onBindingCreated", "binding", "setSubtitleCues", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class ChromecastSubtitlesFragment extends BaseFragment<ChromecastSubtitleSettingsBinding> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Event<SaveChromeCaptionStyle> applyStyleEvent = new Event<>();
    private static final SaveChromeCaptionStyle defaultState = new SaveChromeCaptionStyle((String) null, (Integer) null, 0, 0, 0, 0, 0.0f, 0, 255, (DefaultConstructorMarker) null);
    private boolean hide;
    private SaveChromeCaptionStyle state;

    private final void updateState() {
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.subtitles.ChromecastSubtitlesFragment$1, reason: invalid class name */
    /* JADX INFO: compiled from: ChromecastSubtitlesFragment.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function3<LayoutInflater, ViewGroup, Boolean, ChromecastSubtitleSettingsBinding> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        AnonymousClass1() {
            super(3, ChromecastSubtitleSettingsBinding.class, "inflate", "inflate(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Z)Lcom/lagradost/cloudstream3/databinding/ChromecastSubtitleSettingsBinding;", 0);
        }

        public final ChromecastSubtitleSettingsBinding invoke(LayoutInflater p0, ViewGroup viewGroup, boolean z) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return ChromecastSubtitleSettingsBinding.inflate(p0, viewGroup, z);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ ChromecastSubtitleSettingsBinding invoke(LayoutInflater layoutInflater, ViewGroup viewGroup, Boolean bool) {
            return invoke(layoutInflater, viewGroup, bool.booleanValue());
        }
    }

    public ChromecastSubtitlesFragment() {
        super(new BaseFragment.BindingCreator.Inflate(AnonymousClass1.INSTANCE));
        this.hide = true;
    }

    /* JADX INFO: compiled from: ChromecastSubtitlesFragment.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\b\u0002\u0010\r\u001a\u00020\u000eJ\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0002J\u0012\u0010\u0012\u001a\u00020\n*\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0006J\u0018\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0006\u0010\u0019\u001a\u00020\u0006R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\u001a\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/lagradost/cloudstream3/ui/subtitles/ChromecastSubtitlesFragment$Companion;", "", "<init>", "()V", "applyStyleEvent", "Lcom/lagradost/cloudstream3/utils/Event;", "Lcom/lagradost/cloudstream3/ui/subtitles/SaveChromeCaptionStyle;", "getApplyStyleEvent", "()Lcom/lagradost/cloudstream3/utils/Event;", "push", "", "activity", "Landroid/app/Activity;", "hide", "", "getDefColor", "", "id", "saveStyle", "Landroid/content/Context;", "style", "getPixels", "unit", ContentDisposition.Parameters.Size, "", "getCurrentSavedStyle", "defaultState", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
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

        public final Event<SaveChromeCaptionStyle> getApplyStyleEvent() {
            return ChromecastSubtitlesFragment.applyStyleEvent;
        }

        public static /* synthetic */ void push$default(Companion companion, Activity activity, boolean z, int i, Object obj) {
            if ((i & 2) != 0) {
                z = true;
            }
            companion.push(activity, z);
        }

        public final void push(Activity activity, boolean hide) {
            UIHelper uIHelper = UIHelper.INSTANCE;
            int i = R.id.global_to_navigation_chrome_subtitles;
            Bundle bundle = new Bundle();
            bundle.putBoolean("hide", hide);
            Unit unit = Unit.INSTANCE;
            UIHelper.navigate$default(uIHelper, activity, i, bundle, null, 4, null);
        }

        public final void saveStyle(Context context, SaveChromeCaptionStyle style) {
            Intrinsics.checkNotNullParameter(context, "<this>");
            Intrinsics.checkNotNullParameter(style, "style");
            DataStore.INSTANCE.setKey(context, ChromecastSubtitlesFragmentKt.CHROME_SUBTITLE_KEY, style);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final int getPixels(int unit, float size) {
            DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
            Intrinsics.checkNotNullExpressionValue(displayMetrics, "getDisplayMetrics(...)");
            return (int) TypedValue.applyDimension(unit, size, displayMetrics);
        }

        public final SaveChromeCaptionStyle getCurrentSavedStyle() {
            Object objM8936constructorimpl;
            Object objDecodeFromString;
            Context context = CloudStreamApp.INSTANCE.getContext();
            Object obj = null;
            if (context != null) {
                try {
                    String string = DataStore.INSTANCE.getSharedPrefs(context).getString(ChromecastSubtitlesFragmentKt.CHROME_SUBTITLE_KEY, null);
                    if (string != null) {
                        AppUtils appUtils = AppUtils.INSTANCE;
                        try {
                            Result.Companion companion = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(SaveChromeCaptionStyle.INSTANCE.serializer());
                        } catch (Throwable th) {
                            Result.Companion companion2 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                        }
                        if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) != null) {
                            try {
                                Result.Companion companion3 = Result.INSTANCE;
                                objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(SaveChromeCaptionStyle.class), null, 2, null));
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
                                objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<SaveChromeCaptionStyle>() { // from class: com.lagradost.cloudstream3.ui.subtitles.ChromecastSubtitlesFragment$Companion$getCurrentSavedStyle$$inlined$getKey$1
                                });
                            } catch (Throwable unused) {
                                objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<SaveChromeCaptionStyle>() { // from class: com.lagradost.cloudstream3.ui.subtitles.ChromecastSubtitlesFragment$Companion$getCurrentSavedStyle$$inlined$getKey$1
                                });
                            }
                        } else {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<SaveChromeCaptionStyle>() { // from class: com.lagradost.cloudstream3.ui.subtitles.ChromecastSubtitlesFragment$Companion$getCurrentSavedStyle$$inlined$getKey$1
                            });
                        }
                        obj = objDecodeFromString;
                    }
                } catch (Exception unused2) {
                }
            }
            SaveChromeCaptionStyle saveChromeCaptionStyle = (SaveChromeCaptionStyle) obj;
            return saveChromeCaptionStyle == null ? ChromecastSubtitlesFragment.defaultState : saveChromeCaptionStyle;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onColorSelected(Pair<Integer, Integer> stuff) {
        FragmentActivity activity;
        setColor(stuff.getFirst().intValue(), stuff.getSecond());
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

    private final int getColor(int id) {
        int foregroundColor;
        SaveChromeCaptionStyle saveChromeCaptionStyle = null;
        if (id == 0) {
            SaveChromeCaptionStyle saveChromeCaptionStyle2 = this.state;
            if (saveChromeCaptionStyle2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("state");
            } else {
                saveChromeCaptionStyle = saveChromeCaptionStyle2;
            }
            foregroundColor = saveChromeCaptionStyle.getForegroundColor();
        } else if (id == 1) {
            SaveChromeCaptionStyle saveChromeCaptionStyle3 = this.state;
            if (saveChromeCaptionStyle3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("state");
            } else {
                saveChromeCaptionStyle = saveChromeCaptionStyle3;
            }
            foregroundColor = saveChromeCaptionStyle.getEdgeColor();
        } else if (id == 2) {
            SaveChromeCaptionStyle saveChromeCaptionStyle4 = this.state;
            if (saveChromeCaptionStyle4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("state");
            } else {
                saveChromeCaptionStyle = saveChromeCaptionStyle4;
            }
            foregroundColor = saveChromeCaptionStyle.getBackgroundColor();
        } else if (id != 3) {
            foregroundColor = 0;
        } else {
            SaveChromeCaptionStyle saveChromeCaptionStyle5 = this.state;
            if (saveChromeCaptionStyle5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("state");
            } else {
                saveChromeCaptionStyle = saveChromeCaptionStyle5;
            }
            foregroundColor = saveChromeCaptionStyle.getWindowColor();
        }
        if (foregroundColor == 0) {
            return -16777216;
        }
        return foregroundColor;
    }

    private final void setColor(int id, Integer color) {
        int defColor;
        if (color == null) {
            defColor = INSTANCE.getDefColor(id);
        } else {
            defColor = color.intValue();
        }
        SaveChromeCaptionStyle saveChromeCaptionStyle = null;
        if (id == 0) {
            SaveChromeCaptionStyle saveChromeCaptionStyle2 = this.state;
            if (saveChromeCaptionStyle2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("state");
            } else {
                saveChromeCaptionStyle = saveChromeCaptionStyle2;
            }
            saveChromeCaptionStyle.setForegroundColor(defColor);
        } else if (id == 1) {
            SaveChromeCaptionStyle saveChromeCaptionStyle3 = this.state;
            if (saveChromeCaptionStyle3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("state");
            } else {
                saveChromeCaptionStyle = saveChromeCaptionStyle3;
            }
            saveChromeCaptionStyle.setEdgeColor(defColor);
        } else if (id == 2) {
            SaveChromeCaptionStyle saveChromeCaptionStyle4 = this.state;
            if (saveChromeCaptionStyle4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("state");
            } else {
                saveChromeCaptionStyle = saveChromeCaptionStyle4;
            }
            saveChromeCaptionStyle.setBackgroundColor(defColor);
        } else if (id == 3) {
            SaveChromeCaptionStyle saveChromeCaptionStyle5 = this.state;
            if (saveChromeCaptionStyle5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("state");
            } else {
                saveChromeCaptionStyle = saveChromeCaptionStyle5;
            }
            saveChromeCaptionStyle.setWindowColor(defColor);
        }
        updateState();
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.subtitles.ChromecastSubtitlesFragment$onDestroy$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ChromecastSubtitlesFragment.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class C09161 extends FunctionReferenceImpl implements Function1<Pair<? extends Integer, ? extends Integer>, Unit> {
        C09161(Object obj) {
            super(1, obj, ChromecastSubtitlesFragment.class, "onColorSelected", "onColorSelected(Lkotlin/Pair;)V", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Pair<? extends Integer, ? extends Integer> pair) {
            invoke2((Pair<Integer, Integer>) pair);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(Pair<Integer, Integer> p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            ((ChromecastSubtitlesFragment) this.receiver).onColorSelected(p0);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        CommonActivity.INSTANCE.getOnColorSelectedEvent().minusAssign(new C09161(this));
    }

    @Override // com.lagradost.cloudstream3.ui.BaseFragmentHelper
    public void fixLayout(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        UIHelper.fixSystemBarsPadding$default(UIHelper.INSTANCE, view, null, null, false, Globals.INSTANCE.isLandscape(), Globals.INSTANCE.isLayout(6), false, false, false, 462, null);
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.subtitles.ChromecastSubtitlesFragment$onBindingCreated$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ChromecastSubtitlesFragment.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class C09151 extends FunctionReferenceImpl implements Function1<Pair<? extends Integer, ? extends Integer>, Unit> {
        C09151(Object obj) {
            super(1, obj, ChromecastSubtitlesFragment.class, "onColorSelected", "onColorSelected(Lkotlin/Pair;)V", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Pair<? extends Integer, ? extends Integer> pair) {
            invoke2((Pair<Integer, Integer>) pair);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(Pair<Integer, Integer> p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            ((ChromecastSubtitlesFragment) this.receiver).onColorSelected(p0);
        }
    }

    @Override // com.lagradost.cloudstream3.ui.BaseFragment, com.lagradost.cloudstream3.ui.BaseFragmentHelper
    public void onBindingCreated(ChromecastSubtitleSettingsBinding binding) {
        Intrinsics.checkNotNullParameter(binding, "binding");
        Bundle arguments = getArguments();
        this.hide = arguments != null ? arguments.getBoolean("hide") : true;
        CommonActivity.INSTANCE.getOnColorSelectedEvent().plusAssign(new C09151(this));
        CommonActivity.INSTANCE.getOnDialogDismissedEvent().plusAssign(new AnonymousClass2(this));
        this.state = INSTANCE.getCurrentSavedStyle();
        updateState();
        boolean zIsLayout = Globals.INSTANCE.isLayout(6);
        TextView subsTextColor = binding.subsTextColor;
        Intrinsics.checkNotNullExpressionValue(subsTextColor, "subsTextColor");
        onBindingCreated$setup(subsTextColor, zIsLayout, this, 0);
        TextView subsOutlineColor = binding.subsOutlineColor;
        Intrinsics.checkNotNullExpressionValue(subsOutlineColor, "subsOutlineColor");
        onBindingCreated$setup(subsOutlineColor, zIsLayout, this, 1);
        TextView subsBackgroundColor = binding.subsBackgroundColor;
        Intrinsics.checkNotNullExpressionValue(subsBackgroundColor, "subsBackgroundColor");
        onBindingCreated$setup(subsBackgroundColor, zIsLayout, this, 2);
        final Function0 function0 = new Function0() { // from class: com.lagradost.cloudstream3.ui.subtitles.ChromecastSubtitlesFragment$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return ChromecastSubtitlesFragment.onBindingCreated$lambda$3(this.f$0);
            }
        };
        TextView subsEdgeType = binding.subsEdgeType;
        Intrinsics.checkNotNullExpressionValue(subsEdgeType, "subsEdgeType");
        subsEdgeType.setFocusableInTouchMode(zIsLayout);
        binding.subsEdgeType.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.subtitles.ChromecastSubtitlesFragment$$ExternalSyntheticLambda10
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ChromecastSubtitlesFragment.onBindingCreated$lambda$4(this.f$0, function0, view);
            }
        });
        binding.subsEdgeType.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.lagradost.cloudstream3.ui.subtitles.ChromecastSubtitlesFragment$$ExternalSyntheticLambda11
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                return ChromecastSubtitlesFragment.onBindingCreated$lambda$5(this.f$0, view);
            }
        });
        TextView subsFontSize = binding.subsFontSize;
        Intrinsics.checkNotNullExpressionValue(subsFontSize, "subsFontSize");
        subsFontSize.setFocusableInTouchMode(zIsLayout);
        binding.subsFontSize.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.subtitles.ChromecastSubtitlesFragment$$ExternalSyntheticLambda12
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ChromecastSubtitlesFragment.onBindingCreated$lambda$6(this.f$0, function0, view);
            }
        });
        binding.subsFontSize.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.lagradost.cloudstream3.ui.subtitles.ChromecastSubtitlesFragment$$ExternalSyntheticLambda13
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                return ChromecastSubtitlesFragment.onBindingCreated$lambda$7(this.f$0, view);
            }
        });
        TextView subsFont = binding.subsFont;
        Intrinsics.checkNotNullExpressionValue(subsFont, "subsFont");
        subsFont.setFocusableInTouchMode(zIsLayout);
        binding.subsFont.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.subtitles.ChromecastSubtitlesFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ChromecastSubtitlesFragment.onBindingCreated$lambda$8(this.f$0, function0, view);
            }
        });
        binding.subsFont.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.lagradost.cloudstream3.ui.subtitles.ChromecastSubtitlesFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                return ChromecastSubtitlesFragment.onBindingCreated$lambda$9(this.f$0, view);
            }
        });
        binding.cancelBtt.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.subtitles.ChromecastSubtitlesFragment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ChromecastSubtitlesFragment.onBindingCreated$lambda$10(this.f$0, view);
            }
        });
        binding.applyBtt.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.subtitles.ChromecastSubtitlesFragment$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ChromecastSubtitlesFragment.onBindingCreated$lambda$11(this.f$0, view);
            }
        });
        setSubtitleCues(binding);
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.subtitles.ChromecastSubtitlesFragment$onBindingCreated$2, reason: invalid class name */
    /* JADX INFO: compiled from: ChromecastSubtitlesFragment.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass2 extends FunctionReferenceImpl implements Function1<Integer, Unit> {
        AnonymousClass2(Object obj) {
            super(1, obj, ChromecastSubtitlesFragment.class, "onDialogDismissed", "onDialogDismissed(I)V", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
            invoke(num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(int i) {
            ((ChromecastSubtitlesFragment) this.receiver).onDialogDismissed(i);
        }
    }

    private static final void onBindingCreated$setup(View view, boolean z, final ChromecastSubtitlesFragment chromecastSubtitlesFragment, final int i) {
        view.setFocusableInTouchMode(z);
        view.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.subtitles.ChromecastSubtitlesFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                ChromecastSubtitlesFragment.onBindingCreated$setup$lambda$0(this.f$0, i, view2);
            }
        });
        view.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.lagradost.cloudstream3.ui.subtitles.ChromecastSubtitlesFragment$$ExternalSyntheticLambda5
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view2) {
                return ChromecastSubtitlesFragment.onBindingCreated$setup$lambda$1(this.f$0, i, view2);
            }
        });
    }

    static final void onBindingCreated$setup$lambda$0(ChromecastSubtitlesFragment chromecastSubtitlesFragment, int i, View view) {
        FragmentActivity activity = chromecastSubtitlesFragment.getActivity();
        if (activity != null) {
            ColorPickerDialog.newBuilder().setDialogId(i).setShowAlphaSlider(true).setColor(chromecastSubtitlesFragment.getColor(i)).show(activity);
        }
    }

    static final boolean onBindingCreated$setup$lambda$1(ChromecastSubtitlesFragment chromecastSubtitlesFragment, int i, View view) {
        chromecastSubtitlesFragment.setColor(i, null);
        CommonActivity.INSTANCE.showToast(R.string.subs_default_reset_toast, (Integer) 0);
        return true;
    }

    static final Unit onBindingCreated$lambda$3(ChromecastSubtitlesFragment chromecastSubtitlesFragment) {
        FragmentActivity activity;
        if (chromecastSubtitlesFragment.hide && (activity = chromecastSubtitlesFragment.getActivity()) != null) {
            UIHelper.INSTANCE.hideSystemUI(activity);
        }
        return Unit.INSTANCE;
    }

    static final void onBindingCreated$lambda$4(final ChromecastSubtitlesFragment chromecastSubtitlesFragment, Function0 function0, View view) {
        final List listListOf = CollectionsKt.listOf((Object[]) new Pair[]{new Pair(0, view.getContext().getString(R.string.subtitles_none)), new Pair(1, view.getContext().getString(R.string.subtitles_outline)), new Pair(4, view.getContext().getString(R.string.subtitles_depressed)), new Pair(2, view.getContext().getString(R.string.subtitles_shadow)), new Pair(3, view.getContext().getString(R.string.subtitles_raised))});
        FragmentActivity activity = chromecastSubtitlesFragment.getActivity();
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
            SaveChromeCaptionStyle saveChromeCaptionStyle = chromecastSubtitlesFragment.state;
            if (saveChromeCaptionStyle == null) {
                Intrinsics.throwUninitializedPropertyAccessException("state");
                saveChromeCaptionStyle = null;
            }
            int iIndexOf = arrayList4.indexOf(Integer.valueOf(saveChromeCaptionStyle.getEdgeType()));
            Intrinsics.checkNotNull(view, "null cannot be cast to non-null type android.widget.TextView");
            singleSelectionHelper.showDialog(fragmentActivity, arrayList2, iIndexOf, ((TextView) view).getText().toString(), false, function0, new Function1() { // from class: com.lagradost.cloudstream3.ui.subtitles.ChromecastSubtitlesFragment$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return ChromecastSubtitlesFragment.onBindingCreated$lambda$4$2(this.f$0, listListOf, ((Integer) obj).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindingCreated$lambda$4$2(ChromecastSubtitlesFragment chromecastSubtitlesFragment, List list, int i) {
        SaveChromeCaptionStyle saveChromeCaptionStyle = chromecastSubtitlesFragment.state;
        if (saveChromeCaptionStyle == null) {
            Intrinsics.throwUninitializedPropertyAccessException("state");
            saveChromeCaptionStyle = null;
        }
        List list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        Iterator it = list2.iterator();
        while (it.hasNext()) {
            arrayList.add(Integer.valueOf(((Number) ((Pair) it.next()).getFirst()).intValue()));
        }
        saveChromeCaptionStyle.setEdgeType(((Number) arrayList.get(i)).intValue());
        chromecastSubtitlesFragment.updateState();
        return Unit.INSTANCE;
    }

    static final boolean onBindingCreated$lambda$5(ChromecastSubtitlesFragment chromecastSubtitlesFragment, View view) {
        SaveChromeCaptionStyle saveChromeCaptionStyle = chromecastSubtitlesFragment.state;
        if (saveChromeCaptionStyle == null) {
            Intrinsics.throwUninitializedPropertyAccessException("state");
            saveChromeCaptionStyle = null;
        }
        saveChromeCaptionStyle.setEdgeType(defaultState.getEdgeType());
        chromecastSubtitlesFragment.updateState();
        CommonActivity.INSTANCE.showToast(R.string.subs_default_reset_toast, (Integer) 0);
        return true;
    }

    static final void onBindingCreated$lambda$6(final ChromecastSubtitlesFragment chromecastSubtitlesFragment, Function0 function0, View view) {
        final List listListOf = CollectionsKt.listOf((Object[]) new Pair[]{new Pair(Float.valueOf(0.75f), "75%"), new Pair(Float.valueOf(0.8f), "80%"), new Pair(Float.valueOf(0.85f), "85%"), new Pair(Float.valueOf(0.9f), "90%"), new Pair(Float.valueOf(0.95f), "95%"), new Pair(Float.valueOf(1.0f), "100%"), new Pair(Float.valueOf(1.05f), view.getContext().getString(R.string.normal)), new Pair(Float.valueOf(1.1f), "110%"), new Pair(Float.valueOf(1.15f), "115%"), new Pair(Float.valueOf(1.2f), "120%"), new Pair(Float.valueOf(1.25f), "125%"), new Pair(Float.valueOf(1.3f), "130%"), new Pair(Float.valueOf(1.35f), "135%"), new Pair(Float.valueOf(1.4f), "140%"), new Pair(Float.valueOf(1.45f), "145%"), new Pair(Float.valueOf(1.5f), "150%")});
        FragmentActivity activity = chromecastSubtitlesFragment.getActivity();
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
                arrayList3.add(Float.valueOf(((Number) ((Pair) it2.next()).getFirst()).floatValue()));
            }
            ArrayList arrayList4 = arrayList3;
            SaveChromeCaptionStyle saveChromeCaptionStyle = chromecastSubtitlesFragment.state;
            if (saveChromeCaptionStyle == null) {
                Intrinsics.throwUninitializedPropertyAccessException("state");
                saveChromeCaptionStyle = null;
            }
            int iIndexOf = arrayList4.indexOf(Float.valueOf(saveChromeCaptionStyle.getFontScale()));
            Intrinsics.checkNotNull(view, "null cannot be cast to non-null type android.widget.TextView");
            singleSelectionHelper.showDialog(fragmentActivity, arrayList2, iIndexOf, ((TextView) view).getText().toString(), false, function0, new Function1() { // from class: com.lagradost.cloudstream3.ui.subtitles.ChromecastSubtitlesFragment$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return ChromecastSubtitlesFragment.onBindingCreated$lambda$6$2(this.f$0, listListOf, ((Integer) obj).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindingCreated$lambda$6$2(ChromecastSubtitlesFragment chromecastSubtitlesFragment, List list, int i) {
        SaveChromeCaptionStyle saveChromeCaptionStyle = chromecastSubtitlesFragment.state;
        if (saveChromeCaptionStyle == null) {
            Intrinsics.throwUninitializedPropertyAccessException("state");
            saveChromeCaptionStyle = null;
        }
        List list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        Iterator it = list2.iterator();
        while (it.hasNext()) {
            arrayList.add(Float.valueOf(((Number) ((Pair) it.next()).getFirst()).floatValue()));
        }
        saveChromeCaptionStyle.setFontScale(((Number) arrayList.get(i)).floatValue());
        return Unit.INSTANCE;
    }

    static final boolean onBindingCreated$lambda$7(ChromecastSubtitlesFragment chromecastSubtitlesFragment, View view) {
        SaveChromeCaptionStyle saveChromeCaptionStyle = chromecastSubtitlesFragment.state;
        if (saveChromeCaptionStyle == null) {
            Intrinsics.throwUninitializedPropertyAccessException("state");
            saveChromeCaptionStyle = null;
        }
        saveChromeCaptionStyle.setFontScale(defaultState.getFontScale());
        CommonActivity.INSTANCE.showToast(R.string.subs_default_reset_toast, (Integer) 0);
        return true;
    }

    static final void onBindingCreated$lambda$8(final ChromecastSubtitlesFragment chromecastSubtitlesFragment, Function0 function0, View view) {
        SaveChromeCaptionStyle saveChromeCaptionStyle = null;
        final List listListOf = CollectionsKt.listOf((Object[]) new Pair[]{TuplesKt.to(null, view.getContext().getString(R.string.normal)), TuplesKt.to("Droid Sans", "Droid Sans"), TuplesKt.to("Droid Sans Mono", "Droid Sans Mono"), TuplesKt.to("Droid Serif Regular", "Droid Serif Regular"), TuplesKt.to("Cutive Mono", "Cutive Mono"), TuplesKt.to("Short Stack", "Short Stack"), TuplesKt.to("Quintessential", "Quintessential"), TuplesKt.to("Alegreya Sans SC", "Alegreya Sans SC")});
        FragmentActivity activity = chromecastSubtitlesFragment.getActivity();
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
                arrayList3.add((String) ((Pair) it2.next()).getFirst());
            }
            ArrayList arrayList4 = arrayList3;
            SaveChromeCaptionStyle saveChromeCaptionStyle2 = chromecastSubtitlesFragment.state;
            if (saveChromeCaptionStyle2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("state");
            } else {
                saveChromeCaptionStyle = saveChromeCaptionStyle2;
            }
            int iIndexOf = arrayList4.indexOf(saveChromeCaptionStyle.getFontFamily());
            Intrinsics.checkNotNull(view, "null cannot be cast to non-null type android.widget.TextView");
            singleSelectionHelper.showDialog(fragmentActivity, arrayList2, iIndexOf, ((TextView) view).getText().toString(), false, function0, new Function1() { // from class: com.lagradost.cloudstream3.ui.subtitles.ChromecastSubtitlesFragment$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return ChromecastSubtitlesFragment.onBindingCreated$lambda$8$2(this.f$0, listListOf, ((Integer) obj).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindingCreated$lambda$8$2(ChromecastSubtitlesFragment chromecastSubtitlesFragment, List list, int i) {
        SaveChromeCaptionStyle saveChromeCaptionStyle = chromecastSubtitlesFragment.state;
        if (saveChromeCaptionStyle == null) {
            Intrinsics.throwUninitializedPropertyAccessException("state");
            saveChromeCaptionStyle = null;
        }
        List list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        Iterator it = list2.iterator();
        while (it.hasNext()) {
            arrayList.add((String) ((Pair) it.next()).getFirst());
        }
        saveChromeCaptionStyle.setFontFamily((String) arrayList.get(i));
        chromecastSubtitlesFragment.updateState();
        return Unit.INSTANCE;
    }

    static final boolean onBindingCreated$lambda$9(ChromecastSubtitlesFragment chromecastSubtitlesFragment, View view) {
        SaveChromeCaptionStyle saveChromeCaptionStyle = chromecastSubtitlesFragment.state;
        if (saveChromeCaptionStyle == null) {
            Intrinsics.throwUninitializedPropertyAccessException("state");
            saveChromeCaptionStyle = null;
        }
        saveChromeCaptionStyle.setFontFamily(defaultState.getFontFamily());
        chromecastSubtitlesFragment.updateState();
        CommonActivity.INSTANCE.showToast((Activity) chromecastSubtitlesFragment.getActivity(), R.string.subs_default_reset_toast, (Integer) 0);
        return true;
    }

    static final void onBindingCreated$lambda$10(ChromecastSubtitlesFragment chromecastSubtitlesFragment, View view) {
        FragmentActivity activity = chromecastSubtitlesFragment.getActivity();
        if (activity != null) {
            UIHelper.popCurrentPage$default(UIHelper.INSTANCE, activity, null, 1, null);
        }
    }

    static final void onBindingCreated$lambda$11(ChromecastSubtitlesFragment chromecastSubtitlesFragment, View view) {
        Companion companion = INSTANCE;
        Context context = view.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        SaveChromeCaptionStyle saveChromeCaptionStyle = chromecastSubtitlesFragment.state;
        if (saveChromeCaptionStyle == null) {
            Intrinsics.throwUninitializedPropertyAccessException("state");
            saveChromeCaptionStyle = null;
        }
        companion.saveStyle(context, saveChromeCaptionStyle);
        Event<SaveChromeCaptionStyle> event = applyStyleEvent;
        SaveChromeCaptionStyle saveChromeCaptionStyle2 = chromecastSubtitlesFragment.state;
        if (saveChromeCaptionStyle2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("state");
            saveChromeCaptionStyle2 = null;
        }
        event.invoke(saveChromeCaptionStyle2);
        FragmentActivity activity = chromecastSubtitlesFragment.getActivity();
        if (activity != null) {
            UIHelper.popCurrentPage$default(UIHelper.INSTANCE, activity, null, 1, null);
        }
    }

    private final void setSubtitleCues(ChromecastSubtitleSettingsBinding binding) {
        SubtitleView subtitleView = binding.subtitleText;
        subtitleView.setCues(CollectionsKt.listOf(new Cue.Builder().setTextSize(INSTANCE.getPixels(2, 25.0f), 2).setText(subtitleView.getContext().getString(R.string.subtitles_example_text)).build()));
    }
}
