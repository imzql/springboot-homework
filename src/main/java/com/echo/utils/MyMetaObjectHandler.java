package com.echo.utils;


import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.echo.pojo.zjlAccount;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.time.LocalDateTime;

@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {

        if (metaObject.hasSetter("zjlCreateTime")){
            this.strictInsertFill(metaObject, "zjlCreateTime", LocalDateTime.class, LocalDateTime.now()); // 起始版本 3.3.0(推荐使用)
        }

        if (metaObject.hasSetter("zjlCreateAccountId")){
            //session.setAttribute("account",zjlLoginDTO.getZjlAccount());
            zjlAccount account = (zjlAccount)RequestContextHolder.getRequestAttributes().getAttribute("account", RequestAttributes.SCOPE_SESSION);

            if (account != null){
                Long zjlAccountId = account.getZjlAccountId();
                this.strictInsertFill(metaObject, "zjlCreateAccountId", Long.class,zjlAccountId);
            }


        }

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        if (metaObject.hasSetter("zjlModifiedTime")){
            this.strictUpdateFill(metaObject, "zjlModifiedTime", LocalDateTime.class, LocalDateTime.now()); // 起始版本 3.3.0(推荐)
        }

        if (metaObject.hasSetter("zjlModifiedAccountId")){
            zjlAccount account = (zjlAccount)RequestContextHolder.getRequestAttributes().getAttribute("account", RequestAttributes.SCOPE_SESSION);

            if(account != null){
                Long zjlAccountId = account.getZjlAccountId();
                this.strictUpdateFill(metaObject, "zjlModifiedAccountId", Long.class,zjlAccountId);
            }

        }
    }
}