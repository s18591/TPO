package com.company.tests1;

import com.company.UserRepository;
import com.company.dtos.UserDTO;
import com.company.repositories.IUserRepository;
import org.junit.Test;


public final class UserRepositoryTest extends RepositoryTestBase<UserDTO, IUserRepository> {

    @Test
    public void add() {
        UserRepository userRepository = new UserRepository();
        userRepository.beginTransaction();
        UserDTO user = new UserDTO(5, "Hito", "lox");
        userRepository.add(user);
        System.out.println(userRepository.getCount());
        System.out.println(userRepository.exists(user));
    }

    @Test
    public void update() {
        UserRepository userRepository = new UserRepository();
        userRepository.beginTransaction();
        UserDTO user = new UserDTO(4, "Pasha", "Lech");
        userRepository.update(user);
    }

    @Test
    public void addOrUpdate() {
        UserRepository userRepository = new UserRepository();
        userRepository.beginTransaction();
        UserDTO user = new UserDTO(2, "Lopi", "ASjkfku8d2");
        userRepository.addOrUpdate(user);
    }

    @Test
    public void delete() {
        UserRepository userRepository = new UserRepository();
        userRepository.beginTransaction();
        UserDTO user = new UserDTO(3, "Like", "Asgur");
        userRepository.delete(user);
    }

    @Test
    public void findById() {
        UserRepository userRepository = new UserRepository();
        userRepository.beginTransaction();
        UserDTO user = new UserDTO(4, "Misha", "RomAnchuk");
        userRepository.add(user);
        System.out.println(userRepository.findById(4).getLogin());
    }

    @Test
    public void findByName() {
        UserRepository userRepository = new UserRepository();
        System.out.println(userRepository.findByName("Denys").size());
    }

    @Override
    protected IUserRepository Create() {
        //throw new UnimplementedException();
        return null;
    }
}