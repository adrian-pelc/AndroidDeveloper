package com.adrian.androiddeveloper.views.utils;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Adrian on 2016-02-27.
 */
public class FragmentParams implements Parcelable {

    public static final String FRAG_PARAMS_TAG = "com.adrian.androiddeveloper.views.utils.FRAG_PARAMS_TAG";

    private String mTutName;

    public FragmentParams(String tutName){
        mTutName = tutName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mTutName);
    }

    protected FragmentParams(Parcel in) {
        this.mTutName = in.readString();
    }

    public static final Parcelable.Creator<FragmentParams> CREATOR = new Parcelable.Creator<FragmentParams>() {
        public FragmentParams createFromParcel(Parcel source) {
            return new FragmentParams(source);
        }

        public FragmentParams[] newArray(int size) {
            return new FragmentParams[size];
        }
    };
}
