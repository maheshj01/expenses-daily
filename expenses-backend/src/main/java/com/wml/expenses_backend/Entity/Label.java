package com.wml.expenses_backend.Entity;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "labels", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "labelName"})
})
public class Label {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long labelId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String labelName;

    @OneToMany(mappedBy = "label", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Expense> expenses;

    // Getters and Setters
}

