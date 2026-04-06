package user;
public class LandLord extends UserBase {
     private String phone;
    private String email;
public LandLord(String Id, String Username, String Password, String phone, String email) {
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
        if (phone == null || phone.trim().isEmpty()) {
            System.out.println("Invalid phone number");
            this.phone = "00000000";
            return;
        }
        
        phone = phone.trim();
        String normalizedPhone = null;
        
        // Case 1: Local Cambodian format (0 + 8-9 digits)
        if (phone.startsWith("0") && phone.matches("^0[0-9]{8,9}$")) {
            normalizedPhone = "+855" + phone.substring(1);
        }
        // Case 2: International format (+country code + 8+ digits)
        else if (phone.startsWith("+") && phone.matches("^\\+[0-9]{1,3}[0-9]{8,}$")) {
            normalizedPhone = phone;
        }
        // Invalid format
        else {
            System.out.println("Invalid phone number format.");
            return;
        }
        
        this.phone = normalizedPhone;
    }
    public String getEmail() { 
        return email; 
    }
    public void setEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            this.email = "Unknown Email";
        } else {
            this.email = email.trim();
        }
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