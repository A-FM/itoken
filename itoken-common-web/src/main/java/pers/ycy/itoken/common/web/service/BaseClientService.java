package pers.ycy.itoken.common.web.service;


import pers.ycy.itoken.common.hystrix.Fallback;

/**
 * 通用服务消费者接口
 * <p>Title: BaseClientService</p>
 * <p>Description: </p>
 *
 * @author Lusifer
 * @version 1.0.0
 * @date 2018/8/12 13:56
 */
public interface BaseClientService {
    default String page(int pageNum, int pageSize, String domainJson) {
        return Fallback.badGateway();
    }
}
