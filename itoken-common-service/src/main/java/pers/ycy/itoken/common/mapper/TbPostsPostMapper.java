package pers.ycy.itoken.common.mapper;

import org.apache.ibatis.annotations.CacheNamespace;
import pers.ycy.itoken.common.domain.TbPostsPost;
import pers.ycy.itoken.common.utils.RedisCache;
import tk.mybatis.mapper.MyMapper;

@CacheNamespace(implementation = RedisCache.class)
public interface TbPostsPostMapper extends MyMapper<TbPostsPost> {
}