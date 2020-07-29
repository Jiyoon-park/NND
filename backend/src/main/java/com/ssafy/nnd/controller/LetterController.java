package com.ssafy.nnd.controller;

import java.util.List;
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
	@GetMapping("/letter/read/all")
	public @ResponseBody List<Letter> getAllLetter() {
		return letterRepository.findAll();
	}
	
	// 보내는 사람 기준으로 검색
	@GetMapping("/letter/read/send/{id}")
	public @ResponseBody List<Letter> getLetterBySend(@PathVariable String id) {
		int sendIdx = Integer.parseInt(id);
		Optional<List<Letter>> letter = letterRepository.findBySendIdx(sendIdx);
		System.out.println(letter.get());
		return letter.get();
	}
	
	// 받는 사람 기준으로 검색
	@GetMapping("/letter/read/receive/{id}")
	public @ResponseBody List<Letter> getLetterByReceive(@PathVariable String id) {
		int receiveIdx = Integer.parseInt(id);
		Optional<List<Letter>> letter = letterRepository.findByReceiveIdx(receiveIdx);
		System.out.println(letter.get());
		return letter.get();
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
	@PostMapping("/letter/update/{id}")
	public @ResponseBody Letter updateLetter(@PathVariable String id, @RequestBody Letter newLetter) {
		int postID = Integer.parseInt(id);
		Optional<Letter> letter = letterRepository.findById((long) postID);
		letter.get().setContent(newLetter.getContent());
		letterRepository.save(letter.get());
		return letter.get();
	}
	
	// D
	@DeleteMapping("/member/delete/{id}")
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
