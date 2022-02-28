package com.website.website.repository;

import com.website.website.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface HireMeRepository extends JpaRepository<Message, Serializable>
{}
