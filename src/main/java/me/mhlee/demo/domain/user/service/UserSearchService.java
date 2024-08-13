package me.mhlee.demo.domain.user.service;

import me.mhlee.demo.domain.QueryService;
import me.mhlee.demo.domain.user.IUserSearch;
import me.mhlee.demo.domain.user.QUsers;
import me.mhlee.demo.domain.user.UserParam;
import me.mhlee.demo.domain.user.Users;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional(readOnly = true)
public class UserSearchService extends QueryService implements IUserSearch {

    @Override
    public long count(UserParam.UserSearchParam param) {
        QUsers qUsers = QUsers.users;

        var where = where();
        if (param.getName() != null) {
            where.and(qUsers.name.like(param.getName() + "%"));
        }

        if (param.getAge() != null) {
            where.and(qUsers.age.eq(param.getAge()));
        }

        var count = query().select(qUsers.id.count())
                .from(qUsers)
                .where(where)
                .fetchOne();
        return (count == null) ? 0 : count;
    }

    @Override
    public List<Users.Vo> search(UserParam.UserSearchParam param) {
        QUsers qUsers = QUsers.users;

        var where = where();
        if (param.getName() != null) {
            where.and(qUsers.name.like(param.getName() + "%"));
        }

        if (param.getAge() != null) {
            where.and(qUsers.age.eq(param.getAge()));
        }

        var rows = query().select(qUsers)
                .from(qUsers)
                .where(where)
                .fetch();

        return rows.stream()
                .map(it -> it.toVo())
                .toList();
    }
}
