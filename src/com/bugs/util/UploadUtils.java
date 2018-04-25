package com.bugs.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

public class UploadUtils {

	public static Map<String, String> HandleFormByUpload(HttpServletRequest request, ServletContext servletContext) {
		Map<String, String> map = new HashMap<String, String>();
		String id = request.getParameter("id");
		map.put("id", id);
		System.out.println("=====jinru");
		// 判断表单类型是否是enctype="multipart/form-data";
		if (!ServletFileUpload.isMultipartContent(request)) {
		    return null;
		}
		// 按照enctype="multipart/form-data";来处理内容
		// 1.配置类
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 设置临时的目录(下载过程中的临时文件)：
		factory.setRepository(new File("d:\\"));
		// 2.创建上传核心类的对象
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 3. 解析上传请求
		try {
			List<FileItem> list = upload.parseRequest(request);
			for (int i = 0; i < list.size(); i++) {
				FileItem fileItem = list.get(i);
				// 判断是普通表单项还是上传项
				if (fileItem.isFormField()) {
					String name = fileItem.getFieldName();
					try {
						String value = fileItem.getString("utf-8");
						map.put(name, value);
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else {
					// 获取即将要文件上传的文件在本地磁盘的路径
					String originPath = fileItem.getName();
					File file2 = mkDirByHashCode(originPath,servletContext);
					String path1 = file2.getAbsolutePath();
					String aString = "http://localhost:8080/" + path1
							.substring(path1.indexOf(request.getContextPath().replace("/", "\\"))).replace("\\", "/");
					// 存储可访问的路径
					map.put("imgurl", aString);
					System.out.println("====="+aString);
					try {
						// 把上传的内容写入到该文件中
						fileItem.write(file2);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}

	public static File mkDirByHashCode(String originPath, ServletContext servletContext) {
		String filename = "";
		// 获取文件名称
		if (originPath != null) {
			filename = FilenameUtils.getName(originPath);
			System.out.println("=filename====" + filename);
		}
		int code = filename.hashCode();
		// 返回一个十六进制的字符串 ，一个字节是8个二进制数，十六进制是四位二进制数，十六进制占0.5个字节；
		String string = Integer.toHexString(code);
		String s1 = string.charAt(0) + File.separator + string.charAt(1);

		// 根据日期创建目录
		File file = new File(servletContext.getRealPath("/upload"), s1);
		if (!file.exists()) {
			file.mkdirs();
		}
		// 创建指定文件
		File file2 = new File(file, filename);
		return file2;
	}
}
