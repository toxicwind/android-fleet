package com.lagradost.cloudstream3.ui.player.source_priority;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.common.Scopes;
import com.google.android.material.button.MaterialButton;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.actions.temp.CloudStreamPackage;
import com.lagradost.cloudstream3.databinding.PlayerSelectSourcePriorityBinding;
import com.lagradost.cloudstream3.ui.BaseAdapter;
import com.lagradost.cloudstream3.utils.Qualities;
import com.lagradost.cloudstream3.utils.TextUtilKt;
import com.lagradost.cloudstream3.utils.UIHelper;
import com.lagradost.cloudstream3.utils.UiText;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.enums.EnumEntries;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: SourcePriorityDialog.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f¢\u0006\u0004\b\u000e\u0010\u000fJ\b\u0010\u0014\u001a\u00020\rH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/source_priority/SourcePriorityDialog;", "Landroid/app/Dialog;", "ctx", "Landroid/content/Context;", "themeRes", "", CloudStreamPackage.LINKS_EXTRA, "", "Lcom/lagradost/cloudstream3/ui/player/source_priority/LinkSource;", Scopes.PROFILE, "Lcom/lagradost/cloudstream3/ui/player/source_priority/QualityDataHelper$QualityProfile;", "updatedCallback", "Lkotlin/Function0;", "", "<init>", "(Landroid/content/Context;ILjava/util/List;Lcom/lagradost/cloudstream3/ui/player/source_priority/QualityDataHelper$QualityProfile;Lkotlin/jvm/functions/Function0;)V", "getCtx", "()Landroid/content/Context;", "getLinks", "()Ljava/util/List;", "show", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class SourcePriorityDialog extends Dialog {
    private final Context ctx;
    private final List<LinkSource> links;
    private final QualityDataHelper.QualityProfile profile;
    private final Function0<Unit> updatedCallback;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SourcePriorityDialog(Context ctx, int i, List<LinkSource> links, QualityDataHelper.QualityProfile profile, Function0<Unit> updatedCallback) {
        super(ctx, i);
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        Intrinsics.checkNotNullParameter(links, "links");
        Intrinsics.checkNotNullParameter(profile, "profile");
        Intrinsics.checkNotNullParameter(updatedCallback, "updatedCallback");
        this.ctx = ctx;
        this.links = links;
        this.profile = profile;
        this.updatedCallback = updatedCallback;
    }

    public final Context getCtx() {
        return this.ctx;
    }

    public final List<LinkSource> getLinks() {
        return this.links;
    }

    @Override // android.app.Dialog
    public void show() {
        PlayerSelectSourcePriorityBinding playerSelectSourcePriorityBindingInflate = PlayerSelectSourcePriorityBinding.inflate(LayoutInflater.from(this.ctx), null, false);
        Intrinsics.checkNotNullExpressionValue(playerSelectSourcePriorityBindingInflate, "inflate(...)");
        setContentView(playerSelectSourcePriorityBindingInflate.getRoot());
        UIHelper uIHelper = UIHelper.INSTANCE;
        LinearLayout root = playerSelectSourcePriorityBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        UIHelper.fixSystemBarsPadding$default(uIHelper, root, null, null, false, false, false, false, false, false, TypedValues.PositionType.TYPE_POSITION_TYPE, null);
        final RecyclerView sortSources = playerSelectSourcePriorityBindingInflate.sortSources;
        Intrinsics.checkNotNullExpressionValue(sortSources, "sortSources");
        final RecyclerView sortQualities = playerSelectSourcePriorityBindingInflate.sortQualities;
        Intrinsics.checkNotNullExpressionValue(sortQualities, "sortQualities");
        final EditText profileTextEditable = playerSelectSourcePriorityBindingInflate.profileTextEditable;
        Intrinsics.checkNotNullExpressionValue(profileTextEditable, "profileTextEditable");
        MaterialButton saveBtt = playerSelectSourcePriorityBindingInflate.saveBtt;
        Intrinsics.checkNotNullExpressionValue(saveBtt, "saveBtt");
        MaterialButton closeBtt = playerSelectSourcePriorityBindingInflate.closeBtt;
        Intrinsics.checkNotNullExpressionValue(closeBtt, "closeBtt");
        ImageView helpBtt = playerSelectSourcePriorityBindingInflate.helpBtt;
        Intrinsics.checkNotNullExpressionValue(helpBtt, "helpBtt");
        UiText profileName = QualityDataHelper.INSTANCE.getProfileName(this.profile.getId());
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        profileTextEditable.setText(profileName.asString(context));
        UiText uiTextTxt = TextUtilKt.txt(R.string.profile_number, Integer.valueOf(this.profile.getId()));
        Context context2 = getContext();
        Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
        profileTextEditable.setHint(uiTextTxt.asString(context2));
        PriorityAdapter priorityAdapter = new PriorityAdapter();
        PriorityAdapter priorityAdapter2 = priorityAdapter;
        List<LinkSource> list = this.links;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (LinkSource linkSource : list) {
            arrayList.add(new SourcePriority(null, linkSource.getSource(), QualityDataHelper.INSTANCE.getSourcePriority(this.profile.getId(), linkSource.getSource())));
        }
        HashSet hashSet = new HashSet();
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : arrayList) {
            if (hashSet.add(((SourcePriority) obj).getName())) {
                arrayList2.add(obj);
            }
        }
        BaseAdapter.submitList$default(priorityAdapter2, CollectionsKt.sortedWith(arrayList2, new Comparator() { // from class: com.lagradost.cloudstream3.ui.player.source_priority.SourcePriorityDialog$show$lambda$0$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(Integer.valueOf(-((SourcePriority) t).getPriority()), Integer.valueOf(-((SourcePriority) t2).getPriority()));
            }
        }), null, 2, null);
        sortSources.setAdapter(priorityAdapter);
        PriorityAdapter priorityAdapter3 = new PriorityAdapter();
        PriorityAdapter priorityAdapter4 = priorityAdapter3;
        EnumEntries<Qualities> entries = Qualities.getEntries();
        ArrayList arrayList3 = new ArrayList();
        for (Qualities qualities : entries) {
            String stringByIntFull = Qualities.INSTANCE.getStringByIntFull(qualities.getValue());
            SourcePriority sourcePriority = StringsKt.isBlank(stringByIntFull) ? null : new SourcePriority(qualities, stringByIntFull, QualityDataHelper.INSTANCE.getQualityPriority(this.profile.getId(), qualities));
            if (sourcePriority != null) {
                arrayList3.add(sourcePriority);
            }
        }
        BaseAdapter.submitList$default(priorityAdapter4, CollectionsKt.sortedWith(arrayList3, new Comparator() { // from class: com.lagradost.cloudstream3.ui.player.source_priority.SourcePriorityDialog$show$lambda$1$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(Integer.valueOf(-((SourcePriority) t).getPriority()), Integer.valueOf(-((SourcePriority) t2).getPriority()));
            }
        }), null, 2, null);
        sortQualities.setAdapter(priorityAdapter3);
        saveBtt.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.player.source_priority.SourcePriorityDialog$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SourcePriorityDialog.show$lambda$2(sortQualities, sortSources, profileTextEditable, this, view);
            }
        });
        closeBtt.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.player.source_priority.SourcePriorityDialog$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                UIHelper.INSTANCE.dismissSafe(this.f$0);
            }
        });
        helpBtt.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.player.source_priority.SourcePriorityDialog$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SourcePriorityDialog.show$lambda$4(this.f$0, view);
            }
        });
        super.show();
    }

    static final void show$lambda$2(RecyclerView recyclerView, RecyclerView recyclerView2, EditText editText, SourcePriorityDialog sourcePriorityDialog, View view) {
        Iterable iterableEmptyList;
        Iterable iterableEmptyList2;
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        PriorityAdapter priorityAdapter = adapter instanceof PriorityAdapter ? (PriorityAdapter) adapter : null;
        RecyclerView.Adapter adapter2 = recyclerView2.getAdapter();
        PriorityAdapter priorityAdapter2 = adapter2 instanceof PriorityAdapter ? (PriorityAdapter) adapter2 : null;
        if (priorityAdapter == null || (iterableEmptyList = priorityAdapter.getImmutableCurrentList()) == null) {
            iterableEmptyList = CollectionsKt.emptyList();
        }
        if (priorityAdapter2 == null || (iterableEmptyList2 = priorityAdapter2.getImmutableCurrentList()) == null) {
            iterableEmptyList2 = CollectionsKt.emptyList();
        }
        Iterable<SourcePriority> iterable = iterableEmptyList;
        for (SourcePriority sourcePriority : iterable) {
            QualityDataHelper.INSTANCE.setQualityPriority(sourcePriorityDialog.profile.getId(), (Qualities) sourcePriority.getData(), sourcePriority.getPriority());
        }
        Iterable<SourcePriority> iterable2 = iterableEmptyList2;
        for (SourcePriority sourcePriority2 : iterable2) {
            QualityDataHelper.INSTANCE.setSourcePriority(sourcePriorityDialog.profile.getId(), sourcePriority2.getName(), sourcePriority2.getPriority());
        }
        if (priorityAdapter != null) {
            BaseAdapter.submitList$default(priorityAdapter, CollectionsKt.sortedWith(iterable, new Comparator() { // from class: com.lagradost.cloudstream3.ui.player.source_priority.SourcePriorityDialog$show$lambda$2$$inlined$sortedBy$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    return ComparisonsKt.compareValues(Integer.valueOf(-((SourcePriority) t).getPriority()), Integer.valueOf(-((SourcePriority) t2).getPriority()));
                }
            }), null, 2, null);
        }
        if (priorityAdapter2 != null) {
            BaseAdapter.submitList$default(priorityAdapter2, CollectionsKt.sortedWith(iterable2, new Comparator() { // from class: com.lagradost.cloudstream3.ui.player.source_priority.SourcePriorityDialog$show$lambda$2$$inlined$sortedBy$2
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    return ComparisonsKt.compareValues(Integer.valueOf(-((SourcePriority) t).getPriority()), Integer.valueOf(-((SourcePriority) t2).getPriority()));
                }
            }), null, 2, null);
        }
        String string = editText.getText().toString();
        if (StringsKt.isBlank(string)) {
            QualityDataHelper.INSTANCE.setProfileName(sourcePriorityDialog.profile.getId(), null);
        } else {
            QualityDataHelper.INSTANCE.setProfileName(sourcePriorityDialog.profile.getId(), string);
        }
        sourcePriorityDialog.updatedCallback.invoke();
    }

    static final void show$lambda$4(SourcePriorityDialog sourcePriorityDialog, View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(sourcePriorityDialog.getContext(), R.style.AlertDialogCustom);
        builder.setMessage(R.string.quality_profile_help);
        builder.show();
    }
}
