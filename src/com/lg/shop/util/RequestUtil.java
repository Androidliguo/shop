package com.lg.shop.util;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FilenameUtils;

import com.lg.shop.model.ValidateForm;
import com.lg.shop.model.ValidateType;



public class RequestUtil {
	//规定上传的文件的类型
		public final static String[] allowFile = {"jpg","bmp","gif","png"};
		//这个路径是web-content中的路径
		public final static String PATH = "C:\\Users\\liguo\\javaweb\\shop02\\WebContent";
		//文件的上传。fname表示的是文件的名称。filedName表示的是表单域，这里指的是img,fs指的是字节数组，req指的是请求
		@SuppressWarnings("unchecked")
		public static void uploadFile(String fname, String fieldName,byte[] fs,HttpServletRequest req) throws FileNotFoundException, IOException {
			FileOutputStream fos = null;
			//文件中有内容的时候才选择上传
			try {
				if(fs.length>0) {
					String fn = FilenameUtils.getName(fname);
					String ext = FilenameUtils.getExtension(fname);//得到的是文件额拓展名
					System.out.println(ext);
					boolean b =  checkFile(ext);//判断是否符合所需要的文件的类型
					if(b) {
						fos = new FileOutputStream(PATH+"/img/"+fn);
						System.out.println(PATH+"/img/"+fn);
						System.out.println(fieldName);
						fos.write(fs, 0, fs.length);//将字节数组的内容写入到file中
					} else {//如果文件无法上传的话，那么久输出错误的信息
						Map<String,String> errors = (Map<String,String>)req.getAttribute("errors");
						errors.put(fieldName, "图片类型必须是jpg,bmp,png,gif");
					}
				}
			} finally {//关闭相关的流
				if(fos!=null) fos.close();
			}
		}
	//判断文件的类型是否符合
		private static boolean checkFile(String ext) {
			for(String s:allowFile) {
				if(ext.equals(s)) {
					return true;
				}
			}
			return false;
		}
	
	@SuppressWarnings("unchecked")
	public static boolean validate(Class<?> clz,HttpServletRequest req) {
		Field[] fs = clz.getDeclaredFields();
		boolean isValidate = true;
		Map<String,String> errors = (Map<String,String>)req.getAttribute("errors");
		for(Field f:fs) {
			if(f.isAnnotationPresent(ValidateForm.class)) {
				ValidateForm vf = f.getAnnotation(ValidateForm.class);
				ValidateType vt = vf.type();
				if(vt==ValidateType.NotNull) {
					boolean b = validateNotNull(f.getName(),req);
					if(!b) {
						isValidate = false;
						errors.put(f.getName(), vf.errorMsg());
					}
				} else if(vt==ValidateType.Length) {
					boolean b = validateLength(f.getName(),req,vf.value());
					if(!b) {
						isValidate = false;
						errors.put(f.getName(), vf.errorMsg());
					}
				} else if(vt==ValidateType.Number) {
					boolean b = validateNumber(f.getName(), req);
					if(!b) {
						isValidate = false;
						errors.put(f.getName(), vf.errorMsg());
					}
				}
			}
		}
		return isValidate;
	}
	private static boolean validateNumber(String name, HttpServletRequest req) {
		/**
		 * 如果出现了异常的话，那么我们并不需要捕获异常，我们只需要return false就可以了。
		 */
		try {
			Double.parseDouble(req.getParameter(name));
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
	private static boolean validateLength(String name, HttpServletRequest req,
			String value) {
		String v = req.getParameter(name);
		if(v==null||"".equals(v.trim())) {
			return false;
		}
		if(v.length()<6) {
			return false;
		}
		return true;
	}
	private static boolean validateNotNull(String name,HttpServletRequest req) {
		if(!req.getParameterMap().containsKey(name)) {
			//表示表单中并没有要验证的 值
			return true;
		}
		String v = req.getParameter(name);
		if(v==null||"".equals(v.trim())) {
			return false;
		}
		return true;
	}
	
   public static Object setParam(Class clz,HttpServletRequest req){
	    Map<String,String[]> map=req.getParameterMap();
	    Set<String> keys=map.keySet();
	    Object object=null;
	   try {
		object=clz.newInstance();
		 for(String key:keys){
		    	String[] params=map.get(key);
		    	if(params.length>1){
		    		BeanUtils.copyProperty(object, key,params);
		    	}else{
		    		BeanUtils.copyProperty(object, key,params[0]);
		    	}
		    	
		    }
	} catch (InstantiationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InvocationTargetException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   return object;
	   
   }
}
