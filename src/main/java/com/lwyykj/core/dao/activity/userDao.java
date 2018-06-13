package com.lwyykj.core.dao.activity;

import com.lwyykj.core.bean.activity.user;
import com.lwyykj.core.bean.activity.userQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface userDao {
    int countByExample(userQuery example);

    int deleteByExample(userQuery example);

    int deleteByPrimaryKey(Short auid);

    int insert(user record);

    int insertSelective(user record);

    List<user> selectByExampleWithBLOBs(userQuery example);

    List<user> selectByExample(userQuery example);

    user selectByPrimaryKey(Short auid);

    int updateByExampleSelective(@Param("record") user record, @Param("example") userQuery example);

    int updateByExampleWithBLOBs(@Param("record") user record, @Param("example") userQuery example);

    int updateByExample(@Param("record") user record, @Param("example") userQuery example);

    int updateByPrimaryKeySelective(user record);

    int updateByPrimaryKeyWithBLOBs(user record);

    int updateByPrimaryKey(user record);
}