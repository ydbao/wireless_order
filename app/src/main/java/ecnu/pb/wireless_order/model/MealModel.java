package ecnu.pb.wireless_order.model;

import android.os.Parcel;
import android.os.Parcelable;

public class MealModel implements Parcelable{

    private int meal_id;
    private int meal_type;
    private String meal_name;
    private int meal_price;
    private String meal_image_url;
    private String meal_intro;

    public int getMeal_id() {
        return meal_id;
    }

    public int getMeal_type() {
        return meal_type;
    }

    public String getMeal_name() {
        return meal_name;
    }

    public int getMeal_price() {
        return meal_price;
    }

    public String getMeal_image_url() {
        return meal_image_url;
    }

    public String getMeal_intro() {
        return meal_intro;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(meal_id);
        dest.writeInt(meal_type);
        dest.writeString(meal_name);
        dest.writeInt(meal_price);
        dest.writeString(meal_image_url);
        dest.writeString(meal_intro);
    }
}
