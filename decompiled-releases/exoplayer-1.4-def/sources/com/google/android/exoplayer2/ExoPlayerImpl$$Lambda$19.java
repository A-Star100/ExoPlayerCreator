package com.google.android.exoplayer2;

import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.util.ListenerSet;

final /* synthetic */ class ExoPlayerImpl$$Lambda$19 implements ListenerSet.Event {
    private final PlaybackInfo a;

    ExoPlayerImpl$$Lambda$19(PlaybackInfo playbackInfo) {
        this.a = playbackInfo;
    }

    public final void a(Object obj) {
        ((Player.EventListener) obj).onPlaybackParametersChanged(this.a.n);
    }
}