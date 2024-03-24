package org.modelmapperexercise.service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public class UserRegisterDTO {

    @Pattern(regexp = "/[A-z]+@[A-z]+.[A-z]+/gm",
            message = "Email is not matching the pattern.")
    private String email;
    @Pattern(regexp = "/(?=.*[A-Z])(?=.*\\d).{6,}/gm")
    private String password;
    private String confirmPassword;
    private String fullName;

    public UserRegisterDTO(String email, String password, String confirmPassword, String fullName) {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
