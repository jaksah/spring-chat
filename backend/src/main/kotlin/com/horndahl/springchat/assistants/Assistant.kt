package com.horndahl.springchat.assistants

import dev.langchain4j.service.UserMessage
import dev.langchain4j.service.V
import dev.langchain4j.service.spring.AiService

@AiService
interface Assistant {
    @UserMessage("""
       Based on the database schema, surrounded in ~~~,
       generate a SQL query based on the question provided by the user: {{question}}.
       
       - The answer should be the final SQL query.
       - Do NOT answer anything but the SQL query.
      
       Database schema:
       ~~~
       {{schema}}
       ~~~
    """)
    fun chat(@V("question") input: String, @V("schema") schema: String): String
}