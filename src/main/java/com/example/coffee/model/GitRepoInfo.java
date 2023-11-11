package com.example.coffee.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class GitRepoInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String owner;
    private String repository;
    private String version;
    private String description;

    public GitRepoInfo(String owner, String repository, String version, String description) {
        this.owner = owner;
        this.repository = repository;
        this.version = version;
        this.description = description;
    }

    public GitRepoInfo() {

    }


    @Override
    public String toString() {
        return "GitRepositoryInfo{" +
                "owner='" + owner + '\'' +
                ", repository='" + repository + '\'' +
                ", version='" + version + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
