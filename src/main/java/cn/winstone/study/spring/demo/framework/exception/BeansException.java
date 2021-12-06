package cn.winstone.study.spring.demo.framework.exception;

/**
 * @author Winstone
 * @date 2021/12/6 - 8:26 下午
 */
public class BeansException extends RuntimeException{
	public BeansException(String message, Throwable cause) {
		super(message, cause);
	}

	public BeansException(String message) {
		super(message);
	}

	public BeansException() {
	}
}
