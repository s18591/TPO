package com.company.repositories;

import com.company.dtos.UserDTO;

import java.util.List;


public interface IUserRepository extends IRepository<UserDTO>
{
    List<UserDTO> findByName(String username);
}