package com.devglan.userportal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping({"/api/users"})
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/users",method=RequestMethod.POST)
	public void test1(@RequestBody String data) {
		System.out.println(data);
	}
    
    @PostMapping("/create")
    public User create(@RequestBody User user){
    	System.out.println("User " + user);
        return userService.create(user);
    }

    @GetMapping(path = {"/{id}"})
    public User findOne(@PathVariable("id") int id){
        return userService.findById(id);
    }

    @PutMapping(path = {"/{id}"})
    public User update(@PathVariable("id") int id, @RequestBody User user){
        user.setId(id);
        return userService.update(user);
    }

    @DeleteMapping(path ={"/{id}"})
    public User delete(@PathVariable("id") int id) {
        return userService.delete(id);
    }

    @GetMapping
    public List<User> findAll(){
        //return userService.findAll();
        
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<User>> response = restTemplate.exchange(
        		  "http://localhost:8090/api/users",
        		  HttpMethod.GET,
        		  null,
        		  new ParameterizedTypeReference<List<User>>(){});
        		List<User> users = response.getBody();        
        return users;      
    }
}
