package cn.winstone.study.spring.demo.framework.beans.factory.support;

import cn.winstone.study.spring.demo.framework.beans.factory.config.BeanDefinition;
import cn.winstone.study.spring.demo.framework.exception.BeansException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author Winstone
 * @date 2021/12/6 - 9:33 下午
 */
public class SimpleInstantiationStrategy implements InstantiationStrategy{
	@Override
	public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor<?> constructor, Object[] args) throws BeansException {
		Class<?> clazz = beanDefinition.getBeanClass();
		try {
			if (null != constructor) {
				return clazz.getDeclaredConstructor(constructor.getParameterTypes()).newInstance(args);
			} else {
				return clazz.getDeclaredConstructor().newInstance();
			}
		} catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
			throw new BeansException("Failed to instantiate [" + clazz.getName() + "]", e);
		}
	}
}
