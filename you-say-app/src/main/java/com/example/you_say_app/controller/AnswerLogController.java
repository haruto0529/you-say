package com.example.you_say_app.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.you_say_app.model.dao.AnswerLogDao;
import com.example.you_say_app.model.dto.HistoryDto;

import jakarta.servlet.http.HttpSession;

@Controller
public class AnswerLogController {

	@Autowired
	private AnswerLogDao answerLogDao;

	@GetMapping("/quize/history")
	public String showHistory(HttpSession session, Model model) {
		Object loginUser = session.getAttribute("loginUser");
		if (loginUser == null) {
			return "redirect:/top";
		}
		List<HistoryDto> historyList = answerLogDao.getLog((int) loginUser);
		model.addAttribute("historyList", historyList);

		//		正解数
		int correctCount = 0;

		//		一つずつ正解か確認
		for (HistoryDto historyDto : historyList) {
			//			正解ならcorrectCountを1増やす
			if (historyDto.isResult() == true) {
				correctCount += 1;
			}
		}

		double rate = correctCount / (double) historyList.size() * 100;

		//		小数第2位を四捨五入
		BigDecimal bd = new BigDecimal(String.valueOf(rate));
		BigDecimal percent = bd.setScale(1, RoundingMode.HALF_UP);
		//		正解率をmodelに入れてviewへ
		model.addAttribute("percent", percent);

		return "history";
	}

}
