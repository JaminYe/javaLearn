package cn.jaminye.designpatterns.jieshiqi;

/**
 * 解释器模式
 *
 * @author Jamin
 * @date 2021/3/16 16:51
 */
public class LogUtil {
	private static final String STR = "{}";

	public static String info(String msg, Object... objects) {
		StringBuilder stringBuilder = new StringBuilder(msg.length() + 100);
		//整个字符串的索引
		int index = 0;
		for (int i = 0; i < objects.length; i++) {
			//符号位置
			int index1 = msg.indexOf(STR, index);
			if (index1 == -1) {
				//没有&&开始
				if (index == 0) {
					return msg;
				}
				// 结束的时候
				else {
					stringBuilder.append(msg, index, msg.length());
					return stringBuilder.toString();
				}
			} else {
				//追加文字
				stringBuilder.append(msg, index, index1);
				stringBuilder.append(objects[i].toString());
				index = index1 + 2;

			}
		}

		stringBuilder.append(msg, index, msg.length());
		return stringBuilder.toString();
	}

	public static void main(String[] args) {
		//开头1
		System.out.println(LogUtil.info("{},年龄:{},性别:{},", "测试", 12, "男"));
		//结尾
		System.out.println(LogUtil.info("姓名{},年龄:{},性别:{}", "测试", 12, "男"));
		//开头+结尾
		System.out.println(LogUtil.info("{},年龄:{},性别:{}", "测试", 12, "男"));
		//	无
		System.out.println(LogUtil.info("姓名", "测试", 12, "男"));
	}


}
