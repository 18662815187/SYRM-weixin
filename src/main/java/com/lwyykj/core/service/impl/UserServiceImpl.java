package com.lwyykj.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lwyykj.core.bean.activity.user;
import com.lwyykj.core.bean.activity.userQuery;
import com.lwyykj.core.bean.activity.userQuery.Criteria;
import com.lwyykj.core.dao.activity.userDao;
import com.lwyykj.core.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource
	private userDao userDao;

	@Override
	public user getUserByName(String name) {
		userQuery userQuery = new userQuery();
		Criteria createCriteria = userQuery.createCriteria();
		createCriteria.andAuserEqualTo(name);
		List<user> users = userDao.selectByExample(userQuery);
		if (users != null && users.size() > 0) {
			return users.get(0);
		}
		return null;
	}

}
