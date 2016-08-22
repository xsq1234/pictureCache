package cn.xie.zhbj.utlis;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Environment;

public class LocalCacheUtils {

	public static final String LOCAL_CACHE_DIR = Environment
			.getExternalStorageDirectory().getAbsolutePath() + "/zhbj/cache";

	// 写本地缓存
	public void setLocalCache(Bitmap bitmap, String url) {
		// 创建文件夹
		File dir = new File(LOCAL_CACHE_DIR);
		if (!dir.exists() || !dir.isDirectory()) {// 判断文件夹是否存在
			dir.mkdirs();// 创建文件夹
		}

		try {
			// MD5(url)
			File cacheFile = new File(dir, MD5Encoder.encode(url));
			// 将图片对象压缩保存在本地文件中
			// 参1:压缩格式; 参2:压缩比例, 0-100, 100表示不压缩;参3:输出流
			bitmap.compress(CompressFormat.JPEG, 100, new FileOutputStream(
					cacheFile));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 读本地缓存
	public Bitmap getLocalCache(String url) {
		try {
			File cacheFile = new File(LOCAL_CACHE_DIR, MD5Encoder.encode(url));

			if (cacheFile.exists()) {// 缓存存在
				Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(
						cacheFile));
				return bitmap;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
