package donggukthon.team10.igloo.controller;

import donggukthon.team10.igloo.dto.TestLoginDTO;
import donggukthon.team10.igloo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TestController {
    private final UserService userService;
    @PostMapping("/api/auth/save")
    public String testSave(@RequestParam Long userId){
        userService.saveUser(userId);
        return "ok";
    }
    @PostMapping("/api/auth/login")
    public String testLogin(@RequestBody TestLoginDTO testLoginDTO){
        return userService.login(testLoginDTO);
    }
    @GetMapping("/api/test")
    public String testAuthentication(){
        return "success to authentication!";
    }
}
