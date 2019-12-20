package pers.ycy.itoken.common.service;

import com.github.pagehelper.PageInfo;
import pers.ycy.itoken.common.domain.BaseDomain;

public interface BaseService<T extends BaseDomain> {
	int insert(T t,String createBy);

	int delete(T t);

	int update(T t,String updateBy);

	T selectOne(T t);

	int count(T t);

	PageInfo<T> Page(int pageNum, int pageSize,T t);
}
