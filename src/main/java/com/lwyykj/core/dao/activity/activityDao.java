package com.lwyykj.core.dao.activity;

import com.lwyykj.core.bean.activity.activity;
import com.lwyykj.core.bean.activity.activityQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface activityDao {
	int countByExample(activityQuery example);

	int deleteByExample(activityQuery example);

	int deleteByPrimaryKey(Integer id);

	int insert(activity record);

	int insertSelective(activity record);

	List<activity> selectByExample(activityQuery example);

	activity selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") activity record, @Param("example") activityQuery example);

	int updateByExample(@Param("record") activity record, @Param("example") activityQuery example);

	int updateByPrimaryKeySelective(activity record);

	int updateByPrimaryKey(activity record);

	int updateById(Integer id);
}