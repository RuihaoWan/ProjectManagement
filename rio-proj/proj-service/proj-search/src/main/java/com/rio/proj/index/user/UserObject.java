package com.rio.proj.index.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserObject {
    private Long id;

    private String username;

    private String token;

    private Integer position;

    void update(UserObject newObject) {
        if (newObject.getId() != null) {
            this.id = newObject.getId();
        }
        if (newObject.getUsername() != null) {
            this.username = newObject.getUsername();
        }
        if (newObject.getToken() != null) {
            this.token = newObject.getToken();
        }
        if (newObject.getPosition() != null) {
            this.position = newObject.getPosition();
        }
    }
}
