package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserManagerTest {

    private UserManager manager;

    @BeforeEach
    void テスト前処理() {
        manager = UserManager.getInstance();
        manager.deleteAllUser();
    }

    @Test
    void 正常系_UserManagerインスタンス同一() {
        UserManager first = UserManager.getInstance();
        UserManager second = UserManager.getInstance();

        assertThat(first).isSameAs(second);
    }

    @Test
    void 正常系_userList登録参照() {
        User user1 = createUser("U001", "東北 太郎", 20);
        User user2 = createUser("U002", "東北 花子", 21);

        manager.setUserToList(user1);
        manager.setUserToList(user2);

        assertThat(manager.getUserList()).contains(user1, user2);
    }

    @Test
    void 正常系_userMap登録参照() {
        User user1 = createUser("U001", "東北 太郎", 20);
        User user2 = createUser("U002", "東北 花子", 21);

        manager.setUserToMap(user1);
        manager.setUserToMap(user2);

        assertThat(manager.getUserMap())
                .containsEntry("U001", user1)
                .containsEntry("U002", user2);
    }

    @Test
    void 正常系_user全削除() {
        User user1 = createUser("U001", "東北 太郎", 20);
        User user2 = createUser("U002", "東北 花子", 21);
        registerToBoth(user1, user2);

        manager.deleteAllUser();

        assertThat(manager.getUserList()).isEmpty();
        assertThat(manager.getUserMap()).isEmpty();
    }

    @Test
    void 正常系_code指定user削除() {
        User user1 = createUser("U001", "東北 太郎", 20);
        User user2 = createUser("U002", "東北 花子", 21);
        User user3 = createUser("U003", "東北 次郎", 22);
        registerToBoth(user1, user2, user3);

        manager.deleteUser("U002");

        assertThat(manager.getUserList()).containsExactly(user1, user3);
        assertThat(manager.getUserMap())
                .containsOnlyKeys("U001", "U003")
                .doesNotContainKey("U002");
    }

    @Test
    void 異常系_同値別インスタンスの管理コードでも削除できる() {
        User user1 = createUser("U001", "東北 太郎", 20);
        User user2 = createUser("U002", "東北 花子", 21);
        registerToBoth(user1, user2);

        manager.deleteUser(new String("U002"));

        assertThat(manager.getUserList()).containsExactly(user1);
        assertThat(manager.getUserMap())
                .containsOnlyKeys("U001")
                .doesNotContainKey("U002");
    }

    @Test
    void 正常系_MapList初期生成() {
        assertThat(manager.getUserList()).isNotNull().isEmpty();
        assertThat(manager.getUserMap()).isNotNull().isEmpty();
    }

    @Test
    void 正常系_List登録順序保持() {
        User user1 = createUser("U001", "東北 太郎", 20);
        User user2 = createUser("U002", "東北 花子", 21);
        User user3 = createUser("U003", "東北 次郎", 22);

        manager.setUserToList(user1);
        manager.setUserToList(user2);
        manager.setUserToList(user3);

        assertThat(manager.getUserList()).containsExactly(user1, user2, user3);
    }

    @Test
    void 正常系_Mapキー確認() {
        User user1 = createUser("U001", "東北 太郎", 20);
        User user2 = createUser("U002", "東北 花子", 21);
        User user3 = createUser("U003", "東北 次郎", 22);

        manager.setUserToMap(user1);
        manager.setUserToMap(user2);
        manager.setUserToMap(user3);

        assertThat(manager.getUserMap()).containsOnlyKeys("U001", "U002", "U003");
    }

    private User createUser(String code, String name, int age) {
        User user = new User();
        user.setCode(code);
        user.setName(name);
        user.setAge(age);
        return user;
    }

    private void registerToBoth(User... users) {
        for (User user : users) {
            manager.setUserToList(user);
            manager.setUserToMap(user);
        }
    }
}
