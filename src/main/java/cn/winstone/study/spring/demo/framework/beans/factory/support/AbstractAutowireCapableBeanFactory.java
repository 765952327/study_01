package cn.winstone.study.spring.demo.framework.beans.factory.support;

import cn.winstone.study.spring.demo.framework.beans.factory.config.BeanDefinition;
import cn.winstone.study.spring.demo.framework.exception.BeansException;

/**
 * @author Winstone
 * @date 2021/12/6 - 8:44 下午
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{

	@Override
	protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
		Object bean = null;
		try {
			bean = beanDefinition.getBeanClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new BeansException("Instantiation of bean failed", e);
		}
		addSingleton(beanName, bean);
		return bean;
	}
}
