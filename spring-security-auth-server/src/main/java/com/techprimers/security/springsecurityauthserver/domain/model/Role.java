package com.techprimers.security.springsecurityauthserver.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by Rith on 5/29/2018.
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sec_role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    public Role(String name) {
        this.name = name;
    }
}

