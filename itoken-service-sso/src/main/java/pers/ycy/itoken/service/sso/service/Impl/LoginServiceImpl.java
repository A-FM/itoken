package pers.ycy.itoken.service.sso.service.Impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.ycy.itoken.common.domain.TbSysUser;
import pers.ycy.itoken.common.utils.EnCodeAlgorithm;
import pers.ycy.itoken.common.utils.MapperUtils;
import pers.ycy.itoken.service.sso.mapper.TbSysUserMapper;
import pers.ycy.itoken.service.sso.service.LoginService;
import pers.ycy.itoken.service.sso.service.consumer.RedisService;
import tk.mybatis.mapper.entity.Example;



@Service
public class LoginServiceImpl implements LoginService {

	private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

	@Autowired
	private RedisService redisService;

	@Autowired
	private TbSysUserMapper tbSysUserMapper;

	@Override
	public TbSysUser login(String loginCode, String plantPassword) {

		TbSysUser tbSysUser = null;

		String json = redisService.get(loginCode);
		/* 缓存中没有数据 从数据库中取数据, 并且放到缓存当中, 设置过期时间为60*60*24 */
		if (json == null) {
			Example example = new Example(TbSysUser.class);
			example.createCriteria().andEqualTo("loginCode", loginCode);

			tbSysUser = tbSysUserMapper.selectOneByExample(example);
			String psssword = EnCodeAlgorithm.enCodePassWord(plantPassword);
			System.out.println(psssword);
			if (psssword!=null && psssword.equals(tbSysUser.getPassword())) {
				try {
					redisService.put(loginCode, MapperUtils.obj2json(tbSysUser), 60 * 60 * 24);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return tbSysUser;
			} else {
				return null;
			}
		}
		/* 缓存中有数据 */
		else {
			try {
				tbSysUser = MapperUtils.json2pojo(json, TbSysUser.class);
				if(tbSysUser.getPassword().equals(EnCodeAlgorithm.enCodePassWord(plantPassword))){
					return tbSysUser;
				}else{
					return null;
				}
			} catch (Exception e) {
				logger.error("也不知道咋回事  反正现在是熔断了.{}",e.getMessage());
			}
		}
		return null;
	}
}
