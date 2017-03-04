/**
 * 
 */
package com.xu.manager.ClassUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.xu.manager.bean.CarInformation;

import jxl.Workbook;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
* @author Create By Xuguoqiang
* @date   2016年10月29日--下午9:13:53--
*
*/

public class ExportCarInformationUtils {
	
	public static List<String> getTitle(){
		List<String> titlelist = new ArrayList<String>();
		titlelist.add("汽车编号");
		titlelist.add("汽车种类");
		titlelist.add("汽车类型");
		titlelist.add("行驶里程");
		titlelist.add("变速箱");
		titlelist.add("排量");
		titlelist.add("颜色");
		titlelist.add("国别");
		titlelist.add("汽车价格");
		titlelist.add("汽车拥有者");
		titlelist.add("汽车车龄");
		titlelist.add("汽车上牌时间");
		return titlelist;
		
	}
	public static void exportCarInformationUtils(List<CarInformation> list, List<String> title,
			String realPath, String fileName) {
		 WritableWorkbook book = null;
	        try {
	            // 打开文件
	            book = Workbook.createWorkbook(new File(realPath+"/"+fileName));
	            // 生成名为"请假表"的工作表，参数0表示这是第一页
	            WritableSheet sheet = book.createSheet("汽车信息表", 0);
	            // 设置单元格格式
	            WritableFont WFTitle = new WritableFont(WritableFont.createFont("宋体"),13, WritableFont.BOLD, false);
	            WritableFont writableFont = new WritableFont(WritableFont.createFont("宋体"),11, WritableFont.NO_BOLD, false);
	            WritableCellFormat wcf = new WritableCellFormat(WFTitle);
	            WritableCellFormat writableCellFormat = new WritableCellFormat(writableFont);
	            wcf.setBackground(Colour.ORANGE);
	            wcf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
	            writableCellFormat.setWrap(true);
	            writableCellFormat.setBackground(Colour.LIGHT_GREEN);
	            writableCellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
	            for (int i = 0; i < title.size(); i++) {
	            	sheet.setColumnView(i, 20); //设置列宽
	                sheet.addCell(new Label(i, 0, title.get(i),wcf));//设置标题
				}
	            CarInformation map;
	            if(list!=null && !list.isEmpty()){
	                for(int i=0; i<list.size(); i++){//控制行
	                	//参数1表示第几列，参数2表示第几行，参数3表示数据内容
	                	//sheet.addCell(new Label(i, 0, title.get(i)));
	                	map = list.get(i);
	                	            int j=0;
	        		                sheet.addCell(new Label(j++, i+1, Long.toString(map.getCarId()), writableCellFormat));	
	        		                sheet.addCell(new Label(j++, i+1, map.getCarName(), writableCellFormat));
	        		                sheet.addCell(new Label(j++, i+1, map.getCarType(), writableCellFormat));	
	        		             //   sheet.addCell(new Label(j++, i+1, Long.toString(map.getTravelAge()), writableCellFormat));
	        		                sheet.addCell(new Label(j++, i+1, Long.toString(map.getVariableBox()), writableCellFormat));	
	        		                sheet.addCell(new Label(j++, i+1, map.getCarInfoDetail(), writableCellFormat));
						}
	            }
	            
	            // 写入数据并关闭文件
	            book.write();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }finally{
	            if(book!=null){
	                try {
	                    book.close();
	                } catch (Exception e) {
	                    e.printStackTrace();
	                } 
	            }
	        }
		
	}
	
	
	
	/**生成文件
	 * @param wb
	 * @param filePath
	 */
	private void outputFile(WritableWorkbook wb,String filePath){
		
		FileOutputStream os = null;
		try {
			File f = new File(filePath);
			 
			os = new FileOutputStream(f);
			wb.write();
			os.flush();
		} catch (FileNotFoundException e) {
			System.err.println("获取不到位置");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (Exception ex) {
					ex.printStackTrace();
					os = null;
				}
			}
		}

	
	}
}
