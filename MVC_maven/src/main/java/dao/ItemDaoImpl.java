package dao;

import java.util.List;

import javax.sql.DataSource;

import logic.Item;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.dao.EmptyResultDataAccessException;

public class ItemDaoImpl implements ItemDao {

	private SimpleJdbcTemplate template;

	public void setDataSource(DataSource dataSource) {
		this.template = new SimpleJdbcTemplate(dataSource);
	}

	private static final String SELECT_ALL = "SELECT * FROM car";
	
	public List<Item> findAll() {
		RowMapper<Item> mapper = new BeanPropertyRowMapper<Item>(Item.class);
		
		return this.template.query(ItemDaoImpl.SELECT_ALL, mapper);
	}
	
	private static final String SELECT_BY_PRIMARYKEY = "SELECT * FROM car WHERE carno = ?";
	public Item findByPrimaryKey(Integer itemId){
		RowMapper<Item> mapper = new BeanPropertyRowMapper<Item>(Item.class);
		try{
			return this.template.queryForObject(SELECT_BY_PRIMARYKEY, mapper, itemId);
		}catch(EmptyResultDataAccessException e){
			return null;
		}
	}
		
}