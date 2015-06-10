package controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import logic.Item;
import logic.ItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;

	@RequestMapping(value="/item/index")
	public ModelAndView index() {

		// 상품목록정보 취득
		List<Item> itemList = this.itemService.getItemList();

		// 모델 생성
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("itemList", itemList);

		// 반환값이 되는 ModelAndView 인스턴스를 생성
		ModelAndView modelAndView = new ModelAndView("index");
		modelAndView.addAllObjects(model);

		return modelAndView;
	}

	@RequestMapping(value="/item/search",method = RequestMethod.POST)
	public ModelAndView search(String itemName) {
		if (itemName == null || itemName.equals("")) {
			// 검색상품명이 없는 경우, 상품 전체를 반환한다
			return this.index();
		}

		List<Item> itemList = this.itemService.getItemByItemName(itemName);
		if (itemList == null || itemList.isEmpty()) {
			// 검색상품명이 없는 경우, 상품 전체를 반환한다
			return this.index();
		}

		// 반환값이 되는 ModelAndView 인스턴스를 생성
		ModelAndView modelAndView = new ModelAndView("index");
		modelAndView.addObject("itemList", itemList);

		return modelAndView;
	}

	@RequestMapping(value="/item/create")
	public ModelAndView create() {
		ModelAndView modelAndView = new ModelAndView("add");
		modelAndView.addObject(new Item());
		return modelAndView;
	}

	@RequestMapping(value="/item/register",method = RequestMethod.POST)
	public ModelAndView register(@Valid Item item, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			ModelAndView modelAndView = new ModelAndView("add");
			modelAndView.getModel().putAll(bindingResult.getModel());
			return modelAndView;
		}
		this.itemService.entryItem(item);
		return this.index();
	}

	@RequestMapping(value="/item/edit")
	public ModelAndView edit(Integer itemId) {
		ModelAndView modelAndView = new ModelAndView("update");
		Item item = this.itemService.getItemByItemId(itemId);
		modelAndView.addObject(item);
		return modelAndView;
	}

	@RequestMapping(value="/item/update",method = RequestMethod.POST)
	public ModelAndView update(@Valid Item item, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			ModelAndView modelAndView = new ModelAndView("update");
			modelAndView.getModel().putAll(bindingResult.getModel());
			return modelAndView;
		}
		this.itemService.updateItem(item);
		return this.index();
	}

	@RequestMapping(value="/item/confirm")
	public ModelAndView confirm(Integer itemId) {
		ModelAndView modelAndView = new ModelAndView("delete");
		Item item = this.itemService.getItemByItemId(itemId);
		modelAndView.addObject(item);
		return modelAndView;
	}

	@RequestMapping(value="/item/delete",method = RequestMethod.POST)
	public ModelAndView delete(Item item) {
		this.itemService.deleteItem(item);
		return this.index();
	}

	
}