package cn.winstone.study.spring.demo;


import cn.winstone.study.spring.demo.framework.beans.factory.config.BeanDefinition;
import cn.winstone.study.spring.demo.framework.beans.factory.support.DefaultListableBeanFactory;
import cn.winstone.study.spring.demo.service.UserService;
import java.lang.reflect.Constructor;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
import org.junit.Test;

/**
 * @author Winstone
 * @date 2021/12/5 - 9:52 下午
 */
public class SpringDemoTest{

	@Test
	public void test_beanFactory() {
		// 1、初始化BeanFactory
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		// 2、注册Bean
		BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
		beanFactory.registerBeanDefinition(UserService.class.getSimpleName(), beanDefinition);

		// 3、获取Bean
		UserService userService = (UserService) beanFactory.getBean(UserService.class.getSimpleName(), "小哥");
		userService.queryUserInfo();
	}

	@Test
	public void test_newInstance() throws IllegalAccessException, InstantiationException {
		UserService userService = UserService.class.newInstance();
		System.out.println(userService);
	}

	@Test
	public void test_constructor() throws Exception {
		Class<UserService> userServiceClass = UserService.class;
		Constructor<UserService> declaredConstructor
				= userServiceClass.getDeclaredConstructor(String.class);

		UserService userService = declaredConstructor.newInstance("小小哥");
		System.out.println(userService);
	}

	@Test
	public void test_parameterTypes() throws Exception {
		Class<UserService> beanClass = UserService.class;
		Constructor<?>[] declaredConstructors
				= beanClass.getDeclaredConstructors();
		Constructor<?> constructor = declaredConstructors[0];
		Constructor<UserService> declaredConstructor = beanClass.getDeclaredConstructor(constructor.getParameterTypes());
		UserService userService = declaredConstructor.newInstance("小小小哥");
		System.out.println(userService);
	}

	@Test
	public void test_cglib() {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(UserService.class);
		enhancer.setCallback(new NoOp(){
			@Override
			public int hashCode() {
				return super.hashCode();
			}
		});
		Object obj = enhancer.create(new Class[]{String.class}, new Object[]{"小小小小哥"});
		System.out.println(obj);
	}
}