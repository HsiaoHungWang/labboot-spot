package tw.com.ispan.spot.service;

import java.util.List;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tw.com.ispan.spot.domain.Members;

@SpringBootTest
public class MembersServiceTests {

    @Autowired
    private MembersService membersService;

//    @Test
    public void testCreate() {
		JSONObject param = new JSONObject()
				.put("memberId", 8)
				.put("name", "test")
				.put("password", "98765432")
				.put("email", "test@gmail.com")
				.put("age", 50);
		
        Members result = membersService.create(param.toString());
        System.err.println("result="+result);
    }
    
//    @Test
    public void testModify() {
		JSONObject param = new JSONObject()
				.put("memberId", 8)
				.put("name", "testtests")
				.put("password", "12345678")
				.put("email", "testtests@gmail.com")
				.put("age", 40);
		
		Members result = membersService.modify(param.toString());
        System.err.println("result="+result);
    }

//    @Test
    public void testRemove() {
        boolean result = membersService.remove(8);
        System.err.println("result="+result);
    }
    
//    @Test
    public void testFindById() {
    	Members bean1 = membersService.findByMemberId(1);
        System.err.println("bean1="+bean1);
        Members bean2 = membersService.findByMemberId(8);
        System.err.println("bean2="+bean2);
    }
    
//    @Test
    public void testFind() {
		List<Members> find = membersService.find();
		System.err.println("find="+find);
    }

	@Test
	public void testLogin() {
		Members login1 = membersService.login(null, null);
		System.out.println("login1="+login1);
		
		Members login2 = membersService.login("Jack", "12345678");
		System.out.println("login2="+login2);

		Members login3 = membersService.login("Mary", "");
		System.out.println("login3="+login3);
	}
}
