/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.rmt.filter;

import com.supinfo.rmt.entity.Employee;
import com.supinfo.rmt.entity.Manager;
import com.supinfo.rmt.entity.RmtUser;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.faces.context.FacesContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ccong
 */
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        System.out.println("AuthenticationFilter");
        String userType = (String) httpRequest.getParameter("userType");
        if (userType == null || userType.isEmpty()) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.xhtml");
            return;
        }
        if (userType.equals("EMPLOYEE")) {
            httpResponse.sendRedirect("employee/employee_home.xhtml");
        } else if (userType.equals("MANAGER")) {
            httpResponse.sendRedirect("manager/manager_home.xhtml");
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
