package tanzeer.parkmycar;

/**
 * Created by tanze on 3/23/2018.
 */

public class Users {
    String userName, email, phone, password, registrationNo;
    public Users(){

    }

    public Users(String userName, String email, String phone, String password, String registrationNo) {
        this.userName = userName;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.registrationNo = registrationNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRegistrationNo() {
        return registrationNo;
    }

    public void setRegistrationNo(String registrationNo) {
        this.registrationNo = registrationNo;
    }
}
