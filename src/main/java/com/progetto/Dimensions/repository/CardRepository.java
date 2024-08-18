package com.progetto.Dimensions.repository;

import com.progetto.Dimensions.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository <Card,Long> {

}
