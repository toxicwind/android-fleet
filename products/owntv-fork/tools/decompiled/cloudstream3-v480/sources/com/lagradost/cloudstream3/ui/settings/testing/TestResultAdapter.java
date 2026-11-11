package com.lagradost.cloudstream3.ui.settings.testing;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.viewbinding.ViewBinding;
import com.lagradost.cloudstream3.MainAPI;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.databinding.ProviderTestItemBinding;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.ui.BaseAdapter;
import com.lagradost.cloudstream3.ui.BaseDiffCallback;
import com.lagradost.cloudstream3.ui.NoStateAdapter;
import com.lagradost.cloudstream3.ui.ViewHolderState;
import com.lagradost.cloudstream3.utils.Coroutines;
import com.lagradost.cloudstream3.utils.SubtitleHelper;
import com.lagradost.cloudstream3.utils.TestingUtils;
import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: TestResultAdapter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u0000 \u00132\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00020\u0001:\u0001\u0013B\u0007¢\u0006\u0004\b\u0005\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0016J\u0016\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\r\u001a\u00020\u000eH\u0016J2\u0010\u000f\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00022\u0006\u0010\u0011\u001a\u00020\u0012H\u0016¨\u0006\u0014"}, d2 = {"Lcom/lagradost/cloudstream3/ui/settings/testing/TestResultAdapter;", "Lcom/lagradost/cloudstream3/ui/NoStateAdapter;", "Lkotlin/Pair;", "Lcom/lagradost/cloudstream3/MainAPI;", "Lcom/lagradost/cloudstream3/utils/TestingUtils$TestResultProvider;", "<init>", "()V", "onClearView", "", "holder", "Lcom/lagradost/cloudstream3/ui/ViewHolderState;", "", "onCreateContent", "parent", "Landroid/view/ViewGroup;", "onBindContent", "item", "position", "", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class TestResultAdapter extends NoStateAdapter<Pair<? extends MainAPI, ? extends TestingUtils.TestResultProvider>> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindContent$lambda$5$0(DialogInterface dialogInterface, int i) {
    }

    public TestResultAdapter() {
        super(new BaseDiffCallback(new Function2() { // from class: com.lagradost.cloudstream3.ui.settings.testing.TestResultAdapter$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(TestResultAdapter._init_$lambda$0((Pair) obj, (Pair) obj2));
            }
        }, new Function2() { // from class: com.lagradost.cloudstream3.ui.settings.testing.TestResultAdapter$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(TestResultAdapter._init_$lambda$1((Pair) obj, (Pair) obj2));
            }
        }));
    }

    static final boolean _init_$lambda$0(Pair a, Pair b) {
        Intrinsics.checkNotNullParameter(a, "a");
        Intrinsics.checkNotNullParameter(b, "b");
        return Intrinsics.areEqual(((MainAPI) a.getFirst()).getName(), ((MainAPI) b.getFirst()).getName()) && Intrinsics.areEqual(((MainAPI) a.getFirst()).getMainUrl(), ((MainAPI) b.getFirst()).getMainUrl());
    }

    static final boolean _init_$lambda$1(Pair a, Pair b) {
        Intrinsics.checkNotNullParameter(a, "a");
        Intrinsics.checkNotNullParameter(b, "b");
        return Intrinsics.areEqual(a, b);
    }

    /* JADX INFO: compiled from: TestResultAdapter.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u0004\u0018\u00010\u0005*\u00020\u0005H\u0002¨\u0006\u0006"}, d2 = {"Lcom/lagradost/cloudstream3/ui/settings/testing/TestResultAdapter$Companion;", "", "<init>", "()V", "lastLine", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final String lastLine(String str) {
            String strPrevious;
            List<String> listLines = StringsKt.lines(str);
            ListIterator<String> listIterator = listLines.listIterator(listLines.size());
            while (listIterator.hasPrevious()) {
                strPrevious = listIterator.previous();
                if (!StringsKt.isBlank(strPrevious)) {
                    return strPrevious;
                }
            }
            strPrevious = null;
            return strPrevious;
        }
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public void onClearView(ViewHolderState<Object> holder) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        ViewBinding view = holder.getView();
        ProviderTestItemBinding providerTestItemBinding = view instanceof ProviderTestItemBinding ? (ProviderTestItemBinding) view : null;
        if (providerTestItemBinding == null) {
            return;
        }
        BaseAdapter.INSTANCE.clearImage(providerTestItemBinding.actionButton);
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public ViewHolderState<Object> onCreateContent(ViewGroup parent) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        ProviderTestItemBinding providerTestItemBindingInflate = ProviderTestItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(providerTestItemBindingInflate, "inflate(...)");
        return new ViewHolderState<>(providerTestItemBindingInflate);
    }

    /* JADX WARN: Code duplicated, block: B:45:0x0159  */
    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public void onBindContent(ViewHolderState<Object> holder, Pair<? extends MainAPI, TestingUtils.TestResultProvider> item, int position) {
        Pair pair;
        String str;
        String str2;
        String strLastLine;
        String allMessages;
        String stackTracePretty;
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(item, "item");
        ViewBinding view = holder.getView();
        String str3 = null;
        ProviderTestItemBinding providerTestItemBinding = view instanceof ProviderTestItemBinding ? (ProviderTestItemBinding) view : null;
        if (providerTestItemBinding == null) {
            return;
        }
        final MainAPI mainAPIComponent1 = item.component1();
        TestingUtils.TestResultProvider testResultProviderComponent2 = item.component2();
        View itemView = holder.itemView;
        Intrinsics.checkNotNullExpressionValue(itemView, "itemView");
        TextView langIcon = providerTestItemBinding.langIcon;
        Intrinsics.checkNotNullExpressionValue(langIcon, "langIcon");
        TextView mainText = providerTestItemBinding.mainText;
        Intrinsics.checkNotNullExpressionValue(mainText, "mainText");
        TextView passedFailedMarker = providerTestItemBinding.passedFailedMarker;
        Intrinsics.checkNotNullExpressionValue(passedFailedMarker, "passedFailedMarker");
        TextView failDescription = providerTestItemBinding.failDescription;
        Intrinsics.checkNotNullExpressionValue(failDescription, "failDescription");
        ImageView actionButton = providerTestItemBinding.actionButton;
        Intrinsics.checkNotNullExpressionValue(actionButton, "actionButton");
        langIcon.setText(SubtitleHelper.INSTANCE.getFlagFromIso(mainAPIComponent1.getLang()));
        mainText.setText(mainAPIComponent1.getName());
        if (testResultProviderComponent2.getSuccess()) {
            List<TestingUtils.Logger.Message> log = testResultProviderComponent2.getLog();
            if ((log instanceof Collection) && log.isEmpty()) {
                pair = TuplesKt.to(Integer.valueOf(R.string.test_passed), Integer.valueOf(R.color.colorTestPass));
                break;
            }
            Iterator<T> it = log.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (((TestingUtils.Logger.Message) it.next()).getLevel() == TestingUtils.Logger.LogLevel.Warning) {
                        pair = TuplesKt.to(Integer.valueOf(R.string.test_warning), Integer.valueOf(R.color.colorTestWarning));
                        break;
                    }
                } else {
                    pair = TuplesKt.to(Integer.valueOf(R.string.test_passed), Integer.valueOf(R.color.colorTestPass));
                    break;
                }
            }
        } else {
            pair = TuplesKt.to(Integer.valueOf(R.string.test_failed), Integer.valueOf(R.color.colorTestFail));
        }
        int iIntValue = ((Number) pair.component1()).intValue();
        int iIntValue2 = ((Number) pair.component2()).intValue();
        passedFailedMarker.setText(iIntValue);
        passedFailedMarker.setTextColor(ContextCompat.getColor(itemView.getContext(), iIntValue2));
        Throwable exception = testResultProviderComponent2.getException();
        if (exception == null || (stackTracePretty = ArchComponentExtKt.getStackTracePretty(exception, false)) == null) {
            str = null;
        } else {
            String str4 = stackTracePretty;
            if (StringsKt.isBlank(str4)) {
                str4 = null;
            }
            str = str4;
        }
        Throwable exception2 = testResultProviderComponent2.getException();
        if (exception2 != null && (allMessages = ArchComponentExtKt.getAllMessages(exception2)) != null) {
            String str5 = allMessages;
            str3 = StringsKt.isBlank(str5) ? null : str5;
        }
        String strJoinToString$default = CollectionsKt.joinToString$default(testResultProviderComponent2.getLog(), "\n", null, null, 0, null, null, 62, null);
        StringBuilder sb = new StringBuilder();
        sb.append(strJoinToString$default);
        String str6 = "";
        if (str3 != null) {
            str2 = "\n\nError: " + str3;
            if (str2 == null) {
                str2 = "";
            }
        } else {
            str2 = "";
        }
        sb.append(str2);
        if (str != null) {
            String str7 = "\n\n" + str;
            if (str7 != null) {
                str6 = str7;
            }
        }
        sb.append(str6);
        final String string = sb.toString();
        failDescription.setText((str3 == null || (strLastLine = INSTANCE.lastLine(str3)) == null) ? INSTANCE.lastLine(strJoinToString$default) : strLastLine);
        actionButton.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.testing.TestResultAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                TestResultAdapter.onBindContent$lambda$5(string, mainAPIComponent1, this, view2);
            }
        });
    }

    static final void onBindContent$lambda$5(String str, MainAPI mainAPI, final TestResultAdapter testResultAdapter, View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext(), R.style.AlertDialogCustom);
        builder.setMessage(str).setTitle(R.string.test_log).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.testing.TestResultAdapter$$ExternalSyntheticLambda3
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                TestResultAdapter.onBindContent$lambda$5$0(dialogInterface, i);
            }
        });
        String sourcePlugin = mainAPI.getSourcePlugin();
        if (sourcePlugin != null) {
            final File file = new File(sourcePlugin);
            if (file.exists()) {
                builder.setNegativeButton(R.string.delete_plugin, new DialogInterface.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.settings.testing.TestResultAdapter$$ExternalSyntheticLambda4
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i) {
                        TestResultAdapter.onBindContent$lambda$5$1$0(this.f$0, file, dialogInterface, i);
                    }
                });
            }
        }
        builder.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindContent$lambda$5$1$0(TestResultAdapter testResultAdapter, File file, DialogInterface dialogInterface, int i) {
        Coroutines.INSTANCE.ioSafe(testResultAdapter, new TestResultAdapter$onBindContent$2$2$1$1(file, null));
    }
}
