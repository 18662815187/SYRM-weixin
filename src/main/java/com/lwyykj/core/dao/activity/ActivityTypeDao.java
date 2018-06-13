package com.lwyykj.core.dao.activity;

import com.lwyykj.core.bean.activity.ActivityType;
import com.lwyykj.core.bean.activity.ActivityTypeQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActivityTypeDao {
    int countByExample(ActivityTypeQuery example);

    int deleteByExample(ActivityTypeQuery example);

    int deleteByPrimaryKey(Integer id);

    int insert(ActivityType record);

    int insertSelective(ActivityType record);

    List<ActivityType> selectByExample(ActivityTypeQuery example);

    ActivityType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ActivityType record, @Param("example") ActivityTypeQuery example);

    int updateByExample(@Param("record") ActivityType record, @Param("example") ActivityTypeQuery example);

    int updateByPrimaryKeySelective(ActivityType record);

    int updateByPrimaryKey(ActivityType record);
    
    int delById(Integer id);
}