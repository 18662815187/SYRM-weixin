package com.lwyykj.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lwyykj.core.bean.utils.LoginRecord;
import com.lwyykj.core.bean.utils.LoginRecordQuery;
import com.lwyykj.core.bean.utils.LoginRecordQuery.Criteria;
import com.lwyykj.core.dao.utils.LoginRecordDao;
import com.lwyykj.core.service.LoginRecordService;

/**
 * 登录记录服务实现类,传入药店ID
 * 
 * @author john
 *
 */

@Service("loginRecordService")
@Transactional
public class LoginRecordServiceImpl implements LoginRecordService {
	@Resource
	private LoginRecordDao loginRecordDao;
	//	查询上一次的登录记录
	@Override
	public LoginRecord findByPid(Integer id) {
		LoginRecordQuery loginRecordQuery = new LoginRecordQuery();
		loginRecordQuery.setOrderByClause("id desc");
		Criteria createCriteria = loginRecordQuery.createCriteria();
		createCriteria.andPidEqualTo(id);
		List<LoginRecord> loginRecords = loginRecordDao.selectByExample(loginRecordQuery);
		if (loginRecords != null && loginRecords.size() > 0) {
			return loginRecords.get(0);
		}
		return null;
	}
	//插入登录记录
	@Override
	public int insertRecord(LoginRecord loginRecord) {
		int result = loginRecordDao.insertSelective(loginRecord);
		return result;
	}

}
