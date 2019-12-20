package pers.ycy.itoken.common.mapper;

import org.apache.ibatis.annotations.CacheNamespace;
import pers.ycy.itoken.common.domain.TbSysUser;
import pers.ycy.itoken.common.utils.RedisCache;
import tk.mybatis.mapper.MyMapper;

@CacheNamespace(implementation = RedisCache.class)
public interface TbSysUserMapper extends MyMapper<TbSysUser> {
}