package com.example.you_say_app.controller;

import java.util.Collections;
import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.example.you_say_app.model.dao.CollectionDao;
import com.example.you_say_app.model.dao.QuestionDao;
import com.example.you_say_app.model.dao.RankDao;
import com.example.you_say_app.model.dao.UserDao;
import com.example.you_say_app.model.dto.QuestionDto;
import com.example.you_say_app.model.dto.RankDto;

@Controller
public class QuizeController {

	@Autowired
	private QuestionDao questionDao;
	@Autowired
	private CollectionDao collectionDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private RankDao rankDao;

	// クイズ画面を表示する
	@GetMapping("/quize")
	public String quize(@SessionAttribute(name = "questions", required = false) List<Integer> questionsList,
			Model model, HttpSession session) {

		// 未ログインはトップへリダイレクト
		if (session.getAttribute("loginUser") == null) {
			return "redirect:/top";
		}

		// 問題リストがなければDBから取得してシャッフル
		if (questionsList == null) {
			questionsList = questionDao.getQuestionId();
			Collections.shuffle(questionsList);
			session.setAttribute("questions", questionsList);
		}

		// 問題が尽きたらトップへ
		if (questionsList.isEmpty()) {
			session.removeAttribute("questions");
			return "redirect:/";
		}

		// 先頭の問題を画面に渡す
		model.addAttribute("question", questionDao.putInQuestion(questionsList.get(0)));
		return "quize";
	}

	// 回答を受け取って正誤判定する
	@PostMapping("/quize/check")
	public String checkQuize(@RequestParam("userAnswer") String userAnswer,
			@RequestParam("questionId") int questionId, HttpSession session, Model model,
			@SessionAttribute(name = "questions", required = false) List<Integer> questionsList) {

		// 未ログインならトップへ
		if (session.getAttribute("loginUser") == null) {
			return "redirect:/top";
		}
		// 出題リストがなければクイズ開始へ
		if (questionsList == null) {
			return "redirect:/quize";
		}
		// 出題が残っていなければセッションを消してトップへ
		if (questionsList.size() == 0) {
			session.removeAttribute("questions");
			return "redirect:/";
		}

		boolean isCorrect;
		model.addAttribute("userAnswer", userAnswer);       // ユーザーの回答をビューに渡す
		model.addAttribute("questions", questionsList);     // 残りの出題リストを渡す

		// questionId から問題情報を取得
		QuestionDto questionDto = questionDao.putInQuestion(questionId);
		model.addAttribute("answer", questionDto.getAnswerText()); // 正解をビューに渡す

		// ユーザーの回答と正解を比較
		if (userAnswer.equals(questionDto.getAnswerText())) {
			// セッションからログイン中のユーザーIDを取得
			int userId = (int) session.getAttribute("loginUser");

			// 正解した名言をコレクションとしてDBに保存
			collectionDao.insertCollection(userId, questionDto.getQuoteId());

			// 現在のコレクション数を取得
			int collectionCount = collectionDao.collectionDisplay(userId).size();

			// 現在のランクIDから次のランクIDを求める
			int rankId = userDao.getUserInfo(userId).getRankId();
			int nextRankId = rankId + 1;

			// 次のランク情報を取得（存在する場合のみ判定）
			RankDto nextRank = rankDao.getRank(nextRankId);
			if (nextRank != null) {
				// 次のランクに必要なコレクション数
				int requiredQuantity = nextRank.getRequired_number();
				// 必要数に達していればランクを1つ上げる
				if (collectionCount >= requiredQuantity) {
					userDao.updateUserRank(userId, nextRankId);
				}
			}

			isCorrect = true;  // 正解
		} else {
			isCorrect = false; // 不正解
		}

		model.addAttribute("isCorrect", isCorrect); // 判定結果をビューに渡す

		return "result"; // 結果ページを表示
	}

	// 次の問題へ（先頭の問題をリストから削除してリダイレクト）
	@GetMapping("/onemore")
	public String oneMoreQuize(HttpSession session, Model model,
			@SessionAttribute(name = "questions", required = false) List<Integer> questionsList) {

		if (session.getAttribute("loginUser") == null) {
			return "redirect:/top";
		}
		if (questionsList != null && !questionsList.isEmpty()) {
			questionsList.remove(0);
		}
		return "redirect:/quize";
	}

	// メニューへ戻る（問題リストをセッションから削除）
	@GetMapping("/tomenu")
	public String toMenu(HttpSession session,
			@SessionAttribute(name = "questions", required = false) List<Integer> questionsList) {

		if (session.getAttribute("loginUser") == null) {
			return "redirect:/top";
		}
		if (questionsList != null) {
			session.removeAttribute("questions");
		}
		return "redirect:/";
	}
}