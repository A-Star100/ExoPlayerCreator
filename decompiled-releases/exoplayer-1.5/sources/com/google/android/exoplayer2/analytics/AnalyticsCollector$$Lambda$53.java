package com.google.android.exoplayer2.analytics;

import com.google.android.exoplayer2.analytics.AnalyticsListener;
import com.google.android.exoplayer2.util.ListenerSet;

final /* synthetic */ class AnalyticsCollector$$Lambda$53 implements ListenerSet.Event {
    private final AnalyticsListener.EventTime a;
    private final int b;

    AnalyticsCollector$$Lambda$53(AnalyticsListener.EventTime eventTime, int i) {
        this.a = eventTime;
        this.b = i;
    }

    public final void a(Object obj) {
        AnalyticsCollector.a(this.a, this.b, (AnalyticsListener) obj);
    }
}