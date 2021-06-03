package com.tech.controller.disp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tech.controller.controllers.FamilyMemberjoinController;
import com.tech.controller.controllers.FamilyMemberjoinProcController;
import com.tech.controller.controllers.FamilyMemberloginController;
import com.tech.controller.controllers.FamilyMemberloginProcController;
import com.tech.controller.controllers.FamilyMemberlogoutProcController;

public class FamilyMemDispatcher extends HttpServlet  {
	
	private void doService(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("do신호");
		String uri=request.getRequestURI();
		String conPath=request.getContextPath();
		String com=uri.substring(conPath.length());
		
		
		System.out.println("uri : "+uri);
		System.out.println("conPath : "+conPath);
		System.out.println("com : "+com);
		
		Controller controller=null;

		try {
		if(com.equals("/join/familymemberjoin.do")) {
			controller=new FamilyMemberjoinController();
		}else if(com.equals("/join/FamilyMemberjoinProc.do")) {
			controller=new FamilyMemberjoinProcController();
		}else if(com.equals("/login/familymemberlogin.do")) {
			controller=new FamilyMemberloginController();
		}else if(com.equals("/login/FamilyMemberloginProc.do")) {
			controller=new FamilyMemberloginProcController();
		}else if(com.equals("/login/familymemberlogoutProc.do")) {
			controller=new FamilyMemberlogoutProcController();
		}
		
			controller.execute(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}

	@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doService(request,response);
}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doService(request,response);
	}
	
}

