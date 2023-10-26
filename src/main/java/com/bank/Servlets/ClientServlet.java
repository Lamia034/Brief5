package com.bank.Servlets;

import com.bank.Entity.Client;
import com.bank.Service.AgencyService;
import com.bank.Service.ClientService;
import com.bank.Service.EmployeeService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/client")
public class ClientServlet extends HttpServlet {
    @Inject
    private AgencyService agencyService;
    @Inject
    private EmployeeService employeeService;
    @Inject
    private Client client;
    @Inject
    private ClientService clientService;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("agencies", agencyService.find());
        request.setAttribute("emploies", employeeService.findAll());
        request.setAttribute("clients", clientService.findAllClients());
        request.getRequestDispatcher("/client.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            System.out.println(client == null);
            client.setFirstName(
                    request.getParameter("firstName")
            );
            System.out.println("firstName=>"+client.getFirstName());
            client.setLastName(
                    request.getParameter("lastName")
            );
            client.setPhone(
                    request.getParameter("phone")
            );
            client.setAddress(
                    request.getParameter("address")
            );
            System.out.println("address==>" + client.getAddress());
            client.setBirthDay(
                    LocalDate.parse(request.getParameter("birthDay"))
            );
            System.out.println("date==>" + client.getBirthDay());
            System.out.println("agency===>");
            client.setAgency(
                    agencyService.findByCode(request.getParameter("agency"))
            );
            System.out.println("employee===>");
            client.setEmployee(
                    employeeService.findByRegistrationNbr(
                            Integer.parseInt(request.getParameter("employee"))
                    )
            );
            System.out.println("client===>");
            boolean result = clientService.addClient(client);
            System.out.println("client servlet: " + result);
            if(result == false)
                throw new Exception("wa rah khaouy");
            request.setAttribute("created", true);
            request.getRequestDispatcher("/client.jsp").forward(request, response);
        }catch(Exception e){
            request.setAttribute("created", false);
            request.getRequestDispatcher("/client.jsp").forward(request, response);
        }
    }
}