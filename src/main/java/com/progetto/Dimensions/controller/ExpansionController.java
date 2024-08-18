package com.progetto.Dimensions.controller;

import com.progetto.Dimensions.repository.CardRepository;
import com.progetto.Dimensions.request.ColorRequest;
import com.progetto.Dimensions.response.CardResponse;
import com.progetto.Dimensions.service.ExpansionService;
import com.progetto.Dimensions.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins="*",maxAge = 3600)
@RequestMapping("/expansions")
@RestController
public class ExpansionController {
    @Autowired
    private ExpansionService expansionService;

    @Autowired
    private JwtService jwtService;

    @GetMapping("/AllExpansions")
    public ResponseEntity<?> expansionFindCards() {
        return ResponseEntity.ok().body(expansionService.expansionFindCards());
    }

    @GetMapping("/myExpansion")
    public ResponseEntity<?> myCards(HttpServletRequest httpServletRequest) {
        String header = httpServletRequest.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            Long userID = jwtService.extractUserID(token);
            return ResponseEntity.ok(expansionService.expansionFindCards(userID));
        }
        return ResponseEntity.badRequest().body(new ArrayList<>());
    }

    @PostMapping("/colorCards")
    public ResponseEntity<?> myColorCards(HttpServletRequest httpServletRequest, @RequestBody ColorRequest colorRequest) {
        String header = httpServletRequest.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            Long userID = jwtService.extractUserID(token);
            boolean status = expansionService.colorCards(colorRequest, userID);
            if (status) {
                return ResponseEntity.ok(expansionService.expansionFindCards(userID));
            }
        }
        return ResponseEntity.badRequest().body(new ArrayList<>());
    }
}

