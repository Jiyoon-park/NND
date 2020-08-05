package com.ssafy.nnd.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssafy.nnd.dto.Letter;
import com.ssafy.nnd.repository.LetterRepository;

@CrossOrigin
@Controller
public class LetterController {
	
	@Autowired
	LetterRepository letterRepository;
	
	// R
	// 모든 메시지
	@GetMapping("/letter/list/all")
	public @ResponseBody List<Letter> getAllLetter() {
		return letterRepository.findAll();
	}
	
	@GetMapping("/letter/list/{letterno}")
	public @ResponseBody Optional<Letter> getAllLetter(@PathVariable Long letterno) {
		return letterRepository.findById(letterno);
	}
	
	// 보내는 사람 기준으로 검색
	@GetMapping("/letter/list/send/{idx}")
	public @ResponseBody List<Map<String,Object>> getLetterBySend(@PathVariable Long idx) {
		List<Object> letter = letterRepository.findBySendIdx(idx);
		
		List<Map<String, Object>> datalist = new ArrayList<Map<String,Object>>();

		for (int i = 0; i <letter.size() ; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			Object[] temp = (Object[])letter.get(i);
			map.put("name",temp[1]);
			map.put("profile",temp[3]);
			map.put("letterNo", temp[8]);
			map.put("sendIdx", temp[9]);
			map.put("receiveIdx", temp[10]);
			map.put("content", temp[11]);
			map.put("createDate", temp[12]);
			map.put("read", temp[13]);
			datalist.add(map);
		}
		return datalist;
	}
	
	// 받는 사람 기준으로 검색
	@GetMapping("/letter/list/receive/{idx}")
	public @ResponseBody List<Map<String,Object>> getLetterByReceive(@PathVariable Long idx) {
		List<Object> letter = letterRepository.findByReceiveIdx(idx);
		List<Map<String, Object>> datalist = new ArrayList<Map<String,Object>>();

		for (int i = 0; i <letter.size() ; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			Object[] temp = (Object[])letter.get(i);
			map.put("name",temp[1]);
			map.put("profile",temp[3]);
			map.put("letterNo", temp[8]);
			map.put("sendIdx", temp[9]);
			map.put("receiveIdx", temp[10]);
			map.put("content", temp[11]);
			map.put("createDate", temp[12]);
			map.put("read", temp[13]);
			datalist.add(map);
		}
		return datalist;
	}
	
	// C
	@PutMapping("/letter/create")
	public @ResponseBody String createLetter(@RequestBody Letter letter) {
		System.out.println(letter.toString());
		try {
			letterRepository.save(letter);
			return "success";
		} catch (Exception e) {
			return "error";
		}
	}
	
	// U
//	@PostMapping("/letter/update/{id}")
//	public @ResponseBody Letter updateLetter(@PathVariable String id, @RequestBody Letter newLetter) {
//		int postID = Integer.parseInt(id);
//		Optional<Letter> letter = letterRepository.findById((long) postID);
//		letter.get().setContent(newLetter.getContent());
//		letterRepository.save(letter.get());
//		return letter.get();
//	}
	
	@PostMapping("/letter/update/{letterno}")
    public @ResponseBody Letter updateLetter(@PathVariable Long letterno)
    {
    	Optional<Letter> letter = letterRepository.findById(letterno);
    	letter.get().setRead(1);
    	letterRepository.save(letter.get());
    	return letter.get();
    }
	
	
	// D
	@DeleteMapping("/letter/delete/{id}")
	public @ResponseBody String deleteLetter(@PathVariable String id) {
		int postID = Integer.parseInt(id);
		try {
			letterRepository.deleteById((long) postID);
			return "success";
		} catch (Exception e) {
			return "error";
		}
	}
	
	

}
