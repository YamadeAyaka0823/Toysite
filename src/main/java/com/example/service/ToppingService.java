package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Item;
import com.example.domain.Topping;
import com.example.repository.ToppingRepository;

@Service
@Transactional
public class ToppingService {
	
	@Autowired
	private ToppingRepository toppingRepository;
	
	/**
	 * 3つずつ表示させるリスト.
	 * @param item
	 * @return
	 */
	List<List<Topping>> arrayTable(List<Topping> topping){
		List<List<Topping>> allItemList = new ArrayList<>();
		List<Topping> item3List = new ArrayList<>();
		
		for(int i = 1; i < topping.size(); i++) {
			item3List.add(topping.get(i - 1));
			
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
	 * トッピングの一覧を表示するサービス.
	 * @return
	 */
	public List<List<Topping>> findAll(){
		return arrayTable(toppingRepository.findAll());
	}

}
