package com.zcy;

import com.zcy.dao.LoginMapper;
import com.zcy.domain.Userinfo;
import com.zcy.service.LoginService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginApplicationTests {

	@Test
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
	}
}
