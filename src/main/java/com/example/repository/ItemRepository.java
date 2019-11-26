package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
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
	
	/**
	 * 商品の曖昧検索するためのリポジトリ.
	 * @param name
	 * @return
	 */
	public List<Item> searchName(String name){
		String sql = "SELECT id, deleted, description, image_path, name, price_l, price_m FROM items WHERE name LIKE :name ORDER BY id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("name", "%" + name + "%");
		List<Item> itemList = template.query(sql, param, ITEM_ROW_MAPPER);
		return itemList;
	}
	
	/**
	 * 商品を1件検索するためのリポジトリ.
	 * @param id
	 * @return
	 */
	public Item load(Integer id) {
		String sql = "SELECT id, deleted, description, image_path, name, price_l, price_m FROM items WHERE id = :id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		Item item = template.queryForObject(sql, param, ITEM_ROW_MAPPER);
		return item;
	}

}
