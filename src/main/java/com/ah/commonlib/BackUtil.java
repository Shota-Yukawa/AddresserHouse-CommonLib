package com.ah.commonlib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class BackUtil {
	
	/**
	 * update/part で使用<br>
	 * リクエストのjson keyのテーブル名.カラム名をカラム名のみに絞る。valueはそのまま<br>
	 * 不正なリクエストボディの場合、nullを返す
	 * @param reqBody
	 * @return
	 */
	public static List<Map<String, Object>> shapeReqKeyToColumnCamel(List<Map<String, Object>> reqBody) {
		List<Map<String, Object>> resDataList = new ArrayList<>();
		for (Map<String, Object> reqData : reqBody) {
			Map<String, Object> newDataMap = new HashMap<>();
			for (Entry<String, Object> entry : reqData.entrySet()) {
				if (!entry.getKey().contains("."))
					return null;
				newDataMap.put(StringConverter.convertSnakeToCamel(entry.getKey().split("\\.")[1]), entry.getValue());
			}
			resDataList.add(newDataMap);
		}
		return resDataList;
	}
}
