package com.bank.Servlets;

import com.bank.Enum.CreditStatus;
import com.bank.Service.CreditService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/find-status")
public class ListCreditsServeletByStatus extends HttpServlet {
    @Inject
    private CreditService creditService;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if(request.getParameter("status").isEmpty())
                response.sendRedirect("/ListCredits");
            request.setAttribute("credits", creditService.findByStatus((request.getParameter("status"))));
            System.out.println(creditService.findByStatus((request.getParameter("status"))).size());
            request.getRequestDispatcher("/ListCreditsByStatus.jsp").forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String idParam = request.getParameter("id");
            String statusParam = request.getParameter("status");

            if (idParam != null && statusParam != null) {
                int id = Integer.parseInt(idParam);
                CreditStatus status = CreditStatus.valueOf(statusParam);
                creditService.updateStatus(id, status);
            } else {
                System.out.println("invalid params");
            }

            request.setAttribute("credits", creditService.findAll());
            request.getRequestDispatcher("/ListCredits.jsp").forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }






}
