package com.bank.Service;

import com.bank.DAO.AgencyDAOImpl;
import com.bank.Entity.Agency;
import com.bank.Entity.Employee;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class AgencyService {
    @Inject
    private AgencyDAOImpl agencyDao;


    public void create(Agency agency){
        try{
            if(agency == null)
                throw new Exception("*****   L'AGENCE NE PAS ETRE NULL   *****");
            Optional<Agency> optionalAgency = agencyDao.create(agency);
            optionalAgency.ifPresent((obj)->{
                System.out.println("*****   AGENCE CREER AVEC SUCCESS   *****");
            });
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
    }

    public void delete(String code){
        try{
            if(code == "")
                throw new Exception("*****   LE CODE D'AGENCE NE PAS ETRE VIDE   *****");
            if(agencyDao.delete(code) == 1)
                System.out.println("*****   AGENCE SUPPRIMER AVEC SUCCESS   *****");
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
    }

    public void update(Agency agency){
        try{
            if(agency == null)
                throw new Exception("*****   L'AGENCE NE PAS ETRE NULL   *****");
            Optional<Agency> optionalAgency = agencyDao.update(agency);
            optionalAgency.ifPresent((obj)->{
                System.out.println("*****   AGENCE MODIFER AVEC SUCCESS   *****");
                System.out.println(String.format("***** CODE[%s] NOME[%s] ADRESSE[%s] TELEPHONE[%s]", obj.getCode(), obj.getName(), obj.getAddress(), obj.getPhone()));
            });
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
    }

    public List<Agency> find(){
        Optional<List<Agency>> optionalAgency = agencyDao.find();
        return optionalAgency.orElse(new ArrayList<Agency>());
    }

    public Agency findByAddress(String address) throws Exception {
        if(address == "")
            throw new Exception("*****   L'ADRESSE D'AGENCE NE PAS ETRE VIDE   *****");
        return agencyDao.findByAddress(address).get();
    }

    public Agency findByCode(String code) throws Exception {
        if(code == "")
            throw new Exception("*****   CODE D'AGENCE NE PAS ETRE VIDE   *****");
        return agencyDao.findByCode(code).get();
    }
}
