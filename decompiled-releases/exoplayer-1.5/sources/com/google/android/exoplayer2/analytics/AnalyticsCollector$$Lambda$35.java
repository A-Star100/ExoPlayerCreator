package com.google.android.exoplayer2.analytics;

import com.google.android.exoplayer2.analytics.AnalyticsListener;
import com.google.android.exoplayer2.util.ListenerSet;

final /* synthetic */ class AnalyticsCollector$$Lambda$35 implements ListenerSet.Event {
    private final AnalyticsListener.EventTime a;
    private final int b;

    AnalyticsCollector$$Lambda$35(AnalyticsListener.EventTime eventTime, int i) {
        this.a = eventTime;
        this.b = i;
    }

    public final void a(Object obj) {
        ((AnalyticsListener) obj).c(this.a, this.b);
    }
}