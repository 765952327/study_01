package cn.winstone.study.spring.demo.framework.beans.factory.support;

import cn.winstone.study.spring.demo.framework.beans.factory.config.BeanDefinition;
import cn.winstone.study.spring.demo.framework.exception.BeansException;
import java.lang.reflect.Constructor;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

/**
 * @author Winstone
 * @date 2021/12/6 - 9:36 下午
 */
public class CglibSubClassingInstantiationStrategy implements InstantiationStrategy{
	@Override
	public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor<?> constructor, Object[] args) throws BeansException {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(beanDefinition.getBeanClass());
		enhancer.setCallback(new NoOp(){
			@Override
			public int hashCode() {
				return super.hashCode();
			}
		});

		if (null == constructor) return enhancer.create();
		return enhancer.create(constructor.getParameterTypes(), args);
	}
}
