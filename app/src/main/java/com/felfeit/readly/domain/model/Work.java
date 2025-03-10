package com.felfeit.readly.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Work implements Parcelable {
    private final String title, key, coverUrl, authorName;
    private final int firstPublishYear, editionCount;

    public Work(String title, String authorName, int firstPublishYear, String key, String coverUrl, int editionCount) {
        this.title = title;
        this.authorName = authorName;
        this.firstPublishYear = firstPublishYear;
        this.key = key;
        this.coverUrl = coverUrl;
        this.editionCount = editionCount;
    }

    protected Work(Parcel in) {
        title = in.readString();
        key = in.readString();
        coverUrl = in.readString();
        authorName = in.readString();
        firstPublishYear = in.readInt();
        editionCount = in.readInt();
    }

    public static final Creator<Work> CREATOR = new Creator<Work>() {
        @Override
        public Work createFromParcel(Parcel in) {
            return new Work(in);
        }

        @Override
        public Work[] newArray(int size) {
            return new Work[size];
        }
    };

    public int getFirstPublishYear() {
        return firstPublishYear;
    }

    public String getKey() {
        return key;
    }


    public String getTitle() {
        return title;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public String getAuthorName() {
        return authorName;
    }

    public int getEditionCount() {
        return editionCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(key);
        parcel.writeString(coverUrl);
        parcel.writeString(authorName);
        parcel.writeInt(firstPublishYear);
        parcel.writeInt(editionCount);
    }
}
