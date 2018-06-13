package com.lwyykj.core.service.impl;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.lwyykj.core.bean.activity.ActivityType;
import com.lwyykj.core.bean.activity.ActivityTypeQuery;
import com.lwyykj.core.bean.activity.ActivityTypeQuery.Criteria;
import com.lwyykj.core.dao.activity.ActivityTypeDao;
import com.lwyykj.core.service.AcTypeService;
import cn.itcast.common.page.Pagination;

/**
 * 活动分类服务
 * 
 * @author john
 *
 */
@Service("acTypeService")
public class AcTypeServiceImpl implements AcTypeService {
	@Resource
	private ActivityTypeDao activityTypeDao;

	@Override
	public Pagination findAll(Integer pageNo, String des) {
		StringBuilder sb = new StringBuilder();
		ActivityTypeQuery activityTypeQuery = new ActivityTypeQuery();
		Criteria createCriteria = activityTypeQuery.createCriteria();
		activityTypeQuery.setOrderByClause("id desc");
		activityTypeQuery.setPageNo(Pagination.cpn(pageNo));
		activityTypeQuery.setPageSize(20);
		createCriteria.andIsDelEqualTo(false);
		if (null != des) {
			createCriteria.andDesLike("%" + des + "%");
			sb.append("des=").append(des);
		}
		List<ActivityType> activityTypes = activityTypeDao.selectByExample(activityTypeQuery);
		if (null != activityTypes && activityTypes.size() > 0) {
			Pagination pagination = new Pagination(activityTypeQuery.getPageNo(), activityTypeQuery.getPageSize(),
					activityTypeDao.countByExample(activityTypeQuery), activityTypes);
			String url = "activity/toAcType";
			pagination.pageView(url, sb.toString());
			return pagination;
		}

		return null;
	}

	@Override
	public ActivityType findById(Integer id) {
		return activityTypeDao.selectByPrimaryKey(id);
	}

	@Override
	public int saveOrUpdate(ActivityType activityType) {
		if (activityType.getId() != null && activityType.getId() > 0) {
			return activityTypeDao.updateByPrimaryKeySelective(activityType);
		} else {
			activityType.setIsDel(false);
			activityType.setAddtime((int) (new Date().getTime() / 1000));
			return activityTypeDao.insertSelective(activityType);
		}
	}

	@Override
	public int delById(Integer id) {
		return activityTypeDao.delById(id);
	}

}
