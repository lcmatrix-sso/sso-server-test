package top.lcmatrix.fw.sso.server_test.controller.open;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import top.lcmatrix.fw.sso.server.controller.SSOServerController;
import top.lcmatrix.fw.sso.server_test.entity.User;

@RequestMapping("open")
@Controller
public class LoginController {
	
	@RequestMapping("login")
	public void login(@RequestParam(name="username", required=true)String username,
			@RequestParam(name="password", required=true)String password, String returnUrl, 
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		User user = new User();
		user.setUsername(username);
		request.getSession().setAttribute("user", user);
		String toUrl = StringUtils.isBlank(returnUrl) ? request.getContextPath() : returnUrl;
		SSOServerController.onLoginSuccess(user, toUrl);
	}
}
