package cn.xie.zhbj.utlis;

import android.graphics.Bitmap;
import android.widget.ImageView;

public class MyBitMapUtils {
	private NetCacheUtils mNetUtils;
	private LocalCacheUtils localCacheUtils;
	private MemoryCacheUtils mMemoryUtils;

	public MyBitMapUtils() {
		localCacheUtils = new LocalCacheUtils();
		mMemoryUtils = new MemoryCacheUtils();
		mNetUtils = new NetCacheUtils(localCacheUtils, mMemoryUtils);
	}

	public void disPlay(ImageView imageView, String url) {
/*		// 加载内存缓存
		Bitmap bitmap = mMemoryUtils.getMemoryCache(url);
		if (bitmap != null) {
			imageView.setImageBitmap(bitmap);
			System.out.println("从内存加载缓存啦");
			return;
		}

		// 加载本地缓存
		bitmap = localCacheUtils.getLocalCache(url);
		if (bitmap != null) {
			// 本地缓存存在
			imageView.setImageBitmap(bitmap);
			System.out.println("从本地加载缓存啦");

			// 写内存缓存
			mMemoryUtils.setMemoryCache(bitmap, url);
			return;
		}*/

		mNetUtils.getBitmapFromNet(imageView, url);
	}

}
