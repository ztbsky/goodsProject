
 package cn.mldn.dao;

import java.sql.SQLException;
import java.util.Set;

import cn.mldn.util.dao.IBaseDAO;
import cn.mldn.vo.Tag;

public interface ITagDAO extends IBaseDAO<Integer, Tag> {
	/**
	 * 根据商品的编号删除goods_tag关系表中的信息，以方便保存新的关系
	 * @param gid
	 * @return 
	 * @throws SQLException
	 */
	public boolean doRemoveGoodsAndTag(Integer gid) throws SQLException;
	
	/**
	 * 根据商品编号查找出该商品具备的所有标签信息
	 * @param gid
	 * @return
	 * @throws SQLException
	 */
	public Set<Integer> findAllByGoods(Integer gid)throws SQLException;
	
	
	/**
	 * 向goods_tag表中进行数据的保存
	 * @param gid 商品编号
	 * @param tids 商品的标签编号 集合
	 * @return
	 * @throws SQLException
	 */
	public boolean doCreateGoodsAndTag(Integer gid,Set<Integer> tids)throws SQLException;
	
}
