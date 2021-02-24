package com.springbook.view.board;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.springbook.biz.board.BoardService;
import com.springbook.biz.board.BoardVO;

@Controller
@SessionAttributes("board")
public class BoardController {
	@Autowired
	private BoardService boardservice;

	// 글 등록
	@RequestMapping(value = "/insertBoard.do")
	public String InsertBoard(BoardVO vo) {
		boardservice.insertBoard(vo);
		return "redirect:getBoardList.do"; // return "getBoardList.do";
	}

	// 글 수정
	@RequestMapping(value = "/updateBoard.do")
	public String updateBoard(@ModelAttribute("board") BoardVO vo) {
		System.out.println("번호 : " + vo.getSeq());
		System.out.println("제목 : " + vo.getTitle());
		System.out.println("작성자 : " + vo.getWriter());
		System.out.println("내용 : " + vo.getContent());
		System.out.println("등록일 : " + vo.getRegDate());
		System.out.println("조회수 : " + vo.getCnt());

		boardservice.updateBoard(vo);
		return "getBoardList.do"; // return "getBoardList.do";
	}

	// 글 삭제
	@RequestMapping(value = "/deleteBoard.do")
	public String deleteBoard(BoardVO vo) {

		boardservice.deleteBoard(vo);
		return "getBoardList.do";// return "getBoardList.do";
	}

	// 검색 조건 목록 설정
	@ModelAttribute("conditionMap")
	public Map<String, String> searchConditionMap() {
		Map<String, String> conditionMap = new HashMap<String, String>();
		conditionMap.put("제목", "TITLE");
		conditionMap.put("내용", "CONTENT");
		return conditionMap;
	}

	// 게시글 상세 조회
	@RequestMapping(value = "/getBoard.do")
	public String getBoard(BoardVO vo, Model model) {
		model.addAttribute("board", boardservice.getBoard(vo));
		return "getBoard.jsp";
	}

	// 게시글 리스트
	@RequestMapping(value = "/getBoardList.do")
	public String getBoardList(BoardVO vo, Model model) {
		model.addAttribute("boardList", boardservice.getBoardList(vo));
		return "getBoardList.jsp";
	}
}