package pers.ycy.itoken.common.dto;

import com.sun.org.apache.regexp.internal.RE;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
@Data
public class BaseResult implements Serializable {
	private static final String RESULT_OK = "ok";
	private static final String RESULT_NOT_OK = "not_ok";
	private static final String SUCCESS = "成功操作";


	private String result;
	private Object data;
	private String success;
	private Cursor cursor;
	private List<Error> errors;


	@Data
	public static class Cursor {
		private int total;
		private int offset;
		private int limit;
	}

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Error {
		private String field;
		private String message;
	}

	/**
	 * @param result 返回的结果 如ok / not_ok
	 * @param data 返回的数据,为对象数组
	 * @param success 成功操作的提示语
	 * @param cursor 分页数据
	 * @param errors 错误状态数组
	 */
	private static BaseResult createResult(String result, Object data, String success, Cursor cursor, List<Error> errors) {
		BaseResult baseResult = new BaseResult();
		baseResult.setCursor(cursor);
		baseResult.setData(data);
		baseResult.setErrors(errors);
		baseResult.setResult(result);
		baseResult.setSuccess(success);
		return baseResult;
	}

	public static BaseResult ok() {
		return createResult(RESULT_OK, null, SUCCESS, null, null);
	}

	public static BaseResult ok(String success){
		return createResult(RESULT_OK,null,success,null,null);
	}

	public static BaseResult ok(Object data) {
		return createResult(RESULT_OK, data, SUCCESS, null, null);
	}

	public static BaseResult ok(Object data,Cursor cursor) {
		return createResult(RESULT_OK, data, SUCCESS, cursor, null);
	}

	public static BaseResult notOk(List<BaseResult.Error> errors) {
		return createResult(RESULT_NOT_OK, null, SUCCESS, null, errors);
	}

}
