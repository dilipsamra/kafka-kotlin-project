package com.example.consumer

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document(collection = "messages")
data class MessageRecord(
    @Id
    val id: String,
    val payload: String,
    val receivedAt: Instant = Instant.now()
)
