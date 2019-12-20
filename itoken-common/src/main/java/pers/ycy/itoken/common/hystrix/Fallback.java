package pers.ycy.itoken.common.hystrix;

import com.google.common.collect.Lists;
import pers.ycy.itoken.common.dto.BaseResult;
import pers.ycy.itoken.common.utils.MapperUtils;

/**
 * 通用的熔断方法
 */
public class Fallback {
	/**
	 *
	 * @return 502 从上游服务器接收到无效响应
	 */
	public static String badGateway() {
		BaseResult baseResult = BaseResult.notOk(Lists.newArrayList(new BaseResult.Error("502", "从上游服务器接收到无效响应")));
		try {
			return MapperUtils.obj2json(baseResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
