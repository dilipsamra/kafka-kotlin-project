package com.example.producer

import org.springframework.http.ResponseEntity
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

data class PublishRequest(val payload: String)

@RestController
@RequestMapping("/api/messages")
class MessageController(private val kafkaTemplate: KafkaTemplate<String, String>) {

    private val topic = "messages"

    @PostMapping
    fun publish(@RequestBody request: PublishRequest): ResponseEntity<Any> {
        kafkaTemplate.send(topic, request.payload)
        return ResponseEntity.ok(mapOf("status" to "sent", "topic" to topic))
    }
}
