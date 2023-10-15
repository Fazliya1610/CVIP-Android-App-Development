package com.example.todo;

import android.os.Parcel;
import android.os.Parcelable;

public class Task implements Parcelable {
    private String title;
    private boolean isComplete;

    public Task(String title) {
        this.title = title;
        this.isComplete = false;
    }

    protected Task(Parcel in) {
        title = in.readString();
        isComplete = in.readByte() != 0;
    }

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(title);
        parcel.writeByte((byte) (isComplete ? 1 : 0));
    }
}
