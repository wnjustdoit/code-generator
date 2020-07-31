package com.greedystar.generator;

import com.greedystar.generator.invoker.Many2ManyInvoker;
import com.greedystar.generator.invoker.One2ManyInvoker;
import com.greedystar.generator.invoker.SingleInvoker;
import com.greedystar.generator.invoker.base.Invoker;

/**
 * 执行入口
 *
 * @author wangnan
 * @since 1.0.0, 2020/7/31
 */
public class Main {

    public static void main(String[] args) {
        single();
    }

    private static void single() {
        Invoker invoker = new SingleInvoker.Builder()
                .setTableName("aaa_copy")
                .setClassName("Area")
                .build();
        invoker.execute();
    }

    @Deprecated
    private static void many2many() {
        Invoker invoker = new Many2ManyInvoker.Builder()
                .setTableName("user")
                .setClassName("User")
                .setParentTableName("role")
                .setParentClassName("Role")
                .setRelationTableName("user_role")
                .setForeignKey("userId")
                .setParentForeignKey("roleId")
                .build();
        invoker.execute();
    }

    @Deprecated
    private static void one2many() {
        Invoker invoker = new One2ManyInvoker.Builder()
                .setTableName("user")
                .setClassName("User")
                .setParentTableName("office")
                .setParentClassName("Office")
                .setForeignKey("officeId")
                .build();
        invoker.execute();
    }

}
