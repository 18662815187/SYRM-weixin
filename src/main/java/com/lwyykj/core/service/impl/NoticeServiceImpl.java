package com.lwyykj.core.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lwyykj.core.bean.product.Notice;
import com.lwyykj.core.bean.product.NoticeQuery;
import com.lwyykj.core.bean.product.NoticeQuery.Criteria;
import com.lwyykj.core.dao.product.NoticeDao;
import com.lwyykj.core.service.NoticeService;

/**
 * 通知公告接口实现
 * 
 * @author john
 *
 */
@Service("noticeService")
@Transactional
public class NoticeServiceImpl implements NoticeService {
	@Resource
	private NoticeDao noticeDao;

	// 查询所有通知信息
	@Override
	public List<Notice> findAll() {
		NoticeQuery noticeQuery = new NoticeQuery();
		noticeQuery.setOrderByClause("id desc");
		Criteria createCriteria = noticeQuery.createCriteria();
		createCriteria.andIdIsNotNull();
		createCriteria.andIsShowEqualTo(true);
		List<Notice> notices = noticeDao.selectByExample(noticeQuery);
		if (notices != null && notices.size() > 0) {
			return notices;
		}
		return null;
	}
	//通过ID查询
	@Override
	public Notice findById(Integer id) {
		Notice notice = noticeDao.selectByPrimaryKey(id);
		if (null != notice) {
			return notice;
		}
		return null;
	}

}
