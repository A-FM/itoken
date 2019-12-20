package pers.ycy.itoken.web.posts.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pers.ycy.itoken.common.domain.TbPostsPost;
import pers.ycy.itoken.common.domain.TbSysUser;
import pers.ycy.itoken.common.dto.BaseResult;
import pers.ycy.itoken.common.utils.MapperUtils;
import pers.ycy.itoken.common.web.constants.WebConstants;
import pers.ycy.itoken.common.web.controller.BaseController;
import pers.ycy.itoken.web.posts.service.PostService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
public class PostController extends BaseController<TbPostsPost, PostService> {

	@Autowired
	private PostService postsService;

	@ModelAttribute
	public TbPostsPost tbPostsPost(String postGuid) {
		TbPostsPost tbPostsPost = null;

		if (StringUtils.isBlank(postGuid)) {
			tbPostsPost = new TbPostsPost();
		} else {
			String json = postsService.get(postGuid);
			try {
				tbPostsPost = MapperUtils.json2pojo(json, TbPostsPost.class);
			} catch (Exception e) {
				e.printStackTrace();
			}

			// 二次确认是否为空
			if (tbPostsPost == null) {
				tbPostsPost = new TbPostsPost();
			}
		}

		return tbPostsPost;
	}

	/**
	 * 跳转到首页
	 *
	 * @return
	 */
	@RequestMapping(value = {"","main"}, method = RequestMethod.GET)
	public String main() {
		return "main";
	}

	/**
	 * 跳转列表页
	 *
	 * @return
	 */
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String index() {
		return "index";
	}

	/**
	 * 跳转表单页面
	 *
	 * @return
	 */
	@RequestMapping(value = "form", method = RequestMethod.GET)
	public String form() {
		return "form";
	}

	/**
	 * 保存文章
	 *
	 * @param tbPostsPost
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(TbPostsPost tbPostsPost, HttpServletRequest request, RedirectAttributes redirectAttributes) throws Exception {
		// 初始化
		tbPostsPost.setTimePublished(new Date());
		tbPostsPost.setStatus("0");

		TbSysUser admin = (TbSysUser) request.getSession().getAttribute(WebConstants.SESSION_USER);
		String tbPostsPostJson = MapperUtils.obj2json(tbPostsPost);
		String json = postsService.save(tbPostsPostJson, admin.getUserCode());
		BaseResult baseResult = MapperUtils.json2pojo(json, BaseResult.class);

		redirectAttributes.addFlashAttribute("baseResult", baseResult);
		if (baseResult.getSuccess().endsWith("成功")) {
			return "redirect:/index";
		}
		return "redirect:/form";
	}
}
