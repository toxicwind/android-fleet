package androidx.navigation.internal;

import android.net.Uri;
import android.os.Bundle;
import androidx.core.os.BundleKt;
import androidx.exifinterface.media.ExifInterface;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.FloatingWindow;
import androidx.navigation.NavArgument;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavBackStackEntryState;
import androidx.navigation.NavController;
import androidx.navigation.NavControllerViewModel;
import androidx.navigation.NavDeepLinkRequest;
import androidx.navigation.NavDestination;
import androidx.navigation.NavGraph;
import androidx.navigation.NavOptions;
import androidx.navigation.NavOptionsBuilder;
import androidx.navigation.NavOptionsBuilderKt;
import androidx.navigation.NavUriKt;
import androidx.navigation.Navigator;
import androidx.navigation.NavigatorProvider;
import androidx.navigation.SupportingPane;
import androidx.navigation.serialization.RouteSerializerKt;
import androidx.savedstate.SavedStateReader;
import androidx.savedstate.SavedStateWriter;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.reflect.KClass;
import kotlin.sequences.SequencesKt;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.SharedFlowKt;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import kotlinx.serialization.SerializersKt;
import org.mozilla.javascript.ES6Iterator;
import org.mozilla.javascript.optimizer.Signatures;

/* JADX INFO: compiled from: NavControllerImpl.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000²\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010%\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001f\n\u0002\u0018\u0002\n\u0002\b\u001e\n\u0002\u0010\u0015\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u0000 \u008e\u00022\u00020\u0001:\u0002\u008e\u0002B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u001d\u0010C\u001a\u00020\u00062\u0006\u0010D\u001a\u00020,2\u0006\u0010E\u001a\u00020,H\u0000¢\u0006\u0002\bFJ\u0017\u0010G\u001a\u0004\u0018\u00010,2\u0006\u0010D\u001a\u00020,H\u0000¢\u0006\u0002\bHJm\u0010\u0088\u0001\u001a\u00020\u00062\u000f\u0010\u0089\u0001\u001a\n\u0012\u0006\b\u0001\u0012\u00020u0t2\r\u0010\u008a\u0001\u001a\b\u0012\u0004\u0012\u00020,012\n\u0010\u008b\u0001\u001a\u0005\u0018\u00010\u008c\u00012\n\u0010\u008d\u0001\u001a\u0005\u0018\u00010\u008e\u00012$\b\u0002\u0010\u008f\u0001\u001a\u001d\u0012\u0013\u0012\u00110,¢\u0006\f\bz\u0012\b\b{\u0012\u0004\b\b(|\u0012\u0004\u0012\u00020\u00060yH\u0000¢\u0006\u0003\b\u0090\u0001JZ\u0010\u0091\u0001\u001a\u00020\u00062\u000f\u0010\u0089\u0001\u001a\n\u0012\u0006\b\u0001\u0012\u00020u0t2\u0007\u0010\u0082\u0001\u001a\u00020,2\b\u0010\u0092\u0001\u001a\u00030\u0086\u00012%\b\u0002\u0010\u008f\u0001\u001a\u001e\u0012\u0014\u0012\u00120,¢\u0006\r\bz\u0012\t\b{\u0012\u0005\b\b(\u0082\u0001\u0012\u0004\u0012\u00020\u00060yH\u0000¢\u0006\u0003\b\u0093\u0001J$\u0010\u0094\u0001\u001a\u00020\u00062\u000b\u0010\u0095\u0001\u001a\u00060vR\u00020\u00032\u0006\u0010|\u001a\u00020,H\u0000¢\u0006\u0003\b\u0096\u0001J)\u0010\u0097\u0001\u001a\u00020,2\u0007\u0010\u0098\u0001\u001a\u00020u2\u000f\u0010\u0099\u0001\u001a\n\u0018\u00010\u001dj\u0004\u0018\u0001`\u001eH\u0000¢\u0006\u0003\b\u009a\u0001J>\u0010\u009b\u0001\u001a\u00020\u00062\u000b\u0010\u0095\u0001\u001a\u00060vR\u00020\u00032\u0007\u0010\u0082\u0001\u001a\u00020,2\b\u0010\u0092\u0001\u001a\u00030\u0086\u00012\r\u0010\u009c\u0001\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0000¢\u0006\u0003\b\u009d\u0001J4\u0010\u009e\u0001\u001a\u00020\u00062\u000b\u0010\u0095\u0001\u001a\u00060vR\u00020\u00032\u0007\u0010\u009f\u0001\u001a\u00020,2\r\u0010\u009c\u0001\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0000¢\u0006\u0003\b \u0001J\u0018\u0010¡\u0001\u001a\u00020\u00062\u0007\u0010\u009f\u0001\u001a\u00020,H\u0000¢\u0006\u0003\b¢\u0001J\u0018\u0010£\u0001\u001a\u00020\u00062\u0007\u0010¤\u0001\u001a\u00020]H\u0000¢\u0006\u0003\b¥\u0001J\u0018\u0010¦\u0001\u001a\u00020\u00062\u0007\u0010¤\u0001\u001a\u00020]H\u0000¢\u0006\u0003\b§\u0001J\u0010\u0010¨\u0001\u001a\u00030\u0086\u0001H\u0000¢\u0006\u0003\b©\u0001J#\u0010¨\u0001\u001a\u00030\u0086\u00012\u0007\u0010ª\u0001\u001a\u00020J2\b\u0010«\u0001\u001a\u00030\u0086\u0001H\u0000¢\u0006\u0003\b©\u0001J-\u0010¨\u0001\u001a\u00030\u0086\u00012\u0007\u0010ª\u0001\u001a\u00020J2\b\u0010«\u0001\u001a\u00030\u0086\u00012\b\u0010\u0092\u0001\u001a\u00030\u0086\u0001H\u0000¢\u0006\u0003\b©\u0001J-\u0010¨\u0001\u001a\u00030\u0086\u00012\u0007\u0010¬\u0001\u001a\u00020K2\b\u0010«\u0001\u001a\u00030\u0086\u00012\b\u0010\u0092\u0001\u001a\u00030\u0086\u0001H\u0000¢\u0006\u0003\b©\u0001J@\u0010¨\u0001\u001a\u00030\u0086\u0001\"\t\b\u0000\u0010\u00ad\u0001*\u00020\u00012\u000f\u0010¬\u0001\u001a\n\u0012\u0005\u0012\u0003H\u00ad\u00010®\u00012\b\u0010«\u0001\u001a\u00030\u0086\u00012\b\u0010\u0092\u0001\u001a\u00030\u0086\u0001H\u0000¢\u0006\u0003\b©\u0001J<\u0010¨\u0001\u001a\u00030\u0086\u0001\"\t\b\u0000\u0010\u00ad\u0001*\u00020\u00012\b\u0010¬\u0001\u001a\u0003H\u00ad\u00012\b\u0010«\u0001\u001a\u00030\u0086\u00012\b\u0010\u0092\u0001\u001a\u00030\u0086\u0001H\u0000¢\u0006\u0006\b©\u0001\u0010¯\u0001J/\u0010\u0091\u0001\u001a\u00030\u0086\u00012\u0007\u0010ª\u0001\u001a\u00020J2\b\u0010«\u0001\u001a\u00030\u0086\u00012\n\b\u0002\u0010\u0092\u0001\u001a\u00030\u0086\u0001H\u0000¢\u0006\u0003\b\u0093\u0001J>\u0010\u0091\u0001\u001a\u00030\u0086\u0001\"\t\b\u0000\u0010\u00ad\u0001*\u00020\u00012\b\u0010¬\u0001\u001a\u0003H\u00ad\u00012\b\u0010«\u0001\u001a\u00030\u0086\u00012\n\b\u0002\u0010\u0092\u0001\u001a\u00030\u0086\u0001H\u0000¢\u0006\u0006\b\u0093\u0001\u0010¯\u0001J-\u0010\u0091\u0001\u001a\u00030\u0086\u00012\u0007\u0010¬\u0001\u001a\u00020K2\b\u0010«\u0001\u001a\u00030\u0086\u00012\b\u0010\u0092\u0001\u001a\u00030\u0086\u0001H\u0000¢\u0006\u0003\b\u0093\u0001J@\u0010°\u0001\u001a\u00030\u0086\u00012\u0011\u0010±\u0001\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030t012\u0007\u0010²\u0001\u001a\u00020u2\b\u0010«\u0001\u001a\u00030\u0086\u00012\b\u0010\u0092\u0001\u001a\u00030\u0086\u0001H\u0000¢\u0006\u0003\b³\u0001J'\u0010´\u0001\u001a\u00020\u00062\u0007\u0010\u0082\u0001\u001a\u00020,2\r\u0010µ\u0001\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0000¢\u0006\u0003\b¶\u0001J5\u0010·\u0001\u001a\u00020\u00062\u0007\u0010\u0082\u0001\u001a\u00020,2\n\b\u0002\u0010\u0092\u0001\u001a\u00030\u0086\u00012\u000f\b\u0002\u0010¸\u0001\u001a\b\u0012\u0004\u0012\u00020N0+H\u0000¢\u0006\u0003\b¹\u0001J\u0019\u0010º\u0001\u001a\u00030\u0086\u00012\u0007\u0010¬\u0001\u001a\u00020KH\u0000¢\u0006\u0003\b»\u0001J\u0019\u0010º\u0001\u001a\u00030\u0086\u00012\u0007\u0010ª\u0001\u001a\u00020JH\u0000¢\u0006\u0003\b»\u0001J,\u0010º\u0001\u001a\u00030\u0086\u0001\"\t\b\u0000\u0010\u00ad\u0001*\u00020\u00012\u000f\u0010¬\u0001\u001a\n\u0012\u0005\u0012\u0003H\u00ad\u00010®\u0001H\u0000¢\u0006\u0003\b»\u0001J(\u0010º\u0001\u001a\u00030\u0086\u0001\"\t\b\u0000\u0010\u00ad\u0001*\u00020\u00012\b\u0010¬\u0001\u001a\u0003H\u00ad\u0001H\u0000¢\u0006\u0006\b»\u0001\u0010¼\u0001J\u0019\u0010½\u0001\u001a\u00030\u0086\u00012\u0007\u0010ª\u0001\u001a\u00020JH\u0000¢\u0006\u0003\b¾\u0001J\u0019\u0010½\u0001\u001a\u00030\u0086\u00012\u0007\u0010¬\u0001\u001a\u00020KH\u0000¢\u0006\u0003\b¾\u0001J\u0010\u0010Á\u0001\u001a\u00030\u0086\u0001H\u0000¢\u0006\u0003\bÂ\u0001J\u000f\u0010Ã\u0001\u001a\u00020\u0006H\u0000¢\u0006\u0003\bÄ\u0001J\u0015\u0010Å\u0001\u001a\b\u0012\u0004\u0012\u00020,01H\u0000¢\u0006\u0003\bÆ\u0001J'\u0010Ç\u0001\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u00142\u000f\u0010È\u0001\u001a\n\u0018\u00010\u001dj\u0004\u0018\u0001`\u001eH\u0000¢\u0006\u0002\b\u001bJ \u0010É\u0001\u001a\u00020\u00062\u000f\u0010È\u0001\u001a\n\u0018\u00010\u001dj\u0004\u0018\u0001`\u001eH\u0000¢\u0006\u0003\bÊ\u0001J\u001b\u0010Ë\u0001\u001a\u0004\u0018\u00010K2\b\u0010Ì\u0001\u001a\u00030Í\u0001H\u0000¢\u0006\u0003\bÎ\u0001J'\u0010Ò\u0001\u001a\u0004\u0018\u00010u2\u0007\u0010ª\u0001\u001a\u00020J2\u000b\b\u0002\u0010Ó\u0001\u001a\u0004\u0018\u00010uH\u0000¢\u0006\u0003\bÔ\u0001J:\u0010Õ\u0001\u001a\u0004\u0018\u00010u2\u0007\u0010\u0098\u0001\u001a\u00020u2\u0007\u0010ª\u0001\u001a\u00020J2\b\u0010Ö\u0001\u001a\u00030\u0086\u00012\u000b\b\u0002\u0010Ó\u0001\u001a\u0004\u0018\u00010uH\u0000¢\u0006\u0003\b×\u0001J\u001a\u0010Ò\u0001\u001a\u0004\u0018\u00010u2\u0007\u0010¬\u0001\u001a\u00020KH\u0000¢\u0006\u0003\bÔ\u0001J\u000f\u0010Ø\u0001\u001a\u00020\u0014H\u0000¢\u0006\u0003\bÙ\u0001J'\u0010Ú\u0001\u001a\u00020K\"\t\b\u0000\u0010\u00ad\u0001*\u00020\u00012\b\u0010¬\u0001\u001a\u0003H\u00ad\u0001H\u0000¢\u0006\u0006\bÛ\u0001\u0010Ü\u0001J\u001e\u0010Ý\u0001\u001a\u00020\u00062\r\u0010Ì\u0001\u001a\b0Þ\u0001j\u0003`ß\u0001H\u0000¢\u0006\u0003\bà\u0001J*\u0010Ý\u0001\u001a\u00020\u00062\r\u0010Ì\u0001\u001a\b0Þ\u0001j\u0003`ß\u00012\n\u0010\u008b\u0001\u001a\u0005\u0018\u00010\u008c\u0001H\u0000¢\u0006\u0003\bà\u0001J6\u0010Ý\u0001\u001a\u00020\u00062\r\u0010Ì\u0001\u001a\b0Þ\u0001j\u0003`ß\u00012\n\u0010\u008b\u0001\u001a\u0005\u0018\u00010\u008c\u00012\n\u0010\u008d\u0001\u001a\u0005\u0018\u00010\u008e\u0001H\u0000¢\u0006\u0003\bà\u0001J\u0019\u0010Ý\u0001\u001a\u00020\u00062\b\u0010á\u0001\u001a\u00030â\u0001H\u0000¢\u0006\u0003\bà\u0001J%\u0010Ý\u0001\u001a\u00020\u00062\b\u0010á\u0001\u001a\u00030â\u00012\n\u0010\u008b\u0001\u001a\u0005\u0018\u00010\u008c\u0001H\u0000¢\u0006\u0003\bà\u0001J1\u0010Ý\u0001\u001a\u00020\u00062\b\u0010á\u0001\u001a\u00030â\u00012\n\u0010\u008b\u0001\u001a\u0005\u0018\u00010\u008c\u00012\n\u0010\u008d\u0001\u001a\u0005\u0018\u00010\u008e\u0001H\u0000¢\u0006\u0003\bà\u0001JA\u0010Ý\u0001\u001a\u00020\u00062\u0007\u0010ã\u0001\u001a\u00020u2\u000f\u0010ä\u0001\u001a\n\u0018\u00010\u001dj\u0004\u0018\u0001`\u001e2\n\u0010\u008b\u0001\u001a\u0005\u0018\u00010\u008c\u00012\n\u0010\u008d\u0001\u001a\u0005\u0018\u00010\u008e\u0001H\u0000¢\u0006\u0003\bà\u0001J$\u0010å\u0001\u001a\u00030\u0086\u00012\u0007\u0010ã\u0001\u001a\u00020u2\u000f\u0010ä\u0001\u001a\n\u0018\u00010\u001dj\u0004\u0018\u0001`\u001eH\u0002J<\u0010æ\u0001\u001a\u00030\u0086\u00012\u0007\u0010ç\u0001\u001a\u00020J2\u000f\u0010ä\u0001\u001a\n\u0018\u00010\u001dj\u0004\u0018\u0001`\u001e2\n\u0010\u008b\u0001\u001a\u0005\u0018\u00010\u008c\u00012\n\u0010\u008d\u0001\u001a\u0005\u0018\u00010\u008e\u0001H\u0002J\u0013\u0010æ\u0001\u001a\u00030\u0086\u00012\u0007\u0010¬\u0001\u001a\u00020KH\u0002JB\u0010è\u0001\u001a\u00030\u0086\u00012\r\u0010\u008a\u0001\u001a\b\u0012\u0004\u0012\u00020,012\u000f\u0010ä\u0001\u001a\n\u0018\u00010\u001dj\u0004\u0018\u0001`\u001e2\n\u0010\u008b\u0001\u001a\u0005\u0018\u00010\u008c\u00012\n\u0010\u008d\u0001\u001a\u0005\u0018\u00010\u008e\u0001H\u0002J \u0010é\u0001\u001a\b\u0012\u0004\u0012\u00020,012\u000f\u0010ê\u0001\u001a\n\u0012\u0004\u0012\u00020N\u0018\u00010+H\u0002J<\u0010ë\u0001\u001a\u00020\u00062\u0007\u0010ã\u0001\u001a\u00020u2\u000f\u0010ì\u0001\u001a\n\u0018\u00010\u001dj\u0004\u0018\u0001`\u001e2\u0006\u0010|\u001a\u00020,2\u000f\b\u0002\u0010í\u0001\u001a\b\u0012\u0004\u0012\u00020,01H\u0002J4\u0010Ý\u0001\u001a\u00020\u00062\u0007\u0010¬\u0001\u001a\u00020K2\u001a\u0010î\u0001\u001a\u0015\u0012\u0005\u0012\u00030ï\u0001\u0012\u0004\u0012\u00020\u00060y¢\u0006\u0003\bð\u0001H\u0000¢\u0006\u0003\bà\u0001J2\u0010Ý\u0001\u001a\u00020\u00062\u0007\u0010¬\u0001\u001a\u00020K2\n\u0010\u008b\u0001\u001a\u0005\u0018\u00010\u008c\u00012\f\b\u0002\u0010\u008d\u0001\u001a\u0005\u0018\u00010\u008e\u0001H\u0000¢\u0006\u0003\bà\u0001JC\u0010Ý\u0001\u001a\u00020\u0006\"\t\b\u0000\u0010\u00ad\u0001*\u00020\u00012\b\u0010¬\u0001\u001a\u0003H\u00ad\u00012\u001a\u0010î\u0001\u001a\u0015\u0012\u0005\u0012\u00030ï\u0001\u0012\u0004\u0012\u00020\u00060y¢\u0006\u0003\bð\u0001H\u0000¢\u0006\u0006\bà\u0001\u0010ñ\u0001JA\u0010Ý\u0001\u001a\u00020\u0006\"\t\b\u0000\u0010\u00ad\u0001*\u00020\u00012\b\u0010¬\u0001\u001a\u0003H\u00ad\u00012\n\u0010\u008b\u0001\u001a\u0005\u0018\u00010\u008c\u00012\f\b\u0002\u0010\u008d\u0001\u001a\u0005\u0018\u00010\u008e\u0001H\u0000¢\u0006\u0006\bà\u0001\u0010ò\u0001J\u0017\u0010\u0092\u0001\u001a\n\u0018\u00010\u001dj\u0004\u0018\u0001`\u001eH\u0000¢\u0006\u0003\bó\u0001J \u0010ô\u0001\u001a\u00020\u00062\u000f\u0010õ\u0001\u001a\n\u0018\u00010\u001dj\u0004\u0018\u0001`\u001eH\u0000¢\u0006\u0003\bö\u0001J\u0018\u0010÷\u0001\u001a\u00020\u00062\u0007\u0010ø\u0001\u001a\u00020QH\u0001¢\u0006\u0003\bù\u0001J\u0019\u0010ú\u0001\u001a\u00020\u00062\b\u0010û\u0001\u001a\u00030ü\u0001H\u0000¢\u0006\u0003\bý\u0001J\u0019\u0010þ\u0001\u001a\u00030ÿ\u00012\u0007\u0010\u0080\u0002\u001a\u00020JH\u0000¢\u0006\u0003\b\u0081\u0002J\u0018\u0010\u0082\u0002\u001a\u00020,2\u0007\u0010ª\u0001\u001a\u00020JH\u0000¢\u0006\u0003\b\u0083\u0002J\u0018\u0010\u0082\u0002\u001a\u00020,2\u0007\u0010¬\u0001\u001a\u00020KH\u0000¢\u0006\u0003\b\u0083\u0002J+\u0010\u0082\u0002\u001a\u00020,\"\t\b\u0000\u0010\u00ad\u0001*\u00020\u00012\u000f\u0010¬\u0001\u001a\n\u0012\u0005\u0012\u0003H\u00ad\u00010®\u0001H\u0000¢\u0006\u0003\b\u0083\u0002J'\u0010\u0082\u0002\u001a\u00020,\"\t\b\u0000\u0010\u00ad\u0001*\u00020\u00012\b\u0010¬\u0001\u001a\u0003H\u00ad\u0001H\u0000¢\u0006\u0006\b\u0083\u0002\u0010\u0084\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR \u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R$\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u00148A@AX\u0080\u000e¢\u0006\f\u001a\u0004\b\u001a\u0010\u0016\"\u0004\b\u001b\u0010\u0018R\"\u0010\u001c\u001a\n\u0018\u00010\u001dj\u0004\u0018\u0001`\u001eX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R(\u0010#\u001a\u000e\u0012\b\u0012\u00060\u001dj\u0002`\u001e\u0018\u00010$X\u0080\u000e¢\u0006\u0010\n\u0002\u0010)\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u001a\u0010*\u001a\b\u0012\u0004\u0012\u00020,0+X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b-\u0010.R \u0010/\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020,0100X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b2\u00103R\"\u00104\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020,01058AX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b6\u00107R \u00108\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020,0100X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b9\u00103R \u0010:\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020,0105X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b;\u00107R \u0010<\u001a\u000e\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u00020,0=X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b>\u0010?R \u0010@\u001a\u000e\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u00020A0=X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bB\u0010?R\"\u0010I\u001a\u0010\u0012\u0004\u0012\u00020J\u0012\u0006\u0012\u0004\u0018\u00010K0=X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bL\u0010?R&\u0010M\u001a\u0014\u0012\u0004\u0012\u00020K\u0012\n\u0012\b\u0012\u0004\u0012\u00020N0+0=X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bO\u0010?R\"\u0010R\u001a\u0004\u0018\u00010Q2\b\u0010P\u001a\u0004\u0018\u00010Q@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\bS\u0010TR\u001c\u0010U\u001a\u0004\u0018\u00010VX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bW\u0010X\"\u0004\bY\u0010ZR\u001a\u0010[\u001a\b\u0012\u0004\u0012\u00020]0\\X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b^\u0010_R\u001c\u0010`\u001a\u00020a8@X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bb\u0010c\"\u0004\bd\u0010eR\u0014\u0010f\u001a\u00020gX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bh\u0010iR\u001a\u0010j\u001a\u00020kX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bl\u0010m\"\u0004\bn\u0010oR$\u0010p\u001a\u00020k2\u0006\u0010p\u001a\u00020k8@@AX\u0080\u000e¢\u0006\f\u001a\u0004\bq\u0010m\"\u0004\br\u0010oR,\u0010s\u001a\u001a\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020u0t\u0012\b\u0012\u00060vR\u00020\u00030=X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bw\u0010?R8\u0010x\u001a\u001f\u0012\u0013\u0012\u00110,¢\u0006\f\bz\u0012\b\b{\u0012\u0004\b\b(|\u0012\u0004\u0012\u00020\u0006\u0018\u00010yX\u0080\u000e¢\u0006\u000f\n\u0000\u001a\u0004\b}\u0010~\"\u0005\b\u007f\u0010\u0080\u0001R<\u0010\u0081\u0001\u001a \u0012\u0014\u0012\u00120,¢\u0006\r\bz\u0012\t\b{\u0012\u0005\b\b(\u0082\u0001\u0012\u0004\u0012\u00020\u0006\u0018\u00010yX\u0080\u000e¢\u0006\u0011\n\u0000\u001a\u0005\b\u0083\u0001\u0010~\"\u0006\b\u0084\u0001\u0010\u0080\u0001R#\u0010\u0085\u0001\u001a\u000f\u0012\u0004\u0012\u00020,\u0012\u0005\u0012\u00030\u0086\u00010=X\u0080\u0004¢\u0006\t\n\u0000\u001a\u0005\b\u0087\u0001\u0010?R\u000f\u0010¿\u0001\u001a\u00020JX\u0082\u000e¢\u0006\u0002\n\u0000R\u0015\u0010À\u0001\u001a\b\u0012\u0004\u0012\u00020,0\\X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010Ï\u0001\u001a\u0004\u0018\u00010u8@X\u0080\u0004¢\u0006\b\u001a\u0006\bÐ\u0001\u0010Ñ\u0001R\u0019\u0010\u0085\u0002\u001a\u0004\u0018\u00010,8@X\u0080\u0004¢\u0006\b\u001a\u0006\b\u0086\u0002\u0010\u0087\u0002R\u001e\u0010\u0088\u0002\u001a\t\u0012\u0004\u0012\u00020,0\u0089\u0002X\u0080\u0004¢\u0006\n\n\u0000\u001a\u0006\b\u008a\u0002\u0010\u008b\u0002R\u0019\u0010\u008c\u0002\u001a\u0004\u0018\u00010,8@X\u0080\u0004¢\u0006\b\u001a\u0006\b\u008d\u0002\u0010\u0087\u0002¨\u0006\u008f\u0002"}, d2 = {"Landroidx/navigation/internal/NavControllerImpl;", "", "navController", "Landroidx/navigation/NavController;", "updateOnBackPressedCallbackEnabledCallback", "Lkotlin/Function0;", "", "<init>", "(Landroidx/navigation/NavController;Lkotlin/jvm/functions/Function0;)V", "getNavController", "()Landroidx/navigation/NavController;", "getUpdateOnBackPressedCallbackEnabledCallback", "()Lkotlin/jvm/functions/Function0;", "setUpdateOnBackPressedCallbackEnabledCallback", "(Lkotlin/jvm/functions/Function0;)V", "navContext", "Landroidx/navigation/internal/NavContext;", "getNavContext", "()Landroidx/navigation/internal/NavContext;", "_graph", "Landroidx/navigation/NavGraph;", "get_graph$navigation_runtime_release", "()Landroidx/navigation/NavGraph;", "set_graph$navigation_runtime_release", "(Landroidx/navigation/NavGraph;)V", "graph", "getGraph$navigation_runtime_release", "setGraph$navigation_runtime_release", "navigatorStateToRestore", "Landroid/os/Bundle;", "Landroidx/savedstate/SavedState;", "getNavigatorStateToRestore$navigation_runtime_release", "()Landroid/os/Bundle;", "setNavigatorStateToRestore$navigation_runtime_release", "(Landroid/os/Bundle;)V", "backStackToRestore", "", "getBackStackToRestore$navigation_runtime_release", "()[Landroid/os/Bundle;", "setBackStackToRestore$navigation_runtime_release", "([Landroid/os/Bundle;)V", "[Landroid/os/Bundle;", "backQueue", "Lkotlin/collections/ArrayDeque;", "Landroidx/navigation/NavBackStackEntry;", "getBackQueue$navigation_runtime_release", "()Lkotlin/collections/ArrayDeque;", "_currentBackStack", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "get_currentBackStack$navigation_runtime_release", "()Lkotlinx/coroutines/flow/MutableStateFlow;", "currentBackStack", "Lkotlinx/coroutines/flow/StateFlow;", "getCurrentBackStack$navigation_runtime_release", "()Lkotlinx/coroutines/flow/StateFlow;", "_visibleEntries", "get_visibleEntries$navigation_runtime_release", "visibleEntries", "getVisibleEntries$navigation_runtime_release", "childToParentEntries", "", "getChildToParentEntries$navigation_runtime_release", "()Ljava/util/Map;", "parentToChildCount", "Landroidx/navigation/internal/AtomicInt;", "getParentToChildCount$navigation_runtime_release", "linkChildToParent", "child", "parent", "linkChildToParent$navigation_runtime_release", "unlinkChildFromParent", "unlinkChildFromParent$navigation_runtime_release", "backStackMap", "", "", "getBackStackMap$navigation_runtime_release", "backStackStates", "Landroidx/navigation/NavBackStackEntryState;", "getBackStackStates$navigation_runtime_release", ES6Iterator.VALUE_PROPERTY, "Landroidx/lifecycle/LifecycleOwner;", "lifecycleOwner", "getLifecycleOwner$navigation_runtime_release", "()Landroidx/lifecycle/LifecycleOwner;", "viewModel", "Landroidx/navigation/NavControllerViewModel;", "getViewModel$navigation_runtime_release", "()Landroidx/navigation/NavControllerViewModel;", "setViewModel$navigation_runtime_release", "(Landroidx/navigation/NavControllerViewModel;)V", "onDestinationChangedListeners", "", "Landroidx/navigation/NavController$OnDestinationChangedListener;", "getOnDestinationChangedListeners$navigation_runtime_release", "()Ljava/util/List;", "hostLifecycleState", "Landroidx/lifecycle/Lifecycle$State;", "getHostLifecycleState$navigation_runtime_release", "()Landroidx/lifecycle/Lifecycle$State;", "setHostLifecycleState$navigation_runtime_release", "(Landroidx/lifecycle/Lifecycle$State;)V", "lifecycleObserver", "Landroidx/lifecycle/LifecycleObserver;", "getLifecycleObserver$navigation_runtime_release", "()Landroidx/lifecycle/LifecycleObserver;", "_navigatorProvider", "Landroidx/navigation/NavigatorProvider;", "get_navigatorProvider$navigation_runtime_release", "()Landroidx/navigation/NavigatorProvider;", "set_navigatorProvider$navigation_runtime_release", "(Landroidx/navigation/NavigatorProvider;)V", "navigatorProvider", "getNavigatorProvider$navigation_runtime_release", "setNavigatorProvider$navigation_runtime_release", "navigatorState", "Landroidx/navigation/Navigator;", "Landroidx/navigation/NavDestination;", "Landroidx/navigation/NavController$NavControllerNavigatorState;", "getNavigatorState$navigation_runtime_release", "addToBackStackHandler", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "backStackEntry", "getAddToBackStackHandler$navigation_runtime_release", "()Lkotlin/jvm/functions/Function1;", "setAddToBackStackHandler$navigation_runtime_release", "(Lkotlin/jvm/functions/Function1;)V", "popFromBackStackHandler", "popUpTo", "getPopFromBackStackHandler$navigation_runtime_release", "setPopFromBackStackHandler$navigation_runtime_release", "entrySavedState", "", "getEntrySavedState$navigation_runtime_release", "navigateInternal", "navigator", "entries", "navOptions", "Landroidx/navigation/NavOptions;", "navigatorExtras", "Landroidx/navigation/Navigator$Extras;", "handler", "navigateInternal$navigation_runtime_release", "popBackStackInternal", "saveState", "popBackStackInternal$navigation_runtime_release", "push", "state", "push$navigation_runtime_release", "createBackStackEntry", "destination", "arguments", "createBackStackEntry$navigation_runtime_release", "pop", "superCallback", "pop$navigation_runtime_release", "markTransitionComplete", "entry", "markTransitionComplete$navigation_runtime_release", "prepareForTransition", "prepareForTransition$navigation_runtime_release", "addOnDestinationChangedListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "addOnDestinationChangedListener$navigation_runtime_release", "removeOnDestinationChangedListener", "removeOnDestinationChangedListener$navigation_runtime_release", "popBackStack", "popBackStack$navigation_runtime_release", "destinationId", "inclusive", "route", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlin/reflect/KClass;", "(Ljava/lang/Object;ZZ)Z", "executePopOperations", "popOperations", "foundDestination", "executePopOperations$navigation_runtime_release", "popBackStackFromNavigator", "onComplete", "popBackStackFromNavigator$navigation_runtime_release", "popEntryFromBackStack", "savedState", "popEntryFromBackStack$navigation_runtime_release", "clearBackStack", "clearBackStack$navigation_runtime_release", Signatures.MATH_TO_BOOLEAN, "clearBackStackInternal", "clearBackStackInternal$navigation_runtime_release", "dispatchReentrantCount", "backStackEntriesToDispatch", "dispatchOnDestinationChanged", "dispatchOnDestinationChanged$navigation_runtime_release", "updateBackStackLifecycle", "updateBackStackLifecycle$navigation_runtime_release", "populateVisibleEntries", "populateVisibleEntries$navigation_runtime_release", "setGraph", "startDestinationArgs", "onGraphCreated", "onGraphCreated$navigation_runtime_release", "findInvalidDestinationDisplayNameInDeepLink", "deepLink", "", "findInvalidDestinationDisplayNameInDeepLink$navigation_runtime_release", "currentDestination", "getCurrentDestination$navigation_runtime_release", "()Landroidx/navigation/NavDestination;", "findDestination", "matchingDest", "findDestination$navigation_runtime_release", "findDestinationComprehensive", "searchChildren", "findDestinationComprehensive$navigation_runtime_release", "getTopGraph", "getTopGraph$navigation_runtime_release", "generateRouteFilled", "generateRouteFilled$navigation_runtime_release", "(Ljava/lang/Object;)Ljava/lang/String;", "navigate", "Landroid/net/Uri;", "Landroidx/navigation/NavUri;", "navigate$navigation_runtime_release", "request", "Landroidx/navigation/NavDeepLinkRequest;", "node", "args", "launchSingleTopInternal", "restoreStateInternal", "id", "executeRestoreState", "instantiateBackStack", "backStackState", "addEntryToBackStack", "finalArgs", "restoredEntries", "builder", "Landroidx/navigation/NavOptionsBuilder;", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "(Ljava/lang/Object;Landroidx/navigation/NavOptions;Landroidx/navigation/Navigator$Extras;)V", "saveState$navigation_runtime_release", "restoreState", "navState", "restoreState$navigation_runtime_release", "setLifecycleOwner", "owner", "setLifecycleOwner$navigation_runtime_release", "setViewModelStore", "viewModelStore", "Landroidx/lifecycle/ViewModelStore;", "setViewModelStore$navigation_runtime_release", "getViewModelStoreOwner", "Landroidx/lifecycle/ViewModelStoreOwner;", "navGraphId", "getViewModelStoreOwner$navigation_runtime_release", "getBackStackEntry", "getBackStackEntry$navigation_runtime_release", "(Ljava/lang/Object;)Landroidx/navigation/NavBackStackEntry;", "currentBackStackEntry", "getCurrentBackStackEntry$navigation_runtime_release", "()Landroidx/navigation/NavBackStackEntry;", "_currentBackStackEntryFlow", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "get_currentBackStackEntryFlow$navigation_runtime_release", "()Lkotlinx/coroutines/flow/MutableSharedFlow;", "previousBackStackEntry", "getPreviousBackStackEntry$navigation_runtime_release", "Companion", "navigation-runtime_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class NavControllerImpl {
    private static final String KEY_BACK_STACK = "android-support-nav:controller:backStack";
    private static final String KEY_BACK_STACK_DEST_IDS = "android-support-nav:controller:backStackDestIds";
    private static final String KEY_BACK_STACK_IDS = "android-support-nav:controller:backStackIds";
    private static final String KEY_BACK_STACK_STATES_IDS = "android-support-nav:controller:backStackStates";
    private static final String KEY_BACK_STACK_STATES_PREFIX = "android-support-nav:controller:backStackStates:";
    private static final String KEY_NAVIGATOR_STATE = "android-support-nav:controller:navigatorState";
    private static final String KEY_NAVIGATOR_STATE_NAMES = "android-support-nav:controller:navigatorState:names";
    public static final String TAG = "NavController";
    private final MutableStateFlow<List<NavBackStackEntry>> _currentBackStack;
    private final MutableSharedFlow<NavBackStackEntry> _currentBackStackEntryFlow;
    private NavGraph _graph;
    private NavigatorProvider _navigatorProvider;
    private final MutableStateFlow<List<NavBackStackEntry>> _visibleEntries;
    private Function1<? super NavBackStackEntry, Unit> addToBackStackHandler;
    private final ArrayDeque<NavBackStackEntry> backQueue;
    private final List<NavBackStackEntry> backStackEntriesToDispatch;
    private final Map<Integer, String> backStackMap;
    private final Map<String, ArrayDeque<NavBackStackEntryState>> backStackStates;
    private Bundle[] backStackToRestore;
    private final Map<NavBackStackEntry, NavBackStackEntry> childToParentEntries;
    private final StateFlow<List<NavBackStackEntry>> currentBackStack;
    private int dispatchReentrantCount;
    private final Map<NavBackStackEntry, Boolean> entrySavedState;
    private Lifecycle.State hostLifecycleState;
    private final LifecycleObserver lifecycleObserver;
    private LifecycleOwner lifecycleOwner;
    private final NavController navController;
    private final Map<Navigator<? extends NavDestination>, NavController.NavControllerNavigatorState> navigatorState;
    private Bundle navigatorStateToRestore;
    private final List<NavController.OnDestinationChangedListener> onDestinationChangedListeners;
    private final Map<NavBackStackEntry, AtomicInt> parentToChildCount;
    private Function1<? super NavBackStackEntry, Unit> popFromBackStackHandler;
    private Function0<Unit> updateOnBackPressedCallbackEnabledCallback;
    private NavControllerViewModel viewModel;
    private final StateFlow<List<NavBackStackEntry>> visibleEntries;

    public NavControllerImpl(NavController navController, Function0<Unit> updateOnBackPressedCallbackEnabledCallback) {
        Intrinsics.checkNotNullParameter(navController, "navController");
        Intrinsics.checkNotNullParameter(updateOnBackPressedCallbackEnabledCallback, "updateOnBackPressedCallbackEnabledCallback");
        this.navController = navController;
        this.updateOnBackPressedCallbackEnabledCallback = updateOnBackPressedCallbackEnabledCallback;
        this.backQueue = new ArrayDeque<>();
        MutableStateFlow<List<NavBackStackEntry>> MutableStateFlow = StateFlowKt.MutableStateFlow(CollectionsKt.emptyList());
        this._currentBackStack = MutableStateFlow;
        this.currentBackStack = FlowKt.asStateFlow(MutableStateFlow);
        MutableStateFlow<List<NavBackStackEntry>> MutableStateFlow2 = StateFlowKt.MutableStateFlow(CollectionsKt.emptyList());
        this._visibleEntries = MutableStateFlow2;
        this.visibleEntries = FlowKt.asStateFlow(MutableStateFlow2);
        this.childToParentEntries = new LinkedHashMap();
        this.parentToChildCount = new LinkedHashMap();
        this.backStackMap = new LinkedHashMap();
        this.backStackStates = new LinkedHashMap();
        this.onDestinationChangedListeners = new ArrayList();
        this.hostLifecycleState = Lifecycle.State.INITIALIZED;
        this.lifecycleObserver = new LifecycleEventObserver() { // from class: androidx.navigation.internal.NavControllerImpl$$ExternalSyntheticLambda8
            @Override // androidx.lifecycle.LifecycleEventObserver
            public final void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
                NavControllerImpl.lifecycleObserver$lambda$1(this.f$0, lifecycleOwner, event);
            }
        };
        this._navigatorProvider = new NavigatorProvider();
        this.navigatorState = new LinkedHashMap();
        this.entrySavedState = new LinkedHashMap();
        this.backStackEntriesToDispatch = new ArrayList();
        this._currentBackStackEntryFlow = SharedFlowKt.MutableSharedFlow$default(1, 0, BufferOverflow.DROP_OLDEST, 2, null);
    }

    public final NavController getNavController() {
        return this.navController;
    }

    public final Function0<Unit> getUpdateOnBackPressedCallbackEnabledCallback() {
        return this.updateOnBackPressedCallbackEnabledCallback;
    }

    public final void setUpdateOnBackPressedCallbackEnabledCallback(Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "<set-?>");
        this.updateOnBackPressedCallbackEnabledCallback = function0;
    }

    public final NavContext getNavContext() {
        return this.navController.getNavContext();
    }

    /* JADX INFO: renamed from: get_graph$navigation_runtime_release, reason: from getter */
    public final NavGraph get_graph() {
        return this._graph;
    }

    public final void set_graph$navigation_runtime_release(NavGraph navGraph) {
        this._graph = navGraph;
    }

    public final NavGraph getGraph$navigation_runtime_release() {
        NavGraph navGraph = this._graph;
        if (navGraph == null) {
            throw new IllegalStateException("You must call setGraph() before calling getGraph()".toString());
        }
        Intrinsics.checkNotNull(navGraph, "null cannot be cast to non-null type androidx.navigation.NavGraph");
        return navGraph;
    }

    public final void setGraph$navigation_runtime_release(NavGraph graph) {
        Intrinsics.checkNotNullParameter(graph, "graph");
        setGraph$navigation_runtime_release(graph, null);
    }

    /* JADX INFO: renamed from: getNavigatorStateToRestore$navigation_runtime_release, reason: from getter */
    public final Bundle getNavigatorStateToRestore() {
        return this.navigatorStateToRestore;
    }

    public final void setNavigatorStateToRestore$navigation_runtime_release(Bundle bundle) {
        this.navigatorStateToRestore = bundle;
    }

    /* JADX INFO: renamed from: getBackStackToRestore$navigation_runtime_release, reason: from getter */
    public final Bundle[] getBackStackToRestore() {
        return this.backStackToRestore;
    }

    public final void setBackStackToRestore$navigation_runtime_release(Bundle[] bundleArr) {
        this.backStackToRestore = bundleArr;
    }

    public final ArrayDeque<NavBackStackEntry> getBackQueue$navigation_runtime_release() {
        return this.backQueue;
    }

    public final MutableStateFlow<List<NavBackStackEntry>> get_currentBackStack$navigation_runtime_release() {
        return this._currentBackStack;
    }

    public final StateFlow<List<NavBackStackEntry>> getCurrentBackStack$navigation_runtime_release() {
        return this.currentBackStack;
    }

    public final MutableStateFlow<List<NavBackStackEntry>> get_visibleEntries$navigation_runtime_release() {
        return this._visibleEntries;
    }

    public final StateFlow<List<NavBackStackEntry>> getVisibleEntries$navigation_runtime_release() {
        return this.visibleEntries;
    }

    public final Map<NavBackStackEntry, NavBackStackEntry> getChildToParentEntries$navigation_runtime_release() {
        return this.childToParentEntries;
    }

    public final Map<NavBackStackEntry, AtomicInt> getParentToChildCount$navigation_runtime_release() {
        return this.parentToChildCount;
    }

    public final void linkChildToParent$navigation_runtime_release(NavBackStackEntry child, NavBackStackEntry parent) {
        Intrinsics.checkNotNullParameter(child, "child");
        Intrinsics.checkNotNullParameter(parent, "parent");
        this.childToParentEntries.put(child, parent);
        if (this.parentToChildCount.get(parent) == null) {
            this.parentToChildCount.put(parent, new AtomicInt(0));
        }
        AtomicInt atomicInt = this.parentToChildCount.get(parent);
        Intrinsics.checkNotNull(atomicInt);
        atomicInt.incrementAndGet$navigation_runtime_release();
    }

    public final NavBackStackEntry unlinkChildFromParent$navigation_runtime_release(NavBackStackEntry child) {
        Intrinsics.checkNotNullParameter(child, "child");
        NavBackStackEntry navBackStackEntryRemove = this.childToParentEntries.remove(child);
        if (navBackStackEntryRemove == null) {
            return null;
        }
        AtomicInt atomicInt = this.parentToChildCount.get(navBackStackEntryRemove);
        Integer numValueOf = atomicInt != null ? Integer.valueOf(atomicInt.decrementAndGet$navigation_runtime_release()) : null;
        if (numValueOf != null && numValueOf.intValue() == 0) {
            NavController.NavControllerNavigatorState navControllerNavigatorState = this.navigatorState.get(this._navigatorProvider.getNavigator(navBackStackEntryRemove.getDestination().getNavigatorName()));
            if (navControllerNavigatorState != null) {
                navControllerNavigatorState.markTransitionComplete(navBackStackEntryRemove);
            }
            this.parentToChildCount.remove(navBackStackEntryRemove);
        }
        return navBackStackEntryRemove;
    }

    public final Map<Integer, String> getBackStackMap$navigation_runtime_release() {
        return this.backStackMap;
    }

    public final Map<String, ArrayDeque<NavBackStackEntryState>> getBackStackStates$navigation_runtime_release() {
        return this.backStackStates;
    }

    /* JADX INFO: renamed from: getLifecycleOwner$navigation_runtime_release, reason: from getter */
    public final LifecycleOwner getLifecycleOwner() {
        return this.lifecycleOwner;
    }

    /* JADX INFO: renamed from: getViewModel$navigation_runtime_release, reason: from getter */
    public final NavControllerViewModel getViewModel() {
        return this.viewModel;
    }

    public final void setViewModel$navigation_runtime_release(NavControllerViewModel navControllerViewModel) {
        this.viewModel = navControllerViewModel;
    }

    public final List<NavController.OnDestinationChangedListener> getOnDestinationChangedListeners$navigation_runtime_release() {
        return this.onDestinationChangedListeners;
    }

    public final void setHostLifecycleState$navigation_runtime_release(Lifecycle.State state) {
        Intrinsics.checkNotNullParameter(state, "<set-?>");
        this.hostLifecycleState = state;
    }

    public final Lifecycle.State getHostLifecycleState$navigation_runtime_release() {
        if (this.lifecycleOwner == null) {
            return Lifecycle.State.CREATED;
        }
        return this.hostLifecycleState;
    }

    /* JADX INFO: renamed from: getLifecycleObserver$navigation_runtime_release, reason: from getter */
    public final LifecycleObserver getLifecycleObserver() {
        return this.lifecycleObserver;
    }

    static final void lifecycleObserver$lambda$1(NavControllerImpl navControllerImpl, LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "<unused var>");
        Intrinsics.checkNotNullParameter(event, "event");
        navControllerImpl.hostLifecycleState = event.getTargetState();
        if (navControllerImpl._graph != null) {
            Iterator it = CollectionsKt.toMutableList((Collection) navControllerImpl.backQueue).iterator();
            while (it.hasNext()) {
                ((NavBackStackEntry) it.next()).handleLifecycleEvent(event);
            }
        }
    }

    public final NavigatorProvider get_navigatorProvider$navigation_runtime_release() {
        return this._navigatorProvider;
    }

    public final void set_navigatorProvider$navigation_runtime_release(NavigatorProvider navigatorProvider) {
        Intrinsics.checkNotNullParameter(navigatorProvider, "<set-?>");
        this._navigatorProvider = navigatorProvider;
    }

    /* JADX INFO: renamed from: getNavigatorProvider$navigation_runtime_release, reason: from getter */
    public final NavigatorProvider get_navigatorProvider() {
        return this._navigatorProvider;
    }

    public final void setNavigatorProvider$navigation_runtime_release(NavigatorProvider navigatorProvider) {
        Intrinsics.checkNotNullParameter(navigatorProvider, "navigatorProvider");
        if (!this.backQueue.isEmpty()) {
            throw new IllegalStateException("NavigatorProvider must be set before setGraph call".toString());
        }
        this._navigatorProvider = navigatorProvider;
    }

    public final Map<Navigator<? extends NavDestination>, NavController.NavControllerNavigatorState> getNavigatorState$navigation_runtime_release() {
        return this.navigatorState;
    }

    public final Function1<NavBackStackEntry, Unit> getAddToBackStackHandler$navigation_runtime_release() {
        return this.addToBackStackHandler;
    }

    public final void setAddToBackStackHandler$navigation_runtime_release(Function1<? super NavBackStackEntry, Unit> function1) {
        this.addToBackStackHandler = function1;
    }

    public final Function1<NavBackStackEntry, Unit> getPopFromBackStackHandler$navigation_runtime_release() {
        return this.popFromBackStackHandler;
    }

    public final void setPopFromBackStackHandler$navigation_runtime_release(Function1<? super NavBackStackEntry, Unit> function1) {
        this.popFromBackStackHandler = function1;
    }

    public final Map<NavBackStackEntry, Boolean> getEntrySavedState$navigation_runtime_release() {
        return this.entrySavedState;
    }

    static final Unit navigateInternal$lambda$3(NavBackStackEntry it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void navigateInternal$navigation_runtime_release$default(NavControllerImpl navControllerImpl, Navigator navigator, List list, NavOptions navOptions, Navigator.Extras extras, Function1 function1, int i, Object obj) {
        if ((i & 16) != 0) {
            function1 = new Function1() { // from class: androidx.navigation.internal.NavControllerImpl$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return NavControllerImpl.navigateInternal$lambda$3((NavBackStackEntry) obj2);
                }
            };
        }
        navControllerImpl.navigateInternal$navigation_runtime_release(navigator, list, navOptions, extras, function1);
    }

    public final void navigateInternal$navigation_runtime_release(Navigator<? extends NavDestination> navigator, List<NavBackStackEntry> entries, NavOptions navOptions, Navigator.Extras navigatorExtras, Function1<? super NavBackStackEntry, Unit> handler) {
        Intrinsics.checkNotNullParameter(navigator, "navigator");
        Intrinsics.checkNotNullParameter(entries, "entries");
        Intrinsics.checkNotNullParameter(handler, "handler");
        this.addToBackStackHandler = handler;
        navigator.navigate(entries, navOptions, navigatorExtras);
        this.addToBackStackHandler = null;
    }

    static final Unit popBackStackInternal$lambda$4(NavBackStackEntry it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void popBackStackInternal$navigation_runtime_release$default(NavControllerImpl navControllerImpl, Navigator navigator, NavBackStackEntry navBackStackEntry, boolean z, Function1 function1, int i, Object obj) {
        if ((i & 8) != 0) {
            function1 = new Function1() { // from class: androidx.navigation.internal.NavControllerImpl$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return NavControllerImpl.popBackStackInternal$lambda$4((NavBackStackEntry) obj2);
                }
            };
        }
        navControllerImpl.popBackStackInternal$navigation_runtime_release(navigator, navBackStackEntry, z, function1);
    }

    public final void popBackStackInternal$navigation_runtime_release(Navigator<? extends NavDestination> navigator, NavBackStackEntry popUpTo, boolean saveState, Function1<? super NavBackStackEntry, Unit> handler) {
        Intrinsics.checkNotNullParameter(navigator, "navigator");
        Intrinsics.checkNotNullParameter(popUpTo, "popUpTo");
        Intrinsics.checkNotNullParameter(handler, "handler");
        this.popFromBackStackHandler = handler;
        navigator.popBackStack(popUpTo, saveState);
        this.popFromBackStackHandler = null;
    }

    public final void push$navigation_runtime_release(NavController.NavControllerNavigatorState state, NavBackStackEntry backStackEntry) {
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(backStackEntry, "backStackEntry");
        Navigator navigator = this._navigatorProvider.getNavigator(backStackEntry.getDestination().getNavigatorName());
        if (Intrinsics.areEqual(navigator, state.getNavigator())) {
            Function1<? super NavBackStackEntry, Unit> function1 = this.addToBackStackHandler;
            if (function1 != null) {
                function1.invoke(backStackEntry);
                state.addInternal(backStackEntry);
                return;
            }
            Log.INSTANCE.i(TAG, "Ignoring add of destination " + backStackEntry.getDestination() + " outside of the call to navigate(). ");
            return;
        }
        NavController.NavControllerNavigatorState navControllerNavigatorState = this.navigatorState.get(navigator);
        if (navControllerNavigatorState == null) {
            throw new IllegalStateException(("NavigatorBackStack for " + backStackEntry.getDestination().getNavigatorName() + " should already be created").toString());
        }
        navControllerNavigatorState.push(backStackEntry);
    }

    public final NavBackStackEntry createBackStackEntry$navigation_runtime_release(NavDestination destination, Bundle arguments) {
        Intrinsics.checkNotNullParameter(destination, "destination");
        return NavBackStackEntry.Companion.create$default(NavBackStackEntry.INSTANCE, getNavContext(), destination, arguments, getHostLifecycleState$navigation_runtime_release(), this.viewModel, null, null, 96, null);
    }

    public final void pop$navigation_runtime_release(NavController.NavControllerNavigatorState state, NavBackStackEntry popUpTo, boolean saveState, final Function0<Unit> superCallback) {
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(popUpTo, "popUpTo");
        Intrinsics.checkNotNullParameter(superCallback, "superCallback");
        Navigator navigator = this._navigatorProvider.getNavigator(popUpTo.getDestination().getNavigatorName());
        this.entrySavedState.put(popUpTo, Boolean.valueOf(saveState));
        if (Intrinsics.areEqual(navigator, state.getNavigator())) {
            Function1<? super NavBackStackEntry, Unit> function1 = this.popFromBackStackHandler;
            if (function1 != null) {
                function1.invoke(popUpTo);
                superCallback.invoke();
                return;
            } else {
                popBackStackFromNavigator$navigation_runtime_release(popUpTo, new Function0() { // from class: androidx.navigation.internal.NavControllerImpl$$ExternalSyntheticLambda12
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return NavControllerImpl.pop$lambda$6(superCallback);
                    }
                });
                return;
            }
        }
        NavController.NavControllerNavigatorState navControllerNavigatorState = this.navigatorState.get(navigator);
        Intrinsics.checkNotNull(navControllerNavigatorState);
        navControllerNavigatorState.pop(popUpTo, saveState);
    }

    static final Unit pop$lambda$6(Function0 function0) {
        function0.invoke();
        return Unit.INSTANCE;
    }

    public final void markTransitionComplete$navigation_runtime_release(NavController.NavControllerNavigatorState state, NavBackStackEntry entry, Function0<Unit> superCallback) {
        NavControllerViewModel navControllerViewModel;
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(entry, "entry");
        Intrinsics.checkNotNullParameter(superCallback, "superCallback");
        boolean zAreEqual = Intrinsics.areEqual((Object) this.entrySavedState.get(entry), (Object) true);
        superCallback.invoke();
        this.entrySavedState.remove(entry);
        if (!this.backQueue.contains(entry)) {
            unlinkChildFromParent$navigation_runtime_release(entry);
            if (entry.getLifecycle().getState().isAtLeast(Lifecycle.State.CREATED)) {
                entry.setMaxLifecycle(Lifecycle.State.DESTROYED);
            }
            ArrayDeque<NavBackStackEntry> arrayDeque = this.backQueue;
            if (!(arrayDeque instanceof Collection) || !arrayDeque.isEmpty()) {
                Iterator<NavBackStackEntry> it = arrayDeque.iterator();
                while (it.hasNext()) {
                    if (Intrinsics.areEqual(it.next().getId(), entry.getId())) {
                    }
                }
                if (!zAreEqual && (navControllerViewModel = this.viewModel) != null) {
                    navControllerViewModel.clear(entry.getId());
                }
            } else if (!zAreEqual) {
                navControllerViewModel.clear(entry.getId());
            }
            updateBackStackLifecycle$navigation_runtime_release();
            this._visibleEntries.tryEmit(populateVisibleEntries$navigation_runtime_release());
            return;
        }
        if (state.getIsNavigating()) {
            return;
        }
        updateBackStackLifecycle$navigation_runtime_release();
        this._currentBackStack.tryEmit(CollectionsKt.toMutableList((Collection) this.backQueue));
        this._visibleEntries.tryEmit(populateVisibleEntries$navigation_runtime_release());
    }

    public final void prepareForTransition$navigation_runtime_release(NavBackStackEntry entry) {
        Intrinsics.checkNotNullParameter(entry, "entry");
        if (this.backQueue.contains(entry)) {
            entry.setMaxLifecycle(Lifecycle.State.STARTED);
            return;
        }
        throw new IllegalStateException("Cannot transition entry that is not in the back stack");
    }

    public final void addOnDestinationChangedListener$navigation_runtime_release(NavController.OnDestinationChangedListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.onDestinationChangedListeners.add(listener);
        if (this.backQueue.isEmpty()) {
            return;
        }
        NavBackStackEntry navBackStackEntryLast = this.backQueue.last();
        listener.onDestinationChanged(this.navController, navBackStackEntryLast.getDestination(), navBackStackEntryLast.getArguments());
    }

    public final void removeOnDestinationChangedListener$navigation_runtime_release(NavController.OnDestinationChangedListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.onDestinationChangedListeners.remove(listener);
    }

    public final boolean popBackStack$navigation_runtime_release() {
        if (this.backQueue.isEmpty()) {
            return false;
        }
        NavDestination currentDestination$navigation_runtime_release = getCurrentDestination$navigation_runtime_release();
        Intrinsics.checkNotNull(currentDestination$navigation_runtime_release);
        return popBackStack$navigation_runtime_release(currentDestination$navigation_runtime_release.getId(), true);
    }

    public final boolean popBackStack$navigation_runtime_release(int destinationId, boolean inclusive) {
        return popBackStack$navigation_runtime_release(destinationId, inclusive, false);
    }

    public final boolean popBackStack$navigation_runtime_release(int destinationId, boolean inclusive, boolean saveState) {
        return popBackStackInternal$navigation_runtime_release(destinationId, inclusive, saveState) && dispatchOnDestinationChanged$navigation_runtime_release();
    }

    public final boolean popBackStack$navigation_runtime_release(String route, boolean inclusive, boolean saveState) {
        Intrinsics.checkNotNullParameter(route, "route");
        return popBackStackInternal$navigation_runtime_release(route, inclusive, saveState) && dispatchOnDestinationChanged$navigation_runtime_release();
    }

    public final <T> boolean popBackStack$navigation_runtime_release(KClass<T> route, boolean inclusive, boolean saveState) {
        Intrinsics.checkNotNullParameter(route, "route");
        int iGenerateHashCode = RouteSerializerKt.generateHashCode(SerializersKt.serializer(route));
        if (findDestinationComprehensive$navigation_runtime_release$default(this, getGraph$navigation_runtime_release(), iGenerateHashCode, true, null, 8, null) == null) {
            throw new IllegalArgumentException(("Destination with route " + route.getSimpleName() + " cannot be found in navigation graph " + getGraph$navigation_runtime_release()).toString());
        }
        return popBackStack$navigation_runtime_release(iGenerateHashCode, inclusive, saveState);
    }

    public final <T> boolean popBackStack$navigation_runtime_release(T route, boolean inclusive, boolean saveState) {
        Intrinsics.checkNotNullParameter(route, "route");
        return popBackStackInternal$navigation_runtime_release(route, inclusive, saveState) && dispatchOnDestinationChanged$navigation_runtime_release();
    }

    public static /* synthetic */ boolean popBackStackInternal$navigation_runtime_release$default(NavControllerImpl navControllerImpl, int i, boolean z, boolean z2, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            z2 = false;
        }
        return navControllerImpl.popBackStackInternal$navigation_runtime_release(i, z, z2);
    }

    public final boolean popBackStackInternal$navigation_runtime_release(int destinationId, boolean inclusive, boolean saveState) {
        NavDestination destination;
        if (this.backQueue.isEmpty()) {
            return false;
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = CollectionsKt.reversed(this.backQueue).iterator();
        do {
            if (!it.hasNext()) {
                destination = null;
                break;
            }
            destination = ((NavBackStackEntry) it.next()).getDestination();
            Navigator navigator = this._navigatorProvider.getNavigator(destination.getNavigatorName());
            if (inclusive || destination.getId() != destinationId) {
                arrayList.add(navigator);
            }
        } while (destination.getId() != destinationId);
        if (destination == null) {
            String displayName = NavDestination.INSTANCE.getDisplayName(getNavContext(), destinationId);
            Log.INSTANCE.i(TAG, "Ignoring popBackStack to destination " + displayName + " as it was not found on the current back stack");
            return false;
        }
        return executePopOperations$navigation_runtime_release(arrayList, destination, inclusive, saveState);
    }

    public static /* synthetic */ boolean popBackStackInternal$navigation_runtime_release$default(NavControllerImpl navControllerImpl, Object obj, boolean z, boolean z2, int i, Object obj2) {
        if ((i & 4) != 0) {
            z2 = false;
        }
        return navControllerImpl.popBackStackInternal$navigation_runtime_release(obj, z, z2);
    }

    public final <T> boolean popBackStackInternal$navigation_runtime_release(T route, boolean inclusive, boolean saveState) {
        Intrinsics.checkNotNullParameter(route, "route");
        return popBackStackInternal$navigation_runtime_release(generateRouteFilled$navigation_runtime_release(route), inclusive, saveState);
    }

    public final boolean popBackStackInternal$navigation_runtime_release(String route, boolean inclusive, boolean saveState) {
        NavBackStackEntry navBackStackEntryPrevious;
        boolean zHasRoute;
        Intrinsics.checkNotNullParameter(route, "route");
        if (this.backQueue.isEmpty()) {
            return false;
        }
        ArrayList arrayList = new ArrayList();
        ArrayDeque<NavBackStackEntry> arrayDeque = this.backQueue;
        ListIterator<NavBackStackEntry> listIterator = arrayDeque.listIterator(arrayDeque.size());
        do {
            if (!listIterator.hasPrevious()) {
                navBackStackEntryPrevious = null;
                break;
            }
            navBackStackEntryPrevious = listIterator.previous();
            NavBackStackEntry navBackStackEntry = navBackStackEntryPrevious;
            zHasRoute = navBackStackEntry.getDestination().hasRoute(route, navBackStackEntry.getArguments());
            if (inclusive || !zHasRoute) {
                arrayList.add(this._navigatorProvider.getNavigator(navBackStackEntry.getDestination().getNavigatorName()));
            }
        } while (!zHasRoute);
        NavBackStackEntry navBackStackEntry2 = navBackStackEntryPrevious;
        NavDestination destination = navBackStackEntry2 != null ? navBackStackEntry2.getDestination() : null;
        if (destination == null) {
            Log.INSTANCE.i(TAG, "Ignoring popBackStack to route " + route + " as it was not found on the current back stack");
            return false;
        }
        return executePopOperations$navigation_runtime_release(arrayList, destination, inclusive, saveState);
    }

    public final boolean executePopOperations$navigation_runtime_release(List<? extends Navigator<?>> popOperations, NavDestination foundDestination, boolean inclusive, boolean saveState) {
        final NavControllerImpl navControllerImpl;
        final boolean z;
        Intrinsics.checkNotNullParameter(popOperations, "popOperations");
        Intrinsics.checkNotNullParameter(foundDestination, "foundDestination");
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        final ArrayDeque<NavBackStackEntryState> arrayDeque = new ArrayDeque<>();
        Iterator<? extends Navigator<?>> it = popOperations.iterator();
        while (true) {
            if (!it.hasNext()) {
                navControllerImpl = this;
                z = saveState;
                break;
            }
            Navigator<? extends NavDestination> navigator = (Navigator) it.next();
            final Ref.BooleanRef booleanRef2 = new Ref.BooleanRef();
            navControllerImpl = this;
            z = saveState;
            navControllerImpl.popBackStackInternal$navigation_runtime_release(navigator, this.backQueue.last(), z, new Function1() { // from class: androidx.navigation.internal.NavControllerImpl$$ExternalSyntheticLambda14
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return NavControllerImpl.executePopOperations$lambda$10(booleanRef2, booleanRef, navControllerImpl, z, arrayDeque, (NavBackStackEntry) obj);
                }
            });
            if (!booleanRef2.element) {
                break;
            }
            this = navControllerImpl;
            saveState = z;
        }
        if (z) {
            if (!inclusive) {
                for (NavDestination navDestination : SequencesKt.takeWhile(SequencesKt.generateSequence(foundDestination, (Function1<? super NavDestination, ? extends NavDestination>) new Function1() { // from class: androidx.navigation.internal.NavControllerImpl$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return NavControllerImpl.executePopOperations$lambda$11((NavDestination) obj);
                    }
                }), new Function1() { // from class: androidx.navigation.internal.NavControllerImpl$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return Boolean.valueOf(NavControllerImpl.executePopOperations$lambda$12(this.f$0, (NavDestination) obj));
                    }
                })) {
                    Map<Integer, String> map = navControllerImpl.backStackMap;
                    Integer numValueOf = Integer.valueOf(navDestination.getId());
                    NavBackStackEntryState navBackStackEntryStateFirstOrNull = arrayDeque.firstOrNull();
                    map.put(numValueOf, navBackStackEntryStateFirstOrNull != null ? navBackStackEntryStateFirstOrNull.getId() : null);
                }
            }
            if (!arrayDeque.isEmpty()) {
                NavBackStackEntryState navBackStackEntryStateFirst = arrayDeque.first();
                Iterator it2 = SequencesKt.takeWhile(SequencesKt.generateSequence(findDestination$navigation_runtime_release$default(navControllerImpl, navBackStackEntryStateFirst.getDestinationId(), null, 2, null), (Function1<? super NavDestination, ? extends NavDestination>) new Function1() { // from class: androidx.navigation.internal.NavControllerImpl$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return NavControllerImpl.executePopOperations$lambda$14((NavDestination) obj);
                    }
                }), new Function1() { // from class: androidx.navigation.internal.NavControllerImpl$$ExternalSyntheticLambda4
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return Boolean.valueOf(NavControllerImpl.executePopOperations$lambda$15(this.f$0, (NavDestination) obj));
                    }
                }).iterator();
                while (it2.hasNext()) {
                    navControllerImpl.backStackMap.put(Integer.valueOf(((NavDestination) it2.next()).getId()), navBackStackEntryStateFirst.getId());
                }
                if (navControllerImpl.backStackMap.values().contains(navBackStackEntryStateFirst.getId())) {
                    navControllerImpl.backStackStates.put(navBackStackEntryStateFirst.getId(), arrayDeque);
                }
            }
        }
        navControllerImpl.updateOnBackPressedCallbackEnabledCallback.invoke();
        return booleanRef.element;
    }

    static final Unit executePopOperations$lambda$10(Ref.BooleanRef booleanRef, Ref.BooleanRef booleanRef2, NavControllerImpl navControllerImpl, boolean z, ArrayDeque arrayDeque, NavBackStackEntry entry) {
        Intrinsics.checkNotNullParameter(entry, "entry");
        booleanRef.element = true;
        booleanRef2.element = true;
        navControllerImpl.popEntryFromBackStack$navigation_runtime_release(entry, z, arrayDeque);
        return Unit.INSTANCE;
    }

    static final NavDestination executePopOperations$lambda$11(NavDestination destination) {
        Intrinsics.checkNotNullParameter(destination, "destination");
        NavGraph parent = destination.getParent();
        if (parent == null || parent.getStartDestinationId() != destination.getId()) {
            return null;
        }
        return destination.getParent();
    }

    static final boolean executePopOperations$lambda$12(NavControllerImpl navControllerImpl, NavDestination destination) {
        Intrinsics.checkNotNullParameter(destination, "destination");
        return !navControllerImpl.backStackMap.containsKey(Integer.valueOf(destination.getId()));
    }

    static final NavDestination executePopOperations$lambda$14(NavDestination destination) {
        Intrinsics.checkNotNullParameter(destination, "destination");
        NavGraph parent = destination.getParent();
        if (parent == null || parent.getStartDestinationId() != destination.getId()) {
            return null;
        }
        return destination.getParent();
    }

    static final boolean executePopOperations$lambda$15(NavControllerImpl navControllerImpl, NavDestination destination) {
        Intrinsics.checkNotNullParameter(destination, "destination");
        return !navControllerImpl.backStackMap.containsKey(Integer.valueOf(destination.getId()));
    }

    public final void popBackStackFromNavigator$navigation_runtime_release(NavBackStackEntry popUpTo, Function0<Unit> onComplete) {
        Intrinsics.checkNotNullParameter(popUpTo, "popUpTo");
        Intrinsics.checkNotNullParameter(onComplete, "onComplete");
        int iIndexOf = this.backQueue.indexOf(popUpTo);
        if (iIndexOf < 0) {
            Log.INSTANCE.i(TAG, "Ignoring pop of " + popUpTo + " as it was not found on the current back stack");
            return;
        }
        int i = iIndexOf + 1;
        if (i != this.backQueue.size()) {
            popBackStackInternal$navigation_runtime_release(this.backQueue.get(i).getDestination().getId(), true, false);
        }
        popEntryFromBackStack$navigation_runtime_release$default(this, popUpTo, false, null, 6, null);
        onComplete.invoke();
        this.updateOnBackPressedCallbackEnabledCallback.invoke();
        dispatchOnDestinationChanged$navigation_runtime_release();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void popEntryFromBackStack$navigation_runtime_release$default(NavControllerImpl navControllerImpl, NavBackStackEntry navBackStackEntry, boolean z, ArrayDeque arrayDeque, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            arrayDeque = new ArrayDeque();
        }
        navControllerImpl.popEntryFromBackStack$navigation_runtime_release(navBackStackEntry, z, arrayDeque);
    }

    public final void popEntryFromBackStack$navigation_runtime_release(NavBackStackEntry popUpTo, boolean saveState, ArrayDeque<NavBackStackEntryState> savedState) {
        NavControllerViewModel navControllerViewModel;
        StateFlow<Set<NavBackStackEntry>> transitionsInProgress;
        Set<NavBackStackEntry> value;
        Intrinsics.checkNotNullParameter(popUpTo, "popUpTo");
        Intrinsics.checkNotNullParameter(savedState, "savedState");
        NavBackStackEntry navBackStackEntryLast = this.backQueue.last();
        if (!Intrinsics.areEqual(navBackStackEntryLast, popUpTo)) {
            throw new IllegalStateException(("Attempted to pop " + popUpTo.getDestination() + ", which is not the top of the back stack (" + navBackStackEntryLast.getDestination() + ')').toString());
        }
        CollectionsKt.removeLast(this.backQueue);
        NavController.NavControllerNavigatorState navControllerNavigatorState = this.navigatorState.get(get_navigatorProvider().getNavigator(navBackStackEntryLast.getDestination().getNavigatorName()));
        boolean z = true;
        if ((navControllerNavigatorState == null || (transitionsInProgress = navControllerNavigatorState.getTransitionsInProgress()) == null || (value = transitionsInProgress.getValue()) == null || !value.contains(navBackStackEntryLast)) && !this.parentToChildCount.containsKey(navBackStackEntryLast)) {
            z = false;
        }
        if (navBackStackEntryLast.getLifecycle().getState().isAtLeast(Lifecycle.State.CREATED)) {
            if (saveState) {
                navBackStackEntryLast.setMaxLifecycle(Lifecycle.State.CREATED);
                savedState.addFirst(new NavBackStackEntryState(navBackStackEntryLast));
            }
            if (!z) {
                navBackStackEntryLast.setMaxLifecycle(Lifecycle.State.DESTROYED);
                unlinkChildFromParent$navigation_runtime_release(navBackStackEntryLast);
            } else {
                navBackStackEntryLast.setMaxLifecycle(Lifecycle.State.CREATED);
            }
        }
        if (saveState || z || (navControllerViewModel = this.viewModel) == null) {
            return;
        }
        navControllerViewModel.clear(navBackStackEntryLast.getId());
    }

    public final boolean clearBackStack$navigation_runtime_release(String route) {
        Intrinsics.checkNotNullParameter(route, "route");
        return clearBackStackInternal$navigation_runtime_release(route) && dispatchOnDestinationChanged$navigation_runtime_release();
    }

    public final boolean clearBackStack$navigation_runtime_release(int destinationId) {
        return clearBackStackInternal$navigation_runtime_release(destinationId) && dispatchOnDestinationChanged$navigation_runtime_release();
    }

    public final <T> boolean clearBackStack$navigation_runtime_release(KClass<T> route) {
        Intrinsics.checkNotNullParameter(route, "route");
        return clearBackStack$navigation_runtime_release(RouteSerializerKt.generateHashCode(SerializersKt.serializer(route)));
    }

    public final <T> boolean clearBackStack$navigation_runtime_release(T route) {
        Intrinsics.checkNotNullParameter(route, "route");
        return clearBackStackInternal$navigation_runtime_release(generateRouteFilled$navigation_runtime_release(route)) && dispatchOnDestinationChanged$navigation_runtime_release();
    }

    public final boolean clearBackStackInternal$navigation_runtime_release(int destinationId) {
        Iterator<T> it = this.navigatorState.values().iterator();
        while (it.hasNext()) {
            ((NavController.NavControllerNavigatorState) it.next()).setNavigating(true);
        }
        boolean zRestoreStateInternal = restoreStateInternal(destinationId, null, NavOptionsBuilderKt.navOptions(new Function1() { // from class: androidx.navigation.internal.NavControllerImpl$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return NavControllerImpl.clearBackStackInternal$lambda$19((NavOptionsBuilder) obj);
            }
        }), null);
        Iterator<T> it2 = this.navigatorState.values().iterator();
        while (it2.hasNext()) {
            ((NavController.NavControllerNavigatorState) it2.next()).setNavigating(false);
        }
        return zRestoreStateInternal && popBackStackInternal$navigation_runtime_release(destinationId, true, false);
    }

    static final Unit clearBackStackInternal$lambda$19(NavOptionsBuilder navOptions) {
        Intrinsics.checkNotNullParameter(navOptions, "$this$navOptions");
        navOptions.setRestoreState(true);
        return Unit.INSTANCE;
    }

    public final boolean clearBackStackInternal$navigation_runtime_release(String route) {
        Intrinsics.checkNotNullParameter(route, "route");
        Iterator<T> it = this.navigatorState.values().iterator();
        while (it.hasNext()) {
            ((NavController.NavControllerNavigatorState) it.next()).setNavigating(true);
        }
        boolean zRestoreStateInternal = restoreStateInternal(route);
        Iterator<T> it2 = this.navigatorState.values().iterator();
        while (it2.hasNext()) {
            ((NavController.NavControllerNavigatorState) it2.next()).setNavigating(false);
        }
        return zRestoreStateInternal && popBackStackInternal$navigation_runtime_release(route, true, false);
    }

    public final boolean dispatchOnDestinationChanged$navigation_runtime_release() {
        while (!this.backQueue.isEmpty() && (this.backQueue.last().getDestination() instanceof NavGraph)) {
            popEntryFromBackStack$navigation_runtime_release$default(this, this.backQueue.last(), false, null, 6, null);
        }
        NavBackStackEntry navBackStackEntryLastOrNull = this.backQueue.lastOrNull();
        if (navBackStackEntryLastOrNull != null) {
            this.backStackEntriesToDispatch.add(navBackStackEntryLastOrNull);
        }
        this.dispatchReentrantCount++;
        updateBackStackLifecycle$navigation_runtime_release();
        int i = this.dispatchReentrantCount - 1;
        this.dispatchReentrantCount = i;
        if (i == 0) {
            List<NavBackStackEntry> mutableList = CollectionsKt.toMutableList((Collection) this.backStackEntriesToDispatch);
            this.backStackEntriesToDispatch.clear();
            for (NavBackStackEntry navBackStackEntry : mutableList) {
                Iterator it = CollectionsKt.toList(this.onDestinationChangedListeners).iterator();
                while (it.hasNext()) {
                    ((NavController.OnDestinationChangedListener) it.next()).onDestinationChanged(this.navController, navBackStackEntry.getDestination(), navBackStackEntry.getArguments());
                }
                this._currentBackStackEntryFlow.tryEmit(navBackStackEntry);
            }
            this._currentBackStack.tryEmit(CollectionsKt.toMutableList((Collection) this.backQueue));
            this._visibleEntries.tryEmit(populateVisibleEntries$navigation_runtime_release());
        }
        return navBackStackEntryLastOrNull != null;
    }

    public final void updateBackStackLifecycle$navigation_runtime_release() {
        AtomicInt atomicInt;
        StateFlow<Set<NavBackStackEntry>> transitionsInProgress;
        Set<NavBackStackEntry> value;
        List<NavBackStackEntry> mutableList = CollectionsKt.toMutableList((Collection) this.backQueue);
        if (mutableList.isEmpty()) {
            return;
        }
        NavDestination destination = ((NavBackStackEntry) CollectionsKt.last(mutableList)).getDestination();
        List listMutableListOf = CollectionsKt.mutableListOf(destination);
        if (destination instanceof SupportingPane) {
            boolean z = destination instanceof FloatingWindow;
            Iterator it = CollectionsKt.drop(CollectionsKt.reversed(mutableList), 1).iterator();
            while (it.hasNext()) {
                NavDestination destination2 = ((NavBackStackEntry) it.next()).getDestination();
                if (z && !(destination2 instanceof FloatingWindow) && !(destination2 instanceof NavGraph)) {
                    break;
                }
                listMutableListOf.add(destination2);
                if (!(destination2 instanceof SupportingPane) && !(destination2 instanceof NavGraph)) {
                    break;
                }
            }
        }
        ArrayList arrayList = new ArrayList();
        if (CollectionsKt.last(listMutableListOf) instanceof FloatingWindow) {
            Iterator it2 = CollectionsKt.reversed(mutableList).iterator();
            while (it2.hasNext()) {
                NavDestination destination3 = ((NavBackStackEntry) it2.next()).getDestination();
                arrayList.add(destination3);
                if (!(destination3 instanceof FloatingWindow) && !(destination3 instanceof SupportingPane) && !(destination3 instanceof NavGraph)) {
                    break;
                }
            }
        }
        HashMap map = new HashMap();
        for (NavBackStackEntry navBackStackEntry : CollectionsKt.reversed(mutableList)) {
            Lifecycle.State maxLifecycle = navBackStackEntry.getMaxLifecycle();
            NavDestination destination4 = navBackStackEntry.getDestination();
            NavDestination navDestination = (NavDestination) CollectionsKt.firstOrNull(listMutableListOf);
            if (navDestination != null && navDestination.getId() == destination4.getId()) {
                if (maxLifecycle != Lifecycle.State.RESUMED) {
                    NavController.NavControllerNavigatorState navControllerNavigatorState = this.navigatorState.get(get_navigatorProvider().getNavigator(navBackStackEntry.getDestination().getNavigatorName()));
                    if (!Intrinsics.areEqual((Object) ((navControllerNavigatorState == null || (transitionsInProgress = navControllerNavigatorState.getTransitionsInProgress()) == null || (value = transitionsInProgress.getValue()) == null) ? null : Boolean.valueOf(value.contains(navBackStackEntry))), (Object) true) && ((atomicInt = this.parentToChildCount.get(navBackStackEntry)) == null || atomicInt.get$navigation_runtime_release() != 0)) {
                        map.put(navBackStackEntry, Lifecycle.State.RESUMED);
                    } else {
                        map.put(navBackStackEntry, Lifecycle.State.STARTED);
                    }
                }
                NavDestination navDestination2 = (NavDestination) CollectionsKt.firstOrNull((List) arrayList);
                if (navDestination2 != null && navDestination2.getId() == destination4.getId()) {
                    CollectionsKt.removeFirst(arrayList);
                }
                CollectionsKt.removeFirst(listMutableListOf);
                NavGraph parent = destination4.getParent();
                if (parent != null) {
                    listMutableListOf.add(parent);
                }
            } else if (!arrayList.isEmpty() && destination4.getId() == ((NavDestination) CollectionsKt.first((List) arrayList)).getId()) {
                NavDestination navDestination3 = (NavDestination) CollectionsKt.removeFirst(arrayList);
                if (maxLifecycle == Lifecycle.State.RESUMED) {
                    navBackStackEntry.setMaxLifecycle(Lifecycle.State.STARTED);
                } else if (maxLifecycle != Lifecycle.State.STARTED) {
                    map.put(navBackStackEntry, Lifecycle.State.STARTED);
                }
                NavGraph parent2 = navDestination3.getParent();
                if (parent2 != null && !arrayList.contains(parent2)) {
                    arrayList.add(parent2);
                }
            } else {
                navBackStackEntry.setMaxLifecycle(Lifecycle.State.CREATED);
            }
        }
        for (NavBackStackEntry navBackStackEntry2 : mutableList) {
            Lifecycle.State state = (Lifecycle.State) map.get(navBackStackEntry2);
            if (state != null) {
                navBackStackEntry2.setMaxLifecycle(state);
            } else {
                navBackStackEntry2.updateState();
            }
        }
    }

    public final List<NavBackStackEntry> populateVisibleEntries$navigation_runtime_release() {
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = this.navigatorState.values().iterator();
        while (it.hasNext()) {
            ArrayList arrayList2 = arrayList;
            Set<NavBackStackEntry> value = ((NavController.NavControllerNavigatorState) it.next()).getTransitionsInProgress().getValue();
            ArrayList arrayList3 = new ArrayList();
            for (Object obj : value) {
                NavBackStackEntry navBackStackEntry = (NavBackStackEntry) obj;
                if (!arrayList.contains(navBackStackEntry) && !navBackStackEntry.getMaxLifecycle().isAtLeast(Lifecycle.State.STARTED)) {
                    arrayList3.add(obj);
                }
            }
            CollectionsKt.addAll(arrayList2, arrayList3);
        }
        ArrayList arrayList4 = arrayList;
        ArrayDeque<NavBackStackEntry> arrayDeque = this.backQueue;
        ArrayList arrayList5 = new ArrayList();
        for (NavBackStackEntry navBackStackEntry2 : arrayDeque) {
            NavBackStackEntry navBackStackEntry3 = navBackStackEntry2;
            if (!arrayList.contains(navBackStackEntry3) && navBackStackEntry3.getMaxLifecycle().isAtLeast(Lifecycle.State.STARTED)) {
                arrayList5.add(navBackStackEntry2);
            }
        }
        CollectionsKt.addAll(arrayList4, arrayList5);
        ArrayList arrayList6 = new ArrayList();
        for (Object obj2 : arrayList) {
            if (!(((NavBackStackEntry) obj2).getDestination() instanceof NavGraph)) {
                arrayList6.add(obj2);
            }
        }
        return arrayList6;
    }

    public final void setGraph$navigation_runtime_release(NavGraph graph, Bundle startDestinationArgs) {
        NavControllerImpl navControllerImpl;
        Intrinsics.checkNotNullParameter(graph, "graph");
        if (!this.backQueue.isEmpty() && getHostLifecycleState$navigation_runtime_release() == Lifecycle.State.DESTROYED) {
            throw new IllegalStateException("You cannot set a new graph on a NavController with entries on the back stack after the NavController has been destroyed. Please ensure that your NavHost has the same lifetime as your NavController.".toString());
        }
        if (!Intrinsics.areEqual(this._graph, graph)) {
            NavGraph navGraph = this._graph;
            if (navGraph != null) {
                for (Integer num : new ArrayList(this.backStackMap.keySet())) {
                    Intrinsics.checkNotNull(num);
                    clearBackStackInternal$navigation_runtime_release(num.intValue());
                }
                navControllerImpl = this;
                popBackStackInternal$navigation_runtime_release$default(navControllerImpl, navGraph.getId(), true, false, 4, (Object) null);
            } else {
                navControllerImpl = this;
            }
            navControllerImpl._graph = graph;
            navControllerImpl.onGraphCreated$navigation_runtime_release(startDestinationArgs);
            return;
        }
        int size = graph.getNodes().size();
        for (int i = 0; i < size; i++) {
            NavDestination navDestinationValueAt = graph.getNodes().valueAt(i);
            NavGraph navGraph2 = this._graph;
            Intrinsics.checkNotNull(navGraph2);
            int iKeyAt = navGraph2.getNodes().keyAt(i);
            NavGraph navGraph3 = this._graph;
            Intrinsics.checkNotNull(navGraph3);
            navGraph3.getNodes().replace(iKeyAt, navDestinationValueAt);
        }
        for (NavBackStackEntry navBackStackEntry : this.backQueue) {
            List<NavDestination> listAsReversed = CollectionsKt.asReversed(SequencesKt.toList(NavDestination.INSTANCE.getHierarchy(navBackStackEntry.getDestination())));
            NavDestination navDestinationFindNode = this._graph;
            Intrinsics.checkNotNull(navDestinationFindNode);
            for (NavDestination navDestination : listAsReversed) {
                navDestinationFindNode = navDestinationFindNode;
                if (!Intrinsics.areEqual(navDestination, this._graph) || !Intrinsics.areEqual(navDestinationFindNode, graph)) {
                    if (navDestinationFindNode instanceof NavGraph) {
                        navDestinationFindNode = ((NavGraph) navDestinationFindNode).findNode(navDestination.getId());
                        Intrinsics.checkNotNull(navDestinationFindNode);
                    }
                }
            }
            navBackStackEntry.setDestination(navDestinationFindNode);
        }
    }

    public final void onGraphCreated$navigation_runtime_release(Bundle startDestinationArgs) {
        Bundle bundle = this.navigatorStateToRestore;
        if (bundle != null) {
            Bundle bundleM7930constructorimpl = SavedStateReader.m7930constructorimpl(bundle);
            if (SavedStateReader.m7931containsimpl(bundleM7930constructorimpl, KEY_NAVIGATOR_STATE_NAMES)) {
                for (String str : SavedStateReader.m8004getStringListimpl(bundleM7930constructorimpl, KEY_NAVIGATOR_STATE_NAMES)) {
                    Navigator navigator = this._navigatorProvider.getNavigator(str);
                    if (SavedStateReader.m7931containsimpl(bundleM7930constructorimpl, str)) {
                        navigator.onRestoreState(SavedStateReader.m7987getSavedStateimpl(bundleM7930constructorimpl, str));
                    }
                }
            }
        }
        Bundle[] bundleArr = this.backStackToRestore;
        if (bundleArr != null) {
            for (Bundle bundle2 : bundleArr) {
                NavBackStackEntryState navBackStackEntryState = new NavBackStackEntryState(bundle2);
                NavDestination navDestinationFindDestination$navigation_runtime_release$default = findDestination$navigation_runtime_release$default(this, navBackStackEntryState.getDestinationId(), null, 2, null);
                if (navDestinationFindDestination$navigation_runtime_release$default == null) {
                    throw new IllegalStateException("Restoring the Navigation back stack failed: destination " + NavDestination.INSTANCE.getDisplayName(getNavContext(), navBackStackEntryState.getDestinationId()) + " cannot be found from the current destination " + getCurrentDestination$navigation_runtime_release());
                }
                NavBackStackEntry navBackStackEntryInstantiate = navBackStackEntryState.instantiate(getNavContext(), navDestinationFindDestination$navigation_runtime_release$default, getHostLifecycleState$navigation_runtime_release(), this.viewModel);
                Navigator<? extends NavDestination> navigator2 = this._navigatorProvider.getNavigator(navDestinationFindDestination$navigation_runtime_release$default.getNavigatorName());
                Map<Navigator<? extends NavDestination>, NavController.NavControllerNavigatorState> map = this.navigatorState;
                NavController.NavControllerNavigatorState navControllerNavigatorStateCreateNavControllerNavigatorState$navigation_runtime_release = map.get(navigator2);
                if (navControllerNavigatorStateCreateNavControllerNavigatorState$navigation_runtime_release == null) {
                    navControllerNavigatorStateCreateNavControllerNavigatorState$navigation_runtime_release = this.navController.createNavControllerNavigatorState$navigation_runtime_release(navigator2);
                    map.put(navigator2, navControllerNavigatorStateCreateNavControllerNavigatorState$navigation_runtime_release);
                }
                this.backQueue.add(navBackStackEntryInstantiate);
                navControllerNavigatorStateCreateNavControllerNavigatorState$navigation_runtime_release.addInternal(navBackStackEntryInstantiate);
                NavGraph parent = navBackStackEntryInstantiate.getDestination().getParent();
                if (parent != null) {
                    linkChildToParent$navigation_runtime_release(navBackStackEntryInstantiate, getBackStackEntry$navigation_runtime_release(parent.getId()));
                }
            }
            this.updateOnBackPressedCallbackEnabledCallback.invoke();
            this.backStackToRestore = null;
        }
        Collection<Navigator<? extends NavDestination>> collectionValues = this._navigatorProvider.getNavigators().values();
        ArrayList<Navigator<? extends NavDestination>> arrayList = new ArrayList();
        for (Object obj : collectionValues) {
            if (!((Navigator) obj).getIsAttached()) {
                arrayList.add(obj);
            }
        }
        for (Navigator<? extends NavDestination> navigator3 : arrayList) {
            Map<Navigator<? extends NavDestination>, NavController.NavControllerNavigatorState> map2 = this.navigatorState;
            NavController.NavControllerNavigatorState navControllerNavigatorStateCreateNavControllerNavigatorState$navigation_runtime_release2 = map2.get(navigator3);
            if (navControllerNavigatorStateCreateNavControllerNavigatorState$navigation_runtime_release2 == null) {
                navControllerNavigatorStateCreateNavControllerNavigatorState$navigation_runtime_release2 = this.navController.createNavControllerNavigatorState$navigation_runtime_release(navigator3);
                map2.put(navigator3, navControllerNavigatorStateCreateNavControllerNavigatorState$navigation_runtime_release2);
            }
            navigator3.onAttach(navControllerNavigatorStateCreateNavControllerNavigatorState$navigation_runtime_release2);
        }
        if (this._graph != null && this.backQueue.isEmpty()) {
            if (this.navController.checkDeepLinkHandled$navigation_runtime_release()) {
                return;
            }
            NavGraph navGraph = this._graph;
            Intrinsics.checkNotNull(navGraph);
            navigate$navigation_runtime_release(navGraph, startDestinationArgs, null, null);
            return;
        }
        dispatchOnDestinationChanged$navigation_runtime_release();
    }

    public final String findInvalidDestinationDisplayNameInDeepLink$navigation_runtime_release(int[] deepLink) {
        NavGraph navGraphFindNode;
        NavGraph navGraph;
        Intrinsics.checkNotNullParameter(deepLink, "deepLink");
        NavGraph navGraph2 = this._graph;
        int length = deepLink.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                return null;
            }
            int i2 = deepLink[i];
            if (i == 0) {
                NavGraph navGraph3 = this._graph;
                Intrinsics.checkNotNull(navGraph3);
                navGraphFindNode = navGraph3.getId() == i2 ? this._graph : null;
            } else {
                Intrinsics.checkNotNull(navGraph2);
                navGraphFindNode = navGraph2.findNode(i2);
            }
            if (navGraphFindNode == null) {
                return NavDestination.INSTANCE.getDisplayName(getNavContext(), i2);
            }
            if (i != deepLink.length - 1 && (navGraphFindNode instanceof NavGraph)) {
                while (true) {
                    navGraph = (NavGraph) navGraphFindNode;
                    Intrinsics.checkNotNull(navGraph);
                    if (!(navGraph.findNode(navGraph.getStartDestinationId()) instanceof NavGraph)) {
                        break;
                    }
                    navGraphFindNode = navGraph.findNode(navGraph.getStartDestinationId());
                }
                navGraph2 = navGraph;
            }
            i++;
        }
    }

    public final NavDestination getCurrentDestination$navigation_runtime_release() {
        NavBackStackEntry currentBackStackEntry$navigation_runtime_release = getCurrentBackStackEntry$navigation_runtime_release();
        if (currentBackStackEntry$navigation_runtime_release != null) {
            return currentBackStackEntry$navigation_runtime_release.getDestination();
        }
        return null;
    }

    public static /* synthetic */ NavDestination findDestination$navigation_runtime_release$default(NavControllerImpl navControllerImpl, int i, NavDestination navDestination, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            navDestination = null;
        }
        return navControllerImpl.findDestination$navigation_runtime_release(i, navDestination);
    }

    public final NavDestination findDestination$navigation_runtime_release(int destinationId, NavDestination matchingDest) {
        NavGraph destination;
        NavGraph navGraph = this._graph;
        if (navGraph == null) {
            return null;
        }
        Intrinsics.checkNotNull(navGraph);
        if (navGraph.getId() == destinationId) {
            NavGraph navGraph2 = this._graph;
            if (matchingDest != null) {
                if (Intrinsics.areEqual(navGraph2, matchingDest) && matchingDest.getParent() == null) {
                    return this._graph;
                }
            } else {
                return navGraph2;
            }
        }
        NavBackStackEntry navBackStackEntryLastOrNull = this.backQueue.lastOrNull();
        if (navBackStackEntryLastOrNull == null || (destination = navBackStackEntryLastOrNull.getDestination()) == null) {
            NavGraph navGraph3 = this._graph;
            Intrinsics.checkNotNull(navGraph3);
            destination = navGraph3;
        }
        return findDestinationComprehensive$navigation_runtime_release(destination, destinationId, false, matchingDest);
    }

    public static /* synthetic */ NavDestination findDestinationComprehensive$navigation_runtime_release$default(NavControllerImpl navControllerImpl, NavDestination navDestination, int i, boolean z, NavDestination navDestination2, int i2, Object obj) {
        if ((i2 & 8) != 0) {
            navDestination2 = null;
        }
        return navControllerImpl.findDestinationComprehensive$navigation_runtime_release(navDestination, i, z, navDestination2);
    }

    public final NavDestination findDestinationComprehensive$navigation_runtime_release(NavDestination destination, int destinationId, boolean searchChildren, NavDestination matchingDest) {
        Intrinsics.checkNotNullParameter(destination, "destination");
        if (destination.getId() == destinationId && (matchingDest == null || (Intrinsics.areEqual(destination, matchingDest) && Intrinsics.areEqual(destination.getParent(), matchingDest.getParent())))) {
            return destination;
        }
        NavGraph parent = destination instanceof NavGraph ? (NavGraph) destination : null;
        if (parent == null) {
            parent = destination.getParent();
            Intrinsics.checkNotNull(parent);
        }
        return parent.findNodeComprehensive(destinationId, parent, searchChildren, matchingDest);
    }

    public final NavDestination findDestination$navigation_runtime_release(String route) {
        Intrinsics.checkNotNullParameter(route, "route");
        NavGraph navGraph = this._graph;
        if (navGraph == null) {
            return null;
        }
        Intrinsics.checkNotNull(navGraph);
        if (!Intrinsics.areEqual(navGraph.getRoute(), route)) {
            NavGraph navGraph2 = this._graph;
            Intrinsics.checkNotNull(navGraph2);
            if (navGraph2.matchRoute(route) == null) {
                return getTopGraph$navigation_runtime_release().findNode(route);
            }
        }
        return this._graph;
    }

    public final NavGraph getTopGraph$navigation_runtime_release() {
        NavGraph destination;
        NavBackStackEntry navBackStackEntryLastOrNull = this.backQueue.lastOrNull();
        if (navBackStackEntryLastOrNull == null || (destination = navBackStackEntryLastOrNull.getDestination()) == null) {
            NavGraph navGraph = this._graph;
            Intrinsics.checkNotNull(navGraph);
            destination = navGraph;
        }
        NavGraph navGraph2 = destination instanceof NavGraph ? (NavGraph) destination : null;
        if (navGraph2 != null) {
            return navGraph2;
        }
        NavGraph parent = destination.getParent();
        Intrinsics.checkNotNull(parent);
        return parent;
    }

    public final <T> String generateRouteFilled$navigation_runtime_release(T route) {
        Intrinsics.checkNotNullParameter(route, "route");
        NavDestination navDestinationFindDestinationComprehensive$navigation_runtime_release$default = findDestinationComprehensive$navigation_runtime_release$default(this, getGraph$navigation_runtime_release(), RouteSerializerKt.generateHashCode(SerializersKt.serializer(Reflection.getOrCreateKotlinClass(route.getClass()))), true, null, 8, null);
        if (navDestinationFindDestinationComprehensive$navigation_runtime_release$default == null) {
            throw new IllegalArgumentException(("Destination with route " + Reflection.getOrCreateKotlinClass(route.getClass()).getSimpleName() + " cannot be found in navigation graph " + this._graph).toString());
        }
        Map<String, NavArgument> arguments = navDestinationFindDestinationComprehensive$navigation_runtime_release$default.getArguments();
        LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(arguments.size()));
        Iterator<T> it = arguments.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            linkedHashMap.put(entry.getKey(), ((NavArgument) entry.getValue()).getType());
        }
        return RouteSerializerKt.generateRouteWithArgs(route, linkedHashMap);
    }

    public final void navigate$navigation_runtime_release(Uri deepLink) {
        Intrinsics.checkNotNullParameter(deepLink, "deepLink");
        navigate$navigation_runtime_release(new NavDeepLinkRequest(deepLink, null, null));
    }

    public final void navigate$navigation_runtime_release(Uri deepLink, NavOptions navOptions) {
        Intrinsics.checkNotNullParameter(deepLink, "deepLink");
        navigate$navigation_runtime_release(new NavDeepLinkRequest(deepLink, null, null), navOptions, (Navigator.Extras) null);
    }

    public final void navigate$navigation_runtime_release(Uri deepLink, NavOptions navOptions, Navigator.Extras navigatorExtras) {
        Intrinsics.checkNotNullParameter(deepLink, "deepLink");
        navigate$navigation_runtime_release(new NavDeepLinkRequest(deepLink, null, null), navOptions, navigatorExtras);
    }

    public final void navigate$navigation_runtime_release(NavDeepLinkRequest request) {
        Intrinsics.checkNotNullParameter(request, "request");
        navigate$navigation_runtime_release(request, (NavOptions) null);
    }

    public final void navigate$navigation_runtime_release(NavDeepLinkRequest request, NavOptions navOptions) {
        Intrinsics.checkNotNullParameter(request, "request");
        navigate$navigation_runtime_release(request, navOptions, (Navigator.Extras) null);
    }

    public final void navigate$navigation_runtime_release(NavDeepLinkRequest request, NavOptions navOptions, Navigator.Extras navigatorExtras) {
        Pair[] pairArr;
        Intrinsics.checkNotNullParameter(request, "request");
        if (this._graph == null) {
            throw new IllegalArgumentException(("Cannot navigate to " + request + ". Navigation graph has not been set for NavController " + this.navController + '.').toString());
        }
        NavGraph topGraph$navigation_runtime_release = getTopGraph$navigation_runtime_release();
        NavDestination.DeepLinkMatch deepLinkMatchMatchDeepLinkComprehensive = topGraph$navigation_runtime_release.matchDeepLinkComprehensive(request, true, true, topGraph$navigation_runtime_release);
        if (deepLinkMatchMatchDeepLinkComprehensive != null) {
            Bundle bundleAddInDefaultArgs = deepLinkMatchMatchDeepLinkComprehensive.getDestination().addInDefaultArgs(deepLinkMatchMatchDeepLinkComprehensive.getMatchingArgs());
            if (bundleAddInDefaultArgs == null) {
                Map mapEmptyMap = MapsKt.emptyMap();
                if (mapEmptyMap.isEmpty()) {
                    pairArr = new Pair[0];
                } else {
                    ArrayList arrayList = new ArrayList(mapEmptyMap.size());
                    for (Map.Entry entry : mapEmptyMap.entrySet()) {
                        arrayList.add(TuplesKt.to((String) entry.getKey(), entry.getValue()));
                    }
                    pairArr = (Pair[]) arrayList.toArray(new Pair[0]);
                }
                bundleAddInDefaultArgs = BundleKt.bundleOf((Pair[]) Arrays.copyOf(pairArr, pairArr.length));
                SavedStateWriter.m8016constructorimpl(bundleAddInDefaultArgs);
            }
            NavDestination destination = deepLinkMatchMatchDeepLinkComprehensive.getDestination();
            this.navController.writeIntent$navigation_runtime_release(request, bundleAddInDefaultArgs);
            navigate$navigation_runtime_release(destination, bundleAddInDefaultArgs, navOptions, navigatorExtras);
            return;
        }
        throw new IllegalArgumentException("Navigation destination that matches request " + request + " cannot be found in the navigation graph " + this._graph);
    }

    /* JADX WARN: Code duplicated, block: B:21:0x009f  */
    public final void navigate$navigation_runtime_release(final NavDestination node, Bundle args, NavOptions navOptions, Navigator.Extras navigatorExtras) {
        boolean z;
        boolean z2;
        boolean zPopBackStackInternal$navigation_runtime_release;
        Intrinsics.checkNotNullParameter(node, "node");
        Iterator<T> it = this.navigatorState.values().iterator();
        while (it.hasNext()) {
            ((NavController.NavControllerNavigatorState) it.next()).setNavigating(true);
        }
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        if (navOptions == null) {
            z = false;
        } else {
            if (navOptions.getPopUpToRoute() != null) {
                String popUpToRoute = navOptions.getPopUpToRoute();
                Intrinsics.checkNotNull(popUpToRoute);
                zPopBackStackInternal$navigation_runtime_release = popBackStackInternal$navigation_runtime_release(popUpToRoute, navOptions.getPopUpToInclusive(), navOptions.getPopUpToSaveState());
            } else if (navOptions.getPopUpToRouteClass() != null) {
                KClass<?> popUpToRouteClass = navOptions.getPopUpToRouteClass();
                Intrinsics.checkNotNull(popUpToRouteClass);
                zPopBackStackInternal$navigation_runtime_release = popBackStackInternal$navigation_runtime_release(RouteSerializerKt.generateHashCode(SerializersKt.serializer(popUpToRouteClass)), navOptions.getPopUpToInclusive(), navOptions.getPopUpToSaveState());
            } else if (navOptions.getPopUpToRouteObject() != null) {
                Object popUpToRouteObject = navOptions.getPopUpToRouteObject();
                Intrinsics.checkNotNull(popUpToRouteObject);
                zPopBackStackInternal$navigation_runtime_release = popBackStackInternal$navigation_runtime_release(popUpToRouteObject, navOptions.getPopUpToInclusive(), navOptions.getPopUpToSaveState());
            } else if (navOptions.getPopUpToId() != -1) {
                zPopBackStackInternal$navigation_runtime_release = popBackStackInternal$navigation_runtime_release(navOptions.getPopUpToId(), navOptions.getPopUpToInclusive(), navOptions.getPopUpToSaveState());
            } else {
                z = false;
            }
            z = zPopBackStackInternal$navigation_runtime_release;
        }
        final Bundle bundleAddInDefaultArgs = node.addInDefaultArgs(args);
        if (navOptions != null && navOptions.getRestoreState() && this.backStackMap.containsKey(Integer.valueOf(node.getId()))) {
            booleanRef.element = restoreStateInternal(node.getId(), bundleAddInDefaultArgs, navOptions, navigatorExtras);
            z2 = false;
        } else {
            z2 = navOptions != null && navOptions.getSingleTop() && launchSingleTopInternal(node, args);
            if (!z2) {
                navigateInternal$navigation_runtime_release(this._navigatorProvider.getNavigator(node.getNavigatorName()), CollectionsKt.listOf(NavBackStackEntry.Companion.create$default(NavBackStackEntry.INSTANCE, getNavContext(), node, bundleAddInDefaultArgs, getHostLifecycleState$navigation_runtime_release(), this.viewModel, null, null, 96, null)), navOptions, navigatorExtras, new Function1() { // from class: androidx.navigation.internal.NavControllerImpl$$ExternalSyntheticLambda6
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return NavControllerImpl.navigate$lambda$44(booleanRef, this, node, bundleAddInDefaultArgs, (NavBackStackEntry) obj);
                    }
                });
            }
        }
        this.updateOnBackPressedCallbackEnabledCallback.invoke();
        Iterator<T> it2 = this.navigatorState.values().iterator();
        while (it2.hasNext()) {
            ((NavController.NavControllerNavigatorState) it2.next()).setNavigating(false);
        }
        if (z || booleanRef.element || z2) {
            dispatchOnDestinationChanged$navigation_runtime_release();
        } else {
            updateBackStackLifecycle$navigation_runtime_release();
        }
    }

    static final Unit navigate$lambda$44(Ref.BooleanRef booleanRef, NavControllerImpl navControllerImpl, NavDestination navDestination, Bundle bundle, NavBackStackEntry it) {
        Intrinsics.checkNotNullParameter(it, "it");
        booleanRef.element = true;
        addEntryToBackStack$default(navControllerImpl, navDestination, bundle, it, null, 8, null);
        return Unit.INSTANCE;
    }

    private final boolean launchSingleTopInternal(NavDestination node, Bundle args) {
        int iNextIndex;
        NavDestination destination;
        NavBackStackEntry currentBackStackEntry$navigation_runtime_release = getCurrentBackStackEntry$navigation_runtime_release();
        ArrayDeque<NavBackStackEntry> arrayDeque = this.backQueue;
        ListIterator<NavBackStackEntry> listIterator = arrayDeque.listIterator(arrayDeque.size());
        while (true) {
            if (!listIterator.hasPrevious()) {
                iNextIndex = -1;
                break;
            }
            if (listIterator.previous().getDestination() == node) {
                iNextIndex = listIterator.nextIndex();
                break;
            }
        }
        if (iNextIndex == -1) {
            return false;
        }
        if (node instanceof NavGraph) {
            List list = SequencesKt.toList(SequencesKt.map(NavGraph.INSTANCE.childHierarchy((NavGraph) node), new Function1() { // from class: androidx.navigation.internal.NavControllerImpl$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Integer.valueOf(NavControllerImpl.launchSingleTopInternal$lambda$47((NavDestination) obj));
                }
            }));
            if (this.backQueue.size() - iNextIndex != list.size()) {
                return false;
            }
            ArrayDeque<NavBackStackEntry> arrayDeque2 = this.backQueue;
            List listSubList = arrayDeque2.subList(iNextIndex, arrayDeque2.size());
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listSubList, 10));
            Iterator it = listSubList.iterator();
            while (it.hasNext()) {
                arrayList.add(Integer.valueOf(((NavBackStackEntry) it.next()).getDestination().getId()));
            }
            if (!Intrinsics.areEqual(arrayList, list)) {
                return false;
            }
        } else if (currentBackStackEntry$navigation_runtime_release == null || (destination = currentBackStackEntry$navigation_runtime_release.getDestination()) == null || node.getId() != destination.getId()) {
            return false;
        }
        ArrayDeque arrayDeque3 = new ArrayDeque();
        while (CollectionsKt.getLastIndex(this.backQueue) >= iNextIndex) {
            NavBackStackEntry navBackStackEntry = (NavBackStackEntry) CollectionsKt.removeLast(this.backQueue);
            unlinkChildFromParent$navigation_runtime_release(navBackStackEntry);
            arrayDeque3.addFirst(new NavBackStackEntry(navBackStackEntry, navBackStackEntry.getDestination().addInDefaultArgs(args)));
        }
        ArrayDeque<NavBackStackEntry> arrayDeque4 = arrayDeque3;
        for (NavBackStackEntry navBackStackEntry2 : arrayDeque4) {
            NavGraph parent = navBackStackEntry2.getDestination().getParent();
            if (parent != null) {
                linkChildToParent$navigation_runtime_release(navBackStackEntry2, getBackStackEntry$navigation_runtime_release(parent.getId()));
            }
            this.backQueue.add(navBackStackEntry2);
        }
        for (NavBackStackEntry navBackStackEntry3 : arrayDeque4) {
            this._navigatorProvider.getNavigator(navBackStackEntry3.getDestination().getNavigatorName()).onLaunchSingleTop(navBackStackEntry3);
        }
        return true;
    }

    static final int launchSingleTopInternal$lambda$47(NavDestination it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getId();
    }

    private final boolean restoreStateInternal(int id, Bundle args, NavOptions navOptions, Navigator.Extras navigatorExtras) {
        if (!this.backStackMap.containsKey(Integer.valueOf(id))) {
            return false;
        }
        final String str = this.backStackMap.get(Integer.valueOf(id));
        CollectionsKt.removeAll(this.backStackMap.values(), new Function1() { // from class: androidx.navigation.internal.NavControllerImpl$$ExternalSyntheticLambda13
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(Intrinsics.areEqual((String) obj, str));
            }
        });
        return executeRestoreState(instantiateBackStack((ArrayDeque) TypeIntrinsics.asMutableMap(this.backStackStates).remove(str)), args, navOptions, navigatorExtras);
    }

    private final boolean restoreStateInternal(String route) {
        NavBackStackEntryState navBackStackEntryStateFirstOrNull;
        int iHashCode = NavDestination.INSTANCE.createRoute(route).hashCode();
        if (this.backStackMap.containsKey(Integer.valueOf(iHashCode))) {
            return restoreStateInternal(iHashCode, null, null, null);
        }
        NavDestination navDestinationFindDestination$navigation_runtime_release = findDestination$navigation_runtime_release(route);
        if (navDestinationFindDestination$navigation_runtime_release == null) {
            throw new IllegalStateException(("Restore State failed: route " + route + " cannot be found from the current destination " + getCurrentDestination$navigation_runtime_release()).toString());
        }
        final String str = this.backStackMap.get(Integer.valueOf(navDestinationFindDestination$navigation_runtime_release.getId()));
        CollectionsKt.removeAll(this.backStackMap.values(), new Function1() { // from class: androidx.navigation.internal.NavControllerImpl$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(Intrinsics.areEqual((String) obj, str));
            }
        });
        ArrayDeque<NavBackStackEntryState> arrayDeque = (ArrayDeque) TypeIntrinsics.asMutableMap(this.backStackStates).remove(str);
        NavDestination.DeepLinkMatch deepLinkMatchMatchRoute = navDestinationFindDestination$navigation_runtime_release.matchRoute(route);
        Intrinsics.checkNotNull(deepLinkMatchMatchRoute);
        if (deepLinkMatchMatchRoute.hasMatchingArgs((arrayDeque == null || (navBackStackEntryStateFirstOrNull = arrayDeque.firstOrNull()) == null) ? null : navBackStackEntryStateFirstOrNull.getArgs())) {
            return executeRestoreState(instantiateBackStack(arrayDeque), null, null, null);
        }
        return false;
    }

    private final boolean executeRestoreState(final List<NavBackStackEntry> entries, final Bundle args, NavOptions navOptions, Navigator.Extras navigatorExtras) {
        NavBackStackEntry navBackStackEntry;
        NavDestination destination;
        ArrayList<List<NavBackStackEntry>> arrayList = new ArrayList();
        ArrayList<NavBackStackEntry> arrayList2 = new ArrayList();
        for (Object obj : entries) {
            if (!(((NavBackStackEntry) obj).getDestination() instanceof NavGraph)) {
                arrayList2.add(obj);
            }
        }
        for (NavBackStackEntry navBackStackEntry2 : arrayList2) {
            List list = (List) CollectionsKt.lastOrNull((List) arrayList);
            if (Intrinsics.areEqual((list == null || (navBackStackEntry = (NavBackStackEntry) CollectionsKt.last(list)) == null || (destination = navBackStackEntry.getDestination()) == null) ? null : destination.getNavigatorName(), navBackStackEntry2.getDestination().getNavigatorName())) {
                list.add(navBackStackEntry2);
            } else {
                arrayList.add(CollectionsKt.mutableListOf(navBackStackEntry2));
            }
        }
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        for (List<NavBackStackEntry> list2 : arrayList) {
            Navigator<? extends NavDestination> navigator = this._navigatorProvider.getNavigator(((NavBackStackEntry) CollectionsKt.first((List) list2)).getDestination().getNavigatorName());
            final Ref.IntRef intRef = new Ref.IntRef();
            navigateInternal$navigation_runtime_release(navigator, list2, navOptions, navigatorExtras, new Function1() { // from class: androidx.navigation.internal.NavControllerImpl$$ExternalSyntheticLambda9
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return NavControllerImpl.executeRestoreState$lambda$57(booleanRef, entries, intRef, this, args, (NavBackStackEntry) obj2);
                }
            });
        }
        return booleanRef.element;
    }

    static final Unit executeRestoreState$lambda$57(Ref.BooleanRef booleanRef, List list, Ref.IntRef intRef, NavControllerImpl navControllerImpl, Bundle bundle, NavBackStackEntry entry) {
        List<NavBackStackEntry> listEmptyList;
        Intrinsics.checkNotNullParameter(entry, "entry");
        booleanRef.element = true;
        int iIndexOf = list.indexOf(entry);
        if (iIndexOf != -1) {
            int i = iIndexOf + 1;
            listEmptyList = list.subList(intRef.element, i);
            intRef.element = i;
        } else {
            listEmptyList = CollectionsKt.emptyList();
        }
        navControllerImpl.addEntryToBackStack(entry.getDestination(), bundle, entry, listEmptyList);
        return Unit.INSTANCE;
    }

    private final List<NavBackStackEntry> instantiateBackStack(ArrayDeque<NavBackStackEntryState> backStackState) {
        NavGraph graph$navigation_runtime_release;
        ArrayList arrayList = new ArrayList();
        NavBackStackEntry navBackStackEntryLastOrNull = this.backQueue.lastOrNull();
        if (navBackStackEntryLastOrNull == null || (graph$navigation_runtime_release = navBackStackEntryLastOrNull.getDestination()) == null) {
            graph$navigation_runtime_release = getGraph$navigation_runtime_release();
        }
        if (backStackState != null) {
            NavDestination navDestination = graph$navigation_runtime_release;
            for (NavBackStackEntryState navBackStackEntryState : backStackState) {
                NavControllerImpl navControllerImpl = this;
                NavDestination navDestinationFindDestinationComprehensive$navigation_runtime_release$default = findDestinationComprehensive$navigation_runtime_release$default(navControllerImpl, navDestination, navBackStackEntryState.getDestinationId(), true, null, 8, null);
                if (navDestinationFindDestinationComprehensive$navigation_runtime_release$default == null) {
                    throw new IllegalStateException(("Restore State failed: destination " + NavDestination.INSTANCE.getDisplayName(navControllerImpl.getNavContext(), navBackStackEntryState.getDestinationId()) + " cannot be found from the current destination " + navDestination).toString());
                }
                arrayList.add(navBackStackEntryState.instantiate(navControllerImpl.getNavContext(), navDestinationFindDestinationComprehensive$navigation_runtime_release$default, navControllerImpl.getHostLifecycleState$navigation_runtime_release(), navControllerImpl.viewModel));
                navDestination = navDestinationFindDestinationComprehensive$navigation_runtime_release$default;
                this = navControllerImpl;
            }
        }
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ void addEntryToBackStack$default(NavControllerImpl navControllerImpl, NavDestination navDestination, Bundle bundle, NavBackStackEntry navBackStackEntry, List list, int i, Object obj) {
        if ((i & 8) != 0) {
            list = CollectionsKt.emptyList();
        }
        navControllerImpl.addEntryToBackStack(navDestination, bundle, navBackStackEntry, list);
    }

    private final void addEntryToBackStack(NavDestination node, Bundle finalArgs, NavBackStackEntry backStackEntry, List<NavBackStackEntry> restoredEntries) {
        Bundle bundle;
        ArrayDeque<NavBackStackEntry> arrayDeque;
        NavDestination destination;
        List<NavBackStackEntry> list;
        NavBackStackEntry navBackStackEntryPrevious;
        NavGraph navGraph;
        NavBackStackEntry navBackStackEntryPrevious2;
        List<NavBackStackEntry> list2 = restoredEntries;
        NavDestination destination2 = backStackEntry.getDestination();
        if (!(destination2 instanceof FloatingWindow)) {
            while (!this.backQueue.isEmpty() && (this.backQueue.last().getDestination() instanceof FloatingWindow) && popBackStackInternal$navigation_runtime_release$default(this, this.backQueue.last().getDestination().getId(), true, false, 4, (Object) null)) {
            }
        }
        ArrayDeque arrayDeque2 = new ArrayDeque();
        NavBackStackEntry navBackStackEntry = null;
        if (node instanceof NavGraph) {
            NavGraph navGraph2 = destination2;
            while (true) {
                Intrinsics.checkNotNull(navGraph2);
                NavGraph parent = navGraph2.getParent();
                if (parent != null) {
                    ListIterator<NavBackStackEntry> listIterator = list2.listIterator(list2.size());
                    do {
                        if (!listIterator.hasPrevious()) {
                            navBackStackEntryPrevious2 = null;
                            break;
                        }
                        navBackStackEntryPrevious2 = listIterator.previous();
                    } while (!Intrinsics.areEqual(navBackStackEntryPrevious2.getDestination(), parent));
                    NavBackStackEntry navBackStackEntryCreate$default = navBackStackEntryPrevious2;
                    if (navBackStackEntryCreate$default == null) {
                        bundle = finalArgs;
                        destination = destination2;
                        navBackStackEntryCreate$default = NavBackStackEntry.Companion.create$default(NavBackStackEntry.INSTANCE, getNavContext(), parent, bundle, getHostLifecycleState$navigation_runtime_release(), this.viewModel, null, null, 96, null);
                    } else {
                        bundle = finalArgs;
                        destination = destination2;
                    }
                    arrayDeque2.addFirst(navBackStackEntryCreate$default);
                    if (this.backQueue.isEmpty() || this.backQueue.last().getDestination() != parent) {
                        list = restoredEntries;
                        arrayDeque = arrayDeque2;
                        navGraph = parent;
                    } else {
                        navGraph = parent;
                        arrayDeque = arrayDeque2;
                        list = restoredEntries;
                        popEntryFromBackStack$navigation_runtime_release$default(this, this.backQueue.last(), false, null, 6, null);
                    }
                } else {
                    bundle = finalArgs;
                    arrayDeque = arrayDeque2;
                    navGraph = parent;
                    destination = destination2;
                    list = list2;
                }
                navGraph2 = navGraph;
                if (navGraph2 == null || navGraph2 == node) {
                    break;
                }
                list2 = list;
                arrayDeque2 = arrayDeque;
                destination2 = destination;
            }
        } else {
            bundle = finalArgs;
            arrayDeque = arrayDeque2;
            destination = destination2;
            list = list2;
            backStackEntry = backStackEntry;
        }
        NavGraph destination3 = arrayDeque.isEmpty() ? destination : ((NavBackStackEntry) arrayDeque.first()).getDestination();
        while (destination3 != null && findDestination$navigation_runtime_release(destination3.getId(), destination3) != destination3) {
            NavGraph parent2 = destination3.getParent();
            if (parent2 != null) {
                Bundle bundle2 = (bundle == null || !SavedStateReader.m8008isEmptyimpl(SavedStateReader.m7930constructorimpl(bundle))) ? bundle : null;
                ListIterator<NavBackStackEntry> listIterator2 = list.listIterator(list.size());
                do {
                    if (!listIterator2.hasPrevious()) {
                        navBackStackEntryPrevious = null;
                        break;
                    }
                    navBackStackEntryPrevious = listIterator2.previous();
                } while (!Intrinsics.areEqual(navBackStackEntryPrevious.getDestination(), parent2));
                NavBackStackEntry navBackStackEntryCreate$default2 = navBackStackEntryPrevious;
                if (navBackStackEntryCreate$default2 == null) {
                    navBackStackEntryCreate$default2 = NavBackStackEntry.Companion.create$default(NavBackStackEntry.INSTANCE, getNavContext(), parent2, parent2.addInDefaultArgs(bundle2), getHostLifecycleState$navigation_runtime_release(), this.viewModel, null, null, 96, null);
                }
                arrayDeque.addFirst(navBackStackEntryCreate$default2);
            }
            destination3 = parent2;
        }
        if (!arrayDeque.isEmpty()) {
            destination = ((NavBackStackEntry) arrayDeque.first()).getDestination();
        }
        while (!this.backQueue.isEmpty() && (this.backQueue.last().getDestination() instanceof NavGraph)) {
            NavDestination destination4 = this.backQueue.last().getDestination();
            Intrinsics.checkNotNull(destination4, "null cannot be cast to non-null type androidx.navigation.NavGraph");
            if (((NavGraph) destination4).getNodes().get(destination.getId()) != null) {
                break;
            } else {
                popEntryFromBackStack$navigation_runtime_release$default(this, this.backQueue.last(), false, null, 6, null);
            }
        }
        NavBackStackEntry navBackStackEntryFirstOrNull = this.backQueue.firstOrNull();
        if (navBackStackEntryFirstOrNull == null) {
            navBackStackEntryFirstOrNull = (NavBackStackEntry) arrayDeque.firstOrNull();
        }
        if (!Intrinsics.areEqual(navBackStackEntryFirstOrNull != null ? navBackStackEntryFirstOrNull.getDestination() : null, this._graph)) {
            ListIterator<NavBackStackEntry> listIterator3 = list.listIterator(list.size());
            while (listIterator3.hasPrevious()) {
                NavBackStackEntry navBackStackEntryPrevious3 = listIterator3.previous();
                NavDestination destination5 = navBackStackEntryPrevious3.getDestination();
                NavGraph navGraph3 = this._graph;
                Intrinsics.checkNotNull(navGraph3);
                if (Intrinsics.areEqual(destination5, navGraph3)) {
                    navBackStackEntry = navBackStackEntryPrevious3;
                    break;
                }
            }
            NavBackStackEntry navBackStackEntryCreate$default3 = navBackStackEntry;
            if (navBackStackEntryCreate$default3 == null) {
                NavBackStackEntry.Companion companion = NavBackStackEntry.INSTANCE;
                NavContext navContext = getNavContext();
                NavGraph navGraph4 = this._graph;
                Intrinsics.checkNotNull(navGraph4);
                NavGraph navGraph5 = navGraph4;
                NavGraph navGraph6 = this._graph;
                Intrinsics.checkNotNull(navGraph6);
                navBackStackEntryCreate$default3 = NavBackStackEntry.Companion.create$default(companion, navContext, navGraph5, navGraph6.addInDefaultArgs(bundle), getHostLifecycleState$navigation_runtime_release(), this.viewModel, null, null, 96, null);
            }
            arrayDeque.addFirst(navBackStackEntryCreate$default3);
        }
        for (NavBackStackEntry navBackStackEntry2 : arrayDeque) {
            NavController.NavControllerNavigatorState navControllerNavigatorState = this.navigatorState.get(this._navigatorProvider.getNavigator(navBackStackEntry2.getDestination().getNavigatorName()));
            if (navControllerNavigatorState == null) {
                throw new IllegalStateException(("NavigatorBackStack for " + node.getNavigatorName() + " should already be created").toString());
            }
            navControllerNavigatorState.addInternal(navBackStackEntry2);
        }
        ArrayDeque arrayDeque3 = arrayDeque;
        this.backQueue.addAll(arrayDeque3);
        this.backQueue.add(backStackEntry);
        for (NavBackStackEntry navBackStackEntry3 : CollectionsKt.plus((Collection<? extends NavBackStackEntry>) arrayDeque3, backStackEntry)) {
            NavGraph parent3 = navBackStackEntry3.getDestination().getParent();
            if (parent3 != null) {
                linkChildToParent$navigation_runtime_release(navBackStackEntry3, getBackStackEntry$navigation_runtime_release(parent3.getId()));
            }
        }
    }

    public final void navigate$navigation_runtime_release(String route, Function1<? super NavOptionsBuilder, Unit> builder) {
        Intrinsics.checkNotNullParameter(route, "route");
        Intrinsics.checkNotNullParameter(builder, "builder");
        navigate$navigation_runtime_release$default(this, route, NavOptionsBuilderKt.navOptions(builder), (Navigator.Extras) null, 4, (Object) null);
    }

    public static /* synthetic */ void navigate$navigation_runtime_release$default(NavControllerImpl navControllerImpl, String str, NavOptions navOptions, Navigator.Extras extras, int i, Object obj) {
        if ((i & 4) != 0) {
            extras = null;
        }
        navControllerImpl.navigate$navigation_runtime_release(str, navOptions, extras);
    }

    public final void navigate$navigation_runtime_release(String route, NavOptions navOptions, Navigator.Extras navigatorExtras) {
        Pair[] pairArr;
        Intrinsics.checkNotNullParameter(route, "route");
        if (this._graph == null) {
            throw new IllegalArgumentException(("Cannot navigate to " + route + ". Navigation graph has not been set for NavController " + this + '.').toString());
        }
        NavGraph topGraph$navigation_runtime_release = getTopGraph$navigation_runtime_release();
        NavDestination.DeepLinkMatch deepLinkMatchMatchRouteComprehensive = topGraph$navigation_runtime_release.matchRouteComprehensive(route, true, true, topGraph$navigation_runtime_release);
        if (deepLinkMatchMatchRouteComprehensive != null) {
            NavDestination destination = deepLinkMatchMatchRouteComprehensive.getDestination();
            Bundle bundleAddInDefaultArgs = destination.addInDefaultArgs(deepLinkMatchMatchRouteComprehensive.getMatchingArgs());
            if (bundleAddInDefaultArgs == null) {
                Map mapEmptyMap = MapsKt.emptyMap();
                if (mapEmptyMap.isEmpty()) {
                    pairArr = new Pair[0];
                } else {
                    ArrayList arrayList = new ArrayList(mapEmptyMap.size());
                    for (Map.Entry entry : mapEmptyMap.entrySet()) {
                        arrayList.add(TuplesKt.to((String) entry.getKey(), entry.getValue()));
                    }
                    pairArr = (Pair[]) arrayList.toArray(new Pair[0]);
                }
                bundleAddInDefaultArgs = BundleKt.bundleOf((Pair[]) Arrays.copyOf(pairArr, pairArr.length));
                SavedStateWriter.m8016constructorimpl(bundleAddInDefaultArgs);
            }
            NavDestination destination2 = deepLinkMatchMatchRouteComprehensive.getDestination();
            this.navController.writeIntent$navigation_runtime_release(NavDeepLinkRequest.Builder.INSTANCE.fromUri(NavUriKt.NavUri(NavDestination.INSTANCE.createRoute(destination.getRoute()))).build(), bundleAddInDefaultArgs);
            navigate$navigation_runtime_release(destination2, bundleAddInDefaultArgs, navOptions, navigatorExtras);
            return;
        }
        throw new IllegalArgumentException("Navigation destination that matches route " + route + " cannot be found in the navigation graph " + this._graph);
    }

    public final <T> void navigate$navigation_runtime_release(T route, Function1<? super NavOptionsBuilder, Unit> builder) {
        Intrinsics.checkNotNullParameter(route, "route");
        Intrinsics.checkNotNullParameter(builder, "builder");
        navigate$navigation_runtime_release$default(this, route, NavOptionsBuilderKt.navOptions(builder), (Navigator.Extras) null, 4, (Object) null);
    }

    public static /* synthetic */ void navigate$navigation_runtime_release$default(NavControllerImpl navControllerImpl, Object obj, NavOptions navOptions, Navigator.Extras extras, int i, Object obj2) {
        if ((i & 4) != 0) {
            extras = null;
        }
        navControllerImpl.navigate$navigation_runtime_release(obj, navOptions, extras);
    }

    public final <T> void navigate$navigation_runtime_release(T route, NavOptions navOptions, Navigator.Extras navigatorExtras) {
        Intrinsics.checkNotNullParameter(route, "route");
        navigate$navigation_runtime_release(generateRouteFilled$navigation_runtime_release(route), navOptions, navigatorExtras);
    }

    public final Bundle saveState$navigation_runtime_release() {
        Pair[] pairArr;
        Bundle bundleBundleOf;
        Pair[] pairArr2;
        Pair[] pairArr3;
        Pair[] pairArr4;
        Pair[] pairArr5;
        ArrayList arrayList = new ArrayList();
        Map mapEmptyMap = MapsKt.emptyMap();
        if (mapEmptyMap.isEmpty()) {
            pairArr = new Pair[0];
        } else {
            ArrayList arrayList2 = new ArrayList(mapEmptyMap.size());
            for (Map.Entry entry : mapEmptyMap.entrySet()) {
                arrayList2.add(TuplesKt.to((String) entry.getKey(), entry.getValue()));
            }
            pairArr = (Pair[]) arrayList2.toArray(new Pair[0]);
        }
        Bundle bundleBundleOf2 = BundleKt.bundleOf((Pair[]) Arrays.copyOf(pairArr, pairArr.length));
        SavedStateWriter.m8016constructorimpl(bundleBundleOf2);
        for (Map.Entry<String, Navigator<? extends NavDestination>> entry2 : this._navigatorProvider.getNavigators().entrySet()) {
            String key = entry2.getKey();
            Bundle bundleOnSaveState = entry2.getValue().onSaveState();
            if (bundleOnSaveState != null) {
                arrayList.add(key);
                SavedStateWriter.m8043putSavedStateimpl(SavedStateWriter.m8016constructorimpl(bundleBundleOf2), key, bundleOnSaveState);
            }
        }
        if (arrayList.isEmpty()) {
            bundleBundleOf = null;
        } else {
            Map mapEmptyMap2 = MapsKt.emptyMap();
            if (mapEmptyMap2.isEmpty()) {
                pairArr5 = new Pair[0];
            } else {
                ArrayList arrayList3 = new ArrayList(mapEmptyMap2.size());
                for (Map.Entry entry3 : mapEmptyMap2.entrySet()) {
                    arrayList3.add(TuplesKt.to((String) entry3.getKey(), entry3.getValue()));
                }
                pairArr5 = (Pair[]) arrayList3.toArray(new Pair[0]);
            }
            bundleBundleOf = BundleKt.bundleOf((Pair[]) Arrays.copyOf(pairArr5, pairArr5.length));
            Bundle bundleM8016constructorimpl = SavedStateWriter.m8016constructorimpl(bundleBundleOf);
            SavedStateWriter.m8051putStringListimpl(SavedStateWriter.m8016constructorimpl(bundleBundleOf2), KEY_NAVIGATOR_STATE_NAMES, arrayList);
            SavedStateWriter.m8043putSavedStateimpl(bundleM8016constructorimpl, KEY_NAVIGATOR_STATE, bundleBundleOf2);
        }
        if (!this.backQueue.isEmpty()) {
            if (bundleBundleOf == null) {
                Map mapEmptyMap3 = MapsKt.emptyMap();
                if (mapEmptyMap3.isEmpty()) {
                    pairArr4 = new Pair[0];
                } else {
                    ArrayList arrayList4 = new ArrayList(mapEmptyMap3.size());
                    for (Map.Entry entry4 : mapEmptyMap3.entrySet()) {
                        arrayList4.add(TuplesKt.to((String) entry4.getKey(), entry4.getValue()));
                    }
                    pairArr4 = (Pair[]) arrayList4.toArray(new Pair[0]);
                }
                Bundle bundleBundleOf3 = BundleKt.bundleOf((Pair[]) Arrays.copyOf(pairArr4, pairArr4.length));
                SavedStateWriter.m8016constructorimpl(bundleBundleOf3);
                bundleBundleOf = bundleBundleOf3;
            }
            ArrayList arrayList5 = new ArrayList();
            Iterator it = this.backQueue.iterator();
            while (it.hasNext()) {
                arrayList5.add(new NavBackStackEntryState((NavBackStackEntry) it.next()).writeToState());
            }
            SavedStateWriter.m8045putSavedStateListimpl(SavedStateWriter.m8016constructorimpl(bundleBundleOf), KEY_BACK_STACK, arrayList5);
        }
        if (!this.backStackMap.isEmpty()) {
            if (bundleBundleOf == null) {
                Map mapEmptyMap4 = MapsKt.emptyMap();
                if (mapEmptyMap4.isEmpty()) {
                    pairArr3 = new Pair[0];
                } else {
                    ArrayList arrayList6 = new ArrayList(mapEmptyMap4.size());
                    for (Map.Entry entry5 : mapEmptyMap4.entrySet()) {
                        arrayList6.add(TuplesKt.to((String) entry5.getKey(), entry5.getValue()));
                    }
                    pairArr3 = (Pair[]) arrayList6.toArray(new Pair[0]);
                }
                Bundle bundleBundleOf4 = BundleKt.bundleOf((Pair[]) Arrays.copyOf(pairArr3, pairArr3.length));
                SavedStateWriter.m8016constructorimpl(bundleBundleOf4);
                bundleBundleOf = bundleBundleOf4;
            }
            int[] iArr = new int[this.backStackMap.size()];
            ArrayList arrayList7 = new ArrayList();
            int i = 0;
            for (Map.Entry<Integer, String> entry6 : this.backStackMap.entrySet()) {
                int iIntValue = entry6.getKey().intValue();
                String value = entry6.getValue();
                int i2 = i + 1;
                iArr[i] = iIntValue;
                if (value == null) {
                    value = "";
                }
                arrayList7.add(value);
                i = i2;
            }
            Bundle bundleM8016constructorimpl2 = SavedStateWriter.m8016constructorimpl(bundleBundleOf);
            SavedStateWriter.m8034putIntArrayimpl(bundleM8016constructorimpl2, KEY_BACK_STACK_DEST_IDS, iArr);
            SavedStateWriter.m8051putStringListimpl(bundleM8016constructorimpl2, KEY_BACK_STACK_IDS, arrayList7);
        }
        if (!this.backStackStates.isEmpty()) {
            if (bundleBundleOf == null) {
                Map mapEmptyMap5 = MapsKt.emptyMap();
                if (mapEmptyMap5.isEmpty()) {
                    pairArr2 = new Pair[0];
                } else {
                    ArrayList arrayList8 = new ArrayList(mapEmptyMap5.size());
                    for (Map.Entry entry7 : mapEmptyMap5.entrySet()) {
                        arrayList8.add(TuplesKt.to((String) entry7.getKey(), entry7.getValue()));
                    }
                    pairArr2 = (Pair[]) arrayList8.toArray(new Pair[0]);
                }
                bundleBundleOf = BundleKt.bundleOf((Pair[]) Arrays.copyOf(pairArr2, pairArr2.length));
                SavedStateWriter.m8016constructorimpl(bundleBundleOf);
            }
            ArrayList arrayList9 = new ArrayList();
            for (Map.Entry<String, ArrayDeque<NavBackStackEntryState>> entry8 : this.backStackStates.entrySet()) {
                String key2 = entry8.getKey();
                ArrayDeque<NavBackStackEntryState> value2 = entry8.getValue();
                arrayList9.add(key2);
                ArrayList arrayList10 = new ArrayList();
                Iterator<NavBackStackEntryState> it2 = value2.iterator();
                while (it2.hasNext()) {
                    arrayList10.add(it2.next().writeToState());
                }
                SavedStateWriter.m8045putSavedStateListimpl(SavedStateWriter.m8016constructorimpl(bundleBundleOf), KEY_BACK_STACK_STATES_PREFIX + key2, arrayList10);
            }
            SavedStateWriter.m8051putStringListimpl(SavedStateWriter.m8016constructorimpl(bundleBundleOf), KEY_BACK_STACK_STATES_IDS, arrayList9);
        }
        return bundleBundleOf;
    }

    public final void setLifecycleOwner$navigation_runtime_release(LifecycleOwner owner) {
        Lifecycle lifecycleRegistry;
        Intrinsics.checkNotNullParameter(owner, "owner");
        if (Intrinsics.areEqual(owner, this.lifecycleOwner)) {
            return;
        }
        LifecycleOwner lifecycleOwner = this.lifecycleOwner;
        if (lifecycleOwner != null && (lifecycleRegistry = lifecycleOwner.getLifecycle()) != null) {
            lifecycleRegistry.removeObserver(this.lifecycleObserver);
        }
        this.lifecycleOwner = owner;
        owner.getLifecycle().addObserver(this.lifecycleObserver);
    }

    public final void setViewModelStore$navigation_runtime_release(ViewModelStore viewModelStore) {
        Intrinsics.checkNotNullParameter(viewModelStore, "viewModelStore");
        if (Intrinsics.areEqual(this.viewModel, NavControllerViewModel.INSTANCE.getInstance(viewModelStore))) {
            return;
        }
        if (!this.backQueue.isEmpty()) {
            throw new IllegalStateException("ViewModelStore should be set before setGraph call".toString());
        }
        this.viewModel = NavControllerViewModel.INSTANCE.getInstance(viewModelStore);
    }

    public final ViewModelStoreOwner getViewModelStoreOwner$navigation_runtime_release(int navGraphId) {
        if (this.viewModel == null) {
            throw new IllegalStateException("You must call setViewModelStore() before calling getViewModelStoreOwner().".toString());
        }
        NavBackStackEntry backStackEntry$navigation_runtime_release = getBackStackEntry$navigation_runtime_release(navGraphId);
        if (!(backStackEntry$navigation_runtime_release.getDestination() instanceof NavGraph)) {
            throw new IllegalArgumentException(("No NavGraph with ID " + navGraphId + " is on the NavController's back stack").toString());
        }
        return backStackEntry$navigation_runtime_release;
    }

    public final NavBackStackEntry getBackStackEntry$navigation_runtime_release(int destinationId) {
        NavBackStackEntry navBackStackEntryPrevious;
        ArrayDeque<NavBackStackEntry> arrayDeque = this.backQueue;
        ListIterator<NavBackStackEntry> listIterator = arrayDeque.listIterator(arrayDeque.size());
        do {
            if (!listIterator.hasPrevious()) {
                navBackStackEntryPrevious = null;
                break;
            }
            navBackStackEntryPrevious = listIterator.previous();
        } while (navBackStackEntryPrevious.getDestination().getId() != destinationId);
        NavBackStackEntry navBackStackEntry = navBackStackEntryPrevious;
        if (navBackStackEntry != null) {
            return navBackStackEntry;
        }
        throw new IllegalArgumentException(("No destination with ID " + destinationId + " is on the NavController's back stack. The current destination is " + getCurrentDestination$navigation_runtime_release()).toString());
    }

    public final NavBackStackEntry getBackStackEntry$navigation_runtime_release(String route) {
        NavBackStackEntry navBackStackEntryPrevious;
        NavBackStackEntry navBackStackEntry;
        Intrinsics.checkNotNullParameter(route, "route");
        ArrayDeque<NavBackStackEntry> arrayDeque = this.backQueue;
        ListIterator<NavBackStackEntry> listIterator = arrayDeque.listIterator(arrayDeque.size());
        do {
            if (!listIterator.hasPrevious()) {
                navBackStackEntryPrevious = null;
                break;
            }
            navBackStackEntryPrevious = listIterator.previous();
            navBackStackEntry = navBackStackEntryPrevious;
        } while (!navBackStackEntry.getDestination().hasRoute(route, navBackStackEntry.getArguments()));
        NavBackStackEntry navBackStackEntry2 = navBackStackEntryPrevious;
        if (navBackStackEntry2 != null) {
            return navBackStackEntry2;
        }
        throw new IllegalArgumentException(("No destination with route " + route + " is on the NavController's back stack. The current destination is " + getCurrentDestination$navigation_runtime_release()).toString());
    }

    public final <T> NavBackStackEntry getBackStackEntry$navigation_runtime_release(KClass<T> route) {
        NavBackStackEntry navBackStackEntryPrevious;
        Intrinsics.checkNotNullParameter(route, "route");
        int iGenerateHashCode = RouteSerializerKt.generateHashCode(SerializersKt.serializer(route));
        if (findDestinationComprehensive$navigation_runtime_release$default(this, getGraph$navigation_runtime_release(), iGenerateHashCode, true, null, 8, null) == null) {
            throw new IllegalArgumentException(("Destination with route " + route.getSimpleName() + " cannot be found in navigation graph " + getGraph$navigation_runtime_release()).toString());
        }
        List<NavBackStackEntry> value = this.currentBackStack.getValue();
        ListIterator<NavBackStackEntry> listIterator = value.listIterator(value.size());
        do {
            if (!listIterator.hasPrevious()) {
                navBackStackEntryPrevious = null;
                break;
            }
            navBackStackEntryPrevious = listIterator.previous();
        } while (navBackStackEntryPrevious.getDestination().getId() != iGenerateHashCode);
        NavBackStackEntry navBackStackEntry = navBackStackEntryPrevious;
        if (navBackStackEntry != null) {
            return navBackStackEntry;
        }
        throw new IllegalArgumentException(("No destination with route " + route.getSimpleName() + " is on the NavController's back stack. The current destination is " + getCurrentDestination$navigation_runtime_release()).toString());
    }

    public final <T> NavBackStackEntry getBackStackEntry$navigation_runtime_release(T route) {
        Intrinsics.checkNotNullParameter(route, "route");
        return getBackStackEntry$navigation_runtime_release(generateRouteFilled$navigation_runtime_release(route));
    }

    public final NavBackStackEntry getCurrentBackStackEntry$navigation_runtime_release() {
        return this.backQueue.lastOrNull();
    }

    public final MutableSharedFlow<NavBackStackEntry> get_currentBackStackEntryFlow$navigation_runtime_release() {
        return this._currentBackStackEntryFlow;
    }

    public final NavBackStackEntry getPreviousBackStackEntry$navigation_runtime_release() {
        Object next;
        Iterator it = CollectionsKt.reversed(this.backQueue).iterator();
        if (it.hasNext()) {
            it.next();
        }
        Iterator it2 = SequencesKt.asSequence(it).iterator();
        while (it2.hasNext()) {
            next = it2.next();
            if (!(((NavBackStackEntry) next).getDestination() instanceof NavGraph)) {
                return (NavBackStackEntry) next;
            }
        }
        next = null;
        return (NavBackStackEntry) next;
    }

    public final void restoreState$navigation_runtime_release(Bundle navState) {
        if (navState == null) {
            return;
        }
        Bundle bundleM7930constructorimpl = SavedStateReader.m7930constructorimpl(navState);
        this.navigatorStateToRestore = SavedStateReader.m7931containsimpl(bundleM7930constructorimpl, KEY_NAVIGATOR_STATE) ? SavedStateReader.m7987getSavedStateimpl(bundleM7930constructorimpl, KEY_NAVIGATOR_STATE) : null;
        int i = 0;
        this.backStackToRestore = SavedStateReader.m7931containsimpl(bundleM7930constructorimpl, KEY_BACK_STACK) ? (Bundle[]) SavedStateReader.m7990getSavedStateListimpl(bundleM7930constructorimpl, KEY_BACK_STACK).toArray(new Bundle[0]) : null;
        this.backStackStates.clear();
        if (SavedStateReader.m7931containsimpl(bundleM7930constructorimpl, KEY_BACK_STACK_DEST_IDS) && SavedStateReader.m7931containsimpl(bundleM7930constructorimpl, KEY_BACK_STACK_IDS)) {
            int[] iArrM7962getIntArrayimpl = SavedStateReader.m7962getIntArrayimpl(bundleM7930constructorimpl, KEY_BACK_STACK_DEST_IDS);
            List<String> listM8004getStringListimpl = SavedStateReader.m8004getStringListimpl(bundleM7930constructorimpl, KEY_BACK_STACK_IDS);
            int length = iArrM7962getIntArrayimpl.length;
            int i2 = 0;
            while (i < length) {
                int i3 = i2 + 1;
                this.backStackMap.put(Integer.valueOf(iArrM7962getIntArrayimpl[i]), !Intrinsics.areEqual(listM8004getStringListimpl.get(i2), "") ? listM8004getStringListimpl.get(i2) : null);
                i++;
                i2 = i3;
            }
        }
        if (SavedStateReader.m7931containsimpl(bundleM7930constructorimpl, KEY_BACK_STACK_STATES_IDS)) {
            for (String str : SavedStateReader.m8004getStringListimpl(bundleM7930constructorimpl, KEY_BACK_STACK_STATES_IDS)) {
                if (SavedStateReader.m7931containsimpl(bundleM7930constructorimpl, KEY_BACK_STACK_STATES_PREFIX + str)) {
                    List<Bundle> listM7990getSavedStateListimpl = SavedStateReader.m7990getSavedStateListimpl(bundleM7930constructorimpl, KEY_BACK_STACK_STATES_PREFIX + str);
                    Map<String, ArrayDeque<NavBackStackEntryState>> map = this.backStackStates;
                    ArrayDeque<NavBackStackEntryState> arrayDeque = new ArrayDeque<>(listM7990getSavedStateListimpl.size());
                    Iterator<Bundle> it = listM7990getSavedStateListimpl.iterator();
                    while (it.hasNext()) {
                        arrayDeque.add(new NavBackStackEntryState(it.next()));
                    }
                    map.put(str, arrayDeque);
                }
            }
        }
    }
}
