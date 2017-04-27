package cn.mldn.dao;

import java.sql.SQLException;
import java.util.Map;

import cn.mldn.util.dao.IBaseDAO;
import cn.mldn.vo.Item;

public interface IItemDAO extends IBaseDAO<Integer, Item> {
	/**
	 * 以Map集合的形式返回所有的商品分类数据<br>
	 * 查询所有的商品类型信息
	 * @return key = 分类编号、value = 分类名称；
	 * @throws SQLException
	 */
	public Map<Integer, String> findAllForMap() throws SQLException;
}
