package org.csystem.app.samples.games.template;

import org.csystem.app.samples.games.angrybirdsgame.AngryBirdsGame;
import org.csystem.app.samples.games.cargameapp.CarRaceGame;
import org.csystem.app.samples.games.randomcardgame.RandomCardGame;
import org.csystem.app.samples.games.shuffledeckgame.ShuffleDeckGame;
import org.csystem.app.samples.games.zombiegame.ZombieGame;

public class GamerRunnerApp {
    private final GameController m_gameController;

    private void initGames()
    {
        m_gameController.add(new CarRaceGame());
        m_gameController.add(new ZombieGame());
        m_gameController.add(new AngryBirdsGame());
        m_gameController.add(new ShuffleDeckGame());
        m_gameController.add(new RandomCardGame());
    }

    public GamerRunnerApp()
    {
        m_gameController = new GameController();
        this.initGames();
    }

    public void run()
    {
        m_gameController.run();
    }
}
