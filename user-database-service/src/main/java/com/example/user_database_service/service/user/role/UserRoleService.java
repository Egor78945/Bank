package com.example.user_database_service.service.user.role;

import lombok.RequiredArgsConstructor;
import nu.studer.sample.Tables;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRoleService {
    private final DSLContext dslContext;

    public void saveUserRole(Long userId, Long roleId){
        dslContext
                .insertInto(Tables.USER_ROLES).set(Tables.USER_ROLES.USER_ID, userId)
                .set(Tables.USER_ROLES.ROLE_ID, roleId)
                .execute();
    }

    public void removeUserRoleByUserId(long userId){
        dslContext
                .deleteFrom(Tables.USER_ROLES)
                .where(Tables.USER_ROLES.USER_ID.eq(userId))
                .execute();
    }
}
