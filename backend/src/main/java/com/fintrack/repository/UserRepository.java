package com.fintrack.repository;

import com.fintrack.entity.Boleto;
import com.fintrack.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository <User, Boleto> {
    Optional<User> FindByEmail (String email);
}
