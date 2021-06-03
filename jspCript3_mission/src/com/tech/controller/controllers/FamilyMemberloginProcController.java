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

public class FamilyMemberloginProcController implements Controller {

	@Override
	public void execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		System.out.println("FamilyMemberLoginProcController");	
//		DB연결
		Connection con=DBCon.getConnection();
		String fname=request.getParameter("fname");
		String fpass=request.getParameter("fpass");
		
		//select
		String sql="select fname,fpass,shpwd,bcpwd from familymember where fname=?";
		
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, fname);
		
		ResultSet rs=pstmt.executeQuery();
		
		
		SHA256 sha=SHA256.getInsatnce();	
		
		String shaPass=sha.getSha256(fpass.getBytes());
		
		Boolean idpwboolean=false;
		if(rs.next())
			idpwboolean=BCrypt.checkpw(shaPass, rs.getString("bcpwd"));
		System.out.println("boolean : "+idpwboolean);
		
		if (idpwboolean==true) {
			request.getSession().setAttribute("uid", fname);
		}
		
		response.sendRedirect("../index.jsp");
		
	}

}

