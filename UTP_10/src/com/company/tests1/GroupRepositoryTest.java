package com.company.tests1;

import com.company.GroupRepository;
import com.company.dtos.GroupDTO;
import com.company.repositories.IGroupRepository;
import org.junit.Test;


public class GroupRepositoryTest extends RepositoryTestBase<GroupDTO, IGroupRepository> {

    @Test
    public void add() {
        GroupDTO groupDTO = new GroupDTO(7, "AAA", "This is the first group");
        _repository.add(groupDTO);
        System.out.println(_repository.getCount());
        System.out.println(_repository.exists(groupDTO));
    }

    @Test
    public void update() {
        GroupRepository groupRepository = new GroupRepository();
        groupRepository.beginTransaction();
        GroupDTO groupDTO = new GroupDTO(4, "DDD", "Updated group");
        groupRepository.update(groupDTO);
    }

    @Test
    public void addOrUpdate() {
        GroupRepository groupRepository = new GroupRepository();
        groupRepository.beginTransaction();
        GroupDTO groupDTO = new GroupDTO(3, "CCC", "Second group");
        groupRepository.addOrUpdate(groupDTO);
    }

    @Test
    public void delete() {
        GroupRepository groupRepository = new GroupRepository();
        groupRepository.beginTransaction();
        GroupDTO groupDTO = new GroupDTO(5, "BIG", "the biggest group");
        groupRepository.delete(groupDTO);
    }

    @Test
    public void findById() {
        GroupRepository groupRepository = new GroupRepository();
        groupRepository.beginTransaction();
        GroupDTO groupDTO = new GroupDTO(4, "BIG", "the biggest group");
        groupRepository.add(groupDTO);
        System.out.println(groupRepository.findById(4).getName());
    }

    @Test
    public void findByName() {
        GroupRepository groupRepository = new GroupRepository();
        System.out.println(groupRepository.findByName("ABC").size());
    }

    @Override
    protected IGroupRepository Create() {
        return new GroupRepository();
    }
}