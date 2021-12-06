package cn.winstone.study.spring.demo.framework.beans.factory.support;

import cn.winstone.study.spring.demo.framework.beans.factory.config.BeanDefinition;
import cn.winstone.study.spring.demo.framework.exception.BeansException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Winstone
 * @date 2021/12/6 - 8:48 下午
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry{
	private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

	@Override
	protected BeanDefinition getBeanDefinition(String beanName) throws BeansException {
		BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
		if (beanDefinition == null) throw new BeansException("No bean named '" + beanName + "' is defined");
		return beanDefinition;
	}

	@Override
	public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
		beanDefinitionMap.put(beanName, beanDefinition);
	}
}
