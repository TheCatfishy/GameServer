package gameserver.controller;

import gameserver.model.UserEndPoint;
import gameserver.websocket.ChatEndpoint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Set;

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
    @GetMapping("endpoints")
    public Collection<UserEndPoint> Endpoints() {
        return ChatEndpoint.userEndPoints.values();
    }
}
