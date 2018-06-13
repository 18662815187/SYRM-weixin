package com.lwyykj.core.dao.utils;

import com.lwyykj.core.bean.utils.Vercode;
import com.lwyykj.core.bean.utils.VercodeQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VercodeDao {
    int countByExample(VercodeQuery example);

    int deleteByExample(VercodeQuery example);

    int deleteByPrimaryKey(Integer id);

    int insert(Vercode record);

    int insertSelective(Vercode record);

    List<Vercode> selectByExample(VercodeQuery example);

    Vercode selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Vercode record, @Param("example") VercodeQuery example);

    int updateByExample(@Param("record") Vercode record, @Param("example") VercodeQuery example);

    int updateByPrimaryKeySelective(Vercode record);

    int updateByPrimaryKey(Vercode record);
}