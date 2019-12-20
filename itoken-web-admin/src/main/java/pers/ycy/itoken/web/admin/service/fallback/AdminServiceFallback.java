package pers.ycy.itoken.web.admin.service.fallback;

import org.springframework.stereotype.Component;
import pers.ycy.itoken.common.domain.TbSysUser;
import pers.ycy.itoken.common.hystrix.Fallback;
import pers.ycy.itoken.common.utils.MapperUtils;
import pers.ycy.itoken.web.admin.service.AdminService;

@Component
public class AdminServiceFallback implements AdminService {

	@Override
	public String get(String userCode) {
		try {
			String json = MapperUtils.obj2json(new TbSysUser());
			return json;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String save(String tbSysUserJson, String optsBy) {
		return Fallback.badGateway();
	}

	@Override
	public String page(int pageNum, int pageSize, String tbSysUserJson) {
		return Fallback.badGateway();
	}
}
