package com.lwyykj.core.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.lwyykj.core.bean.activity.ActivityType;
import com.lwyykj.core.bean.activity.ActivityTypeQuery;
import com.lwyykj.core.bean.activity.ActivityTypeQuery.Criteria;
import com.lwyykj.core.dao.activity.ActivityTypeDao;
import com.lwyykj.core.service.TypeService;

@Service("typeService")
public class TypeServiceImpl implements TypeService {
	@Resource
	private ActivityTypeDao activityTypeDao;

	@Override
	public List<ActivityType> findAll() {
		ActivityTypeQuery activityTypeQuery = new ActivityTypeQuery();
		Criteria createCriteria = activityTypeQuery.createCriteria();
		createCriteria.andIsDelEqualTo(false);
		List<ActivityType> activityTypes = activityTypeDao.selectByExample(activityTypeQuery);
		if (null != activityTypes && activityTypes.size() > 0) {
			return activityTypes;
		}
		return null;
	}

}
