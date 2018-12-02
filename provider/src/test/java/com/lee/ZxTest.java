package com.lee;

import com.lee.dao.ZxDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Provider.class)
public class ZxTest {

    @Autowired
    ZxDao zxDao;

    @Test
    public void test1() {
        zxDao.checkEmail("1");
    }

    @Test
    public void test2(){
        System.out.println(zxDao.selectUserByEmail("sad"));
    }


}

