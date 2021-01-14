package com.example.emp.service;

import com.example.info.dao.InfoDao;
import com.example.emp.event.EmpEvent;
import com.example.emp.pojo.Employee;
import com.example.info.pojo.Info;
import com.example.info.service.InfoService;
import com.example.testAspect.MyTransactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
@Service
public class InfoServiceimpl implements InfoService {
    private Logger logger= LoggerFactory.getLogger(InfoServiceimpl.class);
    @Resource
    private InfoDao infoDao;
    @Autowired
    ApplicationContext applicationContext;
    @MyTransactional
    public Employee getEmpById(String id){
        logger.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<info:InfoServiceimpl>>getEmpById");
        logger.debug("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<debug:InfoServiceimpl>>getEmpById");
        logger.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<error:InfoServiceimpl>>getEmpById");
        logger.warn("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<warn:InfoServiceimpl>>getEmpById");
        //**********观察者模式 开始****************
        EmpEvent empEvent = new EmpEvent("查询Emp的时间");
        applicationContext.publishEvent(empEvent);//发布事件，InfoListener里面监听此事件
        //**********观察者模式  结束****************
        return infoDao.getEmpById(id);
    }
    @Override
    public List<Info> getAllInfo() {

        return infoDao.getAllInfo();
    }

    @Override
    public Info getDetails(Long id) {
        return infoDao.getDetails(id);
    }

    @Override
    public boolean addReply(Long infoId) {
        return infoDao.addReply(infoId)==1;
    }
}
