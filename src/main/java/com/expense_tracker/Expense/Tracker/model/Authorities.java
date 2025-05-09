package com.expense_tracker.Expense.Tracker.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="authorities")
@NoArgsConstructor
@Getter
@Setter
public class Authorities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="authority_id")
    private int authorityId;

    @Column(name="authority")
    private String authority;

    @ManyToOne
    @JoinColumn(name="user_id")
    private UserDetails user_authority;

    public int getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(int authorityId) {
        this.authorityId = authorityId;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public UserDetails getUser_authority() {
        return user_authority;
    }

    public void setUser_authority(UserDetails user_authority) {
        this.user_authority = user_authority;
    }

    public void addUser(UserDetails userDetails) {
        this.user_authority=userDetails;
        userDetails.addAuthorities(this);
    }

}
