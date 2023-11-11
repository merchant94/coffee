package com.example.coffee.temp;

import com.example.coffee.model.GitRepoInfo;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class GitRepositoryInfoRepository implements JpaRepository<GitRepoInfo, Long> {
    @Override
    public void flush() {

    }

    @Override
    public <S extends GitRepoInfo> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends GitRepoInfo> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<GitRepoInfo> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public GitRepoInfo getOne(Long aLong) {
        return null;
    }

    @Override
    public GitRepoInfo getById(Long aLong) {
        return null;
    }

    @Override
    public GitRepoInfo getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends GitRepoInfo> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends GitRepoInfo> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends GitRepoInfo> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends GitRepoInfo> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends GitRepoInfo> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends GitRepoInfo> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends GitRepoInfo, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends GitRepoInfo> S save(S entity) {
        return null;
    }

    @Override
    public <S extends GitRepoInfo> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<GitRepoInfo> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<GitRepoInfo> findAll() {
        return null;
    }

    @Override
    public List<GitRepoInfo> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(GitRepoInfo entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends GitRepoInfo> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<GitRepoInfo> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<GitRepoInfo> findAll(Pageable pageable) {
        return null;
    }
//    Optional<GitRepositoryInfo> findByOwnerAndRepository(String owner, String repository);
}
