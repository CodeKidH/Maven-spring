package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import service.MainService;
import domain.UserDto;

public class mainController {
	
	@Autowired
	private MainService service;

	@RequestMapping("/getUser.do")
	public String doGetUser(ModelMap map){
		List<UserDto> lists = service.getUserInfo();
		map.addAttribute("lists", lists);
		return "index.jsp";

	} 
}
