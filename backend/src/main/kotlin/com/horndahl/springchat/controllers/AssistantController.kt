package com.horndahl.springchat.controllers

import com.horndahl.springchat.domain.MessageRequest
import com.horndahl.springchat.domain.MessageResponse
import com.horndahl.springchat.services.ChatService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AssistantController(
    private val chatService: ChatService
) {

    @CrossOrigin(origins = ["*"])
    @PostMapping("/chat")
    fun chat(@RequestBody request: MessageRequest): MessageResponse {
        return MessageResponse(chatService.handleMessage(request.message))
    }
}