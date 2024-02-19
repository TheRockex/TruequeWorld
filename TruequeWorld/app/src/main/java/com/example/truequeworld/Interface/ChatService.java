package com.example.truequeworld.Interface;

import com.example.truequeworld.Class.Chat;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ChatService {
    @GET("/chat/chats")
    Call<List<Chat>> getChats();
    @GET("/chat/id/{id}")
    Call<Chat> getChatById(@Path("id")Integer id);
}
