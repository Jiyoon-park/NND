package com.ssafy.nnd.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.nnd.dto.BasicResponse;
import com.ssafy.nnd.dto.Contest;
import com.ssafy.nnd.dto.LoginVo;
import com.ssafy.nnd.dto.Member;
import com.ssafy.nnd.dto.MemberBoard;
import com.ssafy.nnd.dto.SignupRequest;
import com.ssafy.nnd.repository.MemberRepository;
import com.ssafy.nnd.service.JwtService;



@CrossOrigin
@RestController
public class ContestController {

	@Autowired
	private JwtService jwtService;

	//	@GetMapping("/contest/{id}")
	//	public Object getcontest(@PathVariable long id){
	//		return id;
	//	}

	@GetMapping("/contest")
	public  List<Contest> getAllcontest() throws Exception {
		List<Contest> list = new LinkedList<Contest>();
		boolean flag = false; //마감flag
		for (int i = 1; i <= 3; i++) {
			String url = "https://www.thinkcontest.com/Contest/CateField.html?page="+i+"&c=12";
			Document doc = Jsoup.connect(url).get();
			doc.select("div.all-contest");
			Elements contents = doc.select("table tbody tr");

			for(Element content : contents){
				Contest contest = new Contest();
				contest.setTitle(content.select("a").text());
				String DetailLink = "https://www.thinkcontest.com"+content.select("a").attr("href");
				Document doc2 = Jsoup.connect(DetailLink).get();
				String str = doc2.select("div.poster-holder").attr("onclick");
				StringTokenizer s = new StringTokenizer(str,"'");
				s.nextToken();
				contest.setPoster("https://www.thinkcontest.com/Contest/"+s.nextToken());

				Elements details = doc2.select("table.type-5").select("tbody tr");
				for (Element detail : details) {
					String key = detail.select("th").text();
					if(key.equals("주관"))
						contest.setSubject(detail.select("td").text());
					else if(key.equals("후원/협찬"))
						contest.setSupport(detail.select("td").text());
					else if(key.equals("접수방법"))
						contest.setApply(detail.select("td").text());
					else if(key.equals("참가자격"))
						contest.setQua(detail.select("td").text());
					else if(key.equals("1등 시상금"))
						contest.setPrice(detail.select("td").text());
					else if(key.equals("홈페이지"))
						contest.setLink(detail.select("td a").attr("href"));
					else if(key.equals("응모분야"))
						contest.setField(detail.select("td").text());
				}


				Elements tdContents = content.select("td");
				contest.setHost(tdContents.get(1).text());
				contest.setType(tdContents.get(2).select("span.labeling").text());
				contest.setDday(tdContents.get(2).select("p").text());
				contest.setTerm(tdContents.get(3).text());

				//			System.out.println("title = "+content.select("a").text());
				//			System.out.println("link = "+content.select("a").attr("href"));
				//			System.out.println(contest.getPoster());
				//			System.out.println("host = "+tdContents.get(1).text());
				//			System.out.println("tpye = "+tdContents.get(2).select("span.labeling").text());
				//			System.out.println("dday = "+tdContents.get(2).select("p").text());
				//			System.out.println("term = "+tdContents.get(3).text());
				if(contest.getType().equals("마감"))
				{
					flag = true;
					break;
				}
				list.add(contest);
			}
			if(flag)
				break;
		}
		return list;
	}
}


