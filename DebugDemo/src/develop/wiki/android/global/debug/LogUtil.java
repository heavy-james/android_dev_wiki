package develop.wiki.android.global.debug;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.google.gson.Gson;

import android.content.Context;
import android.util.Log;

public class LogUtil {
	private static final String TAG = "android_dev_wiki_log";
	private static Context mContext;
	private static boolean mDebugMode = false;
	private static boolean mSaveLog = false;
	private static boolean mReportLog = false;
	private static int mLogLevel = 1;
	private static String mSavePath = "/sdcard/android_dev_wiki_log";
	private static String mReportServerUrl = "";
	private static boolean mAppTerminated = false;
	public static final int LEVEL_VERBOSE = 0;
	public static final int LEVEL_DEBUG = 1;
	public static final int LEVEL_INFO = 2;
	public static final int LEVEL_WARN = 3;
	public static final int LEVEL_ERROR = 4;

	public static void init(Context context, int logLevel, String savePath,
			String reportServerUrl) {
		mContext = context;
		mLogLevel = logLevel;
		mSavePath = savePath;
		mReportServerUrl = reportServerUrl;
	}

	public static void setMode(boolean debug, boolean saveLog, boolean reportLog) {
		mDebugMode = debug;
		mSaveLog = saveLog;
		mReportLog = reportLog;
	}

	public static void v(String tag, String content) {
		if (canPrint(LEVEL_VERBOSE)) {
			Log.v(TAG, "[" + tag + "]:" + content);
		}
		if (mSaveLog) {
			Writer.recordBaseInfoLog(new BaseInfo(TAG, "[" + tag + "]:"
					+ content));
		}
	}

	public static void d(String tag, String content) {
		if (canPrint(LEVEL_VERBOSE)) {
			Log.v(TAG, "[" + tag + "]:" + content);
		}
		if (mSaveLog) {
			Writer.recordBaseInfoLog(new BaseInfo(TAG, "[" + tag + "]:"
					+ content));
		}
	}

	public static void i(String tag, String content) {
		if (canPrint(LEVEL_VERBOSE)) {
			Log.v(TAG, "[" + tag + "]:" + content);
		}
		if (mSaveLog) {
			Writer.recordBaseInfoLog(new BaseInfo(TAG, "[" + tag + "]:"
					+ content));
		}
	}

	public static void w(String tag, String content) {
		if (canPrint(LEVEL_VERBOSE)) {
			Log.v(TAG, "[" + tag + "]:" + content);
		}
		if (mSaveLog) {
			Writer.recordBaseInfoLog(new BaseInfo(TAG, "[" + tag + "]:"
					+ content));
		}
	}

	public static void e(String tag, String content) {
		if (canPrint(LEVEL_VERBOSE)) {
			Log.v(TAG, "[" + tag + "]:" + content);
		}
		if (mSaveLog) {
			Writer.recordBaseInfoLog(new BaseInfo(TAG, "[" + tag + "]:"
					+ content));
		}
	}

	private static boolean canPrint(int logLevel) {
		return mDebugMode || logLevel >= mLogLevel;
	}

	public static void releaseSource() {
		mAppTerminated = true;
	}

	static class BaseInfo {
		public String mTag = "";
		public String mContent = "";

		public BaseInfo(String tag, String content) {
			mTag = tag;
			mContent = content;
		}
	}

	static class ActionInfo {
		public String mTag = "";
		public String mContent = "";

		public ActionInfo(String tag, String content) {
			mTag = tag;
			mContent = content;
		}
	}

	static class Writer {

		public static ConcurrentLinkedQueue tempQueue = new ConcurrentLinkedQueue<Object>();

		/**
		 * 记录基本信息 头
		 * 
		 * @param bi
		 */
		public static synchronized void recordBaseInfoLog(BaseInfo bi) {
			tempQueue.add(bi);
			if (!WriteThread.isWriteThreadLive) {// 监察写线程是否工作中，没有 则创建
				new WriteThread().start();
			}
		}

		/**
		 * 记录行为信息
		 * 
		 * @param ai
		 */
		public static synchronized void recordActionInfoLog(ActionInfo ai) {
			tempQueue.add(ai);
			if (!WriteThread.isWriteThreadLive) {
				new WriteThread().start();
			}
		}

		/**
		 * 打开日志文件并写入日志
		 * 
		 * @return
		 * **/
		public static void recordStringLog(String text) {// 新建或打开日志文件
			File file = new File(mSavePath);
			if (!file.exists()) {
				file.getParentFile().mkdirs();
				try {
					file.createNewFile();
				} catch (IOException e) {
					Log.e(TAG, "行为日志：在" + mSavePath + "创建文件失败！" + e);
				}
			}
			FileWriter filerWriter = null;
			BufferedWriter bufWriter = null;
			try {
				filerWriter = new FileWriter(file, true);// 后面这个参数代表是不是要接上文件中原来的数据，不进行覆盖
				bufWriter = new BufferedWriter(filerWriter);
				bufWriter.write(text);
				bufWriter.newLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (bufWriter != null) {
					try {
						bufWriter.close();
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}
				if (filerWriter != null) {
					try {
						filerWriter.close();
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}
			}
		}

		/**
		 * 判断日志文件是否存在
		 * 
		 * @return
		 */
		public static boolean isExitLogFile() {
			File file = new File(mSavePath);
			if (file.exists() && file.length() > 3) {
				return true;
			} else {
				return false;
			}
		}

		/**
		 * 删除日志文件
		 */
		public static void deleteLogFile() {
			File file = new File(mSavePath);
			if (file.exists()) {
				file.delete();
			}
		}
	}

	static class WriteThread extends Thread {
		public static boolean isWriteThreadLive = false;// 写日志线程是否已经在运行了

		public WriteThread() {
			isWriteThreadLive = true;
		}

		@Override
		public void run() {
			isWriteThreadLive = true;
			Gson gson = new Gson();
			while (!Writer.tempQueue.isEmpty()) {// 对列不空时
				try {
					// 写日志到SD卡
					Writer.recordStringLog(gson.toJson(Writer.tempQueue.poll()));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			isWriteThreadLive = false;// 队列中的日志都写完了，关闭线程（也可以常开 要测试下）
		}
	}
}
