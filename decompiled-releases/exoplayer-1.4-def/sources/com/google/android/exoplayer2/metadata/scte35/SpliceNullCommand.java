package com.google.android.exoplayer2.metadata.scte35;

import android.os.Parcel;
import android.os.Parcelable;

public final class SpliceNullCommand extends SpliceCommand {
    static {
        new Parcelable.Creator() {
            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return new SpliceNullCommand();
            }

            public /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new SpliceNullCommand[i];
            }
        };
    }

    public final void writeToParcel(Parcel parcel, int i) {
    }
}
