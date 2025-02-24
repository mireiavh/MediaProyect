package com.mireia.mediaproyectt;

import android.os.Parcel;
import android.os.Parcelable;

public class MediaItem implements Parcelable {
    private String title;
    private int type; // 0: video, 1: audio, 2: web
    private String resource;

    public MediaItem(String title, int type, String resource) {
        this.title = title;
        this.type = type;
        this.resource = resource;
    }

    public String getTitle() {
        return title;
    }

    public int getType() {
        return type;
    }

    public String getResource() {
        return resource;
    }

    protected MediaItem(Parcel in) {
        title = in.readString();
        type = in.readInt();
        resource = in.readString();
    }

    public static final Creator<MediaItem> CREATOR = new Creator<MediaItem>() {
        @Override
        public MediaItem createFromParcel(Parcel in) {
            return new MediaItem(in);
        }

        @Override
        public MediaItem[] newArray(int size) {
            return new MediaItem[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeInt(type);
        dest.writeString(resource);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}