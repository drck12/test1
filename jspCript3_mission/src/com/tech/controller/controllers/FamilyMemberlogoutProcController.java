package com.tech.controller.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tech.DB.DBCon;
import com.tech.controller.disp.Controller;
import com.tech.crypt.work.BCrypt;
import com.tech.crypt.work.SHA256;

public class FamilyMemberlogoutProcController implements Controller {

	@Override
	public void execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		System.out.println("FamilyMemberLogoutProcController");	
//		
		request.getSession().removeAttribute("uid");
		
		response.sendRedirect("../index.jsp");
		
	}

}

