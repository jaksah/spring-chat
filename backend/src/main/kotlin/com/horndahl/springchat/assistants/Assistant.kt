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
       - foreign keys are named as {table_name}_id
       - Include as much data as possible. Never set LIMIT less than 10
      
       Database schema:
       ~~~
       {{schema}}
       ~~~
    """)
    fun chat(@V("question") input: String, @V("schema") schema: String): String

    @UserMessage("""
        The following data is in CSV format where the first row is the column names.
         {{data}}
         
         Based on the data, the following question: {{question}}
         """)
    fun makeSenseOfData(@V("data") data: String, @V("question") question: String): String
}