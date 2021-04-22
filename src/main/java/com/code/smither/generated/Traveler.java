/*
 * This file is generated by jOOQ.
 */
package com.code.smither.generated;


import com.code.smither.generated.tables.Client;
import com.code.smither.generated.tables.Company;
import com.code.smither.generated.tables.Department;
import com.code.smither.generated.tables.Dictionary;
import com.code.smither.generated.tables.Message;
import com.code.smither.generated.tables.Order;
import com.code.smither.generated.tables.Permit;
import com.code.smither.generated.tables.Role;
import com.code.smither.generated.tables.RolePermit;
import com.code.smither.generated.tables.UploadFile;
import com.code.smither.generated.tables.User;
import com.code.smither.generated.tables.UserRole;

import java.util.Arrays;
import java.util.List;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Traveler extends SchemaImpl {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>traveler</code>
     */
    public static final Traveler TRAVELER = new Traveler();

    /**
     * 客户（店铺商户）
     */
    public final Client CLIENT = Client.CLIENT;

    /**
     * 公司
     */
    public final Company COMPANY = Company.COMPANY;

    /**
     * 部门
     */
    public final Department DEPARTMENT = Department.DEPARTMENT;

    /**
     * 字典
     */
    public final Dictionary DICTIONARY = Dictionary.DICTIONARY;

    /**
     * 消息
     */
    public final Message MESSAGE = Message.MESSAGE;

    /**
     * 订单
     */
    public final Order ORDER = Order.ORDER;

    /**
     * 权限
     */
    public final Permit PERMIT = Permit.PERMIT;

    /**
     * 角色
     */
    public final Role ROLE = Role.ROLE;

    /**
     * 角色权限
     */
    public final RolePermit ROLE_PERMIT = RolePermit.ROLE_PERMIT;

    /**
     * 文件上传
     */
    public final UploadFile UPLOAD_FILE = UploadFile.UPLOAD_FILE;

    /**
     * 用户
     */
    public final User USER = User.USER;

    /**
     * 角色
     */
    public final UserRole USER_ROLE = UserRole.USER_ROLE;

    /**
     * No further instances allowed
     */
    private Traveler() {
        super("traveler", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.<Table<?>>asList(
            Client.CLIENT,
            Company.COMPANY,
            Department.DEPARTMENT,
            Dictionary.DICTIONARY,
            Message.MESSAGE,
            Order.ORDER,
            Permit.PERMIT,
            Role.ROLE,
            RolePermit.ROLE_PERMIT,
            UploadFile.UPLOAD_FILE,
            User.USER,
            UserRole.USER_ROLE);
    }
}