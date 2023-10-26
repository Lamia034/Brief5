package com.bank.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Entity
public class Agency {
    @NonNull
    @Id
    private String code;
    @NonNull
    private String name;
    @NonNull
    private String address;
    @NonNull
    private String phone;
    @OneToMany
    private List<Credit> credit;
    @OneToMany
    private List<Employee> employees;
    @OneToMany
    private List<Client> clients;
    @ManyToMany
    private List<Employee> employeeHistory;
    @OneToMany
    private List<AgencyEmployee> history;
}
