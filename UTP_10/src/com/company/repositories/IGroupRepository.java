package com.company.repositories;

import com.company.dtos.GroupDTO;

import java.util.List;

public interface IGroupRepository extends IRepository<GroupDTO>
{
    List<GroupDTO> findByName(String name);
}