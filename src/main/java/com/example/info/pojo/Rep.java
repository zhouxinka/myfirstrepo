package com.example.info.pojo;

import lombok.Data;

import java.sql.Timestamp;
@Data
public class Rep {
    private Long id;
    private String content;
    private Timestamp replyTime;
    private Long infoId;

    public Rep(Long id, String content, Timestamp replyTime, Long infoId) {
        this.id = id;
        this.content = content;
        this.replyTime = replyTime;
        this.infoId = infoId;
    }
}
