package cn.winstone.study.spring.demo.framework.beans.factory.config;

/**
 * @author Winstone
 * @date 2021/12/6 - 8:18 下午
 */
public interface SingletonBeanRegistry{
	Object getSingleton(String beanName);
}
