package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Item;
import com.example.repository.ItemRepository;

@Service
@Transactional
public class ItemService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	/**
	 * 3つずつ表示させるリスト.
	 * @param item
	 * @return
	 */
	List<List<Item>> arrayTable(List<Item> item){
		List<List<Item>> allItemList = new ArrayList<>();
		List<Item> item3List = new ArrayList<>();
		
		for(int i = 1; i < item.size(); i++) {
			item3List.add(item.get(i));
			
			if((i % 3) == 0) {
				allItemList.add(item3List);
				item3List = new ArrayList<>();
			}
		}
		if(item3List.size() == 0) {
			return allItemList;
		}
		allItemList.add(item3List);
		return allItemList;
	}
	
	/**
	 * 商品一覧のためのサービス.
	 * @return
	 */
	public List<List<Item>> findAll(){
		return arrayTable(itemRepository.findAll());
	}

}
