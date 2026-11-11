package com.lagradost.cloudstream3.utils;

import android.content.Context;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.NotificationCompat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.APIHolder;
import com.lagradost.cloudstream3.CloudStreamApp;
import com.lagradost.cloudstream3.CommonActivity;
import com.lagradost.cloudstream3.DubStatus;
import com.lagradost.cloudstream3.EpisodeResponse;
import com.lagradost.cloudstream3.MainAPI;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.MainActivity;
import com.lagradost.cloudstream3.NextAiring$$ExternalSyntheticBackport0;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.Score;
import com.lagradost.cloudstream3.SearchQuality;
import com.lagradost.cloudstream3.SearchResponse;
import com.lagradost.cloudstream3.TvType;
import com.lagradost.cloudstream3.actions.temp.CloudStreamPackage;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.syncproviders.AccountManager;
import com.lagradost.cloudstream3.syncproviders.SyncAPI;
import com.lagradost.cloudstream3.ui.WatchType;
import com.lagradost.cloudstream3.ui.library.ListSorting;
import com.lagradost.cloudstream3.ui.player.ExtractorUri;
import com.lagradost.cloudstream3.ui.result.EpisodeSortType;
import com.lagradost.cloudstream3.ui.result.ResultEpisode;
import com.lagradost.cloudstream3.ui.result.VideoWatchState;
import com.lagradost.cloudstream3.utils.downloader.DownloadObjects;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.internal.EnumSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.ReferenceArraySerializer;
import kotlinx.serialization.internal.StringSerializer;
import kotlinx.serialization.modules.SerializersModule;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: DataStoreHelper.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000¶\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u0015\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0018\bÆ\u0002\u0018\u00002\u00020\u0001:\u000eÄ\u0001Å\u0001Æ\u0001Ç\u0001È\u0001É\u0001Ê\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u000bH\u0002J\u001c\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00160\u000b2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0002J\u000e\u0010`\u001a\u00020a2\u0006\u0010b\u001a\u00020MJ\u000e\u0010c\u001a\u00020M2\u0006\u0010d\u001a\u00020eJ\u0014\u0010O\u001a\b\u0012\u0004\u0012\u00020M0\u000b2\u0006\u0010d\u001a\u00020eJ\b\u0010Z\u001a\u0004\u0018\u00010MJ\n\u0010f\u001a\u00020g*\u00020gJ\n\u0010h\u001a\u00020i*\u00020\u0006J\u000e\u0010j\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u000bJ\u0006\u0010k\u001a\u00020aJ\u0015\u0010l\u001a\u00020a2\b\u0010m\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010nJ\u000e\u0010o\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u000bJ\u0010\u0010p\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u000bH\u0002J\u0006\u0010q\u001a\u00020aJI\u0010r\u001a\u00020a2\b\u0010s\u001a\u0004\u0018\u00010\u00062\b\u0010t\u001a\u0004\u0018\u00010\u00062\b\u0010u\u001a\u0004\u0018\u00010\u00062\b\u0010v\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010w\u001a\u00020x2\n\b\u0002\u0010y\u001a\u0004\u0018\u00010z¢\u0006\u0002\u0010{J\u0017\u0010|\u001a\u00020a2\b\u0010s\u001a\u0004\u0018\u00010\u0006H\u0002¢\u0006\u0002\u0010nJ\u0015\u0010}\u001a\u00020a2\b\u0010s\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010nJ\u0018\u0010~\u001a\u0004\u0018\u00010\u007f2\b\u0010m\u001a\u0004\u0018\u00010\u0006¢\u0006\u0003\u0010\u0080\u0001J\u001b\u0010\u0081\u0001\u001a\u0004\u0018\u00010\u007f2\b\u0010m\u001a\u0004\u0018\u00010\u0006H\u0002¢\u0006\u0003\u0010\u0080\u0001J \u0010\u0082\u0001\u001a\u00020a2\b\u0010m\u001a\u0004\u0018\u00010\u00062\u0007\u0010\u0015\u001a\u00030\u0083\u0001¢\u0006\u0003\u0010\u0084\u0001J\u001a\u0010\u0085\u0001\u001a\u0005\u0018\u00010\u0083\u00012\b\u0010m\u001a\u0004\u0018\u00010\u0006¢\u0006\u0003\u0010\u0086\u0001J\u000e\u0010\u0087\u0001\u001a\t\u0012\u0005\u0012\u00030\u0083\u00010\u000bJ\u000e\u0010\u0088\u0001\u001a\t\u0012\u0005\u0012\u00030\u0089\u00010\u000bJ\u0016\u0010\u008a\u0001\u001a\u00020a2\b\u0010m\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010nJ.\u0010\u008b\u0001\u001a\u00020a2\b\u0010m\u001a\u0004\u0018\u00010\u00062\t\u0010\u0015\u001a\u0005\u0018\u00010\u0089\u00012\n\u0010\u008c\u0001\u001a\u0005\u0018\u00010\u008d\u0001¢\u0006\u0003\u0010\u008e\u0001J \u0010\u008f\u0001\u001a\u00020a2\b\u0010m\u001a\u0004\u0018\u00010\u00062\u0007\u0010\u0015\u001a\u00030\u0089\u0001¢\u0006\u0003\u0010\u0090\u0001J\u001a\u0010\u0091\u0001\u001a\u0005\u0018\u00010\u0089\u00012\b\u0010m\u001a\u0004\u0018\u00010\u0006¢\u0006\u0003\u0010\u0092\u0001J\u000e\u0010\u0093\u0001\u001a\t\u0012\u0005\u0012\u00030\u0094\u00010\u000bJ\u0016\u0010\u0095\u0001\u001a\u00020a2\b\u0010m\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010nJ \u0010\u0096\u0001\u001a\u00020a2\b\u0010m\u001a\u0004\u0018\u00010\u00062\u0007\u0010\u0015\u001a\u00030\u0094\u0001¢\u0006\u0003\u0010\u0097\u0001J\u001a\u0010\u0098\u0001\u001a\u0005\u0018\u00010\u0094\u00012\b\u0010m\u001a\u0004\u0018\u00010\u0006¢\u0006\u0003\u0010\u0099\u0001J)\u0010\u009a\u0001\u001a\u00020a2\b\u0010m\u001a\u0004\u0018\u00010\u00062\u0007\u0010\u009b\u0001\u001a\u00020z2\u0007\u0010\u009c\u0001\u001a\u00020z¢\u0006\u0003\u0010\u009d\u0001J?\u0010\u009e\u0001\u001a\u00020a2\b\u0010m\u001a\u0004\u0018\u00010\u00062\u0007\u0010\u009f\u0001\u001a\u00020z2\u0007\u0010 \u0001\u001a\u00020z2\t\u0010¡\u0001\u001a\u0004\u0018\u00010\u00012\t\u0010¢\u0001\u001a\u0004\u0018\u00010\u0001¢\u0006\u0003\u0010£\u0001J\u0019\u0010¤\u0001\u001a\u0004\u0018\u00010g2\b\u0010m\u001a\u0004\u0018\u00010\u0006¢\u0006\u0003\u0010¥\u0001J\u001a\u0010¦\u0001\u001a\u0005\u0018\u00010§\u00012\b\u0010m\u001a\u0004\u0018\u00010\u0006¢\u0006\u0003\u0010¨\u0001J!\u0010©\u0001\u001a\u00020a2\b\u0010m\u001a\u0004\u0018\u00010\u00062\b\u0010ª\u0001\u001a\u00030§\u0001¢\u0006\u0003\u0010«\u0001J\u0012\u0010¬\u0001\u001a\u0005\u0018\u00010\u00ad\u00012\u0006\u0010m\u001a\u00020\u0006J\u0019\u0010®\u0001\u001a\u00020a2\u0006\u0010m\u001a\u00020\u00062\b\u0010¯\u0001\u001a\u00030\u00ad\u0001J \u0010°\u0001\u001a\u00020a2\b\u0010m\u001a\u0004\u0018\u00010\u00062\u0007\u0010¯\u0001\u001a\u00020\u0006¢\u0006\u0003\u0010±\u0001J\u0010\u0010²\u0001\u001a\u00030³\u00012\u0006\u0010m\u001a\u00020\u0006J\u0017\u0010´\u0001\u001a\u0004\u0018\u00010\u00062\u0006\u0010m\u001a\u00020\u0006¢\u0006\u0003\u0010µ\u0001J\u001f\u0010¶\u0001\u001a\u00020a2\u0006\u0010m\u001a\u00020\u00062\b\u0010\u0018\u001a\u0004\u0018\u00010\u0006¢\u0006\u0003\u0010·\u0001J\u0017\u0010¸\u0001\u001a\u0004\u0018\u00010\u00062\u0006\u0010m\u001a\u00020\u0006¢\u0006\u0003\u0010µ\u0001J\u001f\u0010¹\u0001\u001a\u00020a2\u0006\u0010m\u001a\u00020\u00062\b\u0010\u0018\u001a\u0004\u0018\u00010\u0006¢\u0006\u0003\u0010·\u0001J!\u0010º\u0001\u001a\u00020a2\u0006\u0010m\u001a\u00020\u00062\u0007\u0010»\u0001\u001a\u00020\f2\u0007\u0010¼\u0001\u001a\u00020\fJ&\u0010½\u0001\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000b2\u0006\u0010m\u001a\u00020\u00062\r\u0010¾\u0001\u001a\b\u0012\u0004\u0012\u00020\f0\u000bR\u0019\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR7\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R0\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\f0\u000b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u001a\u0010\u000f\"\u0004\b\u001b\u0010\u0011R7\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u001f\u0010\u0013\u001a\u0004\b\u001d\u0010\u000f\"\u0004\b\u001e\u0010\u0011R0\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00160\u000b2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00160\u000b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b!\u0010\u000f\"\u0004\b\"\u0010\u0011R7\u0010#\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b&\u0010\u0013\u001a\u0004\b$\u0010\u000f\"\u0004\b%\u0010\u0011R0\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00160\u000b2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00160\u000b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b(\u0010\u000f\"\u0004\b)\u0010\u0011R+\u0010+\u001a\u00020*2\u0006\u0010\n\u001a\u00020*8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b0\u0010\u0013\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R+\u00102\u001a\u0002012\u0006\u0010\n\u001a\u0002018F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b7\u0010\u0013\u001a\u0004\b3\u00104\"\u0004\b5\u00106R+\u00108\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u00068F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b=\u0010\u0013\u001a\u0004\b9\u0010:\"\u0004\b;\u0010<R+\u0010>\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u00068F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bA\u0010\u0013\u001a\u0004\b?\u0010:\"\u0004\b@\u0010<R+\u0010B\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u00068B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\bE\u0010\u0013\u001a\u0004\bC\u0010:\"\u0004\bD\u0010<R$\u0010G\u001a\u00020F2\u0006\u0010\u0018\u001a\u00020F8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bH\u0010I\"\u0004\bJ\u0010KR\u000e\u0010L\u001a\u00020\fX\u0086T¢\u0006\u0002\n\u0000R7\u0010N\u001a\b\u0012\u0004\u0012\u00020M0\u00052\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020M0\u00058F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bS\u0010T\u001a\u0004\bO\u0010P\"\u0004\bQ\u0010RR+\u0010U\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u00068F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bX\u0010T\u001a\u0004\bV\u0010:\"\u0004\bW\u0010<R\u0011\u0010Y\u001a\u00020\f8F¢\u0006\u0006\u001a\u0004\bZ\u0010[R(\u0010\\\u001a\u0004\u0018\u00010\f2\b\u0010\u0018\u001a\u0004\u0018\u00010\f8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b]\u0010[\"\u0004\b^\u0010_R5\u0010¿\u0001\u001a\b\u0012\u0004\u0012\u00020\f0\u00052\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\f0\u00058F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\bÀ\u0001\u0010Á\u0001\"\u0006\bÂ\u0001\u0010Ã\u0001¨\u0006Ë\u0001"}, d2 = {"Lcom/lagradost/cloudstream3/utils/DataStoreHelper;", "", "<init>", "()V", "profileImages", "", "", "getProfileImages", "()[Ljava/lang/Integer;", "[Ljava/lang/Integer;", "<set-?>", "", "", "searchPreferenceProvidersStrings", "getSearchPreferenceProvidersStrings", "()Ljava/util/List;", "setSearchPreferenceProvidersStrings", "(Ljava/util/List;)V", "searchPreferenceProvidersStrings$delegate", "Lcom/lagradost/cloudstream3/utils/UserPreferenceDelegate;", "serializeTv", "data", "Lcom/lagradost/cloudstream3/TvType;", "deserializeTv", ES6Iterator.VALUE_PROPERTY, "searchPreferenceProviders", "getSearchPreferenceProviders", "setSearchPreferenceProviders", "searchPreferenceTagsStrings", "getSearchPreferenceTagsStrings", "setSearchPreferenceTagsStrings", "searchPreferenceTagsStrings$delegate", "searchPreferenceTags", "getSearchPreferenceTags", "setSearchPreferenceTags", "homePreferenceStrings", "getHomePreferenceStrings", "setHomePreferenceStrings", "homePreferenceStrings$delegate", "homePreference", "getHomePreference", "setHomePreference", "", "homeBookmarkedList", "getHomeBookmarkedList", "()[I", "setHomeBookmarkedList", "([I)V", "homeBookmarkedList$delegate", "", "playBackSpeed", "getPlayBackSpeed", "()F", "setPlayBackSpeed", "(F)V", "playBackSpeed$delegate", "resizeMode", "getResizeMode", "()I", "setResizeMode", "(I)V", "resizeMode$delegate", "librarySortingMode", "getLibrarySortingMode", "setLibrarySortingMode", "librarySortingMode$delegate", "_resultsSortingMode", "get_resultsSortingMode", "set_resultsSortingMode", "_resultsSortingMode$delegate", "Lcom/lagradost/cloudstream3/ui/result/EpisodeSortType;", "resultsSortingMode", "getResultsSortingMode", "()Lcom/lagradost/cloudstream3/ui/result/EpisodeSortType;", "setResultsSortingMode", "(Lcom/lagradost/cloudstream3/ui/result/EpisodeSortType;)V", "TAG", "Lcom/lagradost/cloudstream3/utils/DataStoreHelper$Account;", "accounts", "getAccounts", "()[Lcom/lagradost/cloudstream3/utils/DataStoreHelper$Account;", "setAccounts", "([Lcom/lagradost/cloudstream3/utils/DataStoreHelper$Account;)V", "accounts$delegate", "Lcom/lagradost/cloudstream3/utils/PreferenceDelegate;", "selectedKeyIndex", "getSelectedKeyIndex", "setSelectedKeyIndex", "selectedKeyIndex$delegate", "currentAccount", "getCurrentAccount", "()Ljava/lang/String;", "currentHomePage", "getCurrentHomePage", "setCurrentHomePage", "(Ljava/lang/String;)V", "setAccount", "", "account", "getDefaultAccount", "context", "Landroid/content/Context;", "fixVisual", "Lcom/lagradost/cloudstream3/utils/DataStoreHelper$PosDur;", "toYear", "Ljava/util/Date;", "getAllWatchStateIds", "deleteAllResumeStateIds", "deleteBookmarkedData", "id", "(Ljava/lang/Integer;)V", "getAllResumeStateIds", "getAllResumeStateIdsOld", "migrateResumeWatching", "setLastWatched", "parentId", "episodeId", "episode", "season", "isFromDownload", "", "updateTime", "", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;ZLjava/lang/Long;)V", "removeLastWatchedOld", "removeLastWatched", "getLastWatched", "Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$ResumeWatching;", "(Ljava/lang/Integer;)Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$ResumeWatching;", "getLastWatchedOld", "setBookmarkedData", "Lcom/lagradost/cloudstream3/utils/DataStoreHelper$BookmarkedData;", "(Ljava/lang/Integer;Lcom/lagradost/cloudstream3/utils/DataStoreHelper$BookmarkedData;)V", "getBookmarkedData", "(Ljava/lang/Integer;)Lcom/lagradost/cloudstream3/utils/DataStoreHelper$BookmarkedData;", "getAllBookmarkedData", "getAllSubscriptions", "Lcom/lagradost/cloudstream3/utils/DataStoreHelper$SubscribedData;", "removeSubscribedData", "updateSubscribedData", "episodeResponse", "Lcom/lagradost/cloudstream3/EpisodeResponse;", "(Ljava/lang/Integer;Lcom/lagradost/cloudstream3/utils/DataStoreHelper$SubscribedData;Lcom/lagradost/cloudstream3/EpisodeResponse;)V", "setSubscribedData", "(Ljava/lang/Integer;Lcom/lagradost/cloudstream3/utils/DataStoreHelper$SubscribedData;)V", "getSubscribedData", "(Ljava/lang/Integer;)Lcom/lagradost/cloudstream3/utils/DataStoreHelper$SubscribedData;", "getAllFavorites", "Lcom/lagradost/cloudstream3/utils/DataStoreHelper$FavoritesData;", "removeFavoritesData", "setFavoritesData", "(Ljava/lang/Integer;Lcom/lagradost/cloudstream3/utils/DataStoreHelper$FavoritesData;)V", "getFavoritesData", "(Ljava/lang/Integer;)Lcom/lagradost/cloudstream3/utils/DataStoreHelper$FavoritesData;", "setViewPos", CloudStreamPackage.POSITION_EXTRA, CloudStreamPackage.DURATION_EXTRA, "(Ljava/lang/Integer;JJ)V", "setViewPosAndResume", "position", TypedValues.TransitionType.S_DURATION, "currentEpisode", "nextEpisode", "(Ljava/lang/Integer;JJLjava/lang/Object;Ljava/lang/Object;)V", "getViewPos", "(Ljava/lang/Integer;)Lcom/lagradost/cloudstream3/utils/DataStoreHelper$PosDur;", "getVideoWatchState", "Lcom/lagradost/cloudstream3/ui/result/VideoWatchState;", "(Ljava/lang/Integer;)Lcom/lagradost/cloudstream3/ui/result/VideoWatchState;", "setVideoWatchState", "watchState", "(Ljava/lang/Integer;Lcom/lagradost/cloudstream3/ui/result/VideoWatchState;)V", "getDub", "Lcom/lagradost/cloudstream3/DubStatus;", "setDub", NotificationCompat.CATEGORY_STATUS, "setResultWatchState", "(Ljava/lang/Integer;I)V", "getResultWatchState", "Lcom/lagradost/cloudstream3/ui/WatchType;", "getResultSeason", "(I)Ljava/lang/Integer;", "setResultSeason", "(ILjava/lang/Integer;)V", "getResultEpisode", "setResultEpisode", "addSync", "idPrefix", ImagesContract.URL, "getSync", "idPrefixes", "pinnedProviders", "getPinnedProviders", "()[Ljava/lang/String;", "setPinnedProviders", "([Ljava/lang/String;)V", "Account", "PosDur", "LibrarySearchResponse", "SubscribedData", "BookmarkedData", "FavoritesData", "ResumeWatchingResult", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class DataStoreHelper {
    public static final String TAG = "data_store_helper";

    /* JADX INFO: renamed from: _resultsSortingMode$delegate, reason: from kotlin metadata */
    private static final UserPreferenceDelegate _resultsSortingMode;

    /* JADX INFO: renamed from: accounts$delegate, reason: from kotlin metadata */
    private static final PreferenceDelegate accounts;

    /* JADX INFO: renamed from: homeBookmarkedList$delegate, reason: from kotlin metadata */
    private static final UserPreferenceDelegate homeBookmarkedList;

    /* JADX INFO: renamed from: homePreferenceStrings$delegate, reason: from kotlin metadata */
    private static final UserPreferenceDelegate homePreferenceStrings;

    /* JADX INFO: renamed from: librarySortingMode$delegate, reason: from kotlin metadata */
    private static final UserPreferenceDelegate librarySortingMode;

    /* JADX INFO: renamed from: playBackSpeed$delegate, reason: from kotlin metadata */
    private static final UserPreferenceDelegate playBackSpeed;

    /* JADX INFO: renamed from: resizeMode$delegate, reason: from kotlin metadata */
    private static final UserPreferenceDelegate resizeMode;

    /* JADX INFO: renamed from: searchPreferenceTagsStrings$delegate, reason: from kotlin metadata */
    private static final UserPreferenceDelegate searchPreferenceTagsStrings;

    /* JADX INFO: renamed from: selectedKeyIndex$delegate, reason: from kotlin metadata */
    private static final PreferenceDelegate selectedKeyIndex;
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new MutablePropertyReference1Impl(DataStoreHelper.class, "searchPreferenceProvidersStrings", "getSearchPreferenceProvidersStrings()Ljava/util/List;", 0), new MutablePropertyReference1Impl(DataStoreHelper.class, "searchPreferenceTagsStrings", "getSearchPreferenceTagsStrings()Ljava/util/List;", 0), new MutablePropertyReference1Impl(DataStoreHelper.class, "homePreferenceStrings", "getHomePreferenceStrings()Ljava/util/List;", 0), new MutablePropertyReference1Impl(DataStoreHelper.class, "homeBookmarkedList", "getHomeBookmarkedList()[I", 0), new MutablePropertyReference1Impl(DataStoreHelper.class, "playBackSpeed", "getPlayBackSpeed()F", 0), new MutablePropertyReference1Impl(DataStoreHelper.class, "resizeMode", "getResizeMode()I", 0), new MutablePropertyReference1Impl(DataStoreHelper.class, "librarySortingMode", "getLibrarySortingMode()I", 0), new MutablePropertyReference1Impl(DataStoreHelper.class, "_resultsSortingMode", "get_resultsSortingMode()I", 0), new MutablePropertyReference1Impl(DataStoreHelper.class, "accounts", "getAccounts()[Lcom/lagradost/cloudstream3/utils/DataStoreHelper$Account;", 0), new MutablePropertyReference1Impl(DataStoreHelper.class, "selectedKeyIndex", "getSelectedKeyIndex()I", 0)};
    public static final DataStoreHelper INSTANCE = new DataStoreHelper();
    private static final Integer[] profileImages = {Integer.valueOf(R.drawable.profile_bg_dark_blue), Integer.valueOf(R.drawable.profile_bg_blue), Integer.valueOf(R.drawable.profile_bg_orange), Integer.valueOf(R.drawable.profile_bg_pink), Integer.valueOf(R.drawable.profile_bg_purple), Integer.valueOf(R.drawable.profile_bg_red), Integer.valueOf(R.drawable.profile_bg_teal)};

    /* JADX INFO: renamed from: searchPreferenceProvidersStrings$delegate, reason: from kotlin metadata */
    private static final UserPreferenceDelegate searchPreferenceProvidersStrings = new UserPreferenceDelegate("search_pref_providers", new ArrayList(0));

    private DataStoreHelper() {
    }

    public final Integer[] getProfileImages() {
        return profileImages;
    }

    private final List<String> getSearchPreferenceProvidersStrings() {
        return (List) searchPreferenceProvidersStrings.getValue(this, $$delegatedProperties[0]);
    }

    private final void setSearchPreferenceProvidersStrings(List<String> list) {
        searchPreferenceProvidersStrings.setValue(this, $$delegatedProperties[0], list);
    }

    private final List<String> serializeTv(List<? extends TvType> data) {
        List<? extends TvType> list = data;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((TvType) it.next()).name());
        }
        return arrayList;
    }

    private final List<TvType> deserializeTv(List<String> data) {
        TvType tvType;
        ArrayList arrayList = new ArrayList();
        for (String str : data) {
            TvType[] tvTypeArrValues = TvType.values();
            int length = tvTypeArrValues.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    tvType = null;
                    break;
                }
                tvType = tvTypeArrValues[i];
                if (Intrinsics.areEqual(tvType.name(), str)) {
                    break;
                }
                i++;
            }
            if (tvType != null) {
                arrayList.add(tvType);
            }
        }
        return arrayList;
    }

    public final List<String> getSearchPreferenceProviders() {
        List listFilterProviderByPreferredMedia$default;
        List<String> searchPreferenceProvidersStrings2 = getSearchPreferenceProvidersStrings();
        if (searchPreferenceProvidersStrings2.isEmpty()) {
            Context context = CloudStreamApp.INSTANCE.getContext();
            if (context == null || (listFilterProviderByPreferredMedia$default = AppContextUtils.filterProviderByPreferredMedia$default(AppContextUtils.INSTANCE, context, false, 1, null)) == null) {
                searchPreferenceProvidersStrings2 = CollectionsKt.emptyList();
            } else {
                List list = listFilterProviderByPreferredMedia$default;
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    arrayList.add(((MainAPI) it.next()).getName());
                }
                searchPreferenceProvidersStrings2 = arrayList;
            }
        }
        return searchPreferenceProvidersStrings2;
    }

    public final void setSearchPreferenceProviders(List<String> value) {
        Intrinsics.checkNotNullParameter(value, "value");
        setSearchPreferenceProvidersStrings(value);
    }

    private final List<String> getSearchPreferenceTagsStrings() {
        return (List) searchPreferenceTagsStrings.getValue(this, $$delegatedProperties[1]);
    }

    private final void setSearchPreferenceTagsStrings(List<String> list) {
        searchPreferenceTagsStrings.setValue(this, $$delegatedProperties[1], list);
    }

    public final List<TvType> getSearchPreferenceTags() {
        return deserializeTv(getSearchPreferenceTagsStrings());
    }

    public final void setSearchPreferenceTags(List<? extends TvType> value) {
        Intrinsics.checkNotNullParameter(value, "value");
        setSearchPreferenceTagsStrings(serializeTv(value));
    }

    private final List<String> getHomePreferenceStrings() {
        return (List) homePreferenceStrings.getValue(this, $$delegatedProperties[2]);
    }

    private final void setHomePreferenceStrings(List<String> list) {
        homePreferenceStrings.setValue(this, $$delegatedProperties[2], list);
    }

    public final List<TvType> getHomePreference() {
        return deserializeTv(getHomePreferenceStrings());
    }

    public final void setHomePreference(List<? extends TvType> value) {
        Intrinsics.checkNotNullParameter(value, "value");
        setHomePreferenceStrings(serializeTv(value));
    }

    public final int[] getHomeBookmarkedList() {
        return (int[]) homeBookmarkedList.getValue(this, $$delegatedProperties[3]);
    }

    public final void setHomeBookmarkedList(int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<set-?>");
        homeBookmarkedList.setValue(this, $$delegatedProperties[3], iArr);
    }

    public final float getPlayBackSpeed() {
        return ((Number) playBackSpeed.getValue(this, $$delegatedProperties[4])).floatValue();
    }

    public final void setPlayBackSpeed(float f) {
        playBackSpeed.setValue(this, $$delegatedProperties[4], Float.valueOf(f));
    }

    public final int getResizeMode() {
        return ((Number) resizeMode.getValue(this, $$delegatedProperties[5])).intValue();
    }

    public final void setResizeMode(int i) {
        resizeMode.setValue(this, $$delegatedProperties[5], Integer.valueOf(i));
    }

    public final int getLibrarySortingMode() {
        return ((Number) librarySortingMode.getValue(this, $$delegatedProperties[6])).intValue();
    }

    public final void setLibrarySortingMode(int i) {
        librarySortingMode.setValue(this, $$delegatedProperties[6], Integer.valueOf(i));
    }

    private final int get_resultsSortingMode() {
        return ((Number) _resultsSortingMode.getValue(this, $$delegatedProperties[7])).intValue();
    }

    private final void set_resultsSortingMode(int i) {
        _resultsSortingMode.setValue(this, $$delegatedProperties[7], Integer.valueOf(i));
    }

    public final EpisodeSortType getResultsSortingMode() {
        EpisodeSortType episodeSortType = (EpisodeSortType) CollectionsKt.getOrNull(EpisodeSortType.getEntries(), get_resultsSortingMode());
        return episodeSortType == null ? EpisodeSortType.NUMBER_ASC : episodeSortType;
    }

    public final void setResultsSortingMode(EpisodeSortType value) {
        Intrinsics.checkNotNullParameter(value, "value");
        set_resultsSortingMode(value.ordinal());
    }

    /* JADX INFO: compiled from: DataStoreHelper.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B=\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0001\u0010\u0007\u001a\u00020\u0003\u0012\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0005HÆ\u0003J?\u0010\u001b\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\b\u0003\u0010\u0007\u001a\u00020\u00032\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0014\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001f\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010 \u001a\u00020\u0005HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0016\u0010\u0007\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0018\u0010\b\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u0011\u0010\u0012\u001a\u00020\u00138F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015¨\u0006!"}, d2 = {"Lcom/lagradost/cloudstream3/utils/DataStoreHelper$Account;", "", "keyIndex", "", "name", "", "customImage", "defaultImageIndex", "lockPin", "<init>", "(ILjava/lang/String;Ljava/lang/String;ILjava/lang/String;)V", "getKeyIndex", "()I", "getName", "()Ljava/lang/String;", "getCustomImage", "getDefaultImageIndex", "getLockPin", "image", "Lcom/lagradost/cloudstream3/utils/UiImage;", "getImage", "()Lcom/lagradost/cloudstream3/utils/UiImage;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Account {

        @JsonProperty("customImage")
        private final String customImage;

        @JsonProperty("defaultImageIndex")
        private final int defaultImageIndex;

        @JsonProperty("keyIndex")
        private final int keyIndex;

        @JsonProperty("lockPin")
        private final String lockPin;

        @JsonProperty("name")
        private final String name;

        public static /* synthetic */ Account copy$default(Account account, int i, String str, String str2, int i2, String str3, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                i = account.keyIndex;
            }
            if ((i3 & 2) != 0) {
                str = account.name;
            }
            if ((i3 & 4) != 0) {
                str2 = account.customImage;
            }
            if ((i3 & 8) != 0) {
                i2 = account.defaultImageIndex;
            }
            if ((i3 & 16) != 0) {
                str3 = account.lockPin;
            }
            String str4 = str3;
            String str5 = str2;
            return account.copy(i, str, str5, i2, str4);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getKeyIndex() {
            return this.keyIndex;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getCustomImage() {
            return this.customImage;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final int getDefaultImageIndex() {
            return this.defaultImageIndex;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getLockPin() {
            return this.lockPin;
        }

        public final Account copy(@JsonProperty("keyIndex") int keyIndex, @JsonProperty("name") String name, @JsonProperty("customImage") String customImage, @JsonProperty("defaultImageIndex") int defaultImageIndex, @JsonProperty("lockPin") String lockPin) {
            Intrinsics.checkNotNullParameter(name, "name");
            return new Account(keyIndex, name, customImage, defaultImageIndex, lockPin);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Account)) {
                return false;
            }
            Account account = (Account) other;
            return this.keyIndex == account.keyIndex && Intrinsics.areEqual(this.name, account.name) && Intrinsics.areEqual(this.customImage, account.customImage) && this.defaultImageIndex == account.defaultImageIndex && Intrinsics.areEqual(this.lockPin, account.lockPin);
        }

        public int hashCode() {
            int iHashCode = ((this.keyIndex * 31) + this.name.hashCode()) * 31;
            String str = this.customImage;
            int iHashCode2 = (((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + this.defaultImageIndex) * 31;
            String str2 = this.lockPin;
            return iHashCode2 + (str2 != null ? str2.hashCode() : 0);
        }

        public String toString() {
            return "Account(keyIndex=" + this.keyIndex + ", name=" + this.name + ", customImage=" + this.customImage + ", defaultImageIndex=" + this.defaultImageIndex + ", lockPin=" + this.lockPin + ')';
        }

        public Account(@JsonProperty("keyIndex") int i, @JsonProperty("name") String name, @JsonProperty("customImage") String str, @JsonProperty("defaultImageIndex") int i2, @JsonProperty("lockPin") String str2) {
            Intrinsics.checkNotNullParameter(name, "name");
            this.keyIndex = i;
            this.name = name;
            this.customImage = str;
            this.defaultImageIndex = i2;
            this.lockPin = str2;
        }

        public /* synthetic */ Account(int i, String str, String str2, int i2, String str3, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this(i, str, (i3 & 4) != 0 ? null : str2, i2, (i3 & 16) != 0 ? null : str3);
        }

        public final int getKeyIndex() {
            return this.keyIndex;
        }

        public final String getName() {
            return this.name;
        }

        public final String getCustomImage() {
            return this.customImage;
        }

        public final int getDefaultImageIndex() {
            return this.defaultImageIndex;
        }

        public final String getLockPin() {
            return this.lockPin;
        }

        public final UiImage getImage() {
            String str = this.customImage;
            if (str != null) {
                return new UiImage.Image(str, null, 2, null);
            }
            Integer num = (Integer) ArraysKt.getOrNull(DataStoreHelper.INSTANCE.getProfileImages(), this.defaultImageIndex);
            return num != null ? new UiImage.Drawable(num.intValue()) : new UiImage.Drawable(((Number) ArraysKt.first(DataStoreHelper.INSTANCE.getProfileImages())).intValue());
        }
    }

    public final Account[] getAccounts() {
        return (Account[]) accounts.getValue(this, $$delegatedProperties[8]);
    }

    public final void setAccounts(Account[] accountArr) {
        Intrinsics.checkNotNullParameter(accountArr, "<set-?>");
        accounts.setValue(this, $$delegatedProperties[8], accountArr);
    }

    public final int getSelectedKeyIndex() {
        return ((Number) selectedKeyIndex.getValue(this, $$delegatedProperties[9])).intValue();
    }

    public final void setSelectedKeyIndex(int i) {
        selectedKeyIndex.setValue(this, $$delegatedProperties[9], Integer.valueOf(i));
    }

    /* JADX INFO: renamed from: getCurrentAccount, reason: collision with other method in class */
    public final String m8635getCurrentAccount() {
        return String.valueOf(getSelectedKeyIndex());
    }

    public final String getCurrentHomePage() {
        Object objM8936constructorimpl;
        Object objDecodeFromString;
        CloudStreamApp.Companion companion = CloudStreamApp.INSTANCE;
        String str = m8635getCurrentAccount() + "/home_api_used";
        Context context = companion.getContext();
        Object obj = null;
        if (context != null) {
            try {
                String string = DataStore.INSTANCE.getSharedPrefs(context).getString(str, null);
                if (string != null) {
                    AppUtils appUtils = AppUtils.INSTANCE;
                    try {
                        Result.Companion companion2 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(StringSerializer.INSTANCE);
                    } catch (Throwable th) {
                        Result.Companion companion3 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                    }
                    if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) != null) {
                        try {
                            Result.Companion companion4 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(String.class), null, 2, null));
                        } catch (Throwable th2) {
                            Result.Companion companion5 = Result.INSTANCE;
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
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<String>() { // from class: com.lagradost.cloudstream3.utils.DataStoreHelper$special$$inlined$getKey$1
                            });
                        } catch (Throwable unused) {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<String>() { // from class: com.lagradost.cloudstream3.utils.DataStoreHelper$special$$inlined$getKey$1
                            });
                        }
                    } else {
                        objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<String>() { // from class: com.lagradost.cloudstream3.utils.DataStoreHelper$special$$inlined$getKey$1
                        });
                    }
                    obj = objDecodeFromString;
                }
            } catch (Exception unused2) {
            }
        }
        return (String) obj;
    }

    public final void setCurrentHomePage(String str) {
        String str2 = m8635getCurrentAccount() + "/home_api_used";
        if (str == null) {
            CloudStreamApp.INSTANCE.removeKey(str2);
        } else {
            CloudStreamApp.INSTANCE.setKey(str2, str);
        }
    }

    public final void setAccount(Account account) {
        String name;
        Intrinsics.checkNotNullParameter(account, "account");
        String currentHomePage = getCurrentHomePage();
        setSelectedKeyIndex(account.getKeyIndex());
        AccountManager.INSTANCE.updateAccountIds();
        CommonActivity commonActivity = CommonActivity.INSTANCE;
        Context context = CloudStreamApp.INSTANCE.getContext();
        if (context == null || (name = context.getString(R.string.logged_account, account.getName())) == null) {
            name = account.getName();
        }
        Account account2 = null;
        CommonActivity.showToast$default(commonActivity, name, (Integer) null, 2, (Object) null);
        MainActivity.INSTANCE.getBookmarksUpdatedEvent().invoke(true);
        MainActivity.INSTANCE.getReloadLibraryEvent().invoke(true);
        for (Account account3 : getAccounts()) {
            if (account3.getKeyIndex() == account.getKeyIndex()) {
                account2 = account3;
                break;
            }
        }
        if (account2 == null || Intrinsics.areEqual(getCurrentHomePage(), currentHomePage)) {
            return;
        }
        MainActivity.INSTANCE.getReloadHomeEvent().invoke(true);
    }

    public final Account getDefaultAccount(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Account[] accounts2 = getAccounts();
        int length = accounts2.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                i = -1;
                break;
            }
            if (accounts2[i].getKeyIndex() == 0) {
                break;
            }
            i++;
        }
        Account account = (Account) ArraysKt.getOrNull(accounts2, i);
        if (account != null) {
            return account;
        }
        String string = context.getString(R.string.default_account);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return new Account(0, string, null, 0, null, 20, null);
    }

    public final List<Account> getAccounts(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        List<Account> mutableList = ArraysKt.toMutableList(getAccounts());
        Account defaultAccount = INSTANCE.getDefaultAccount(context);
        mutableList.remove(defaultAccount);
        mutableList.add(0, defaultAccount);
        return mutableList;
    }

    public final Account getCurrentAccount() {
        List<Account> list;
        Account account;
        Context context = CloudStreamApp.INSTANCE.getContext();
        if (context == null || (list = INSTANCE.getAccounts(context)) == null) {
            list = ArraysKt.toList(getAccounts());
        }
        Iterator<T> it = list.iterator();
        do {
            account = null;
            if (!it.hasNext()) {
                break;
            }
            Account account2 = (Account) it.next();
            if (account2.getKeyIndex() == INSTANCE.getSelectedKeyIndex()) {
                account = account2;
            }
        } while (account == null);
        return account;
    }

    /* JADX INFO: compiled from: DataStoreHelper.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0014"}, d2 = {"Lcom/lagradost/cloudstream3/utils/DataStoreHelper$PosDur;", "", "position", "", TypedValues.TransitionType.S_DURATION, "<init>", "(JJ)V", "getPosition", "()J", "getDuration", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class PosDur {

        @JsonProperty(TypedValues.TransitionType.S_DURATION)
        private final long duration;

        @JsonProperty("position")
        private final long position;

        public static /* synthetic */ PosDur copy$default(PosDur posDur, long j, long j2, int i, Object obj) {
            if ((i & 1) != 0) {
                j = posDur.position;
            }
            if ((i & 2) != 0) {
                j2 = posDur.duration;
            }
            return posDur.copy(j, j2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final long getPosition() {
            return this.position;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final long getDuration() {
            return this.duration;
        }

        public final PosDur copy(@JsonProperty("position") long position, @JsonProperty(TypedValues.TransitionType.S_DURATION) long duration) {
            return new PosDur(position, duration);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof PosDur)) {
                return false;
            }
            PosDur posDur = (PosDur) other;
            return this.position == posDur.position && this.duration == posDur.duration;
        }

        public int hashCode() {
            return (NextAiring$$ExternalSyntheticBackport0.m(this.position) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.duration);
        }

        public String toString() {
            return "PosDur(position=" + this.position + ", duration=" + this.duration + ')';
        }

        public PosDur(@JsonProperty("position") long j, @JsonProperty(TypedValues.TransitionType.S_DURATION) long j2) {
            this.position = j;
            this.duration = j2;
        }

        public final long getPosition() {
            return this.position;
        }

        public final long getDuration() {
            return this.duration;
        }
    }

    public final PosDur fixVisual(PosDur posDur) {
        Intrinsics.checkNotNullParameter(posDur, "<this>");
        if (posDur.getDuration() <= 0) {
            return new PosDur(0L, posDur.getDuration());
        }
        long position = (posDur.getPosition() * 100) / posDur.getDuration();
        if (position <= 1) {
            return new PosDur(0L, posDur.getDuration());
        }
        if (position <= 5) {
            return new PosDur((5 * posDur.getDuration()) / 100, posDur.getDuration());
        }
        return position >= 95 ? new PosDur(posDur.getDuration(), posDur.getDuration()) : posDur;
    }

    public final Date toYear(int i) {
        Calendar gregorianCalendar = GregorianCalendar.getInstance();
        gregorianCalendar.set(1, i);
        Date time = gregorianCalendar.getTime();
        Intrinsics.checkNotNullExpressionValue(time, "getTime(...)");
        return time;
    }

    /* JADX INFO: compiled from: DataStoreHelper.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b,\b&\u0018\u00002\u00020\u0001BÅ\u0001\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0001\u0010\b\u001a\u00020\u0007\u0012\b\b\u0001\u0010\t\u001a\u00020\u0007\u0012\n\b\u0001\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0001\u0010\f\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0001\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\u0016\b\u0001\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u000f\u0012\n\b\u0001\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u0012\u0016\b\u0001\u0010\u0012\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u000f\u0012\n\b\u0003\u0010\u0013\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0003\u0010\u0014\u001a\u0004\u0018\u00010\u0015\u0012\u0010\b\u0003\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0017¢\u0006\u0004\b\u0018\u0010\u0019R\"\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0016@\u0016X\u0097\u000e¢\u0006\u0010\n\u0002\u0010\u001e\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u0016\u0010\u0004\u001a\u00020\u00058\u0016X\u0097\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0016\u0010\u0006\u001a\u00020\u00078\u0016X\u0097\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0016\u0010\b\u001a\u00020\u00078\u0016X\u0097\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\"R\u0016\u0010\t\u001a\u00020\u00078\u0016X\u0097\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\"R \u0010\n\u001a\u0004\u0018\u00010\u000b8\u0016@\u0016X\u0097\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R \u0010\f\u001a\u0004\u0018\u00010\u00078\u0016@\u0016X\u0097\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\"\"\u0004\b*\u0010+R\u001a\u0010\r\u001a\u0004\u0018\u00010\u00038\u0016X\u0097\u0004¢\u0006\n\n\u0002\u0010\u001e\u001a\u0004\b,\u0010\u001bR$\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u000f8\u0016X\u0097\u0004¢\u0006\b\n\u0000\u001a\u0004\b-\u0010.R \u0010\u0010\u001a\u0004\u0018\u00010\u00118\u0016@\u0016X\u0097\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u00100\"\u0004\b1\u00102R,\u0010\u0012\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u000f8\u0016@\u0016X\u0097\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010.\"\u0004\b4\u00105R\u0018\u0010\u0013\u001a\u0004\u0018\u00010\u00078\u0016X\u0097\u0004¢\u0006\b\n\u0000\u001a\u0004\b6\u0010\"R \u0010\u0014\u001a\u0004\u0018\u00010\u00158\u0016@\u0016X\u0097\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u00108\"\u0004\b9\u0010:R\u001e\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00178\u0016X\u0097\u0004¢\u0006\b\n\u0000\u001a\u0004\b;\u0010<R2\u0010>\u001a\u0004\u0018\u00010\u00032\b\u0010=\u001a\u0004\u0018\u00010\u00038\u0006@FX\u0087\u000e¢\u0006\u0016\n\u0002\u0010\u001e\u0012\u0004\b?\u0010@\u001a\u0004\bA\u0010\u001b\"\u0004\bB\u0010\u001d¨\u0006C"}, d2 = {"Lcom/lagradost/cloudstream3/utils/DataStoreHelper$LibrarySearchResponse;", "Lcom/lagradost/cloudstream3/SearchResponse;", "id", "", "latestUpdatedTime", "", "name", "", ImagesContract.URL, "apiName", "type", "Lcom/lagradost/cloudstream3/TvType;", "posterUrl", "year", "syncData", "", "quality", "Lcom/lagradost/cloudstream3/SearchQuality;", "posterHeaders", "plot", "score", "Lcom/lagradost/cloudstream3/Score;", "tags", "", "<init>", "(Ljava/lang/Integer;JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/lagradost/cloudstream3/TvType;Ljava/lang/String;Ljava/lang/Integer;Ljava/util/Map;Lcom/lagradost/cloudstream3/SearchQuality;Ljava/util/Map;Ljava/lang/String;Lcom/lagradost/cloudstream3/Score;Ljava/util/List;)V", "getId", "()Ljava/lang/Integer;", "setId", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getLatestUpdatedTime", "()J", "getName", "()Ljava/lang/String;", "getUrl", "getApiName", "getType", "()Lcom/lagradost/cloudstream3/TvType;", "setType", "(Lcom/lagradost/cloudstream3/TvType;)V", "getPosterUrl", "setPosterUrl", "(Ljava/lang/String;)V", "getYear", "getSyncData", "()Ljava/util/Map;", "getQuality", "()Lcom/lagradost/cloudstream3/SearchQuality;", "setQuality", "(Lcom/lagradost/cloudstream3/SearchQuality;)V", "getPosterHeaders", "setPosterHeaders", "(Ljava/util/Map;)V", "getPlot", "getScore", "()Lcom/lagradost/cloudstream3/Score;", "setScore", "(Lcom/lagradost/cloudstream3/Score;)V", "getTags", "()Ljava/util/List;", ES6Iterator.VALUE_PROPERTY, "rating", "getRating$annotations", "()V", "getRating", "setRating", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static abstract class LibrarySearchResponse implements SearchResponse {

        @JsonProperty("apiName")
        private final String apiName;

        @JsonProperty("id")
        private Integer id;

        @JsonProperty("latestUpdatedTime")
        private final long latestUpdatedTime;

        @JsonProperty("name")
        private final String name;

        @JsonProperty("plot")
        private final String plot;

        @JsonProperty("posterHeaders")
        private Map<String, String> posterHeaders;

        @JsonProperty("posterUrl")
        private String posterUrl;

        @JsonProperty("quality")
        private SearchQuality quality;

        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY, value = "rating")
        private Integer rating;

        @JsonProperty("score")
        private Score score;

        @JsonProperty("syncData")
        private final Map<String, String> syncData;

        @JsonProperty("tags")
        private final List<String> tags;

        @JsonProperty("type")
        private TvType type;

        @JsonProperty(ImagesContract.URL)
        private final String url;

        @JsonProperty("year")
        private final Integer year;

        @Deprecated(level = DeprecationLevel.ERROR, message = "`rating` is the old scoring system, use score instead", replaceWith = @ReplaceWith(expression = "score", imports = {}))
        public static /* synthetic */ void getRating$annotations() {
        }

        public LibrarySearchResponse(@JsonProperty("id") Integer num, @JsonProperty("latestUpdatedTime") long j, @JsonProperty("name") String name, @JsonProperty(ImagesContract.URL) String url, @JsonProperty("apiName") String apiName, @JsonProperty("type") TvType tvType, @JsonProperty("posterUrl") String str, @JsonProperty("year") Integer num2, @JsonProperty("syncData") Map<String, String> map, @JsonProperty("quality") SearchQuality searchQuality, @JsonProperty("posterHeaders") Map<String, String> map2, @JsonProperty("plot") String str2, @JsonProperty("score") Score score, @JsonProperty("tags") List<String> list) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(apiName, "apiName");
            this.id = num;
            this.latestUpdatedTime = j;
            this.name = name;
            this.url = url;
            this.apiName = apiName;
            this.type = tvType;
            this.posterUrl = str;
            this.year = num2;
            this.syncData = map;
            this.quality = searchQuality;
            this.posterHeaders = map2;
            this.plot = str2;
            this.score = score;
            this.tags = list;
        }

        public /* synthetic */ LibrarySearchResponse(Integer num, long j, String str, String str2, String str3, TvType tvType, String str4, Integer num2, Map map, SearchQuality searchQuality, Map map2, String str5, Score score, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(num, j, str, str2, str3, tvType, str4, num2, map, searchQuality, map2, (i & 2048) != 0 ? null : str5, (i & 4096) != 0 ? null : score, (i & 8192) != 0 ? null : list);
        }

        @Override // com.lagradost.cloudstream3.SearchResponse
        public Integer getId() {
            return this.id;
        }

        @Override // com.lagradost.cloudstream3.SearchResponse
        public void setId(Integer num) {
            this.id = num;
        }

        public long getLatestUpdatedTime() {
            return this.latestUpdatedTime;
        }

        @Override // com.lagradost.cloudstream3.SearchResponse
        public String getName() {
            return this.name;
        }

        @Override // com.lagradost.cloudstream3.SearchResponse
        public String getUrl() {
            return this.url;
        }

        @Override // com.lagradost.cloudstream3.SearchResponse
        public String getApiName() {
            return this.apiName;
        }

        @Override // com.lagradost.cloudstream3.SearchResponse
        public TvType getType() {
            return this.type;
        }

        @Override // com.lagradost.cloudstream3.SearchResponse
        public void setType(TvType tvType) {
            this.type = tvType;
        }

        @Override // com.lagradost.cloudstream3.SearchResponse
        public String getPosterUrl() {
            return this.posterUrl;
        }

        @Override // com.lagradost.cloudstream3.SearchResponse
        public void setPosterUrl(String str) {
            this.posterUrl = str;
        }

        public Integer getYear() {
            return this.year;
        }

        public Map<String, String> getSyncData() {
            return this.syncData;
        }

        @Override // com.lagradost.cloudstream3.SearchResponse
        public SearchQuality getQuality() {
            return this.quality;
        }

        @Override // com.lagradost.cloudstream3.SearchResponse
        public void setQuality(SearchQuality searchQuality) {
            this.quality = searchQuality;
        }

        @Override // com.lagradost.cloudstream3.SearchResponse
        public Map<String, String> getPosterHeaders() {
            return this.posterHeaders;
        }

        @Override // com.lagradost.cloudstream3.SearchResponse
        public void setPosterHeaders(Map<String, String> map) {
            this.posterHeaders = map;
        }

        public String getPlot() {
            return this.plot;
        }

        @Override // com.lagradost.cloudstream3.SearchResponse
        public Score getScore() {
            return this.score;
        }

        @Override // com.lagradost.cloudstream3.SearchResponse
        public void setScore(Score score) {
            this.score = score;
        }

        public List<String> getTags() {
            return this.tags;
        }

        public final Integer getRating() {
            return this.rating;
        }

        public final void setRating(Integer num) {
            if (num != null) {
                setScore(Score.INSTANCE.fromOld(num));
            }
        }
    }

    /* JADX INFO: compiled from: DataStoreHelper.kt */
    @Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b(\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B×\u0001\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0016\b\u0001\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\r\u001a\u00020\u000b\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0007\u0012\u0016\b\u0002\u0010\u0012\u001a\u0010\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0014\u0012\u0016\b\u0002\u0010\u0015\u001a\u0010\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0018\u0012\u0010\b\u0002\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u001a¢\u0006\u0004\b\u001b\u0010\u001cJ\b\u0010B\u001a\u0004\u0018\u00010CJ\t\u0010D\u001a\u00020\u0003HÆ\u0003J\u0017\u0010E\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0005HÆ\u0003J\u0010\u0010F\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\"J\t\u0010G\u001a\u00020\u0003HÆ\u0003J\t\u0010H\u001a\u00020\u000bHÆ\u0003J\t\u0010I\u001a\u00020\u000bHÆ\u0003J\t\u0010J\u001a\u00020\u000bHÆ\u0003J\u000b\u0010K\u001a\u0004\u0018\u00010\u000fHÆ\u0003J\u000b\u0010L\u001a\u0004\u0018\u00010\u000bHÆ\u0003J\u0010\u0010M\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\"J\u0017\u0010N\u001a\u0010\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u0005HÆ\u0003J\u000b\u0010O\u001a\u0004\u0018\u00010\u0014HÆ\u0003J\u0017\u0010P\u001a\u0010\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u0005HÆ\u0003J\u000b\u0010Q\u001a\u0004\u0018\u00010\u000bHÆ\u0003J\u000b\u0010R\u001a\u0004\u0018\u00010\u0018HÆ\u0003J\u0011\u0010S\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u001aHÆ\u0003Jî\u0001\u0010T\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\u0016\b\u0003\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000b2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00072\u0016\b\u0002\u0010\u0012\u001a\u0010\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u00052\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0016\b\u0002\u0010\u0015\u001a\u0010\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u00052\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0010\b\u0002\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u001aHÆ\u0001¢\u0006\u0002\u0010UJ\u0014\u0010V\u001a\u00020W2\b\u0010X\u001a\u0004\u0018\u00010YHÖ\u0083\u0004J\n\u0010Z\u001a\u00020\u0007HÖ\u0081\u0004J\n\u0010[\u001a\u00020\u000bHÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR$\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u001e\u0010\b\u001a\u0004\u0018\u00010\u0007X\u0096\u000e¢\u0006\u0010\n\u0002\u0010%\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u0014\u0010\t\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001eR\u0014\u0010\n\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0014\u0010\f\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b)\u0010(R\u0014\u0010\r\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b*\u0010(R\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u000bX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010(\"\u0004\b0\u00101R\u0018\u0010\u0011\u001a\u0004\u0018\u00010\u0007X\u0096\u0004¢\u0006\n\n\u0002\u0010%\u001a\u0004\b2\u0010\"R\"\u0010\u0012\u001a\u0010\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b3\u0010 R\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u00105\"\u0004\b6\u00107R(\u0010\u0015\u001a\u0010\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u0010 \"\u0004\b9\u0010:R\u0016\u0010\u0016\u001a\u0004\u0018\u00010\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b;\u0010(R\u001c\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?R\u001c\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u001aX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b@\u0010A¨\u0006\\"}, d2 = {"Lcom/lagradost/cloudstream3/utils/DataStoreHelper$SubscribedData;", "Lcom/lagradost/cloudstream3/utils/DataStoreHelper$LibrarySearchResponse;", "subscribedTime", "", "lastSeenEpisodeCount", "", "Lcom/lagradost/cloudstream3/DubStatus;", "", "id", "latestUpdatedTime", "name", "", ImagesContract.URL, "apiName", "type", "Lcom/lagradost/cloudstream3/TvType;", "posterUrl", "year", "syncData", "quality", "Lcom/lagradost/cloudstream3/SearchQuality;", "posterHeaders", "plot", "score", "Lcom/lagradost/cloudstream3/Score;", "tags", "", "<init>", "(JLjava/util/Map;Ljava/lang/Integer;JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/lagradost/cloudstream3/TvType;Ljava/lang/String;Ljava/lang/Integer;Ljava/util/Map;Lcom/lagradost/cloudstream3/SearchQuality;Ljava/util/Map;Ljava/lang/String;Lcom/lagradost/cloudstream3/Score;Ljava/util/List;)V", "getSubscribedTime", "()J", "getLastSeenEpisodeCount", "()Ljava/util/Map;", "getId", "()Ljava/lang/Integer;", "setId", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getLatestUpdatedTime", "getName", "()Ljava/lang/String;", "getUrl", "getApiName", "getType", "()Lcom/lagradost/cloudstream3/TvType;", "setType", "(Lcom/lagradost/cloudstream3/TvType;)V", "getPosterUrl", "setPosterUrl", "(Ljava/lang/String;)V", "getYear", "getSyncData", "getQuality", "()Lcom/lagradost/cloudstream3/SearchQuality;", "setQuality", "(Lcom/lagradost/cloudstream3/SearchQuality;)V", "getPosterHeaders", "setPosterHeaders", "(Ljava/util/Map;)V", "getPlot", "getScore", "()Lcom/lagradost/cloudstream3/Score;", "setScore", "(Lcom/lagradost/cloudstream3/Score;)V", "getTags", "()Ljava/util/List;", "toLibraryItem", "Lcom/lagradost/cloudstream3/syncproviders/SyncAPI$LibraryItem;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "copy", "(JLjava/util/Map;Ljava/lang/Integer;JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/lagradost/cloudstream3/TvType;Ljava/lang/String;Ljava/lang/Integer;Ljava/util/Map;Lcom/lagradost/cloudstream3/SearchQuality;Ljava/util/Map;Ljava/lang/String;Lcom/lagradost/cloudstream3/Score;Ljava/util/List;)Lcom/lagradost/cloudstream3/utils/DataStoreHelper$SubscribedData;", "equals", "", "other", "", "hashCode", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class SubscribedData extends LibrarySearchResponse {
        private final String apiName;
        private Integer id;

        @JsonProperty("lastSeenEpisodeCount")
        private final Map<DubStatus, Integer> lastSeenEpisodeCount;
        private final long latestUpdatedTime;
        private final String name;
        private final String plot;
        private Map<String, String> posterHeaders;
        private String posterUrl;
        private SearchQuality quality;
        private Score score;

        @JsonProperty("subscribedTime")
        private final long subscribedTime;
        private final Map<String, String> syncData;
        private final List<String> tags;
        private TvType type;
        private final String url;
        private final Integer year;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ SubscribedData copy$default(SubscribedData subscribedData, long j, Map map, Integer num, long j2, String str, String str2, String str3, TvType tvType, String str4, Integer num2, Map map2, SearchQuality searchQuality, Map map3, String str5, Score score, List list, int i, Object obj) {
            long j3 = (i & 1) != 0 ? subscribedData.subscribedTime : j;
            Map map4 = (i & 2) != 0 ? subscribedData.lastSeenEpisodeCount : map;
            Integer num3 = (i & 4) != 0 ? subscribedData.id : num;
            long j4 = (i & 8) != 0 ? subscribedData.latestUpdatedTime : j2;
            String str6 = (i & 16) != 0 ? subscribedData.name : str;
            String str7 = (i & 32) != 0 ? subscribedData.url : str2;
            String str8 = (i & 64) != 0 ? subscribedData.apiName : str3;
            TvType tvType2 = (i & 128) != 0 ? subscribedData.type : tvType;
            String str9 = (i & 256) != 0 ? subscribedData.posterUrl : str4;
            Integer num4 = (i & 512) != 0 ? subscribedData.year : num2;
            Map map5 = (i & 1024) != 0 ? subscribedData.syncData : map2;
            SearchQuality searchQuality2 = (i & 2048) != 0 ? subscribedData.quality : searchQuality;
            long j5 = j3;
            Map map6 = (i & 4096) != 0 ? subscribedData.posterHeaders : map3;
            return subscribedData.copy(j5, map4, num3, j4, str6, str7, str8, tvType2, str9, num4, map5, searchQuality2, map6, (i & 8192) != 0 ? subscribedData.plot : str5, (i & 16384) != 0 ? subscribedData.score : score, (i & 32768) != 0 ? subscribedData.tags : list);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final long getSubscribedTime() {
            return this.subscribedTime;
        }

        /* JADX INFO: renamed from: component10, reason: from getter */
        public final Integer getYear() {
            return this.year;
        }

        public final Map<String, String> component11() {
            return this.syncData;
        }

        /* JADX INFO: renamed from: component12, reason: from getter */
        public final SearchQuality getQuality() {
            return this.quality;
        }

        public final Map<String, String> component13() {
            return this.posterHeaders;
        }

        /* JADX INFO: renamed from: component14, reason: from getter */
        public final String getPlot() {
            return this.plot;
        }

        /* JADX INFO: renamed from: component15, reason: from getter */
        public final Score getScore() {
            return this.score;
        }

        public final List<String> component16() {
            return this.tags;
        }

        public final Map<DubStatus, Integer> component2() {
            return this.lastSeenEpisodeCount;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final Integer getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final long getLatestUpdatedTime() {
            return this.latestUpdatedTime;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getUrl() {
            return this.url;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getApiName() {
            return this.apiName;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final TvType getType() {
            return this.type;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final String getPosterUrl() {
            return this.posterUrl;
        }

        public final SubscribedData copy(@JsonProperty("subscribedTime") long subscribedTime, @JsonProperty("lastSeenEpisodeCount") Map<DubStatus, Integer> lastSeenEpisodeCount, Integer id, long latestUpdatedTime, String name, String url, String apiName, TvType type, String posterUrl, Integer year, Map<String, String> syncData, SearchQuality quality, Map<String, String> posterHeaders, String plot, Score score, List<String> tags) {
            Intrinsics.checkNotNullParameter(lastSeenEpisodeCount, "lastSeenEpisodeCount");
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(apiName, "apiName");
            return new SubscribedData(subscribedTime, lastSeenEpisodeCount, id, latestUpdatedTime, name, url, apiName, type, posterUrl, year, syncData, quality, posterHeaders, plot, score, tags);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SubscribedData)) {
                return false;
            }
            SubscribedData subscribedData = (SubscribedData) other;
            return this.subscribedTime == subscribedData.subscribedTime && Intrinsics.areEqual(this.lastSeenEpisodeCount, subscribedData.lastSeenEpisodeCount) && Intrinsics.areEqual(this.id, subscribedData.id) && this.latestUpdatedTime == subscribedData.latestUpdatedTime && Intrinsics.areEqual(this.name, subscribedData.name) && Intrinsics.areEqual(this.url, subscribedData.url) && Intrinsics.areEqual(this.apiName, subscribedData.apiName) && this.type == subscribedData.type && Intrinsics.areEqual(this.posterUrl, subscribedData.posterUrl) && Intrinsics.areEqual(this.year, subscribedData.year) && Intrinsics.areEqual(this.syncData, subscribedData.syncData) && this.quality == subscribedData.quality && Intrinsics.areEqual(this.posterHeaders, subscribedData.posterHeaders) && Intrinsics.areEqual(this.plot, subscribedData.plot) && Intrinsics.areEqual(this.score, subscribedData.score) && Intrinsics.areEqual(this.tags, subscribedData.tags);
        }

        public int hashCode() {
            int iM = ((NextAiring$$ExternalSyntheticBackport0.m(this.subscribedTime) * 31) + this.lastSeenEpisodeCount.hashCode()) * 31;
            Integer num = this.id;
            int iHashCode = (((((((((iM + (num == null ? 0 : num.hashCode())) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.latestUpdatedTime)) * 31) + this.name.hashCode()) * 31) + this.url.hashCode()) * 31) + this.apiName.hashCode()) * 31;
            TvType tvType = this.type;
            int iHashCode2 = (iHashCode + (tvType == null ? 0 : tvType.hashCode())) * 31;
            String str = this.posterUrl;
            int iHashCode3 = (iHashCode2 + (str == null ? 0 : str.hashCode())) * 31;
            Integer num2 = this.year;
            int iHashCode4 = (iHashCode3 + (num2 == null ? 0 : num2.hashCode())) * 31;
            Map<String, String> map = this.syncData;
            int iHashCode5 = (iHashCode4 + (map == null ? 0 : map.hashCode())) * 31;
            SearchQuality searchQuality = this.quality;
            int iHashCode6 = (iHashCode5 + (searchQuality == null ? 0 : searchQuality.hashCode())) * 31;
            Map<String, String> map2 = this.posterHeaders;
            int iHashCode7 = (iHashCode6 + (map2 == null ? 0 : map2.hashCode())) * 31;
            String str2 = this.plot;
            int iHashCode8 = (iHashCode7 + (str2 == null ? 0 : str2.hashCode())) * 31;
            Score score = this.score;
            int data = (iHashCode8 + (score == null ? 0 : score.getData())) * 31;
            List<String> list = this.tags;
            return data + (list != null ? list.hashCode() : 0);
        }

        public String toString() {
            return "SubscribedData(subscribedTime=" + this.subscribedTime + ", lastSeenEpisodeCount=" + this.lastSeenEpisodeCount + ", id=" + this.id + ", latestUpdatedTime=" + this.latestUpdatedTime + ", name=" + this.name + ", url=" + this.url + ", apiName=" + this.apiName + ", type=" + this.type + ", posterUrl=" + this.posterUrl + ", year=" + this.year + ", syncData=" + this.syncData + ", quality=" + this.quality + ", posterHeaders=" + this.posterHeaders + ", plot=" + this.plot + ", score=" + this.score + ", tags=" + this.tags + ')';
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public SubscribedData(@JsonProperty("subscribedTime") long j, @JsonProperty("lastSeenEpisodeCount") Map<DubStatus, Integer> lastSeenEpisodeCount, Integer num, long j2, String name, String url, String apiName, TvType tvType, String str, Integer num2, Map<String, String> map, SearchQuality searchQuality, Map<String, String> map2, String str2, Score score, List<String> list) {
            super(num, j2, name, url, apiName, tvType, str, num2, map, searchQuality, map2, str2, score, list);
            Intrinsics.checkNotNullParameter(lastSeenEpisodeCount, "lastSeenEpisodeCount");
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(apiName, "apiName");
            this.subscribedTime = j;
            this.lastSeenEpisodeCount = lastSeenEpisodeCount;
            this.id = num;
            this.latestUpdatedTime = j2;
            this.name = name;
            this.url = url;
            this.apiName = apiName;
            this.type = tvType;
            this.posterUrl = str;
            this.year = num2;
            this.syncData = map;
            this.quality = searchQuality;
            this.posterHeaders = map2;
            this.plot = str2;
            this.score = score;
            this.tags = list;
        }

        public /* synthetic */ SubscribedData(long j, Map map, Integer num, long j2, String str, String str2, String str3, TvType tvType, String str4, Integer num2, Map map2, SearchQuality searchQuality, Map map3, String str5, Score score, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(j, map, num, j2, str, str2, str3, tvType, str4, num2, (i & 1024) != 0 ? null : map2, (i & 2048) != 0 ? null : searchQuality, (i & 4096) != 0 ? null : map3, (i & 8192) != 0 ? null : str5, (i & 16384) != 0 ? null : score, (i & 32768) != 0 ? null : list);
        }

        public final long getSubscribedTime() {
            return this.subscribedTime;
        }

        public final Map<DubStatus, Integer> getLastSeenEpisodeCount() {
            return this.lastSeenEpisodeCount;
        }

        @Override // com.lagradost.cloudstream3.utils.DataStoreHelper.LibrarySearchResponse, com.lagradost.cloudstream3.SearchResponse
        public Integer getId() {
            return this.id;
        }

        @Override // com.lagradost.cloudstream3.utils.DataStoreHelper.LibrarySearchResponse, com.lagradost.cloudstream3.SearchResponse
        public void setId(Integer num) {
            this.id = num;
        }

        @Override // com.lagradost.cloudstream3.utils.DataStoreHelper.LibrarySearchResponse
        public long getLatestUpdatedTime() {
            return this.latestUpdatedTime;
        }

        @Override // com.lagradost.cloudstream3.utils.DataStoreHelper.LibrarySearchResponse, com.lagradost.cloudstream3.SearchResponse
        public String getName() {
            return this.name;
        }

        @Override // com.lagradost.cloudstream3.utils.DataStoreHelper.LibrarySearchResponse, com.lagradost.cloudstream3.SearchResponse
        public String getUrl() {
            return this.url;
        }

        @Override // com.lagradost.cloudstream3.utils.DataStoreHelper.LibrarySearchResponse, com.lagradost.cloudstream3.SearchResponse
        public String getApiName() {
            return this.apiName;
        }

        @Override // com.lagradost.cloudstream3.utils.DataStoreHelper.LibrarySearchResponse, com.lagradost.cloudstream3.SearchResponse
        public TvType getType() {
            return this.type;
        }

        @Override // com.lagradost.cloudstream3.utils.DataStoreHelper.LibrarySearchResponse, com.lagradost.cloudstream3.SearchResponse
        public void setType(TvType tvType) {
            this.type = tvType;
        }

        @Override // com.lagradost.cloudstream3.utils.DataStoreHelper.LibrarySearchResponse, com.lagradost.cloudstream3.SearchResponse
        public String getPosterUrl() {
            return this.posterUrl;
        }

        @Override // com.lagradost.cloudstream3.utils.DataStoreHelper.LibrarySearchResponse, com.lagradost.cloudstream3.SearchResponse
        public void setPosterUrl(String str) {
            this.posterUrl = str;
        }

        @Override // com.lagradost.cloudstream3.utils.DataStoreHelper.LibrarySearchResponse
        public Integer getYear() {
            return this.year;
        }

        @Override // com.lagradost.cloudstream3.utils.DataStoreHelper.LibrarySearchResponse
        public Map<String, String> getSyncData() {
            return this.syncData;
        }

        @Override // com.lagradost.cloudstream3.utils.DataStoreHelper.LibrarySearchResponse, com.lagradost.cloudstream3.SearchResponse
        public SearchQuality getQuality() {
            return this.quality;
        }

        @Override // com.lagradost.cloudstream3.utils.DataStoreHelper.LibrarySearchResponse, com.lagradost.cloudstream3.SearchResponse
        public void setQuality(SearchQuality searchQuality) {
            this.quality = searchQuality;
        }

        @Override // com.lagradost.cloudstream3.utils.DataStoreHelper.LibrarySearchResponse, com.lagradost.cloudstream3.SearchResponse
        public Map<String, String> getPosterHeaders() {
            return this.posterHeaders;
        }

        @Override // com.lagradost.cloudstream3.utils.DataStoreHelper.LibrarySearchResponse, com.lagradost.cloudstream3.SearchResponse
        public void setPosterHeaders(Map<String, String> map) {
            this.posterHeaders = map;
        }

        @Override // com.lagradost.cloudstream3.utils.DataStoreHelper.LibrarySearchResponse
        public String getPlot() {
            return this.plot;
        }

        @Override // com.lagradost.cloudstream3.utils.DataStoreHelper.LibrarySearchResponse, com.lagradost.cloudstream3.SearchResponse
        public Score getScore() {
            return this.score;
        }

        @Override // com.lagradost.cloudstream3.utils.DataStoreHelper.LibrarySearchResponse, com.lagradost.cloudstream3.SearchResponse
        public void setScore(Score score) {
            this.score = score;
        }

        @Override // com.lagradost.cloudstream3.utils.DataStoreHelper.LibrarySearchResponse
        public List<String> getTags() {
            return this.tags;
        }

        public final SyncAPI.LibraryItem toLibraryItem() {
            String strValueOf;
            String name = getName();
            String url = getUrl();
            Integer id = getId();
            if (id == null || (strValueOf = String.valueOf(id.intValue())) == null) {
                return null;
            }
            Long lValueOf = Long.valueOf(getLatestUpdatedTime());
            String apiName = getApiName();
            TvType type = getType();
            String posterUrl = getPosterUrl();
            Map<String, String> posterHeaders = getPosterHeaders();
            SearchQuality quality = getQuality();
            Integer year = getYear();
            return new SyncAPI.LibraryItem(name, url, strValueOf, null, null, null, lValueOf, apiName, type, posterUrl, posterHeaders, quality, year != null ? DataStoreHelper.INSTANCE.toYear(year.intValue()) : null, getId(), getPlot(), getScore(), getTags());
        }
    }

    /* JADX INFO: compiled from: DataStoreHelper.kt */
    @Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b'\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B¿\u0001\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\b\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\b\u0010\r\u001a\u0004\u0018\u00010\b\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u0012\u0016\b\u0002\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u0012\u0016\b\u0002\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0016\u0012\u0010\b\u0002\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0018¢\u0006\u0004\b\u0019\u0010\u001aJ\u000e\u0010?\u001a\u00020@2\u0006\u0010\u0004\u001a\u00020\bJ\t\u0010A\u001a\u00020\u0003HÆ\u0003J\u0010\u0010B\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u001eJ\t\u0010C\u001a\u00020\u0003HÆ\u0003J\t\u0010D\u001a\u00020\bHÆ\u0003J\t\u0010E\u001a\u00020\bHÆ\u0003J\t\u0010F\u001a\u00020\bHÆ\u0003J\u000b\u0010G\u001a\u0004\u0018\u00010\fHÆ\u0003J\u000b\u0010H\u001a\u0004\u0018\u00010\bHÆ\u0003J\u0010\u0010I\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u001eJ\u0017\u0010J\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0018\u00010\u0010HÆ\u0003J\u000b\u0010K\u001a\u0004\u0018\u00010\u0012HÆ\u0003J\u0017\u0010L\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0018\u00010\u0010HÆ\u0003J\u000b\u0010M\u001a\u0004\u0018\u00010\bHÆ\u0003J\u000b\u0010N\u001a\u0004\u0018\u00010\u0016HÆ\u0003J\u0011\u0010O\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0018HÆ\u0003JÖ\u0001\u0010P\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\b2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00052\u0016\b\u0002\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0018\u00010\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0016\b\u0002\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0018\u00010\u00102\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0010\b\u0002\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0018HÆ\u0001¢\u0006\u0002\u0010QJ\u0014\u0010R\u001a\u00020S2\b\u0010T\u001a\u0004\u0018\u00010UHÖ\u0083\u0004J\n\u0010V\u001a\u00020\u0005HÖ\u0081\u0004J\n\u0010W\u001a\u00020\bHÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0096\u000e¢\u0006\u0010\n\u0002\u0010!\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u0014\u0010\u0006\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001cR\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0014\u0010\t\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b%\u0010$R\u0014\u0010\n\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010$R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u001c\u0010\r\u001a\u0004\u0018\u00010\bX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010$\"\u0004\b,\u0010-R\u0018\u0010\u000e\u001a\u0004\u0018\u00010\u0005X\u0096\u0004¢\u0006\n\n\u0002\u0010!\u001a\u0004\b.\u0010\u001eR\"\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0018\u00010\u0010X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b/\u00100R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u00102\"\u0004\b3\u00104R(\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0018\u00010\u0010X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u00100\"\u0004\b6\u00107R\u0016\u0010\u0014\u001a\u0004\u0018\u00010\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b8\u0010$R\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010:\"\u0004\b;\u0010<R\u001c\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0018X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b=\u0010>¨\u0006X"}, d2 = {"Lcom/lagradost/cloudstream3/utils/DataStoreHelper$BookmarkedData;", "Lcom/lagradost/cloudstream3/utils/DataStoreHelper$LibrarySearchResponse;", "bookmarkedTime", "", "id", "", "latestUpdatedTime", "name", "", ImagesContract.URL, "apiName", "type", "Lcom/lagradost/cloudstream3/TvType;", "posterUrl", "year", "syncData", "", "quality", "Lcom/lagradost/cloudstream3/SearchQuality;", "posterHeaders", "plot", "score", "Lcom/lagradost/cloudstream3/Score;", "tags", "", "<init>", "(JLjava/lang/Integer;JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/lagradost/cloudstream3/TvType;Ljava/lang/String;Ljava/lang/Integer;Ljava/util/Map;Lcom/lagradost/cloudstream3/SearchQuality;Ljava/util/Map;Ljava/lang/String;Lcom/lagradost/cloudstream3/Score;Ljava/util/List;)V", "getBookmarkedTime", "()J", "getId", "()Ljava/lang/Integer;", "setId", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getLatestUpdatedTime", "getName", "()Ljava/lang/String;", "getUrl", "getApiName", "getType", "()Lcom/lagradost/cloudstream3/TvType;", "setType", "(Lcom/lagradost/cloudstream3/TvType;)V", "getPosterUrl", "setPosterUrl", "(Ljava/lang/String;)V", "getYear", "getSyncData", "()Ljava/util/Map;", "getQuality", "()Lcom/lagradost/cloudstream3/SearchQuality;", "setQuality", "(Lcom/lagradost/cloudstream3/SearchQuality;)V", "getPosterHeaders", "setPosterHeaders", "(Ljava/util/Map;)V", "getPlot", "getScore", "()Lcom/lagradost/cloudstream3/Score;", "setScore", "(Lcom/lagradost/cloudstream3/Score;)V", "getTags", "()Ljava/util/List;", "toLibraryItem", "Lcom/lagradost/cloudstream3/syncproviders/SyncAPI$LibraryItem;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "copy", "(JLjava/lang/Integer;JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/lagradost/cloudstream3/TvType;Ljava/lang/String;Ljava/lang/Integer;Ljava/util/Map;Lcom/lagradost/cloudstream3/SearchQuality;Ljava/util/Map;Ljava/lang/String;Lcom/lagradost/cloudstream3/Score;Ljava/util/List;)Lcom/lagradost/cloudstream3/utils/DataStoreHelper$BookmarkedData;", "equals", "", "other", "", "hashCode", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class BookmarkedData extends LibrarySearchResponse {
        private final String apiName;

        @JsonProperty("bookmarkedTime")
        private final long bookmarkedTime;
        private Integer id;
        private final long latestUpdatedTime;
        private final String name;
        private final String plot;
        private Map<String, String> posterHeaders;
        private String posterUrl;
        private SearchQuality quality;
        private Score score;
        private final Map<String, String> syncData;
        private final List<String> tags;
        private TvType type;
        private final String url;
        private final Integer year;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ BookmarkedData copy$default(BookmarkedData bookmarkedData, long j, Integer num, long j2, String str, String str2, String str3, TvType tvType, String str4, Integer num2, Map map, SearchQuality searchQuality, Map map2, String str5, Score score, List list, int i, Object obj) {
            long j3 = (i & 1) != 0 ? bookmarkedData.bookmarkedTime : j;
            return bookmarkedData.copy(j3, (i & 2) != 0 ? bookmarkedData.id : num, (i & 4) != 0 ? bookmarkedData.latestUpdatedTime : j2, (i & 8) != 0 ? bookmarkedData.name : str, (i & 16) != 0 ? bookmarkedData.url : str2, (i & 32) != 0 ? bookmarkedData.apiName : str3, (i & 64) != 0 ? bookmarkedData.type : tvType, (i & 128) != 0 ? bookmarkedData.posterUrl : str4, (i & 256) != 0 ? bookmarkedData.year : num2, (i & 512) != 0 ? bookmarkedData.syncData : map, (i & 1024) != 0 ? bookmarkedData.quality : searchQuality, (i & 2048) != 0 ? bookmarkedData.posterHeaders : map2, (i & 4096) != 0 ? bookmarkedData.plot : str5, (i & 8192) != 0 ? bookmarkedData.score : score, (i & 16384) != 0 ? bookmarkedData.tags : list);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final long getBookmarkedTime() {
            return this.bookmarkedTime;
        }

        public final Map<String, String> component10() {
            return this.syncData;
        }

        /* JADX INFO: renamed from: component11, reason: from getter */
        public final SearchQuality getQuality() {
            return this.quality;
        }

        public final Map<String, String> component12() {
            return this.posterHeaders;
        }

        /* JADX INFO: renamed from: component13, reason: from getter */
        public final String getPlot() {
            return this.plot;
        }

        /* JADX INFO: renamed from: component14, reason: from getter */
        public final Score getScore() {
            return this.score;
        }

        public final List<String> component15() {
            return this.tags;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Integer getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final long getLatestUpdatedTime() {
            return this.latestUpdatedTime;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getUrl() {
            return this.url;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getApiName() {
            return this.apiName;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final TvType getType() {
            return this.type;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final String getPosterUrl() {
            return this.posterUrl;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final Integer getYear() {
            return this.year;
        }

        public final BookmarkedData copy(@JsonProperty("bookmarkedTime") long bookmarkedTime, Integer id, long latestUpdatedTime, String name, String url, String apiName, TvType type, String posterUrl, Integer year, Map<String, String> syncData, SearchQuality quality, Map<String, String> posterHeaders, String plot, Score score, List<String> tags) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(apiName, "apiName");
            return new BookmarkedData(bookmarkedTime, id, latestUpdatedTime, name, url, apiName, type, posterUrl, year, syncData, quality, posterHeaders, plot, score, tags);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BookmarkedData)) {
                return false;
            }
            BookmarkedData bookmarkedData = (BookmarkedData) other;
            return this.bookmarkedTime == bookmarkedData.bookmarkedTime && Intrinsics.areEqual(this.id, bookmarkedData.id) && this.latestUpdatedTime == bookmarkedData.latestUpdatedTime && Intrinsics.areEqual(this.name, bookmarkedData.name) && Intrinsics.areEqual(this.url, bookmarkedData.url) && Intrinsics.areEqual(this.apiName, bookmarkedData.apiName) && this.type == bookmarkedData.type && Intrinsics.areEqual(this.posterUrl, bookmarkedData.posterUrl) && Intrinsics.areEqual(this.year, bookmarkedData.year) && Intrinsics.areEqual(this.syncData, bookmarkedData.syncData) && this.quality == bookmarkedData.quality && Intrinsics.areEqual(this.posterHeaders, bookmarkedData.posterHeaders) && Intrinsics.areEqual(this.plot, bookmarkedData.plot) && Intrinsics.areEqual(this.score, bookmarkedData.score) && Intrinsics.areEqual(this.tags, bookmarkedData.tags);
        }

        public int hashCode() {
            int iM = NextAiring$$ExternalSyntheticBackport0.m(this.bookmarkedTime) * 31;
            Integer num = this.id;
            int iHashCode = (((((((((iM + (num == null ? 0 : num.hashCode())) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.latestUpdatedTime)) * 31) + this.name.hashCode()) * 31) + this.url.hashCode()) * 31) + this.apiName.hashCode()) * 31;
            TvType tvType = this.type;
            int iHashCode2 = (iHashCode + (tvType == null ? 0 : tvType.hashCode())) * 31;
            String str = this.posterUrl;
            int iHashCode3 = (iHashCode2 + (str == null ? 0 : str.hashCode())) * 31;
            Integer num2 = this.year;
            int iHashCode4 = (iHashCode3 + (num2 == null ? 0 : num2.hashCode())) * 31;
            Map<String, String> map = this.syncData;
            int iHashCode5 = (iHashCode4 + (map == null ? 0 : map.hashCode())) * 31;
            SearchQuality searchQuality = this.quality;
            int iHashCode6 = (iHashCode5 + (searchQuality == null ? 0 : searchQuality.hashCode())) * 31;
            Map<String, String> map2 = this.posterHeaders;
            int iHashCode7 = (iHashCode6 + (map2 == null ? 0 : map2.hashCode())) * 31;
            String str2 = this.plot;
            int iHashCode8 = (iHashCode7 + (str2 == null ? 0 : str2.hashCode())) * 31;
            Score score = this.score;
            int data = (iHashCode8 + (score == null ? 0 : score.getData())) * 31;
            List<String> list = this.tags;
            return data + (list != null ? list.hashCode() : 0);
        }

        public String toString() {
            return "BookmarkedData(bookmarkedTime=" + this.bookmarkedTime + ", id=" + this.id + ", latestUpdatedTime=" + this.latestUpdatedTime + ", name=" + this.name + ", url=" + this.url + ", apiName=" + this.apiName + ", type=" + this.type + ", posterUrl=" + this.posterUrl + ", year=" + this.year + ", syncData=" + this.syncData + ", quality=" + this.quality + ", posterHeaders=" + this.posterHeaders + ", plot=" + this.plot + ", score=" + this.score + ", tags=" + this.tags + ')';
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public BookmarkedData(@JsonProperty("bookmarkedTime") long j, Integer num, long j2, String name, String url, String apiName, TvType tvType, String str, Integer num2, Map<String, String> map, SearchQuality searchQuality, Map<String, String> map2, String str2, Score score, List<String> list) {
            super(num, j2, name, url, apiName, tvType, str, num2, map, searchQuality, map2, str2, null, null, 12288, null);
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(apiName, "apiName");
            this.bookmarkedTime = j;
            this.id = num;
            this.latestUpdatedTime = j2;
            this.name = name;
            this.url = url;
            this.apiName = apiName;
            this.type = tvType;
            this.posterUrl = str;
            this.year = num2;
            this.syncData = map;
            this.quality = searchQuality;
            this.posterHeaders = map2;
            this.plot = str2;
            this.score = score;
            this.tags = list;
        }

        public /* synthetic */ BookmarkedData(long j, Integer num, long j2, String str, String str2, String str3, TvType tvType, String str4, Integer num2, Map map, SearchQuality searchQuality, Map map2, String str5, Score score, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(j, num, j2, str, str2, str3, tvType, str4, num2, (i & 512) != 0 ? null : map, (i & 1024) != 0 ? null : searchQuality, (i & 2048) != 0 ? null : map2, (i & 4096) != 0 ? null : str5, (i & 8192) != 0 ? null : score, (i & 16384) != 0 ? null : list);
        }

        public final long getBookmarkedTime() {
            return this.bookmarkedTime;
        }

        @Override // com.lagradost.cloudstream3.utils.DataStoreHelper.LibrarySearchResponse, com.lagradost.cloudstream3.SearchResponse
        public Integer getId() {
            return this.id;
        }

        @Override // com.lagradost.cloudstream3.utils.DataStoreHelper.LibrarySearchResponse, com.lagradost.cloudstream3.SearchResponse
        public void setId(Integer num) {
            this.id = num;
        }

        @Override // com.lagradost.cloudstream3.utils.DataStoreHelper.LibrarySearchResponse
        public long getLatestUpdatedTime() {
            return this.latestUpdatedTime;
        }

        @Override // com.lagradost.cloudstream3.utils.DataStoreHelper.LibrarySearchResponse, com.lagradost.cloudstream3.SearchResponse
        public String getName() {
            return this.name;
        }

        @Override // com.lagradost.cloudstream3.utils.DataStoreHelper.LibrarySearchResponse, com.lagradost.cloudstream3.SearchResponse
        public String getUrl() {
            return this.url;
        }

        @Override // com.lagradost.cloudstream3.utils.DataStoreHelper.LibrarySearchResponse, com.lagradost.cloudstream3.SearchResponse
        public String getApiName() {
            return this.apiName;
        }

        @Override // com.lagradost.cloudstream3.utils.DataStoreHelper.LibrarySearchResponse, com.lagradost.cloudstream3.SearchResponse
        public TvType getType() {
            return this.type;
        }

        @Override // com.lagradost.cloudstream3.utils.DataStoreHelper.LibrarySearchResponse, com.lagradost.cloudstream3.SearchResponse
        public void setType(TvType tvType) {
            this.type = tvType;
        }

        @Override // com.lagradost.cloudstream3.utils.DataStoreHelper.LibrarySearchResponse, com.lagradost.cloudstream3.SearchResponse
        public String getPosterUrl() {
            return this.posterUrl;
        }

        @Override // com.lagradost.cloudstream3.utils.DataStoreHelper.LibrarySearchResponse, com.lagradost.cloudstream3.SearchResponse
        public void setPosterUrl(String str) {
            this.posterUrl = str;
        }

        @Override // com.lagradost.cloudstream3.utils.DataStoreHelper.LibrarySearchResponse
        public Integer getYear() {
            return this.year;
        }

        @Override // com.lagradost.cloudstream3.utils.DataStoreHelper.LibrarySearchResponse
        public Map<String, String> getSyncData() {
            return this.syncData;
        }

        @Override // com.lagradost.cloudstream3.utils.DataStoreHelper.LibrarySearchResponse, com.lagradost.cloudstream3.SearchResponse
        public SearchQuality getQuality() {
            return this.quality;
        }

        @Override // com.lagradost.cloudstream3.utils.DataStoreHelper.LibrarySearchResponse, com.lagradost.cloudstream3.SearchResponse
        public void setQuality(SearchQuality searchQuality) {
            this.quality = searchQuality;
        }

        @Override // com.lagradost.cloudstream3.utils.DataStoreHelper.LibrarySearchResponse, com.lagradost.cloudstream3.SearchResponse
        public Map<String, String> getPosterHeaders() {
            return this.posterHeaders;
        }

        @Override // com.lagradost.cloudstream3.utils.DataStoreHelper.LibrarySearchResponse, com.lagradost.cloudstream3.SearchResponse
        public void setPosterHeaders(Map<String, String> map) {
            this.posterHeaders = map;
        }

        @Override // com.lagradost.cloudstream3.utils.DataStoreHelper.LibrarySearchResponse
        public String getPlot() {
            return this.plot;
        }

        @Override // com.lagradost.cloudstream3.utils.DataStoreHelper.LibrarySearchResponse, com.lagradost.cloudstream3.SearchResponse
        public Score getScore() {
            return this.score;
        }

        @Override // com.lagradost.cloudstream3.utils.DataStoreHelper.LibrarySearchResponse, com.lagradost.cloudstream3.SearchResponse
        public void setScore(Score score) {
            this.score = score;
        }

        @Override // com.lagradost.cloudstream3.utils.DataStoreHelper.LibrarySearchResponse
        public List<String> getTags() {
            return this.tags;
        }

        public final SyncAPI.LibraryItem toLibraryItem(String id) {
            Intrinsics.checkNotNullParameter(id, "id");
            String name = getName();
            String url = getUrl();
            Long lValueOf = Long.valueOf(getLatestUpdatedTime());
            String apiName = getApiName();
            TvType type = getType();
            String posterUrl = getPosterUrl();
            Map<String, String> posterHeaders = getPosterHeaders();
            SearchQuality quality = getQuality();
            Integer year = getYear();
            return new SyncAPI.LibraryItem(name, url, id, null, null, null, lValueOf, apiName, type, posterUrl, posterHeaders, quality, year != null ? DataStoreHelper.INSTANCE.toYear(year.intValue()) : null, getId(), getPlot(), getScore(), getTags());
        }
    }

    /* JADX INFO: compiled from: DataStoreHelper.kt */
    @Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b'\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B¿\u0001\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\b\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\b\u0010\r\u001a\u0004\u0018\u00010\b\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u0012\u0016\b\u0002\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u0012\u0016\b\u0002\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0016\u0012\u0010\b\u0002\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0018¢\u0006\u0004\b\u0019\u0010\u001aJ\b\u0010?\u001a\u0004\u0018\u00010@J\t\u0010A\u001a\u00020\u0003HÆ\u0003J\u0010\u0010B\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u001eJ\t\u0010C\u001a\u00020\u0003HÆ\u0003J\t\u0010D\u001a\u00020\bHÆ\u0003J\t\u0010E\u001a\u00020\bHÆ\u0003J\t\u0010F\u001a\u00020\bHÆ\u0003J\u000b\u0010G\u001a\u0004\u0018\u00010\fHÆ\u0003J\u000b\u0010H\u001a\u0004\u0018\u00010\bHÆ\u0003J\u0010\u0010I\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u001eJ\u0017\u0010J\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0018\u00010\u0010HÆ\u0003J\u000b\u0010K\u001a\u0004\u0018\u00010\u0012HÆ\u0003J\u0017\u0010L\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0018\u00010\u0010HÆ\u0003J\u000b\u0010M\u001a\u0004\u0018\u00010\bHÆ\u0003J\u000b\u0010N\u001a\u0004\u0018\u00010\u0016HÆ\u0003J\u0011\u0010O\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0018HÆ\u0003JÖ\u0001\u0010P\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\b2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00052\u0016\b\u0002\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0018\u00010\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0016\b\u0002\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0018\u00010\u00102\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0010\b\u0002\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0018HÆ\u0001¢\u0006\u0002\u0010QJ\u0014\u0010R\u001a\u00020S2\b\u0010T\u001a\u0004\u0018\u00010UHÖ\u0083\u0004J\n\u0010V\u001a\u00020\u0005HÖ\u0081\u0004J\n\u0010W\u001a\u00020\bHÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0096\u000e¢\u0006\u0010\n\u0002\u0010!\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u0014\u0010\u0006\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001cR\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0014\u0010\t\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b%\u0010$R\u0014\u0010\n\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010$R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u001c\u0010\r\u001a\u0004\u0018\u00010\bX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010$\"\u0004\b,\u0010-R\u0018\u0010\u000e\u001a\u0004\u0018\u00010\u0005X\u0096\u0004¢\u0006\n\n\u0002\u0010!\u001a\u0004\b.\u0010\u001eR\"\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0018\u00010\u0010X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b/\u00100R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u00102\"\u0004\b3\u00104R(\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0018\u00010\u0010X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u00100\"\u0004\b6\u00107R\u0016\u0010\u0014\u001a\u0004\u0018\u00010\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b8\u0010$R\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010:\"\u0004\b;\u0010<R\u001c\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0018X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b=\u0010>¨\u0006X"}, d2 = {"Lcom/lagradost/cloudstream3/utils/DataStoreHelper$FavoritesData;", "Lcom/lagradost/cloudstream3/utils/DataStoreHelper$LibrarySearchResponse;", "favoritesTime", "", "id", "", "latestUpdatedTime", "name", "", ImagesContract.URL, "apiName", "type", "Lcom/lagradost/cloudstream3/TvType;", "posterUrl", "year", "syncData", "", "quality", "Lcom/lagradost/cloudstream3/SearchQuality;", "posterHeaders", "plot", "score", "Lcom/lagradost/cloudstream3/Score;", "tags", "", "<init>", "(JLjava/lang/Integer;JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/lagradost/cloudstream3/TvType;Ljava/lang/String;Ljava/lang/Integer;Ljava/util/Map;Lcom/lagradost/cloudstream3/SearchQuality;Ljava/util/Map;Ljava/lang/String;Lcom/lagradost/cloudstream3/Score;Ljava/util/List;)V", "getFavoritesTime", "()J", "getId", "()Ljava/lang/Integer;", "setId", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getLatestUpdatedTime", "getName", "()Ljava/lang/String;", "getUrl", "getApiName", "getType", "()Lcom/lagradost/cloudstream3/TvType;", "setType", "(Lcom/lagradost/cloudstream3/TvType;)V", "getPosterUrl", "setPosterUrl", "(Ljava/lang/String;)V", "getYear", "getSyncData", "()Ljava/util/Map;", "getQuality", "()Lcom/lagradost/cloudstream3/SearchQuality;", "setQuality", "(Lcom/lagradost/cloudstream3/SearchQuality;)V", "getPosterHeaders", "setPosterHeaders", "(Ljava/util/Map;)V", "getPlot", "getScore", "()Lcom/lagradost/cloudstream3/Score;", "setScore", "(Lcom/lagradost/cloudstream3/Score;)V", "getTags", "()Ljava/util/List;", "toLibraryItem", "Lcom/lagradost/cloudstream3/syncproviders/SyncAPI$LibraryItem;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "copy", "(JLjava/lang/Integer;JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/lagradost/cloudstream3/TvType;Ljava/lang/String;Ljava/lang/Integer;Ljava/util/Map;Lcom/lagradost/cloudstream3/SearchQuality;Ljava/util/Map;Ljava/lang/String;Lcom/lagradost/cloudstream3/Score;Ljava/util/List;)Lcom/lagradost/cloudstream3/utils/DataStoreHelper$FavoritesData;", "equals", "", "other", "", "hashCode", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class FavoritesData extends LibrarySearchResponse {
        private final String apiName;

        @JsonProperty("favoritesTime")
        private final long favoritesTime;
        private Integer id;
        private final long latestUpdatedTime;
        private final String name;
        private final String plot;
        private Map<String, String> posterHeaders;
        private String posterUrl;
        private SearchQuality quality;
        private Score score;
        private final Map<String, String> syncData;
        private final List<String> tags;
        private TvType type;
        private final String url;
        private final Integer year;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ FavoritesData copy$default(FavoritesData favoritesData, long j, Integer num, long j2, String str, String str2, String str3, TvType tvType, String str4, Integer num2, Map map, SearchQuality searchQuality, Map map2, String str5, Score score, List list, int i, Object obj) {
            long j3 = (i & 1) != 0 ? favoritesData.favoritesTime : j;
            return favoritesData.copy(j3, (i & 2) != 0 ? favoritesData.id : num, (i & 4) != 0 ? favoritesData.latestUpdatedTime : j2, (i & 8) != 0 ? favoritesData.name : str, (i & 16) != 0 ? favoritesData.url : str2, (i & 32) != 0 ? favoritesData.apiName : str3, (i & 64) != 0 ? favoritesData.type : tvType, (i & 128) != 0 ? favoritesData.posterUrl : str4, (i & 256) != 0 ? favoritesData.year : num2, (i & 512) != 0 ? favoritesData.syncData : map, (i & 1024) != 0 ? favoritesData.quality : searchQuality, (i & 2048) != 0 ? favoritesData.posterHeaders : map2, (i & 4096) != 0 ? favoritesData.plot : str5, (i & 8192) != 0 ? favoritesData.score : score, (i & 16384) != 0 ? favoritesData.tags : list);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final long getFavoritesTime() {
            return this.favoritesTime;
        }

        public final Map<String, String> component10() {
            return this.syncData;
        }

        /* JADX INFO: renamed from: component11, reason: from getter */
        public final SearchQuality getQuality() {
            return this.quality;
        }

        public final Map<String, String> component12() {
            return this.posterHeaders;
        }

        /* JADX INFO: renamed from: component13, reason: from getter */
        public final String getPlot() {
            return this.plot;
        }

        /* JADX INFO: renamed from: component14, reason: from getter */
        public final Score getScore() {
            return this.score;
        }

        public final List<String> component15() {
            return this.tags;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Integer getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final long getLatestUpdatedTime() {
            return this.latestUpdatedTime;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getUrl() {
            return this.url;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getApiName() {
            return this.apiName;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final TvType getType() {
            return this.type;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final String getPosterUrl() {
            return this.posterUrl;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final Integer getYear() {
            return this.year;
        }

        public final FavoritesData copy(@JsonProperty("favoritesTime") long favoritesTime, Integer id, long latestUpdatedTime, String name, String url, String apiName, TvType type, String posterUrl, Integer year, Map<String, String> syncData, SearchQuality quality, Map<String, String> posterHeaders, String plot, Score score, List<String> tags) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(apiName, "apiName");
            return new FavoritesData(favoritesTime, id, latestUpdatedTime, name, url, apiName, type, posterUrl, year, syncData, quality, posterHeaders, plot, score, tags);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof FavoritesData)) {
                return false;
            }
            FavoritesData favoritesData = (FavoritesData) other;
            return this.favoritesTime == favoritesData.favoritesTime && Intrinsics.areEqual(this.id, favoritesData.id) && this.latestUpdatedTime == favoritesData.latestUpdatedTime && Intrinsics.areEqual(this.name, favoritesData.name) && Intrinsics.areEqual(this.url, favoritesData.url) && Intrinsics.areEqual(this.apiName, favoritesData.apiName) && this.type == favoritesData.type && Intrinsics.areEqual(this.posterUrl, favoritesData.posterUrl) && Intrinsics.areEqual(this.year, favoritesData.year) && Intrinsics.areEqual(this.syncData, favoritesData.syncData) && this.quality == favoritesData.quality && Intrinsics.areEqual(this.posterHeaders, favoritesData.posterHeaders) && Intrinsics.areEqual(this.plot, favoritesData.plot) && Intrinsics.areEqual(this.score, favoritesData.score) && Intrinsics.areEqual(this.tags, favoritesData.tags);
        }

        public int hashCode() {
            int iM = NextAiring$$ExternalSyntheticBackport0.m(this.favoritesTime) * 31;
            Integer num = this.id;
            int iHashCode = (((((((((iM + (num == null ? 0 : num.hashCode())) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.latestUpdatedTime)) * 31) + this.name.hashCode()) * 31) + this.url.hashCode()) * 31) + this.apiName.hashCode()) * 31;
            TvType tvType = this.type;
            int iHashCode2 = (iHashCode + (tvType == null ? 0 : tvType.hashCode())) * 31;
            String str = this.posterUrl;
            int iHashCode3 = (iHashCode2 + (str == null ? 0 : str.hashCode())) * 31;
            Integer num2 = this.year;
            int iHashCode4 = (iHashCode3 + (num2 == null ? 0 : num2.hashCode())) * 31;
            Map<String, String> map = this.syncData;
            int iHashCode5 = (iHashCode4 + (map == null ? 0 : map.hashCode())) * 31;
            SearchQuality searchQuality = this.quality;
            int iHashCode6 = (iHashCode5 + (searchQuality == null ? 0 : searchQuality.hashCode())) * 31;
            Map<String, String> map2 = this.posterHeaders;
            int iHashCode7 = (iHashCode6 + (map2 == null ? 0 : map2.hashCode())) * 31;
            String str2 = this.plot;
            int iHashCode8 = (iHashCode7 + (str2 == null ? 0 : str2.hashCode())) * 31;
            Score score = this.score;
            int data = (iHashCode8 + (score == null ? 0 : score.getData())) * 31;
            List<String> list = this.tags;
            return data + (list != null ? list.hashCode() : 0);
        }

        public String toString() {
            return "FavoritesData(favoritesTime=" + this.favoritesTime + ", id=" + this.id + ", latestUpdatedTime=" + this.latestUpdatedTime + ", name=" + this.name + ", url=" + this.url + ", apiName=" + this.apiName + ", type=" + this.type + ", posterUrl=" + this.posterUrl + ", year=" + this.year + ", syncData=" + this.syncData + ", quality=" + this.quality + ", posterHeaders=" + this.posterHeaders + ", plot=" + this.plot + ", score=" + this.score + ", tags=" + this.tags + ')';
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public FavoritesData(@JsonProperty("favoritesTime") long j, Integer num, long j2, String name, String url, String apiName, TvType tvType, String str, Integer num2, Map<String, String> map, SearchQuality searchQuality, Map<String, String> map2, String str2, Score score, List<String> list) {
            super(num, j2, name, url, apiName, tvType, str, num2, map, searchQuality, map2, str2, null, null, 12288, null);
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(apiName, "apiName");
            this.favoritesTime = j;
            this.id = num;
            this.latestUpdatedTime = j2;
            this.name = name;
            this.url = url;
            this.apiName = apiName;
            this.type = tvType;
            this.posterUrl = str;
            this.year = num2;
            this.syncData = map;
            this.quality = searchQuality;
            this.posterHeaders = map2;
            this.plot = str2;
            this.score = score;
            this.tags = list;
        }

        public /* synthetic */ FavoritesData(long j, Integer num, long j2, String str, String str2, String str3, TvType tvType, String str4, Integer num2, Map map, SearchQuality searchQuality, Map map2, String str5, Score score, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(j, num, j2, str, str2, str3, tvType, str4, num2, (i & 512) != 0 ? null : map, (i & 1024) != 0 ? null : searchQuality, (i & 2048) != 0 ? null : map2, (i & 4096) != 0 ? null : str5, (i & 8192) != 0 ? null : score, (i & 16384) != 0 ? null : list);
        }

        public final long getFavoritesTime() {
            return this.favoritesTime;
        }

        @Override // com.lagradost.cloudstream3.utils.DataStoreHelper.LibrarySearchResponse, com.lagradost.cloudstream3.SearchResponse
        public Integer getId() {
            return this.id;
        }

        @Override // com.lagradost.cloudstream3.utils.DataStoreHelper.LibrarySearchResponse, com.lagradost.cloudstream3.SearchResponse
        public void setId(Integer num) {
            this.id = num;
        }

        @Override // com.lagradost.cloudstream3.utils.DataStoreHelper.LibrarySearchResponse
        public long getLatestUpdatedTime() {
            return this.latestUpdatedTime;
        }

        @Override // com.lagradost.cloudstream3.utils.DataStoreHelper.LibrarySearchResponse, com.lagradost.cloudstream3.SearchResponse
        public String getName() {
            return this.name;
        }

        @Override // com.lagradost.cloudstream3.utils.DataStoreHelper.LibrarySearchResponse, com.lagradost.cloudstream3.SearchResponse
        public String getUrl() {
            return this.url;
        }

        @Override // com.lagradost.cloudstream3.utils.DataStoreHelper.LibrarySearchResponse, com.lagradost.cloudstream3.SearchResponse
        public String getApiName() {
            return this.apiName;
        }

        @Override // com.lagradost.cloudstream3.utils.DataStoreHelper.LibrarySearchResponse, com.lagradost.cloudstream3.SearchResponse
        public TvType getType() {
            return this.type;
        }

        @Override // com.lagradost.cloudstream3.utils.DataStoreHelper.LibrarySearchResponse, com.lagradost.cloudstream3.SearchResponse
        public void setType(TvType tvType) {
            this.type = tvType;
        }

        @Override // com.lagradost.cloudstream3.utils.DataStoreHelper.LibrarySearchResponse, com.lagradost.cloudstream3.SearchResponse
        public String getPosterUrl() {
            return this.posterUrl;
        }

        @Override // com.lagradost.cloudstream3.utils.DataStoreHelper.LibrarySearchResponse, com.lagradost.cloudstream3.SearchResponse
        public void setPosterUrl(String str) {
            this.posterUrl = str;
        }

        @Override // com.lagradost.cloudstream3.utils.DataStoreHelper.LibrarySearchResponse
        public Integer getYear() {
            return this.year;
        }

        @Override // com.lagradost.cloudstream3.utils.DataStoreHelper.LibrarySearchResponse
        public Map<String, String> getSyncData() {
            return this.syncData;
        }

        @Override // com.lagradost.cloudstream3.utils.DataStoreHelper.LibrarySearchResponse, com.lagradost.cloudstream3.SearchResponse
        public SearchQuality getQuality() {
            return this.quality;
        }

        @Override // com.lagradost.cloudstream3.utils.DataStoreHelper.LibrarySearchResponse, com.lagradost.cloudstream3.SearchResponse
        public void setQuality(SearchQuality searchQuality) {
            this.quality = searchQuality;
        }

        @Override // com.lagradost.cloudstream3.utils.DataStoreHelper.LibrarySearchResponse, com.lagradost.cloudstream3.SearchResponse
        public Map<String, String> getPosterHeaders() {
            return this.posterHeaders;
        }

        @Override // com.lagradost.cloudstream3.utils.DataStoreHelper.LibrarySearchResponse, com.lagradost.cloudstream3.SearchResponse
        public void setPosterHeaders(Map<String, String> map) {
            this.posterHeaders = map;
        }

        @Override // com.lagradost.cloudstream3.utils.DataStoreHelper.LibrarySearchResponse
        public String getPlot() {
            return this.plot;
        }

        @Override // com.lagradost.cloudstream3.utils.DataStoreHelper.LibrarySearchResponse, com.lagradost.cloudstream3.SearchResponse
        public Score getScore() {
            return this.score;
        }

        @Override // com.lagradost.cloudstream3.utils.DataStoreHelper.LibrarySearchResponse, com.lagradost.cloudstream3.SearchResponse
        public void setScore(Score score) {
            this.score = score;
        }

        @Override // com.lagradost.cloudstream3.utils.DataStoreHelper.LibrarySearchResponse
        public List<String> getTags() {
            return this.tags;
        }

        public final SyncAPI.LibraryItem toLibraryItem() {
            String strValueOf;
            String name = getName();
            String url = getUrl();
            Integer id = getId();
            if (id == null || (strValueOf = String.valueOf(id.intValue())) == null) {
                return null;
            }
            Long lValueOf = Long.valueOf(getLatestUpdatedTime());
            String apiName = getApiName();
            TvType type = getType();
            String posterUrl = getPosterUrl();
            Map<String, String> posterHeaders = getPosterHeaders();
            SearchQuality quality = getQuality();
            Integer year = getYear();
            return new SyncAPI.LibraryItem(name, url, strValueOf, null, null, null, lValueOf, apiName, type, posterUrl, posterHeaders, quality, year != null ? DataStoreHelper.INSTANCE.toYear(year.intValue()) : null, getId(), getPlot(), getScore(), getTags());
        }
    }

    /* JADX INFO: compiled from: DataStoreHelper.kt */
    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\b6\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B³\u0001\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0003\u0012\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\n\u0012\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\n\b\u0001\u0010\r\u001a\u0004\u0018\u00010\f\u0012\n\b\u0001\u0010\u000e\u001a\u0004\u0018\u00010\f\u0012\n\b\u0001\u0010\u000f\u001a\u0004\u0018\u00010\f\u0012\b\b\u0001\u0010\u0010\u001a\u00020\u0011\u0012\n\b\u0003\u0010\u0012\u001a\u0004\u0018\u00010\u0013\u0012\u0016\b\u0003\u0010\u0014\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0015\u0012\n\b\u0003\u0010\u0016\u001a\u0004\u0018\u00010\u0017¢\u0006\u0004\b\u0018\u0010\u0019J\t\u0010<\u001a\u00020\u0003HÆ\u0003J\t\u0010=\u001a\u00020\u0003HÆ\u0003J\t\u0010>\u001a\u00020\u0003HÆ\u0003J\u000b\u0010?\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010@\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010A\u001a\u0004\u0018\u00010\nHÆ\u0003J\u0010\u0010B\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0002\u0010(J\u0010\u0010C\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0002\u0010(J\u0010\u0010D\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0002\u0010(J\u0010\u0010E\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0002\u0010(J\t\u0010F\u001a\u00020\u0011HÆ\u0003J\u000b\u0010G\u001a\u0004\u0018\u00010\u0013HÆ\u0003J\u0017\u0010H\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0015HÆ\u0003J\u000b\u0010I\u001a\u0004\u0018\u00010\u0017HÆ\u0003Jº\u0001\u0010J\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u00032\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0003\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0003\u0010\r\u001a\u0004\u0018\u00010\f2\n\b\u0003\u0010\u000e\u001a\u0004\u0018\u00010\f2\n\b\u0003\u0010\u000f\u001a\u0004\u0018\u00010\f2\b\b\u0003\u0010\u0010\u001a\u00020\u00112\n\b\u0003\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0016\b\u0003\u0010\u0014\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00152\n\b\u0003\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÆ\u0001¢\u0006\u0002\u0010KJ\u0014\u0010L\u001a\u00020\u00112\b\u0010M\u001a\u0004\u0018\u00010NHÖ\u0083\u0004J\n\u0010O\u001a\u00020\fHÖ\u0081\u0004J\n\u0010P\u001a\u00020\u0003HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0016X\u0097\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0016\u0010\u0004\u001a\u00020\u00038\u0016X\u0097\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001bR\u0016\u0010\u0005\u001a\u00020\u00038\u0016X\u0097\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001bR \u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0016@\u0016X\u0097\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R \u0010\b\u001a\u0004\u0018\u00010\u00038\u0016@\u0016X\u0097\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u001b\"\u0004\b#\u0010$R\u0018\u0010\t\u001a\u0004\u0018\u00010\n8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\"\u0010\u000b\u001a\u0004\u0018\u00010\f8\u0016@\u0016X\u0097\u000e¢\u0006\u0010\n\u0002\u0010+\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u001a\u0010\r\u001a\u0004\u0018\u00010\f8\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010+\u001a\u0004\b,\u0010(R\u001a\u0010\u000e\u001a\u0004\u0018\u00010\f8\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010+\u001a\u0004\b-\u0010(R\u001a\u0010\u000f\u001a\u0004\u0018\u00010\f8\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010+\u001a\u0004\b.\u0010(R\u0016\u0010\u0010\u001a\u00020\u00118\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010/R \u0010\u0012\u001a\u0004\u0018\u00010\u00138\u0016@\u0016X\u0097\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u00101\"\u0004\b2\u00103R,\u0010\u0014\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00158\u0016@\u0016X\u0097\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u00105\"\u0004\b6\u00107R \u0010\u0016\u001a\u0004\u0018\u00010\u00178\u0016@\u0016X\u0097\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u00109\"\u0004\b:\u0010;¨\u0006Q"}, d2 = {"Lcom/lagradost/cloudstream3/utils/DataStoreHelper$ResumeWatchingResult;", "Lcom/lagradost/cloudstream3/SearchResponse;", "name", "", ImagesContract.URL, "apiName", "type", "Lcom/lagradost/cloudstream3/TvType;", "posterUrl", "watchPos", "Lcom/lagradost/cloudstream3/utils/DataStoreHelper$PosDur;", "id", "", "parentId", "episode", "season", "isFromDownload", "", "quality", "Lcom/lagradost/cloudstream3/SearchQuality;", "posterHeaders", "", "score", "Lcom/lagradost/cloudstream3/Score;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/lagradost/cloudstream3/TvType;Ljava/lang/String;Lcom/lagradost/cloudstream3/utils/DataStoreHelper$PosDur;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;ZLcom/lagradost/cloudstream3/SearchQuality;Ljava/util/Map;Lcom/lagradost/cloudstream3/Score;)V", "getName", "()Ljava/lang/String;", "getUrl", "getApiName", "getType", "()Lcom/lagradost/cloudstream3/TvType;", "setType", "(Lcom/lagradost/cloudstream3/TvType;)V", "getPosterUrl", "setPosterUrl", "(Ljava/lang/String;)V", "getWatchPos", "()Lcom/lagradost/cloudstream3/utils/DataStoreHelper$PosDur;", "getId", "()Ljava/lang/Integer;", "setId", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getParentId", "getEpisode", "getSeason", "()Z", "getQuality", "()Lcom/lagradost/cloudstream3/SearchQuality;", "setQuality", "(Lcom/lagradost/cloudstream3/SearchQuality;)V", "getPosterHeaders", "()Ljava/util/Map;", "setPosterHeaders", "(Ljava/util/Map;)V", "getScore", "()Lcom/lagradost/cloudstream3/Score;", "setScore", "(Lcom/lagradost/cloudstream3/Score;)V", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/lagradost/cloudstream3/TvType;Ljava/lang/String;Lcom/lagradost/cloudstream3/utils/DataStoreHelper$PosDur;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;ZLcom/lagradost/cloudstream3/SearchQuality;Ljava/util/Map;Lcom/lagradost/cloudstream3/Score;)Lcom/lagradost/cloudstream3/utils/DataStoreHelper$ResumeWatchingResult;", "equals", "other", "", "hashCode", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class ResumeWatchingResult implements SearchResponse {

        @JsonProperty("apiName")
        private final String apiName;

        @JsonProperty("episode")
        private final Integer episode;

        @JsonProperty("id")
        private Integer id;

        @JsonProperty("isFromDownload")
        private final boolean isFromDownload;

        @JsonProperty("name")
        private final String name;

        @JsonProperty("parentId")
        private final Integer parentId;

        @JsonProperty("posterHeaders")
        private Map<String, String> posterHeaders;

        @JsonProperty("posterUrl")
        private String posterUrl;

        @JsonProperty("quality")
        private SearchQuality quality;

        @JsonProperty("score")
        private Score score;

        @JsonProperty("season")
        private final Integer season;

        @JsonProperty("type")
        private TvType type;

        @JsonProperty(ImagesContract.URL)
        private final String url;

        @JsonProperty("watchPos")
        private final PosDur watchPos;

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component10, reason: from getter */
        public final Integer getSeason() {
            return this.season;
        }

        /* JADX INFO: renamed from: component11, reason: from getter */
        public final boolean getIsFromDownload() {
            return this.isFromDownload;
        }

        /* JADX INFO: renamed from: component12, reason: from getter */
        public final SearchQuality getQuality() {
            return this.quality;
        }

        public final Map<String, String> component13() {
            return this.posterHeaders;
        }

        /* JADX INFO: renamed from: component14, reason: from getter */
        public final Score getScore() {
            return this.score;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getUrl() {
            return this.url;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getApiName() {
            return this.apiName;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final TvType getType() {
            return this.type;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getPosterUrl() {
            return this.posterUrl;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final PosDur getWatchPos() {
            return this.watchPos;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final Integer getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final Integer getParentId() {
            return this.parentId;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final Integer getEpisode() {
            return this.episode;
        }

        public final ResumeWatchingResult copy(@JsonProperty("name") String name, @JsonProperty(ImagesContract.URL) String url, @JsonProperty("apiName") String apiName, @JsonProperty("type") TvType type, @JsonProperty("posterUrl") String posterUrl, @JsonProperty("watchPos") PosDur watchPos, @JsonProperty("id") Integer id, @JsonProperty("parentId") Integer parentId, @JsonProperty("episode") Integer episode, @JsonProperty("season") Integer season, @JsonProperty("isFromDownload") boolean isFromDownload, @JsonProperty("quality") SearchQuality quality, @JsonProperty("posterHeaders") Map<String, String> posterHeaders, @JsonProperty("score") Score score) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(apiName, "apiName");
            return new ResumeWatchingResult(name, url, apiName, type, posterUrl, watchPos, id, parentId, episode, season, isFromDownload, quality, posterHeaders, score);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ResumeWatchingResult)) {
                return false;
            }
            ResumeWatchingResult resumeWatchingResult = (ResumeWatchingResult) other;
            return Intrinsics.areEqual(this.name, resumeWatchingResult.name) && Intrinsics.areEqual(this.url, resumeWatchingResult.url) && Intrinsics.areEqual(this.apiName, resumeWatchingResult.apiName) && this.type == resumeWatchingResult.type && Intrinsics.areEqual(this.posterUrl, resumeWatchingResult.posterUrl) && Intrinsics.areEqual(this.watchPos, resumeWatchingResult.watchPos) && Intrinsics.areEqual(this.id, resumeWatchingResult.id) && Intrinsics.areEqual(this.parentId, resumeWatchingResult.parentId) && Intrinsics.areEqual(this.episode, resumeWatchingResult.episode) && Intrinsics.areEqual(this.season, resumeWatchingResult.season) && this.isFromDownload == resumeWatchingResult.isFromDownload && this.quality == resumeWatchingResult.quality && Intrinsics.areEqual(this.posterHeaders, resumeWatchingResult.posterHeaders) && Intrinsics.areEqual(this.score, resumeWatchingResult.score);
        }

        public int hashCode() {
            int iHashCode = ((((this.name.hashCode() * 31) + this.url.hashCode()) * 31) + this.apiName.hashCode()) * 31;
            TvType tvType = this.type;
            int iHashCode2 = (iHashCode + (tvType == null ? 0 : tvType.hashCode())) * 31;
            String str = this.posterUrl;
            int iHashCode3 = (iHashCode2 + (str == null ? 0 : str.hashCode())) * 31;
            PosDur posDur = this.watchPos;
            int iHashCode4 = (iHashCode3 + (posDur == null ? 0 : posDur.hashCode())) * 31;
            Integer num = this.id;
            int iHashCode5 = (iHashCode4 + (num == null ? 0 : num.hashCode())) * 31;
            Integer num2 = this.parentId;
            int iHashCode6 = (iHashCode5 + (num2 == null ? 0 : num2.hashCode())) * 31;
            Integer num3 = this.episode;
            int iHashCode7 = (iHashCode6 + (num3 == null ? 0 : num3.hashCode())) * 31;
            Integer num4 = this.season;
            int iHashCode8 = (((iHashCode7 + (num4 == null ? 0 : num4.hashCode())) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.isFromDownload)) * 31;
            SearchQuality searchQuality = this.quality;
            int iHashCode9 = (iHashCode8 + (searchQuality == null ? 0 : searchQuality.hashCode())) * 31;
            Map<String, String> map = this.posterHeaders;
            int iHashCode10 = (iHashCode9 + (map == null ? 0 : map.hashCode())) * 31;
            Score score = this.score;
            return iHashCode10 + (score != null ? score.getData() : 0);
        }

        public String toString() {
            return "ResumeWatchingResult(name=" + this.name + ", url=" + this.url + ", apiName=" + this.apiName + ", type=" + this.type + ", posterUrl=" + this.posterUrl + ", watchPos=" + this.watchPos + ", id=" + this.id + ", parentId=" + this.parentId + ", episode=" + this.episode + ", season=" + this.season + ", isFromDownload=" + this.isFromDownload + ", quality=" + this.quality + ", posterHeaders=" + this.posterHeaders + ", score=" + this.score + ')';
        }

        public ResumeWatchingResult(@JsonProperty("name") String name, @JsonProperty(ImagesContract.URL) String url, @JsonProperty("apiName") String apiName, @JsonProperty("type") TvType tvType, @JsonProperty("posterUrl") String str, @JsonProperty("watchPos") PosDur posDur, @JsonProperty("id") Integer num, @JsonProperty("parentId") Integer num2, @JsonProperty("episode") Integer num3, @JsonProperty("season") Integer num4, @JsonProperty("isFromDownload") boolean z, @JsonProperty("quality") SearchQuality searchQuality, @JsonProperty("posterHeaders") Map<String, String> map, @JsonProperty("score") Score score) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(apiName, "apiName");
            this.name = name;
            this.url = url;
            this.apiName = apiName;
            this.type = tvType;
            this.posterUrl = str;
            this.watchPos = posDur;
            this.id = num;
            this.parentId = num2;
            this.episode = num3;
            this.season = num4;
            this.isFromDownload = z;
            this.quality = searchQuality;
            this.posterHeaders = map;
            this.score = score;
        }

        public /* synthetic */ ResumeWatchingResult(String str, String str2, String str3, TvType tvType, String str4, PosDur posDur, Integer num, Integer num2, Integer num3, Integer num4, boolean z, SearchQuality searchQuality, Map map, Score score, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, str3, (i & 8) != 0 ? null : tvType, str4, posDur, num, num2, num3, num4, z, (i & 2048) != 0 ? null : searchQuality, (i & 4096) != 0 ? null : map, (i & 8192) != 0 ? null : score);
        }

        @Override // com.lagradost.cloudstream3.SearchResponse
        public String getName() {
            return this.name;
        }

        @Override // com.lagradost.cloudstream3.SearchResponse
        public String getUrl() {
            return this.url;
        }

        @Override // com.lagradost.cloudstream3.SearchResponse
        public String getApiName() {
            return this.apiName;
        }

        @Override // com.lagradost.cloudstream3.SearchResponse
        public TvType getType() {
            return this.type;
        }

        @Override // com.lagradost.cloudstream3.SearchResponse
        public void setType(TvType tvType) {
            this.type = tvType;
        }

        @Override // com.lagradost.cloudstream3.SearchResponse
        public String getPosterUrl() {
            return this.posterUrl;
        }

        @Override // com.lagradost.cloudstream3.SearchResponse
        public void setPosterUrl(String str) {
            this.posterUrl = str;
        }

        public final PosDur getWatchPos() {
            return this.watchPos;
        }

        @Override // com.lagradost.cloudstream3.SearchResponse
        public Integer getId() {
            return this.id;
        }

        @Override // com.lagradost.cloudstream3.SearchResponse
        public void setId(Integer num) {
            this.id = num;
        }

        public final Integer getParentId() {
            return this.parentId;
        }

        public final Integer getEpisode() {
            return this.episode;
        }

        public final Integer getSeason() {
            return this.season;
        }

        public final boolean isFromDownload() {
            return this.isFromDownload;
        }

        @Override // com.lagradost.cloudstream3.SearchResponse
        public SearchQuality getQuality() {
            return this.quality;
        }

        @Override // com.lagradost.cloudstream3.SearchResponse
        public void setQuality(SearchQuality searchQuality) {
            this.quality = searchQuality;
        }

        @Override // com.lagradost.cloudstream3.SearchResponse
        public Map<String, String> getPosterHeaders() {
            return this.posterHeaders;
        }

        @Override // com.lagradost.cloudstream3.SearchResponse
        public void setPosterHeaders(Map<String, String> map) {
            this.posterHeaders = map;
        }

        @Override // com.lagradost.cloudstream3.SearchResponse
        public Score getScore() {
            return this.score;
        }

        @Override // com.lagradost.cloudstream3.SearchResponse
        public void setScore(Score score) {
            this.score = score;
        }
    }

    public final List<Integer> getAllWatchStateIds() {
        String str = m8635getCurrentAccount() + "/result_watch_state";
        List<String> keys = CloudStreamApp.INSTANCE.getKeys(str);
        if (keys == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = keys.iterator();
        while (it.hasNext()) {
            Integer intOrNull = StringsKt.toIntOrNull(StringsKt.removePrefix((String) it.next(), (CharSequence) (str + '/')));
            if (intOrNull != null) {
                arrayList.add(intOrNull);
            }
        }
        return arrayList;
    }

    public final void deleteAllResumeStateIds() {
        CloudStreamApp.INSTANCE.removeKeys(m8635getCurrentAccount() + "/result_resume_watching_2");
    }

    public final void deleteBookmarkedData(Integer id) {
        if (id == null) {
            return;
        }
        AccountManager.INSTANCE.getLocalListApi().setRequireLibraryRefresh(true);
        CloudStreamApp.INSTANCE.removeKey(m8635getCurrentAccount() + "/result_watch_state", String.valueOf(id.intValue()));
        CloudStreamApp.INSTANCE.removeKey(m8635getCurrentAccount() + "/result_watch_state_data", String.valueOf(id.intValue()));
    }

    public final List<Integer> getAllResumeStateIds() {
        String str = m8635getCurrentAccount() + "/result_resume_watching_2";
        List<String> keys = CloudStreamApp.INSTANCE.getKeys(str);
        if (keys == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = keys.iterator();
        while (it.hasNext()) {
            Integer intOrNull = StringsKt.toIntOrNull(StringsKt.removePrefix((String) it.next(), (CharSequence) (str + '/')));
            if (intOrNull != null) {
                arrayList.add(intOrNull);
            }
        }
        return arrayList;
    }

    private final List<Integer> getAllResumeStateIdsOld() {
        String str = m8635getCurrentAccount() + "/result_resume_watching";
        List<String> keys = CloudStreamApp.INSTANCE.getKeys(str);
        if (keys == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = keys.iterator();
        while (it.hasNext()) {
            Integer intOrNull = StringsKt.toIntOrNull(StringsKt.removePrefix((String) it.next(), (CharSequence) (str + '/')));
            if (intOrNull != null) {
                arrayList.add(intOrNull);
            }
        }
        return arrayList;
    }

    public final void migrateResumeWatching() {
        CloudStreamApp.INSTANCE.setKey(DataStoreHelperKt.RESULT_RESUME_WATCHING_HAS_MIGRATED, true);
        List<Integer> allResumeStateIdsOld = getAllResumeStateIdsOld();
        if (allResumeStateIdsOld != null) {
            Iterator<T> it = allResumeStateIdsOld.iterator();
            while (it.hasNext()) {
                int iIntValue = ((Number) it.next()).intValue();
                DataStoreHelper dataStoreHelper = INSTANCE;
                DownloadObjects.ResumeWatching lastWatchedOld = dataStoreHelper.getLastWatchedOld(Integer.valueOf(iIntValue));
                if (lastWatchedOld != null) {
                    dataStoreHelper.setLastWatched(Integer.valueOf(lastWatchedOld.getParentId()), null, lastWatchedOld.getEpisode(), lastWatchedOld.getSeason(), lastWatchedOld.isFromDownload(), Long.valueOf(lastWatchedOld.getUpdateTime()));
                    dataStoreHelper.removeLastWatchedOld(Integer.valueOf(lastWatchedOld.getParentId()));
                }
            }
        }
    }

    public static /* synthetic */ void setLastWatched$default(DataStoreHelper dataStoreHelper, Integer num, Integer num2, Integer num3, Integer num4, boolean z, Long l, int i, Object obj) {
        if ((i & 16) != 0) {
            z = false;
        }
        boolean z2 = z;
        if ((i & 32) != 0) {
            l = null;
        }
        dataStoreHelper.setLastWatched(num, num2, num3, num4, z2, l);
    }

    public final void setLastWatched(Integer parentId, Integer episodeId, Integer episode, Integer season, boolean isFromDownload, Long updateTime) {
        if (parentId == null) {
            return;
        }
        CloudStreamApp.INSTANCE.setKey(m8635getCurrentAccount() + "/result_resume_watching_2", String.valueOf(parentId.intValue()), new DownloadObjects.ResumeWatching(parentId.intValue(), episodeId, episode, season, updateTime != null ? updateTime.longValue() : System.currentTimeMillis(), isFromDownload));
    }

    private final void removeLastWatchedOld(Integer parentId) {
        if (parentId == null) {
            return;
        }
        CloudStreamApp.INSTANCE.removeKey(m8635getCurrentAccount() + "/result_resume_watching", String.valueOf(parentId.intValue()));
    }

    public final void removeLastWatched(Integer parentId) {
        if (parentId == null) {
            return;
        }
        CloudStreamApp.INSTANCE.removeKey(m8635getCurrentAccount() + "/result_resume_watching_2", String.valueOf(parentId.intValue()));
    }

    public final DownloadObjects.ResumeWatching getLastWatched(Integer id) {
        Object objM8936constructorimpl;
        Object objDecodeFromString;
        Object obj = null;
        if (id == null) {
            return null;
        }
        CloudStreamApp.Companion companion = CloudStreamApp.INSTANCE;
        String str = m8635getCurrentAccount() + "/result_resume_watching_2";
        String strValueOf = String.valueOf(id.intValue());
        Context context = companion.getContext();
        if (context != null) {
            DataStore dataStore = DataStore.INSTANCE;
            try {
                String string = dataStore.getSharedPrefs(context).getString(dataStore.getFolderName(str, strValueOf), null);
                if (string != null) {
                    AppUtils appUtils = AppUtils.INSTANCE;
                    try {
                        Result.Companion companion2 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(SerializersKt.noCompiledSerializer("com.lagradost.cloudstream3.utils.downloader.DownloadObjects.ResumeWatching"));
                    } catch (Throwable th) {
                        Result.Companion companion3 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                    }
                    if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) != null) {
                        try {
                            Result.Companion companion4 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(DownloadObjects.ResumeWatching.class), null, 2, null));
                        } catch (Throwable th2) {
                            Result.Companion companion5 = Result.INSTANCE;
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
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<DownloadObjects.ResumeWatching>() { // from class: com.lagradost.cloudstream3.utils.DataStoreHelper$getLastWatched$$inlined$getKey$1
                            });
                        } catch (Throwable unused) {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<DownloadObjects.ResumeWatching>() { // from class: com.lagradost.cloudstream3.utils.DataStoreHelper$getLastWatched$$inlined$getKey$1
                            });
                        }
                    } else {
                        objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<DownloadObjects.ResumeWatching>() { // from class: com.lagradost.cloudstream3.utils.DataStoreHelper$getLastWatched$$inlined$getKey$1
                        });
                    }
                    obj = objDecodeFromString;
                }
            } catch (Exception unused2) {
            }
        }
        return (DownloadObjects.ResumeWatching) obj;
    }

    private final DownloadObjects.ResumeWatching getLastWatchedOld(Integer id) {
        Object objM8936constructorimpl;
        Object objDecodeFromString;
        Object obj = null;
        if (id == null) {
            return null;
        }
        CloudStreamApp.Companion companion = CloudStreamApp.INSTANCE;
        String str = m8635getCurrentAccount() + "/result_resume_watching";
        String strValueOf = String.valueOf(id.intValue());
        Context context = companion.getContext();
        if (context != null) {
            DataStore dataStore = DataStore.INSTANCE;
            try {
                String string = dataStore.getSharedPrefs(context).getString(dataStore.getFolderName(str, strValueOf), null);
                if (string != null) {
                    AppUtils appUtils = AppUtils.INSTANCE;
                    try {
                        Result.Companion companion2 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(SerializersKt.noCompiledSerializer("com.lagradost.cloudstream3.utils.downloader.DownloadObjects.ResumeWatching"));
                    } catch (Throwable th) {
                        Result.Companion companion3 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                    }
                    if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) != null) {
                        try {
                            Result.Companion companion4 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(DownloadObjects.ResumeWatching.class), null, 2, null));
                        } catch (Throwable th2) {
                            Result.Companion companion5 = Result.INSTANCE;
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
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<DownloadObjects.ResumeWatching>() { // from class: com.lagradost.cloudstream3.utils.DataStoreHelper$getLastWatchedOld$$inlined$getKey$1
                            });
                        } catch (Throwable unused) {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<DownloadObjects.ResumeWatching>() { // from class: com.lagradost.cloudstream3.utils.DataStoreHelper$getLastWatchedOld$$inlined$getKey$1
                            });
                        }
                    } else {
                        objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<DownloadObjects.ResumeWatching>() { // from class: com.lagradost.cloudstream3.utils.DataStoreHelper$getLastWatchedOld$$inlined$getKey$1
                        });
                    }
                    obj = objDecodeFromString;
                }
            } catch (Exception unused2) {
            }
        }
        return (DownloadObjects.ResumeWatching) obj;
    }

    public final void setBookmarkedData(Integer id, BookmarkedData data) {
        Intrinsics.checkNotNullParameter(data, "data");
        if (id == null) {
            return;
        }
        CloudStreamApp.INSTANCE.setKey(m8635getCurrentAccount() + "/result_watch_state_data", String.valueOf(id.intValue()), data);
        AccountManager.INSTANCE.getLocalListApi().setRequireLibraryRefresh(true);
    }

    public final BookmarkedData getBookmarkedData(Integer id) {
        Object objM8936constructorimpl;
        Object objDecodeFromString;
        Object obj = null;
        if (id == null) {
            return null;
        }
        CloudStreamApp.Companion companion = CloudStreamApp.INSTANCE;
        String str = m8635getCurrentAccount() + "/result_watch_state_data";
        String strValueOf = String.valueOf(id.intValue());
        Context context = companion.getContext();
        if (context != null) {
            DataStore dataStore = DataStore.INSTANCE;
            try {
                String string = dataStore.getSharedPrefs(context).getString(dataStore.getFolderName(str, strValueOf), null);
                if (string != null) {
                    AppUtils appUtils = AppUtils.INSTANCE;
                    try {
                        Result.Companion companion2 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(SerializersKt.noCompiledSerializer("com.lagradost.cloudstream3.utils.DataStoreHelper.BookmarkedData"));
                    } catch (Throwable th) {
                        Result.Companion companion3 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                    }
                    if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) != null) {
                        try {
                            Result.Companion companion4 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(BookmarkedData.class), null, 2, null));
                        } catch (Throwable th2) {
                            Result.Companion companion5 = Result.INSTANCE;
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
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<BookmarkedData>() { // from class: com.lagradost.cloudstream3.utils.DataStoreHelper$getBookmarkedData$$inlined$getKey$1
                            });
                        } catch (Throwable unused) {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<BookmarkedData>() { // from class: com.lagradost.cloudstream3.utils.DataStoreHelper$getBookmarkedData$$inlined$getKey$1
                            });
                        }
                    } else {
                        objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<BookmarkedData>() { // from class: com.lagradost.cloudstream3.utils.DataStoreHelper$getBookmarkedData$$inlined$getKey$1
                        });
                    }
                    obj = objDecodeFromString;
                }
            } catch (Exception unused2) {
            }
        }
        return (BookmarkedData) obj;
    }

    public final List<BookmarkedData> getAllBookmarkedData() {
        Object objM8936constructorimpl;
        Object objDecodeFromString;
        List<String> keys = CloudStreamApp.INSTANCE.getKeys(m8635getCurrentAccount() + "/result_watch_state_data");
        if (keys == null) {
            return CollectionsKt.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (String str : keys) {
            Context context = CloudStreamApp.INSTANCE.getContext();
            Object obj = null;
            if (context != null) {
                try {
                    String string = DataStore.INSTANCE.getSharedPrefs(context).getString(str, null);
                    if (string != null) {
                        AppUtils appUtils = AppUtils.INSTANCE;
                        try {
                            Result.Companion companion = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(SerializersKt.noCompiledSerializer("com.lagradost.cloudstream3.utils.DataStoreHelper.BookmarkedData"));
                        } catch (Throwable th) {
                            Result.Companion companion2 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                        }
                        if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) != null) {
                            try {
                                Result.Companion companion3 = Result.INSTANCE;
                                objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(BookmarkedData.class), null, 2, null));
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
                                objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<BookmarkedData>() { // from class: com.lagradost.cloudstream3.utils.DataStoreHelper$getAllBookmarkedData$lambda$0$$inlined$getKey$1
                                });
                            } catch (Throwable unused) {
                                objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<BookmarkedData>() { // from class: com.lagradost.cloudstream3.utils.DataStoreHelper$getAllBookmarkedData$lambda$0$$inlined$getKey$1
                                });
                            }
                        } else {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<BookmarkedData>() { // from class: com.lagradost.cloudstream3.utils.DataStoreHelper$getAllBookmarkedData$lambda$0$$inlined$getKey$1
                            });
                        }
                        obj = objDecodeFromString;
                    }
                } catch (Exception unused2) {
                }
            }
            BookmarkedData bookmarkedData = (BookmarkedData) obj;
            if (bookmarkedData != null) {
                arrayList.add(bookmarkedData);
            }
        }
        return arrayList;
    }

    public final List<SubscribedData> getAllSubscriptions() {
        Object objM8936constructorimpl;
        Object objDecodeFromString;
        List<String> keys = CloudStreamApp.INSTANCE.getKeys(m8635getCurrentAccount() + "/result_subscribed_state_data");
        if (keys == null) {
            return CollectionsKt.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (String str : keys) {
            Context context = CloudStreamApp.INSTANCE.getContext();
            Object obj = null;
            if (context != null) {
                try {
                    String string = DataStore.INSTANCE.getSharedPrefs(context).getString(str, null);
                    if (string != null) {
                        AppUtils appUtils = AppUtils.INSTANCE;
                        try {
                            Result.Companion companion = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(SerializersKt.noCompiledSerializer("com.lagradost.cloudstream3.utils.DataStoreHelper.SubscribedData"));
                        } catch (Throwable th) {
                            Result.Companion companion2 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                        }
                        if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) != null) {
                            try {
                                Result.Companion companion3 = Result.INSTANCE;
                                objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(SubscribedData.class), null, 2, null));
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
                                objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<SubscribedData>() { // from class: com.lagradost.cloudstream3.utils.DataStoreHelper$getAllSubscriptions$lambda$0$$inlined$getKey$1
                                });
                            } catch (Throwable unused) {
                                objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<SubscribedData>() { // from class: com.lagradost.cloudstream3.utils.DataStoreHelper$getAllSubscriptions$lambda$0$$inlined$getKey$1
                                });
                            }
                        } else {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<SubscribedData>() { // from class: com.lagradost.cloudstream3.utils.DataStoreHelper$getAllSubscriptions$lambda$0$$inlined$getKey$1
                            });
                        }
                        obj = objDecodeFromString;
                    }
                } catch (Exception unused2) {
                }
            }
            SubscribedData subscribedData = (SubscribedData) obj;
            if (subscribedData != null) {
                arrayList.add(subscribedData);
            }
        }
        return arrayList;
    }

    public final void removeSubscribedData(Integer id) {
        if (id == null) {
            return;
        }
        AccountManager.INSTANCE.getLocalListApi().setRequireLibraryRefresh(true);
        CloudStreamApp.INSTANCE.removeKey(m8635getCurrentAccount() + "/result_subscribed_state_data", String.valueOf(id.intValue()));
    }

    public final void updateSubscribedData(Integer id, SubscribedData data, EpisodeResponse episodeResponse) {
        if (id == null || data == null || episodeResponse == null) {
            return;
        }
        SubscribedData subscribedDataCopy$default = SubscribedData.copy$default(data, 0L, episodeResponse.getLatestEpisodes(), null, APIHolder.INSTANCE.getUnixTimeMS(), null, null, null, null, null, null, null, null, null, null, null, null, 65525, null);
        CloudStreamApp.INSTANCE.setKey(m8635getCurrentAccount() + "/result_subscribed_state_data", String.valueOf(id.intValue()), subscribedDataCopy$default);
    }

    public final void setSubscribedData(Integer id, SubscribedData data) {
        Intrinsics.checkNotNullParameter(data, "data");
        if (id == null) {
            return;
        }
        CloudStreamApp.INSTANCE.setKey(m8635getCurrentAccount() + "/result_subscribed_state_data", String.valueOf(id.intValue()), data);
        AccountManager.INSTANCE.getLocalListApi().setRequireLibraryRefresh(true);
    }

    public final SubscribedData getSubscribedData(Integer id) {
        Object objM8936constructorimpl;
        Object objDecodeFromString;
        Object obj = null;
        if (id == null) {
            return null;
        }
        CloudStreamApp.Companion companion = CloudStreamApp.INSTANCE;
        String str = m8635getCurrentAccount() + "/result_subscribed_state_data";
        String strValueOf = String.valueOf(id.intValue());
        Context context = companion.getContext();
        if (context != null) {
            DataStore dataStore = DataStore.INSTANCE;
            try {
                String string = dataStore.getSharedPrefs(context).getString(dataStore.getFolderName(str, strValueOf), null);
                if (string != null) {
                    AppUtils appUtils = AppUtils.INSTANCE;
                    try {
                        Result.Companion companion2 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(SerializersKt.noCompiledSerializer("com.lagradost.cloudstream3.utils.DataStoreHelper.SubscribedData"));
                    } catch (Throwable th) {
                        Result.Companion companion3 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                    }
                    if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) != null) {
                        try {
                            Result.Companion companion4 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(SubscribedData.class), null, 2, null));
                        } catch (Throwable th2) {
                            Result.Companion companion5 = Result.INSTANCE;
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
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<SubscribedData>() { // from class: com.lagradost.cloudstream3.utils.DataStoreHelper$getSubscribedData$$inlined$getKey$1
                            });
                        } catch (Throwable unused) {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<SubscribedData>() { // from class: com.lagradost.cloudstream3.utils.DataStoreHelper$getSubscribedData$$inlined$getKey$1
                            });
                        }
                    } else {
                        objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<SubscribedData>() { // from class: com.lagradost.cloudstream3.utils.DataStoreHelper$getSubscribedData$$inlined$getKey$1
                        });
                    }
                    obj = objDecodeFromString;
                }
            } catch (Exception unused2) {
            }
        }
        return (SubscribedData) obj;
    }

    public final List<FavoritesData> getAllFavorites() {
        Object objM8936constructorimpl;
        Object objDecodeFromString;
        List<String> keys = CloudStreamApp.INSTANCE.getKeys(m8635getCurrentAccount() + "/result_favorites_state_data");
        if (keys == null) {
            return CollectionsKt.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (String str : keys) {
            Context context = CloudStreamApp.INSTANCE.getContext();
            Object obj = null;
            if (context != null) {
                try {
                    String string = DataStore.INSTANCE.getSharedPrefs(context).getString(str, null);
                    if (string != null) {
                        AppUtils appUtils = AppUtils.INSTANCE;
                        try {
                            Result.Companion companion = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(SerializersKt.noCompiledSerializer("com.lagradost.cloudstream3.utils.DataStoreHelper.FavoritesData"));
                        } catch (Throwable th) {
                            Result.Companion companion2 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                        }
                        if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) != null) {
                            try {
                                Result.Companion companion3 = Result.INSTANCE;
                                objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(FavoritesData.class), null, 2, null));
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
                                objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<FavoritesData>() { // from class: com.lagradost.cloudstream3.utils.DataStoreHelper$getAllFavorites$lambda$0$$inlined$getKey$1
                                });
                            } catch (Throwable unused) {
                                objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<FavoritesData>() { // from class: com.lagradost.cloudstream3.utils.DataStoreHelper$getAllFavorites$lambda$0$$inlined$getKey$1
                                });
                            }
                        } else {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<FavoritesData>() { // from class: com.lagradost.cloudstream3.utils.DataStoreHelper$getAllFavorites$lambda$0$$inlined$getKey$1
                            });
                        }
                        obj = objDecodeFromString;
                    }
                } catch (Exception unused2) {
                }
            }
            FavoritesData favoritesData = (FavoritesData) obj;
            if (favoritesData != null) {
                arrayList.add(favoritesData);
            }
        }
        return arrayList;
    }

    public final void removeFavoritesData(Integer id) {
        if (id == null) {
            return;
        }
        AccountManager.INSTANCE.getLocalListApi().setRequireLibraryRefresh(true);
        CloudStreamApp.INSTANCE.removeKey(m8635getCurrentAccount() + "/result_favorites_state_data", String.valueOf(id.intValue()));
    }

    public final void setFavoritesData(Integer id, FavoritesData data) {
        Intrinsics.checkNotNullParameter(data, "data");
        if (id == null) {
            return;
        }
        CloudStreamApp.INSTANCE.setKey(m8635getCurrentAccount() + "/result_favorites_state_data", String.valueOf(id.intValue()), data);
        AccountManager.INSTANCE.getLocalListApi().setRequireLibraryRefresh(true);
    }

    public final FavoritesData getFavoritesData(Integer id) {
        Object objM8936constructorimpl;
        Object objDecodeFromString;
        Object obj = null;
        if (id == null) {
            return null;
        }
        CloudStreamApp.Companion companion = CloudStreamApp.INSTANCE;
        String str = m8635getCurrentAccount() + "/result_favorites_state_data";
        String strValueOf = String.valueOf(id.intValue());
        Context context = companion.getContext();
        if (context != null) {
            DataStore dataStore = DataStore.INSTANCE;
            try {
                String string = dataStore.getSharedPrefs(context).getString(dataStore.getFolderName(str, strValueOf), null);
                if (string != null) {
                    AppUtils appUtils = AppUtils.INSTANCE;
                    try {
                        Result.Companion companion2 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(SerializersKt.noCompiledSerializer("com.lagradost.cloudstream3.utils.DataStoreHelper.FavoritesData"));
                    } catch (Throwable th) {
                        Result.Companion companion3 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                    }
                    if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) != null) {
                        try {
                            Result.Companion companion4 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(FavoritesData.class), null, 2, null));
                        } catch (Throwable th2) {
                            Result.Companion companion5 = Result.INSTANCE;
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
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<FavoritesData>() { // from class: com.lagradost.cloudstream3.utils.DataStoreHelper$getFavoritesData$$inlined$getKey$1
                            });
                        } catch (Throwable unused) {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<FavoritesData>() { // from class: com.lagradost.cloudstream3.utils.DataStoreHelper$getFavoritesData$$inlined$getKey$1
                            });
                        }
                    } else {
                        objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<FavoritesData>() { // from class: com.lagradost.cloudstream3.utils.DataStoreHelper$getFavoritesData$$inlined$getKey$1
                        });
                    }
                    obj = objDecodeFromString;
                }
            } catch (Exception unused2) {
            }
        }
        return (FavoritesData) obj;
    }

    public final void setViewPos(Integer id, long pos, long dur) {
        if (id != null && dur >= 30000) {
            CloudStreamApp.INSTANCE.setKey(m8635getCurrentAccount() + "/video_pos_dur", String.valueOf(id.intValue()), new PosDur(pos, dur));
        }
    }

    public final void setViewPosAndResume(Integer id, long position, long duration, Object currentEpisode, Object nextEpisode) {
        setViewPos(id, position, duration);
        if (id != null && (currentEpisode instanceof ResultEpisode) && ((ResultEpisode) currentEpisode).getVideoWatchState() == VideoWatchState.Watched) {
            setVideoWatchState(id, VideoWatchState.None);
        }
        boolean z = (100 * position) / duration >= 90;
        Object obj = z ? nextEpisode : currentEpisode;
        if (obj == null && z) {
            if (currentEpisode instanceof ResultEpisode) {
                removeLastWatched(Integer.valueOf(((ResultEpisode) currentEpisode).getParentId()));
                return;
            } else {
                if (currentEpisode instanceof ExtractorUri) {
                    removeLastWatched(((ExtractorUri) currentEpisode).getParentId());
                    return;
                }
                return;
            }
        }
        if (obj instanceof ResultEpisode) {
            ResultEpisode resultEpisode = (ResultEpisode) obj;
            setLastWatched$default(this, Integer.valueOf(resultEpisode.getParentId()), Integer.valueOf(resultEpisode.getId()), Integer.valueOf(resultEpisode.getEpisode()), resultEpisode.getSeason(), false, null, 32, null);
        } else if (obj instanceof ExtractorUri) {
            ExtractorUri extractorUri = (ExtractorUri) obj;
            setLastWatched$default(this, extractorUri.getParentId(), extractorUri.getId(), extractorUri.getEpisode(), extractorUri.getSeason(), true, null, 32, null);
        }
    }

    /* JADX WARN: Code duplicated, block: B:25:0x0087  */
    /* JADX WARN: Code duplicated, block: B:32:0x009d A[Catch: Exception -> 0x00af, TRY_LEAVE, TryCatch #0 {Exception -> 0x00af, blocks: (B:8:0x002f, B:11:0x003b, B:16:0x0055, B:23:0x0081, B:26:0x0088, B:32:0x009d, B:31:0x0098, B:22:0x0077, B:15:0x004b, B:19:0x005c, B:12:0x003d, B:28:0x008c), top: B:42:0x002f, inners: #1, #2, #3, #5 }] */
    /* JADX WARN: Code duplicated, block: B:47:0x008c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    public final PosDur getViewPos(Integer id) {
        Object objDecodeFromString;
        Object objM8936constructorimpl;
        KSerializer kSerializer;
        Object obj = null;
        if (id == null) {
            return null;
        }
        CloudStreamApp.Companion companion = CloudStreamApp.INSTANCE;
        String str = m8635getCurrentAccount() + "/video_pos_dur";
        String strValueOf = String.valueOf(id.intValue());
        Context context = companion.getContext();
        if (context != null) {
            DataStore dataStore = DataStore.INSTANCE;
            try {
                String string = dataStore.getSharedPrefs(context).getString(dataStore.getFolderName(str, strValueOf), null);
                if (string == null) {
                    objDecodeFromString = null;
                } else {
                    AppUtils appUtils = AppUtils.INSTANCE;
                    try {
                        Result.Companion companion2 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(SerializersKt.noCompiledSerializer("com.lagradost.cloudstream3.utils.DataStoreHelper.PosDur"));
                    } catch (Throwable th) {
                        Result.Companion companion3 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                    }
                    if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) == null) {
                        if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                            objM8936constructorimpl = null;
                        }
                        kSerializer = (KSerializer) objM8936constructorimpl;
                        if (kSerializer != null) {
                            try {
                                objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, string);
                            } catch (SerializationException e) {
                                ArchComponentExtKt.logError(e);
                                objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<PosDur>() { // from class: com.lagradost.cloudstream3.utils.DataStoreHelper$getViewPos$$inlined$getKey$1
                                });
                            } catch (Throwable unused) {
                                objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<PosDur>() { // from class: com.lagradost.cloudstream3.utils.DataStoreHelper$getViewPos$$inlined$getKey$1
                                });
                            }
                        } else {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<PosDur>() { // from class: com.lagradost.cloudstream3.utils.DataStoreHelper$getViewPos$$inlined$getKey$1
                            });
                        }
                    } else {
                        try {
                            Result.Companion companion4 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(PosDur.class), null, 2, null));
                        } catch (Throwable th2) {
                            Result.Companion companion5 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th2));
                        }
                        if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                            objM8936constructorimpl = null;
                        }
                        kSerializer = (KSerializer) objM8936constructorimpl;
                        if (kSerializer != null) {
                            objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, string);
                        } else {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<PosDur>() { // from class: com.lagradost.cloudstream3.utils.DataStoreHelper$getViewPos$$inlined$getKey$1
                            });
                        }
                    }
                }
            } catch (Exception unused2) {
            }
            if (objDecodeFromString != null) {
                obj = objDecodeFromString;
            }
        }
        return (PosDur) obj;
    }

    /* JADX WARN: Code duplicated, block: B:25:0x008e  */
    /* JADX WARN: Code duplicated, block: B:32:0x00a4 A[Catch: Exception -> 0x00b6, TRY_LEAVE, TryCatch #3 {Exception -> 0x00b6, blocks: (B:8:0x002f, B:11:0x003b, B:16:0x005c, B:23:0x0088, B:26:0x008f, B:32:0x00a4, B:31:0x009f, B:22:0x007e, B:15:0x0052, B:12:0x003d, B:19:0x0063, B:28:0x0093), top: B:44:0x002f, inners: #0, #4, #5 }] */
    /* JADX WARN: Code duplicated, block: B:47:0x0093 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    public final VideoWatchState getVideoWatchState(Integer id) {
        Object objDecodeFromString;
        Object objM8936constructorimpl;
        KSerializer kSerializer;
        Object obj = null;
        if (id == null) {
            return null;
        }
        CloudStreamApp.Companion companion = CloudStreamApp.INSTANCE;
        String str = m8635getCurrentAccount() + "/video_watch_state";
        String strValueOf = String.valueOf(id.intValue());
        Context context = companion.getContext();
        if (context != null) {
            DataStore dataStore = DataStore.INSTANCE;
            try {
                String string = dataStore.getSharedPrefs(context).getString(dataStore.getFolderName(str, strValueOf), null);
                if (string == null) {
                    objDecodeFromString = null;
                } else {
                    AppUtils appUtils = AppUtils.INSTANCE;
                    try {
                        Result.Companion companion2 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(new EnumSerializer("com.lagradost.cloudstream3.ui.result.VideoWatchState", VideoWatchState.values()));
                    } catch (Throwable th) {
                        Result.Companion companion3 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                    }
                    if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) == null) {
                        if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                            objM8936constructorimpl = null;
                        }
                        kSerializer = (KSerializer) objM8936constructorimpl;
                        if (kSerializer != null) {
                            try {
                                objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, string);
                            } catch (SerializationException e) {
                                ArchComponentExtKt.logError(e);
                                objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<VideoWatchState>() { // from class: com.lagradost.cloudstream3.utils.DataStoreHelper$getVideoWatchState$$inlined$getKey$1
                                });
                            } catch (Throwable unused) {
                                objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<VideoWatchState>() { // from class: com.lagradost.cloudstream3.utils.DataStoreHelper$getVideoWatchState$$inlined$getKey$1
                                });
                            }
                        } else {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<VideoWatchState>() { // from class: com.lagradost.cloudstream3.utils.DataStoreHelper$getVideoWatchState$$inlined$getKey$1
                            });
                        }
                    } else {
                        try {
                            Result.Companion companion4 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(VideoWatchState.class), null, 2, null));
                        } catch (Throwable th2) {
                            Result.Companion companion5 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th2));
                        }
                        if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                            objM8936constructorimpl = null;
                        }
                        kSerializer = (KSerializer) objM8936constructorimpl;
                        if (kSerializer != null) {
                            objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, string);
                        } else {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<VideoWatchState>() { // from class: com.lagradost.cloudstream3.utils.DataStoreHelper$getVideoWatchState$$inlined$getKey$1
                            });
                        }
                    }
                }
            } catch (Exception unused2) {
                objDecodeFromString = null;
            }
            if (objDecodeFromString != null) {
                obj = objDecodeFromString;
            }
        }
        return (VideoWatchState) obj;
    }

    public final void setVideoWatchState(Integer id, VideoWatchState watchState) {
        Intrinsics.checkNotNullParameter(watchState, "watchState");
        if (id == null) {
            return;
        }
        if (watchState == VideoWatchState.None) {
            CloudStreamApp.INSTANCE.removeKey(m8635getCurrentAccount() + "/video_watch_state", String.valueOf(id.intValue()));
            return;
        }
        CloudStreamApp.INSTANCE.setKey(m8635getCurrentAccount() + "/video_watch_state", String.valueOf(id.intValue()), watchState);
    }

    /* JADX WARN: Code duplicated, block: B:33:0x00b4 A[PHI: r4
      0x00b4: PHI (r4v1 java.lang.Object) = (r4v0 java.lang.Object), (r4v2 java.lang.Object) binds: [B:3:0x002b, B:31:0x00b1] A[DONT_GENERATE, DONT_INLINE]] */
    public final DubStatus getDub(int id) {
        Object objM8936constructorimpl;
        Object objDecodeFromString;
        EnumEntries<DubStatus> entries = DubStatus.getEntries();
        CloudStreamApp.Companion companion = CloudStreamApp.INSTANCE;
        String str = m8635getCurrentAccount() + "/result_dub";
        String strValueOf = String.valueOf(id);
        Object obj = -1;
        Context context = companion.getContext();
        Object obj2 = null;
        if (context != null) {
            DataStore dataStore = DataStore.INSTANCE;
            try {
                String string = dataStore.getSharedPrefs(context).getString(dataStore.getFolderName(str, strValueOf), null);
                if (string == null) {
                    obj2 = obj;
                } else {
                    AppUtils appUtils = AppUtils.INSTANCE;
                    try {
                        Result.Companion companion2 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(IntSerializer.INSTANCE);
                    } catch (Throwable th) {
                        Result.Companion companion3 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                    }
                    if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) != null) {
                        try {
                            Result.Companion companion4 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(Integer.class), null, 2, null));
                        } catch (Throwable th2) {
                            Result.Companion companion5 = Result.INSTANCE;
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
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<Integer>() { // from class: com.lagradost.cloudstream3.utils.DataStoreHelper$getDub$$inlined$getKey$1
                            });
                        } catch (Throwable unused) {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<Integer>() { // from class: com.lagradost.cloudstream3.utils.DataStoreHelper$getDub$$inlined$getKey$1
                            });
                        }
                    } else {
                        objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<Integer>() { // from class: com.lagradost.cloudstream3.utils.DataStoreHelper$getDub$$inlined$getKey$1
                        });
                    }
                    obj2 = objDecodeFromString;
                }
            } catch (Exception unused2) {
            }
            obj = obj2 != null ? obj2 : -1;
        }
        Integer num = (Integer) obj;
        return (DubStatus) CollectionsKt.getOrNull(entries, num != null ? num.intValue() : -1);
    }

    public final void setDub(int id, DubStatus status) {
        Intrinsics.checkNotNullParameter(status, "status");
        CloudStreamApp.INSTANCE.setKey(m8635getCurrentAccount() + "/result_dub", String.valueOf(id), Integer.valueOf(status.ordinal()));
    }

    public final void setResultWatchState(Integer id, int status) {
        if (id == null) {
            return;
        }
        if (status == WatchType.NONE.getInternalId()) {
            deleteBookmarkedData(id);
            return;
        }
        CloudStreamApp.INSTANCE.setKey(m8635getCurrentAccount() + "/result_watch_state", String.valueOf(id.intValue()), Integer.valueOf(status));
    }

    /* JADX WARN: Code duplicated, block: B:22:0x007e  */
    /* JADX WARN: Code duplicated, block: B:29:0x0094 A[Catch: Exception -> 0x00a6, TRY_LEAVE, TryCatch #3 {Exception -> 0x00a6, blocks: (B:5:0x002a, B:8:0x0036, B:13:0x004c, B:20:0x0078, B:23:0x007f, B:29:0x0094, B:28:0x008f, B:19:0x006e, B:12:0x0042, B:9:0x0038, B:16:0x0053, B:25:0x0083), top: B:41:0x002a, inners: #0, #4, #5 }] */
    /* JADX WARN: Code duplicated, block: B:44:0x0083 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    public final WatchType getResultWatchState(int id) {
        Object objDecodeFromString;
        Object objM8936constructorimpl;
        KSerializer kSerializer;
        WatchType.Companion companion = WatchType.INSTANCE;
        CloudStreamApp.Companion companion2 = CloudStreamApp.INSTANCE;
        String str = m8635getCurrentAccount() + "/result_watch_state";
        String strValueOf = String.valueOf(id);
        Context context = companion2.getContext();
        Object obj = null;
        if (context != null) {
            DataStore dataStore = DataStore.INSTANCE;
            try {
                String string = dataStore.getSharedPrefs(context).getString(dataStore.getFolderName(str, strValueOf), null);
                if (string == null) {
                    objDecodeFromString = null;
                } else {
                    AppUtils appUtils = AppUtils.INSTANCE;
                    try {
                        Result.Companion companion3 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(IntSerializer.INSTANCE);
                    } catch (Throwable th) {
                        Result.Companion companion4 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                    }
                    if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) == null) {
                        if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                            objM8936constructorimpl = null;
                        }
                        kSerializer = (KSerializer) objM8936constructorimpl;
                        if (kSerializer != null) {
                            try {
                                objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, string);
                            } catch (SerializationException e) {
                                ArchComponentExtKt.logError(e);
                                objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<Integer>() { // from class: com.lagradost.cloudstream3.utils.DataStoreHelper$getResultWatchState$$inlined$getKey$1
                                });
                            } catch (Throwable unused) {
                                objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<Integer>() { // from class: com.lagradost.cloudstream3.utils.DataStoreHelper$getResultWatchState$$inlined$getKey$1
                                });
                            }
                        } else {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<Integer>() { // from class: com.lagradost.cloudstream3.utils.DataStoreHelper$getResultWatchState$$inlined$getKey$1
                            });
                        }
                    } else {
                        try {
                            Result.Companion companion5 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(Integer.class), null, 2, null));
                        } catch (Throwable th2) {
                            Result.Companion companion6 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th2));
                        }
                        if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                            objM8936constructorimpl = null;
                        }
                        kSerializer = (KSerializer) objM8936constructorimpl;
                        if (kSerializer != null) {
                            objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, string);
                        } else {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<Integer>() { // from class: com.lagradost.cloudstream3.utils.DataStoreHelper$getResultWatchState$$inlined$getKey$1
                            });
                        }
                    }
                }
            } catch (Exception unused2) {
                objDecodeFromString = null;
            }
            if (objDecodeFromString != null) {
                obj = objDecodeFromString;
            }
        }
        return companion.fromInternalId((Integer) obj);
    }

    /* JADX WARN: Code duplicated, block: B:22:0x007c  */
    /* JADX WARN: Code duplicated, block: B:29:0x0092 A[Catch: Exception -> 0x00a4, TRY_LEAVE, TryCatch #3 {Exception -> 0x00a4, blocks: (B:5:0x0028, B:8:0x0034, B:13:0x004a, B:20:0x0076, B:23:0x007d, B:29:0x0092, B:28:0x008d, B:19:0x006c, B:12:0x0040, B:16:0x0051, B:9:0x0036, B:25:0x0081), top: B:41:0x0028, inners: #2, #4, #5 }] */
    /* JADX WARN: Code duplicated, block: B:44:0x0081 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    public final Integer getResultSeason(int id) {
        Object objDecodeFromString;
        Object objM8936constructorimpl;
        KSerializer kSerializer;
        CloudStreamApp.Companion companion = CloudStreamApp.INSTANCE;
        String str = m8635getCurrentAccount() + "/result_season";
        String strValueOf = String.valueOf(id);
        Context context = companion.getContext();
        Object obj = null;
        if (context != null) {
            DataStore dataStore = DataStore.INSTANCE;
            try {
                String string = dataStore.getSharedPrefs(context).getString(dataStore.getFolderName(str, strValueOf), null);
                if (string == null) {
                    objDecodeFromString = null;
                } else {
                    AppUtils appUtils = AppUtils.INSTANCE;
                    try {
                        Result.Companion companion2 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(IntSerializer.INSTANCE);
                    } catch (Throwable th) {
                        Result.Companion companion3 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                    }
                    if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) == null) {
                        if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                            objM8936constructorimpl = null;
                        }
                        kSerializer = (KSerializer) objM8936constructorimpl;
                        if (kSerializer != null) {
                            try {
                                objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, string);
                            } catch (SerializationException e) {
                                ArchComponentExtKt.logError(e);
                                objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<Integer>() { // from class: com.lagradost.cloudstream3.utils.DataStoreHelper$getResultSeason$$inlined$getKey$1
                                });
                            } catch (Throwable unused) {
                                objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<Integer>() { // from class: com.lagradost.cloudstream3.utils.DataStoreHelper$getResultSeason$$inlined$getKey$1
                                });
                            }
                        } else {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<Integer>() { // from class: com.lagradost.cloudstream3.utils.DataStoreHelper$getResultSeason$$inlined$getKey$1
                            });
                        }
                    } else {
                        try {
                            Result.Companion companion4 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(Integer.class), null, 2, null));
                        } catch (Throwable th2) {
                            Result.Companion companion5 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th2));
                        }
                        if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                            objM8936constructorimpl = null;
                        }
                        kSerializer = (KSerializer) objM8936constructorimpl;
                        if (kSerializer != null) {
                            objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, string);
                        } else {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<Integer>() { // from class: com.lagradost.cloudstream3.utils.DataStoreHelper$getResultSeason$$inlined$getKey$1
                            });
                        }
                    }
                }
            } catch (Exception unused2) {
                objDecodeFromString = null;
            }
            if (objDecodeFromString != null) {
                obj = objDecodeFromString;
            }
        }
        return (Integer) obj;
    }

    public final void setResultSeason(int id, Integer value) {
        CloudStreamApp.INSTANCE.setKey(m8635getCurrentAccount() + "/result_season", String.valueOf(id), value);
    }

    /* JADX WARN: Code duplicated, block: B:22:0x007c  */
    /* JADX WARN: Code duplicated, block: B:29:0x0092 A[Catch: Exception -> 0x00a4, TRY_LEAVE, TryCatch #3 {Exception -> 0x00a4, blocks: (B:5:0x0028, B:8:0x0034, B:13:0x004a, B:20:0x0076, B:23:0x007d, B:29:0x0092, B:28:0x008d, B:19:0x006c, B:12:0x0040, B:16:0x0051, B:9:0x0036, B:25:0x0081), top: B:41:0x0028, inners: #2, #4, #5 }] */
    /* JADX WARN: Code duplicated, block: B:44:0x0081 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    public final Integer getResultEpisode(int id) {
        Object objDecodeFromString;
        Object objM8936constructorimpl;
        KSerializer kSerializer;
        CloudStreamApp.Companion companion = CloudStreamApp.INSTANCE;
        String str = m8635getCurrentAccount() + "/result_episode";
        String strValueOf = String.valueOf(id);
        Context context = companion.getContext();
        Object obj = null;
        if (context != null) {
            DataStore dataStore = DataStore.INSTANCE;
            try {
                String string = dataStore.getSharedPrefs(context).getString(dataStore.getFolderName(str, strValueOf), null);
                if (string == null) {
                    objDecodeFromString = null;
                } else {
                    AppUtils appUtils = AppUtils.INSTANCE;
                    try {
                        Result.Companion companion2 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(IntSerializer.INSTANCE);
                    } catch (Throwable th) {
                        Result.Companion companion3 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                    }
                    if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) == null) {
                        if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                            objM8936constructorimpl = null;
                        }
                        kSerializer = (KSerializer) objM8936constructorimpl;
                        if (kSerializer != null) {
                            try {
                                objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, string);
                            } catch (SerializationException e) {
                                ArchComponentExtKt.logError(e);
                                objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<Integer>() { // from class: com.lagradost.cloudstream3.utils.DataStoreHelper$getResultEpisode$$inlined$getKey$1
                                });
                            } catch (Throwable unused) {
                                objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<Integer>() { // from class: com.lagradost.cloudstream3.utils.DataStoreHelper$getResultEpisode$$inlined$getKey$1
                                });
                            }
                        } else {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<Integer>() { // from class: com.lagradost.cloudstream3.utils.DataStoreHelper$getResultEpisode$$inlined$getKey$1
                            });
                        }
                    } else {
                        try {
                            Result.Companion companion4 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(Integer.class), null, 2, null));
                        } catch (Throwable th2) {
                            Result.Companion companion5 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th2));
                        }
                        if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                            objM8936constructorimpl = null;
                        }
                        kSerializer = (KSerializer) objM8936constructorimpl;
                        if (kSerializer != null) {
                            objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, string);
                        } else {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<Integer>() { // from class: com.lagradost.cloudstream3.utils.DataStoreHelper$getResultEpisode$$inlined$getKey$1
                            });
                        }
                    }
                }
            } catch (Exception unused2) {
                objDecodeFromString = null;
            }
            if (objDecodeFromString != null) {
                obj = objDecodeFromString;
            }
        }
        return (Integer) obj;
    }

    public final void setResultEpisode(int id, Integer value) {
        CloudStreamApp.INSTANCE.setKey(m8635getCurrentAccount() + "/result_episode", String.valueOf(id), value);
    }

    public final void addSync(int id, String idPrefix, String url) {
        Intrinsics.checkNotNullParameter(idPrefix, "idPrefix");
        Intrinsics.checkNotNullParameter(url, "url");
        CloudStreamApp.INSTANCE.setKey(idPrefix + "_sync", String.valueOf(id), url);
    }

    public final List<String> getSync(int id, List<String> idPrefixes) {
        Object objM8936constructorimpl;
        Object objDecodeFromString;
        Intrinsics.checkNotNullParameter(idPrefixes, "idPrefixes");
        List<String> list = idPrefixes;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (String str : list) {
            CloudStreamApp.Companion companion = CloudStreamApp.INSTANCE;
            String str2 = str + "_sync";
            String strValueOf = String.valueOf(id);
            Context context = companion.getContext();
            Object obj = null;
            if (context != null) {
                DataStore dataStore = DataStore.INSTANCE;
                try {
                    String string = dataStore.getSharedPrefs(context).getString(dataStore.getFolderName(str2, strValueOf), null);
                    if (string != null) {
                        AppUtils appUtils = AppUtils.INSTANCE;
                        try {
                            Result.Companion companion2 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(StringSerializer.INSTANCE);
                        } catch (Throwable th) {
                            Result.Companion companion3 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                        }
                        if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) != null) {
                            try {
                                Result.Companion companion4 = Result.INSTANCE;
                                objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(String.class), null, 2, null));
                            } catch (Throwable th2) {
                                Result.Companion companion5 = Result.INSTANCE;
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
                                objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<String>() { // from class: com.lagradost.cloudstream3.utils.DataStoreHelper$getSync$lambda$0$$inlined$getKey$1
                                });
                            } catch (Throwable unused) {
                                objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<String>() { // from class: com.lagradost.cloudstream3.utils.DataStoreHelper$getSync$lambda$0$$inlined$getKey$1
                                });
                            }
                        } else {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<String>() { // from class: com.lagradost.cloudstream3.utils.DataStoreHelper$getSync$lambda$0$$inlined$getKey$1
                            });
                        }
                        obj = objDecodeFromString;
                    }
                } catch (Exception unused2) {
                }
            }
            arrayList.add((String) obj);
        }
        return arrayList;
    }

    public final String[] getPinnedProviders() {
        Object objM8936constructorimpl;
        Object objDecodeFromString;
        Context context = CloudStreamApp.INSTANCE.getContext();
        Object obj = null;
        if (context != null) {
            try {
                String string = DataStore.INSTANCE.getSharedPrefs(context).getString(DataStoreHelperKt.USER_PINNED_PROVIDERS, null);
                if (string != null) {
                    AppUtils appUtils = AppUtils.INSTANCE;
                    try {
                        Result.Companion companion = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(new ReferenceArraySerializer(Reflection.getOrCreateKotlinClass(String.class), StringSerializer.INSTANCE));
                    } catch (Throwable th) {
                        Result.Companion companion2 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                    }
                    if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) != null) {
                        try {
                            Result.Companion companion3 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(String[].class), null, 2, null));
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
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<String[]>() { // from class: com.lagradost.cloudstream3.utils.DataStoreHelper$special$$inlined$getKey$2
                            });
                        } catch (Throwable unused) {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<String[]>() { // from class: com.lagradost.cloudstream3.utils.DataStoreHelper$special$$inlined$getKey$2
                            });
                        }
                    } else {
                        objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<String[]>() { // from class: com.lagradost.cloudstream3.utils.DataStoreHelper$special$$inlined$getKey$2
                        });
                    }
                    obj = objDecodeFromString;
                }
            } catch (Exception unused2) {
            }
        }
        String[] strArr = (String[]) obj;
        return strArr == null ? new String[0] : strArr;
    }

    public final void setPinnedProviders(String[] value) {
        Intrinsics.checkNotNullParameter(value, "value");
        CloudStreamApp.INSTANCE.setKey(DataStoreHelperKt.USER_PINNED_PROVIDERS, value);
    }

    static {
        List listListOf = CollectionsKt.listOf((Object[]) new TvType[]{TvType.Movie, TvType.TvSeries});
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listListOf, 10));
        Iterator it = listListOf.iterator();
        while (it.hasNext()) {
            arrayList.add(((TvType) it.next()).name());
        }
        searchPreferenceTagsStrings = new UserPreferenceDelegate("search_pref_tags", arrayList);
        List listListOf2 = CollectionsKt.listOf((Object[]) new TvType[]{TvType.Movie, TvType.TvSeries});
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(listListOf2, 10));
        Iterator it2 = listListOf2.iterator();
        while (it2.hasNext()) {
            arrayList2.add(((TvType) it2.next()).name());
        }
        homePreferenceStrings = new UserPreferenceDelegate("home_pref_homepage", arrayList2);
        homeBookmarkedList = new UserPreferenceDelegate("home_bookmarked_last_list", new int[0]);
        playBackSpeed = new UserPreferenceDelegate("playback_speed", Float.valueOf(1.0f));
        resizeMode = new UserPreferenceDelegate("resize_mode", 0);
        librarySortingMode = new UserPreferenceDelegate("library_sorting_mode", Integer.valueOf(ListSorting.AlphabeticalA.ordinal()));
        _resultsSortingMode = new UserPreferenceDelegate("results_sorting_mode", Integer.valueOf(EpisodeSortType.NUMBER_ASC.ordinal()));
        accounts = new PreferenceDelegate("data_store_helper/account", new Account[0]);
        selectedKeyIndex = new PreferenceDelegate("data_store_helper/account_key_index", 0);
    }
}
