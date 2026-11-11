package com.lagradost.cloudstream3.ui.player.source_priority;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.button.MaterialButton;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.actions.temp.CloudStreamPackage;
import com.lagradost.cloudstream3.databinding.PlayerQualityProfileDialogBinding;
import com.lagradost.cloudstream3.mvvm.DebugException;
import com.lagradost.cloudstream3.ui.BaseAdapter;
import com.lagradost.cloudstream3.utils.Coroutines;
import com.lagradost.cloudstream3.utils.SingleSelectionHelper;
import com.lagradost.cloudstream3.utils.TextUtilKt;
import com.lagradost.cloudstream3.utils.UIHelper;
import com.lagradost.cloudstream3.utils.UiText;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.enums.EnumEntries;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: QualityProfileDialog.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aBQ\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\u0014\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r\u0018\u00010\u000b\u0012\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0004\b\u0010\u0010\u0011BE\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0004\b\u0010\u0010\u0012B)\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0004\b\u0010\u0010\u0013J\b\u0010\u0019\u001a\u00020\rH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\t\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0018R\u001c\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r\u0018\u00010\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/source_priority/QualityProfileDialog;", "Landroid/app/Dialog;", "activity", "Landroidx/fragment/app/FragmentActivity;", "themeRes", "", CloudStreamPackage.LINKS_EXTRA, "", "Lcom/lagradost/cloudstream3/ui/player/source_priority/LinkSource;", "usedProfile", "profileSelectionCallback", "Lkotlin/Function1;", "Lcom/lagradost/cloudstream3/ui/player/source_priority/QualityDataHelper$QualityProfile;", "", "useProfileSelection", "", "<init>", "(Landroidx/fragment/app/FragmentActivity;ILjava/util/List;Ljava/lang/Integer;Lkotlin/jvm/functions/Function1;Z)V", "(Landroidx/fragment/app/FragmentActivity;ILjava/util/List;ILkotlin/jvm/functions/Function1;)V", "(Landroidx/fragment/app/FragmentActivity;ILjava/util/List;)V", "getActivity", "()Landroidx/fragment/app/FragmentActivity;", "getThemeRes", "()I", "Ljava/lang/Integer;", "show", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class QualityProfileDialog extends Dialog {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final FragmentActivity activity;
    private final List<LinkSource> links;
    private final Function1<QualityDataHelper.QualityProfile, Unit> profileSelectionCallback;
    private final int themeRes;
    private final boolean useProfileSelection;
    private final Integer usedProfile;

    public final FragmentActivity getActivity() {
        return this.activity;
    }

    public final int getThemeRes() {
        return this.themeRes;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private QualityProfileDialog(FragmentActivity fragmentActivity, int i, List<LinkSource> list, Integer num, Function1<? super QualityDataHelper.QualityProfile, Unit> function1, boolean z) {
        super(fragmentActivity, i);
        this.activity = fragmentActivity;
        this.themeRes = i;
        this.links = list;
        this.usedProfile = num;
        this.profileSelectionCallback = function1;
        this.useProfileSelection = z;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public QualityProfileDialog(FragmentActivity activity, int i, List<LinkSource> links, int i2, Function1<? super QualityDataHelper.QualityProfile, Unit> profileSelectionCallback) {
        this(activity, i, links, Integer.valueOf(i2), profileSelectionCallback, true);
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(links, "links");
        Intrinsics.checkNotNullParameter(profileSelectionCallback, "profileSelectionCallback");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public QualityProfileDialog(FragmentActivity activity, int i, List<LinkSource> links) {
        this(activity, i, links, null, null, false);
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(links, "links");
    }

    /* JADX INFO: compiled from: QualityProfileDialog.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0086@¢\u0006\u0002\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/source_priority/QualityProfileDialog$Companion;", "", "<init>", "()V", "getAllDefaultSources", "", "Lcom/lagradost/cloudstream3/ui/player/source_priority/LinkSource;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Object getAllDefaultSources(Continuation<? super List<LinkSource>> continuation) {
            return Coroutines.INSTANCE.ioWork(this, new QualityProfileDialog$Companion$getAllDefaultSources$2(null), continuation);
        }
    }

    @Override // android.app.Dialog
    public void show() {
        final PlayerQualityProfileDialogBinding playerQualityProfileDialogBindingInflate = PlayerQualityProfileDialogBinding.inflate(getLayoutInflater(), null, false);
        Intrinsics.checkNotNullExpressionValue(playerQualityProfileDialogBindingInflate, "inflate(...)");
        setContentView(playerQualityProfileDialogBindingInflate.getRoot());
        UIHelper uIHelper = UIHelper.INSTANCE;
        RelativeLayout root = playerQualityProfileDialogBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        UIHelper.fixSystemBarsPadding$default(uIHelper, root, null, null, false, false, false, false, false, false, TypedValues.PositionType.TYPE_POSITION_TYPE, null);
        playerQualityProfileDialogBindingInflate.profilesRecyclerview.setAdapter(new ProfilesAdapter(this.usedProfile, new Function2() { // from class: com.lagradost.cloudstream3.ui.player.source_priority.QualityProfileDialog$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return QualityProfileDialog.show$lambda$0$0(playerQualityProfileDialogBindingInflate, (Integer) obj, ((Integer) obj2).intValue());
            }
        }));
        show$lambda$0$refreshProfiles(this, playerQualityProfileDialogBindingInflate);
        playerQualityProfileDialogBindingInflate.editBtt.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.player.source_priority.QualityProfileDialog$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                QualityProfileDialog.show$lambda$0$1(playerQualityProfileDialogBindingInflate, this, view);
            }
        });
        playerQualityProfileDialogBindingInflate.setDefaultBtt.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.player.source_priority.QualityProfileDialog$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                QualityProfileDialog.show$lambda$0$2(this.f$0, playerQualityProfileDialogBindingInflate, view);
            }
        });
        MaterialButton cancelBtt = playerQualityProfileDialogBindingInflate.cancelBtt;
        Intrinsics.checkNotNullExpressionValue(cancelBtt, "cancelBtt");
        cancelBtt.setVisibility(this.useProfileSelection ? 0 : 8);
        MaterialButton useBtt = playerQualityProfileDialogBindingInflate.useBtt;
        Intrinsics.checkNotNullExpressionValue(useBtt, "useBtt");
        useBtt.setVisibility(this.useProfileSelection ? 0 : 8);
        MaterialButton applyBtt = playerQualityProfileDialogBindingInflate.applyBtt;
        Intrinsics.checkNotNullExpressionValue(applyBtt, "applyBtt");
        applyBtt.setVisibility(this.useProfileSelection ? 8 : 0);
        if (this.useProfileSelection) {
            playerQualityProfileDialogBindingInflate.cancelBtt.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.player.source_priority.QualityProfileDialog$$ExternalSyntheticLambda6
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    QualityProfileDialog.show$lambda$0$3(this.f$0, view);
                }
            });
            playerQualityProfileDialogBindingInflate.useBtt.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.player.source_priority.QualityProfileDialog$$ExternalSyntheticLambda7
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    QualityProfileDialog.show$lambda$0$4(playerQualityProfileDialogBindingInflate, this, view);
                }
            });
        } else {
            playerQualityProfileDialogBindingInflate.applyBtt.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.player.source_priority.QualityProfileDialog$$ExternalSyntheticLambda8
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    QualityProfileDialog.show$lambda$0$5(this.f$0, view);
                }
            });
        }
        super.show();
    }

    private static final QualityDataHelper.QualityProfile show$lambda$0$getCurrentProfile(PlayerQualityProfileDialogBinding playerQualityProfileDialogBinding) {
        RecyclerView.Adapter adapter = playerQualityProfileDialogBinding.profilesRecyclerview.getAdapter();
        ProfilesAdapter profilesAdapter = adapter instanceof ProfilesAdapter ? (ProfilesAdapter) adapter : null;
        if (profilesAdapter != null) {
            return profilesAdapter.getCurrentProfile();
        }
        return null;
    }

    private static final void show$lambda$0$refreshProfiles(QualityProfileDialog qualityProfileDialog, PlayerQualityProfileDialogBinding playerQualityProfileDialogBinding) {
        if (qualityProfileDialog.usedProfile != null) {
            TextUtilKt.setText(playerQualityProfileDialogBinding.currentlySelectedProfileText, QualityDataHelper.INSTANCE.getProfileName(qualityProfileDialog.usedProfile.intValue()));
        }
        RecyclerView.Adapter adapter = playerQualityProfileDialogBinding.profilesRecyclerview.getAdapter();
        ProfilesAdapter profilesAdapter = adapter instanceof ProfilesAdapter ? (ProfilesAdapter) adapter : null;
        if (profilesAdapter != null) {
            BaseAdapter.submitList$default(profilesAdapter, QualityDataHelper.INSTANCE.getProfiles(), null, 2, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit show$lambda$0$0(PlayerQualityProfileDialogBinding playerQualityProfileDialogBinding, Integer num, int i) {
        RecyclerView.Adapter adapter;
        RecyclerView.Adapter adapter2 = playerQualityProfileDialogBinding.profilesRecyclerview.getAdapter();
        if (adapter2 != null) {
            adapter2.notifyItemChanged(i);
        }
        playerQualityProfileDialogBinding.selectedItemHolder.setAlpha(1.0f);
        if (num != null && (adapter = playerQualityProfileDialogBinding.profilesRecyclerview.getAdapter()) != null) {
            adapter.notifyItemChanged(num.intValue());
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void show$lambda$0$1(final PlayerQualityProfileDialogBinding playerQualityProfileDialogBinding, final QualityProfileDialog qualityProfileDialog, View view) {
        QualityDataHelper.QualityProfile qualityProfileShow$lambda$0$getCurrentProfile = show$lambda$0$getCurrentProfile(playerQualityProfileDialogBinding);
        if (qualityProfileShow$lambda$0$getCurrentProfile != null) {
            Context context = qualityProfileDialog.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
            new SourcePriorityDialog(context, qualityProfileDialog.themeRes, qualityProfileDialog.links, qualityProfileShow$lambda$0$getCurrentProfile, new Function0() { // from class: com.lagradost.cloudstream3.ui.player.source_priority.QualityProfileDialog$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return QualityProfileDialog.show$lambda$0$1$0$0(this.f$0, playerQualityProfileDialogBinding);
                }
            }).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit show$lambda$0$1$0$0(QualityProfileDialog qualityProfileDialog, PlayerQualityProfileDialogBinding playerQualityProfileDialogBinding) {
        show$lambda$0$refreshProfiles(qualityProfileDialog, playerQualityProfileDialogBinding);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void show$lambda$0$2(final QualityProfileDialog qualityProfileDialog, final PlayerQualityProfileDialogBinding playerQualityProfileDialogBinding, View view) {
        final QualityDataHelper.QualityProfile qualityProfileShow$lambda$0$getCurrentProfile = show$lambda$0$getCurrentProfile(playerQualityProfileDialogBinding);
        if (qualityProfileShow$lambda$0$getCurrentProfile == null) {
            return;
        }
        EnumEntries<QualityDataHelper.QualityProfileType> entries = QualityDataHelper.QualityProfileType.getEntries();
        ArrayList arrayList = new ArrayList();
        for (QualityDataHelper.QualityProfileType qualityProfileType : entries) {
            if (qualityProfileType != QualityDataHelper.QualityProfileType.None) {
                arrayList.add(qualityProfileType);
            }
        }
        final ArrayList arrayList2 = arrayList;
        ArrayList arrayList3 = arrayList2;
        ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList3, 10));
        Iterator it = arrayList3.iterator();
        while (it.hasNext()) {
            UiText uiTextTxt = TextUtilKt.txt(((QualityDataHelper.QualityProfileType) it.next()).getStringRes(), new Object[0]);
            Context context = qualityProfileDialog.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
            arrayList4.add(uiTextTxt.asString(context));
        }
        ArrayList arrayList5 = arrayList4;
        ArrayList arrayList6 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList3, 10));
        int i = 0;
        for (Object obj : arrayList3) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            arrayList6.add(TuplesKt.to(Integer.valueOf(i), (QualityDataHelper.QualityProfileType) obj));
            i = i2;
        }
        ArrayList arrayList7 = new ArrayList();
        for (Object obj2 : arrayList6) {
            if (qualityProfileShow$lambda$0$getCurrentProfile.getTypes().contains(((Pair) obj2).getSecond())) {
                arrayList7.add(obj2);
            }
        }
        ArrayList arrayList8 = arrayList7;
        ArrayList arrayList9 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList8, 10));
        Iterator it2 = arrayList8.iterator();
        while (it2.hasNext()) {
            arrayList9.add(Integer.valueOf(((Number) ((Pair) it2.next()).getFirst()).intValue()));
        }
        ArrayList arrayList10 = arrayList9;
        SingleSelectionHelper singleSelectionHelper = SingleSelectionHelper.INSTANCE;
        FragmentActivity fragmentActivity = qualityProfileDialog.activity;
        UiText uiTextTxt2 = TextUtilKt.txt(R.string.set_default, new Object[0]);
        Context context2 = qualityProfileDialog.getContext();
        Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
        singleSelectionHelper.showMultiDialog(fragmentActivity, arrayList5, arrayList10, uiTextTxt2.asString(context2), new Function0() { // from class: com.lagradost.cloudstream3.ui.player.source_priority.QualityProfileDialog$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Unit.INSTANCE;
            }
        }, new Function1() { // from class: com.lagradost.cloudstream3.ui.player.source_priority.QualityProfileDialog$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj3) {
                return QualityProfileDialog.show$lambda$0$2$6(arrayList2, qualityProfileShow$lambda$0$getCurrentProfile, qualityProfileDialog, playerQualityProfileDialogBinding, (List) obj3);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit show$lambda$0$2$6(List list, QualityDataHelper.QualityProfile qualityProfile, QualityProfileDialog qualityProfileDialog, PlayerQualityProfileDialogBinding playerQualityProfileDialogBinding, List index) throws DebugException {
        Intrinsics.checkNotNullParameter(index, "index");
        ArrayList<QualityDataHelper.QualityProfileType> arrayList = new ArrayList();
        Iterator it = index.iterator();
        while (it.hasNext()) {
            QualityDataHelper.QualityProfileType qualityProfileType = (QualityDataHelper.QualityProfileType) CollectionsKt.getOrNull(list, ((Number) it.next()).intValue());
            if (qualityProfileType != null) {
                arrayList.add(qualityProfileType);
            }
        }
        for (QualityDataHelper.QualityProfileType qualityProfileType2 : arrayList) {
            if (qualityProfileType2.getUnique()) {
                List<QualityDataHelper.QualityProfile> profiles = QualityDataHelper.INSTANCE.getProfiles();
                ArrayList arrayList2 = new ArrayList();
                for (Object obj : profiles) {
                    if (((QualityDataHelper.QualityProfile) obj).getTypes().contains(qualityProfileType2)) {
                        arrayList2.add(obj);
                    }
                }
                Iterator it2 = arrayList2.iterator();
                while (it2.hasNext()) {
                    QualityDataHelper.INSTANCE.removeQualityProfileType(((QualityDataHelper.QualityProfile) it2.next()).getId(), qualityProfileType2);
                }
            }
            QualityDataHelper.INSTANCE.addQualityProfileType(qualityProfile.getId(), qualityProfileType2);
        }
        show$lambda$0$refreshProfiles(qualityProfileDialog, playerQualityProfileDialogBinding);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void show$lambda$0$3(QualityProfileDialog qualityProfileDialog, View view) {
        UIHelper.INSTANCE.dismissSafe(qualityProfileDialog);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void show$lambda$0$4(PlayerQualityProfileDialogBinding playerQualityProfileDialogBinding, QualityProfileDialog qualityProfileDialog, View view) {
        QualityDataHelper.QualityProfile qualityProfileShow$lambda$0$getCurrentProfile = show$lambda$0$getCurrentProfile(playerQualityProfileDialogBinding);
        if (qualityProfileShow$lambda$0$getCurrentProfile != null) {
            Function1<QualityDataHelper.QualityProfile, Unit> function1 = qualityProfileDialog.profileSelectionCallback;
            if (function1 != null) {
                function1.invoke(qualityProfileShow$lambda$0$getCurrentProfile);
            }
            UIHelper.INSTANCE.dismissSafe(qualityProfileDialog);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void show$lambda$0$5(QualityProfileDialog qualityProfileDialog, View view) {
        UIHelper.INSTANCE.dismissSafe(qualityProfileDialog);
    }
}
