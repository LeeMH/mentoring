package me.mhlee.demo.app.user;

import lombok.AllArgsConstructor;
import me.mhlee.demo.controller.dto.UserDto;
import me.mhlee.demo.domain.user.IUserSearch;
import me.mhlee.demo.domain.user.UserParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class UserSearchApp implements UserSearchUseCase {
    private final IUserSearch userSearchService;

    @Override
    public Page<UserDto.UserSearchRes> search(UserParam.UserSearchParam param) {
        var count = userSearchService.count(param);
        var rows = userSearchService.search(param);

        var result = rows.stream()
                .map(it -> new UserDto.UserSearchRes(it.getLoginId(), it.getName(), it.getAge()))
                .toList();

        return new PageImpl(result, param.getPaging().getPageRequest(), count);
    }
}
