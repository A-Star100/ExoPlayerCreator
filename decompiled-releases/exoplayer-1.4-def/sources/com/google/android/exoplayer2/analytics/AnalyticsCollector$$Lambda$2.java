package com.google.android.exoplayer2.analytics;

import com.google.android.exoplayer2.analytics.AnalyticsListener;
import com.google.android.exoplayer2.util.ListenerSet;

final /* synthetic */ class AnalyticsCollector$$Lambda$2 implements ListenerSet.Event {
    AnalyticsCollector$$Lambda$2(AnalyticsListener.EventTime eventTime) {
    }

    public final void a(Object obj) {
        ((AnalyticsListener) obj).v();
    }
}