package user;
public class LandLord extends UserBase {
     private String phone;
     private String propertyName;
        private String email;
public LandLord(String Id, String Username, String Password) {
        super(Id, Username, Password);
        setPhone(phone);
        setEmail(email);
    }
public LandLord(UserBase u1, String phone, String email) {
        super(u1.getID(), u1.getUserName(), u1.getPassword());
        setPhone(phone);
        setEmail(email);
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if(phone == null || phone.length() < 8) {
            System.out.println("Invalid phone number");
            this.phone = "00000000";
        } else {
            this.phone = phone;
        }
    }
       public String getEmail() { return email; }
    public void setEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            this.email = "Unknown Email";
        } else {
            this.email = email.trim();
        }
    }
@Override 
public String getRole() {
    return "LandLord";
}
@Override
public boolean can(String action) {
        return true;
}
     @Override
    public String toString() {
        return super.toString() + ", phone='" + phone + "', email='" + email + "'";
    }
}