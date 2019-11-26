package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Item;
import com.example.domain.Topping;
import com.example.service.ItemService;
import com.example.service.ToppingService;

@Controller
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private ToppingService toppingService;
	
	/**
	 * 商品一覧.
	 * @param model
	 * @return
	 */
	@RequestMapping("/itemList")
	public String itemList(Model model) {
		List<List<Item>> itemList = itemService.findAll();
		model.addAttribute("itemList", itemList);
		return "item_list";
	}
	
	/**
	 * 商品の曖昧検索.
	 * @param name
	 * @param model
	 * @return
	 */
	@RequestMapping("/itemSearch")
	public String itemSearch(String name, Model model) {
		
		List<List<Item>> itemList = itemService.searchName(name);
		if(itemList.size() == 0) {
			model.addAttribute("error", "1件もありませんでした");
			return itemList(model);
		}
		model.addAttribute("itemList", itemList);
		return "item_list";
	}
	
	/**
	 * 商品の詳細.
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/itemDetail")
	public String itemDetail(Integer id, Model model) {
		Item item = itemService.load(id);
		model.addAttribute("item", item);
		
		/** toppingの全件検索 */
		List<List<Topping>> toppingList = toppingService.findAll();
		model.addAttribute("toppingList", toppingList);
		return "item_detail";
	}

}
