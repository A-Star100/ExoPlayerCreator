package com.google.android.exoplayer2.analytics;

import com.google.android.exoplayer2.analytics.AnalyticsListener;
import com.google.android.exoplayer2.util.ListenerSet;

final /* synthetic */ class AnalyticsCollector$$Lambda$56 implements ListenerSet.Event {
    private final AnalyticsListener.EventTime a;

    AnalyticsCollector$$Lambda$56(AnalyticsListener.EventTime eventTime) {
        this.a = eventTime;
    }

    public final void a(Object obj) {
        ((AnalyticsListener) obj).f(this.a);
    }
}