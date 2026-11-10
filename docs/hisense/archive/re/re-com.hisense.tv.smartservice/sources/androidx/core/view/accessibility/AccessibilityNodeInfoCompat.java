package androidx.core.view.accessibility;

import android.R;
import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.graphics.Region;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.core.os.BuildCompat;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class AccessibilityNodeInfoCompat {
    public static final int ACTION_ACCESSIBILITY_FOCUS = 64;
    public static final String ACTION_ARGUMENT_COLUMN_INT = "android.view.accessibility.action.ARGUMENT_COLUMN_INT";
    public static final String ACTION_ARGUMENT_EXTEND_SELECTION_BOOLEAN = "ACTION_ARGUMENT_EXTEND_SELECTION_BOOLEAN";
    public static final String ACTION_ARGUMENT_HTML_ELEMENT_STRING = "ACTION_ARGUMENT_HTML_ELEMENT_STRING";
    public static final String ACTION_ARGUMENT_MOVEMENT_GRANULARITY_INT = "ACTION_ARGUMENT_MOVEMENT_GRANULARITY_INT";
    public static final String ACTION_ARGUMENT_MOVE_WINDOW_X = "ACTION_ARGUMENT_MOVE_WINDOW_X";
    public static final String ACTION_ARGUMENT_MOVE_WINDOW_Y = "ACTION_ARGUMENT_MOVE_WINDOW_Y";

    @SuppressLint({"ActionValue"})
    public static final String ACTION_ARGUMENT_PRESS_AND_HOLD_DURATION_MILLIS_INT = "android.view.accessibility.action.ARGUMENT_PRESS_AND_HOLD_DURATION_MILLIS_INT";
    public static final String ACTION_ARGUMENT_PROGRESS_VALUE = "android.view.accessibility.action.ARGUMENT_PROGRESS_VALUE";
    public static final String ACTION_ARGUMENT_ROW_INT = "android.view.accessibility.action.ARGUMENT_ROW_INT";
    public static final String ACTION_ARGUMENT_SELECTION_END_INT = "ACTION_ARGUMENT_SELECTION_END_INT";
    public static final String ACTION_ARGUMENT_SELECTION_START_INT = "ACTION_ARGUMENT_SELECTION_START_INT";
    public static final String ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE = "ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE";
    public static final int ACTION_CLEAR_ACCESSIBILITY_FOCUS = 128;
    public static final int ACTION_CLEAR_FOCUS = 2;
    public static final int ACTION_CLEAR_SELECTION = 8;
    public static final int ACTION_CLICK = 16;
    public static final int ACTION_COLLAPSE = 524288;
    public static final int ACTION_COPY = 16384;
    public static final int ACTION_CUT = 65536;
    public static final int ACTION_DISMISS = 1048576;
    public static final int ACTION_EXPAND = 262144;
    public static final int ACTION_FOCUS = 1;
    public static final int ACTION_LONG_CLICK = 32;
    public static final int ACTION_NEXT_AT_MOVEMENT_GRANULARITY = 256;
    public static final int ACTION_NEXT_HTML_ELEMENT = 1024;
    public static final int ACTION_PASTE = 32768;
    public static final int ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY = 512;
    public static final int ACTION_PREVIOUS_HTML_ELEMENT = 2048;
    public static final int ACTION_SCROLL_BACKWARD = 8192;
    public static final int ACTION_SCROLL_FORWARD = 4096;
    public static final int ACTION_SELECT = 4;
    public static final int ACTION_SET_SELECTION = 131072;
    public static final int ACTION_SET_TEXT = 2097152;
    public static final int BOOLEAN_PROPERTY_IS_HEADING = 2;
    public static final int BOOLEAN_PROPERTY_IS_SHOWING_HINT = 4;
    public static final int BOOLEAN_PROPERTY_IS_TEXT_ENTRY_KEY = 8;
    public static final String BOOLEAN_PROPERTY_KEY = "androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY";
    public static final int BOOLEAN_PROPERTY_SCREEN_READER_FOCUSABLE = 1;
    public static final int FOCUS_ACCESSIBILITY = 2;
    public static final int FOCUS_INPUT = 1;
    public static final String HINT_TEXT_KEY = "androidx.view.accessibility.AccessibilityNodeInfoCompat.HINT_TEXT_KEY";
    public static final int MOVEMENT_GRANULARITY_CHARACTER = 1;
    public static final int MOVEMENT_GRANULARITY_LINE = 4;
    public static final int MOVEMENT_GRANULARITY_PAGE = 16;
    public static final int MOVEMENT_GRANULARITY_PARAGRAPH = 8;
    public static final int MOVEMENT_GRANULARITY_WORD = 2;
    public static final String PANE_TITLE_KEY = "androidx.view.accessibility.AccessibilityNodeInfoCompat.PANE_TITLE_KEY";
    public static final String ROLE_DESCRIPTION_KEY = "AccessibilityNodeInfo.roleDescription";
    public static final String SPANS_ACTION_ID_KEY = "androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ACTION_ID_KEY";
    public static final String SPANS_END_KEY = "androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_END_KEY";
    public static final String SPANS_FLAGS_KEY = "androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_FLAGS_KEY";
    public static final String SPANS_ID_KEY = "androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ID_KEY";
    public static final String SPANS_START_KEY = "androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY";
    public static final String STATE_DESCRIPTION_KEY = "androidx.view.accessibility.AccessibilityNodeInfoCompat.STATE_DESCRIPTION_KEY";
    public static final String TOOLTIP_TEXT_KEY = "androidx.view.accessibility.AccessibilityNodeInfoCompat.TOOLTIP_TEXT_KEY";
    public static int sClickableSpanId;
    public final AccessibilityNodeInfo mInfo;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public int mParentVirtualDescendantId = -1;
    public int mVirtualDescendantId = -1;

    public static class AccessibilityActionCompat {
        public static final AccessibilityActionCompat ACTION_CONTEXT_CLICK;
        public static final AccessibilityActionCompat ACTION_HIDE_TOOLTIP;

        @NonNull
        public static final AccessibilityActionCompat ACTION_IME_ENTER;
        public static final AccessibilityActionCompat ACTION_MOVE_WINDOW;

        @NonNull
        public static final AccessibilityActionCompat ACTION_PAGE_DOWN;

        @NonNull
        public static final AccessibilityActionCompat ACTION_PAGE_LEFT;

        @NonNull
        public static final AccessibilityActionCompat ACTION_PAGE_RIGHT;

        @NonNull
        public static final AccessibilityActionCompat ACTION_PAGE_UP;

        @NonNull
        public static final AccessibilityActionCompat ACTION_PRESS_AND_HOLD;
        public static final AccessibilityActionCompat ACTION_SCROLL_DOWN;
        public static final AccessibilityActionCompat ACTION_SCROLL_LEFT;
        public static final AccessibilityActionCompat ACTION_SCROLL_RIGHT;
        public static final AccessibilityActionCompat ACTION_SCROLL_TO_POSITION;
        public static final AccessibilityActionCompat ACTION_SCROLL_UP;
        public static final AccessibilityActionCompat ACTION_SET_PROGRESS;
        public static final AccessibilityActionCompat ACTION_SHOW_ON_SCREEN;
        public static final AccessibilityActionCompat ACTION_SHOW_TOOLTIP;
        public static final String TAG = "A11yActionCompat";
        public final Object mAction;

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public final AccessibilityViewCommand mCommand;
        public final int mId;
        public final Class<? extends AccessibilityViewCommand.CommandArguments> mViewCommandArgumentClass;
        public static final AccessibilityActionCompat ACTION_FOCUS = new AccessibilityActionCompat(1, null);
        public static final AccessibilityActionCompat ACTION_CLEAR_FOCUS = new AccessibilityActionCompat(2, null);
        public static final AccessibilityActionCompat ACTION_SELECT = new AccessibilityActionCompat(4, null);
        public static final AccessibilityActionCompat ACTION_CLEAR_SELECTION = new AccessibilityActionCompat(8, null);
        public static final AccessibilityActionCompat ACTION_CLICK = new AccessibilityActionCompat(16, null);
        public static final AccessibilityActionCompat ACTION_LONG_CLICK = new AccessibilityActionCompat(32, null);
        public static final AccessibilityActionCompat ACTION_ACCESSIBILITY_FOCUS = new AccessibilityActionCompat(64, null);
        public static final AccessibilityActionCompat ACTION_CLEAR_ACCESSIBILITY_FOCUS = new AccessibilityActionCompat(128, null);
        public static final AccessibilityActionCompat ACTION_NEXT_AT_MOVEMENT_GRANULARITY = new AccessibilityActionCompat(256, (CharSequence) null, (Class<? extends AccessibilityViewCommand.CommandArguments>) AccessibilityViewCommand.MoveAtGranularityArguments.class);
        public static final AccessibilityActionCompat ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY = new AccessibilityActionCompat(512, (CharSequence) null, (Class<? extends AccessibilityViewCommand.CommandArguments>) AccessibilityViewCommand.MoveAtGranularityArguments.class);
        public static final AccessibilityActionCompat ACTION_NEXT_HTML_ELEMENT = new AccessibilityActionCompat(1024, (CharSequence) null, (Class<? extends AccessibilityViewCommand.CommandArguments>) AccessibilityViewCommand.MoveHtmlArguments.class);
        public static final AccessibilityActionCompat ACTION_PREVIOUS_HTML_ELEMENT = new AccessibilityActionCompat(2048, (CharSequence) null, (Class<? extends AccessibilityViewCommand.CommandArguments>) AccessibilityViewCommand.MoveHtmlArguments.class);
        public static final AccessibilityActionCompat ACTION_SCROLL_FORWARD = new AccessibilityActionCompat(4096, null);
        public static final AccessibilityActionCompat ACTION_SCROLL_BACKWARD = new AccessibilityActionCompat(8192, null);
        public static final AccessibilityActionCompat ACTION_COPY = new AccessibilityActionCompat(16384, null);
        public static final AccessibilityActionCompat ACTION_PASTE = new AccessibilityActionCompat(32768, null);
        public static final AccessibilityActionCompat ACTION_CUT = new AccessibilityActionCompat(65536, null);
        public static final AccessibilityActionCompat ACTION_SET_SELECTION = new AccessibilityActionCompat(131072, (CharSequence) null, (Class<? extends AccessibilityViewCommand.CommandArguments>) AccessibilityViewCommand.SetSelectionArguments.class);
        public static final AccessibilityActionCompat ACTION_EXPAND = new AccessibilityActionCompat(262144, null);
        public static final AccessibilityActionCompat ACTION_COLLAPSE = new AccessibilityActionCompat(524288, null);
        public static final AccessibilityActionCompat ACTION_DISMISS = new AccessibilityActionCompat(1048576, null);
        public static final AccessibilityActionCompat ACTION_SET_TEXT = new AccessibilityActionCompat(2097152, (CharSequence) null, (Class<? extends AccessibilityViewCommand.CommandArguments>) AccessibilityViewCommand.SetTextArguments.class);

        static {
            int i = Build.VERSION.SDK_INT;
            ACTION_SHOW_ON_SCREEN = new AccessibilityActionCompat(AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_ON_SCREEN, R.id.accessibilityActionShowOnScreen, null, null, null);
            int i2 = Build.VERSION.SDK_INT;
            ACTION_SCROLL_TO_POSITION = new AccessibilityActionCompat(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_TO_POSITION, R.id.accessibilityActionScrollToPosition, null, null, AccessibilityViewCommand.ScrollToPositionArguments.class);
            int i3 = Build.VERSION.SDK_INT;
            ACTION_SCROLL_UP = new AccessibilityActionCompat(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_UP, R.id.accessibilityActionScrollUp, null, null, null);
            int i4 = Build.VERSION.SDK_INT;
            ACTION_SCROLL_LEFT = new AccessibilityActionCompat(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_LEFT, R.id.accessibilityActionScrollLeft, null, null, null);
            int i5 = Build.VERSION.SDK_INT;
            ACTION_SCROLL_DOWN = new AccessibilityActionCompat(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_DOWN, R.id.accessibilityActionScrollDown, null, null, null);
            int i6 = Build.VERSION.SDK_INT;
            ACTION_SCROLL_RIGHT = new AccessibilityActionCompat(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_RIGHT, R.id.accessibilityActionScrollRight, null, null, null);
            ACTION_PAGE_UP = new AccessibilityActionCompat(Build.VERSION.SDK_INT >= 29 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_UP : null, R.id.accessibilityActionPageUp, null, null, null);
            ACTION_PAGE_DOWN = new AccessibilityActionCompat(Build.VERSION.SDK_INT >= 29 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_DOWN : null, R.id.accessibilityActionPageDown, null, null, null);
            ACTION_PAGE_LEFT = new AccessibilityActionCompat(Build.VERSION.SDK_INT >= 29 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_LEFT : null, R.id.accessibilityActionPageLeft, null, null, null);
            ACTION_PAGE_RIGHT = new AccessibilityActionCompat(Build.VERSION.SDK_INT >= 29 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_RIGHT : null, R.id.accessibilityActionPageRight, null, null, null);
            int i7 = Build.VERSION.SDK_INT;
            ACTION_CONTEXT_CLICK = new AccessibilityActionCompat(AccessibilityNodeInfo.AccessibilityAction.ACTION_CONTEXT_CLICK, R.id.accessibilityActionContextClick, null, null, null);
            int i8 = Build.VERSION.SDK_INT;
            ACTION_SET_PROGRESS = new AccessibilityActionCompat(AccessibilityNodeInfo.AccessibilityAction.ACTION_SET_PROGRESS, R.id.accessibilityActionSetProgress, null, null, AccessibilityViewCommand.SetProgressArguments.class);
            int i9 = Build.VERSION.SDK_INT;
            ACTION_MOVE_WINDOW = new AccessibilityActionCompat(AccessibilityNodeInfo.AccessibilityAction.ACTION_MOVE_WINDOW, R.id.accessibilityActionMoveWindow, null, null, AccessibilityViewCommand.MoveWindowArguments.class);
            ACTION_SHOW_TOOLTIP = new AccessibilityActionCompat(Build.VERSION.SDK_INT >= 28 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_TOOLTIP : null, R.id.accessibilityActionShowTooltip, null, null, null);
            ACTION_HIDE_TOOLTIP = new AccessibilityActionCompat(Build.VERSION.SDK_INT >= 28 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_HIDE_TOOLTIP : null, R.id.accessibilityActionHideTooltip, null, null, null);
            ACTION_PRESS_AND_HOLD = new AccessibilityActionCompat(Build.VERSION.SDK_INT >= 30 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PRESS_AND_HOLD : null, R.id.accessibilityActionPressAndHold, null, null, null);
            ACTION_IME_ENTER = new AccessibilityActionCompat(Build.VERSION.SDK_INT >= 30 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_IME_ENTER : null, R.id.accessibilityActionImeEnter, null, null, null);
        }

        public AccessibilityActionCompat(int actionId, CharSequence label) {
            this(null, actionId, label, null, null);
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public AccessibilityActionCompat createReplacementAction(CharSequence label, AccessibilityViewCommand command) {
            return new AccessibilityActionCompat(null, this.mId, label, command, this.mViewCommandArgumentClass);
        }

        public boolean equals(@Nullable Object obj) {
            if (obj == null || !(obj instanceof AccessibilityActionCompat)) {
                return false;
            }
            AccessibilityActionCompat accessibilityActionCompat = (AccessibilityActionCompat) obj;
            Object obj2 = this.mAction;
            if (obj2 == null) {
                return accessibilityActionCompat.mAction == null;
            }
            return obj2.equals(accessibilityActionCompat.mAction);
        }

        public int getId() {
            int i = Build.VERSION.SDK_INT;
            return ((AccessibilityNodeInfo.AccessibilityAction) this.mAction).getId();
        }

        public CharSequence getLabel() {
            int i = Build.VERSION.SDK_INT;
            return ((AccessibilityNodeInfo.AccessibilityAction) this.mAction).getLabel();
        }

        public int hashCode() {
            Object obj = this.mAction;
            if (obj != null) {
                return obj.hashCode();
            }
            return 0;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public boolean perform(View view, Bundle arguments) {
            if (this.mCommand == null) {
                return false;
            }
            AccessibilityViewCommand.CommandArguments commandArguments = null;
            Class<? extends AccessibilityViewCommand.CommandArguments> cls = this.mViewCommandArgumentClass;
            if (cls != null) {
                try {
                    AccessibilityViewCommand.CommandArguments commandArgumentsNewInstance = cls.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                    try {
                        commandArgumentsNewInstance.setBundle(arguments);
                        commandArguments = commandArgumentsNewInstance;
                    } catch (Exception e2) {
                        e = e2;
                        commandArguments = commandArgumentsNewInstance;
                        Class<? extends AccessibilityViewCommand.CommandArguments> cls2 = this.mViewCommandArgumentClass;
                        Log.e(TAG, "Failed to execute command with argument class ViewCommandArgument: " + (cls2 == null ? "null" : cls2.getName()), e);
                    }
                } catch (Exception e3) {
                    e = e3;
                }
            }
            return this.mCommand.perform(view, commandArguments);
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public AccessibilityActionCompat(int actionId, CharSequence label, AccessibilityViewCommand command) {
            this(null, actionId, label, command, null);
        }

        public AccessibilityActionCompat(Object action) {
            this(action, 0, null, null, null);
        }

        public AccessibilityActionCompat(int actionId, CharSequence label, Class<? extends AccessibilityViewCommand.CommandArguments> viewCommandArgumentClass) {
            this(null, actionId, label, null, viewCommandArgumentClass);
        }

        public AccessibilityActionCompat(Object action, int id, CharSequence label, AccessibilityViewCommand command, Class<? extends AccessibilityViewCommand.CommandArguments> viewCommandArgumentClass) {
            this.mId = id;
            this.mCommand = command;
            int i = Build.VERSION.SDK_INT;
            if (action == null) {
                this.mAction = new AccessibilityNodeInfo.AccessibilityAction(id, label);
            } else {
                this.mAction = action;
            }
            this.mViewCommandArgumentClass = viewCommandArgumentClass;
        }
    }

    public static class RangeInfoCompat {
        public static final int RANGE_TYPE_FLOAT = 1;
        public static final int RANGE_TYPE_INT = 0;
        public static final int RANGE_TYPE_PERCENT = 2;
        public final Object mInfo;

        public RangeInfoCompat(Object info) {
            this.mInfo = info;
        }

        public static RangeInfoCompat obtain(int type, float min, float max, float current) {
            int i = Build.VERSION.SDK_INT;
            return new RangeInfoCompat(AccessibilityNodeInfo.RangeInfo.obtain(type, min, max, current));
        }

        public float getCurrent() {
            int i = Build.VERSION.SDK_INT;
            return ((AccessibilityNodeInfo.RangeInfo) this.mInfo).getCurrent();
        }

        public float getMax() {
            int i = Build.VERSION.SDK_INT;
            return ((AccessibilityNodeInfo.RangeInfo) this.mInfo).getMax();
        }

        public float getMin() {
            int i = Build.VERSION.SDK_INT;
            return ((AccessibilityNodeInfo.RangeInfo) this.mInfo).getMin();
        }

        public int getType() {
            int i = Build.VERSION.SDK_INT;
            return ((AccessibilityNodeInfo.RangeInfo) this.mInfo).getType();
        }
    }

    @Deprecated
    public AccessibilityNodeInfoCompat(Object info) {
        this.mInfo = (AccessibilityNodeInfo) info;
    }

    private void addSpanLocationToExtras(ClickableSpan span, Spanned spanned, int id) {
        extrasIntList(SPANS_START_KEY).add(Integer.valueOf(spanned.getSpanStart(span)));
        extrasIntList(SPANS_END_KEY).add(Integer.valueOf(spanned.getSpanEnd(span)));
        extrasIntList(SPANS_FLAGS_KEY).add(Integer.valueOf(spanned.getSpanFlags(span)));
        extrasIntList(SPANS_ID_KEY).add(Integer.valueOf(id));
    }

    private void clearExtrasSpans() {
        int i = Build.VERSION.SDK_INT;
        this.mInfo.getExtras().remove(SPANS_START_KEY);
        this.mInfo.getExtras().remove(SPANS_END_KEY);
        this.mInfo.getExtras().remove(SPANS_FLAGS_KEY);
        this.mInfo.getExtras().remove(SPANS_ID_KEY);
    }

    private List<Integer> extrasIntList(String key) {
        int i = Build.VERSION.SDK_INT;
        ArrayList<Integer> integerArrayList = this.mInfo.getExtras().getIntegerArrayList(key);
        if (integerArrayList != null) {
            return integerArrayList;
        }
        ArrayList<Integer> arrayList = new ArrayList<>();
        this.mInfo.getExtras().putIntegerArrayList(key, arrayList);
        return arrayList;
    }

    public static String getActionSymbolicName(int action) {
        if (action == 1) {
            return "ACTION_FOCUS";
        }
        if (action == 2) {
            return "ACTION_CLEAR_FOCUS";
        }
        switch (action) {
            case 4:
                return "ACTION_SELECT";
            case 8:
                return "ACTION_CLEAR_SELECTION";
            case 16:
                return "ACTION_CLICK";
            case 32:
                return "ACTION_LONG_CLICK";
            case 64:
                return "ACTION_ACCESSIBILITY_FOCUS";
            case 128:
                return "ACTION_CLEAR_ACCESSIBILITY_FOCUS";
            case 256:
                return "ACTION_NEXT_AT_MOVEMENT_GRANULARITY";
            case 512:
                return "ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY";
            case 1024:
                return "ACTION_NEXT_HTML_ELEMENT";
            case 2048:
                return "ACTION_PREVIOUS_HTML_ELEMENT";
            case 4096:
                return "ACTION_SCROLL_FORWARD";
            case 8192:
                return "ACTION_SCROLL_BACKWARD";
            case 16384:
                return "ACTION_COPY";
            case 32768:
                return "ACTION_PASTE";
            case 65536:
                return "ACTION_CUT";
            case 131072:
                return "ACTION_SET_SELECTION";
            case 262144:
                return "ACTION_EXPAND";
            case 524288:
                return "ACTION_COLLAPSE";
            case 2097152:
                return "ACTION_SET_TEXT";
            case R.id.accessibilityActionMoveWindow:
                return "ACTION_MOVE_WINDOW";
            case R.id.accessibilityActionImeEnter:
                return "ACTION_IME_ENTER";
            default:
                switch (action) {
                    case R.id.accessibilityActionShowOnScreen:
                        return "ACTION_SHOW_ON_SCREEN";
                    case R.id.accessibilityActionScrollToPosition:
                        return "ACTION_SCROLL_TO_POSITION";
                    case R.id.accessibilityActionScrollUp:
                        return "ACTION_SCROLL_UP";
                    case R.id.accessibilityActionScrollLeft:
                        return "ACTION_SCROLL_LEFT";
                    case R.id.accessibilityActionScrollDown:
                        return "ACTION_SCROLL_DOWN";
                    case R.id.accessibilityActionScrollRight:
                        return "ACTION_SCROLL_RIGHT";
                    case R.id.accessibilityActionContextClick:
                        return "ACTION_CONTEXT_CLICK";
                    case R.id.accessibilityActionSetProgress:
                        return "ACTION_SET_PROGRESS";
                    default:
                        switch (action) {
                            case R.id.accessibilityActionShowTooltip:
                                return "ACTION_SHOW_TOOLTIP";
                            case R.id.accessibilityActionHideTooltip:
                                return "ACTION_HIDE_TOOLTIP";
                            case R.id.accessibilityActionPageUp:
                                return "ACTION_PAGE_UP";
                            case R.id.accessibilityActionPageDown:
                                return "ACTION_PAGE_DOWN";
                            case R.id.accessibilityActionPageLeft:
                                return "ACTION_PAGE_LEFT";
                            case R.id.accessibilityActionPageRight:
                                return "ACTION_PAGE_RIGHT";
                            case R.id.accessibilityActionPressAndHold:
                                return "ACTION_PRESS_AND_HOLD";
                            default:
                                return "ACTION_UNKNOWN";
                        }
                }
        }
    }

    private boolean getBooleanProperty(int property) {
        Bundle extras = getExtras();
        return extras != null && (extras.getInt(BOOLEAN_PROPERTY_KEY, 0) & property) == property;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public static ClickableSpan[] getClickableSpans(CharSequence text) {
        if (text instanceof Spanned) {
            return (ClickableSpan[]) ((Spanned) text).getSpans(0, text.length(), ClickableSpan.class);
        }
        return null;
    }

    private SparseArray<WeakReference<ClickableSpan>> getOrCreateSpansFromViewTags(View host) {
        SparseArray<WeakReference<ClickableSpan>> spansFromViewTags = getSpansFromViewTags(host);
        if (spansFromViewTags != null) {
            return spansFromViewTags;
        }
        SparseArray<WeakReference<ClickableSpan>> sparseArray = new SparseArray<>();
        host.setTag(androidx.core.R.id.tag_accessibility_clickable_spans, sparseArray);
        return sparseArray;
    }

    private SparseArray<WeakReference<ClickableSpan>> getSpansFromViewTags(View host) {
        return (SparseArray) host.getTag(androidx.core.R.id.tag_accessibility_clickable_spans);
    }

    private boolean hasSpans() {
        return !extrasIntList(SPANS_START_KEY).isEmpty();
    }

    private int idForClickableSpan(ClickableSpan span, SparseArray<WeakReference<ClickableSpan>> spans) {
        if (spans != null) {
            for (int i = 0; i < spans.size(); i++) {
                if (span.equals(spans.valueAt(i).get())) {
                    return spans.keyAt(i);
                }
            }
        }
        int i2 = sClickableSpanId;
        sClickableSpanId = i2 + 1;
        return i2;
    }

    public static AccessibilityNodeInfoCompat obtain(View source) {
        return new AccessibilityNodeInfoCompat(AccessibilityNodeInfo.obtain(source));
    }

    private void removeCollectedSpans(View view) {
        SparseArray<WeakReference<ClickableSpan>> spansFromViewTags = getSpansFromViewTags(view);
        if (spansFromViewTags != null) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < spansFromViewTags.size(); i++) {
                if (spansFromViewTags.valueAt(i).get() == null) {
                    arrayList.add(Integer.valueOf(i));
                }
            }
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                spansFromViewTags.remove(((Integer) arrayList.get(i2)).intValue());
            }
        }
    }

    private void setBooleanProperty(int property, boolean value) {
        Bundle extras = getExtras();
        if (extras != null) {
            int i = extras.getInt(BOOLEAN_PROPERTY_KEY, 0) & (~property);
            if (!value) {
                property = 0;
            }
            extras.putInt(BOOLEAN_PROPERTY_KEY, property | i);
        }
    }

    public static AccessibilityNodeInfoCompat wrap(@NonNull AccessibilityNodeInfo info) {
        return new AccessibilityNodeInfoCompat(info);
    }

    public static AccessibilityNodeInfoCompat wrapNonNullInstance(Object object) {
        if (object != null) {
            return new AccessibilityNodeInfoCompat(object);
        }
        return null;
    }

    public void addAction(int action) {
        this.mInfo.addAction(action);
    }

    public void addChild(View child) {
        this.mInfo.addChild(child);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public void addSpansToExtras(CharSequence text, View view) {
        int i = Build.VERSION.SDK_INT;
    }

    public boolean canOpenPopup() {
        int i = Build.VERSION.SDK_INT;
        return this.mInfo.canOpenPopup();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AccessibilityNodeInfoCompat)) {
            return false;
        }
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = (AccessibilityNodeInfoCompat) obj;
        AccessibilityNodeInfo accessibilityNodeInfo = this.mInfo;
        if (accessibilityNodeInfo == null) {
            if (accessibilityNodeInfoCompat.mInfo != null) {
                return false;
            }
        } else if (!accessibilityNodeInfo.equals(accessibilityNodeInfoCompat.mInfo)) {
            return false;
        }
        return this.mVirtualDescendantId == accessibilityNodeInfoCompat.mVirtualDescendantId && this.mParentVirtualDescendantId == accessibilityNodeInfoCompat.mParentVirtualDescendantId;
    }

    public List<AccessibilityNodeInfoCompat> findAccessibilityNodeInfosByText(String text) {
        ArrayList arrayList = new ArrayList();
        List<AccessibilityNodeInfo> listFindAccessibilityNodeInfosByText = this.mInfo.findAccessibilityNodeInfosByText(text);
        int size = listFindAccessibilityNodeInfosByText.size();
        for (int i = 0; i < size; i++) {
            arrayList.add(new AccessibilityNodeInfoCompat(listFindAccessibilityNodeInfosByText.get(i)));
        }
        return arrayList;
    }

    public List<AccessibilityNodeInfoCompat> findAccessibilityNodeInfosByViewId(String viewId) {
        int i = Build.VERSION.SDK_INT;
        List<AccessibilityNodeInfo> listFindAccessibilityNodeInfosByViewId = this.mInfo.findAccessibilityNodeInfosByViewId(viewId);
        ArrayList arrayList = new ArrayList();
        Iterator<AccessibilityNodeInfo> it = listFindAccessibilityNodeInfosByViewId.iterator();
        while (it.hasNext()) {
            arrayList.add(new AccessibilityNodeInfoCompat(it.next()));
        }
        return arrayList;
    }

    public AccessibilityNodeInfoCompat findFocus(int focus) {
        int i = Build.VERSION.SDK_INT;
        return wrapNonNullInstance(this.mInfo.findFocus(focus));
    }

    public AccessibilityNodeInfoCompat focusSearch(int direction) {
        int i = Build.VERSION.SDK_INT;
        return wrapNonNullInstance(this.mInfo.focusSearch(direction));
    }

    public List<AccessibilityActionCompat> getActionList() {
        int i = Build.VERSION.SDK_INT;
        List<AccessibilityNodeInfo.AccessibilityAction> actionList = this.mInfo.getActionList();
        if (actionList == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        int size = actionList.size();
        for (int i2 = 0; i2 < size; i2++) {
            arrayList.add(new AccessibilityActionCompat(actionList.get(i2)));
        }
        return arrayList;
    }

    public int getActions() {
        return this.mInfo.getActions();
    }

    @Deprecated
    public void getBoundsInParent(Rect outBounds) {
        this.mInfo.getBoundsInParent(outBounds);
    }

    public void getBoundsInScreen(Rect outBounds) {
        this.mInfo.getBoundsInScreen(outBounds);
    }

    public AccessibilityNodeInfoCompat getChild(int index) {
        return wrapNonNullInstance(this.mInfo.getChild(index));
    }

    public int getChildCount() {
        return this.mInfo.getChildCount();
    }

    public CharSequence getClassName() {
        return this.mInfo.getClassName();
    }

    public CollectionInfoCompat getCollectionInfo() {
        int i = Build.VERSION.SDK_INT;
        AccessibilityNodeInfo.CollectionInfo collectionInfo = this.mInfo.getCollectionInfo();
        if (collectionInfo != null) {
            return new CollectionInfoCompat(collectionInfo);
        }
        return null;
    }

    public CollectionItemInfoCompat getCollectionItemInfo() {
        int i = Build.VERSION.SDK_INT;
        AccessibilityNodeInfo.CollectionItemInfo collectionItemInfo = this.mInfo.getCollectionItemInfo();
        if (collectionItemInfo != null) {
            return new CollectionItemInfoCompat(collectionItemInfo);
        }
        return null;
    }

    public CharSequence getContentDescription() {
        return this.mInfo.getContentDescription();
    }

    public int getDrawingOrder() {
        int i = Build.VERSION.SDK_INT;
        return this.mInfo.getDrawingOrder();
    }

    public CharSequence getError() {
        int i = Build.VERSION.SDK_INT;
        return this.mInfo.getError();
    }

    public Bundle getExtras() {
        int i = Build.VERSION.SDK_INT;
        return this.mInfo.getExtras();
    }

    @Nullable
    public CharSequence getHintText() {
        int i = Build.VERSION.SDK_INT;
        return this.mInfo.getHintText();
    }

    @Deprecated
    public Object getInfo() {
        return this.mInfo;
    }

    public int getInputType() {
        int i = Build.VERSION.SDK_INT;
        return this.mInfo.getInputType();
    }

    public AccessibilityNodeInfoCompat getLabelFor() {
        int i = Build.VERSION.SDK_INT;
        return wrapNonNullInstance(this.mInfo.getLabelFor());
    }

    public AccessibilityNodeInfoCompat getLabeledBy() {
        int i = Build.VERSION.SDK_INT;
        return wrapNonNullInstance(this.mInfo.getLabeledBy());
    }

    public int getLiveRegion() {
        int i = Build.VERSION.SDK_INT;
        return this.mInfo.getLiveRegion();
    }

    public int getMaxTextLength() {
        int i = Build.VERSION.SDK_INT;
        return this.mInfo.getMaxTextLength();
    }

    public int getMovementGranularities() {
        int i = Build.VERSION.SDK_INT;
        return this.mInfo.getMovementGranularities();
    }

    public CharSequence getPackageName() {
        return this.mInfo.getPackageName();
    }

    @Nullable
    public CharSequence getPaneTitle() {
        return Build.VERSION.SDK_INT >= 28 ? this.mInfo.getPaneTitle() : this.mInfo.getExtras().getCharSequence(PANE_TITLE_KEY);
    }

    public AccessibilityNodeInfoCompat getParent() {
        return wrapNonNullInstance(this.mInfo.getParent());
    }

    public RangeInfoCompat getRangeInfo() {
        int i = Build.VERSION.SDK_INT;
        AccessibilityNodeInfo.RangeInfo rangeInfo = this.mInfo.getRangeInfo();
        if (rangeInfo != null) {
            return new RangeInfoCompat(rangeInfo);
        }
        return null;
    }

    @Nullable
    public CharSequence getRoleDescription() {
        int i = Build.VERSION.SDK_INT;
        return this.mInfo.getExtras().getCharSequence(ROLE_DESCRIPTION_KEY);
    }

    @Nullable
    public CharSequence getStateDescription() {
        if (BuildCompat.isAtLeastR()) {
            return this.mInfo.getStateDescription();
        }
        int i = Build.VERSION.SDK_INT;
        return this.mInfo.getExtras().getCharSequence(STATE_DESCRIPTION_KEY);
    }

    public CharSequence getText() {
        if (!hasSpans()) {
            return this.mInfo.getText();
        }
        List<Integer> listExtrasIntList = extrasIntList(SPANS_START_KEY);
        List<Integer> listExtrasIntList2 = extrasIntList(SPANS_END_KEY);
        List<Integer> listExtrasIntList3 = extrasIntList(SPANS_FLAGS_KEY);
        List<Integer> listExtrasIntList4 = extrasIntList(SPANS_ID_KEY);
        SpannableString spannableString = new SpannableString(TextUtils.substring(this.mInfo.getText(), 0, this.mInfo.getText().length()));
        for (int i = 0; i < listExtrasIntList.size(); i++) {
            spannableString.setSpan(new AccessibilityClickableSpanCompat(listExtrasIntList4.get(i).intValue(), this, getExtras().getInt(SPANS_ACTION_ID_KEY)), listExtrasIntList.get(i).intValue(), listExtrasIntList2.get(i).intValue(), listExtrasIntList3.get(i).intValue());
        }
        return spannableString;
    }

    public int getTextSelectionEnd() {
        int i = Build.VERSION.SDK_INT;
        return this.mInfo.getTextSelectionEnd();
    }

    public int getTextSelectionStart() {
        int i = Build.VERSION.SDK_INT;
        return this.mInfo.getTextSelectionStart();
    }

    @Nullable
    public CharSequence getTooltipText() {
        return Build.VERSION.SDK_INT >= 28 ? this.mInfo.getTooltipText() : this.mInfo.getExtras().getCharSequence(TOOLTIP_TEXT_KEY);
    }

    @Nullable
    public TouchDelegateInfoCompat getTouchDelegateInfo() {
        AccessibilityNodeInfo.TouchDelegateInfo touchDelegateInfo;
        if (Build.VERSION.SDK_INT < 29 || (touchDelegateInfo = this.mInfo.getTouchDelegateInfo()) == null) {
            return null;
        }
        return new TouchDelegateInfoCompat(touchDelegateInfo);
    }

    public AccessibilityNodeInfoCompat getTraversalAfter() {
        int i = Build.VERSION.SDK_INT;
        return wrapNonNullInstance(this.mInfo.getTraversalAfter());
    }

    public AccessibilityNodeInfoCompat getTraversalBefore() {
        int i = Build.VERSION.SDK_INT;
        return wrapNonNullInstance(this.mInfo.getTraversalBefore());
    }

    public String getViewIdResourceName() {
        int i = Build.VERSION.SDK_INT;
        return this.mInfo.getViewIdResourceName();
    }

    public AccessibilityWindowInfoCompat getWindow() {
        int i = Build.VERSION.SDK_INT;
        return AccessibilityWindowInfoCompat.wrapNonNullInstance(this.mInfo.getWindow());
    }

    public int getWindowId() {
        return this.mInfo.getWindowId();
    }

    public int hashCode() {
        AccessibilityNodeInfo accessibilityNodeInfo = this.mInfo;
        if (accessibilityNodeInfo == null) {
            return 0;
        }
        return accessibilityNodeInfo.hashCode();
    }

    public boolean isAccessibilityFocused() {
        int i = Build.VERSION.SDK_INT;
        return this.mInfo.isAccessibilityFocused();
    }

    public boolean isCheckable() {
        return this.mInfo.isCheckable();
    }

    public boolean isChecked() {
        return this.mInfo.isChecked();
    }

    public boolean isClickable() {
        return this.mInfo.isClickable();
    }

    public boolean isContentInvalid() {
        int i = Build.VERSION.SDK_INT;
        return this.mInfo.isContentInvalid();
    }

    public boolean isContextClickable() {
        int i = Build.VERSION.SDK_INT;
        return this.mInfo.isContextClickable();
    }

    public boolean isDismissable() {
        int i = Build.VERSION.SDK_INT;
        return this.mInfo.isDismissable();
    }

    public boolean isEditable() {
        int i = Build.VERSION.SDK_INT;
        return this.mInfo.isEditable();
    }

    public boolean isEnabled() {
        return this.mInfo.isEnabled();
    }

    public boolean isFocusable() {
        return this.mInfo.isFocusable();
    }

    public boolean isFocused() {
        return this.mInfo.isFocused();
    }

    public boolean isHeading() {
        if (Build.VERSION.SDK_INT >= 28) {
            return this.mInfo.isHeading();
        }
        if (getBooleanProperty(2)) {
            return true;
        }
        CollectionItemInfoCompat collectionItemInfo = getCollectionItemInfo();
        return collectionItemInfo != null && collectionItemInfo.isHeading();
    }

    public boolean isImportantForAccessibility() {
        int i = Build.VERSION.SDK_INT;
        return this.mInfo.isImportantForAccessibility();
    }

    public boolean isLongClickable() {
        return this.mInfo.isLongClickable();
    }

    public boolean isMultiLine() {
        int i = Build.VERSION.SDK_INT;
        return this.mInfo.isMultiLine();
    }

    public boolean isPassword() {
        return this.mInfo.isPassword();
    }

    public boolean isScreenReaderFocusable() {
        return Build.VERSION.SDK_INT >= 28 ? this.mInfo.isScreenReaderFocusable() : getBooleanProperty(1);
    }

    public boolean isScrollable() {
        return this.mInfo.isScrollable();
    }

    public boolean isSelected() {
        return this.mInfo.isSelected();
    }

    public boolean isShowingHintText() {
        int i = Build.VERSION.SDK_INT;
        return this.mInfo.isShowingHintText();
    }

    public boolean isTextEntryKey() {
        return Build.VERSION.SDK_INT >= 29 ? this.mInfo.isTextEntryKey() : getBooleanProperty(8);
    }

    public boolean isVisibleToUser() {
        int i = Build.VERSION.SDK_INT;
        return this.mInfo.isVisibleToUser();
    }

    public boolean performAction(int action) {
        return this.mInfo.performAction(action);
    }

    public void recycle() {
        this.mInfo.recycle();
    }

    public boolean refresh() {
        int i = Build.VERSION.SDK_INT;
        return this.mInfo.refresh();
    }

    public boolean removeAction(AccessibilityActionCompat action) {
        int i = Build.VERSION.SDK_INT;
        return this.mInfo.removeAction((AccessibilityNodeInfo.AccessibilityAction) action.mAction);
    }

    public boolean removeChild(View child) {
        int i = Build.VERSION.SDK_INT;
        return this.mInfo.removeChild(child);
    }

    public void setAccessibilityFocused(boolean focused) {
        int i = Build.VERSION.SDK_INT;
        this.mInfo.setAccessibilityFocused(focused);
    }

    @Deprecated
    public void setBoundsInParent(Rect bounds) {
        this.mInfo.setBoundsInParent(bounds);
    }

    public void setBoundsInScreen(Rect bounds) {
        this.mInfo.setBoundsInScreen(bounds);
    }

    public void setCanOpenPopup(boolean opensPopup) {
        int i = Build.VERSION.SDK_INT;
        this.mInfo.setCanOpenPopup(opensPopup);
    }

    public void setCheckable(boolean checkable) {
        this.mInfo.setCheckable(checkable);
    }

    public void setChecked(boolean checked) {
        this.mInfo.setChecked(checked);
    }

    public void setClassName(CharSequence className) {
        this.mInfo.setClassName(className);
    }

    public void setClickable(boolean clickable) {
        this.mInfo.setClickable(clickable);
    }

    public void setCollectionInfo(Object collectionInfo) {
        int i = Build.VERSION.SDK_INT;
        this.mInfo.setCollectionInfo(collectionInfo == null ? null : (AccessibilityNodeInfo.CollectionInfo) ((CollectionInfoCompat) collectionInfo).mInfo);
    }

    public void setCollectionItemInfo(Object collectionItemInfo) {
        int i = Build.VERSION.SDK_INT;
        this.mInfo.setCollectionItemInfo(collectionItemInfo == null ? null : (AccessibilityNodeInfo.CollectionItemInfo) ((CollectionItemInfoCompat) collectionItemInfo).mInfo);
    }

    public void setContentDescription(CharSequence contentDescription) {
        this.mInfo.setContentDescription(contentDescription);
    }

    public void setContentInvalid(boolean contentInvalid) {
        int i = Build.VERSION.SDK_INT;
        this.mInfo.setContentInvalid(contentInvalid);
    }

    public void setContextClickable(boolean contextClickable) {
        int i = Build.VERSION.SDK_INT;
        this.mInfo.setContextClickable(contextClickable);
    }

    public void setDismissable(boolean dismissable) {
        int i = Build.VERSION.SDK_INT;
        this.mInfo.setDismissable(dismissable);
    }

    public void setDrawingOrder(int drawingOrderInParent) {
        int i = Build.VERSION.SDK_INT;
        this.mInfo.setDrawingOrder(drawingOrderInParent);
    }

    public void setEditable(boolean editable) {
        int i = Build.VERSION.SDK_INT;
        this.mInfo.setEditable(editable);
    }

    public void setEnabled(boolean enabled) {
        this.mInfo.setEnabled(enabled);
    }

    public void setError(CharSequence error) {
        int i = Build.VERSION.SDK_INT;
        this.mInfo.setError(error);
    }

    public void setFocusable(boolean focusable) {
        this.mInfo.setFocusable(focusable);
    }

    public void setFocused(boolean focused) {
        this.mInfo.setFocused(focused);
    }

    public void setHeading(boolean isHeading) {
        if (Build.VERSION.SDK_INT >= 28) {
            this.mInfo.setHeading(isHeading);
        } else {
            setBooleanProperty(2, isHeading);
        }
    }

    public void setHintText(@Nullable CharSequence hintText) {
        int i = Build.VERSION.SDK_INT;
        this.mInfo.setHintText(hintText);
    }

    public void setImportantForAccessibility(boolean important) {
        int i = Build.VERSION.SDK_INT;
        this.mInfo.setImportantForAccessibility(important);
    }

    public void setInputType(int inputType) {
        int i = Build.VERSION.SDK_INT;
        this.mInfo.setInputType(inputType);
    }

    public void setLabelFor(View labeled) {
        int i = Build.VERSION.SDK_INT;
        this.mInfo.setLabelFor(labeled);
    }

    public void setLabeledBy(View label) {
        int i = Build.VERSION.SDK_INT;
        this.mInfo.setLabeledBy(label);
    }

    public void setLiveRegion(int mode) {
        int i = Build.VERSION.SDK_INT;
        this.mInfo.setLiveRegion(mode);
    }

    public void setLongClickable(boolean longClickable) {
        this.mInfo.setLongClickable(longClickable);
    }

    public void setMaxTextLength(int max) {
        int i = Build.VERSION.SDK_INT;
        this.mInfo.setMaxTextLength(max);
    }

    public void setMovementGranularities(int granularities) {
        int i = Build.VERSION.SDK_INT;
        this.mInfo.setMovementGranularities(granularities);
    }

    public void setMultiLine(boolean multiLine) {
        int i = Build.VERSION.SDK_INT;
        this.mInfo.setMultiLine(multiLine);
    }

    public void setPackageName(CharSequence packageName) {
        this.mInfo.setPackageName(packageName);
    }

    public void setPaneTitle(@Nullable CharSequence paneTitle) {
        if (Build.VERSION.SDK_INT >= 28) {
            this.mInfo.setPaneTitle(paneTitle);
        } else {
            this.mInfo.getExtras().putCharSequence(PANE_TITLE_KEY, paneTitle);
        }
    }

    public void setParent(View parent) {
        this.mParentVirtualDescendantId = -1;
        this.mInfo.setParent(parent);
    }

    public void setPassword(boolean password) {
        this.mInfo.setPassword(password);
    }

    public void setRangeInfo(RangeInfoCompat rangeInfo) {
        int i = Build.VERSION.SDK_INT;
        this.mInfo.setRangeInfo((AccessibilityNodeInfo.RangeInfo) rangeInfo.mInfo);
    }

    public void setRoleDescription(@Nullable CharSequence roleDescription) {
        int i = Build.VERSION.SDK_INT;
        this.mInfo.getExtras().putCharSequence(ROLE_DESCRIPTION_KEY, roleDescription);
    }

    public void setScreenReaderFocusable(boolean screenReaderFocusable) {
        if (Build.VERSION.SDK_INT >= 28) {
            this.mInfo.setScreenReaderFocusable(screenReaderFocusable);
        } else {
            setBooleanProperty(1, screenReaderFocusable);
        }
    }

    public void setScrollable(boolean scrollable) {
        this.mInfo.setScrollable(scrollable);
    }

    public void setSelected(boolean selected) {
        this.mInfo.setSelected(selected);
    }

    public void setShowingHintText(boolean showingHintText) {
        int i = Build.VERSION.SDK_INT;
        this.mInfo.setShowingHintText(showingHintText);
    }

    public void setSource(View source) {
        this.mVirtualDescendantId = -1;
        this.mInfo.setSource(source);
    }

    public void setStateDescription(@Nullable CharSequence stateDescription) {
        if (BuildCompat.isAtLeastR()) {
            this.mInfo.setStateDescription(stateDescription);
        } else {
            int i = Build.VERSION.SDK_INT;
            this.mInfo.getExtras().putCharSequence(STATE_DESCRIPTION_KEY, stateDescription);
        }
    }

    public void setText(CharSequence text) {
        this.mInfo.setText(text);
    }

    public void setTextEntryKey(boolean isTextEntryKey) {
        if (Build.VERSION.SDK_INT >= 29) {
            this.mInfo.setTextEntryKey(isTextEntryKey);
        } else {
            setBooleanProperty(8, isTextEntryKey);
        }
    }

    public void setTextSelection(int start, int end) {
        int i = Build.VERSION.SDK_INT;
        this.mInfo.setTextSelection(start, end);
    }

    public void setTooltipText(@Nullable CharSequence tooltipText) {
        if (Build.VERSION.SDK_INT >= 28) {
            this.mInfo.setTooltipText(tooltipText);
        } else {
            this.mInfo.getExtras().putCharSequence(TOOLTIP_TEXT_KEY, tooltipText);
        }
    }

    public void setTouchDelegateInfo(@NonNull TouchDelegateInfoCompat delegatedInfo) {
        if (Build.VERSION.SDK_INT >= 29) {
            this.mInfo.setTouchDelegateInfo(delegatedInfo.mInfo);
        }
    }

    public void setTraversalAfter(View view) {
        int i = Build.VERSION.SDK_INT;
        this.mInfo.setTraversalAfter(view);
    }

    public void setTraversalBefore(View view) {
        int i = Build.VERSION.SDK_INT;
        this.mInfo.setTraversalBefore(view);
    }

    public void setViewIdResourceName(String viewId) {
        int i = Build.VERSION.SDK_INT;
        this.mInfo.setViewIdResourceName(viewId);
    }

    public void setVisibleToUser(boolean visibleToUser) {
        int i = Build.VERSION.SDK_INT;
        this.mInfo.setVisibleToUser(visibleToUser);
    }

    @NonNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        Rect rect = new Rect();
        getBoundsInParent(rect);
        sb.append("; boundsInParent: " + rect);
        getBoundsInScreen(rect);
        sb.append("; boundsInScreen: " + rect);
        sb.append("; packageName: ");
        sb.append(getPackageName());
        sb.append("; className: ");
        sb.append(getClassName());
        sb.append("; text: ");
        sb.append(getText());
        sb.append("; contentDescription: ");
        sb.append(getContentDescription());
        sb.append("; viewId: ");
        sb.append(getViewIdResourceName());
        sb.append("; checkable: ");
        sb.append(isCheckable());
        sb.append("; checked: ");
        sb.append(isChecked());
        sb.append("; focusable: ");
        sb.append(isFocusable());
        sb.append("; focused: ");
        sb.append(isFocused());
        sb.append("; selected: ");
        sb.append(isSelected());
        sb.append("; clickable: ");
        sb.append(isClickable());
        sb.append("; longClickable: ");
        sb.append(isLongClickable());
        sb.append("; enabled: ");
        sb.append(isEnabled());
        sb.append("; password: ");
        sb.append(isPassword());
        sb.append("; scrollable: " + isScrollable());
        sb.append("; [");
        int i = Build.VERSION.SDK_INT;
        List<AccessibilityActionCompat> actionList = getActionList();
        for (int i2 = 0; i2 < actionList.size(); i2++) {
            AccessibilityActionCompat accessibilityActionCompat = actionList.get(i2);
            String actionSymbolicName = getActionSymbolicName(accessibilityActionCompat.getId());
            if (actionSymbolicName.equals("ACTION_UNKNOWN") && accessibilityActionCompat.getLabel() != null) {
                actionSymbolicName = accessibilityActionCompat.getLabel().toString();
            }
            sb.append(actionSymbolicName);
            if (i2 != actionList.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public AccessibilityNodeInfo unwrap() {
        return this.mInfo;
    }

    public static class CollectionInfoCompat {
        public static final int SELECTION_MODE_MULTIPLE = 2;
        public static final int SELECTION_MODE_NONE = 0;
        public static final int SELECTION_MODE_SINGLE = 1;
        public final Object mInfo;

        public CollectionInfoCompat(Object info) {
            this.mInfo = info;
        }

        public static CollectionInfoCompat obtain(int rowCount, int columnCount, boolean hierarchical, int selectionMode) {
            int i = Build.VERSION.SDK_INT;
            return new CollectionInfoCompat(AccessibilityNodeInfo.CollectionInfo.obtain(rowCount, columnCount, hierarchical, selectionMode));
        }

        public int getColumnCount() {
            int i = Build.VERSION.SDK_INT;
            return ((AccessibilityNodeInfo.CollectionInfo) this.mInfo).getColumnCount();
        }

        public int getRowCount() {
            int i = Build.VERSION.SDK_INT;
            return ((AccessibilityNodeInfo.CollectionInfo) this.mInfo).getRowCount();
        }

        public int getSelectionMode() {
            int i = Build.VERSION.SDK_INT;
            return ((AccessibilityNodeInfo.CollectionInfo) this.mInfo).getSelectionMode();
        }

        public boolean isHierarchical() {
            int i = Build.VERSION.SDK_INT;
            return ((AccessibilityNodeInfo.CollectionInfo) this.mInfo).isHierarchical();
        }

        public static CollectionInfoCompat obtain(int rowCount, int columnCount, boolean hierarchical) {
            int i = Build.VERSION.SDK_INT;
            return new CollectionInfoCompat(AccessibilityNodeInfo.CollectionInfo.obtain(rowCount, columnCount, hierarchical));
        }
    }

    public static class CollectionItemInfoCompat {
        public final Object mInfo;

        public CollectionItemInfoCompat(Object info) {
            this.mInfo = info;
        }

        public static CollectionItemInfoCompat obtain(int rowIndex, int rowSpan, int columnIndex, int columnSpan, boolean heading, boolean selected) {
            int i = Build.VERSION.SDK_INT;
            return new CollectionItemInfoCompat(AccessibilityNodeInfo.CollectionItemInfo.obtain(rowIndex, rowSpan, columnIndex, columnSpan, heading, selected));
        }

        public int getColumnIndex() {
            int i = Build.VERSION.SDK_INT;
            return ((AccessibilityNodeInfo.CollectionItemInfo) this.mInfo).getColumnIndex();
        }

        public int getColumnSpan() {
            int i = Build.VERSION.SDK_INT;
            return ((AccessibilityNodeInfo.CollectionItemInfo) this.mInfo).getColumnSpan();
        }

        public int getRowIndex() {
            int i = Build.VERSION.SDK_INT;
            return ((AccessibilityNodeInfo.CollectionItemInfo) this.mInfo).getRowIndex();
        }

        public int getRowSpan() {
            int i = Build.VERSION.SDK_INT;
            return ((AccessibilityNodeInfo.CollectionItemInfo) this.mInfo).getRowSpan();
        }

        @Deprecated
        public boolean isHeading() {
            int i = Build.VERSION.SDK_INT;
            return ((AccessibilityNodeInfo.CollectionItemInfo) this.mInfo).isHeading();
        }

        public boolean isSelected() {
            int i = Build.VERSION.SDK_INT;
            return ((AccessibilityNodeInfo.CollectionItemInfo) this.mInfo).isSelected();
        }

        public static CollectionItemInfoCompat obtain(int rowIndex, int rowSpan, int columnIndex, int columnSpan, boolean heading) {
            int i = Build.VERSION.SDK_INT;
            return new CollectionItemInfoCompat(AccessibilityNodeInfo.CollectionItemInfo.obtain(rowIndex, rowSpan, columnIndex, columnSpan, heading));
        }
    }

    public void addAction(AccessibilityActionCompat action) {
        int i = Build.VERSION.SDK_INT;
        this.mInfo.addAction((AccessibilityNodeInfo.AccessibilityAction) action.mAction);
    }

    public void addChild(View root, int virtualDescendantId) {
        int i = Build.VERSION.SDK_INT;
        this.mInfo.addChild(root, virtualDescendantId);
    }

    public boolean performAction(int action, Bundle arguments) {
        int i = Build.VERSION.SDK_INT;
        return this.mInfo.performAction(action, arguments);
    }

    public static AccessibilityNodeInfoCompat obtain(View root, int virtualDescendantId) {
        int i = Build.VERSION.SDK_INT;
        return wrapNonNullInstance(AccessibilityNodeInfo.obtain(root, virtualDescendantId));
    }

    public boolean removeChild(View root, int virtualDescendantId) {
        int i = Build.VERSION.SDK_INT;
        return this.mInfo.removeChild(root, virtualDescendantId);
    }

    public void setLabelFor(View root, int virtualDescendantId) {
        int i = Build.VERSION.SDK_INT;
        this.mInfo.setLabelFor(root, virtualDescendantId);
    }

    public void setLabeledBy(View root, int virtualDescendantId) {
        int i = Build.VERSION.SDK_INT;
        this.mInfo.setLabeledBy(root, virtualDescendantId);
    }

    public void setParent(View root, int virtualDescendantId) {
        this.mParentVirtualDescendantId = virtualDescendantId;
        int i = Build.VERSION.SDK_INT;
        this.mInfo.setParent(root, virtualDescendantId);
    }

    public void setSource(View root, int virtualDescendantId) {
        this.mVirtualDescendantId = virtualDescendantId;
        int i = Build.VERSION.SDK_INT;
        this.mInfo.setSource(root, virtualDescendantId);
    }

    public void setTraversalAfter(View root, int virtualDescendantId) {
        int i = Build.VERSION.SDK_INT;
        this.mInfo.setTraversalAfter(root, virtualDescendantId);
    }

    public void setTraversalBefore(View root, int virtualDescendantId) {
        int i = Build.VERSION.SDK_INT;
        this.mInfo.setTraversalBefore(root, virtualDescendantId);
    }

    public static final class TouchDelegateInfoCompat {
        public final AccessibilityNodeInfo.TouchDelegateInfo mInfo;

        public TouchDelegateInfoCompat(@NonNull Map<Region, View> targetMap) {
            if (Build.VERSION.SDK_INT >= 29) {
                this.mInfo = new AccessibilityNodeInfo.TouchDelegateInfo(targetMap);
            } else {
                this.mInfo = null;
            }
        }

        @Nullable
        public Region getRegionAt(@IntRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_LONG) int index) {
            if (Build.VERSION.SDK_INT >= 29) {
                return this.mInfo.getRegionAt(index);
            }
            return null;
        }

        @IntRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_LONG)
        public int getRegionCount() {
            if (Build.VERSION.SDK_INT >= 29) {
                return this.mInfo.getRegionCount();
            }
            return 0;
        }

        @Nullable
        public AccessibilityNodeInfoCompat getTargetForRegion(@NonNull Region region) {
            AccessibilityNodeInfo targetForRegion;
            if (Build.VERSION.SDK_INT < 29 || (targetForRegion = this.mInfo.getTargetForRegion(region)) == null) {
                return null;
            }
            return new AccessibilityNodeInfoCompat(targetForRegion);
        }

        public TouchDelegateInfoCompat(@NonNull AccessibilityNodeInfo.TouchDelegateInfo info) {
            this.mInfo = info;
        }
    }

    public AccessibilityNodeInfoCompat(AccessibilityNodeInfo info) {
        this.mInfo = info;
    }

    public static AccessibilityNodeInfoCompat obtain() {
        return new AccessibilityNodeInfoCompat(AccessibilityNodeInfo.obtain());
    }

    public static AccessibilityNodeInfoCompat obtain(AccessibilityNodeInfoCompat info) {
        return new AccessibilityNodeInfoCompat(AccessibilityNodeInfo.obtain(info.mInfo));
    }
}
