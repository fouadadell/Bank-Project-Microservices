package com.bank.account.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customers extends BaseEntity {

    @Id
    @GeneratedValue
    private Long customerId;

    private String name;

    private String email;

    private String mobileNumber;

    @OneToOne(targetEntity = Accounts.class, fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "accountId")
    private Accounts accounts;
}
