package cn.jaminye.util;

import org.apache.axis2.AxisFault;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.description.AxisDescription;
import org.apache.axis2.description.AxisModule;
import org.apache.axis2.modules.Module;
import org.apache.neethi.Assertion;
import org.apache.neethi.Policy;

/**
 * @author Jamin
 * @date 2021/2/4 17:06
 */
public class LoggingModule implements Module {
	@Override
	public void init(ConfigurationContext configContext, AxisModule module) throws AxisFault {
		System.out.println("======================>init");
	}

	@Override
	public void engageNotify(AxisDescription axisDescription) throws AxisFault {

	}

	@Override
	public boolean canSupportAssertion(Assertion assertion) {
		return false;
	}

	@Override
	public void applyPolicy(Policy policy, AxisDescription axisDescription) throws AxisFault {

	}

	@Override
	public void shutdown(ConfigurationContext configurationContext) throws AxisFault {
		System.out.println("=========================shutdown");
	}
}
