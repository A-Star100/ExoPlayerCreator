package com.google.android.exoplayer2.metadata;

import com.google.android.exoplayer2.decoder.DecoderInputBuffer;

public final class MetadataInputBuffer extends DecoderInputBuffer {
    public long g;

    public MetadataInputBuffer() {
        super(1);
    }
}