package com.bank.DAO;

import com.bank.Entity.Client;
import com.bank.Service.AgencyService;
import com.bank.Service.ClientService;
import com.bank.Service.EmployeeService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
@ApplicationScoped
class ClientDAOImplTest {
    @Inject
    private AgencyService agencyService;
    @Inject
    private EmployeeService EmployeeService;
    @Test
    public void Create(){
        try{
            Client client = new Client();
            ClientService clientService = new ClientService();
            //client.setCode(1);
            client.setFirstName("firstName");
            client.setLastName("lastName");
            client.setPhone("123456789");
            client.setAddress("address");
            client.setBirthDay(LocalDate.parse("1999-06-30"));
            client.setEmployee(
                    new EmployeeDAOImpl().findByRegistrationNbr(1).get()
            );
            client.setAgency(
                    new AgencyDAOImpl().findByCode("agency1").get()
            );
            Assertions.assertTrue(clientService.addClient(client));
        }catch(Exception e){
            e.getStackTrace();
            System.out.println(e.getMessage());
        }
    }


}