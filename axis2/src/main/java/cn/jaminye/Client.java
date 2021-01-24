package cn.jaminye;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.junit.jupiter.api.Test;

import javax.xml.namespace.QName;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 客户端
 *
 * @author Jamin
 * @date 2021/1/5 14:04
 */
public class Client {
	private RPCServiceClient rpcServiceClient;
	private OMFactory omFactory;
	private ServiceClient serviceClient;

	public static void main(String[] args) throws AxisFault {
		/*//使用rpc的方式调用    有返回值
		RPCServiceClient rpcServiceClient = new RPCServiceClient();
		Options options = rpcServiceClient.getOptions();
		//指定调用地址
		options.setTo(new EndpointReference("http://localhost:8080/axis2/services/HelloWorld"));
		//指定入参与出参类型
		Object[] inArgs = {1, 2};
		Class[] classes = {String.class};
		QName qName = new QName("http://jaminye.cn", "plus");
		System.out.println(rpcServiceClient.invokeBlocking(qName, inArgs, classes)[0]);*/


		/*// 使用rpc的方式调用   无返回值
		Options options1 = rpcServiceClient.getOptions();
		//地址
		options1.setTo(new EndpointReference("http://localhost:8080/axis2/services/HelloWorld/testHello"));
		//参数
		Object[] inArgs1 = {"测试"};
		//命名空间
		QName qName1 = new QName("http://jaminye.cn", "testHello");
		rpcServiceClient.invokeRobust(qName1, inArgs1);*/


		// 命名传参
		ServiceClient serviceClient = new ServiceClient();
		Options options = serviceClient.getOptions();
		options.setTo(new EndpointReference("http://localhost:8080/axis2/services/HelloWorld"));
		options.setAction("http://jaminye.cn/HelloWorld");
		OMFactory omFactory = OMAbstractFactory.getOMFactory();
		OMNamespace omNamespace = omFactory.createOMNamespace("http://jaminye.cn", "");
		OMElement method = omFactory.createOMElement("plus", omNamespace);
		OMElement paramsX = omFactory.createOMElement("x", omNamespace);
		paramsX.setText("1");
		OMElement paramsY = omFactory.createOMElement("y", omNamespace);
		paramsY.setText("2");
		method.addChild(paramsX);
		method.addChild(paramsY);
		method.build();
		OMElement result = serviceClient.sendReceive(method);
		//返回原来的xml
		System.out.println(result);
		//获取真正的返回值
		System.out.println(result.getFirstElement().getText());


		/*options.setTo(new EndpointReference("http://localhost:8080/axis2/services/HelloWorld"));
		options.setAction("http://jaminye.cn/HelloWorld");
		OMFactory omFactory = OMAbstractFactory.getOMFactory();
		OMNamespace omNamespace = omFactory.createOMNamespace("http://jaminye.cn", "");
		OMElement method = omFactory.createOMElement("testHello", omNamespace);
		OMElement paramsName = omFactory.createOMElement("message", omNamespace);
		paramsName.setText("测试");
		method.addChild(paramsName);
		method.build();
		serviceClient.sendRobust(method);*/
	}

	/**
	 * 返回对象
	 *
	 * @throws AxisFault
	 */
	@Test
	public void testPerson() throws AxisFault {
		RPCServiceClient rpcServiceClient = new RPCServiceClient();
		Options options = rpcServiceClient.getOptions();
		//指定调用地址
		options.setTo(new EndpointReference("http://localhost:8080/axis2/services/HelloWorld"));
		Object[] inArgs = {};
		Class[] classes = {Person.class};
		QName qName = new QName("http://jaminye.cn", "returnPerson");
		System.out.println((Person) rpcServiceClient.invokeBlocking(qName, inArgs, classes)[0]);
	}

	/**
	 * 字节数组入参
	 *
	 * @throws IOException
	 */
	@Test
	public void testArray() throws IOException {
		RPCServiceClient rpcServiceClient = new RPCServiceClient();
		Options options = rpcServiceClient.getOptions();
		//指定调用地址
		options.setTo(new EndpointReference("http://localhost:8080/axis2/services/HelloWorld"));
		new File("d:\\1.jpeg").createNewFile();
		File file = new File("D:\\Windows\\Pictures\\Camera Roll\\2.jpg");
		FileInputStream fis = new FileInputStream(file);
		byte[] bytes = new byte[(int) file.length()];
		fis.read(bytes);
		System.out.println("文件长度====" + file.length());
		Object[] inArgs = {bytes};
		Class[] classes = {Boolean.class};
		QName qName = new QName("http://jaminye.cn", "uploadImageWithByte");
		fis.close();
		System.out.println(rpcServiceClient.invokeBlocking(qName, inArgs, classes)[0]);
	}
}
