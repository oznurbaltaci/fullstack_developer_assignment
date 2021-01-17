package com.linktera.linkteraquiz.repository.impl;

import com.linktera.linkteraquiz.enums.UserType;
import com.linktera.linkteraquiz.model.Book;
import com.linktera.linkteraquiz.model.User;
import com.linktera.linkteraquiz.repository.UserRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Scope("singleton")
public class UserRepositoryImpl implements UserRepository {

    Map<UUID, User> users;


    public UserRepositoryImpl() {
        this.users = new HashMap<UUID, User>();
        UUID uuid = UUID.randomUUID();
        UUID uuid2 = UUID.randomUUID();
        users.put(uuid, new User(uuid,"uye", "uyel" ,"jkdjf", "12", UserType.CUSTOMER));
        users.put(uuid2, new User(uuid2,"personel", "pel" ,"jkdjf", "12", UserType.PERSONEL));
    }

    @Override
    public List<User> getList() {
        return new ArrayList<>(users.values());
    }

    @Override
    public User get(UUID uuid) {
        return users.get(uuid);
    }

    @Override
    public void save(User entity) {
        UUID uuid = UUID.randomUUID();
        entity.setUserId(uuid);
        users.put(uuid, entity);

    }

    @Override
    public void update(UUID uuid, User entity) {
        users.replace(uuid, entity);
    }

    @Override
    public void delete(UUID uuid) {
        users.remove(uuid);
    }

    @Override
    public Optional<User> findByUserId(UUID userId) {
        return users.values().stream().filter(t -> t.getUserId().equals(userId)).findFirst();
    }

    @Override
    public Optional<User> findByUsernameAndPassword(String username, String password) {
        return users.values().stream().filter(t -> t.getUsername().equals(username) && t.getPassword().equals(password)).findFirst();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return users.values().stream().filter(t -> t.getUsername().equals(username)).findFirst();
    }

    @Override
    public boolean checkByUsername(String username) {
        return users.values().stream().anyMatch(t -> t.getUsername().equals(username));
    }
}
