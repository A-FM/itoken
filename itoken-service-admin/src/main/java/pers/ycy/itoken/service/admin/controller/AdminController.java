package pers.ycy.itoken.service.admin.controller;

import com.github.pagehelper.PageInfo;
import javafx.geometry.Pos;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.ycy.itoken.common.domain.BaseDomain;
import pers.ycy.itoken.common.domain.TbPostsPost;
import pers.ycy.itoken.common.domain.TbSysUser;
import pers.ycy.itoken.common.dto.BaseResult;
import pers.ycy.itoken.common.utils.EnCodeAlgorithm;
import pers.ycy.itoken.common.utils.MapperUtils;
import pers.ycy.itoken.service.admin.service.AdminService;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping(value = "v1/admins")
public class AdminController {
	@Autowired
	private AdminService<TbSysUser> adminService;

	/**
	 * 根据ID获取管理员
	 * @param userCode 管理员ID
	 * @return  Json对象
	 */
	@RequestMapping(value = "{userCode}",method = RequestMethod.GET)
	public BaseResult get(@PathVariable(required = true,value = "userCode")String userCode){
		TbSysUser tbSysUser = new TbSysUser();
		tbSysUser.setUserCode(userCode);
		TbSysUser obj = adminService.selectOne(tbSysUser);
		return BaseResult.ok(obj);
	}

	/**
	 * 分页
	 * @param pageNum 第几页
	 * @param pageSize 一页多少个
	 * @param tbSysUserJson Json化分页查询对象条件
	 * @return BaseResult 对象的 成功与否反馈
	 * @throws Exception Json2Pojo 出错
	 */
	@RequestMapping(value = "page/{pageNum}/{pageSize}",method = RequestMethod.GET)
	public BaseResult page(@PathVariable(required = true,value = "pageNum") int pageNum, @PathVariable(required = true,value = "pageSize") int pageSize, @RequestParam(required = false) String tbSysUserJson) throws Exception {

		/* 因为  tbSysUserJson是可以为空的, 如果什么都不搞  直接Json2Pojo放进去的话 可能会报NullException,
		* 然后这里先将其设为null, 如果为null的话  BaserServiceImpl中有 特殊处理.
		* */
		TbSysUser tbSysUser = null;
		if(StringUtils.isNotBlank(tbSysUserJson)){
			tbSysUser = MapperUtils.json2pojo(tbSysUserJson,TbSysUser.class);
		}

		PageInfo pageInfo = adminService.Page(pageNum, pageSize, tbSysUser);


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

		return BaseResult.ok(list,cursor);
	}

	/**
	 * 保存管理员 , 根据Restful风格API设计 ,此处 无value 链接.
	 * @param tbSysUserJson Json化管理员数据
	 * @param optsBy    本次操作行为所属者
	 * @return BaseResult对象,用于说明操作是否成功
	 * @throws Exception Json2Pojo出错.
	 */
	@RequestMapping(method = RequestMethod.POST)
	public BaseResult save(@RequestParam(required = true) String tbSysUserJson,@RequestParam(required = true) String optsBy) throws Exception {
		int result = 0;
		TbSysUser tbSysUser = MapperUtils.json2pojo(tbSysUserJson,TbSysUser.class);
		if(tbSysUser!=null){
			String passWord = EnCodeAlgorithm.enCodePassWord(tbSysUser.getPassword());
			tbSysUser.setPassword(passWord);
			/* user的ID为空, 可以判断出示新增用户 */
			if(StringUtils.isBlank(tbSysUser.getUserCode())){
				tbSysUser.setUserCode(UUID.randomUUID().toString());
				result = adminService.insert(tbSysUser,optsBy);
			/* 如果有Id的话就是 修改用户了. */
			}else{
				result = adminService.update(tbSysUser,optsBy);
			}
			/* 最少有一行数据被影响 */
			if(result>0){
				return BaseResult.ok("保存管理员成功");
			}
		}
		return BaseResult.ok("保存管理员失败");
	}
}
