
<%@page import="java.util.UUID"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.Set"%>
<%@page import="cn.mldn.vo.Goods"%>
<%@page import="com.jspsmart.upload.SmartUpload"%>
<%@page import="java.util.Iterator"%>
<%@page import="cn.mldn.vo.Tag"%>
<%@page import="cn.mldn.vo.Item"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="cn.mldn.service.IGoodsService"%>
<%@page import="java.util.Date"%>
<%@page import="cn.mldn.util.factory.Factory"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
<%
	String basePath = request.getScheme() + "://" + 
		request.getServerName() + ":" + request.getServerPort() + 
		request.getContextPath() + "/" ;
%>
<base href="<%=basePath%>"/>
<title>商品管理</title>
<meta name="viewport" content="width=device-width,initial-scale=1">
<script type="text/javascript" src="jquery/jquery.min.js"></script>
<script type="text/javascript" src="js/back/admin/goods/goods_add.js"></script>
<script type="text/javascript" src="js/common/mldn_util.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.min.css">
</head>
<%!
	public static final String GOODS_LIST_URL = "pages/back/admin/goods/goods_list.jsp" ;
%>
<%
	request.setCharacterEncoding("UTF-8");
	SmartUpload smart = new SmartUpload();
	smart.initialize(config, request, response);
	smart.upload();
	Goods vo =new Goods();
	vo.setGid(Integer.parseInt(smart.getRequest().getParameter("gid")));
	vo.setName(smart.getRequest().getParameter("name"));
	vo.setPrice(Double.parseDouble(smart.getRequest().getParameter("price")));
	vo.setIid(Integer.parseInt(smart.getRequest().getParameter("item")));
	 //获取所有选中的标签编号
	String tags[] =smart.getRequest().getParameterValues("tag");
	Set<Integer> tagSet =new HashSet<Integer>();
	for(int x = 0 ; x < tags.length ; x++){
		//把选中的每一个商品标签的 编号 存入 set集合中
		tagSet.add(Integer.parseInt(tags[x]));
	}
	//对于上传的图片一定要生成新的图片名称	
	
	String msg ="商品信息编辑失败！";
	IGoodsService goodsService =Factory.getServiceInstance("goods.service");
	if(goodsService.edit(vo,tagSet)){
		msg="商品信息编辑成功！";
		//商品信息添加完成后，还需要进行图片的保存
		if(smart.getFiles().getSize()>0){
			String filePath =getServletContext().getRealPath("/upload/goods/"+smart.getRequest().getParameter("oldphoto")); 
			smart.getFiles().getFile(0).saveAs(filePath);
		}
	}
	
%>


<h1>getServletContext()=<%=getServletContext().getRealPath("") %></h1>
<h1>java.util.UUID.randomUUID()=<%=java.util.UUID.randomUUID() %></h1>
<h1>smart.getFiles()=<%=smart.getFiles() %></h1>
<h1>smart.getFiles()=<%=smart.getFiles().getFile(0) %></h1>
<h1>smart.getFiles()=<%=smart.getFiles().getFile(0).getFileExt() %></h1>
<body>
	<div class="container">
		<div class="row">
			<div class="col-xs-12">
				<jsp:include page="/pages/plugins/forward.jsp">
					<jsp:param value="<%=GOODS_LIST_URL %>" name="url"/>
					<jsp:param value="<%=msg %>" name="msg"/>
				</jsp:include> 
			</div>
		</div>
	</div>
</body>
</html>









