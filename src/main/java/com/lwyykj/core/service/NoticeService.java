package com.lwyykj.core.service;

import java.util.List;
import com.lwyykj.core.bean.product.Notice;

/**
 * 通知公告接口
 * @author john
 *
 */
public interface NoticeService {
	
	
	//查询所有公告
	List<Notice> findAll();
	//通过ID查询对象
	public Notice findById(Integer id);
}
