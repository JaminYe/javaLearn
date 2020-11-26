package cn.jaminye.zklock.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class TestToken {


	public static void main(String[] args) {


		//fucswitch.similarPhoneNum.url
		//http://10.243.30.128:8001/shopservice/services/logistics/ISimilarNumService?wsdl


		try {
			Map<String, String> map = new TreeMap<String, String>();
			//map.put("activityId", "114");
			map.put("eip_serv_id", "app.getIsNewVersionAndRule");

			// map.put("sceneId", "AC20201119976413");
			// map.put("sceneId1", "AC20201119232551");
			// map.put("cityName", "芜湖");

			Set<String> keys = map.keySet();
			StringBuilder sb = new StringBuilder();
			for (String key : keys) {
				sb.append(key).append("=").append(map.get(key)).append("&");
			}
			String param = sb.substring(0, sb.length() - 1);
			System.out.println(param + "&token=" + getMD5String(param, "UTF-8"));
			//	System.out.println(">>>>>>>>>"+new TestToken().getMD5String(param, "UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String getMD5String(String plainText, String charset) throws UnsupportedEncodingException {
		plainText += "&key=SD7B3L3P";
		StringBuffer buf = new StringBuffer("");
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes(charset));
			byte b[] = md.digest();
			int i;
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return buf.toString();
	}
}