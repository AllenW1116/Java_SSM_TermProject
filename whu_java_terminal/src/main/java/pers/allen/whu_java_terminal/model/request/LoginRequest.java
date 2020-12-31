package pers.allen.whu_java_terminal.model.request;


/**
 * 用于包装传入的登录信息
 */
public class LoginRequest {
    private String phone;

    private String pwd;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
