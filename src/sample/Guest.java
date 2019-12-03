package sample;

public class Guest {
    private int guestId;
    private String firstName;
    private String lastName;
    private String streetAndNumber;
    private String zip;
    private String domicile;
    private String country;
    private String phone;
    private String mobile;
    private String email;

    public Guest(int guestId, String firstName, String lastName, String streetAndNumber, String zip, String domicile, String country, String phone, String mobile, String email) {
        this.guestId = guestId;
        this.firstName = firstName;
        this. lastName = lastName;
        this.streetAndNumber = streetAndNumber;
        this.zip = zip;
        this.domicile = domicile;
        this.country = country;
        this.phone = phone;
        this.mobile = mobile;
        this.email = email;
    }

    public int getGuestId() {
        return guestId;
    }

    public String outputString() { return "Guest " + firstName + " " + lastName + " lives in " + streetAndNumber + " " + zip + " " + domicile + " " + country + " and has phone number " + phone + " and mobile number " + mobile + " and email " + email; }
}
