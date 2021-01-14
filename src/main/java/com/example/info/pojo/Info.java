package com.example.info.pojo;

import lombok.Data;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
public class Info {
    private Long id;
    private String title;
    private String content;
    private Integer viewCount;
    private Integer replyCount;
    private Timestamp reportTime;
    private Timestamp lastPostTime;
    private List<Rep> rep=new ArrayList<>(0);



}
