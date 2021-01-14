package com.example.info.dao;

import com.example.emp.pojo.Employee;
import com.example.info.pojo.Info;
import com.example.info.pojo.Rep;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface InfoDao {

    @Select("select * from informations  order by lastPostTime desc")
    List<Info> getAllInfo();

    @Select("select * from employee  where id=#{id}")
    Employee getEmpById(String id);

    @Results({
            @Result(column = "id", property = "id"),
            @Result(
                    property = "rep",
                    column = "id",
                    many = @Many(select = "InfoDao.findRep"))
    })
    @Select("select * from informations where id=#{id}")
    Info getDetails(Long id);

    @Select("select * from replies  where infoId=#{0}")
    List<Rep> findRep(Long id);
    @Insert("insert into replies(content,replyTime,infoId) values(#{content},now(),#{infoId})")
    int addReply(Long infoId);
}
