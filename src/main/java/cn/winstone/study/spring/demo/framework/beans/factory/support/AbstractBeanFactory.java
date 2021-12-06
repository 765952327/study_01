package cn.winstone.study.spring.demo.framework.beans.factory.support;

import cn.winstone.study.spring.demo.framework.beans.factory.BeanFactory;
import cn.winstone.study.spring.demo.framework.beans.factory.config.BeanDefinition;
import cn.winstone.study.spring.demo.framework.exception.BeansException;

/**
 * @author Winstone
 * @date 2021/12/6 - 9:21 上午
 */
public abstract class AbstractBeanFactory
		extends DefaultSingletonBeanRegistry
		implements BeanFactory{

	@Override
	public Object getBean(String name) {
		Object bean = getSingleton(name);
		if (bean != null) {
			return bean;
		}

		BeanDefinition beanDefinition = getBeanDefinition(name);
		return createBean(name, beanDefinition);
	}

	@Override
	public Object getBean(String name, Object... args) throws BeansException {
		Object bean = getSingleton(name);
		if (bean != null) {
			return bean;
		}

		BeanDefinition beanDefinition = getBeanDefinition(name);
		return createBeanInstance(beanDefinition, name, args);
	}

	protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

	protected abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException;

	protected abstract Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args) throws BeansException;
}
