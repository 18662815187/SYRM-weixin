package com.lwyykj.core.service;

import com.lwyykj.core.bean.activity.activity;

import cn.itcast.common.page.Pagination;

public interface ActivityService {
	//列表分页
	public Pagination selectPaginationByQuery(Integer pageNo, String title,String time);
	//通过ID查询
	public activity findById(Integer id);
	//更新
	public int update(activity activity);
	//保存
	public int save(activity activity);
	//删除，假删
	public int delete(Integer id);
}
