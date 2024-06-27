package com.example.leaderboard.kafka;

import com.example.leaderboard.model.score.record.ScoreRecordDto;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class KafkaProducer {

    private static final String TOPIC = "myTopic";

    private KafkaTemplate<String, ScoreRecordDto> kafkaTemplate;

    public void sendMessage(ScoreRecordDto scoreRecordDto) {
        kafkaTemplate.send(TOPIC, scoreRecordDto);
    }
}
