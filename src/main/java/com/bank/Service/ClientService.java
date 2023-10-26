package com.bank.Service;

import com.bank.DAO.ClientDAOImpl;
import com.bank.Entity.Client;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ClientService {
    @Inject
    private ClientDAOImpl ClientDao;

    public boolean addClient(Client clt){
        boolean result = ClientDao.create(clt);
        return result;
    }
    public void deleteClient(int code){
        int result = ClientDao.delete(code);
        System.out.println(String.format("*****   NOMBRE DES ELEMENTS SUPPRIMEES EST:%d   *****", result));
    }

    public List<Client> findAllClients(){
        try{
            Optional<List<Client>> listClt= ClientDao.findAll();
            return listClt.get();
//            listClt.ifPresent((list)->{
//                for(Client clt:list)
//                    System.out.println(String.format("*****   CODE[%s] NOM[%s] PRENOM[%s] DATE_NAISSANCE[%s] TELE[%s] ADRESSE[%s]  EMPLOYEE_REGISTRATION[%d]   *****", clt.getCode(), clt.getFirstName(), clt.getLastName(), clt.getBirthDay().toString(), clt.getPhone(), clt.getAddress(), clt.getEmployee().getRegistrationNbr()));
//            });
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
        return null;
    }

    public Client findClientByCode(int code){
            Optional<Client> Clt= ClientDao.findByCode(code);
            Clt.ifPresent((clt)->{
                System.out.println(String.format("*****   CODE[%s] NOM[%s] PRENOM[%s] DATE_NAISSANCE[%s] TELE[%s] ADRESSE[%s] EMPLOYEE_REGISTRATION[%d]   *****", clt.getCode(), clt.getFirstName(), clt.getLastName(), clt.getBirthDay().toString(), clt.getPhone(), clt.getAddress(), clt.getEmployee().getRegistrationNbr()));
            });
        return Clt.get();
    }

    public void updateClient(Client clt){
            ClientDao.update(clt).ifPresent((listEmp)->{
                System.out.println("*****   CLIENT MODIFIER AVEC SUCCESS   *****");
            });
    }
    public void findClientByAttribute(Client clt){
        ClientDao.find(clt).ifPresent((listClt)->{
            for(Client c:listClt){
                System.out.println(String.format("*****   CODE[%s] NOM[%s] PRENOM[%s] DATE_NAISSANCE[%s] TELE[%s] ADRESSE[%s] EMPLOYEE_REGISTRATION[%d]   *****", c.getCode(), c.getFirstName(), c.getLastName(), c.getBirthDay().toString(), c.getPhone(), c.getAddress(), clt.getEmployee().getRegistrationNbr()));
            }
        });
    }
}
