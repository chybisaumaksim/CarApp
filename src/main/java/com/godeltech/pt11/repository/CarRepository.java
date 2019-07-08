package com.godeltech.pt11.repository;

import com.godeltech.pt11.entity.Car;
import com.godeltech.pt11.entity.Colour;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {

    List<Car> findByColour(Colour colour);
}
