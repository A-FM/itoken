package pers.ycy.itoken.service.posts.mapper;

import org.springframework.stereotype.Repository;
import pers.ycy.itoken.common.domain.TbPostsPost;
import tk.mybatis.mapper.MyMapper;

@Repository
public interface TbPostsPostExtendMapper extends MyMapper<TbPostsPost> {
}