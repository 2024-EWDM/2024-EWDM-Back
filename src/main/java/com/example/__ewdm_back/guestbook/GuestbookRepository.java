package com.example.__ewdm_back.guestbook;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface GuestbookRepository extends CrudRepository<Guestbook, Integer> {
    @Override
    ArrayList<Guestbook> findAll();
}
