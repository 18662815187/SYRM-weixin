package com.lwyykj.core.service;
/**
 * 活动用户服务
 * @author john
 *
 */

import java.util.List;

import com.lwyykj.core.bean.activity.AcUser;

import cn.itcast.common.page.Pagination;


public interface AcUserService {
	//根据ID查询
	public Pagination findById(Integer id,String tel,Integer pageNo);
	//根据主键删除
	public int delById(Integer id);
	//根据AC_ID查询所有
	public List<AcUser> findByACID(Integer ac_id);
}
