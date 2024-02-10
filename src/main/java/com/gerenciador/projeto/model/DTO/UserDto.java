package com.gerenciador.projeto.model.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserDto {

    @NotBlank(message = "O nome do usuario não pode estár vazio.")
    private String username;
    @NotBlank(message = "O nickname não pode estár vazio.")
    private String nickname;

    @Size (min=6, message = "A senha deve ter pelo menos 6 caracteres.")
    private String password;
    @Size (min=6, message = "A senha deve ter pelo menos 6 caracteres.")
    private String newPassword;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Email(message = "\"Email deve ser válido.")
    private String email;

    @Pattern(regexp="^\\+?[1-9]\\d{1,14}$", message="Número de telefone inválido")
    private String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }




}
