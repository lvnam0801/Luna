package com.lvnam0801.Luna.Resource.Core.Party.Controller;

import com.lvnam0801.Luna.Resource.Core.Party.Representation.Party;
import com.lvnam0801.Luna.Resource.Core.Party.Representation.PartyRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/party")
public class PartyController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/get-all")
    public Party[] getParties() {
        String sql = "SELECT * FROM Party";

        Party[] parties = jdbcTemplate.query(
            sql,
            (rs, rowNum) -> new Party(
                rs.getInt("PartyID"),
                rs.getString("PartyType"),
                rs.getString("ContactName"),
                rs.getString("Phone"),
                rs.getString("Email"),
                rs.getString("Note"),
                rs.getString("Status"),
                rs.getString("StreetAddress"),
                rs.getString("City"),
                rs.getString("StateProvince"),
                rs.getString("PostalCode"),
                rs.getString("Country")
            )
        ).toArray(Party[]::new);

        return parties;
    }

    @PostMapping("/create")
    public ResponseEntity<Party> createParty(@RequestBody PartyRequest request) {
        String sql = """
            INSERT INTO Party (PartyType, ContactName, Phone, Email, Note, Status, StreetAddress, City, StateProvince, PostalCode, Country)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        jdbcTemplate.update(sql,
            request.partyType(),
            request.contactName(),
            request.phone(),
            request.email(),
            request.note(),
            request.status(),
            request.streetAddress(),
            request.city(),
            request.stateProvince(),
            request.postalCode(),
            request.country()
        );

        Integer id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        Party party = new Party(
            id,
            request.partyType(),
            request.contactName(),
            request.phone(),
            request.email(),
            request.note(),
            request.status(),
            request.streetAddress(),
            request.city(),
            request.stateProvince(),
            request.postalCode(),
            request.country()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(party);
    }
}