package cn.e3mall.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 注册管理
 * @author 吾霞
 *
 */
@Controller
public class RegisterController {
	
	@RequestMapping("/page/register")
	public String showRegister(){
		return "register";
	}
}
