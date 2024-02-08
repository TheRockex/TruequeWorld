package com.dam.truequeworld.repositories;

import com.dam.truequeworld.models.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Integer> {
}
