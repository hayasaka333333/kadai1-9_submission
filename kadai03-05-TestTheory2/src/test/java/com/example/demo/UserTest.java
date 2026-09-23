package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    void 正常系_ユーザー管理コード登録参照() {
        User user = new User();
        user.setCode("U001");

        assertThat(user.getCode()).isEqualTo("U001");
    }

    @Test
    void 正常系_名前登録参照() {
        User user = new User();
        user.setName("東北 太郎");

        assertThat(user.getName()).isEqualTo("東北 太郎");
    }

    @Test
    void 正常系_年齢登録参照() {
        User user = new User();
        user.setAge(20);

        assertThat(user.getAge()).isEqualTo(20);
    }

    @Test
    void 異常系_範囲外年齢登録() {
        User user = new User();
        user.setAge(Integer.MAX_VALUE);

        assertThat(user.getAge()).isEqualTo(-1);
    }

    @Test
    void 異常系_連続設定後も範囲外年齢を返さない() {
        User user = new User();

        user.setAge(20);
        assertThat(user.getAge()).isEqualTo(20);

        user.setAge(Integer.MAX_VALUE);
        assertThat(user.getAge()).isEqualTo(-1);

        user.setAge(30);
        assertThat(user.getAge()).isEqualTo(30);

        user.setAge(Integer.MAX_VALUE);
        assertThat(user.getAge()).isEqualTo(-1);
        assertThat(user.getAge()).isEqualTo(-1);
    }
}
