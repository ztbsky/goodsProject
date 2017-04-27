package cn.mldn.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.mldn.dao.IItemDAO;
import cn.mldn.util.dao.abs.AbstractDAO;
import cn.mldn.vo.Item;

public class ItemDAOImpl extends AbstractDAO implements IItemDAO {

	
	
	@Override
	public boolean doCreate(Item vo) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doUpdate(Item vo) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doRemove(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Item findById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Integer,String> findAllForMap() throws SQLException {
		Map<Integer,String> all =new HashMap<Integer,String>();
		String sql = "select iid ,title from item" ;
		super.pstmt=super.conn.prepareStatement(sql);
		ResultSet rs =super.pstmt.executeQuery();
		while(rs.next()){
			all.put(rs.getInt(1), rs.getString(2));
		}
		return all;
	}
	
	@Override
	public List<Item> findAll() throws SQLException {
		List<Item> all =new ArrayList<Item>();
		String sql = "select iid ,title from item" ;
		super.pstmt=super.conn.prepareStatement(sql);
		ResultSet rs =super.pstmt.executeQuery();
		while(rs.next()){
			Item vo =new Item();
			vo.setIid(rs.getInt(1));
			vo.setTitle(rs.getString(2));
			all.add(vo);
		}
		return all;
	}

	@Override
	public List<Item> findAllSplit(Integer currentPage, Integer lineSize) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Item> findAllSplit(Integer currentPage, Integer lineSize, String column, String keyWord)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getAllCount() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getAllCount(String column, String keyWord) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
