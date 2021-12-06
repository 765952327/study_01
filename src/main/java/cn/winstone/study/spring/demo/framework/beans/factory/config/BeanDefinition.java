package cn.winstone.study.spring.demo.framework.beans.factory.config;

/**
 * 简单的Bean定义
 *
 * @author Winstone
 * @date 2021/12/5 - 9:48 下午
 */
public class BeanDefinition{
	private Class<?> beanClass;

	public BeanDefinition(Class<?> beanClass) {
		this.beanClass = beanClass;
	}

	public Class<?> getBeanClass() {
		return beanClass;
	}

	public void setBeanClass(Class<?> beanClass) {
		this.beanClass = beanClass;
	}
}
