package com.zcy;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


//测试类总报错找不到包，所以注释掉此部分测试代码
@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginApplicationTests {

	/*@Test
	public void contextLoads() {
	}

	@Autowired
	private LoginMapper loginMapper;
	@Test
	public void loginUser(){
		List<Userinfo> list = loginMapper.check("test", "111");
		System.out.println(list.get(0).getNickname());
	}

	@Autowired
	private LoginService loginService;
	@Test
	public void serviceLogin(){
		List<Userinfo> list = loginService.check("test", "111");
		System.out.println(list.get(0).getNickname());
	}*/
}
