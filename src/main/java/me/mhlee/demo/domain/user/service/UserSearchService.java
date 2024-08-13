package me.mhlee.demo.domain.user.service;

import com.querydsl.jpa.impl.JPAQuery;
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

        var query = buildSearchQuery(qUsers, param);

        var count = query.select(qUsers.id.count())
                .fetchOne();
        return (count == null) ? 0 : count;
    }

    @Override
    public List<Users.Vo> search(UserParam.UserSearchParam param) {
        QUsers qUsers = QUsers.users;

        var query = buildSearchQuery(qUsers, param);

        var rows = query.select(qUsers)
                .orderBy(qUsers.id.desc())
                .offset(param.getPaging().getOffset())
                .limit(param.getPaging().getSize())
                .fetch();

        return rows.stream()
                .map(it -> it.toVo())
                .toList();
    }

    private JPAQuery<?> buildSearchQuery(QUsers qUsers, UserParam.UserSearchParam param) {
        var where = where();
        if (param.getName() != null) {
            where.and(qUsers.name.like(param.getName() + "%"));
        }

        if (param.getAge() != null) {
            where.and(qUsers.age.eq(param.getAge()));
        }

        return query()
                .from(qUsers)
                .where(where);

    }
}
