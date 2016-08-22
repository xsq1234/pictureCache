package cn.xie.zhbj.utlis;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

public class MemoryCacheUtils {
	// 容易造成内存溢出
	// private HashMap<String, Bitmap> mMemoryCache = new HashMap<String,
	// Bitmap>();
	// 软引用改造
	// 从 Android 2.3 (API Level 9)开始，垃圾回收器会更倾向于回收持有软引用或弱引用的对象，这让软引用和弱引用变得不再可靠。
	// private HashMap<String, SoftReference<Bitmap>> mMemoryCache = new
	// HashMap<String, SoftReference<Bitmap>>();

	// LruCache
	private LruCache<String, Bitmap> mLruCache;

	public MemoryCacheUtils() {
		// LruCache: LRU, least recenttly used; 最近最少使用算法, 能够将最近最少使用的图片对象自动移除
		// 原理: 定义一个内存上限, 一旦超出, 就根据LRU算法移除图片对象, 从而保证内存一直在合理的范围之内
		// 16M
		long maxMemory = Runtime.getRuntime().maxMemory();// 当前虚拟机分配的最大内存
		mLruCache = new LruCache<String, Bitmap>((int) maxMemory / 8) {// 设置内存上限为总内存八分之一

			// 返回每个图片对象的大小
			@Override
			protected int sizeOf(String key, Bitmap value) {
				// value.getByteCount();
				// return getRowBytes() * getHeight();
				// 图片大小 = 每一个行字节数 * 高度
				int size = value.getRowBytes() * value.getHeight();

				return size;
			}

		};

	}

	// 写内存缓存
	public void setMemoryCache(Bitmap bitmap, String url) {
		// 对bitmap进行软引用包装
		// SoftReference<Bitmap> softBitmap = new SoftReference<Bitmap>(bitmap);
		// mMemoryCache.put(url, softBitmap);
		mLruCache.put(url, bitmap);
	}

	// 读内存缓存
	public Bitmap getMemoryCache(String url) {
		// SoftReference<Bitmap> softBitmap = mMemoryCache.get(url);
		//
		// if (softBitmap != null) {//一定要判断是否为空, 因为可能被回收
		// Bitmap bitmap = softBitmap.get();
		// return bitmap;
		// }

		return mLruCache.get(url);
	}

}
