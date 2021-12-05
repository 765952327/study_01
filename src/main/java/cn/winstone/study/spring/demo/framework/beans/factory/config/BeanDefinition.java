package cn.winstone.study.spring.demo.framework.beans.factory.config;

/**
 * 简单的Bean定义
 *
 * @author Winstone
 * @date 2021/12/5 - 9:48 下午
 */
public class BeanDefinition{
	private Object bean;

	public Object getBean() {
		return bean;
	}

	public void setBean(Object bean) {
		this.bean = bean;
	}
}
