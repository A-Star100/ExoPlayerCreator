package com.google.android.exoplayer2.audio;

import com.google.android.exoplayer2.audio.AudioRendererEventListener;
import com.google.android.exoplayer2.util.Util;

final /* synthetic */ class AudioRendererEventListener$EventDispatcher$$Lambda$5 implements Runnable {
    private final AudioRendererEventListener.EventDispatcher a;
    private final String b;

    AudioRendererEventListener$EventDispatcher$$Lambda$5(AudioRendererEventListener.EventDispatcher eventDispatcher, String str) {
        this.a = eventDispatcher;
        this.b = str;
    }

    public final void run() {
        AudioRendererEventListener.EventDispatcher eventDispatcher = this.a;
        ((AudioRendererEventListener) Util.a((Object) eventDispatcher.b)).b(this.b);
    }
}