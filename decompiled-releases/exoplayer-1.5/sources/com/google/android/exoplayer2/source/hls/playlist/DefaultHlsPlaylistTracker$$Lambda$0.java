package com.google.android.exoplayer2.source.hls.playlist;

import com.google.android.exoplayer2.source.hls.HlsDataSourceFactory;
import com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistTracker;
import com.google.android.exoplayer2.upstream.LoadErrorHandlingPolicy;

final /* synthetic */ class DefaultHlsPlaylistTracker$$Lambda$0 implements HlsPlaylistTracker.Factory {
    static final HlsPlaylistTracker.Factory a = new DefaultHlsPlaylistTracker$$Lambda$0();

    private DefaultHlsPlaylistTracker$$Lambda$0() {
    }

    public final HlsPlaylistTracker a(HlsDataSourceFactory hlsDataSourceFactory, LoadErrorHandlingPolicy loadErrorHandlingPolicy, HlsPlaylistParserFactory hlsPlaylistParserFactory) {
        return new DefaultHlsPlaylistTracker(hlsDataSourceFactory, loadErrorHandlingPolicy, hlsPlaylistParserFactory);
    }
}
