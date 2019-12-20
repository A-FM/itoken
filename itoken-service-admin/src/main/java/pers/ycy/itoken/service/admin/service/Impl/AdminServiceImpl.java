package pers.ycy.itoken.service.admin.service.Impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.ycy.itoken.common.domain.TbSysUser;
import pers.ycy.itoken.common.mapper.TbSysUserMapper;
import pers.ycy.itoken.common.service.Impl.BaseServiceImpl;
import pers.ycy.itoken.service.admin.service.AdminService;


@Service
@Transactional(readOnly = true)
public class AdminServiceImpl extends BaseServiceImpl<TbSysUser, TbSysUserMapper> implements AdminService<TbSysUser> {

}
