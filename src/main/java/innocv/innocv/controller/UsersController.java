package innocv.innocv.controller;

import innocv.innocv.entity.User;
import innocv.innocv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("users")
public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    User getUser(@PathVariable Integer id, HttpServletResponse response) {
        User currentUser = userService.getUser(id);
        if (currentUser == null)
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);

        return currentUser;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PutMapping("/{id}")
    void updateUser(@RequestBody User user, @PathVariable Integer id, HttpServletResponse response) {
        User currentUser = userService.updateUser(user, id);
        if (user.equals(currentUser))
            response.setStatus(HttpServletResponse.SC_CREATED);
        else
            response.setStatus(HttpServletResponse.SC_OK);
    }

     @DeleteMapping("/{id}")
    void deleteUser(@PathVariable Integer id, HttpServletResponse response) {
         User currentUser = userService.deleteUser(id);
         if (currentUser == null)
             response.setStatus(HttpServletResponse.SC_NOT_FOUND);
         else
             response.setStatus(HttpServletResponse.SC_NO_CONTENT);
     }

}
