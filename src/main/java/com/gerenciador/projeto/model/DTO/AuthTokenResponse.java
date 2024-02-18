package com.gerenciador.projeto.model.DTO;

public class AuthTokenResponse {
    private String token;
    private String type = "Bearer"; // Tipo padrão de token, mas pode ser ajustado conforme necessário

    public AuthTokenResponse() {
    }

    public AuthTokenResponse(String token) {
        this.token = token;
    }

    // Getters e Setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "AuthTokenResponse{" +
                "token='" + token + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
