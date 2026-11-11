package com.lagradost.cloudstream3.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDragHandleView;
import com.google.android.material.button.MaterialButton;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.databinding.BottomInputDialogBinding;
import com.lagradost.cloudstream3.databinding.BottomSelectionDialogBinding;
import com.lagradost.cloudstream3.databinding.BottomTextDialogBinding;
import com.lagradost.cloudstream3.ui.settings.Globals;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: SingleSelectionHelper.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J^\u0010\u0004\u001a\u00020\u0005*\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u001e\u0010\u000f\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\r0\u0011\u0012\u0004\u0012\u00020\u00050\u0010J^\u0010\u0013\u001a\u00020\u0005*\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\f2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\n0\f2\u001e\u0010\u000f\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\r0\u0011\u0012\u0004\u0012\u00020\u00050\u0010H\u0002J\u0082\u0001\u0010\u0014\u001a\u00020\u0005*\u0004\u0018\u00010\u00062\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\n0\f2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u001b\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00020\u00122\u0006\u0010\u001d\u001a\u00020\u00122\u0018\u0010\u000f\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f\u0012\u0004\u0012\u00020\u00050\u00102\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00050\u001f2\b\b\u0002\u0010 \u001a\u00020\rJ_\u0010!\u001a\u00020\u0005*\u0004\u0018\u00010\u00062\u0006\u0010\u0015\u001a\u00020\"2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010#\u001a\u00020\n2\u0006\u0010\u001b\u001a\u00020\n2\b\u0010$\u001a\u0004\u0018\u00010\r2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00050\u00102\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00050\u001fH\u0002¢\u0006\u0002\u0010%JX\u0010&\u001a\u00020\u0005*\u0004\u0018\u00010\u00062\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\n0\f2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u001b\u001a\u00020\n2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00050\u001f2\u0018\u0010\u000f\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f\u0012\u0004\u0012\u00020\u00050\u0010JT\u0010\u0014\u001a\u00020\u0005*\u0004\u0018\u00010\u00062\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\n0\f2\u0006\u0010\u001a\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00020\u00122\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00050\u001f2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00050\u0010JT\u0010'\u001a\u00020\u0005*\u0004\u0018\u00010\u00062\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\n0\f2\u0006\u0010\u001a\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00020\u00122\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00050\u001f2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00050\u0010JB\u0010(\u001a\u00020)*\u00020\u00062\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\n0\f2\u0006\u0010\u001b\u001a\u00020\n2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00050\u001f2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00050\u0010JK\u0010*\u001a\u00020\u0005*\u00020\u00062\u0006\u0010\u001b\u001a\u00020\n2\u0006\u0010#\u001a\u00020\n2\b\u0010$\u001a\u0004\u0018\u00010\r2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00050\u001f2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00050\u0010¢\u0006\u0002\u0010+J(\u0010,\u001a\u00020\u0005*\u00020\u00062\u0006\u0010-\u001a\u00020\n2\u0006\u0010.\u001a\u00020/2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00050\u001f¨\u00060"}, d2 = {"Lcom/lagradost/cloudstream3/utils/SingleSelectionHelper;", "", "<init>", "()V", "showOptionSelectStringRes", "", "Landroid/app/Activity;", "view", "Landroid/view/View;", "poster", "", "options", "", "", "tvOptions", "callback", "Lkotlin/Function1;", "Lkotlin/Pair;", "", "showOptionSelect", "showDialog", "binding", "Lcom/lagradost/cloudstream3/databinding/BottomSelectionDialogBinding;", "dialog", "Landroid/app/Dialog;", "items", "selectedIndex", "name", "showApply", "isMultiSelect", "dismissCallback", "Lkotlin/Function0;", "itemLayout", "showInputDialog", "Lcom/lagradost/cloudstream3/databinding/BottomInputDialogBinding;", ES6Iterator.VALUE_PROPERTY, "textInputType", "(Landroid/app/Activity;Lcom/lagradost/cloudstream3/databinding/BottomInputDialogBinding;Landroid/app/Dialog;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;)V", "showMultiDialog", "showBottomDialog", "showBottomDialogInstant", "Lcom/google/android/material/bottomsheet/BottomSheetDialog;", "showNginxTextInputDialog", "(Landroid/app/Activity;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;)V", "showBottomDialogText", "title", "text", "Landroid/text/Spanned;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class SingleSelectionHelper {
    public static final SingleSelectionHelper INSTANCE = new SingleSelectionHelper();

    private SingleSelectionHelper() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void showOptionSelectStringRes$default(SingleSelectionHelper singleSelectionHelper, Activity activity, View view, String str, List list, List list2, Function1 function1, int i, Object obj) {
        if ((i & 8) != 0) {
            list2 = CollectionsKt.emptyList();
        }
        singleSelectionHelper.showOptionSelectStringRes(activity, view, str, list, list2, function1);
    }

    public final void showOptionSelectStringRes(Activity activity, View view, String str, List<Integer> options, List<Integer> tvOptions, Function1<? super Pair<Boolean, Integer>, Unit> callback) {
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.checkNotNullParameter(tvOptions, "tvOptions");
        Intrinsics.checkNotNullParameter(callback, "callback");
        if (activity == null) {
            return;
        }
        List<Integer> list = options;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(activity.getString(((Number) it.next()).intValue()));
        }
        ArrayList arrayList2 = arrayList;
        List<Integer> list2 = tvOptions;
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        Iterator<T> it2 = list2.iterator();
        while (it2.hasNext()) {
            arrayList3.add(activity.getString(((Number) it2.next()).intValue()));
        }
        showOptionSelect(activity, view, str, arrayList2, arrayList3, callback);
    }

    private final void showOptionSelect(Activity activity, View view, String str, List<String> list, List<String> list2, final Function1<? super Pair<Boolean, Integer>, Unit> function1) {
        if (activity == null || view == null) {
            return;
        }
        UIHelper uIHelper = UIHelper.INSTANCE;
        List<String> list3 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list3, 10));
        int i = 0;
        for (Object obj : list3) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            arrayList.add(new Pair(Integer.valueOf(i), (String) obj));
            i = i2;
        }
        uIHelper.popupMenuNoIconsAndNoStringRes(view, arrayList, new Function1() { // from class: com.lagradost.cloudstream3.utils.SingleSelectionHelper$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                return SingleSelectionHelper.showOptionSelect$lambda$1(function1, (MenuItem) obj2);
            }
        });
    }

    static final Unit showOptionSelect$lambda$1(Function1 function1, MenuItem popupMenuNoIconsAndNoStringRes) {
        Intrinsics.checkNotNullParameter(popupMenuNoIconsAndNoStringRes, "$this$popupMenuNoIconsAndNoStringRes");
        function1.invoke(new Pair(false, Integer.valueOf(popupMenuNoIconsAndNoStringRes.getItemId())));
        return Unit.INSTANCE;
    }

    public static /* synthetic */ void showDialog$default(SingleSelectionHelper singleSelectionHelper, Activity activity, BottomSelectionDialogBinding bottomSelectionDialogBinding, Dialog dialog, List list, List list2, String str, boolean z, boolean z2, Function1 function1, Function0 function0, int i, int i2, Object obj) {
        singleSelectionHelper.showDialog(activity, bottomSelectionDialogBinding, dialog, list, list2, str, z, z2, function1, function0, (i2 & 512) != 0 ? R.layout.sort_bottom_single_choice : i);
    }

    public final void showDialog(final Activity activity, BottomSelectionDialogBinding binding, final Dialog dialog, List<String> items, List<Integer> selectedIndex, String name, boolean z, final boolean z2, final Function1<? super List<Integer>, Unit> callback, final Function0<Unit> dismissCallback, int i) {
        Intrinsics.checkNotNullParameter(binding, "binding");
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        Intrinsics.checkNotNullParameter(items, "items");
        Intrinsics.checkNotNullParameter(selectedIndex, "selectedIndex");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(callback, "callback");
        Intrinsics.checkNotNullParameter(dismissCallback, "dismissCallback");
        if (activity == null) {
            return;
        }
        final boolean z3 = z || z2;
        final ListView listview1 = binding.listview1;
        Intrinsics.checkNotNullExpressionValue(listview1, "listview1");
        TextView text1 = binding.text1;
        Intrinsics.checkNotNullExpressionValue(text1, "text1");
        MaterialButton applyBtt = binding.applyBtt;
        Intrinsics.checkNotNullExpressionValue(applyBtt, "applyBtt");
        MaterialButton cancelBtt = binding.cancelBtt;
        Intrinsics.checkNotNullExpressionValue(cancelBtt, "cancelBtt");
        LinearLayout applyBttHolder = binding.applyBttHolder;
        Intrinsics.checkNotNullExpressionValue(applyBttHolder, "applyBttHolder");
        if (Globals.INSTANCE.isLayout(5) && (dialog instanceof BottomSheetDialog)) {
            BottomSheetDragHandleView dragHandle = binding.dragHandle;
            Intrinsics.checkNotNullExpressionValue(dragHandle, "dragHandle");
            dragHandle.setVisibility(0);
            listview1.setNestedScrollingEnabled(true);
        }
        applyBttHolder.setVisibility(z3 ? 0 : 8);
        if (!z3) {
            ViewGroup.LayoutParams layoutParams = listview1.getLayoutParams();
            Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type android.widget.LinearLayout.LayoutParams");
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
            ListView listView = listview1;
            ViewGroup.LayoutParams layoutParams3 = listView.getLayoutParams();
            ViewGroup.MarginLayoutParams marginLayoutParams = layoutParams3 instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams3 : null;
            int i2 = marginLayoutParams != null ? marginLayoutParams.leftMargin : 0;
            ViewGroup.LayoutParams layoutParams4 = listView.getLayoutParams();
            ViewGroup.MarginLayoutParams marginLayoutParams2 = layoutParams4 instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams4 : null;
            int i3 = marginLayoutParams2 != null ? marginLayoutParams2.topMargin : 0;
            ViewGroup.LayoutParams layoutParams5 = listView.getLayoutParams();
            ViewGroup.MarginLayoutParams marginLayoutParams3 = layoutParams5 instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams5 : null;
            layoutParams2.setMargins(i2, i3, marginLayoutParams3 != null ? marginLayoutParams3.rightMargin : 0, 0);
            listview1.setLayoutParams(layoutParams2);
        }
        String str = name;
        text1.setText(str);
        text1.setVisibility(StringsKt.isBlank(str) ? 8 : 0);
        ArrayAdapter arrayAdapter = new ArrayAdapter(activity, i);
        arrayAdapter.addAll(items);
        listview1.setAdapter((ListAdapter) arrayAdapter);
        if (z2) {
            listview1.setChoiceMode(2);
        } else {
            listview1.setChoiceMode(1);
        }
        Iterator<Integer> it = selectedIndex.iterator();
        while (it.hasNext()) {
            listview1.setItemChecked(it.next().intValue(), true);
        }
        Integer num = (Integer) CollectionsKt.minOrNull((Iterable) selectedIndex);
        if (num != null) {
            listview1.setSelection(num.intValue());
        }
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.lagradost.cloudstream3.utils.SingleSelectionHelper$$ExternalSyntheticLambda3
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                dismissCallback.invoke();
            }
        });
        listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.lagradost.cloudstream3.utils.SingleSelectionHelper$$ExternalSyntheticLambda4
            @Override // android.widget.AdapterView.OnItemClickListener
            public final void onItemClick(AdapterView adapterView, View view, int i4, long j) {
                SingleSelectionHelper.showDialog$lambda$2(z3, z2, listview1, callback, dialog, activity, adapterView, view, i4, j);
            }
        });
        if (z3) {
            applyBtt.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.utils.SingleSelectionHelper$$ExternalSyntheticLambda5
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SingleSelectionHelper.showDialog$lambda$3(listview1, callback, dialog, activity, view);
                }
            });
            cancelBtt.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.utils.SingleSelectionHelper$$ExternalSyntheticLambda6
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    UIHelper.INSTANCE.dismissSafe(dialog, activity);
                }
            });
        }
    }

    static final void showDialog$lambda$2(boolean z, boolean z2, ListView listView, Function1 function1, Dialog dialog, Activity activity, AdapterView adapterView, View view, int i, long j) {
        if (!z) {
            function1.invoke(CollectionsKt.listOf(Integer.valueOf(i)));
            UIHelper.INSTANCE.dismissSafe(dialog, activity);
        } else {
            if (z2) {
                return;
            }
            listView.setItemChecked(i, true);
        }
    }

    static final void showDialog$lambda$3(ListView listView, Function1 function1, Dialog dialog, Activity activity, View view) {
        ArrayList arrayList = new ArrayList();
        int count = listView.getCount();
        for (int i = 0; i < count; i++) {
            if (listView.getCheckedItemPositions().get(i)) {
                arrayList.add(Integer.valueOf(i));
            }
        }
        function1.invoke(arrayList);
        UIHelper.INSTANCE.dismissSafe(dialog, activity);
    }

    private final void showInputDialog(final Activity activity, BottomInputDialogBinding bottomInputDialogBinding, final Dialog dialog, String str, String str2, Integer num, final Function1<? super String, Unit> function1, final Function0<Unit> function0) {
        if (activity == null) {
            return;
        }
        final EditText nginxTextInput = bottomInputDialogBinding.nginxTextInput;
        Intrinsics.checkNotNullExpressionValue(nginxTextInput, "nginxTextInput");
        TextView text1 = bottomInputDialogBinding.text1;
        Intrinsics.checkNotNullExpressionValue(text1, "text1");
        MaterialButton applyBtt = bottomInputDialogBinding.applyBtt;
        Intrinsics.checkNotNullExpressionValue(applyBtt, "applyBtt");
        MaterialButton cancelBtt = bottomInputDialogBinding.cancelBtt;
        Intrinsics.checkNotNullExpressionValue(cancelBtt, "cancelBtt");
        LinearLayout applyBttHolder = bottomInputDialogBinding.applyBttHolder;
        Intrinsics.checkNotNullExpressionValue(applyBttHolder, "applyBttHolder");
        applyBttHolder.setVisibility(0);
        text1.setText(str2);
        if (num != null) {
            nginxTextInput.setInputType(num.intValue());
        }
        nginxTextInput.setText(str, TextView.BufferType.EDITABLE);
        applyBtt.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.utils.SingleSelectionHelper$$ExternalSyntheticLambda7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SingleSelectionHelper.showInputDialog$lambda$0(function1, nginxTextInput, dialog, activity, view);
            }
        });
        cancelBtt.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.utils.SingleSelectionHelper$$ExternalSyntheticLambda8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                UIHelper.INSTANCE.dismissSafe(dialog, activity);
            }
        });
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.lagradost.cloudstream3.utils.SingleSelectionHelper$$ExternalSyntheticLambda9
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                function0.invoke();
            }
        });
    }

    static final void showInputDialog$lambda$0(Function1 function1, EditText editText, Dialog dialog, Activity activity, View view) {
        function1.invoke(editText.getText().toString());
        UIHelper.INSTANCE.dismissSafe(dialog, activity);
    }

    public final void showMultiDialog(Activity activity, List<String> items, List<Integer> selectedIndex, String name, Function0<Unit> dismissCallback, Function1<? super List<Integer>, Unit> callback) {
        Intrinsics.checkNotNullParameter(items, "items");
        Intrinsics.checkNotNullParameter(selectedIndex, "selectedIndex");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(dismissCallback, "dismissCallback");
        Intrinsics.checkNotNullParameter(callback, "callback");
        if (activity == null) {
            return;
        }
        Activity activity2 = activity;
        BottomSelectionDialogBinding bottomSelectionDialogBindingInflate = BottomSelectionDialogBinding.inflate(LayoutInflater.from(activity2));
        Intrinsics.checkNotNullExpressionValue(bottomSelectionDialogBindingInflate, "inflate(...)");
        AlertDialog alertDialogCreate = new AlertDialog.Builder(activity2, R.style.AlertDialogCustom).setView(bottomSelectionDialogBindingInflate.getRoot()).create();
        Intrinsics.checkNotNullExpressionValue(alertDialogCreate, "create(...)");
        alertDialogCreate.show();
        showDialog$default(this, activity, bottomSelectionDialogBindingInflate, alertDialogCreate, items, selectedIndex, name, true, true, callback, dismissCallback, 0, 512, null);
    }

    public final void showDialog(Activity activity, List<String> items, int i, String name, boolean z, Function0<Unit> dismissCallback, final Function1<? super Integer, Unit> callback) {
        Intrinsics.checkNotNullParameter(items, "items");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(dismissCallback, "dismissCallback");
        Intrinsics.checkNotNullParameter(callback, "callback");
        if (activity == null) {
            return;
        }
        Activity activity2 = activity;
        BottomSelectionDialogBinding bottomSelectionDialogBindingInflate = BottomSelectionDialogBinding.inflate(LayoutInflater.from(activity2));
        Intrinsics.checkNotNullExpressionValue(bottomSelectionDialogBindingInflate, "inflate(...)");
        AlertDialog alertDialogCreate = new AlertDialog.Builder(activity2, R.style.AlertDialogCustom).setView(bottomSelectionDialogBindingInflate.getRoot()).create();
        Intrinsics.checkNotNullExpressionValue(alertDialogCreate, "create(...)");
        alertDialogCreate.show();
        showDialog$default(this, activity, bottomSelectionDialogBindingInflate, alertDialogCreate, items, CollectionsKt.listOf(Integer.valueOf(i)), name, z, false, new Function1() { // from class: com.lagradost.cloudstream3.utils.SingleSelectionHelper$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SingleSelectionHelper.showDialog$lambda$5(callback, (List) obj);
            }
        }, dismissCallback, 0, 512, null);
    }

    static final Unit showDialog$lambda$5(Function1 function1, List it) {
        Intrinsics.checkNotNullParameter(it, "it");
        if (!it.isEmpty()) {
            function1.invoke(CollectionsKt.first(it));
        }
        return Unit.INSTANCE;
    }

    public final void showBottomDialog(Activity activity, List<String> items, int i, String name, boolean z, Function0<Unit> dismissCallback, final Function1<? super Integer, Unit> callback) {
        Intrinsics.checkNotNullParameter(items, "items");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(dismissCallback, "dismissCallback");
        Intrinsics.checkNotNullParameter(callback, "callback");
        if (activity == null) {
            return;
        }
        Activity activity2 = activity;
        BottomSelectionDialogBinding bottomSelectionDialogBindingInflate = BottomSelectionDialogBinding.inflate(LayoutInflater.from(activity2));
        Intrinsics.checkNotNullExpressionValue(bottomSelectionDialogBindingInflate, "inflate(...)");
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(activity2);
        bottomSheetDialog.setContentView(bottomSelectionDialogBindingInflate.getRoot());
        bottomSheetDialog.show();
        showDialog$default(this, activity, bottomSelectionDialogBindingInflate, bottomSheetDialog, items, CollectionsKt.listOf(Integer.valueOf(i)), name, z, false, new Function1() { // from class: com.lagradost.cloudstream3.utils.SingleSelectionHelper$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SingleSelectionHelper.showBottomDialog$lambda$0(callback, (List) obj);
            }
        }, dismissCallback, 0, 512, null);
    }

    static final Unit showBottomDialog$lambda$0(Function1 function1, List it) {
        Intrinsics.checkNotNullParameter(it, "it");
        if (!it.isEmpty()) {
            function1.invoke(CollectionsKt.first(it));
        }
        return Unit.INSTANCE;
    }

    public final BottomSheetDialog showBottomDialogInstant(Activity activity, List<String> items, String name, Function0<Unit> dismissCallback, final Function1<? super Integer, Unit> callback) {
        Intrinsics.checkNotNullParameter(activity, "<this>");
        Intrinsics.checkNotNullParameter(items, "items");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(dismissCallback, "dismissCallback");
        Intrinsics.checkNotNullParameter(callback, "callback");
        Activity activity2 = activity;
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(activity2);
        BottomSelectionDialogBinding bottomSelectionDialogBindingInflate = BottomSelectionDialogBinding.inflate(LayoutInflater.from(activity2));
        Intrinsics.checkNotNullExpressionValue(bottomSelectionDialogBindingInflate, "inflate(...)");
        bottomSheetDialog.setContentView(bottomSelectionDialogBindingInflate.getRoot());
        bottomSheetDialog.show();
        showDialog(activity, bottomSelectionDialogBindingInflate, bottomSheetDialog, items, CollectionsKt.emptyList(), name, false, false, new Function1() { // from class: com.lagradost.cloudstream3.utils.SingleSelectionHelper$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SingleSelectionHelper.showBottomDialogInstant$lambda$0(callback, (List) obj);
            }
        }, dismissCallback, R.layout.sort_bottom_single_choice_no_checkmark);
        return bottomSheetDialog;
    }

    static final Unit showBottomDialogInstant$lambda$0(Function1 function1, List it) {
        Intrinsics.checkNotNullParameter(it, "it");
        if (!it.isEmpty()) {
            function1.invoke(CollectionsKt.first(it));
        }
        return Unit.INSTANCE;
    }

    public final void showNginxTextInputDialog(Activity activity, String name, String value, Integer num, Function0<Unit> dismissCallback, Function1<? super String, Unit> callback) {
        Intrinsics.checkNotNullParameter(activity, "<this>");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(dismissCallback, "dismissCallback");
        Intrinsics.checkNotNullParameter(callback, "callback");
        Activity activity2 = activity;
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(activity2);
        BottomInputDialogBinding bottomInputDialogBindingInflate = BottomInputDialogBinding.inflate(LayoutInflater.from(activity2));
        Intrinsics.checkNotNullExpressionValue(bottomInputDialogBindingInflate, "inflate(...)");
        bottomSheetDialog.setContentView(bottomInputDialogBindingInflate.getRoot());
        bottomSheetDialog.show();
        showInputDialog(activity, bottomInputDialogBindingInflate, bottomSheetDialog, value, name, num, callback, dismissCallback);
    }

    public final void showBottomDialogText(Activity activity, String title, Spanned text, final Function0<Unit> dismissCallback) {
        Intrinsics.checkNotNullParameter(activity, "<this>");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(dismissCallback, "dismissCallback");
        BottomTextDialogBinding bottomTextDialogBindingInflate = BottomTextDialogBinding.inflate(activity.getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(bottomTextDialogBindingInflate, "inflate(...)");
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(activity);
        bottomSheetDialog.setContentView(bottomTextDialogBindingInflate.getRoot());
        bottomTextDialogBindingInflate.dialogTitle.setText(title);
        bottomTextDialogBindingInflate.dialogText.setText(text);
        bottomSheetDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.lagradost.cloudstream3.utils.SingleSelectionHelper$$ExternalSyntheticLambda2
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                dismissCallback.invoke();
            }
        });
        bottomSheetDialog.show();
    }
}
