package com.dam.truequeworld.repositories;

import com.dam.truequeworld.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
    @Query("SELECT u FROM User u WHERE u.email = :gmail")
    User findByGmail(String gmail);
}
