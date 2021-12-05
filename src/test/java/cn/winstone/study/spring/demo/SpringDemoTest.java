package cn.winstone.study.spring.demo;


import cn.winstone.study.spring.demo.framework.beans.factory.config.BeanDefinition;
import cn.winstone.study.spring.demo.framework.beans.factory.BeanFactory;
import cn.winstone.study.spring.demo.service.UserService;
import org.junit.Test;

/**
 * @author Winstone
 * @date 2021/12/5 - 9:52 下午
 */
public class SpringDemoTest{

	@Test
	public void test_beanFactory() {
		// 1、初始化BeanFactory
		BeanFactory beanFactory = new BeanFactory();
		// 2、注册Bean
		BeanDefinition beanDefinition = new BeanDefinition();
		beanDefinition.setBean(new UserService());
		beanFactory.registerBeanDefinition(UserService.class.getSimpleName(), beanDefinition);

		// 3、获取Bean
		UserService userService = (UserService) beanFactory.getBean(UserService.class.getSimpleName());
		userService.queryUserInfo();
	}

}