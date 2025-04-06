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

}
