package tk.mybatis.mapper;


import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 自己的 Mapper
 * 特别注意，该接口不能被扫描到，否则会出错
 * <p>Title: MyMapper</p>
 * <p>Description: </p>
 *
 */
/* tk.mybatis所用, 用于生成一些基础的查询 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}