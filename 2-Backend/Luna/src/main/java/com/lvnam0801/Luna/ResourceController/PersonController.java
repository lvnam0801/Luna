package com.lvnam0801.Luna.ResourceController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lvnam0801.Luna.ResourceRepresentation.Person;


@RestController
public class PersonController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/getPersons")
    public Person[] greeting() 
    {
        String sql = "SELECT * FROM Persons";
        Person[] persons = jdbcTemplate.query(sql, (rs, rowNum) -> new Person(rs.getInt("PersonID"), rs.getString("LastName"), rs.getString("FirstName"))).toArray(Person[]::new);
        return persons;
    }

    // @GetMapping("/addPerson")
    // public boolean addPerson(Person person) 
    // {
    //     String sql = "INSERT INTO Persons (PersonID, LastName, FirstName) VALUES (?, ?, ?)";
    //     jdbcTemplate.update(sql, person.id(), person.LastName(), person.FistName());
    //     return true;
    // }
}