package com.example.leaderboard.controller;

import com.example.leaderboard.kafka.KafkaProducer;
import com.example.leaderboard.model.score.record.ScoreRecordDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
@AllArgsConstructor
public class KafkaController {

    private KafkaProducer kafkaProducer;

    @PostMapping("/send")
    public String sendMessage(@RequestBody ScoreRecordDto scoreRecordDto) {
        kafkaProducer.sendMessage(scoreRecordDto);
        return "Message sent to Kafka topic";
    }
}
