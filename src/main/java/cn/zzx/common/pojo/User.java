package cn.zzx.common.pojo;

import org.hibernate.validator.constraints.Email;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

public class User {
    @NotEmpty(message = "学号不能为空")

    private String studentId;
    @NotEmpty(message = "名字不能为空")
    private String userName;
    @NotEmpty(message = "密码不能为空")
    @Size(min =6,max = 12,message = "密码长度不正确6~12")
    private String userPassword;
    private String userSex;
    @Email(message = "email 格式不正确")
    @NotEmpty(message = "Email不能为空")
    private String userEmail;
    private Integer userType;//默认都是0
    private Integer userAdmin;

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public Integer getUserAdmin() {
        return userAdmin;
    }

    public void setUserAdmin(Integer userAdmin) {
        this.userAdmin = userAdmin;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "User{" +
                "studentId='" + studentId + '\'' +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userType=" + userType +
                ", userAdmin=" + userAdmin +
                '}';
    }
}
