package com.example.you_say_app.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.you_say_app.model.connect4.Connect4;

@Controller
@SessionAttributes("game")
public class Connect4Controller {

    @GetMapping("/minigame")
    public String showStart() {
        return "connect4";
    }

    @PostMapping("/start")
    public String showGame(
            @RequestParam("gameMode") String gameMode,
            @RequestParam(value = "firstPlayer", defaultValue = "human") String firstPlayer,
            Model model) {

        Connect4 c4;
        if (gameMode.equals("2P")) {
            c4 = new Connect4(1, true, 2, true);
        } else if (firstPlayer.equals("human")) {
            c4 = new Connect4(1, true, 2, false);
        } else {
            c4 = new Connect4(1, false, 2, true);
        }

        // コンピュータが先攻の場合、最初の手を自動で打つ
        if (!c4.isGameOver()) {
            boolean firstIsComputer = firstPlayer.equals("computer");
            if (gameMode.equals("1P") && firstIsComputer) {
                c4.dropPiece(-1);
            }
        }

        model.addAttribute("game", c4);
        return "game";
    }

    @GetMapping("/status")
    @ResponseBody
    public Map<String, Object> getStatus(@ModelAttribute("game") Connect4 game) {
        return buildResponse(game);
    }

    @PostMapping("/drop")
    @ResponseBody
    public Map<String, Object> drop(
            @ModelAttribute("game") Connect4 game,
            @RequestBody Map<String, Integer> body) {

        if (!game.isGameOver()) {
            game.dropPiece(body.get("column"));
        }

        return buildResponse(game);
    }

    @PostMapping("/restart")
    @ResponseBody
    public Map<String, Object> restart(@ModelAttribute("game") Connect4 game) {
        // 現在のゲームをリセット（新しいインスタンスはセッションに入れられないため、
        // リスタートはフロントから /start に遷移させる方が確実）
        // ここではステータスのみ返す
        return buildResponse(game);
    }

    private Map<String, Object> buildResponse(Connect4 game) {
        Map<String, Object> result = new HashMap<>();
        result.put("board", game.getBoard());
        result.put("message", game.getMessage());
        result.put("gameOver", game.isGameOver());
        result.put("computerTurn", game.isComputerTurn());
        result.put("vsComputer", game.isVsComputer());
        return result;
    }
}