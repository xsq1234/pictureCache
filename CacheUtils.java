package cn.xie.zhbj.utlis;

import android.content.Context;

/**
 * 将缓存保存在sp中
 * 
 * @author gustas
 * 
 */
public class CacheUtils {
	public static void setCache(Context ctx, String url, String json) {

		PrefUtils.setString(ctx, url, json);
	}

	public static String getCache(Context ctx, String url) {
		String json = PrefUtils.getString(ctx, url, null);
		return json;
	}
}
