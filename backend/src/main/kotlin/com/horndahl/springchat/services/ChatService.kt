package com.horndahl.springchat.services

import com.horndahl.springchat.assistants.Assistant
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.ResultSetExtractor
import org.springframework.stereotype.Service


@Service
class ChatService(
    private val assistant: Assistant,
    @Value("classpath:schema.sql")
    private val resourceFile: Resource,
    private val jdbcTemplate: JdbcTemplate
) {

    val schema = resourceFile.inputStream.bufferedReader().use { it.readText() }

    private fun queryToCsv(sqlQuery: String): String {
        return jdbcTemplate.query(sqlQuery, ResultSetExtractor { rs ->
            val metaData = rs.metaData
            val columnCount = metaData.columnCount
            val columnNames = mutableMapOf<String, Any?>()

            for (i in 1..columnCount) {
                columnNames[metaData.getColumnName(i)] = metaData.getColumnName(i)
            }

            val results = mutableListOf<Map<String, Any?>>()
            results.add(columnNames)  // Add column names as the first row

            while (rs.next()) {
                val row = mutableMapOf<String, Any?>()
                for (i in 1..columnCount) {
                    row[metaData.getColumnName(i)] = rs.getObject(i)
                }
                results.add(row)
            }
            results
        })?.joinToString("\n") { it.values.joinToString(",") } ?: ""
    }


    fun handleMessage(message: String): String {
        val sqlQuery = assistant.chat(message, schema)
        val cleanQuery = sqlQuery.trim('~')
        val result = queryToCsv(cleanQuery)
        return assistant.makeSenseOfData(result, message)
    }
}