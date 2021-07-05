package cn.jaminye.util;

import org.apache.axis2.AxisFault;
import org.apache.axis2.context.MessageContext;
import org.apache.axis2.engine.Handler;
import org.apache.axis2.handlers.AbstractHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Jamin
 * @date 2021/2/4 17:08
 */
public class LogHandler extends AbstractHandler implements Handler {
	private static Log log = LogFactory.getLog(LogHandler.class);

	@Override
	public InvocationResponse invoke(MessageContext msgContext) throws AxisFault {
		log.info("returnMsg====================>{}" + msgContext.getEnvelope().toString());
		return InvocationResponse.CONTINUE;
	}
}
