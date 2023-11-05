package com.example.rh.Controller;

import com.example.rh.Model.connections.Postgresql;
import com.example.rh.Model.contract.Contract;
import com.example.rh.Model.person.Person;
import com.example.rh.Model.position.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Connection;
import java.util.Vector;

@Controller
public class ContractController {
    @Autowired
    private Postgresql psql;

    @RequestMapping(value = "create-contract", method = RequestMethod.GET)
    public String createContract(Model model) throws Exception {
        Connection connection = psql.connect();
        Person person = new Person();
        Vector<Person> personVector = person.getAllPerson(connection);
        model.addAttribute("person", personVector);

        Position position = new Position();
        Vector<Position> positionVector = position.getAllPosition(connection);
        model.addAttribute("position", positionVector);

        connection.close();
        return "redirect:/contract.jsp";
    }

    @RequestMapping(value = "insert-contract", method = RequestMethod.POST)
    public String insertContract(@RequestParam("person") String personId,
                                 @RequestParam("position") String positionId,
                                 @RequestParam("start") String start,
                                 @RequestParam("end") String end,
                                 @RequestParam("choix") String isTest,
                                 @RequestParam("salary") double baseSalary,
                                 @RequestParam("type") String type) {
        try {
            Connection connection = psql.connect();

            java.sql.Date startDate = java.sql.Date.valueOf(start);
            java.sql.Date endDate = java.sql.Date.valueOf(end);

            Contract contract = new Contract(personId, positionId, startDate, endDate, isTest.equals("true"), baseSalary, type);
            contract.createContract(connection);

            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/create-contract";
    }

}
