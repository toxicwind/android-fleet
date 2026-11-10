package androidx.mediarouter.media;

import android.annotation.SuppressLint;
import android.content.IntentFilter;
import android.media.MediaRoute2Info;
import android.media.RouteDiscoveryPreference;
import android.net.Uri;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"NewApi"})
class MediaRouter2Utils {
    public static MediaRoute2Info toFwkMediaRoute2Info(MediaRouteDescriptor mediaRouteDescriptor) {
        if (mediaRouteDescriptor == null) {
            return null;
        }
        MediaRoute2Info.Builder iconUri = new MediaRoute2Info.Builder(mediaRouteDescriptor.getId(), mediaRouteDescriptor.getName()).setDescription(mediaRouteDescriptor.getDescription()).setConnectionState(mediaRouteDescriptor.getConnectionState()).setVolumeHandling(mediaRouteDescriptor.getVolumeHandling()).setVolume(mediaRouteDescriptor.getVolume()).setVolumeMax(mediaRouteDescriptor.getVolumeMax()).addFeatures(toFeatures(mediaRouteDescriptor.getControlFilters())).setIconUri(mediaRouteDescriptor.getIconUri());
        Bundle bundle = new Bundle();
        bundle.putBundle("androidx.mediarouter.media.KEY_EXTRAS", mediaRouteDescriptor.getExtras());
        bundle.putParcelableArrayList("androidx.mediarouter.media.KEY_CONTROL_FILTERS", new ArrayList<>(mediaRouteDescriptor.getControlFilters()));
        bundle.putInt("androidx.mediarouter.media.KEY_DEVICE_TYPE", mediaRouteDescriptor.getDeviceType());
        iconUri.setExtras(bundle);
        if (mediaRouteDescriptor.getControlFilters().isEmpty()) {
            iconUri.addFeature("android.media.route.feature.EMPTY");
        }
        return iconUri.build();
    }

    public static MediaRouteDescriptor toMediaRouteDescriptor(MediaRoute2Info mediaRoute2Info) {
        if (mediaRoute2Info == null) {
            return null;
        }
        MediaRouteDescriptor.Builder builder = new MediaRouteDescriptor.Builder(mediaRoute2Info.getId(), mediaRoute2Info.getName().toString());
        builder.setConnectionState(mediaRoute2Info.getConnectionState());
        builder.setVolumeHandling(mediaRoute2Info.getVolumeHandling());
        builder.setVolumeMax(mediaRoute2Info.getVolumeMax());
        builder.setVolume(mediaRoute2Info.getVolume());
        builder.setExtras(mediaRoute2Info.getExtras());
        builder.setEnabled(true);
        builder.setCanDisconnect(false);
        CharSequence description = mediaRoute2Info.getDescription();
        if (description != null) {
            builder.setDescription(description.toString());
        }
        Uri iconUri = mediaRoute2Info.getIconUri();
        if (iconUri != null) {
            builder.setIconUri(iconUri);
        }
        Bundle extras = mediaRoute2Info.getExtras();
        if (extras == null || !extras.containsKey("androidx.mediarouter.media.KEY_EXTRAS") || !extras.containsKey("androidx.mediarouter.media.KEY_DEVICE_TYPE") || !extras.containsKey("androidx.mediarouter.media.KEY_CONTROL_FILTERS")) {
            return null;
        }
        builder.setExtras(extras.getBundle("androidx.mediarouter.media.KEY_EXTRAS"));
        builder.setDeviceType(extras.getInt("androidx.mediarouter.media.KEY_DEVICE_TYPE", 0));
        ArrayList parcelableArrayList = extras.getParcelableArrayList("androidx.mediarouter.media.KEY_CONTROL_FILTERS");
        if (parcelableArrayList != null) {
            builder.addControlFilters(parcelableArrayList);
        }
        return builder.build();
    }

    static Collection<String> toFeatures(List<IntentFilter> list) {
        HashSet hashSet = new HashSet();
        for (IntentFilter intentFilter : list) {
            int iCountCategories = intentFilter.countCategories();
            for (int i = 0; i < iCountCategories; i++) {
                hashSet.add(toRouteFeature(intentFilter.getCategory(i)));
            }
        }
        return hashSet;
    }

    static RouteDiscoveryPreference toDiscoveryPreference(MediaRouteDiscoveryRequest mediaRouteDiscoveryRequest) {
        if (mediaRouteDiscoveryRequest == null || !mediaRouteDiscoveryRequest.isValid()) {
            return new RouteDiscoveryPreference.Builder(new ArrayList(), false).build();
        }
        return new RouteDiscoveryPreference.Builder((List) mediaRouteDiscoveryRequest.getSelector().getControlCategories().stream().map(new Function() { // from class: androidx.mediarouter.media.-$$Lambda$zMyvfVxKhaSv8GFN-7x4sfyRIzM
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return MediaRouter2Utils.toRouteFeature((String) obj);
            }
        }).collect(Collectors.toList()), mediaRouteDiscoveryRequest.isActiveScan()).build();
    }

    /* JADX WARN: Code duplicated, block: B:18:0x0034  */
    static String toRouteFeature(String str) {
        byte b;
        int iHashCode = str.hashCode();
        if (iHashCode != -2065577523) {
            if (iHashCode != 956939050) {
                if (iHashCode == 975975375 && str.equals("android.media.intent.category.LIVE_VIDEO")) {
                    b = 1;
                } else {
                    b = -1;
                }
            } else if (str.equals("android.media.intent.category.LIVE_AUDIO")) {
                b = 0;
            } else {
                b = -1;
            }
        } else if (str.equals("android.media.intent.category.REMOTE_PLAYBACK")) {
            b = 2;
        } else {
            b = -1;
        }
        if (b == 0) {
            return "android.media.route.feature.LIVE_AUDIO";
        }
        if (b != 1) {
            return b != 2 ? str : "android.media.route.feature.REMOTE_PLAYBACK";
        }
        return "android.media.route.feature.LIVE_VIDEO";
    }

    /* JADX WARN: Code duplicated, block: B:18:0x0034  */
    static String toControlCategory(String str) {
        byte b;
        int iHashCode = str.hashCode();
        if (iHashCode != 94496206) {
            if (iHashCode != 1328964233) {
                if (iHashCode == 1348000558 && str.equals("android.media.route.feature.LIVE_VIDEO")) {
                    b = 1;
                } else {
                    b = -1;
                }
            } else if (str.equals("android.media.route.feature.LIVE_AUDIO")) {
                b = 0;
            } else {
                b = -1;
            }
        } else if (str.equals("android.media.route.feature.REMOTE_PLAYBACK")) {
            b = 2;
        } else {
            b = -1;
        }
        if (b == 0) {
            return "android.media.intent.category.LIVE_AUDIO";
        }
        if (b != 1) {
            return b != 2 ? str : "android.media.intent.category.REMOTE_PLAYBACK";
        }
        return "android.media.intent.category.LIVE_VIDEO";
    }
}
