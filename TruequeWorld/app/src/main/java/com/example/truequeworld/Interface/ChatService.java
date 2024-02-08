package com.example.truequeworld.Interface;

import com.example.truequeworld.Class.Chat;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ChatService {
    @GET("/chats/chat")
    Call<List<Chat>> getChats();
}
