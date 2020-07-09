package com.thinkgem.jeesite.common.task;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.thinkgem.jeesite.common.config.User;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.FileUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.resource.entity.TsResource;
import com.thinkgem.jeesite.modules.resource.service.TsResourceService;
import com.thinkgem.jeesite.modules.resourcetype.entity.TsResourceType;
import com.thinkgem.jeesite.modules.resourcetype.service.TsResourceTypeService;

@Service
@Lazy(false)
public class SynchronousFileTask {
	private static Logger logger = LoggerFactory.getLogger(SynchronousFileTask.class);
	
	static boolean isOver = true;
	
	@Autowired
	private TsResourceService tsResourceService;
	
	@Autowired
	private TsResourceTypeService tsResourceTypeService;

	@Scheduled(cron = "0 0 2,12,22  * * ? ")
//	@Scheduled(cron = "0 4 18 * * ?")
	public void synchronousFileTask() {
//		logger.info("-------执行importGameXXJH5开始------->" + DateUtils.getDateTime());
//		SimpleDateFormat myFmt = new SimpleDateFormat("yyMMdd");
//		Date date = new Date();
//		String nowdate = myFmt.format(date);
//		String tablename = "order_" + nowdate;
//		rundata(tablename);
//		logger.info("-------导入H5数据库中" + tablename + "表的渠道汇总数据----");
//		logger.info("-------执行importGameXXJH5结束------->" + DateUtils.getDateTime());
		//启动复制文件
		logger.info("-------执行synchronousFileTask开始------->" + DateUtils.getDateTime());
		if(isOver){
			isOver = false;
			logger.info("-------本次开始执行------->" + DateUtils.getDateTime());
			WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
			ServletContext servletContext = webApplicationContext.getServletContext(); 
			String projectPath=servletContext.getRealPath("/").replace("\\", "/");
			createDirAndCopyFile(projectPath);
			logger.info("-------本次执行完成------->" + DateUtils.getDateTime());
			isOver = true;
		}else{
			//上次的还没执行完成
			logger.info("-------等待上一次执行完成------->" + DateUtils.getDateTime());
		}
		logger.info("-------执行synchronousFileTask结束------->" + DateUtils.getDateTime());
		
	}
	
	
	public boolean createDirAndCopyFile(String projectPath){
		//获取数据
		logger.info("-------获取数据------->" + DateUtils.getDateTime());
		System.out.println("1");
		List<TsResource> tempList = tsResourceService.findAllList(new TsResource());
		System.out.println(tempList);
		System.out.print("tempList.size()=["+tempList.size());
		//这里是调用  
		List<TsResource> lists = tsResourceService.findAllListNoParam();
//		List<TsResource> list = tsRsService.findAllList2();
		System.out.print("lists.size()=["+lists.size());
		for (TsResource list:lists) {
			//获取存储的相对路径
			String files = list.getFiles();
			//获取存储的树结构路径
			String busType = list.getBusType();
			//获取树结构路径对象
			TsResourceType rsType = tsResourceTypeService.get(busType);
			if(null==rsType){
				continue;
			}
			String FileStoreRootPath = User.getConfig("FileStoreRootPath");
			if(StringUtils.isBlank(FileStoreRootPath)){
				//获取默认的根节点路径
				FileStoreRootPath = User.getConfig("DefaultFileStoreRootPath");
				FileStoreRootPath = System.getenv(FileStoreRootPath);
				if(StringUtils.isBlank(FileStoreRootPath)){
					FileStoreRootPath = "C:\\";
				}
			}
			try {
				if(!(new File(FileStoreRootPath).exists())){
					FileUtils.forceMkdir(new File(FileStoreRootPath));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			//获取树结构路径对象的父ID
			String pid = rsType.getParentId();
			//获取树结构路径对象的父ID
			String pids = rsType.getParentIds();
			//获取树结构路径对象的名称
			String name = rsType.getName();
			//创建目录
			//创建子级目录
			System.out.print("    files=["+files);
			System.out.print("    name=["+name);
			System.out.print("    pids=["+pids);
			StringBuffer temp_path = new StringBuffer() ;
			temp_path.append(FileStoreRootPath);
//			rsType = tsRsTypeService.get(pid);
//			temp_path.append(File.separator);
//			temp_path.append(name);
//			System.out.print("    请求的URL地址：=【"+Servlets.getRequest().getContextPath());
			String[] ppaths = pids.split(",");
			try {
				for (int i = 0; i < ppaths.length; i++) {
					if(i==0){
						continue;
					}
					rsType = tsResourceTypeService.get(ppaths[i]);
					if(null!=rsType){
						temp_path.append(File.separator);
						temp_path.append(rsType.getName());
					}
				}
				temp_path.append(File.separator);
				temp_path.append(name);
				if(!(new File(temp_path.toString()).exists())){
					FileUtils.forceMkdir(new File(temp_path.toString()));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			//获取文件
			String file_all_path = files.replace("|/jeesite", projectPath);
			System.out.println("file_all_path=["+file_all_path);
			 try {
					file_all_path = URLDecoder.decode(file_all_path, "UTF-8");
					System.out.println(file_all_path);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} // 设置编码字符集为utf-8
			File temp_file = new File(file_all_path);
//			fileName = temp_file.getName()
	        String fName = file_all_path.trim();  
	        String fileName = fName.substring(fName.lastIndexOf("/")+1);  
	        //或者  
	        System.out.println("fileName = " + fileName);  
			temp_path.append(File.separator);
			temp_path.append(fileName);
			String newFile_all_path = temp_path.toString();
           
			if(temp_file.exists()){
				
				FileUtils.copyFileCover(file_all_path, newFile_all_path,true);
				//设置对象属性
				list.setRemark50("SAVED");
				tsResourceService.save(list);
			}
			
		}
		
		return true;
	}
	
	
}