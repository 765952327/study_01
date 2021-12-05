package cn.winstone.study.spring.demo.framework.beans.factory;

import cn.winstone.study.spring.demo.framework.beans.factory.config.BeanDefinition;
import java.util.HashMap;

/**
 * 简单实现的类工厂
 *
 * @author Winstone
 * @date 2021/12/5 - 9:46 下午
 */
public class BeanFactory{
	private final HashMap<String, BeanDefinition> definitionHashMap = new HashMap<>();

	public Object getBean(String name) {
		return definitionHashMap.get(name).getBean();
	}

	public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
		definitionHashMap.put(name, beanDefinition);
	}
}
