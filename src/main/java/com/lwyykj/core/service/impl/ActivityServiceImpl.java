package com.lwyykj.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lwyykj.common.FormatDate;
import com.lwyykj.core.bean.activity.activity;
import com.lwyykj.core.bean.activity.activityQuery;
import com.lwyykj.core.bean.activity.activityQuery.Criteria;
import com.lwyykj.core.dao.activity.ActivityTypeDao;
import com.lwyykj.core.dao.activity.activityDao;
import com.lwyykj.core.service.ActivityService;
import cn.itcast.common.page.Pagination;

@Service("activityService")
public class ActivityServiceImpl implements ActivityService {
	@Resource
	private activityDao activityDao;
	@Resource
	private ActivityTypeDao activityTypeDao;

	public Pagination selectPaginationByQuery(Integer pageNo, String title, String time) {
		String style = "yy-MM-dd";
		int a = 0;
		int b = 0;
		StringBuilder sb = new StringBuilder();
		activityQuery activityQuery = new activityQuery();
		activityQuery.setOrderByClause("id desc");
		activityQuery.setPageSize(20);
		activityQuery.setPageNo(Pagination.cpn(pageNo));
		Criteria createCriteria = activityQuery.createCriteria();
		createCriteria.andIsDelEqualTo(false);
		if (title != null) {
			createCriteria.andTitleLike("%" + title + "%");
			sb.append("title=").append(title);
		}
		if (time != null && time != "") {
			String[] CanTime = time.split(" ~ ");
			a = FormatDate.formatDate(CanTime[0], style);
			b = FormatDate.formatDate(CanTime[1], style);
			if (a == b) {
				b += 86399;
			}
			createCriteria.andAddtimeBetween(a, b);
			sb.append("&time=").append(time);
		}
		List<activity> activities = activityDao.selectByExample(activityQuery);
		if (null != activities && activities.size() > 0) {
			for (activity activity : activities) {
				String des = activityTypeDao.selectByPrimaryKey(activity.getAcType()).getDes();
				if (des != null && des != "") {
					activity.setTypeName(des);
				}
			}
			// System.out.println("进来了");
			Pagination pagination = new Pagination(activityQuery.getPageNo(), activityQuery.getPageSize(),
					activityDao.countByExample(activityQuery), activities);
			String url = "activity/list.do";
			pagination.pageView(url, sb.toString());
			return pagination;
		}
		return null;
	}

	@Override
	public activity findById(Integer id) {
		activity activity = activityDao.selectByPrimaryKey(id);
		if (activity != null) {
			return activity;
		}
		return null;
	}

	@Override
	public int update(activity activity) {
		return activityDao.updateByPrimaryKeySelective(activity);
	}

	@Override
	public int save(activity activity) {
		return activityDao.insertSelective(activity);
	}

	@Override
	public int delete(Integer id) {
		return activityDao.updateById(id);
	}
}
