package com.example.blogging_platform.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "keyword")
public class Keyword {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", unique = true)
    private String name;

    @ManyToMany(mappedBy = "keywords", fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<Post> posts;
}
