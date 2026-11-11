package com.lagradost.cloudstream3.ui.search;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.palette.graphics.Palette;
import androidx.preference.PreferenceManager;
import coil3.Image;
import coil3.request.ImageRequest;
import com.lagradost.cloudstream3.AnimeSearchResponse;
import com.lagradost.cloudstream3.DubStatus;
import com.lagradost.cloudstream3.LiveSearchResponse;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.Score;
import com.lagradost.cloudstream3.SearchQuality;
import com.lagradost.cloudstream3.SearchResponse;
import com.lagradost.cloudstream3.TvType;
import com.lagradost.cloudstream3.syncproviders.SyncAPI;
import com.lagradost.cloudstream3.ui.settings.Globals;
import com.lagradost.cloudstream3.utils.AppContextUtils;
import com.lagradost.cloudstream3.utils.DataStoreHelper;
import com.lagradost.cloudstream3.utils.ImageLoader;
import com.lagradost.cloudstream3.utils.ImageUtilKt;
import com.lagradost.cloudstream3.utils.SubtitleHelper;
import com.lagradost.cloudstream3.utils.UIHelper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: SearchResultBuilder.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bJi\u0010\f\u001a\u00020\t2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\t0\u000e2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00132\u0016\b\u0002\u0010\u0018\u001a\u0010\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\t\u0018\u00010\u000eH\u0007¢\u0006\u0002\u0010\u001aR\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/lagradost/cloudstream3/ui/search/SearchResultBuilder;", "", "<init>", "()V", "showCache", "", "", "", "updateCache", "", "context", "Landroid/content/Context;", "bind", "clickCallback", "Lkotlin/Function1;", "Lcom/lagradost/cloudstream3/ui/search/SearchClickCallback;", "card", "Lcom/lagradost/cloudstream3/SearchResponse;", "position", "", "itemView", "Landroid/view/View;", "nextFocusUp", "nextFocusDown", "colorCallback", "Landroidx/palette/graphics/Palette;", "(Lkotlin/jvm/functions/Function1;Lcom/lagradost/cloudstream3/SearchResponse;ILandroid/view/View;Ljava/lang/Integer;Ljava/lang/Integer;Lkotlin/jvm/functions/Function1;)V", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class SearchResultBuilder {
    public static final SearchResultBuilder INSTANCE = new SearchResultBuilder();
    private static final Map<String, Boolean> showCache = new LinkedHashMap();

    /* JADX INFO: compiled from: SearchResultBuilder.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[SearchQuality.values().length];
            try {
                iArr[SearchQuality.BlueRay.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[SearchQuality.Cam.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[SearchQuality.CamRip.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[SearchQuality.DVD.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[SearchQuality.HD.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[SearchQuality.HQ.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[SearchQuality.HdCam.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[SearchQuality.Telecine.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr[SearchQuality.Telesync.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr[SearchQuality.WorkPrint.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                iArr[SearchQuality.SD.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                iArr[SearchQuality.FourK.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                iArr[SearchQuality.UHD.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                iArr[SearchQuality.SDR.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                iArr[SearchQuality.HDR.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                iArr[SearchQuality.WebRip.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private SearchResultBuilder() {
    }

    public final void updateCache(Context context) {
        if (context == null) {
            return;
        }
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String[] stringArray = context.getResources().getStringArray(R.array.poster_ui_options_values);
        Intrinsics.checkNotNullExpressionValue(stringArray, "getStringArray(...)");
        for (String str : stringArray) {
            Map<String, Boolean> map = showCache;
            Boolean bool = map.get(str);
            map.put(str, Boolean.valueOf(defaultSharedPreferences.getBoolean(str, bool != null ? bool.booleanValue() : true)));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void bind$default(SearchResultBuilder searchResultBuilder, Function1 function1, SearchResponse searchResponse, int i, View view, Integer num, Integer num2, Function1 function2, int i2, Object obj) {
        if ((i2 & 16) != 0) {
            num = null;
        }
        if ((i2 & 32) != 0) {
            num2 = null;
        }
        if ((i2 & 64) != 0) {
            function2 = null;
        }
        searchResultBuilder.bind(function1, searchResponse, i, view, num, num2, function2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v22, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r10v2, types: [android.widget.ImageView] */
    /* JADX WARN: Type inference failed for: r11v23 */
    /* JADX WARN: Type inference failed for: r11v7, types: [android.view.View] */
    /* JADX WARN: Type inference failed for: r11v8 */
    /* JADX WARN: Type inference failed for: r11v9, types: [int] */
    /* JADX WARN: Type inference failed for: r1v15, types: [android.view.View] */
    /* JADX WARN: Type inference failed for: r1v40, types: [android.view.View] */
    /* JADX WARN: Type inference failed for: r2v11, types: [android.view.View] */
    /* JADX WARN: Type inference failed for: r4v14 */
    /* JADX WARN: Type inference failed for: r4v15, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r4v18 */
    /* JADX WARN: Type inference failed for: r5v2, types: [androidx.cardview.widget.CardView] */
    /* JADX WARN: Type inference failed for: r9v4, types: [android.widget.ProgressBar] */
    /* JADX WARN: Type inference failed for: r9v43 */
    /* JADX WARN: Type inference failed for: r9v44, types: [int] */
    /* JADX WARN: Type inference failed for: r9v45 */
    /* JADX WARN: Type inference failed for: r9v46 */
    /* JADX WARN: Type inference failed for: r9v47, types: [int] */
    /* JADX WARN: Type inference failed for: r9v48 */
    public final void bind(final Function1<? super SearchClickCallback, Unit> clickCallback, final SearchResponse card, final int position, final View itemView, Integer nextFocusUp, Integer nextFocusDown, Function1<? super Palette, Unit> colorCallback) {
        Integer numValueOf;
        int i;
        ?? r4;
        String str;
        String str2;
        Context context;
        String shortSeasonText;
        Context context2;
        Context context3;
        Context context4;
        Context context5;
        Context context6;
        Context context7;
        Intrinsics.checkNotNullParameter(clickCallback, "clickCallback");
        Intrinsics.checkNotNullParameter(card, "card");
        Intrinsics.checkNotNullParameter(itemView, "itemView");
        View viewFindViewById = itemView.findViewById(R.id.imageView);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(...)");
        ImageView imageView = (ImageView) viewFindViewById;
        TextView textView = (TextView) itemView.findViewById(R.id.imageText);
        TextView textView2 = (TextView) itemView.findViewById(R.id.text_is_dub);
        TextView textView3 = (TextView) itemView.findViewById(R.id.text_is_sub);
        TextView textView4 = (TextView) itemView.findViewById(R.id.text_flag);
        TextView textView5 = (TextView) itemView.findViewById(R.id.text_rating);
        TextView textView6 = (TextView) itemView.findViewById(R.id.text_quality);
        View viewFindViewById2 = itemView.findViewById(R.id.title_shadow);
        View viewFindViewById3 = itemView.findViewById(R.id.background_card);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "findViewById(...)");
        ?? r5 = (CardView) viewFindViewById3;
        ?? r9 = (ProgressBar) itemView.findViewById(R.id.watchProgress);
        ?? r10 = (ImageView) itemView.findViewById(R.id.search_item_download_play);
        TextView textView7 = (TextView) itemView.findViewById(R.id.episode_text);
        if (r9 != 0) {
            ((View) r9).setVisibility(8);
            Unit unit = Unit.INSTANCE;
        }
        if (r10 != 0) {
            ((View) r10).setVisibility(8);
            Unit unit2 = Unit.INSTANCE;
        }
        if (textView2 != null) {
            textView2.setVisibility(8);
            Unit unit3 = Unit.INSTANCE;
        }
        if (textView3 != null) {
            textView3.setVisibility(8);
            Unit unit4 = Unit.INSTANCE;
        }
        if (textView4 != null) {
            textView4.setVisibility(8);
            Unit unit5 = Unit.INSTANCE;
        }
        if (textView5 != null) {
            textView5.setVisibility(8);
            Unit unit6 = Unit.INSTANCE;
        }
        if (textView7 != null) {
            textView7.setVisibility(8);
            Unit unit7 = Unit.INSTANCE;
        }
        Map<String, Boolean> map = showCache;
        Boolean bool = map.get((textView2 == null || (context7 = textView2.getContext()) == null) ? null : context7.getString(R.string.show_sub_key));
        boolean zBooleanValue = bool != null ? bool.booleanValue() : false;
        Boolean bool2 = map.get((textView2 == null || (context6 = textView2.getContext()) == null) ? null : context6.getString(R.string.show_dub_key));
        boolean zBooleanValue2 = bool2 != null ? bool2.booleanValue() : false;
        Boolean bool3 = map.get((textView == null || (context5 = textView.getContext()) == null) ? null : context5.getString(R.string.show_title_key));
        boolean zBooleanValue3 = bool3 != null ? bool3.booleanValue() : false;
        Boolean bool4 = map.get((textView == null || (context4 = textView.getContext()) == null) ? null : context4.getString(R.string.show_episode_text_key));
        boolean zBooleanValue4 = bool4 != null ? bool4.booleanValue() : false;
        Boolean bool5 = map.get((textView6 == null || (context3 = textView6.getContext()) == null) ? null : context3.getString(R.string.show_hd_key));
        boolean zBooleanValue5 = bool5 != null ? bool5.booleanValue() : false;
        Boolean bool6 = map.get((textView6 == null || (context2 = textView6.getContext()) == null) ? null : context2.getString(R.string.show_rating_key));
        boolean zBooleanValue6 = bool6 != null ? bool6.booleanValue() : false;
        if (card instanceof SyncAPI.LibraryItem) {
            Score personalRating = ((SyncAPI.LibraryItem) card).getPersonalRating();
            String stringNull$default = personalRating != null ? Score.toStringNull$default(personalRating, 0.1d, 10, 1, false, (char) 0, 24, null) : null;
            boolean z = stringNull$default == null || StringsKt.isBlank(stringNull$default);
            if (textView5 != null) {
                textView5.setVisibility(!z ? 0 : 8);
                Unit unit8 = Unit.INSTANCE;
            }
            if (!z && textView5 != null) {
                textView5.setText(stringNull$default);
                Unit unit9 = Unit.INSTANCE;
            }
        } else if (zBooleanValue6) {
            Score score = card.getScore();
            String stringNull$default2 = score != null ? Score.toStringNull$default(score, 0.1d, 10, 1, false, (char) 0, 24, null) : null;
            boolean z2 = stringNull$default2 == null || StringsKt.isBlank(stringNull$default2);
            if (textView5 != null) {
                textView5.setVisibility(!z2 ? 0 : 8);
                Unit unit10 = Unit.INSTANCE;
            }
            if (!z2 && textView5 != null) {
                textView5.setText(stringNull$default2);
                Unit unit11 = Unit.INSTANCE;
            }
        }
        if (viewFindViewById2 != null) {
            viewFindViewById2.setVisibility(zBooleanValue3 ? 0 : 8);
            Unit unit12 = Unit.INSTANCE;
        }
        SearchQuality quality = card.getQuality();
        switch (quality == null ? -1 : WhenMappings.$EnumSwitchMapping$0[quality.ordinal()]) {
            case -1:
                numValueOf = null;
                break;
            case 0:
            default:
                throw new NoWhenBranchMatchedException();
            case 1:
                numValueOf = Integer.valueOf(R.string.quality_blueray);
                break;
            case 2:
                numValueOf = Integer.valueOf(R.string.quality_cam);
                break;
            case 3:
                numValueOf = Integer.valueOf(R.string.quality_cam_rip);
                break;
            case 4:
                numValueOf = Integer.valueOf(R.string.quality_dvd);
                break;
            case 5:
                numValueOf = Integer.valueOf(R.string.quality_hd);
                break;
            case 6:
                numValueOf = Integer.valueOf(R.string.quality_hq);
                break;
            case 7:
                numValueOf = Integer.valueOf(R.string.quality_cam_hd);
                break;
            case 8:
                numValueOf = Integer.valueOf(R.string.quality_tc);
                break;
            case 9:
                numValueOf = Integer.valueOf(R.string.quality_ts);
                break;
            case 10:
                numValueOf = Integer.valueOf(R.string.quality_workprint);
                break;
            case 11:
                numValueOf = Integer.valueOf(R.string.quality_sd);
                break;
            case 12:
                numValueOf = Integer.valueOf(R.string.quality_4k);
                break;
            case 13:
                numValueOf = Integer.valueOf(R.string.quality_uhd);
                break;
            case 14:
                numValueOf = Integer.valueOf(R.string.quality_sdr);
                break;
            case 15:
                numValueOf = Integer.valueOf(R.string.quality_hdr);
                break;
            case 16:
                numValueOf = Integer.valueOf(R.string.quality_webrip);
                break;
        }
        if (numValueOf != null) {
            int iIntValue = numValueOf.intValue();
            if (textView6 != null) {
                textView6.setText(iIntValue);
                Unit unit13 = Unit.INSTANCE;
            }
            if (textView6 != null) {
                textView6.setVisibility(zBooleanValue5 ? 0 : 8);
                Unit unit14 = Unit.INSTANCE;
            }
            Unit unit15 = Unit.INSTANCE;
            i = 8;
        } else {
            if (textView6 != null) {
                i = 8;
                textView6.setVisibility(8);
                Unit unit16 = Unit.INSTANCE;
            } else {
                i = 8;
            }
            Unit unit17 = Unit.INSTANCE;
        }
        if (textView != null) {
            textView.setText(card.getName());
            Unit unit18 = Unit.INSTANCE;
        }
        if (textView != null) {
            textView.setVisibility(zBooleanValue3 != 0 ? 0 : i);
            Unit unit19 = Unit.INSTANCE;
        }
        imageView.setVisibility(0);
        String posterUrl = card.getPosterUrl();
        if (posterUrl != null && posterUrl.length() != 0) {
            ImageLoader.INSTANCE.loadImage(imageView, card.getPosterUrl(), card.getPosterHeaders(), new Function1() { // from class: com.lagradost.cloudstream3.ui.search.SearchResultBuilder$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return SearchResultBuilder.bind$lambda$2(itemView, (ImageRequest.Builder) obj);
                }
            });
            r4 = 0;
        } else {
            r4 = 0;
            ImageLoader.loadImage$default(ImageLoader.INSTANCE, imageView, Integer.valueOf(R.drawable.default_cover), (Function1) null, 2, (Object) null);
        }
        r5.setFocusable(r4);
        r5.setFocusableInTouchMode(r4);
        if (!Globals.INSTANCE.isLayout(2)) {
            r5.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.search.SearchResultBuilder$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SearchResultBuilder.bind$click(clickCallback, card, position, view);
                }
            });
            r5.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.lagradost.cloudstream3.ui.search.SearchResultBuilder$$ExternalSyntheticLambda2
                @Override // android.view.View.OnLongClickListener
                public final boolean onLongClick(View view) {
                    return SearchResultBuilder.bind$lambda$4(clickCallback, position, card, view);
                }
            });
        }
        itemView.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.search.SearchResultBuilder$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SearchResultBuilder.bind$click(clickCallback, card, position, view);
            }
        });
        if (nextFocusUp != null) {
            itemView.setNextFocusUpId(nextFocusUp.intValue());
        }
        if (nextFocusDown != null) {
            itemView.setNextFocusDownId(nextFocusDown.intValue());
        }
        if (Globals.INSTANCE.isLayout(2)) {
            itemView.setFocusableInTouchMode(true);
            itemView.setFocusable(true);
        }
        itemView.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.lagradost.cloudstream3.ui.search.SearchResultBuilder$$ExternalSyntheticLambda4
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                return SearchResultBuilder.bind$lambda$6(clickCallback, position, card, view);
            }
        });
        itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.lagradost.cloudstream3.ui.search.SearchResultBuilder$$ExternalSyntheticLambda5
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z3) {
                SearchResultBuilder.bind$focus(clickCallback, position, card, view, z3);
            }
        });
        if (card instanceof LiveSearchResponse) {
            String flagFromIso = SubtitleHelper.INSTANCE.getFlagFromIso(((LiveSearchResponse) card).getLang());
            if (flagFromIso != null && textView4 != null) {
                textView4.setVisibility(r4);
                textView4.setText(flagFromIso);
            }
        } else if (card instanceof DataStoreHelper.ResumeWatchingResult) {
            DataStoreHelper.ResumeWatchingResult resumeWatchingResult = (DataStoreHelper.ResumeWatchingResult) card;
            DataStoreHelper.PosDur watchPos = resumeWatchingResult.getWatchPos();
            DataStoreHelper.PosDur posDurFixVisual = watchPos != null ? DataStoreHelper.INSTANCE.fixVisual(watchPos) : null;
            if (posDurFixVisual != null) {
                if (r9 != 0) {
                    r9.setMax((int) (posDurFixVisual.getDuration() / 1000));
                    Unit unit20 = Unit.INSTANCE;
                }
                if (r9 != 0) {
                    r9.setProgress((int) (posDurFixVisual.getPosition() / 1000));
                    Unit unit21 = Unit.INSTANCE;
                }
                if (r9 != 0) {
                    r9.setVisibility(r4);
                    Unit unit22 = Unit.INSTANCE;
                }
            }
            if (r10 != 0) {
                r10.setVisibility(r4);
                Unit unit23 = Unit.INSTANCE;
            }
            TvType type = resumeWatchingResult.getType();
            if (type != null && !MainAPIKt.isMovieType(type) && zBooleanValue4 && textView7 != null && (context = textView7.getContext()) != null && (shortSeasonText = AppContextUtils.INSTANCE.getShortSeasonText(context, resumeWatchingResult.getEpisode(), resumeWatchingResult.getSeason())) != null) {
                textView7.setText(shortSeasonText);
                textView7.setVisibility(r4);
                Unit unit24 = Unit.INSTANCE;
                Unit unit25 = Unit.INSTANCE;
            }
        } else if (card instanceof AnimeSearchResponse) {
            AnimeSearchResponse animeSearchResponse = (AnimeSearchResponse) card;
            Set<DubStatus> dubStatus = animeSearchResponse.getDubStatus();
            Set<DubStatus> set = dubStatus;
            if (set != null && !set.isEmpty()) {
                if (dubStatus.contains(DubStatus.Dubbed) && textView2 != null) {
                    textView2.setVisibility(zBooleanValue2 ? r4 : i);
                    Unit unit26 = Unit.INSTANCE;
                }
                if (dubStatus.contains(DubStatus.Subbed) && textView3 != null) {
                    textView3.setVisibility(zBooleanValue ? r4 : i);
                    Unit unit27 = Unit.INSTANCE;
                }
            }
            Integer num = animeSearchResponse.getEpisodes().get(DubStatus.Dubbed);
            Integer num2 = animeSearchResponse.getEpisodes().get(DubStatus.Subbed);
            if (textView2 != null) {
                String string = textView2.getContext().getString(R.string.app_dubbed_text);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                if (num != null && num.intValue() > 0) {
                    String string2 = textView2.getContext().getString(R.string.app_dub_sub_episode_text_format);
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                    Object[] objArr = new Object[2];
                    objArr[r4] = string;
                    objArr[1] = num;
                    String str3 = String.format(string2, Arrays.copyOf(objArr, 2));
                    Intrinsics.checkNotNullExpressionValue(str3, "format(...)");
                    str2 = str3;
                } else {
                    str2 = string;
                }
                textView2.setText(str2);
            }
            if (textView3 != null) {
                String string3 = textView3.getContext().getString(R.string.app_subbed_text);
                Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
                if (num2 != null && num2.intValue() > 0) {
                    String string4 = textView3.getContext().getString(R.string.app_dub_sub_episode_text_format);
                    Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
                    Object[] objArr2 = new Object[2];
                    objArr2[r4] = string3;
                    objArr2[1] = num2;
                    String str4 = String.format(string4, Arrays.copyOf(objArr2, 2));
                    Intrinsics.checkNotNullExpressionValue(str4, "format(...)");
                    str = str4;
                } else {
                    str = string3;
                }
                textView3.setText(str);
            }
        }
        ?? arrayList = new ArrayList();
        TextView[] textViewArr = new TextView[3];
        textViewArr[r4] = textView2;
        textViewArr[1] = textView3;
        textViewArr[2] = textView5;
        for (?? r11 = r4; r11 < 3; r11++) {
            TextView textView8 = textViewArr[r11];
            if (textView8 != null && textView8.getVisibility() == 0) {
                arrayList.add(textView8);
            }
        }
        if (arrayList.size() == 1) {
            ((TextView) arrayList.get(r4)).setBackgroundResource(R.drawable.bg_color_both);
        } else if (arrayList.size() > 1) {
            ((TextView) arrayList.get(r4)).setBackgroundResource(R.drawable.bg_color_top);
            int size = arrayList.size();
            for (int i2 = 1; i2 < size; i2++) {
                ((TextView) arrayList.get(i2)).setBackgroundResource(R.drawable.bg_color_center);
            }
            ((TextView) arrayList.get(arrayList.size() - 1)).setBackgroundResource(R.drawable.bg_color_bottom);
        }
        if (textView2 != null) {
            UIHelper uIHelper = UIHelper.INSTANCE;
            Context context8 = textView2.getContext();
            Intrinsics.checkNotNullExpressionValue(context8, "getContext(...)");
            textView2.setBackgroundTintList(ColorStateList.valueOf(uIHelper.colorFromAttribute(context8, R.attr.textColor)));
        }
    }

    static final Unit bind$lambda$2(final View view, ImageRequest.Builder loadImage) {
        Intrinsics.checkNotNullParameter(loadImage, "$this$loadImage");
        loadImage.error(new Function1() { // from class: com.lagradost.cloudstream3.ui.search.SearchResultBuilder$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SearchResultBuilder.bind$lambda$2$0(view, (ImageRequest) obj);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Image bind$lambda$2$0(View view, ImageRequest it) {
        Intrinsics.checkNotNullParameter(it, "it");
        Context context = view.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        return ImageUtilKt.getImageFromDrawable(context, R.drawable.default_cover);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bind$click(Function1<? super SearchClickCallback, Unit> function1, SearchResponse searchResponse, int i, View view) {
        int i2 = searchResponse instanceof DataStoreHelper.ResumeWatchingResult ? 2 : 0;
        if (view == null) {
            return;
        }
        function1.invoke(new SearchClickCallback(i2, view, i, searchResponse));
    }

    private static final void bind$longClick(Function1<? super SearchClickCallback, Unit> function1, int i, SearchResponse searchResponse, View view) {
        if (view == null) {
            return;
        }
        function1.invoke(new SearchClickCallback(1, view, i, searchResponse));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bind$focus(Function1<? super SearchClickCallback, Unit> function1, int i, SearchResponse searchResponse, View view, boolean z) {
        if (!z || view == null) {
            return;
        }
        function1.invoke(new SearchClickCallback(4, view, i, searchResponse));
    }

    static final boolean bind$lambda$4(Function1 function1, int i, SearchResponse searchResponse, View view) {
        bind$longClick(function1, i, searchResponse, view);
        return true;
    }

    static final boolean bind$lambda$6(Function1 function1, int i, SearchResponse searchResponse, View view) {
        bind$longClick(function1, i, searchResponse, view);
        return true;
    }
}
