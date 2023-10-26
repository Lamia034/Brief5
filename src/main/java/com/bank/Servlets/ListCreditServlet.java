package com.bank.Servlets;

import com.bank.Service.CreditService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/ListCredits")
public class ListCreditServlet extends HttpServlet {
    @Inject
    private CreditService creditService;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            request.setAttribute("credits", creditService.findAll());
            request.getRequestDispatcher("/ListCredits.jsp").forward(request, response);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}