package tw.com.ispan.spot.controller;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.ispan.spot.domain.Members;
import tw.com.ispan.spot.service.MembersService;

@RestController
@RequestMapping("/api/members")
public class MemberContoller {
	@Autowired
	private MembersService membersService;

	@PostMapping("/login")
	public String login(@RequestBody String json) {
		JSONObject responseJson = new JSONObject();

//接收使用者輸入的資料
		JSONObject obj = new JSONObject(json);
		String name = obj.isNull("name") ? null : obj.getString("name");
		String password = obj.isNull("password") ? null : obj.getString("password");

//驗證使用者輸入的資料
		if(name==null || name.length()==0 || password==null || password.length()==0) {
			responseJson.put("message", "請輸入帳號與密碼");
			responseJson.put("success", false);
			return responseJson.toString();
		}
		
//呼叫企業邏輯程式
		Members bean = membersService.login(name, password);

//根據執行結果呼叫View
		if(bean==null) {
			responseJson.put("message", "登入失敗");
			responseJson.put("success", false);
		} else {
			responseJson.put("message", "登入成功");
			responseJson.put("success", true);
		}
		return responseJson.toString();
	}
	
    @GetMapping
    public ResponseEntity<?> find() {
        List<Members> members = membersService.find();
        if(members==null || members.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(members);
		}
    }

    @DeleteMapping("/{pk}")
    public String remove(@PathVariable(name = "pk") Integer memberId) {
        JSONObject responseJson = new JSONObject();

        if(memberId==null) {
            responseJson.put("message", "MemberId是必要欄位");
            responseJson.put("success", false);
        } else if(!membersService.exists(memberId)) {
            responseJson.put("message", "MemberId不存在");
            responseJson.put("success", false);
        } else {
            boolean result = membersService.remove(memberId);
            if(result==false) {
                responseJson.put("message", "刪除失敗");
                responseJson.put("success", false);
            } else {
                responseJson.put("message", "刪除成功");
                responseJson.put("success", true);
            }
        }
        return responseJson.toString();
    }

    @PutMapping("/{pk}")
    public String modify(@PathVariable(name = "pk") Integer memberId, @RequestBody String body) {
        JSONObject responseJson = new JSONObject();

        if(memberId==null) {
            responseJson.put("message", "MemberId是必要欄位");
            responseJson.put("success", false);
        } else if(!membersService.exists(memberId)) {
            responseJson.put("message", "MemberId不存在");
            responseJson.put("success", false);
        } else {
            Members member = membersService.modify(body);
            if(member==null) {
                responseJson.put("message", "修改失敗");
                responseJson.put("success", false);
            } else {
                responseJson.put("message", "修改成功");
                responseJson.put("success", true);
            }
        }

        return responseJson.toString();
    }
	
    @PostMapping
    public String create(@RequestBody String json) {
        JSONObject responseJson = new JSONObject();

        JSONObject obj = new JSONObject(json);
        Integer memberId = obj.isNull("memberId") ? null : obj.getInt("memberId");
        
        if(memberId!=null && membersService.exists(memberId)) {
            responseJson.put("message", "MemberId已存在");
            responseJson.put("success", false);
        } else {
            Members member = membersService.create(json);
            if(member==null) {
                responseJson.put("message", "新增失敗");
                responseJson.put("success", false);
            } else {
                responseJson.put("message", "新增成功");
                responseJson.put("success", true);
            }
        }
        
        return responseJson.toString();
    }
}
