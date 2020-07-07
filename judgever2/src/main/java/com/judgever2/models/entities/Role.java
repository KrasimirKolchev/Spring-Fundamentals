package com.judgever2.models.entities;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "authorities")
public class Role extends BaseEntity implements GrantedAuthority {
    private String authority;

    public Role() {
    }

    public Role(String name) {
        this.authority = name;
    }

    @Column(name = "authority", unique = true, nullable = false)
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

}
