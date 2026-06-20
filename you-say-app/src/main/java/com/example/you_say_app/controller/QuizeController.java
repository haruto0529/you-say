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
import com.example.you_say_app.model.dto.QuestionDto;

@Controller
public class QuizeController {

    @Autowired
    private QuestionDao questionDao;
    @Autowired
    private CollectionDao collectionDao;

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

        if (session.getAttribute("loginUser") == null) {
            return "redirect:/top";
        }
        if (questionsList == null) {
            return "redirect:/quize";
        }
        if (questionsList.size() == 0) {
            session.removeAttribute("questions");
            return "redirect:/";
        }

        boolean isCorrect;
        model.addAttribute("userAnswer", userAnswer);
        model.addAttribute("questions", questionsList);
        QuestionDto questionDto = questionDao.putInQuestion(questionId);
        model.addAttribute("answer", questionDto.getAnswerText());

        // ユーザーの回答と正解を比較
        if (userAnswer.equals(questionDto.getAnswerText())) {
        	int userId = (int) session.getAttribute("loginUser");
        	//正解した場合DBに保存
        	collectionDao.insertCollection(userId,questionDto.getQuoteId());
            isCorrect = true;
        } else {
            isCorrect = false;
        }
        model.addAttribute("isCorrect", isCorrect);

        return "result";
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