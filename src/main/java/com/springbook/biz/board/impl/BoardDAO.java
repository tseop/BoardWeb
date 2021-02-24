package com.springbook.biz.board.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.print.attribute.HashPrintRequestAttributeSet;

import org.springframework.stereotype.Repository;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.common.JDBCUtil;

@Repository("boardDAO")
public class BoardDAO {

	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;

	private final String BOARD_INSERT = "INSERT INTO BOARD(SEQ,TITLE,WRITER,CONTENT) VALUES((SELECT NVL(MAX(SEQ),0)+1 FROM BOARD),?,?,?)";
	private final String UPDATE_LOG = "INSERT INTO UPDATE_LOG(SEQ,BOARDSEQ,ID,TITLENAME,TIME) VALUES((SELECT NVL(MAX(SEQ),0)+1 FROM UPDATE_LOG),?,?,?,?)";
	private final String BOARD_UPDATE = "UPDATE BOARD SET TITLE=?,CONTENT=? WHERE SEQ=?";
	private final String BOARD_DELETE = "DELETE BOARD WHERE SEQ=?";
	private final String BOARD_GET = "SELECT * FROM BOARD WHERE SEQ=?";
	private final String BOARD_LIST = "SELECT * FROM BOARD ORDER BY SEQ DESC";

	private SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private Calendar time = Calendar.getInstance();
	private String format_time1 = format1.format(time.getTime());

	public void insertBoard(BoardVO vo) {
		System.out.println("insertBoard 기능");

		conn = JDBCUtil.getConnention();

		try {
			stmt = conn.prepareStatement(BOARD_INSERT);
			stmt.setString(1, vo.getTitle());
			stmt.setString(2, vo.getWriter());
			stmt.setString(3, vo.getContent());
			stmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}

	public void updateBoard(BoardVO vo, String updatePurson) {
		System.out.println(updatePurson + "님이 " + vo.getWriter() + "님의 게시물 '" + vo.getTitle() + "' 에서 updateBoard 기능 사용 ( " + format_time1 + " )");

		conn = JDBCUtil.getConnention();

		try {
			stmt = conn.prepareStatement(BOARD_UPDATE);
			stmt.setString(1, vo.getTitle());
			stmt.setString(2, vo.getContent());
			stmt.setInt(3, vo.getSeq());
			stmt.executeUpdate();

			stmt = conn.prepareStatement(UPDATE_LOG);
			stmt.setInt(1, vo.getSeq());
			stmt.setString(2, updatePurson);
			stmt.setString(3, vo.getTitle());
			stmt.setString(4, format_time1);
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}

	public void deleteBoard(BoardVO vo) {
		System.out.println("deleteBoard 기능");

		conn = JDBCUtil.getConnention();

		try {
			stmt = conn.prepareStatement(BOARD_DELETE);
			stmt.setInt(1, vo.getSeq());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}

	public BoardVO getBoard(BoardVO vo) {
		System.out.println("getBoard 기능");
		BoardVO board = null;

		try {
			conn = JDBCUtil.getConnention();
			stmt = conn.prepareStatement(BOARD_GET);
			stmt.setInt(1, vo.getSeq());
			rs = stmt.executeQuery();

			if (rs.next()) {
				board = new BoardVO();
				board.setSeq(rs.getInt("SEQ"));
				board.setTitle(rs.getString("TITLE"));
				board.setWriter(rs.getString("WRITER"));
				board.setContent(rs.getString("CONTENT"));
				board.setRegDate(rs.getDate("REGDATE"));
				board.setCnt(rs.getInt("CNT"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
		return board;
	}

	public List<BoardVO> getBoardList(BoardVO vo) {
		System.out.println("getBoardList 기능");
		List<BoardVO> boardList = new ArrayList<BoardVO>();

		try {
			conn = JDBCUtil.getConnention();
			stmt = conn.prepareStatement(BOARD_LIST);
			rs = stmt.executeQuery();
			while (rs.next()) {
				BoardVO board = new BoardVO();
				board.setSeq(rs.getInt("SEQ"));
				board.setTitle(rs.getString("TITLE"));
				board.setWriter(rs.getString("WRITER"));
				board.setContent(rs.getString("CONTENT"));
				board.setRegDate(rs.getDate("REGDATE"));
				board.setCnt(rs.getInt("CNT"));
				boardList.add(board);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
		return boardList;
	}

//	//전체 게시글 수 조회 스프링 퀵 스타터 217p 참조
//	public int getBoardTotalCount(BoardVO vo) {
//		String BOARD_TOT_COUNT = "SELECT COUNT(*) FROM BOARD";
//		int count = JdbcTemplate.queryForInt(BOARD_TOT_COUNT);
//		System.out.println("전체 게시글 수 : " + count + "건");
//	}
}
