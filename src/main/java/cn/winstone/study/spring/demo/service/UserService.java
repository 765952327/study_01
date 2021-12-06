package cn.winstone.study.spring.demo.service;

/**
 * @author Winstone
 * @date 2021/12/5 - 9:51 下午
 */
public class UserService{
	private String name;

	public UserService(String name) {
		this.name = name;
	}

	public void queryUserInfo() {
		System.out.println("查询用户信息:" + name);
	}

	@Override
	public String toString(){
		final StringBuilder sb = new StringBuilder("");
		sb.append("").append(name);
		return sb.toString();
	}
}
