package Shop.web.servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import Shop.domain.Category;
import Shop.domain.Product;
import Shop.utils.CommonsUtils;
import Shop.web.service.AdminService;


public class AdminAddProduct extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 //Ŀ�ģ��ռ������� ��װһ��Product ʵ�� ���ϴ���ͼƬ�浽������������
		Map<String,Object> map = new HashMap<String,Object>();
		Product product = new Product();
		try {
		//���������ļ����
		DiskFileItemFactory factory = new  DiskFileItemFactory();
		
		//�����ļ��ϴ����Ķ���
		ServletFileUpload upload= new ServletFileUpload(factory);
		
		//����request����ļ����
		
			List<FileItem> parseRequest = upload.parseRequest(request);
			for (FileItem fileItem : parseRequest) {
				//�ж��Ƿ�Ϊ��ͨ����
				 boolean formField = fileItem.isFormField();
				 if(formField) {
					 //��ͨ���� ��ñ�����  ��װ��product ʵ����
					 String fieldName = fileItem.getFieldName();
					 String fielValue = fileItem.getString("UTF-8");
					 
					 map.put(fieldName, fielValue);
					 
				 }else {
					 //�ļ��ϴ��� ����ļ������ļ�����
					 String fielname = fileItem.getName();
					 String path = this.getServletContext().getRealPath("upload");
					 InputStream in = fileItem.getInputStream();
					 OutputStream out = new FileOutputStream(path+"/"+fielname);
					 IOUtils.copy(in, out);
					 in.close();
					 out.close();
					 fileItem.delete();
					 
					 map.put("pimage", "upload/"+fielname);
				 }
			}
			BeanUtils.populate(product, map);
			// �Ƿ�product�����װ��ȫ
			product.setPid(CommonsUtils.getUUID());
			
			product.setPdate(new Date());
			
			product.setPflag(0);
			
			Category category = new Category();
			category.setCid(map.get("cid").toString());
			product.setCategory(category);
			
			//��product���ݸ�service ��
			AdminService service = new AdminService();
			service.saveProduct(product);
			
			
			
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
