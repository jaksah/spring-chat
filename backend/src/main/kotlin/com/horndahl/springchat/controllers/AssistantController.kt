package com.horndahl.springchat.controllers

import com.horndahl.springchat.assistants.Assistant
import com.horndahl.springchat.domain.MessageRequest
import com.horndahl.springchat.domain.MessageResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AssistantController(
    private val assistant: Assistant,
    @Value("classpath:schema.sql")
    private val resourceFile: Resource
) {

    val schema = resourceFile.inputStream.bufferedReader().use { it.readText() }

    @CrossOrigin(origins = ["*"])
    @PostMapping("/chat")
    fun chat(@RequestBody request: MessageRequest): MessageResponse {
        return MessageResponse(assistant.chat(request.message, schema))
    }
}