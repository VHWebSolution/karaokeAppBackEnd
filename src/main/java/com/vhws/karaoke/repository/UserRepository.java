package com.vhws.karaoke.repository;

import com.vhws.karaoke.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
