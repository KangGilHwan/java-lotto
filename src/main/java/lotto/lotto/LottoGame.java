package lotto.lotto;

import lotto.lotto.domain.BuyingLotto;
import lotto.lotto.domain.Lotto;
import lotto.lotto.domain.Rank;
import lotto.lotto.domain.WeeklyLotto;
import lotto.lotto.view.Input;
import lotto.lotto.view.View;

import java.util.HashMap;
import java.util.List;

public class LottoGame {
    public static void main(String[] args) {
        int money = Input.inputMoney();

        BuyingLotto man = new BuyingLotto(money);
        List<Lotto> lottos = man.getLottos();

        View.lottoAutomaticView(lottos);
        String winningLotto = Input.inputWinningNumber();

        WeeklyLotto week = new WeeklyLotto(winningLotto);
        HashMap<Rank, Integer> result = week.checkRank(lottos);

        View.resultLottoView(result);
    }
}
