package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.domain.Item;

@Repository
public class ItemRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static final RowMapper<Item> ITEM_ROW_MAPPER = (rs, i) -> {
		Item item = new Item();
		item.setId(rs.getInt("id"));
		item.setDeleted(rs.getBoolean("deleted"));
		item.setDescription(rs.getString("description"));
		item.setImagePath(rs.getString("image_path"));
		item.setName(rs.getString("name"));
		item.setPriceL(rs.getInt("price_l"));
		item.setPriceM(rs.getInt("price_m"));
		return item;
	};
	
	/**
	 * 商品一覧のためのリポジトリ.
	 * @return
	 */
	public List<Item> findAll(){
		String sql = "SELECT id, deleted, description, image_path, name, price_l, price_m FROM items ORDER BY id";
		List<Item> itemList = template.query(sql, ITEM_ROW_MAPPER);
		return itemList;
	}

}
