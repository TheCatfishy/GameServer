package gameserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(value = "/api/game")
public class GameController {

    public GameController(){
        System.out.println("GameController CTOR");
    }
    @GetMapping()
    public String Index(){
        return "{hoi:yes}";
    }

}
