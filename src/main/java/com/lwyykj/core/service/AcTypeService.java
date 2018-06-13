package com.lwyykj.core.service;

import com.lwyykj.core.bean.activity.ActivityType;

import cn.itcast.common.page.Pagination;

public interface AcTypeService {
	// 列表带分页
	public Pagination findAll(Integer pageNo, String des);

	// 根据ID查询详细内容
	public ActivityType findById(Integer id);

	// 添加或更新
	public int saveOrUpdate(ActivityType activityType);

	// 删除
	public int delById(Integer id);
}
