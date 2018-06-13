package com.lwyykj.core.service;

import com.lwyykj.core.bean.utils.LoginRecord;

public interface LoginRecordService {
	//查询上一次的登录记录
	public LoginRecord findByPid(Integer id);
	//插入登录记录
	public int insertRecord(LoginRecord loginRecord);
}
