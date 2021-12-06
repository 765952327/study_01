package cn.winstone.study.spring.demo.framework.beans.factory.support;

import cn.winstone.study.spring.demo.framework.beans.factory.config.BeanDefinition;

/**
 * @author Winstone
 * @date 2021/12/6 - 8:49 下午
 */
public interface BeanDefinitionRegistry{
	void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
