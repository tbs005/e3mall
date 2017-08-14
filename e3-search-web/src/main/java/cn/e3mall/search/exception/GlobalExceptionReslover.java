package cn.e3mall.search.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 全局异常处理
 * @author 吾霞
 *
 */
public class GlobalExceptionReslover implements HandlerExceptionResolver {

	private static final Logger logger=LoggerFactory.getLogger(GlobalExceptionReslover.class);
	
	
	public ModelAndView resolveException(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2,
			Exception ex) {
		//写日志文件
		logger.error("系统发生异常", ex);
		//发邮件、发短信
		//Jmail：可以查找相关的资料
		//需要在购买短信。调用第三方接口即可。
		//展示错误页面
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("message", "系统发生异常，请稍后重试");
		modelAndView.setViewName("error/exception");
		return modelAndView;		

	}

}
