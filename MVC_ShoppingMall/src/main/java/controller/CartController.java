package controller;

import javax.servlet.http.HttpSession;

import logic.Cart;
import logic.Item;
import logic.ItemSet;
import logic.Shop;
import logic.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import utils.WebConstants;

@Controller
public class CartController {

	@Autowired
	private Shop shopService;

	@RequestMapping(value = "/cart/cartAdd")
	public ModelAndView add(Integer itemId, Integer quantity, HttpSession session) {

		// 異붽� �곹뭹 �뺣낫瑜�痍⑤뱷
		Item selectedItem = this.shopService.getItemByItemId(itemId);

		// 移댄듃瑜�痍⑤뱷
		Cart cart = (Cart) session.getAttribute(WebConstants.CART_KEY);
		if (cart == null) {
			cart = this.shopService.getCart();
			session.setAttribute(WebConstants.CART_KEY, cart);
		}

		// 移댄듃���곹뭹��異붽�
		cart.push(new ItemSet(selectedItem, quantity));

		// 移댄듃 �뺣낫��硫붿떆吏�� �ㅼ젙
		ModelAndView modelAndView = new ModelAndView("cart/cart");
		modelAndView.addObject("message", selectedItem.getItemName() + "을" + quantity + "개가 들어있습니다.");
		modelAndView.addObject("cart", cart);

		// 濡쒓렇���좎�瑜�痍⑤뱷
		User loginUser = (User) session.getAttribute(WebConstants.USER_KEY);
		if (loginUser != null) {
			modelAndView.addObject("loginUser", loginUser);
		}
		return modelAndView;
	}

	@RequestMapping(value = "/cart/cartClear")
	public ModelAndView clear(HttpSession session) {

		// 移댄듃瑜�痍⑤뱷
		Cart cart = (Cart) session.getAttribute(WebConstants.CART_KEY);
		if (cart == null) {
			cart = this.shopService.getCart();
			session.setAttribute(WebConstants.CART_KEY, cart);
		}

		// 移댄듃瑜��대━��
		cart.clearAll();

		// 硫붿떆吏�� �ㅼ젙
		ModelAndView modelAndView = new ModelAndView("cart/cart");
		modelAndView.addObject("message", "카트를 비웠습니다");

		// 濡쒓렇���좎�瑜�痍⑤뱷
		User loginUser = (User) session.getAttribute(WebConstants.USER_KEY);
		if (loginUser != null) {
			modelAndView.addObject("loginUser", loginUser);
		}
		return modelAndView;
	}

	@RequestMapping(value = "/cart/cartConfirm")
	public ModelAndView confirm(HttpSession session) {

		// 移댄듃瑜�痍⑤뱷
		Cart cart = (Cart) session.getAttribute(WebConstants.CART_KEY);
		if (cart == null) {
			cart = this.shopService.getCart();
			session.setAttribute(WebConstants.CART_KEY, cart);
		}

		// 移댄듃 �뺣낫瑜��ㅼ젙
		ModelAndView modelAndView = new ModelAndView("cart/cart");
		modelAndView.addObject("cart", cart);

		// 濡쒓렇���좎�瑜�痍⑤뱷
		User loginUser = (User) session.getAttribute(WebConstants.USER_KEY);
		if (loginUser != null) {
			modelAndView.addObject("loginUser", loginUser);
		}
		return modelAndView;
	}
}