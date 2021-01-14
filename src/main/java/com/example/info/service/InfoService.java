package com.example.info.service;

import com.example.info.pojo.Info;

import java.util.List;

public interface InfoService {
    List<Info> getAllInfo();
    Info getDetails(Long id);
    boolean addReply(Long infoId);
}
