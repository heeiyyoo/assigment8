package com.example.assigment81.service;

import com.example.assigment81.model.User;
import com.example.assigment81.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User createUser(){
        UUID uuid = UUID.randomUUID();
        String uuidAsString = uuid.toString();
        Random rand = new Random();
        User user = new User();
        user.setAge(rand.nextInt(101));
        user.setName(uuidAsString);
        userRepository.save(user);
        return user;
    }
    public Stream<User> stream3 (){
        List<User> listStream = Arrays.asList(createUser(),createUser(),createUser());
        return listStream.stream();
    }

    public List<User> under18(){
        List<User> users = new ArrayList<>();
        Integer id = 1;
        while(userRepository.existsUserById(id)){
            users.add(userRepository.getById(id));
            id++;
        }
        return users.stream().filter(age -> age.getAge() < 18).collect(Collectors.toList());
    }

    public List<User> doubleAge(){
        List<User> users = new ArrayList<>();
        Integer id = 1;
        while(userRepository.existsUserById(id)){
            users.add(userRepository.getById(id));
            id++;
        }
        users.stream().forEach(use -> use.setAge((use.getAge() * 2)));
        id = 1;
        while(userRepository.existsUserById(id)){
            userRepository.save(users.get(id));
            id++;
        }
        return users;
    }

    public User lastelement() {
        List<User> users = new ArrayList<>();
        Integer id = 1;
        while (userRepository.existsUserById(id)) {
            users.add(userRepository.getById(id));
            id++;
        }
        return users.stream().reduce((first, second) -> second).orElse(null);
    }

    public List<User> smallestage() {
        List<User> users = new ArrayList<>();
        List<User> bigAndSmall = new ArrayList<>();
        Integer id = 1;
        while (userRepository.existsUserById(id)) {
            users.add(userRepository.getById(id));
            id++;
        }
        bigAndSmall.add(users.stream().
                min(Comparator.comparing(User::getAge)).orElseThrow(NoSuchElementException::new));
        bigAndSmall.add(users.stream().
                max(Comparator.comparing(User::getAge)).orElseThrow(NoSuchElementException::new));
        return bigAndSmall;
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor)
    {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    public List<User> distinct() {
        List<User> users = new ArrayList<>();
        Integer id = 1;
        while (userRepository.existsUserById(id)) {
            users.add(userRepository.getById(id));
            id++;
        }
        return users.stream()
                .filter( distinctByKey(p -> p.getName() + " " + p.getAge()) )
                .collect( Collectors.toList() );
    }

    public List<User> hardPart(){
        List<User> users = new ArrayList<>();
        List<User> filteredList;
        Integer id = 1;
        while (userRepository.existsUserById(id)) {
            users.add(userRepository.getById(id));
            id++;
        }
        filteredList =  users.stream().filter(age -> age.getAge() > 30).collect(Collectors.toList());
        filteredList.stream().forEach(use -> use.setName(use.getName().toUpperCase()));
        filteredList.sort(Comparator.comparing(User::getAge).reversed());
        return filteredList;
    }


    public User returnAl(Integer Id){
        return userRepository.getById(Id);
    }
}
