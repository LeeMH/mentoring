package me.mhlee.demo.domain.user;

import lombok.Setter;
import lombok.experimental.Accessors;
import me.mhlee.demo.domain.point.Points;

public class UserParam {
    @Accessors(chain = true)
    @Setter
    public static class UserAndPoint {
        public Users.Vo user;
        public Points.Vo point;
    }
}
