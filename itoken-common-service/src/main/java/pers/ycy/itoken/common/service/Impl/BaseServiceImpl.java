package pers.ycy.itoken.common.service.Impl;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.ycy.itoken.common.domain.BaseDomain;
import pers.ycy.itoken.common.service.BaseService;
import tk.mybatis.mapper.MyMapper;

import java.util.Date;

@Service
@Transactional(readOnly = true)
/* 自己实现的基础service 用于基本操作的CRUD */
public abstract class BaseServiceImpl<T extends BaseDomain, D extends MyMapper<T>> implements BaseService<T> {

	@Autowired
	private D dao;


	@Override
	@Transactional(readOnly = false)
	public int insert(T t,String CreateBy) {
		t.setCreateBy(CreateBy);
		t.setCreateDate(new Date());
		t.setUpdateBy(CreateBy);
		t.setUpdateDate(new Date());
		return dao.insert(t);
	}

	@Override
	@Transactional(readOnly = false)
	public int delete(T t) {
		return dao.delete(t);
	}

	@Override
	@Transactional(readOnly = false)
	public int update(T t,String updateBy) {
		t.setUpdateDate(new Date());
		t.setUpdateBy(updateBy);
		return dao.updateByPrimaryKey(t);
	}

	@Override
	public T selectOne(T t) {
		return dao.selectOne(t);
	}

	@Override
	public int count(T t) {
		return dao.selectCount(t);
	}

	/**
	 * 分页    给的查询条件可能为空, 在这里需要做一下判断   分类处理.   但是dao.select(t) 里面已经实现过了, 这样有点瞎扯淡....
	 * @param pageNum 当前页
	 * @param pageSize 每页条数
	 * @param t 查询条件
	 * @return
	 */
	@Override
	public PageInfo<T> Page(int pageNum, int pageSize,T t) {
		PageMethod.startPage(pageNum, pageSize);
		if(t==null){
			return new PageInfo<>(dao.selectAll());
		}else{
			return new PageInfo<>(dao.select(t));
		}
	}
}
