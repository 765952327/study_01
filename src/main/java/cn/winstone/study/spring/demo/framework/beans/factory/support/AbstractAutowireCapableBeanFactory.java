package cn.winstone.study.spring.demo.framework.beans.factory.support;

import cn.winstone.study.spring.demo.framework.beans.factory.config.BeanDefinition;
import cn.winstone.study.spring.demo.framework.exception.BeansException;
import java.lang.reflect.Constructor;

/**
 * @author Winstone
 * @date 2021/12/6 - 8:44 下午
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{

	private InstantiationStrategy instantiationStrategy = new CglibSubClassingInstantiationStrategy();

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

	@Override
	protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args) {
		Constructor<?> constructorToUse = null;
		Class<?> beanClass = beanDefinition.getBeanClass();
		Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();

		for (Constructor<?> ctor : declaredConstructors) {
			if (null != args && ctor.getParameterTypes().length == args.length) {
				constructorToUse = ctor;
				break;
			}
		}
		return getInstantiationStrategy().instantiate(beanDefinition, beanName, constructorToUse, args);
	}

	private InstantiationStrategy getInstantiationStrategy() {
		return instantiationStrategy;
	}
}
