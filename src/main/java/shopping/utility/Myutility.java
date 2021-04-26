package shopping.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import shopping.common.controller.SuperController;

public class Myutility {
	public static MultipartRequest getMultiPartRequest( HttpServletRequest request, String uploadedPath  ){
		//파일 업로드를 위한 MultipartRequest 객체를 구해주는 static 메소드이다.		
		String encType = "UTF-8"; //문자열 인코딩
		int sizeLimit = 20 * 1024 * 1024; //업로드 허용 최대 사이즈
		MultipartRequest multi = null ; //파일 업로드 객체		
		try {
			multi = new MultipartRequest(request, uploadedPath, sizeLimit,
					encType, new DefaultFileRenamePolicy());			
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return multi ;
	}

	
	
	public static Map<String, SuperController> getActionMapList(
			String configFilePath) {
		Map<String, SuperController> todolists = null ;
		todolists =  new HashMap<String, SuperController>();		
		//스트림을 통하여 Properties 객체에 넣기
		//우선 MyPropertyEx.java 파일을 실습해보도록 한다.
		Properties prop = new Properties();
		FileInputStream fis = null;
		try {		
			//설정 파일에서 맵핑 정보를 읽어 와서 Properties 객체인 prop에 저장한다.
			fis = new FileInputStream(configFilePath);
			prop.load(fis); //자바의 스트림을 이용하여 Properties 컬렉션에 요소들을 추가한다.
		} catch (IOException e) {
			e.printStackTrace();  
		} finally {
			if (fis != null)
				try {
					fis.close();
				} catch (IOException ex) {
			}
		}
		
		Enumeration<Object> enu = prop.keys() ;
		
		while (enu.hasMoreElements()) {
			//프로퍼티의 이름을 커맨드 이름으로 사용한다.
			String command = enu.nextElement().toString();
			
			//커맨드 이름에 해당하는 className 이름을 Properties에서 구한다.
			String className = prop.getProperty( command ) ;
			
			try {
				//className 이름을 이용하여 Class 객체를 구한다.
				Class<?> handlerClass = Class.forName(className);
				
				//각 요청 업무에 따르는 Controller들은 SuperController의 하위 클래스이다.
				//Class로부터 핸들러 객체를 생성한다.
				SuperController handlerInstance = 
						(SuperController) handlerClass.newInstance();
				
				//객체로 만들어서 자바의 맵 구조에 추가한다.
				todolists.put(command, handlerInstance);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
		//System.out.println("맵 사이즈 : " + todolists.size() );	
		return todolists ;
	}
}