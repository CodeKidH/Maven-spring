package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.Item;
import logic.Shop;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class IndexController implements Controller {

	private Shop shopService;

	public void setShopService(Shop shopService) {
		this.shopService = shopService;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		List<Item> itemList = this.shopService.getItemList();
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("itemList", itemList);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		modelAndView.addAllObjects(model);
		
		return modelAndView;
	}
}