package com.lwyykj.core.dao.utils;

import com.lwyykj.core.bean.utils.LoginRecord;
import com.lwyykj.core.bean.utils.LoginRecordQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LoginRecordDao {
    int countByExample(LoginRecordQuery example);

    int deleteByExample(LoginRecordQuery example);

    int deleteByPrimaryKey(Integer id);

    int insert(LoginRecord record);

    int insertSelective(LoginRecord record);

    List<LoginRecord> selectByExample(LoginRecordQuery example);

    LoginRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LoginRecord record, @Param("example") LoginRecordQuery example);

    int updateByExample(@Param("record") LoginRecord record, @Param("example") LoginRecordQuery example);

    int updateByPrimaryKeySelective(LoginRecord record);

    int updateByPrimaryKey(LoginRecord record);
}