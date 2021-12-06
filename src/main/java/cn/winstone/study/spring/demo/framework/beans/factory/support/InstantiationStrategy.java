package cn.winstone.study.spring.demo.framework.beans.factory.support;

import cn.winstone.study.spring.demo.framework.beans.factory.config.BeanDefinition;
import cn.winstone.study.spring.demo.framework.exception.BeansException;
import java.lang.reflect.Constructor;

/**
 * 实例化策略
 *
 * @author Winstone
 * @date 2021/12/6 - 9:30 下午
 */
public interface InstantiationStrategy{

	Object instantiate(BeanDefinition beanDefinition,
					   String beanName,
					   Constructor<?> constructor,
					   Object[] args) throws BeansException;
}
