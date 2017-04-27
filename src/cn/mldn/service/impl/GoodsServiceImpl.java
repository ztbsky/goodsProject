package cn.mldn.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import cn.mldn.dao.IGoodsDAO;
import cn.mldn.dao.IItemDAO;
import cn.mldn.dao.ITagDAO;
import cn.mldn.service.IGoodsService;
import cn.mldn.service.abs.AbstractService;
import cn.mldn.util.factory.Factory;
import cn.mldn.vo.Goods;

public class GoodsServiceImpl extends AbstractService implements IGoodsService {

	@Override
	public Map<String, Object> getEditPre(int gid) throws Exception {
		IItemDAO itemDAO = Factory.getDAOInstance("item.dao");
		ITagDAO tagDAO =Factory.getDAOInstance("tag.dao");
		IGoodsDAO goodsDAO =Factory.getDAOInstance("goods.dao");
		
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("allItems", itemDAO.findAll());
		map.put("allTags", tagDAO.findAll());
		map.put("goods", goodsDAO.findById(gid));
		map.put("goodsTag", tagDAO.findAllByGoods(gid));
		return map;
	}
	
	
	@Override
	public boolean edit(Goods vo, Set<Integer> tids) throws Exception {
		if (vo.getIid()==null || vo.getIid().equals(0) || vo.getPrice()<=0 ) {
			return false;
		}
		IGoodsDAO goodsDAO =Factory.getDAOInstance("goods.dao");
		Goods nameGoods =goodsDAO.findByName(vo.getName());//根据名字查找商品的信息
		if (nameGoods==null || vo.getGid().equals(nameGoods.getGid()) ) {
			if (goodsDAO.doUpdate(vo)) {//修改商品信息
				ITagDAO tagDAO =Factory.getDAOInstance("tag.dao");
				tagDAO.doRemoveGoodsAndTag(vo.getGid());//删除已有的标签对应关系
				tagDAO.doCreateGoodsAndTag(vo.getGid(), tids);//保存新的标签对应关系
				return true;
			}
		}
		return false;
	}
	
	
	
	@Override
	public Map<String, Object> list(int currentPage, int lineSize, String column, String keyWord)
			throws Exception {
		Map<String, Object>map =new HashMap<String,Object>();
		IItemDAO itemDAO = Factory.getDAOInstance("item.dao");
		map.put("allItems", itemDAO.findAllForMap());
		
		IGoodsDAO goodsDAO =Factory.getDAOInstance("goods.dao");
		
		if (column==null || keyWord==null || "".equals(column) || "".equals(keyWord)) {
			map.put("allGoodss", goodsDAO.findAllSplit(currentPage, lineSize));
			map.put("goodsCount", goodsDAO.getAllCount());
		}else{
			map.put("allGoodss", goodsDAO.findAllSplit(currentPage, lineSize, column, keyWord));
			map.put("goodsCount", goodsDAO.getAllCount(column, keyWord));
		}
		
		return map;
	}
	@Override
	public Map<String, Object> listWithBug(int currentPage, int lineSize, String column, String keyWord)
			throws Exception {
		Map<String, Object>map =new HashMap<String,Object>();
		IGoodsDAO goodsDAO =Factory.getDAOInstance("goods.dao");
		
		if (column==null || keyWord==null || "".equals(column) || "".equals(keyWord)) {
			map.put("allGoodss", goodsDAO.findAllSplit(currentPage, lineSize));
			map.put("goodsCount", goodsDAO.getAllCount());
		}else{
			map.put("allGoodss", goodsDAO.findAllSplit(currentPage, lineSize, column, keyWord));
			map.put("goodsCount", goodsDAO.getAllCount(column, keyWord));
		}
		
		return map;
	}
	
	
	@Override
	public boolean add(Goods vo, Set<Integer> tids) throws Exception {
		if (vo.getIid()==null || vo.getIid().equals(0) || vo.getPrice()<=0 || vo.getPhoto()==null ||"".equals(vo.getPhoto())) {
			return false;
		}
		IGoodsDAO goodsDAO =Factory.getDAOInstance("goods.dao");
		if (goodsDAO.findByName(vo.getName())==null) {
			if (goodsDAO.doCreate(vo)) {//添加新的商品成功
				Integer gid = goodsDAO.findGreateId();//获取添加成功的商品的编号
				ITagDAO tagDAO =Factory.getDAOInstance("tag.dao");
				tagDAO.doCreateGoodsAndTag(gid, tids);
				return true;
			}
		}
		return false;
	}

	@Override
	public Map<String, Object> getAddPre() throws Exception {
		IItemDAO itemDAO = Factory.getDAOInstance("item.dao");
		ITagDAO tagDAO =Factory.getDAOInstance("tag.dao");
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("allItems", itemDAO.findAll());
		map.put("allTags", tagDAO.findAll());
		return map;
	}
	
}
