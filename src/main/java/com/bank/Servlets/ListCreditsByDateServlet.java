package com.bank.Servlets;

import com.bank.Service.CreditService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/find-date")
public class ListCreditsByDateServlet extends HttpServlet {
        @Inject
        private CreditService creditService;
        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                try {
                        System.out.println(creditService.findByDate(LocalDate.parse((request.getParameter("date")))).size());
                        if(request.getParameter("date").isEmpty())
                                response.sendRedirect("/ListCredits");
                        request.setAttribute("credits", creditService.findByDate(LocalDate.parse((request.getParameter("date")))));
                        request.getRequestDispatcher("/ListCreditsByDate.jsp").forward(request, response);
                } catch (Exception e) {
                        throw new RuntimeException(e);
                }
        }
}