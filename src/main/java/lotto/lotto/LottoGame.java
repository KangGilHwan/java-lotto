package lotto.lotto;

import static spark.Spark.*;

import lotto.lotto.domain.*;
import lotto.lotto.view.Input;
import lotto.lotto.view.View;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoGame {
    public static void main(String[] args) {
        int money = Input.inputMoney();
        int manualNumber = Input.inputManualNumber();
        List<String> manualLotto = Input.inputManualLotto(manualNumber, money);

        BuyingLotto man = new BuyingLotto(money, manualLotto);
        LottoDAO lottoDao = LottoDAO.getInstance();
        List<Lotto> lottos = man.getLottos();
        try {
            lottoDao.insertLottos(lottos);
        } catch (Exception e){
            e.getMessage();
        }
        View.lottoAutomaticView(lottos, manualNumber);
        String winningLotto = Input.inputWinningNumber();
        int bonusBall = Input.inputBonus();

        WeeklyLotto week = WeeklyLotto.of(winningLotto, bonusBall);
        Map<Rank, Integer> result = week.checkRank(lottos);
        ResultDTO resultLotto = new ResultDTO(result, money);
        try {
            lottoDao.insertRank(result);
        } catch (Exception e){
            e.getMessage();
        }

        View.resultLottoView(result);
        View.incomeMoney(resultLotto);
    }
}
