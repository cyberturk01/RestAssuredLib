package pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Address {

    @SerializedName("street")
    @Expose
    private String street;
    @SerializedName("flat_no")
    @Expose
    private String flatNo;
    @SerializedName("pincode")
    @Expose
    private Integer pincode;
    @SerializedName("type")
    @Expose
    private String type;

    /**
     * No args constructor for use in serialization
     *
     */
    public Address() {
    }

    /**
     *
     * @param pincode
     * @param flatNo
     * @param street
     * @param type
     */
    public Address(String street, String flatNo, Integer pincode, String type) {
        super();
        this.street = street;
        this.flatNo = flatNo;
        this.pincode = pincode;
        this.type = type;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getFlatNo() {
        return flatNo;
    }

    public void setFlatNo(String flatNo) {
        this.flatNo = flatNo;
    }

    public Integer getPincode() {
        return pincode;
    }

    public void setPincode(Integer pincode) {
        this.pincode = pincode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}