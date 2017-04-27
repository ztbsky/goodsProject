package cn.mldn.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.mldn.dao.IGoodsDAO;
import cn.mldn.util.dao.abs.AbstractDAO;
import cn.mldn.vo.Goods;

public class GoodsDAOImpl extends AbstractDAO implements IGoodsDAO {

	@Override
	public Integer findGreateId() throws SQLException {
		String sql ="select goods_seq.currval from dual";
		super.pstmt=super.conn.prepareStatement(sql);
		ResultSet rs =super.pstmt.executeQuery();
		if(rs.next()){
			return rs.getInt(1);
		}
		return 0;
	}
	
	@Override
	public boolean doCreate(Goods vo) throws SQLException {
		String sql ="insert into goods (gid,name,price,photo,iid) values (goods_seq.nextval,?,?,?,?)";
		super.pstmt=super.conn.prepareStatement(sql);
		super.pstmt.setString(1, vo.getName());
		super.pstmt.setDouble(2, vo.getPrice());
		super.pstmt.setString(3, vo.getPhoto());
		super.pstmt.setInt(4, vo.getIid());
		return super.pstmt.executeUpdate()>0;
	}

	@Override
	public boolean doUpdate(Goods vo) throws SQLException {
		String sql ="update goods set name=?,price=?,iid=? where gid=?";
		super.pstmt=super.conn.prepareStatement(sql);
		super.pstmt.setString(1, vo.getName());
		super.pstmt.setDouble(2, vo.getPrice());
		super.pstmt.setInt(3, vo.getIid());
		super.pstmt.setInt(4, vo.getGid());
		boolean r =super.pstmt.executeUpdate()>0;
		
		return r;
	}

	@Override
	public boolean doRemove(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Goods findById(Integer id) throws SQLException {
		String sql ="select gid,name,price,photo,iid from goods where gid=?";
		super.pstmt=super.conn.prepareStatement(sql);
		super.pstmt.setInt(1, id);
		ResultSet rs =super.pstmt.executeQuery();
		if (rs.next()) {
			Goods vo =new Goods();
			vo.setGid(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setPrice(rs.getDouble(3));
			vo.setPhoto(rs.getString(4));
			vo.setIid(rs.getInt(5));
			return vo;
		}
		return null;
	}

	@Override
	public List<Goods> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Goods> findAllSplit(Integer currentPage, Integer lineSize) throws SQLException {
		List<Goods> all =new ArrayList<Goods>();
		String sql ="select * from ( select gid,name,price,photo,iid,rownum rn from goods where rownum<=?) temp where temp.rn>? ";
		super.pstmt=super.conn.prepareStatement(sql);
		super.pstmt.setInt(1, currentPage * lineSize);
		super.pstmt.setInt(2, (currentPage-1) * lineSize);
		ResultSet rs =super.pstmt.executeQuery();
		while (rs.next()) {
			Goods vo =new Goods();
			vo.setGid(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setPrice(rs.getDouble(3));
			vo.setPhoto(rs.getString(4));
			vo.setIid(rs.getInt(5));
			all.add(vo);
		}
		return all;
	}

	@Override
	public List<Goods> findAllSplit(Integer currentPage, Integer lineSize, String column, String keyWord)
			throws SQLException {
		List<Goods> all =new ArrayList<Goods>();
		String sql ="select * from ( select gid,name,price,photo,iid,rownum rn from goods where "+column+" like ? and rownum<=?) temp where temp.rn>? ";
		super.pstmt=super.conn.prepareStatement(sql);
		super.pstmt.setString(1, "%"+keyWord+"%");
		super.pstmt.setInt(2, currentPage * lineSize);
		super.pstmt.setInt(3, (currentPage-1) * lineSize);
		ResultSet rs =super.pstmt.executeQuery();
		while (rs.next()) {
			Goods vo =new Goods();
			vo.setGid(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setPrice(rs.getDouble(3));
			vo.setPhoto(rs.getString(4));
			vo.setIid(rs.getInt(5));
			all.add(vo);
		}
		return all;
	}

	@Override
	public Integer getAllCount() throws SQLException {
		String sql ="select count(*) from goods";
		super.pstmt=super.conn.prepareStatement(sql);
		ResultSet rs =super.pstmt.executeQuery();
		if (rs.next()) {
			return rs.getInt(1);
		}
		return 0;
	}

	@Override
	public Integer getAllCount(String column, String keyWord) throws SQLException {
		String sql ="select count(*) from goods where "+column+" like ? ";
		super.pstmt=super.conn.prepareStatement(sql);
		super.pstmt.setString(1, "%"+keyWord+"%");
		ResultSet rs =super.pstmt.executeQuery();
		if (rs.next()) {
			return rs.getInt(1);
		}
		return 0;
	}

	@Override
	public Goods findByName(String name) throws SQLException {
		String sql ="select gid,name,price,photo,iid from goods where name=?";
		super.pstmt=super.conn.prepareStatement(sql);
		super.pstmt.setString(1, name);
		ResultSet rs =super.pstmt.executeQuery();
		if (rs.next()) {
			Goods vo =new Goods();
			vo.setGid(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setPrice(rs.getDouble(3));
			vo.setPhoto(rs.getString(4));
			vo.setIid(rs.getInt(5));
			return vo;
		}
		return null;
	}

}
