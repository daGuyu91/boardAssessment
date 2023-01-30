package com.assessment.board.controller;

import com.assessment.board.logic.GamePlay;

public class GameController extends AbstractController {

    private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(GameController.class);

    protected static final String DISPLAY_BOARD = "displayboard";
    protected static final String BOARD_DISPLAY = "board/display.htm";
    protected static final String WINNER = "feedback.message.winner";


    @RequestMapping(value = "board/display.htm", method = RequestMethod.GET)
    public String createBoard(HttpSession session) {
        return DISPLAY_BOARD;
    }

    @RequestMapping(value = "board/start.htm", method = RequestMethod.POST)
    public String createBoard(HttpSession session, @RequestParam String player1, @RequestParam String player2) {

        GamePlay gamePlay = new GamePlay(player1, player2);


        session.setAttribute("gameStatus", gamePlay);

        return createRedirectViewPath(BOARD_DISPLAY);
    }

    @RequestMapping(value = "board/play.htm", method = RequestMethod.POST)
    public String play(HttpSession session, RedirectAttributes attributes, Model model, @RequestParam Integer pitIndex) {

        GamePlay gamePlay = (GamePlay) session.getAttribute("gameStatus");

        gamePlay.play(gamePlay.getCurrentPlayer(), pitIndex);

        if (gamePlay.getBoard().gameOver()){
            LOG.info("GAME OVER Winner is "+gamePlay.getWinner());
            addFeedbackMessage(attributes, WINNER, gamePlay.getWinner());
        }

        return createRedirectViewPath(BOARD_DISPLAY);
    }
}
