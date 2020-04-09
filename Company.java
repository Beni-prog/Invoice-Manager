package Domain;

public class Company {
    private String name;
    private String  phoneNumber;

    public Company(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    private String getPhoneNumber() {
        return phoneNumber;
    }

    private void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public String toString() {
        return "\n"+ name +"-"+ phoneNumber ;
    }
}
