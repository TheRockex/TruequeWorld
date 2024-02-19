package com.dam.truequeworld.servicies;

import com.dam.truequeworld.models.Chat;
import com.dam.truequeworld.models.Favorite;
import com.dam.truequeworld.repositories.ChatRepository;
import com.dam.truequeworld.repositories.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {
    @Autowired
    private ChatRepository chatRepository;

    public List<Chat> getAllChats(){
        return chatRepository.findAll();
    }

    public Chat getChatById(Integer id){
        return chatRepository.findById(id).orElse(null);
    }

    public Chat saveChat(Chat chat){
        return chatRepository.save(chat);
    }

    public boolean deleteChatById(Integer id){
        chatRepository.deleteById(id);
        return chatRepository.findById(id).isEmpty();
    }
}
