package com.lagradost.cloudstream3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.button.MaterialButton;
import com.lagradost.cloudstream3.R;

/* JADX INFO: loaded from: classes6.dex */
public final class ViewTestBinding implements ViewBinding {
    public final LinearLayout failedTestSection;
    public final TextView failedTestSectionProgress;
    public final TextView mainTestHeader;
    public final LinearLayout mainTestSection;
    public final TextView mainTestSectionProgress;
    public final LinearLayout passedTestSection;
    public final TextView passedTestSectionProgress;
    private final CardView rootView;
    public final ContentLoadingProgressBar testTotalProgress;
    public final MaterialButton testsPlayPause;

    private ViewTestBinding(CardView cardView, LinearLayout linearLayout, TextView textView, TextView textView2, LinearLayout linearLayout2, TextView textView3, LinearLayout linearLayout3, TextView textView4, ContentLoadingProgressBar contentLoadingProgressBar, MaterialButton materialButton) {
        this.rootView = cardView;
        this.failedTestSection = linearLayout;
        this.failedTestSectionProgress = textView;
        this.mainTestHeader = textView2;
        this.mainTestSection = linearLayout2;
        this.mainTestSectionProgress = textView3;
        this.passedTestSection = linearLayout3;
        this.passedTestSectionProgress = textView4;
        this.testTotalProgress = contentLoadingProgressBar;
        this.testsPlayPause = materialButton;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CardView getRoot() {
        return this.rootView;
    }

    public static ViewTestBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ViewTestBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.view_test, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ViewTestBinding bind(View view) {
        int i = R.id.failed_test_section;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
        if (linearLayout != null) {
            i = R.id.failed_test_section_progress;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
            if (textView != null) {
                i = R.id.main_test_header;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                if (textView2 != null) {
                    i = R.id.main_test_section;
                    LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                    if (linearLayout2 != null) {
                        i = R.id.main_test_section_progress;
                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, i);
                        if (textView3 != null) {
                            i = R.id.passed_test_section;
                            LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                            if (linearLayout3 != null) {
                                i = R.id.passed_test_section_progress;
                                TextView textView4 = (TextView) ViewBindings.findChildViewById(view, i);
                                if (textView4 != null) {
                                    i = R.id.test_total_progress;
                                    ContentLoadingProgressBar contentLoadingProgressBar = (ContentLoadingProgressBar) ViewBindings.findChildViewById(view, i);
                                    if (contentLoadingProgressBar != null) {
                                        i = R.id.tests_play_pause;
                                        MaterialButton materialButton = (MaterialButton) ViewBindings.findChildViewById(view, i);
                                        if (materialButton != null) {
                                            return new ViewTestBinding((CardView) view, linearLayout, textView, textView2, linearLayout2, textView3, linearLayout3, textView4, contentLoadingProgressBar, materialButton);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
