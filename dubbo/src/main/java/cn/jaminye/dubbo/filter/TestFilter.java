package cn.jaminye.dubbo.filter;

import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * dubbo过滤器
 *
 * @author Jamin
 * @date 2020/8/30 16:20
 */
//设置过滤器在哪里使用
@Activate(group = {CommonConstants.PROVIDER, CommonConstants.CONSUMER})
public class TestFilter implements Filter {
    Logger logger = LoggerFactory.getLogger(TestFilter.class);

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        // logger.debug("测试");
        System.out.println("测试");
        return invoker.invoke(invocation);
    }
}
