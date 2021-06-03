package com.tech.controller.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tech.DB.DBCon;
import com.tech.controller.disp.Controller;
import com.tech.crypt.work.BCrypt;
import com.tech.crypt.work.SHA256;

public class FamilyMemberjoinProcController implements Controller {

	@Override
	public void execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("familyMemberJoinProcController");
		
		Connection con=DBCon.getConnection();
		String fname=request.getParameter("fname");
		String fpass=request.getParameter("fpass");
		String tel=request.getParameter("tel");
		String addr=request.getParameter("addr");
		
		SHA256 sha=SHA256.getInsatnce();
		String shaPwd=sha.getSha256(fpass.getBytes());
		
		String bcPwd=BCrypt.hashpw(shaPwd, BCrypt.gensalt());
		
		System.out.println("인증확인 : "+ BCrypt.checkpw(shaPwd, bcPwd));
				
		String sql="insert into familymember(fname,fpass,shpwd,bcpwd) values (?,?,?,?)";
		
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, fname);
		pstmt.setString(2, fpass);
		pstmt.setString(3, shaPwd);
		pstmt.setString(4, bcPwd);
		
		int af=pstmt.executeUpdate();
		if(af>0)
			System.out.println("insert done");
		else
			System.out.println("insert none");
		
		response.sendRedirect("../index.jsp");
		
	}

}
