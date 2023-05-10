package com.sysmap.parrot.entities.embedded;

import com.sysmap.parrot.entities.User;
import lombok.Data;

import java.util.UUID;

@Data
public class Like {
    public UUID userId;

    public Like(UUID userId) {
        this.userId = userId;
    }
}
