package com.rice.biz;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.directwebremoting.io.FileTransfer;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.rice.bean.Order;
import com.rice.dao.OrderDAO;
import com.rice.util.DwrMethodRight;
import com.rice.util.PageResult;
import com.rice.util.Result;
import com.sun.org.apache.commons.beanutils.BeanUtils;

/**
 * 订单 biz
 * @author lyw
 *
 */
@Service
@RemoteProxy
@Scope("prototype")
public class OrderBiz {
	/**
	 * 订单DAO
	 */	
	@Resource //注入
	private OrderDAO orderDao;
	/**
	 * 获取订单列表
	 * @param request
	 * @return
	 */
	@RemoteMethod
	public PageResult<Order> getAll(Map<String,String> parm,HttpServletRequest request){
		//时间
		Timestamp create_time=null;
		if(parm.get("create_time")==null||parm.get("create_time").isEmpty()){
			//默认为今日订单
			create_time=new Timestamp(System.currentTimeMillis());
		}else
			create_time=new Timestamp(Long.valueOf(parm.get("create_time").toString()));
		
		//格式化时间
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		parm.put("create_time", df.format(create_time));
		return this.orderDao.findByPageResult(parm);
	}
	/**
	 * 修改订单为已付款
	 * @param list 订单列表
	 * @param request
	 * @return
	 */
	@RemoteMethod
	public Result doModifyPayment(List<Order> list,HttpServletRequest request){
		//是否具有权限
		boolean isRight=DwrMethodRight.methodRight(request);
		if(isRight==false)
			return new Result(false,"sorry，没有权限");
		//更新条数
		int modCount=0;
		try {
			for (Order order : list) {
				if("是".equals(order.getIsPayment()))
					continue;
				//修改订单为已付款
				order.setIsPayment("是");
				this.orderDao.doUpdate(order);
				modCount++;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if(modCount>0)
			return new Result(true,"成功！修改了"+modCount+"条。");	
		return new Result(false,"修改已付款失败");	
	}
	/**
	 * 订餐
	 * @param parm
	 * @param request
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@RemoteMethod
	public Result doAdd(Map<String,String> parm,HttpServletRequest request){
		try {
			//删除id属性
			if (parm.get("id") != null)
				parm.remove("id");
			//时间
			Timestamp time = new Timestamp(System.currentTimeMillis());
			//小时
			int hours = time.getHours();
			//分
			int minutes = time.getMinutes();
			//9点-11点为订餐时间
			if(hours>=12||hours<9)
				return new Result(false,"亲...还不到订餐时间哦");
			//11点10分停止订餐
			if(hours>=11&&minutes>10)
				return new Result(false,"亲...还不到订餐时间哦");
			
			Order order = new Order();
			try {
				BeanUtils.populate(order, parm);

				//设置时间
				order.setCreate_time(time);
				//没有付款
				order.setIsPayment("否");
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (order != null) {
				this.orderDao.doAdd(order);
				if (order.getId() != null)
					return new Result(true, "亲..订餐成功!");
			}
		} catch (Exception e) {
			// TODO: handle exception

			return new Result(false,e.getMessage());
		}
		return new Result(false,"sorry..订餐失败!");
	}
	/**
	 * 删除订餐信息
	 * @param parm
	 * @param request
	 * @return
	 */
	@RemoteMethod
	public Result doDelete(List<Order> list,HttpServletRequest request){
		//是否具有权限
		boolean isRight=DwrMethodRight.methodRight(request);
		if(isRight==false)
			return new Result(false,"sorry，没有权限");
		//删除条数
		int delCount=0;
		try {
			for (Order order : list) {
				this.orderDao.doDelete(order);
				delCount++;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if(delCount>0)
			return new Result(true,"成功！删除了"+delCount+"条。");	
		return new Result(false,"删除失败");	
	}
	public void setOrderDao(OrderDAO orderDao) {
		this.orderDao = orderDao;
	}
	/**
	 * 统计当日订餐信息
	 * @param request
	 * @return
	 */
	@RemoteMethod
	@SuppressWarnings({ "deprecation", "null" })
	public FileTransfer doExportAssessmentExcel(HttpServletRequest request){
		//获取统计信息
		List<Map<String,Object>> statisticsList=this.orderDao.todayStatistics();
		
		if(statisticsList==null||statisticsList.size()==0)
			return null;
		
		//替换统计map
		this.replaceStatisticsMap(statisticsList);
		
		
		Date date=new Date(System.currentTimeMillis());
		//格式化时间
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		String format=df.format(date);
		
		/*
		 * 创建Excel
		 */
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet=wb.createSheet(format+"统计信息");
		
		/*
		 * 标题样式
		 */
		HSSFCellStyle topStyle = wb.createCellStyle();
		HSSFFont font=wb.createFont();
		font.setFontHeightInPoints((short)12);//设置字体大小
		topStyle.setFont(font);//使用字体
		topStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		topStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		
		/*
		 * 头部行-标题
		 */
		HSSFRow topRow=sheet.createRow(0);
		HSSFCell topCell0=topRow.createCell((short)0);
		topCell0.setCellValue("菜单名称");
		topCell0.setCellStyle(topStyle);
		HSSFCell topCell1=topRow.createCell((short)1);
		topCell1.setCellValue("订餐信息");
		topCell1.setCellStyle(topStyle);
		HSSFCell topCell2=topRow.createCell((short)2);
		topCell2.setCellValue("统计");
		topCell2.setCellStyle(topStyle);
		//行高
		topRow.setHeightInPoints(30f);
		
		/*
		 * 单元格样式
		 */
		HSSFCellStyle cellStyle = wb.createCellStyle();
		cellStyle.setFont(font);//使用字体
		cellStyle.setWrapText(true);//自动换行
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);
		
		/*
		 * 遍历统计信息并添加信息
		 */
		for (int i = 1; i < statisticsList.size()+1; i++) {
			//创建行
			HSSFRow row=sheet.createRow(i);
			row.setHeightInPoints(60f);
			//订餐信息
			Map<String, Object> map=statisticsList.get(i-1);
			
			//菜单名称
			HSSFCell cell=row.createCell((short)0);
			cell.setCellValue(map.get("dish_name").toString());
			cell.setCellStyle(cellStyle);
			
			//订餐信息
			HSSFCell cell1=row.createCell((short)1);
			cell1.setCellValue(map.get("info").toString());
			cell1.setCellStyle(cellStyle);
			
			//统计
			HSSFCell cell2=row.createCell((short)2);
			cell2.setCellValue(map.get("dish_count").toString());
			cell2.setCellStyle(cellStyle);
			
		}
		/*
		 * 设置宽度
		 */
		sheet.setColumnWidth((short)0, (short)(20*256));
		sheet.setColumnWidth((short)1, (short)(50*256));
		sheet.setColumnWidth((short)2, (short)(20*256));
		
		//输出流
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		try {
			//写入输出流
			wb.write(baos);
			baos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 获取文件名
		String fileName = format + ".xls";
		return new FileTransfer(
				fileName,
				"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
				baos.toByteArray());
	}
	/**
	 * 替换统计map
	 * @param statisticsList
	 */
	@SuppressWarnings("null")
	private void replaceStatisticsMap(List<Map<String,Object>> statisticsList){
		//没有统计信息 return
		if(statisticsList!=null||statisticsList.size()==0){
			List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
			for (int i = 0; i < statisticsList.size(); i++) {
				Map<String, Object> map=statisticsList.get(i);
				
				//是否存在菜单
				boolean isExit=false;
				for (Map<String, Object> listMap : list) {
					
					//菜单名称相同
					if(listMap.get("dish_name").toString().equals(map.get("dish_name").toString())){
						//之前信息
						String info=listMap.get("info").toString();
						//名字
						String name=map.get("user_name").toString().trim();
						//订该菜单几份
						String user_count=map.get("user_count").toString()+"份,";
						
						/*
						 * 不重复添加订餐人信息
						 */
						String[] infoArray=info.split(",");
						boolean isExitName=false;
						//遍历信息，判断是否存在订餐人名称
						for (String s : infoArray) {
							int beginIndex=s.indexOf("[");//开始index
							int endIndex=s.indexOf("]");//结束index
							//之前订餐人名称
							String infoName=s.substring(beginIndex+1,endIndex).trim();
							if(infoName.equals(name))
								isExitName=true;
						}
						if(isExitName==false)
							listMap.put("info", info+"["+name+"] "+user_count);
						
						isExit=true;
						break;
					}
				}

				//不存在菜单，添加信息
				if(isExit==false){
					Map<String, Object> listMap=new HashMap<String, Object>();
					
					//菜单名称
					listMap.put("dish_name", map.get("dish_name"));
					//订餐信息
					String user_count=map.get("user_count").toString()+"份,";
					listMap.put("info",  "["+map.get("user_name").toString()+"] "+user_count);
					//该菜单被订几份
					listMap.put("dish_count", "共"+map.get("dish_count")+"份");
					
					list.add(listMap);
				}
				
			}
			//替换
			statisticsList.clear();
			statisticsList.addAll(list);
		}
	}
}
