package com.example.blogging_platform.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @NotBlank
    @Column(name = "title")
    private String title;

    @NotBlank
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @CreatedDate
    @Column(
            name = "created_at",
            nullable = false,
            updatable = false
    )
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "last_modified_at", insertable = false)
    private LocalDateTime lastModifiedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User user;

    @ManyToMany
    @JoinTable(
            name = "post_keyword",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "keyword_id")
    )
    @ToString.Exclude
    private Set<Keyword> keywords;

    @OneToMany(mappedBy = "post")
    @ToString.Exclude
    private Set<Comment> comments;

    @OneToMany(mappedBy = "post")
    @ToString.Exclude
    private Set<Reaction> reactions;
}