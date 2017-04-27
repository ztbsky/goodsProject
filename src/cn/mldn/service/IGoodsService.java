package cn.mldn.service;

import java.util.Map;
import java.util.Set;

import cn.mldn.vo.Goods;

public interface IGoodsService {
	
	/**
	 * 进行数据的分页显示，如果没有模糊查询则进行全部数据的分页处理
	 * @param currentPage
	 * @param lineSize
	 * @param column
	 * @param keyWord
	 * @return 返回的数据包含一下信息：<br>
	 * 1、key = allGoodss、value= 所有的商品数据信息；<br>
	 * 2、key = goodsCount、value= 商品的数量信息；<br>
	 * 3、key = allItems、value= 所有的商品分类数据（Map集合）
	 * @throws Exception
	 */
	public Map<String , Object> list(int currentPage,int lineSize,String column,String keyWord) throws Exception;
	
	/**
	 * 进行数据的分页显示，如果没有模糊查询则进行全部数据的分页处理
	 * @param currentPage
	 * @param lineSize
	 * @param column
	 * @param keyWord
	 * @return 返回的数据包含一下信息：<br>
	 * 1、key = allGoodss、value= 所有的商品数据信息；<br>
	 * 2、key = goodsCount、value= 商品的数量信息；<br>
	 * @throws Exception
	 */
	public Map<String , Object> listWithBug(int currentPage,int lineSize,String column,String keyWord) throws Exception;
	
	
	/**
	 * 进行商品数据修改前 的信息查找操作，该操作会执行如下调用：<br>
	 * 1、查询所有的商品分类信息，调用IItemDAO.findAll();<br>
	 * 2、查询所有的商品标签信息，调用ITagDAO.findAll();<br>
	 * 3、根据商品编号查询出商品信息，调用IGoods.findById()方法;<br>
	 * 4、查询出该商品已经有的标签信息，调用ITagDAO.findAllByGoods()方法；<br>
	 * @return 返回的数据包含有如下内容：<br>
	 * 1、key = allItems、value = 所有的Item的商品分类信息；<br>
	 * 2、key = allTags、value = 所有的商品标签信息；<br>
	 * 3、key = goods、 value =要修改的商品对象 Goods对象
	 * 4、key = goodsTag、value = 要修改gid对象 已有的商品标签
	 * @throws Execption
	 */
	public Map<String,Object> getEditPre(int gid) throws Exception;
	
	
	
	/**
	 * 实现商品数据的添加处理， 基本的处理流程如下：<br>
	 * 1、首先一定要保证商品有标签，以及商品有所属分类，商品价格一定要大于0。<br>
	 * 2、首先要使用IGoodsDAO.findByName()判断商品名称是否重复，但是不对自己的编号判断；<br>
	 * 3、如果商品名称不存在，则使用IGoodsDAO.doUpdate()进行商品信息的保存<br>
	 * 4、商品与标签的关系由于可能产生编号，则应该先删除；<br>
	 * 5、调用ITagDAO.doCreateGoodsAndTag()方法保存商品和标签的对应关系<br>
	 * @param vo 商品信息
	 * @param tids 所有的标签的ID信息
	 * @return 保存成功返回true 否则返回false
	 * @throws Exception
	 */
	public boolean edit(Goods vo,Set<Integer> tids) throws Exception;
	
	/**
	 * 实现商品数据的添加处理， 基本的处理流程如下：<br>
	 * 1、首先一定要保证商品有标签，以及商品有所属分类，商品价格一定要大于0。<br>
	 * 2、首先要使用IGoodsDAO.findByName()判断商品名称是否重复；<br>
	 * 3、如果商品名称不存在，则使用IGoodsDAO.doCreate()进行商品信息的保存<br>
	 * 4、为了可以向goods_tag关系表中进行数据保存，所以一定要首先取得增长后的商品编号，调用IGoodsDAO.findGreateId()方法；<br>
	 * 5、调用ITagDAO.doCreateGoodsAndTag()方法保存商品和标签的对应关系<br>
	 * @param vo 商品信息
	 * @param tids 所有的标签的ID信息
	 * @return 保存成功返回true 否则返回false
	 * @throws Exception
	 */
	public boolean add(Goods vo,Set<Integer> tids) throws Exception;
	
	/**
	 * 进行商品数据添加前 的信息查找操作，该操作会执行如下调用：<br>
	 * 1、查询所有的商品分类信息，调用IItemDAO.findAll();<br>
	 * 2、查询所有的商品标签信息，调用ITagDAO.findAll();<br>
	 * @return 返回的数据包含有如下内容：<br>
	 * 1、key = allItems、value = 所有的Item的商品分类信息；<br>
	 * 2、key = allTags、value = 所有的商品标签信息；<br>
	 * @throws Execption
	 */
	public Map<String,Object> getAddPre() throws Exception;
}
