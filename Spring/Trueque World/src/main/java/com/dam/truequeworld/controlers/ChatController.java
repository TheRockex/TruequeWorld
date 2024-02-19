package com.dam.truequeworld.controlers;

import com.dam.truequeworld.models.Chat;
import com.dam.truequeworld.servicies.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private ChatService chatService;

    @GetMapping("/chats")
    public List<Chat> getChats(){
        return chatService.getAllChats();
    }

    @GetMapping("/id/{id}")
    public Chat getChatById(@PathVariable Integer id){
        return chatService.getChatById(id);
    }

    @PostMapping("/save")
    public Chat insertChat(@RequestBody Chat chat){
        return chatService.saveChat(chat);
    }

    @DeleteMapping("/delete/{id}")
    public Boolean deleteChatById(@PathVariable Integer id){
        return chatService.deleteChatById(id);
    }
}

