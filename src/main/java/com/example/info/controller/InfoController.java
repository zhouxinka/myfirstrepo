package com.example.info.controller;

import com.example.emp.pojo.Employee;
import com.example.info.pojo.Info;
import com.example.emp.service.InfoServiceimpl;
import com.example.testTimerTask.TimerTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("/info")
@RestController
public class InfoController {
    private Logger logger= LoggerFactory.getLogger(InfoController.class);
    @Resource
    private InfoServiceimpl infoServiceimpl;
    @Autowired
    TimerTaskService timerTaskService;

    @GetMapping("/getEmpById")
    @ResponseBody
    public Employee getEmpById(@RequestParam("id") String id){
        logger.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<info:getEmpById");
        logger.debug("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<debug:getEmpById");
        logger.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<error:getEmpById");
        logger.warn("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<warn:getEmpById");
        //timerTaskService.testTimerTask();
        //timerTaskService.testScheduleAtFixedRate();
        return infoServiceimpl.getEmpById(id);
    }


    @GetMapping("/getAllInfo")
    public List<Info> getAllInfo(){
        logger.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<getAllInfo");
        return infoServiceimpl.getAllInfo();
    }
    @GetMapping("/getDetails")
    public Info getDetails(@RequestParam("id") Long id){
        logger.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<getDetails");
        return infoServiceimpl.getDetails(id);
    }
    @PostMapping("/addReply")
    public boolean addReply(@RequestParam("id") Long infoId){
        logger.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<addReply");
        return infoServiceimpl.addReply(infoId);
    }

}
