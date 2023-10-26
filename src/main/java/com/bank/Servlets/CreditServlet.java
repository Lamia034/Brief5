package com.bank.Servlets;

import com.bank.Entity.Client;
import com.bank.Enum.CreditStatus;
import com.bank.Service.AgencyService;
import com.bank.Service.ClientService;
import com.bank.Service.CreditService;
import com.bank.Service.EmployeeService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import com.bank.Entity.Credit;

import java.io.IOException;

@WebServlet("/")
public class CreditServlet extends HttpServlet {
    @Inject
    private Credit credit;
    @Inject
    private ClientService clientService;
    @Inject
    private EmployeeService employeeService;
    @Inject
    private CreditService creditService;
    @Inject
    private AgencyService agencyService;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("clients", clientService.findAllClients());
        request.setAttribute("agencies", agencyService.find());
        request.setAttribute("emploies", employeeService.findAll());
        request.getRequestDispatcher("/credit.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            System.out.println("duration:"+request.getParameter("duration"));
            System.out.println("amount:"+request.getParameter("amount"));
            credit.setLoanTax(
                    Double.parseDouble(request.getParameter("tax"))
            );
            System.out.println(credit.getLoanTax());
            credit.setStatus(CreditStatus.PENDING);
            System.out.println(credit.getStatus());
            credit.setDuration(
                    Integer.parseInt(request.getParameter("duration"))
            );
            System.out.println(credit.getDuration());
            credit.setValue(
                    Integer.parseInt(
                            request.getParameter("amount")
                    )
            );
            System.out.println(credit.getValue());
            credit.setClient(
                    clientService.findClientByCode(
                            Integer.parseInt(request.getParameter("client"))
                    )
            );
            credit.setRemark(
                    request.getParameter("remark")
            );
            credit.setEmployee(
                    employeeService.findByRegistrationNbr(
                            Integer.parseInt(request.getParameter("employee"))
                    )
            );
            credit.setAgency(
                    agencyService.findByCode(
                            request.getParameter("agency")
                    )
            );
            if(creditService.addCredit(credit) != null){

                    request.setAttribute("created", true);

                request.setAttribute("credits", creditService.findAll());
                request.getRequestDispatcher("/ListCredits.jsp").forward(request, response);
            }
        }catch(Exception e){
            request.setAttribute("created", false);
            request.setAttribute("error", "Impossible de créer le crédit...valeurs invalides");
            request.setAttribute("clients", clientService.findAllClients());
            request.setAttribute("agencies", agencyService.find());
            request.setAttribute("emploies", employeeService.findAll());
            request.getRequestDispatcher("/credit.jsp").forward(request, response);
        }
    }
}