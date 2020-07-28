package com.ssafy.nnd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.nnd.dto.MemberBoard;
import com.ssafy.nnd.repository.MemberBoardRepository;


@Service
public class MemberBoardService {

    @Autowired
    private MemberBoardRepository memberBoardRepository;

    public MemberBoard findBoardByIdx(Long idx) {
        return memberBoardRepository.findById(idx).orElse(new MemberBoard());
    }
}


