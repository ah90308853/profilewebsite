package com.website.website.repository;

import com.website.website.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.io.Serializable;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Serializable>
{
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    User save(User user);
}
