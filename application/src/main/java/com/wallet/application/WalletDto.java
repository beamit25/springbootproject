package com.wallet.application;
import org.springframework.context.annotation.PropertySource;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.*;
@Entity
@PropertySource("classpath:application.properties")
public class WalletDto {
    @Id
    private Integer id;
    @NotBlank(message = "Name cant be null, it should contain chars")
    @Pattern(regexp = "[a-zA-Z]{3,16}", message = "Name should contain min 3 & max 16 chars , no digits and special chars allowed.")

    private String name;

    private Double balance;

    private String email;

    public WalletDto(Integer id, String name, Double balance, String email) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.email = email;
    }

    public WalletDto() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "WalletDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                ", email='" + email + '\'' +
                '}';
    }
}
