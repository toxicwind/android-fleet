package com.lagradost.cloudstream3.ui.settings.testing;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.widget.ContentLoadingProgressBar;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.android.material.button.MaterialButton;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.utils.AppContextUtils;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TestView.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001IB'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u001a\u0010:\u001a\u0002042\u0012\u0010;\u001a\u000e\u0012\u0004\u0012\u000203\u0012\u0004\u0012\u00020402J\u000e\u0010<\u001a\u0002042\u0006\u0010=\u001a\u000203J%\u0010>\u001a\u0002042\u0006\u0010?\u001a\u00020\u00072\u0006\u0010@\u001a\u00020\u00072\b\u0010A\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010BJ\u0010\u0010C\u001a\u0002042\b\b\u0001\u0010D\u001a\u00020\u0007J\u000e\u0010E\u001a\u0002042\u0006\u0010;\u001a\u00020FJ\u000e\u0010G\u001a\u0002042\u0006\u0010;\u001a\u00020FJ\u000e\u0010H\u001a\u0002042\u0006\u0010;\u001a\u00020FR\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\r\"\u0004\b\u0012\u0010\u000fR\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\r\"\u0004\b\u0015\u0010\u000fR\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001c\u0010\u001c\u001a\u0004\u0018\u00010\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0019\"\u0004\b\u001e\u0010\u001bR\u001c\u0010\u001f\u001a\u0004\u0018\u00010\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0019\"\u0004\b!\u0010\u001bR\u001c\u0010\"\u001a\u0004\u0018\u00010\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0019\"\u0004\b$\u0010\u001bR\u001c\u0010%\u001a\u0004\u0018\u00010&X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u001c\u0010+\u001a\u0004\u0018\u00010,X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R&\u00101\u001a\u000e\u0012\u0004\u0012\u000203\u0012\u0004\u0012\u00020402X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u00106\"\u0004\b7\u00108R\u000e\u00109\u001a\u000203X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006J"}, d2 = {"Lcom/lagradost/cloudstream3/ui/settings/testing/TestView;", "Landroidx/cardview/widget/CardView;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "mainSection", "Landroid/view/View;", "getMainSection", "()Landroid/view/View;", "setMainSection", "(Landroid/view/View;)V", "testsPassedSection", "getTestsPassedSection", "setTestsPassedSection", "testsFailedSection", "getTestsFailedSection", "setTestsFailedSection", "mainSectionText", "Landroid/widget/TextView;", "getMainSectionText", "()Landroid/widget/TextView;", "setMainSectionText", "(Landroid/widget/TextView;)V", "mainSectionHeader", "getMainSectionHeader", "setMainSectionHeader", "testsPassedSectionText", "getTestsPassedSectionText", "setTestsPassedSectionText", "testsFailedSectionText", "getTestsFailedSectionText", "setTestsFailedSectionText", "totalProgressBar", "Landroidx/core/widget/ContentLoadingProgressBar;", "getTotalProgressBar", "()Landroidx/core/widget/ContentLoadingProgressBar;", "setTotalProgressBar", "(Landroidx/core/widget/ContentLoadingProgressBar;)V", "playPauseButton", "Lcom/google/android/material/button/MaterialButton;", "getPlayPauseButton", "()Lcom/google/android/material/button/MaterialButton;", "setPlayPauseButton", "(Lcom/google/android/material/button/MaterialButton;)V", "stateListener", "Lkotlin/Function1;", "Lcom/lagradost/cloudstream3/ui/settings/testing/TestView$TestState;", "", "getStateListener", "()Lkotlin/jvm/functions/Function1;", "setStateListener", "(Lkotlin/jvm/functions/Function1;)V", "state", "setOnPlayButtonListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "setState", "newState", "setProgress", "passed", "failed", "total", "(IILjava/lang/Integer;)V", "setMainHeader", "header", "setOnMainClick", "Landroid/view/View$OnClickListener;", "setOnPassedClick", "setOnFailedClick", "TestState", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class TestView extends CardView {
    private View mainSection;
    private TextView mainSectionHeader;
    private TextView mainSectionText;
    private MaterialButton playPauseButton;
    private TestState state;
    private Function1<? super TestState, Unit> stateListener;
    private View testsFailedSection;
    private TextView testsFailedSectionText;
    private View testsPassedSection;
    private TextView testsPassedSectionText;
    private ContentLoadingProgressBar totalProgressBar;

    /* JADX INFO: compiled from: TestView.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[TestState.values().length];
            try {
                iArr[TestState.None.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[TestState.Running.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[TestState.Stopped.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public TestView(Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public TestView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TestView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        this.stateListener = new Function1() { // from class: com.lagradost.cloudstream3.ui.settings.testing.TestView$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TestView.stateListener$lambda$0((TestView.TestState) obj);
            }
        };
        this.state = TestState.None;
        LayoutInflater.from(context).inflate(R.layout.view_test, (ViewGroup) this, true);
        this.mainSection = findViewById(R.id.main_test_section);
        this.testsPassedSection = findViewById(R.id.passed_test_section);
        this.testsFailedSection = findViewById(R.id.failed_test_section);
        this.mainSectionHeader = (TextView) findViewById(R.id.main_test_header);
        this.mainSectionText = (TextView) findViewById(R.id.main_test_section_progress);
        this.testsPassedSectionText = (TextView) findViewById(R.id.passed_test_section_progress);
        this.testsFailedSectionText = (TextView) findViewById(R.id.failed_test_section_progress);
        this.totalProgressBar = (ContentLoadingProgressBar) findViewById(R.id.test_total_progress);
        this.playPauseButton = (MaterialButton) findViewById(R.id.tests_play_pause);
        if (attributeSet != null) {
            int[] TestView = R.styleable.TestView;
            Intrinsics.checkNotNullExpressionValue(TestView, "TestView");
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, TestView, 0, 0);
            TextView textView = this.mainSectionHeader;
            if (textView != null) {
                textView.setText(typedArrayObtainStyledAttributes.getString(R.styleable.TestView_header_text));
            }
            typedArrayObtainStyledAttributes.recycle();
        }
        MaterialButton materialButton = this.playPauseButton;
        if (materialButton != null) {
            materialButton.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.testing.TestView$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    TestView._init_$lambda$1(this.f$0, view);
                }
            });
        }
    }

    public /* synthetic */ TestView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    /* JADX INFO: compiled from: TestView.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\n\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u001d\b\u0002\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\r"}, d2 = {"Lcom/lagradost/cloudstream3/ui/settings/testing/TestView$TestState;", "", "stringRes", "", "icon", "<init>", "(Ljava/lang/String;III)V", "getStringRes", "()I", "getIcon", "None", "Stopped", "Running", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public enum TestState {
        None(R.string.start, R.drawable.ic_baseline_play_arrow_24),
        Stopped(R.string.restart, R.drawable.ic_baseline_play_arrow_24),
        Running(R.string.stop, R.drawable.pause_to_play);

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
        private final int icon;
        private final int stringRes;

        public static EnumEntries<TestState> getEntries() {
            return $ENTRIES;
        }

        TestState(int i, int i2) {
            this.stringRes = i;
            this.icon = i2;
        }

        public final int getIcon() {
            return this.icon;
        }

        public final int getStringRes() {
            return this.stringRes;
        }
    }

    public final View getMainSection() {
        return this.mainSection;
    }

    public final void setMainSection(View view) {
        this.mainSection = view;
    }

    public final View getTestsPassedSection() {
        return this.testsPassedSection;
    }

    public final void setTestsPassedSection(View view) {
        this.testsPassedSection = view;
    }

    public final View getTestsFailedSection() {
        return this.testsFailedSection;
    }

    public final void setTestsFailedSection(View view) {
        this.testsFailedSection = view;
    }

    public final TextView getMainSectionText() {
        return this.mainSectionText;
    }

    public final void setMainSectionText(TextView textView) {
        this.mainSectionText = textView;
    }

    public final TextView getMainSectionHeader() {
        return this.mainSectionHeader;
    }

    public final void setMainSectionHeader(TextView textView) {
        this.mainSectionHeader = textView;
    }

    public final TextView getTestsPassedSectionText() {
        return this.testsPassedSectionText;
    }

    public final void setTestsPassedSectionText(TextView textView) {
        this.testsPassedSectionText = textView;
    }

    public final TextView getTestsFailedSectionText() {
        return this.testsFailedSectionText;
    }

    public final void setTestsFailedSectionText(TextView textView) {
        this.testsFailedSectionText = textView;
    }

    public final ContentLoadingProgressBar getTotalProgressBar() {
        return this.totalProgressBar;
    }

    public final void setTotalProgressBar(ContentLoadingProgressBar contentLoadingProgressBar) {
        this.totalProgressBar = contentLoadingProgressBar;
    }

    public final MaterialButton getPlayPauseButton() {
        return this.playPauseButton;
    }

    public final void setPlayPauseButton(MaterialButton materialButton) {
        this.playPauseButton = materialButton;
    }

    static final Unit stateListener$lambda$0(TestState it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Unit.INSTANCE;
    }

    public final Function1<TestState, Unit> getStateListener() {
        return this.stateListener;
    }

    public final void setStateListener(Function1<? super TestState, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "<set-?>");
        this.stateListener = function1;
    }

    static final void _init_$lambda$1(TestView testView, View view) {
        TestState testState;
        int i = WhenMappings.$EnumSwitchMapping$0[testView.state.ordinal()];
        if (i == 1) {
            testState = TestState.Running;
        } else if (i == 2) {
            testState = TestState.Stopped;
        } else {
            if (i != 3) {
                throw new NoWhenBranchMatchedException();
            }
            testState = TestState.Running;
        }
        testView.setState(testState);
    }

    public final void setOnPlayButtonListener(Function1<? super TestState, Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.stateListener = listener;
    }

    public final void setState(TestState newState) {
        Intrinsics.checkNotNullParameter(newState, "newState");
        this.state = newState;
        this.stateListener.invoke(newState);
        MaterialButton materialButton = this.playPauseButton;
        if (materialButton != null) {
            materialButton.setText(newState.getStringRes());
        }
        MaterialButton materialButton2 = this.playPauseButton;
        if (materialButton2 != null) {
            materialButton2.m8217x11712a47(ContextCompat.getDrawable(getContext(), newState.getIcon()));
        }
    }

    /* JADX WARN: Code duplicated, block: B:34:0x0078  */
    public final void setProgress(int passed, int failed, Integer total) {
        boolean z;
        String strValueOf;
        int i = passed + failed;
        TextView textView = this.mainSectionText;
        if (textView != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(i);
            sb.append(" / ");
            if (total == null || (strValueOf = String.valueOf(total.intValue())) == null) {
                strValueOf = "?";
            }
            sb.append(strValueOf);
            textView.setText(sb.toString());
        }
        TextView textView2 = this.testsPassedSectionText;
        if (textView2 != null) {
            textView2.setText(String.valueOf(passed));
        }
        TextView textView3 = this.testsFailedSectionText;
        if (textView3 != null) {
            textView3.setText(String.valueOf(failed));
        }
        ContentLoadingProgressBar contentLoadingProgressBar = this.totalProgressBar;
        if (contentLoadingProgressBar != null) {
            contentLoadingProgressBar.setMax((total != null ? total.intValue() : 0) * 1000);
        }
        ContentLoadingProgressBar contentLoadingProgressBar2 = this.totalProgressBar;
        if (contentLoadingProgressBar2 != null) {
            AppContextUtils.INSTANCE.animateProgressTo(contentLoadingProgressBar2, i * 1000);
        }
        ContentLoadingProgressBar contentLoadingProgressBar3 = this.totalProgressBar;
        if (contentLoadingProgressBar3 != null) {
            ContentLoadingProgressBar contentLoadingProgressBar4 = contentLoadingProgressBar3;
            if (i == 0) {
                z = false;
            } else {
                if ((total != null ? total.intValue() : 0) != 0) {
                    z = true;
                } else {
                    z = false;
                }
            }
            contentLoadingProgressBar4.setVisibility(z ? 0 : 8);
        }
        if (total != null && i == total.intValue()) {
            setState(TestState.Stopped);
        }
    }

    public final void setMainHeader(int header) {
        TextView textView = this.mainSectionHeader;
        if (textView != null) {
            textView.setText(header);
        }
    }

    public final void setOnMainClick(View.OnClickListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        View view = this.mainSection;
        if (view != null) {
            view.setOnClickListener(listener);
        }
    }

    public final void setOnPassedClick(View.OnClickListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        View view = this.testsPassedSection;
        if (view != null) {
            view.setOnClickListener(listener);
        }
    }

    public final void setOnFailedClick(View.OnClickListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        View view = this.testsFailedSection;
        if (view != null) {
            view.setOnClickListener(listener);
        }
    }
}
