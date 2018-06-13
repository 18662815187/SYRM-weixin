package com.lwyykj.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lwyykj.core.bean.activity.AcUser;
import com.lwyykj.core.bean.activity.AcUserQuery;
import com.lwyykj.core.bean.activity.AcUserQuery.Criteria;
import com.lwyykj.core.dao.activity.AcLikeDao;
import com.lwyykj.core.dao.activity.AcUserDao;
import com.lwyykj.core.service.AcUserService;

import cn.itcast.common.page.Pagination;

/**
 * 活动结果服务
 * 
 * @author john
 *
 */
@Service("acUserService")
public class AcUserServiceImpl implements AcUserService {
	@Resource
	private AcUserDao acUserDao;
	@Resource
	private AcLikeDao acLikeDao;

	@Override
	public Pagination findById(Integer id, String tel, Integer pageNo) {
		StringBuilder sb = new StringBuilder();
		AcUserQuery acUserQuery = new AcUserQuery();
		Criteria createCriteria = acUserQuery.createCriteria();
		createCriteria.andAcIdEqualTo(id);
		sb.append("id=").append(id);
		// acUserQuery.setOrderByClause("id desc");
		acUserQuery.setPageSize(20);
		acUserQuery.setPageNo(Pagination.cpn(pageNo));
		if (null != tel) {
			createCriteria.andPhoneEqualTo(tel);
			sb.append("&tel=").append(tel);
		}
		List<AcUser> acUsers = acUserDao.selectByExample(acUserQuery);
		if (null != acUsers && acUsers.size() > 0) {
			for (AcUser acUser : acUsers) {
				Integer num = acLikeDao.countByUid(acUser.getId());
				acUser.setNum(num);
			}
			Pagination pagination = new Pagination(acUserQuery.getPageNo(), acUserQuery.getPageSize(),
					acUserDao.countByExample(acUserQuery), acUsers);
			String url = "/activity/toResult";
			pagination.pageView(url, sb.toString());
			return pagination;
		}
		return null;
	}

	@Override
	public int delById(Integer id) {
		acLikeDao.delByUid(id);
		return acUserDao.deleteByPrimaryKey(id);
	}

	// 用于导出的数据
	@Override
	public List<AcUser> findByACID(Integer ac_id) {
		AcUserQuery acUserQuery = new AcUserQuery();
		Criteria createCriteria = acUserQuery.createCriteria();
		createCriteria.andAcIdEqualTo(ac_id);
		List<AcUser> acUsers = acUserDao.selectByExample(acUserQuery);
		if (null != acUsers && acUsers.size() > 0) {
			for (AcUser acUser : acUsers) {
				int a = acLikeDao.countByUid(acUser.getId());
				acUser.setNum(a);
			}
			return acUsers;
		}
		return null;
	}

}
