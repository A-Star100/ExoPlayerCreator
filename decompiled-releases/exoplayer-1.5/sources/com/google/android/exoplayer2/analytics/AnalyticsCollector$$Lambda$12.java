package com.google.android.exoplayer2.analytics;

import com.google.android.exoplayer2.analytics.AnalyticsListener;
import com.google.android.exoplayer2.util.ListenerSet;

final /* synthetic */ class AnalyticsCollector$$Lambda$12 implements ListenerSet.Event {
    private final AnalyticsListener.EventTime a;
    private final boolean b;

    AnalyticsCollector$$Lambda$12(AnalyticsListener.EventTime eventTime, boolean z) {
        this.a = eventTime;
        this.b = z;
    }

    public final void a(Object obj) {
        ((AnalyticsListener) obj).d(this.a, this.b);
    }
}
