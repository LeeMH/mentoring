package me.mhlee.demo.domain.user;

import java.util.List;

public interface IUserSearch {
    long count(UserParam.UserSearchParam param);
    List<Users.Vo> search(UserParam.UserSearchParam param);
}
