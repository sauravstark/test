package com.example.leaderboard.kafka;

import com.example.leaderboard.model.score.record.CreateScoreRecordRequest;
import com.example.leaderboard.model.score.record.ScoreRecordDto;
import com.example.leaderboard.service.ScoreRecordService;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class KafkaConsumer {
    private ScoreRecordService scoreRecordService;

    @KafkaListener(topics = "myTopic", groupId = "myGroup")
    public void consume(ScoreRecordDto scoreRecordDto) {
        val gamename = scoreRecordDto.getGamename();
        val username = scoreRecordDto.getUsername();
        val createScoreRecordRequest = CreateScoreRecordRequest.builder()
                .score(scoreRecordDto.getScore())
                .recordTime(scoreRecordDto.getRecordTime())
                .build();
        scoreRecordService.createScoreRecord(gamename, username, createScoreRecordRequest);
    }
}
