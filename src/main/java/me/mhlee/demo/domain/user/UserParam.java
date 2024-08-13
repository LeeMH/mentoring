package me.mhlee.demo.domain.user;

import lombok.Data;
import lombok.Setter;
import lombok.experimental.Accessors;
import me.mhlee.demo.common.Paging;
import me.mhlee.demo.domain.point.Points;
import org.springframework.lang.Nullable;

public class UserParam {
    @Accessors(chain = true)
    @Setter
    public static class UserAndPoint {
        public Users.Vo user;
        public Points.Vo point;
    }

    @Data
    @Accessors(chain = true)
    public static class UserSearchParam {
        @Nullable
        private String name;

        @Nullable
        private Integer age;

        private Paging paging = new Paging();
    }
}
