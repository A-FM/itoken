package pers.ycy.itoken.service.posts.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.ycy.itoken.common.domain.TbPostsPost;
import pers.ycy.itoken.common.dto.BaseResult;
import pers.ycy.itoken.common.utils.MapperUtils;
import pers.ycy.itoken.service.posts.service.PostsService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "v1/posts")
public class PostController {
	@Autowired
	private PostsService postsService;

	/**
	 * 根据Id获取文章
	 *
	 * @param postGuid 文章Id
	 * @return Json形式的文章对象
	 */
	@RequestMapping(value = "{postGuid}", method = RequestMethod.GET)
	public BaseResult get(@PathVariable(required = true, value = "postGuid") String postGuid) {
		TbPostsPost tbPostsPost = new TbPostsPost();
		tbPostsPost.setPostGuid(postGuid);
		TbPostsPost obj = postsService.selectOne(tbPostsPost);
		return BaseResult.ok(obj);
	}


	/**
	 * 分页
	 *
	 * @param pageNum         第几页
	 * @param pageSize        一页多少个
	 * @param tbPostsPostJson Json化分页查询对象条件
	 * @return BaseResult 对象的 成功与否反馈
	 * @throws Exception Json2Pojo 出错
	 */
	@ApiOperation(value = "文章服务分页查询")
	@ApiImplicitParams({@ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "int", paramType = "path"),
			@ApiImplicitParam(name = "pageSize", value = "笔数", required = true, dataType = "int", paramType = "path"),
			@ApiImplicitParam(name = "tbPostsPostJson", value = "文章对象 JSON 字符串", required = false, dataTypeClass = String.class, paramType = "json")
	})
	@RequestMapping(value = "page/{pageNum}/{pageSize}", method = RequestMethod.GET)
	public BaseResult page(@PathVariable(required = true, value = "pageNum") int pageNum, @PathVariable(required = true, value = "pageSize") int pageSize, @RequestParam(required = false) String tbPostsPostJson) throws Exception {

		/* 因为  tbPostsPostJson是可以为空的, 如果什么都不搞  直接Json2Pojo放进去的话 可能会报NullException,
		 * 然后这里先将其设为null, 如果为null的话  BaserServiceImpl中有 特殊处理.
		 * */

		TbPostsPost tbPostsPost = null;
		if (StringUtils.isNotBlank(tbPostsPostJson)) {
			tbPostsPost = MapperUtils.json2pojo(tbPostsPostJson, TbPostsPost.class);
		}

		PageInfo pageInfo = postsService.Page(pageNum, pageSize, tbPostsPost);


		// 分页之后的结果
		List list = pageInfo.getList();


		// 封装Cursor对象
		BaseResult.Cursor cursor = new BaseResult.Cursor();
		/* 总条数 */
		cursor.setTotal(new Long(pageInfo.getTotal()).intValue());
		/* 当前页 */
		cursor.setOffset(pageInfo.getPageNum());
		/* 每页显示条数 */
		cursor.setLimit(pageInfo.getPageSize());

		return BaseResult.ok(list, cursor);
	}

	/**
	 * 保存文章
	 *
	 * @param tbPostsPostJson Json格式化文章
	 * @param optsBy          操作人
	 * @return 保存成功与否信息
	 * @throws Exception Json2Pojo 问题.
	 */
	@RequestMapping(method = RequestMethod.POST)
	public BaseResult save(@RequestParam(required = true) String tbPostsPostJson, @RequestParam(required = true) String optsBy) throws Exception {
		int result = 0;
		TbPostsPost tbPostsPost = MapperUtils.json2pojo(tbPostsPostJson, TbPostsPost.class);
		if (tbPostsPost != null) {
			/* 文章的ID为空, 可以判断出示新增用户 */
			if (StringUtils.isBlank(tbPostsPost.getPostGuid())) {
				tbPostsPost.setPostGuid(UUID.randomUUID().toString());
				result = postsService.insert(tbPostsPost, optsBy);
				/* 如果有Id的话就是 修改文章了. */
			} else {
				result = postsService.update(tbPostsPost, optsBy);
			}
			/* 最少有一行数据被影响 */
			if (result > 0) {
				return BaseResult.ok("保存文章成功");
			}
		}
		return BaseResult.ok("保存文章失败");
	}
}
