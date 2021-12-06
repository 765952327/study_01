package cn.winstone.study.spring.demo.framework.beans.factory;

import cn.winstone.study.spring.demo.framework.exception.BeansException;

/**
 * 简单实现的类工厂
 *
 * @author Winstone
 * @date 2021/12/5 - 9:46 下午
 */
public interface BeanFactory{
	Object getBean(String name) throws BeansException;

	Object getBean(String name, Object... args) throws BeansException;
}
