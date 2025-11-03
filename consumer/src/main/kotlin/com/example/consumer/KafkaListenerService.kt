package com.example.consumer

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.stereotype.Service
import java.util.UUID

@Configuration
open class KafkaConsumerConfig(
    @Value("\${KAFKA_BOOTSTRAP_SERVERS:localhost:9092}")
    private val bootstrapServers: String
) {
    @Bean
    fun consumerFactory(): ConsumerFactory<String, String> {
        val props: MutableMap<String, Any> = HashMap()
        props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServers
        props[ConsumerConfig.GROUP_ID_CONFIG] = "kotlin-consumer-group"
        props[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        props[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        return DefaultKafkaConsumerFactory(props)
    }

    @Bean
    fun kafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, String> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, String>()
        factory.consumerFactory = consumerFactory()
        return factory
    }
}

@Service
open class MessageListener(private val repo: MessageRepository) {
    private val log = LoggerFactory.getLogger(javaClass)

    @KafkaListener(topics = ["messages"], groupId = "kotlin-consumer-group")
    fun listen(message: String) {
        val id = UUID.randomUUID().toString()
        log.info("Received message from Kafka, saving with id={}", id)
        val record = MessageRecord(id = id, payload = message)
        repo.save(record)
    }
}
