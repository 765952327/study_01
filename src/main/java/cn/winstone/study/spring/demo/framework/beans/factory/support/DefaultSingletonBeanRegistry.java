package cn.winstone.study.spring.demo.framework.beans.factory.support;

import cn.winstone.study.spring.demo.framework.beans.factory.config.SingletonBeanRegistry;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Winstone
 * @date 2021/12/6 - 8:20 下午
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry{
	private Map<String, Object> singletonObjects = new HashMap<>();

	@Override
	public Object getSingleton(String beanName) {
		return singletonObjects.get(beanName);
	}

	protected void addSingleton(String beanName, Object singletonObject) {
		singletonObjects.put(beanName, singletonObject);
	}
}
