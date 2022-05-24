package com.example.springjpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "member")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
    @Id
    @Column(name = "MEMBER_ID", length = 20)
    private Integer id;
    @Column(name = "MEMBER_NAME", length = 50)
    private String name;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "ROLES", length = 10)
    private String roles;
    @Column(name = "USE_YN", length = 1)
    private String useYn;
    @Column(name = "REG_DTM", length = 20)
    private String regDtm;
}

