package pers.ycy.itoken.service.sso.service;

import pers.ycy.itoken.common.domain.TbSysUser;

public interface LoginService {
	public TbSysUser login(String loginCode,String plantPassword);
}
