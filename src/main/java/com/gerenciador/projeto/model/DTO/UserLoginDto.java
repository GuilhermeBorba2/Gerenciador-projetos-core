package com.gerenciador.projeto.model.DTO;

import java.io.Serializable;

public class UserLoginDto implements Serializable {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserLoginDto(){

    }

    @Override
    public String toString(){
        return "UserLogin{ "+
                "username='" + username +'\''+
                ", password='[PROTECTED'"+
                '}';
    }


}

