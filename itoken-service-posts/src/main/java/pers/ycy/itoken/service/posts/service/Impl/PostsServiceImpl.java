package pers.ycy.itoken.service.posts.service.Impl;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.ycy.itoken.common.domain.TbPostsPost;
import pers.ycy.itoken.common.mapper.TbPostsPostMapper;
import pers.ycy.itoken.common.service.Impl.BaseServiceImpl;
import pers.ycy.itoken.service.posts.service.PostsService;

/* 在继承了已有 service(BaseDomain)的 基础上 开放了可扩展性PostsService */
@Service
@Transactional(readOnly = true)
public class PostsServiceImpl extends BaseServiceImpl<TbPostsPost, TbPostsPostMapper> implements PostsService {
}
