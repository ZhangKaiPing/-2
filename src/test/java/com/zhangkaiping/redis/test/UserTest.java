package com.zhangkaiping.redis.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zhangkaiping.entity.User;
import com.zkp.utils.DateUtil;
import com.zkp.utils.RandomUtil;
import com.zkp.utils.StringUtil;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-redis.xml")
public class UserTest {

	@Autowired
	private RedisTemplate redisTemplate;
	
	@SuppressWarnings("deprecation")
	@Test
	public void testJDK() {
		List<User> list=new ArrayList<User>();
		
		for (int i = 1; i <=50000; i++) {
			User u=new User();
			//设置id
			u.setId(i);
			
			//设置姓名
			u.setName(StringUtil.randomChineseString(3));
			
			//设置性别
			String str= "男,女";
			String[] split2 = str.split(",");
			u.setSex(split2[RandomUtil.random(0, 1)]);
			
			//设置手机号
			u.setPhone("13"+RandomUtil.randomNumber(9));
			
			
			//设邮箱
			String st= "@qq.com,@163.com,@sian.com,@gmail.com,@sohu.com,@hotmail.com,@foxmail.com";
			String[] split = st.split(",");
			u.setEmail(RandomUtil.random(1,99999999)+split[RandomUtil.random(0, 6)]);
			
		
			//设置生日
			Date d1 = new Date(1949-01-01);
			d1.setYear(1949);
			d1.setMonth(01);
			d1.setDate(01);
			Date d2 = new Date(2001-12-31);
			
			Date randomDate = DateUtil.randomDate(d1,d2);
			u.setBirthday(randomDate);
			list.add(u);
			System.out.println(u);
		
		}
		
		ListOperations opsForList = redisTemplate.opsForList();
		
		long start = System.currentTimeMillis();
		
		opsForList.leftPushAll("user_jdk", list);
		
		long end = System.currentTimeMillis();
		
		System.out.println("用时"+(end-start));
		
		System.out.println("存了50000条数据");
		System.out.println("jdk方式");
	}

	
	
	@Test
	public void testJSON() {
		List<User> list=new ArrayList<User>();
		
		for (int i = 1; i <=50000; i++) {
			User u=new User();
			//设置id
			u.setId(i);
			
			//设置姓名
			u.setName(StringUtil.randomChineseString(3));
			
			//设置性别
			String str= "男,女";
			String[] split2 = str.split(",");
			u.setSex(split2[RandomUtil.random(0, 1)]);
			
			//设置手机号
			u.setPhone("13"+RandomUtil.randomNumber(9));
			
			
			//设邮箱
			String st= "@qq.com,@163.com,@sian.com,@gmail.com,@sohu.com,@hotmail.com,@foxmail.com";
			String[] split = st.split(",");
			u.setEmail(RandomUtil.random(1,99999999)+split[RandomUtil.random(0, 6)]);
			
		
			//设置生日
			Date d1 = new Date(1949-01-01);
			d1.setYear(1949);
			d1.setMonth(01);
			d1.setDate(01);
			Date d2 = new Date(2001-12-31);
			
			Date randomDate = DateUtil.randomDate(d1,d2);
			u.setBirthday(randomDate);
			list.add(u);
			System.out.println(u);
		
		}
		
		ListOperations opsForList = redisTemplate.opsForList();
		
		long start = System.currentTimeMillis();
		
		opsForList.leftPushAll("user_json", list);
		
		long end = System.currentTimeMillis();
		
		System.out.println("用时"+(end-start));
		
		System.out.println("存了50000条数据");
		System.out.println("json方式");
	}
	
	
	@Test
	public void testHash() {
		Map<String,User> hash=new HashMap<String, User>();
		
		for (int i = 1; i <=50000; i++) {
			User u=new User();
			//设置id
			u.setId(i);
			
			//设置姓名
			u.setName(StringUtil.randomChineseString(3));
			
			//设置性别
			String str= "男,女";
			String[] split2 = str.split(",");
			u.setSex(split2[RandomUtil.random(0, 1)]);
			
			//设置手机号
			u.setPhone("13"+RandomUtil.randomNumber(9));
			
			
			//设邮箱
			String st= "@qq.com,@163.com,@sian.com,@gmail.com,@sohu.com,@hotmail.com,@foxmail.com";
			String[] split = st.split(",");
			u.setEmail(RandomUtil.random(1,99999999)+split[RandomUtil.random(0, 6)]);
			
		
			//设置生日
			Date d1 = new Date(1949-01-01);
			d1.setYear(1949);
			d1.setMonth(01);
			d1.setDate(01);
			Date d2 = new Date(2001-12-31);
			
			Date randomDate = DateUtil.randomDate(d1,d2);
			u.setBirthday(randomDate);
			hash.put(i+"",u);
			System.out.println(u);
			
		
		}
		
		HashOperations opsForHash = redisTemplate.opsForHash();
		
		long start = System.currentTimeMillis();
		
		opsForHash.putAll("user_hash",hash);
		
		long end = System.currentTimeMillis();
		
		System.out.println("用时"+(end-start));
		
		System.out.println("存了50000条数据");
		System.out.println("hash方式");
	}

}
