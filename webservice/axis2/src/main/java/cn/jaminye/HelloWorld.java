package cn.jaminye;

import org.apache.axis2.context.MessageContext;
import org.apache.axis2.context.ServiceGroupContext;

import javax.jws.WebParam;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * 测试
 *
 * @author Jamin
 * @date 2021/1/5 9:05
 */
public class HelloWorld {
	/**
	 * 无返回值
	 *
	 * @param message
	 * @author Jamin
	 * @date 2021/1/5 9:16
	 */
	public void testHello(@WebParam String message) {
		System.out.println(message);
	}

	/**
	 * 有返回值
	 *
	 * @param x
	 * @param y
	 * @return {@link int}
	 * @author Jamin
	 * @date 2021/1/5 9:15
	 */
	public int plus(@WebParam int x, @WebParam int y) {
		return x + y;
	}

	/**
	 * 上传字节
	 *
	 * @param imageByte
	 * @return {@link boolean}
	 * @author Jamin
	 * @date 2021/1/14 9:33
	 */
	public boolean uploadImageWithByte(byte[] imageByte) {
		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream("d:\\1.jpeg");
			fileOutputStream.write(imageByte, 0, imageByte.length);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (fileOutputStream != null) {
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * 无参返回数组
	 *
	 * @param
	 * @return {@link String[]}
	 * @author Jamin
	 * @date 2021/1/19 15:46
	 */
	public String[] returnArray() {
		String[] strings = {"1", "2", "3", "4"};
		return strings;
	}

	/**
	 * 返回一个对象
	 *
	 * @param
	 * @return {@link cn.jaminye.Person}
	 * @author Jamin
	 * @date 2021/1/19 15:49
	 */
	public Person returnPerson() {
		return new Person("1", "测试", "13");
	}

	/**
	 * 返回对象字节数组
	 *
	 * @param
	 * @return {@link byte[]}
	 * @author Jamin
	 * @date 2021/1/19 15:56
	 */
	public byte[] returnBytes() throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		Person person = new Person("1", "测试", "13");
		oos.writeObject(person);
		return bos.toByteArray();
	}

	/**
	 * 获取登陆信息
	 *
	 * @param
	 * @return {@link String}
	 * @author Jamin
	 * @date 2021/1/27 9:58
	 */
	public String getUserName() {
		ServiceGroupContext serviceGroupContext = MessageContext.getCurrentMessageContext().getServiceGroupContext();
		Object userName = serviceGroupContext.getProperty("userName");
		if (userName != null) {
			return userName.toString();
		} else {
			return "";
		}
	}

	/**
	 * 异步调用
	 *
	 * @param
	 * @return {@link String}
	 * @author Jamin
	 * @date 2021/1/29 9:27
	 */
	public String asynchronousCall() throws InterruptedException {
		System.out.println("======>进入方法");
		Thread.sleep(10000);
		System.out.println("=====>结束方法");
		return "end";
	}


}
