package com.lwyykj.core.dao.activity;

import com.lwyykj.core.bean.activity.AcLike;
import com.lwyykj.core.bean.activity.AcLikeQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AcLikeDao {
	int countByExample(AcLikeQuery example);

	int deleteByExample(AcLikeQuery example);

	int deleteByPrimaryKey(Integer id);

	int insert(AcLike record);

	int insertSelective(AcLike record);

	List<AcLike> selectByExample(AcLikeQuery example);

	AcLike selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") AcLike record, @Param("example") AcLikeQuery example);

	int updateByExample(@Param("record") AcLike record, @Param("example") AcLikeQuery example);

	int updateByPrimaryKeySelective(AcLike record);

	int updateByPrimaryKey(AcLike record);

	int countByUid(Integer uid);
	
	int delByUid(Integer uid);
}