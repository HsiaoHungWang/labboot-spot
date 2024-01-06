package tw.com.ispan.spot.service;

import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.ispan.spot.domain.Members;
import tw.com.ispan.spot.repository.MembersRepository;

@Service
@Transactional
public class MembersService {
	@Autowired
	private MembersRepository membersRepository;

	public Members create(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			Integer memberId = obj.isNull("memberId") ? null : obj.getInt("memberId");
			String name = obj.isNull("name") ? null : obj.getString("name");
			String password = obj.isNull("password") ? null : obj.getString("password");
			String email = obj.isNull("email") ? null : obj.getString("email");
			Integer age = obj.isNull("age") ? null : obj.getInt("age");
			
			if(memberId==null || !membersRepository.existsById(memberId)) {
				Members insert = new Members();
				insert.setName(name);
				insert.setPassword(password);
				insert.setEmail(email);
				insert.setAge(age);
				return membersRepository.save(insert);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Members modify(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			Integer memberId = obj.isNull("memberId") ? null : obj.getInt("memberId");
			String name = obj.isNull("name") ? null : obj.getString("name");
			String password = obj.isNull("password") ? null : obj.getString("password");
			String email = obj.isNull("email") ? null : obj.getString("email");
			Integer age = obj.isNull("age") ? null : obj.getInt("age");

			if(memberId!=null && membersRepository.existsById(memberId)) {
				Optional<Members> optional = membersRepository.findById(memberId);
				if(optional.isPresent()) {
					Members update = optional.get();
					update.setName(name);
					update.setPassword(password);
					update.setEmail(email);
					update.setAge(age);
					return membersRepository.save(update);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean remove(Integer memberId) {
		if(memberId!=null) {
			try {
				if(membersRepository.existsById(memberId)) {
					membersRepository.deleteById(memberId);
					return true;
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public Members findByMemberId(Integer memberId) {
		if(memberId!=null) {
			Optional<Members> optional = membersRepository.findById(memberId);
			if(optional.isPresent()) {
				return optional.get();
			}
		}
		return null;
	}
	
	public List<Members> find() {
		List<Members> members = membersRepository.findAll();
		if(members!=null && !members.isEmpty()) {
			return members;
		} else {
			return null;
		}
	}

	public boolean exists(Integer memberId) {
		if(memberId!=null) {
			return membersRepository.existsById(memberId);
		}
		return false;
	}

	public Members login(String name, String password) {
		if(name!=null && name.length()!=0) {
			List<Members> members = membersRepository.findByName(name);
			if(members!=null && !members.isEmpty() && password!=null && password.length()!=0) {
				Members member = members.get(0);
				String pass = member.getPassword(); //資料庫取出
				if(password.equals(pass)) {
					return member;					
				}
			}
		}
		return null;
	}
}
