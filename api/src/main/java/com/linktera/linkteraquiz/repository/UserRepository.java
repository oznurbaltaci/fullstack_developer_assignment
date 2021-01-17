package com.linktera.linkteraquiz.repository;

import com.linktera.linkteraquiz.model.User;
import com.linktera.linkteraquiz.repository.base.BaseRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends BaseRepository<User> {
    Optional<User> findByUserId(UUID userId);
    Optional<User> findByUsernameAndPassword(String username, String password);
    Optional<User> findByUsername(String username);
    boolean checkByUsername(String username);
}
