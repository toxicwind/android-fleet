package com.lagradost.cloudstream3.ui.player.source_priority;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.palette.graphics.Palette;
import androidx.viewbinding.ViewBinding;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.databinding.PlayerQualityProfileItemBinding;
import com.lagradost.cloudstream3.ui.BaseAdapter;
import com.lagradost.cloudstream3.ui.BaseDiffCallback;
import com.lagradost.cloudstream3.ui.NoStateAdapter;
import com.lagradost.cloudstream3.ui.ViewHolderState;
import com.lagradost.cloudstream3.utils.ImageLoader;
import com.lagradost.cloudstream3.utils.ImageUtilKt;
import com.lagradost.cloudstream3.utils.TextUtilKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ProfilesAdapter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 \u001f2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001fBK\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u00128\u0010\u0005\u001a4\u0012\u0015\u0012\u0013\u0018\u00010\u0004¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u0006¢\u0006\u0004\b\f\u0010\rJ\u0016\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u0016\u0010\u0018\u001a\u00020\u000b2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014H\u0016J&\u0010\u001a\u001a\u00020\u000b2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010\u001b\u001a\u00020\u00022\u0006\u0010\u001c\u001a\u00020\u0004H\u0016J\b\u0010\u001e\u001a\u0004\u0018\u00010\u0002R\u0015\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000fRC\u0010\u0005\u001a4\u0012\u0015\u0012\u0013\u0018\u00010\u0004¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0012\u0010\u001d\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0010¨\u0006 "}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/source_priority/ProfilesAdapter;", "Lcom/lagradost/cloudstream3/ui/NoStateAdapter;", "Lcom/lagradost/cloudstream3/ui/player/source_priority/QualityDataHelper$QualityProfile;", "usedProfile", "", "clickCallback", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "oldIndex", "newIndex", "", "<init>", "(Ljava/lang/Integer;Lkotlin/jvm/functions/Function2;)V", "getUsedProfile", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getClickCallback", "()Lkotlin/jvm/functions/Function2;", "onCreateContent", "Lcom/lagradost/cloudstream3/ui/ViewHolderState;", "", "parent", "Landroid/view/ViewGroup;", "onClearView", "holder", "onBindContent", "item", "position", "currentItem", "getCurrentProfile", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class ProfilesAdapter extends NoStateAdapter<QualityDataHelper.QualityProfile> {
    private static final Integer[] art = {Integer.valueOf(R.drawable.profile_bg_teal), Integer.valueOf(R.drawable.profile_bg_blue), Integer.valueOf(R.drawable.profile_bg_dark_blue), Integer.valueOf(R.drawable.profile_bg_purple), Integer.valueOf(R.drawable.profile_bg_pink), Integer.valueOf(R.drawable.profile_bg_red), Integer.valueOf(R.drawable.profile_bg_orange)};
    private final Function2<Integer, Integer, Unit> clickCallback;
    private Integer currentItem;
    private final Integer usedProfile;

    public final Integer getUsedProfile() {
        return this.usedProfile;
    }

    public final Function2<Integer, Integer, Unit> getClickCallback() {
        return this.clickCallback;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public ProfilesAdapter(Integer num, Function2<? super Integer, ? super Integer, Unit> clickCallback) {
        super(new BaseDiffCallback(new Function2() { // from class: com.lagradost.cloudstream3.ui.player.source_priority.ProfilesAdapter$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(ProfilesAdapter._init_$lambda$0((QualityDataHelper.QualityProfile) obj, (QualityDataHelper.QualityProfile) obj2));
            }
        }, null, 2, null));
        Intrinsics.checkNotNullParameter(clickCallback, "clickCallback");
        this.usedProfile = num;
        this.clickCallback = clickCallback;
    }

    static final boolean _init_$lambda$0(QualityDataHelper.QualityProfile a, QualityDataHelper.QualityProfile b) {
        Intrinsics.checkNotNullParameter(a, "a");
        Intrinsics.checkNotNullParameter(b, "b");
        return a.getId() == b.getId();
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public ViewHolderState<Object> onCreateContent(ViewGroup parent) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        PlayerQualityProfileItemBinding playerQualityProfileItemBindingInflate = PlayerQualityProfileItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(playerQualityProfileItemBindingInflate, "inflate(...)");
        return new ViewHolderState<>(playerQualityProfileItemBindingInflate);
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public void onClearView(ViewHolderState<Object> holder) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        ViewBinding view = holder.getView();
        if (view instanceof PlayerQualityProfileItemBinding) {
            BaseAdapter.INSTANCE.clearImage(((PlayerQualityProfileItemBinding) view).profileImageBackground);
        }
    }

    @Override // com.lagradost.cloudstream3.ui.BaseAdapter
    public void onBindContent(ViewHolderState<Object> holder, QualityDataHelper.QualityProfile item, final int position) {
        Bitmap bitmapDrawableToBitmap;
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(item, "item");
        ViewBinding view = holder.getView();
        PlayerQualityProfileItemBinding playerQualityProfileItemBinding = view instanceof PlayerQualityProfileItemBinding ? (PlayerQualityProfileItemBinding) view : null;
        if (playerQualityProfileItemBinding == null) {
            return;
        }
        TextView profileText = playerQualityProfileItemBinding.profileText;
        Intrinsics.checkNotNullExpressionValue(profileText, "profileText");
        ImageView profileImageBackground = playerQualityProfileItemBinding.profileImageBackground;
        Intrinsics.checkNotNullExpressionValue(profileImageBackground, "profileImageBackground");
        final TextView textIsWifi = playerQualityProfileItemBinding.textIsWifi;
        Intrinsics.checkNotNullExpressionValue(textIsWifi, "textIsWifi");
        final TextView textIsMobileData = playerQualityProfileItemBinding.textIsMobileData;
        Intrinsics.checkNotNullExpressionValue(textIsMobileData, "textIsMobileData");
        final TextView textIsDownloadData = playerQualityProfileItemBinding.textIsDownloadData;
        Intrinsics.checkNotNullExpressionValue(textIsDownloadData, "textIsDownloadData");
        View outline = playerQualityProfileItemBinding.outline;
        Intrinsics.checkNotNullExpressionValue(outline, "outline");
        CardView cardView = playerQualityProfileItemBinding.cardView;
        Intrinsics.checkNotNullExpressionValue(cardView, "cardView");
        CardView cardView2 = cardView;
        final View itemView = holder.itemView;
        Intrinsics.checkNotNullExpressionValue(itemView, "itemView");
        TextUtilKt.setText(profileText, item.getName());
        textIsMobileData.setVisibility(item.getTypes().contains(QualityDataHelper.QualityProfileType.Data) ? 0 : 8);
        textIsWifi.setVisibility(item.getTypes().contains(QualityDataHelper.QualityProfileType.WiFi) ? 0 : 8);
        textIsDownloadData.setVisibility(item.getTypes().contains(QualityDataHelper.QualityProfileType.Download) ? 0 : 8);
        Integer num = this.currentItem;
        outline.setVisibility(num != null && num.intValue() == position ? 0 : 8);
        Integer[] numArr = art;
        Integer num2 = numArr[position % numArr.length];
        int iIntValue = num2.intValue();
        ImageLoader.loadImage$default(ImageLoader.INSTANCE, profileImageBackground, num2, (Function1) null, 2, (Object) null);
        Drawable drawable = ContextCompat.getDrawable(itemView.getContext(), iIntValue);
        if (drawable != null && (bitmapDrawableToBitmap = ImageUtilKt.drawableToBitmap(drawable)) != null) {
            Palette.from(bitmapDrawableToBitmap).generate(new Palette.PaletteAsyncListener() { // from class: com.lagradost.cloudstream3.ui.player.source_priority.ProfilesAdapter$$ExternalSyntheticLambda0
                @Override // androidx.palette.graphics.Palette.PaletteAsyncListener
                public final void onGenerated(Palette palette) {
                    ProfilesAdapter.onBindContent$lambda$0(itemView, textIsWifi, textIsMobileData, textIsDownloadData, palette);
                }
            });
        }
        int id = item.getId();
        Integer num3 = this.usedProfile;
        profileText.setTypeface(null, (num3 != null && id == num3.intValue()) ? 1 : 0);
        cardView2.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.player.source_priority.ProfilesAdapter$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                ProfilesAdapter.onBindContent$setCurrentItem(this.f$0, position);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindContent$setCurrentItem(ProfilesAdapter profilesAdapter, int i) {
        Integer num = profilesAdapter.currentItem;
        if (num != null && num.intValue() == i) {
            return;
        }
        profilesAdapter.currentItem = Integer.valueOf(i);
        profilesAdapter.clickCallback.invoke(num, Integer.valueOf(i));
    }

    static final void onBindContent$lambda$0(View view, TextView textView, TextView textView2, TextView textView3, Palette palette) {
        Integer numValueOf = palette != null ? Integer.valueOf(palette.getDarkVibrantColor(ContextCompat.getColor(view.getContext(), R.color.dubColorBg))) : null;
        if (numValueOf != null) {
            textView.setBackgroundTintList(ColorStateList.valueOf(numValueOf.intValue()));
            textView2.setBackgroundTintList(ColorStateList.valueOf(numValueOf.intValue()));
            textView3.setBackgroundTintList(ColorStateList.valueOf(numValueOf.intValue()));
        }
    }

    public final QualityDataHelper.QualityProfile getCurrentProfile() {
        Integer num = this.currentItem;
        if (num == null) {
            return null;
        }
        return (QualityDataHelper.QualityProfile) CollectionsKt.getOrNull(getImmutableCurrentList(), num.intValue());
    }
}
