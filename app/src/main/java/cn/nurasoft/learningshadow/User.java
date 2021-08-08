package cn.nurasoft.learningshadow;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author hugo
 * @time 2021/8/2上午11:50
 * @project LearningShadow
 * Think Twice, Code Once!
 */
public class User implements Parcelable {
    private String name;

    protected User(Parcel in) {
        name = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }
}
