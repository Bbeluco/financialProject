package com.study.financial.DTO;

import com.study.financial.entities.UserEntity;

public class UserCompleteInfoDTO {
    private int id;
    private String name;

    private String cpf;
    private String email;
    private String password;

    private float balance;

    public UserCompleteInfoDTO(UserEntity user) {
        this.id = user.getId();
        this.name = user.getName();
        this.cpf = user.getCpf();
        this.email = user.getEmail();
        this.balance = user.getWallet().getBalance();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }
}
