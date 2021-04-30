package shopping.common.controller ;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import shopping.utility.Myutility;


@WebServlet(
		urlPatterns = { "/Shopping" }, 
		initParams = { 
				@WebInitParam(name = "configFile", value = "/WEB-INF/todolist.md")
		})
public class FrontController extends HttpServlet {//implements SuperController{
	private static final long serialVersionUID = 1L;
    
	// <커맨드, 핸들러인스턴스> 매핑 정보 저장
	//맵 구조의 왼쪽은 String 형태의 커맨드가 들어 온다.
	//맵 구조의 오른 쪽은 SuperController라는 클래스 타입의 객체이다.
	private Map<String, SuperController> actionMaps =  new HashMap<String, SuperController>();
	
	public void init() throws ServletException {
		//configFile 초기화 파라미터 읽기
		String configFile = getInitParameter("configFile");
		
		//스트림을 통하여 Properties 객체에 넣기
		Properties prop = new Properties();
		FileInputStream fis = null;
		try {
			String configFilePath = getServletContext().getRealPath(configFile);
			fis = new FileInputStream(configFilePath);
			prop.load(fis); //자바의 스트림을 이용하여 Properties 컬렉션에 요소들을 추가한다.
		} catch (IOException e) {
			throw new ServletException(e);
		} finally {
			if (fis != null)  
				try {
					fis.close();
				} catch (IOException ex) {
			}
		}
		
		Iterator keyIter = prop.keySet().iterator();
		
		//prop 객체를 Iterator를 이용하여 map 객체에 담기
		while (keyIter.hasNext()) {
			String command = (String) keyIter.next();
			
			//handlerClassName는 클래스 정보를 담고 있는 문자열
			String handlerClassName = prop.getProperty(command);
			try {
				Class<?> handlerClass = Class.forName(handlerClassName);
				
				//각 요청 업무에 따르는 Controller들은 SuperController의 하위 클래스이다. 
				SuperController handlerInstance = 
						(SuperController) handlerClass.newInstance();
				
				//객체로 만들어서 자바의 맵 구조에 추가한다.
				actionMaps.put(command, handlerInstance);
			} catch (ClassNotFoundException e) {
				throw new ServletException(e);
			} catch (InstantiationException e) {
				throw new ServletException(e);
			} catch (IllegalAccessException e) {
				throw new ServletException(e);
			}
		}
		System.out.println( "맵 사이즈 : " + actionMaps.size());
	}	
	
	public void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//파일 업로드를 이용하기 위하여 multipart/form-data을 사용하게 되면
		//request 객체를 직접 이용하지 못한다.
		//파라미터 command를 챙긴다.
		String command = request.getParameter("command");		
		
		ServletContext application = getServletContext();	
		
		String uploadedPath = application.getRealPath("/upload") ; //실제 업로드될 웹서버 경로
		
		System.out.println( "uploadedPath : " + uploadedPath );
		
		application.setAttribute("uploadedPath", uploadedPath);
		
		if( command == null ){ //파일 업로드를 위한 케이스이다.
			System.out.println("파일 업로드를 수행합니다.");
			//그래서, 파일 업로드를 위한 MultipartRequest 객체를 구해주는 메소드를 호출한다.
			MultipartRequest multi = Myutility.getMultiPartRequest(request, uploadedPath) ;
		
			if( multi != null ){ //MultipartRequest 객체가 구해지면
				command = multi.getParameter("command") ;
				request.setAttribute("multi", multi ); //request에 바인딩
				request.setAttribute("uploadedPath", uploadedPath ); //request에 바인딩
			}
		}
		
		String method = request.getMethod().toLowerCase() ;
		//해당 커맨드에 맞는 컨트롤러를 찾아서 해당 컨트롤러의 doProcess() 메소드를 호출한다.
		//map 객체에서 해당 command에 해당하는 Controller 객체를 얻어 낸다.
		SuperController controller = actionMaps.get(command);
		if(controller != null){			
			if ( method.equals("get") ) {
				System.out.println( controller.toString() + " GET 메소드 호출됨" );
				controller.doGet( request, response ); //해당 doProcess() 메소드를 호출한다.	
			} else if ( method.equals("post") ) {
				System.out.println( controller.toString() + " POST 메소드 호출됨" );
				controller.doPost( request, response ); //해당 doProcess() 메소드를 호출한다.
			}
		}else{
			System.out.println( command + "는 존재하지 않습니다.");
		}
	}	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doProcess(request, response);
	}	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doProcess(request, response);
	}
}