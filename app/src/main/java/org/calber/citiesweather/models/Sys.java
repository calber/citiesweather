
package org.calber.citiesweather.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sys implements Parcelable {

    @SerializedName("type")
    @Expose
    public Integer type;
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("message")
    @Expose
    public Double message;
    @SerializedName("country")
    @Expose
    public String country;
    @SerializedName("sunrise")
    @Expose
    public Integer sunrise;
    @SerializedName("sunset")
    @Expose
    public Integer sunset;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.type);
        dest.writeValue(this.id);
        dest.writeValue(this.message);
        dest.writeString(this.country);
        dest.writeValue(this.sunrise);
        dest.writeValue(this.sunset);
    }

    public Sys() {
    }

    protected Sys(Parcel in) {
        this.type = (Integer) in.readValue(Integer.class.getClassLoader());
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.message = (Double) in.readValue(Double.class.getClassLoader());
        this.country = in.readString();
        this.sunrise = (Integer) in.readValue(Integer.class.getClassLoader());
        this.sunset = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<Sys> CREATOR = new Parcelable.Creator<Sys>() {
        @Override
        public Sys createFromParcel(Parcel source) {
            return new Sys(source);
        }

        @Override
        public Sys[] newArray(int size) {
            return new Sys[size];
        }
    };
}
