package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Item;
import com.example.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
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

}
