package com.example.coffee.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GitInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;
    @Column(name = "owner", nullable = false)
    private String owner;
    @Column(name = "repository", nullable = false)
    private String repository;
    @Column(name = "version")
    private String version;
    @Column(name = "description")
    private String description;

    @Builder
    public GitInfo(String owner, String repository, String version, String description) {
        this.owner = owner;
        this.repository = repository;
        this.version = version;
        this.description = description;
    }
}
