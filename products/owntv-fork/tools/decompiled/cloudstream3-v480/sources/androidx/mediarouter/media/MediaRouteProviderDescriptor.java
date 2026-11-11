package androidx.mediarouter.media;

import android.os.Bundle;
import android.os.Parcelable;
import j$.util.DesugarCollections;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class MediaRouteProviderDescriptor {
    private static final String KEY_ROUTES = "routes";
    private static final String KEY_SUPPORTS_DYNAMIC_GROUP_ROUTE = "supportsDynamicGroupRoute";
    Bundle mBundle;
    final List<MediaRouteDescriptor> mRoutes;
    final boolean mSupportsDynamicGroupRoute;

    MediaRouteProviderDescriptor(List<MediaRouteDescriptor> list, boolean z) {
        if (list.isEmpty()) {
            this.mRoutes = Collections.EMPTY_LIST;
        } else {
            this.mRoutes = DesugarCollections.unmodifiableList(new ArrayList(list));
        }
        this.mSupportsDynamicGroupRoute = z;
    }

    public List<MediaRouteDescriptor> getRoutes() {
        return this.mRoutes;
    }

    public boolean isValid() {
        int size = getRoutes().size();
        for (int i = 0; i < size; i++) {
            MediaRouteDescriptor mediaRouteDescriptor = this.mRoutes.get(i);
            if (mediaRouteDescriptor == null || !mediaRouteDescriptor.isValid()) {
                return false;
            }
        }
        return true;
    }

    public boolean supportsDynamicGroupRoute() {
        return this.mSupportsDynamicGroupRoute;
    }

    public String toString() {
        return "MediaRouteProviderDescriptor{ routes=" + Arrays.toString(getRoutes().toArray()) + ", isValid=" + isValid() + " }";
    }

    public Bundle asBundle() {
        Bundle bundle = this.mBundle;
        if (bundle != null) {
            return bundle;
        }
        this.mBundle = new Bundle();
        if (!this.mRoutes.isEmpty()) {
            int size = this.mRoutes.size();
            ArrayList<? extends Parcelable> arrayList = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                arrayList.add(this.mRoutes.get(i).asBundle());
            }
            this.mBundle.putParcelableArrayList(KEY_ROUTES, arrayList);
        }
        this.mBundle.putBoolean(KEY_SUPPORTS_DYNAMIC_GROUP_ROUTE, this.mSupportsDynamicGroupRoute);
        return this.mBundle;
    }

    public static MediaRouteProviderDescriptor fromBundle(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList parcelableArrayList = bundle.getParcelableArrayList(KEY_ROUTES);
        if (parcelableArrayList != null) {
            for (int i = 0; i < parcelableArrayList.size(); i++) {
                arrayList.add(MediaRouteDescriptor.fromBundle((Bundle) parcelableArrayList.get(i)));
            }
        }
        return new MediaRouteProviderDescriptor(arrayList, bundle.getBoolean(KEY_SUPPORTS_DYNAMIC_GROUP_ROUTE, false));
    }

    public static final class Builder {
        private final List<MediaRouteDescriptor> mRoutes;
        private boolean mSupportsDynamicGroupRoute;

        public Builder() {
            this.mRoutes = new ArrayList();
            this.mSupportsDynamicGroupRoute = false;
        }

        public Builder(MediaRouteProviderDescriptor mediaRouteProviderDescriptor) {
            ArrayList arrayList = new ArrayList();
            this.mRoutes = arrayList;
            this.mSupportsDynamicGroupRoute = false;
            if (mediaRouteProviderDescriptor == null) {
                throw new IllegalArgumentException("descriptor must not be null");
            }
            arrayList.addAll(mediaRouteProviderDescriptor.getRoutes());
            this.mSupportsDynamicGroupRoute = mediaRouteProviderDescriptor.mSupportsDynamicGroupRoute;
        }

        public Builder addRoute(MediaRouteDescriptor mediaRouteDescriptor) {
            if (mediaRouteDescriptor == null) {
                throw new IllegalArgumentException("route must not be null");
            }
            if (this.mRoutes.contains(mediaRouteDescriptor)) {
                throw new IllegalArgumentException("route descriptor already added");
            }
            this.mRoutes.add(mediaRouteDescriptor);
            return this;
        }

        public Builder addRoutes(Collection<MediaRouteDescriptor> collection) {
            if (collection == null) {
                throw new IllegalArgumentException("routes must not be null");
            }
            if (!collection.isEmpty()) {
                Iterator<MediaRouteDescriptor> it = collection.iterator();
                while (it.hasNext()) {
                    addRoute(it.next());
                }
            }
            return this;
        }

        Builder setRoutes(Collection<MediaRouteDescriptor> collection) {
            this.mRoutes.clear();
            if (collection != null) {
                this.mRoutes.addAll(collection);
            }
            return this;
        }

        public Builder setSupportsDynamicGroupRoute(boolean z) {
            this.mSupportsDynamicGroupRoute = z;
            return this;
        }

        public MediaRouteProviderDescriptor build() {
            return new MediaRouteProviderDescriptor(this.mRoutes, this.mSupportsDynamicGroupRoute);
        }
    }
}
