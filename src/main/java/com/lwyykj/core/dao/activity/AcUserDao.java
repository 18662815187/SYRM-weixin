package com.lwyykj.core.dao.activity;

import com.lwyykj.core.bean.activity.AcUser;
import com.lwyykj.core.bean.activity.AcUserQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AcUserDao {
    int countByExample(AcUserQuery example);

    int deleteByExample(AcUserQuery example);

    int deleteByPrimaryKey(Integer id);

    int insert(AcUser record);

    int insertSelective(AcUser record);

    List<AcUser> selectByExample(AcUserQuery example);

    AcUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AcUser record, @Param("example") AcUserQuery example);

    int updateByExample(@Param("record") AcUser record, @Param("example") AcUserQuery example);

    int updateByPrimaryKeySelective(AcUser record);

    int updateByPrimaryKey(AcUser record);
}