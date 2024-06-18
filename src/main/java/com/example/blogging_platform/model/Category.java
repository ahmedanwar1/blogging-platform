package com.example.blogging_platform.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @NotBlank
    @Column(name = "name", unique = true)
    private String name;

    @NotBlank
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<Post> posts;
}
