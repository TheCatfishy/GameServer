package gameserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.Expose;
import gameserver.websocket.ChatEndpoint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.websocket.Session;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEndPoint {
    public String user;
    @Expose(serialize = false,deserialize = false)
    @JsonIgnore
    public ChatEndpoint endPoint;
    @Expose(serialize = false,deserialize = false)
    @JsonIgnore
    public Session session;

}
